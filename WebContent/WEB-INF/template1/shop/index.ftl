<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
[@seo type = "index"]
	<title>[#if seo.title??][@seo.title?interpret /][#else]${message("shop.index.title")}[/#if][#if systemShowPowered][/#if]</title>
	
	
	[#if seo.keywords??]
		<meta name="keywords" content="[@seo.keywords?interpret /]" />
	[/#if]
	[#if seo.description??]
		<meta name="description" content="[@seo.description?interpret /]" />
	[/#if]
[/@seo]
<link rel="icon" href="${base}/favicon.ico" type="image/x-icon" />
<link href="${base}/resources/shop/slider/slider.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${base}/resources/shop/slider/slider.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
[#include "/shop/include/bootstrap.ftl" /]
<script type="text/javascript">
$().ready(function() {

	var $slider = $("#slider");
	var $newArticleTab = $("#newArticle .tab");
	var $promotionProductTab = $("#promotionProduct .tab");
	var $promotionProductInfo = $("#promotionProduct .info");
	var $hotProductTab = $("#hotProduct .tab");
	var $newProductTab = $("#newProduct .tab");
	var $hotProductImage = $("#hotProduct img");
	var $newProductImage = $("#newProduct img");
	
	$slider.nivoSlider({
		effect: "random",
		animSpeed: 1000,
		pauseTime: 6000,
		controlNav: true,
		keyboardNav: false,
		captionOpacity: 0.4
	});
	
	$newArticleTab.tabs("#newArticle .tabContent", {
		tabs: "li",
		event: "mouseover",
		initialIndex: 1
	});
	
	function promotionInfo() {
		$promotionProductInfo.each(function() {
			var $this = $(this);
			var beginDate = $this.attr("beginTimeStamp") != null ? new Date(parseFloat($this.attr("beginTimeStamp"))) : null;
			var endDate = $this.attr("endTimeStamp") != null ? new Date(parseFloat($this.attr("endTimeStamp"))) : null;
			if (beginDate == null || beginDate <= new Date()) {
				if (endDate != null && endDate >= new Date()) {
					var time = (endDate - new Date()) / 1000;
					$this.html("${message("shop.index.remain")}:<em>" + Math.floor(time / (24 * 3600)) + "<\/em> ${message("shop.index.day")} <em>" + Math.floor((time % (24 * 3600)) / 3600) + "<\/em> ${message("shop.index.hour")} <em>" + Math.floor((time % 3600) / 60) + "<\/em> ${message("shop.index.minute")}");
				} else if (endDate != null && endDate < new Date()) {
					$this.html("${message("shop.index.ended")}");
				} else {
					$this.html("${message("shop.index.going")}");
				}
			}
		});
	}
	
	promotionInfo();
	setInterval(promotionInfo, 60 * 1000);
	
	$hotProductImage.lazyload({
		threshold: 100,
		effect: "fadeIn",
		skip_invisible: false
	});
	
	$newProductImage.lazyload({
		threshold: 100,
		effect: "fadeIn",
		skip_invisible: false
	});

});
</script>
</head>
<body>
	[#include "/shop/include/header.ftl" /]
	<div class="container index">
		<div class="span18">
			[@ad_position id = 3 /]
		</div>
		<div class="span6 last">
		    [@ad_position id = 4 /]
			<div id="newArticle" class="newArticle" style="margin-top:10px;">
				[@article_category_root_list count = 3]
					<ul class="tab">
						[#list articleCategories as articleCategory]
							<li>
								<a href="${base}${articleCategory.path}" target="_blank">${articleCategory.name}</a>
							</li>
						[/#list]
					</ul>
					[#list articleCategories as articleCategory]
						[@article_list articleCategoryId = articleCategory.id count = 5]
							<ul class="tabContent" style="display:block;">
								[#list articles as article]
									<li>
										<a href="${base}${article.path}" title="${article.title}" target="_blank">${abbreviate(article.title, 30)}</a>
									</li>
								[/#list]
							</ul>
						[/@article_list]
					[/#list]
				[/@article_category_root_list]
			</div>
			
		</div>
		
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix" style="width:100%">
				[@promotion_list hasEnded = false count = 2]
					[#list promotions as promotion]
					<ul class="tab"  style="width:100%">
							<li>
								 <a href="${base}/product/list.jhtml?promotionId=${promotion.id}" target="_blank">${promotion.name}</a>
							</li>
					</ul>
						<ul class="tabContent"  style="width:100%">
								 [@product_list promotionId = promotion.id count = 4]
                                 [#list products as product]
								
									<li style="margin:0px 30px;">
										<a href="${base}${product.path}" title="${product.name}" target="_blank"><img src="${base}/upload/image/blank.gif" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" /></a>
										<div style="position:relative;bottom:0px;height:60px;background-color:#cccccc;width:170px;">
											${product.name} &nbsp;&nbsp;${product.price}
										</div>
									</li>
								[/#list]
							[/@product_list]
						</ul>
					[/#list]
				[/@promotion_list]
			</div>
		</div>
		
		<div class="span24">
			[@ad_position id = 5 /]
		</div>
		<div class="span24">
			<div id="hotProduct" class="hotProduct clearfix" style="width:100%">
				[@product_category_root_list count = 3]
					[#list productCategories as productCategory]
					<ul class="tab"  style="width:100%">
							<li>
								<a href="${base}${productCategory.path}?tagIds=1" target="_blank">${productCategory.name}</a>
							</li>
					</ul>
						<ul class="tabContent"  style="width:100%">
							[@product_list productCategoryId = productCategory.id tagIds = 1 count = 4]
								[#list products as product]
									<li style="margin:0px 30px;">
										<a href="${base}${product.path}" title="${product.name}" target="_blank">
										<img src="${base}/upload/image/blank.gif" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
										</a>
										<div style="position:relative;bottom:0px;height:60px;background-color:#cccccc;width:170px;">
											${product.name} &nbsp;&nbsp;${product.price}
										</div>
									</li>
								[/#list]
							[/@product_list]
						</ul>
					[/#list]
				[/@product_category_root_list]
			</div>
		</div>
		<div class="span24">
			<div class="friendLink">
				<dl>
					<dt>${message("shop.index.friendLink")}</dt>
					[@friend_link_list count = 10]
						[#list friendLinks as friendLink]
							<dd>
								<a href="${friendLink.url}" target="_blank">${friendLink.name}</a>
								[#if friendLink_has_next]|[/#if]
							</dd>
						[/#list]
					[/@friend_link_list]
					<dd class="more">
						<a href="${base}/friend_link.jhtml">${message("shop.index.more")}</a>
					</dd>
				</dl>
			</div>
		</div>
	</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>