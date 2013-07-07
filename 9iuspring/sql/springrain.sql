
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `auditlog`
-- ----------------------------
DROP TABLE IF EXISTS `auditlog_history_2013`;
CREATE TABLE `auditlog_history_2013` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `operationType` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `preValue` varchar(8000) DEFAULT NULL COMMENT '旧值',
  `curValue` varchar(8000) DEFAULT NULL COMMENT '新值',
  `operationTime` datetime DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) DEFAULT NULL COMMENT '记录ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ----------------------------
-- Records of auditlog
-- ----------------------------


-- ----------------------------
-- Table structure for `t_subsystem`
-- ----------------------------
DROP TABLE IF EXISTS `t_subsystem`;
CREATE TABLE `t_subsystem` (
  `id` varchar(40) NOT NULL COMMENT '子系统编号',
  `name` varchar(60) DEFAULT NULL COMMENT '子系统名称',
  `sortno` int(11) DEFAULT NULL COMMENT '子系统排序',
  `uri` text COMMENT '子系统访问路径',
  `remark` text COMMENT '备注',
  `state` varchar(2) DEFAULT NULL COMMENT '状态(0:不可用1:可用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='子系统';
alter table t_subsystem comment '子系统';
-- ----------------------------
-- Records of t_subsystem
-- ----------------------------


-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(40) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `pid` varchar(40) DEFAULT NULL,
  `description` text,
  `pageurl` text,
  `type` int(11) DEFAULT NULL COMMENT '0.普通资源1.菜单资源',
  `systemId` varchar(40) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table t_menu comment '菜单表';

alter table t_menu add constraint fk_t_menu_systemid_t_subsystem_id foreign key (systemId) references t_subsystem(id);
-- ----------------------------
-- Records of t_menu
-- ----------------------------

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
  `description` text COMMENT '描述',
  `state` int(11) DEFAULT NULL COMMENT '0.失效 1.有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';
alter table t_org comment '组织机构';
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
  `pid` varchar(40) DEFAULT NULL COMMENT '所属部门',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT NULL COMMENT '状态(0:禁用1:启用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';
alter table t_role comment '角色表';
-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `code` varchar(40) DEFAULT NULL COMMENT '代码',
  `account` varchar(40) DEFAULT NULL COMMENT '账号',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `salt` varchar(60) DEFAULT NULL COMMENT '密码校验码',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` int(11) DEFAULT NULL COMMENT '0.女1.男',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话号码',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `eamil` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `state` int(11) DEFAULT NULL COMMENT '0.无效1.有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
alter table t_user comment '用户表';
-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `roleId` varchar(40) NOT NULL COMMENT '角色编号',
  `menuId` varchar(40) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table t_role_menu comment '角色菜单表';
alter table t_role_menu add constraint fk_t_role_menu_roleId_t_role_id foreign key (roleId) references t_role(id);
alter table t_role_menu add constraint fk_t_role_menu_menuId_t_menu_id foreign key (menuId) references t_menu(id);


-- ----------------------------
-- Records of t_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `userId` varchar(40) NOT NULL COMMENT '用户编号',
  `orgId` varchar(40) NOT NULL COMMENT '机构编号',
  `manager` int(11) NOT NULL COMMENT '0.不是1.是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户机构表';
alter table t_user_org comment '用户机构表';
alter table t_user_org add constraint fk_t_user_org_userId_t_user_id foreign key (userId) references t_user(id);
alter table t_user_org add constraint fk_t_user_org_orgId_t_org_id foreign key (orgId) references t_org(id);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table t_user_role comment '用户角色表';
alter table t_user_role add constraint fk_t_user_role_userId_t_user_id foreign key (userId) references t_user(id);
alter table t_user_role add constraint fk_t_user_role_roleId_t_role_id foreign key (roleId) references t_role(id);
-- ----------------------------
-- Records of t_user_role
-- ----------------------------
