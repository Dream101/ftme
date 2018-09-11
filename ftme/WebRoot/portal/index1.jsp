<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>中因官网</title>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
		<link href="${pageContext.request.contextPath}/css/front-style.css" rel="stylesheet" type="text/css" media="all" />
		<%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		--%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.js"></script>
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		</script>
		<!--
	      	作者：offline
	      	时间：2017-01-17
	      	描述：responsiveslides.min.js是LOGO的js(31-42行)
	      -->
		<script src="${pageContext.request.contextPath}/js/portal/responsiveslides.min.js"></script>
		<script>
			$(function() {
				$("#slider").responsiveSlides({
					auto: true,
					nav: true,
					speed: 500,
					namespace: "callbacks",
					pager: true,
				});
			});
		</script>

		<!---- start-smoth-scrolling     描述：move-top.js导航--->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal/move-top.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event) {
					event.preventDefault();
					$('html,body').animate({
						scrollTop: $(this.hash).offset().top
					}, 1200);
				});
			});
		</script>
		<!---End-smoth-scrolling---->
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.1/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css" />		
	

</head>

	<body>
		<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<input type="hidden" name="falg" id="falg" value="0" />
		<input type="hidden" name="pwduppfalg" class="pwduppfalg" value="${ckfalg}" />
		<div class="header" id="home">
			<!--  页眉（导航一些基本信息）  -->
			<div class="header-top">
				<div class="container">
					<div class="icon">
						<ul>
							<%--<li>${companyinfo.companyName }</li>
							--%><li><i class="indicate"></i></li>
							<li>${companyinfo.companyAddress}</li>
							<li><i class="message"></i></li>
							<li>${companyinfo.companyEmail}</li>
							<li><i class="phone"></i></li>
							<li><p>${companyinfo.companyPhone}</p></li>
						</ul>
					</div>
					<div class="social-icons">
						<%--<a href="#" class="login" id="register" style="color: rgba(80, 106, 133, 1);font-weight: 600;margin-right: 2rem;">登&nbsp;录</a>
						<a href="#" class="login" id="find" style="color: rgba(80, 106, 133, 1);font-weight: 600;margin-right: 2rem;">找回密码</a>
					--%></div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="container">
				<div class="header-bottom">
					<div class="logo">
						<h1><a id="logo" href="${pageContext.request.contextPath}/portal/portal"><img src="${pageContext.request.contextPath}/images/portal-logo.jpg" alt=""></a></h1>
					</div>
					<div class="top-menu">
						<span class="menu"><img src="${pageContext.request.contextPath}/images/nav-icon.png" alt=""/></span>
						<ul>
							<nav class="cl-effect-5">
								<li><a href="${pageContext.request.contextPath}/portal/portal" class="active" ><span data-hover="首页">首页</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/portfolio.jsp"><span data-hover="公司介绍">公司介绍</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/blog"><span data-hover="行业动态">行业动态</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/features.jsp"><span data-hover="业务范围">业务范围</span></a></li><%--
								<li><a href="${pageContext.request.contextPath}/portal/pages.html"><span>错误页面（可以不管）</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/contact.html"><span>contact</span></a></li>
							--%></nav>
						</ul>
						</ul>
						<div class="clearfix"></div>
					</div>
					<!--script-nav-->
					<script>
						$("span.menu").click(function() {
							$(".top-menu ul").slideToggle("slow", function() {});
						});
					</script>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		
		<!-- header-section-ends 	LOGO的div -->
		<div class="header-slider">
			<div class="slider">
				<div class="callbacks_container">
					<ul class="rslides" id="slider">
						<c:forEach items="${pictureinfoList}" var="pictureinfo">
							<li style="width:100%; height:800px;">
								<img src="${pageContext.request.contextPath}/${pictureinfo.picturePath}" alt="" width="1360px" height="500px" >
								<div class="caption">
									<h3>${pictureinfo.picturename}</h3>
									<p>${pictureinfo.content}</p>
	
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		
		
		<!--
			放置内容
        -->
		<div class="beautifull">
			<div class="container">
				<div class="beautifull-header">
					<div class="introduce">
						<h4>公司介绍</h4>
						<p>北京中因科技有限公司是一家专业从事遗传性眼病临床基因诊断、预防和转基因治疗研究的企业。我们与北京中科医学检验所建立战略合作，基于北京中科医学检验所成熟可靠的临床检验平台和北京中因科技有限公司雄厚的眼科遗传学高端医生团队和技术基础，建立了遗传性眼病临床基因诊断平台，为遗传性眼病患者提供快速、准确、可靠和专业的临床基因诊断服务。平台是国内最早开展遗传性眼病临床基因诊断的平台之一，也是目前国内眼科学界覆盖病种最多、覆盖基因最全面的遗传性眼病临床基因诊断平台。</p>
					</div>

					<div class="loginMain">
						<div class="bigBox" id="bigBox3">
							<div class="box" id="box">
								<ul>
									<li class="hover">医生登录</li>
									<li>患者登录</li>
									<li>找回密码</li>
								</ul>

							</div>
							<div class="box1">
								<div class="active">
									<form class="form form-horizontal" action="" method="post">
										<table align="center" style="margin-top: 60px; margin-left: 40px;">
											<tr>
												<td><span class="c-red" style="margin-left: 50px">*</span>账号：
												<input type="text" class="input-text" autocomplete="off" style="width: 50%;" value="" id="username1" name="username"></td>
											</tr>
											<tr>
												<td><span class="c-red" style="margin-left: 50px">*</span>密码：
												<input type="password" class="input-text" autocomplete="off"style="width: 50%;" id="password1" name="password"></td>
											</tr>
											<tr>
												<td align="center" rowspan="2" colspan="2"><input class="btn btn-primary radius" id="butSel1" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></td>
											</tr>
										</table>
												
									</form>
								</div>
								<div>
									<form class="form form-horizontal" action="" method="post">
										<table align="center" style="margin-top: 60px; margin-left: 40px;">
											<tr>
												<td><span class="c-red" style="margin-left: 50px">*</span>账号：<input type="text" class="input-text" autocomplete="off" style="width: 50%;" value="" id="username2" name="username"></td>
											</tr>
											<tr>
												<td><span class="c-red" style="margin-left: 50px">*</span>密码：<input type="password" class="input-text" autocomplete="off"style="width: 50%;" id="password2" name="password"></td>
											</tr>
											<tr>
												<td align="center" rowspan="2" colspan="2"><input class="btn btn-primary radius" id="butSel2" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></td>
											</tr>
										</table>
									</form>
								</div>
								<div>
									<form class="form form-horizontal" action="" method="post" id="form-admin-add">
									<table align="center" style="margin-top: 60px; margin-left: 40px;">
											<tr>
												<td><span class="c-red" style="margin-left: 50px">*</span>账号：<input type="text" class="input-text" autocomplete="off" style="width: 50%;" value="" id="username3" name="username"></td>
											</tr>
											<tr>
												<td><span class="c-red" style="margin-left: 50px">*</span>姓名：<input type="text" class="input-text" autocomplete="off"style="width: 50%;" id="uname3" name="uname"></td>
											</tr>
											<tr>
												<td align="center" rowspan="2" colspan="2"><input class="btn btn-primary radius" id="butSel3" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></td>
											</tr>
										</table>
									</form>
								</div>

							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="beautifull-grids">
				<div class="col-md-4 beautiful-grid">
					<div class="icon1">
						<i class="heart"></i>
					</div>
					<div class="passion">
						<h4>产品系列</h4>
						<p>本公司自主设计并定制的“遗传性眼病基因诊断芯片”，可有效捕获上百种常见眼科遗传病的近500个致病基因！目前基因诊断主要用本芯片完成。</p>
					</div>
					<div class="clearfix"></div>

				</div>
				<div class="col-md-4 beautiful-grid">
					<div class="icon1">
						<i class="lamp"></i>
					</div>
					<div class="passion">
						<h4>行业地位</h4>
						<p>目前主要接受北京各大医院眼科的送样样本</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="col-md-4 beautiful-grid">
					<div class="icon1">
						<i class="star"></i>
					</div>
					<div class="passion">
						<h4>技术优势</h4>
						<p>基因诊断芯片设计和诊断报告遗传分析由知名遗传学家和眼科专家参与；高通量测序结果由本领域高水平专业信息分析人员分析。</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

		<!--
			与我们联系
        -->
		<%--<div class="contact-section">
			<div class="container">
				<div class="contact-grids">
					<div class="col-md-4 contact-grid">
						<h5>合作企业</h5>
						<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem seq.dem tumquam modipsae que voloratati andig.</p>
						<a href="#" class="more">more about us<img src="${pageContext.request.contextPath}/images/arrow.png"></a>
					</div>
					<div class="col-md-4 contact-grid">
						<h5>联系我们 </h5>
						<p>${companyinfo.companyName }</p>
						<div class="icon2">
							<ul>
								<li><i class="indicate"></i></li>
								<li>
									<p class="label1">${companyinfo.companyAddress}</p>
								</li>
							</ul>
							<ul>
								<li><i class="phone"></i></li>
								<li>
									<p class="label1">${companyinfo.companyPhone}</p>
								</li>
							</ul>
							<ul>
								<li><i class="message"></i></li>
								<li><p class="label1">${companyinfo.companyEmail} </p></li>
							</ul>
						</div>
					</div>
					<div class="col-md-4 contact-grid">
						<h5>公司照片</h5>
						<div class="cont">
							<div class="contact-leftgrid">
								<div class="img-grid1">
									<img src="${pageContext.request.contextPath}/images/pic7.jpg">
								</div>
								<div class="img-grid2">
									<img src="${pageContext.request.contextPath}/images/pic8.jpg">
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="contact-rightgrid">
								<div class="img-grid3">
									<img src="${pageContext.request.contextPath}/images/pic9.jpg">
								</div>
								<div class="img-grid4">
									<img src="${pageContext.request.contextPath}/images/pic10.jpg">
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		--%><div class="footer-section">
			<div class="container">
				<div class="footer-left">
					<p> Copyright &copy;2015 All rights Reserved | Design by<a href="http://w3layouts.com" target="target_blank">W3Layouts</a></p>
				</div>
				<div class="bottom-menu">
					<ul>
						<li><a href="${pageContext.request.contextPath}/portal/portal" class="active" ><span data-hover="首页">首页</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/portfolio.jsp"><span data-hover="公司介绍">公司介绍</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/blog"><span data-hover="行业动态">行业动态</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/features.jsp"><span data-hover="业务范围">业务范围</span></a></li>
					</ul>
				</div>
				<div class="clearfix"></div>

				<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
			</div>
		</div>
	<script>
	$(function() {
		function tab(id) {
			this.bigBox = document.getElementById(id)
			this.lis = this.bigBox.getElementsByTagName("li");
			this.box1 = this.bigBox.querySelector(".box1");
			this.div = this.box1.getElementsByTagName("div");
			this.nov();
		}
		tab.prototype = {
			nov: function() {
				var that = this;
				$(this.lis).click(function() {
					var index = $(this).index();
					$(that.lis).eq(index).addClass("hover").siblings().removeClass("hover");
					$(that.div).eq(index).addClass("active").siblings().removeClass("active");
				})
			}
		}
		var tab3 = new tab("bigBox3")
	})
</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal/portal.js"></script>		
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal/login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/retrieve-pwd.js"></script>

	</body>
</html>