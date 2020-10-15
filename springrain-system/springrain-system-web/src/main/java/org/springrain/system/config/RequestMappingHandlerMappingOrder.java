package org.springrain.system.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springrain.frame.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现一个URI映射多个业务实现,通过@Order注解标识优先级,目前强制一个URI只能有一个@Order起效.
 */
public class RequestMappingHandlerMappingOrder extends RequestMappingHandlerMapping {


    // post uri 作为key,对应构造的RequestMappingInfo,用于删除和@Order冲突的RequestMapping
    private static Map<String, RequestMappingInfo> requestMappingInfoMap = new HashMap<>();

    /**
     * 注册时绑定 HandlerMethod 和 RequestMappingInfo的关系
     */
    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {

        //普通mapping key
        String key=mapping.getMethodsCondition().toString()+" "+mapping.getPatternsCondition();
        // order mapping key
        String orderKey=key+" order";
        if (method.getDeclaringClass().isAnnotationPresent(Order.class)||method.isAnnotationPresent(Order.class)){//如果存在order,就删除掉已经注册的 普通mapping
            RequestMappingInfo oldMapping= requestMappingInfoMap.get(key);
            if (oldMapping!=null&&requestMappingInfoMap.get(orderKey)==null) {//如果没有order mapping映射,就删除这个普通映射
                super.unregisterMapping(oldMapping);
            }
            //放入 order
            requestMappingInfoMap.put(orderKey,mapping);
        }else if(requestMappingInfoMap.get(orderKey)!=null){//已经存在 order 排序的映射了,就不再处理 普通mapping了
            return;
        }else{//其他情况 作为 普通mapping
            requestMappingInfoMap.put(key,mapping);
        }
        super.registerHandlerMethod(handler, method, mapping);
    }






}
