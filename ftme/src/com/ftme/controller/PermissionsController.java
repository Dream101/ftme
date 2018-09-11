package com.ftme.controller;

import java.util.ArrayList;
import java.util.List;

import com.ftme.common.model.Permissions;
import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.PermissionsServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 权限模块
 * @author wyl
 */
@Clear(FrontInterceptor.class)
public class PermissionsController extends Controller {

	private PermissionsServer permissionsServer = new PermissionsServer();
	public Permissions permissions = new Permissions();
	private WylUtil wyl=new WylUtil();
	//跳转到对应的jsp页面
	public void permissionsList(){//管理页面
		render("/view/govern/permissions/role-replace.jsp");
	}
	
	public void governLimitList(){
//		setAttr("hierarchy1", permissionsServer.permissionsHierarchy1());
//		setAttr("hierarchy3", permissionsServer.permissionsHierarchy3());
		render("/view/govern/permissions/governLimit-List.jsp");
	}
	
	public void governLimit(){//权限管理页面
		String userNo=getPara("userNo");
		setAttr("user", permissionsServer.userById(userNo));
		setAttr("userHierarchy",permissionsServer.userinfoPermissionsHierarchy(userNo));
		setAttr("hierarchy1", permissionsServer.permissionsHierarchy1());
		setAttr("hierarchy3", permissionsServer.permissionsHierarchy3());
		render("/view/govern/permissions/govern-Limit.jsp");
	}
	
	// -------------------------------------------左侧导航栏----------------------------------
	public void permissionsFindList() {
		Userinfo u = getSessionAttr("loginUser");
		//////System.out.println(u.toString());
		List<Record> list = new ArrayList<Record>();
		if (u != null) {
			list = permissionsServer.permissionsFindList(u.getRoleNo(), u
					.getUserNo());
		}
		setAttr("list", list);
		setAttr("count",list.size());
		renderJson();
	}

	// -------------------------------------------右侧按钮----------------------------------
	//获取当前用户的权限等级
	public void perRoleNo(){
		Userinfo u = getSessionAttr("loginUser");
		////System.out.println("当前用户的权限等级="+u.getRoleNo());
		renderJson(u.getRoleNo().equals("0")||u.getRoleNo().equals("2"));
	}
	
	//查询该管理员拥有的权限
	public void permissionsPageButton() {
		String page= getSessionAttr("page");
		Userinfo u = getSessionAttr("loginUser");
		////System.out.println("查询该管理员拥有的权限permissionsPageButton=="+u.toString());
		List<Record> list = new ArrayList<Record>();
		if (u != null) {
			list = permissionsServer.permissionsPageButton(u,page);
		}
		for (Record record : list) {
			////System.out.println("查询该管理员拥有的权限list====="+record.toString());
		}
		
		setAttr("list", list);
		setAttr("count", list.size());
		renderJson();
	}
	
	public void userinfoPermissionsHierarchy(){
		String userNo=getPara("userNo");
		List<Record> list= permissionsServer.userinfoPermissionsHierarchy(userNo);
		renderJson(list);
	}
	
	//添加或删除管理员的权限
	public void permissionsSaveOrDel(){
		String userNo= getPara("userNo");
		String permissionsidNo= getPara("permissionsidNo");
		String superior=getPara("superior");
		Record rolepermissions=new Record();
		rolepermissions.set("userNo",userNo );
		rolepermissions.set("permissionsidNo",permissionsidNo);
		rolepermissions.set("superior",superior );
		if(getPara("type").equals("add")){
			permissionsServer.permissionsSave(rolepermissions);
			renderJson("add");
		}else{
			permissionsServer.permissionsDel(rolepermissions);
			renderJson("del");
		}
	}
	
	// -------------------------------------------角色替换（员工<->管理员）----------------------------------
	//查询对应条件的数据userNo!="编号"||selname!="姓名"||phone!="手机号"
	public void permissionsSelectFile() {
		Integer begin=new Integer(getPara("begin"));

		Userinfo user=new Userinfo();
		user.setRoleNo(getPara("roleNo"));
		user.setUserNo(getPara("userNo").equals("编号")?" ":getPara("userNo"));
		user.setAccountState(new Integer(getPara("accountState")));
		user.setName(getPara("name").equals("姓名")?" ":getPara("name"));
		user.setPhone(getPara("phone").equals("手机号")?" ":getPara("phone"));
		
		////System.out.println(user.toString());
		int count=permissionsServer.permissionsSelectFile(user,null).size();
		Record fy=wyl.fenyi(count, begin);
//		////System.out.println("sss"+userinfo.getName()+"sss"+userinfo.getUserNo()+"sss"+userinfo.getPhone()+"sss");
		List<Record> list = permissionsServer.permissionsSelectFile(user,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/doctor/doctor-list.jsp");
	}
	/**
	 * 员工和管理员角色替换
	 */
	public void roleReplace() {
		String roleNo= getPara("roleNo");
		String userNo=getPara("userNo");
		////System.out.println(roleNo+"   "+userNo);
		Record userinfo=permissionsServer.userById(userNo);
		if(roleNo.equals("1")){
			userinfo.set("roleNo", roleNo);
			userinfo.set("roleType","管理员");
		}else if(roleNo.equals("2")){
			userinfo.set("roleNo", roleNo);
			userinfo.set("roleType","员工");
			permissionsServer.demotion(userNo);
		}
		setAttr("falg", permissionsServer.roleReplace(userinfo));
		renderJson();
	}
	
}
