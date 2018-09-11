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
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
<jsp:include page="/view/_meta.jsp" flush="true"/>  
<title>上传文件 - 上传文件管理</title>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
		<nav class="breadcrumb">为患者:${user.name } 上传文件
		<a class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="${pageContext.request.contextPath}/frontPatient/frontList"
			title="返回">返回</a>
		</nav>
		<article class="page-container">
	<form class="form form-horizontal" action="" method="post" id="form-admin-add" enctype="multipart/form-data">
		<input type="hidden" value="${user.id}" name="id" id="id"/>
		<input type="hidden" value="${user.userNo}" name="userNo" id="userNo"/>
		<div class="row cl" id="uno_div">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>请选择上传文件类别：</label>
			<div class="formControls col-xs-8 col-sm-9">
			<span class="select-box" style="width:200px;">
				<select class="select" id="documentType" name="documentType" size="1">
					<option value="遗传诊断报告">遗传诊断报告</option>
					<option value="临床资料">临床资料</option>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上传：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="btn-upload form-group">
				 <input class="input-text upload-url radius" type="text" name="filename" id="filename" readonly><a href="javascript:void();" class="btn btn-primary radius"><i class="iconfont">&#xf0020;</i> 浏览文件</a>
				  <input type="file" multiple name="file" id="file" class="input-file">
				</span>
				<span id="fileOkOrNo"></span> 
			</div>
			
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="remark" cols="" id="remark" rows="" class="textarea"  placeholder="上传文件的简单信息...100个字符以内" dragonfly="true" style="width: 50%;"></textarea>
				<p class="textarea-numberbar">
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<span class="add"><input class="btn btn-primary radius" id="butsub" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"></span>
				
				<%--<input class="btn btn-primary radius" type="button" id="butsub" value="&nbsp;&nbsp;提交&nbsp;&nbsp;"/>
			--%></div>
		</div>
	</form>
</article>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.searchableSelect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/front/doctor-patient-Upload.js"></script>
</body>
</html>