package com.bawei.dao;

import java.util.List;
import java.util.Map;

import com.bawei.entity.Article;

/**
 * 文章Dao接口
 * @author user
 *
 */
public interface ArticleDao {

	/**
	 * 获取最新的7条文章
	 * @return
	 */
	public List<Article> getNewest();
	
	/**
	 * 获取最新7条推荐的文章
	 * @return
	 */
	public List<Article> getRecommend();
	
	/**
	 * 获取最新5条幻灯的文章
	 * @return
	 */
	public List<Article> getSlide();
	
	/**
	 * 根据文章类别来查找最新的8条数据
	 * @param typeId
	 * @return
	 */
	public List<Article> getIndex(Integer typeId);
	
	/**
	 * 通过id查询文章
	 * @param id
	 * @return
	 */
	public Article findById(Integer id);
	
	/**
	 * 获取上一个文章
	 * @param id
	 * @return
	 */
	public Article getLastArticle(Integer id);
	
	/**
	 * 获取下一个文章
	 * @param id
	 * @return
	 */
	public Article getNextArticle(Integer id);
	
	/**
	 * 更新文章
	 * @param article
	 * @return
	 */
	public Integer update(Article article);
	
	/**
	 * 根据条件分页查询文章
	 * @param map
	 * @return
	 */
	public List<Article> list(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加文章内容
	 * @param article
	 * @return
	 */
	public Integer add(Article article);
	
	/**
	 * 删除文章内容
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
	

	/**
	 * 获取指定类别的文章数量
	 * @param typeId
	 * @return
	 */
	public Integer getNumByTypeId(Integer typeId);
}
