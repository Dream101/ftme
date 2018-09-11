package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Interactive;
import com.ftme.common.model.News;
import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.InteractiveServer;
import com.ftme.server.NewsServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 联系我们模块
 */
@Clear(FrontInterceptor.class)
public class InteractiveController extends Controller {
	
	private InteractiveServer InteractiveServer=new InteractiveServer();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	private Interactive interactive=new Interactive();
	//跳转到对应的jsp页面
	public void interactiveList(){
		redirect("/view/privilege/interactive-list.jsp");
	}
	
	public void interactiveAdd(){
		redirect("/view/privilege/interactive-add.jsp");
	}
	//查询
	public void interactiveFind() {
		begin=new Integer(getPara("begin"));
		int count=InteractiveServer.interactiveFind(null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = InteractiveServer.interactiveFind(fy);
		////System.out.println(count+"   "+list.size());
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/interactive/interactive-list.jsp");
	}
	
	// 添加联系我们
	public void interactiveSave() {
		interactive.setId(null);
		interactive=getModel(Interactive.class,"interactive");
		interactive.setInteractiveNo(wyl.getNO());
		
		Record n = interactive.toRecord();
		boolean falg= InteractiveServer.interactiveSave(n);
		////System.out.println("添加联系我们=="+falg);
		redirect("/view/privilege/interactive-list.jsp");
	}
	
	// 查询单个联系我们信息
	public void interactiveById(){
		String interactiveNo = getPara("interactiveNo");
		Record interactive=InteractiveServer.interactiveById(interactiveNo);
		interactive.set("interactiveState", 1);
		InteractiveServer.interactiveUpdate(interactive);
		setAttr("interactive", InteractiveServer.interactiveById(interactiveNo));
		
		render("/view/privilege/interactiveinfo.jsp");
	}
	
	// 更改状态查询数据的状态
	public void interactiveUpdate() {
		interactive.setId(null);
		interactive=getModel(Interactive.class,"interactive");
		
		Record userinfo = interactive.toRecord();
		
		InteractiveServer.interactiveUpdate(userinfo);
		interactiveList();
	}

}
