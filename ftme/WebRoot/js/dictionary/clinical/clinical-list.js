var up;
var next;
var allpages;
var current;
$(function() {
	clinicalinto();
	clinical_list_affair();
});

//进入页面时查询数据
function clinicalinto(begin) {
	if(begin==null){
		begin=1;
	}
	var clinicalNo = $("#clinicalNo").val() == "" ? " " : $("#clinicalNo").val();
	var clinicalname = $("#clinicalname").val() == "" ? " " : $("#clinicalname").val();
//	clinical_selclick_clinicalNo();
//	clinical_selclick_clinicalname();
	var urlpath="";
	if((clinicalname!=" "||clinicalNo!=" ")&&(clinicalNo!="临床诊断编号"||clinicalNo!="临床诊断")){
		urlpath=$(".path").val()+"/clinical/clinicalSelectFile";
	}else{
		urlpath= $(".path").val() + "/clinical/clinicalFile";
	}
	if(clinical_selclick_clinicalNo()&&clinical_selclick_clinicalname()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"clinicalNo":clinicalNo,
				"clinicalname":clinicalname,
			},
			success : function(data) {
				dataPut(data);
			}
		});
	}
};
//各种事件绑定
function clinical_list_affair() {
	$("#butSel").click(function() {
		clinicalinto();
	});
	$("#clinicalNo").blur(function() {
		clinical_selclick_clinicalNo();
	});
	$("#clinicalname").blur(function() {
		clinical_selclick_clinicalname();
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		clinicalinto(1);
	}});
	$("#myup").on({click: function() {
		clinicalinto(up);
	}});
	$("#mynext").on({click: function() {
		clinicalinto(next);
	}});
	$("#last").on({click: function() {
		clinicalinto(allpages);
	}});
	$("#zhan").on({change: function() {
		clinicalinto($(this).val());
	}});
}
/*临床诊断内容-删除*/
function clinical_Del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/clinical/clinicalDelete",
			dataType:"json",
			data:{
				"clinicalNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					clinicalinto(current);
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
	$.each(data.list,function(index,  clinical) {
		if(clinical.id>0){
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +clinical.id +"</td>" +
			"<td>" +clinical.clinicalNo +"</td>" +
			"<td>" +clinical.clinicalname +"</td>" +
			"<td class='f-14 td-manage'><span class='upp'><a style='text-decoration:none' class='clinicalclinicalUpp' href='"+$(".path").val()+"/clinical/clinicalById?clinicalNo="+clinical.clinicalNo+"' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a></span> <span class='del'><a style='text-decoration:none' id='"+clinical.clinicalNo+"' class='clinicalclinicalDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span></td>"+
			"</tr>");
				k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".clinicalclinicalDel");
	//重新建立绑定
	$("tbody").on("click", ".clinicalclinicalDel", function() {
		clinical_Del(this, $(this).attr("id"));
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
//验证临床诊断编号
function clinical_selclick_clinicalNo(){
	var clinicalNo = $("#clinicalNo").val();
	if(clinicalNo=="临床诊断编号"){
		return true;
	}
	var result =/^[A-Za-z0-9]+$/.test(clinicalNo);
	if (!result&&clinicalNo!="") {
		layer.tips('编号格式不正确！', '#clinicalNo', {
			tips : [ 2, '#076BAB' ]
		});return false;
	}else{
		return true;
	}
};
//验证临床诊断内容
function clinical_selclick_clinicalname(){
	var clinicalname = $("#clinicalNo").val();
	if(clinicalNo=="临床诊断"){
		return true;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(clinicalname);
	if (!result&&clinicalname!="") {
		layer.tips('临床诊断内容格式不对！', '#clinicalname', {
			tips : [ 2, '#076BAB' ]
		});return false;
	}else{
		return true;
	}
};
