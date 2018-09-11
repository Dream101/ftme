<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>

	<head>
		<title>业务范围</title>
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
						<h1><a id="logo" href="${pageContext.request.contextPath}/portal/index1.jsp"><img src="${pageContext.request.contextPath}/images/portal-logo.jpg" alt=""></a></h1>
					</div>
					<div class="top-menu">
						<span class="menu"><img src="${pageContext.request.contextPath}/images/nav-icon.png" alt=""/></span>
						<ul>
							<nav class="cl-effect-5">
								<li><a href="${pageContext.request.contextPath}/portal/index1.jsp" ><span data-hover="首页">首页</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/portfolio.jsp"><span data-hover="公司介绍">公司介绍</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/blog"><span data-hover="行业动态">行业动态</span></a></li>
								<li><a href="${pageContext.request.contextPath}/portal/features.jsp" class="active" ><span data-hover="业务范围">业务范围</span></a></li><%--
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
							<li class="hover">角膜疾病</li>
							<li>青光眼</li>
							<li>晶状体疾病</li>
							<li>玻璃体和视网膜疾病</li>
							<li>色盲</li>
							<li>视神经病变</li>
							<li>眼肌疾病</li>
							<li>眼肿瘤</li>
							<li>眼球发育异常</li>
						</ul>

					</div>
					<div class="box1"><h2></h2>
						<div class="active">
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr>
									<td>圆锥角膜 （常显遗传）</td>
									<td>ZNF469、VSX1</td>
								</tr>
								<tr>
									<td>角膜上皮营养不良（常显和常隐遗传）</td>
									<td>KRT3、KRT12、TACSTD2</td>
								</tr>
								<tr>
									<td>角膜基质营养不良（常显和常隐遗传）</td>
									<td>DCN、GSN、PIKFYVE等</td>
								</tr>
								<tr>
									<td>Fuchs角膜内皮营养不良（常显遗传）</td>
									<td>AGBL1、Col8A2、ZEB1等</td>
								</tr>
								<tr>
									<td>后部多形性角膜内皮营养不良（常显遗传）</td>
									<td>Col8A2、VSX1、ZEB1</td>
								</tr>
								<tr>
									<td>先天遗传性角膜内皮营养不良（常隐遗传）</td>
									<td>SLC4A11</td>
								</tr>
								<tr>
									<td>Peters 异常（常显和常隐遗传）</td>
									<td>PAX6、CYP1B1、B3GALTL等</td>
								</tr>
							</table>
						</div>
						<div>
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr><td>开角型青光眼（常显和常隐遗传）</td><td>Myoc、OPTN、WDR36等 </td></tr>
								<tr><td>先天性青光眼（常隐和双基因遗传）</td><td>CYP1B1、LTBP2、Myoc</td></tr>
								<tr><td>Axenfeld-rieger 综合症（常显遗传）</td><td>FOXC1、FGFR2、PITX2</td></tr>
							</table>
						</div>
						<div>
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr>
									<td>先天性白内障（常显、常隐和X连锁遗传）</td>
									<td>BFSP2、CRYAB、CRYGA等</td>
								</tr>
								<tr>
									<td>Marfan 综合症 （常显遗传）</td>
									<td>FBN1</td>
								</tr>
								<tr>
									<td>WMS综合症（常显和常隐遗传）</td>
									<td>FBN1、ADAMTS10、LTBP2 等</td>
								</tr>
								<tr>
									<td>晶状体脱位 （常显和常隐遗传）</td>
									<td>FBN1、ADAMTSL4、LTBP2 等</td>
								</tr>
							</table>
						</div>
						<div>
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr><td>家族性渗出性玻璃体视网膜病变 （常显和X连锁遗传）</td><td>FZD4 、LRP5、TSPAN12 等</td></tr>
								<tr><td>Leber先天性黒朦（常隐遗传为主，少数常显遗传）</td><td>AIPL1、CRB1、CEP290等</td></tr>
								<tr><td>视网膜色素变性（常显、常隐、X连锁和双基因遗传）</td><td>CRX、RHO、PRPH2等</td></tr>
								<tr><td>锥杆细胞营养不良（常显、常隐和X连锁遗传）</td><td>CRX、GUCY2D、CERKL等</td></tr>
								<tr><td>白点状视网膜色素变性（常显和常隐遗传）</td><td>PRPH2、RHO、RDH5等</td></tr>
								<tr><td>结晶样视网膜色素变性（常隐遗传）</td><td>CYP4V2</td></tr>
								<tr><td>Best病（常显遗传）</td><td>BEST1、PRPH2</td></tr>
								<tr><td>黄斑变性（常显和常隐遗传）</td><td>CNGB3、FSCN2、RP1L1等</td></tr>
								<tr><td>Stargardt 病 （常显和常隐遗传）</td><td>PROM1、ABCA4、ELOVL4等</td></tr>
								<tr><td>视网膜劈裂（X连锁隐性遗传）</td><td>RS1</td></tr>
								<tr><td>无脉络膜症（X连锁隐性遗传）</td><td>CHM</td></tr>
								<tr><td>回旋状脉络膜萎缩（常隐遗传）</td><td>OAT</td></tr>
								<tr><td>眼白化病（常隐和X连锁隐性遗传）</td><td>OCA2、C10orf11、SLC24A5 等</td></tr>
								<tr><td>小口氏病（常隐遗传）</td><td>SAG、GRK1</td></tr>
								<tr><td>Norrie 病（X连锁隐性遗传）</td><td>NDP</td></tr>
								<tr><td>Usher 综合症 （常隐遗传）</td><td>USH2A、USH1C、MYO7A等</td></tr>
								<tr><td>BBS 综合症（常隐遗传）</td><td>BBS1、BBS2、BBS4 等</td></tr>
								<tr><td>Joubert 综合症（常隐遗传）</td><td>AHI1、ARL13B、CEP41 等</td></tr>
								<tr><td>Stickler 综合症（常显和常隐遗传）</td><td>COL11A1、COL11A2、COL2A1等</td></tr>
								<tr><td>Wagner 综合症（常显遗传）</td><td>VCAN</td></tr>
								<tr><td>Goldmann-Favre 综合症（常隐遗传）</td><td>NR2E3</td></tr>
							</table>
						</div>
						<div>
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr><td>红绿色盲（X连锁遗传）</td><td>OPN1LW、OPN1MW</td></tr>
								<tr><td>蓝色盲 （常显遗传）</td><td>OPN1SW</td></tr>
								<tr><td>全色盲（常隐遗传）</td><td>CNGB3、CNGA3、GNAT2等</td></tr>
							</table>
						</div>
						<div>
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr><td>Leber 遗传性视神经病 （线粒体遗传）</td><td>mtDNA等</td></tr>
								<tr><td>视神经萎缩（常显、常隐和X连锁遗传）</td><td>OPA1、NR2F1、OPA3等</td></tr>
								
							</table>
						</div>
						<div>
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr><td>眼球震颤 （X连锁遗传）</td><td>CASK、FRMD7、GPR143 </td></tr>
								<tr><td>斜视（常显和常隐遗传）</td><td>KIF21A、TUBB3、PHOX2A等</td></tr>
							</table>
						</div>
						<div>
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr><td>视网膜母细胞瘤 （常显遗传特征）</td><td>RB1</td></tr>
							</table>
						</div>
						<div>
							<table align="center">
								<tr>
									<th>疾病名称和遗传方式</th>
									<th>检测基因</th>
								</tr>
								<tr><td>小眼球伴或不伴眼组织缺损（常显、常隐和X连锁遗传）</td><td>RAX、PRSS56、PITX3等</td></tr>
								<tr><td>无虹膜 （常显和常隐遗传）</td><td>PAX6 、ELP4、RBP4等</td></tr>
							</table>
						</div>
					</div>
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
						<li><a href="${pageContext.request.contextPath}/portal/portal"><span data-hover="首页">首页</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/portfolio.jsp"><span data-hover="公司介绍">公司介绍</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/blog"><span data-hover="行业动态">行业动态</span></a></li>
						<li><a href="${pageContext.request.contextPath}/portal/features.jsp" class="active" ><span data-hover="业务范围">业务范围</span></a></li>
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