package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.News;
import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.NewsServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 行业动态模块
 */
@Clear(FrontInterceptor.class)
public class NewsController extends Controller {
	
	private NewsServer newsServer=new NewsServer();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	private News news=new News();
	//跳转到对应的jsp页面
	public void newsList(){
		redirect("/view/privilege/news-list.jsp");
	}
	
	public void newsAdd(){
		redirect("/view/privilege/news-add.jsp");
	}
	
	public void newsFile() {
		begin=new Integer(getPara("begin"));
		int count=newsServer.newsFind(null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = newsServer.newsFind(fy);
		////System.out.println(count+"   "+list.size());
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/news/news-list.jsp");
	}
	
	// 添加行业动态
	public void newsSave() {
		news.setId(null);
		news=getModel(News.class,"news");
		news.setNewsNo(wyl.getNO());
		Userinfo user=getSessionAttr("loginUser");
		news.setAdmin(user.getUserNo());
		
		Record n = news.toRecord();
		boolean falg= newsServer.newsSave(n);
		////System.out.println("添加行业动态=="+falg);
		redirect("/view/privilege/news-list.jsp");
	}
	
	// 删除行业动态
	public void newsDelete() {
		String newsNo = getPara("newsNo");
		Record news = newsServer.newsById(newsNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(news!=null){
			falg=newsServer.newsDelete(news);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 查询单个行业动态信息
	public void newsById(){
		String newsNo = getPara("newsNo");
		setAttr("news", newsServer.newsById(newsNo));
		render("/view/privilege/news-upp.jsp");
	}
	
	// 修改行业动态
	public void newsUpdate() {
		news.setId(null);
		news=getModel(News.class,"news");
		
		Record userinfo = news.toRecord();
		
		newsServer.newsUpdate(userinfo);
		newsList();
	}

}
