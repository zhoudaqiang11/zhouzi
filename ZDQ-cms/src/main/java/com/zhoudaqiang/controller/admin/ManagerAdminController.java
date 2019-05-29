package com.zhoudaqiang.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhoudaqiang.entity.Manager;
import com.zhoudaqiang.service.ManagerService;
import com.zhoudaqiang.util.Md5Util;

@Controller
@RequestMapping("/admin/manager")
public class ManagerAdminController {

	@Resource
	private ManagerService managerService;
	
	
	@RequestMapping("/mpdifyPassword")
	public String midifypassword(String newpassword,HttpServletResponse response){
		Manager manager = new Manager();
		manager.setPassword(Md5Util.md5(newpassword,Md5Util.SALT));
		return null;
	}
	
}
