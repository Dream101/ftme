$(function() {
	retrievePwd_addlist_affair();
});
//各种事件绑定
function retrievePwd_addlist_affair() {
	$("#butSel").click(function() {
		retrievePwd();
	});
	$("#butSel3").click(function() {
		retrievePwd3();
	});
};
//表单提交验证
function retrievePwd() {
		
	var username=$("#username").val();
	var uname=$("#uname").val();
	if(username==""){
		layer.tips('账号不能为空！', '#username', {
			tips : [ 2, '#076BAB' ]
		});
	}else if(uname==""){
		///^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(uname)
		layer.tips('姓名不能为空！', '#uname', {
			tips : [ 2, '#076BAB' ]
		});
	}else{
		$.ajax( {
			type : "post",
			url :$(".path").val()+"/userinfo/retrievePwd",
			dataType : "json",
			async: false,
			data : {
				"username":username,
				"name":uname
			},
			success : function(data) {
//				var i = layer.load(0);
//				alert(data.falg);
				parent.layer.msg(data.falg, {icon : 1});
//				layer.close(i);
//				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
//				parent.layer.close(index);
			}
		});
	}
	
};
//表单提交验证
function retrievePwd3() {
		
	var username=$("#username3").val();
	var uname=$("#uname3").val();
	if(username==""){
		layer.tips('账号不能为空！', '#username3', {
			tips : [ 2, '#076BAB' ]
		});
	}else if(uname==""){
		///^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(uname)
		layer.tips('姓名不能为空！', '#uname3', {
			tips : [ 2, '#076BAB' ]
		});
	}else{
		$.ajax( {
			type : "post",
			url :$(".path").val()+"/userinfo/retrievePwd",
			dataType : "json",
			async: false,
			data : {
				"username":username,
				"name":uname
			},
			success : function(data) {
//				var i = layer.load(0);
//				alert(data.falg);
				parent.layer.msg(data.falg, {icon : 1});
//				layer.close(i);
//				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
//				parent.layer.close(index);
			}
		});
	}
	
};