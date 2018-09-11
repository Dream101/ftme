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


<body >
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<nav class="breadcrumb"> "${user.name }"信息页面
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px;margin-left: 5px;"
			href="${pageContext.request.contextPath}/login/exit?type=qt"
			title="退出">退出</a>
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px; margin-left: 5px;"
			href="${pageContext.request.contextPath}/patientFront/frontPatientList"
			title="刷新"><i class="Hui-iconfont">&#xe68f;</i>
		</a>
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px;margin-left: 5px;"
			href="${pageContext.request.contextPath}/view/front/patient-Upload.jsp"
			title="上传">上传</a>
		<a  class="btn btn-success radius r" style="line-height: 1.6em; margin-top: 3px; margin-left: 5px;" href="${pageContext.request.contextPath}/view/front/frontpwd-update.jsp"
			title="更改密码">更改密码</a>
		</nav>
		<div class="page-container">
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr>
					<td style="text-align:right;" width="200px">患者编号：</td>
					<td>${user.userNo}</td>
				</tr>
				<tr>
					<td style="text-align:right;">临床资料：</td>
					<td>
						<c:forEach items="${clinicalFile}" var="clinical">
							<span class="patientXz">${clinical.documentName }&nbsp;&nbsp;&nbsp;&nbsp;
								<%--<a style="text-decoration:none;" class="btn btn-success radius" href="${pageContext.request.contextPath}/frontPatient/download?uploadPath=${clinical.uploadPath }" title="下载">${clinical.documentName }</a>&nbsp;&nbsp;&nbsp;&nbsp;
							--%></span>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">临床诊断：</td>
					<td>${user.clinicalname }</td>
				</tr>
				<tr>
					<td style="text-align:right;">检测项目：</td>
					<td>${user.itemsname }</td>
				</tr>
				<tr>
					<td style="text-align:right;">检测结果：</td>
					<td>${user.testresultsname }</td>
				</tr>
				<tr>
					<td style="text-align:right;">遗传诊断报告：</td>
					<td>
						<c:forEach items="${testresultsFile}" var="testresults">
							<span class="patientXz">
								<a style="text-decoration:none;" class="btn btn-success radius" href="${pageContext.request.contextPath}/patientFront/download?uploadPath=${testresults.uploadPath }" title="下载">${testresults.documentName }</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</span>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td style="text-align:right;">姓名：</td>
					<td>${user.name }</td>
				</tr>
				<tr>
					<td style="text-align:right;">性别 ：</td>
					<td>${user.sex=="1"?"男":"女" }</td>
				</tr>
				<tr>
					<td style="text-align:right;">手机号：</td>
					<td>${user.phone }</td>
				</tr><%--
				<tr>
					<td style="text-align:right;">地址：</td>
					<td>${user.remark }</td>
				</tr>
			--%></thead>
			<tbody>
			</tbody>
		</table>
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