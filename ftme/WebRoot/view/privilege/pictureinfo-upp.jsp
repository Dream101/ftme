<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script> 
<jsp:include page="/view/_meta.jsp" flush="true"/>  
<title>修改轮播图信息 - 前台轮播图管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/img.js"></script>
</head>
<body>
<input type="hidden" name="path" class="path" value="${pageContext.request.contextPath}" />
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>前台轮播图管理 <span class="c-gray en">&gt;</span> 修改轮播图信息<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="${pageContext.request.contextPath}/picture/pictureinfo"target="rightFrame" title="返回" >返回</a></nav>
<article class="page-container">
	<form class="form form-horizontal" action="" method="post" id="form-admin-add" enctype="multipart/form-data">
		<input type="hidden" value="${picture.id}" id="id" name="id"/>
		<input type="hidden" value="${picture.pictureNo}" id="pictureNo" name="pictureNo">
		<input type="hidden" value="${picture.picturePath}" id="picturePath" name="picturePath">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${picture.picturename}" style="width: 200px;"  id="picturename" name="picturename">
				<span class="uname"></span>
			</div>
		</div>
		<div  class="row cl" id="divPreview">
			<img id="imgHeadPhoto" src="${pageContext.request.contextPath}${picture.picturePath}" 
			style="width: 272px; height: 100px; border: solid 1px #d2e2e2; margin-left: 26%;" alt="" />
		</div>
		<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3" ><span class="c-red">*</span>上传：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="btn-upload form-group">
				 <input class="input-text upload-url radius" type="text" name="filename" value="${picture.picturePath}" id="filename" readonly><a href="javascript:void();" class="btn btn-primary radius"><i class="iconfont">&#xf0020;</i>浏览文件</a>
				  <input type="file" multiple name="file" id="file" class="input-file" onchange="PreviewImage(this,'imgHeadPhoto','divPreview');" >
				</span>
				<span class="c-red">轮播图尺寸比例为13.6 ：5(如：1360*500)</span>
				<%--<img src="${pageContext.request.contextPath}${picture.picturePath}" style="height: 80px; width: 300px;" class="logo">
				 --%>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="content" cols="" id="content" rows="" class="textarea"  dragonfly="true" style="width: 50%; height: 200px;">${picture.content}</textarea>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/privilege/pictureadd.js"></script>
</body>
</html>