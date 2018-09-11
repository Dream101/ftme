var up;
var next;
var allpages;
var current;
$(function() {
	doctorinto();
});
//进入页面时查询数据
function doctorinto(begin) {
	if(begin==null){
		begin=1;
	}
	
	$.ajax( {
		type : "post",
		url :$(".path").val() + "/doctor/doctorFileDel",
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
		doctorinto(1);
	}});
	$("#myup").on({click: function() {
		doctorinto(up);
	}});
	$("#mynext").on({click: function() {
		doctorinto(next);
	}});
	$("#last").on({click: function() {
		doctorinto(allpages);
	}});
	$("#zhan").on({change: function() {
		doctorinto($(this).val());
	}});
};
/*员工-恢复*/
function doctor_Restore(obj,id){
	layer.confirm('确认要恢复吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/doctor/doctorRestore",
			dataType:"json",
			data:{
				"userNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已恢复!',{icon:1,time:1000});
					doctorinto(current);
				}else{
					layer.msg('恢复失败!',{icon:1,time:1000});
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
			var emailState=doctor_email(user.emailState);//邮箱状态
			var accountState="<span class='btn btn-danger radius'>禁用</span>";//账号状态
						
		$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +user.id +"</td>" +
			"<td>" +user.userNo +"</td>" +
			"<td>" +user.hospitalname +"</td>" +
			"<td>" +user.roleType +"</td>" +
			"<td>" +user.username +"</td>" +
			"<td>" +user.password +"</td>" +
			"<td>" +user.name +"</td>" +
			"<td>" +sex +"</td>" +
//			"<td>" +user.email +"</td>" +
			"<td>" +user.phone +"</td>" +
			"<td>" +user.datetime +"</td>" +
//			"<td>" +emailState +"</td>" +
			"<td>" +accountState+"</td>" +
			"<td class='f-14 td-manage'><span class='restore'><a style='text-decoration:none' id='"+user.userNo+"' class='doctorRestore' href='javascript:;' title='恢复'><i class='Hui-iconfont'>&#xe66b;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
//	alert(i);
	//接触之前的绑定
	$("tbody").off("click",".doctorRestore");
	//重新建立绑定
	$("tbody").on("click", ".doctorRestore", function() {
		doctor_Restore(this, $(this).attr("id"));
	} );
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
///*员工-审核*/
//function staff_account(accountState){
//	var clas="",type="";
//	if(accountState=="0"){
//		type="未审批";
//		clas ="btn btn-secondary radius";
//	}else if(accountState=="1"){
//		type="审批通过";
//		clas="btn btn-success radius";
//	}else if(accountState=="2"){
//		type="审批未通过";
//		clas="btn btn-danger radius";
//	}
//	return "<span class='"+clas+"'>"+type+"</span>";

//};
/*员工-邮箱是激活*/
function doctor_email(emailState){
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