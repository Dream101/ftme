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
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> <%--<span class="select-box inline">
		<select name="accountState" class="select" id="accountState" >
			<option value="-1">请选择状态</option>
			<option value="0">未审批</option>
			<option value="1">通过审批</option>
			<option value="2">审批未通过</option>
		</select>
		</span>--%>
		<input type="text" name="name" id="selname" placeholder="管理员姓名" style="width:200px" class="input-text">
		<input type="text" name="phone" id="phone" placeholder="管理员手机号" style="width:200px" class="input-text">
		<button name="" id="butSel" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="add"><a class="btn btn-primary radius" data-title="添加管理员" href="${pageContext.request.contextPath}/govern/governAdd"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a></span> <span class="r">共有数据：<strong>${count}</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th class="table-sort"  >ID</th>
					<th>用户编号</th>
					<th>角色</th>
					<th >账号</th>
					<th >密码 </th>
					<th >姓名</th>
					<th >性别 </th>
					<th >手机号</th>
					<th >注册时间</th>
					<th >操作</th>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/govern/govern-list.js"></script>
</body>
</html>