<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/tld/c.tld" prefix="c"%>
<%@taglib uri="/tld/fn.tld" prefix="fn"%>

<style>
.login-userpic img {
	width: 100%;
	height: 100%;
	-webkit-border-radius: 50% !important;
	-moz-border-radius: 50% !important;
	border-radius: 50% !important;
	border: 2px solid #337ab7;
}
.student-dialog .modal-dialog {
	width: 900px;
}
.header {
	position: fixed;
	left: 0;
	right: 0;
	z-index: 1002;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.176) !important;
	background-color: #00afda;
	background: -webkit-linear-gradient(left, #00afda, #1b5dab);
	background: -o-linear-gradient(right, #00afda, #1b5dab);
	background: -moz-linear-gradient(right, #00afda, #1b5dab);
	background: linear-gradient(to right, #00afda, #1b5dab);
}
</style>
<style type="text/css">

</style>
<header class="header">
	<div id="toggle-nav-id" class="toggle-nav">
		<div class="icon-reorder tooltips" data-original-title="เปิด/ปิด เมนู" data-placement="bottom"><i class="icon_menu"></i></div>
	</div>

	<!--logo start-->
	<a href="#">
		<img class="header-img" alt="" src="${pageContext.request.contextPath}/ui/img/logo_main_exam.png" height="58"/>
	</a>
	<!--logo end-->
	
	<div class="top-nav notification-row">
		<!-- notificatoin dropdown start-->
        <ul class="nav pull-right top-menu">
			<!-- user login dropdown start-->
			<li class="dropdown">
				<a href="#" style="z-index: 900;text-align: right;">
					<span class="username">${SSUser.fullName}</span>
				</a>
				<a href="#" style="font-size:15px;font-family: pridi;top: -16px;margin-bottom: -19px;z-index: 100;">
					${SSUser.department}
				</a>
			</li>
			<!-- logout-->
	      	<li class="dropdown topbar-user pull-right">
				<a href="index.htm?mode=index" style="color: #fff;margin-top: 10px;" title="ออกจากระบบ">
					<i class="fa fa-sign-out" style="font-size: 20px;"></i>
					<span style="font-size:16px;font-family: pridi;">ออกจากระบบ</span>
				</a>
			</li>
          	<!-- logout-->
          	
		</ul>
	<!-- notificatoin dropdown end-->
	</div>
	
</header>
<!--header end-->

<%@ include file="/page/inc_menu.jsp"%>
