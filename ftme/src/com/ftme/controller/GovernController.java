package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.GovernServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 管理员模块
 * @author wyl
 *
 */
@Clear(FrontInterceptor.class)
public class GovernController extends Controller {
	private GovernServer gServer=new GovernServer();
	private Userinfo userinfo = new Userinfo();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	//跳转到对应的jsp页面
	public void governList(){//管理页面
		setSessionAttr("page", "govern");
		redirect("/view/govern/govern-list.jsp");
//		render("/view/govern/govern-list.jsp");
	}
	public void governAdd(){//添加页面
		render("/view/govern/govern-add.jsp");
	}
	public void governDelList(){//删除页面
		redirect("/view/govern/govern-deletelist.jsp");
	}
	
	// 查询通过验证未被删除的管理员信息
	public void governFile() {
		begin=new Integer(getPara("begin"));
		int count=gServer.governFind(1, 1,null).size();
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = gServer.governFind(1, 1,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/govern/govern-list.jsp");
	}
	
	//条件查询管理员信息
	public void governSelectFile() {
		begin=new Integer(getPara("begin"));
		userinfo.setAccountState(new Integer(getPara("accountState")));
		userinfo.setName(getPara("name").equals("管理员姓名")?" ":getPara("name"));
		userinfo.setPhone(getPara("phone").equals("管理员手机号")?" ":getPara("phone"));
		
		
		int count=gServer.governSelectFile(userinfo,null).size();
		Record fy=wyl.fenyi(count, begin);
//		////System.out.println("sss"+userinfo.getName()+"sss"+userinfo.getUserNo()+"sss"+userinfo.getPhone()+"sss");
		List<Record> list = gServer.governSelectFile(userinfo,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/govern/govern-list.jsp");
	}
	// 查询通过验证被删除的管理员信息
	public void governFileDel() {
		begin=new Integer(getPara("begin"));
		int count=gServer.governFindAccount(1, 3,null).size();
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = gServer.governFindAccount(1, 3,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
//		render("/view/govern/govern-deletelist.jsp");
	}

	// 添加管理员
	public void governSave() {
		userinfo.setId(null);
		userinfo=getModel(Userinfo.class,"user");
		//管理员送样编号
		Userinfo u=getSessionAttr("loginUser");
		userinfo.setUserNo(wyl.getNO());
		userinfo.setRoleNo("1");
		userinfo.setRoleType("管理员");
		if(u!=null){
			userinfo.setUno(u.getUserNo());//管理员编号，是前台选择的值
			userinfo.setAccountState(1);
			userinfo.setEmailState(1);
		}else{
			userinfo.setAccountState(0);
			userinfo.setEmailState(0);
		}
		
		Record user = userinfo.toRecord();
		////System.out.println(userinfo.toString());
		boolean falg= gServer.governSave(user);
//		setAttr("falg", falg);
//		redirect("/view/govern/govern-list.jsp");
		governList();
	}
	
	// 删除管理员
	public void governDelete() {
		String userNo = getPara("userNo");
		Record userinfo = gServer.governById(userNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(userinfo!=null){
			
			userinfo.set("accountState",(userinfo.getInt("accountState")+3));
			falg=gServer.governUpdate(userinfo);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 恢复删除的管理员
	public void governRestore() {
		String userNo = getPara("userNo");
		Record userinfo = gServer.governById(userNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(userinfo!=null){
			userinfo.set("accountState", (userinfo.getInt("accountState")-3));
			falg=gServer.governUpdate(userinfo);			
		}
		setAttr("falg", falg);
		renderJson();
	}

	// 查询单个管理员信息
	public void governById() {
		String userNo = getPara("userNo");
		setAttr("user", gServer.governById(userNo));
		render("/view/govern/govern-upp.jsp");
	}
	
	// 修改管理员
	public void governUpdate() {
		userinfo.setId(null);
		userinfo=getModel(Userinfo.class,"user");
		
		Userinfo u=getSessionAttr("loginUser");
		userinfo.setUno(u.getUserNo());
		////System.out.println(userinfo.toString());
		Record user = userinfo.toRecord();
		gServer.governUpdate(user);
		governList();
	}
	
	// 验证账号是否正确
	public void ajaxusername() {
		Integer id=new Integer(getPara("id"));
		String username = getPara("username");
		List<Record> list=gServer.ajaxusername(username);
//		////System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
	
	// 验证邮箱是否正确
	public void ajaxEmail() {
		Integer id=new Integer(getPara("id"));
		String email = getPara("email");
		List<Record> list=gServer.ajaxEmail(email);
//		////System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
	// 验证手机号是否正确
	public void ajaxPhone() {
		Integer id=new Integer(getPara("id"));
		String phone = getPara("phone");
		List<Record> list=gServer.ajaxPhone(phone);
//		////System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
}
