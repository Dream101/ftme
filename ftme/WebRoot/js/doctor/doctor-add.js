$(function() {
	doctor_addlist_affair();
});
//各种事件绑定
function doctor_addlist_affair() {
	$("#username").blur(function() {
		doctor_addclick_username();
	});
	$("#username1").blur(function() {
		doctor_addclick_username1();
	});
	$(".password").blur(function() {
		doctor_addclick_password();
	});
	$("#email").blur(function() {
		doctor_addclick_email();
	});
	$("#name").blur(function() {
		doctor_addclick_name();
	});
	$("#name1").blur(function() {
		doctor_addclick_name1();
	});
	rightrole();
//	$("#phone").blur(function() {
//		doctor_addclick_phone();
//	});
//	$("#remark").blur(function() {
//		doctor_addclick_remark();
//	});
	
};
//表单提交验证
function doctor_add_click() {
	var hospitalNo = $("#hospitalNo option:selected").val();
	if(hospitalNo=="-1"){
		layer.tips('请选择医院！', '#hospitalNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else if (!doctor_addclick_username()) {
		return false;
	}else if (!doctor_addclick_username1()) {
		return false;
	} else if (!doctor_addclick_password()) {
		//		alert(2);
		return false;
	} else if (!doctor_addclick_name()) {
		//		alert(3);
		return false;
	}else if (!doctor_addclick_name1()) {
		//		alert(3);
		return false;
	}else if (!doctor_addclick_email()) {
		//		alert(4);
		return false;
	}else {
		return true;
	}
//	 else if (!doctor_addclick_phone()) {
//		//		alert(5);
//		return false;
//	} 
};
//验证医生账号
var falg = false;
function doctor_addclick_username() {
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
		var urlpath=$(".path").val()+"/doctor/ajaxusername";
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
function doctor_addclick_username1() {
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
//验证医生密码
function doctor_addclick_password() {
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
//验证医生姓名
function doctor_addclick_name() {
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
function doctor_addclick_name1() {
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
//验证医生邮箱
function doctor_addclick_email() {
	var email = $("#email").val();
	if(email == ""){
//		layer.tips('邮箱不能为空！', '#email', {
//			tips : [ 2, '#076BAB' ]
//		});
//		return false;
		return true;
	}
	var result = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email);
	if (!result) {
		layer.tips('邮箱格式不正确！', '#email', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		var urlpath=$(".path").val()+"/doctor/ajaxEmail";
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
				}
				falg=data;
			}
		});
	}
	return falg;
};
////验证手机号
//function doctor_addclick_phone() {
//	var phone = $("#phone").val();
//	if(phone == ""){
//		layer.tips('手机号不能为空！', '#phone', {
//			tips : [ 2, '#076BAB' ]
//		});
//		return false;
//	}
//	var result = /^(1[0-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(phone);
//	if (!result) {
//		layer.tips('手机号码格式不正确！', '#phone', {
//			tips : [ 2, '#076BAB' ]
//		});
//		return false;
//	} else {
//		var urlpath=$(".path").val()+"/doctor/ajaxPhone";
//		$.ajax( {
//			type : "post",
//			url :urlpath,
//			dataType : "json",
//			async: false,
//			data : {
//				"phone":phone,
//				"id":$("#id").val(),
//			},
//			success : function(data) {
//				if(!data){
//					layer.tips('该手机号已经存在！', '#phone', {
//						tips : [ 2, '#076BAB' ]
//					});
//				}
//				falg=data;
//			}
//		});
//	}
//		return falg;
//};
//验证医生备注
function doctor_addclick_remark() {
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