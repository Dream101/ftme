package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.StaffServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 员工管理模块
 * @author wyl
 *
 */
@Clear(FrontInterceptor.class)
public class StaffController extends Controller {
	private StaffServer staffServer = new StaffServer();
	private Userinfo userinfo = new Userinfo();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	//跳转到对应的jsp页面
	public void staffList(){
		setSessionAttr("page", "staff");
		redirect("/view/staff/staff-list.jsp");
	}
	public void staffAdd(){
		redirect("/view/staff/staff-add.jsp");
	}
	public void staffDelList(){
		redirect("/view/staff/staff-deletelist.jsp");
	}
	public void staffDocument(){
		redirect("/view/staff/staff-document.jsp");
	}
	
	// 查询通过验证未被删除的员工信息
	public void staffFile() {
		begin=new Integer(getPara("begin"));
		int count=staffServer.staffFindCount(2, 1);
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = staffServer.staffFind(2, 1,fy);
//		//System.out.println(list.size());
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/staff/staff-list.jsp");
	}
	
	//条件查询员工信息
	public void staffSelectFile() {
		begin=new Integer(getPara("begin"));
		
		userinfo.setUserNo(getPara("userNo").equals("员工编号")?" ":getPara("userNo"));
		userinfo.setAccountState(new Integer(getPara("accountState")));
		userinfo.setName(getPara("name").equals("员工姓名")?" ":getPara("name"));
		userinfo.setPhone(getPara("phone").equals("员工手机号")?" ":getPara("phone"));
		
		int count=staffServer.staffSelectFileCount(userinfo);
		Record fy=wyl.fenyi(count, begin);
//		//System.out.println("sss"+userinfo.getName()+"sss"+userinfo.getUserNo()+"sss"+userinfo.getPhone()+"sss");
		List<Record> list = staffServer.staffSelectFile(userinfo,fy);
//		//System.out.println(list.size());
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/staff/staff-list.jsp");
	}
	// 查询通过验证被删除的员工信息
	public void staffFileDel() {
		begin=new Integer(getPara("begin"));
		int count=staffServer.staffFindAccountcount(2, 3);
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = staffServer.staffFindAccount(2, 3,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
//		render("/view/staff/staff-deletelist.jsp");
	}

	// 添加员工
	public void staffSave() {
		userinfo.setId(null);
		userinfo=getModel(Userinfo.class,"user");
		Userinfo u=getSessionAttr("loginUser");
		userinfo.setUserNo(wyl.getNO());
		userinfo.setRoleNo("2");
		userinfo.setRoleType("员工");
		if(u!=null){
			userinfo.setUno(u.getUserNo());
			userinfo.setAccountState(1);
			userinfo.setEmailState(1);
		}else{
			userinfo.setAccountState(0);
			userinfo.setEmailState(0);
		}
		
		Record user = userinfo.toRecord();
		staffServer.staffSave(user);
		redirect("/view/staff/staff-list.jsp");
	}
	
	// 删除员工
	public void staffDelete() {
		String userNo = getPara("userNo");
		Record userinfo = staffServer.staffById(userNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(userinfo!=null){
			
			userinfo.set("accountState",(userinfo.getInt("accountState")+3));
			falg=staffServer.staffUpdate(userinfo);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 恢复删除的员工
	public void staffRestore() {
		String userNo = getPara("userNo");
		Record userinfo = staffServer.staffById(userNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(userinfo!=null){
			userinfo.set("accountState", (userinfo.getInt("accountState")-3));
			falg=staffServer.staffUpdate(userinfo);			
		}
		setAttr("falg", falg);
		renderJson();
	}

	// 查询单个员工信息
	public void staffById() {
		String userNo = getPara("userNo");
		setAttr("user", staffServer.staffById(userNo));
		render("/view/staff/staff-upp.jsp");
	}
	
	// 修改员工
	public void staffUpdate() {
		userinfo.setId(null);
		userinfo=getModel(Userinfo.class,"user");
		
		Userinfo u=getSessionAttr("loginUser");
		userinfo.setUno(u.getUserNo());
		Record user = userinfo.toRecord();
		staffServer.staffUpdate(user);
		staffList();
	}

	// 验证账号是否正确
	public void ajaxusername() {
		Integer id=new Integer(getPara("id"));
		String username = getPara("username");
		List<Record> list=staffServer.ajaxusername(username);
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
	
	// 验证邮箱是否正确
	public void ajaxEmail() {
		Integer id=new Integer(getPara("id"));
		String email = getPara("email");
		List<Record> list=staffServer.ajaxEmail(email);
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
	// 验证手机号是否正确
	public void ajaxPhone() {
		Integer id=new Integer(getPara("id"));
		String phone = getPara("phone");
		List<Record> list=staffServer.ajaxPhone(phone);
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
	
}
