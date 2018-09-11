var up;
var next;
var allpages;
var current;
$(function() {
	patientinto();
	patient_list_affair();
});

//进入页面时查询数据
function patientinto(begin) {
	if(begin==null){
		begin=1;
	}
//	alert(11);
//	alert(document.getElementById("selname").value == "模糊查询姓名,手机号,编号")
//	alert(document.getElementById("selname").value);
//	alert($("#selname").val() == "");
	
	var selname = ($("#selname").val()=="姓名,手机号,编号"||$("#selname").val() == "")? " " : $("#selname").val();
//	alert("selname-="+selname);
//	var phone = $("#phone").val() == "" ? " " : $("#phone").val();
	var hospitalNo = $("#hospitalNo option:selected").val();
	var uno = $("#uno option:selected").val();
//	var userNo = $("#userNo option:selected").val();
	var itemsname = $("#itemsname option:selected").val();
	var testresultsname = $("#testresultsname option:selected").val();
	var datetime1 = $("#datetime1").val() == ""? " " : $("#datetime1").val();
	var datetime2 = $("#datetime2").val() == ""? " " : $("#datetime2").val();
//	var accountState = $("#accountState option:selected").val();
//	patient_selclick_selname();
//	patient_selclick_phone();
	var urlpath="";
	if((selname!=" "||hospitalNo!=-1||uno!=-1||itemsname!=-1||testresultsname!=-1||datetime1!=" "||datetime2!=" ")&&selname!="姓名,手机号,编号"){
		urlpath=$(".path").val()+"/patient/patientSelectFile";
	}else{
		urlpath= $(".path").val() + "/patient/patientFile";
	}
	
//			"phone":phone,
	if(patient_selclick_selname()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"uno":uno,
				"hospitalNo":hospitalNo,
				"name":selname,
				"accountState":"1",
				"testresultsname":testresultsname,
				"itemsname":itemsname,
				"datetime1":datetime1,
				"datetime2":datetime2
			},
			success : function(data) {
				dataPut(data);
			}
		});
	}
};
//各种事件绑定
function patient_list_affair() {
	$("#butSel").click(function() {
		patientinto();
	});
//	$("#userNo").blur(function() {
//	});
	$("#hospitalNo").change(function() {
		var hospitalNo=$("#hospitalNo option:selected").val();
		var urlpath=$(".path").val()+"/patient/doctorFineAll";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"hospitalNo":hospitalNo,
			},
			success : function(data) {
				$("#uno").empty();
				$(".searchable-select").eq(0).remove();
				if(data.count=="0"){
					if(hospitalNo==-1){
						$("#uno").append("<option value='-1'>---请选择医生---</option>");
					}else{
						$("#uno").append("<option value='-1'>该医院未添加医生</option>");				
					}
				}else{
					$("#uno").append("<option value='-1'>---请选择医生---</option>");	
					$.each(data.list,function(index, user) {
						$("#uno").append("<option value='"+user.userNo+"'>"+user.name+"</option>");
					});
					$("#uno").searchableSelect();
				}
			}
		});
//		$("#userNo").empty();
//		$("#userNo").append("<option value='-1'>---请选择患者---</option>");	
	});
//	$("#uno").change(function() {
//		$("#userNo").empty();
//		$("#userNo").append("<option value='-1'>---请选择患者---</option>");	
//		var uno=$("#uno option:selected").val();
//		var urlpath=$(".path").val()+"/patient/patientFineAll";
//		$.ajax( {
//			type : "post",
//			url :urlpath,
//			dataType : "json",
//			data : {
//				"uno":uno,
//			},
//			success : function(data) {
//				$("#userNo").empty();
//				if(data.count=="0"){
//					if(uno==-1){
//						$("#userNo").append("<option value='-1'>---请选择患者---</option>");	
//					}else{
//						$("#userNo").append("<option value='-1'>该医生未添加患者</option>");			
//					}
//								
//				}else{
//					$("#userNo").append("<option value='-1'>---请选择患者---</option>");	
//					$.each(data.list,function(index, user) {
//						$("#userNo").append("<option value='"+user.userNo+"'>"+user.name+"</option>");
//					});
//				}
//			}
//		});
//	});
	$("#selname").blur(function() {
		patient_selclick_selname();
	});
//	$("#phone").blur(function() {
//		patient_selclick_phone();
//	});
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
}
/*患者-删除*/
function patient_Del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/patient/patientDelete",
			dataType:"json",
			data:{
				"userNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					patientinto(current);
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
//			var emailState=patient_email(user.emailState);//邮箱状态
//			var accountState=patient_account(user.accountState);//账号状态
//			var shenpi="";
//			if(user.accountState=="0"){
//				shenpi="<span class='btn btn-secondary radius'>审批</span>";
//			}
		$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +user.id +"</td>" +
			"<td>" +user.userNo +"</td>" +
			"<td>" +user.roleType +"</td>" +
			"<td>" +user.hospitalname +"</td>" +
			"<td>" +user.doctorname +"</td>" +
			"<td>" +user.clinicalname +"</td>" +
			"<td>" +user.itemsname +"</td>" +
			"<td>" +user.testresultsname +"</td>" +
			"<td>" +user.username +"</td>" +
//			"<td>" +user.password +"</td>" +
			"<td>" +user.name +"</td>" +
			"<td>" +sex +"</td>" +
			//"<td>" +user.courierInfo +"</td>" +
//			"<td>" +user.email +"</td>" +
//			"<td>" +user.remark +"</td>" +
			"<td>" +user.datetime +"</td>" +
//			"<td>" +emailState +"</td>" +"<td>" +accountState+"</td>" +
			"<td class='f-14 td-manage'><span class='detail'><a style='text-decoration:none' class='patientUserDetail' href='"+$(".path").val()+"/patient/patientByIdDetail?userNo="+user.userNo+"' title='详情'><i class='Hui-iconfont'>详情</i></a></span><span class='upp'><a style='text-decoration:none' class='patientUserUpp' href='"+$(".path").val()+"/patient/patientById?userNo="+user.userNo+"' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a></span> <span class='del'><a style='text-decoration:none' id='"+user.userNo+"' class='patientUserDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".patientUserDel");
	//重新建立绑定
	$("tbody").on("click", ".patientUserDel", function() {
		patient_Del(this, $(this).attr("id"));
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
function patient_selclick_phone(){
	var phone = $("#phone").val();
	var result = /^(1[0-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(phone);
	if (!result&&phone!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('手机号码格式不正确！', '#phone', {
			tips : [ 2, '#076BAB' ]
		});
	} else{
//		$("#butSel").removeAttr("disabled");
	}
};

//模糊匹配编号，姓名，手机号   ^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$
function patient_selclick_selname(){
	var selname = $("#selname").val();
	if(selname=="姓名,手机号,编号"){
		return true;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(selname);
	if (!result&&selname!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('不能包含特殊符号！', '#selname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};

///*患者-审核*/
//function patient_account(accountState){
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
/*患者-邮箱是激活*/
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