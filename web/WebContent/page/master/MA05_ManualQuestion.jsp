<!DOCTYPE html>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/tld/c.tld" prefix="c"%>
<%@taglib uri="/tld/fn.tld" prefix="fn"%>
<%@taglib uri="/tld/fmt.tld" prefix="fmt"%>

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
							<li><b>เพิ่มคำถามกำหนดเอง</b></li>
			            </ol>
				  	</div>
				</div>
				
				<div class="row">
  					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="form">
									<html:form action="/master"  styleId="questionFormID" styleClass="form-horizontal form-validate">
									<html:hidden property="mode"/>
		    						<html:hidden property="id"/>
		    						
		    						<div class="row">
		    							<div class="col-md-12"><a href="#" class="btn btn-default" onclick="submitFormInit('init');"><i class="fa fa-undo" aria-hidden="true"></i> &nbsp;ย้อนกลับ</a></div>
		    						</div>
		    						<hr />
		    						
		    						<div class="form-group">
						        		<label class="control-label col-sm-3"><span class="required">*</span> ตัวชี้วัด :</label>
										<div class="col-sm-4">
											<html:select property="indicatorId" styleClass="form-control">
												<html:option value="">-- เลือก --</html:option>
												<html:optionsCollection property="comboIndicator" value="id" label="nameShort"/>
											</html:select>
										</div>
									</div>
		    						
									<div class="form-group">
						        		<label class="control-label col-sm-3">หัวข้อ :</label>
										<div class="col-sm-6">
											<html:text property="title" styleId="title" maxlength="500" styleClass="form-control"></html:text>
										</div>
									</div>
									
									<div class="form-group">
						        		<label class="control-label col-sm-3"><span class="required">*</span> คำถาม :</label>
										<div class="col-sm-6">
											<html:text property="question" styleId="question" maxlength="500" styleClass="form-control"></html:text>
										</div>
									</div>
									
									<div class="form-group">
						        		<label class="control-label col-sm-3"><span class="required">*</span> รูปแบบ :</label>
										<div class="col-sm-8">
										<output>
											<html:radio property="templateType" value="1">&nbsp; รูปภาพ</html:radio>&nbsp;&nbsp;
											<html:radio property="templateType" value="2">&nbsp; ตาราง</html:radio>&nbsp;&nbsp;
											<html:radio property="templateType" value="3">&nbsp; ข้อความ</html:radio>
										</output>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8">
											<button class="btn btn-primary" type="button" onclick="submitFormAdd()"><i class="fa fa-save" aria-hidden="true"></i> &nbsp;บันทึก</button>
										</div>
									</div>
									
									</html:form>
								</div>
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
$(function() {
	$("#questionFormID").validate({
		rules: {
			indicatorId: "required",
			question: "required",
	  	},
	  	messages: {
	  		indicatorId: { required: "ระบุ: ตัวชี้วัด" },
	      	question: { required: "ระบุ: คำถาม" },
	  	},
	  	submitHandler: function(form) {
	  		showLoadingSwal();
	  		
	  		swal({
	  			title: "ยืนยันการบันทึกข้อมูล?",
	  			text: "คุณต้องการยืนยันการบันทึกข้อมูลนี้ใช่หรือไม่",
	  			type: "warning",
	  			showCancelButton: true,
	  			confirmButtonColor: '#337ab7',
	  			confirmButtonText: 'ตกลง',
	  			cancelButtonText: 'ยกเลิก',
	  			closeOnConfirm: false
	  		},
	  		function(isConfirm) {
	  			if (isConfirm) {
	  				document.forms[0].mode.value = "saveManualQuestion";
	  		   	 	document.forms[0].submit();
	  			}
	  		});
		}
	});
});
	
function submitFormInit(mode) {
    document.masterForm.mode.value = mode;
    document.masterForm.submit();
}
	
function submitFormAdd() {
	$("#questionFormID").submit();
}
</script>

</body>

</html>
