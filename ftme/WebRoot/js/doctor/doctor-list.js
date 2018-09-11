var up;
var next;
var allpages;
var current;
$(function() {
	doctorinto();
	doctor_list_affair();
});

//进入页面时查询数据
function doctorinto(begin) {
	if(begin==null){
		begin=1;
	}
	var hospitalNo = $("#hospitalNo option:selected").val();
	var userNo = $("#userNo option:selected").val();
	var selname = ($("#selname").val()=="医生姓名"||$("#selname").val() == "")? " " : $("#selname").val();
	var phone = ($("#phone").val()=="医生手机号"||$("#phone").val() == "")? " " : $("#phone").val();
	var accountState = $("#accountState option:selected").val();
//	doctor_selclick_selname();
//	doctor_selclick_phone();
	var urlpath="";
	if((accountState!=-1||phone!=" "||selname!=" "||userNo!=-1||hospitalNo!=-1)&&(phone!="医生手机号"||selname!="医生姓名")){
		urlpath=$(".path").val()+"/doctor/doctorSelectFile";
	}else{
		urlpath= $(".path").val() + "/doctor/doctorFile";
	}
	if(doctor_selclick_selname()&&doctor_selclick_phone()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"hospitalNo":hospitalNo,
				"userNo":userNo,
				"name":selname,
				"phone":phone,
				"accountState":accountState
			},
			success : function(data) {
				dataPut(data);
			}
		});
	}
};
//各种事件绑定
function doctor_list_affair() {
	$("#butSel").click(function() {
		doctorinto();
	});
	$("#hospitalNo").change(function() {
		doctorList();
	});
	$("#selname").blur(function() {
		doctor_selclick_selname();
	});
	$("#phone").blur(function() {
		doctor_selclick_phone();
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
//二级联动
function doctorList(){
	var hospitalNo=$("#hospitalNo").val();
	var urlpath=$(".path").val()+"/doctor/doctorFineAll";
	$.ajax( {
		type : "post",
		url :urlpath,
		dataType : "json",
//		async: false,
		data : {
			"hospitalNo":hospitalNo,
		},
		success : function(data) {
			$("#userNo").empty();
			$(".searchable-select").remove();
			if(data.count=="0"){
				$("#userNo").append("<option value='-1'>该医院未添加医生</option>");				
			}else{
				$("#userNo").append("<option value='-1'>---请选择医生---</option>");	
				$.each(data.list,function(index, user) {
					$("#userNo").append("<option value='"+user.userNo+"'>"+user.name+"</option>");
				});
				$("#userNo").searchableSelect();
			}
		}
	});
};
/*医生-删除*/
function doctor_Del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/doctor/doctorDelete",
			dataType:"json",
			data:{
				"userNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					doctorinto(current);
				}else{
					layer.msg('删除失败!',{icon:1,time:1000});
				}
			}
		});
	});
};

//数据填充达页面上的方法
function dataPut(data){
	var k=0;
	$("tbody").empty();
	$(".r strong").empty();
	$(".fypage").empty();
	$(".r strong").text(data.count);
	$.each(data.list,function(index, user) {
		if(user.id>0){
			var sex=user.sex=="1"?"男":"女";
			var emailState=doctor_email(user.emailState);//邮箱状态
			var accountState=doctor_account(user.accountState);//账号状态
			var shenpi="";
			if(user.accountState=="0"){
				shenpi="<span class='btn btn-secondary radius'>审批</span>";
			}
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
			"<td class='f-14 td-manage'><span class='review'><a style='text-decoration:none'  href='javascript:;'>"+shenpi+"</a></span><span class='upp'><a style='text-decoration:none' class='doctorUserUpp' href='"+$(".path").val()+"/doctor/doctorById?userNo="+user.userNo+"' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a></span> <span class='del'><a style='text-decoration:none' id='"+user.userNo+"' class='doctorUserDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".doctorUserDel");
	//重新建立绑定
	$("tbody").on("click", ".doctorUserDel", function() {
		doctor_Del(this, $(this).attr("id"));
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
function doctor_selclick_phone(){
	var phone = $("#phone").val();
	if(phone=="医生手机号"){
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
//验证医生姓名
function doctor_selclick_selname(){
	var selname = $("#selname").val();
	if(selname=="医生姓名"){
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

/*医生-审核*/
function doctor_account(accountState){
	var clas="",type="";
	if(accountState=="0"){
		type="不可用";
		clas ="btn btn-secondary radius";
	}else if(accountState=="1"){
		type="可用";
		clas="btn btn-success radius";
	}else if(accountState=="2"){
		type="不可用";
		clas="btn btn-danger radius";
	}
	return "<span class='"+clas+"'>"+type+"</span>";
};
/*医生-邮箱是激活*/
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