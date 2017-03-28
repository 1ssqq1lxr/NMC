package com.lxr.nvc.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxr.nvc.model.HttpModel;
import com.lxr.nvc.model.Model;

public class HttpRequestUtil {
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private HttpSession session;
	
	private Model model;
	

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpRequestUtil(HttpServletRequest request, HttpServletResponse response,String encoding) {
		super();
		this.request = request;
		this.response = response;
	
		
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return this.request.getSession();
	}

	public Model getModel() {
		return new HttpModel(this.request);
	}

	
	
	
}
