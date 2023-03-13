<%@page contentType="text/html; charset=utf-8"%>
<%
 String useParam=(String)request.getAttribute("useParam");
 String useMode=(String)request.getAttribute("useMode");
 String actionPath=(String)request.getAttribute("actionPath");
 String formName=(String)request.getAttribute("formName");
 String queryStr=(String)request.getAttribute("queryStr");
%>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Redirect page...</title>
<script language="javascript">
	window.location.href = '<%= request.getContextPath() %>/<%=actionPath%>?<%=useParam%>=<%=useMode%><%=queryStr%>';
</script>
</head>
<body onLoad="submitFormInit()">
<form name="<%=formName%>" action="<%=actionPath%>">
<input type="hidden" name="<%=useParam%>" />
</form></body></html>
