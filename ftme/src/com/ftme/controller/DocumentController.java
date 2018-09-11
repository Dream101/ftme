package com.ftme.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ftme.common.model.Documentinfo;
import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.DocumentServer;
import com.ftme.server.HospitalServer;
import com.ftme.server.PatientServer;
import com.ftme.util.WylUtil;
import com.ftme.util.ZIPUtil;
import com.jfinal.aop.Clear;
import com.jfinal.config.JFinalConfig;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;

/**
 * 文档管理模块
 * 
 * @author wyl
 * 
 */
@Clear(FrontInterceptor.class)
public class DocumentController extends Controller {
	private DocumentServer dServer = new DocumentServer();// 文件业务层
	// private HospitalServer hServer=new HospitalServer();//医院业务层
	// private PatientServer pServer=new PatientServer();//患者业务层
	private Documentinfo document = new Documentinfo();
	private List<Record> list = new ArrayList<Record>();
	private WylUtil wyl = new WylUtil();
	private Integer begin;

	// 跳转到对应的jsp页面
	public void documentAdd() {
		List<Record> list1 = dServer.hospitalFind(0);
		// List<Record> plist=pServer.patientFineAll(4, 1);
		setSessionAttr("page", "document");
		setAttr("list", list1);
		render("/view/staff/document/documentAdd.jsp");
	}

	public void documentList() {
		List<Record> list1 = dServer.hospitalFind(0);
		setAttr("list", list1);
		// ////System.out.println(list1.size());
		render("/view/staff/document/documentList.jsp");
	}

	// 查询方法（全查询）
	public void documentFindAll() {
		Userinfo u = getSessionAttr("loginUser");
		begin = new Integer(getPara("begin"));
		int count = dServer.documentFindAll(null, u).size();
		Record fy = wyl.fenyi(count, begin);
		List<Record> list = dServer.documentFindAll(fy, u);
		// ////System.out.println("documentFindAll查询方法（全查询）"+count);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
	}

	// 查询方法（条件查询）
	public void documentFindSel() {
		Userinfo u = getSessionAttr("loginUser");
		String userNo = getPara("userNo");
		String hospitalNo = getPara("hospitalNo");
		String documentType = getPara("documentType");
		String phone = getPara("phone").equals("文档编号,姓名,手机号")?" ":getPara("phone");
		// (userNo.equals("-1") ? "" : " and userNo = '" + userNo + "' ")+
		String condition = (hospitalNo.equals("-1") ? ""
				: " and hospitalNo = '" + hospitalNo + "' ")
				+ (documentType.equals("-1") ? "" : " and documentType = '"
						+ documentType + "' ")
				+ (phone.equals(" ") ? "" : " and (userNo like '%" + phone
						+ "%'")
				+ (phone.equals(" ") ? "" : " or name like '%" + phone + "%'")
				+ (phone.equals(" ") ? "" : " or phone like '%" + phone + "%')");
		////System.out.println(userNo + "  " + hospitalNo + "  " + documentType	+ "  1" + phone + "1");
		////System.out.println(condition);

		begin = new Integer(getPara("begin"));
		int count = dServer.documentFindSel(condition, null, u).size();
		Record fy = wyl.fenyi(count, begin);
		List<Record> list = dServer.documentFindSel(condition, fy, u);
		setAttr("list", list);
		setAttr("count", count);
		setAttr("fy", fy);
		renderJson();
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
			// SmsService s=new SmsService();
			// s.sendSms("15039939677", "135265");
			// s.sendSms(u.getStr("phone"), "654321");
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
	
	// 删除方法
	public void documentDelete() {
		boolean falg = true;
		////System.out.println("删除方法documentDelete=" + getPara("documentNo"));
		Record docu = dServer.documentFindByDocumentNo(getPara("documentNo"));
		if (docu != null) {
			////System.out.println(docu.getStr("uploadPath") + "  "+ docu.getStr("documentPath"));
			File f = new File(docu.getStr("uploadPath"));
			f.delete();
			falg = false;
			////System.out.println("删除文件");
			falg = dServer.documentinfoDelete(docu);
		}
		renderJson(falg);
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
	
	//判断该患者编号是否正确
	public void patientClick(){
		Record u = dServer.staffById(getPara("userNo"));
		////System.out.println(u==null);
		setAttr("falg",u!=null);
		setAttr("user",u);
		renderJson();
	}

	// 二级联动获取该医院的患者信息
	public void patientFineAll() {
		String hospitalNo = getPara("hospitalNo");
		this.list = dServer.patientFineAll(hospitalNo);
		setAttr("list", list);
		setAttr("count", list.size());
		renderJson();
	}

}
