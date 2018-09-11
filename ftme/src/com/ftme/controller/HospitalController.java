package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Hospital;
import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.HospitalServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 医院模块
 */
@Clear(FrontInterceptor.class)
public class HospitalController extends Controller {
	
	private HospitalServer hospitalServer=new HospitalServer();
	private Hospital hospi=new Hospital();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	//跳转到对应的jsp页面
	public void hospitalList(){
		setSessionAttr("page", "hospital");
		redirect("/view/dictionary/hospital/hospital-list.jsp");
	}
	public void hospitalAdd(){
		redirect("/view/dictionary/hospital/hospital-add.jsp");
	}
	public void hospitalDelList(){
		redirect("/view/dictionary/hospital/hospital-deletelist.jsp");
	}
	
	// 查询通过验证未被删除的医院信息
	public void hospitalFile() {
		begin=new Integer(getPara("begin"));
		int count=hospitalServer.hospitalFind(0,null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = hospitalServer.hospitalFind(0,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/hospital/hospital-list.jsp");
	}
	
	//条件查询医院信息
	public void hospitalSelectFile() {
		begin=new Integer(getPara("begin"));
		
		String hospitalNo=getPara("hospitalNo").equals("医院编号")?" ":getPara("hospitalNo");
		String hospitalname=getPara("hospitalname").equals("医院名称")?" ":getPara("hospitalname");
		
		String condition=(hospitalNo.equals(" ")?"":" and hospitalNo = '"+hospitalNo+"' ")+(hospitalname.equals(" ")?"":" and hospitalname like '%"+hospitalname+"%' ");
//		//System.out.println(condition);
		
		int count=hospitalServer.hospitalFindSel(condition,null).size();
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = hospitalServer.hospitalFindSel(condition,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/hospital/hospital-list.jsp");
	}
	
	// 查询通过验证未被删除的医院信息
	public void hospitalFileDel() {
		begin=new Integer(getPara("begin"));
		int count=hospitalServer.hospitalFind(1,null).size();;
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = hospitalServer.hospitalFind(1,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/hospital/hospital-list.jsp");
	}
	
	// 添加医院
	public void hospitalSave() {
		hospi.setId(null);
		hospi=getModel(Hospital.class,"hospital");
		hospi.setHospitalState("0");
		Record userinfo = hospi.toRecord();
		Record hospital = hospi.toRecord();
		boolean falg= hospitalServer.hospitalSave(hospital);
		redirect("/view/dictionary/hospital/hospital-list.jsp");
	}
	
	// 删除医院
	public void hospitalDelete() {
		String hospitalNo = getPara("hospitalNo");
		Record hospital = hospitalServer.hospitalById(hospitalNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(hospital!=null){
			hospital.set("hospitalState",1);
			falg=hospitalServer.hospitalUpdate(hospital);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 恢复删除的医院
	public void hospitalRestore() {
		String hospitalNo = getPara("hospitalNo");
		Record hospital = hospitalServer.hospitalById(hospitalNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(hospital!=null){
			hospital.set("hospitalState",0);
			falg=hospitalServer.hospitalUpdate(hospital);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	// 查询单个医院信息
	public void hospitalById() {
		String hospitalNo = getPara("hospitalNo");
		setAttr("hospital", hospitalServer.hospitalById(hospitalNo));
		render("/view/dictionary/hospital/hospital-upp.jsp");
	}
	
	// 修改医院
	public void hospitalUpdate() {
		hospi.setId(null);
		hospi=getModel(Hospital.class,"hospital");
		
		Record userinfo = hospi.toRecord();
		
		hospitalServer.hospitalUpdate(userinfo);
		hospitalList();
	}
	// 验证医院编号是否存在
	public void ajaxhospitalNo() {
		Integer id=new Integer(getPara("id"));
		String hospitalNo = getPara("hospitalNo");
		List list=hospitalServer.ajaxhospitalNo(hospitalNo);
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		
		renderJson(falg);
	}
	
	// 验证医院名称是否存在
	public void ajaxhospitalname() {
		Integer id=new Integer(getPara("id"));
		String hospitalname = getPara("hospitalname");
		List list=hospitalServer.ajaxhospitalname(hospitalname);
		
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
}
