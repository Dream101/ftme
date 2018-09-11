$(function() {
	staff_addlist_affair();
});
//各种事件绑定
function staff_addlist_affair() {
	$("#username").blur(function() {
		staff_addclick_username();
	});
	$(".password").blur(function() {
		staff_addclick_password();
	});
	$("#email").blur(function() {
		staff_addclick_email();
	});
	$("#name").blur(function() {
		staff_addclick_name();
	});
	$("#phone").blur(function() {
		staff_addclick_phone();
	});
	rightrole();
	//备注验证
//	$("#remark").blur(function() {
//		staff_addclick_remark();
//	});
};
//表单提交验证
function staff_add_click() {
	if (!staff_addclick_username()) {
		return false;
	} else if (!staff_addclick_password()) {
		//		alert(2);
		return false;
	} else if (!staff_addclick_name()) {
		//		alert(3);
		return false;
	} else if (!staff_addclick_phone()) {
		//		alert(5);
		return false;
	} else if (!staff_addclick_email()) {
		//		alert(4);
		return false;
	} else {
		return true;
	}
	
};
//验证员工账号
var falg = false;
function staff_addclick_username() {
	var username = $("#username").val();
	if (username == "") {
		layer.tips('账号不能为空！', '#username', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/.test(username);
if (!result) {
		//		$("#butSel").attr( {"disabled" : "disabled"});		
		layer.tips('账号格式不正确！', '#username', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		
//		$("#butSel").removeAttr("disabled");
		var urlpath=$(".path").val()+"/staff/ajaxusername";
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
//验证员工密码
function staff_addclick_password() {
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
//验证员工姓名
function staff_addclick_name() {
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
//验证员工邮箱
function staff_addclick_email() {
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
		var urlpath=$(".path").val()+"/staff/ajaxEmail";
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
//验证手机号
function staff_addclick_phone() {
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
		var urlpath=$(".path").val()+"/staff/ajaxPhone";
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
//验证员工备注
function staff_addclick_remark() {
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