package com.ftme.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseInteractive<M extends BaseInteractive<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setInteractiveNo(java.lang.String interactiveNo) {
		set("interactiveNo", interactiveNo);
	}

	public java.lang.String getInteractiveNo() {
		return get("interactiveNo");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setInteractiveState(java.lang.String interactiveState) {
		set("interactiveState", interactiveState);
	}

	public java.lang.String getInteractiveState() {
		return get("interactiveState");
	}

	public void setDatetime(java.util.Date datetime) {
		set("datetime", datetime);
	}

	public java.util.Date getDatetime() {
		return get("datetime");
	}

	public void setState(java.lang.String state) {
		set("state", state);
	}

	public java.lang.String getState() {
		return get("state");
	}

}