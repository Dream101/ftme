package com.ftme.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTestresults<M extends BaseTestresults<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setTestresultsNo(java.lang.String testresultsNo) {
		set("testresultsNo", testresultsNo);
	}

	public java.lang.String getTestresultsNo() {
		return get("testresultsNo");
	}

	public void setTestresultsname(java.lang.String testresultsname) {
		set("testresultsname", testresultsname);
	}

	public java.lang.String getTestresultsname() {
		return get("testresultsname");
	}

	public void setTestresultsState(java.lang.String testresultsState) {
		set("testresultsState", testresultsState);
	}

	public java.lang.String getTestresultsState() {
		return get("testresultsState");
	}

	public void setState(java.lang.String state) {
		set("state", state);
	}

	public java.lang.String getState() {
		return get("state");
	}

}
