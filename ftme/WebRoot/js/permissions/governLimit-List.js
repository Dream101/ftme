var up;
var next;
var allpages;
var current;
var userNo="";
$(function() {
	governLimitinto();
	governLimit_list_affair();
});

//进入页面时查询数据
function governLimitinto(begin) {
	if(begin==null){
		begin=1;
	}
	$("thead").empty();
	$("tbody").empty();
	$(".fypage").empty();
	var userNo=$("#userNo").val() == "" ? " " : $("#userNo").val();
	var selname = $("#selname").val() == "" ? " " : $("#selname").val();
	var phone = $("#phone").val() == "" ? " " : $("#phone").val();
	governLimit_selclick_selname();
	governLimit_selclick_phone();
	governLimit_selclick_userNo();
	var urlpath="";
	if((phone!=" "||selname!=" "||userNo!=" ")&&(userNo!="编号"||selname!="姓名"||phone!="手机号")){
		urlpath=$(".path").val()+"/permissions/permissionsSelectFile";
	}else{
		urlpath= $(".path").val() + "/govern/governFile";
	}
	if(governLimit_selclick_selname()&&governLimit_selclick_phone()&&governLimit_selclick_userNo()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"name":selname,
				"phone":phone,
				"roleNo":1,
				"userNo":userNo,
				"accountState":1
			},
			success : function(data) {
				dataPut(data);
			}
		});
	}
	
};
//各种事件绑定
function governLimit_list_affair() {
	$("#butSel").click(function() {
		governLimitinto();
	});
	$("#selname").blur(function() {
		governLimit_selclick_selname();
	});
	$("#phone").blur(function() {
		governLimit_selclick_phone();
	});
	$("#userNo").blur(function() {
		governLimit_selclick_userNo();
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		governLimitinto(1);
	}});
	$("#myup").on({click: function() {
		governLimitinto(up);
	}});
	$("#mynext").on({click: function() {
		governLimitinto(next);
	}});
	$("#last").on({click: function() {
		governLimitinto(allpages);
	}});
	$("#zhan").on({change: function() {
		governLimitinto($(this).val());
	}});
}
//数据填充达页面上的方法
function dataPut(data){
	$("thead").append("<tr class='text-c'><th class='table-sort'  width='22px'>ID</th><th>用户编号</th><th>角色</th><th >姓名</th><th >手机号</th><th >操作</th></tr>");
	var roleNo="";
	var k=0;
	$.each(data.list,function(index, user) {
		if(user.id>0){//<i class='Hui-iconfont'>&#xe61d;</i>
			var replace="<a style='text-decoration:none'  class='governLimit' href='"+$(".path").val()+"/permissions/governLimit?userNo="+user.userNo+"' title='权限设置'>权限设置</a>";
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +user.id +"</td>" +
			"<td>" +user.userNo +"</td>" +
			"<td>" +user.roleType +"</td>" +
			"<td>" +user.name +"</td>" +
			"<td>" +user.phone +"</td>" +
			"<td class='f-14 td-manage'>"+replace+"</td>"+
			"</tr>");
			k=k+1;
		}else{
		}
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
	}
};

//验证编号
function governLimit_selclick_userNo(){
	var userNo = $("#userNo").val();
	if(userNo=="编号"){
		return true;
	}
	var result =/^[0-9]*$/.test(userNo);
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

//验证手机号
function governLimit_selclick_phone(){
	var phone = $("#phone").val();
	if(phone=="手机号"){
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

//验证患者姓名
function governLimit_selclick_selname(){
	var selname = $("#selname").val();
	if(selname=="姓名"){
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
