var up;
var next;
var allpages;
var current;
$(function() {
	testresultsinto();
	testresults_list_affair();
});

//进入页面时查询数据
function testresultsinto(begin) {
	if(begin==null){
		begin=1;
	}
	var testresultsNo = $("#testresultsNo").val() == "" ? " " : $("#testresultsNo").val();
	var testresultsname = $("#testresultsname").val() == "" ? " " : $("#testresultsname").val();
//	testresults_selclick_testresultsNo();
//	testresults_selclick_testresultsname();
	var urlpath="";
	
	if((testresultsname!=" "||testresultsNo!=" ")&&(testresultsNo!="检测结果编号"||testresultsname!="检测结果")){
		urlpath=$(".path").val()+"/testresults/testresultsSelectFile";
	}else{
		urlpath= $(".path").val() + "/testresults/testresultsFile";
	}
	
	if(testresults_selclick_testresultsNo()&&testresults_selclick_testresultsname()){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"testresultsNo":testresultsNo,
				"testresultsname":testresultsname,
			},
			success : function(data) {
				dataPut(data);
			}
		});
	}
};
//各种事件绑定
function testresults_list_affair() {
	$("#butSel").click(function() {
		testresultsinto();
	});
	$("#testresultsNo").blur(function() {
		testresults_selclick_testresultsNo();
	});
	$("#testresultsname").blur(function() {
		testresults_selclick_testresultsname();
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		testresultsinto(1);
	}});
	$("#myup").on({click: function() {
		testresultsinto(up);
	}});
	$("#mynext").on({click: function() {
		testresultsinto(next);
	}});
	$("#last").on({click: function() {
		testresultsinto(allpages);
	}});
	$("#zhan").on({change: function() {
		testresultsinto($(this).val());
	}});
}
/*检测结果-删除*/
function testresults_Del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/testresults/testresultsDelete",
			dataType:"json",
			data:{
				"testresultsNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					testresultsinto(current);
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
	$.each(data.list,function(index,  testresults) {
		if(testresults.id>0){
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +testresults.id +"</td>" +
			"<td>" +testresults.testresultsNo +"</td>" +
			"<td>" +testresults.testresultsname +"</td>" +
			"<td class='f-14 td-manage'><span class='upp'><a style='text-decoration:none' class='testresultstestresultsUpp' href='"+$(".path").val()+"/testresults/testresultsById?testresultsNo="+testresults.testresultsNo+"' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a></span> <span class='del'><a style='text-decoration:none' id='"+testresults.testresultsNo+"' class='testresultstestresultsDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span></td>"+
			"</tr>");
				k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".testresultstestresultsDel");
	//重新建立绑定
	$("tbody").on("click", ".testresultstestresultsDel", function() {
		testresults_Del(this, $(this).attr("id"));
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
//验证检测结果编号
function testresults_selclick_testresultsNo(){
	var testresultsNo = $("#testresultsNo").val();
	if(testresultsNo=="检测结果编号"){
		return true;
	}
	var result =/^[A-Za-z0-9]+$/.test(testresultsNo);
	if (!result&&testresultsNo!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('编号格式不正确！', '#testresultsNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};
//验证检测结果
function testresults_selclick_testresultsname(){
	var testresultsname = $("#testresultsNo").val();
	if(testresultsname=="检测结果"){
		return true;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(testresultsname);
	if (!result&&testresultsname!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('检测结果格式不对！', '#testresultsname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};
