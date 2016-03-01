package gov.guilin.job;

import gov.guilin.service.SmsService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Job - 对己完成支付的短信商品发送短信
 * 
 * @author guilin
 * @version
 */
@Component("smsJob")
@Lazy(false)
public class SmsJob {

	private static final Logger logger = Logger.getLogger(SmsJob.class);

	@Resource(name = "smsServiceImpl")
	private SmsService smsService;

	/**
	 * 对虚拟商品发送短信,需要己完成支付
	 */
	@Scheduled(cron = "${job.order_sms_send.cron}")
	public void releaseSms() {
		logger.debug("send to paid sms product start");
		
		smsService.sendSmsAndChangeOrderStatusBatch();
		logger.debug("send to paid sms product end");
	}
}
