package com.ftme.server;

import java.util.List;

import com.ftme.common.model.Userinfo;

public class LoginServer {
	
	public Userinfo userinfo=null;
	
	//后台登录
	public int login(String username,String pwd){
		List<Userinfo> list=Userinfo.dao.find("select * from userinfo where roleNo<3 and accountState=1 and username=?",username);
		int result=0;//0账号错误：1密码错误;2正确
		for (Userinfo user : list) {
			if(pwd.equals(user.getPassword())){
				result=2;
				userinfo=user;
				break;
			}else{
				result=1;
			}
		}
		return result;
	}
	
	//前台登录
	public List<Userinfo> frontLogin(String username,String pwd){
		List<Userinfo> list=Userinfo.dao.find("select * from userinfo where roleNo>2 and accountState=1 and username=?",username);
		return list;
	}
	
}
