package com.ftme.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseRolepermissions<M extends BaseRolepermissions<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setUserNo(java.lang.String userNo) {
		set("userNo", userNo);
	}

	public java.lang.String getUserNo() {
		return get("userNo");
	}

	public void setPermissionsidNo(java.lang.String permissionsidNo) {
		set("permissionsidNo", permissionsidNo);
	}

	public java.lang.String getPermissionsidNo() {
		return get("permissionsidNo");
	}

	public void setSuperior(java.lang.String superior) {
		set("superior", superior);
	}

	public java.lang.String getSuperior() {
		return get("superior");
	}

	public void setState(java.lang.String state) {
		set("state", state);
	}

	public java.lang.String getState() {
		return get("state");
	}

}
