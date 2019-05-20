package com.bw.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bw.mapper.UserMapperDao;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapperDao userMapperDao;

	@Override
	public List<Map<String, Object>> getlistuser(Map<String, Object> map) {
		
		return userMapperDao.getlistuser(map);
	}
}
