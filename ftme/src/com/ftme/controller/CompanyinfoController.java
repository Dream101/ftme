package com.ftme.controller;

import com.ftme.common.model.Companyinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.CompanyinfoServer;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
/**
 * 公司简介
 * @author wyl
 *
 */
@Clear(FrontInterceptor.class)
public class CompanyinfoController extends Controller {
	
	private CompanyinfoServer companyinfoServer=new CompanyinfoServer();
	private Companyinfo companyinfo=new Companyinfo();
	//跳转到对应的jsp页面
	public void companyinfo(){
		setSessionAttr("companyinfo", companyinfoServer.companyinfoFindFirst());
		redirect("/view/privilege/companyinfo.jsp");
	}
	// 查询通过验证未被删除的临床诊断
	public void companyinfoByFind() {
		setSessionAttr("companyinfo", companyinfoServer.companyinfoFindFirst());
		redirect("/view/privilege/companyinfo-upp.jsp");
	}
	/**
	 * 修改
	 */
	public void companyinfoUpdate(){
		boolean falg= getModel(Companyinfo.class,"companyinfo").update();
//		//System.out.println("修改(通过方法链的方式)="+falg);
		companyinfo();
	}
	
}
