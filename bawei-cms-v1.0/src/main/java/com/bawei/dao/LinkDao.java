package com.bawei.dao;

import java.util.List;
import java.util.Map;

import com.bawei.entity.Link;

/**
 * ��������Dao�ӿ�
 * @author user
 *
 */
public interface LinkDao {

	/**
	 * ����������ҳ��ѯ�������Ӽ���
	 * @param map
	 * @return
	 */
	public List<Link> list(Map<String,Object> map);
}
