$(function() {
	testresults_addlist_affair();
});
//各种事件绑定
function testresults_addlist_affair() {

	$("#testresultsname").blur(function() {
		testresults_addclick_testresultsname();
	});
	//	$("#butSel").click(function() {
	//		testresults_add_click();
	//	});
};
//表单提交验证
function testresults_add_click() {
//	if (!testresults_addclick_testresultsNo()) {
//		return false;
//	} else 
	if (!testresults_addclick_testresultsname()) {
		//		alert(2);
		return false;
	} else {
		return true;
	}
};
//验证检测结果
var falg = false;
function testresults_addclick_testresultsname() {
	var testresultsname = $("#testresultsname").val();
	if (testresultsname == "") {
		layer.tips('检测结果不能为空！', '#testresultsname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	}
	var result = /^[\u4E00-\u9FA5A-Za-z0-9]+$/.test(testresultsname);
if (!result) {
		//		$("#butSel").attr( {"disabled" : "disabled"});		
		layer.tips('检测结果格式不正确！', '#testresultsname', {
			tips : [ 2, '#076BAB' ]
		});
		return false;
	} else {
//		$("#butSel").removeAttr("disabled");
		var urlpath=$(".path").val()+"/testresults/ajaxtestresultsname";
		$.ajax( {
			type : "post",
			url :urlpath,
			dataType : "json",
			async: false,
			data : {
				"testresultsname":testresultsname,
				"id":$("#id").val(),
			},
			success : function(data) {
				if(!data){
					layer.tips('该检测结果已经存在！', '#testresultsname', {
						tips : [ 2, '#076BAB' ]
					});
				}
					falg=data;
			}
		});
	}
	return falg;
};