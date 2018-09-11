package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Items;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.ItemsServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 检测项目模块
 */
@Clear(FrontInterceptor.class)
public class ItemsController extends Controller {
	
	private ItemsServer itemsServer=new ItemsServer();
	private Items hospi=new Items();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	//跳转到对应的jsp页面
	public void itemsList(){
		setSessionAttr("page", "items");
		redirect("/view/dictionary/items/items-list.jsp");
	}
	public void itemsAdd(){
		redirect("/view/dictionary/items/items-add.jsp");
	}
	public void itemsDelList(){
		redirect("/view/dictionary/items/items-deletelist.jsp");
	}
	
	// 查询通过验证未被删除的基因检测项目
	public void itemsFile() {
		begin=new Integer(getPara("begin"));
		int count=itemsServer.itemsFind(0,null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = itemsServer.itemsFind(0,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/items/items-list.jsp");
	}
	
	//条件查询基因检测项目
	public void itemsSelectFile() {
		begin=new Integer(getPara("begin"));
		
		String itemsNo=getPara("itemsNo").equals("检测项目编号")?" ":getPara("itemsNo");
		String itemsname=getPara("itemsname").equals("检测项目名称")?" ":getPara("itemsname");
		
		String condition=(itemsNo.equals(" ")?"":" and itemsNo = '"+itemsNo+"' ")+(itemsname.equals(" ")?"":" and itemsname like '%"+itemsname+"%' ");
//		//System.out.println(condition);
		
		int count=itemsServer.itemsFindSel(condition,null).size();
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = itemsServer.itemsFindSel(condition,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/items/items-list.jsp");
	}
	
	// 查询通过验证未被删除的基因检测项目
	public void itemsFileDel() {
		begin=new Integer(getPara("begin"));
		int count=itemsServer.itemsFind(1,null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = itemsServer.itemsFind(1,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/items/items-list.jsp");
	}
	
	// 添加基因检测项目
	public void itemsSave() {
		hospi.setId(null);
		hospi=getModel(Items.class,"items");
		hospi.setItemsState("0");
		hospi.setItemsNo(wyl.getNO());
		
		Record items = hospi.toRecord();
		boolean falg= itemsServer.itemsSave(items);
//		setAttr("falg", falg);
		redirect("/view/dictionary/items/items-list.jsp");
	}
	
	// 删除基因检测项目
	public void itemsDelete() {
		String itemsNo = getPara("itemsNo");
		Record items = itemsServer.itemsById(itemsNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(items!=null){
			items.set("itemsState",1);
			falg=itemsServer.itemsUpdate(items);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 恢复删除的基因检测项目
	public void itemsRestore() {
		String itemsNo = getPara("itemsNo");
		Record items = itemsServer.itemsById(itemsNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(items!=null){
			items.set("itemsState",0);
			falg=itemsServer.itemsUpdate(items);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	// 查询单个基因检测项目
	public void itemsById() {
		String itemsNo = getPara("itemsNo");
		setAttr("items", itemsServer.itemsById(itemsNo));
		render("/view/dictionary/items/items-upp.jsp");
	}
	
	// 修改基因检测项目
	public void itemsUpdate() {
		hospi.setId(null);
		hospi=getModel(Items.class,"items");
		
		Record items = hospi.toRecord();
		
		itemsServer.itemsUpdate(items);
		itemsList();
	}
	
	// 验证基因检测项目名称是否存在
	public void ajaxitemsname() {
		Integer id=new Integer(getPara("id"));
		String itemsname = getPara("itemsname");
		List list=itemsServer.ajaxitemsname(itemsname);
		
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
}
