<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.resourceNotFound.title")}[#if systemShowPowered][/#if]</title>

[#include "/shop/include/bootstrap.ftl" /]
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/error.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>

<style type="text/css">
	.cont{color:#62a6b4;background:url("style/images/err404.gif") 50px 130px no-repeat;width:1180px;height: 470px;margin:0 auto;position:relative;}
	.cont .err_info{width:500px; height:350px;position:absolute;right: 160px;top:100px;text-align:center; }
	.cont .err_info p{line-height:25px;margin-top:10px;text-align:left;font-size:13px;width: 460px;}
	.cont .err_info .err_english{font-size:40px;line-height:45px;}
	.cont .err_info .err_chinese{font-size:20px;margin-top:10px;border-bottom: 1px dotted #62a6b4;padding-bottom:10px;}
	.cont .err_info h4{margin-top:20px;font-size:23px;}
	.cont .err_info a{font-size:22px;color:red;margin-top:10px;display: block;width: 460px; }
</style>
</head>
<body>
	[#include "/shop/include/header_one.ftl" /]
	<div class="cont">
		<div class="err_info">
			<p class="err_english">HTTP 404 Page Not Found</p>
			<p class="err_chinese">您搜索的页面可能已经删除、更名或暂时不可用</p>
			<h4>请尝试以下操作</h4>
			<p>确保浏览器的地址中显示的网站地址的拼写和格式正确无误，如果通过单击链接而达到了该网页；请与网站管理员联系，通知他们该链接的格式不正确，单击后退按钮尝试另一个链接。</p>
			<a href="${base}">返回首页 >></a>
		</div>
	</div>

	[#include "/shop/include/footer.ftl" /]
</body>
</html>