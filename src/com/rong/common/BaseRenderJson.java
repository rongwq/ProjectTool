package com.rong.common;

import com.jfinal.core.Controller;

public class BaseRenderJson {
	public static void apiReturnObj(Controller ai,String code  ,Object obj,String tag){
		if(obj==null){
			BaseRenderJson.apiReturnObj(ai,"1", "", "无数据返回");
			return;
		}
		BaseTemplate tmp = new BaseTemplate();
		tmp.setResultCode(code);
		tmp.setResultDes(tag);
		tmp.setResultData(obj);
		ai.renderJson(tmp);
	}
}
