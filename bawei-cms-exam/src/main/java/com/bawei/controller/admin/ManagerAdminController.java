package com.bawei.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bawei.entity.Manager;
import com.bawei.service.ManagerService;
import com.bawei.util.Md5Util;
import com.bawei.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * 管理员Controller实现类
 * @author user
 *
 */
@Controller
@RequestMapping("/admin/manager")
public class ManagerAdminController {

	@Resource
	private ManagerService managerSerivce;
	
	/**
	 * 修改管理员密码方法
	 * @param newPassword
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword,HttpServletResponse response)throws Exception{
		Manager manager=new Manager();
		manager.setPassword(Md5Util.md5(newPassword, Md5Util.SALT));
		int resultTotal=managerSerivce.update(manager);
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 管理员安全退出方法
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
	
	
}
