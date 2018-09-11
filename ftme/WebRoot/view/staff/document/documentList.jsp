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
<title>员工列表</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 上传文件列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="${pageContext.request.contextPath}/document/documentList" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 
	<span class="select-box inline">
		<select class="select" id="hospitalNo" name="hospitalNo" size="1">
			<option value="-1">---请选择医院---</option>
			<c:forEach items="${list}" var="hospital">
				<option value="${hospital.hospitalNo}">${hospital.hospitalname}</option>
			</c:forEach>
		</select>
	</span><%--
	<span class="select-box inline">
		<select class="select" id="userNo" name="userNo" size="1">
			<option value="-1">---请选择患者---</option>
		</select>
	</span>
	--%><span class="select-box inline">
		<select class="select" id="documentType" name="documentType" size="1">
		<option value="-1">---请选择类别---</option>
			<option value="遗传诊断报告">遗传诊断报告</option>
			<option value="测序结果">测序结果</option>
			<option value="临床资料">临床资料</option>
		</select>
	</span>
		<input type="text" name="phone" id="phone" placeholder="用户编号,姓名,手机号" style="width:250px" class="input-text">
		<button name="" id="butSel" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="r">共有数据：<strong>${count}</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th class="table-sort"  >ID</th>
					<!-- <th>文件编号</th> -->
					<th>用户编号</th>
					<th>医院名称</th>
					<th>患者名称</th>
					<th>患者手机号</th>
					<th>文档类别</th>
					<th >文档名称</th>
					<%--<th >文档上传路径   </th>
					<th >上传者</th>
					--%><th >备注 </th>	
					<%--<th >上传时间</th>
					--%><th >操作</th>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/staff/document/documentList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/permissions/right-role.js"></script>
</body>
</html>