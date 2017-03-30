package com.lxr.nvc.model;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpModel implements Model{
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

	public Object getJson() {
		return json;
	}

	public void setJson(Object json) {
		this.json = json;
	}

	

}
