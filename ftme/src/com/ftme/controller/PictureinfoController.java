package com.ftme.controller;

import java.io.File;

import com.ftme.common.model.Companyinfo;
import com.ftme.common.model.Pictureinfo;
import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.PictureinfoServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.JsonRender;
import com.jfinal.upload.UploadFile;
/**
 * 前台logo图片管理
 * @author wyl
 *
 */
@Clear(FrontInterceptor.class)
public class PictureinfoController extends Controller {
	
	private PictureinfoServer pictureinfoServer=new PictureinfoServer();
	private Pictureinfo pictureinfo=new Pictureinfo();
	//跳转到对应的jsp页面
	public void pictureinfo(){
		setSessionAttr("pictureList", pictureinfoServer.pictureinfoFind());
		redirect("/view/privilege/pictureinfo.jsp");
	}
	// 查询要修改的logo信息
	public void pictureinfoByFind() {
		String pictureNo=getPara("pictureNo");
		setSessionAttr("picture", pictureinfoServer.pictureinfoByFind(pictureNo));
		redirect("/view/privilege/pictureinfo-upp.jsp");
	}
	/**
	 * 修改
	 */
	public void pictureinfoFileUpdate(){
//		//System.out.println(getPara("pictureNo"));
//		//System.out.println(getPara("id"));
//		//System.out.println(getPara("picturePath"));
//		//System.out.println(getPara("picturename"));
//		//System.out.println(getPara("content"));
//		//System.out.println(getPara("file"));
		
		boolean falg = false;
		UploadFile file = getFile("file");
		
		String newName = new WylUtil().getPath(file.getFileName());

		String filePath = file.getUploadPath() + "/" + newName;// 要将文件更改到那个地方，那个名字
		String path = filePath.replace("\\", "/");// 将文件中\\换成/

		////System.out.println("new File==  " + path);
		file.getFile().renameTo(new File(path));// 更改文件名称

		String url = "/uplode/" + newName;// /uplode/2016121503182159.docx
		////System.out.println("url  =" + url);

		Record u = pictureinfoServer.pictureinfoByFind(getPara("pictureNo"));
		Userinfo userinfo = getSessionAttr("loginUser");
		if (u != null && userinfo != null) {
			pictureinfo.setId(getParaToInt("id"));
			pictureinfo.setPictureNo(getPara("pictureNo"));
			pictureinfo.setContent(getPara("content"));
			pictureinfo.setPicturename(getPara("picturename"));
			pictureinfo.setPicturePath(url);
			falg = pictureinfo.update();
		} else {
			File f = new File(filePath);
			f.delete();
			falg = false;
			////System.out.println("删除文件");
		}
		////System.out.println("修改(通过方法链的方式)="+falg);
		
	}
}
