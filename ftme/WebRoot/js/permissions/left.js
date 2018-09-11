$(function() {
	leftinto();
	

	//<dd id=''><div class="title"><span><img src=""+$(".path").val()+"/images/leftico01.png" /></span>患者管理</div></dd>
	
//	<dd>
//	<div class="title"><span><img src=""+$(".path").val()+"/images/leftico01.png" /></span>患者管理</div>
//	<ul class="menuson">
//	<li class="active"><div class="header"><cite></cite><a href=""+$(".path").val()+"/patient/patientList" target="rightFrame">患者列表</a><i></i></div></li>
//	</ul>
//	</dd>
	
});

function leftinto() {
	$.ajax( {
		type : "post",
		url :$(".path").val()+"/permissions/permissionsFindList",
		dataType : "json",
		success : function(data) {
			$(".leftmenu").empty();
			var i=0;
			$.each(data.list,function(index, p) {
				if(p.superior==null||p.hierarchy==1){
//					alert(p.permissionsidNo+"  "+p.permissionsidName+"  "+$(".path").val()+p.icon);				
					$(".leftmenu").append("<dd id='"+p.permissionsidNo+"'><div class='title'><span><img src='"+$(".path").val()+p.icon+"' /></span>"+p.permissionsidName+"</div><ul class='menuson'></ul></dd>");
				}else if(p.superior!=null||p.hierarchy==2){
//					alert(p.permissionsidNo+"  "+p.monikeri+"  "+p.linkname);
					if(i==0){
						//	class='active'
						$("#"+p.superior+" .menuson").append("<li ><div class='header'><cite></cite><a href='"+$(".path").val()+p.monikeri+"' target='rightFrame'>"+p.linkname+"</a><i></i></div></li>");
						i=i+1;
					}else{
						$("#"+p.superior+" .menuson").append("<li ><div class='header'><cite></cite><a href='"+$(".path").val()+p.monikeri+"' target='rightFrame'>"+p.linkname+"</a><i></i></div></li>");
					}
				}
			});
//			alert("i==0说明没有登录，缓存中没有记录登录者的信息，无法查询左侧导航栏的信息==="+(i==0)+"   ");
			if(data.count==0){
				top.location = $(".path").val() + "/login.jsp";
			}else{
				left_list_affair();
			}
		},
		error: function(data){
			top.location = $(".path").val() + "/login.jsp";
		}
	});
};
function left_list_affair() {
	//接触之前的绑定
//	$("dl").off("click",".menuson .header");
	//重新建立绑定
	$(".leftmenu").on("click", ".menuson .header", function() {
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		$parent.addClass("active");
		if (!!$(this).next('.sub-menus').size()) {
			if ($parent.hasClass("open")) {
				$parent.removeClass("open").find('.sub-menus').hide();
			} else {
				$parent.addClass("open").find('.sub-menus').show();
			}
		}
	});
	
	//模块名称点击事件
	$(".leftmenu").on("click", ".title", function() {
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if ($ul.is(':visible')) {
			$(this).next('.menuson').slideUp();
		} else {
			$(this).next('.menuson').slideDown();
		}
	});
	
	//	// 三级菜单点击
	//	$('.sub-menus li').click(function(e) {
	//		$(".sub-menus li.active").removeClass("active")
	//		$(this).addClass("active");
	//	});
	
};