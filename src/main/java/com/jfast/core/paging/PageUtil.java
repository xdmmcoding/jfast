package com.jfast.core.paging;

import java.util.List;

public class PageUtil {

	// 分页参数变量
	public static final String PAGEPARAM = "pageParam";
	
	/**
	 * 初始化分页参数信息
	 * @return
	 */
	public static PageParam initPageParam(){
		PageParam pageParam=new PageParam();
		pageParam.setLimit(10);
		pageParam.setPage(1);
		return pageParam;
	}

	/**
	 * 封装分页信息
	 * 
	 * @param list
	 * @param pageParam
	 * @return
	 */
	public static <T> PageBean<T> getPage(List<T> list, PageParam pageParam) {
		PageBean<T> pv = new PageBean<T>();
		pv.setItem(list);
		pv.setTotal(pageParam.getTotal());
		pv.setPage(pageParam.getPage());
		return pv;
	}
	
	public static PageParam initPageParam(Integer page,Integer limit){
		PageParam pageParam=new PageParam();
		pageParam.setPage(page);
		pageParam.setLimit(limit);
		return pageParam;
	}
}
