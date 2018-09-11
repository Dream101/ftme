$(function() {
	$("#register").click(function() {
		layer.open({
			type: 2,
			title: '登录',
			fix: false,
			skin: 'layui-layer-lan',
			shadeClose: true,
			shade: 0.6,
			area: ['600px', '30%'],
			content: $(".path").val()+'/portal/login.jsp',
			end: function() {
				var falg=$("#falg").val();//1:医生，2：患者
				if(falg=="1"){
					window.location.href=$(".path").val()+"/frontPatient/frontList";
				}else if(falg=="2"){
					window.location.href=$(".path").val()+"/patientFront/frontPatientList";
				}
			}
		});
	});
	$("#find").click( function() {
		layer.open({
			type: 2,
			title: '找回密码',
			fix: false,
			skin: 'layui-layer-lan',
			shadeClose: true,
			shade: 0.6,
			area: ['600px', '30%'],
			content: $(".path").val()+'/view/retrieve-pwd.jsp',
			end: function() {
			}
		});
	});
});