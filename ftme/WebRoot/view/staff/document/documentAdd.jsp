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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 上传文件
<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="${pageContext.request.contextPath}/document/documentAdd"target="rightFrame" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<article class="page-container">
	<form class="form form-horizontal" action="" method="post" id="form-admin-add" enctype="multipart/form-data">
		<input type="hidden" value="-1" id="id"/><%--
	  userNo            患者的id（用户表userinfo）
 hospitalNo        所属医院    
 name              患者的名称（管理员查询的检索字段）
 phone             手机号（管理员查询的检索字段）
 documentType      文档类别(遗传诊断报告,基因的测序结果,诊断病例分析)
 documentName       文档名称    
 documentUploadName 文档上传后的新文档名称
 documentPath       文档上传路径  
 admin             上传者(当前登录者)
 remark            备注      
 documentState     状态（本患者是否已经下载过遗传诊断报告：0可下载，1不可下载）
 datetime          上传时间    
 state             备用字段    
 		
 		<div class="row cl" id="uno_div">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所在医院：</label>
			<div class="formControls col-xs-8 col-sm-9"><span class="select-box" style="width:200px;">
				<select class="select" id="hospitalNo" name="hospitalNo" size="1">
					<option value="-1">---请选择医院---</option>
					<c:forEach items="${list}" var="hospital">
						<option value="${hospital.hospitalNo},${hospital.hospitalname}">${hospital.hospitalname}</option>
					</c:forEach>
				</select>
				</span> </div>
		</div>
		<div class="row cl" id="uno_div">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>上传文档的患者：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<select class="select" id="userNo" name="userNo" size="1">
					<option value="-1">---请选择患者---</option>
				</select>
				 </div>
		</div>--%>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>患者编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" style="width: 200px;" placeholder="患者编号" id="userNo" name="userNo">
				<span class="uname"></span>
			</div>
		</div>
		<div class="row cl" id="uno_div">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>请选择上传文件类别：</label>
			<div class="formControls col-xs-8 col-sm-9">
			<span class="select-box" style="width:200px;">
				<select class="select" id="documentType" name="documentType" size="1">
					<option value="遗传诊断报告">遗传诊断报告</option>
					<option value="测序结果">测序结果</option>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/staff/document/documentAdd.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/permissions/right-role.js"></script>
<script>
	//$(function(){
	//	$("#userNo").searchableSelect();
	//});
</script>
</body>
</html>