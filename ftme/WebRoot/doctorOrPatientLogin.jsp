<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
			content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
		<link
			href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/static/h-ui.admin/css/H-ui.login.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/static/h-ui.admin/css/style.css"
			rel="stylesheet" type="text/css" />
		<link
			href="${pageContext.request.contextPath}/lib/Hui-iconfont/1.0.7/iconfont.css"
			rel="stylesheet" type="text/css" />

		<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
		<title>前台登录</title>
		<meta name="keywords" content="北京中因后台登录">
		<meta name="description" content="后台登录界面">
			
	</head>
	<body>
		<input type="hidden" id="errorinfo" name="TenantId" value="${errorinfo}" />
		<input type="hidden" id="flag" name="flag" value="${flag}" />
		<input type="hidden" id="TenantId" name="TenantId" value="" />
		<div class="header"></div>
		<div class="loginWraper">
			<div id="loginform" class="loginBox">
				<div class="form form-horizontal" >
					<div class="row cl">
						<label class="form-label col-xs-3">
							<i class="Hui-iconfont">&#xe60d;</i>
						</label>
						<div class="formControls col-xs-8">
							<input id="userName" name="userName" type="text" placeholder="手机号" value=""
								class="input-text size-L">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-3">
							<i class="Hui-iconfont">&#xe60e;</i>
						</label>
						<div class="formControls col-xs-8">
							<input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
						</div>
					</div>
					<div class="row cl">
						<div class="formControls col-xs-8 col-xs-offset-3">
							<button class="btn btn-success radius size-L login" id="butSel">&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</button>
							<a class="btn btn-default radius size-L" href="http://59.110.24.174">&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;</a>
							<span class="forget" style="padding-left:30px;" onclick="retrievePwd('找回密码','${pageContext.request.contextPath}/portal/retrieve-pwd.html')">忘记密码</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			Copyright 北京中因科技有限公司
		</div>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/lib/layer/2.1/layer.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.js">
</script>
		<script type="text/javascript">
		
$(function(){
	$("#butSel").click(function() {
		var username=$("#userName").val();
		var password=$("#password").val();
		if(username==""){
			layer.tips('账号不能为空！', '#userName', {
				tips : [ 2, '#076BAB' ]
			});
		}else if(!/^1[34578]\d{9}$/.test(username)){
			layer.tips('账号格式不正确！', '#userName', {
				tips : [ 2, '#076BAB' ]
			});
		}else if(password==""){
			layer.tips('密码不能为空！', '#password', {
				tips : [ 2, '#076BAB' ]
			});
		}else if(password.length < 6 ||!/^[a-zA-Z]\w{5,17}$/.test(password)){
			layer.tips('密码格式不正确！', '#password', {
				tips : [ 2, '#076BAB' ]
			});
		}else{
			$.ajax( {
				type : "post",
				url :"${pageContext.request.contextPath}/login/frontLogin",
				dataType : "json",
				async: false,
				data : {
					"username":username,
					"password":password
				},
				success : function(data) {
				//alert(data);
					if(data=="3"){
						window.location.href="${pageContext.request.contextPath}/frontPatient/frontList";
					}else if(data=="4"){
						window.location.href="${pageContext.request.contextPath}/patientFront/frontPatientList";
					}else{
						layer.msg("该用户不存在！", {icon: 2, time: 1000});
					}
				}
			});
		}
	})
});
function retrievePwd(title,url){

			/* layer.open({
			  type: 2,
			  shade: false,
			  area: '500px',
			  maxmin: true,
			  content: url,
			  zIndex: layer.zIndex, //重点1
			  success: function(layero){
			  }
			}); */   
			layer.open({
			  type: 2,
			  title: title, //title,
			  offset: ['30%', '40%'],
			  shadeClose: true,
			  fixed:true,
			  scrollbar:false,
			  //shade: 0.8,
			  area: ['500px', '320px'],
			  content: url
			  
			}); 
			
		};
		</script>
	
	</body>
</html>
