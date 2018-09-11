<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${pageContext.request.contextPath}/css/style.css"
			rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/permissions/left.js"></script></head>

	<body style="background: #f0f9fd;">
	<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<div class="lefttop">
			<span></span>导航栏
		</div>

		<dl class="leftmenu">
			
			<%--<dd>
				<div class="title">
					<span><img src="${pageContext.request.contextPath}/images/leftico01.png" />
					</span>患者管理
				</div>
				<ul class="menuson">
					<li class="active">
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/patient/patientList" target="rightFrame">患者列表</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/patient/patientDelList" target="rightFrame">删除的患者</a><i></i>
						</div>
					</li>
				</ul>
			</dd>
			
			<dd>
				<div class="title">
					<span><img
							src="${pageContext.request.contextPath}/images/leftico01.png" />
					</span>员工管理
				</div>
				<ul class="menuson">
					<li>
						<div class="header">
							<cite></cite><a
								href="${pageContext.request.contextPath}/staff/staffList"
								target="rightFrame">员工列表</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a
								href="${pageContext.request.contextPath}/staff/staffDelList"
								target="rightFrame">删除员工列表</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a
								href="${pageContext.request.contextPath}/document/documentAdd"
								target="rightFrame">文档上传</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a
								href="${pageContext.request.contextPath}/document/documentList"
								target="rightFrame">文档管理</a><i></i>
						</div>
					</li>
				</ul>
			</dd>

			<dd>
				<div class="title">
					<span><img
							src="${pageContext.request.contextPath}/images/leftico01.png" />
					</span>医生管理
				</div>
				<ul class="menuson">
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/hospital/hospitalList" target="rightFrame">医院列表</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/hospital/hospitalDelList" target="rightFrame">删除的医院</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/doctor/doctorList" target="rightFrame">医生列表</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/doctor/doctorDelList" target="rightFrame">删除的医生</a><i></i>
						</div>
					</li>
				</ul>
			</dd>
			<dd>
				<div class="title">
					<span><img
							src="${pageContext.request.contextPath}/images/leftico01.png" />
					</span>管理员管理
				</div>
				<ul class="menuson">
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/permissions/replaceRole" target="rightFrame">角色管理</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/permissions/governLimit" target="rightFrame">管理员权限设置</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/govern/governList" target="rightFrame">管理员列表</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/govern/governDelList" target="rightFrame">删除的管理员</a><i></i>
						</div>
					</li>
				</ul>
			</dd>

			<dd>
				<div class="title">
					<span><img
							src="${pageContext.request.contextPath}/images/leftico01.png" />
					</span>前台页面管理
				</div>
				<ul class="menuson">

					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/govern/" target="rightFrame">图片管理</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/govern/" target="rightFrame">公司信息管理</a><i></i>
						</div>
					</li>
					<li>
						<div class="header">
							<cite></cite><a href="${pageContext.request.contextPath}/govern/" target="rightFrame">通知信息</a><i></i>
						</div>
					</li>
				</ul>

			</dd>
		--%></dl>

	</body>
</html>