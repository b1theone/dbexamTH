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
							<li><b>เพิ่มตัวเลือกคำถาม</b></li>
			            </ol>
				  	</div>
				</div>
				
				<div class="row">
  					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="form">
									<html:form action="/master"  styleId="questImageFormID" styleClass="form-horizontal form-validate" enctype="multipart/form-data">
									<html:hidden property="mode"/>
		    						<html:hidden property="id"/>
		    						<html:hidden property="questImageId"/>
		    						
		    						<div class="row">
		    							<div class="col-md-12"><a href="#" class="btn btn-default" onclick="submitFormInit('init');"><i class="fa fa-undo" aria-hidden="true"></i> &nbsp;ย้อนกลับ</a></div>
		    						</div>
		    						<hr />
		    						
		    						<div class="form-group">
						        		<label class="control-label col-sm-3">ตัวชี้วัด :</label>
										<div class="col-sm-8"><output>${PQuestion.indicator.nameShort}</output></div>
									</div>
									
									<div class="form-group">
						        		<label class="control-label col-sm-3">หัวข้อ :</label>
										<div class="col-sm-8">
											<html:text property="title" styleId="title" maxlength="500" styleClass="form-control"></html:text>
										</div>
									</div>
		    						<div class="form-group">
						        		<label class="control-label col-sm-3">คำถาม :</label>
										<div class="col-sm-8">
											<html:text property="question" styleId="question" maxlength="500" styleClass="form-control"></html:text>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8">
											<button class="btn btn-success" type="button" onclick="doEditQuestion()"><i class="fa fa-edit" aria-hidden="true"></i> &nbsp;แก้ไขคำถาม</button>
											<button class="btn btn-danger" type="button" onclick="doRemoveQuestion()"><i class="fa fa-trash-o" aria-hidden="true"></i> &nbsp;ลบคำถาม</button>
										</div>
									</div>
									
									<hr/>
									
									<div class="row">
		    							<div class="col-md-12"><h3>ตัวเลือกคำถาม</h3></div>
		    						</div>
									
									<div class="form-group">
						        		<label class="control-label col-sm-3">รูปแบบ :</label>
										<div class="col-sm-8">
										<output>
											<logic:equal name="masterForm" property="templateType" value="1">รูปภาพ</logic:equal>
											<logic:equal name="masterForm" property="templateType" value="2">ตาราง</logic:equal>
											<logic:equal name="masterForm" property="templateType" value="3">ข้อความ</logic:equal>
										</output>
										</div>
									</div>
									
									<logic:equal name="masterForm" property="templateType" value="1">
									<c:if test="${msgError eq 'ext'}">
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8">
											<div class="alert alert-warning" style="font-size: 1em;margin-bottom: 0px;">
												<i class="fa fa-exclamation-triangle"></i> &nbsp;ไฟล์ที่ระบุไม่ถูกต้อง
											</div>
										</div>
									</div>
									</c:if>
									
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-6">
											<div class="input-group input-file1" name="Fichier1">
								    			<html:text property="uploadFileName1" styleId="uploadFileName1" styleClass="form-control" placeHolder="เลือกรูปภาพ..."></html:text>
									            <span class="input-group-btn">
								        			<button class="btn btn-grey btn-choose" type="button"><i class="fa fa-download" aria-hidden="true"></i> เลือกรูปภาพ (jpg,png)</button>
								    			</span>
											</div>
										</div>
									</div>
									<div class="form-group">
					        			<label class="control-label col-sm-3">ชื่อรูป :</label>
										<div class="col-sm-3"><html:text property="fileName1" styleId="fileName1" maxlength="150" styleClass="form-control"></html:text></div>
									</div>
									<div class="form-group">
					        			<label class="control-label col-sm-3">ชื่อหน่วย :</label>
										<div class="col-sm-3"><html:text property="unitName1" styleId="unitName1" maxlength="100" styleClass="form-control"></html:text></div>
										<div class="col-sm-6 forange"><output>เช่น เช่น น้ำหนัก, กิโลกรัมละ, ราคา เป็นต้น</output></div>
									</div>
									<div class="form-group">
					        			<label class="control-label col-sm-3"><span class="required">*</span> จำนวน :</label>
										<div class="col-sm-3"><html:text property="amount1" styleId="amount1" maxlength="6" styleClass="form-control"></html:text></div>
									</div>
									<div class="form-group">
					        			<label class="control-label col-sm-3">หน่วย :</label>
										<div class="col-sm-3"><html:text property="unit1" styleId="unit1" maxlength="50" styleClass="form-control"></html:text></div>
										<div class="col-sm-6 forange"><output>เช่น เช่น กิโลกรัม, บาท เป็นต้น</output></div>
									</div>
									<div class="form-group">
						        		<label class="control-label col-sm-3"><span class="required">*</span> แสดงรูปเท่านั้น :</label>
										<div class="col-sm-8">
										<output>
											<html:radio property="imageOnly" value="Y">&nbsp; ใช่</html:radio>&nbsp;&nbsp;
											<html:radio property="imageOnly" value="N">&nbsp; ไม่ใช่</html:radio>
										</output>
										</div>
									</div>
									</logic:equal>
									
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8">
											<button class="btn btn-primary" type="button" onclick="submitFormAdd()"><i class="fa fa-save" aria-hidden="true"></i> &nbsp;บันทึก</button>
											<html:file property="uploadFile1" styleId="uploadFile1" styleClass="input-ghost" style="visibility:hidden; height:0;" accept=".jpg,.jpeg,.png"></html:file>
										</div>
									</div>
									
									<logic:present name="masterForm" property="imageList">
									<logic:notEmpty name="masterForm" property="imageList">
							            <table cellspacing="0" width="100%" class="table table-hover table-bordered table-gray">
							                 <thead>		                 	
							                 	<tr>
									                <th width="50" class="text-center">ลำดับ</th>
									                <logic:equal name="masterForm" property="templateType" value="1">
									                	<th>รูป</th>
									                </logic:equal>
									                <th width="180">ชื่อ</th>
									                <th>ชื่อหน่วย</th>
									                <th>จำนวน</th>
									                <th>หน่วย</th>
									                <th width="50">&nbsp;</th>
									             </tr>
							                </thead>
							                <tbody>
							                	<logic:iterate id="item" name="masterForm" property="imageList" indexId="index">
							                	<tr>
							                        <td align="center"><%= index+1 %></td>
							                        <logic:equal name="masterForm" property="templateType" value="1">
							                        	<td><img src="${pageContext.request.contextPath}/image?id=${item.imageId}" height="80" style="text-align: center;"/></td>
							                        </logic:equal>
							                        <td align="left">${item.imageName}</td>
							                        <td align="left">${item.unitName}</td>
							                        <td align="left"><fmt:formatNumber value="${item.amount}" pattern="#,###,##0"></fmt:formatNumber></td>
							                        <td align="left">${item.unit}</td>
							                        <td align="center">
							                        	<button type="button" onclick="doRemoveImage('${item.id}');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i></button>
													</td>
						                     	</tr>
							                	</logic:iterate> 
											</tbody>
										</table>
									</logic:notEmpty>
									</logic:present>
									
									</html:form>
									
									<html:form action="/master"  styleId="questAnswerFormID" styleClass="form-horizontal form-validate">
									<html:hidden property="mode"/>
		    						<html:hidden property="id"/>
		    						<html:hidden property="questAnswerId"/>
									
									<hr/>
									<div class="row">
		    							<div class="col-md-12"><h3>คำตอบ</h3></div>
		    						</div>
									
									<div class="form-group">
						        		<label class="control-label col-sm-3"><span class="required">*</span> ลำดับ :</label>
										<div class="col-sm-1">
											<html:select property="answerSeq" styleClass="form-control">
												<html:option value="1">1</html:option>
												<html:option value="2">2</html:option>
												<html:option value="3">3</html:option>
												<html:option value="4">4</html:option>
											</html:select>
										</div>
									</div>
									
									<div class="form-group">
					        			<label class="control-label col-sm-3"><span class="required">*</span> คำตอบ :</label>
										<div class="col-sm-6"><html:text property="answerName" styleId="answerName" maxlength="300" styleClass="form-control"></html:text></div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-6">
										<output>
											<html:radio property="answerYN" value="Y">&nbsp; ถูก</html:radio>&nbsp;&nbsp;
											<html:radio property="answerYN" value="N">&nbsp; ผิด</html:radio>
										</output>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-8">
										<c:if test="${PAnswerSize < 4}">
											<button class="btn btn-primary" type="button" onclick="doSaveAnswer()"><i class="fa fa-save" aria-hidden="true"></i> &nbsp;บันทึก</button>
										</c:if>
										<c:if test="${PAnswerSize eq 4}">
											<button class="btn btn-primary" type="button" disabled="disabled"><i class="fa fa-save" aria-hidden="true"></i> &nbsp;บันทึก</button>
											<span class="forange">&nbsp;&nbsp;ครบ 4 คำตอบแล้ว ไม่สามารถเพิ่มคำตอบอื่นได้อีก</span>
										</c:if>
										</div>
									</div>
									
									<logic:present name="masterForm" property="answerList">
									<logic:notEmpty name="masterForm" property="answerList">
							            <table cellspacing="0" width="100%" class="table table-hover table-bordered table-gray">
							                 <thead>		                 	
							                 	<tr>
									                <th width="50" class="text-center">ลำดับ</th>
									                <th>คำตอบ</th>
									                <th width="60" class="text-center">ถูก/ผิด</th>
									                <th width="50">&nbsp;</th>
									             </tr>
							                </thead>
							                <tbody>
							                	<logic:iterate id="item" name="masterForm" property="answerList" indexId="index">
							                	<tr>
							                        <td align="center">${item.seq}</td>
							                        <td align="left">${item.answer}</td>
							                        <td align="center">
								                        <c:if test="${item.answerYn eq 'Y'}"><span class="fblue fbold">ถูก</span></c:if>
								                        <c:if test="${item.answerYn eq 'N'}">ผิด</c:if>
							                        </td>
							                        <td align="center">
							                        	<button type="button" onclick="doRemoveAnswer('${item.id}');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i></button>
													</td>
						                     	</tr>
							                	</logic:iterate> 
											</tbody>
										</table>
									</logic:notEmpty>
									</logic:present>
									
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
	$("#questImageFormID").validate({
		rules: {
			amount1: "required",
	  	},
	  	messages: {
	  		amount1: { required: "ระบุ: จำนวน" },
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
	  				document.forms[0].mode.value = "saveManualImage";
	  		   	 	document.forms[0].submit();
	  			}
	  		});
		}
	});
	
	$("#questAnswerFormID").validate({
		rules: {
			answerName: "required",
	  	},
	  	messages: {
	  		answerName: { required: "ระบุ: คำตอบ" },
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
	  				document.forms[1].mode.value = "saveManualAnswer";
	  		   	 	document.forms[1].submit();
	  			}
	  		});
		}
	});
	
	bs_input_file1();
});

function bs_input_file1() {
	$(".input-file1").before(
		function() {
			if ( ! $(this).prev().hasClass('input-ghost') ) {
				var element = $('#uploadFile1');
				element.change(function(){
					element.next(element).find('input').val((element.val()).split('\\').pop());
				});
				$(this).find("button.btn-choose").click(function(){
					element.click();
				});
				$(this).find("button.btn-reset").click(function(){
					element.val(null);
					$(this).parents(".input-file1").find('input').val('');
				});
				$(this).find('input').css("cursor","pointer");
				$(this).find('input').mousedown(function() {
					$(this).parents('.input-file1').prev().click();
					return false;
				});
				return element;
			}
		}
	);
}

function submitFormInit(mode) {
    document.forms[0].mode.value = mode;
    document.forms[0].submit();
}

function submitFormAdd() {
	$("#questImageFormID").submit();
}

function doEditQuestion() {
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
			document.forms[0].mode.value = "editManualQuestion";
	   	 	document.forms[0].submit();
		}
	});
}

function doRemoveQuestion() {
	bootbox.dialog({
		title: 'ยืนยันการลบข้อมูล',
	    message: 'คุณต้องการยืนยันการลบข้อมูลนี้ใช่หรือไม่',
	    buttons: {
	        Cancel: {
	            label: 'ยกเลิก',
	            className: "btn-default",
	            callback: function () {
	            }
	        },
	        success: {
	            label: 'ตกลง',
	            className: "btn-danger",
	            callback: function () {
	    		  	document.forms[0].mode.value = 'removeManualQuestion';
	    		    document.forms[0].submit();  
	            }
	        }
	    }
	});
}

function doRemoveImage(id) {
	bootbox.dialog({
		title: 'ยืนยันการลบข้อมูล',
	    message: 'คุณต้องการยืนยันการลบข้อมูลนี้ใช่หรือไม่',
	    buttons: {
	        Cancel: {
	            label: 'ยกเลิก',
	            className: "btn-default",
	            callback: function () {
	            }
	        },
	        success: {
	            label: 'ตกลง',
	            className: "btn-danger",
	            callback: function () {
	    		  	document.forms[0].mode.value = 'removeManualImage';
	    		  	document.forms[0].questImageId.value = id;
	    		    document.forms[0].submit();  
	            }
	        }
	    }
	});
}

function doSaveAnswer() {
	$("#questAnswerFormID").submit();
}

function doRemoveAnswer(id) {
	bootbox.dialog({
		title: 'ยืนยันการลบข้อมูล',
	    message: 'คุณต้องการยืนยันการลบข้อมูลนี้ใช่หรือไม่',
	    buttons: {
	        Cancel: {
	            label: 'ยกเลิก',
	            className: "btn-default",
	            callback: function () {
	            }
	        },
	        success: {
	            label: 'ตกลง',
	            className: "btn-danger",
	            callback: function () {
	    		  	document.forms[1].mode.value = 'removeManualAnswer';
	    		  	document.forms[1].questAnswerId.value = id;
	    		    document.forms[1].submit();  
	            }
	        }
	    }
	});
}
</script>

</body>

</html>
