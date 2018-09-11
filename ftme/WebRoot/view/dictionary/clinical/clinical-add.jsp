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
<title>添加临床诊断 - 临床诊断管理</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span> 临床诊断管理
		<span class="c-gray en">&gt;</span> 临床诊断列表
		<span class="c-gray en">&gt;</span> 添加临床诊断
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="${pageContext.request.contextPath}/clinical/clinicalList"
			target="rightFrame" title="返回">返回</a>
		</nav>
<article class="page-container">
	<form class="form form-horizontal" id="form-admin-add" action="${pageContext.request.contextPath}/clinical/clinicalSave" method="post" onsubmit="return clinical_add_click()">
		<input type="hidden" value="-1" id="id"/>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>临床诊断：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value=""  style="width:50%;" placeholder="请输入临床诊断内容" id="clinicalname" name="clinical.clinicalname">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<span class="add"><input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></span>
			</div>
		</div>
	</form>
</article>
<jsp:include    page="/view/_footer.jsp" flush="true"/>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dictionary/clinical/clinical-add.js"></script>
</body>
</html>