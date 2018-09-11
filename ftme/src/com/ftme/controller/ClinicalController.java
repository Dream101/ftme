package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Clinical;
import com.ftme.common.model.Items;
import com.ftme.common.model.Testresults;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.ClinicalServer;
import com.ftme.server.ItemsServer;
import com.ftme.server.TestresultsServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 临床诊断模块
 */
@Clear(FrontInterceptor.class)
public class ClinicalController extends Controller {
	
	private ClinicalServer clinicalServer=new ClinicalServer();
	private Clinical clinical=new Clinical();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	//跳转到对应的jsp页面
	public void clinicalList(){
		setSessionAttr("page", "clinical");
		redirect("/view/dictionary/clinical/clinical-list.jsp");
	}
	public void clinicalAdd(){
		redirect("/view/dictionary/clinical/clinical-add.jsp");
	}
	public void clinicalDelList(){
		redirect("/view/dictionary/clinical/clinical-deletelist.jsp");
	}
	
	// 查询通过验证未被删除的临床诊断
	public void clinicalFile() {
		begin=new Integer(getPara("begin"));
		int count=clinicalServer.clinicalFind(0,null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = clinicalServer.clinicalFind(0,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/clinical/clinical-list.jsp");
	}
	
	//条件查询临床诊断
	public void clinicalSelectFile() {
		begin=new Integer(getPara("begin"));
		
		String clinicalNo=getPara("clinicalNo").equals("临床诊断编号")?" ":getPara("clinicalNo");
		String clinicalname=getPara("clinicalname").equals("临床诊断")?" ":getPara("clinicalname");
		
		String condition=(clinicalNo.equals(" ")?"":" and clinicalNo = '"+clinicalNo+"' ")+(clinicalname.equals(" ")?"":" and clinicalname like '%"+clinicalname+"%' ");
		////System.out.println(condition);
		
		int count=clinicalServer.clinicalFindSel(condition,null).size();
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = clinicalServer.clinicalFindSel(condition,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/clinical/clinical-list.jsp");
	}
	
	// 查询通过验证未被删除的临床诊断
	public void clinicalFileDel() {
		begin=new Integer(getPara("begin"));
		int count=clinicalServer.clinicalFind(1,null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = clinicalServer.clinicalFind(1,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/dictionary/clinical/clinical-list.jsp");
	}
	
	// 添加临床诊断
	public void clinicalSave() {
		clinical.setId(null);
		clinical=getModel(Clinical.class,"clinical");
		clinical.setClinicalState("0");
		clinical.setClinicalNo(wyl.getNO());
		Record testresult = clinical.toRecord();
		clinicalServer.clinicalSave(testresult);
		redirect("/view/dictionary/clinical/clinical-list.jsp");
	}
	
	// 删除临床诊断
	public void clinicalDelete() {
		String clinicalNo = getPara("clinicalNo");
		Record clinical = clinicalServer.clinicalById(clinicalNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(clinical!=null){
			clinical.set("clinicalState",1);
			falg=clinicalServer.clinicalUpdate(clinical);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 恢复删除的临床诊断
	public void clinicalRestore() {
		String clinicalNo = getPara("clinicalNo");
		Record clinical = clinicalServer.clinicalById(clinicalNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(clinical!=null){
			clinical.set("clinicalState",0);
			falg=clinicalServer.clinicalUpdate(clinical);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	// 查询单个临床诊断
	public void clinicalById() {
		String clinicalNo = getPara("clinicalNo");
		setAttr("clinical", clinicalServer.clinicalById(clinicalNo));
		render("/view/dictionary/clinical/clinical-upp.jsp");
	}
	
	// 修改临床诊断
	public void clinicalUpdate() {
		clinical.setId(null);
		clinical=getModel(Clinical.class,"clinical");
		
		Record testresult = clinical.toRecord();
		
		clinicalServer.clinicalUpdate(testresult);
		clinicalList();
	}
	
	// 验证临床诊断是否存在
	public void ajaxclinicalname() {
		Integer id=new Integer(getPara("id"));
		String clinicalname = getPara("clinicalname");
		List list=clinicalServer.ajaxclinicalname(clinicalname);
		
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
}
