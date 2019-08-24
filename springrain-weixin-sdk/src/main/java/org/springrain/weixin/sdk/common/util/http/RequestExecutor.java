package org.springrain.weixin.sdk.common.util.http;

import org.springrain.weixin.sdk.common.exception.WxErrorException;
import org.springrain.weixin.sdk.common.service.IWxConfig;

import java.io.IOException;

/**
 * http请求执行器
 *
 * @param <T> 返回值类型
 * @param <E> 请求参数类型
 */
public interface RequestExecutor<T, E> {

    /**
     * @param configStorage 微信配置属性
     * @param uri           uri
     * @param data          数据
     * @throws WxErrorException
     * @throws IOException
     */
    T execute(IWxConfig wxConfig, String uri, E data) throws WxErrorException, IOException;

}
