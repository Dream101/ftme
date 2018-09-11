$(function() {
	picture_add_affair();
});
//各种事件绑定
function picture_add_affair() {

//	$("#content").blur(function() {
//		picture_addclick_content();
//	});
//	$("#picturename").blur(function() {
//		picture_addclick_userNo();
//	});
//	$("#file").blur(function() {
//		var file = $("#file").val();
//		var picturePath=$("#picturePath").val();
//		if(file != picturePath&&file!=""){
//			alert(path);
//			$(".logo").attr("src",path);
//		}
//	});
//	$("#hospitalNo").change(function() {
//		patientList();
//	});
//	$("div").on("change", "#hospitalNo", function() {
//		patientList();
//	} );
	$("#butsub").click(function() {
		picture_Add();
	});
//	rightrole();
};
//表单提交
var filename="";
function picture_Add() {
// id          | INT(10)      | NO   | PRI |         | 主键      
// pictureNo   | VARCHAR(30)  | YES  |     |         | 图片编号    
// picturename | VARCHAR(34)  | YES  |     |         | 标题      
// picturePath | VARCHAR(40)  | YES  |     |         | 图片存储路径  
// content     | VARCHAR(255) | YES  |     |         | 内容      
	var id=$("#id").val();
	var pictureNo =$("#pictureNo").val();
	var picturename = $("#picturename").val();
	var picturePath=$("#picturePath").val();
	var file = $("#file").val();
	var content = $("#content").val();
	
//	var content = $("#content").val(); 
	if (file == "") {
		file=$("#picturePath").val();
//		$("#file").val(file);
	}
//	alert(pictureNo+"  "+picturename+"  "+file);
	var urlpath=$(".path").val()+"/picture/pictureinfoFileUpdate";
	 $("#form-admin-add").ajaxSubmit({  
        type:'post',
        url:urlpath,
        data:{
		 	"id":id,
		 	"pictureNo":pictureNo,
		 	"picturename":picturename,
		 	"picturePath":picturePath,
		 	"file":file,
		 	"content":content
        },
        success:function(mydata){
//            layer.msg('修改成功!!', {icon : 1});
            window.location.href=$(".path").val()+'/picture/pictureinfo';
        },  
        error:function(XmlHttpRequest,textStatus,errorThrown){  
//                console.log(XmlHttpRequest);  
//                console.log(textStatus);  
//                console.log(errorThrown);
//            layer.msg('修改失败!!', {icon : 1});
              window.location.href=$(".path").val()+'/picture/pictureinfo';
        }  
    });
	
};
////二级联动
//function patientList(){
//	var str=$("#hospitalNo option:selected").val();
//	var hospital=str.split(",");
//	var urlpath=$(".path").val()+"/picture/patientFineAll";
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
//
////验证标题是否为空
//function picture_addclick_picturename() {
//	var picturename = $("#picturename").val();
//	if(picturename!=null||picturename!=""){
//		return true;
//	}else{
//		return false;
//	}
//};
//
////验证上传文件的备注
//function picture_addclick_content() {
//	var content = $("#content").val();
//	if(content!=null||content!=""){
//		return true;
//	}else{
//		return false;
//	}
//};