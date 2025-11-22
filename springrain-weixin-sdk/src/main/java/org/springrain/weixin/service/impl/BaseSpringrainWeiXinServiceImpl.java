package org.springrain.weixin.service.impl;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springrain.frame.dao.IBaseJdbcDao;
import org.springrain.frame.service.BaseServiceImpl;

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
