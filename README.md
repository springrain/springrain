## 5.1.0 项目入口是springrain-system-web,基于Istio实现微服务,正在整理文档.

## 实现了什么?
* 不增加学习成本,像单体一样开发分布式微服务.
* 不修改业务代码,可以实现单体,分层,微服务多种部署模式切换.
* 集成seata分布式事务实现. 

## 实现思路
* 启动加载springbean时,先检查本地是否有实现,如果没有就启动GRPC远程调用.开发人员无感知.
* 使用seata分布式事务实现.无注解,开发人员无感知.
* 基于Istio实现微服务的监控,熔断,限流.开发人员无感知.

## 限制
* 接口和实现的命名强制规范.
* 一个RPC接口只能有一个实现.

## 体验单体到分层切换
* 修改springrain-system-web依赖springrain-system-service,不再依赖springrain-system-serviceimpl.
* springrain-system-serviceimpl添加springrain-grpc-server依赖,启用org.springrain.SystemServiceImplApplication的@SpringBootApplication注解
* 修改file.conf中vgroup_mapping.seata_tx_group = "default",启动seata服务.就是把默认的my_test_tx_group更换成seata_tx_group
* 启动springrain-system-serviceimpl
* 启动springrain-system-web
* 访问http://127.0.0.1:8080/ 

### 项目名为springrain[春雨]我的个人博客是 http://www.weicms.net </br>
### 文档
https://gitee.com/chunanyong/springrain/tree/master/springrain-system/springrain-system-web/doc  </br>
### 代码生成器
https://gitee.com/chunanyong/springrain/tree/master/springrain-system/springrain-system-web/gencode  </br>
### sql脚本
https://gitee.com/chunanyong/springrain/tree/master/springrain-system/springrain-system-web/sql  </br>


springrain是spring/springboot的极简封装,springboot一站式开发的范例.

springrain是一个完整的Maven项目,包含spring core,spring jdbc,spring mvc.

springrain自带一个代码生成器,能够生成对表的增删改查的逻辑代码,以及前台页面样式和js文件

项目只依赖spring,没有hibernate,struts,ibatis.

使用shiro权限控制到按钮级

结合shiro,redis实现了天然的分布式session共享

hibernate太过复杂.ibatis把sql写入xml文件,利于数据库调优和sql语句管理.数据库调优可以使用druid输出比慢sql,比分析xml中的语句更直观,springrain所有的sql语句都使用Finder封装,只要查看Finder的引用,就能查看项目中所有的sql语句,也比较容易管理.

一些测试案例：

```java
//就极简而言,一个数据库只需要一个Service,就可以管理这个数据库的任意一张表 
//@Test  查询基本类型
public void testObject() throws Exception{
       // Finder finder=new Finder("select id from t_user where 1=1 ");
        Finder finder=Finder.getSelectFinder(User.class,"id").append(" WHERE 1=1 "); 
         finder.append("and id=:userId ").setParam("userId", "admin");
        String id = baseDemoService.queryForObject(finder, String.class);
        System.out.println(id);

}

//@Test 查询一个对象
public void testObjectUser() throws Exception{
        //Finder finder=new Finder("select * from t_user where id=:userId order by id"); 
Finder finder=Finder.getSelectFinder(User.class).append(" WHERE id=:userId order by id desc "); 
        finder.setParam("userId", "admin");
        User u = baseDemoService.queryForObject(finder, User.class);
        System.out.println(u.getName());

}
//@Test 查询分页
public void testMsSql() throws Exception{
        //Finder finder=new Finder("select * from t_user order by id");
        Finder finder=Finder.getSelectFinder(User.class).append(" order by id desc ");
        Listlist = baseDemoService.queryForList(finder, User.class, new Page(2));
        System.out.println(list.size());
        for(User s:list){
         System.out.println(s.getName());
         }
}



//@Test 调用数据库存储过程
public void testProc() throws Exception{
        Finder finder=new Finder();
        finder.setParam("unitId", 0);
        finder.setProcName("proc_up");
        Map queryObjectByProc = (Map) baseDemoService.queryObjectByProc(finder);
        System.out.println(queryObjectByProc.get("#update-count-10"));
        

}

//@Test 调用数据库函数
public void testFunction() throws Exception{
        Finder finder=new Finder();
        finder.setFunName("fun_userId");
        finder.setParam("userId", "admin");
        String userName= baseDemoService.queryForObjectByByFunction(finder,String.class);
        System.out.println(userName);
}

```