<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.supplier.add")}</title>


<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/admin/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $areaId = $("#areaId");
	
	[@flash_message /]
	
	// 地区选择
	$areaId.lSelect({
		url: "${base}/admin/common/area.jhtml"
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			supplierCode: {
				required: true,
				pattern: /^[0-9a-z_A-Z\u4e00-\u9fa5]+$/,
				minlength: ${setting.usernameMinLength},
				maxlength: ${setting.usernameMaxLength},
				remote: {
					url: "check_username.jhtml",
					cache: false
				}
			},
			supplierName: {
				required: true
			},
			address: {
				required: true
			},
			constactPerson: {
				required: true
			},
			phone: {
				required: true
			},
			email: {
				required: true,
				email: true
				[#if !setting.isDuplicateEmail]
					,remote: {
						url: "check_email.jhtml",
						cache: false
					}
				[/#if]
			}
			[#list supplierAttributes as supplierAttribute]
				[#if supplierAttribute.isRequired]
					,supplierAttribute_${supplierAttribute.id}: {
						required: true
					}
				[/#if]
			[/#list]
		},
		messages: {
			supplierCode: {
				pattern: "${message("admin.validate.illegal")}",
				remote: "${message("admin.member.disabledExist")}"
			}
			[#if !setting.isDuplicateEmail]
				,email: {
					remote: "${message("admin.validate.exist")}"
				}
			[/#if]
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.supplier.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post">
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.supplier.base")}" />
			</li>
			[#if supplierAttributes?has_content]
				<li>
					<input type="button" value="${message("admin.supplier.profile")}" />
				</li>
			[/#if]
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.code")}:
				</th>
				<td>
					<input type="text" name="supplierCode" class="text" maxlength="30" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.name")}:
				</th>
				<td>
					<input type="text" name="supplierName" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.constactPerson")}:
				</th>
				<td>
					<input type="text" name="constactPerson" class="text" maxlength="20" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.phone")}:
				</th>
				<td>
					<input type="text" name="phone" class="text" maxlength="25" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.email")}:
				</th>
				<td>
					<input type="text" name="email" class="text"  maxlength="50" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.address")}:
				</th>
				<td>
					<input type="text" name="address" class="text" maxlength="200" />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true" checked="checked" />${message("Supplier.isEnabled")}
						<input type="hidden" name="_isEnabled" value="false" />
					</label>
				</td>
			</tr>
		</table>
		[#if supplierAttributes?has_content]
			<table class="input tabContent">
				[#list supplierAttributes as supplierAttribute]
					<tr>
						<th>
							[#if supplierAttribute.isRequired]<span class="requiredField">*</span>[/#if]${supplierAttribute.name}:
						</th>
						<td>
							[#if supplierAttribute.type == "supplie"]
							   <span class="fieldSet">
									[#list supplierType as stypes ]
										<label>
											<input type="radio" name="supplierAttribute_${supplierAttribute.id}" value="${stypes}" [#if !stypes_has_next]checked[/#if] />${message("Supplier.Type." + stypes)}
										</label>
									[/#list]
								</span>
							[#elseif supplierAttribute.type == "fax"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" maxlength="25" />
							[#elseif supplierAttribute.type == "zipCode"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" maxlength="50" />
							[#elseif supplierAttribute.type == "activeStartDate"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text Wdate" onfocus="WdatePicker();" />
							[#elseif supplierAttribute.type == "activeEndDate"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text Wdate" onfocus="WdatePicker();" />
							[#elseif supplierAttribute.type == "bankDeposit"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" maxlength="250" />
							[#elseif supplierAttribute.type == "bankName"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" maxlength="250" />
							[#elseif supplierAttribute.type == "bankAccount"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" maxlength="200" />
							[#elseif supplierAttribute.type == "remark"]
								<textarea rows="3" cols="20"  name="supplierAttribute_${supplierAttribute.id}" class="text" maxlength="250" ></textarea>
							[#elseif supplierAttribute.type == "text"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" maxlength="200" />
							[#elseif supplierAttribute.type == "select"]
								<select name="supplierAttribute_${supplierAttribute.id}">
									<option value="">${message("admin.common.choose")}</option>
									[#list supplierAttribute.options as option]
										<option value="${option}">
											${option}
										</option>
									[/#list]
								</select>
							[#elseif supplierAttribute.type == "checkbox"]
								<span class="fieldSet">
									[#list supplierAttribute.options as option]
										<label>
											<input type="checkbox" name="supplierAttribute_${supplierAttribute.id}" value="${option}" />${option}
										</label>
									[/#list]
								</span>
							[/#if]
						</td>
					</tr>
				[/#list]
			</table>
		[/#if]
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>