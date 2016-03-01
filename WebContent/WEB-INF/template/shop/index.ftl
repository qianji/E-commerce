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
[#include "/shop/include/bootstrap.ftl" /]
<!--<link href="${base}/resources/shop/slider/slider.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/index.css" rel="stylesheet" type="text/css" />-->
<script type="text/javascript" src="${base}/resources/shop/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lazyload.js"></script>
<script type="text/javascript" src="${base}/resources/shop/slider/slider.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/style/js/fade_focus.js"></script>

<link href="${base}/style/css/diff.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/style/js/index.js"></script>


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
	<div  class="rotator">
	<div  id="fade_focus">
		<div class="loading">Loading...</div>
		[@ad_position id = 3 /]
	</div>
	
	<div class="ro_ad">
		<div id="ad_phone">
			<img src="images/phone.png" width="100%" height="100%">
		</div>
		
		[@article_category_root_list count = 3]
		<ul>
			[#list articleCategories as articleCategory]
			[#if articleCategory_index ==0 ]
			<li id="shopping_fast" style="background-color: #63afed;"><a href="${base}${articleCategory.path}" style="color:white" target="_blank">${articleCategory.name}</a></li>
			[/#if]
			[#if articleCategory_index ==1 ]
			<li id="perspective"><a href="${base}${articleCategory.path}" style="color:white" target="_blank">${articleCategory.name}</a></li>
			[/#if]
			[#if articleCategory_index ==2 ]
			<li id="shopping_guide" style="float:right;_width:67px;"><a href="${base}${articleCategory.path}" style="color:white" target="_blank">${articleCategory.name}</a></li>
			[/#if]
			[/#list]
		</ul>
	    
		[#list articleCategories as articleCategory]
		[@article_list articleCategoryId = articleCategory.id count = 3]
		 [#if articleCategory_index ==0 ]
		<div id="shopping_fast_c">
			 [#list articles as article]
			<p><a href="${base}${article.path}" title="${article.title}" target="_blank">${abbreviate(article.title, 30)}</a></p>
			[/#list]
		</div>
		[/#if]
		 [#if articleCategory_index ==1 ]
		<div id="perspective_c">
			 [#list articles as article]
			<p><a href="${base}${article.path}" title="${article.title}" target="_blank">${abbreviate(article.title, 30)}</a></p>
			[/#list]
		</div>
		[/#if]
		 [#if articleCategory_index ==2 ]
		<div id="shopping_guide_c">
			 [#list articles as article]
			<p><a href="${base}${article.path}" title="${article.title}" target="_blank">${abbreviate(article.title, 30)}</a></p>
			[/#list]
		</div>
		[/#if]
		[/@article_list]
		[/#list]
		[/@article_category_root_list]
		
	</div>
	<div class="c"></div>
</div>

<div class="hot_recommend" id="promotionProduct">

	[@promotion_list hasEnded = false count = 1]
					[#list promotions as promotion]
						<h1><a href="${base}/product/list.jhtml?promotionId=${promotion.id}" target="_blank">${promotion.name}</a></h1>
						<ul>
								 [@product_list promotionId = promotion.id count = 4]
                                 [#list products as product]
									<li>
										<a href="${base}${product.path}" title="${product.name}" target="_blank">
											<div class="img_cont">
											<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
											</div>
											<h2>${abbreviate(product.name, 24)}</h2>
											<h3>
												<span class="info"[#if promotion.beginDate??] beginTimeStamp="${promotion.beginDate?long}"[/#if][#if promotion.endDate??] endTimeStamp="${promotion.endDate?long}"[/#if]>
                                                    [#if promotion.beginDate??]
                                                            ${message("shop.index.end")}: <em>${promotion.endDate?string("yyyy-MM-dd HH:mm")}</em>
                                                    [/#if]
                                            	</span>
											</h3>
											<h4>${currency(product.price, true, true)}</h4>
											<h5>
											原价:${currency(product.marketPrice, true, true)}
                                            </h5>
											
										</a>
									</li>
								[/#list]
							[/@product_list]
					[/#list]
						<li class="ad">
			<a><img src="images/ad.jpg" alt="旅游图片"></a>
		</li>
	</ul>
[/@promotion_list]
</div>
<div class="c"></div>


[@product_category_root_list count = 1]
[#list productCategories as productCategory]
[#if productCategory_index == 0]
<div class="admission_ticket">
	<h1>${productCategory.name}</h1>
	<div class="hot_objective">
		<div class="title">桂林景区门票</div>
		<div class="objective">
			<a href="#"><span>热门目的地：</span></a>
			<a href="#"><span class="defalt">漓江</span></a>
			<a href="#"><span>股东瀑布</span><span>兴坪</span></a>
			<a href="#"><span>九马画山</span></a>
		</div>
		<div class="more"><a href="">更多优惠门票 》</a></div>
	</div>
	<div class="c"></div>
	<ul>
		<li>
			<ol id="guilin_scenic">
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
			
			</ol>	
		</li>
		[@product_list productCategoryId = productCategory.id tagIds = 1 count = 6]
		[#list products as product]
			[#if product_index % 2 == 0]
			<li>
				<a href="${base}${product.path}" title="${product.name}" target="_blank">
					<div class="img_cont">
						<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
						<div class="money"><span class="goods">${abbreviate(product.name, 20)}</span><span class="mone">${currency(product.price, true, true)}</span></div>	
					</div>
				</a>
			[#else]
				<a href="${base}${product.path}" title="${product.name}" target="_blank">
					<div class="img_cont">
						<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
						<div class="money"><span class="goods">${abbreviate(product.name, 20)}</span><span class="mone">${currency(product.price, true, true)}</span></div>	
					</div>
				</a>
			</li>
			[/#if]
		[/#list]
		<li class="ad">
			<a><img src="images/ad.jpg" alt="旅游图片"></a>
		</li>
		[/@product_list]
	</ul>
</div>
<div class="c"></div>
[/#if]
[/#list]
[/@product_category_root_list]


[@product_category_root_list count = 2]
[#list productCategories as productCategory]
[#if productCategory_index == 1]
<div class="specialty">
	<h1>${productCategory.name}</h1>
	<div class="hot_objective">
		<div class="title">桂林景区门票</div>
		<div class="objective">
			<a href="#"><span>热门目的地：</span></a>
			<a href="#"><span class="defalt">漓江</span></a>
			<a href="#"><span>股东瀑布</span><span>兴坪</span></a>
			<a href="#"><span>九马画山</span></a>
		</div>
		<div class="more"><a href="">更多优惠门票 》</a></div>
	</div>
	<div class="c"></div>
	<ul>
		<li>
			<ol id="guilin_scenic">
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
			
			</ol>	
		</li>
		[@product_list productCategoryId = productCategory.id tagIds = 1 count = 6]
		[#list products as product]
			[#if product_index % 2 == 0]
			<li>
				<a href="${base}${product.path}" title="${product.name}" target="_blank">
					<div class="img_cont">
						<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
						<div class="money"><span class="goods">${abbreviate(product.name, 20)}</span><span class="mone">${currency(product.price, true, true)}</span></div>	
					</div>
				</a>
			[#else]
				<a href="${base}${product.path}" title="${product.name}" target="_blank">
					<div class="img_cont">
						<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
						<div class="money"><span class="goods">${abbreviate(product.name, 20)}</span><span class="mone">${currency(product.price, true, true)}</span></div>	
					</div>
				</a>
			</li>
			[/#if]
		[/#list]
		<li class="ad">
			<a><img src="images/ad.jpg" alt="旅游图片"></a>
		</li>
		[/@product_list]
	</ul>
</div>
<div class="c"></div>
[/#if]
[/#list]
[/@product_category_root_list]

[@product_category_root_list count = 3]
[#list productCategories as productCategory]
[#if productCategory_index == 2]
<div class="grogshop">
	<h1>${productCategory.name}</h1>
	<div class="hot_objective">
		<div class="title">桂林景区门票</div>
		<div class="objective">
			<a href="#"><span>热门目的地：</span></a>
			<a href="#"><span class="defalt">漓江</span></a>
			<a href="#"><span>股东瀑布</span><span>兴坪</span></a>
			<a href="#"><span>九马画山</span></a>
		</div>
		<div class="more"><a href="">更多优惠门票 》</a></div>
	</div>
	<div class="c"></div>
	<ul>
		<li>
			<ol id="guilin_scenic">
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
				<li><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">九马画山</a><span>|</span><a href="#" class="a_w">漓江</a><span>|</span><a href="#" class="a_w">大榕树</a></li>
			
			</ol>	
		</li>
		[@product_list productCategoryId = productCategory.id tagIds = 1 count = 6]
		[#list products as product]
			[#if product_index % 2 == 0]
			<li>
				<a href="${base}${product.path}" title="${product.name}" target="_blank">
					<div class="img_cont">
						<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
						<div class="money"><span class="goods">${abbreviate(product.name, 20)}</span><span class="mone">${currency(product.price, true, true)}</span></div>	
					</div>
				</a>
			[#else]
				<a href="${base}${product.path}" title="${product.name}" target="_blank">
					<div class="img_cont">
						<img src="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" data-original="[#if product.image??]${product.image}[#else]${setting.defaultThumbnailProductImage}[/#if]" />
						<div class="money"><span class="goods">${abbreviate(product.name, 20)}</span><span class="mone">${currency(product.price, true, true)}</span></div>	
					</div>
				</a>
			</li>
			[/#if]
		[/#list]
		<li class="ad">
			<a><img src="images/ad.jpg" alt="旅游图片"></a>
		</li>
		[/@product_list]
	</ul>
</div>
<div class="c"></div>
[/#if]
[/#list]
[/@product_category_root_list]


<div class="friendship">
	<h1>合作伙伴</h1>
	<div class="friendship_bg_line"></div>
	<table>
		<tr>
			 [@friend_link_list count = 10]
			 [#list friendLinks as friendLink]
			 	<td><a href="${friendLink.url}" target="_blank"><img alt="${friendLink.name}" src="${friendLink.logo}"/></a><h3>${friendLink.name}</h3></td>
			 [/#list]
			 [/@friend_link_list]
		</tr>
	</table>
</div>
	[#include "/shop/include/footer.ftl" /]
</body>
</html>
