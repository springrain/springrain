/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : springrain

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-30 17:09:39
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
  `htmlFieldName` varchar(200) NOT NULL,
  `htmlFieldId` varchar(50) NOT NULL,
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
INSERT INTO `tc_htmlcase` VALUES ('31d9bc636a2840b1a084c4d4183e20c2', 'testlogin', 'accountv6', 'hcaccount2', 'account', 'account', '1', null, '账号错误!', '0', '2');
INSERT INTO `tc_htmlcase` VALUES ('43d2a44d57f94eafa251ab7fbbcfac6f', 'testlogin', 'passwordv6', 'hcpassword2', 'password', 'password', '1', null, '密码错误!', '0', '5');
INSERT INTO `tc_htmlcase` VALUES ('50890135da43425999fb7c10094b19ac', 'testlogin', 'accountv3', 'hcaccount1', 'account', 'account', '', null, '账号不能为空!', '0', '1');
INSERT INTO `tc_htmlcase` VALUES ('d134165168a3423daedb9679399d7c44', 'testlogin', 'passwordv7', 'hcpassword3', 'password', 'password', 'admin', null, '后台管理系统', '0', '6');
INSERT INTO `tc_htmlcase` VALUES ('e0336bd67c3d4eaf9842a3895da6c7e4', 'testlogin', 'passwordv3', 'hcpassword1', 'password', 'password', '', null, '密码不能为空!', '0', '4');
INSERT INTO `tc_htmlcase` VALUES ('f35012f2f9b2470b80b791a4d1f0437d', 'testlogin', 'accountv7', 'hcaccount3', 'account', 'account', 'admin', null, '密码不能为空!', '0', '3');

-- ----------------------------
-- Table structure for tc_htmlfield
-- ----------------------------
DROP TABLE IF EXISTS `tc_htmlfield`;
CREATE TABLE `tc_htmlfield` (
  `id` varchar(50) NOT NULL,
  `functionId` varchar(50) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `htmlId` varchar(200) DEFAULT NULL,
  `htmlName` varchar(200) DEFAULT NULL,
  `htmlFieldType` int(11) DEFAULT NULL COMMENT '1text,2password',
  `htmlFieldLength` int(11) DEFAULT NULL COMMENT '字段长度',
  `htmlMinValue` decimal(15,2) DEFAULT NULL,
  `htmlMaxValue` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tc_htmlfield
-- ----------------------------
INSERT INTO `tc_htmlfield` VALUES ('10bd8828bc534c5fae5d0890f3989e4c', 'testlogin', '密码', '', 'password', '2', '20', null, null);
INSERT INTO `tc_htmlfield` VALUES ('246852823ba54888ae144434b8d27e34', 'testlogin', '账号', '', 'account', '1', '20', null, null);

-- ----------------------------
-- Table structure for tc_htmlfunction
-- ----------------------------
DROP TABLE IF EXISTS `tc_htmlfunction`;
CREATE TABLE `tc_htmlfunction` (
  `id` varchar(50) NOT NULL,
  `name` varchar(200) NOT NULL COMMENT '功能名称',
  `url` varchar(2000) DEFAULT NULL COMMENT '功能URL',
  `pid` varchar(50) DEFAULT NULL,
  `btnId` varchar(200) DEFAULT NULL,
  `btnText` varchar(200) DEFAULT NULL,
  `sortno` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tc_htmlfunction
-- ----------------------------
INSERT INTO `tc_htmlfunction` VALUES ('testlogin', '测试登陆功能', 'http://127.0.0.1:8080/springrain/system/login', null, 'sbtButton', null, '1');

-- ----------------------------
-- Table structure for tc_validaterule
-- ----------------------------
DROP TABLE IF EXISTS `tc_validaterule`;
CREATE TABLE `tc_validaterule` (
  `id` varchar(50) NOT NULL,
  `fieldId` varchar(50) NOT NULL COMMENT '业务Id',
  `resultType` int(11) NOT NULL COMMENT '1功能成功,2功能失败,3字段为空,4字段格式不对,5字段范围不对,6内容错误,7字段正常',
  `findType` int(11) NOT NULL DEFAULT '0' COMMENT '0dcoucment,1id,2name,3className,4cssSelector,5linkText,6.tagName,7xpath,8alert',
  `elementKey` varchar(2000) DEFAULT NULL COMMENT '元素的Key,例如 userName 或者xpath的表达式',
  `compare` varchar(100) DEFAULT 'eq' COMMENT 'eq,lt,',
  `resultValue` varchar(2000) DEFAULT NULL COMMENT '期望结果,例如判断网页的标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tc_validaterule
-- ----------------------------
INSERT INTO `tc_validaterule` VALUES ('accountv3', '246852823ba54888ae144434b8d27e34', '3', '3', 'msg', 'eq', '账号不能为空!');
INSERT INTO `tc_validaterule` VALUES ('accountv6', '246852823ba54888ae144434b8d27e34', '6', '3', 'msg', 'eq', '账号错误!');
INSERT INTO `tc_validaterule` VALUES ('accountv7', '246852823ba54888ae144434b8d27e34', '7', '3', 'msg', 'eq', '密码不能为空!');
INSERT INTO `tc_validaterule` VALUES ('passwordv3', '10bd8828bc534c5fae5d0890f3989e4c', '3', '3', 'msg', 'eq', '密码不能为空!');
INSERT INTO `tc_validaterule` VALUES ('passwordv6', '10bd8828bc534c5fae5d0890f3989e4c', '6', '3', 'msg', 'eq', '密码错误!');
INSERT INTO `tc_validaterule` VALUES ('passwordv7', '10bd8828bc534c5fae5d0890f3989e4c', '1', '0', 'title', 'eq', '后台管理系统');
