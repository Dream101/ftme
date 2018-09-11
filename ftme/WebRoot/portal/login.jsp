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

<title>登录</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<input type="hidden" name="pwduppfalg" class="pwduppfalg" value="${ckfalg}" />
<article class="page-container">
	<form class="form form-horizontal" action="" method="post" id="form-admin-add">
		<table  style="margin-top: 30px;">
			<tr>
				<td><span class="c-red" style="margin-left: 50px">*</span>账号：<input type="text" class="input-text" autocomplete="off" style="width: 50%;" value="" id="username" name="username"></td>
			</tr>
			<tr>
				<td><span class="c-red" style="margin-left: 50px">*</span>密码：<input type="password" class="input-text" autocomplete="off"style="width: 50%;" id="password" name="password"></td>
			</tr>
		</table>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius"  id="butSel" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<input class="btn btn-primary radius"  id="find" type="button" value="&nbsp;&nbsp;找回密码&nbsp;&nbsp;">
				<%--<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"/>--%>
			</div>
		</div>
	</form>
</article>


<jsp:include page="/view/_footer.jsp" flush="true"/>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal/portal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal/login.js"></script>
</body>
</html>