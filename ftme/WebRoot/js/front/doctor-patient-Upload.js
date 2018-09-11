$(function() {
	frontPatient_add_affair();
});
//各种事件绑定
function frontPatient_add_affair() {
	$("#remark").blur(function() {
		frontPatient_addclick_remark();
	});
	$("#file").blur(function() {
		var file = $("#file").val();
		if(file != filename){
			$("#fileOkOrNo").empty();
		}
	});
	$("#butsub").click(function() {
		frontPatient_Add();
	});
//	rightrole();
};
//表单提交
var filename="";
function frontPatient_Add() {
	
	var userNo =$("#userNo").val();
//	var userNo = $("#userNo option:selected").val();// userNo 患者的id（用户表userinfo）
//	var hospitalNo = $("#hospitalNo option:selected").val();// hospitalNo 所属医院    
	var frontPatientType = $("#frontPatientType option:selected").val();// frontPatientType 文档类别(遗传诊断报告,基因的测序结果,诊断病例分析)
	var file = $("#file").val();
	$("#fileOkOrNo").empty();
	var remark = $("#remark").val();// remark 备注     

	if (file == "") {
		layer.tips('请选择上传的文件!', $("#file"), {
			tips : [ 2, '#076BAB' ]
		});
	}else if (file == filename) {
		$("#fileOkOrNo").append("<span class='btn btn-danger radius' >请重新选择上传的文件!</span>");
	}else{
		var urlpath=$(".path").val()+"/frontPatient/documentFileAdd";
		$("#fileOkOrNo").empty();
		filename=file;
		 $("#form-admin-add").ajaxSubmit({  
            type:'post',
            url:urlpath,
            success:function(mydata){
//                console.log(mydata);
//                if(mydata){
                	layer.msg('添加成功!!', {icon : 1});
                	$("#fileOkOrNo").append("<span class='btn btn-success radius' >上传成功！</span>");
//                }else{
//                	layer.msg('添加失败!!', {icon : 1});
//                	$("#fileOkOrNo").append("<span class='btn btn-danger radius'>上传失败！</span>");
//                }
            },  
            error:function(XmlHttpRequest,textStatus,errorThrown){  
                console.log(XmlHttpRequest);  
                console.log(textStatus);  
                console.log(errorThrown);
                layer.msg('添加失败!!', {icon : 1});
            }  
        });
	}
};
//验证上传文件的备注
function frontPatient_addclick_remark() {
	var remark = $("#remark").val();
	var result = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/.test(remark);
	if (!result && remark != "") {
		layer.tips('备注内容有特殊符号！', '#remark', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		return true;
	}
};