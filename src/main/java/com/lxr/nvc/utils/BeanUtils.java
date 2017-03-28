package com.lxr.nvc.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class  BeanUtils {
	public static <T,M> T  getBean(Class<T> classt,HttpRequestUtil requestUtil){
		T t =null;
		Field[] declaredFields = classt.getDeclaredFields();
		try {
			t=classt.newInstance();
			for(Field field:declaredFields){
				Class<M> type = (Class<M>) field.getType();
				String parameter = requestUtil.getRequest().getParameter(field.getName());
				if(parameter!=null && !parameter.equals("")){
					String upperCase = field.getName().substring(0, 1).toUpperCase();
					String method_name = "set"+upperCase+field.getName().substring(1);
					Method declaredMethod = classt.getDeclaredMethod(method_name, type);
					if(type.equals(int.class) || type.equals(Integer.class)){
						declaredMethod.invoke(t, Integer.parseInt(parameter));
					}
					else if(type.equals(Double.class) || type.equals(double.class)){
						declaredMethod.invoke(t, Double.parseDouble(parameter));
					}
					else if(type.equals(Date.class) ){
						declaredMethod.invoke(t, new SimpleDateFormat("yyyyMMddHHmmss").parse(parameter));
					}
					else{
						declaredMethod.invoke(t, parameter);
					}
					
					
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return  t;
		
	}
}
