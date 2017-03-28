package com.lxr.nvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncoderFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("进入拦截器");
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		   String charset = "UTF-8";   
	        response.setCharacterEncoding(charset);  
	        response.setContentType("text/html;charset=" + charset);  
	        EncodingRequest res = new EncodingRequest((HttpServletRequest)request, charset);  
	        filterChain.doFilter(res, response);  
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("离开拦截器");
	}

}
