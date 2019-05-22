package com.bawei.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bawei.dao.LinkDao;
import com.bawei.entity.Link;
import com.bawei.service.LinkService;

/**
 * ��������Serviceʵ����
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

}
