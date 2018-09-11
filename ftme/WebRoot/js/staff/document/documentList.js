var up;
var next;
var allpages;
var current;
$(function() {
	documentinto(1);
	document_list_affair();
});

//进入页面时查询数据
function documentinto(begin) {
	if(begin==null){
		begin=1;
	}
//	var userNo = $("#userNo option:selected").val();// userNo 患者的id（用户表userinfo）
	var hospitalNo = $("#hospitalNo option:selected").val();// hospitalNo 所属医院    
	var documentType = $("#documentType option:selected").val();// documentType 文档类别(遗传诊断报告,基因的测序结果,诊断病例分析)
	var phone = ($("#phone").val()=="用户编号,姓名,手机号"||$("#phone").val() == "")? " " : $("#phone").val();
//	alert(userNo+"  "+hospitalNo+"  "+documentType+"  "+phone);
	var urlpath
	if((documentType!="-1"||phone!=" "||hospitalNo!="-1"||phone!=" ")&&(phone!="用户编号,姓名,手机号")){
		urlpath=$(".path").val()+"/document/documentFindSel";
	}else{
		urlpath=$(".path").val()+"/document/documentFindAll";
	}
	if(document_selclick_phone()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
//				"userNo":userNo,
				"hospitalNo":hospitalNo,
				"phone":phone,
				"documentType":documentType
			},
			success : function(data) {
//				alert("count="+data.count);
				dataPut(data);
			}
		});
	}
};
//各种事件绑定
function document_list_affair() {
	$("#phone").blur(function() {
		document_selclick_phone();
	});
//	$("#hospitalNo").change(function() {
//		patientList();
//	});
	$("#butSel").click(function() {
		documentinto();
	});
};
////二级联动
//function patientList(){
//	var hospitalNo=$("#hospitalNo").val();
//	var urlpath=$(".path").val()+"/document/patientFineAll";
//	$.ajax( {
//		type : "post",
//		url :urlpath,
//		dataType : "json",
////		async: false,
//		data : {
//			"hospitalNo":hospitalNo,
//		},
//		success : function(data) {
//			$("#userNo").empty();
//			if(data.count=="0"){
//				$("#userNo").append("<option value='-1'>---请选择患者---</option>");				
//			}else{
//				$.each(data.list,function(index, user) {
//					$("#userNo").append("<option value='"+user.userNo+"'>"+user.name+"</option>");
//				});
//			}
//		}
//	});
//};

//分页
function fenye(){
	$("#first").on({click: function() {
		documentinto(1);
	}});
	$("#myup").on({click: function() {
		documentinto(up);
	}});
	$("#mynext").on({click: function() {
		documentinto(next);
	}});
	$("#last").on({click: function() {
		documentinto(allpages);
	}});
	$("#zhan").on({change: function() {
		documentinto($(this).val());
	}});
}
/*员工-删除*/
function document_Del(obj,documentNo){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/document/documentDelete",
			dataType:"json",
			data:{
				"documentNo":documentNo,
			},
			success:function(data){
				if(data){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					documentinto(current);
				}else{
					layer.msg('删除失败!',{icon:1,time:1000});
				}
			}
		});
	});
};
/*员工-删除*/
function document_XZ(obj,documentPath){
	layer.confirm('确认要下载吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/document/download",
			dataType:"json",
			data:{
				"documentPath":documentPath,
			},
			success:function(data){
//				alert(daat);
//				if(data){
//					$(obj).parents("tr").remove();
//					layer.msg('已删除!',{icon:1,time:1000});
//					documentinto(current);
//				}else{
//					layer.msg('删除失败!',{icon:1,time:1000});
//				}
			}
		});
	});
};
//数据填充达页面上的方法
function dataPut(data){
	$("tbody").empty();
	$(".r strong").empty();
	$(".fypage").empty();
	$(".r strong").text(data.count);
	var k=0;
	$.each(data.list,function(index, document) {
		if(document.id>0){
			
		$("tbody").append(

			"<tr class='text-c'>" +
			"<td>" +document.id +"</td>" +
			"<td>" +document.userNo +"</td>" +
			"<td>" +document.hospitalname +"</td>" +
			"<td>" +document.name +"</td>" +
			"<td>" +document.phone +"</td>" +
			"<td>" +document.documentType +"</td>" +
			"<td>" +document.documentName +"</td>" +
//			"<td>" +document.documentPath +"</td>" +
//			"<td>" +document.admin +"</td>" +
			"<td>" +document.remark +"</td>" +
//			"<td>" +document.datetime +"</td>" +<a style='text-decoration:none' class='documentXz' href='"+$(".path").val()+document.documentPath+"'   title='下载'><i class='Hui-iconfont'>&#xe640;</i></a> 
			"<td class='f-14 td-manage'><a style='text-decoration:none' class='documentXz' href='"+$(".path").val()+"/document/download?uploadPath="+document.uploadPath+"'   title='下载'><i class='Hui-iconfont'>&#xe640;</i></a><a style='text-decoration:none' id='"+document.documentNo+"' class='documentDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	
//	alert(k)
	//接触之前的绑定
	$("tbody").off("click",".documentDel");
//	$("tbody").off("click",".documentXz");
	//重新建立绑定
	$("tbody").on("click", ".documentDel", function() {
		document_Del(this, $(this).attr("id"));
	} );
//	$("tbody").on("click", ".documentXz", function() {
//		document_XZ(this, $(this).attr("id"));
//	} );
	var fy=data.fy;
	if(fy!=null&&k>0){
		up=fy.up;
		next=fy.next;
		allpages=fy.allpages;
		current=fy.current;
		//加尾巴
		$(".fypage").append("<span class=r>当前第"+current+"页共有"+ allpages+ "页&nbsp;&nbsp;<a href='#' id='first' class='btn btn-success radius' >首页</a>&nbsp;&nbsp;<a href='#' id='myup' class='btn btn-success radius'>上一页</a>&nbsp;&nbsp;<a href='#' id='mynext' class='btn btn-success radius'>下一页</a>&nbsp;&nbsp;<a href='#' id='last' class='btn btn-success radius'	>尾页</a>&nbsp;&nbsp;<select name='zhan' id='zhan'style='width: 40px; height: 28px;'></select></span>");
		//给下拉框赋值
		for ( var i = 1; i <= allpages; i++) {
			$("#zhan").append("<option value=" + i + ">" + i + "</option>");
		}
		//你当前正处在哪一页，就应该让几选中
		$("#zhan option:eq(" + (parseInt(current) - 1) + ")").attr('selected', true);
		fenye();
		rightrole();
	}
};

//验证手机号
function document_selclick_phone(){
	var phone = $("#phone").val();
	
	if(phone=="用户编号,姓名,手机号"){
		return true;
	}
	
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(phone)
	if (!result&&phone!="") {
		layer.tips('不能包含特殊符号！', '#phone', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else{
		return true;
	}
};