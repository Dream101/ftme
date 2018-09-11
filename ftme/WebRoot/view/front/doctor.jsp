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
<title></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script></head>

<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<div class="breadcrumb">
			<a class="btn btn-success radius r"
				style="line-height: 1.6em; margin-top: 3px;margin-left: 5px;"
				href="${pageContext.request.contextPath}/login/exit?type=qt"
				title="退出">退出</a>
			<a class="btn btn-success radius r"
				style="line-height: 1.6em; margin-top: 3px; margin-left: 5px;"
				href="${pageContext.request.contextPath}/frontPatient/frontList"
				title="刷新"><i class="Hui-iconfont">&#xe68f;</i>
			</a>
			<a  class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px; margin-left: 5px;" href="${pageContext.request.contextPath}/view/front/frontdoctorpwd-update.jsp"
				title="更改密码">更改密码</a>
		</div>
		<div class="page-container">
	<div class="text-c"> 
		<span class="select-box inline">
			<select class="select" style="width:180px;" id="itemsname" name="itemsname" size="1" >
				<option value="-1">---请选择检测项目---</option>
				<option value="暂无检测项目">暂无检测项目</option>
				<c:forEach items="${itemslist}" var="items">
					<option value="${items.itemsname}">
						${items.itemsname}
					</option>
				</c:forEach>
			</select>
		</span>
		<span class="select-box inline">
			<select class="select" style="width:180px;" id="testresultsname" name="testresultsname" size="1">
				<option value="-1">--请选择检测结果--</option>
				<option value="暂无检测结果">暂无检测结果</option>
					<c:forEach items="${testresultslist}" var="testresults">
						<option value="${testresults.testresultsname}">
							${testresults.testresultsname}
						</option>
				</c:forEach>
			</select>
		</span>
		<input type="text" name="name" id="selname" data-title="模糊查询姓名,手机号,编号" placeholder="姓名,手机号,编号" required style="width:100px;" class="input-text">
		<button name="" id="butSel" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜索</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"><span class="r">共有数据：<strong>${count}</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th class="table-sort"  >ID</th>
					<th>用户编号</th>
					<th>临床诊断</th>
					<th>检测项目</th>
					<th>检测结果</th> 
					<th >姓名</th>
					<th >性别 </th>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.searchableSelect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/doctor.js"></script>
</body>
</html>