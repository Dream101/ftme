$(function() {
	companyinfo_addlist_affair();
});
//各种事件绑定
function companyinfo_addlist_affair() {
	
//	 id               | INT(10)     | NO   | PRI |         | 主键      
//	 companyName      | VARCHAR(34) | YES  |     |         | 公司名称    
//	 companyPhone     | VARCHAR(20) | YES  |     |         | 联系方式    
//	 companyAddress   | VARCHAR(60) | YES  |     |         | 公司地址    
//	 companyIntroduce | TEXT(65535) | YES  |     |         | 公司简介    
//	 state            | VARCHAR(25) | YES  |     |         | 备用字段    
	$("#companyName").blur(function() {
		companyinfo_addclick_companyName();
	});
	$("#companyPhone").blur(function() {
		companyinfo_addclick_companyPhone();
	});
	$("#companyAddress").blur(function() {
		companyinfo_addclick_companyAddress();
	});
	$("#companyIntroduce").blur(function() {
		companyinfo_addclick_companyIntroduce();
	});
};
//表单提交验证
function companyinfo_add_click() {
	if (!companyinfo_addclick_companyName()) {
		return false;
	} else if (!companyinfo_addclick_companyPhone()) {
		//		alert(2);
		return false;
	} else if (!companyinfo_addclick_companyAddress()) {
		//		alert(2);
		return false;
	} else if (!companyinfo_addclick_companyIntroduce()) {
		//		alert(2);
		return false;
	} else {
		return true;
	}
};
//验证公司名称   
function companyinfo_addclick_companyName () {
	var companyName = $("#companyName").val();
	if (companyName == "") {
		layer.tips('公司名称不能为空！', '#companyName', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
	}
};
//验证联系方式 
function companyinfo_addclick_companyPhone () {
	var companyPhone = $("#companyPhone").val();
	if (companyPhone == "") {
		layer.tips('联系方式不能为空！', '#companyPhone', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
	}
};
//验证公司地址   
function companyinfo_addclick_companyAddress () {
	var companyAddress = $("#companyAddress").val();
	if (companyAddress == "") {
		layer.tips('公司地址不能为空！', '#companyAddress', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
	}
};
//验证公司简介
function companyinfo_addclick_companyIntroduce () {
	var companyIntroduce = $("#companyIntroduce").val();
	if (companyIntroduce == "") {
		layer.tips('公司简介不能为空！', '#companyIntroduce', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}else{
		return true;
	}
};