

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_auditlog_history_2013`
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2013`;
CREATE TABLE `t_auditlog_history_2013` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `operationType` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `preValue` varchar(8000) DEFAULT NULL COMMENT '旧值',
  `curValue` varchar(8000) DEFAULT NULL COMMENT '新值',
  `operationTime` datetime DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) DEFAULT NULL COMMENT '记录ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录';

-- ----------------------------
-- Records of t_auditlog_history_2013
-- ----------------------------
-- ----------------------------
-- Table structure for `t_fwlog_history_2013`
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2013`;
CREATE TABLE t_fwlog_history_2013 (
  id varchar(100) NOT NULL COMMENT 'ID',
  startDate datetime DEFAULT NULL COMMENT '访问时间',
  strDate varchar(100) DEFAULT NULL COMMENT '访问时间(毫秒)',
  tomcat varchar(50) DEFAULT NULL COMMENT 'Tomcat',
  userCode varchar(300) DEFAULT NULL COMMENT '登陆账号',
  userName varchar(200) DEFAULT NULL COMMENT '姓名',
  sessionId varchar(300) DEFAULT NULL COMMENT 'sessionId',
  ip varchar(200) DEFAULT NULL COMMENT 'IP',
  fwUrl varchar(3000) DEFAULT NULL COMMENT '访问菜单',
  menuName varchar(100) DEFAULT NULL COMMENT '菜单名称',
  isqx varchar(2) DEFAULT NULL COMMENT '是否有权限访问',
  PRIMARY KEY (id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问日志';
-- ----------------------------
-- Records of t_fwlog_history_2013
-- ----------------------------

  -- ----------------------------
-- Table structure for `t_auditlog_history_2014`
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2014`;
CREATE TABLE `t_auditlog_history_2014` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `operationType` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `preValue` varchar(8000) DEFAULT NULL COMMENT '旧值',
  `curValue` varchar(8000) DEFAULT NULL COMMENT '新值',
  `operationTime` datetime DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) DEFAULT NULL COMMENT '记录ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录';

-- ----------------------------
-- Records of t_auditlog_history_2014
-- ----------------------------
-- ----------------------------
-- Table structure for `t_fwlog_history_2014`
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2014`;
CREATE TABLE t_fwlog_history_2014 (
  id varchar(100) NOT NULL COMMENT 'ID',
  startDate datetime DEFAULT NULL COMMENT '访问时间',
  strDate varchar(100) DEFAULT NULL COMMENT '访问时间(毫秒)',
  tomcat varchar(50) DEFAULT NULL COMMENT 'Tomcat',
  userCode varchar(300) DEFAULT NULL COMMENT '登陆账号',
  userName varchar(200) DEFAULT NULL COMMENT '姓名',
  sessionId varchar(300) DEFAULT NULL COMMENT 'sessionId',
  ip varchar(200) DEFAULT NULL COMMENT 'IP',
  fwUrl varchar(3000) DEFAULT NULL COMMENT '访问菜单',
  menuName varchar(100) DEFAULT NULL COMMENT '菜单名称',
  isqx varchar(2) DEFAULT NULL COMMENT '是否有权限访问',
  PRIMARY KEY (id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问日志';
  
  

  -- ----------------------------
-- Table structure for `t_auditlog_history_2015`
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2015`;
CREATE TABLE `t_auditlog_history_2015` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `operationType` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `preValue` varchar(8000) DEFAULT NULL COMMENT '旧值',
  `curValue` varchar(8000) DEFAULT NULL COMMENT '新值',
  `operationTime` datetime DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) DEFAULT NULL COMMENT '记录ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录';

-- ----------------------------
-- Records of t_auditlog_history_2015
-- ----------------------------
-- ----------------------------
-- Table structure for `t_fwlog_history_2015`
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2015`;
CREATE TABLE t_fwlog_history_2015 (
  id varchar(100) NOT NULL COMMENT 'ID',
  startDate datetime DEFAULT NULL COMMENT '访问时间',
  strDate varchar(100) DEFAULT NULL COMMENT '访问时间(毫秒)',
  tomcat varchar(50) DEFAULT NULL COMMENT 'Tomcat',
  userCode varchar(300) DEFAULT NULL COMMENT '登陆账号',
  userName varchar(200) DEFAULT NULL COMMENT '姓名',
  sessionId varchar(300) DEFAULT NULL COMMENT 'sessionId',
  ip varchar(200) DEFAULT NULL COMMENT 'IP',
  fwUrl varchar(3000) DEFAULT NULL COMMENT '访问菜单',
  menuName varchar(100) DEFAULT NULL COMMENT '菜单名称',
  isqx varchar(2) DEFAULT NULL COMMENT '是否有权限访问',
  PRIMARY KEY (id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问日志';
  
  
  -- ----------------------------
-- Table structure for `t_dic_data`
-- ----------------------------
DROP TABLE IF EXISTS `t_dic_data`;
CREATE TABLE `t_dic_data` (
  `id` varchar(40) NOT NULL PRIMARY KEY,
  `name` varchar(60) NOT NULL COMMENT '名称',
  `code` varchar(60) DEFAULT NULL COMMENT '编码',
 `description` varchar(2000) DEFAULT NULL COMMENT '描述',
 `state` varchar(2) DEFAULT '是' COMMENT '是否有效',
 `typekey` varchar(20) COMMENT '类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公共字典';

-- ----------------------------
-- Records of t_dic_data
-- ----------------------------


INSERT INTO `t_dic_data` VALUES ('16b80bfb-f0ee-47a0-ba94-cc256abaed17', '专科', '', '', '是', 'xueli');
INSERT INTO `t_dic_data` VALUES ('7ed23330-5538-4943-8678-0c5a2121cf57', '高中', '', '', '是', 'xueli');
INSERT INTO `t_dic_data` VALUES ('d7d1744b-e69f-48d0-9760-b2eae6af039b', '本科', '', '', '是', 'xueli');

INSERT INTO `t_dic_data` VALUES ('936db407-ae1-45a7-a657-b60580e2a77a', '汉族', '', '', '是', 'minzu');
INSERT INTO `t_dic_data` VALUES ('936db407-ae2-45a7-a657-b60580e2a77a', '回族', '', '', '是', 'minzu');

INSERT INTO `t_dic_data` VALUES ('936db407-ae3-45a7-a657-b60580e2a77a', '一级', '', '', '是', 'grade');
INSERT INTO `t_dic_data` VALUES ('936db407-ae4-45a7-a657-b60580e2a77a', '二级', '', '', '是', 'grade');




-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(40) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `pid` varchar(40) DEFAULT NULL,
 `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `pageurl` varchar(3000),
  `type` int(11) DEFAULT NULL COMMENT '0.功能按钮,1.导航菜单',
 `state` varchar(2) DEFAULT '是' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('system_manager', '系统管理', null, null, null, 1, '是');
INSERT INTO `t_menu` VALUES ('business_manager', '业务管理', NULL, NULL, null, 1, '是');
INSERT INTO `t_menu` VALUES ('dic_manager', '字典管理', NULL, NULL, null, 1, '是');


-- ----------------------------
-- Table structure for `t_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `name` varchar(60) DEFAULT NULL COMMENT '名称',
  `comcode` varchar(40) DEFAULT NULL COMMENT '代码',
  `pid` varchar(40) DEFAULT NULL COMMENT '上级部门ID',
  `sysid` varchar(40) DEFAULT NULL COMMENT '子系统ID',
  `type` int(11) DEFAULT NULL COMMENT '0,组织机构 1.部门',
  `leaf` int(11) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `sortno` int(11) DEFAULT NULL COMMENT '排序号',
 `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `state` varchar(2) DEFAULT '是' COMMENT '是否有效(否/是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of t_org
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(40) NOT NULL COMMENT '角色ID',
  `name` varchar(60) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '权限编码',
  `pid` varchar(40) DEFAULT NULL COMMENT '上级角色ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `state` varchar(2) DEFAULT '是' COMMENT '是否有效(否/是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('admin', '管理员', 'admin', null, null, '是');

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `roleId` varchar(40) NOT NULL COMMENT '角色编号',
  `menuId` varchar(40) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`),
  KEY `fk_t_role_menu_roleId_t_role_id` (`roleId`),
  KEY `fk_t_role_menu_menuId_t_menu_id` (`menuId`),
  CONSTRAINT `fk_t_role_menu_menuId_t_menu_id` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `fk_t_role_menu_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单中间表';



-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `workno` varchar(40) DEFAULT NULL COMMENT '工号',
  `account` varchar(40) DEFAULT NULL COMMENT '账号',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
   `cardno` varchar(40) DEFAULT NULL COMMENT '身份证',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex`  varchar(2)  DEFAULT '男' COMMENT '性别',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话号码',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `gradeId` varchar(40) DEFAULT null COMMENT '级别',
   `eduName` varchar(40) DEFAULT null COMMENT '学历',
   `fireName` varchar(30) DEFAULT NULL COMMENT '紧急联系人',
   `firePhone` varchar(30) DEFAULT NULL COMMENT '紧急联系电话',
 `description` varchar(2000) DEFAULT NULL COMMENT '备注',
  `state` varchar(10) DEFAULT '是' COMMENT '是否有效,是/否/离职',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user`(id,name,workno,account,password,state)  VALUES ('admin', 'admin', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3','是');

-- ----------------------------
-- Table structure for `t_user_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `userId` varchar(40) NOT NULL COMMENT '用户编号',
  `orgId` varchar(40) NOT NULL COMMENT '机构编号',
  `manager` varchar(2) DEFAULT '否' COMMENT '是否主管,是/否',
  PRIMARY KEY (`id`),
  KEY `fk_t_user_org_userId_t_user_id` (`userId`),
  KEY `fk_t_user_org_orgId_t_org_id` (`orgId`),
  CONSTRAINT `fk_t_user_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`),
  CONSTRAINT `fk_t_user_org_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门中间表';

-- ----------------------------
-- Records of t_user_org
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `userId` varchar(40) NOT NULL COMMENT '用户编号',
  `roleId` varchar(40) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `fk_t_user_role_userId_t_user_id` (`userId`),
  KEY `fk_t_user_role_roleId_t_role_id` (`roleId`),
  CONSTRAINT `fk_t_user_role_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `fk_t_user_role_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------

INSERT INTO `t_user_role` VALUES ('admin_admin', 'admin', 'admin');

INSERT INTO `t_role_menu` VALUES ('system_manager_admin', 'admin', 'system_manager');
INSERT INTO `t_role_menu` VALUES ('business_manager_admin', 'admin', 'business_manager');
INSERT INTO `t_role_menu` VALUES ('dic_manager_admin', 'admin', 'dic_manager');


INSERT INTO `t_menu` VALUES ('limtDistribute_list', '权限分配', 'system_manager', null, '/limit/list', '1', '是');
INSERT INTO `t_menu` VALUES ('limtDistribute_update', '修改权限', 'limtDistribute_list', null, '/limit/update', '0', '是');
INSERT INTO `t_role_menu` VALUES ('limtDistribute_list_admin', 'admin', 'limtDistribute_list');
INSERT INTO `t_role_menu` VALUES ('limtDistribute_update_admin', 'admin', 'limtDistribute_update');
INSERT INTO `t_role_menu` VALUES ('limtDistribute_list_tree_admin', 'admin', 'limtDistribute_list_tree');


insert into t_menu values('t_auditlog_list','修改日志', 'system_manager', null,'/auditlog/list','1','是');
insert into t_menu values('t_auditlog_look','查看修改日志', 't_auditlog_list', null,'/auditlog/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_auditlog_list_admin', 'admin', 't_auditlog_list');
INSERT INTO `t_role_menu` VALUES ('t_auditlog_look_admin', 'admin', 't_auditlog_look');

insert into t_menu values('t_fwlog_list','访问日志', 'system_manager', null,'/fwlog/list','1','是');
insert into t_menu values('t_fwlog_look','查看访问日志', 't_fwlog_list', null,'/fwlog/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_fwlog_list_admin', 'admin', 't_fwlog_list');
INSERT INTO `t_role_menu` VALUES ('t_fwlog_look_admin', 'admin', 't_fwlog_look');

insert into t_menu values('t_menu_list','菜单管理', 'system_manager', null,'/menu/list','1','是');
insert into t_menu values('t_menu_update','修改菜单', 't_menu_list', null,'/menu/update','0','是');
insert into t_menu values('t_menu_look','查看菜单', 't_menu_list', null,'/menu/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_menu_list_admin', 'admin', 't_menu_list');
INSERT INTO `t_role_menu` VALUES ('t_menu_update_admin', 'admin', 't_menu_update');
INSERT INTO `t_role_menu` VALUES ('t_menu_look_admin', 'admin', 't_menu_look');
insert into t_menu values('t_menu_delMulti','批量删除菜单', 't_menu_list', null,'/menu/delMulti','0','是');
insert into t_menu values('t_menu_delete','删除菜单', 't_menu_list', null,'/menu/delete','0','是');
INSERT INTO `t_role_menu` VALUES ('t_menu_delMulti_admin', 'admin', 't_menu_delMulti');
INSERT INTO `t_role_menu` VALUES ('t_menu_delete_admin', 'admin', 't_menu_delete');



insert into t_menu values('t_org_list','部门管理', 'business_manager', null,'/org/list','1','是');
insert into t_menu values('t_org_update','修改部门', 't_org_list', null,'/org/update','0','是');
insert into t_menu values('t_org_look','查看部门', 't_org_list', null,'/org/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_org_list_admin', 'admin', 't_org_list');
INSERT INTO `t_role_menu` VALUES ('t_org_update_admin', 'admin', 't_org_update');
INSERT INTO `t_role_menu` VALUES ('t_org_look_admin', 'admin', 't_org_look');
insert into t_menu values('t_org_delMulti','批量删除部门', 't_org_list', null,'/org/delMulti','0','是');
insert into t_menu values('t_org_delete','删除部门', 't_org_list', null,'/org/delete','0','是');
INSERT INTO `t_role_menu` VALUES ('t_org_delMulti_admin', 'admin', 't_org_delMulti');
INSERT INTO `t_role_menu` VALUES ('t_org_delete_admin', 'admin', 't_org_delete');

insert into t_menu values('t_user_list','用户管理', 'business_manager', null,'/user/list','1','是');
insert into t_menu values('t_user_update','修改用户', 't_user_list', null,'/user/update','0','是');
insert into t_menu values('t_user_look','查看用户', 't_user_list', null,'/user/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_user_list_admin', 'admin', 't_user_list');
INSERT INTO `t_role_menu` VALUES ('t_user_update_admin', 'admin', 't_user_update');
INSERT INTO `t_role_menu` VALUES ('t_user_look_admin', 'admin', 't_user_look');
insert into t_menu values('t_user_delMulti','批量删除用户', 't_user_list', null,'/user/delMulti','0','是');
insert into t_menu values('t_user_delete','删除用户', 't_user_list', null,'/user/delete','0','是');
INSERT INTO `t_role_menu` VALUES ('t_user_delMulti_admin', 'admin', 't_user_delMulti');
INSERT INTO `t_role_menu` VALUES ('t_user_delete_admin', 'admin', 't_user_delete');


insert into t_menu values('t_role_list','角色管理', 'dic_manager', null,'/role/list','1','是');
insert into t_menu values('t_role_update','修改角色', 't_role_list', null,'/role/update','0','是');
insert into t_menu values('t_role_look','查看角色', 't_role_list', null,'/role/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_role_list_admin', 'admin', 't_role_list');
INSERT INTO `t_role_menu` VALUES ('t_role_update_admin', 'admin', 't_role_update');
INSERT INTO `t_role_menu` VALUES ('t_role_look_admin', 'admin', 't_role_look');
insert into t_menu values('t_role_delMulti','批量删除角色', 't_role_list', null,'/role/delMulti','0','是');
insert into t_menu values('t_role_delete','删除角色', 't_role_list', null,'/role/delete','0','是');
INSERT INTO `t_role_menu` VALUES ('t_role_delMulti_admin', 'admin', 't_role_delMulti');
INSERT INTO `t_role_menu` VALUES ('t_role_delete_admin', 'admin', 't_role_delete');





insert into t_menu values('t_dic_data_xueli_list','学历管理', 'dic_manager', null,'/dicdata/xueli/list','1','是');
insert into t_menu values('t_dic_data_xueli_update','修改学历', 't_dic_data_xueli_list', null,'/dicdata/xueli/update','0','是');
insert into t_menu values('t_dic_data_xueli_look','查看学历', 't_dic_data_xueli_list', null,'/dicdata/xueli/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_xueli_list_admin', 'admin', 't_dic_data_xueli_list');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_xueli_update_admin', 'admin', 't_dic_data_xueli_update');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_xueli_look_admin', 'admin', 't_dic_data_xueli_look');
insert into t_menu values('t_dic_data_xueli_delMulti','批量删除学历', 't_dic_data_xueli_list', null,'/dicdata/xueli/delMulti','0','是');
insert into t_menu values('t_dic_data_xueli_delete','删除学历', 't_dic_data_xueli_list', null,'/dicdata/xueli/delete','0','是');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_xueli_delMulti_admin', 'admin', 't_dic_data_xueli_delMulti');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_xueli_delete_admin', 'admin', 't_dic_data_xueli_delete');



insert into t_menu values('t_dic_data_minzu_list','民族管理', 'dic_manager', null,'/dicdata/minzu/list','1','是');
insert into t_menu values('t_dic_data_minzu_update','修改民族', 't_dic_data_minzu_list', null,'/dicdata/minzu/update','0','是');
insert into t_menu values('t_dic_data_minzu_look','查看民族', 't_dic_data_minzu_list', null,'/dicdata/minzu/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_minzu_list_admin', 'admin', 't_dic_data_minzu_list');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_minzu_update_admin', 'admin', 't_dic_data_minzu_update');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_minzu_look_admin', 'admin', 't_dic_data_minzu_look');
insert into t_menu values('t_dic_data_minzu_delMulti','批量删除民族', 't_dic_data_minzu_list', null,'/dicdata/minzu/delMulti','0','是');
insert into t_menu values('t_dic_data_minzu_delete','删除民族', 't_dic_data_minzu_list', null,'/dicdata/minzu/delete','0','是');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_minzu_delMulti_admin', 'admin', 't_dic_data_minzu_delMulti');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_minzu_delete_admin', 'admin', 't_dic_data_minzu_delete');


insert into t_menu values('t_dic_data_grade_list','级别管理', 'dic_manager', null,'/dicdata/grade/list','1','是');
insert into t_menu values('t_dic_data_grade_update','修改级别', 't_dic_data_grade_list', null,'/dicdata/grade/update','0','是');
insert into t_menu values('t_dic_data_grade_look','查看级别', 't_dic_data_grade_list', null,'/dicdata/grade/look','0','是');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_grade_list_admin', 'admin', 't_dic_data_grade_list');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_grade_update_admin', 'admin', 't_dic_data_grade_update');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_grade_look_admin', 'admin', 't_dic_data_grade_look');
insert into t_menu values('t_dic_data_grade_delMulti','批量删除级别', 't_dic_data_grade_list', null,'/dicdata/grade/delMulti','0','是');
insert into t_menu values('t_dic_data_grade_delete','删除级别', 't_dic_data_grade_list', null,'/dicdata/grade/delete','0','是');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_grade_delMulti_admin', 'admin', 't_dic_data_grade_delMulti');
INSERT INTO `t_role_menu` VALUES ('t_dic_data_grade_delete_admin', 'admin', 't_dic_data_grade_delete');







DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL auto_increment COMMENT '序号',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `content` mediumtext NOT NULL  COMMENT '内容',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='博客';


INSERT INTO `blog` VALUES ('1', 'JFinal Demo Title here', 'JFinal Demo Content here');
INSERT INTO `blog` VALUES ('2', 'test 1', 'test 1');
INSERT INTO `blog` VALUES ('3', 'test 2', 'test 2');
INSERT INTO `blog` VALUES ('4', 'test 3', 'test 3');
INSERT INTO `blog` VALUES ('5', 'test 4', 'test 4');

INSERT INTO t_menu values('blog_list','博客管理', 'business_manager', null,'/blog/list','1','是');
INSERT INTO t_menu values('blog_update','修改博客', 'blog_list', null,'/blog/update','0','是');
INSERT INTO t_menu values('blog_look','查看博客', 'blog_list', null,'/blog/look','0','是');
INSERT INTO t_menu values('blog_export','导出博客', 'blog_list', null,'/blog/list/export','0','是');
INSERT INTO t_menu values('blog_delMulti','批量删除博客', 'blog_list', null,'/blog/delMulti','0','是');
INSERT INTO t_menu values('blog_delete','删除博客', 'blog_list', null,'/blog/delete','0','是');
INSERT INTO t_menu values('blog_upload','导入博客', 'blog_list', null,'/blog/upload','0','是');
INSERT INTO `t_role_menu` VALUES ('blog_list_admin', 'admin', 'blog_list');
INSERT INTO `t_role_menu` VALUES ('blog_update_admin', 'admin', 'blog_update');
INSERT INTO `t_role_menu` VALUES ('blog_look_admin', 'admin', 'blog_look');
INSERT INTO `t_role_menu` VALUES ('blog_export_admin', 'admin', 'blog_export');
INSERT INTO `t_role_menu` VALUES ('blog_delMulti_admin', 'admin', 'blog_delMulti');
INSERT INTO `t_role_menu` VALUES ('blog_delete_admin', 'admin', 'blog_delete');
INSERT INTO `t_role_menu` VALUES ('blog_upload_admin', 'admin', 'blog_upload');




