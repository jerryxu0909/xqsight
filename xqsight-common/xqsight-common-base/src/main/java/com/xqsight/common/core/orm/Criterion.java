package com.xqsight.common.core.orm;

import com.xqsight.common.model.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;


/**
 * @author wangganggang
 * @Date 2017/3/23
 *
 * 查询共用类
 */
public class Criterion implements Serializable {
    private static Logger logger = LogManager.getLogger(Criterion.class);

    private String order = Sort.DESC;
    private String orderBy;

    private List<PropertyFilter> propertyFilters = Collections.EMPTY_LIST;

    private String customSql;

    private List<PropertyFilter> extraCriteria = Collections.EMPTY_LIST;

    public Criterion() {
    }

    public Criterion(List<PropertyFilter> propertyFilters) {
        this.propertyFilters = propertyFilters;
    }

    public Criterion(List<PropertyFilter> propertyFilters,List<Sort> sorts) {
        this.propertyFilters = propertyFilters;
        if (sorts != null) {
            StringBuilder orderBySb = new StringBuilder();
            StringBuilder orderSb = new StringBuilder();

            int i = 0;
            for (Sort sort : sorts) {
                if (i != 0) {
                    orderBySb.append(Constants.COMMA_SIGN_SPLIT_NAME);
                    orderSb.append(Constants.COMMA_SIGN_SPLIT_NAME);
                }
                i++;
                orderBySb.append(sort.getOrderBy());
                orderSb.append(sort.getOrder());
            }
            this.setOrderBy(orderBySb.toString());
            this.setOrder(orderSb.toString());
        }
    }

    public Criterion(List<PropertyFilter> propertyFilters, String orderBy) {
        this.propertyFilters = propertyFilters;
        this.orderBy = orderBy;
    }

    public Criterion(List<PropertyFilter> propertyFilters, String orderBy, String order) {
        this.propertyFilters = propertyFilters;
        this.orderBy = orderBy;
        this.order = order;
    }

    /**
     * Property accessor of order
     *
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Property accessor of orderBy
     *
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * Property accessor of criteria
     *
     * @return the criteria
     */
    public List<PropertyFilter> getCriteria() {
        return propertyFilters;
    }

    /**
     * @param propertyFilters the criteria to set
     */
    public void setCriteria(List<PropertyFilter> propertyFilters) {
        this.propertyFilters = propertyFilters;
    }

    public String getWhereSqlString() {
        return constructSqlString(propertyFilters);
    }

    public void setCustomCriteria(String sql) {
        this.customSql = sql;
    }

    public String getCustomSqlString() {
        return customSql;
    }

    public String getExtraWhereSqlString() {
        return constructSqlString(extraCriteria);
    }

    public String getOrderBySqlString() {
        if (StringUtils.isNotBlank(orderBy)) {
            //拼装order条件
            StringBuilder sb = new StringBuilder(" ORDER BY ");
            String[] orderBys = StringUtils.split(orderBy, ',');
            if (StringUtils.isBlank(order)) {
                order = Sort.DESC;
            }
            String[] orders = StringUtils.split(order, ',');

            int i = 0;
            for (String orderBy : orderBys) {
                if (i != 0) {
                    sb.append(" " + Constants.COMMA_SIGN_SPLIT_NAME + " ");
                }
                sb.append(orderBy);

                String curOrder = Sort.DESC;
                if (orders.length == 1) {
                    curOrder = orders[0];
                } else {
                    if ((i + 1) > orders.length) {
                        curOrder = Sort.DESC;
                    } else {
                        curOrder = orders[i];
                    }
                }
                sb.append(" " + curOrder);
                i++;
            }
            logger.debug("getOrderBySqlString:{}", sb.toString());
            return sb.toString();
        }

        return "";
    }

    public List<PropertyFilter> getExtraCriteria() {
        return extraCriteria;
    }

    public void setExtraCriteria(List<PropertyFilter> extraCriteria) {
        this.extraCriteria = extraCriteria;
    }

    protected String constructSqlString(List<PropertyFilter> in) {
        //查询参数
        if (in != null && in.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (PropertyFilter filter : in) {
                sb.append("and" + filter.getSqlString());
            }
            logger.debug("getWhereSqlString:{}", sb.toString());
            return sb.toString();
        }
        return "";
    }
}
