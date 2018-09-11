$(function() {
	patient_addlist_affair();
});
//各种事件绑定
function patient_addlist_affair() {
	$("#userNo").blur(function() {
		patient_addclick_userNo();
	});
	$("#username").blur(function() {
		patient_addclick_username();
	});
	$("#username1").blur(function() {
		patient_addclick_username1();
	});
	$("#password").blur(function() {
		patient_addclick_password();
	});
	$("#email").blur(function() {
		patient_addclick_email();
	});
	$("#name").blur(function() {
		patient_addclick_name();
	});
	$("#name1").blur(function() {
		patient_addclick_name1();
	});
	$("#phone").blur(function() {
		patient_addclick_phone();
	});
//	//快递单号
//	$("#courierInfo").blur(function() {
//		patient_addclick_courierInfo();
//	});
	//备注
	$("#remark").blur(function() {
//		patient_addclick_remark();
	});
	$("#hospitalNo").change(function() {                                       
		var str=$("#hospitalNo option:selected").val();
		var hospital=str.split(",");
		var urlpath=$(".path").val()+"/patient/doctorFineAll";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"hospitalNo":hospital[0],
			},
			success : function(data) {
				$("#uno").empty();
				$(".searchable-select").remove();
				if(data.count=="0"){
					$("#uno").append("<option value='-1'>---请选择医生---</option>");	
					$("#uno").searchableSelect();
				}else{
					$("#uno").append("<option value='-1'>---请选择医生---</option>");	
					$.each(data.list,function(index, user) {
						$("#uno").append("<option value='"+user.userNo+","+user.name+"'>"+user.name+"</option>");
					});
					$("#uno").searchableSelect();
				}
			}
		});
	});
	rightrole();
	//	$("#butSel").click(function() {
	//		patient_add_click();
	//	});
};
//表单提交验证
function patient_add_click() {
	var hospitalNo = $("#hospitalNo option:selected").val();
	var uno = $("#uno option:selected").val();
	
	if(hospitalNo=="-1"){
		layer.tips('请选择治疗医院！', '#hospitalNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else if (uno=="-1") {
		layer.tips('请选择治疗医生！', '#uno', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else if (!patient_addclick_userNo()) {
		return false;
	}else if (!patient_addclick_username()) {
		return false;
	}else if (!patient_addclick_username1()) {
		return false;
	} else if (!patient_addclick_password()) {
//				alert(2);
		return false;
	}else if (!patient_addclick_name()) {
//				alert(3);
		return false;
	}else if (!patient_addclick_name1()) {
//				alert(3);
		return false;
	}else if (!patient_addclick_email()) {
		//		alert(4);
		return false;
	}else {
		return true;
	}
//	else if (!patient_addclick_remark()) {
////				alert(6);
//		return false;
//	}
		
	
	

//	else if (!patient_addclick_phone()) {
//				alert(5);
//		return false;
//	}
};
//验证患者账号
var falg = false;
//验证患者编号
var falg = false;
function patient_addclick_userNo() {
	var userNo = $("#userNo").val();
	if (userNo == "") {
		layer.tips('编号不能为空！', '#userNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/.test(userNo);
if (!result) {
		//		$("#butSel").attr( {"disabled" : "disabled"});		
		layer.tips('编号格式不正确！', '#userNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
//	$("#butSel").removeAttr("disabled");
		var urlpath=$(".path").val()+"/patient/ajaxuserno";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"userNo":userNo,
			},
			success : function(data) {
				if(!data){
					layer.tips('该编号已经存在！', '#userNo', {
						tips : [ 2, '#076BAB' ]
					});
				}
					falg=data;
			}
		});
	}
	return falg;
};
function patient_addclick_username() {
	var username = $("#username").val();
	if (username == "") {
		layer.tips('账号不能为空！', '#username', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^(1[0-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(username);
if (!result) {
		//		$("#butSel").attr( {"disabled" : "disabled"});		
		layer.tips('账号格式不正确！', '#username', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		
//		$("#butSel").removeAttr("disabled");
		var urlpath=$(".path").val()+"/patient/ajaxusername";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"username":username,
				"id":$("#id").val(),
			},
			success : function(data) {
				if(!data){
					layer.tips('该账号已经存在！', '#username', {
						tips : [ 2, '#076BAB' ]
					});
				}
					falg=data;
			}
		});
	}
	return falg;
};
//验证两次输入的患者账号（手机号）是否一样
function patient_addclick_username1() {
	var username = $("#username").val();
	var username1 = $("#username1").val();
	if (username1 == "") {
		layer.tips('确认账号不能为空！', '#username1', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^(1[0-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(username1);
	if (!result) {
		//		$("#butSel").attr( {"disabled" : "disabled"});		
		layer.tips('账号格式不正确！', '#username1', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else if(username!=username1){
		layer.tips('两次输入的账号不一致!', '#username1', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
	}
};

//验证患者密码
function patient_addclick_password() {
	var password = $("#password").val();
	if(password == ""){
		layer.tips('密码不能为空！', '#password', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[a-zA-Z]\w{5,17}$/.test(password);
	if (password.length < 6 ||!result) {
		layer.tips('密码格式不正确！', '#password', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		return true;
	}
};
//验证患者姓名
function patient_addclick_name() {
	var name = $("#name").val();
	if(name == ""){
		layer.tips('姓名不能为空！', '#name', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(name);
	if (!result) {
		layer.tips('姓名格式不对！', '#name', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		return true;
	}
};
//验证两次输入的患者姓名是否一样
function patient_addclick_name1() {
	var name = $("#name").val();
	var name1 = $("#name1").val();
	if(name1 == ""){
		layer.tips('姓名不能为空！', '#name1', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(name1);
	if (!result) {
		layer.tips('姓名格式不对！', '#name1', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else if(name!=name1){
		layer.tips('两次输入的姓名不一致!', '#name1', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
	}
};
//验证患者邮箱
function patient_addclick_email() {
	var email = $("#email").val();
	if(email == ""){
		return true;
	}
	var result = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email);
	if (!result) {
		layer.tips('邮箱格式不正确！', '#email', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		var urlpath=$(".path").val()+"/patient/ajaxEmail";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"email":email,
				"id":$("#id").val(),
			},
			success : function(data) {
				if(!data){
					layer.tips('该邮箱已经存在！', '#email', {
						tips : [ 2, '#076BAB' ]
					});
					$("#email").val("");
				}
				falg=data;
			}
		});
	}
	return falg;
};
//验证手机号
function patient_addclick_phone() {
	var phone = $("#phone").val();
	if(phone == ""){
		layer.tips('手机号不能为空！', '#phone', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^(1[0-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(phone);
	if (!result) {
		layer.tips('手机号码格式不正确！', '#phone', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		var urlpath=$(".path").val()+"/patient/ajaxPhone";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"phone":phone,
				"id":$("#id").val(),
			},
			success : function(data) {
				if(!data){
					layer.tips('该手机号已经存在！', '#phone', {
						tips : [ 2, '#076BAB' ]
					});
				}
				falg=data;
			}
		});
	}
		return falg;
};
//验证快递单号
function patient_addclick_courierInfo() {
	var courierInfo = $("#courierInfo").val();
	var result = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/.test(courierInfo);
	if (!result && courierInfo != "") {
		layer.tips('快递单号有特殊符号！', '#courierInfo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		return true;
	}
};
//验证患者备注
function patient_addclick_remark() {
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