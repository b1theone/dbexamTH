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
							<li><b>บันทึกข้อสอบลงชุดข้อสอบ</b></li>
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
	    						<html:hidden property="questionId"/>
	    						
	    						<div class="row">
	    							<div class="col-md-12"><a href="#" class="btn btn-default" onclick="submitFormInit('initSuiteQuest');"><i class="fa fa-undo" aria-hidden="true"></i> &nbsp;ย้อนกลับ</a></div>
	    						</div>
	    						<hr />
	    						
	    						<div class="form-group">
					        		<label class="control-label col-sm-3">ชุดข้อสอบ :</label>
									<div class="col-sm-9"><output>${PExamSuite.name}</output></div>
								</div>
								
								<div class="form-group">
					        		<label class="control-label col-sm-3">ตัวชี้วัด :</label>
									<div class="col-sm-4">
										<html:select property="indicatorIdAdd" styleClass="form-control">
											<html:optionsCollection property="comboIndicator" value="id" label="nameShort"/>
										</html:select>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-8">
										<button class="btn btn-primary" type="button" onclick="submitFormInit('searchAddSuiteQuest')"><i class="fa fa-search" aria-hidden="true"></i> &nbsp;ค้นหา</button>
									</div>
								</div>
								
								<logic:present name="masterForm" property="questionAddList">
								<logic:notEmpty name="masterForm" property="questionAddList">
									<div class="form-group">
										<div class="col-sm-12">
											<button class="btn btn-primary" type="button" onclick="submitFormSave()"><i class="fa fa-save" aria-hidden="true"></i> &nbsp;บันทึก</button>
										</div>
									</div>
									
									<table cellspacing="0" width="100%" class="table table-hover table-bordered table-blue">
										<thead>
				                 		<tr>
				                 			<th width="40" class="text-center">&nbsp;</th>
							                <th width="40" class="text-center">ลำดับ</th>
							                <th>หัวข้อคำถาม</th>
							                <th>คำถาม</th>
							                <th width="40">&nbsp;</th>
						             	</tr>
						                </thead>
						                <tbody>
						                <logic:iterate id="item" name="masterForm" property="questionAddList" indexId="index">
					                	<tr>
					                		<td align="center">
					                			<html:multibox property="checkIds" styleId="checkIds"><bean:write name="item" property="id"/></html:multibox>
					                		</td>
					                        <td align="center"><%= index+1 %></td>
					                        <td align="left">${item.title}</td>
					                        <td align="left">${item.question}</td>
					                        <td align="center">
					                        	<c:if test="${item.createType eq '1'}">
					                        		<button type="button" onclick="submitFormView('${item.id}','initView');" class="btn btn-success btn-xs"><i class="fa fa-search"></i></button>
					                        	</c:if>
					                        	<c:if test="${item.createType eq '2'}">
					                        		<button type="button" onclick="submitFormView('${item.id}','initViewManual');" class="btn btn-success btn-xs"><i class="fa fa-search"></i></button>
					                        	</c:if>
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
$(document).ready(function() {
	
});

function submitFormSave() {
	document.forms[0].mode.value = 'saveSuiteQuest';
	document.forms[0].submit();
}

function submitFormInit(mode) {
    document.masterForm.mode.value = mode;
    document.masterForm.submit();
}

function submitFormView(id,mode) {
	document.masterForm.mode.value = mode;
	document.masterForm.questionId.value = id;
    document.masterForm.submit();
}
</script>

</body>

</html>
