package com.jfast.core.paging;

import java.util.List;

public class PageBean<T> {
	/**每页的条数*/
	private int limit;
	/**当前页*/
	private int page;
	//解析接口状态
	private Integer code = 0; //解析接口状态
	//解析提示文本
	private String msg;
	//解析数据长度
	private Integer count; 
	//
	private List<T> data;
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
