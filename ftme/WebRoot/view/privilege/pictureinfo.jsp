<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 

<jsp:include page="/view/_meta.jsp" flush="true"/>  
<title>首页轮播图管理</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 前台信息管理 <span class="c-gray en">&gt;</span>首页轮播图管理<a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th>顺序</th>
					<th width="320px">轮播图</th>
					<th>标题</th>
					<th>具体描述</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pictureList}" var="picture">
					<tr class="text-c">
						<td>${picture.pictureNo}</td>
						<td><img src="${pageContext.request.contextPath}${picture.picturePath}" style="height: 80px; width: 300px;"></td>
						<td>${picture.picturename}</td>
						<td>${picture.content}</td>
						<td class="f-14 product-brand-manage"><a style="text-decoration:none" href="${pageContext.request.contextPath}/picture/pictureinfoByFind?pictureNo=${picture.pictureNo}" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>修改</a></td>
						<!-- <a style="text-decoration:none" class="ml-5" onClick="active_del(this,'10001')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>-->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script> 
</body>
</html>
