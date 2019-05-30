package com.bawei.controller.admin;

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

import com.bawei.entity.ArcType;
import com.bawei.entity.PageBean;
import com.bawei.service.ArcTypeService;
import com.bawei.service.ArticleService;
import com.bawei.service.impl.InitComponent;
import com.bawei.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 文章类别controller类
 * @author user
 *
 */
@Controller
@RequestMapping("/admin/arcType")
public class ArcTypeAdminController {

	@Resource
	private ArcTypeService arcTypeService;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private InitComponent initComponent;
	
	/**
	 * 文章类别列表方法
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<ArcType> arcTypeList=arcTypeService.list(map);
		Long total=arcTypeService.getTotal(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(arcTypeList);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 文章类别的修改和添加方法
	 * @param link
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(ArcType arcType,HttpServletResponse response,HttpServletRequest request)throws Exception{
		int resultTotal=0;
		if(arcType.getId()==null){ // 添加操作
			resultTotal=arcTypeService.add(arcType);
		}else{ // 修改操作
			resultTotal=arcTypeService.update(arcType);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 友情链接的删除方法
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		JSONObject result=new JSONObject();
		for(int i=0;i<idsStr.length;i++){
			if(articleService.getNumByTypeId(Integer.parseInt(idsStr[i]))>0){
				result.put("exist", "此类别下面还有文章存在，无法删除此文章类别");
			}else{
				arcTypeService.delete(Integer.parseInt(idsStr[i]));				
			}
		}
		initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
