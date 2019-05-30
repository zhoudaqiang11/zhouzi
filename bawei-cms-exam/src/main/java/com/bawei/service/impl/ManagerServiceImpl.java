package com.bawei.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bawei.dao.ManagerDao;
import com.bawei.entity.Manager;
import com.bawei.service.ManagerService;

/**
 * 管理员Service实现类
 * @author user
 *
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService{

	@Resource
	private ManagerDao managerDao;

	public Manager getByUserName(String userName) {
		return managerDao.getByUserName(userName);
	}

	public Integer update(Manager manager) {
		return managerDao.update(manager);
	}
}
