
var up;
var next;
var allpages;
var current;
$(function() {
	staffinto();
});

//进入页面时查询数据
function staffinto(begin) {
	if(begin==null){
		begin=1;
	}
	
	$.ajax( {
		type : "post",
		url :$(".path").val() + "/staff/staffFileDel",
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
		staffinto(1);
	}});
	$("#myup").on({click: function() {
		staffinto(up);
	}});
	$("#mynext").on({click: function() {
		staffinto(next);
	}});
	$("#last").on({click: function() {
		staffinto(allpages);
	}});
	$("#zhan").on({change: function() {
		staffinto($(this).val());
	}});
}

/*员工-恢复*/
function staff_Restore(obj,id){
	layer.confirm('确认要恢复吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/staff/staffRestore",
			dataType:"json",
			data:{
				"userNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已恢复!',{icon:1,time:1000});
					staffinto(current);
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
//			var emailState=staff_email(user.emailState);//邮箱状态
			var accountState="<span class='btn btn-danger radius'>禁用</span>";//账号状态
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
			"<td>" +accountState+"</td>" +
			"<td class='f-14 td-manage'><span class='restore'><a style='text-decoration:none' id='"+user.userNo+"' class='staffRestore' href='javascript:;' title='恢复'><i class='Hui-iconfont'>&#xe66b;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".staffRestore");
	//重新建立绑定
	$("tbody").on("click", ".staffRestore", function() {
		staff_Restore(this, $(this).attr("id"));
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

//验证手机号
function staff_selclick_phone(){
	var phone = $("#phone").val();
	var result = /^(1[0-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(phone);
	if (!result&&phone!="") {
		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('手机号码格式不正确！', '#phone', {
			tips : [ 2, '#076BAB' ]
		});
	} else{
		$("#butSel").removeAttr("disabled");
	}
};
//验证员工编号
function staff_selclick_userNo(){
	var userNo = $("#userNo").val();
	var result =/^[A-Za-z0-9]+$/.test(userNo);
	if (!result&&userNo!="") {
		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('编号格式不正确！', '#userNo', {
			tips : [ 2, '#076BAB' ]
		});
	}else{
		$("#butSel").removeAttr("disabled");
	}
};
//验证员工姓名
function staff_selclick_selname(){
	var selname = $("#selname").val();
	var result = /^[\u4e00-\u9fa5]{0,}$/.test(selname)
	if (!result) {
		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('姓名只能是汉字！', '#selname', {
			tips : [ 2, '#076BAB' ]
		});
	}else{
		$("#butSel").removeAttr("disabled");
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
function staff_email(emailState){
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

////根据条件查询
//function selectStaff() {
//	var userNo = $("#userNo").val() == "" ? " " : $("#userNo").val();
//	var selname = $("#selname").val() == "" ? " " : $("#selname").val();
//	var phone = $("#phone").val() == "" ? " " : $("#phone").val();
//	var accountState = $("#accountState option:selected").val();
//	staff_selclick_userNo();
//	staff_selclick_selname();
//	staff_selclick_phone();
//	//	alert(userNo+selname+phone+accountState);
//	$.ajax({
//		type:"post",
//		url:$(".path").val()+"/staff/staffSelectFile",
//		dataType:"json",
//		data:{
//			"userNo":userNo,
//			"name":selname,
//			"phone":phone,
//			"accountState":accountState,
//		},
//		success:function(data){
//			dataPut(data);
//		}
//	});
//};


/*$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],默认第几个排序	"bStateSave": true,状态保存	"aoColumnDefs": [
	  {"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示	  {"orderable":false,"aTargets":[0,8]} 不参与排序的列	]
});*/
/*员工-添加*/
function article_add(title,url,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
};
/*员工-编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
};
/*员工-删除*/
function article_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
};