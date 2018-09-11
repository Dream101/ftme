package com.ftme.controller;

import com.ftme.common.model.Userinfo;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.server.SmsrecordServer;
import com.ftme.server.UserinfoServer;
import com.ftme.util.WylUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
/**
 * 注册修改模块（修改密码）
 * @author wyl
 *
 */
@Clear(FrontInterceptor.class)
public class UserInfoController extends Controller {

	private UserinfoServer userinfoServer = new UserinfoServer();
	private Userinfo user = new Userinfo();
	private SmsrecordServer smsServer=new SmsrecordServer();
	private WylUtil wyl = new WylUtil();

	// 修改密码
	public void pwdUpdate() {
		user = getSessionAttr("loginUser");
		String pwd = getPara("password");
		String newpwd = getPara("newpassword");

		user.setPassword(newpwd);
		int falg = userinfoServer.pwdUpdate(user);
		if (falg > 0) {
			getSession().removeAttribute("loginUser");
			redirect("/redirect.jsp");
		} else {
			setAttr("ckfalg", "密码修改失败");
			render("/view/pwd-update.jsp");
		}
	}

	// 后台修改密码验证
	public void ckpwd() {
		user = getSessionAttr("loginUser");
		String pwd = getPara("password");
		if (user == null) {
			renderJson(false);
		} else {
			if (user.getPassword().equals(pwd)) {
				renderJson(true);
			} else {
				renderJson(false);
			}
		}
	}
	
	// 前台修改密码
	@Clear
	public void pwdUpdateFront() {
//		user = getSessionAttr("frontLoginUser");
		user = getSessionAttr("loginUser");
		String pwd = getPara("password");
		String newpwd = getPara("newpassword");

		user.setPassword(newpwd);
		////System.out.println(user.toString());
		int falg = userinfoServer.pwdUpdate(user);
		if (falg > 0) {
			getSession().removeAttribute("loginUser");
			redirect("//portal/portal");
		} else {
			setAttr("ckfalg", "密码修改失败");
			render("/view/front/frontpwd-update.jsp");
		}
	}
	
	// 前台修改密码验证
	@Clear
	public void ckpwdFront() {
//		user = getSessionAttr("frontLoginUser");
		user = getSessionAttr("loginUser");
		String pwd = getPara("password");
		////System.out.println(user.toJson());
		if (user == null) {
			renderJson(false);
		} else {
			////System.out.println(user.getPassword()==pwd);
			////System.out.println(user.getPassword());
			////System.out.println(pwd);
			////System.out.println(user.getPassword().equals(pwd));
			if (user.getPassword().equals(pwd)) {
				renderJson(true);
			} else {
				renderJson(false);
			}
		}
	}
	
	//找回密码
	@Clear
	public void retrievePwd() {
		String name=getPara("name");
		String username=getPara("username");
		Record userinfo=userinfoServer.retrieveCKPwd(username, name);
		
		if(userinfo!=null){
			Record smsrecord=wyl.sendSMS(userinfo, 1);
			smsServer.smsrecordSave(smsrecord);
			setAttr("falg", "密码找回成功！将以短信方式通知该用户！");
			renderJson();
		}else{
			setAttr("falg", "密码找回失败！账号或姓名错误！");
			renderJson();
		}
	}
}
