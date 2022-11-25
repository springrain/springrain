package org.springrain.weixin.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springrain.frame.dao.IBaseJdbcDao;
import org.springrain.frame.service.BaseServiceImpl;

import jakarta.annotation.Resource;

public class BaseSpringrainWeiXinServiceImpl extends BaseServiceImpl {

    @Resource
    @Lazy
    IBaseJdbcDao baseSpringrainDao;

    public BaseSpringrainWeiXinServiceImpl() {
    }

    @Override
    public IBaseJdbcDao getBaseDao() {
        return baseSpringrainDao;
    }
}
