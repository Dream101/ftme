var up;
var next;
var allpages;
var current;
$(function() {
	interactiveinto();
});

//进入页面时查询数据
function interactiveinto(begin) {
	if(begin==null){
		begin=1;
	}
	var urlpath = $(".path").val() + "/interactive/interactiveFind";
	$.ajax( {
		type : "post",
		url :urlpath,
		dataType : "json",
		data : {
			"begin":begin,
		},
		success : function(data) {
			dataPut(data);
		}
	});
};
//分页
function fenye(){
	$("#first").on({click: function() {
		interactiveinto(1);
	}});
	$("#myup").on({click: function() {
		interactiveinto(up);
	}});
	$("#mynext").on({click: function() {
		interactiveinto(next);
	}});
	$("#last").on({click: function() {
		interactiveinto(allpages);
	}});
	$("#zhan").on({change: function() {
		interactiveinto($(this).val());
	}});
}
/*行业动态-删除*/
function interactive_Del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/interactive/interactiveDelete",
			dataType:"json",
			data:{
				"interactiveNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
					interactiveinto(current);
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
//	 interactiveNo   | VARCHAR(30)  | YES  |     |                   | 行业动态编号  
// title    | VARCHAR(25)  | YES  |     |                   | 名称      
// content  | TEXT(65535)  | YES  |     |                   | 内容      
// datetime | DATETIME(19) | YES  |     | CURRENT_TIMESTAMP | 添加时间    
// admin    | VARCHAR(20)  | YES  |     |                   | 添加者（当前登录者）
	$.each(data.list,function(index,interactive) {
		if(interactive.id>0){
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +interactive.id +"</td>" +
			"<td>" +interactive.interactiveNo+"</td>" +
			"<td>" +interactive.name +"</td>" +
			"<td>" +interactive.phone +"</td>" +
			"<td>" +(interactive.interactiveState==0?"<span class='btn btn-secondary radius'>未查看</span>":"<span class='btn btn-success radius'>已查看</span>")+"</td>" +// <span class='del'><a style='text-decoration:none' id='"+interactive.interactiveNo+"' class='interactiveinteractiveDel' href='javascript:;' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a></span>
			"<td class='f-14 td-manage'><span class='upp'><a style='text-decoration:none' class='interactiveinteractiveUpp' href='"+$(".path").val()+"/interactive/interactiveById?interactiveNo="+interactive.interactiveNo+"' title='查看'>查看</a></span></td>"+
			"</tr>");
			k=k+1;
		}
	});
//	//接触之前的绑定
//	$("tbody").off("click",".interactiveinteractiveDel");
//	//重新建立绑定
//	$("tbody").on("click", ".interactiveinteractiveDel", function() {
//		interactive_Del(this, $(this).attr("id"));
//	} );
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