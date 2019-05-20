package com.bw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bw.service.UserService;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("list.do")
	public String list(Model model){
		Map<String, Object> Map = new HashMap<>();
		List<Map<String, Object>> paramlist=userService.getlistuser(Map);
		model.addAttribute("paramlist", paramlist);
		return "list";
	}
}
