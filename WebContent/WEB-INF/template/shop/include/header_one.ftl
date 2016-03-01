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

<div class="head_bg">
	<div class="head">
		<a class="logo" href="${base}"><img src="${base}/style/images/logo.jpg" width="180" height="67" /></a>
		<ul>
			[@navigation_list position = "middle"]
				[#list navigations as navigation]
					<li[#if navigation.url = url] class="active"[/#if]>
						<a class="icon${navigation_index}" href="${navigation.url}"[#if navigation.isBlankTarget] target="_blank"[/#if]><span>${navigation.name}</span></a>
					</li>
				[/#list]
			[/@navigation_list]
				
		</ul>
	</div>
</div>

<div class="c"></div>
