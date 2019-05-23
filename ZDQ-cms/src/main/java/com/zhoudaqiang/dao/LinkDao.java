package com.zhoudaqiang.dao;

import java.util.List;
import java.util.Map;

import com.zhoudaqiang.entity.Link;

/**
 * 友情链接Dao接口
 * @author user
 *
 */
public interface LinkDao {

	/**
	 * 根据条件分页查询友情链接集合
	 * @param map
	 * @return
	 */
	public List<Link> list(Map<String,Object> map);
}
