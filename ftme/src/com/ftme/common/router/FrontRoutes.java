package com.ftme.common.router;

import com.ftme.controller.FrontDoctorPatientController;
import com.ftme.controller.FrontPatientController;
import com.ftme.controller.PortalController;
import com.jfinal.config.Routes;

public class FrontRoutes extends Routes {

	@Override
	public void config() {
		this.add("frontPatient",FrontDoctorPatientController.class);//医生模块
		this.add("patientFront",FrontPatientController.class);//患者模块
	}

}
