var up;
var next;
var allpages;
var current;
$(function() {
	governinto();
});
//进入页面时查询数据
function governinto(begin) {
	if(begin==null){
		begin=1;
	}
	
	$.ajax( {
		type : "post",
		url :$(".path").val() + "/govern/governFileDel",
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
		governinto(1);
	}});
	$("#myup").on({click: function() {
		governinto(up);
	}});
	$("#mynext").on({click: function() {
		governinto(next);
	}});
	$("#last").on({click: function() {
		governinto(allpages);
	}});
	$("#zhan").on({change: function() {
		governinto($(this).val());
	}});
};
/*员工-恢复*/
function govern_Restore(obj,id){
	layer.confirm('确认要恢复吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/govern/governRestore",
			dataType:"json",
			data:{
				"userNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已恢复!',{icon:1,time:1000});
					governinto(current);
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
//			var emailState=govern_email(user.emailState);//邮箱状态
//			var accountState="<span class='btn btn-danger radius'>禁用</span>";//账号状态
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +user.id +"</td>" +
			"<td>" +user.userNo +"</td>" +
			"<td>" +user.roleType +"</td>" +
			"<td>" +user.username +"</td>" +
			"<td>" +user.password +"</td>" +
			"<td>" +user.name +"</td>" +
			"<td>" +sex +"</td>" +
//			"<td>" +user.email +"</td>" +
			"<td>" +user.phone +"</td>" +
			"<td>" +user.datetime +"</td>" +
//			"<td>" +emailState +"</td>" +
//			"<td>" +accountState+"</td>" +
			"<td class='f-14 td-manage'><span class='restore'><a style='text-decoration:none' id='"+user.userNo+"' class='governRestore' href='javascript:;' title='恢复'><i class='Hui-iconfont'>&#xe66b;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
//	alert(k);
	//接触之前的绑定
	$("tbody").off("click",".governRestore");
	//重新建立绑定
	$("tbody").on("click", ".governRestore", function() {
		govern_Restore(this, $(this).attr("id"));
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
	}
	
};
