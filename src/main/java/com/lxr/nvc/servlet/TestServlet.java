package com.lxr.nvc.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported=true,urlPatterns="/test")
public class TestServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setHeader("Cache-Control", "private");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Connection", "Keep-Alive");
		resp.setHeader("Proxy-Connection", "Keep-Alive");
		resp.setContentType("text/html;charset=UTF-8");
		if(req.isAsyncSupported()){ // 判断是否开启异步
			final AsyncContext asyncContext = req.startAsync(req,resp);
			asyncContext.addListener(new AsyncListener() {
				
				@Override
				public void onTimeout(AsyncEvent arg0) throws IOException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onStartAsync(AsyncEvent arg0) throws IOException {
					// TODO Auto-generated method stub
					System.out.println("wo kai shi le ");
				}
				
				@Override
				public void onError(AsyncEvent arg0) throws IOException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onComplete(AsyncEvent arg0) throws IOException {
					// TODO Auto-generated method stub
					System.out.println("wo wan cheng le  ");
				}
			});
			asyncContext.start(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						HttpServletResponse request = (HttpServletResponse)asyncContext.getResponse();
						request.getWriter().print("123123");
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					finally{
						asyncContext.complete();
						//当自己维护线程池的时候需要使用这个方法通知servlet容器子线程已经完成任务   
//						当使用asyncContext.start()方法 实在当前web容器中开启一个线程  不推荐此  因为关闭了主线程又开启了一个新的线程 意义不大
					}
				}
			});
		}
	
	}
		
}
