package com.jfast.vo;

import com.jfast.E;

/**
 * 
 * @Description 接口返回格式    
 * @ClassName   ResVo  
 * @Date        2019年4月8日 下午2:26:52  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2019.
 */
public class ExportVo<T> extends OptResult{
	
    private static final long serialVersionUID = 1L;
    /**
     * 数据对象
     */
    private T data;
	
	/**
	 * 
     * @Description 接口返回格式  
     * @Author      xd  
     * @Date        2019年4月8日 下午2:26:25  
     * @param @param flag
     * @param @param code
     * @param @param message
     * @param @param t
     * @param @return 参数  
     * @return DataResult<T> 返回类型   
     * @throws
	 */
	public static ExportVo<Object> result(boolean flag, Integer code,String message,Object data) {
		ExportVo<Object> dataResult = new ExportVo<Object>();
        dataResult.setSuccess(flag);
        dataResult.setCode(code);
        dataResult.setMessage(message);
        dataResult.setData(data);
        return dataResult;
    }
	/**
	 * 
     * @Description 成功  
     * @Author      xd  
     * @Date        2019年7月30日 上午8:53:31  
     * @param @param data
     * @param @return 参数  
     * @return DataResult<Object> 返回类型   
     * @throws
	 */
	public static ExportVo<Object> success(Object data) {
		ExportVo<Object> dataResult = new ExportVo<Object>();
        dataResult.setSuccess(true);
        dataResult.setCode(E.SUCCESS.getCode());
        dataResult.setMessage(E.SUCCESS.getMessage());
        dataResult.setData(data);
        return dataResult;
    }
	
	public static ExportVo<Object> success() {
		ExportVo<Object> dataResult = new ExportVo<Object>();
		dataResult.setSuccess(true);
		dataResult.setCode(E.SUCCESS.getCode());
		dataResult.setMessage(E.SUCCESS.getMessage());
		return dataResult;
	}
	/**
	 * 
     * @Description 失败  
     * @Author      xd  
     * @Date        2019年7月30日 上午8:53:50  
     * @param @param code
     * @param @param message
     * @param @return 参数  
     * @return DataResult<Object> 返回类型   
     * @throws
	 */
    public static ExportVo<Object> fail(Integer code,String message) {
    	ExportVo<Object> dataResult = new ExportVo<Object>();
        dataResult.setSuccess(false);
        dataResult.setCode(code);
        dataResult.setMessage(message);
        dataResult.setData("");
        return dataResult;
    }
    
    public static ExportVo<Object> fail(String message) {
    	ExportVo<Object> dataResult = new ExportVo<Object>();
        dataResult.setSuccess(false);
        dataResult.setCode(E.FAIL.getCode());
        dataResult.setMessage(message);
        dataResult.setData("");
        return dataResult;
    }
    
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataResult{" +
                "data=" + data +
                '}';
    }
}
