<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<title>新闻动态管理</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span> 前台信息管理
		<span class="c-gray en">&gt;</span> 新闻动态管理
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i>
		</a>
		</nav>
		<div class="page-container">
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="add"><a class="btn btn-primary radius"
					data-title="添加动态"
					href="${pageContext.request.contextPath}/news/newsAdd"><i
						class="Hui-iconfont">&#xe600;</i> 添加动态</a>
				</span>
				<span class="r">共有数据：<strong>${count}</strong> 条</span>
			</div>
			<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th class="table-sort"  >ID</th>
					<th>新闻动态编号 </th>
					<th>标题</th>
					<th>操作</th>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/privilege/news-list.js"></script>
</body>
</html>