<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${pageContext.request.contextPath}/css/style.css"
			rel="stylesheet" type="text/css" />
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js">
</script>
		<script type="text/javascript">
$(function() {
	//顶部导航切换
	$(".nav li a").click(function() {
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})
	$("#exit").click(function() {
		var urlpath=$(".path").val()+"/login/exit";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			success : function(data) {
				if(data){
					top.location =$(".path").val()+"/login.jsp";
				/*	layer.tips('退出成功！', '#username', {
						tips : [ 1, '#076BAB' ]
					});*/
				}else{
					layer.tips('退出失败！', '#username', {
						tips : [ 2, '#076BAB' ]
					});
				}
			}
		});
	});
})
</script>


	</head>

	<body
		style="background: url(${pageContext.request.contextPath}/images/topbg.gif) repeat-x;">
		<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<div class="topleft">
			<a href="${pageContext.request.contextPath}/view/main.jsp"
				target="_parent"><img
					src="${pageContext.request.contextPath}/images/logo.png"
					title="系统首页" /> </a>
		</div>

		<!--<ul class="nav">
			<li>
				<a href="${pageContext.request.contextPath}/patient/patientList"
					target="rightFrame" class="selected"><img
						src="${pageContext.request.contextPath}/images/icon01.png"
						title="工作台" />
					<h2>
						患者管理
					</h2> </a>
			</li>

			<li>
				<a href="${pageContext.request.contextPath}/doctor/doctorList"
					target="rightFrame" class="selected"><img
						src="${pageContext.request.contextPath}/images/icon01.png"
						title="工作台" />
					<h2>
						医生管理
					</h2> </a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/hospital/hospitalList"
					target="rightFrame" class="selected"><img
						src="${pageContext.request.contextPath}/images/icon01.png"
						title="工作台" />
					<h2>
						医院管理
					</h2> </a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/staff/staffList"
					target="rightFrame" class="selected"><img
						src="${pageContext.request.contextPath}/images/icon01.png"
						title="工作台" />
					<h2>
						员工管理
					</h2> </a>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/govern/governList"
					target="rightFrame" class="selected"><img
						src="${pageContext.request.contextPath}/images/icon01.png"
						title="工作台" />
					<h2>
						管理员管理
					</h2> </a>
			</li>
			<li>
				<a href="default.html" target="rightFrame" class="selected"><img
						src="${pageContext.request.contextPath}/images/icon01.png"
						title="工作台" />
					<h2>
						前台页面管理
					</h2> </a>
			</li>
		</ul>
		--><div class="topright">
			<ul>
				<%-- <li>
					<span><img
							src="${pageContext.request.contextPath}/images/help.png"
							title="帮助" class="helpimg" /> </span><a href="#">帮助</a>
				</li> --%>
				<li>
					<a href="${pageContext.request.contextPath}/view/pwd-update.jsp"
						target="rightFrame">更改密码</a>
				</li>
				<li>
					<a href="javascript:;" id="exit"  target="_parent">退出</a>
					
				</li>
			</ul>

			<div class="user">
				<span>${loginUser.roleType}：${loginUser.username }</span>
				<!--
				<i>消息</i>
				<b>5</b>
			-->
			</div>

		</div>

	</body>
</html>