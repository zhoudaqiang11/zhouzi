package com.zhoudaqiang.controller.admin;

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

import com.zhoudaqiang.entity.ArcType;
import com.zhoudaqiang.entity.PageBean;
import com.zhoudaqiang.service.ArcTypeService;
import com.zhoudaqiang.service.ArticleService;
import com.zhoudaqiang.service.impl.InitComponet;
import com.zhoudaqiang.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/admin/arcType")
public class ArcTypeAdminController {

	@Resource
	private ArcTypeService arcTypeService;
	
	@Resource
	private InitComponet initComponet;
	
	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			HttpServletResponse response) throws Exception{
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("start",pageBean.getStart());
		map.put("size",pageBean.getPageSize());
		List<ArcType> arctypelist=arcTypeService.list(map);
		JSONArray jsonArray = JSONArray.fromObject(arctypelist);
		Long toal=arcTypeService.getTotal(map);
		JSONObject result = new JSONObject();
		result.put("rows",jsonArray);
		result.put("toal",toal);
		ResponseUtil.write(response,result);
		return null;
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response) throws Exception{
		String[] moreid=ids.split(",");
		JSONObject result = new JSONObject();
		for (String id : moreid) {
			Integer resultnum=articleService.getnumbytypeid(Integer.parseInt(id));
			if(resultnum>0){
				result.put("exist","此类别下面还有文章存在，无法删除此文章类别");
			}else{
				arcTypeService.delete(Integer.parseInt(id));
			}
		}
		initComponet.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
		result.put("success",true);
		ResponseUtil.write(response,result);
		return null;
	}
	@RequestMapping("/save")
	public String save(ArcType arcType,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int results=0;
		if(arcType.getId()==null){
			results=arcTypeService.add(arcType);
		}else{
			results=arcTypeService.update(arcType);
		}
		JSONObject result = new JSONObject();
		if(results>0){
			initComponet.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
			result.put("success",true);
		}else{
			result.put("success",false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
}
