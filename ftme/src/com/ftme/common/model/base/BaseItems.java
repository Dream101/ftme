package com.ftme.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseItems<M extends BaseItems<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setItemsNo(java.lang.String itemsNo) {
		set("itemsNo", itemsNo);
	}

	public java.lang.String getItemsNo() {
		return get("itemsNo");
	}

	public void setItemsname(java.lang.String itemsname) {
		set("itemsname", itemsname);
	}

	public java.lang.String getItemsname() {
		return get("itemsname");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setItemsState(java.lang.String itemsState) {
		set("itemsState", itemsState);
	}

	public java.lang.String getItemsState() {
		return get("itemsState");
	}

	public void setState(java.lang.String state) {
		set("state", state);
	}

	public java.lang.String getState() {
		return get("state");
	}

}
