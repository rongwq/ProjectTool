package com.rong.controller;

import java.util.Date;
import java.util.GregorianCalendar;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.rong.common.BaseRenderJson;
import com.rong.dao.Memorandum;

/****
 * @Project_Name:	ProjectTool
 * @Copyright:		Copyright © 2012-2018 G-emall Technology Co.,Ltd
 * @Version:		1.0.0.1
 * @File_Name:		IndexController.java
 * @CreateDate:		2018年3月5日 下午2:58:53
 * @Designer:		Wenqiang-Rong
 * @Desc:			
 * @ModifyHistory:	
 ****/

public class IndexController extends Controller{
	public void index() {
		GregorianCalendar temp = new GregorianCalendar();
		String year = getPara("year",String.valueOf(temp.get(GregorianCalendar.YEAR)));
		int monthVal = getParaToInt("month",temp.get(GregorianCalendar.MONTH));
		String month = String.valueOf(monthVal+1);
		String date = getPara("date",String.valueOf(temp.get(GregorianCalendar.DAY_OF_MONTH)));
		String time = year+"-"+month+"-"+date;
		Page<Memorandum> finishedList = Memorandum.page(1, 99, true, time,null,null);
		setAttr("finishedList", finishedList.getList());
		Page<Memorandum> unFinishedList = Memorandum.page(1, 99, false, time,null,null);
		setAttr("unFinishedList", unFinishedList.getList());
		Page<Memorandum> expirList = Memorandum.page(1, 99, false, time,true,null);
		setAttr("expirList", expirList.getList());
		render("/index.jsp");
	}
	
	public void detail() {
		String time = getPara("time");
		Page<Memorandum> detailList = Memorandum.page(1, 99, null, null,null,time);
		BaseRenderJson.apiReturnObj(this, "1", detailList.getList(), "查询成功");
	}
	
	public void save(){
		Date startDate = getParaToDate("startDate");
		Date expirDate = getParaToDate("expirDate");
		String msg = getPara("msg");
		Memorandum item = new Memorandum();
		item.setCreateTime(new Date());
		item.setStartDate(startDate);
		item.setExpirDate(expirDate);
		item.setMsg(msg);
		item.setFinished(false);
		item.save();
		BaseRenderJson.apiReturnObj(this, "1", "", "保存成功");
	}
	
	public void finish(){
		Long id = getParaToLong("id");
		Memorandum item = Memorandum.dao.findById(id);
		item.setFinished(true);
		item.update();
		BaseRenderJson.apiReturnObj(this, "1", "", "更新成功");
	}
	
	public void unfinish(){
		Long id = getParaToLong("id");
		Memorandum item = Memorandum.dao.findById(id);
		item.setFinished(false);
		item.update();
		BaseRenderJson.apiReturnObj(this, "1", "", "更新成功");
	}
	
	public void del(){
		Long id = getParaToLong("id");
		Memorandum item = Memorandum.dao.findById(id);
		item.delete();
		BaseRenderJson.apiReturnObj(this, "1", "", "删除成功");
	}
}


