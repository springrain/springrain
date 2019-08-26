package org.springrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 主入口,排除@Controller注解,主要为了Controller指定命名规则. 这个类所在的包,就是默认扫描的根包.
 *
 * @author caomei
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "${springrain.basepackagepath}" }, excludeFilters = {
        @Filter(type = FilterType.ANNOTATION, value = Controller.class) })
public class SpringrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringrainApplication.class, args);
    }

}
