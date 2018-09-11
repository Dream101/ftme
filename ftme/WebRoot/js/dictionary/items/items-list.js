var up;
var next;
var allpages;
var current;
$(function() {
	itemsinto();
	items_list_affair();
});

//进入页面时查询数据
function itemsinto(begin) {
	if(begin==null){
		begin=1;
	}
	var itemsNo = $("#itemsNo").val() == "" ? " " : $("#itemsNo").val();
	var itemsname = $("#itemsname").val() == "" ? " " : $("#itemsname").val();
	
	var urlpath="";
	if(itemsname!=" "||itemsNo!=" "||itemsNo!="检测项目编号"||itemsname!="检测项目名称"){
		urlpath=$(".path").val()+"/items/itemsSelectFile";
	}else{
		urlpath= $(".path").val() + "/items/itemsFile";
	}
	if((items_selclick_itemsNo()&&items_selclick_itemsname())){
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			data : {
				"begin":begin,
				"itemsNo":itemsNo,
				"itemsname":itemsname,
			},
			success : function(data) {
				dataPut(data);
			}
		});
	}
	
};
//各种事件绑定
function items_list_affair() {
	$("#butSel").click(function() {
		itemsinto();
	});
	$("#itemsNo").blur(function() {
		items_selclick_itemsNo();
	});
	$("#itemsname").blur(function() {
		items_selclick_itemsname();
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		itemsinto(1);
	}});
	$("#myup").on({click: function() {
		itemsinto(up);
	}});
	$("#mynext").on({click: function() {
		itemsinto(next);
	}});
	$("#last").on({click: function() {
		itemsinto(allpages);
	}});
	$("#zhan").on({change: function() {
		itemsinto($(this).val());
	}});
}
/*检测项目-删除*/
function items_Del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/items/itemsDelete",
			dataType:"json",
			data:{
				"itemsNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					itemsinto(current);
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
	$.each(data.list,function(index,  items) {
		if(items.id>0){
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +items.id +"</td>" +
			"<td>" +items.itemsNo +"</td>" +
			"<td>" +items.itemsname +"</td>" +
			"<td>" +items.remark +"</td>" +
			"<td class='f-14 td-manage'><span class='upp'><a style='text-decoration:none' class='itemsitemsUpp' href='"+$(".path").val()+"/items/itemsById?itemsNo="+items.itemsNo+"' title='编辑'><i class='Hui-iconfont'>&#xe6df;</i></a></span> <span class='del'><a style='text-decoration:none' id='"+items.itemsNo+"' class='itemsitemsDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".itemsitemsDel");
	//重新建立绑定
	$("tbody").on("click", ".itemsitemsDel", function() {
		items_Del(this, $(this).attr("id"));
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
//验证检测项目编号
function items_selclick_itemsNo(){
	var itemsNo = $("#itemsNo").val();
	if(itemsNo=="检测项目编号"){
		return true;
	}
	var result =/^[A-Za-z0-9]+$/.test(itemsNo);
	if (!result&&itemsNo!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('编号格式不正确！', '#itemsNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};
//验证检测项目名称
function items_selclick_itemsname(){
	var itemsname = $("#itemsNo").val();
	if(itemsname=="检测项目名称"){
		return true;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(itemsname);
	if (!result&&itemsname!="") {
//		$("#butSel").attr({"disabled":"disabled"});
		layer.tips('检测项目名称格式不对！', '#itemsname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
//		$("#butSel").removeAttr("disabled");
	}
};
