package com.ftme.common.router;

import com.ftme.controller.ClinicalController;
import com.ftme.controller.CompanyinfoController;
import com.ftme.controller.DoctorController;
import com.ftme.controller.DocumentController;
import com.ftme.controller.GovernController;
import com.ftme.controller.HospitalController;
import com.ftme.controller.InteractiveController;
import com.ftme.controller.ItemsController;
import com.ftme.controller.LoginController;
import com.ftme.controller.NewsController;
import com.ftme.controller.PatientController;
import com.ftme.controller.PermissionsController;
import com.ftme.controller.PictureinfoController;
import com.ftme.controller.StaffController;
import com.ftme.controller.TestresultsController;
import com.ftme.controller.UserInfoController;
import com.jfinal.config.Routes;

public class BackstageRoutes extends Routes {

	@Override
	public void config() {
		this.add("/userinfo", UserInfoController.class);//修改密码
		this.add("/login", LoginController.class);//登录
		this.add("/permissions",PermissionsController.class);//权限管理
		this.add("/govern",GovernController.class);//管理员
		this.add("/staff",StaffController.class);//员工
		this.add("/document",DocumentController.class);//文件
		this.add("/hospital",HospitalController.class);//医院
		this.add("/items",ItemsController.class);//基因检测项目
		this.add("/testresults",TestresultsController.class );//基因检测结果
		this.add("/clinical",ClinicalController.class );//基因检测结果
		this.add("/doctor",DoctorController.class);//医生
		this.add("/patient",PatientController.class);//患者
//		this.add("/company",CompanyinfoController.class);//公司简介
//		this.add("/picture",PictureinfoController.class);//前台logo图片管理
//		this.add("/news",NewsController.class);//前台行业动态管理
//		this.add("/interactive",InteractiveController.class);//前台联系我们管理
	}

}
 