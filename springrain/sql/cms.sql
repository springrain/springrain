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
  `filepath` varchar(1000) NOT NULL COMMENT '文件物理路径',
  `filesuffix` varchar(50) DEFAULT NULL COMMENT '文件后缀',
  `fileurl` varchar(1000) NOT NULL COMMENT '文件路径',
  `thumbnail` varchar(1000) DEFAULT NULL COMMENT '缩略图',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `modelType` int(11) NOT NULL DEFAULT 0 COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `sortno` int(11) NOT NULL  DEFAULT 0  COMMENT '排序',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

-- ----------------------------
-- Records of cms_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for cms_channel
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `id` varchar(50) NOT NULL,
  `name` varchar(500) DEFAULT NULL COMMENT '名称',
  `pid` varchar(50) NOT NULL,
  `comcode` varchar(1000) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '网站ID',
  `positionLevel` int(11)  NOT NULL DEFAULT 0 COMMENT '0导航,1-10个级别',
  `title` varchar(500) DEFAULT NULL COMMENT '标题',
  `keywords` varchar(1000) DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `sortno` int(11) NOT NULL  DEFAULT 0  COMMENT '排序',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
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
  `sortno` int(11) NOT NULL  DEFAULT 0  COMMENT '排序',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目内容中间表';

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
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `title` varchar(500) DEFAULT NULL,
  `keywords` varchar(1000) DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `name` varchar(500) DEFAULT NULL COMMENT '名称',
  `mintitle` varchar(200) DEFAULT NULL COMMENT '小标题',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `createPerson` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `content` text NOT NULL COMMENT '内容',
  `source` varchar(1000) DEFAULT NULL COMMENT '来源',
  `sourceurl` varchar(1000) DEFAULT NULL COMMENT '来源地址',
  `sortno` int(11) NOT NULL  DEFAULT 0  COMMENT '排序',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';

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
  `url` varchar(1000) NOT NULL COMMENT '网站地址',
  `logo` varchar(2000) NOT NULL COMMENT '网站logo',
  `sortno` int(11) DEFAULT NULL COMMENT '排序',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='友情链接';

-- ----------------------------
-- Records of cms_friend_site
-- ----------------------------

-- ----------------------------
-- Table structure for cms_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_link`;
CREATE TABLE `cms_link` (
  `id` varchar(50) NOT NULL,
  `name` varchar(1000),
  `defaultLink` varchar(1000) NOT NULL COMMENT '默认URL地址',
  `link` varchar(1000) NOT NULL COMMENT '使用的URL',
  `siteId` varchar(50) NOT NULL COMMENT '网站ID',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `modelType` int(11) NOT NULL DEFAULT 0 COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `ftlfile` varchar(1000)  NOT NULL COMMENT '当前渲染使用的模板路径',
  `nodeftlfile` varchar(1000) DEFAULT NULL COMMENT '子内容使用的ftl模板文件',
  `statichtml` int(11) NOT NULL  DEFAULT 0  COMMENT '是否静态化 0否,1是',
  `sortno` int(11) NOT NULL  DEFAULT 0  COMMENT '排序',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务链接表';

-- ----------------------------
-- Records of cms_link
-- ----------------------------


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
  `inputType`  int(11)NOT NULL DEFAULT 0 COMMENT '0text,1date,2datatime,3int,4float,5select,6file,7img,8imgs',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `modelType` int(11) NOT NULL DEFAULT 0 COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `createPerson` varchar(50) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `defaultValue` varchar(100) DEFAULT NULL COMMENT '默认值',
  `style` varchar(500) DEFAULT NULL COMMENT '样式',
  `sortno` int(11) NOT NULL  DEFAULT 0  COMMENT '排序',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义属性表';

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
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='自定义属性对应值表';

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
  `name` varchar(500) DEFAULT NULL COMMENT '名称',
  `title` varchar(500) DEFAULT NULL,
  `domainurl` varchar(2000) DEFAULT NULL COMMENT '网站域名',
  `logo` varchar(2000) NOT NULL COMMENT '网站logo',
  `footer` varchar(2000) NOT NULL COMMENT '页脚',
  `qq` varchar(50) NOT NULL COMMENT 'QQ',
  `phone` varchar(50) NOT NULL COMMENT '电话',
  `contacts` varchar(50) NOT NULL COMMENT '联系人',
  `keywords` varchar(1000) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `themeId` varchar(50) DEFAULT NULL COMMENT '主题Id',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `siteType`  int(11) NOT NULL DEFAULT 0 COMMENT '0微信订阅服务号,1wap,2网站   ',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点表';

-- ----------------------------
-- Records of cms_site
-- ----------------------------



-- ----------------------------
-- Table structure for cms_site_wxconfig
-- ----------------------------
DROP TABLE IF EXISTS `cms_site_wxconfig`;
CREATE TABLE `cms_site_wxconfig` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL COMMENT '站点Id',
  `appId` varchar(500) DEFAULT NULL COMMENT '开发者Id',
  `appSecret` varchar(500) DEFAULT NULL COMMENT '应用密钥',
  `token` varchar(500) DEFAULT NULL COMMENT '开发者Id',
  `encodingAESKey` varchar(500) DEFAULT NULL COMMENT '消息加解密密钥',
  `wxId` varchar(500) DEFAULT NULL COMMENT '原始ID',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信号需要的配置信息';

-- ----------------------------
-- Records of cms_site_wxconfig
-- ----------------------------


-- ----------------------------
-- Table structure for cms_template
-- ----------------------------
DROP TABLE IF EXISTS `cms_template`;
CREATE TABLE `cms_template` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(500) NOT NULL COMMENT '名称',
  `description` varchar(2000) DEFAULT NULL COMMENT '备注',
  `ftlfile` varchar(1000) DEFAULT NULL COMMENT '渲染使用的模板路径',
  `imgfile` varchar(1000) DEFAULT NULL COMMENT '缩略图路径路径',
  `modelType` int(11) NOT NULL DEFAULT 0 COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `usecount` int(11) DEFAULT NULL COMMENT '使用次数',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板表';

-- ----------------------------
-- Records of cms_template
-- ----------------------------

-- ----------------------------
-- Table structure for cms_theme
-- ----------------------------
DROP TABLE IF EXISTS `cms_theme`;
CREATE TABLE `cms_theme` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `name` varchar(500) NOT NULL COMMENT '名称',
  `usecount` int(11) DEFAULT NULL COMMENT '使用次数',
  `state` int(11) NOT NULL DEFAULT 1 COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题表';

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
  `templateId` varchar(50) NOT NULL COMMENT '模板Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题和模板中间表';

-- ----------------------------
-- Records of cms_theme_template
-- ----------------------------


INSERT INTO `t_tableindex` (`id`, `maxIndex`, `prefix`) VALUES ('cms_site', '10', 's_');
INSERT INTO `t_tableindex` (`id`, `maxIndex`, `prefix`) VALUES ('cms_channel', '100', 'h_');
INSERT INTO `t_tableindex` (`id`, `maxIndex`, `prefix`) VALUES ('cms_content', '1000', 'c_');



