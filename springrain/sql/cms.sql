/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50714
Source Host           : 127.0.0.1:3306
Source Database       : springrain

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2016-10-23 16:21:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_attachment
-- ----------------------------
DROP TABLE IF EXISTS `cms_attachment`;
CREATE TABLE `cms_attachment` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '站点Id',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `propertyCode` varchar(50) DEFAULT NULL COMMENT '扩展属性code',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `filepath` varchar(500) NOT NULL COMMENT '文件物理路径',
  `filetype` varchar(500) DEFAULT NULL COMMENT '图片类型',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `modelType` varchar(50) NOT NULL COMMENT 'site,channel,content(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `sortno` int(11) NOT NULL COMMENT '排序',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for cms_channel
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
-- Records of cms_channel
-- ----------------------------

-- ----------------------------
-- Table structure for cms_channel_content
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel_content`;
CREATE TABLE `cms_channel_content` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL,
  `channelId` varchar(50) NOT NULL,
  `contentId` varchar(50) NOT NULL,
  `ostype` varchar(20) DEFAULT NULL COMMENT 'pc,pad,weixin,app 可用于细化数据归属',
  `sortno` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_channel_content
-- ----------------------------

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL COMMENT '评论用户ID',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `pcommentId` varchar(50) NOT NULL COMMENT '父级评论Id',
  `siteId` int(11) NOT NULL COMMENT '站点ID',
  `createDate` datetime NOT NULL COMMENT '评论时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `content` longtext COMMENT '评论内容',
  `ups` smallint(6) NOT NULL DEFAULT '0' COMMENT '支持数',
  `downs` smallint(6) NOT NULL DEFAULT '0' COMMENT '反对数',
  `checked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否审核',
  `score` int(11) DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CMS评论表';

-- ----------------------------
-- Records of cms_comment
-- ----------------------------

-- ----------------------------
-- Table structure for cms_content
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
  `sortno` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_content
-- ----------------------------

-- ----------------------------
-- Table structure for cms_friend_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_friend_site`;
CREATE TABLE `cms_friend_site` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL,
  `name` varchar(200) NOT NULL,
  `linkType` varchar(50) NOT NULL COMMENT '跳出类型,_blank',
  `url` varchar(500) NOT NULL COMMENT '网站地址',
  `logo` varchar(2000) NOT NULL COMMENT '网站logo',
  `sortno` int(11) DEFAULT NULL COMMENT '排序',
  `state` int(11) DEFAULT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_friend_site
-- ----------------------------

-- ----------------------------
-- Table structure for cms_link
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
  `ostype` varchar(20) NOT NULL COMMENT 'pc,pad,weixin,mobile,app 五个平台',
  `modelType` varchar(50) NOT NULL COMMENT 'site,channel,content(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `ftlfile` varchar(1000) DEFAULT NULL COMMENT '当前渲染使用的模板路径',
  `nodeftlfile` varchar(1000) DEFAULT NULL COMMENT '子内容使用的ftl模板文件',
  `sortno` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_link
-- ----------------------------

-- ----------------------------
-- Table structure for cms_picture
-- ----------------------------
DROP TABLE IF EXISTS `cms_picture`;
CREATE TABLE `cms_picture` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '站点Id',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `propertyCode` varchar(50) DEFAULT NULL COMMENT '扩展属性code',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `filepath` varchar(500) NOT NULL COMMENT '文件物理路径',
  `imgtype` varchar(500) DEFAULT NULL COMMENT '图片类型',
  `pictureUrl` varchar(500) NOT NULL COMMENT '缩略图',
  `middlePictureUrl` varchar(500) DEFAULT NULL COMMENT '中图',
  `smallPictureUrl` varchar(500) DEFAULT NULL COMMENT '小图',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `modelType` varchar(50) NOT NULL COMMENT 'site,channel,content(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `sortno` int(11) NOT NULL COMMENT '排序',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_picture
-- ----------------------------

-- ----------------------------
-- Table structure for cms_property
-- ----------------------------
DROP TABLE IF EXISTS `cms_property`;
CREATE TABLE `cms_property` (
  `id` varchar(100) NOT NULL COMMENT 'siteId_code,保证一个站点下code唯一',
  `siteId` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL COMMENT '系统级别的编码,一个站点不可重复',
  `inputType` varchar(50) DEFAULT NULL COMMENT 'text,date,datatime,int,float,select,file,img,imgs',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `modelType` varchar(50) NOT NULL COMMENT 'site,channel,content(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `createPerson` varchar(50) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `defaultValue` varchar(100) DEFAULT NULL COMMENT '默认值',
  `style` varchar(255) DEFAULT NULL COMMENT '样式',
  `sortno` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_property
-- ----------------------------

-- ----------------------------
-- Table structure for cms_propertyvalue
-- ----------------------------
DROP TABLE IF EXISTS `cms_propertyvalue`;
CREATE TABLE `cms_propertyvalue` (
  `id` varchar(50) NOT NULL,
  `propertyId` int(11) DEFAULT NULL,
  `pvalue` text,
  `siteId` varchar(50) NOT NULL,
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `sortno` int(11) DEFAULT NULL COMMENT '排序',
  `state` int(11) DEFAULT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='属性表';

-- ----------------------------
-- Records of cms_propertyvalue
-- ----------------------------

-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL COMMENT '用户Id',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `title` varchar(255) DEFAULT NULL,
  `logo` varchar(2000) NOT NULL COMMENT '网站logo',
  `footer` varchar(2000) NOT NULL COMMENT '页脚',
  `qq` varchar(20) NOT NULL COMMENT 'QQ',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `contacts` varchar(20) NOT NULL COMMENT '联系人',
  `keywords` varchar(1000) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `themeId` varchar(50) DEFAULT NULL COMMENT '主题组Id',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `siteType` varchar(50) DEFAULT '网站' COMMENT '网站类型(网站,商城,论坛)',
  `state` int(11) DEFAULT NULL COMMENT '状态 0关闭,1开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_site
-- ----------------------------

-- ----------------------------
-- Table structure for cms_site_channel
-- ----------------------------
DROP TABLE IF EXISTS `cms_site_channel`;
CREATE TABLE `cms_site_channel` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '网站ID',
  `channelId` varchar(50) NOT NULL,
  `ostype` varchar(20) NOT NULL COMMENT 'pc,pad,weixin,mobile,app 五个平台',
  `position` varchar(10) DEFAULT NULL COMMENT '渲染位置,(上中下底)',
  `channeltype` int(11) NOT NULL COMMENT '栏目类型分为 导航菜单(0) 内容类似标签(1) ',
  `sortno` int(11) NOT NULL COMMENT '排序',
  `state` int(11) NOT NULL COMMENT '0失效,1有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_site_channel
-- ----------------------------

-- ----------------------------
-- Table structure for cms_template
-- ----------------------------
DROP TABLE IF EXISTS `cms_template`;
CREATE TABLE `cms_template` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `description` varchar(2000) DEFAULT NULL COMMENT '备注',
  `ftlfile` varchar(1000) DEFAULT NULL COMMENT '渲染使用的模板路径',
  `imgfile` varchar(1000) DEFAULT NULL COMMENT '缩略图路径路径',
  `modelType` varchar(50) NOT NULL COMMENT 'site,channel,content(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `usecount` int(11) DEFAULT NULL COMMENT '使用次数',
  `ostype` varchar(20) DEFAULT NULL COMMENT 'pc,pad,weixin,mobile,app 五个平台的linkURL',
  `state` int(11) DEFAULT NULL COMMENT '状态 0关闭,1开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_template
-- ----------------------------

-- ----------------------------
-- Table structure for cms_theme
-- ----------------------------
DROP TABLE IF EXISTS `cms_theme`;
CREATE TABLE `cms_theme` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `usecount` int(11) DEFAULT NULL COMMENT '使用次数',
  `ostype` varchar(20) DEFAULT NULL COMMENT 'pc,pad,weixin,mobile,app 五个平台的linkURL',
  `state` int(11) DEFAULT NULL COMMENT '状态 0关闭,1开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_theme
-- ----------------------------

-- ----------------------------
-- Table structure for cms_theme_template
-- ----------------------------
DROP TABLE IF EXISTS `cms_theme_template`;
CREATE TABLE `cms_theme_template` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `themeId` varchar(50) NOT NULL COMMENT '主题Id',
  `templateId` varchar(50) NOT NULL COMMENT '模板ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_theme_template
-- ----------------------------
