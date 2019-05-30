package com.bawei.service;

import com.bawei.entity.Manager;

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
	
	/**
	 * 管理员更新信息
	 * @param manager
	 * @return
	 */
	public Integer update(Manager manager);
}
