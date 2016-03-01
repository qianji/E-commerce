<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("shop.register.title")}[#if systemShowPowered][/#if]</title>
<style type="text/css">
	/*.re form table					{border-collapse:collapse;border:1px solid red;width:500px;}
	.re form table tr .text_left	{text-align:right;}
	.re form table tr td			{border:1px solid red;height:40px;text-align:right;color:#5F5F5F;}
	.re form table tr td span		{color:red;font-weight:500;}
	.re form table tr td input		{width:200px;height:25px;border:1px solid #D3D3D3;line-height:25px;}
	.re form table tr td .submit	{margin:10px 0 0 5px;;width: 335px;background-color:#67b2ee;height: 35px;color:white;font-size:16px;font-weight:700;border:none;cursor:pointer;}
	.re form table tr td .check_no	{width: 110px;}  */
	 body							{text-align:center;}
	 div  							{text-align: left;}
	 img							{vertical-align:middle;}
	.font_weight					{font-weight: 700;}
	.re								{width:1180px;height:500px;margin:0 auto;position:relative;border: 1px solid white;}
	.re .re_logo					{background-image:url("style/images/user_re_logo.gif");width:325px;height:35px;margin:40px 0 0 60px;}
	.re .re_plan					{background:url("style/images/re_plan.gif") no-repeat;width:986px;height:34px;position: absolute;z-index:999;top:100px;left:55px;}
	.re .re_plan div				{font-size:13px;line-height:33px;height: 33px;float:right;}
	.re .re_plan .info				{color:white;;width:462px;}
	.re .re_plan .succeed_re		{color:#7c7c7c;width:500px;}
	
	.re form 						{width:600px;margin:100px 0 0 73px;position:absolute;z-index:2;border:0px solid red;overflow:hidden;}
	.re form .agree					{font-size:12px;color:rgb(80,80,80);margin: 15px 0 0 10px;  }
	.re form .agree	span			{color:	#1c73b9;cursor:pointer;}
	.re .scenery					{width:600px;height:400px;position:absolute;top:105px;right:100px;background:url("style/images/login_guilin_scenery.png") 0 -80px no-repeat;}

	.re form table					{text-align:right;border-collapse:collapse;}
	.re form table tr td			{height:40px;border:0px solid red;vertical-align:middle;}
	.re form table tr td span		{color:red;}
	.re form table tr td input		{width:250px;height:30px;border:1px solid #D3D3D3;line-height:25px;}
	.re form table tr td .check_NO	{width:166px;float: left;}
	.re form table tr td .check_img	{width: 79;height:30px;float:left;margin:2px 0 0 4px;}
	.re form table tr .text_left{text-align:left;}
	.re form table tr td .submit	{margin:10px 0 0 10px;;width: 355px;background-color:#67b2ee;height: 35px;color:white;font-size:16px;font-weight:700;border:none;cursor:pointer;}
	
</style>
[#include "/shop/include/bootstrap.ftl" /]
<link href="${base}/resources/shop/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/shop/css/register.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/shop/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rng.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/base64.js"></script>
<script type="text/javascript" src="${base}/resources/shop/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/shop/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
$().ready(function() {

	var $registerForm = $("#registerForm");
	var $username = $("#username");
	var $password = $("#password");
	var $email = $("#email");
	var $areaId = $("#areaId");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $submit = $(":submit");
	var $agreement = $("#agreement");
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/common/area.jhtml"
	});
	
	// 更换验证码
	$captchaImage.click(function() {
		$captchaImage.attr("src", "${base}/common/captcha.jhtml?captchaId=${captchaId}&timestamp=" + (new Date()).valueOf());
	});
	
	// 注册协议
	$agreement.hover(function() {
		$(this).height(200);
	});
	
	// 表单验证
	$registerForm.validate({
		rules: {
			username: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: ${setting.usernameMinLength},
				remote: {
					url: "${base}/register/check_username.jhtml",
					cache: false
				}
			},
			password: {
				required: true,
				pattern: /^[^\s&\"<>]+$/,
				minlength: ${setting.passwordMinLength}
			},
			rePassword: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
				[#if !setting.isDuplicateEmail]
					,remote: {
						url: "${base}/register/check_email.jhtml",
						cache: false
					}
				[/#if]
			},
			captcha: "required"
			[@member_attribute_list]
				[#list memberAttributes as memberAttribute]
					[#if memberAttribute.isRequired]
						,memberAttribute_${memberAttribute.id}: {
							required: true
						}
					[/#if]
				[/#list]
			[/@member_attribute_list]
		},
		messages: {
			username: {
				pattern: "${message("shop.register.usernameIllegal")}",
				remote: "${message("shop.register.disabledExist")}"
			},
			password: {
				pattern: "${message("shop.register.passwordIllegal")}"
			}
			[#if !setting.isDuplicateEmail]
				,email: {
					remote: "${message("shop.register.emailExist")}"
				}
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
						url: $registerForm.attr("action"),
						type: "POST",
						data: {
							username: $username.val(),
							enPassword: enPassword,
							email: $email.val()
							[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
								,captchaId: "${captchaId}",
								captcha: $captcha.val()
							[/#if]
							[@member_attribute_list]
								[#list memberAttributes as memberAttribute]
									,memberAttribute_${memberAttribute.id}: $(":input[name='memberAttribute_${memberAttribute.id}']").val()
								[/#list]
							[/@member_attribute_list]
						},
						dataType: "json",
						cache: false,
						success: function(message) {
							$.message(message);
							if (message.type == "success") {
								setTimeout(function() {
									$submit.prop("disabled", false);
									location.href = "${base}/";
								}, 3000);
							} else {
								$submit.prop("disabled", false);
								[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
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
</head>
<body>
	[#include "/shop/include/header_two.ftl" /]
	
	
<div class="re">
	<div class="re_logo"></div>
	<form id="registerForm" action="${base}/register/submit.jhtml" method="post">
		<table>
			<tr>
				<td width="20"><span>*</span></td>
				<td width="90" class="font_weight">用&nbsp;户&nbsp;名：</td>
				<td width="255" class="text_left">
					<input type="text" id="username" name="username" maxlength="${setting.usernameMaxLength}" />
				</td>
				<td width="200"></td>
			</tr>
			<tr>
				<td><span>*</span></td>
				<td class="font_weight">密&nbsp;&nbsp;&nbsp;码：</td>
				<td class="text_left">
					<input type="password" id="password" name="password"  maxlength="${setting.passwordMaxLength}" autocomplete="off" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td><span>*</span></td>
				<td class="font_weight">确认密码：</td>
				<td class="text_left">
					<input type="password" name="rePassword"  maxlength="${setting.passwordMaxLength}" autocomplete="off" />

				</td>
				<td id="agree_err" class="text_left"></td>
			</tr>
			<tr>
				<td><span>*</span></td>
				<td class="font_weight">E-mail:</td>
				<td class="text_left">
					<input type="text" id="email" name="email" maxlength="200" />
				</td>
				<td id="email_err" class="text_left"></td>
			</tr>


			[@member_attribute_list]
			[#list memberAttributes as memberAttribute]
			<tr>
				<td>[#if memberAttribute.isRequired]<span class="requiredField">*</span>[/#if]</td>
				<td class="font_weight">${memberAttribute.name}:</td>
				<td class="text_left">
					[#if memberAttribute.type == "name"]
					<input type="text" name="memberAttribute_${memberAttribute.id}" maxlength="200" />
					[#elseif memberAttribute.type == "gender"]
					<span class="fieldSet">
					[#list genders as gender]
					<label>
					<input type="radio" name="memberAttribute_${memberAttribute.id}" value="${gender}" />${message("Member.Gender." + gender)}
					</label>
					[/#list]
					</span>
					[#elseif memberAttribute.type == "birth"]
					<input type="text" name="memberAttribute_${memberAttribute.id}" class="form-control" style="width:230px" onfocus="WdatePicker();" />
					[#elseif memberAttribute.type == "area"]
					<span class="fieldSet">
					<input type="hidden" id="areaId" name="memberAttribute_${memberAttribute.id}" />
					</span>
					[#elseif memberAttribute.type == "address"]
					<input type="text" name="memberAttribute_${memberAttribute.id}" class="form-control" style="width:230px" maxlength="200" />
					[#elseif memberAttribute.type == "zipCode"]
					<input type="text" name="memberAttribute_${memberAttribute.id}" class="form-control" style="width:230px" maxlength="200" />
					[#elseif memberAttribute.type == "phone"]
					<input type="text" name="memberAttribute_${memberAttribute.id}" class="form-control" style="width:230px" maxlength="200" />
					[#elseif memberAttribute.type == "mobile"]
					<input type="text" name="memberAttribute_${memberAttribute.id}" class="form-control" style="width:230px" maxlength="200" />
					[#elseif memberAttribute.type == "text"]
					<input type="text" name="memberAttribute_${memberAttribute.id}" class="form-control" style="width:230px" maxlength="200" />
					[#elseif memberAttribute.type == "select"]
					<select name="memberAttribute_${memberAttribute.id}">
						<option value="">${message("shop.common.choose")}</option>
						[#list memberAttribute.options as option]
						<option value="${option}">
							${option}
						</option>
					[/#list]
					</select>
					[#elseif memberAttribute.type == "checkbox"]
					<span class="fieldSet">
					[#list memberAttribute.options as option]
					<label>
					<input type="checkbox" name="memberAttribute_${memberAttribute.id}" value="${option}" />${option}
					</label>
					[/#list]
					</span>
					[/#if]
					</td>
					<td></td>
					</tr>
					[/#list]
					[/@member_attribute_list]


					[#if setting.captchaTypes?? && setting.captchaTypes?seq_contains("memberRegister")]
					<tr>
						<td><span>*</span></td>
						<td class="font_weight">验&nbsp;证&nbsp;码：</td>
						<td class="text_left">
							<input type="text" id="captcha" name="captcha"  class="check_NO" maxlength="4" autocomplete="off" />
							<div class="check_img"><img id="captchaImage" class="captchaImage" src="${base}/common/captcha.jhtml?captchaId=${captchaId}" title="${message("shop.captcha.imageTitle")}" /></div>
						</td>
						<td>
						</td>
					</tr>
					[/#if]


			<tr>
				<td colspan="4" align="left">
					<input type="submit" class="submit" value="${message("shop.register.submit")}" />
				</td>
			</tr>
			
		</table>
	
		<div class="agree">
			<input type="checkbox" checked="checked" name="read_agreement"/>我已阅读并同意
			<a id="agreement" href="#">《桂林旅游网用户注册协议》</a>
		</div>		
	</form>
	<div class="re_plan">
		<div class="succeed_re">2.完成注册</div>
		<div class="info">1.填写账户信息</div>
	</div>
	<div class="scenery"></div>
</div>

	[#include "/shop/include/footer.ftl" /]
</body>
</html>
