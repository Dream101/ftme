<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>信息管理系统界面</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
	</head>
	<frameset rows="88,*,31" cols="*" frameborder="no" border="0"
		framespacing="0">
		<frame src="${pageContext.request.contextPath}/view/top.jsp"
			name="topFrame" scrolling="no" noresize="noresize" id="topFrame"
			title="topFrame" />
		<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
			<frame src="${pageContext.request.contextPath}/view/left.jsp"
				name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame"
				title="leftFrame" />
			<frame src="${pageContext.request.contextPath}/view/right.jsp"
				name="rightFrame" id="rightFrame" title="rightFrame" />
		</frameset>
		<frame src="${pageContext.request.contextPath}/view/footer.jsp"
			name="bottomFrame" scrolling="no" noresize="noresize"
			id="bottomFrame" title="bottomFrame" />
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
