$(function() {
	$("#username").focus();//光标到用户名输入框
	affair();
	checkLogin();//验证登陆名和密码
	errerinfo();//返回的值
});

var a = 1;
//各种事件绑定
function affair() {
	$("#username").bind("blur", function() {
		checkName();
	});

	$("#pwd").bind("blur", function() {
		checkPwd();
	});
};
//验证账号格式
function checkName() {
	var username = $("#username").val();
	var result = /^[A-Za-z0-9]{4,40}$/.test(username);
//	alert(username+"   "+result);
	if (username.length == 0) {
		layer.tips('账号不能为空！', '#username', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else if (username.length < 5 || !result) {
		layer.tips('账号格式不正确！', '#username', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		return true;
	}
};
//验证密码格式
function checkPwd() {
	var pwd = $("#pwd").val();
	var result = /^[A-Za-z0-9]{4,40}$/.test(pwd);
	if (pwd.length == 0) {
		layer.tips('密码不能为空！', '#pwd', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else if (pwd.length < 6 || !result) {
		layer.tips('密码格式不正确！', '#pwd', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		return true;
	}
};
//验证密码格式
function errerinfo() {
	var flag = $("#flag").val();
	var errorinfo = $("#errorinfo").val();
	if(flag==null||flag==""){
		return ;
	}
	if (flag == 0) {
		layer.tips(errorinfo, '#username', {
			tips : [ 2, '#076BAB' ]
		});
	} else if(flag == 1){
		layer.tips(errorinfo, '#pwd', {
			tips : [ 2, '#076BAB' ]
		});
	}
};
//点击登陆按钮时候检测是否用户名密码为空
function checkLogin() {
	$(".btn-success").click(function() {
//		var username = $("#username").val();
//		var pwd = $("#pwd").val();
		if (!checkName()) {
			return false;
		} else if (!checkPwd()) {
			return false;
		} else {
			return true;
		}
	});
};