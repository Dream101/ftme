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

<title>修改密码</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<input type="hidden" name="pwduppfalg" class="pwduppfalg" value="${ckfalg}" />

<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 修改密码
	<a class="btn btn-success radius r"
		style="line-height: 1.6em; margin-top: 3px; margin-left: 5px;"
		href="${pageContext.request.contextPath}/patientFront/frontPatientList"
		title="返回">返回
	</a>
</nav>
<article class="page-container">
	<form class="form form-horizontal" action="${pageContext.request.contextPath}/userinfo/pwdUpdateFront" method="post" id="form-admin-add" onsubmit="return frontpwd_add_click()">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>原始密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" style="width: 50%;" value="" id="password" name="password">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" style="width: 50%;" id="password1" name="newpassword">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认新密码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" style="width: 50%;" id="password2" name="newpassword2">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius"  id="butSel" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<%--<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"/>--%>
			</div>
		</div>
	</form>
</article>
<jsp:include    page="/view/_footer.jsp" flush="true"/>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/front-pwd-update.js"></script>

</body>
</html>