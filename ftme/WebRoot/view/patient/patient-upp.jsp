<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
		<jsp:include page="/view/_meta.jsp" flush="true" />
		<title>修改患者 - 患者管理</title>
	</head>
	<body>
		<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span> 患者管理
		<span class="c-gray en">&gt;</span> 患者列表
		<span class="c-gray en">&gt;</span> 修改患者
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="${pageContext.request.contextPath}/patient/patientList"
			target="rightFrame" title="返回">返回</a>
		</nav>
		<article class="page-container">
		<form class="form form-horizontal"
			action="${pageContext.request.contextPath}/patient/patientUpdate"
			method="post" id="form-admin-add" onsubmit="return patient_add_click();">
			<input type="hidden" value="${user.id}" name="user.id" id="id"/>
	 		<input type="hidden" value="${user.userNo}" name="user.userNo"/>
	 		<input type="hidden" value="${user.name}" name="name"/>
	 		<input type="hidden" value="${user.phone}" name="phone"/>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>治疗医院：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width: 200px;"> 
						<select class="select" id="hospitalNo" name="user.hospitalNo" size="1">
							<option value="-1">
								---请选择医院---
							</option>
							<c:forEach items="${list}" var="hospital">
									<c:if test="${hospital.hospitalNo==user.hospitalNo}">
										<option value="${hospital.hospitalNo},${hospital.hospitalname}" selected="selected">
											${hospital.hospitalname}
										</option>
									</c:if>
									<c:if test="${hospital.hospitalNo!=user.hospitalNo}">
										<option value="${hospital.hospitalNo},${hospital.hospitalname}">
											${hospital.hospitalname}
										</option>
									</c:if>
								</c:forEach>
							</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>治疗医生：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
						<select class="select" id="uno" name="user.uno" size="1">
							<option value="-1">---请选择医生---</option>
								<c:forEach items="${list2}" var="p">
									<c:if test="${p.userNo==user.uno}">
										<option value="${p.userNo},${p.name}" selected="selected">
											${p.name}
										</option>
									</c:if>
									<c:if test="${p.userNo!=user.uno}">
										<option value="${p.userNo},${p.name}">
											${p.name}
										</option>
									</c:if>
								</c:forEach>
							</select>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>检测项目：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width: 200px;"> 
						<select class="select" id="itemsname" name="user.itemsname" size="1">
							<option value="暂无检测项目">
								---请选择检测项目---
							</option>
							<c:forEach items="${itemslist}" var="items">
									<c:if test="${items.itemsname==user.itemsname}">
										<option value="${items.itemsname}" selected="selected">
											${items.itemsname}
										</option>
									</c:if>
									<c:if test="${items.itemsname!=user.itemsname}">
										<option value="${items.itemsname}">
											${items.itemsname}
										</option>
									</c:if>
								</c:forEach>
							</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					临床诊断：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width: 200px;"> 
						<select class="select" id="clinicalname" name="user.clinicalname" size="1">
							<option value="暂无临床诊断">
								---请选择临床诊断--
							</option>
							<c:forEach items="${clinicallist}" var="clinical">
									<c:if test="${clinical.clinicalname==user.clinicalname}">
										<option value="${clinical.clinicalname}" selected="selected">
											${clinical.clinicalname}
										</option>
									</c:if>
									<c:if test="${clinical.clinicalname!=user.clinicalname}">
										<option value="${clinical.clinicalname}">
											${clinical.clinicalname}
										</option>
									</c:if>
								</c:forEach>
							</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					检测结果：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width: 200px;"> 
						<select class="select" id="testresultsname" name="user.testresultsname" size="1">
							<option value="暂无检测结果">
								---请选择检测结果--
							</option>
							<c:forEach items="${testresultslist}" var="testresults">
									<c:if test="${testresults.testresultsname==user.testresultsname}">
										<option value="${testresults.testresultsname}" selected="selected">
											${testresults.testresultsname}
										</option>
									</c:if>
									<c:if test="${testresults.testresultsname!=user.testresultsname}">
										<option value="${testresults.testresultsname}">
											${testresults.testresultsname}
										</option>
									</c:if>
								</c:forEach>
							</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>账号：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.username}" style="width: 50%;"
						placeholder="" id="username" name="user.username">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>账号：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.username}" style="width: 50%;" id="username1" name="username1">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>密码：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" autocomplete="off"
						style="width: 50%;" value="${user.password}" placeholder=""
						id="password" name="user.password">
				</div>
			</div>
			<%--<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" autocomplete="off"  placeholder="确认密码" id="password2" name="user.password2">
			</div>
		</div>
		--%>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>姓名：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.name}" style="width: 50%;"
						placeholder="" id="name" name="user.name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>姓名：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.name}" style="width: 50%;" placeholder="" id="name1" name="name1">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>性别：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<c:if test="${user.sex==1}">
						<input name="user.sex" type="radio" id="sex-1" value="1" checked="checked" >
						<label for="sex-1">男</label>
						<input name="user.sex" type="radio" id="sex-2" value="0" >
						<label for="sex-2">女</label>
					</c:if>
					<c:if test="${user.sex==0}">
						<input name="user.sex" type="radio" id="sex-1" value="1" >
						<label for="sex-1">男</label>
						<input name="user.sex" type="radio" id="sex-2" value="0"  checked="checked">
						<label for="sex-2">女</label>
					</c:if>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					邮箱：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.email}" style="width: 50%;" placeholder=""	 id="email" name="user.email">
				</div>
			</div>
		<%--<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>手机：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.phone}" style="width: 50%;"
						placeholder="" id="phone" name="user.phone">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					快递单号：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.courierInfo}" style="width: 50%;"
					placeholder=""id="courierInfo" name="user.courierInfo">
				</div>
			</div>--%>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					地址：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.remark}" style="width: 50%;"
					placeholder=""	id="remark" name="user.remark">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<span class='upp'><input class="btn btn-primary radius" id="butSel" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></span>
					<%--<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"/>--%>
				</div>
			</div>
		</form>
		</article>
		<jsp:include page="/view/_footer.jsp" flush="true" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/patient/patient-add.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/permissions/right-role.js"></script>
<script>
	$(function(){
		$("#uno").searchableSelect();
	});
</script>
	</body>
</html>