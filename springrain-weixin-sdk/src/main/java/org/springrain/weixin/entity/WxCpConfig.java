package org.springrain.weixin.entity;

import org.springrain.frame.entity.BaseEntity;
import org.springrain.weixin.sdk.common.wxconfig.IWxCpConfig;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "wx_cpconfig")
public class WxCpConfig extends BaseEntity implements IWxCpConfig {
    private static final long serialVersionUID = 1L;

    private volatile String id;


    @Override
    @Id
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }






}
