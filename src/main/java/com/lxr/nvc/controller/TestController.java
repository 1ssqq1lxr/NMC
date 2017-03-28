package com.lxr.nvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxr.nvc.annotation.Controller;
import com.lxr.nvc.annotation.Param;
import com.lxr.nvc.annotation.RequestUrl;
import com.lxr.nvc.model.Model;

@Controller(url="")
public class TestController {
		@RequestUrl(value="/haha.do", method = "get")
		public String jhasd(@Param(value="name") String name,@Param(value="age")Integer age){
//			System.out.println(po.getName());
//			model.addAttribute("lxr", "123123");
//			request.setAttribute("lxr", "123123");
			return "/NewFile.jsp";
		}
}
