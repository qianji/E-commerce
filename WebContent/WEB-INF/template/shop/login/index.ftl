<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.login.title")}[#if systemShowPowered][/#if]</title>
[#include "/shop/include/bootstrap.ftl" /]

<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>

<script type="text/javascript">
$().ready(function() {

	var $loginForm = $("#loginForm");
	var $username = $("#username");
	var $password = $("#password");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $isRememberUsername = $("#isRememberUsername");
	var $submit = $(":submit");
	
	// 记住用户名
	if (getCookie("memberUsername") != null) {
		$isRememberUsername.prop("checked", true);
		$username.val(getCookie("memberUsername"));
		$password.focus();
	} else {
		$isRememberUsername.prop("checked", false);
		$username.focus();
	}
	
	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
	// 表单验证、记住用户名
	$loginForm.validate({
		rules: {
			username: "required",
			password: "required"
			[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberLogin")]
				,captcha: "required"
			[/#if]
		},
		submitHandler: function(form) {
			$.ajax({
				url: "${base}/common/public_key.jhtml",
				type: "GET",
				dataType: "json",
				cache: false,
				beforeSend: function() {
					$submit.prop("disabled", true);
				},
				success: function(data) {
					var rsaKey = new RSAKey();
					rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
					var enPassword = hex2b64(rsaKey.encrypt($password.val()));
					$.ajax({
						url: $loginForm.attr("action"),
						type: "POST",
						data: {
							username: $username.val(),
							enPassword: enPassword
							[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberLogin")]
								,captchaId: "${captchaId}",
								captcha: $captcha.val()
							[/#if]
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							if ($isRememberUsername.prop("checked")) {
								addCookie("memberUsername", $username.val(), {expires: 7 * 24 * 60 * 60});
							} else {
								removeCookie("memberUsername");
							}
							$submit.prop("disabled", false);
							if (message.type == "success") {
								[#if redirectUrl??]
									location.href = "${redirectUrl}";
								[#else]
									location.href = "${base}/";
								[/#if]
							} else {
								$.message(message);
								[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberLogin")]
									$captcha.val("");
									$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
								[/#if]
							}
						}
					});
				}
			});
		}
	});

});
</script>

<style type="text/css">
	 body							{text-align:center;}
	.login							{text-align:left;position:relative;background:url("style/images/login_guilin_scenery.png") no-repeat;margin:20px auto 30px auto;width:1100px;height:500px;}
	.login .main					{width:410px;height:310px;border:1px solid #d6d6d6;right:60px;top:110px;position: absolute;text-align:center; }
	.login .main form				{width: 291px;margin:30px auto 10px auto;text-align: left;}
	.login .main form p				{font-weight:700;color:#5f5f5f;margin:6px 0;font-size:14px;}
	.login .main form .reme			{max-height:20px;vertical-align: middle;font-weight:500;font-size:12px;position:relative;}
	.login .main form .reme .remeber{width:20px;height: 20px;border:none;font-weight:500;}
	.login .main form .reme #rem_u	{position:absolute;top:1px;*top:4px;left:23px;top:4px\9\0;}
	.login .main form input			{width:290px;line-height:25px;border:1px solid #d3d3d3;height:25px;}
	.login .main form .check_no		{width:200px;}
	.login .main form .check_no_img	{width:86px;height:28px;display:inline;}
	.login .main form .check_no_img img{vertical-align: middle;}
	.login .main form .submit		{background-color:#67b2ee;height: 35px;font-size:16px; color:white;font-weight:700;border: none;cursor:pointer;}
	
	.login .main .re				{font-size:12px;text-align:center;}
	.login .main .re .no_re			{color:#ff7200;	}
	.login .main .re .re_experience	{color:#8f8f8f;	}
	.login .main .re .at_noce		{color:#1c73b9;	}
	
</style>
</head>
<body>
	[#include "/shop/include/header_two.ftl" /]
	
	<div class="login">
	<div class="main">
		 <form id="loginForm" action="${base}/login/submit.jhtml" method="post">
			<p>
				[#if setting.isEmailLogin]
					<label for="username">${message("shop.login.usernameOrEmail")}:</label>
				[#else]
					<label for="username">${message("shop.login.username")}:</label>
				[/#if]
			</p>
			<input type="text" name="username" id="username" maxlength="${setting.usernameMaxLength}" />
			
			<p>${message("shop.login.password")}</p>
			<input type="password" name="password" id="password" maxlength="${setting.passwordMaxLength}" autocomplete="off">
			
			[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberLogin")]
			<p>${message("shop.captcha.name")}:</p>
			<input type="text" id="captcha" name="captcha" class="check_no captcha" maxlength="4" autocomplete="off" />
				<div class="check_no_img"><img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" /></div>
			[/#if]
			<p class="reme">
				<input type="checkbox" class="remeber" id="isRememberUsername" name="isRememberUsername" value="true" />
				<span id="rem_u">${message("shop.login.isRememberUsername")} &nbsp;&nbsp;<a href="${base}/password/find.jhtml">${message("shop.login.findPassword")}</a></span>
			</p>
			<input type="submit" class="submit" value="登&nbsp;&nbsp;&nbsp;录" />
			<label>
			
			</label>
		</form>
		<div class="re">
			<span class="no_re">还没有注册账号？</span>
			<span class="re_experience">注册即可体验在线购物！</span>
			<span class="at_noce"><a href="${base}/register.jhtml">立即注册</a></span>
		</div>
	</div>
	</div>

	[#include "/shop/include/footer.ftl" /]
</body>
</html>
