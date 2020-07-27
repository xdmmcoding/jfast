package com.jfast.vo;

import java.util.List;

public class GridVo {
	//解析接口状态
	private Integer code = 0; //解析接口状态
	//解析提示文本
	private String msg;
	//解析数据长度
	private Integer count; 
	//
	private List<?> data;
	//
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
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	
	public GridVo(Integer count,List<?> data){
		this.count = count;
		this.data = data;
	}
	
	public GridVo(Integer code,String msg,Integer count,List<?> data){
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
}
