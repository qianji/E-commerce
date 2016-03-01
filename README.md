# 相关文档
  见git的Downloads

# 经验
* 创建数据库带上编码utf8,以防止导入数据的问题
```
create database shopxx DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
```

* 导入eclipse后，需要更换jre,工程编码为utf8

* 安装完成后，要重启tomcat

* 若搜索为乱码， server.xml中的Connector节点设置URIEncoding="UTF-8",参考如下
```
 <Connector port="80" protocol="HTTP/1.1" 
               connectionTimeout="20000" 
               redirectPort="8443" URIEncoding="UTF-8" compression="on" compressableMimeType="text/html,text/css,text/javascript" />


```

* 若修改java类后，前台展示没有生效，为缓存的原因。在管理系统中，内容-->静态化管理--> 首页/商品/文章 --> 确定，清空缓存，页面即可生效,默认缓存位置为System.getProperty("java.io.tmpdir")

* 重新安装删除/install/install_lock.conf文件,若为eclipse,去目录$WORKSPACE_PATH$\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\查看

# git操作
```
# init
mkdir /path/to/your/project
cd /path/to/your/project
git init
git remote add origin https://xghrbc1001@bitbucket.org/xcjTeadm/guilin.git

# clone
eg: git clone https://xghrbc1001@bitbucket.org/xcjTeadm/guilin.git(需将xghrbc1001换成自己的用户名)


# push to remote
git push origin master

# 首先从远程的origin的master主分支下载最新的版本到origin/master分支上,然后比较本地的master分支和origin/master分支的差别,最后进行合并
git fetch origin master
git log -p master..origin/master
git merge origin/master

# 从远程获取最新的版本到本地的tmp分支上,之后再进行比较合并
git fetch origin master:tmp
git diff tmp 
git merge tmp

# reset
staging --> untrack

# git add/rm
unstrack--> staging

# git commit
staaging --> staged

# git push
staged --> server

# git pull
server  --> staged

# git tag
git tag -a 0.0.1 -m 'Realease version'
git push --tags
git tag -d 0.0.1
git push :refs/tags/0.0.1

# git checkout [path/.]
remove from unstrack,staging,staged

# git diff tool
git config --global diff.tool meld
git config --global merge.tool meld

```

# 改造工程相关
* 很多类中写死了路径，eg :CaptchaEngine.java,替换/net/shopxx/

# 需求
## 客户需求
* 做一个类似trip.taobao.com
* 对旅行
* 支持多供应商 
* 在淘宝的网址 http://gllygf.tmall.com

## www.youguilin.com.cn 后台帐号
* 网址： http://supplier.youguilin.com.cn/login.aspx?tip=nologin&time=1398358085329
* 旅行社:用户名:LXS合众    密码:123456
* 景点:用户名:JQ漓江       密码:123456
* 酒店:用户名:JD金嗓子     密码:123456

## 可参考的一些网址
* www.nuomi.com 订单流程可参考，实体虚拟商品都有
* www.zuitu.com 供应商管理，可参考这个

## 演示服务器
* http://124.227.108.106:8181/youguilin/
* rdesktop -u administrator -p password  124.227.108.106:1918

# Schedule

## 一期（除机票外的完整的流程演示给客户看)
### 订单
* 门票，酒店在下单时，不需要地址,配送方式(也可以把短信，身份证看成配送方式)
* 用户中心，若为门票，用户可以重新发送短信
* 用户中心，订单详情，会显示二维码的数字串
* 订单，添加状态，己使用
* 管理系统，订单管理，若为虚拟商品，无需物流,发货，为发送短信
* 参考实现方式：可通过商品与物流进行绑定。shopxx的配送方式是与支付方式绑定的
* 考虑与机票,火车票的扩展，机票只接入中航信的接口

### 供应商
### 硬件

# 项目相关
* 发送短信 SmsService.java
* 邮件 youguiling123@163.com  youguiling smtp.163.com  25

# 程序相关　
## eclipse导入maven的web项目却显示不是web项目怎么办
* mvn eclipse:eclipse -Dwtpversion=1.0

## SampleController
```
/**
* http://localhost:8080/guilin/hello/view.jhtml?username=test
**/
```
见SampleController
template/shop下新建hello/world/view.ftl
	
## img lazyload
* http://www.appelsiini.net/projects/lazyload

## 轮播
* http://www.bootcss.com/p/unslider/
* 广告位代码,广告位大小: 965*270
```
<ul>
	[#list adPosition.ads as ad]
		[#if ad.hasBegun() && !ad.hasEnded() && ad.type == "image"]
			[#if ad.url??]
				<li><a href="${ad.url}">
					<img src="${ad.path}" width="${adPosition.width}" height="${adPosition.height}" alt="${ad.title}" title="${ad.title}" />
				</a></li>
			[#else]
				<li><img src="${ad.path}" width="${adPosition.width}" height="${adPosition.height}" alt="${ad.title}" title="${ad.title}" /></li>
			[/#if]
		[/#if]
	[/#list]
</ul>
```

## 支付插件
```
1、参考并复制net.shopxx.plugin.alipayDirect包
2、重命名包名称、插件类(AlipayDirectPlugin.java)名称、配置控制器类(AlipayDirectController.java)名称、配置视图(setting.ftl)名称
3、修改插件类的@Component注解值并保证其惟一
4、修改插件类实现方法，具体如下：
/**
* 获取插件名称
*/
public abstract String getName();

/**
* 获取插件版本
*/
public abstract String getVersion();

/**
* 获取插件作者
*/
public abstract String getAuthor();

/**
* 获取插件网址
*/
public abstract String getSiteUrl();

/**
* 获取安装URL，管理后台插件安装链接地址，若不需要安装功能返回null，若需要该功能，需编写相应配置控制器类
*/
public abstract String getInstallUrl();

/**
* 获取卸载URL，管理后台插件卸载链接地址，若不需要卸载功能返回null，若需要该功能，需编写相应配置控制器类
*/
public abstract String getUninstallUrl();

/**
* 获取设置URL，管理后台插件设置链接地址，若不需要设置功能返回null，若需要该功能，需编写相应配置控制器类
*/
public abstract String getSettingUrl();

/**
* 获取请求URL，跳转到第三方支付界面的URL
*/
public abstract String getRequestUrl();

/**
* 获取请求方法，跳转到第三方支付界面的请求方法
*/
public abstract RequestMethod getRequestMethod();

/**
* 获取请求字符编码，跳转到第三方支付界面的字符编码
*/
public abstract String getRequestCharset();

/**
* 获取请求参数，跳转到第三方支付界面的请求参数
* 
* @param sn
*            编号
* @param description
*            描述
* @param request
*            httpServletRequest
* @return 请求参数
*/
public abstract Map<String, Object> getParameterMap(String sn, String description, HttpServletRequest request);

/**
* 验证通知是否合法，验证第三方支付完成后的返回结果是否合法
* 
* @param sn
*            编号
* @param notifyMethod
*            通知方法
* @param request
*            httpServletRequest
* @return 通知是否合法
*/
public abstract boolean verifyNotify(String sn, NotifyMethod notifyMethod, HttpServletRequest request);

/**
* 获取通知返回消息，收到第三方支付完成后的返回结果的响应
* 
* @param sn
*            编号
* @param notifyMethod
*            通知方法
* @param request
*            httpServletRequest
* @return 通知返回消息
*/
public abstract String getNotifyMessage(String sn, NotifyMethod notifyMethod, HttpServletRequest request);

/**
* 获取超时时间，支付超时时间
*/
public abstract Integer getTimeout();
```

# csdn
* 353454657  20401903 qq

# 运行桂林演示demo
* git中文件demo.rar,将201405文件夹放到upload/目录
* 安装程序
* 安装完后,导入mysql(若导入报表己存在错误,drop掉数据库)
```
source /home/beni/guilin/guilin/WebContent/install/data/mysql/guilin.sql

```

# 移动端技术
* ionic
* appcan

# 后续
* 恋人礼品
* 景德镇陶瓷 ciduci.cn dataoci.com china-ciqi.com
* 码农商城
* 趣玩网
* 福建古田银耳

# stock
* 红绿灰 honglvhui.com
* zhang-die
* ztjtmt.com
* hongyulv.com
