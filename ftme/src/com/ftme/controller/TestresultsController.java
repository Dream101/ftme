package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Items;
import com.ftme.common.model.Testresults;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.ItemsServer;
import com.ftme.server.TestresultsServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 检测结果模块
 */
@Clear(FrontInterceptor.class)
public class TestresultsController extends Controller {
	
	private TestresultsServer testresultsServer=new TestresultsServer();
	private Testresults testresults=new Testresults();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	//跳转到对应的jsp页面
	public void testresultsList(){
		setSessionAttr("page", "testresults");
		redirect("/view/dictionary/testresults/testresults-list.jsp");
	}
	public void testresultsAdd(){
		redirect("/view/dictionary/testresults/testresults-add.jsp");
	}
	public void testresultsDelList(){
		redirect("/view/dictionary/testresults/testresults-deletelist.jsp");
	}
	
	// 查询通过验证未被删除的基因检测结果
	public void testresultsFile() {
		begin=new Integer(getPara("begin"));
		int count=testresultsServer.testresultsFind(0,null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = testresultsServer.testresultsFind(0,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/testresults/testresults-list.jsp");
	}
	
	//条件查询基因检测结果
	public void testresultsSelectFile() {
		begin=new Integer(getPara("begin"));
		
		String testresultsNo=getPara("testresultsNo").equals("检测结果编号")?" ":getPara("testresultsNo");
		String testresultsname=getPara("testresultsname").equals("检测结果")?" ":getPara("testresultsname");
		
		String condition=(testresultsNo.equals(" ")?"":" and testresultsNo = '"+testresultsNo+"' ")+(testresultsname.equals(" ")?"":" and testresultsname like '%"+testresultsname+"%' ");
//		//System.out.println(condition);
		
		int count=testresultsServer.testresultsFindSel(condition,null).size();
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = testresultsServer.testresultsFindSel(condition,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/testresults/testresults-list.jsp");
	}
	
	// 查询通过验证未被删除的基因检测结果
	public void testresultsFileDel() {
		begin=new Integer(getPara("begin"));
		int count=testresultsServer.testresultsFind(1,null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = testresultsServer.testresultsFind(1,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/testresults/testresults-list.jsp");
	}
	
	// 添加基因检测结果
	public void testresultsSave() {
		testresults.setId(null);
		testresults=getModel(Testresults.class,"testresults");
		testresults.setTestresultsState("0");
		testresults.setTestresultsNo(wyl.getNO());
		Record testresult = testresults.toRecord();
		testresultsServer.testresultsSave(testresult);
		redirect("/view/dictionary/testresults/testresults-list.jsp");
	}
	
	// 删除基因检测结果
	public void testresultsDelete() {
		String testresultsNo = getPara("testresultsNo");
		Record testresults = testresultsServer.testresultsById(testresultsNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(testresults!=null){
			testresults.set("testresultsState",1);
			falg=testresultsServer.testresultsUpdate(testresults);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 恢复删除的基因检测结果
	public void testresultsRestore() {
		String testresultsNo = getPara("testresultsNo");
		Record testresults = testresultsServer.testresultsById(testresultsNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(testresults!=null){
			testresults.set("testresultsState",0);
			falg=testresultsServer.testresultsUpdate(testresults);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	// 查询单个基因检测结果
	public void testresultsById() {
		String testresultsNo = getPara("testresultsNo");
		setAttr("testresults", testresultsServer.testresultsById(testresultsNo));
		render("/view/dictionary/testresults/testresults-upp.jsp");
	}
	
	// 修改基因检测结果
	public void testresultsUpdate() {
		testresults.setId(null);
		testresults=getModel(Testresults.class,"testresults");
		
		Record testresult = testresults.toRecord();
		
		testresultsServer.testresultsUpdate(testresult);
		testresultsList();
	}
	
	// 验证基因检测结果是否存在
	public void ajaxtestresultsname() {
		Integer id=new Integer(getPara("id"));
		String testresultsname = getPara("testresultsname");
		List list=testresultsServer.ajaxtestresultsname(testresultsname);
		
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
}
