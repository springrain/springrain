package org.springrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springrain.frame.util.GlobalStatic;

//import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 主入口,排除@Controller注解,主要为了Controller指定命名规则. 这个类所在的包,就是默认扫描的根包.
 * 注意:只能有一个@SpringBootApplication入口文件,如果其他模块引用了这个模块,就需要把类上的注解都注释掉.
 * 也可以其他的名称和这个名称保持一致,使用会自动覆盖这个实现,使用InitSystemWebPermission这个类初始化自定义的权限
 *
 */
//@EnableScheduling  //启用调度
@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
//@ComponentScan(basePackages = {"${springrain.basepackagepath}"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class SpringrainApplication {
    public static void main(String[] args) {
        //设置项目名前缀
        GlobalStatic.projectKeyPrefix = "springrain_";
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringrainApplication.class, args);
    }

}
