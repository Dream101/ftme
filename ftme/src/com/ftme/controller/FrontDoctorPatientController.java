package com.ftme.controller;

import java.io.File;
import java.util.List;

import com.ftme.common.model.Documentinfo;
import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.interceptor.LoginInterceptor;
import com.ftme.server.DocumentServer;
import com.ftme.server.PatientServer;
import com.ftme.server.SmsrecordServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;

/**
 * 前台医生模块
 * 
 * @author wyl
 */
@Clear(LoginInterceptor.class)
public class FrontDoctorPatientController extends Controller {
	private PatientServer pServer = new PatientServer();
	private SmsrecordServer smsServer=new SmsrecordServer();
	private DocumentServer dServer = new DocumentServer();// 文件业务层
	private Userinfo userinfo = new Userinfo();
	private WylUtil wyl = new WylUtil();
	// private List<Record> list=new ArrayList<Record>();
	private Integer begin;
	
	// 跳转到对应的jsp页面
	public void frontList() {
		List<Record> itemslist=pServer.itemsFindAll();
		List<Record> testresultslist=pServer.testresultsFindAll();
		
		setAttr("itemslist", itemslist);
		setAttr("testresultslist",testresultslist);
		render("/view/front/doctor.jsp");
	}
	//跳转到上传页面
	public void frontUpload() {
		String userNo = getPara("userNo");
		Record u = pServer.patientById(userNo);
		setAttr("user", u);
		render("/view/front/doctor-patient-Upload.jsp");
	}

	// 查询通过验证未被删除的患者信息
	public void frontFile() {
		begin = new Integer(getPara("begin"));
		Userinfo u = getSessionAttr("loginUser");
		int count = pServer.doctorPatientFind(4, 1,u.getUserNo(), null).size();
		Record fy = wyl.fenyi(count, begin);
		List<Record> list = pServer.doctorPatientFind(4, 1,u.getUserNo(), fy);
		// ////System.out.println(list.size());
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/front/front-list.jsp");
	}

	// 条件查询患者信息
	public void frontSelectFile() {
		begin = new Integer(getPara("begin"));
		Userinfo u = getSessionAttr("loginUser");

//		userinfo.setUserNo(getPara("userNo"));
		userinfo.setHospitalNo(getPara("hospitalNo"));
		userinfo.setUno(getPara("uno"));
		userinfo.setAccountState(new Integer(getPara("accountState")));
		userinfo.setName(getPara("name").equals("姓名,手机号,编号")?" ":getPara("name"));//判断搜索条件是否是 姓名,手机号,编号
		userinfo.setPhone(getPara("phone"));
		userinfo.setItemsname(getPara("itemsname"));
		userinfo.setTestresultsname(getPara("testresultsname"));
		String datetime1=getPara("datetime1");
		String datetime2=getPara("datetime2");
		
		int count = pServer.doctorPatientSelectFile(userinfo, null,u.getUserNo(),datetime1,datetime2).size();
		Record fy = wyl.fenyi(count, begin);
		// ////System.out.println("sss"+userinfo.getName()+"sss"+userinfo.getUserNo()+"sss"+userinfo.getPhone()+"sss");
		List<Record> list = pServer.doctorPatientSelectFile(userinfo, fy,u.getUserNo(),datetime1,datetime2);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
		// render("/view/front/front-list.jsp");
	}
	
	//下載
	public void download() {
		String path = getPara("uploadPath");
//		String picurl = getPara("picurl");
		// renderText("success");
		File file = new File(path);
		if (file.exists()) {
			renderFile(file);
			return;
		}
		render(new JsonRender().forIE());
	}

	// 添加方法
	public void documentFileAdd() {
		boolean falg = false;
		UploadFile file = getFile("file");
		String newName = new WylUtil().getPath(file.getFileName());

		String filePath = file.getUploadPath() + "/" + newName;// 要将文件更改到那个地方，那个名字
		String path = filePath.replace("\\", "/");// 将文件中\\换成/

		////System.out.println("new File==  " + path);
		file.getFile().renameTo(new File(path));// 更改文件名称

		String url = "/uplode/" + newName;// /uplode/2016121503182159.docx
		////System.out.println("url  =" + url);

		Record u = dServer.staffById(getPara("userNo"));
		Userinfo userinfo = getSessionAttr("loginUser");
		////System.out.println(u.toString());
		 Documentinfo document = new Documentinfo();
		if (u != null && userinfo != null) {
			document.setDocumentNo(wyl.getNO());
			document.setUserNo(getPara("userNo"));
//			String[] hospital = getPara("hospitalNo").split(",");
			document.setHospitalNo(u.getStr("hospitalNo"));
			document.setHospitalname(u.getStr("hospitalname"));
			document.setDoctorNo(u.getStr("uno"));
			document.setDoctorname(u.getStr("doctorname"));
			document.setName(u.getStr("name"));
			document.setPhone(u.getStr("phone"));
			document.setDocumentType(getPara("documentType"));
			document.setDocumentName(file.getFileName());
			document.setDocumentUploadName(newName);
			document.setUploadPath(path);
			document.setDocumentPath(url);
			document.setAdmin(userinfo.getStr("userNo"));
			document.setRemark(getPara("remark"));
			document.setDocumentState("0");
			
			////System.out.println("document  =" + document.toString());
			
			falg = dServer.documentinfoSave(document.toRecord());
		} else {
			File f = new File(filePath);
			f.delete();
			falg = false;
			////System.out.println("删除文件");
		}
		setAttr("falg", falg);
		render(new JsonRender().forIE());
	}
	
	// 查询单个患者信息
	public void frontById() {
		String userNo = getPara("userNo");
		Record u = pServer.patientById(userNo);
//		////System.out.println("frontById=" + u.toJson());
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
		render("/view/front/front-upp.jsp");
	}
	
	//查询单个患者详情
	public void frontByIdDetail() {
		String userNo = getPara("userNo");
		Record u = pServer.patientById(userNo);
//		List<Record> itemsFile=pServer.documentFindByDocumentNo(u.getStr("userNo"), "测序结果");
		setAttr("clinicalFile",pServer.documentFindByDocumentNo(u.getStr("userNo"), "临床资料"));
//		setAttr("itemsFile",itemsFile);
		setAttr("testresultsFile",pServer.documentFindByDocumentNo(u.getStr("userNo"), "遗传诊断报告"));
//		setAttr("falg","false");
//		if(u.get("courierInfo")!=null&&itemsFile.size()>0){
//			setAttr("falg", !u.getStr("courierInfo").equals("暂无"));
//		}
		setAttr("user", u);
		render("/view/front/doctor-patient-detail.jsp");
	}
	// 修改患者
	public void frontUpdate() {
		
		String name=getPara("name");
		String phone=getPara("phone");
		
		userinfo.setId(null);
		userinfo = getModel(Userinfo.class, "user");
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
		
		frontList();
	}
}
