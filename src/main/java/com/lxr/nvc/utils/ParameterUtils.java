package com.lxr.nvc.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxr.nvc.annotation.Param;
import com.lxr.nvc.model.Model;

public class ParameterUtils {
	private static Object[] objs ;
	public static <T> Object[] buildParams(HttpRequestUtil requestUtil,Parameter[] parameters){
		try {
			objs = new Object[parameters.length];
			for(int i=0;i<parameters.length;i++){
				Parameter parameter	=parameters[i];
				//					Type parameterizedType = parameter.getParameterizedType();// 获取带泛型的形参类型
				Class<T> type = (Class<T>) parameter.getType();
				Type parameterizedType = parameter.getParameterizedType();
				if(type.equals(HttpServletRequest.class)){
					objs[i]=requestUtil.getRequest();
				}
				else if(type.equals(HttpServletResponse.class)){
					objs[i]=requestUtil.getResponse();
				}
				else if(type.equals(HttpSession.class)){
					objs[i]=requestUtil.getSession();
				}
				else if(type.equals(Model.class)){
					objs[i]=requestUtil.getModel();
				}
				else if(type.isPrimitive() ){
					Param annotation = parameter.getAnnotation(Param.class);

					if(type.equals(int.class)){
						objs[i]=Integer.parseInt(requestUtil.getRequest().getParameter(annotation.value()));
					}
					else if( type.equals(double.class)){
						objs[i]=Double.parseDouble(requestUtil.getRequest().getParameter(annotation.value()));
					}
					else if(type.equals(Date.class) ){
						objs[i]=new SimpleDateFormat("yyyyMMddHHmmss").parse(requestUtil.getRequest().getParameter(annotation.value()));
					}
					else if(type.equals(boolean.class)){
						objs[i]=Boolean.parseBoolean(requestUtil.getRequest().getParameter(annotation.value()));
					}

				}

				else if(type.equals(String.class)){
					Param annotation = parameter.getAnnotation(Param.class);
					if(annotation.value()!=null && !annotation.value().equals("")){
						objs[i]=requestUtil.getRequest().getParameter(annotation.value());
					}
				

				}
				else if(type.equals(Double.class)){

					Param annotation = parameter.getAnnotation(Param.class);
					if(annotation.value()!=null && !annotation.value().equals("")){
						objs[i]=Double.parseDouble(requestUtil.getRequest().getParameter(annotation.value()));
					}
					
				}
				else if(type.equals(Date.class)){
					Param annotation = parameter.getAnnotation(Param.class);
					if(annotation.value()!=null && !annotation.value().equals("")){
						objs[i]=new SimpleDateFormat("yyyyMMddHHmmss").parse(requestUtil.getRequest().getParameter(annotation.value()));
					}
					
				}
				else if(type.equals(Boolean.class)){
					Param annotation = parameter.getAnnotation(Param.class);
					if(annotation.value()!=null && !annotation.value().equals("")){
						objs[i]=Boolean.parseBoolean(requestUtil.getRequest().getParameter(annotation.value()));
					}
					
				}
				else if(type.equals(Integer.class)){
					Param annotation = parameter.getAnnotation(Param.class);
					if(annotation.value()!=null && !annotation.value().equals("")){
						objs[i]=Integer.parseInt(requestUtil.getRequest().getParameter(annotation.value()));
					}
				
				}
				else { //对象的处理
					T t=BeanUtils.getBean(type, requestUtil);
					objs[i]=t;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return objs;

	}
}
