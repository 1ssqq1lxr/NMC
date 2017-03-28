package com.lxr.nvc.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lxr.nvc.annotation.Controller;
import com.lxr.nvc.model.HttpModel;
import com.lxr.nvc.utils.HttpRequestUtil;
import com.lxr.nvc.utils.UrlFilter;
/**
 * lxr
 * @author Administrator
 * 核心servlet 拦截所有请求
 */

public class ConfigureServlet implements Servlet{

	private final  String LOAD_CONTROLLER_URL="com.lxr.nvc.controller";
	
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println();
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		String url = httpServletRequest.getServletPath(); //得到所有相对项目路径
		HttpRequestUtil requestUtil = new HttpRequestUtil(httpServletRequest,httpServletResponse,"utf-8");
		UrlFilter.filter(requestUtil,LOAD_CONTROLLER_URL,url);
		
	}

		
		
}
