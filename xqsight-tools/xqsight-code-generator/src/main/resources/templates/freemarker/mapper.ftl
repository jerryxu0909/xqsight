<#include "copyright.ftl"/>
package ${basePackage}.${moduleName}.mapper;

import com.xqsight.common.base.dao.ICrudDao;

import ${basePackage}.${moduleName}.model.${table.className};

/**
 * <p>${table.remarks}实现类service</p>
 * <p>Table: ${table.tableName} - ${table.remarks}</p>
 * @since ${.now}
 * @author wangganggang
*/
public interface ${table.className}Mapper extends ICrudDao<${table.className},Long>{
}