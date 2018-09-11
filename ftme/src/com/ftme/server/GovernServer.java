package com.ftme.server;

import java.util.List;

import com.ftme.common.model.Userinfo;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class GovernServer {
	/**
	 * 获取对应角色正常状态的集合
	 * 
	 * @param roleNo
	 *            角色：0=超级管理员，1=管理员，2=员工，3=医生,4=患者
	 * @param accountState
	 *            账号状态：0未审批，1审批通过，2审批未通过，大于等于3的都是被删除的（当前状态加3为删除状态
	 * @return 根据传递的参数返回对应的集合
	 */
	public List<Record> governFind(int roleNo, int accountState, Record fy) {
		String limit="";
		if(fy!=null){
			limit=" limit  "+ fy.getInt("begins") + "," + fy.getInt("amount");
		}
		////System.out.println("获取对应角色除什么状态外的的集合governFind= "+roleNo+"  "+accountState);
		
		return Db.find( "select * from userinfo where roleNo=? and accountState=? order by id desc "+limit, roleNo, accountState);
		
	}

	/**
	 * 获取对应角色对应状态的集合
	 * 
	 * @param roleNo
	 *            角色：0=超级管理员，1=管理员，2=员工，3=医生,4=患者
	 * @param accountState
	 *            账号状态：0未审批，1审批通过，2审批未通过，大于等于3的都是被删除的（当前状态加3为删除状态）
	 * @return 根据传递的参数返回对应的集合
	 */
	public List<Record> governFindAccount(int roleNo, int accountState, Record fy) {
		String limit="";
		if(fy!=null){
			limit=" limit  "+ fy.getInt("begins") + "," + fy.getInt("amount");
		}
		if (accountState < 3) {
			return Db.find(
					"select * from userinfo where roleNo=? and accountState=? order by id desc "+limit,
					roleNo, accountState);
		} else {
			return Db.find(
					"select * from userinfo where roleNo=? and accountState>=? order by id desc "+limit,
					roleNo, accountState);
		}
	}

	/**
	 * 通过条件进行查询
	 * 
	 * @param user
	 * @return
	 */
	public List<Record> governSelectFile(Userinfo user, Record fy) {
		// 条件：通过三元运算符来将条件拼接好
		String limit="";
		if(fy!=null){
			limit=" limit  "+ fy.getInt("begins") + "," + fy.getInt("amount");
		}
		String condition = (user.getAccountState() == -1 ? "3" : user.getAccountState()+ " ")
				+ (user.getName().equals(" ") ? "" : " and name like '%" + user.getName() + "%' ")
				+ (user.getPhone().equals(" ") ? "" : " and phone = '" + user.getPhone() + "' order by id desc ")+limit;
		////System.out.println(condition);
		if (user.getAccountState() == -1) {
			return Db
					.find("select * from userinfo where roleNo=1 and accountState<"
							+ condition);
		} else {
			return Db
					.find("select * from userinfo where roleNo=1 and accountState="
							+ condition);
		}
	}

	

	/**
	 * 获取某个角色中用户的具体信息
	 * 
	 * @param userNo
	 *            用户id
	 * @return 返回具体的该id的具体内容
	 */
	public Record governById(String userNo) {
		List<Record> list = Db.find("select * from userinfo where userNo=? ",
				userNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 添加管理员信息
	 * 
	 * @param user
	 *            添加的内容
	 * @return
	 */
	public boolean governSave(Record user) {
		return Db.save("userinfo", user);
	}

	/**
	 * 修改管理员信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean governUpdate(Record user) {
		return Db.update("userinfo", user);
	}

	/**
	 * 删除管理员信息（伪删除将其账号状态加3：大于等于3的都是被删除的）
	 * 
	 * @param user
	 * @return
	 */
	public boolean governDelete(Record user) {
		return Db.update("userinfo", user);
	}
	
	/**
	 * 验证账号是否唯一
	 * 
	 * @param username
	 * @return
	 */
	public List<Record> ajaxusername(String username) {
		List<Record> list = Db.find("select * from userinfo where username=?",
				username);
		return list;
	}

	/**
	 * 验证账号是否唯一
	 * 
	 * @param email
	 * @return
	 */
	public List<Record> ajaxEmail(String email) {
		List<Record> list = Db.find("select * from userinfo where email=?",
				email);
		return list;
	}

	/**
	 * 验证手机号是否唯一
	 * 
	 * @param phone
	 * @return
	 */
	public List<Record> ajaxPhone(String phone) {
		List<Record> list = Db.find("select * from userinfo where phone=?",
				phone);
		return list;
	}
	
}
