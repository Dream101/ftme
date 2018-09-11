<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>行业动态</title>
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/${pageContext.request.contextPath}/lib/layer/2.1/layer.js"></script>
		
	</head>

	<body>
		<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<input type="hidden" name="falg" id="falg" value="0" />
		<div class="header" id="home">
			<!--  页眉（导航一些基本信息）  -->
			<div class="header-top">
				<div class="container">
					<div class="icon">
						<ul>
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
								<li><a href="${pageContext.request.contextPath}/portal/portal"><span data-hover="首页">首页</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/portfolio.jsp"><span data-hover="公司介绍">公司介绍</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/blog"  class="active" ><span data-hover="行业动态">行业动态</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/features.jsp"><span data-hover="业务范围">业务范围</span></a></li><%--
								<li><a href="${pageContext.request.contextPath}/portal/pages.jsp"><span>错误页面（可以不管）</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/contact.jsp"><span>contact</span></a></li>
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
		
		<!-- content-section-starts -->
		<div class="blog-content">
			<div class="container">
				<div class="blog-content-head text-left">
					<h3>行业动态</h3>
				</div>
				<div class="section group">
					<div class="blog-grids">
						<div class="cont span_2_of_3">
							<div class="col-md-12 blog-grid">
								<c:forEach items="${blogList}" var="blog">
									<div class="blog_posts">
										<div class="blog_date">
											<figure><span>${blog.day}</span>${blog.month}月</figure>
										</div>
										<div class="blog_desc">
											<div class="blog_heading">
												<a href="${pageContext.request.contextPath}/portal/blogByNo?newsNo=${blog.newsNo}">${blog.title}</a>
												<p>${blog.datetime}
												</p>
											</div>
	
											<div class="blog_data">
												<p>${blog.content}</p>
												<div class="more">
													<span><a class="button outline-outward hvr-rectangle-in" href="${pageContext.request.contextPath}/portal/blogByNo?newsNo=${blog.newsNo}">查看更多</a></span>
												</div>
											</div>
	
											<div class="clearfix"></div>
										</div>
										<div class="clearfix"></div>
									</div>
								</c:forEach>
								<div class="content-pagenation">
								<c:if test="${fy.allpages>0}">
									<li><a href="${pageContext.request.contextPath}/portal/blog?begin=1">首页</a></li>
									<li><a href="${pageContext.request.contextPath}/portal/blog?begin=${fy.up}">上一页</a></li>
									<li><a href="${pageContext.request.contextPath}/portal/blog?begin=${fy.next}">下一页</a></li>
									<li><a href="${pageContext.request.contextPath}/portal/blog?begin=${fy.allpages}">尾页</a></li>
									<div class="clearfix"> </div>
									</c:if>
									<%--
								
			<span class=r>当前第"+current+"页共有"+ ${fy.allpages}+ "页&nbsp;&nbsp;<a href='' id='first' class='btn btn-success radius' >首页</a>&nbsp;&nbsp;<a href='#' id='myup' class='btn btn-success radius'>上一页</a>&nbsp;&nbsp;<a href='#' id='mynext' class='btn btn-success radius'>下一页</a>&nbsp;&nbsp;<a href='#' id='last' class='btn btn-success radius'	>尾页</a></span>
		
								--%></div>
							</div>
						</div>

						<div class="clearfix"></div>
					</div>
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
						<li><a href="${pageContext.request.contextPath}/portal/portal"><span data-hover="首页">首页</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/portfolio.jsp"><span data-hover="公司介绍">公司介绍</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/blog" class="active" ><span data-hover="行业动态">行业动态</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/features.jsp"><span data-hover="业务范围">业务范围</span></a></li>
					</ul>
				</div>
				<div class="clearfix"></div>

				<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
			</div>
		</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.1/layer.js"></script>
		
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal/portal.js"></script>		
	</body>
</html>