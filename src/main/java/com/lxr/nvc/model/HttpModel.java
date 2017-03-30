package com.lxr.nvc.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpModel implements Model{
	ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
	HttpServletRequest request ;
	
	Object json;
	
	
	public HttpModel(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public void addAttribute(String key,Object value) {
		// TODO Auto-generated method stub
		request.setAttribute(key, value);
	}

	public void addAttributeMap(Map<?, ?> map) {
		// TODO Auto-generated method stub
		for(Map.Entry<?,?> entry:map.entrySet()){
			addAttribute((String)entry.getKey(),entry.getValue());
		}
	}

	@Override
	public void setJson(Object obj) {
		// TODO Auto-generated method stub	
			threadLocal.set(obj);
		
	}

	public Object getJson() {
		// TODO Auto-generated method stub
		Object object = threadLocal.get();
		if(object==null){
			throw new RuntimeException("未存放json数据");
		}
		else{
			return object;
		}
		
	}

}
