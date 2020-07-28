package com.jfast.core.paging;

import java.io.Serializable;

import org.apache.ibatis.session.RowBounds;

public class PageParam implements Serializable{
	    
	private static final long serialVersionUID = 1L;
	/**每页的条数*/
	private int limit;
	/**当前页*/
	private int page;
	/**总条数*/
	private int total;
	
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	private  transient RowBounds rowBounds;
	
	public RowBounds getRowBounds() {
		this.rowBounds = new RowBounds(page,limit);
		return rowBounds;
	}

}
