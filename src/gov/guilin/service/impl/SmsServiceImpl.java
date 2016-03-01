/*
 * Copyright 2014 guilin. All rights reserved.
 * Support: guilin
 * License: guilin
 */
package gov.guilin.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import gov.guilin.CommonAttributes;
import gov.guilin.Page;
import gov.guilin.Pageable;
import gov.guilin.dao.OrderDao;
import gov.guilin.entity.Order;
import gov.guilin.entity.Order.OrderStatus;
import gov.guilin.entity.Order.PaymentStatus;
import gov.guilin.entity.Order.ShippingStatus;
import gov.guilin.entity.OrderItem;
import gov.guilin.service.SmsService;
import gov.guilin.service.TemplateService;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Service -短信
 * 
 * @author guilin
 * @version
 */
@Service("smsServiceImpl")
public class SmsServiceImpl extends BaseServiceImpl<Order, Long> implements SmsService {

	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;
	@Resource(name = "templateServiceImpl")
	private TemplateService templateService;

	@Resource(name = "orderDaoImpl")
	private OrderDao orderDao;

	private static final int PAGE_SIZE = 100;

	private static String templatePath;

	static {
		try {
			File guilinXmlFile = new ClassPathResource(CommonAttributes.XML_PATH).getFile();
			org.dom4j.Document document = new SAXReader().read(guilinXmlFile);
			org.dom4j.Element element = (org.dom4j.Element) document.selectSingleNode("/guilin/template[@id='sms']");
			templatePath = element.attributeValue("templatePath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Resource(name = "orderDaoImpl")
	public void setBaseDao(OrderDao orderDao) {
		super.setBaseDao(orderDao);
	}

	/**
	 * 批量将需发送短信的定单进行发送
	 */
	public void sendSmsAndChangeOrderStatusBatch() {
		Long count = orderDao.count(OrderStatus.confirmed, PaymentStatus.paid, ShippingStatus.unshipped, false);

		int pageNumCount = (int) (count / PAGE_SIZE) + 2;

		if (count > 0) {

			for (int pageNum = 1; pageNum < pageNumCount; pageNum++) {
				Pageable pageable = new Pageable(pageNum, PAGE_SIZE);
				Page<Order> pageOrders = orderDao.findPage(OrderStatus.confirmed, PaymentStatus.paid, ShippingStatus.unshipped, false, pageable);
				if (pageOrders == null) {
					continue;
				}

				List<Order> orders = pageOrders.getContent();
				if (orders == null || orders.isEmpty()) {
					continue;
				}

				for (Order order : orders) {
					this.sendSmsAndChangeOrderStatus(order);
				}
			}

		}
	}

	/**
	 * 改变虚拟商品的状态并发送短信
	 */
	public void sendSmsAndChangeOrderStatus(Order order) {
		try {
			// 己支付,但未发送短信的商品
			if (order.getPaymentStatus() == PaymentStatus.paid && order.getShippingStatus() == ShippingStatus.unshipped) {
				order.setShippingStatus(ShippingStatus.shipped);

				List<OrderItem> orderItems = order.getOrderItems();
				if (orderItems == null || orderItems.isEmpty()) {
					return;
				}

				Configuration configuration = freeMarkerConfigurer.getConfiguration();
				Template template = configuration.getTemplate(templatePath);

				for (OrderItem orderItem : orderItems) {
					// 商品为需要发送短信商品
					if (orderItem.getProduct() != null && orderItem.getProduct().getProductType() == 0) {
						String smskey = generateSmsKey();
						orderItem.setSmskey(smskey);
						// 构建发送短信模板
						Map<String, Object> model = new HashMap<String, Object>();
						model.put("orderNo", order.getSn());
						model.put("key", smskey);
						model.put("paoductName", orderItem.getProduct().getName());
						String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
						this.send(order.getPhone(), text, false);
						// this.send(order.getPhone(), "您好,你的商品码为" + smsKey +
						// ",二维码链接:http://124.227.108.106:8181/youguilin/qrcode/gen/"
						// + smsKey + ".jhtml", false);
					}

				}

				// 将二级码保存,也将订单状态进行更改
				super.update(order);
				orderDao.flush();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 添加短信发送任务
	 * 
	 * @param commString
	 *            commString
	 */
	private void addSendTask(final String commString) {
		try {
			taskExecutor.execute(new Runnable() {
				public void run() {
					connectURL(commString, serviceURL);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param 短信通客户接口测试
	 * @param sendsmsaddress
	 * @return
	 * 
	 */
	public String commandID = "3";
	public String username = "xiong";
	public String password = "888888";
	public String serviceURL = "http://124.173.70.59:8081/SmsAndMms/mt";

	public static String connectURL(String commString, String sendsmsaddress) {
		String rec_string = "";
		URL url = null;
		HttpURLConnection urlConn = null;
		try {
			url = new URL(sendsmsaddress); // 根据数据的发送地址构建URL
			urlConn = (HttpURLConnection) url.openConnection(); // 打开链接
			urlConn.setConnectTimeout(30000); // 链接超时设置为30秒
			urlConn.setReadTimeout(30000); // 读取超时设置30秒
			urlConn.setRequestMethod("POST"); // 链接相应方式为post
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);

			OutputStream out = urlConn.getOutputStream();
			out.write(commString.getBytes("UTF-8"));
			out.flush();
			out.close();

			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1) {
				sb.append((char) ch);
			}

			rec_string = sb.toString().trim();
			rec_string = URLDecoder.decode(rec_string, "UTF-8");
			rd.close();
		} catch (Exception e) {
			rec_string = "-107";
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}

		return rec_string;
	}

	// public String key(){
	//
	// }

	/**
	 * sms test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// "BGRJ6U8DSDXW"
		String key = UUID.randomUUID().toString();
		System.out.println(key.split("-")[4]);
		// SmsServiceImpl smsServiceImpl = new SmsServiceImpl();
		// smsServiceImpl.send("18101035252", key, false);
	}

	public String generateSmsKey() {
		return UUID.randomUUID().toString().split("-")[4];
	}

	public String send(String mobile, String content, boolean async) {

		String res = "";
		try {
			String commString = "Sn=" + username + "&Pwd=" + password + "&mobile=" + mobile + "&content=" + content;
			if (async) {
				addSendTask(commString);
			} else {
				res = connectURL(commString, serviceURL);
			}
		} catch (Exception e) {
			return "-10000";
		}
		// 设置返回值 解析返回值
		String resultok = "";
		// //正则表达式
		Pattern pattern = Pattern.compile("<int xmlns=\"http://tempuri.org/\">(.*)</int>");
		Matcher matcher = pattern.matcher(res);
		while (matcher.find()) {
			resultok = matcher.group(1);
		}
		return resultok;
	}

}