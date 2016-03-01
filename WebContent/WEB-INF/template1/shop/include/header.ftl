<script type="text/javascript">
$().ready(function() {

	var $headerLogin = $("#headerLogin");
	var $headerRegister = $("#headerRegister");
	var $headerUsername = $("#headerUsername");
	var $headerLogout = $("#headerLogout");
	var $productSearchForm = $("#productSearchForm");
	var $keyword = $("#productSearchForm input");
	var defaultKeyword = "${message("shop.header.keyword")}";
	
	var username = getCookie("username");
	if (username != null) {
		$headerUsername.text("${message("shop.header.welcome")}, " + username).show();
		$headerLogout.show();
	} else {
		$headerLogin.show();
		$headerRegister.show();
	}
	
	$keyword.focus(function() {
		if ($keyword.val() == defaultKeyword) {
			$keyword.val("");
		}
	});
	
	$keyword.blur(function() {
		if ($keyword.val() == "") {
			$keyword.val(defaultKeyword);
		}
	});
	
	$productSearchForm.submit(function() {
		if ($.trim($keyword.val()) == "" || $keyword.val() == defaultKeyword) {
			return false;
		}
	});

});
</script>

<div class="container header">
<div class="span24">
    <div class="topNav" style="font-size:12px;height:30px;line-height:30px;background-color:#f7f7f7;width:100%;">
		<ul>
				
				<li id="headerLogin" class="headerLogin">
					<a href="${base}/login.jhtml">${message("shop.header.login")}</a>|
				</li>
				<li id="headerRegister" class="headerRegister">
					<a href="${base}/register.jhtml">${message("shop.header.register")}</a>|
				</li>
				<li id="headerUsername" class="headerUsername"></li>
				<li id="headerLogout" class="headerLogout">
					<a href="${base}/logout.jhtml">[${message("shop.header.logout")}]</a>|
				</li>
				<li>
					<a href="${base}/member/index.jhtml">${message("shop.member.navigation.title")}</a>|
				</li>
				<li>
			        <a href="${base}/cart/list.jhtml">${message("shop.header.cart")}</a>
			    </li>
			    [#if setting.phone??]
				 	<li>
						${message("shop.header.phone")}:
						<strong>${setting.phone}</strong>
				    </li>
		        [/#if]
			</ul>
    </div>
</div>

	<div class="span5">
		<div class="logo">
			<a href="${base}/">
				<img src="${setting.logo}" alt="${setting.siteName}" />
			</a>
		</div>
	</div>
	<div class="span10">
		<form id="productSearchForm" action="${base}/product/search.jhtml" method="get">
			<input  name="keyword" class="form-control" placeholder="搜索您想需要的商品" value="${productKeyword!message("shop.header.keyword")}" maxlength="30" />
		</form>
		<div class="hotSearch">
			[#if setting.hotSearches?has_content]
				${message("shop.header.hotSearch")}:
				[#list setting.hotSearches as hotSearch]
					<a href="${base}/product/search.jhtml?keyword=${hotSearch?url}">${hotSearch}</a>
				[/#list]
			[/#if]
		</div>
	</div>
			
	<div class="span24">
		<nav class="collapse navbar-inverse navbar-collapse bs-navbar-collapse" role="navigation">
		   <ul class="nav navbar-nav">
				[@navigation_list position = "middle"]
					[#list navigations as navigation]
						<li[#if navigation.url = url] class="active"[/#if]>
							<a href="${navigation.url}"[#if navigation.isBlankTarget] target="_blank"[/#if]>${navigation.name}</a>
						</li>
					[/#list]
				[/@navigation_list]
		  </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li>
	          <a href="../about">关于</a>
	        </li>
	      </ul>
	    </nav>
    </div>
    
    <div class="span24" style="margin-bottom:10px;">
    	
    </div>
    
</div>