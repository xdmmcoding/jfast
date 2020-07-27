package com.jfast.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfast.vo.ExportVo;



@Controller
@RequestMapping("/app/resource")
public class ClientResourceContoller {
	/**
	 * 
     * @Description 判断下载权限  
     * @Author      lixudong  
     * @Date        2019年11月29日 下午3:23:13  
     * @param @param request
     * @param @param response
     * @param @param contentid
     * @param @param type
     * @param @param siteid
     * @param @param subjectid
     * @param @return
     * @param @throws Exception 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = { "/down.html" }, produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
	@ResponseBody
	public ExportVo<Object> download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return ExportVo.success(null);
	}
}
