package com.jfast.service.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @Description Dao接口  
 * @ClassName   IBaseService  
 * @Date        2019年7月23日 下午4:17:01  
 * @Author      xd  
 * Copyright (c) All Rights Reserved, 2019.
 */
public interface IBaseService<M, E, K> extends Serializable {
    /**
     * 新增实体
     *
     * @param entity
     * @return
     */
	@Transactional
    int create(M entity);

    /**
     * 更新实体(选择性，为NULL时不更新)
     *
     * @param entity
     * @return
     */
	@Transactional
    int update(M entity);
    /**
     * 
     * @Description 更新实体。不判断传入值是否为NULL  
     * @Author      xd  
     * @Date        2020年5月25日 下午3:33:07  
     * @param @param entity
     * @param @return 参数  
     * @return int 返回类型   
     * @throws
     */
	@Transactional
    int updateByAll(M entity);

    /**
     * 根据ID获取实体
     *
     * @param entityId
     * @return
     */
    M getById(K entityId);
    /**
     * 根据ID删除实体
     *
     * @param entityId
     * @return
     */
    @Transactional
    int delete(K entityId);

    /**查询符合给定条件的实体列表（不包含BLOB字段）
     *
     * @param condition
     * @return
     */
    List<M> getResultWithBLOBs(E condition);

    /**查询符合给定条件的实体列表（包含BLOB字段）
     *
     * @param condition
     * @return
     */
    List<M> getResult(E condition);
}
