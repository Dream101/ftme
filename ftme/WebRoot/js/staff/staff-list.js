var up;
var next;
var allpages;
var current;
$(function() {
	staffinto();
	staff_list_affair();
});

//进入页面时查询数据
function staffinto(begin) {
	if(begin==null){
		begin=1;
	}
	var userNo = ($("#userNo").val()=="员工编号"||$("#userNo").val() == "")? " " : $("#userNo").val();
	var selname = ($("#selname").val()=="员工姓名"||$("#selname").val() == "")? " " : $("#selname").val();
	var phone = ($("#phone").val()=="员工手机号"||$("#phone").val() == "")? " " : $("#phone").val();
//	var accountState = -1;
//	staff_selclick_userNo();
//	staff_selclick_selname();
//	staff_selclick_phone();
	var urlpath="";
	if((phone!=" "||selname!=" "||userNo!=" ")&&(userNo!="员工编号"||selname!="员工姓名"||phone!="员工手机号")){
		urlpath=$(".path").val()+"/staff/staffSelectFile";
	}else{
		urlpath= $(".path").val() + "/staff/staffFile";
	}
	if(staff_selclick_userNo()&&staff_selclick_phone()&&staff_selclick_selname()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"userNo":userNo,
				"name":selname,
				"phone":phone,
				"accountState":-1
			},
			success : function(data) {
				dataPut(data);
			}
		});
	}
};
//各种事件绑定
function staff_list_affair() {
	$("#butSel").click(function() {
		staffinto();
	});
	$("#userNo").blur(function() {
		staff_selclick_userNo();
	});
	$("#selname").blur(function() {
		staff_selclick_selname();
	});
	$("#phone").blur(function() {
		staff_selclick_phone();
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
/*员工-删除*/
function staff_Del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/staff/staffDelete",
			dataType:"json",
			data:{
				"userNo":id
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					staffinto(current);
				}else{
					layer.msg('删除失败!',{icon:1,time:1000});
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
//			var accountState=staff_account(user.accountState);//账号状态
//			var shenpi="";
//			if(user.accountState=="0"){
//				shenpi="<span class='btn btn-secondary radius'>审批</span>";
//			}
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
//			"<td>" +emailState +"</td>" + "<td>" +accountState+"</td>" +<a style='text-decoration:none'  href='javascript:;'>"+shenpi+"</a>
			"<td class='f-14 td-manage'><span class='upp'><a style='text-decoration:none' class='staffUserUpp' href='"+$(".path").val()+"/staff/staffById?userNo="+user.userNo+"' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a></span> <span class='del'><a style='text-decoration:none' id='"+user.userNo+"' class='staffUserDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".staffUserDel");
	//重新建立绑定
	$("tbody").on("click", ".staffUserDel", function() {
		staff_Del(this, $(this).attr("id"));
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
	if(phone=="员工手机号"){
		return true;
	}
	var result = /^(1[0-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(phone);
	if (!result&&phone!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('手机号码格式不正确！', '#phone', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};
//验证员工编号
function staff_selclick_userNo(){
	var userNo = $("#userNo").val();
	if(userNo=="员工编号"){
		return true;
	}
	var result =/^[A-Za-z0-9]+$/.test(userNo);
	if (!result&&userNo!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('编号格式不正确！', '#userNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};
//验证员工姓名
function staff_selclick_selname(){
	var selname = $("#selname").val();
	if(selname=="员工姓名"){
		return true;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(selname);
	if (!result&&selname!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('姓名格式不对！', '#selname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};

/*员工-审核*/
function staff_account(accountState){
	var clas="",type="";
	if(accountState=="0"){
		type="未审批";
		clas ="btn btn-secondary radius";
	}else if(accountState=="1"){
		type="审批通过";
		clas="btn btn-success radius";
	}else if(accountState=="2"){
		type="审批未通过";
		clas="btn btn-danger radius";
	}
	return "<span class='"+clas+"'>"+type+"</span>";
};
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