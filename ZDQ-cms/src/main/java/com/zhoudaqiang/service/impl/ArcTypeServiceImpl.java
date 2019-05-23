package com.zhoudaqiang.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhoudaqiang.dao.ArcTypeDao;
import com.zhoudaqiang.entity.ArcType;
import com.zhoudaqiang.service.ArcTypeService;

/**
 * 帖子类别Service实现类
 * @author user
 *
 */
@Service("arcTypeService")
public class ArcTypeServiceImpl implements ArcTypeService{

	@Resource
	private ArcTypeDao arcTypeDao;
	
	public List<ArcType> list(Map<String, Object> map) {
		return arcTypeDao.list(map);
	}

	public ArcType findById(Integer id) {
		return arcTypeDao.findById(id);
	}

}
