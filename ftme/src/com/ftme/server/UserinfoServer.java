package com.ftme.server;

import java.util.List;

import com.ftme.common.model.Userinfo;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class UserinfoServer {
	
	//修改密码
	public int pwdUpdate(Userinfo userinfo){
		Boolean falg=Db.update("userinfo", userinfo.toRecord());
		int count=0;
		if(falg){
			count=1;
		}
		return count;
	}
	
	//修改密码
	public Record retrieveCKPwd(String username,String name){
		List<Record> list=Db.find("select * from userinfo where username='"+username+"' and name='"+name+"'");
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
}
