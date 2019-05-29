package com.zhoudaqiang.controller.admin;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;

import com.zhoudaqiang.entity.Article;
import com.zhoudaqiang.entity.PageBean;
import com.zhoudaqiang.service.ArticleService;
import com.zhoudaqiang.service.impl.InitComponet;
import com.zhoudaqiang.util.ResponseUtil;
import com.zhoudaqiang.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/admin/article")
public class ArticleAdminController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private InitComponet initComponet;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Article article,HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("title",StringUtil.formatLike(article.getTitle()));
		map.put("start",pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		List<Article> articlelist=articleService.list(map);
		System.out.println(articlelist);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray = JSONArray.fromObject(articlelist);
		Long toal=articleService.getTotal(map);
		JSONObject result = new JSONObject();
		result.put("rows",jsonArray);
		result.put("toal",toal);
		ResponseUtil.write(response,result);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response) throws Exception{
		String[] idsplit=ids.split(",");
		for (String id : idsplit) {
			articleService.delete(Integer.parseInt(id));
		}
		JSONObject result = new JSONObject();
		result.put("success",true);
		initComponet.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
		ResponseUtil.write(response, result);
		return null;
	}
}
