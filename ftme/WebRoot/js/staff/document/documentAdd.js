$(function() {
	document_add_affair();
});
//各种事件绑定
function document_add_affair() {
	$("#remark").blur(function() {
		document_addclick_remark();
	});
	$("#userNo").blur(function() {
		document_addclick_userNo();
	});
	$("#file").blur(function() {
		var file = $("#file").val();
		if(file != filename){
			$("#fileOkOrNo").empty();
		}
	});
//	$("#hospitalNo").change(function() {
//		patientList();
//	});
//	$("div").on("change", "#hospitalNo", function() {
//		patientList();
//	} );
	$("#butsub").click(function() {
		document_Add();
	});
	rightrole();
};
//表单提交
var filename="";
function document_Add() {
	
	var userNo =$("#userNo").val();
//	var userNo = $("#userNo option:selected").val();// userNo 患者的id（用户表userinfo）
//	var hospitalNo = $("#hospitalNo option:selected").val();// hospitalNo 所属医院    
	var documentType = $("#documentType option:selected").val();// documentType 文档类别(遗传诊断报告,基因的测序结果,诊断病例分析)
	var file = $("#file").val();
	$("#fileOkOrNo").empty();
	var remark = $("#remark").val();// remark 备注     

//	if(hospitalNo=="-1"){
//		layer.tips('请选择医院!', $("#hospitalNo"), {
//			tips : [ 2, '#076BAB' ]
//		});
//	}else 
	if(userNo==""){
		layer.tips('请选择患者!', $("#userNo"), {
			tips : [ 2, '#076BAB' ]
		});
	}else if (file == "") {
		layer.tips('请选择上传的文件!', $("#file"), {
			tips : [ 2, '#076BAB' ]
		});
	}else if (file == filename) {
		$("#fileOkOrNo").append("<span class='btn btn-danger radius' >请重新选择上传的文件!</span>");
	}else{
		var urlpath=$(".path").val()+"/document/documentFileAdd";
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
////二级联动
//function patientList(){
//	var str=$("#hospitalNo option:selected").val();
//	var hospital=str.split(",");
//	var urlpath=$(".path").val()+"/document/patientFineAll";
//	$.ajax( {
//		type : "post",
//		url :urlpath,
//		dataType : "json",
////		async: false,
//		data : {
//			"hospitalNo":hospital[0],
//		},
//		success : function(data) {
//			$("#userNo").empty();
//			$(".searchable-select").remove();
//			if(data.count=="0"){
//				$("#userNo").append("<option value='-1'>该医院未添加患者</option>");
//				$("#userNo").searchableSelect();
//			}else{
//				$.each(data.list,function(index, user) {
//					$("#userNo").append("<option value='"+user.userNo+"'>"+user.name+"</option>");
//				});
//				$("#userNo").searchableSelect();
//			}
//		}
//	});
//};

//验证患者编号是否正确备注
function document_addclick_userNo() {
	var userNo = $("#userNo").val();
	var result = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/.test(userNo);
	if (!result && userNo != "") {
		layer.tips('患者编号格式错误！', '#userNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		$.ajax( {
			type : "post",
			url :$(".path").val()+"/document/patientClick",
			dataType : "json",
			async: false,
			data : {
				"userNo":userNo,
			},
			success : function(data) {
				if(data.falg){
					layer.tips('患者名称：'+data.user.name, '#userNo', {
						tips : [ 2, '#076BAB' ]
					});
					return true;
				}else{
					layer.tips('患者编号错误！', '#userNo', {
						tips : [ 2, '#076BAB' ]
					});
					return false;
				}
			}
		});
	}
};

//判断是否
//验证上传文件的备注
function document_addclick_remark() {
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