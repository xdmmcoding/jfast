package com.jfast.vo;

import java.util.List;

public class ExcelDataVo{
	/**表头*/
	private String[] head;
	/**数据*/
	private List<String[]> rows;
	
	public String[] getHead() {
		return head;
	}
	public void setHead(String[] head) {
		this.head = head;
	}
	public List<String[]> getRows() {
		return rows;
	}
	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}
}
