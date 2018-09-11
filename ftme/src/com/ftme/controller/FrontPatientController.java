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
 * 前台患者功能模块
 * 
 * @author wyl
 */
@Clear(LoginInterceptor.class)
public class FrontPatientController extends Controller {
	private PatientServer pServer = new PatientServer();
	private DocumentServer dServer = new DocumentServer();// 文件业务层
	private Userinfo userinfo = new Userinfo();
	private WylUtil wyl = new WylUtil();
	// private List<Record> list=new ArrayList<Record>();
	private Integer begin;
	
	// 跳转到对应的jsp页面
	public void frontPatientList() {
		Userinfo u = getSessionAttr("loginUser");//首页写好后从，session里面取值
//		String userNo = getPara("userNo");
//		Record u = pServer.patientById(userNo);
		setAttr("testresultsFile",pServer.documentFindByDocumentNo(u.getStr("userNo"), "遗传诊断报告"));
		setAttr("clinicalFile",pServer.documentFindByDocumentNo(u.getStr("userNo"), "临床资料"));
		setAttr("user", u);
		render("/view/front/patient.jsp");
	}
	//跳转到上传页面
	public void frontUpload() {
		String userNo = getPara("userNo");
		Record u = pServer.patientById(userNo);
		setAttr("user", u);
		render("/view/front/doctor-patient-Upload.jsp");
	}

	//下載
	public void download() {
		////System.out.println("download");
		String path = getPara("uploadPath");
//		String picurl = getPara("picurl");
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

		Userinfo u = getSessionAttr("loginUser");
//		Record u = dServer.staffById(getPara("userNo"));
//		Record u = dServer.staffById("dw1234143124");
		////System.out.println(u.toString());
		 Documentinfo document = new Documentinfo();
		if (u != null ) {
			document.setDocumentNo(wyl.getNO());
			document.setUserNo(u.getStr("userNo"));
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
			document.setAdmin(u.getStr("userNo"));
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
	
	//查询单个患者详情
	public void frontByIdDetail() {
		String userNo = getPara("userNo");
		Record u = pServer.patientById(userNo);
//		List<Record> itemsFile=pServer.documentFindByDocumentNo(u.getStr("userNo"), "测序结果");
//		setAttr("clinicalFile",pServer.documentFindByDocumentNo(u.getStr("userNo"), "临床资料"));
//		setAttr("itemsFile",itemsFile);
		setAttr("testresultsFile",pServer.documentFindByDocumentNo(u.getStr("userNo"), "遗传诊断报告"));
//		setAttr("falg","false");
//		if(u.get("courierInfo")!=null&&itemsFile.size()>0){
//			setAttr("falg", !u.getStr("courierInfo").equals("暂无"));
//		}
		setAttr("user", u);
		render("/view/front/doctor-patient-detail.jsp");
	}
	
}
