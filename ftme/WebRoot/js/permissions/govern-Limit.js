$(function(){
//	$(".ckbox").removeAttr("checked");
	$(".ckbox").click(function(){
		
		var ck="";
		if($(this).val()=="false"){
			$(this).val("true");
			ck="add";
	    }else{
	    	$(this).val("false");
	        ck="del";
	    }
//		alert(ck+"   "+$(this).val());
		$.ajax( {
			type : "post",
			url :$(".path").val()+"/permissions/permissionsSaveOrDel",
			dataType : "json",
			data : {
				"userNo":$(".userNo").val(),
				"permissionsidNo":$(this).attr("id"),
				"type":ck,
				"superior":$(this).attr("name")
			},
			success : function(data) {
//				alert(data);
			}
		});
	});
});
//页面加载后进行将之前选中的权限选中
function loaded(){
	
	//判断是否之前已经选过的
	$.ajax( {
		type : "post",
		url :$(".path").val()+"/permissions/userinfoPermissionsHierarchy",
		dataType : "json",
		data : {
			"userNo":$(".userNo").val()
		},
		success : function(data) {
			$.each(data,function(index, rolepermi) {
//				alert("#"+rolepermi.superior+"  #"+rolepermi.permissionsidNo);
				$("#"+rolepermi.superior+" #"+rolepermi.permissionsidNo).attr("checked" ,"true");
				$("#"+rolepermi.superior+" #"+rolepermi.permissionsidNo).val("true");  
			});
		}
	});
};