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
		<title>添加医生 - 医生管理</title>
	</head>
	<body>
		<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span> 医生管理
		<span class="c-gray en">&gt;</span> 医生列表
		<span class="c-gray en">&gt;</span> 添加医生
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="${pageContext.request.contextPath}/doctor/doctorList"
			target="rightFrame" title="返回">返回</a>
		</nav>
		<article class="page-container">
		<form class="form form-horizontal"
			action="${pageContext.request.contextPath}/doctor/doctorSave"
			method="post" id="form-admin-add" onsubmit="return doctor_add_click();">
			<input type="hidden" value="-1" id="id" />
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>所属医院：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="width: 150px;"> <select
							class="select" id="hospitalNo" name="user.hospitalNo" size="1">
							<option value="-1">
								---请选择医院---
							</option>
							<c:forEach items="${list}" var="hospital">
								<option value="${hospital.hospitalNo},${hospital.hospitalname}">
									${hospital.hospitalname}
								</option>
							</c:forEach>
						</select> </span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>账号：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" style="width: 50%;"
						placeholder="医生的手机号" id="username" name="user.username">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>确认账号：
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
						style="width: 50%;" value="" placeholder="请输入密码，字母开头，密码长度在6~18之间"
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
					<input type="text" class="input-text" value="" style="width: 50%;" placeholder="请输入姓名" id="name" name="user.name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>确认姓名：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${user.name}" style="width: 50%;" id="name1" name="name1">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>性别：
				</label>
				<div class="formControls col-xs-8 col-sm-9">

					<input name="user.sex" type="radio" id="sex-1" value="1"
						checked="checked">
					<label for="sex-1">
						男
					</label>

					<input name="user.sex" type="radio" id="sex-2" value="0">
					<label for="sex-2">
						女
					</label>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					邮箱：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" style="width: 50%;"
						placeholder="" id="email" name="user.email">
				</div>
			</div>
			<%--<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>手机：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" style="width: 50%;"
						placeholder="" id="phone" name="user.phone">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					备注：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea name="user.remark" cols="" id="remark" rows=""
						class="textarea" style="width: 50%;" placeholder="说点什么...100个字符以内"
						dragonfly="true" onKeyUp="textarealength(this,100)"></textarea>

				</div>
			</div>
			--%>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
					<span class="c-red">*</span>是否发送短信：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input name="smsFalse" type="radio" id="sex-1" value="0"
						checked="checked">
					<label for="sex-1">
						是
					</label>

					<input name="smsFalse" type="radio" id="sex-2" value="1">
					<label for="sex-2">
						否
					</label>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<span class="add"><input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></span>
					<%--<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"/>--%>
				</div>
			</div>
		</form>
		</article>
		<jsp:include page="/view/_footer.jsp" flush="true" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/doctor/doctor-add.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/permissions/right-role.js"></script>

	</body>
</html>