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

<div class="head_two">
	<div class="context">
		<ul>
			<li style="float:left;	">亲，欢迎来到桂林旅游网！&nbsp;&nbsp;</li>
			[#if setting.phone??]
			 	<li>
					${message("shop.header.phone")}:
					<strong>${setting.phone}</strong>
			    </li>
	        [/#if]
			<li>
		        <a href="${base}/cart/list.jhtml">${message("shop.header.cart")}</a><span>|</span>	
		    </li>
		    <li>
				<a href="${base}/member/index.jhtml">${message("shop.member.navigation.title")}</a><span>|</span>
			</li>
			<li id="headerLogout">
				<a href="${base}/logout.jhtml">${message("shop.header.logout")}</a><span>|</span>
			</li>
			<li id="headerRegister">
				<a href="${base}/register.jhtml">${message("shop.header.register")}</a><span>|</span>
			</li>
			<li id="headerLogin">
				<a href="${base}/login.jhtml">${message("shop.header.login")}</a><span>|</span>
			</li>
			<li id="headerUsername"></li>
		</ul>
	</div>
</div>
