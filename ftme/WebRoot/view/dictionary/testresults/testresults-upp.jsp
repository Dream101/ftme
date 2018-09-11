<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
<jsp:include page="/view/_meta.jsp" flush="true"/>  
<title>修改检测结果 - 检测结果管理</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span> 检测结果管理
		<span class="c-gray en">&gt;</span> 检测结果列表
		<span class="c-gray en">&gt;</span> 修改检测结果 
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="${pageContext.request.contextPath}/testresults/testresultsList"
			target="rightFrame" title="返回">返回</a>
		</nav>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-add" action="${pageContext.request.contextPath}/testresults/testresultsUpdate" method="post" onsubmit="return testresults_add_click()">
		<input type="hidden" value="${testresults.id}" name="testresults.id" id="id"/>
		<input type="hidden" value="${testresults.testresultsNo}" name="testresults.id" id="id"/>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>检测结果：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${testresults.testresultsname}"
				 style="width:50%;"  id="testresultsname" name="testresults.testresultsname">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<span class='upp'><input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></span>
			</div>
		</div>
	</form>
</article>
<jsp:include    page="/view/_footer.jsp" flush="true"/>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dictionary/testresults/testresults-add.js"></script>
</body>
</html>