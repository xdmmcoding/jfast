package com.jfast.vo;

/**
 * 
 * @Description 资源上传返回类  
 * @ClassName   UploadVo  
 * @Date        2019年8月12日 上午11:43:05  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2019.
 */
public class UploadVo{
	
	private String id;//上传多个图片时的文件标识
	
	private String modular;//模块名称
	
	private String type;//资源类型
	
	private String fileName;
	
	private String url;
	
	public String getId() {
		return id;
	}

	public String getModular() {
		return modular;
	}

	public void setModular(String modular) {
		this.modular = modular;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
