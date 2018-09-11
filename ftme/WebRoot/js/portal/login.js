$(function() {
	LoginPwd_addlist_affair();
});
//各种事件绑定
function LoginPwd_addlist_affair() {
	$("#butSel1").click(function() {
		LoginPwd();
	});
	$("#butSel2").click(function() {
		LoginPwd2();
	});
};
//表单提交验证
function LoginPwd() {
		
	var username=$("#username1").val();
	var password=$("#password1").val();
	if(username==""){
		layer.tips('账号不能为空！', '#username1', {
			tips : [ 2, '#076BAB' ]
		});
	}else if(/^[a-zA-Z]\w{5,17}$/.test(username)){
		layer.tips('账号格式不正确！', '#username1', {
			tips : [ 2, '#076BAB' ]
		});
	}else if(password==""){
		layer.tips('密码不能为空！', '#password1', {
			tips : [ 2, '#076BAB' ]
		});
	}else if(password.length < 6 ||!/^[a-zA-Z]\w{5,17}$/.test(password)){
		layer.tips('密码格式不正确！', '#password1', {
			tips : [ 2, '#076BAB' ]
		});
	}else{
		$.ajax( {
			type : "post",
			url :$(".path").val()+"/login/frontLogin",
			dataType : "json",
			async: false,
			data : {
				"username":username,
				"password":password
			},
			success : function(data) {
				if(data=="1"){
					window.location.href=$(".path").val()+"/frontPatient/frontList";
				}else if(data=="2"){
					window.location.href=$(".path").val()+"/patientFront/frontPatientList";
				}
//				var i = layer.load(0);
//				alert(data.falg);
//				parent.layer.msg(data.falg, {icon : 1});
//				alert(data);
//				window.parent.document.getElementById("falg").value=data;
//				layer.close(i);
//				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
//				parent.layer.close(index);
			}
		});
	}
	
};
//表单提交验证
function LoginPwd2() {
		
	var username=$("#username2").val();
	var password=$("#password2").val();
	if(username==""){
		layer.tips('账号不能为空！', '#username2', {
			tips : [ 2, '#076BAB' ]
		});
	}else if(/^[a-zA-Z]\w{5,17}$/.test(username)){
		layer.tips('账号格式不正确！', '#username2', {
			tips : [ 2, '#076BAB' ]
		});
	}else if(password==""){
		layer.tips('密码不能为空！', '#password2', {
			tips : [ 2, '#076BAB' ]
		});
	}else if(password.length < 6 ||!/^[a-zA-Z]\w{5,17}$/.test(password)){
		layer.tips('密码格式不正确！', '#password2', {
			tips : [ 2, '#076BAB' ]
		});
	}else{
		$.ajax( {
			type : "post",
			url :$(".path").val()+"/login/frontLogin",
			dataType : "json",
			async: false,
			data : {
				"username":username,
				"password":password
			},
			success : function(data) {
				if(data=="1"){
					window.location.href=$(".path").val()+"/frontPatient/frontList";
				}else if(data=="2"){
					window.location.href=$(".path").val()+"/patientFront/frontPatientList";
				}
//				var i = layer.load(0);
//				alert(data.falg);
//				parent.layer.msg(data.falg, {icon : 1});
//				alert(data);
//				window.parent.document.getElementById("falg").value=data;
//				layer.close(i);
//				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
//				parent.layer.close(index);
			}
		});
	}
	
};