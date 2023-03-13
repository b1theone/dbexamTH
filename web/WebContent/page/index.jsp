<!DOCTYPE html>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/tld/c.tld" prefix="c"%>

<%@ include file="/page/inc_header_script.jsp"%>

<body>

<script language="javascript" type="text/javascript">
	$(document).ready(function() {
		
		jQuery.ajaxSetup({
			  beforeSend: function() {
			     $('#loader').show();
			  },
			  complete: function(){
			     $('#loader').hide();
			  },
			  success: function() {}
		});

		$("#eduForm").validate({
			rules: {
				un: "required",
				pw: "required",
			},
			highlight: function(element) {
	            $(element).closest('.form-control').addClass('has-error-input');
	        },
	        unhighlight: function(element) {
	            $(element).closest('.form-control').removeClass('has-error-input');
	        },
	        errorElement: 'span',
	        errorClass: 'has-error-block',
	        errorPlacement: function(error, element) {},
	      	submitHandler: function(form) {
	      		document.forms[0].mode.value = 'login';
		   	 	document.forms[0].submit();
			}
		});
		
		$('#un').focus();
	});
  	
	function submitEnter() {
		if (checkKeyEnter() == true) submitFormLogin();
	}
	function checkKeyEnter(){
	   if(event.keyCode==13) return true;
	}
	function submitFormLogin() {
		$("#eduForm").submit();
		//document.forms[0].action = 'master.htm?mode=init';
   	 	//document.forms[0].submit();
	}
	
	function showDialogLogin(url,title,size){
		$.post(url,{}, 
			function(results) {
				var $textAndPic = $('<div id="dialogs"></div>');
				$textAndPic.append(results);
				BootstrapDialog.show({
					type: BootstrapDialog.TYPE_DEFAULT,
					title: title,
		          	cssClass : size,
		          	message: $textAndPic,
		          	draggable: true	 ,
		          	buttons:[{
		                 label: 'ปิดหน้าจอ',
		                 action: function(dialogItself){
		                     dialogItself.close();
		                 }
		            }]
		       });				 
		});	
	}
</script>

<style type="text/css">
	html,body{
	    position: relative;
	    height: 100%;
	}
	body {
		background-color: #d5e6f0;
	}
	#main-login {
		position: relative;
		min-width: 400px;
		min-height: 350px; /* 450px */
		width: 400px;
		height: 350px; /* 450px */
	    margin: 40px auto 20px;
	    background-color: #ffffff;
	    padding: 5px 0px 0px 0px;
	    -webkit-border-radius: 8px;
		-moz-border-radius: 8px;
		-ms-user-select: none;
		border-radius: 8px;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.3) !important;
	}
	#login-left {
		background-image: url("${pageContext.request.contextPath}/vec/ui/img/bg/bg_login_left.png");
		background-repeat: no-repeat;
		background-position: bottom left;
		background-color: #f57c00;
		height: 100%;
		-webkit-border-radius: 8px 0px 0px 8px;
		-moz-border-radius: 8px 0px 0px 8px;
		-ms-user-select: none;
		border-radius: 8px 0px 0px 8px;
		font-family: pridien;
		color: #ffffff;
	}
	.login-title {
		color: #000; /* #ff6e7b */
		font-family: pridi, pridien;
		font-size: 1.8em;
		font-weight: bold;
	}
	.login-box {
		margin-top: 25px;
	}
	
	.login-box a {
		color: #999;
		text-decoration: underline;
	}
	.login-box a:hover, .login-box a:focus {
		color: '#f57c00';
		text-decoration: underline;
	}
</style>

<html:form action="/index" styleId="eduForm">
<html:hidden property="mode"/>
	
	<div class="container">
		<div class="row" style="margin-top:10px">
			<div id="main-login">
				<div class="col-sm-12" style="min-width: 300px;">
					<div style="text-align: center;margin-top: 50px;">
						<%-- <img alt="" src="${pageContext.request.contextPath}/vec/ui/img/bg/logo-login.png" height="100"/> --%>
					</div>
					<div style="text-align: center;margin-top: 5px;">
						<div class="login-title">ระบบคลังข้อมูลแบบทดสอบ</div>
						<div class="login-box">
							<!-- <div style="margin-bottom: 5px;">เข้าใช้งานระบบ</div> -->
							
							<div class="form-group">
								<div class="col-sm-10" style="margin: 0 auto;float: none;">
									<html:text property="un" maxlength="75" styleClass="form-control" styleId="un" onkeypress="submitEnter();" placeHolder="ชื่อผู้ใช้งาน" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-10" style="margin: 0 auto;float: none;">
									<html:password property="pw" maxlength="75" styleClass="form-control" styleId="pw" onkeypress="submitEnter();" placeHolder="รหัสผ่าน"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-10" style="margin: 0 auto;float: none;">
									<button type="button" class="btn btn-blue btn-sm" style="width:100%;" onclick="submitFormLogin();">เข้าสู่ระบบ</button>
								</div>
							</div>
							
							<div class="form-group">
							<bean:define id="msgErrorCheck" value="${not empty msgError}"/>
							<logic:equal name="msgErrorCheck" value="true">
								<div class="col-sm-10 fred" style="margin: 0 auto;float: none;">
									<div class="alert alert-danger" style="padding: 5px;font-size: 12px;">${msgError}</div>
								</div>
							</logic:equal>
							<logic:equal name="msgErrorCheck" value="false">
								<div class="col-sm-10" style="margin: 0 auto;float: none;">&nbsp;</div>
							</logic:equal>
							</div>
							
							<!-- <div style="font-size: 11px;color: #aaa;">ศูนย์เทคโนโลยีและกำลังคนอาชีวศึกษา สำนักงานคณะกรรมการการอาชีวศึกษา</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</html:form>

</body>
</html>