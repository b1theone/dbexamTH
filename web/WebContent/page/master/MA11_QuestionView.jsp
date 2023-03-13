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
	    						<html:hidden property="suiteQuestId"/>
	    						<html:hidden property="questionId"/>
	    						
	    						<div class="row">
	    							<div class="col-md-12"><a href="#" class="btn btn-default" onclick="submitFormInit('searchAddSuiteQuest');"><i class="fa fa-undo" aria-hidden="true"></i> &nbsp;ย้อนกลับ</a></div>
	    						</div>
	    						<hr />
								
								<c:if test="${PExamQuestion ne null}">
								<div class="row">
				                	<div class="col-sm-12">
				                       	<div class="col-sm-12 text-center">
				                       		<bean:write name="masterForm" property="title"/>
				                       	</div>
				                       	
				                       	<div class="col-sm-12">
				                       	<c:if test="${PExamQuestion.questionType eq '1'}">
				                       	<table style="width: 100%;margin-top: 5px;margin-bottom: 10px;">
					                       	<tr>
					                       		<c:forEach items="${PExamQuestion.imageList}" var="iquest">
					                       		<td align="center" style="border: 1px solid #999;">
					                       			<div style="height: 100px;"><img src="${pageContext.request.contextPath}/image?id=${iquest.imageId}" height="100" style="text-align: center;"/></div>
					                       			<div>${iquest.imageName}</div>
					                       			<div>${iquest.unitName} ${iquest.amount} ${iquest.unit}</div>
					                       		</td>
					                       		</c:forEach>
					                       	</tr>
				                       	</table>
				                       	</c:if>
				                       	<c:if test="${PExamQuestion.questionType eq '2'}">
				                       		<c:if test="${PExamQuestion.rateType eq 'N'}">
				                       			<table style="margin-top: 5px;margin-bottom: 10px;" align="center" cellpadding="5">
				                       				<tr>
				                       					<td style="border: 1px solid #999;"><b>รายการ</b></td>
				                       					<td style="border: 1px solid #999;"><b>ราคาต่อหน่วย (บาท)</b></td>
				                       				</tr>
				                       				<c:forEach items="${PExamQuestion.imageList}" var="iquest">
				                       				<tr>
				                       					<td style="border: 1px solid #999;">${iquest.imageName}</td>
				                       					<td align="center" style="border: 1px solid #999;">${iquest.amount}</td>
				                       				</tr>
				                       				</c:forEach>
				                       			</table>
				                       		</c:if>
				                       		<c:if test="${PExamQuestion.rateType eq 'Y'}">
				                       			<table style="margin-top: 5px;margin-bottom: 10px;border: 1px solid #999;" align="center">
				                       				<c:forEach items="${PExamQuestion.imageList}" var="iquest">
				                       				<tr>
				                       					<td style="padding: 5px;">${iquest.imageName}</td>
				                       					<td style="padding: 5px;">${iquest.unitName}</td>
				                       					<td style="padding: 5px;"><fmt:formatNumber value="${iquest.amount}" pattern="#,##0" /></td>
				                       					<td style="padding: 5px;">${iquest.unit}</td>
				                       				</tr>
				                       				</c:forEach>
				                       			</table>
				                       		</c:if>
				                       	</c:if>
				                       	</div>
				                       	
				                       	<div class="col-sm-12" style="padding-bottom: 5px;">
				                       		<bean:write name="masterForm" property="question"/>
				                       	</div>
				                       	<c:forEach items="${PExamQuestion.answerList}" var="ians" varStatus="irow">
				                       		<div class="col-sm-12 ${ians.answerYn eq 'Y' ? 'fblue' : ''}" style="padding-bottom: 5px;">${irow.index + 1}) ${ians.answer}</div>
				                       	</c:forEach>
				           			</div>
				           		</div>
			           			</c:if>
								
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
	
	function doEditQuestion() {
	    swal({
			title: "ยืนยันการแก้ไขข้อมูล?",
			text: "คุณต้องการยืนยันการแก้ไขข้อมูลนี้ใช่หรือไม่",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: '#337ab7',
			confirmButtonText: 'ตกลง',
			cancelButtonText: 'ยกเลิก',
			closeOnConfirm: false
		},
		function(isConfirm) {
			
			if (isConfirm) {
				//showLoadingSwal();
				
				document.forms[0].mode.value = "edit";
		   	 	document.forms[0].submit();
			}
		});
	}
</script>

</body>

</html>
