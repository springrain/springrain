/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50716
Source Host           : 127.0.0.1:3306
Source Database       : springrain

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-01 15:09:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_cpconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_cpconfig`;
CREATE TABLE `wx_cpconfig` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '站点Id',
  `appId` varchar(500) DEFAULT NULL COMMENT '开发者Id',
  `appSecret` varchar(500) DEFAULT NULL COMMENT '应用密钥',
  `token` varchar(500) DEFAULT NULL COMMENT '开发者Id',
  `encodingAESKey` varchar(500) DEFAULT NULL COMMENT '消息加解密密钥',
  `wxId` varchar(500) DEFAULT NULL COMMENT '原始ID',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信号需要的配置信息';

-- ----------------------------
-- Records of wx_cpconfig
-- ----------------------------
INSERT INTO `wx_cpconfig` VALUES ('s_10006', 's_10006', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `wx_menu`;
CREATE TABLE `wx_menu` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `type` varchar(50) DEFAULT NULL COMMENT '菜单类型',
  `keyword` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `url` varchar(255) DEFAULT NULL COMMENT '跳转地址',
  `pid` varchar(50) DEFAULT NULL COMMENT '上级菜单id',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `siteId` varchar(50) DEFAULT NULL COMMENT '站点id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_menu
-- ----------------------------

-- ----------------------------
-- Table structure for wx_mpconfig
-- ----------------------------
DROP TABLE IF EXISTS `wx_mpconfig`;
CREATE TABLE `wx_mpconfig` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '站点Id',
  `appId` varchar(500) DEFAULT NULL COMMENT '开发者Id',
  `appSecret` varchar(500) DEFAULT NULL COMMENT '应用密钥',
  `token` varchar(500) DEFAULT NULL COMMENT '开发者Id',
  `encodingAESKey` varchar(500) DEFAULT NULL COMMENT '消息加解密密钥',
  `wxId` varchar(500) DEFAULT NULL COMMENT '原始ID',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信号需要的配置信息';

-- ----------------------------
-- Records of wx_mpconfig
-- ----------------------------
INSERT INTO `wx_mpconfig` VALUES ('s_10006', 's_10006', '1', '1', '1', '1', '1', '1');
