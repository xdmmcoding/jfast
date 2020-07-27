package com.jfast.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jfast.mapper.base.IBaseMapper;
import com.jfast.pojo.Dictionary;
import com.jfast.pojo.DictionaryExample;
import com.jfast.pojo.DictionaryKey;

@Mapper
public interface DictionaryMapper extends IBaseMapper<Dictionary, DictionaryExample, DictionaryKey>{
	
}