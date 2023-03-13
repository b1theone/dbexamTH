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
	    						
	    						<div class="row">
	    							<div class="col-md-12">
	    								<a href="#" class="btn btn-success" onclick="submitFormInit('initAddSuite');"><i class="fa fa-plus-square" aria-hidden="true"></i> &nbsp;เพิ่มชุดข้อสอบ</a>
	    							</div>
	    						</div>
	    						<hr />
	    						
	    						<div class="form-group">
					        		<label class="control-label col-sm-3">ชื่อชุดข้อสอบ :</label>
									<div class="col-sm-4">
										<html:text property="suiteSearch" styleId="suiteSearch" styleClass="form-control"></html:text>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-8">
										<button class="btn btn-primary" type="button" onclick="submitFormInit('initSuite')"><i class="fa fa-search" aria-hidden="true"></i> &nbsp;ค้นหา</button>
									</div>
								</div>
								
								<logic:present name="masterForm" property="resultList">
								<logic:notEmpty name="masterForm" property="resultList">
						            <table cellspacing="0" width="100%" class="table table-hover table-bordered table-gray">
						                 <thead>		                 	
						                 	<tr>
								                <th width="50" class="text-center">ลำดับ</th>
								                <th>ชุดข้อสอบ</th>
								                <th width="70" class="text-center">คะแนนเต็ม</th>
								                <th width="100" class="text-center">ระยะเวลา (นาที)</th>
								                <th width="70" class="text-center">สุ่มคำถาม</th>
								                <th width="80" class="text-center">จำนวนคำถาม</th>
								                <th width="70">&nbsp;</th>
								                <th width="70">&nbsp;</th>
								             </tr>
						                </thead>
						                <tbody>
						                	<logic:iterate id="item" name="masterForm" property="resultList" indexId="index">
						                	<tr>
						                        <td align="center"><%= index+1 %></td>
						                        <td align="left">${item.name}</td>
						                        <td align="center">${item.scoreMax}</td>
						                        <td align="center">${item.examTime}</td>
						                        <td align="center">${item.randomType eq 'Y' ? 'สุ่ม' : 'ไม่สุ่ม'}</td>
						                        <td align="center">${item.totalQuestion}</td>
						                        <td align="center">
						                        	<button type="button" class="btn btn-primary btn-xs" onclick="submitFormEdit('${item.id}','initSuiteQuest')"><span class="fa fa-save"></span> คำถาม</button>
						                        </td>
						                        <td align="center">
					                        		<button type="button" onclick="submitFormEdit('${item.id}','initEditSuite');" class="btn btn-primary btn-xs"><i class="fa fa-edit"></i></button>
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
		    		  	document.masterForm.mode.value = 'deleteSuite';
		    		  	document.masterForm.id.value = id;
		    		    document.masterForm.submit();  
		            }
		        }
		    }
		});
	}
</script>

</body>

</html>
