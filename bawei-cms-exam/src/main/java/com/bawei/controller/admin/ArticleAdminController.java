package com.bawei.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.bawei.entity.Article;
import com.bawei.entity.PageBean;
import com.bawei.service.ArticleService;
import com.bawei.service.impl.InitComponent;
import com.bawei.util.DateUtil;
import com.bawei.util.ResponseUtil;
import com.bawei.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 文章管理Controller类
 * @author user
 *
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleAdminController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private InitComponent initComponent;
	
	/**
	 * 修改或者保存文章方法
	 * @param article
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(@RequestParam("slideImageFile") MultipartFile slideImageFile, Article article,HttpServletResponse response,HttpServletRequest request)throws Exception{
		if(!slideImageFile.isEmpty()){
			//获取根路径
			String filePath=request.getServletContext().getRealPath("/");
			//上传图片重新命名
			String imageName=DateUtil.getCurrentDateStr()+"."+slideImageFile.getOriginalFilename().split("\\.")[1];
			//上传图片到指定位置
			slideImageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
			//文章中图片的名称
			article.setSlideImage(imageName);
		}
		int resultTotal=0;
		article.setPublishDate(new Date());
		if(article.getId()==null){ //添加操作
			resultTotal=articleService.add(article);
		}else{ //修改操作
			resultTotal=articleService.update(article);
		}
		StringBuffer result=new StringBuffer();
		if(resultTotal>0){
			initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
			result.append("<script language='javascript'>alert('提交成功');</script>");
		}else{
			result.append("<script language='javascript'>alert('提交失败');</script>");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 文章列表方法
	 * @param page  分页数量
	 * @param rows  一页多少行
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,Article s_article,@RequestParam(value="rows",required=false)String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", StringUtil.formatLike(s_article.getTitle()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Article> articleList=articleService.list(map);
		Long total=articleService.getTotal(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(articleList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 通过Id查询文章内容
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Article article=articleService.findById(Integer.parseInt(id));
		JSONObject jsonObject=JSONObject.fromObject(article);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	/**
	 * 删除文章方法
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			articleService.delete(Integer.parseInt(idsStr[i]));
		}
		initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
