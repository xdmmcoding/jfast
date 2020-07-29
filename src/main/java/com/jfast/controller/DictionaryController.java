package com.jfast.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jfast.pojo.Dictionary;
import com.jfast.pojo.DictionaryExample;
import com.jfast.service.DictionaryService;
import com.jfast.vo.ExportVo;

@Controller
@RequestMapping("/dict")
public class DictionaryController{
	
	@Autowired
	private DictionaryService dictionaryService;
	/**
	 * 
     * @Description 查询数字字典  
     * @Author      xd  
     * @Date        2019年10月21日 上午11:43:50  
     * @param @param request
     * @param @param response
     * @param @param file
     * @param @param type
     * @param @param modular
     * @param @return 参数  
     * @return ExportVo<Object> 返回类型   
     * @throws
	 */
	@RequestMapping(value = "/selected",method=RequestMethod.POST)
	@ResponseBody
	public ExportVo<Object> selected(HttpServletRequest request,HttpServletResponse response,String type) {
		if(StringUtils.isEmpty(type))return ExportVo.fail(null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		DictionaryExample dictex = new DictionaryExample();
		dictex.createCriteria().andDictTypeEqualTo(type);
		dictex.setOrderByClause("sort asc");
		List<Dictionary> dictList = dictionaryService.getResult(dictex);
		if(dictList != null && !dictList.isEmpty()){
			for(Dictionary dict : dictList){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", dict.getDictCode());
				map.put("name", dict.getDictNameCn());
				list.add(map);
			}
		}
		return ExportVo.success(list);
	}
}
