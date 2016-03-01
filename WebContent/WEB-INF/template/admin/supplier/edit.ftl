<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.supplier.edit")}</title>


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
						url: "check_email.jhtml?previousEmail=${supplier.email?url}",
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
			[#if !setting.isDuplicateEmail]
				email: {
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
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.supplier.edit")}
	</div>
	<form id="inputForm" action="update.jhtml" method="post">
		<input type="hidden" name="id" value="${supplier.id}" />
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.supplier.base")}" />
			</li>
			[#if supplierAttributes?has_content]
				<li>
					<input type="button" value="${message("admin.supplier.profile")}" />
				</li>
			[/#if]
			<li>
				<input type="button" value="${message("admin.supplier.corporatSales")}" />
			</li>
		</ul>
		<table class="input tabContent">
			<tr>
				<th>
					${message("Supplier.code")}:
				</th>
				<td>
					${supplier.supplierCode}
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.name")}:
				</th>
				<td>
				    <input type="text" name="supplierName" class="text" maxlength="200" value="${supplier.supplierName}"  title="${message("Supplier.name")}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.constactPerson")}:
				</th>
				<td>
				    <input type="text" name="constactPerson" class="text" maxlength="20" value="${supplier.constactPerson}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.phone")}:
				</th>
				<td>
				    <input type="text" name="phone" class="text" maxlength="25" value="${supplier.phone}" />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.email")}:
				</th>
				<td>
					<input type="text" name="email" class="text"  maxlength="50" value="${supplier.email}"  />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("Supplier.address")}:
				</th>
				<td>
					<input type="text" name="address" class="text"  maxlength="200" value="${supplier.address}"  />
				</td>
			</tr>
			<tr>
				<th>
					${message("admin.common.setting")}:
				</th>
				<td>
					<label>
						<input type="checkbox" name="isEnabled" value="true"[#if supplier.isEnabled] checked="checked"[/#if] />${message("Supplier.isEnabled")}
						<input type="hidden" name="_isEnabled" value="false" />
					</label>
					[#if supplier.isLocked]
						<label>
							<input type="checkbox" name="isLocked" value="true" checked="checked" />${message("Supplier.isLocked")}
							<input type="hidden" name="_isLocked" value="false" />
						</label>
					[/#if]
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
									[#list supplierType as stypes]
										<label>
											<input type="radio" name="supplierAttribute_${supplierAttribute.id}" value="${stypes}"[#if stypes == supplier.supplie] checked="checked"[/#if] />${message("Supplier.Type." + stypes)}
										</label>
									[/#list]
								</span>
							[#elseif supplierAttribute.type == "fax"]
							    <input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" value="${supplier.fax}" maxlength="25" />
							[#elseif supplierAttribute.type == "zipCode"]	
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" value="${supplier.zipCode}" maxlength="50" />
							[#elseif supplierAttribute.type == "activeStartDate"]	
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text Wdate" value="${supplier.activeStartDate}" onfocus="WdatePicker();" />
							[#elseif supplierAttribute.type == "activeEndDate"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text Wdate" value="${supplier.activeEndDate}" onfocus="WdatePicker();" />
							[#elseif supplierAttribute.type == "bankDeposit"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" value="${supplier.bankDeposit}" maxlength="250" />
							[#elseif supplierAttribute.type == "bankName"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" value="${supplier.bankName}" maxlength="250" />
							[#elseif supplierAttribute.type == "bankAccount"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" value="${supplier.bankAccount}" maxlength="50" />
							[#elseif supplierAttribute.type == "remark"]
								<textarea rows="3" cols="20"  name="supplierAttribute_${supplierAttribute.id}" class="text" maxlength="250" >${supplier.remark}</textarea>
							[#elseif supplierAttribute.type == "text"]
								<input type="text" name="supplierAttribute_${supplierAttribute.id}" class="text" value="${supplier.getAttributeValue(supplierAttribute)}" maxlength="200" />
							[#elseif supplierAttribute.type == "select"]
								<select name="supplierAttribute_${supplierAttribute.id}">
									<option value="">${message("admin.common.choose")}</option>
									[#list supplierAttribute.options as option]
										<option value="${option}"[#if option == supplier.getAttributeValue(supplierAttribute)] selected="selected"[/#if]>
											${option}
										</option>
									[/#list]
								</select>
							[#elseif supplierAttribute.type == "checkbox"]
								<span class="fieldSet">
									[#list supplierAttribute.options as option]
										<label>
											<input type="checkbox" name="supplierAttribute_${supplierAttribute.id}" value="${option}"[#if (supplier.getAttributeValue(supplierAttribute)?seq_contains(option))!] checked="checked"[/#if] />${option}
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