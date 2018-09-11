package com.ftme.interceptor;

import java.util.HashSet;
import java.util.Set;

import com.ftme.common.model.Userinfo;
import com.ftme.controller.ClinicalController;
import com.ftme.controller.CompanyinfoController;
import com.ftme.controller.DoctorController;
import com.ftme.controller.DocumentController;
import com.ftme.controller.GovernController;
import com.ftme.controller.HospitalController;
import com.ftme.controller.ItemsController;
import com.ftme.controller.LoginController;
import com.ftme.controller.NewsController;
import com.ftme.controller.PatientController;
import com.ftme.controller.PermissionsController;
import com.ftme.controller.PictureinfoController;
import com.ftme.controller.StaffController;
import com.ftme.controller.TestresultsController;
import com.ftme.controller.UserInfoController;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class FrontInterceptor implements Interceptor {
	// private static final Set<String> excludedActionKeys = new
	// HashSet<String>();
	//	
	// public static void addExcludedActionKey(String actionKey) {
	// excludedActionKeys.add(actionKey);
	// }
	private static final Set<String> ControllerKey = new HashSet<String>();

	public static void addControllerKey(String controllerKey) {
		ControllerKey.add(controllerKey);
	}
	
	public void intercept(Invocation ai) {
		addControllerKey("/login");
		addControllerKey("/portal");
		Controller controller = ai.getController();
		Userinfo user = controller.getSessionAttr("loginUser");
		if (user == null&&!ControllerKey.contains(ai.getControllerKey())) {
			////System.out.println(user== null );
			////System.out.println((!ControllerKey.contains(ai.getControllerKey())) + "   " + ai.getControllerKey());
			controller.redirect("/portal/jump.jsp");
		}else if(new Integer(user.getRoleNo())<3){
			////System.out.println(user.getRoleNo());
			controller.redirect("/portal/jump.jsp");
		}else {
			ai.invoke();
		}

	}

}
