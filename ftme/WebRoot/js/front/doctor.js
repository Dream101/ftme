var up;
var next;
var allpages;
var current;
$(function() {
	frontPatientinto();
	frontPatient_list_affair();
});
//进入页面时查询数据
function frontPatientinto(begin) {
	if(begin==null){
		begin=1;
	}
	var selname = ($("#selname").val()=="姓名,手机号,编号"||$("#selname").val() == "")? " " : $("#selname").val();
	var itemsname = $("#itemsname option:selected").val();
	var testresultsname = $("#testresultsname option:selected").val();
	var urlpath="";
	if((selname!=" "||itemsname!=-1||testresultsname!=-1)&&selname!="姓名,手机号,编号"){
		urlpath=$(".path").val()+"/frontPatient/frontSelectFile";
	}else{
		urlpath= $(".path").val() + "/frontPatient/frontFile";
	}
//	alert(urlpath);
	
//			"phone":phone,
	if(frontPatient_selclick_selname()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"uno":"-1",
				"hospitalNo":"-1",
				"name":selname,
				"accountState":"1",
				"testresultsname":testresultsname,
				"itemsname":itemsname,
				"datetime1":" ",
				"datetime2":" "
			},
			success : function(data) {
//				alert(data);
				dataPut(data);
			}
		});
	}
};
//各种事件绑定
function frontPatient_list_affair() {
	$("#butSel").click(function() {
		frontPatientinto();
	});
	$("#selname").blur(function() {
		frontPatient_selclick_selname();
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		frontPatientinto(1);
	}});
	$("#myup").on({click: function() {
		frontPatientinto(up);
	}});
	$("#mynext").on({click: function() {
		frontPatientinto(next);
	}});
	$("#last").on({click: function() {
		frontPatientinto(allpages);
	}});
	$("#zhan").on({change: function() {
		frontPatientinto($(this).val());
	}});
}
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
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +user.id +"</td>" +
			"<td>" +user.userNo +"</td>" +
			"<td>" +user.clinicalname +"</td>" +
			"<td>" +user.itemsname +"</td>" +
			"<td>" +user.testresultsname +"</td>" +
			"<td>" +user.name +"</td>" +
			"<td>" +sex +"</td>" +
			"<td class='f-14 td-manage'>" +
			"<span class='detail'><a style='text-decoration:none' class='frontByIdDetail' href='"+$(".path").val()+"/frontPatient/frontByIdDetail?userNo="+user.userNo+"' title='详情'><i class='Hui-iconfont'>详情</i></a></span>&nbsp;&nbsp;" +
			"<span class='frontPatientXZ'><a style='text-decoration:none' class='frontUpload' href='"+$(".path").val()+"/frontPatient/frontUpload?userNo="+user.userNo+"' title='上传'><i class='Hui-iconfont'>上传</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".frontPatientUserDel");
	//重新建立绑定
	$("tbody").on("click", ".frontPatientUserDel", function() {
		frontPatient_Del(this, $(this).attr("id"));
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
		//rightrole();
	}
};
//模糊匹配编号，姓名，手机号   ^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$
function frontPatient_selclick_selname(){
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