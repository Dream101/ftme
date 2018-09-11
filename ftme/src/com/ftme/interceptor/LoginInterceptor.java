package com.ftme.interceptor;

import java.util.HashSet;
import java.util.Set;

import com.ftme.common.model.Userinfo;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor implements Interceptor {
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
////		将要排除的具体的action的某个方法过滤掉
//		 addExcludedActionKey("/login/login");
//		 ////System.out.println((!excludedActionKeys.contains(ai.getActionKey()))+"   "+ai.getActionKey());

		// 将需要排除的action放在一个集合里
		addControllerKey("/login");
		addControllerKey("/portal");
		Controller controller = ai.getController();
		Userinfo user = controller.getSessionAttr("loginUser");
		if (user == null&&!ControllerKey.contains(ai.getControllerKey())) {
			////System.out.println(user== null );
			////System.out.println((!ControllerKey.contains(ai.getControllerKey())) + "   " + ai.getControllerKey());
			controller.redirect("/redirect.jsp");
		}else if(new Integer(user.getRoleNo())>2){
			controller.redirect("/redirect.jsp");
		}else {
			ai.invoke();
		}

	}

}
