var up;
var next;
var allpages;
var current;
$(function() {
	patientinto();
});
//进入页面时查询数据
function patientinto(begin) {
	if(begin==null){
		begin=1;
	}
	
	$.ajax( {
		type : "post",
		url :$(".path").val() + "/patient/patientFileDel",
		dataType : "json",
		data : {
			"begin":begin,
		},
		success : function(data) {
			dataPut(data);
		}
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		patientinto(1);
	}});
	$("#myup").on({click: function() {
		patientinto(up);
	}});
	$("#mynext").on({click: function() {
		patientinto(next);
	}});
	$("#last").on({click: function() {
		patientinto(allpages);
	}});
	$("#zhan").on({change: function() {
		patientinto($(this).val());
	}});
};
/*员工-恢复*/
function patient_Restore(obj,id){
	layer.confirm('确认要恢复吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/patient/patientRestore",
			dataType:"json",
			data:{
				"userNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已恢复!',{icon:1,time:1000});
					patientinto(current);
				}else{
					layer.msg('恢复失败!',{icon:1,time:1000});
				}
				
				
			}
		});
		
	});
};

/*员工-彻底删除数据*/
function patient_Completely_Delete(obj,id){
	layer.confirm('确认要删除删除数据吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/patient/patientCompletelyDelete",
			dataType:"json",
			data:{
				"userNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除删除数据!',{icon:1,time:1000});
					patientinto(current);
				}else{
					layer.msg('删除删除数据失败!',{icon:1,time:1000});
				}
				
				
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
	$.each(data.list,function(index, user) {
		if(user.id>0){
			var sex=user.sex=="1"?"男":"女";
//			var emailState=patient_email(user.emailState);//邮箱状态
//			var accountState="<span class='btn btn-danger radius'>禁用</span>";//账号状态
						
		$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +user.id +"</td>" +
			"<td>" +user.userNo +"</td>" +
			"<td>" +user.roleType +"</td>" +
			"<td>" +user.hospitalname +"</td>" +
			"<td>" +user.doctorname +"</td>" +
			"<td>" +user.itemsname +"</td>" +
			"<td>" +user.testresultsname +"</td>" +
			"<td>" +user.username +"</td>" +
//			"<td>" +user.password +"</td>" +
			"<td>" +user.name +"</td>" +
			"<td>" +sex +"</td>" +
//			"<td>" +user.courierInfo +"</td>" +
//			"<td>" +user.email +"</td>" +
//			"<td>" +user.remark +"</td>" +
			"<td>" +user.datetime +"</td>" +
//			"<td>" +emailState +"</td>" +"<td>" +accountState+"</td>" +
			"<td class='f-14 td-manage'>" +
				"<span class='restore'><a style='text-decoration:none' id='"+user.userNo+"' class='patientRestore' href='javascript:;' title='恢复'><i class='Hui-iconfont'>&#xe66b;</i></a></span>" +
				"<span class='completelyDelete'><a style='text-decoration:none' id='"+user.userNo+"' class='patientCompletelyDelete' href='javascript:;' title='彻底删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span>" +
			"</td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//解除之前的绑定
	$("tbody").off("click",".patientRestore");
	$("tbody").off("click",".patientCompletelyDelete");
	//重新建立绑定
	$("tbody").on("click", ".patientRestore", function() {
		patient_Restore(this, $(this).attr("id"));
	});
	$("tbody").on("click", ".patientCompletelyDelete", function() {
		patient_Completely_Delete(this, $(this).attr("id"));
	});
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
/*员工-邮箱是激活*/
function patient_email(emailState){
	var clas="",type="";
	if(emailState=="0"){
		type="已激活";
		clas ="btn btn-success radius";
	}else{
		type="未激活";
		clas="btn disabled radius";
	}
	return "<span class='"+clas+"'>"+type+"</span>";
};