/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : springrain

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-02 10:05:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tc_htmlcase
-- ----------------------------
DROP TABLE IF EXISTS `tc_htmlcase`;
CREATE TABLE `tc_htmlcase` (
  `id` varchar(50) NOT NULL,
  `fuctionId` varchar(50) NOT NULL COMMENT '功能ID',
  `ruleId` varchar(50) DEFAULT NULL,
  `caseCode` varchar(50) NOT NULL COMMENT '测试用例编码,例如 001',
  `htmlFieldValue` varchar(2000) NOT NULL,
  `realResult` varchar(2000) DEFAULT NULL,
  `successResult` varchar(2000) NOT NULL,
  `pass` int(11) NOT NULL DEFAULT '0' COMMENT '是否通过',
  `sortno` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tc_htmlcase
-- ----------------------------
INSERT INTO `tc_htmlcase` VALUES ('08094f2b7d984b10a2633a9f2ad8cc6f', 'testlogin', 'passwordv3', 'hcpassword1', '', null, '密码不能为空!', '0', '4');
INSERT INTO `tc_htmlcase` VALUES ('38c8170e8068415f88a6a9f889d7bad0', 'testlogin', 'passwordv6', 'hcpassword2', '1', null, '密码错误!', '0', '5');
INSERT INTO `tc_htmlcase` VALUES ('7d82e851515e46548a7a76c49a2a4dee', 'testlogin', 'passwordv7', 'hcpassword3', 'admin', null, '后台管理系统', '0', '6');
INSERT INTO `tc_htmlcase` VALUES ('a78f05f3474947bcbde8cc3c660763cf', 'testlogin', 'accountv3', 'hcaccount1', '', null, '账号不能为空!', '0', '1');
INSERT INTO `tc_htmlcase` VALUES ('ec94003792a24e14b970c9f4486a73b3', 'testlogin', 'accountv6', 'hcaccount2', '1', null, '账号错误!', '0', '2');
INSERT INTO `tc_htmlcase` VALUES ('ef590ba46a9b46319849fd1be6fbf4b6', 'testlogin', 'accountv7', 'hcaccount3', 'admin', null, '密码不能为空!', '0', '3');

-- ----------------------------
-- Table structure for tc_htmlfield
-- ----------------------------
DROP TABLE IF EXISTS `tc_htmlfield`;
CREATE TABLE `tc_htmlfield` (
  `id` varchar(50) NOT NULL,
  `functionId` varchar(50) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `findType` int(11) NOT NULL DEFAULT '0' COMMENT '0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert',
  `elementKey` varchar(2000) DEFAULT NULL COMMENT '元素的Key,例如 userName 或者xpath的表达式',
  `elementValue` varchar(2000) DEFAULT NULL COMMENT '期望结果,例如判断网页的标题',
  `xpath` varchar(2000) NOT NULL COMMENT '实际的xpath表达式',
  `required` int(11) NOT NULL DEFAULT '1' COMMENT '是否必填',
  `htmlFieldType` int(11) DEFAULT NULL COMMENT '1text,2password',
  `htmlFieldLength` int(11) DEFAULT NULL COMMENT '字段长度',
  `htmlMinValue` decimal(15,2) DEFAULT NULL,
  `htmlMaxValue` decimal(15,2) DEFAULT NULL,
  `sortno` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tc_htmlfield
-- ----------------------------
INSERT INTO `tc_htmlfield` VALUES ('032101b521d74af3a597144b2382bd6e', 'testlogin', '密码', '2', 'name', 'password', '//*[@name=\'password\']', '1', '2', '20', null, null, '2');
INSERT INTO `tc_htmlfield` VALUES ('e46246aaa2874e2694f6805e9fa1eb02', 'testlogin', '账号', '2', 'name', 'account', '//*[@name=\'account\']', '1', '1', '20', null, null, '1');

-- ----------------------------
-- Table structure for tc_htmlfunction
-- ----------------------------
DROP TABLE IF EXISTS `tc_htmlfunction`;
CREATE TABLE `tc_htmlfunction` (
  `id` varchar(50) NOT NULL,
  `name` varchar(200) NOT NULL COMMENT '功能名称',
  `url` varchar(2000) DEFAULT NULL COMMENT '功能URL',
  `pid` varchar(50) DEFAULT NULL,
  `findType` int(11) NOT NULL DEFAULT '0' COMMENT '0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert',
  `elementKey` varchar(2000) DEFAULT NULL COMMENT '元素的Key,例如 userName 或者xpath的表达式',
  `compare` varchar(100) DEFAULT 'eq' COMMENT 'eq,lt,',
  `elementValue` varchar(2000) DEFAULT NULL COMMENT '期望结果,例如判断网页的标题',
  `xpath` varchar(2000) NOT NULL COMMENT '实际的xpath表达式',
  `sortno` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tc_htmlfunction
-- ----------------------------
INSERT INTO `tc_htmlfunction` VALUES ('testlogin', '测试登陆功能', 'http://127.0.0.1:8080/springrain/system/login', null, '1', 'id', 'eq', 'sbtButton', '//*[@id=\'sbtButton\']', '1');

-- ----------------------------
-- Table structure for tc_validaterule
-- ----------------------------
DROP TABLE IF EXISTS `tc_validaterule`;
CREATE TABLE `tc_validaterule` (
  `id` varchar(50) NOT NULL,
  `fieldId` varchar(50) NOT NULL COMMENT '业务Id',
  `resultType` int(11) NOT NULL COMMENT '1字段为空,2字段格式不对,3字段范围不对,4内容错误,5字段正常',
  `validateValue` varchar(2000) DEFAULT NULL COMMENT '期望结果,例如判断网页的标题',
  `validateFindType` int(11) NOT NULL DEFAULT '0' COMMENT '0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert',
  `validateElementKey` varchar(2000) DEFAULT NULL COMMENT '元素的Key,例如 userName 或者xpath的表达式',
  `validateCompare` varchar(100) DEFAULT 'eq' COMMENT 'eq,lt,',
  `validateElementValue` varchar(2000) DEFAULT NULL COMMENT '期望结果,例如判断网页的标题',
  `validateXpath` varchar(2000) NOT NULL COMMENT '实际的xpath表达式',
  `sortno` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tc_validaterule
-- ----------------------------
INSERT INTO `tc_validaterule` VALUES ('accountv3', 'e46246aaa2874e2694f6805e9fa1eb02', '1', null, '3', 'msg', 'eq', '账号不能为空!', '//*[@class=\'msg\']', '1');
INSERT INTO `tc_validaterule` VALUES ('accountv6', 'e46246aaa2874e2694f6805e9fa1eb02', '4', null, '3', 'msg', 'eq', '账号错误!', '//*[@class=\'msg\']', '2');
INSERT INTO `tc_validaterule` VALUES ('accountv7', 'e46246aaa2874e2694f6805e9fa1eb02', '5', null, '3', 'msg', 'eq', '密码不能为空!', '//*[@class=\'msg\']', '3');
INSERT INTO `tc_validaterule` VALUES ('passwordv3', '032101b521d74af3a597144b2382bd6e', '1', null, '3', 'msg', 'eq', '密码不能为空!', '//*[@class=\'msg\']', '4');
INSERT INTO `tc_validaterule` VALUES ('passwordv6', '032101b521d74af3a597144b2382bd6e', '4', null, '3', 'msg', 'eq', '密码错误!', '//*[@class=\'msg\']', '5');
INSERT INTO `tc_validaterule` VALUES ('passwordv7', '032101b521d74af3a597144b2382bd6e', '1', null, '0', 'title', 'eq', '后台管理系统', '/html/head/title', '6');
