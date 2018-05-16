/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 127.0.0.1:3306
 Source Schema         : springrain

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 16/05/2018 11:08:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_auditlog_history_2018
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2018`;
CREATE TABLE `t_auditlog_history_2018`  (
  `id` varchar(50)  NOT NULL COMMENT 'ID',
  `operationType` varchar(50)  NULL DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50)  NULL DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text  NULL COMMENT '旧值',
  `curValue` text  NULL COMMENT '新值',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500)  NULL DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50)  NULL DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '操作记录' ;

-- ----------------------------
-- Table structure for t_auditlog_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2019`;
CREATE TABLE `t_auditlog_history_2019`  (
  `id` varchar(50)  NOT NULL COMMENT 'ID',
  `operationType` varchar(50)  NULL DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50)  NULL DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text  NULL COMMENT '旧值',
  `curValue` text  NULL COMMENT '新值',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500)  NULL DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50)  NULL DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '操作记录' ;

-- ----------------------------
-- Table structure for t_dic_data
-- ----------------------------
DROP TABLE IF EXISTS `t_dic_data`;
CREATE TABLE `t_dic_data`  (
  `id` varchar(50)  NOT NULL,
  `name` varchar(60)  NOT NULL COMMENT '名称',
  `code` varchar(60)  NULL DEFAULT NULL COMMENT '编码',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '父ID',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(2000)  NULL DEFAULT NULL COMMENT '描述',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `typekey` varchar(20)  NULL DEFAULT NULL COMMENT '类型',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '公共字典' ;

-- ----------------------------
-- Records of t_dic_data
-- ----------------------------
INSERT INTO `t_dic_data` VALUES ('16b80bfb-f0ee-47a0-ba94-cc256abaed17', '专科', '', NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('7ed23330-5538-4943-8678-0c5a2121cf57', '高中', '', NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae1-45a7-a657-b60580e2a77a', '汉族', '101', NULL, NULL, '', 1, 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae2-45a7-a657-b60580e2a77a', '回族', '', NULL, NULL, '', 1, 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae3-45a7-a657-b60580e2a77a', '一级', '', NULL, NULL, '', 1, 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae4-45a7-a657-b60580e2a77a', '二级', '', NULL, NULL, '', 1, 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('d7d1744b-e69f-48d0-9760-b2eae6af039b', '本科', '', NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_fwlog_history_2018
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2018`;
CREATE TABLE `t_fwlog_history_2018`  (
  `id` varchar(100)  NOT NULL COMMENT 'ID',
  `startDate` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100)  NULL DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50)  NULL DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300)  NULL DEFAULT NULL COMMENT '登陆账号',
  `userName` varchar(200)  NULL DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300)  NULL DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200)  NULL DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000)  NULL DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100)  NULL DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2)  NULL DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '访问日志' ;

-- ----------------------------
-- Table structure for t_fwlog_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2019`;
CREATE TABLE `t_fwlog_history_2019`  (
  `id` varchar(100)  NOT NULL COMMENT 'ID',
  `startDate` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100)  NULL DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50)  NULL DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300)  NULL DEFAULT NULL COMMENT '登陆账号',
  `userName` varchar(200)  NULL DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300)  NULL DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200)  NULL DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000)  NULL DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100)  NULL DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2)  NULL DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '访问日志' ;

-- ----------------------------
-- Table structure for t_h5drag
-- ----------------------------
DROP TABLE IF EXISTS `t_h5drag`;
CREATE TABLE `t_h5drag`  (
  `id` varchar(100)  NOT NULL,
  `ajaxURL` varchar(1000)  NULL DEFAULT NULL COMMENT 'ajax请求的URL',
  `pubTitle` varchar(1000)  NULL DEFAULT NULL COMMENT '大标题',
  `module` varchar(1000)  NULL DEFAULT NULL COMMENT '组件',
  `bgColor` varchar(2000)  NULL DEFAULT NULL COMMENT '背景色',
  `classArrStr` varchar(2000)  NULL DEFAULT NULL COMMENT '类名',
  `listArrStr` varchar(2000)  NULL DEFAULT NULL COMMENT '数据 array',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = 'H5拖拽后台表' ;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` varchar(50)  NOT NULL,
  `name` varchar(500)  NULL DEFAULT NULL,
  `pid` varchar(50)  NULL DEFAULT NULL,
  `description` varchar(2000)  NULL DEFAULT NULL COMMENT '描述',
  `pageurl` varchar(3000)  NULL DEFAULT NULL,
  `menuType` int(11) NULL DEFAULT NULL COMMENT '0.功能按钮,1.导航菜单',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `sortno` int(11) NULL DEFAULT NULL,
  `menuIcon` varchar(100)  NULL DEFAULT NULL,
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '菜单' ;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('169815aca9cf41d390e7feb6629d361d', '栏目管理', 'business_manager', '', '/system/cms/channel/list', 1, 1, 4, '/img/iconImg/icon15.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3501ed1e23da40219b4f0fa5b7b2749a', '菜单列表', 't_menu_list', '', '/system/menu/list', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('36ab9175f7b7423eadda974ba046be05', '修改密码', 't_user_list', '', '/system/user/modifiypwd/pre', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('4adc1e3e3e244c0991d9dab66c63badf', '目录创建', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/uploadDic', 0, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('7cd0678633d5407dba2bd6a1553cadce', '文件下载', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/downfile', 0, 1, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('91779a0d304f4b91932b63dec87a8536', '角色管理-系统', 'system_manager', '', '/system/role/list/all', 1, 1, NULL, '/img/iconImg/icon23.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('b94392f7b8714f64819c5c0222eb134a', '角色修改-系统', 't_role_list', '', '/system/role/update/admin', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('b9c4e8ecffe949c0b346e1fd0d6b9977', '内容管理', 'business_manager', '', '/system/cms/content/list', 1, 1, 5, '/img/iconImg/icon9.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('business_manager', '业务管理', NULL, '', NULL, 1, 1, 1, '/img/iconImg/icon10.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('ca152df1a7b44d4f81162f34b808934a', '验证老密码', '36ab9175f7b7423eadda974ba046be05', '', '/system/user/modifiypwd/ispwd', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('ca28235dbd234b7585e133e70cc7999a', '文件上传', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/uploadFile', 0, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('d6abe682007849869c3a168215ae40d4', 'WEB-INF文件管理', 'system_manager', '', '/system/file/web/list', 1, 1, 7, '/img/iconImg/icon20.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('dic_manager', '字典管理', 'system_manager', '', NULL, 1, 1, NULL, '/img/iconImg/icon30.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('e51808e351c24a7e9fb4d47392930a2d', '保存新密码', '36ab9175f7b7423eadda974ba046be05', '', '/system/user/modifiypwd/save', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f41b9f3b4a0d45f5a3b5842ee40e0e96', '站点管理', 'business_manager', '', '/system/cms/site/list', 1, 1, 3, '/img/iconImg/icon4.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f5203235547342f094a2c126ad4603bb', '文件管理', 'system_manager', '', '/system/file/list', 1, 1, 6, '/img/iconImg/icon20.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('system_manager', '系统管理', NULL, '', NULL, 1, 1, 2, '/img/iconImg/icon13.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_auditlog_list', '修改日志', 'system_manager', '', '/system/auditlog/list', 1, 1, 1, '/img/iconImg/icon42.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_auditlog_look', '查看修改日志', 't_auditlog_list', NULL, '/system/auditlog/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_delete', '删除级别', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_deletemore', '批量删除级别', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_list', '级别管理', 'dic_manager', '', '/system/dicdata/grade/list', 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_look', '查看级别', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_tree', '级别树形结构', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_update', '修改级别', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_delete', '删除民族', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_deletemore', '批量删除民族', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_list', '民族管理', 'dic_manager', '', '/system/dicdata/minzu/list', 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_look', '查看民族', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_tree', '民族树形结构', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_update', '修改民族', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_delete', '删除学历', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_deletemore', '批量删除学历', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_list', '学历管理', 'dic_manager', '', '/system/dicdata/xueli/list', 1, 1, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_look', '查看学历', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_tree', '学历树形结构', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_update', '修改学历', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_fwlog_list', '访问日志', 'system_manager', '', '/system/fwlog/list', 1, 1, 2, '/img/iconImg/icon42.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_fwlog_look', '查看访问日志', 't_fwlog_list', NULL, '/system/fwlog/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_delete', '删除菜单', 't_menu_list', NULL, '/system/menu/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_deletemore', '批量删除菜单', 't_menu_list', NULL, '/system/menu/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_list', '菜单管理', 'system_manager', '', '/system/menu/list/all', 1, 1, 3, '/img/iconImg/icon11.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_look', '查看菜单', 't_menu_list', NULL, '/system/menu/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_tree', '菜单树形结构', 't_menu_list', NULL, '/system/menu/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_update', '修改菜单', 't_menu_list', NULL, '/system/menu/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_delete', '删除部门', 't_org_list', NULL, '/system/org/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_deletemore', '批量删除部门', 't_org_list', NULL, '/system/org/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_list', '部门管理', 'business_manager', '', '/system/org/list', 1, 1, 1, '/img/iconImg/icon29.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_look', '查看部门', 't_org_list', NULL, '/system/org/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_tree', '部门树形结构', 't_org_list', NULL, '/system/org/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_update', '修改部门', 't_org_list', NULL, '/system/org/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_delete', '删除角色', 't_role_list', NULL, '/system/role/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_deletemore', '批量删除角色', 't_role_list', NULL, '/system/role/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_list', '角色管理', 'system_manager', '', '/system/role/list', 1, 1, 4, '/img/iconImg/icon23.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_look', '查看角色', 't_role_list', NULL, '/system/role/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_update', '修改角色', 't_role_list', NULL, '/system/role/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_delete', '删除用户', 't_user_list', NULL, '/system/user/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_deletemore', '批量删除用户', 't_user_list', NULL, '/system/user/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_list', '用户管理', 'business_manager', '', '/system/user/list', 1, 1, 2, '/img/iconImg/icon24.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_list_export', '导出用户', 't_user_list', NULL, '/system/user/list/export', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_look', '查看用户', 't_user_list', NULL, '/system/user/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_update', '修改用户', 't_user_list', NULL, '/system/user/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `name` varchar(60)  NULL DEFAULT NULL COMMENT '名称',
  `comcode` varchar(1000)  NULL DEFAULT NULL COMMENT '代码',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '上级部门ID',
  `orgType` int(11) NULL DEFAULT NULL COMMENT '1.部门,2.虚拟权限组,10站长部门,11微信,12企业号,13pc,14wap,15投票',
  `leaf` int(11) NULL DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序号',
  `description` varchar(2000)  NULL DEFAULT NULL COMMENT '描述',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '部门' ;

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES ('o_10001', '平台', ',o_10001,', NULL, NULL, NULL, 1, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10002', '网站', ',o_10001,o_10002,', 'o_10001', NULL, NULL, 1, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10003', '张三的站', ',o_10001,o_10002,o_10003,', 'o_10002', 10, NULL, 1, '张三的站', 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_permissions_log
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_log`;
CREATE TABLE `t_permissions_log`  (
  `id` varchar(50)  NOT NULL COMMENT '主键',
  `siteId` varchar(50)  NULL DEFAULT NULL COMMENT '站点ID',
  `actionType` int(2) NULL DEFAULT NULL COMMENT '操作类型 创建、编辑、删除、启用、禁用',
  `operatorUserId` varchar(50)  NULL DEFAULT NULL COMMENT '操作人ID',
  `operatorUserName` varchar(200)  NULL DEFAULT NULL COMMENT '操作人当时名称',
  `operatorUserRoles` text  NULL COMMENT '操作人当时所属角色名称，逗号分割',
  `operatorObjectType` int(2) NULL DEFAULT NULL COMMENT '操作对象类型',
  `operatorObjectId` varchar(50)  NULL DEFAULT NULL COMMENT '操作对象ID',
  `operatorObjectName` varchar(200)  NULL DEFAULT NULL COMMENT '操作对象当时的名称',
  `actionContent` longtext  NULL COMMENT '操作内容详情',
  `createUserId` varchar(50)  NULL DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `bak1` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak2` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak3` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak4` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak5` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '权限变更日志' ;

-- ----------------------------
-- Records of t_permissions_log
-- ----------------------------
INSERT INTO `t_permissions_log` VALUES ('19ca6e3bf501482b8aeab8af57d9f8f7', NULL, 2, 'u_10001', '超级管理员', '超级管理员', 2, 'r_10001', '超级管理员', '<contenttsplit><contenttsplit>业务管理 - 栏目管理<br>业务管理 - 测试用例 - 测试用例管理 - 删除Htmlcase<br>业务管理 - 测试用例 - 测试用例管理 - 批量删除Htmlcase<br>业务管理 - 测试用例 - 测试用例管理 - 导出Htmlcase<br>业务管理 - 测试用例 - 测试用例管理 - 查看Htmlcase<br>业务管理 - 测试用例 - 测试用例管理 - 修改Htmlcase<br>业务管理 - 测试用例 - 测试用例管理 - 导入Htmlcase<br>业务管理 - 测试用例 - 页面字段管理 - 删除Htmlfield<br>业务管理 - 测试用例 - 页面字段管理 - 批量删除Htmlfield<br>业务管理 - 测试用例 - 页面字段管理 - 导出Htmlfield<br>业务管理 - 测试用例 - 页面字段管理 - 查看Htmlfield<br>业务管理 - 测试用例 - 页面字段管理 - 修改Htmlfield<br>业务管理 - 测试用例 - 页面字段管理 - 导入Htmlfield<br>业务管理 - 测试用例 - 页面功能管理 - 删除Htmlfunction<br>业务管理 - 测试用例 - 页面功能管理 - 批量删除Htmlfunction<br>业务管理 - 测试用例 - 页面功能管理 - 导出Htmlfunction<br>业务管理 - 测试用例 - 页面功能管理 - 查看Htmlfunction<br>业务管理 - 测试用例 - 页面功能管理 - 修改Htmlfunction<br>业务管理 - 测试用例 - 页面功能管理 - 导入Htmlfunction<br>业务管理 - 测试用例 - 验证规则管理 - 删除Validaterule<br>业务管理 - 测试用例 - 验证规则管理 - 批量删除Validaterule<br>业务管理 - 测试用例 - 验证规则管理 - 导出Validaterule<br>业务管理 - 测试用例 - 验证规则管理 - 查看Validaterule<br>业务管理 - 测试用例 - 验证规则管理 - 修改Validaterule<br>业务管理 - 测试用例 - 验证规则管理 - 导入Validaterule<br>业务管理 - 内容管理<br>业务管理 - 站点管理', 'u_10001', '2018-05-15 17:26:25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_permissions_log` VALUES ('809a7b8698494f2aab7988b171afcc82', NULL, 2, 'u_10001', '超级管理员', '超级管理员', 2, 'r_10001', '超级管理员', '<contenttsplit><contenttsplit>', 'u_10001', '2018-05-15 17:26:45', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(50)  NOT NULL COMMENT '角色ID',
  `name` varchar(60)  NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255)  NULL DEFAULT NULL COMMENT '权限编码',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '上级角色ID,暂时不实现',
  `roleType` int(11) NOT NULL DEFAULT 0 COMMENT '0系统角色,1部门主管,2业务岗位',
  `remark` varchar(255)  NULL DEFAULT NULL COMMENT '备注',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `orgId` varchar(50)  NULL DEFAULT NULL COMMENT '归属的部门Id',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色' ;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('r_10001', '超级管理员', NULL, NULL, 0, '', 1, 'o_10001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `menuId` varchar(50)  NOT NULL COMMENT '菜单编号',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `fk_t_role_menu_roleId_t_role_id`(`roleId`) ,
  INDEX `fk_t_role_menu_menuId_t_menu_id`(`menuId`) ,
  CONSTRAINT `fk_t_role_menu_menuId_t_menu_id` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_role_menu_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色菜单中间表' ;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('052ca6e8c15a46608f874c4a2623b76f', 'r_10001', 't_menu_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('05b0f81315424c57ade2d25571ae6d56', 'r_10001', 't_dic_data_xueli_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('05eaee6161f449e685aa5b7843d4387c', 'r_10001', 'ca28235dbd234b7585e133e70cc7999a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0882149a2f6d4536b24da9535bfb2085', 'r_10001', 't_dic_data_grade_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('08e854dcdb8a47028dc2f3ffea656fbc', 'r_10001', 'e51808e351c24a7e9fb4d47392930a2d', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0a2f718b7aa04ddf9b9165159948a494', 'r_10001', 'f5203235547342f094a2c126ad4603bb', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0e2945d86120434db951c05be8f855c2', 'r_10001', 't_user_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('161f0e43c9c44a138f59bfbd6b407939', 'r_10001', '36ab9175f7b7423eadda974ba046be05', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('165849143aad44d5b9cdc3d0869f4af3', 'r_10001', 't_menu_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('1cfd544e62b841dc8be2467ee51dfc38', 'r_10001', 't_fwlog_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('25505b186be34b05957ea7b3bfbe515e', 'r_10001', 'd6abe682007849869c3a168215ae40d4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('26f46b98af8042bbb33978d5375b4ef7', 'r_10001', 't_dic_data_grade_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('27d8086a3ff949148344271197177d14', 'r_10001', 't_dic_data_minzu_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('28a729c775804ce9ab77524969e806c1', 'r_10001', 'ca152df1a7b44d4f81162f34b808934a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('3828cf4c20c540b0a571a3d6b6f9104a', 'r_10001', 'dic_manager', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('47efee46f18046639d3b6338c9e3f7f0', 'r_10001', 't_menu_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('48af021b187d489a9016aef0f27a9feb', 'r_10001', 't_org_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('4d81f8656923475981cdac2f16cb2a0f', 'r_10001', 't_role_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('4d8748f4b9824bc9a0adb4bbeaaef306', 'r_10001', '3501ed1e23da40219b4f0fa5b7b2749a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('4ec2d65ea17940fabd691674d70bbea9', 'r_10001', 't_org_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('4f210223295a461d9e95c73591ba1b4a', 'r_10001', 't_fwlog_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('67638c8600884ead9e78d65811dbec09', 'r_10001', 't_dic_data_grade_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('6ee5b1e7260340319eccf758f2ae4509', 'r_10001', 't_user_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('6efc6ff902b643068ceea91c66078289', 'r_10001', '4adc1e3e3e244c0991d9dab66c63badf', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('71e33057208a4eb09acb3144a441040e', 'r_10001', 't_role_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('722cd22efd714ed79a730fb2ed370dc3', 'r_10001', 't_dic_data_grade_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('75542ccb051849f39ffda0e61c03171f', 'r_10001', 'b94392f7b8714f64819c5c0222eb134a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('762a197d6b784e3cb72deda0b406d3aa', 'r_10001', 't_menu_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('779ee00079fb4054a45597fd90f6dfe3', 'r_10001', 't_menu_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('7c0deb6c8b7b44cabe05c359274c063f', 'r_10001', 't_auditlog_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('7d5af816da7647efab4a8cd8c0cc1cb9', 'r_10001', 't_org_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('7d5ed877bf924304837da9145ae42f54', 'r_10001', 't_dic_data_minzu_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('7f4d9ae8742743179464433400eb4d1a', 'r_10001', 't_dic_data_grade_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('7fce89e49e3d49b7bed87f5da5f7f175', 'r_10001', 't_dic_data_xueli_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('82ee4ccbe2734f4384e740c20bacfeed', 'r_10001', 't_menu_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('834e3b72177847d08d02260fdcc9e01a', 'r_10001', 't_org_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('9382510a6d9d47f9b036bf5d3bfd2e9a', 'r_10001', '91779a0d304f4b91932b63dec87a8536', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('9a666f79f77b43c68d93534e93b79e00', 'r_10001', 't_role_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('9c245be1416447e6ab94141277ee545c', 'r_10001', 't_dic_data_minzu_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('9d3f48a13b0b402d9e0beaadfc0229f3', 'r_10001', 't_dic_data_minzu_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('9dea0a4adaf245c4b07e56907d460995', 'r_10001', 't_dic_data_minzu_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a15488cda1f34b9192b196311489d0e9', 'r_10001', 't_auditlog_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a52fbb32acbb4aef88fbc129e36f880d', 'r_10001', 't_dic_data_xueli_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a5b5e51cec33487994ad387a6dff859e', 'r_10001', 't_dic_data_grade_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a655d6b4fd1e497b8f3a4a9d61c88f42', 'r_10001', 't_org_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('ab0409c12ed54b26aecc05c909ebfdcd', 'r_10001', 't_user_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('afd31ebb59d1455e901f48b6fe005def', 'r_10001', 't_role_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b3f7bd1f72a64454b3c220a72e573f25', 'r_10001', '7cd0678633d5407dba2bd6a1553cadce', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('bffee090d0764aa4a1983f593b21ed26', 'r_10001', 't_dic_data_xueli_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('c25133edb7e44154be39b47210466a16', 'r_10001', 't_user_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('d7d33a7649d3471993c95454d9669042', 'r_10001', 'system_manager', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('db6396905d6b4e1ab4cb9aa89d9a735a', 'r_10001', 't_user_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('de8510c67461452ba19850ea87b0b95c', 'r_10001', 't_dic_data_xueli_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('e175e7e5179b4921bff1ca31df3d14e9', 'r_10001', 't_dic_data_xueli_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('e555f82d3fbe4b72b58b0d290a16842f', 'r_10001', 't_dic_data_minzu_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('eafb142b105640568602f6895bdf282c', 'r_10001', 't_user_list_export', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('fb2dbcde054241c78b7f3a83dbf59b26', 'r_10001', 't_org_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('fc7b68b84deb420690d3f34736d1976c', 'r_10001', 'business_manager', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('fe13d3aba12f4f0da0c27b2cd2ddb9b8', 'r_10001', 't_role_update', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role_org
-- ----------------------------
DROP TABLE IF EXISTS `t_role_org`;
CREATE TABLE `t_role_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `orgId` varchar(50)  NOT NULL COMMENT '部门编号',
  `orgType` int(11) NULL DEFAULT NULL COMMENT '0组织机构 ,1.部门,2.虚拟权限组',
  `hasLeaf` int(11) NOT NULL DEFAULT 0 COMMENT '是否包含子部门,0不包含,1包含',
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '是否可用,0不可用,1可用',
  `manager` int(11) NULL DEFAULT 0 COMMENT '0:非主管，1:主管',
  PRIMARY KEY (`id`) ,
  INDEX `fk_t_role_org_roleId_t_role_id`(`roleId`) ,
  INDEX `fk_t_role_org_orgId_t_org_id`(`orgId`) ,
  CONSTRAINT `fk_t_role_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_role_org_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色部门中间表' ;

-- ----------------------------
-- Table structure for t_tableindex
-- ----------------------------
DROP TABLE IF EXISTS `t_tableindex`;
CREATE TABLE `t_tableindex`  (
  `id` varchar(50)  NOT NULL COMMENT '表名',
  `maxIndex` int(11) NOT NULL DEFAULT 1 COMMENT '表记录最大的行,一直累加',
  `prefix` varchar(50)  NOT NULL COMMENT '前缀 单个字母加 _',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '记录表最大的行记录' ;

-- ----------------------------
-- Records of t_tableindex
-- ----------------------------
INSERT INTO `t_tableindex` VALUES ('cms_channel', 10000, 'h_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('cms_content', 100000, 'c_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('cms_site', 10001, 's_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('t_org', 10003, 'o_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('t_user', 10001, 'u_', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `name` varchar(30)  NULL DEFAULT NULL COMMENT '姓名',
  `account` varchar(50)  NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(50)  NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(2)  NULL DEFAULT '男' COMMENT '性别',
  `mobile` varchar(16)  NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60)  NULL DEFAULT NULL COMMENT '邮箱',
  `weixinId` varchar(200)  NULL DEFAULT NULL COMMENT '微信Id',
  `userType` int(11) NULL DEFAULT NULL COMMENT '0后台管理员|/system/,1会员用户|/front/,2cms管理员|/cms/houtai/|cms_siteManager,3活动管理员|/huodong/houtai',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户' ;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('u_10001', '超级管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '男', NULL, NULL, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_org
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `userId` varchar(50)  NOT NULL COMMENT '用户编号',
  `orgId` varchar(50)  NOT NULL COMMENT '机构编号',
  `managerType` int(11) NOT NULL DEFAULT 1 COMMENT '是否主管(0会员 10 员工 11主管 12代理主管 13虚拟主管)',
  `hasleaf` int(11) NOT NULL DEFAULT 1 COMMENT '是否包含子部门(0不包含1包含)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `fk_t_user_org_userId_t_user_id`(`userId`) ,
  INDEX `fk_t_user_org_orgId_t_org_id`(`orgId`) ,
  CONSTRAINT `fk_t_user_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_user_org_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户部门中间表' ;

-- ----------------------------
-- Records of t_user_org
-- ----------------------------
INSERT INTO `t_user_org` VALUES ('1', 'u_10001', 'o_10001', 11, 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_platform_infos
-- ----------------------------
DROP TABLE IF EXISTS `t_user_platform_infos`;
CREATE TABLE `t_user_platform_infos`  (
  `id` varchar(50)  NOT NULL COMMENT '主键id',
  `openId` varchar(100)  NULL DEFAULT NULL COMMENT '公众号openId,企业号userId,小程序openId,APP推送deviceToken',
  `deviceType` int(11) NULL DEFAULT NULL COMMENT '设备/应用类型：1公众号2小程序3企业号4APP IOS消息推送5APP安卓消息推送6web',
  `siteId` varchar(50)  NULL DEFAULT NULL COMMENT '所属站点ID',
  `userId` varchar(50)  NULL DEFAULT NULL COMMENT 't_user表中ID',
  `bak1` varchar(255)  NULL DEFAULT NULL,
  `bak2` varchar(255)  NULL DEFAULT NULL,
  `bak3` varchar(255)  NULL DEFAULT NULL,
  `bak4` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) 
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户平台信息表' ;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `userId` varchar(50)  NOT NULL COMMENT '用户编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `fk_t_user_role_userId_t_user_id`(`userId`) ,
  INDEX `fk_t_user_role_roleId_t_role_id`(`roleId`) ,
  CONSTRAINT `fk_t_user_role_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_user_role_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户角色中间表' ;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', 'u_10001', 'r_10001', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
