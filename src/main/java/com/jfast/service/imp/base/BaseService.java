package com.jfast.service.imp.base;


import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jfast.mapper.base.IBaseMapper;
import com.jfast.service.base.IBaseService;


/**
 * 
 * @Description Dao接口实现  
 * @ClassName   BaseService  
 * @Date        2019年7月23日 下午4:17:17  
 * @Author      lixudong  
 * Copyright (c) All Rights Reserved, 2019.
 */
public abstract class BaseService<M, E, K extends Serializable> implements IBaseService<M, E, K> {
	private static final long serialVersionUID = -1666364201802785958L;

    /**
     * 获取Mapper
     * @return
     */
    public abstract IBaseMapper<M, E, K> getMapper();

    /**
     * 新增实体
     *
     * @param entity
     * @return
     */
    @Transactional
    public int create(M entity) {
        return getMapper().insertSelective(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public int update(M entity) {
        return getMapper().updateByPrimaryKeySelective(entity);
    }
    @Override
    @Transactional
    public int updateByAll(M entity) {
    	return getMapper().updateByPrimaryKey(entity);
    }

    /**
     * 根据ID删除实体
     *
     * @param entityId
     * @return
     */
    @Override
    @Transactional
    public int delete(K entityId) {
        return getMapper().deleteByPrimaryKey(entityId);
    }

    /**
     * 根据ID获取实体
     *
     * @param entityId
     * @return
     */
    @Override
    public M getById(K entityId) {
        return getMapper().selectByPrimaryKey(entityId);
    }


    /**
     * 根据条件查询集合（不包含BLOB字段）
     * @param condition
     * @return
     */
    @Override
    public List<M> getResult(E condition) {
        return getMapper().selectByExample(condition);
    };
    
    /**
     * 根据条件查询集合（包含BLOB字段）
     * @param condition
     * @return
     */
    @Override
    public List<M> getResultWithBLOBs(E condition) {
    	return getMapper().selectByExampleWithBLOBs(condition);
    };

}
