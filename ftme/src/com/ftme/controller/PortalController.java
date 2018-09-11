package com.ftme.controller;

import java.util.List;

import com.ftme.server.PortalServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

/**
 * 官网模块
 * @author wyl
 */
@Clear
public class PortalController extends Controller {
	private PortalServer pServer = new PortalServer();
	private WylUtil wyl=new WylUtil();
	// 跳转到对应的jsp页面
	public void portal() {
		redirect("/portal/index.html");
	}
	
	public void portalJson() {
//		setSessionAttr("pictureinfoList", pServer.findLogo());
//		setSessionAttr("companyinfo", pServer.findcompany());
		Record result=new Record();
		result.set("logo", pServer.findLogo());
		result.set("company", pServer.findcompany());
		
		renderJson(result);
//		render("/portal/index1.jsp");
	}
	
	public void portalCompanyJson() {
		Record result=new Record();
		result.set("company", pServer.findcompany());
		
		renderJson(result);
//		render("/portal/index1.jsp");
	}
	
	//查询行业动态
	public void blog(){
		int count =pServer.findnews(null).size();
		Record fy= wyl.blogFY(count, getPara("begin"));
		
		List<Record> list =pServer.findnews(fy);
			
		for (Record blog : list) {
			blog.set("day", blog.get("datetime").toString().substring(8,10));
			blog.set("month", blog.get("datetime").toString().substring(5,7));
		}
		setAttr("fy", fy);
		setAttr("blogList", list);
		render("/portal/blog.jsp");
	}
	
	//查询行业动态
		public void blogJson(){
			int count =pServer.findnews(null).size();
			Record fy= wyl.blogFY(count, getPara("begin"));
			List<Record> list =pServer.findnews(fy);
			for (Record blog : list) {
				blog.set("day", blog.get("datetime").toString().substring(8,10));
				blog.set("month", blog.get("datetime").toString().substring(5,7));
			}
			Record result=new Record();
			result.set("fy", fy);
			result.set("blogList",list);
			renderJson(result);
		}
	
	//查询某条行业动态
	public void blogByNo(){
		Record news =pServer.blogByNo(getPara("newsNo"));
		if(news!=null){
			news.set("day", news.get("datetime").toString().substring(8,10));
			news.set("month", news.get("datetime").toString().substring(5,7));
		}
		String content = news.getStr("content");
//		System.out.print(content);
		content = content.replace("\n", "<br/>");
//		System.out.print(content);
		news.set("content", content);
		setAttr("blog", news);
		render("/portal/single.jsp");
	}
	
	//查询某条行业动态
	public void blogByNoJson(){
		Record result =pServer.blogByNo(getPara("newsNo"));
		if(result!=null){
			result.set("day", result.get("datetime").toString().substring(8,10));
			result.set("month", result.get("datetime").toString().substring(5,7));
		}
		String content = result.getStr("content");
//			System.out.print(content);
		content = content.replace("\n", "<br/>");
//			System.out.print(content);
		result.set("content", content);
		renderJson(result);
	}
	
}
