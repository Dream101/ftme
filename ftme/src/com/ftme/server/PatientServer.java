package com.ftme.server;

import java.util.ArrayList;
import java.util.List;

import com.ftme.common.model.Userinfo;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class PatientServer {
	/**
	 * 获取对应角色正常状态的集合
	 * 
	 * @param roleNo
	 *            角色：0=超级管理员，1=管理员，2=员工，3=医生,4=患者
	 * @param accountState
	 *            账号状态：0未审批，1审批通过，2审批未通过，大于等于3的都是被删除的（当前状态加3为删除状态
	 * @return 根据传递的参数返回对应的集合
	 */
	public List<Record> patientFind(int roleNo, int accountState, Record fy) {
		String limit="";
		if(fy!=null){
			limit=" limit  "+ fy.getInt("begins") + "," + fy.getInt("amount");
		}
		////System.out.println("获取对应角色正常状态的集合patientFind= "+roleNo+"  "+accountState);
//		if (accountState < 3) {
		return Db.find( "select * from userinfo where roleNo=? and accountState=? order by id desc "+limit, roleNo, accountState);
//		} else {
//			return Db.find(
//					"select * from userinfo where roleNo=? and accountState<? order by id desc "+limit,
//					roleNo, accountState);
//		}

	}
	/**
	 * 前台医生获取对应的患者信息
	 */
	public List<Record> doctorPatientFind(int roleNo, int accountState,String uno, Record fy) {
		String limit="";
		if(fy!=null){
			limit=" limit  "+ fy.getInt("begins") + "," + fy.getInt("amount");
		}
		////System.out.println("获取对应角色正常状态的集合patientFind= "+roleNo+"  "+accountState);
//		if (accountState < 3) {
		return Db.find( "select * from userinfo where roleNo=? and accountState=? and uno='"+uno+"' order by id desc "+limit, roleNo, accountState);
//		} else {
//			return Db.find(
//					"select * from userinfo where roleNo=? and accountState<? order by id desc "+limit,
//					roleNo, accountState);
//		}

	}
	/**
	 * 前台医生模块条件进行查询获取对应的患者信息
	 */
	public List<Record> doctorPatientSelectFile(Userinfo user, Record fy,String uno,String datetime1,String datetime2) {
		String limit="";
		if(fy!=null){
			limit=" limit  "+ fy.getInt("begins") + "," + fy.getInt("amount");
		}
		// 条件：通过三元运算符来将条件拼接好
		String condition = (user.getAccountState() == -1 ? "3" : user.getAccountState())
				+(" and uno='"+uno+"'")
				+ (user.getHospitalNo().equals("-1") ? "" : " and hospitalNo = '" + user.getHospitalNo() + "'")
				+ (user.getItemsname().equals("-1") ? "" : " and itemsname = '" + user.getItemsname() + "'")
				+ (user.getTestresultsname().equals("-1") ? "" : " and testresultsname = '" + user.getTestresultsname() + "'")
				+ (user.getUno().equals("-1") ? "" : " and uno = '" + user.getUno() + "' ")
				+ (datetime1.equals(" ") ? "" : " and datetime > '" + datetime1 + "' ")
				+ (datetime2.equals(" ") ? "" : " and datetime < '" + datetime2 + "' ")
				+ (user.getName().equals(" ") ? "" : " and (userNo like '%"+ user.getName() + "%'")
				+ (user.getName().equals(" ") ? "" : " or name like '%" + user.getName() + "%'")
				+ (user.getName().equals(" ")  ? "" : " or phone like '%" + user.getName() + "%')")
				+" order by id desc "+ limit;
		////System.out.println("拼接的条件="+condition);
		if (user.getAccountState() == -1) {
			return Db
					.find("select * from userinfo where roleNo=4 and accountState<"
							+ condition);
		} else {
			return Db
					.find("select * from userinfo where roleNo=4 and accountState="
							+ condition);
		}
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
	public List<Record> patientFindAccount(int roleNo, int accountState, Record fy) {
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
	public List<Record> patientSelectFile(Userinfo user, Record fy,String datetime1,String datetime2) {
		String limit="";
		if(fy!=null){
			limit=" limit  "+ fy.getInt("begins") + "," + fy.getInt("amount");
		}
		// 条件：通过三元运算符来将条件拼接好
		String condition = (user.getAccountState() == -1 ? "3" : user.getAccountState())
				+ (user.getHospitalNo().equals("-1") ? "" : " and hospitalNo = '" + user.getHospitalNo() + "'")
				+ (user.getItemsname().equals("-1") ? "" : " and itemsname = '" + user.getItemsname() + "'")
				+ (user.getTestresultsname().equals("-1") ? "" : " and testresultsname = '" + user.getTestresultsname() + "'")
				+ (user.getUno().equals("-1") ? "" : " and uno = '" + user.getUno() + "' ")
				+ (datetime1.equals(" ") ? "" : " and datetime > '" + datetime1 + "' ")
				+ (datetime2.equals(" ") ? "" : " and datetime < '" + datetime2 + "' ")
				+ (user.getName().equals(" ") ? "" : " and (userNo like '%"+ user.getName() + "%'")
				+ (user.getName().equals(" ") ? "" : " or name like '%" + user.getName() + "%'")
				+ (user.getName().equals(" ")  ? "" : " or phone like '%" + user.getName() + "%')")
				+" order by id desc "+ limit;
		////System.out.println("拼接的条件="+condition);
		if (user.getAccountState() == -1) {
			return Db
					.find("select * from userinfo where roleNo=4 and accountState<"
							+ condition);
		} else {
			return Db
					.find("select * from userinfo where roleNo=4 and accountState="
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
	public Record patientById(String userNo) {
		List<Record> list = Db.find("select * from userinfo where userNo=? ",
				userNo);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 获取某个用户有关的文件
	 * @param userNo		用户编号
	 * @param documentType	文件类型
	 * @return
	 */
	public List<Record> documentFindByDocumentNo(String userNo,String documentType) {
		
		List<Record> list= Db.find("select * from documentinfo where userNo= ? and documentType=?",userNo,documentType);
		////System.out.println(documentType+"  "+list.size());
		if(list.size()==0){
			return new ArrayList<Record>();
		}else{
			return list;
		}
		
	}
	
	/**
	 * 添加患者信息
	 * 
	 * @param user
	 *            添加的内容
	 * @return
	 */
	public boolean patientSave(Record user) {
		return Db.save("userinfo", user);
	}

	
	/**
	 * 修改患者信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean patientUpdate(Record user) {
		return Db.update("userinfo", user);
	}

	/**
	 * 删除患者信息（伪删除将其账号状态加3：大于等于3的都是被删除的）
	 * 
	 * @param user
	 * @return
	 */
	public boolean patientCompletelyDelete(Record user) {
		return Db.delete("userinfo", user);
	}
	
	/**
	 * 删除患者信息（伪删除将其账号状态加3：大于等于3的都是被删除的）
	 * 
	 * @param user
	 * @return
	 */
	public boolean patientDelete(Record user) {
		return Db.update("userinfo", user);
	}
	
	/**
	 * 验证编号是否唯一
	 * 
	 * @param username
	 * @return
	 */
	public boolean ajaxuserno(String userno) {
		Integer count =Db.find("select id from userinfo where userno=?", userno).size();
		return count>0?false:true;
	}
	
	/**
	 * 验证账号是否唯一
	 * 
	 * @param username
	 * @return
	 */
	public List<Record> ajaxusername(String username) {
		List<Record> list = Db.find("select * from userinfo where username='"+username+"'");
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
	
//	--------------------------------------------------------------------与之关联的数据查询----------------------------------------------------------------------------
	/**
	 * 获取对应状态的的医院信息
	 * @param hospitalState 医院状态:0可用，1不可以用
	 * @return
	 */
	public List<Record> hospitalFind(Integer hospitalState) {
			return Db.find("select * from hospital where hospitalState=? order by id desc",hospitalState);
	}
	
	/**
	 * 获取所有对应医院医生的信息
	 * @param hospitalNo	医院编号
	 * @return
	 */
	public List<Record> doctorFineAll(String hospitalNo) {
		////System.out.println("医生的信息patientFineAll="+hospitalNo);
		return Db.find("select * from userinfo where roleNo=3 and accountState=1 and hospitalNo = '"+hospitalNo+"' order by id desc");
	}
	
	/**
	 * 获取所有对应医生的患者信息
	 * @param uno	对应的医生编号
	 * @return
	 */
	public List<Record> patientFineAll(String uno) {
		////System.out.println("医生的信息patientFineAll="+uno);
		return Db.find("select * from userinfo where roleNo=4 and accountState=1 and uno = '"+uno+"' order by id desc");
	}
	
	/**
	 * 修改患者信息时，如果姓名和电话有改动，那么该患者的所有的文档上传记录中的姓名和电话也需要改动
	 * @return
	 */
	public boolean documentUppNameOrPhone(Userinfo userinfo){
		return Db.update("update documentinfo set name='"+userinfo.getName()+"',phone='"+userinfo.getPhone()+"' where userNo='"+userinfo.getUserNo()+"'")>0;
	}
	
	/**
	 * 获取所有可用的临床诊断
	 * @return
	 */
	public List<Record> clinicalFindAll() {
		return Db.find("select * from clinical where clinicalState=? order by id desc ",0);
	}
	
	/**
	 * 获取所有可用的基因检测项目
	 * @return
	 */
	public List<Record> itemsFindAll() {
		return Db.find("select * from items where itemsState=? order by id desc ",0);
	}
	
	/**
	 * 获取所有可用的基因检测结果
	 * @return
	 */
	public List<Record> testresultsFindAll() {
		return Db.find("select * from testresults where testresultsState=? order by id desc ",0);
	}
}
