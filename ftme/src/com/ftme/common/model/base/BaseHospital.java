package com.ftme.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseHospital<M extends BaseHospital<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setHospitalNo(java.lang.String hospitalNo) {
		set("hospitalNo", hospitalNo);
	}

	public java.lang.String getHospitalNo() {
		return get("hospitalNo");
	}

	public void setHospitalname(java.lang.String hospitalname) {
		set("hospitalname", hospitalname);
	}

	public java.lang.String getHospitalname() {
		return get("hospitalname");
	}

	public void setHospitalState(java.lang.String hospitalState) {
		set("hospitalState", hospitalState);
	}

	public java.lang.String getHospitalState() {
		return get("hospitalState");
	}

	public void setState(java.lang.String state) {
		set("state", state);
	}

	public java.lang.String getState() {
		return get("state");
	}

}
