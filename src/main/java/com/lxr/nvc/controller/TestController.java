package com.lxr.nvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lxr.nvc.annotation.Controller;
import com.lxr.nvc.annotation.Param;
import com.lxr.nvc.annotation.RequestUrl;
import com.lxr.nvc.annotation.ResponseBody;
import com.lxr.nvc.model.Model;

@Controller(url="")
public class TestController {
		@ResponseBody
		@RequestUrl(value="/haha.do", method = "get")
		public void jhasd(@Param(value="name") String name,@Param(value="age")Integer age,Model model,TestPo bo){
//			System.out.println(po.getName());
//			model.addAttribute("lxr", "123123");
//			request.setAttribute("lxr", "123123");
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("lxr", 123);
			map.put("wcf", 456);
			model.setJson(map);
			
		}
}
