var roleNo;
$(function(){
	rightrole();
//	alert("function="+roleNo=="0");
	if(!roleNo){
		$(".add").hide();
		$(".upp").hide();
		$(".del").hide();
		$(".restore").hide();
		$(".completelyDelete").hide();
		$(".documentXz").hide();
		$(".patientXz").hide();
		$(".documentDel").hide();
		$(".detail").hide();
	}
	
//	$("#butsub").hide();
	
});

function rightrole(){
	$.ajax( {
		type : "post",
		url :$(".path").val()+"/permissions/perRoleNo",
		dataType : "json",
		async: false,
		success : function(data) {
			roleNo=data;
//			alert("ajax="+roleNo);
		}
	});
	if(!roleNo){
		$(".add").hide();
		$(".upp").hide();
		$(".del").hide();
		$(".restore").hide();
		$(".completelyDelete").hide();
		$(".documentXz").hide();
		$(".patientXz").hide();
		$(".documentDel").hide();
		$(".detail").hide();
		$.ajax( {
			type : "post",
			url :$(".path").val()+"/permissions/permissionsPageButton",
			dataType : "json",
			data : {
			},
			success : function(data) {
				$.each(data.list,function(index, rolepermi) {
//					alert("."+rolepermi.permissionsidNo);
					$("."+rolepermi.permissionsidNo).show();
				});
			}
		});
	}
	
	
//	$("#butsub").hide();
//	$(".add .upp .del .restore .documentXz .documentDel .detail").hide();
//	alert("走了");
};