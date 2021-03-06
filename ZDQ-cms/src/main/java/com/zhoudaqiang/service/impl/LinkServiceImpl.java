package com.zhoudaqiang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhoudaqiang.dao.LinkDao;
import com.zhoudaqiang.entity.Link;
import com.zhoudaqiang.service.LinkService;

/**
 * 友情链接Service实现类
 * @author user
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;
	
	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	@Override
	public Long gettoal(Map<String, Object> map) {
		
		return linkDao.gettoal(map);
	}

	@Override
	public void delete(int parseInt) {
		linkDao.delete(parseInt);
		
	}

	@Override
	public int add(Link link) {
		
		return linkDao.add(link);
	}

	@Override
	public int update(Link link) {
		
		return linkDao.update(link);
	}



}
