package com.lxr.nvc.utils;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import com.alibaba.fastjson.JSON;
import com.lxr.nvc.annotation.Controller;
import com.lxr.nvc.annotation.RequestUrl;
import com.lxr.nvc.annotation.ResponseBody;
import com.lxr.nvc.model.Model;
/**
 * lxr
 * @author Administrator
 *  
 */
public class UrlFilter {
	/**
	 * @param requestUtil
	 * @param pack
	 * @param url
	 */
	public static void filter(HttpRequestUtil requestUtil,String pack,String url){
		Set<Class<?>> classes = PackageUtil.getClasses(pack);
		if(!classes.isEmpty()&&classes.size()>0){
			for(Class classt:classes){
				Controller annotation = (Controller) classt.getAnnotation(Controller.class);
				if(annotation!=null){

					Method[] declaredMethods = classt.getDeclaredMethods();
					if((url.startsWith(annotation.url().startsWith("/")?annotation.url():("/"+annotation.url()))|| annotation.url()==null || annotation.url().equals("") ) && declaredMethods.length>0){
						if(annotation.url()!=null && !annotation.url().equals("")){
							if(annotation.url().startsWith("/")){
								url = url.replace( annotation.url(),"");
							}
							else{
								url = url.replace( "/"+annotation.url(),"");
							}
						}

						for(Method method:declaredMethods){
							RequestUrl requestUrl = method.getAnnotation(RequestUrl.class);
							ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
							String request_method = requestUtil.getRequest().getMethod();
							if(requestUrl.method() ==null || requestUrl.method().equalsIgnoreCase(request_method)){
								if(requestUrl!=null ){
									String method_url=requestUrl.value();
 									if(method_url.equals(url)){
										System.out.println(method.getName());

										try {
											Object newInstance = classt.newInstance();
											Parameter[] parameters = method.getParameters();
											Object[] buildParams = ParameterUtils.buildParams(requestUtil, parameters);
											String  invoke = (String) method.invoke(newInstance,buildParams);
											if(invoke!=null&&!invoke.equals("")){
												if(invoke.startsWith("redirect:")){//重定向
													requestUtil.getResponse().sendRedirect(invoke.replace("redirect:", ""));
												}
												else{ //请求转发
													requestUtil.getRequest().getRequestDispatcher(invoke.endsWith(".jsp")?invoke:invoke+".jsp").forward(requestUtil.getRequest(), requestUtil.getResponse());
												}
											}
											else if(responseBody!=null && invoke==null ){
												requestUtil.getResponse().setCharacterEncoding("UTF-8");  
												requestUtil.getResponse().setContentType("application/json; charset=utf-8"); 
												String jsonString = JSON.toJSONString( requestUtil.getModel().getJson());
												PrintWriter outputStream = requestUtil.getResponse().getWriter();
												outputStream.write(jsonString);
												outputStream.close();
											}
											else{
												throw new RuntimeException("未渲染视图或返回json数据");
											}

										} catch (Exception e) {
											// TODO Auto-generated catch block
											throw new RuntimeException("方法有错"); 
										} 
									}
								}
							}
							else{
								System.out.println("未找到匹配方法类型");
							}

						}
					}
					else{
						System.out.println("未找到匹配的url");
					}	

				}
			}
		}
		else{
			System.out.println("未找到匹配的url");
		}

	}
}
