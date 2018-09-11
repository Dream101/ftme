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
<title>公司简介</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 前台信息管理 <span class="c-gray en">&gt;</span> 公司简介 <a class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px" href="${pageContext.request.contextPath}/company/companyinfoByFind" title="修改" >修改</a></nav>
<div class="page-container"><div class="page-container">
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<!--<tr>
					<th>公司名称 </th>
					<th>联系方式 </th>
					<th>公司地址</th>
					<th>公司简介</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>${companyinfo.companyName }</td>
					<td>${companyinfo.companyPhone }</td>
					<td>${companyinfo.companyAddress }</td>
					<td>${companyinfo.companyIntroduce }</td>
					<td rowspan="2" colspan="2"><a href="">修改</a></td>
				</tr>-->
				<tr>
					<th>公司名称</th>
					<td>${companyinfo.companyName }</td>
				</tr>
				<tr>
					<th>联系方式</th>
					<td>${companyinfo.companyPhone }</td>
					
				</tr>
				<tr>
					<th>公司地址</th>
					<td>${companyinfo.companyAddress }</td>
					
				</tr>
				<tr>
					<th>公司简介</th>
					<td>${companyinfo.companyIntroduce }</td>
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
