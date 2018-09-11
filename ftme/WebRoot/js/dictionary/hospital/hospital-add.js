$(function() {
	hospital_addlist_affair();
});
//各种事件绑定
function hospital_addlist_affair() {
	$("#hospitalNo").blur(function() {
		hospital_addclick_hospitalNo();
	});
	$("#hospitalname").blur(function() {
		hospital_addclick_hospitalname();
	});
	//	$("#butSel").click(function() {
	//		hospital_add_click();
	//	});
};
//表单提交验证
function hospital_add_click() {
	if (!hospital_addclick_hospitalNo()) {
		return false;
	} else if (!hospital_addclick_hospitalname()) {
		//		alert(2);
		return false;
	} else {
		return true;
	}
};
//验证医院名称
var falg = false;
function hospital_addclick_hospitalname() {
	var hospitalname = $("#hospitalname").val();
	if (hospitalname == "") {
		layer.tips('医院名称不能为空！', '#hospitalname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(hospitalname);
if (!result) {
		//		$("#butSel").attr( {"disabled" : "disabled"});		
		layer.tips('医院名称格式不正确！', '#hospitalname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
//		$("#butSel").removeAttr("disabled");
		var urlpath=$(".path").val()+"/hospital/ajaxhospitalname";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"hospitalname":hospitalname,
				"id":$("#id").val(),
			},
			success : function(data) {
				if(!data){
					layer.tips('该医院名称已经存在！', '#hospitalname', {
						tips : [ 2, '#076BAB' ]
					});
				}
					falg=data;
			}
		});
	}
	return falg;
};
//验证医院编号
function hospital_addclick_hospitalNo() {
	var hospitalNo = $("#hospitalNo").val();
	
	if(hospitalNo == ""){
		layer.tips('医院编号不能为空！', '#hospitalNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[A-Za-z0-9]+$/.test(hospitalNo);
	if (!result) {
		layer.tips('医院编号格式不正确！', '#hospitalNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		var urlpath=$(".path").val()+"/hospital/ajaxhospitalNo";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"hospitalNo":hospitalNo,
				"id":$("#id").val(),
			},
			success : function(data) {
				if(!data){
					layer.tips('该医院编号已经存在！', '#hospitalNo', {
						tips : [ 2, '#076BAB' ]
					});
				}
				falg=data;
			}
		});
	}
		return falg;
};