package com.zhoudaqiang.dao;

import java.util.List;
import java.util.Map;

import com.zhoudaqiang.entity.ArcType;

/**
 * 帖子类别Dao接口
 * @author user
 *
 */
public interface ArcTypeDao {

	/**
	 * 根据条件分页查询帖子类别集合
	 * @param map
	 * @return
	 */
	public List<ArcType> list(Map<String,Object> map);
	
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 */
	public ArcType findById(Integer id);
}
