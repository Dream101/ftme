package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.LoginServer;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
/**
 * 登录退出模块
 * @author wyl
 *
 */
@Clear
public class LoginController extends Controller {
	private LoginServer loginServer = new LoginServer();
	
	public void index(){
		render("/login.jsp");
	}
	
	// 后台登录
	public void login() {
		String username = getPara("username");
		String pwd = getPara("pwd");
		int flag = loginServer.login(username, pwd);
		////System.out.println(username+"  "+ pwd+flag);
		setAttr("flag", flag);
		switch (flag) {
		case 0:
			setAttr("errorinfo","账号错误！请重新输入");
			render("/login.jsp");
			break;
		case 1:
			setAttr("username", username);
			setAttr("errorinfo","密码错误！请重新输入");
			render("/login.jsp");
			break;
		case 2:
			setSessionAttr("loginUser", loginServer.userinfo);
			redirect("/view/main.jsp");
			break;
		}
	}
	
	//前台登录
	public void frontLogin() {
		String username = getPara("username");
		String pwd = getPara("pwd");
		List<Userinfo> list = loginServer.frontLogin(username, pwd);
		////System.out.println(list.toString());
		if(list.size()>0){
			setSessionAttr("loginUser",list.get(0));
			renderJson(list.get(0).getRoleNo());
		}else{
			redirect("/doctorOrPatientLogin.jsp");
		}
		
		
	}
	
	/**
	 * 退出
	 */
	@Clear
	public void exit(){
		String type= getPara("type");
		removeSessionAttr("loginUser");
		if(type!=null){
			frontLogin();
		}else{
			renderJson(true);
		}
	}
	
}
