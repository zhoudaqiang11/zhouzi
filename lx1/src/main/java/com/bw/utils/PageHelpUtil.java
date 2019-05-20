package com.bw.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
public class PageHelpUtil {
/*	public static Map<String,Object> page(String url,PageInfo<?> pageInfo,String name)
			{
		String first="<a href="+url+"?cpage=1&name="+name+">首页</a>";
		String prev="<a href="+url+"?cpage="+pageInfo.getPrePage()+"&name="+name+">上一页</a>";
		String next="<a href="+url+"?cpage="+pageInfo.getNextPage()+"&name="+name+">下一页</a>";
		String last="<a href="+url+"?cpage="+pageInfo.getLastPage()+"&name="+name+">尾页</a>";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cpage", pageInfo.getPageNum());
		map.put("count", pageInfo.getTotal());
		map.put("pagecount", pageInfo.getPages());
		map.put("page", first+" "+prev+" "+next+" "+last);
		return map;
	}
	*/
	
	/***
	 * 需要定义表单中的name属性值为paramMap的key
	 * @param url
	 * @param cpage
	 * @param pageSize
	 * @param count
	 * @param paramMap
	 * @return
	 */
	public static Map<String,Object> pageMap(String url,PageInfo<?> pageInfo,
			Map<String,Object> paramMap)
	{
		//需要定义表单中的name属性值为map的key
		String param = "";
		Set<String> keySet = paramMap.keySet();
		for (String key : keySet) {
			param+="&"+key+"="+paramMap.get(key) ;
		}
		
		String first="<a href="+url+"?cpage=1"+param+">首页</a>";
		if(pageInfo.getPrePage()==0) {
			pageInfo.setPrePage(1);
		}
		String prev="<a href="+url+"?cpage="+pageInfo.getPrePage()+param+">上一页</a>";
		if(pageInfo.getNextPage()==0) {
			pageInfo.setNextPage(pageInfo.getLastPage());
		}
		String next="<a href="+url+"?cpage="+pageInfo.getNextPage()+param+">下一页</a>";
		String last="<a href="+url+"?cpage="+pageInfo.getLastPage()+param+">尾页</a>";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cpage", pageInfo.getPageNum());
		map.put("count", pageInfo.getTotal());
		map.put("pagecount", pageInfo.getPages());
		map.put("page", first+" "+prev+" "+next+" "+last);
		return map;
	}
	
}

