package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.DoctorServer;
import com.ftme.server.PatientServer;
import com.ftme.server.SmsrecordServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 医生模块
 * @author wyl
 *
 */
@Clear(FrontInterceptor.class)
public class DoctorController extends Controller {
	private SmsrecordServer smsServer=new SmsrecordServer();
	private DoctorServer dServer=new DoctorServer();
	private Userinfo userinfo = new Userinfo();
	private WylUtil wyl=new WylUtil();
	private Integer begin;
	//跳转到对应的jsp页面
	public void doctorList(){//管理页面
		List<Record> list1=dServer.hospitalFind(0);
		setAttr("list", list1);
		setSessionAttr("page", "doctor");
//		redirect("/view/doctor/doctor-list.jsp");
		render("/view/doctor/doctor-list.jsp");
	}
	public void doctorAdd(){//添加页面
		List<Record> list1=dServer.hospitalFind(0);
		setAttr("list", list1);
		render("/view/doctor/doctor-add.jsp");
	}
	public void doctorDelList(){//删除页面
		redirect("/view/doctor/doctor-deletelist.jsp");
	}
	
	// 查询通过验证未被删除的医生信息
	public void doctorFile() {
		begin=new Integer(getPara("begin"));
		int count=dServer.doctorFind(3, 3,null).size();
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = dServer.doctorFind(3, 3,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/doctor/doctor-list.jsp");
	}
	
	//条件查询医生信息
	public void doctorSelectFile() {
		begin=new Integer(getPara("begin"));
		userinfo.setHospitalNo(getPara("hospitalNo"));
		userinfo.setUserNo(getPara("userNo"));
		userinfo.setAccountState(new Integer(getPara("accountState")));
		userinfo.setName(getPara("name").equals("医生姓名")?" ":getPara("name"));
		userinfo.setPhone(getPara("phone").equals("医生手机号")?" ":getPara("phone"));
		
		
		int count=dServer.doctorSelectFile(userinfo,null).size();
		Record fy=wyl.fenyi(count, begin);
//		//System.out.println("sss"+userinfo.getName()+"sss"+userinfo.getUserNo()+"sss"+userinfo.getPhone()+"sss");
		List<Record> list = dServer.doctorSelectFile(userinfo,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/doctor/doctor-list.jsp");
	}
	// 查询通过验证被删除的医生信息
	public void doctorFileDel() {
		begin=new Integer(getPara("begin"));
		int count=dServer.doctorFindAccount(3, 3,null).size();
		Record fy=wyl.fenyi(count, begin);
		List<Record> list = dServer.doctorFindAccount(3, 3,fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
//		render("/view/doctor/doctor-deletelist.jsp");
	}

	// 添加医生
	public void doctorSave() {
		String smsFalse=getPara("smsFalse");//是否发送短信  0：发送 1：不发送
		userinfo.setId(null);
		userinfo=getModel(Userinfo.class,"user");
		//医生送样编号
		Userinfo u=getSessionAttr("loginUser");
		String [] str=userinfo.getHospitalNo().split(",");
		userinfo.setHospitalNo(str[0]);
		userinfo.setHospitalname(str[1]);
		userinfo.setUserNo(wyl.getPatientNOOrDoctorNO(userinfo.getHospitalNo()));
		
		userinfo.setRoleNo("3");
		userinfo.setRoleType("医生");
		userinfo.setPhone(userinfo.getUsername());
		
		if(u!=null){
			userinfo.setUno(u.getUserNo());//医生编号，是前台选择的值
			userinfo.setAccountState(1);
			userinfo.setEmailState(1);
		}else{
			userinfo.setAccountState(0);
			userinfo.setEmailState(0);
		}
		
		Record user = userinfo.toRecord();

//		//System.out.println(smsFalse.equals("0")+"   "+smsFalse);
		if(smsFalse.equals("0")){
			Record smsrecord=wyl.sendSMS(user, 2);
			smsServer.smsrecordSave(smsrecord);
		}
		
		boolean falg= dServer.doctorSave(user);
		
		doctorList();
	}
	
	// 删除医生
	public void doctorDelete() {
		String userNo = getPara("userNo");
		Record userinfo = dServer.doctorById(userNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(userinfo!=null){
			userinfo.set("accountState",(userinfo.getInt("accountState")+3));
			falg=dServer.doctorUpdate(userinfo);			
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 恢复删除的医生
	public void doctorRestore() {
		String userNo = getPara("userNo");
		Record userinfo = dServer.doctorById(userNo);
		boolean falg=false;
		//当有这个数据的时候进行修改删除
		if(userinfo!=null){
			userinfo.set("accountState", (userinfo.getInt("accountState")-3));
			falg=dServer.doctorUpdate(userinfo);			
		}
		setAttr("falg", falg);
		renderJson();
	}

	// 查询单个医生信息
	public void doctorById() {
		String userNo = getPara("userNo");
		List<Record> list1=dServer.hospitalFind(0);
		setAttr("list", list1);
		setAttr("user", dServer.doctorById(userNo));
		render("/view/doctor/doctor-upp.jsp");
	}
	
	// 修改医生
	public void doctorUpdate() {
		userinfo.setId(null);
		userinfo=getModel(Userinfo.class,"user");
		String [] hospital=userinfo.getHospitalNo().split(",");
		
		userinfo.setPhone(userinfo.getUsername());
		userinfo.setHospitalNo(hospital[0]);//医院编号
		userinfo.setHospitalname(hospital[1]);//医院名称
		Userinfo u=getSessionAttr("loginUser");
		userinfo.setUno(u.getUserNo());
		
		////System.out.println(userinfo.toString());
		
		Record user = userinfo.toRecord();
		dServer.doctorUpdate(user);
		doctorList();
	}
	
	//二级联动获取该医院的医生信息
	public void doctorFineAll(){
		String hospitalNo=getPara("hospitalNo");
		List<Record> list=dServer.doctorFineAll(hospitalNo);
		setAttr("list", list);
		setAttr("count", list.size());
		renderJson();
	}
	// 验证账号是否正确
	public void ajaxusername() {
		Integer id=new Integer(getPara("id"));
		String username = getPara("username");
		List<Record> list=dServer.ajaxusername(username);
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
	
	// 验证邮箱是否正确
	public void ajaxEmail() {
		Integer id=new Integer(getPara("id"));
		String email = getPara("email");
		List<Record> list=dServer.ajaxEmail(email);
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
	// 验证手机号是否正确
	public void ajaxPhone() {
		Integer id=new Integer(getPara("id"));
		String phone = getPara("phone");
		List<Record> list=dServer.ajaxPhone(phone);
//		//System.out.println(u!=null);
		boolean falg=wyl.ck(list, id);
		renderJson(falg);
	}
}
