$(function() {
	clinical_addlist_affair();
});
//各种事件绑定
function clinical_addlist_affair() {

	$("#clinicalname").blur(function() {
		clinical_addclick_clinicalname();
	});
	//	$("#butSel").click(function() {
	//		clinical_add_click();
	//	});
};
//表单提交验证
function clinical_add_click() {
//	if (!clinical_addclick_clinicalNo()) {
//		return false;
//	} else 
	if (!clinical_addclick_clinicalname()) {
		//		alert(2);
		return false;
	} else {
		return true;
	}
};
//验证检测结果
var falg = false;
function clinical_addclick_clinicalname() {
	var clinicalname = $("#clinicalname").val();
	if (clinicalname == "") {
		layer.tips('临床诊断内容不能为空！', '#clinicalname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else {
//		$("#butSel").removeAttr("disabled");
		var urlpath=$(".path").val()+"/clinical/ajaxclinicalname";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"clinicalname":clinicalname,
				"id":$("#id").val(),
			},
			success : function(data) {
				if(!data){
					layer.tips('该临床诊断内容已经存在！', '#clinicalname', {
						tips : [ 2, '#076BAB' ]
					});
				}
					falg=data;
			}
		});
	}
	return falg;
//	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(clinicalname);
//	if (!result) {
//		//		$("#butSel").attr( {"disabled" : "disabled"});		
//		layer.tips('临床诊断内容格式不正确！', '#clinicalname', {
//			tips : [ 2, '#076BAB' ]
//		});
//		return false;
//	}
};