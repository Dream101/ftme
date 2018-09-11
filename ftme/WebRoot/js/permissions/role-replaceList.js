var up;
var next;
var allpages;
var current;
$(function() {
//	permissionsinto();
	permissions_list_affair();
});

//进入页面时查询数据
function permissionsinto(begin) {
	if(begin==null){
		begin=1;
	}
	$("thead").empty();
	$("tbody").empty();
	$(".fypage").empty();
	var userNo=$("#userNo").val() == "" ? " " : $("#userNo").val();
	var selname = $("#selname").val() == "" ? " " : $("#selname").val();
	var phone = $("#phone").val() == "" ? " " : $("#phone").val();
	var roleNo = $("#roleNo option:selected").val();
//	permissions_selclick_selname();
//	permissions_selclick_phone();
//	permissions_selclick_userNo();
	var urlpath=$(".path").val()+"/permissions/permissionsSelectFile";
//	if((roleNo!=-1||phone!=" "||selname!=" "||userNo!=" ")&&(userNo!="编号"||selname!="姓名"||phone!="手机号")){
//		urlpath=$(".path").val()+"/permissions/permissionsSelectFile";
//	}else{
//		
//	}
	if(permissions_selclick_selname()&&permissions_selclick_phone()&&permissions_selclick_userNo()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"name":selname,
				"phone":phone,
				"roleNo":roleNo,
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
function permissions_list_affair() {
	$("#butSel").click(function() {
		permissionsinto();
	});
	$("#selname").blur(function() {
		permissions_selclick_selname();
	});
	$("#phone").blur(function() {
		permissions_selclick_phone();
	});
	$("#userNo").blur(function() {
		permissions_selclick_userNo();
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		permissionsinto(1);
	}});
	$("#myup").on({click: function() {
		permissionsinto(up);
	}});
	$("#mynext").on({click: function() {
		permissionsinto(next);
	}});
	$("#last").on({click: function() {
		permissionsinto(allpages);
	}});
	$("#zhan").on({change: function() {
		permissionsinto($(this).val());
	}});
}
/*角色互换*/
function rolepermissions(obj,id,roleNo){
	layer.confirm(roleNo=="1"?'确认要提升到管理员吗？':'确认要降级到员工吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/permissions/roleReplace",
			dataType:"json",
			data:{
				"userNo":id,
				"roleNo":roleNo
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg(roleNo=="1"?'提升为管理员成功!':'降级到员工成功！',{icon:1,time:1000});
					permissionsinto();
				}else{
					layer.msg(roleNo=="1"?'提升为管理员失败！':'降级到员工失败！',{icon:1,time:1000});
				}
			}
		});
	});
};

//数据填充达页面上的方法
function dataPut(data){
	
	$("thead").append("<tr class='text-c'><th class='table-sort'  width='22px'>ID</th><th>用户编号</th><th>角色</th><th >姓名</th><th >手机号</th><th >操作</th></tr>");
	var roleNo="";
	var k=0;
	$.each(data.list,function(index, user) {
		if(user.id>0){
			var replace="";
			roleNo=user.roleNo;
			if(user.roleNo=="1"){
				replace="<span class='btn btn-warning radius'> <a style='text-decoration:none' id='"+user.userNo+"' class='permissionsReplace' href='javascript:;' title='降职'>降职</a></span>";
			}else if(user.roleNo=="2"){
				replace="<span class='btn btn-secondary radius'> <a style='text-decoration:none' id='"+user.userNo+"' class='permissionsReplace' href='javascript:;' title='升职'>升职</a></span>";
			}
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
	//接触之前的绑定
	$("tbody").off("click",".permissionsReplace");
	//重新建立绑定
	$("tbody").on("click", ".permissionsReplace", function() {
		if(roleNo=="1"){
			rolepermissions(this, $(this).attr("id"),"2");
		}else{
			rolepermissions(this, $(this).attr("id"),"1");
		}
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

//验证编号
function permissions_selclick_userNo(){
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
function permissions_selclick_phone(){
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
function permissions_selclick_selname(){
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

/*患者-审核*/
function permissions_account(accountState){
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