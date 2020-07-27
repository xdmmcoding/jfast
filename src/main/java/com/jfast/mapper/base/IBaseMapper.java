package com.jfast.mapper.base;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;


public interface IBaseMapper<M, E, K> extends Serializable {

	//int countByExample(E example);

	int deleteByPrimaryKey(K id);

	int insert(M record);

	int insertSelective(M record);

	List<M> selectByExample(E example);

	List<M> selectByExample(E example, RowBounds bounds);

	List<M> selectByExampleWithBLOBs(E example);

	M selectByPrimaryKey(K logId);

	int updateByPrimaryKeySelective(M record);

	int updateByPrimaryKey(M record);
}