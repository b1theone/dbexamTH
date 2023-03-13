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
							<li><b>ข้อมูลชุดข้อสอบ</b></li>
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
	    						<html:hidden property="suiteQuestId"/>
	    						
	    						<div class="row">
	    							<div class="col-md-12">
	    								<a href="#" class="btn btn-success" onclick="submitFormInit('initAddSuiteQuest');"><i class="fa fa-plus-square" aria-hidden="true"></i> &nbsp;เพิ่มคำถาม</a>
	    								<a href="#" class="btn btn-default" onclick="submitFormInit('initSuite');"><i class="fa fa-undo" aria-hidden="true"></i> &nbsp;ย้อนกลับ</a>
	    							</div>
	    						</div>
	    						<hr />
	    						
	    						<div class="row">
	    							<div class="col-md-12 fbold">
	    								ชุดข้อสอบ : ${PExamSuite.name}
	    							</div>
	    						</div>
								
								<logic:present name="masterForm" property="questionList">
								<logic:notEmpty name="masterForm" property="questionList">
						            <table cellspacing="0" width="100%" class="table table-hover table-bordered table-gray">
						                 <thead>		                 	
						                 	<tr>
								                <th width="50" class="text-center">ลำดับ</th>
								                <th width="180">ตัวชี้วัด</th>
								                <th>หัวข้อ</th>
								                <th>คำถาม</th>
								                <th width="40">&nbsp;</th>
								             </tr>
						                </thead>
						                <tbody>
						                	<logic:iterate id="item" name="masterForm" property="questionList" indexId="index">
						                	<tr>	             
					                        <td align="center"><%= index+1 %></td>
					                        <td align="left">${item.examQuestion.indicator.nameShort}</td>
					                        <td align="left">${item.examQuestion.title}</td>
					                        <td align="left">${item.examQuestion.question}</td>
					                        <td align="center">
					                        	<button type="button" onclick="submitFormRemove('${item.id}');" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i></button>
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
		        
			</section>
      
		</section>
    <!--main content end-->
  </section>
  <!-- container section start -->
  
<script type="text/javascript">
	function submitFormInit(mode) {
	    document.masterForm.mode.value = mode;
	    document.masterForm.submit();
	}
	
	function submitFormEdit(id,mode) {
	    document.masterForm.mode.value = mode;
	  	document.masterForm.id.value = id;
	    document.masterForm.submit();
	}
	
	function submitFormRemove(id) {
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
		    		  	document.masterForm.mode.value = 'deleteSuiteQuest';
		    		  	document.masterForm.suiteQuestId.value = id;
		    		    document.masterForm.submit();  
		            }
		        }
		    }
		});
	}
</script>

</body>

</html>
