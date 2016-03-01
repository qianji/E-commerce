<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.supplier.view")}</title>


<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.supplier.view")}
	</div>
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
				${message("Supplier.code")}:
			</th>
			<td>
				${supplier.supplierCode}
			</td>
		</tr>
		<tr>
			<th>
				${message("Supplier.name")}:
			</th>
			<td>
				${supplier.supplierName}
			</td>
		</tr>
		<tr>
			<th>
				${message("Supplier.constactPerson")}:
			</th>
			<td>
				${supplier.constactPerson}
			</td>
		</tr>
		<tr>
			<th>
				${message("Supplier.phone")}:
			</th>
			<td>
				${supplier.phone}
			</td>
		</tr>
		<tr>
			<th>
				${message("Supplier.email")}:
			</th>
			<td>
				${supplier.email}
			</td>
		</tr>
		<tr>
			<th>
				${message("Supplier.address")}:
			</th>
			<td>
				${supplier.address}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.supplier.status")}:
			</th>
			<td>
				[#if !supplier.isEnabled]
					<span class="red">${message("admin.supplier.disabled")}</span>
				[#elseif supplier.isLocked]
					<span class="red"> ${message("admin.supplier.locked")} </span>
				[#else]
					<span class="green">${message("admin.supplier.normal")}</span>
				[/#if]
			</td>
		</tr>
		[#if supplier.isLocked]
			<tr>
				<th>
					${message("Supplier.lockedDate")}:
				</th>
				<td>
					${supplier.lockedDate?string("yyyy-MM-dd HH:mm:ss")}
				</td>
			</tr>
		[/#if]
		<tr>
			<th>
				${message("admin.common.createDate")}:
			</th>
			<td>
				${supplier.createDate?string("yyyy-MM-dd HH:mm:ss")}
			</td>
		</tr>
		<tr>
			<th>
				${message("admin.common.modifyDate")}:
			</th>
			<td>
				${(supplier.modifyDate?string("yyyy-MM-dd HH:mm:ss"))!"-"}
			</td>
		</tr>
		<tr>
			<th>
				${message("Supplier.ModifyIp")}:
			</th>
			<td>
				${(supplier.loginIp)!"-"}
			</td>
		</tr>
	</table>
	[#if supplierAttributes?has_content]
		<table class="input tabContent">
			[#list supplierAttributes as supplierAttribute]
				<tr>
					<th>
						${supplierAttribute.name}:
					</th>
					<td>
						[#if supplierAttribute.type == "supplie"]
							${message("Supplier.Type." + supplier.supplie)}
						[#elseif supplierAttribute.type == "fax"]
							${supplier.fax}
						[#elseif supplierAttribute.type == "zipCode"]
							${supplier.zipCode}
						[#elseif supplierAttribute.type == "activeStartDate"]
						 	${supplier.activeStartDate?string("yyyy-MM-dd")}
						[#elseif supplierAttribute == "activeEndDate"]
							${supplier.activeEndDate?string("yyyy-MM-dd")}
						[#elseif supplierAttribute.type == "bankDeposit"]
							${supplier.bankDeposit}
						[#elseif supplierAttribute.type == "bankName"]
							${supplier.bankName}
						[#elseif supplierAttribute.type == "bankAccount"]
							${supplier.bankAccount}
						[#elseif supplierAttribute.type == "remark"]
							<textarea rows="3" cols="20" readonly="readonly">${supplier.remark}</tetextarea>
						[#elseif supplierAttribute.type == "text"]
							${supplier.getAttributeValue(supplierAttribute)}
						[#elseif supplierAttribute.type == "select"]
							${supplier.getAttributeValue(supplierAttribute)}
						[#elseif supplierAttribute.type == "checkbox"]
							[#list supplier.getAttributeValue(supplierAttribute) as option]
								${option}
							[/#list]
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
				<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list.jhtml'" />
			</td>
		</tr>
	</table>
</body>
</html>