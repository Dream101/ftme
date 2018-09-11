<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 

<jsp:include page="/view/_meta.jsp" flush="true"/>  
<title>管理员列表</title>
</head>
<body onload="loaded();">
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员权限管理 <span class="c-gray en">&gt;</span> 设置管理员"${user.name }"权限 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<input type="hidden" name="userNo" class="userNo" value="${user.userNo }">
	<div class="mt-20">
		<c:forEach items="${hierarchy1}" var="permissions">
			<div class="cl pd-5 bg-1 bk-gray mt-20" id="${permissions.superior }">
				<span>${permissions.permissionsidName }：</span>
			<%--</div>
			<div id="${permissions.superior }">
				--%><c:forEach items="${hierarchy3}" var="p3">
					<c:if test="${p3.superior==permissions.superior}">
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="${p3.superior }" class="ckbox" id="${p3.permissionsidNo }" value="false"  >${p3.linkname }
					</c:if>
				</c:forEach>
			</div>
		</c:forEach>
		<div class="fypage" style="margin-top: 10px;"></div>
	</div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/permissions/govern-Limit.js"></script>

</body>
</html>