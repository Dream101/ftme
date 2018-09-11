package com.ftme.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseNews<M extends BaseNews<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setNewsNo(java.lang.String newsNo) {
		set("newsNo", newsNo);
	}

	public java.lang.String getNewsNo() {
		return get("newsNo");
	}

	public void setTitle(java.lang.String title) {
		set("title", title);
	}

	public java.lang.String getTitle() {
		return get("title");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setDatetime(java.util.Date datetime) {
		set("datetime", datetime);
	}

	public java.util.Date getDatetime() {
		return get("datetime");
	}

	public void setAdmin(java.lang.String admin) {
		set("admin", admin);
	}

	public java.lang.String getAdmin() {
		return get("admin");
	}

	public void setState(java.lang.String state) {
		set("state", state);
	}

	public java.lang.String getState() {
		return get("state");
	}

}