var up;
var next;
var allpages;
var current;
$(function() {
	hospitalinto();
});

//进入页面时查询数据
function hospitalinto(begin) {
	if(begin==null){
		begin=1;
	}
	
	$.ajax( {
		type : "post",
		url :$(".path").val() + "/hospital/hospitalFileDel",
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

/*医院-恢复*/
function hospital_Restore(obj,id){
	layer.confirm('确认要恢复吗？',function(index){
		$.ajax({
			type:"post",
			url:$(".path").val()+"/hospital/hospitalRestore",
			dataType:"json",
			data:{
				"hospitalNo":id,
			},
			success:function(data){
				if(data.falg){
					$(obj).parents("tr").remove();
					layer.msg('已恢复!',{icon:1,time:1000});
					hospitalinto(current);
				}else{
					layer.msg('恢复失败!',{icon:1,time:1000});
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
	$.each(data.list,function(index, hospital) {
		if(hospital.id>0){
						
			$("tbody").append(
			"<tr class='text-c'>" +
			"<td>" +hospital.id +"</td>" +
			"<td>" +hospital.hospitalNo +"</td>" +
			"<td>" +hospital.hospitalname +"</td>" +
			"<td class='f-14 td-manage'><span class='restore'><a style='text-decoration:none' id='"+hospital.hospitalNo+"' class='hospitalRestore' href='javascript:;' title='恢复'><i class='Hui-iconfont'>&#xe66b;</i></a></span></td>"+
			"</tr>");
			k=k+1;
		}else{
			
		}
	});
	//接触之前的绑定
	$("tbody").off("click",".hospitalRestore");
	//重新建立绑定
	$("tbody").on("click", ".hospitalRestore", function() {
		hospital_Restore(this, $(this).attr("id"));
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