/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50528
Source Host           : 127.0.0.1:3306
Source Database       : testdb1

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2013-04-02 09:47:32
*/

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
-- Table structure for `testtable`
-- ----------------------------
DROP TABLE IF EXISTS `testtable`;
CREATE TABLE `testtable` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(200) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(200) NOT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



