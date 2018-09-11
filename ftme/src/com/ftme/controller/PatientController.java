package com.ftme.controller;

import java.util.List;

import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.PatientServer;
import com.ftme.server.SmsrecordServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

/**
 * 患者信息模块
 * 
 * @author wyl
 */
@Clear(FrontInterceptor.class)
public class PatientController extends Controller {
	private PatientServer pServer = new PatientServer();
	private SmsrecordServer smsServer=new SmsrecordServer();
	private Userinfo userinfo = new Userinfo();
	private WylUtil wyl = new WylUtil();
	// private List<Record> list=new ArrayList<Record>();
	private Integer begin;
	
	// 跳转到对应的jsp页面
	public void patientList() {
		List<Record> list1 = pServer.hospitalFind(0);
		List<Record> itemslist=pServer.itemsFindAll();
		List<Record> testresultslist=pServer.testresultsFindAll();
		
		setAttr("itemslist", itemslist);
		setAttr("testresultslist",testresultslist);
		setAttr("list", list1);
		setSessionAttr("page", "patient");
		render("/view/patient/patient-list.jsp");
	}

	public void patientAdd() {// 添加页面
		setAttr("clinicallist", pServer.clinicalFindAll());
		setAttr("itemslist", pServer.itemsFindAll());
		setAttr("list", pServer.hospitalFind(0));
		render("/view/patient/patient-add.jsp");
	}

	public void patientDelList() {
		redirect("/view/patient/patient-deletelist.jsp");
	}

	// 查询通过验证未被删除的患者信息
	public void patientFile() {
		begin = new Integer(getPara("begin"));
		int count = pServer.patientFind(4, 1, null).size();
		Record fy = wyl.fenyi(count, begin);
		List<Record> list = pServer.patientFind(4, 1, fy);
		// //System.out.println(list.size());
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/patient/patient-list.jsp");
	}

	// 条件查询患者信息
	public void patientSelectFile() {
		begin = new Integer(getPara("begin"));

//		userinfo.setUserNo(getPara("userNo"));
		userinfo.setHospitalNo(getPara("hospitalNo"));
		userinfo.setUno(getPara("uno"));
		userinfo.setAccountState(new Integer(getPara("accountState")));
		userinfo.setName(getPara("name").equals("姓名,手机号,编号")?" ":getPara("name"));//判断搜索条件是否是 姓名,手机号,编号
//		//System.out.println(getPara("name").equals("姓名,手机号,编号")+"   getPara(name).equals(姓名,手机号,编号)="+(getPara("name")=="姓名,手机号,编号"));
		userinfo.setPhone(getPara("phone"));
		userinfo.setItemsname(getPara("itemsname"));
		userinfo.setTestresultsname(getPara("testresultsname"));
		String datetime1=getPara("datetime1");
		String datetime2=getPara("datetime2");
		int count = pServer.patientSelectFile(userinfo, null,datetime1,datetime2).size();
		Record fy = wyl.fenyi(count, begin);
		// //System.out.println("sss"+userinfo.getName()+"sss"+userinfo.getUserNo()+"sss"+userinfo.getPhone()+"sss");
		List<Record> list = pServer.patientSelectFile(userinfo, fy,datetime1,datetime2);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/patient/patient-list.jsp");
	}

	// 查询通过验证被删除的患者信息
	public void patientFileDel() {
		begin = new Integer(getPara("begin"));
		int count = pServer.patientFindAccount(4, 3, null).size();
		Record fy = wyl.fenyi(count, begin);
		List<Record> list = pServer.patientFindAccount(4, 3, fy);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/patient/patient-deletelist.jsp");
	}

	// 添加患者
	public void patientSave() {
		String smsFalse=getPara("smsFalse");//是否发送短信  0：发送 1：不发送
		userinfo.setId(null);
		userinfo = getModel(Userinfo.class, "user");
		String [] hospital=userinfo.getHospitalNo().split(",");
		userinfo.setHospitalNo(hospital[0]);
		userinfo.setHospitalname(hospital[1]);
		String [] doctor=userinfo.getUno().split(",");
		userinfo.setUno(doctor[0]);
		userinfo.setDoctorname(doctor[1]);
		userinfo.setPhone(userinfo.getUsername());
		userinfo.setAccountState(1);
		userinfo.setEmailState(1);
//		userinfo.setUserNo(wyl.getPatientNOOrDoctorNO(userinfo.getHospitalNo()));
		userinfo.setRoleNo("4");
		userinfo.setRoleType("患者");
		userinfo.setTestresultsname("暂无检测结果");
		userinfo.setCourierInfo("暂无");
		
		userinfo.setCourierState("发送短信");
		Record user = userinfo.toRecord();
		pServer.patientSave(user);
//		//System.out.println(smsFalse.equals("0")+"   "+smsFalse);
		if(smsFalse.equals("0")){
//			//System.out.println("发送短信");
			Record smsrecord=wyl.sendSMS(user, 2);
			smsServer.smsrecordSave(smsrecord);
		}
		
		patientList();
	}
	
	//	检测报告下来后发送快递后，进行发送短信
	public void smsDocument() {
		String userNo = getPara("userNo");
		Record user = pServer.patientById(userNo);
		user.set("courierState","短信已发送");
		pServer.patientUpdate(user);
		Record smsrecord=wyl.sendSMS(user, 3);
		smsServer.smsrecordSave(smsrecord);
//		renderNull();
		patientList();
	}

	// 删除患者
	public void patientDelete() {
		String userNo = getPara("userNo");
		Record userinfo = pServer.patientById(userNo);
		boolean falg = false;
		// 当有这个数据的时候进行修改删除
		if (userinfo != null) {
			userinfo.set("accountState", (userinfo.getInt("accountState") + 3));
			falg = pServer.patientUpdate(userinfo);
		}
		setAttr("falg", falg);
		renderJson();
	}
	
	// 删除患者
	public void patientCompletelyDelete() {
		String userNo = getPara("userNo");
		Record userinfo = pServer.patientById(userNo);
		boolean falg = false;
		// 当有这个数据的时候进行修改删除
		if (userinfo != null) {
			userinfo.set("accountState", (userinfo.getInt("accountState") + 3));
			falg = pServer.patientCompletelyDelete(userinfo);
		}
		setAttr("falg", falg);
		renderJson();
	}

	// 恢复删除的患者
	public void patientRestore() {
		String userNo = getPara("userNo");
		Record userinfo = pServer.patientById(userNo);
		boolean falg = false;
		// 当有这个数据的时候进行修改删除
		if (userinfo != null) {
			userinfo.set("accountState", (userinfo.getInt("accountState") - 3));
			falg = pServer.patientUpdate(userinfo);
		}
		setAttr("falg", falg);
		renderJson();
	}

	// 查询单个患者信息
	public void patientById() {
		String userNo = getPara("userNo");
		Record u = pServer.patientById(userNo);
//		//System.out.println("patientById=" + u.toJson());
		List<Record> list1 = pServer.hospitalFind(0);//医院集合
		List<Record> list2 = pServer.doctorFineAll(u.getStr("hospitalNo"));//某个医院的医生集合
		List<Record> itemslist=pServer.itemsFindAll();
		List<Record> testresultslist=pServer.testresultsFindAll();
		setAttr("clinicallist", pServer.clinicalFindAll());
		setAttr("list", list1);
		setAttr("list2", list2);
		setAttr("itemslist", itemslist);
		setAttr("testresultslist",testresultslist);
		setAttr("user", u);
		render("/view/patient/patient-upp.jsp");
	}
	
	//查询单个患者详情
	public void patientByIdDetail() {
		String userNo = getPara("userNo");
		Record u = pServer.patientById(userNo);
		List<Record> itemsFile=pServer.documentFindByDocumentNo(u.getStr("userNo"), "测序结果");
		setAttr("clinicalFile",pServer.documentFindByDocumentNo(u.getStr("userNo"), "临床资料"));
		setAttr("itemsFile",itemsFile);
		setAttr("testresultsFile",pServer.documentFindByDocumentNo(u.getStr("userNo"), "遗传诊断报告"));
		setAttr("falg","false");
//		if(u.get("courierInfo")!=null&&itemsFile.size()>0){
//			setAttr("falg", !u.getStr("courierInfo").equals("暂无"));
//		}
		if(itemsFile.size()>0){
			setAttr("falg",true);
		}
		setAttr("user", u);
		render("/view/patient/patient-detail.jsp");
	}
	// 修改患者
	public void patientUpdate() {
		
		String name=getPara("name");
		String phone=getPara("phone");
		
		userinfo.setId(null);
		userinfo = getModel(Userinfo.class, "user");
//		//System.out.println(userinfo.toString());
		String [] hospital=userinfo.getHospitalNo().split(",");
		userinfo.setHospitalNo(hospital[0]);
		userinfo.setHospitalname(hospital[1]);
		String [] doctor=userinfo.getUno().split(",");
		userinfo.setUno(doctor[0]);
		userinfo.setDoctorname(doctor[1]);
		userinfo.setPhone(userinfo.getUsername());
		Record user = userinfo.toRecord();
		
		if(!userinfo.getName().equals(name)||!userinfo.getPhone().equals(phone)){
			pServer.documentUppNameOrPhone(userinfo);
			pServer.patientUpdate(user);
		}else{
			pServer.patientUpdate(user);
		}
		
		patientList();
	}

	// 二级联动获取该医院的医生信息
	public void doctorFineAll() {
		String hospitalNo = getPara("hospitalNo");
		List<Record> list = pServer.doctorFineAll(hospitalNo);
		setAttr("list", list);
		setAttr("count", list.size());
		renderJson();
	}

	// 三级联动获取该医生下的的患者信息
	public void patientFineAll() {
		String uno = getPara("uno");
		List<Record> list = pServer.patientFineAll(uno);
		setAttr("list", list);
		setAttr("count", list.size());
		renderJson();
	}

	// 验证账号是否正确
	public void ajaxuserno() {
		String userNo = getPara("userNo");
		boolean falg = pServer.ajaxuserno(userNo);
		// //System.out.println(u!=null);
//		//System.out.println(falg);
		renderJson(falg);
	}
	
	// 验证账号是否正确
	public void ajaxusername() {
		Integer id = new Integer(getPara("id"));
		String username = getPara("username");
		List<Record> list = pServer.ajaxusername(username);
		// //System.out.println(u!=null);
		boolean falg = wyl.ck(list, id);
//		//System.out.println(falg);
		renderJson(falg);
	}

	// 验证邮箱是否正确
	public void ajaxEmail() {
		Integer id = new Integer(getPara("id"));
		String email = getPara("email");
		List<Record> list = pServer.ajaxEmail(email);
		// //System.out.println(u!=null);
		boolean falg = wyl.ck(list, id);
		renderJson(falg);
	}

	// 验证手机号是否正确
	public void ajaxPhone() {
		Integer id = new Integer(getPara("id"));
		String phone = getPara("phone");
		List<Record> list = pServer.ajaxPhone(phone);
		// //System.out.println(u!=null);
		boolean falg = wyl.ck(list, id);
		renderJson(falg);
	}

}
