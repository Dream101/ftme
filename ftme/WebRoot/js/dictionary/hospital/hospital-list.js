var up;
var next;
var allpages;
var current;
$(function() {
	hospitalinto();
	hospital_list_affair();
});

//进入页面时查询数据
function hospitalinto(begin) {
	if(begin==null){
		begin=1;
	}
	var hospitalNo = $("#hospitalNo").val() == "" ? " " : $("#hospitalNo").val();
	var hospitalname = $("#hospitalname").val() == "" ? " " : $("#hospitalname").val();
//	hospital_selclick_hospitalNo();
//	hospital_selclick_hospitalname();
	var urlpath="";
	if((hospitalname!=" "||hospitalNo!=" ")&&(hospitalNo!="医院编号"||hospitalname!="医院名称")){
		urlpath=$(".path").val()+"/hospital/hospitalSelectFile";
	}else{
		urlpath= $(".path").val() + "/hospital/hospitalFile";
	}
	if(hospital_selclick_hospitalNo()&&hospital_selclick_hospitalname()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"hospitalNo":hospitalNo,
				"hospitalname":hospitalname,
			},
			success : function(data) {
				dataPut(data);
			}
		});
	}
};
//各种事件绑定
function hospital_list_affair() {
	$("#butSel").click(function() {
		hospitalinto();
	});
	$("#hospitalNo").blur(function() {
		hospital_selclick_hospitalNo();
	});
	$("#hospitalname").blur(function() {
		hospital_selclick_hospitalname();
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		hospitalinto(1);
	}});
	$("#myup").on({click: function() {
		hospitalinto(up);
	}});
	$("#mynext").on({click: function() {
		hospitalinto(next);
	}});
	$("#last").on({click: function() {
		hospitalinto(allpages);
	}});
	$("#zhan").on({change: function() {
		hospitalinto($(this).val());
	}});
}
/*医院-删除*/
function hospital_Del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/hospital/hospitalDelete",
			dataType:"json",
			data:{
				"hospitalNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					hospitalinto(current);
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
	$.each(data.list,function(index,  hospital) {
		if(hospital.id>0){
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +hospital.id +"</td>" +
			"<td>" +hospital.hospitalNo +"</td>" +
			"<td>" +hospital.hospitalname +"</td>" +
			"<td class='f-14 td-manage'><span class='upp'><a style='text-decoration:none' class='hospitalhospitalUpp' href='"+$(".path").val()+"/hospital/hospitalById?hospitalNo="+hospital.hospitalNo+"' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a></span> <span class='del'><a style='text-decoration:none' id='"+hospital.hospitalNo+"' class='hospitalhospitalDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".hospitalhospitalDel");
	//重新建立绑定
	$("tbody").on("click", ".hospitalhospitalDel", function() {
		hospital_Del(this, $(this).attr("id"));
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
//验证医院编号
function hospital_selclick_hospitalNo(){
	var hospitalNo = $("#hospitalNo").val();
	if(hospitalNo=="医院编号"){
		return true;
	}
	var result =/^[A-Za-z0-9]+$/.test(hospitalNo);
	if (!result&&hospitalNo!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('编号格式不正确！', '#hospitalNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};
//验证医院名称
function hospital_selclick_hospitalname(){
	var hospitalname = $("#hospitalNo").val();
	if(hospitalname=="医院名称"){
		return true;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(hospitalname);
	if (!result&&hospitalname!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('医院名称格式不对！', '#hospitalname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};
