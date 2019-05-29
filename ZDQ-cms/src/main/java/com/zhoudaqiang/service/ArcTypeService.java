package com.zhoudaqiang.service;

import java.util.List;
import java.util.Map;

import com.zhoudaqiang.entity.ArcType;

/**
 * 帖子类别Service接口
 * @author user
 *
 */
public interface ArcTypeService {

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

	public Long getTotal(Map<String, Object> map);

	public void delete(int parseInt);

	public int add(ArcType arcType);

	public int update(ArcType arcType);

}
