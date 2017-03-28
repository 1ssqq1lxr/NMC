package com.lxr.nvc.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
					else if(type.isPrimitive() || type.equals(String.class)){
						System.out.println(	parameter.isNamePresent());
						System.out.println(parameter.getName());
						objs[i]=(T)requestUtil.getRequest().getParameter(parameter.getName());
					}
					else if(type.isAssignableFrom(String.class)){
						System.out.println(parameter.getName());
						System.out.println(parameter);
						objs[i]=requestUtil.getRequest().getParameter(parameter.getName());
					}
					else { //对象的处理
						T t=BeanUtils.getBean(type, requestUtil);
						objs[i]=t;
//						Method[] methods =classt.getDeclaredMethods();
//						if(methods.length>0){
//							for(Method method:methods){
//								
//							}
//						}

						
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			return objs;
			
		}
}
