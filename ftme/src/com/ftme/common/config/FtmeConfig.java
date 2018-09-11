package com.ftme.common.config;

import interceptor.ExceptionIntoLogInterceptor;

import com.ftme.common.model._MappingKit;
import com.ftme.common.router.BackstageRoutes;
import com.ftme.common.router.FrontRoutes;
import com.ftme.controller.ClinicalController;
import com.ftme.controller.DoctorController;
import com.ftme.controller.DocumentController;
import com.ftme.controller.GovernController;
import com.ftme.controller.HospitalController;
import com.ftme.controller.ItemsController;
import com.ftme.controller.LoginController;
import com.ftme.controller.PatientController;
import com.ftme.controller.PermissionsController;
import com.ftme.controller.PortalController;
import com.ftme.controller.StaffController;
import com.ftme.controller.TestresultsController;
import com.ftme.controller.UserInfoController;
import com.ftme.interceptor.FrontInterceptor;
import com.ftme.interceptor.LoginInterceptor;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class FtmeConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		// JFinal常量配置讲解
		// 常量配置是第一个被加载的因为后面的一些配置都需要这里配置的常量作为基础
		// 1.加载数据库配置 读取配置文件使用loadPropertyFile或者PropKit 读取键值对
		// loadPropertyFile("config.properties");
		PropKit.use("config.properties");
		// 2、设置开发模式
		me.setDevMode(PropKit.getBoolean("devMode"));
		// me.setDevMode(getPropertyToBoolean("devMode"));
		// 设置Action Report什么出现 默认true
		me.setReportAfterInvocation(false);
		// 3、配置默认的视图类型 默认是Freemarker
		me.setViewType(ViewType.JSP);
		// 4、配置默认视图层路径viewpath
		// me.setBaseViewPath("/WEB-INF/view");
		// 5、设置默认上传路径 cos组件有效 jfinal默认有值 相对 绝对都可以
		me.setBaseUploadPath("C:\\uplode");
		// me.setMaxPostSize(1024*1024*20);
		// 6、设置默认下载路径 cos组件有效 jfinal默认有值 相对 绝对都可以xmdownload
		me.setBaseDownloadPath("D:\\");
		// 7、设置默认的Freemarker模板文件后缀名 jfinal默认.html
		// me.setFreeMarkerViewExtension(".ftl");
		// me.setJspViewExtension(".jtl");
		// me.setVelocityViewExtension(".vtl");
		// 8、这是url参数分隔符 默认-
		// me.setUrlParaSeparator("~");
		// 设置国际化
		// me.setI18nDefaultBaseName("i18n");
		// me.setI18nDefaultLocale("zh_CN"");
		// 设置Error View
		// me.setError404View("/common/404.html");
		// me.setErrorRenderFactory(errorRenderFactory);
		// 设置默认编码
		// me.setEncoding("GBK");
		// 设置默认的xml渲染工厂 默认使用Freemarker render渲染
		// me.setXmlRenderFactory(自定义工厂);
		// 设置默认json中时间格式化
		// me.setJsonDatePattern("yyyy-mm-dd HH:mm");
		// me.setJsonFactory(FastJsonFactory.me());
		// renderJson 和JsonKit底层依赖于JsonManager中设置的JsonFactory
		// 设置自己的Log工厂实现
		// me.setLogFactory(Slf4JLogFactory.me());
	}

	@Override
	public void configRoute(Routes me) {
		me.add("portal",PortalController.class);//官网
		me.add(new FrontRoutes());//前台路径
		me.add(new BackstageRoutes());//后台路径
	}

	@Override
	public void configPlugin(Plugins me) {
		// 数据库连接池
		// C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),getProperty("user"), getProperty("password"));
		C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"),PropKit.get("driver"),100, 10, 10, 10, 2);
		// ORM Activerecord
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);
		// arp.addMapping("area", Area.class);
		 _MappingKit.mapping(arp);
		// 加入插件管理器
		me.add(c3p0Plugin);
		me.add(arp);
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new LoginInterceptor());
		me.add(new FrontInterceptor());
//		me.addGlobalActionInterceptor(new ExceptionIntoLogInterceptor());//添加控制层全局拦截器
		me.addGlobalServiceInterceptor(new ExceptionIntoLogInterceptor());//添加控制层全局拦截器
		/*//全局拦截器，对所有请求拦截

        
        //interceptors.addGlobalActionInterceptor(new GlobalActionInterceptor());
        interceptors.addGlobalActionInterceptor(new ExceptionIntoLogInterceptor());
        //添加业务层全局拦截器
        //interceptors.addGlobalServiceInterceptor(new GlobalServiceInterceptor());
        interceptors.addGlobalServiceInterceptor(new ExceptionIntoLogInterceptor());

        //兼容老版jfinal写法
        //interceptors.add(new GlobalActionInterceptor());*/
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
	}

}
