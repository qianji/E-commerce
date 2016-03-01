<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.supplierAttribute.edit")}</title>


<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/input.js"></script>
<script type="text/javascript">
$().ready(function() {
	var $inputForm = $("#inputForm");
	var $supplierAttributeTable = $("#supplierAttributeTable");
	var $addOptionButton = $("#addOptionButton");
	var $deleteOption = $("a.deleteOption");
	[@flash_message /]
	
	// 增加选项内容
	$addOptionButton.click(function() {
		addOption();
	});
		
	// 删除选项内容
	$deleteOption.live("click", function() {
		if ($supplierAttributeTable.find("tr.optionTr").size() <= 1) {
			$.message("warn", "${message("admin.common.deleteAllNotAllowed")}");
		} else {
			$(this).closest("tr").remove();
		}
	});
	
	// 增加选项内容
	function addOption() {
		[@compress single_line = true]
			var trHtml = 
			'<tr class="optionTr">
				<th>${message("SupplierCompanySales.options")}:<\/th>
				<td>
					<input type="text" name="userName" class="text options" value="" maxlength="100" \/>
					<select name="memberRankId">
						[#list memberRanks as memberRank]
							<option value="${memberRank.id}"[#if memberRank.isDefault] selected="selected"[/#if]>${memberRank.name}<\/option>
						[/#list]
					<\/select>
					<a href="javascript:;" class="deleteOption">[${message("admin.common.delete")}]<\/a>
				<\/td>
			<\/tr>';
		[/@compress]
		$supplierAttributeTable.append(trHtml);
	};
	
	$.validator.addClassRules({
		options: {
			required: true
		}
	});
	
	// 表单验证
	$inputForm.validate({
		rules: {
			type: "required",
			name: "required",
			order: "digits"
		}
	});

});
</script>
</head>
<body>
	<div class="path">
		<a href="${base}/admin/common/index.jhtml">${message("admin.path.index")}</a> &raquo; ${message("admin.supplier.view")}
	</div>
	<form id="inputForm" action="update_supplier_company.jhtml" method="post">
		<input type="hidden" name="id" value="${supplier.id}" />
		<ul id="tab" class="tab">
			<li>
				<input type="button" value="${message("admin.supplierCompany.edit")}" />
			</li>
		</ul>
		<table class="input tabContent" id="supplierAttributeTable" >
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
						&nbsp;
					</th>
					<td>
						<a href="javascript:;" id="addOptionButton" class="button">${message("admin.common.add")}</a>
					</td>
				</tr>
				[#list supplier.supplierCompanySale as companys]
					<tr class="optionTr">
						<th>${message("SupplierCompanySales.options")}:</th>
						<td>
							<input type="text" name="userName" class="text options" value="${companys.member.username}" maxlength="200" />
							<select name="memberRankId">
								[#list memberRanks as memberRank]
									<option value="${memberRank.id}"[#if memberRank == companys.memberRank] selected="selected"[/#if]>${memberRank.name}</option>
								[/#list]
							</select>
							<a href="javascript:;" class="deleteOption">[${message("admin.common.delete")}]</a>
						</td>
					</tr>
				[/#list]
		</table>
		<table class="input">
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("admin.common.submit")}" />
					<input type="button" class="button" value="${message("admin.common.back")}" onclick="location.href='list_company_sales.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>