$(function() {
	items_addlist_affair();
});
//各种事件绑定
function items_addlist_affair() {
	$("#remark").blur(function() {
		items_addclick_remark();
	});
	$("#itemsname").blur(function() {
		items_addclick_itemsname();
	});
	//	$("#butSel").click(function() {
	//		items_add_click();
	//	});
};
//表单提交验证
function items_add_click() {
	if (!items_addclick_itemsname()) {
		return false;
	} else if (!items_addclick_remark()) {
		//		alert(2);
		return false;
	} else {
		return true;
	}
};
//验证检测项目名称
var falg = false;
function items_addclick_itemsname() {
	var itemsname = $("#itemsname").val();
	if (itemsname == "") {
		layer.tips('检测项目名称不能为空！', '#itemsname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(itemsname);
if (!result) {
		//		$("#butSel").attr( {"disabled" : "disabled"});		
		layer.tips('检测项目名称格式不正确！', '#itemsname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		
//		$("#butSel").removeAttr("disabled");
		var urlpath=$(".path").val()+"/items/ajaxitemsname";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"itemsname":itemsname,
				"id":$("#id").val(),
			},
			success : function(data) {
				if(!data){
					layer.tips('该检测项目名称已经存在！', '#itemsname', {
						tips : [ 2, '#076BAB' ]
					});
				}
			falg=data;
			}
		});
	}
	return falg;
};
//验证检测项目备注
function items_addclick_remark() {
	var remark = $("#remark").val();
	if (remark == "") {
		layer.tips('检测项目备注不能为空！', '#remark', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else{
		return true;
	}
//	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(remark);
//	if (!result&&remark!=null) {
//		layer.tips('检测项目备注格式不正确！', '#remark', {
//			tips : [ 2, '#076BAB' ]
//		});
//		return false;
//	} 
};