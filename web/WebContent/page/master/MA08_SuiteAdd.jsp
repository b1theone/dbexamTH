<!DOCTYPE html>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/tld/c.tld" prefix="c"%>
<%@taglib uri="/tld/fn.tld" prefix="fn"%>

<%@ include file="/page/inc_header_script.jsp"%>

<body>
	<!-- container section start -->
	<section id="container" class="">

		<%@ include file="/page/inc_header.jsp"%>

    	<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
        		<!--overview start-->
				<div class="row">
					<div class="col-lg-12">
				    	<ol class="breadcrumb">
							<li><i class="fa fa-home"></i> <a href="#">หน้าหลัก</a></li>
							<li><b>บันทึกชุดข้อสอบ</b></li>
			            </ol>
				  	</div>
				</div>
				
				<div class="row">
  					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<html:form action="/master"  styleId="eduForm" styleClass="form-horizontal form-validate">
								<html:hidden property="mode"/>
	    						<html:hidden property="id"/>
	    						
	    						<div class="row">
	    							<div class="col-md-12"><a href="#" class="btn btn-default" onclick="submitFormInit('initSuite');"><i class="fa fa-undo" aria-hidden="true"></i> &nbsp;ย้อนกลับ</a></div>
	    						</div>
	    						<hr />
	    						
	    						<div class="form-group">
					        		<label class="control-label col-sm-3">ชื่อชุดข้อสอบ :</label>
									<div class="col-sm-6">
										<html:text property="suiteName" styleId="suiteName" styleClass="form-control"></html:text>
									</div>
								</div>
								
								<div class="form-group">
					        		<label class="control-label col-sm-3">คะแนนเต็ม :</label>
									<div class="col-sm-2">
										<html:text property="scoreMax" styleId="scoreMax" styleClass="form-control"></html:text>
									</div>
									<div class="col-sm-3"><output>คะแนน</output></div>
								</div>
								
								<div class="form-group">
					        		<label class="control-label col-sm-3">คะแนนต่อข้อ :</label>
									<div class="col-sm-2">
										<html:text property="scorePoint" styleId="scorePoint" styleClass="form-control"></html:text>
									</div>
									<div class="col-sm-3"><output>คะแนน</output></div>
								</div>
								
								<div class="form-group">
					        		<label class="control-label col-sm-3">คะแนนที่ผ่าน :</label>
									<div class="col-sm-2">
										<html:text property="scorePass" styleId="scorePass" styleClass="form-control"></html:text>
									</div>
									<div class="col-sm-3"><output>คะแนน</output></div>
								</div>
								
								<div class="form-group">
					        		<label class="control-label col-sm-3">ระยะเวลาทดสอบ :</label>
									<div class="col-sm-2">
										<html:text property="examTime" styleId="examTime" styleClass="form-control"></html:text>
									</div>
									<div class="col-sm-3"><output>นาที</output></div>
								</div>
								
								<div class="form-group">
					        		<label class="control-label col-sm-3">สุ่มคำถาม :</label>
									<div class="col-sm-4">
										<html:radio property="randomType" value="N">&nbsp; ไม่สุ่ม</html:radio>
										&nbsp;&nbsp;
										<html:radio property="randomType" value="Y">&nbsp; สุ่ม</html:radio>
									</div>
								</div>
								
								<div class="form-group">
					        		<label class="control-label col-sm-3">สถานะ :</label>
									<div class="col-sm-4">
										<html:radio property="suiteActive" value="N">&nbsp; ปิด</html:radio>
										&nbsp;&nbsp;
										<html:radio property="suiteActive" value="Y">&nbsp; เปิด</html:radio>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-8">
										<button class="btn btn-primary" type="button" onclick="submitFormSave()"><i class="fa fa-save" aria-hidden="true"></i> &nbsp;บันทึก</button>
									</div>
								</div>

							</html:form>
							</div>
						</div>
          			</div>
				</div>
		        
			</section>
      
		</section>
    <!--main content end-->
  </section>
  <!-- container section start -->
  
<script type="text/javascript">
$(document).ready(function() {
	$("#eduForm").validate({
		rules: {
			suiteName: "required",
			scoreMax: "required",
			scorePass: "required",
			examTime: "required",
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
      		document.forms[0].mode.value = 'saveSuite';
	   	 	document.forms[0].submit();
		}
	});
});

function submitFormSave() {
	$("#eduForm").submit();
}

function submitFormInit(mode) {
    document.masterForm.mode.value = mode;
    document.masterForm.submit();
}
</script>

</body>

</html>
