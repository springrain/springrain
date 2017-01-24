/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : springrain

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2017-01-24 17:41:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_auditlog_history_2017
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2017`;
CREATE TABLE `t_auditlog_history_2017` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `operationType` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text COMMENT '旧值',
  `curValue` text COMMENT '新值',
  `operationTime` datetime DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作记录';

-- ----------------------------
-- Records of t_auditlog_history_2017
-- ----------------------------

-- ----------------------------
-- Table structure for t_auditlog_history_2018
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2018`;
CREATE TABLE `t_auditlog_history_2018` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `operationType` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text COMMENT '旧值',
  `curValue` text COMMENT '新值',
  `operationTime` datetime DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作记录';

-- ----------------------------
-- Records of t_auditlog_history_2018
-- ----------------------------

-- ----------------------------
-- Table structure for t_dic_data
-- ----------------------------
DROP TABLE IF EXISTS `t_dic_data`;
CREATE TABLE `t_dic_data` (
  `id` varchar(50) NOT NULL,
  `name` varchar(60) NOT NULL COMMENT '名称',
  `code` varchar(60) DEFAULT NULL COMMENT '编码',
  `pid` varchar(50) DEFAULT NULL COMMENT '父ID',
  `sortno` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(2000) DEFAULT NULL COMMENT '描述',
  `active` int(11) DEFAULT '1' COMMENT '是否有效(0否,1是)',
  `typekey` varchar(20) DEFAULT NULL COMMENT '类型',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公共字典';

-- ----------------------------
-- Records of t_dic_data
-- ----------------------------
INSERT INTO `t_dic_data` VALUES ('16b80bfb-f0ee-47a0-ba94-cc256abaed17', '专科', '', null, null, '', '1', 'xueli', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('7ed23330-5538-4943-8678-0c5a2121cf57', '高中', '', null, null, '', '1', 'xueli', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('936db407-ae1-45a7-a657-b60580e2a77a', '汉族', '101', null, null, '', '1', 'minzu', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('936db407-ae2-45a7-a657-b60580e2a77a', '回族', '', null, null, '', '1', 'minzu', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('936db407-ae3-45a7-a657-b60580e2a77a', '一级', '', null, null, '', '1', 'grade', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('936db407-ae4-45a7-a657-b60580e2a77a', '二级', '', null, null, '', '1', 'grade', null, null, null, null, null);
INSERT INTO `t_dic_data` VALUES ('d7d1744b-e69f-48d0-9760-b2eae6af039b', '本科', '', null, null, '', '1', 'xueli', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_fwlog_history_2017
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2017`;
CREATE TABLE `t_fwlog_history_2017` (
  `id` varchar(100) NOT NULL COMMENT 'ID',
  `startDate` datetime DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100) DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50) DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300) DEFAULT NULL COMMENT '登陆账号',
  `userName` varchar(200) DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300) DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200) DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000) DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2) DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问日志';

-- ----------------------------
-- Records of t_fwlog_history_2017
-- ----------------------------
INSERT INTO `t_fwlog_history_2017` VALUES ('041dea2241d345498f46919299d9fb59', '2017-01-24 17:39:03', '2017-01-24 17:39:03.0507', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('0cbfcd295a5c4a668141ecaa9532ddf8', '2017-01-24 17:36:35', '2017-01-24 17:36:35.0004', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('12e10c511cba4962ad51d17e9274d4e7', '2017-01-24 17:17:33', '2017-01-24 17:17:33.0064', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/user/update/pre', '[修改用户]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('19eabd2d870e4cd4b64befe57546dbce', '2017-01-24 17:36:24', '2017-01-24 17:36:24.0378', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('1b1017b590ec4e45876e8430283f2b38', '2017-01-24 17:36:29', '2017-01-24 17:36:29.0773', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('1f6b80e4fc8143cfa2ae7b2224c1c781', '2017-01-24 17:39:04', '2017-01-24 17:39:04.0540', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/fwlog/list', '[访问日志]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('22d7ac52452e4ac9aec88a658bf0f1ee', '2017-01-24 17:17:20', '2017-01-24 17:17:20.0836', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('256da7bca81e48e0a38799256e6d36f8', '2017-01-24 17:39:03', '2017-01-24 17:39:03.0388', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('2e0af9e2fa854d67a346b0996f28073d', '2017-01-24 17:36:32', '2017-01-24 17:36:32.0709', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('38dc47cf8c094407a38388b0c0c0fe33', '2017-01-24 17:36:18', '2017-01-24 17:36:18.0076', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('40386796b92843bfbc4adb4609935d0e', '2017-01-24 17:36:16', '2017-01-24 17:36:16.0155', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list/json', '[角色管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('41e466550ee74b618e3a520d71e51940', '2017-01-24 17:37:37', '2017-01-24 17:37:37.0172', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('4374850b90dc44ef98f42c097ea9799e', '2017-01-24 17:36:27', '2017-01-24 17:36:27.0756', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('457f03da6c644f9bb5941b713734b464', '2017-01-24 17:36:31', '2017-01-24 17:36:31.0396', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('45b535edd8ab4da181fe56d0e6bf5bfb', '2017-01-24 17:39:05', '2017-01-24 17:39:05.0373', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('490e7d0bded34a129e3f25e594d2e08a', '2017-01-24 17:37:43', '2017-01-24 17:37:43.0652', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('55698f89a58c405aab31629ba4966456', '2017-01-24 17:17:33', '2017-01-24 17:17:33.0166', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list/json', '[角色管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('5da5c40ac2cd4ba4bfb5c54bcc80059c', '2017-01-24 17:39:00', '2017-01-24 17:39:00.0046', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('5e62055308c440e5be6fd00f38cefb6b', '2017-01-24 17:39:12', '2017-01-24 17:39:12.0563', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('602316be96794954bc6f66b4812cff23', '2017-01-24 17:17:28', '2017-01-24 17:17:28.0083', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/user/list', '[用户管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('687c7cfd26f741cd8118f50a9fdcdbf3', '2017-01-24 17:36:18', '2017-01-24 17:36:18.0181', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('6d5e3892308044009d243f7639cb1b04', '2017-01-24 17:37:19', '2017-01-24 17:37:19.0963', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('71138b63095a412486205dc698169f4d', '2017-01-24 17:17:19', '2017-01-24 17:17:19.0461', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('71e63961f4ff451dbf9572d332671716', '2017-01-24 17:37:36', '2017-01-24 17:37:36.0219', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/user/list', '[用户管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('73763da9aaf04bc6bba4335e9b065c64', '2017-01-24 17:17:19', '2017-01-24 17:17:19.0554', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('73af7b4dd5ed4ad88b70dc44e4edd0b9', '2017-01-24 17:36:34', '2017-01-24 17:36:34.0261', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/user/list', '[用户管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('80d8525fcdf7439eb7f97744628030cf', '2017-01-24 17:36:24', '2017-01-24 17:36:24.0244', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('81409cf00a884f7eac00606598c5f266', '2017-01-24 17:39:00', '2017-01-24 17:39:00.0987', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('8283773ff4d04c978266de3dc92f9af3', '2017-01-24 17:39:06', '2017-01-24 17:39:06.0260', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/fwlog/list', '[访问日志]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('87b220c6727c4494b8d47150106db8cc', '2017-01-24 17:17:23', '2017-01-24 17:17:23.0243', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('89f0ac16004a4623970a074578b5a2eb', '2017-01-24 17:37:42', '2017-01-24 17:37:42.0589', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('9039f73a7380443bbb03a2e5c9de7c21', '2017-01-24 17:37:41', '2017-01-24 17:37:41.0893', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('9a7082e57f0b4f69b6edbf04286fc494', '2017-01-24 17:17:23', '2017-01-24 17:17:23.0912', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('9ba05a312fc4436d8fd5b7fc2934b2aa', '2017-01-24 17:37:40', '2017-01-24 17:37:40.0220', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/user/list', '[用户管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('9c6d8233c982400994d0438860f7384c', '2017-01-24 17:36:23', '2017-01-24 17:36:23.0493', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/user/list', '[用户管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('b356024424e749ef9672c47521a649fa', '2017-01-24 17:39:13', '2017-01-24 17:39:13.0804', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('be50b7996e5a4cfb89a9b87d03467b96', '2017-01-24 17:36:26', '2017-01-24 17:36:26.0188', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('c5f8ef89196e405e80985a08c74578a8', '2017-01-24 17:17:23', '2017-01-24 17:17:23.0829', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('d5dfbce06e34408fb751e265260cec27', '2017-01-24 17:36:35', '2017-01-24 17:36:35.0097', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('dd6b2cc352034f7391b3b8988794a3a8', '2017-01-24 17:36:32', '2017-01-24 17:36:32.0791', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('e28c25b4a94d4d26bda25ed5a1c3a39b', '2017-01-24 17:36:29', '2017-01-24 17:36:29.0866', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('e42addb5796b4f12be1bbdf76ee89fb0', '2017-01-24 17:36:15', '2017-01-24 17:36:15.0981', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/user/update/pre', '[修改用户]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('e4fce4df679a47479a9265baab003f36', '2017-01-24 17:37:19', '2017-01-24 17:37:19.0733', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('f50075dcca3a42ac9988585c1e274ea7', '2017-01-24 17:36:25', '2017-01-24 17:36:25.0107', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('f529b9be645f4fd0b89768e32afa91e0', '2017-01-24 17:37:37', '2017-01-24 17:37:37.0092', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('fb5e942b03b64ce39c3f2e3e7f1d5e12', '2017-01-24 17:17:16', '2017-01-24 17:17:16.0524', null, 'admin', '超级管理员', 'e6321da8-3d8b-4e70-a402-27ad1590fc6f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是', null, null, null, null, null);
INSERT INTO `t_fwlog_history_2017` VALUES ('fb9e8e1eba90453dac9c524af79cfd6c', '2017-01-24 17:39:13', '2017-01-24 17:39:13.0898', null, 'admin', '超级管理员', 'c4fa651f-bc54-4b77-b74e-a9825c2a9af9', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_fwlog_history_2018
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2018`;
CREATE TABLE `t_fwlog_history_2018` (
  `id` varchar(100) NOT NULL COMMENT 'ID',
  `startDate` datetime DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100) DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50) DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300) DEFAULT NULL COMMENT '登陆账号',
  `userName` varchar(200) DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300) DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200) DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000) DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2) DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问日志';

-- ----------------------------
-- Records of t_fwlog_history_2018
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(50) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `pid` varchar(50) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `pageurl` varchar(3000) DEFAULT NULL,
  `menuType` int(11) DEFAULT NULL COMMENT '0.功能按钮,1.导航菜单',
  `active` int(11) DEFAULT '1' COMMENT '是否有效(0否,1是)',
  `sortno` int(11) DEFAULT NULL,
  `menuIcon` varchar(100) DEFAULT NULL,
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('169815aca9cf41d390e7feb6629d361d', '栏目管理', 'business_manager', '', '/system/cms/channel/list', '1', '1', '4', '&#xe60a;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('b9c4e8ecffe949c0b346e1fd0d6b9977', '内容管理', 'business_manager', '内容管理', '/system/cms/content/list', '1', '1', '5', '&#xe63c;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('business_manager', '业务管理', null, null, null, '1', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('dic_manager', '字典管理', null, null, null, '1', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('f41b9f3b4a0d45f5a3b5842ee40e0e96', '站点管理', 'business_manager', '', '/system/cms/site/list', '1', '1', '3', '&#xe641;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('system_manager', '系统管理', null, null, null, '1', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_auditlog_list', '修改日志', 'system_manager', '', '/system/auditlog/list', '1', '1', '1', '&#xe632;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_auditlog_look', '查看修改日志', 't_auditlog_list', null, '/system/auditlog/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_delete', '删除级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_deletemore', '批量删除级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_list', '级别管理', 'dic_manager', '', '/system/dicdata/grade/list', '1', '1', '1', '&#xe630;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_look', '查看级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_tree', '级别树形结构', 't_dic_data_grade_list', null, '/system/dicdata/grade/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_update', '修改级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_delete', '删除民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_deletemore', '批量删除民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_list', '民族管理', 'dic_manager', '', '/system/dicdata/minzu/list', '1', '1', '1', '&#xe650;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_look', '查看民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_tree', '民族树形结构', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_update', '修改民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_delete', '删除学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_deletemore', '批量删除学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_list', '学历管理', 'dic_manager', '', '/system/dicdata/xueli/list', '1', '1', '3', '&#xe621;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_look', '查看学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_tree', '学历树形结构', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_update', '修改学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_fwlog_list', '访问日志', 'system_manager', '', '/system/fwlog/list', '1', '1', '2', '&#xe62d;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_fwlog_look', '查看访问日志', 't_fwlog_list', null, '/system/fwlog/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_delete', '删除菜单', 't_menu_list', null, '/system/menu/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_deletemore', '批量删除菜单', 't_menu_list', null, '/system/menu/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_list', '菜单管理', 'system_manager', '', '/system/menu/list', '1', '1', '3', '&#xe631;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_look', '查看菜单', 't_menu_list', null, '/system/menu/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_tree', '菜单树形结构', 't_menu_list', null, '/system/menu/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_menu_update', '修改菜单', 't_menu_list', null, '/system/menu/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_delete', '删除部门', 't_org_list', null, '/system/org/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_deletemore', '批量删除部门', 't_org_list', null, '/system/org/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_list', '部门管理', 'business_manager', '', '/system/org/list', '1', '1', '1', '&#xe613;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_look', '查看部门', 't_org_list', null, '/system/org/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_tree', '部门树形结构', 't_org_list', null, '/system/org/tree', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_org_update', '修改部门', 't_org_list', null, '/system/org/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_delete', '删除角色', 't_role_list', null, '/system/role/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_deletemore', '批量删除角色', 't_role_list', null, '/system/role/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_list', '角色管理', 'system_manager', '', '/system/role/list', '1', '1', '4', '&#xe613;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_look', '查看角色', 't_role_list', null, '/system/role/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_role_update', '修改角色', 't_role_list', null, '/system/role/update', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_delete', '删除用户', 't_user_list', null, '/system/user/delete', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_deletemore', '批量删除用户', 't_user_list', null, '/system/user/delete/more', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_list', '用户管理', 'business_manager', '', '/system/user/list', '1', '1', '2', '&#xe612;', null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_list_export', '导出用户', 't_user_list', null, '/system/user/list/export', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_look', '查看用户', 't_user_list', null, '/system/user/look', '0', '1', null, null, null, null, null, null, null);
INSERT INTO `t_menu` VALUES ('t_user_update', '修改用户', 't_user_list', null, '/system/user/update', '0', '1', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `name` varchar(60) DEFAULT NULL COMMENT '名称',
  `comcode` varchar(1000) DEFAULT NULL COMMENT '代码',
  `pid` varchar(50) DEFAULT NULL COMMENT '上级部门ID',
  `orgType` int(11) DEFAULT NULL COMMENT '1.部门,2.虚拟权限组,10站长部门,11微信,12企业号,13pc,14wap,15投票',
  `leaf` int(11) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `sortno` int(11) DEFAULT NULL COMMENT '排序号',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `active` int(11) DEFAULT '1' COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES ('o_10001', '平台', ',o_10001,', null, null, null, '1', '', '1', null, null, null, null, null);
INSERT INTO `t_org` VALUES ('o_10002', '网站', ',o_10001,o_10002,', 'o_10001', null, null, '1', '', '1', null, null, null, null, null);
INSERT INTO `t_org` VALUES ('o_10003', '张三的站', ',o_10001,o_10002,o_10003,', 'o_10002', '10', null, '1', '张三的站', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(50) NOT NULL COMMENT '角色ID',
  `name` varchar(60) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '权限编码',
  `pid` varchar(50) DEFAULT NULL COMMENT '上级角色ID,暂时不实现',
  `roleType` int(11) NOT NULL DEFAULT '0' COMMENT '0系统角色,1部门主管,2业务岗位',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `active` int(11) DEFAULT '1' COMMENT '是否有效(0否,1是)',
  `orgId` varchar(50) DEFAULT NULL COMMENT '归属的部门Id',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('r_10001', '超级管理员', null, null, '0', null, '1', 'o_10001', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `roleId` varchar(50) NOT NULL COMMENT '角色编号',
  `menuId` varchar(50) NOT NULL COMMENT '菜单编号',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_role_menu_roleId_t_role_id` (`roleId`),
  KEY `fk_t_role_menu_menuId_t_menu_id` (`menuId`),
  CONSTRAINT `fk_t_role_menu_menuId_t_menu_id` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `fk_t_role_menu_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单中间表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('001a1c624ce2429bbca105c58792fd8d', 'r_10001', 't_user_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('0324dd16a332483a915483771d491842', 'r_10001', 't_dic_data_grade_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('087c9f7b0a6244a2920c76bb603216b6', 'r_10001', 't_role_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('0d2ea133cb27486baf771d4a5672f4d5', 'r_10001', 'dic_manager', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('19d04ce149d5471687ddf09dafd464ee', 'r_10001', 't_menu_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('1be39c19215d4eae941a001442c49da8', 'r_10001', 't_role_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('210b35dfa70c4d26907c2cb97bbeec90', 'r_10001', 'f41b9f3b4a0d45f5a3b5842ee40e0e96', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('2c12cc9b747541b6950fdb980554e7b6', 'r_10001', 't_dic_data_xueli_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('2eab95dd4dda4241a8303fbf7047b2ca', 'r_10001', 't_menu_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('2ef151ce8a8e45d09635f21337205d09', 'r_10001', 't_user_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('2fdf23167fd64d899183224aabc935c6', 'r_10001', 't_menu_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('31422f219b874cf5985f9b4e3ccc0a00', 'r_10001', 't_auditlog_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('3294654b0aa247018264f7bbb95e447b', 'r_10001', 't_org_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('37789dbfad7140988b849b6f3e246ae5', 'r_10001', 't_dic_data_minzu_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('3a0a3e3853cd466e8cf0bf1306cfc985', 'r_10001', 't_org_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('3bfcf1b6d71847148c3717b4f58da91a', 'r_10001', 't_user_list_export', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('4a38cefab0874d5b83c6534c4b47aeda', 'r_10001', 't_dic_data_minzu_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('51a0a2d1e004422c83163813dee8a8ee', 'r_10001', 't_dic_data_xueli_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('560956a9f29943d0be1106db643bb009', 'r_10001', 'b9c4e8ecffe949c0b346e1fd0d6b9977', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('560e55ca7bc14e659bb2ba56706c45d7', 'r_10001', 't_user_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('5776b6b6e3484213afbec97f79d3957c', 'r_10001', 't_fwlog_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('59616f2b6b364d1e89a7be64e053032c', 'r_10001', 't_dic_data_grade_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('59d4b9ed98074717b4e5f9a995dd857d', 'r_10001', 't_role_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('5e59a6a7ba11493e85f9ed86777157a7', 'r_10001', 't_menu_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('60ea86d6422b4060ba9c257c4cc1eec4', 'r_10001', '169815aca9cf41d390e7feb6629d361d', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('65cb03bb4a4d4f33a6c213d0f1a5a0a6', 'r_10001', 't_org_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('665571fdc13e4a1985707c246d5686b0', 'r_10001', 't_user_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('691fe8c0ad844d2fa9feb1958193c69e', 'r_10001', 't_role_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('6b4179fa942d4ad9922ab8864f6393a7', 'r_10001', 't_dic_data_minzu_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('700476536d8643759a1849f1ffc9b9fd', 'r_10001', 't_auditlog_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('7203ca2bc497423b8247eeabc6e630bf', 'r_10001', 't_dic_data_grade_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('7db22a6554a54385ad79614859cac705', 'r_10001', 't_dic_data_grade_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('8c8309acb74f4dcbbc614db07ac05def', 'r_10001', 't_fwlog_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('9536c440ea8142ab881c6335c903a7ad', 'r_10001', 'business_manager', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('a26b9aaaac874f26ba6170a6ec2e0237', 'r_10001', 't_dic_data_xueli_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('a755f59664694a01ba2d3f6060b4ce2a', 'r_10001', 't_dic_data_minzu_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('b44a7f18a7d149b0a002b98cf04dda77', 'r_10001', 't_user_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('b7949985b3e545ddbb93a057a3e5c6c4', 'r_10001', 't_dic_data_minzu_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('b8aecccfd89d4355a3bb57164480977b', 'r_10001', 't_menu_update', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('ba8c13b6db6645fab3e573d4fe35521c', 'r_10001', 't_org_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('ba93b2e17dab4adcb78104b1fa7c13de', 'r_10001', 't_menu_look', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('bae8949f420d45e5a0af630524cb2aa6', 'r_10001', 't_dic_data_minzu_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('bc72823cd678418d9318257ef6df35d3', 'r_10001', 't_dic_data_xueli_deletemore', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('bf04febd9a7949249b93491e4feaf0e5', 'r_10001', 't_role_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('d1ead29d5b9e4bd8ad2abd1d245436b7', 'r_10001', 't_dic_data_grade_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('df60df3f97ad4c4d8bf171d8b2259329', 'r_10001', 'system_manager', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('ece2acfdc14647a79b8ae46dfcd27b06', 'r_10001', 't_dic_data_xueli_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('f2082e78793140038122e06cfc655e63', 'r_10001', 't_dic_data_grade_list', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('f6605e3916d44a9baffa173e85a2c427', 'r_10001', 't_org_delete', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('f69713ca657e4e20986a372b597e9363', 'r_10001', 't_org_tree', null, null, null, null, null);
INSERT INTO `t_role_menu` VALUES ('fb3b50a9a5ae4f4399bae84355b0b66d', 'r_10001', 't_dic_data_xueli_update', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_tableindex
-- ----------------------------
DROP TABLE IF EXISTS `t_tableindex`;
CREATE TABLE `t_tableindex` (
  `id` varchar(50) NOT NULL COMMENT '表名',
  `maxIndex` int(11) NOT NULL DEFAULT '1' COMMENT '表记录最大的行,一直累加',
  `prefix` varchar(50) NOT NULL COMMENT '前缀 单个字母加 _',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='记录表最大的行记录';

-- ----------------------------
-- Records of t_tableindex
-- ----------------------------
INSERT INTO `t_tableindex` VALUES ('cms_channel', '10000', 'h_', null, null, null, null, null);
INSERT INTO `t_tableindex` VALUES ('cms_content', '100000', 'c_', null, null, null, null, null);
INSERT INTO `t_tableindex` VALUES ('cms_site', '10001', 's_', null, null, null, null, null);
INSERT INTO `t_tableindex` VALUES ('t_org', '10003', 'o_', null, null, null, null, null);
INSERT INTO `t_tableindex` VALUES ('t_user', '10001', 'u_', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` varchar(2) DEFAULT '男' COMMENT '性别',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `weixinId` varchar(200) DEFAULT NULL COMMENT '微信Id',
  `userType` int(11) DEFAULT NULL COMMENT '0后台管理员|/system/,1会员用户|/front/,2cms管理员|/cms/houtai/|cms_siteManager,3活动管理员|/huodong/houtai',
  `active` int(11) DEFAULT '1' COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('u_10001', '超级管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '男', null, null, null, '0', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_user_org
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `userId` varchar(50) NOT NULL COMMENT '用户编号',
  `orgId` varchar(50) NOT NULL COMMENT '机构编号',
  `managerType` int(11) NOT NULL DEFAULT '1' COMMENT '是否主管(0会员 10 员工 11主管 12代理主管 13虚拟主管)',
  `hasleaf` int(11) NOT NULL DEFAULT '1' COMMENT '是否包含子部门(0不包含1包含)',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_user_org_userId_t_user_id` (`userId`),
  KEY `fk_t_user_org_orgId_t_org_id` (`orgId`),
  CONSTRAINT `fk_t_user_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`),
  CONSTRAINT `fk_t_user_org_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户部门中间表';

-- ----------------------------
-- Records of t_user_org
-- ----------------------------
INSERT INTO `t_user_org` VALUES ('1', 'u_10001', 'o_10001', '11', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `userId` varchar(50) NOT NULL COMMENT '用户编号',
  `roleId` varchar(50) NOT NULL COMMENT '角色编号',
  `bak1` varchar(100) DEFAULT NULL,
  `bak2` varchar(100) DEFAULT NULL,
  `bak3` varchar(100) DEFAULT NULL,
  `bak4` varchar(100) DEFAULT NULL,
  `bak5` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_t_user_role_userId_t_user_id` (`userId`),
  KEY `fk_t_user_role_roleId_t_role_id` (`roleId`),
  CONSTRAINT `fk_t_user_role_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `fk_t_user_role_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色中间表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', 'u_10001', 'r_10001', null, null, null, null, null);
