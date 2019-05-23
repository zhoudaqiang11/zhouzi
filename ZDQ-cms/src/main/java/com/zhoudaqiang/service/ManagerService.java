package com.zhoudaqiang.service;

import com.zhoudaqiang.entity.Manager;

/**
 * 管理员Service接口
 * @author user
 *
 */
public interface ManagerService {

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Manager getByUserName(String userName);
}
