package com.rong.dao.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMemorandum<M extends BaseMemorandum<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}

	public java.lang.Long getId() {
		return getLong("id");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setStartDate(java.util.Date startDate) {
		set("start_date", startDate);
	}

	public java.util.Date getStartDate() {
		return get("start_date");
	}
	
	public void setExpirDate(java.util.Date expirDate) {
		set("expir_date", expirDate);
	}

	public java.util.Date getExpirDate() {
		return get("expir_date");
	}

	public void setMsg(java.lang.String msg) {
		set("msg", msg);
	}

	public java.lang.String getMsg() {
		return getStr("msg");
	}

	public void setFinished(java.lang.Boolean finished) {
		set("finished", finished);
	}

	public java.lang.Boolean getFinished() {
		return get("finished");
	}

}