package com.zhoudaqiang.dao;

import com.zhoudaqiang.entity.Manager;

/**
 * 管理员Dao接口
 * @author user
 *
 */
public interface ManagerDao {

	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Manager getByUserName(String userName);
	
}
