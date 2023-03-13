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
							<li><b>เพิ่มข้อมูลคำถาม</b></li>
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
	    						<html:hidden property="ids" styleId="ids"/>
	    						
	    						<div class="row">
	    							<div class="col-md-12"><a href="#" class="btn btn-default" onclick="submitFormInit('init');"><i class="fa fa-undo" aria-hidden="true"></i> &nbsp;ย้อนกลับ</a></div>
	    						</div>
	    						<hr />
	    						
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
										<button class="btn btn-primary" type="button" onclick="submitFormInit('searchAdd')"><i class="fa fa-search" aria-hidden="true"></i> &nbsp;ค้นหา</button>
									</div>
								</div>
								
								<logic:present name="masterForm" property="resultList">
								<logic:notEmpty name="masterForm" property="resultList">
									<div class="form-group">
										<div class="col-sm-2"><html:text property="questionAmount" styleId="questionAmount" styleClass="form-control" maxlength="2" placeHolder="จำนวนคำถาม"></html:text></div>
										<div class="col-sm-8">
											<button type="button" class="btn btn-blue" id="btnSave" disabled="disabled" onclick="doSampleQuestion()">สร้างคำถาม &rarr;</button>
										</div>
									</div>
									<table cellspacing="0" width="100%" class="table table-hover table-bordered table-blue">
										<thead>
				                 		<tr>
				                 			<th width="40" class="text-center"><input type="checkbox" onclick="checkAll();"></th>
							                <th width="40" class="text-center">ลำดับ</th>
							                <!-- <th width="180">ตัวชี้วัด</th> -->
							                <th>ชื่อรูปแบบคำถาม</th>
							                <th class="text-center">ประเภท</th>
							                <th>ตัวอย่างคำถาม</th>
						             	</tr>
						                </thead>
						                <tbody>
						                <logic:iterate id="item" name="masterForm" property="resultList" indexId="index">
					                	<tr>	             
					                		<td align="center">
					                			<html:multibox property="checkIds" styleId="checkIds" onclick="checkDisabled();"><bean:write name="item" property="id"/></html:multibox>
					                		</td>
					                        <td align="center"><%= index+1 %></td>
					                        <%-- <td align="left">${item.indicator.nameShort}</td> --%>
					                        <td align="left">${item.name}</td>
					                        <td align="center">
					                        	<c:if test="${item.templateType eq '1'}">รูปภาพ</c:if>
					                        	<c:if test="${item.templateType eq '2'}">ตาราง</c:if>
					                        	<c:if test="${item.templateType eq '3'}">ข้อความ</c:if>
					                        </td>
					                       	<td align="left">${item.question}</td>
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
	
	function submitFormEdit(id, mode) {
	    document.masterForm.mode.value = mode;
	  	document.masterForm.id.value = id;
	    document.masterForm.submit();
	}
	
	function checkAll(){
		var viewCheck = document.getElementsByName('checkIds');
	  	for (var i=0;i<viewCheck.length;i++) {
	  		if (checkIds == 'check') {
	  			viewCheck[i].checked = false;
				$("#btnSave").attr("disabled", true);
	  		} else {
	  			viewCheck[i].checked = true;	
				$("#btnSave").attr("disabled", false);
	  		}
	  	}	  	
	  	if (checkIds == 'check') {
	  		checkIds = 'uncheck';
	  	}else {
	  		checkIds = 'check';
	  	}
	}
	
	function checkDisabled(id){
	    var count = 1;
	    var num = 0;
	    var f = 0;
		var viewCheck = document.getElementsByName('checkIds');
		for (var i=0;i<viewCheck.length;i++){
			num++;
			if(viewCheck[i].checked == false){	    	
				if(num > count){	    						    					
					$("#btnSave").attr("disabled", false);
    				count++;
				} else if(count == 1 ){
					$("#btnSave").attr("disabled", true);
	    		} 
	    		f++;
			} else {
				$("#btnSave").attr("disabled", true);
	    		count-1;
	    		f-1;
			}		
		}
		if(count == num && count == f && num == f){
			$("#btnSave").attr("disabled", true);
		} else {
			$("#btnSave").attr("disabled", false);
		}
	}
	
	function doSampleQuestion() {
		document.masterForm.mode.value = 'sample';
    	document.masterForm.submit();
	}
</script>

</body>

</html>
