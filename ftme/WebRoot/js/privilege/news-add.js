//表单提交验证
function news_add_click() {
	
	var title = $("#title").val();
	var content = $("#content").val();
	if(title == ""){
		layer.tips('标题不能为空！', '#newsNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else if(content == ""){
		layer.tips('内容不能为空！', '#newsNo', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
		return true;
	}
};