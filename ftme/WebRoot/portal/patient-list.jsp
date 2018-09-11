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
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
		<link href="${pageContext.request.contextPath}/css/front-style.css" rel="stylesheet" type="text/css" media="all" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
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
		<%--<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.1/layer.js"></script>
		<script type="text/javascript">
			$("#register").live("click", function() {
				layer.open({
					type: 2,
					title: '登录',
					fix: false,
					skin: 'layui-layer-lan',
					shadeClose: true,
					shade: 0.6,
					area: ['500px', '60%'],
					content: 'http://localhost:8080/ftme/view/retrieve-pwd.jsp',
					end: function() {
						findAllDerail(1);
					}
				});
			});
			$("#find").live("click", function() {
				layer.open({
					type: 2,
					title: '找回密码',
					fix: false,
					skin: 'layui-layer-lan',
					shadeClose: true,
					shade: 0.6,
					area: ['500px', '60%'],
					content: 'http://localhost:8080/ftme/view/retrieve-pwd.jsp',
					end: function() {
						findAllDerail(1);
					}
				});
			});
		</script>
--%><body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 患者管理 <span class="c-gray en">&gt;</span> 患者列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="${pageContext.request.contextPath}/patient/patientList" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<!-- header -->
		<div class="header" id="home">
			<!--  页眉（导航一些基本信息）  -->
			<div class="header-top">
				<div class="container">
					<div class="icon">
						<ul>
							<li><i class="message"></i></li>
							<li><a href="mailto:example@mail.com">${companyinfo.companyEmail}</a></li>
							<li><i class="phone"></i></li>
							<li><p>${companyinfo.companyPhone}</p></li>
						</ul>
					</div>
					<div class="social-icons">
						<button id="register">登录</button>
						<button id="find">找回密码</button>
						<!--<a href="#"><i class="icon3"></i></a>
						<a href="#"><i class="icon4"></i></a>
						<a href="#"><i class="icon5"></i></a>
						<a href="#"><i class="icon6"></i></a>-->
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="container">
				<div class="header-bottom">
					<div class="logo">
						<h1><a href="index.html">seen</a></h1>
					</div>
					<div class="top-menu">
						<span class="menu"><img src="images/nav-icon.png" alt=""/></span>
						<ul>
							<nav class="cl-effect-5">
								<li><a href="#" class="active">首页</a></li>
								<li><a href="#"><span >公司介绍</span></a></li>
								<li><a href="#"><span>最近动态</span></a></li>
								<li><a href="#"><span>错误页面（可以不管）</span></a></li>
								<li><a href="#"><span>业务范围</span></a></li>
								<li><a href="#"><span>contact</span></a></li>
							</nav>
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
						<%--<c:forEach items="${pictureinfoList}" var="pictureinfo">
							<li>
								<img src="${pageContext.request.contextPath}/${pictureinfo.picturePath}" alt="">
								<div class="caption">
									<h3>${pictureinfo.picturename}</h3>
									<p>${pictureinfo.content}</p>
	
								</div>
							</li>
						</c:forEach>
						--%><li>
							<img src="images/banner1.jpg" alt="">
							<div class="caption">
								<h3>Made GRAPHIC DESIGNER</h3>
								<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknowns.</p>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<!--
			放置内容
       
		<!--
			与我们联系
        -->
		<div class="wantto-section">
			<div class="container">
				<h4>Want to work with us?</h4>
				<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem seq.</p>
				<a href="#" class="hvr-rectangle-in">get in touch</a>
			</div>
		</div>

		<!--
        	页脚
        -->
		<div class="contact-section">
			<div class="container">
				<div class="contact-grids">
					<div class="col-md-3 contact-grid">
						<h5>who we are</h5>
						<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem seq.dem tumquam modipsae que voloratati andig.</p>
						<a href="#" class="more">more about us<img src="images/arrow.png"></a>
					</div>
					<div class="col-md-3 contact-grid">
						<h5>get in touch </h5>
						<p>Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores.</p>
						<div class="icon2">
							<ul>
								<li><i class="indicate"></i></li>
								<li>
									<p class="label1">84 Street, City, State 24813</p>
								</li>
							</ul>
							<ul>
								<li><i class="phone"></i></li>
								<li>
									<p class="label1">+00(123)4567890</p>
								</li>
							</ul>
							<ul>
								<li><i class="message"></i></li>
								<li><a href="mailto:example@mail.com">Info@Seen.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-md-3 contact-grid">
						<h5>latest works</h5>
						<div class="cont">
							<div class="contact-leftgrid">
								<div class="img-grid1">
									<img src="images/pic7.jpg">
								</div>
								<div class="img-grid2">
									<img src="images/pic8.jpg">
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="contact-rightgrid">
								<div class="img-grid3">
									<img src="images/pic9.jpg">
								</div>
								<div class="img-grid4">
									<img src="images/pic10.jpg">
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<div class="col-md-3 contact-grid">
						<h5>free update</h5>
						<p>Conecus iure posae volor remped modis aut lor volor accabora incim resto explabo.Laceaque eictemperum quiae sitiorem tumquam.</p>
						<div class="subscribe">
							<form>
								<input type="text" class="text" value="Your email..." onfocus="this.value = '';" onblur="if (this.value == '') {this.value = ' Enter your email';}">
								<input type="submit" value="Subscribe">
							</form>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<div class="footer-section">
			<div class="container">
				<div class="footer-left">
					<p> Copyright &copy;2015 All rights Reserved | Design by<a href="http://w3layouts.com" target="target_blank">W3Layouts</a></p>
				</div>
				<div class="bottom-menu">
					<ul>
						<li><a href="index.html" class="active">home</a></li>
						<li><a href="portfolio.html">portfolio</a></li>
						<li><a href="blog.html">blog</a></li>
						<li><a href="pages.html">pages</a></li>
						<li><a href="features.html">features</a></li>
						<li><a href="contact.html">contact</a></li>
					</ul>
				</div>
				<div class="clearfix"></div>

				<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
			</div>
		</div>
</body>
</html>