package com.bawei.service;

import java.util.List;
import java.util.Map;

import com.bawei.entity.ArcType;

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
	 * 获取文章类别的总数量
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 根据id查询实体
	 * @param id
	 * @return
	 */
	public ArcType findById(Integer id);
	
	/**
	 * 文章类别添加功能
	 * @param arcType
	 * @return
	 */
	public Integer add(ArcType arcType);
	
	/**
	 * 文章类别修改功能
	 * @param arcType
	 * @return
	 */
	public Integer update(ArcType arcType);
	
	/**
	 * 
	 * 文章类别删除功能
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
