<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>

	<head>
		<title>公司介绍</title>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
		<link href="${pageContext.request.contextPath}/css/front-style.css" rel="stylesheet" type="text/css" media="all" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.js"></script>
		
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		</script>
		<link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900,200italic,300italic,400italic,600italic,700italic,900italic' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Fugaz+One' rel='stylesheet' type='text/css'>
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

		<!---- start-smoth-scrolling---->
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
		<!---End-smoth-scrolling----
 <!------ Light Box ------>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/swipebox.css">
		<script src="${pageContext.request.contextPath}/js/portal/jquery.swipebox.min.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
				$(".swipebox").swipebox();
			});
		</script>
		<!------ Eng Light Box ------>
	</head>

	<body>
	<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
	<input type="hidden" name="falg" id="falg" value="0" />
		<!-- header -->
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
					<div class="social-icons"><%--
						<a href="#" class="login" id="register" style="color: rgba(80, 106, 133, 1);font-weight: 600;margin-right: 2rem;">登&nbsp;录</a>
						<a href="#" class="login" id="find" style="color: rgba(80, 106, 133, 1);font-weight: 600;margin-right: 2rem;">找回密码</a>
					--%></div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="container">
				<div class="header-bottom">
					<div class="logo">
						<h1><a id="logo" href="${pageContext.request.contextPath}/portal/index1.jsp"><img src="${pageContext.request.contextPath}/images/portal-logo.jpg" alt=""></a></h1>
					</div>
					<div class="top-menu">
						<span class="menu"><img src="${pageContext.request.contextPath}/images/nav-icon.png" alt=""/></span>
						<ul>
							<nav class="cl-effect-5">
								<li><a href="${pageContext.request.contextPath}/portal/index1.jsp" ><span data-hover="首页">首页</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/portfolio.jsp" class="active" ><span data-hover="公司介绍">公司介绍</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/blog"><span data-hover="行业动态">行业动态</span></a></li>
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


		
		<div class="portfolio-work-section" id="portfolio">
			<div class="container">
				<div class="bigBox" id="bigBox">
					<div class="box" id="box">
						<ul>
							<li class="hover">公司历程</li>
							<li>公司介绍</li>
							<li>企业文化</li>
							<%--<li>专家团队</li>
						--%></ul>

					</div>
					<div class="box1">
						<%--<div class="active">北京中因科技是业内领先的转化医学研究机构，北京市高新技术企业，拥有来自美国哈佛医学院， 斯坦福医学院，宾夕法尼亚大学，MD安德森癌症中心等国际顶尖知名院校的科学家组成的强大技术团队，拥有高通量测 序、基因大数据分析、CAR-T免疫细胞治疗、基因治疗和转基因动物模型构建研发实验室平台，长期以来致力于遗传病 及肿瘤的转化医学研究，并将高通量测序与CAR-T免疫细胞治疗技术、基因治疗技术相结合，开创了独特的肿瘤诊疗一 体化、遗传病诊疗一体化模式。</div>
						--%>
						<div class="active">一.2011年至公司成立，已经为1000余例遗传性眼科患者提供基因检测<br/>二.2016年1月，北京中因科技有限公司的成立，是一家专业从事遗传性眼病临床基因诊断的企业</div>
						<div>北京中因科技有限公司是一家专业从事遗传性眼病临床基因诊断、预防和转基因治疗研究的企业。公司拥有雄厚的眼科遗传学高端医生团队和技术基础，建立了遗传性眼病临床基因诊断平台，为遗传性眼病患者提供快速、准确、可靠和专业的临床基因诊断服务。该平台是国内最早开展遗传性眼病临床基因诊断的平台之一，也是目前国内眼科学界覆盖病种最多、覆盖基因最全面的遗传性眼病临床基因诊断平台。该平台建设得到了相关遗传学专家和眼科专家的指导，自2011年至今，已经为1000余例遗传性眼病患者提供基因检测，确保了检测方法的科学性、检测结果的准确性和检测流程的成熟可靠，检测报告的医学遗传解释部分由知名遗传性眼病专家参与撰写和审阅，确保基因诊断结果与临床表型相符，为广大临床医生和患者提供参考性和可读性更强的遗传咨询报告</div>
						<div>专业、标准、权威<br/>希望通过持之以恒的努力，建立专业、可靠、诚信、权威的遗传性眼病临床基因诊断平台；通过婚前、孕前或产前的基因诊断有效预防严重遗传性眼病患儿的出生；为遗传性眼病患者提供有效地转基因治疗、干细胞治疗和药物治疗。建立世界一流的遗传性眼病基因诊断、预防和治疗中心。</div>
						<%--<div>专家团队</div>
						
					--%></div>
				</div>

			</div>

			<div>
				<div class="clearfix"></div>
			</div>
			<!-- Script for gallery Here -->
			<script type="text/javascript" src="${pageContext.request.contextPath}/js/portal/jquery.mixitup.min.js"></script>
			<script type="text/javascript">
				$(function() {
					var filterList = {
						init: function() {
							// MixItUp plugin
							// http://mixitup.io
							$('#portfoliolist').mixitup({
								targetSelector: '.portfolio',
								filterSelector: '.filter',
								effects: ['fade'],
								easing: 'snap',
								// call the hover effect
								onMixEnd: filterList.hoverEffect()
							});
						},
						hoverEffect: function() {
							// Simple parallax effect
							$('#portfoliolist .portfolio').hover(
								function() {
									$(this).find('.label').stop().animate({
										bottom: 0
									}, 200, 'easeOutQuad');
									$(this).find('img').stop().animate({
										top: -30
									}, 500, 'easeOutQuad');
								},
								function() {
									$(this).find('.label').stop().animate({
										bottom: -40
									}, 200, 'easeInQuad');
									$(this).find('img').stop().animate({
										top: 0
									}, 300, 'easeOutQuad');
								}
							);
						}
					};
					// Run the show!
					filterList.init();
				});
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
					var tab1 = new tab("bigBox")
				})
			</script>
			<!-- Gallery Script Ends -->

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
						<li><a href="${pageContext.request.contextPath}/portal/portal" ><span data-hover="首页">首页</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/portfolio.jsp" class="active" ><span data-hover="公司介绍">公司介绍</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/blog"><span data-hover="行业动态">行业动态</span></a></li>
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