package com.bawei.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.bawei.entity.ArcType;
import com.bawei.entity.Article;
import com.bawei.entity.Link;
import com.bawei.service.ArcTypeService;
import com.bawei.service.ArticleService;
import com.bawei.service.LinkService;

/**
 * 初始化组件
 * @author user
 *
 */
@Component("initComponet")
public class InitComponent implements ApplicationContextAware,ServletContextListener{

	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
		System.out.println(this);
	}
	
	/**
	 * 缓存application方法实现
	 * @param application
	 */
	public void refreshSystem(ServletContext application){
		LinkService linkService=(LinkService) applicationContext.getBean("linkService");
		List<Link> linkList=linkService.list(null);
		application.setAttribute("linkList", linkList);
		
		ArcTypeService arcTypeService=(ArcTypeService) applicationContext.getBean("arcTypeService");
		List<ArcType> arcTypeList=arcTypeService.list(null);
		application.setAttribute("arcTypeList", arcTypeList);
		
		ArticleService articleService=(ArticleService) applicationContext.getBean("articleService");
		List<Article> newestArticleList=articleService.getNewest(); // 获取最新7条帖子
		application.setAttribute("newestArticleList", newestArticleList);
		
		List<Article> recommendArticleList=articleService.getRecommend(); 
		application.setAttribute("recommendArticleList", recommendArticleList);
		
		List<Article> slideArticleList=articleService.getSlide();
		application.setAttribute("slideArticleList", slideArticleList);
		
		List allIndexArticleList=new ArrayList(); // 存储多个集合 每个集合里是指定类型的最新8个帖子
		if(arcTypeList!=null && arcTypeList.size()!=0){
			for(int i=0;i<arcTypeList.size();i++){
				List<Article> subArticleList=articleService.getIndex(arcTypeList.get(i).getId());
				allIndexArticleList.add(subArticleList);
			}
		}
		application.setAttribute("allIndexArticleList", allIndexArticleList);
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext application=sce.getServletContext();
		refreshSystem(application);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
