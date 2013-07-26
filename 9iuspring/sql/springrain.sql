

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `auditlog_history_2013`
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
-- Records of auditlog_history_2013
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
 `state` varchar(2) DEFAULT '是' COMMENT '状态,是否可用(0:否1:是)',
  PRIMARY KEY (`id`),
  KEY `fk_t_menu_systemid_t_subsystem_id` (`systemId`),
  CONSTRAINT `fk_t_menu_systemid_t_subsystem_id` FOREIGN KEY (`systemId`) REFERENCES `t_subsystem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('auditlog_list', '日志查看', null, null, '/auditlog/list', '0', null, '1');
INSERT INTO `t_menu` VALUES ('menu_list', '菜单管理', NULL, NULL, '/menu/list', '0', NULL, '1');
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
  `state` varchar(2) DEFAULT '是' COMMENT '状态,是否可用(0:否1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';

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
  `state` varchar(2) DEFAULT '是' COMMENT '状态,是否可用(0:否1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('admin', '管理员', 'admin', null, null, null);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('auditlog_list_admin', 'admin', 'auditlog_list');
INSERT INTO `t_role_menu` VALUES ('menu_list_admin', 'admin', 'menu_list');

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
   `state` varchar(2) DEFAULT '是' COMMENT '状态,是否可用(0:否1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='子系统';

-- ----------------------------
-- Records of t_subsystem
-- ----------------------------


-- ----------------------------
-- Table structure for `t_grade`
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `id` varchar(40) NOT NULL COMMENT '级别Id',
  `name` varchar(60) DEFAULT NULL COMMENT '级别名称',
  `remark` text COMMENT '备注',
  `state` varchar(2) DEFAULT '是' COMMENT '状态,是否可用(0:否1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='级别';

-- ----------------------------
-- Records of t_grade
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
  `sex`  varchar(2)  DEFAULT '男' COMMENT '性别',
  `phone` varchar(16) DEFAULT NULL COMMENT '电话号码',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `eamil` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
    `gradeId` varchar(40) DEFAULT null COMMENT '级别',
  `state` varchar(2) DEFAULT '是' COMMENT '状态,是否可用(0:否1:是)',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_t_user_gradeId_t_grade_id` FOREIGN KEY (`gradeId`) REFERENCES `t_grade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('admin', 'admin', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', null, null, null, null, null, null, null, null,'是');

-- ----------------------------
-- Table structure for `t_user_org`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `userId` varchar(40) NOT NULL COMMENT '用户编号',
  `orgId` varchar(40) NOT NULL COMMENT '机构编号',
  `manager` int(11) NOT NULL COMMENT '0.不是1.是',
  PRIMARY KEY (`id`),
  KEY `fk_t_user_org_userId_t_user_id` (`userId`),
  KEY `fk_t_user_org_orgId_t_org_id` (`orgId`),
  CONSTRAINT `fk_t_user_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`),
  CONSTRAINT `fk_t_user_org_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户机构表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('admin_admin', 'admin', 'admin');
