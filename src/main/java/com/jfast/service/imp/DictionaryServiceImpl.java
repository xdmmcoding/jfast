package com.jfast.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfast.mapper.DictionaryMapper;
import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.Dictionary;
import com.jfast.pojo.DictionaryExample;
import com.jfast.pojo.DictionaryKey;
import com.jfast.service.DictionaryService;
import com.jfast.service.imp.base.BaseService;

@Service("dictionaryService")
public class DictionaryServiceImpl extends BaseService<Dictionary, DictionaryExample, DictionaryKey> implements DictionaryService {
	    
	private static final long serialVersionUID = 1L;

	@Autowired 
	private DictionaryMapper dictionaryMapper;
	
	@Override
	public IBaseMapper<Dictionary, DictionaryExample, DictionaryKey> getMapper() {
		return dictionaryMapper;
	}
}
