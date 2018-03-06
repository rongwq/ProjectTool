package com.rong.config;

import java.util.Map;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 公共拦截器
 * @author Wenqiang-Rong
 * @date 2017年12月21日
 */
public class CommonInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation ai) {
		try {
			// 设置当前请求链接和上一次请求链接
			setUrl(ai.getController());
			myIntercept(ai);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void myIntercept(Invocation ai) {
		ai.invoke();
	}
	
	/**
	 * 保存单次请求和上次请求的数据至session，以便进一步操作
	 * 如：前端js返回操作，common.js:back()
	 * @param cont
	 */
	public void setUrl(Controller cont){
		Map<String, String[]> params = cont.getRequest().getParameterMap();  
        String queryString = "";  
        for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                queryString += key + "=" + value + "&";  
            }  
        }
        String url = cont.getRequest().getRequestURL().toString();
        // 去掉最后一个空格  
        if(!"".equals(queryString)){
	        queryString = queryString.substring(0, queryString.length() - 1);  
	        url += "?" + queryString;
        }
        if(cont.getSessionAttr("currUrl")!=null){
        	cont.setSessionAttr("backUrl", cont.getSessionAttr("currUrl"));
        }
        cont.setSessionAttr("currUrl", url);
	}

}
