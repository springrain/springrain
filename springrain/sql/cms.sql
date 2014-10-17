/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50619
Source Host           : 127.0.0.1:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2014-10-15 14:06:12
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- 系统主题表,用于展示系统的基本主题,站点根据选择复制到网站私有的文件夹 类似 /userId/siteId/themeId
-- ----------------------------
DROP TABLE IF EXISTS `cms_theme`;
CREATE TABLE `cms_theme` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `businessTypeId` varchar(50) DEFAULT NULL COMMENT '行业类型',
  `description` varchar(2000) DEFAULT NULL COMMENT '备注',
  `themedir` varchar(5000) DEFAULT NULL COMMENT '主题路径',
  `usecount` int(11) DEFAULT NULL COMMENT '使用次数',
  `ostype` varchar(20) DEFAULT NULL COMMENT 'pc,pad,mobile,app 四个平台的linkURL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 网站表 记录网站的基本信息,网站访问路径类似于 /pc/siteId/ /pad/siteId /mobile/siteId /app/siteId  首页固定 /index,存放到 cms_link 表中
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL  COMMENT '用户Id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `title` varchar(255) DEFAULT NULL,
  `keywords` varchar(1000) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `themeId` varchar(50) DEFAULT NULL COMMENT '主题Id',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `state` int(11) DEFAULT NULL COMMENT '状态 0关闭,1开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 栏目表 记录归属栏目,拥有上下级关系,栏目的 url 存放于 cms_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `id` varchar(50) NOT NULL,
  `pid` varchar(50) DEFAULT NULL COMMENT '上级Id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `keywords` varchar(1000) DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目表';


-- ----------------------------
-- 栏目和网站的关系表 主要处理多个平台的归属关系和渲染位置
-- ----------------------------
DROP TABLE IF EXISTS `cms_site_channel`;
CREATE TABLE `cms_site_channel` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '网站ID',
  `channelId` varchar(50) NOT NULL,
  `ostype` varchar(20) NOT NULL COMMENT 'pc,pad,mobile,app 四个平台',
  `position` varchar(10) DEFAULT NULL COMMENT '渲染位置',
  `sort` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 文章内容表 文章的基本信息,页面url存放到 cms_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_content`;
CREATE TABLE `cms_content` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `keywords` varchar(1000) DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `mintitle` varchar(200) DEFAULT NULL COMMENT '小标题',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `createPerson` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `content` text NOT NULL COMMENT '内容',
  `sort` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 栏目和文章的中间表 记录多个平台的归属关系
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel_content`;
CREATE TABLE `cms_channel_content` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL,
  `channelId` varchar(50) NOT NULL,
  `contentId` varchar(50) NOT NULL,
  `ostype` varchar(20) DEFAULT NULL COMMENT 'pc,pad,mobile,app 可用于细化数据归属',
  `sort` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- 跳转链接信息表 记录跳转的信息链接和本页面以及下级页面使用的模板路径
-- ----------------------------
DROP TABLE IF EXISTS `cms_link`;
CREATE TABLE `cms_link` (
  `id` varchar(50) NOT NULL,
  `name` varchar(500) NOT NULL,
  `defaultLink` varchar(1000) NOT NULL COMMENT '默认URL地址',
  `link` varchar(1000) NOT NULL COMMENT '使用的URL',
  `siteId` varchar(50) NOT NULL COMMENT '网站ID',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `ostype` varchar(20) NOT NULL COMMENT 'pc,pad,mobile,app 四个平台',
  `modelType` varchar(50) NOT NULL COMMENT 'site,channel,content',
  `ftlfile` varchar(1000) DEFAULT NULL COMMENT '当前渲染使用的模板路径',
  `nodeftlfile` varchar(1000) DEFAULT NULL COMMENT '子内容使用的ftl模板文件',
  `sort` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 图片信息表
-- ----------------------------
DROP TABLE IF EXISTS `cms_picture`;
CREATE TABLE `cms_picture` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '站点Id',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `filepath` varchar(500) NOT NULL COMMENT '文件物理路径',
  `imgtype` varchar(500) DEFAULT NULL COMMENT '图片类型',
  `pictureUrl` varchar(500) NOT NULL COMMENT '网络路径',
  `middlePictureUrl` varchar(500) DEFAULT NULL,
  `smallPictureUrl` varchar(500) DEFAULT NULL COMMENT '缩略图',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `modelType` varchar(50) NOT NULL COMMENT 'site,channel,content',
  `sort` int(11) NOT NULL COMMENT '排序',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 扩展属性表,扩展属性内定 site,channel,content 三个类型界别,属性可以依次进行继承使用,子元素可以覆盖父类元素的扩展属性 
-- ----------------------------
DROP TABLE IF EXISTS `cms_property`;
CREATE TABLE `cms_property` (
  `id` varchar(255) NOT NULL,
  `siteId` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) DEFAULT NULL COMMENT 'text,date,datatime,int,float,select,file',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `modelType` varchar(50) NOT NULL COMMENT 'site,channel,content',
  `createPerson` varchar(50) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `defaultValue` varchar(100) DEFAULT NULL COMMENT '默认值',
  `style` varchar(255) DEFAULT NULL COMMENT '样式',
  `sort` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- 扩展属性值 实际用户进行定义的属性值
-- ----------------------------
DROP TABLE IF EXISTS `cms_propertyvalue`;
CREATE TABLE `cms_propertyvalue` (
  `id` varchar(50) NOT NULL,
  `propertyId` int(11) DEFAULT NULL,
  `value` text,
  `siteId` varchar(50) NOT NULL,
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `state` int(11) DEFAULT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='属性表';



