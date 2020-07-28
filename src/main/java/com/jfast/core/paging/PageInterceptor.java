package com.jfast.core.paging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;


/**
 * 
 * @Description 通过拦截<code>StatementHandler</code>的<code>prepare</code>方法，重写sql语句实现物理分页。
 * 老规矩，签名里要拦截的类型只能是接口。  
 * @ClassName   PagePlugin  
 * @Date        2018年11月1日 下午2:38:08  
 * @Author      lixudong  
 * Copyright (c) All Rights Reserved, 2018.
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class })})
public class PageInterceptor implements Interceptor {
    private static final Log logger = LogFactory.getLog(PageInterceptor.class);
    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY= new DefaultReflectorFactory(); 
    private static String defaultDialect = "mysql"; // 数据库类型(默认为mysql)
    private static String defaultPageSqlId = ".*query.*"; // 需要拦截的ID(正则匹配)
    private static String dialect = ""; // 数据库类型(默认为mysql)
    private static String pageSqlId = ""; // 需要拦截的ID(正则匹配)
    private static boolean pageSqlIdFlag = false; // 需要拦截的ID(正则匹配)

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
        }
        if (null == dialect || "".equals(dialect)) {
            logger.warn("Property dialect is not setted,use default 'mysql' ");
            dialect = defaultDialect;
        }
        if (null == pageSqlId || "".equals(pageSqlId)) {
            logger.warn("Property pageSqlId is not setted,use default '.*Page$' ");
            pageSqlId = defaultPageSqlId;
        }
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        if(!pageSqlIdFlag || (pageSqlIdFlag && mappedStatement.getId().matches(pageSqlId))){        	
        	// 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写带有分页参数的sql
        	BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        	Object parameterObject = boundSql.getParameterObject();
        	if (parameterObject == null) {
        		logger.debug("[=====没有传入任何参数===========]");
        	} else {
        		PageParam pageParam = getPageObject(parameterObject);
        		//如果需要分页
        		if(pageParam!=null){
        			String sql = boundSql.getSql();
        			// 重写sql
        			String pageSql = getPageSql(dialect,sql, pageParam);
        			metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
        			// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
        			metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        			metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
        			// 重设分页参数里的总页数等
        			Connection connection = (Connection) invocation.getArgs()[0];
        			setPageParameter(getPageTotalSql(dialect,sql), connection,mappedStatement, boundSql, pageParam);
        		}else{
        			logger.debug("[=====不需要分页=====]");
        		}
        	}
        }
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }
    
    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    	dialect = properties.getProperty("dialect");
    	pageSqlId = properties.getProperty("pageSqlId");
    	try {
    		String flag = properties.getProperty("pageSqlIdFlag");
        	if(StringUtils.isNotEmpty(flag)){
        		pageSqlIdFlag = Boolean.valueOf(flag);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("dialect=="+dialect+"  pageSqlId=="+pageSqlId);
    }
    /**
     * 
     * @Description 解析分页参数  
     * @Author      lixudong  
     * @Date        2018年11月1日 下午2:23:03  
     * @param @param obj
     * @param @return 参数  
     * @return PageParam 返回类型   
     * @throws
     */
    private PageParam getPageObject(Object obj) {
		PageParam pp = null;
		if (obj != null) {
			if (obj instanceof PageParam) {
				pp = (PageParam) obj;
			} else if (obj instanceof Map) {
				for (Object val : ((Map<?, ?>) obj).values()) {
					if (val instanceof PageParam) {
						pp = (PageParam) val;
						break;
					}
				}
			}

		}
		return pp;
	}
    /**
     * 
     * @Description 重置分页参数  
     * @Author      lixudong  
     * @Date        2018年11月1日 下午2:37:19  
     * @param @param sql
     * @param @param connection
     * @param @param mappedStatement
     * @param @param boundSql
     * @param @param page 参数  
     * @return void 返回类型   
     * @throws
     */
    private void setPageParameter(String sql, Connection connection,MappedStatement mappedStatement, BoundSql boundSql, PageParam page) {
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		try {
			countStmt = connection.prepareStatement(sql);
			setParameters(countStmt, mappedStatement, boundSql,boundSql.getParameterObject());
			rs = countStmt.executeQuery();
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			page.setTotal(totalCount);
		} catch (SQLException e) {
			logger.error("执行分页查询异常", e);
		} finally {
			try {
				if (rs != null)rs.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
			try {
				if (countStmt != null)countStmt.close();
			} catch (SQLException e) {
				logger.error("Ignore this exception", e);
			}
		}
	}
    /**
     * 
     * @Description 对(?)参数进行设值  
     * @Author      lixudong  
     * @Date        2018年11月1日 下午2:36:12  
     * @param @param ps
     * @param @param mappedStatement
     * @param @param boundSql
     * @param @param parameterObject
     * @param @throws SQLException 参数  
     * @return void 返回类型   
     * @throws
     */
	private void setParameters(PreparedStatement ps,MappedStatement mappedStatement, BoundSql boundSql,Object parameterObject) throws SQLException {
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
		parameterHandler.setParameters(ps);
	}
	
	
	/**
	 * 
     * @Description 重写分页sql  
     * @Author      lixudong  
     * @Date        2019年7月24日 上午10:23:00  
     * @param @param dialect
     * @param @param srcSql
     * @param @param pageParam
     * @param @return 参数  
     * @return String 返回类型   
     * @throws
	 */
	public String getPageSql(String dialect,String srcSql, PageParam pageParam) {
		if("mysql".equals(dialect)){			
			StringBuilder pageSql = new StringBuilder(100);
			String beginrow = String.valueOf((pageParam.getPage() - 1) * pageParam.getLimit());
			pageSql.append(srcSql);
			pageSql.append(" limit " + beginrow + "," + pageParam.getLimit());
			return pageSql.toString();
		}
		
		return null;
	}

	
	public String getPageTotalSql(String dialect,String srcSql) {
		if("mysql".equals(dialect)){	
			return "select count(0) as total from (" + srcSql + ") tbln";
		}
		
		return null;
	}
}
