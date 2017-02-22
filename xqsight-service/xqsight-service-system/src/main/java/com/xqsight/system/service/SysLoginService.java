/**
 * 新启工作室
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.system.service;

import com.xqsight.common.core.dao.Dao;
import com.xqsight.common.core.service.DefaultEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.system.model.SysLogin;
import com.xqsight.system.mapper.SysLoginMapper;


/**
 * <p>用户登陆表实现类service</p>
 * <p>Table: sys_login - 用户登陆表</p>
 * @since 2017-02-22 04:29:47
 * @author wangganggang
 */
@Service
public class SysLoginService extends DefaultEntityService<SysLogin, Long> {

	@Autowired
	private SysLoginMapper sysLoginMapper;

	@Override
	protected Dao<SysLogin, Long> getDao() {
		return sysLoginMapper;
	}
}