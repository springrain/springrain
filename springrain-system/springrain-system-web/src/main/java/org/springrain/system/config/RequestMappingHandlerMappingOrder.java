package org.springrain.system.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springrain.frame.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理Controller映射的排序,一个url映射可以有多个实现
 */
public class RequestMappingHandlerMappingOrder extends RequestMappingHandlerMapping {


    // post uri 作为key,对应构造的RequestMappingInfo,用于删除和@Order冲突的RequestMapping
    private static Map<String, RequestMappingInfo> requestMappingInfoMap = new HashMap<>();

    /**
     * 注册时绑定 HandlerMethod 和 RequestMappingInfo的关系
     */
    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        String key=mapping.getMethodsCondition().toString()+" "+mapping.getPatternsCondition();
        RequestMappingInfo oldMapping= requestMappingInfoMap.get(key);
        if (method.getDeclaringClass().isAnnotationPresent(Order.class)||method.isAnnotationPresent(Order.class)){//如果存在order,就删除掉已经注册的 mapping
            if (oldMapping!=null) {
                super.unregisterMapping(oldMapping);
            }
        }else if(oldMapping!=null){//已经注册过了,不能重复注册
            return;
        }
        requestMappingInfoMap.put(key,mapping);

        super.registerHandlerMethod(handler, method, mapping);
    }






}
