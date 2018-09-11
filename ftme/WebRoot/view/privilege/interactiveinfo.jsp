<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 

<jsp:include page="/view/_meta.jsp" flush="true"/>  
<title>联系我们详情</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 前台信息管理 <span class="c-gray en">&gt;</span>联系我们详情 <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="${pageContext.request.contextPath}/interactive/interactiveList" title="返回" >返回</a></nav>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr>
					<th width="50px">名&nbsp;&nbsp;&nbsp;&nbsp;称:</th>
					<td>${interactive.name }</td>
				</tr>
				<tr>
					<th width="50px">手机号:</th>
					<td>${interactive.phone }</td>
					
				</tr>
				<tr>
					<th width="50px">内&nbsp;&nbsp;&nbsp;&nbsp;容:</th>
					<td>${interactive.content }</td>
					
				</tr>
				<tr>
					<th width="50px">时&nbsp;&nbsp;&nbsp;&nbsp;间:</th>
					<td>${interactive.datetime }</td>
				</tr>
			</thead>
			<tbody>
				<tr class="text-c" ></tr>
			</tbody>
		</table>
		<div class="fypage" style="margin-top: 10px;"></div>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script>
</body>
</html>
