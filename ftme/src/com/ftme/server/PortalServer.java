package com.ftme.server;

import java.util.ArrayList;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

/**
 * 官网-查询logo，基本信息
 * @author wyl
 *
 */
public class PortalServer {
	
	/**
	 * 查找logo
	 * @return
	 */
	public List<Record> findLogo(){
		List<Record> list=Db.find("select * from pictureinfo order by pictureNo asc");
		////System.out.println(list.toString());
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	
	/**
	 * 查找公司简介
	 * @return
	 */
	public Record findcompany(){
		List<Record> list=Db.find("select * from companyinfo");
		////System.out.println(list.toString());
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查找行业动态
	 * @return
	 * 
	 */
	public List<Record> findnews(Record fy){
		List<Record> list=new ArrayList<Record>();
		if(fy!=null){
			list= Db.find("select * from news order by datetime desc limit " + fy.getInt("begins") + "," + fy.getInt("amount"));
		}else{
			list= Db.find("select * from news order by datetime desc");
		}
		////System.out.println(list.size());
		return list;
	}
	/**
	 * 查询某条行业动态
	 * @return
	 */
	public Record blogByNo(String newsNo){
		List<Record> list=Db.find("select * from news where newsNo=?",newsNo);
		////System.out.println(list.toString());
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
