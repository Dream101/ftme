$(function() {
	frontpwd_addlist_affair();
	var puf = $(".pwduppfalg").val();
	if(puf!=""){
		//alert(puf)
		layer.msg(puf, {icon : 2});
	}
});

//各种事件绑定
function frontpwd_addlist_affair() {
	$("#password2").blur(function() {
		frontpwd_addclick_password2();
	});
	$("#password").blur(function() {
		frontpwd_addclick_password();
	});
};
//表单提交验证
function frontpwd_add_click() {
	if(!frontpwd_addclick_password()){
		return false;
	}else if(!frontpwd_addclick_password2()){
		return false;
	}else{
		return true;
	}
};
var falg=false;
//验证员工密码
function frontpwd_addclick_password() {
	var password = $("#password").val();
	var result = /^[a-zA-Z]\w{5,17}$/.test(password);
	if (!result) {
		layer.tips('密码格式不正确！字母开头，5-18个字符！', '#password', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		var urlpath=$(".path").val()+"/userinfo/ckpwdFront";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"password":password,
			},
			success : function(data) {
				if(!data){
					layer.tips("原始密码错误！", '#password', {
						tips : [ 2, '#076BAB' ]
					});
				}else{
					layer.tips('原始密码正确！', '#password', {
						tips : [ 2, '#076BAB' ]
					});
				}
				falg=data;
			}
		});
		return falg;
	}
};
//验证员工密码
function frontpwd_addclick_password2() {
	var password2 = $("#password2").val();
	var password = $("#password1").val();
	var result2 = /^[a-zA-Z]\w{5,17}$/.test(password2);
	var result = /^[a-zA-Z]\w{5,17}$/.test(password);
	if (!result) {
		layer.tips('密码格式不正确！字母开头，5-18个字符！', '#password1', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else if(!result2){
		layer.tips('密码格式不正确！字母开头，5-18个字符！', '#password2', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else if(password!=password2){
		layer.tips('密码不一致！', '#password2', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
	}
};