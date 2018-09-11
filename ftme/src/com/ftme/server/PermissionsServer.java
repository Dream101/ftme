package com.ftme.server;

import java.util.ArrayList;
import java.util.List;

import com.ftme.common.model.Userinfo;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class PermissionsServer {
	//-----------------------------------------左侧导航栏--------------------------------------
	/**
	 * 根据不同人员的身份角色不同进行不同左侧导航栏的查询
	 * @param roleNo	角色id	0=超级管理员，1=管理员，2=员工，3=医生,4=患者
	 * @param userNo	用户编号（唯一）
	 * @return			查询的数据
	 */
	public List<Record> permissionsFindList(String roleNo, String userNo) {
		
		if(roleNo.equals("0")){
			////System.out.println("roleNo="+roleNo+"    roleNo.equals('0')"+(roleNo.equals("0")));
			return Db.find("select * from permissions where hierarchy<3 order by hierarchy,id asc");
		}
		else if(roleNo.equals("1")){//管理员需要查询权限表，来查找自己对应的权限
			return Db.find("select * from permissions where hierarchy<3 and permissionsidNo in (select distinct  permissionsidNo from rolepermissions where userNo='govern') order by hierarchy,id asc");
		}
		else if(roleNo.equals("2")){//员工只有上传文件的权限
			return Db.find("select * from permissions where hierarchy<3 and superior in (select distinct  superior from rolepermissions where userNo='staff') order by hierarchy,id asc");
		}else{
			List<Record> list=new ArrayList<Record>();
			return list;
		}
	}
	//-----------------------------------------权限按钮--------------------------------------
	/**
	 * 获取管理员权限模块
	 */
	public List<Record> permissionsHierarchy1() {
		return Db.find("select * from permissions where hierarchy=1 and permissionsidNo in ('patient','staff','doctor','document')");
	}
	/**
	 * 获取管理员所有权限模块下的按钮权限
	 */
	public List<Record> permissionsHierarchy3() {
		return Db.find("select * from permissions where hierarchy=3 and superior in ('patient','staff','doctor','document')");
	}
	
	/**
	 * 该管理员的权限
	 */
	public List<Record> userinfoPermissionsHierarchy(String userNo) {
		return Db.find("select * from rolepermissions where  userNo=?",userNo);
	}
	
	/**
	 * 添加权限
	 * @param permissions
	 * @return
	 */
	public boolean permissionsSave(Record rolepermissions) {
		return Db.save("rolepermissions", rolepermissions);
	}
	/**
	 * 删除权限
	 * @param permissions
	 * @return
	 */
	public boolean permissionsDel(Record rolepermissions) {
		Record recode= Db.findFirst("select * from rolepermissions where superior='"+rolepermissions.get("superior")+"' and userNo='"+rolepermissions.get("userNo")+"' and permissionsidNo='"+rolepermissions.get("permissionsidNo")+"'");
		return Db.delete("rolepermissions", recode);
	}
	
	
	
	
	
	/**
	 * 获取该管理员拥有的权限
	 * @param user
	 * @param page
	 * @return
	 */
	public List<Record> permissionsPageButton(Userinfo user, String page) {
		//管理员需要查询权限表，来查找自己对应的权限
		////System.out.println("获取该管理员拥有的权限----"+user.get("userNo")+"  "+page);
		if(user.getRoleNo().equals("1")){
			return Db.find("select * from rolepermissions where userNo=? and superior=?",user.get("userNo"),page);
		}
		else{
			List<Record> list=new ArrayList<Record>();
			return list;
		}
	}
	
	//-----------------------------------------角色互换--------------------------------------
	/**
	 * 通过条件进行查询
	 * 
	 * @param user
	 * @return
	 */
	public List<Record> permissionsSelectFile(Userinfo user, Record fy) {
		// 条件：通过三元运算符来将条件拼接好
		String limit="";
		if(fy!=null){
			limit=" limit  "+ fy.getInt("begins") + "," + fy.getInt("amount");
		}
		String condition = (user.getAccountState() == -1 ? "3" : user.getAccountState()+ " ")
				+ (user.getRoleNo().equals("-1") ? "" : " and roleNo = '" + user.getRoleNo() + "' ")
				+ (user.getUserNo().equals(" ") ? "" : " and userNo = '" + user.getUserNo() + "' ")
				+ (user.getName().equals(" ") ? "" : " and name like '%" + user.getName() + "%' ")
				+ (user.getPhone().equals(" ") ? "" : " and phone = '" + user.getPhone() + "' ")
				+"order by id desc "+limit;
		////System.out.println(condition);
		if (user.getAccountState() == -1) {
			return Db
					.find("select * from userinfo where accountState<"
							+ condition);
		} else {
			return Db
					.find("select * from userinfo where accountState="
							+ condition);
		}
	}
	/**
	 * 获取某个角色中用户的具体信息
	 * @param userNo  用户id
	 * @return 返回具体的该id的具体内容
	 */
	public Record userById(String userNo) {
		List<Record> list = Db.find("select * from userinfo where userNo=? ", userNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	/**
	 * 角色替换(修改角色)
	 * @param user
	 * @return
	 */
	public boolean roleReplace(Record user) {
		
		return Db.update("userinfo", user);
	}
	/**
	 * 管理员降级的时候拥有的权限会被清空
	 * @param userNo
	 */
	public void demotion(String userNo){
		Db.update("delete from rolepermissions where userNo=?",userNo);
	}
}
