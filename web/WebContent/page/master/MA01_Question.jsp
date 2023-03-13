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
							<li><b>ข้อมูลคำถาม</b></li>
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
	    								<a href="#" class="btn btn-success" onclick="submitFormInit('initAdd');"><i class="fa fa-plus-square" aria-hidden="true"></i> &nbsp;เพิ่มคำถามจากต้นแบบ</a>
	    								<a href="#" class="btn btn-success" onclick="submitFormInit('initManualQuestion');"><i class="fa fa-plus-square" aria-hidden="true"></i> &nbsp;เพิ่มคำถามกำหนดเอง</a>
	    							</div>
	    						</div>
	    						<hr />
	    						
	    						<div class="form-group">
					        		<label class="control-label col-sm-3">ตัวชี้วัด :</label>
									<div class="col-sm-4">
										<html:select property="indicatorIdSearch" styleClass="form-control">
											<html:optionsCollection property="comboIndicator" value="id" label="nameShort"/>
										</html:select>
									</div>
								</div>
	    						
	    						<div class="form-group">
					        		<label class="control-label col-sm-3">คำถาม :</label>
									<div class="col-sm-4">
										<html:text property="questionSearch" styleId="questionSearch" styleClass="form-control"></html:text>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-8">
										<button class="btn btn-primary" type="button" onclick="submitFormInit('search')"><i class="fa fa-search" aria-hidden="true"></i> &nbsp;ค้นหา</button>
									</div>
								</div>
								
								<logic:present name="masterForm" property="resultList">
								<logic:notEmpty name="masterForm" property="resultList">
						            <table cellspacing="0" width="100%" class="table table-hover table-bordered table-gray">
						                 <thead>		                 	
						                 	<tr>
								                <th width="50" class="text-center">ลำดับ</th>
								                <th width="180">ตัวชี้วัด</th>
								                <th>หัวข้อ</th>
								                <th>คำถาม</th>
								                <th width="80" class="text-center">ประเภท</th>
								                <th width="70">&nbsp;</th>
								             </tr>
						                </thead>
						                <tbody>
						                	<logic:iterate id="item" name="masterForm" property="resultList" indexId="index">
						                	<tr>	             
					                        <td align="center"><%= index+1 %></td>
					                        <td align="left">${item.indicator.nameShort}</td>
					                        <td align="left">${item.title}</td>
					                        <td align="left">${item.question}</td>
					                        <td align="center">${item.createType eq '1' ? 'จากต้นแบบ' : 'กำหนดเอง'}</td>
					                        <td align="center">
					                        	<c:if test="${item.createType eq '1'}">
					                        		<button type="button" onclick="submitFormEdit('${item.id}','initEdit');" class="btn btn-primary btn-xs"><i class="fa fa-edit"></i></button>
					                        	</c:if>
					                        	<c:if test="${item.createType eq '2'}">
					                        		<button type="button" onclick="submitFormEdit('${item.id}','initManualImage');" class="btn btn-primary btn-xs"><i class="fa fa-edit"></i></button>
					                        	</c:if>
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
		    		  	document.masterForm.mode.value = 'delete';
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
