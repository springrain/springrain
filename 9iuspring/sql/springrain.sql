/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/7/3 22:55:07                            */
/*==============================================================*/


drop table if exists T_USERS;

drop table if exists t_menu;

drop table if exists t_organization;

drop table if exists t_role;

drop table if exists t_subsystem;

drop table if exists t_user_menu;

drop table if exists t_user_org;

drop table if exists t_user_role;

/*==============================================================*/
/* Table: T_USERS                                               */
/*==============================================================*/
create table T_USERS
(
   id                   varchar(40) not null comment '编号',
   name                 varchar(30) comment '姓名',
   code                 varchar(40) comment '代码',
   account              varchar(40) comment '账号',
   password             varchar(40) comment '密码',
   salt                 varchar(60) comment '密码校验码',
   age                  int comment '年龄',
   sex                  int comment '0.女1.男',
   phone                varchar(16) comment '电话号码',
   mobile               varchar(16) comment '手机号码',
   eamil                varchar(60) comment '邮箱',
   address              varchar(255) comment '地址',
   state                int comment '0.无效1.有效',
   primary key (id)
);

alter table T_USERS comment '用户表';

/*==============================================================*/
/* Table: t_menu                                                */
/*==============================================================*/
create table t_menu
(
   id                   varchar(40) not null,
   name                 varchar(60),
   pid                  varchar(40),
   description          text,
   pageurl              text,
   type                 0.普通资源1.菜单资源,
   state                int,
   primary key (id)
);

/*==============================================================*/
/* Table: t_organization                                        */
/*==============================================================*/
create table t_organization
(
   id                   varchar(40) not null comment '编号',
   name                 varchar(60) comment '名称',
   comcode              varchar(40) comment '代码',
   pid                  varchar(40) comment '上级部门ID',
   sysid                varchar(40) comment '子系统ID',
   type                 int comment '0,组织机构 1.部门',
   leaf                 int comment '叶子节点(0:树枝节点;1:叶子节点)',
   sortno               int comment '排序号',
   description          text comment '描述',
   state                int comment '0.失效 1.有效',
   primary key (id)
);

alter table t_organization comment '组织机构';

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   id                   varchar(40) not null comment '角色ID',
   name                 varchar(60) comment '角色名称',
   permissions          varchar(255) comment '权限',
   pid                  varchar(40) comment '所属部门',
   remark               varchar(255) comment '备注',
   state                int comment '状态(0:禁用2:启用)',
   primary key (id)
);

alter table t_role comment '角色';

/*==============================================================*/
/* Table: t_subsystem                                           */
/*==============================================================*/
create table t_subsystem
(
   id                   varchar(40) not null comment '子系统编号',
   name                 varchar(60) comment '子系统名称',
   sortno               int comment '子系统排序',
   uri                  text comment '子系统访问路径',
   remark               text comment '备注',
   state                varchar(2) comment '状态(0:不可用1:可用)',
   primary key (id)
);

alter table t_subsystem comment '子系统';

/*==============================================================*/
/* Table: t_user_menu                                           */
/*==============================================================*/
create table t_user_menu
(
   id                   varchar(40) not null comment '编号',
   role_id              varchar(40) comment '角色编号',
   menu_id              varchar(40) comment '菜单编号',
   primary key (id)
);

/*==============================================================*/
/* Table: t_user_org                                            */
/*==============================================================*/
create table t_user_org
(
   id                   varchar(40) not null comment '编号',
   user_id              varchar(40) comment '用户编号',
   org_id               varchar(40) comment '机构编号',
   ismanager            int comment '0.不是1.是',
   primary key (id)
);

alter table t_user_org comment '用户机构表';

/*==============================================================*/
/* Table: t_user_role                                           */
/*==============================================================*/
create table t_user_role
(
   id                   varchar(40) not null comment '编号',
   user_id              varchar(40) comment '用户编号',
   role_id              varchar(40) comment '角色编号',
   primary key (id)
);

