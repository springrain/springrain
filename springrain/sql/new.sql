/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : 127.0.0.1:3306
Source Database       : springrain

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-01-19 11:46:14
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
  `modelType` int(11) NOT NULL DEFAULT '0' COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件表';

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
  `pid` varchar(50) DEFAULT NULL COMMENT '父类ID',
  `comcode` varchar(1000) NOT NULL COMMENT '编号 通过 ,, 间隔',
  `siteId` varchar(50) NOT NULL COMMENT '网站ID',
  `positionLevel` int(11) NOT NULL DEFAULT '0' COMMENT '0导航,1-10个级别',
  `keywords` varchar(1000) DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='栏目表';

-- ----------------------------
-- Records of cms_channel
-- ----------------------------
INSERT INTO `cms_channel` VALUES ('h_1001', 'test', null, ',h_1001,', 's_101', '1', '1', '1', null, '1', '1');

-- ----------------------------
-- Table structure for cms_channel_content
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel_content`;
CREATE TABLE `cms_channel_content` (
  `id` varchar(50) NOT NULL,
  `siteId` varchar(50) NOT NULL,
  `channelId` varchar(50) NOT NULL,
  `contentId` varchar(50) NOT NULL,
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='栏目内容中间表';

-- ----------------------------
-- Records of cms_channel_content
-- ----------------------------
INSERT INTO `cms_channel_content` VALUES ('e7ce4368a6fa4a66a8c9ba1d5f570e97', 's_101', 'h_1001', 'c_10001', '1', '1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='CMS评论表';

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
  `mintitle` varchar(200) DEFAULT NULL COMMENT '小标题',
  `summary` text COMMENT '摘要',
  `content` text NOT NULL COMMENT '内容',
  `keywords` varchar(1000) DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `createPerson` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `source` varchar(1000) DEFAULT NULL COMMENT '来源',
  `sourceurl` varchar(1000) DEFAULT NULL COMMENT '来源地址',
  `status` int(11) DEFAULT NULL COMMENT '状态  0未审核  1审核通过',
  `active` int(11) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容表';

-- ----------------------------
-- Records of cms_content
-- ----------------------------
INSERT INTO `cms_content` VALUES ('c_10001', '测试内容', '内容', null, '<p><img title=\"blob.png\" alt=\"blob.png\"></p>', '123', '1', null, null, '2017-01-18 15:24:21', '1', '1', null, '1');

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
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='友情链接';

-- ----------------------------
-- Records of cms_friend_site
-- ----------------------------

-- ----------------------------
-- Table structure for cms_link
-- ----------------------------
DROP TABLE IF EXISTS `cms_link`;
CREATE TABLE `cms_link` (
  `id` varchar(50) NOT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `defaultLink` varchar(1000) NOT NULL COMMENT '默认URL地址',
  `link` varchar(1000) NOT NULL COMMENT '使用的URL',
  `siteId` varchar(50) NOT NULL COMMENT '网站ID',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `modelType` int(11) NOT NULL DEFAULT '0' COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `ftlfile` varchar(1000) NOT NULL COMMENT '当前渲染使用的模板路径',
  `nodeftlfile` varchar(1000) DEFAULT NULL COMMENT '子内容使用的ftl模板文件',
  `statichtml` int(11) NOT NULL DEFAULT '0' COMMENT '是否静态化 0否,1是',
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务链接表';

-- ----------------------------
-- Records of cms_link
-- ----------------------------
INSERT INTO `cms_link` VALUES ('6d2354eb66314f2986ebf7ddef9f3080', '测试内容', '/f/2/s_101/c_10001', '/f/2/s_101/c_10001', 's_101', 'c_10001', '1', '2', '/u/s_101/content', null, '0', '1', '1');
INSERT INTO `cms_link` VALUES ('95c10276dc8543a7aeaac95a6081d199', 'test', '/f/2/s_101/h_1001', '/f/2/s_101/h_1001', 's_101', 'h_1001', '1', '1', '/u/s_101/channel', '/u/s_101/content.html', '0', '1', '0');
INSERT INTO `cms_link` VALUES ('c14ac00a948146b69b67135d6345eda9', 'test', '/f/2/s_101/index', '/f/2/s_101/index', 's_101', 's_101', '1', '0', '/u/s_101/index', '/u/s_101/channel.html', '0', '1', '0');

-- ----------------------------
-- Table structure for cms_property
-- ----------------------------
DROP TABLE IF EXISTS `cms_property`;
CREATE TABLE `cms_property` (
  `id` varchar(100) NOT NULL COMMENT 'siteId_code,保证一个站点下code唯一',
  `siteId` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL COMMENT '系统级别的编码,一个站点不可重复',
  `inputType` int(11) NOT NULL DEFAULT '0' COMMENT '0text,1date,2datatime,3int,4float,5select,6file,7img,8imgs',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `modelType` int(11) NOT NULL DEFAULT '0' COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `createPerson` varchar(50) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `defaultValue` varchar(100) DEFAULT NULL COMMENT '默认值',
  `style` varchar(500) DEFAULT NULL COMMENT '样式',
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='自定义属性表';

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
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='自定义属性对应值表';

-- ----------------------------
-- Records of cms_propertyvalue
-- ----------------------------

-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `id` varchar(50) NOT NULL,
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `userId` varchar(50) NOT NULL COMMENT '用户Id',
  `name` varchar(500) DEFAULT NULL COMMENT '名称',
  `title` varchar(500) DEFAULT NULL,
  `domainurl` varchar(2000) DEFAULT NULL COMMENT '网站域名',
  `logo` varchar(2000) NOT NULL COMMENT '网站logo',
  `footer` text NOT NULL COMMENT '页脚',
  `qq` varchar(50) NOT NULL COMMENT 'QQ',
  `phone` varchar(50) NOT NULL COMMENT '电话',
  `contacts` varchar(50) NOT NULL COMMENT '联系人',
  `keywords` varchar(1000) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `themeId` varchar(50) DEFAULT NULL COMMENT '主题Id',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `siteType` int(11) NOT NULL DEFAULT '0' COMMENT '0微信订阅服务号,1wap,2网站   ',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  `springbeanid` varchar(100) DEFAULT NULL COMMENT 'springbeanid',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点表';

-- ----------------------------
-- Records of cms_site
-- ----------------------------
INSERT INTO `cms_site` VALUES ('s_101', null, null, 'admin', 'test', '1', '1', '/upload/test', '1', '1', '1', '1', '1', '1', '', null, '2', '1', null);

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
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信号需要的配置信息';

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
  `modelType` int(11) NOT NULL DEFAULT '0' COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `usecount` int(11) DEFAULT NULL COMMENT '使用次数',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='模板表';

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
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='主题表';

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='主题和模板中间表';

-- ----------------------------
-- Records of cms_theme_template
-- ----------------------------

-- ----------------------------
-- Table structure for t_auditlog_history_2016
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2016`;
CREATE TABLE `t_auditlog_history_2016` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `operationType` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text COMMENT '旧值',
  `curValue` text COMMENT '新值',
  `operationTime` datetime DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500) DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50) DEFAULT NULL COMMENT '记录ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作记录';

-- ----------------------------
-- Records of t_auditlog_history_2016
-- ----------------------------

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='操作记录';

-- ----------------------------
-- Records of t_auditlog_history_2017
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公共字典';

-- ----------------------------
-- Records of t_dic_data
-- ----------------------------
INSERT INTO `t_dic_data` VALUES ('16b80bfb-f0ee-47a0-ba94-cc256abaed17', '专科', '', null, null, '', '1', 'xueli');
INSERT INTO `t_dic_data` VALUES ('7ed23330-5538-4943-8678-0c5a2121cf57', '高中', '', null, null, '', '1', 'xueli');
INSERT INTO `t_dic_data` VALUES ('936db407-ae1-45a7-a657-b60580e2a77a', '汉族', '101', null, null, '', '1', 'minzu');
INSERT INTO `t_dic_data` VALUES ('936db407-ae2-45a7-a657-b60580e2a77a', '回族', '', null, null, '', '1', 'minzu');
INSERT INTO `t_dic_data` VALUES ('936db407-ae3-45a7-a657-b60580e2a77a', '一级', '', null, null, '', '1', 'grade');
INSERT INTO `t_dic_data` VALUES ('936db407-ae4-45a7-a657-b60580e2a77a', '二级', '', null, null, '', '1', 'grade');
INSERT INTO `t_dic_data` VALUES ('d7d1744b-e69f-48d0-9760-b2eae6af039b', '本科', '', null, null, '', '1', 'xueli');

-- ----------------------------
-- Table structure for t_fwlog_history_2016
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2016`;
CREATE TABLE `t_fwlog_history_2016` (
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问日志';

-- ----------------------------
-- Records of t_fwlog_history_2016
-- ----------------------------

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问日志';

-- ----------------------------
-- Records of t_fwlog_history_2017
-- ----------------------------
INSERT INTO `t_fwlog_history_2017` VALUES ('0023949b531849bcb3ddde545bf2ea4c', '2017-01-18 11:38:15', '2017-01-18 11:38:15.0362', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('004146a620b34d2d830b3247f679ff4c', '2017-01-10 15:25:30', '2017-01-10 15:25:30.0063', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('00572d8945e34c09ab51e05f71c70cca', '2017-01-18 14:50:28', '2017-01-18 14:50:27.0871', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('00647788609649e3b6eb8033cf6d4013', '2017-01-18 14:50:14', '2017-01-18 14:50:14.0258', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('00731766a0f14d9c801229f9e61654dc', '2017-01-18 14:49:56', '2017-01-18 14:49:56.0182', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('00b09352868c4bb08dd59310638adddc', '2017-01-18 11:17:56', '2017-01-18 11:17:56.0282', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('010757c25a5a4baf8f83a9c681d8bf64', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0955', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0134631bdd84407da53bb496c029de13', '2017-01-11 14:36:39', '2017-01-11 14:36:39.0458', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0141cd9b383945f1af4a2790a0356d06', '2017-01-11 16:40:56', '2017-01-11 16:40:56.0425', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0155feb7d839448391d9587ee4f853ad', '2017-01-12 19:27:26', '2017-01-12 19:27:26.0411', null, 'admin', 'admin', '4acfe4e8-3242-414f-be7b-ca1c6176668b', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('01611987f742481799292604aa28afa2', '2017-01-11 16:57:22', '2017-01-11 16:57:21.0902', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('016df055ab8944228da980ee7d7fc6e5', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0461', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('01c293766eb84915878871c86fae99f5', '2017-01-18 14:38:52', '2017-01-18 14:38:52.0305', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('01e16c94366c4816adf5b6a0d3964391', '2017-01-18 14:49:42', '2017-01-18 14:49:41.0807', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('024aa4a132a54d4a88b0e8aa10905021', '2017-01-12 18:45:54', '2017-01-12 18:45:54.0099', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('024ec2274772499fae4c1c55c53e2810', '2017-01-18 11:37:23', '2017-01-18 11:37:22.0943', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('02652a56028740c792536d5a5cacb5df', '2017-01-18 14:48:55', '2017-01-18 14:48:55.0209', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('029e4977f07140bbad8e64715657d173', '2017-01-11 09:59:49', '2017-01-11 09:59:48.0890', null, 'admin', 'admin', '87a05838-310a-4bfd-b127-cc0b388faf3f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('02bcdc36a8214fb6b52ca3fe3f9c1f8d', '2017-01-18 14:48:59', '2017-01-18 14:48:58.0981', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('032cdcd89d8c49209c091accd2dc619d', '2017-01-13 10:21:52', '2017-01-13 10:21:51.0549', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('033228c4ab2f4d6dbe3cc404a46962dc', '2017-01-18 14:49:42', '2017-01-18 14:49:41.0944', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('036bdd100d8d4909bca25db38e78039c', '2017-01-12 18:43:44', '2017-01-12 18:43:43.0721', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('037cd24249644113b8e995136a442545', '2017-01-12 18:35:04', '2017-01-12 18:35:03.0900', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('03a4244ada0545acb4e4ef53cad51dc7', '2017-01-12 18:35:26', '2017-01-12 18:35:25.0912', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('044282cd085349488a67b4ae3610949b', '2017-01-12 14:01:17', '2017-01-12 14:01:17.0484', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0452f81ffe864c2fa33d78a9451944b2', '2017-01-18 15:17:50', '2017-01-18 15:17:50.0422', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('04e1fabf580b402faf9a03948fd0be42', '2017-01-12 18:53:56', '2017-01-12 18:53:56.0308', null, 'admin', 'admin', '1534998f-ddbc-4cf0-8dc5-d640539678ad', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('04f1e363e16147a28950cb8ea1adfd87', '2017-01-12 13:54:12', '2017-01-12 13:54:12.0489', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('059f8d119820440aaad6efaab3e43dbc', '2017-01-12 14:17:36', '2017-01-12 14:17:35.0614', null, 'admin', 'admin', '7b862a1e-d5b9-4509-8b02-dc28c5bd9f0b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('05acd0f97fc74d1388b1ef32879ea6a9', '2017-01-10 15:34:40', '2017-01-10 15:34:39.0959', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('05b88adea99b43d9aef21e1bf5967d87', '2017-01-11 10:57:46', '2017-01-11 10:57:46.0287', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('05e246074efb4a42a7f44a3f5a5ba5d8', '2017-01-12 14:37:29', '2017-01-12 14:37:28.0529', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('05e6f29ef28040bd981418d6d4414d28', '2017-01-18 12:09:45', '2017-01-18 12:09:44.0855', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('060d8062e81647fab53b2123f649eb4d', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0784', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('067633512d604d9a9923d3c0fdc99185', '2017-01-11 16:42:45', '2017-01-11 16:42:44.0781', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('068d6b0164d74e8395682fb8f3f011e5', '2017-01-18 14:30:07', '2017-01-18 14:30:06.0807', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('06d6f80957cc4108b59f922065ae7386', '2017-01-18 11:18:21', '2017-01-18 11:18:20.0617', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('06dac278bb9c49bb8f72497a1a62fd74', '2017-01-18 14:58:19', '2017-01-18 14:58:18.0885', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0722bb0030d1428daac241049f850865', '2017-01-11 10:16:09', '2017-01-11 10:16:09.0226', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('076abf668a944abdbd4d10cfe673c5dd', '2017-01-12 13:52:57', '2017-01-12 13:52:56.0706', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0772b82d8290471481cc61bfe1e984f9', '2017-01-18 11:54:59', '2017-01-18 11:54:58.0966', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0775d6a0ef9f456fb044cc49fbe83ed5', '2017-01-18 11:38:11', '2017-01-18 11:38:10.0557', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0784989d924145ea92fe90f5901fa123', '2017-01-18 14:09:22', '2017-01-18 14:09:21.0844', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('07bac1db829d44d08aaabdea76bd74d7', '2017-01-12 18:45:36', '2017-01-12 18:45:35.0538', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthird-party/zeroclipboard/ZeroClipboard.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('07bd245666ba4f8f93977e5344288db3', '2017-01-12 12:26:20', '2017-01-12 12:26:20.0432', null, 'admin', 'admin', '4dde7d85-af78-4ef5-8d44-95049547d30a', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('080bedf68625405a98b7c4116b631414', '2017-01-11 10:58:22', '2017-01-11 10:58:21.0999', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('087667ede8a24891a0159c66577b326e', '2017-01-11 15:00:38', '2017-01-11 15:00:37.0526', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('08775a63e7cb4980a976470191d2b3ed', '2017-01-18 11:53:43', '2017-01-18 11:53:42.0880', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('08d18e54c5ec4bbcabd5e6890b157607', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0023', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('08d4bcd38c744b0193cc129f57124627', '2017-01-12 12:34:54', '2017-01-12 12:34:54.0028', null, 'admin', 'admin', 'b75ce246-bc93-4c34-b53a-b69c5906e5d7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0914db6e5c1e4a51a4a0c004553b725f', '2017-01-11 14:36:45', '2017-01-11 14:36:45.0476', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/cmschannel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0934c605104e4734963f2c2ab2899500', '2017-01-18 14:58:32', '2017-01-18 14:58:31.0779', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('09a82d5fc9f842b5b1d2f2d61003a169', '2017-01-18 14:10:29', '2017-01-18 14:10:28.0646', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('09c8fc93cc5c46d6b7701ae77ca19147', '2017-01-18 14:49:34', '2017-01-18 14:49:34.0210', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('09fcc72de7f64c15817639c1ed6cacc8', '2017-01-11 11:44:58', '2017-01-11 11:44:57.0569', null, 'admin', 'admin', '30d2378e-ce1f-48a2-91a5-a7fe18ddd94f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0a42c2801dfb44e2975b8db5d13caa8f', '2017-01-18 14:49:43', '2017-01-18 14:49:42.0591', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0a595235eb6941d7a1c2d6b0775f5479', '2017-01-11 10:44:02', '2017-01-11 10:44:01.0715', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0b1b1316c3a84a57af6bc3bd86c47deb', '2017-01-12 12:34:56', '2017-01-12 12:34:55.0561', null, 'admin', 'admin', 'b75ce246-bc93-4c34-b53a-b69c5906e5d7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0b6cd9da051a4548be2e2e61d6860df9', '2017-01-18 14:14:22', '2017-01-18 14:14:21.0705', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0b7b9a51a8494952a4432a28c96a80a2', '2017-01-11 11:44:47', '2017-01-11 11:44:46.0720', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0b809c38e62d4f6f8c2c4afb68c089f1', '2017-01-11 15:45:23', '2017-01-11 15:45:23.0062', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0c1d275bf09742bbaaad3d96472857c5', '2017-01-11 17:30:31', '2017-01-11 17:30:31.0477', null, 'admin', 'admin', 'e1c888d2-cd21-41f1-b672-7656f69da531', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0c3dd3a718b14b969e030c5960438288', '2017-01-11 10:01:44', '2017-01-11 10:01:44.0274', null, 'admin', 'admin', '4b34858b-3c9d-4853-ad00-501a1d5057e4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0ca70323d387408093a67b224e835778', '2017-01-11 10:57:05', '2017-01-11 10:57:04.0694', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0ccc9e695fe34b86b81cb87d7a80440f', '2017-01-11 13:39:25', '2017-01-11 13:39:24.0851', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0ce8eb058d8748f39a7a9c2da56c0ee3', '2017-01-18 14:10:32', '2017-01-18 14:10:31.0870', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0d64c50113fd4167b8f1c9c7408145db', '2017-01-11 11:04:46', '2017-01-11 11:04:46.0084', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0d84cb687dbb4ce9957c9fe834e85813', '2017-01-18 11:16:55', '2017-01-18 11:16:54.0605', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0d8bd3b81b824210a8f55811ac96fd08', '2017-01-10 10:44:50', '2017-01-10 10:44:49.0615', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0d9854fabbb043e8b7be4914ad77dd31', '2017-01-12 19:35:38', '2017-01-12 19:35:37.0883', null, 'admin', 'admin', '35616fb5-6b83-4707-a604-52e68f109437', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0dc56f6a80b649539318c62c20c39d46', '2017-01-12 10:37:56', '2017-01-12 10:37:55.0719', null, 'admin', 'admin', '2ec1cc3e-4092-4a3f-baf8-34234e8b3d65', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0e13becb22654ab386714bd6782b1a37', '2017-01-12 14:12:22', '2017-01-12 14:12:22.0452', null, 'admin', 'admin', 'e071116e-b364-4a51-a488-64f340ff3599', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0e3aa3a1fc4c4dfc9e4b0ca16c655e05', '2017-01-12 13:58:37', '2017-01-12 13:58:37.0343', null, 'admin', 'admin', '62a2b596-f843-45ac-9d60-4e297251c6eb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0e45ea3fa79b4a339d3ea3100db856e2', '2017-01-13 10:21:44', '2017-01-13 10:21:44.0172', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0e6b9ae9a67e4467bb6198f60d5aaf98', '2017-01-11 17:26:17', '2017-01-11 17:26:16.0598', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0ec6de0e10024a4c9dae5efffa4a48ae', '2017-01-13 10:24:03', '2017-01-13 10:24:03.0203', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('0ee9392dbb9242deb148f03131e070ed', '2017-01-18 14:38:51', '2017-01-18 14:38:51.0457', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('0f885910c5d844779f8a102cd39ed381', '2017-01-12 14:01:14', '2017-01-12 14:01:14.0034', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('10049897d6a04190b207df3ed8e3550e', '2017-01-18 14:58:32', '2017-01-18 14:58:31.0955', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('100bf28d5984460f8c92319071b9dd4c', '2017-01-10 14:26:10', '2017-01-10 14:26:09.0503', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('104a2f0a55974e78948e2cac9e1670e9', '2017-01-18 14:58:05', '2017-01-18 14:58:05.0434', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1130fabffddb40a4a287c2a18798d740', '2017-01-18 11:59:11', '2017-01-18 11:59:10.0965', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1145458552ee4f47b1d80a79618d15c9', '2017-01-11 09:59:40', '2017-01-11 09:59:39.0892', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('116610cac55d454c8b4a5615f41803ac', '2017-01-18 14:58:29', '2017-01-18 14:58:29.0019', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('11a9c415d6a0493d9c4c8e95cf9e46d5', '2017-01-11 11:04:47', '2017-01-11 11:04:46.0583', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('11c2f27f79c641f0bd2c69d91bef133c', '2017-01-18 14:39:47', '2017-01-18 14:39:47.0467', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('11de0bbfedd3448593598c57317d422f', '2017-01-18 14:29:47', '2017-01-18 14:29:46.0552', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('11e24e770dbc410cb00ddfcf1edd4fd4', '2017-01-10 14:51:33', '2017-01-10 14:51:32.0515', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('11ee2b9be3a14f6a9e00637735cdb5aa', '2017-01-11 16:22:38', '2017-01-11 16:22:37.0706', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('12210d5c2fc546fda9992b6dfce3c926', '2017-01-12 19:28:51', '2017-01-12 19:28:51.0338', null, 'admin', 'admin', '7bdb306b-a7cd-4c37-b38b-f602a8760273', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('123463892e2249ec97c9437a322acb38', '2017-01-18 11:37:14', '2017-01-18 11:37:13.0556', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('12c790f14f2a4f86b290051c0b2d5a53', '2017-01-12 10:34:27', '2017-01-12 10:34:26.0883', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/themes/default/css/ueditor.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('12f60a87642c4d5d9c405e2a40478113', '2017-01-18 14:50:15', '2017-01-18 14:50:14.0533', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('12f8e8e757aa486c8e141511a2778208', '2017-01-11 11:03:16', '2017-01-11 11:03:16.0271', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1317884026fa46d09283db95c47602b3', '2017-01-12 14:37:25', '2017-01-12 14:37:25.0494', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('143c581e5f5a441da6c66084dd796e2d', '2017-01-18 14:16:09', '2017-01-18 14:16:08.0525', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('147f01609a494b3e85606acba68d9d2e', '2017-01-12 14:01:07', '2017-01-12 14:01:07.0112', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('148ffd89fb484d50b9204f6963a0fcec', '2017-01-11 11:02:03', '2017-01-11 11:02:03.0228', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('14d2e8c5df41429a9df6a2a66738abc1', '2017-01-12 14:40:53', '2017-01-12 14:40:53.0435', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('150046f9d26e4969ad2a3859b42de764', '2017-01-18 12:08:53', '2017-01-18 12:08:53.0355', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('15b90cd5c52c4dfe98b9ee021ec0db09', '2017-01-12 14:09:29', '2017-01-12 14:09:29.0393', null, 'admin', 'admin', '83e929e2-918d-479f-821b-bf7e3d995c50', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('15c41275e59d48769038775ee87420b9', '2017-01-11 10:56:39', '2017-01-11 10:56:39.0115', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('16580ee8ab5c4b3cb9d1100e37f76ed2', '2017-01-18 14:49:53', '2017-01-18 14:49:53.0391', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('16647503926d41058e9d37d1b86b5247', '2017-01-18 11:20:12', '2017-01-18 11:20:12.0058', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('169a55988a704952af99803fd4785fa4', '2017-01-12 15:06:36', '2017-01-12 15:06:35.0554', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('16cea8622d324d4abf3d811c04ec09b9', '2017-01-12 09:48:14', '2017-01-12 09:48:13.0814', null, 'admin', 'admin', 'a43ddec1-d5a4-4b20-acca-ccc568a8fa78', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('171ebe4536544cf3b882a14cab751b10', '2017-01-12 18:46:05', '2017-01-12 18:46:05.0401', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthird-party/codemirror/codemirror.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('173eb43dc83a4577b4d4597dd024205a', '2017-01-18 14:50:03', '2017-01-18 14:50:03.0391', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('17ae1238b4404336b684a51e0f9603c8', '2017-01-18 14:58:30', '2017-01-18 14:58:30.0306', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('17b1cb64788341b5beb58537d4b3d177', '2017-01-12 19:34:27', '2017-01-12 19:34:27.0338', null, 'admin', 'admin', '39523b9f-57d7-4ebb-932c-9e15a7ab07cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('17e86312b4fb4e94a447e2174307c1d5', '2017-01-10 14:18:58', '2017-01-10 14:18:58.0342', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1830cb77f558499e94f5224468328351', '2017-01-18 14:49:51', '2017-01-18 14:49:51.0439', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1837197928ee49c0beb3081d14af33e1', '2017-01-11 10:26:18', '2017-01-11 10:26:18.0383', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('18a3a7871b084c8e9109e329dc27e041', '2017-01-12 19:28:50', '2017-01-12 19:28:49.0541', null, 'admin', 'admin', '7bdb306b-a7cd-4c37-b38b-f602a8760273', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('18a5f7604031486f897442f96d211478', '2017-01-11 11:05:24', '2017-01-11 11:05:23.0659', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/delete', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('18f3f7c69d684cc6821eb41f1a48c835', '2017-01-18 14:58:30', '2017-01-18 14:58:29.0711', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1945394a317c493db1f280270b25c75f', '2017-01-10 14:26:10', '2017-01-10 14:26:09.0948', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('19a13516fb8846ada16d2af441ac7087', '2017-01-11 13:39:42', '2017-01-11 13:39:42.0195', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('19c2b3c44c794356996c336d560568b0', '2017-01-11 17:30:24', '2017-01-11 17:30:23.0840', null, 'admin', 'admin', 'e1c888d2-cd21-41f1-b672-7656f69da531', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('19d42bfa29264bcaaf0b23ccfad453f0', '2017-01-11 10:38:29', '2017-01-11 10:38:28.0910', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1a328e8e966844ceb64385fcab3c472e', '2017-01-11 16:56:00', '2017-01-11 16:55:59.0821', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1a5118c7bfbe4bd4a01dff7178696b9f', '2017-01-18 11:21:03', '2017-01-18 11:21:03.0250', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1a9f10a8d90a4f598d22409a3c6c96fe', '2017-01-12 14:03:42', '2017-01-12 14:03:41.0810', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1ab86c9ac7a54d96b944a8a07a9c6b44', '2017-01-12 12:17:51', '2017-01-12 12:17:51.0316', null, 'admin', 'admin', 'ee4ffd11-e144-43ff-8c0f-f4cf892e3778', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1bc23e4edf2946fabd9a3c0b3129d20d', '2017-01-18 14:50:14', '2017-01-18 14:50:14.0273', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1bdebbab3b4b4d4ba56e0749bea16d18', '2017-01-10 14:24:35', '2017-01-10 14:24:34.0857', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/grade/list', '[级别管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1be13a43c02641109fc58b6ac6ec42c0', '2017-01-18 11:38:06', '2017-01-18 11:38:06.0164', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1c3438000a2c470fa845fe49f0ab15b9', '2017-01-12 12:02:08', '2017-01-12 12:02:08.0456', null, 'admin', 'admin', '4c211f44-2df8-4eab-b4a5-05e1d33324cf', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1c9e1c10e48b4c5dba9680d803349a42', '2017-01-11 17:26:08', '2017-01-11 17:26:08.0118', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1ce1697010124f2d9d0eefc5a9d10164', '2017-01-12 18:40:04', '2017-01-12 18:40:03.0964', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1cf929267b6541c39b3fafcf39b2e5dc', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0229', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1d12599495894302b1184549dc4cb1ec', '2017-01-12 12:00:32', '2017-01-12 12:00:32.0172', null, 'admin', 'admin', '4eacb460-3a4b-4898-bd82-c1f1b7c999d5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1d35167a70ed4839b8adbd04533f35d7', '2017-01-10 14:10:51', '2017-01-10 14:10:50.0655', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1dd3e797fb2e40c18a00bd755853b594', '2017-01-11 10:00:55', '2017-01-11 10:00:55.0179', null, 'admin', 'admin', '4b34858b-3c9d-4853-ad00-501a1d5057e4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1de0b4b55d9249f29ecbe6a6eeead048', '2017-01-18 14:37:55', '2017-01-18 14:37:55.0163', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1e399791a57a40e389d9feea22b6968c', '2017-01-11 09:59:40', '2017-01-11 09:59:39.0513', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1e4950a86f1548b6a89e6a07b43a8f4a', '2017-01-18 14:50:04', '2017-01-18 14:50:04.0376', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1e497c4904974902bd21b015009e9fd8', '2017-01-12 10:39:22', '2017-01-12 10:39:21.0977', null, 'admin', 'admin', '2ec1cc3e-4092-4a3f-baf8-34234e8b3d65', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1e968d98964f4543b792ef5c786454f0', '2017-01-11 11:08:18', '2017-01-11 11:08:17.0693', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1ee4b69979ff4fb1966bcca1614f0247', '2017-01-18 12:21:13', '2017-01-18 12:21:13.0085', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1eeb53bc8fd94820b79a29dac9f715c1', '2017-01-18 11:53:53', '2017-01-18 11:53:52.0860', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1f059e73bdca413a91a48c6bef7def70', '2017-01-18 11:50:05', '2017-01-18 11:50:05.0265', null, 'admin', 'admin', 'd291ac99-d294-437e-9c59-b6bcb49afe7b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1f1cd193822b4447ad72c864a9b93de1', '2017-01-18 11:20:38', '2017-01-18 11:20:38.0137', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1f36b7a58ab747a4b827409efd6e1f98', '2017-01-11 17:24:29', '2017-01-11 17:24:28.0652', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/cmscontent/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1f48280f0599486ebef5f8ebaed7c90b', '2017-01-18 11:31:00', '2017-01-18 11:30:59.0613', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/', null, '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1f95f962db824707bfbc303182b63ef7', '2017-01-10 10:44:47', '2017-01-10 10:44:47.0182', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1f9d5c7dfd23415b90285b21122bcb29', '2017-01-11 11:18:37', '2017-01-11 11:18:37.0255', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1fad599cd7fe4242aafc89e9c10cb527', '2017-01-12 15:06:29', '2017-01-12 15:06:29.0375', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('1fb1f4322bb640af82ddba8b55848ccf', '2017-01-10 14:35:10', '2017-01-10 14:35:10.0378', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('1fbb5b75c3fd404299276a93a6ad3901', '2017-01-11 11:02:03', '2017-01-11 11:02:02.0945', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2026abeb17b4495bb90c7686c7669fda', '2017-01-10 14:22:02', '2017-01-10 14:22:02.0473', null, 'admin', 'admin', 'c59f18f4-412c-48e4-bcff-3880fca966c4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('20663dff1df44aa892f7e9d91d2d778b', '2017-01-18 14:49:54', '2017-01-18 14:49:53.0630', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('209c3cdb6889407db94e6574f180cf69', '2017-01-11 10:02:34', '2017-01-11 10:02:34.0140', null, 'admin', 'admin', '4b34858b-3c9d-4853-ad00-501a1d5057e4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('21097ff4a787438c8b1f08cd8f274c01', '2017-01-18 14:49:56', '2017-01-18 14:49:56.0464', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('211e1a0e9c5a4bec850a02e852090b13', '2017-01-12 15:27:21', '2017-01-12 15:27:21.0277', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('21abaafcf6304c72aeb5d14fda5bd074', '2017-01-18 14:10:27', '2017-01-18 14:10:27.0226', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('21fc7a6a8a3543159ebbe93c280be3da', '2017-01-11 10:05:48', '2017-01-11 10:05:48.0123', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('22758781b87e48669912aa7e179ce3ba', '2017-01-18 14:50:07', '2017-01-18 14:50:06.0914', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('228e7eea5a9e46f488e24b2012c67801', '2017-01-11 11:18:32', '2017-01-11 11:18:32.0061', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('22a7c5788cc8463a81bc262eb33c9602', '2017-01-11 17:30:22', '2017-01-11 17:30:22.0489', null, 'admin', 'admin', 'e1c888d2-cd21-41f1-b672-7656f69da531', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('22b29253b8c84c50bc82dd341669bf86', '2017-01-18 11:20:11', '2017-01-18 11:20:11.0166', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('22c60af7b2ac40928b492003fe80459d', '2017-01-10 14:24:33', '2017-01-10 14:24:32.0760', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('22db3025789a4bd48ca612865a16d18a', '2017-01-18 11:38:08', '2017-01-18 11:38:08.0406', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('22fd06160758448fadd209666d3202fe', '2017-01-11 13:39:43', '2017-01-11 13:39:42.0782', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('23111980e3694e96b40f4b05dc63f494', '2017-01-10 14:20:41', '2017-01-10 14:20:40.0646', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('23947eb10e034eff9ba851fcc1839f4c', '2017-01-18 14:10:31', '2017-01-18 14:10:31.0495', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('23fc9271ca0b44c69f8c240fad0fa912', '2017-01-18 14:14:25', '2017-01-18 14:14:24.0696', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2420ae33cbc24de9a67bc4f0f67c49cb', '2017-01-11 16:40:55', '2017-01-11 16:40:54.0501', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2426b4d8dd0f43b79137a0897da085fb', '2017-01-18 14:27:14', '2017-01-18 14:27:13.0590', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('242f2c0d725c47228c1a3eff01c8f6b3', '2017-01-10 14:25:45', '2017-01-10 14:25:45.0047', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('24423243dfd94030b9bc8aedea26bcf8', '2017-01-12 14:45:07', '2017-01-12 14:45:06.0530', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('244367e7510e48019a42a9d0ea8c2649', '2017-01-18 15:24:21', '2017-01-18 15:24:21.0143', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('246815c3d2b34214a9e7f04cf7efc606', '2017-01-12 10:15:04', '2017-01-12 10:15:04.0117', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2479bb0fe5e745ae8366be970cdfa97e', '2017-01-12 19:35:37', '2017-01-12 19:35:37.0241', null, 'admin', 'admin', '35616fb5-6b83-4707-a604-52e68f109437', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('247ce83469754c0ab9009921ae7f8b38', '2017-01-11 09:55:20', '2017-01-11 09:55:19.0540', null, 'admin', 'admin', '2c888c7f-55ba-4b57-855a-d14224d9fa1b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('24b0332e86d2424cab6311336400e63a', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0758', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('24cfab5d80d6457cb0d134e2d439ffe4', '2017-01-18 11:38:08', '2017-01-18 11:38:08.0116', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('24d8cfa876d14b7b84a326f41e8b0339', '2017-01-18 14:58:29', '2017-01-18 14:58:29.0237', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('255fea012d094112bc2d0126dd1f5f22', '2017-01-12 09:48:09', '2017-01-12 09:48:09.0067', null, 'admin', 'admin', 'a43ddec1-d5a4-4b20-acca-ccc568a8fa78', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('259082166ae34595bba39431eea43529', '2017-01-11 10:05:46', '2017-01-11 10:05:46.0152', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('25f2968837ed4e1c9a1f9569a559a328', '2017-01-18 15:17:03', '2017-01-18 15:17:02.0629', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2635853abd4f4abab62daff5368612ff', '2017-01-12 14:00:51', '2017-01-12 14:00:51.0130', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('26831390f4bc44e9bd62eae9ec882bd1', '2017-01-18 15:17:17', '2017-01-18 15:17:16.0509', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2691f9b4f3c94ca7a4c880d55b454f55', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0261', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('26a160d2b3cc429a932dc5642210f8f6', '2017-01-12 12:27:22', '2017-01-12 12:27:22.0203', null, 'admin', 'admin', '4dde7d85-af78-4ef5-8d44-95049547d30a', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('26aba9cc59b84093a276858c8ccdc056', '2017-01-12 15:10:18', '2017-01-12 15:10:18.0096', null, 'admin', 'admin', '97494dcd-46fb-4fb4-bbb1-048237ed5810', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('26da3e1d31bc4f45bae8673cf123e2d8', '2017-01-18 14:50:20', '2017-01-18 14:50:19.0825', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2745345596914546a51450474d71f3ef', '2017-01-11 15:45:45', '2017-01-11 15:45:45.0411', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('27521157c09e48c086e98ae571053979', '2017-01-18 14:50:16', '2017-01-18 14:50:16.0302', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('27ec7317b9474dcc8a79de54fe6e80f8', '2017-01-12 15:25:29', '2017-01-12 15:25:28.0915', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('283d26f9526142c7bac682c4c2d54b02', '2017-01-12 16:03:27', '2017-01-12 16:03:26.0707', null, 'admin', 'admin', '71da9334-ba68-46fc-966b-6aa214f52f10', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('288d019946784b03802b69db66dbcc94', '2017-01-18 14:27:19', '2017-01-18 14:27:19.0033', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('289d989232ce4ccdae2219324d798160', '2017-01-11 11:05:21', '2017-01-11 11:05:20.0547', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('28a8a7349d664cb7855acce5a3ebe8fa', '2017-01-12 10:33:31', '2017-01-12 10:33:31.0306', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/ueditorConfig/init', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('28ef065d7e9549219d716003290b9219', '2017-01-11 11:05:26', '2017-01-11 11:05:26.0124', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('293e5bce137e4e02b63dc51c74fb7529', '2017-01-10 14:51:45', '2017-01-10 14:51:45.0040', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('295d5b77cab8457cb4fba67d11379ce3', '2017-01-18 14:50:04', '2017-01-18 14:50:03.0594', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2977ca975afa4f7e9e2efeab2256b435', '2017-01-10 14:23:05', '2017-01-10 14:23:04.0621', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/grade/list', '[级别管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('297e20429b414b36bcb5813da366d86d', '2017-01-18 11:53:49', '2017-01-18 11:53:48.0814', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('29ba0735f84044559cf76eddf8aef3d1', '2017-01-18 14:15:21', '2017-01-18 14:15:20.0561', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2a0636c7129e4d2e82e33692c17887a8', '2017-01-12 19:40:50', '2017-01-12 19:40:49.0570', null, 'admin', 'admin', '81e57aea-c9f8-42e1-8b94-71cf8c2964df', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2a0ec7f1021d4097a7d2b1ef67f1d395', '2017-01-13 10:24:48', '2017-01-13 10:24:48.0057', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2a17b366164b43c4873cd57ef728e326', '2017-01-11 16:40:56', '2017-01-11 16:40:55.0947', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2a284c0524434ba7ac2192780740c7b7', '2017-01-12 18:41:51', '2017-01-12 18:41:51.0237', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2a817246cc09486cbaa112e92c5df41e', '2017-01-18 14:50:34', '2017-01-18 14:50:34.0363', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2ab2f46592b941f6ae39f9e5b2d079f8', '2017-01-11 17:27:21', '2017-01-11 17:27:20.0778', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2ad7c40b76174b21a358d05a8aeb3249', '2017-01-12 18:45:13', '2017-01-12 18:45:13.0052', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/third-party/zeroclipboard/ZeroClipboard.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2adb8b53848e4810ac2e3216cbb2bd3b', '2017-01-12 18:51:11', '2017-01-12 18:51:10.0714', null, 'admin', 'admin', 'bf865796-14f7-427b-91c9-2c97e9ad0fb2', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2b39883ec7e54dfda36ff58d5a5bf94a', '2017-01-10 14:50:51', '2017-01-10 14:50:51.0350', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2b3ad6d788554daf97b37388dd158b8b', '2017-01-12 15:19:46', '2017-01-12 15:19:46.0259', null, 'admin', 'admin', '1043ccac-d9e8-493f-8d39-6504147fe8e8', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2bea5d49caaa4969a2107aa2e821215c', '2017-01-12 19:07:54', '2017-01-12 19:07:54.0195', null, 'admin', 'admin', 'f2afac55-d9b7-49cd-b0f4-fa37b1fd792f', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2c22221510004007ba24cc43b1b16a77', '2017-01-18 11:20:07', '2017-01-18 11:20:06.0766', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2c283ed092084deea15873f0a2089bdb', '2017-01-13 14:11:01', '2017-01-13 14:11:01.0422', null, 'admin', 'admin', '479e8dba-8177-45fe-9f61-6dc36210a0e1', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/2/s_11/channel/h_101', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2c58fd59e97f41f4b16f30be27b3aa77', '2017-01-12 19:07:52', '2017-01-12 19:07:52.0446', null, 'admin', 'admin', 'f2afac55-d9b7-49cd-b0f4-fa37b1fd792f', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2c5e3e75f8264b9da4cafcf997711c1c', '2017-01-12 19:08:10', '2017-01-12 19:08:09.0719', null, 'admin', 'admin', 'f2afac55-d9b7-49cd-b0f4-fa37b1fd792f', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2c76f3b743264991b1263e07c231b217', '2017-01-12 13:52:46', '2017-01-12 13:52:45.0965', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2c976efb7f414d838640178e79f38e57', '2017-01-18 11:21:04', '2017-01-18 11:21:03.0507', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2cbe7a1d5b8a426fb7f53b25d2067182', '2017-01-12 11:36:50', '2017-01-12 11:36:49.0560', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2cd59c5623334b92b46a54b712af7394', '2017-01-10 14:52:45', '2017-01-10 14:52:44.0811', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/tree', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2cd6772ecd7740c69e2e9b817fc98138', '2017-01-10 14:23:03', '2017-01-10 14:23:03.0357', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2d306de7f0d54f91b1522cf41a0f16d0', '2017-01-12 19:27:27', '2017-01-12 19:27:27.0226', null, 'admin', 'admin', '4acfe4e8-3242-414f-be7b-ca1c6176668b', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2db202a57c514c58b84505d8c1524f27', '2017-01-18 14:08:58', '2017-01-18 14:08:57.0543', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2dbb10fa0a1143bab62cd8a2e6f6d630', '2017-01-12 14:00:47', '2017-01-12 14:00:47.0465', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2dce758aeaf749ec955fa587eaa5161a', '2017-01-18 11:19:42', '2017-01-18 11:19:42.0150', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2dd5f9d4db61420192e7706450e002b8', '2017-01-12 15:26:44', '2017-01-12 15:26:43.0836', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2de3d3bd67d94bab800752ad97295733', '2017-01-18 14:58:06', '2017-01-18 14:58:05.0562', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2dfe910cc32b417eb6525fc179409847', '2017-01-12 13:53:10', '2017-01-12 13:53:10.0050', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2e240f5dce764d3292a58446a73391f0', '2017-01-12 19:30:24', '2017-01-12 19:30:23.0947', null, 'admin', 'admin', '27778b74-5bc9-449b-9e29-fca6a177cccd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2e32155fe0974c59a66cb95329026564', '2017-01-11 15:38:06', '2017-01-11 15:38:06.0007', null, 'admin', 'admin', '06e91c2c-cc56-4c15-b5c3-d903949e5626', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2e3af5f72697440f8e467f7f5670ea17', '2017-01-11 17:25:08', '2017-01-11 17:25:07.0516', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2e4f20e0f41b4b8f91312186e8e12152', '2017-01-12 10:14:43', '2017-01-12 10:14:43.0442', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2ed31a19a518469496ef1509b5df032b', '2017-01-11 11:44:49', '2017-01-11 11:44:48.0722', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2ef39c2fb5cd41279023454148ac73c8', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0913', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2f00787aa7ac4599a9b2e8afe61f419c', '2017-01-11 11:43:14', '2017-01-11 11:43:14.0423', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2f317c96f505428089414336166b16fd', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0125', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2f352ca833fe4ae984d4cc6b78f12ba8', '2017-01-11 15:52:03', '2017-01-11 15:52:03.0352', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2f48704d3c2d44a9aa110d31ecf72f8f', '2017-01-18 11:54:54', '2017-01-18 11:54:53.0645', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2f612f436ef34b08b7629f45f551c24f', '2017-01-10 15:22:52', '2017-01-10 15:22:51.0655', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2f7ac855add14edbabbd8ded3554527f', '2017-01-12 11:59:21', '2017-01-12 11:59:21.0471', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('2f7dec9e752643148982ec762e0183f5', '2017-01-18 14:50:20', '2017-01-18 14:50:20.0042', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('2fa63016a1c94e18b9881232d2f034b9', '2017-01-12 10:34:27', '2017-01-12 10:34:26.0890', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/ueditorConfig/init', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('30207ca4bf5e4182b777fddff5fa41cd', '2017-01-18 14:49:20', '2017-01-18 14:49:20.0437', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('307d58433284460687a4ea097b4ba16c', '2017-01-11 11:03:32', '2017-01-11 11:03:32.0210', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('30d4fb572f3f404c97bca84cacf37b15', '2017-01-18 14:27:14', '2017-01-18 14:27:13.0959', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('31317513dcbe4c8392c8e05128d28d0d', '2017-01-12 15:10:00', '2017-01-12 15:09:59.0969', null, 'admin', 'admin', '97494dcd-46fb-4fb4-bbb1-048237ed5810', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('314a11d54dd84d1fb89348fd6de9fc8b', '2017-01-12 15:10:01', '2017-01-12 15:10:01.0183', null, 'admin', 'admin', '97494dcd-46fb-4fb4-bbb1-048237ed5810', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('317cd3160502495293b7fe5fa568e80e', '2017-01-12 14:17:39', '2017-01-12 14:17:39.0229', null, 'admin', 'admin', '7b862a1e-d5b9-4509-8b02-dc28c5bd9f0b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('31f99de1044a421d906be4d093b7adaf', '2017-01-18 14:48:55', '2017-01-18 14:48:54.0519', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('32068c4d858947c9b9c6caa58ddb0ec8', '2017-01-12 14:46:13', '2017-01-12 14:46:13.0228', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('32369c59829a48fb89cdfa9792c452a8', '2017-01-11 11:05:44', '2017-01-11 11:05:43.0763', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('325b170c832f40bdbef6f62f07b958b5', '2017-01-10 16:36:03', '2017-01-10 16:36:03.0068', null, 'admin', 'admin', '1cf251f7-7664-4696-bee8-f4d6bfdb1747', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3333d39046e4486a8d190fcc2dd51769', '2017-01-18 12:09:32', '2017-01-18 12:09:32.0351', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('334ea0c293864e53a0f449caf678e87a', '2017-01-10 14:20:37', '2017-01-10 14:20:36.0780', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('33f2a692534e4d35a0458a6c186bbc78', '2017-01-18 11:20:02', '2017-01-18 11:20:02.0408', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('349c8b9f23b945fabe20012961d05b9a', '2017-01-18 14:49:54', '2017-01-18 14:49:54.0398', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('34d851577dc54a9eb02a848f1e623cfc', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0890', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('35bb4b7b3e1244dead5b7276869db77b', '2017-01-10 14:11:04', '2017-01-10 14:11:04.0295', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('364d04b57b0e42a4b9add47a2f81071a', '2017-01-18 14:15:21', '2017-01-18 14:15:20.0669', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3679bcd53d974a96b067d60cef0222c2', '2017-01-10 15:38:08', '2017-01-10 15:38:07.0640', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('36961d19f29d4f5aa2d587d20a498e49', '2017-01-18 14:49:36', '2017-01-18 14:49:36.0259', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('36a3e396415344468250a27d8576f9f7', '2017-01-18 14:50:13', '2017-01-18 14:50:12.0855', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('36b4b1e7afbf49a1a555bf01d9b5f20a', '2017-01-11 16:42:47', '2017-01-11 16:42:47.0383', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('37779de0a9ff48feb4461de109c53e8f', '2017-01-11 11:04:45', '2017-01-11 11:04:44.0869', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('37ec7b16700a474894164e137335b03b', '2017-01-11 09:55:20', '2017-01-11 09:55:19.0959', null, 'admin', 'admin', '2c888c7f-55ba-4b57-855a-d14224d9fa1b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('381c213077ee46d29f7331d75e658b12', '2017-01-11 09:59:27', '2017-01-11 09:59:26.0687', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/look/json', '[查看角色]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('38264c8f2d1a4f8f961d4f17d36312cb', '2017-01-12 18:46:26', '2017-01-12 18:46:25.0585', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3870e499184944c7bc0c05e1328d5e52', '2017-01-10 14:10:57', '2017-01-10 14:10:57.0135', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('388d161970074a65956df9f0eb96d380', '2017-01-12 10:15:58', '2017-01-12 10:15:58.0468', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/themes/default/css/ueditor.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('38a017c0b9c347eaab8a1f3ed4079133', '2017-01-18 14:29:46', '2017-01-18 14:29:45.0643', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('392c27e4b62941fdabd92bfba70aa1b5', '2017-01-12 11:40:57', '2017-01-12 11:40:56.0580', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('395a964e374047e7a25f0aa13892460c', '2017-01-18 11:53:38', '2017-01-18 11:53:37.0721', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3965bef086714e0c91b6cc5be482b847', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0494', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('399269b2dfda4082891e9a90bbda5f39', '2017-01-10 10:44:39', '2017-01-10 10:44:38.0526', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3a43d6b2b7124262a4e4630672de0b52', '2017-01-10 14:24:14', '2017-01-10 14:24:13.0730', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3b27cfbdec414c83b8268bf89551a998', '2017-01-10 14:21:59', '2017-01-10 14:21:59.0052', null, 'admin', 'admin', 'c59f18f4-412c-48e4-bcff-3880fca966c4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3b34e815c87d4f3c933120d96d738134', '2017-01-12 13:45:08', '2017-01-12 13:45:08.0336', null, 'admin', 'admin', '1f47dfeb-28d9-4eb1-8e46-d06b1c1668ed', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3b427731d91d4d8f94b60e106bb5ac50', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0755', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3bc5e183b1da45dc9d35a36400007c4b', '2017-01-18 12:09:22', '2017-01-18 12:09:22.0251', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3c1847e831f14333bd145af6601360f4', '2017-01-12 10:37:52', '2017-01-12 10:37:52.0494', null, 'admin', 'admin', '2ec1cc3e-4092-4a3f-baf8-34234e8b3d65', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3c2478419af74ba0a73206bf83ef0d33', '2017-01-11 11:05:57', '2017-01-11 11:05:56.0528', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3c97c49fe1594aefa2a51fa91ef60c94', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0817', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3d028cf22b9647369e7a131279959e95', '2017-01-11 17:30:36', '2017-01-11 17:30:35.0694', null, 'admin', 'admin', 'e1c888d2-cd21-41f1-b672-7656f69da531', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/delete', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3d7b64d023894fc99168eeab350d528a', '2017-01-11 11:49:05', '2017-01-11 11:49:04.0731', null, 'admin', 'admin', '1e3a60e6-cea9-4d83-b16c-a54bd9b97bf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3de1b80811604821899eda9fafdc7d9b', '2017-01-11 15:38:25', '2017-01-11 15:38:24.0678', null, 'admin', 'admin', '06e91c2c-cc56-4c15-b5c3-d903949e5626', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3e2fb545a31241608a564868ab9bf745', '2017-01-18 15:00:50', '2017-01-18 15:00:50.0492', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3e64f2225c374fee9f5a36ad5d16df4d', '2017-01-12 18:47:04', '2017-01-12 18:47:03.0765', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3e79da8f01ef4a6a844c0218998855db', '2017-01-11 13:35:09', '2017-01-11 13:35:09.0005', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3e8a74532d074ca984575298d20d2896', '2017-01-10 14:23:10', '2017-01-10 14:23:09.0949', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3f21e2f8687b4b61afc688ce807b25cc', '2017-01-11 11:05:57', '2017-01-11 11:05:56.0612', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3f542e8fa09c47faa5cf66967887d75e', '2017-01-11 09:59:39', '2017-01-11 09:59:38.0757', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('3f5787e7973847caa756ebe405d7df06', '2017-01-18 14:58:37', '2017-01-18 14:58:37.0190', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3f59b6c6b0ee4805b0f8339734e85cad', '2017-01-11 10:04:59', '2017-01-11 10:04:59.0181', null, 'admin', 'admin', '4b34858b-3c9d-4853-ad00-501a1d5057e4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3fc6b409e86d4a9cb079725820734c48', '2017-01-12 10:15:44', '2017-01-12 10:15:43.0758', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/lang/zh-cn/zh-cn.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('3fc80593d5fa4020908fe1ff6b301df7', '2017-01-10 14:22:01', '2017-01-10 14:22:00.0749', null, 'admin', 'admin', 'c59f18f4-412c-48e4-bcff-3880fca966c4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('400f9417f6084e939a49bb9344be7d4e', '2017-01-11 10:05:57', '2017-01-11 10:05:56.0725', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4090f11235a54506af25d049343e8c0d', '2017-01-10 14:20:53', '2017-01-10 14:20:52.0831', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('40a29336907d4eefa179a588a24169a8', '2017-01-18 11:20:08', '2017-01-18 11:20:07.0846', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('40a78f7b15734f40b542403b9eea9191', '2017-01-12 14:04:37', '2017-01-12 14:04:37.0301', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('40c43fdaec844f8b8c35c1112b29ceb6', '2017-01-11 14:45:24', '2017-01-11 14:45:23.0988', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4181b4f2a71d4a06b9ac529e180f072f', '2017-01-10 14:19:00', '2017-01-10 14:18:59.0578', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('41df9beba3f84c6c99cf74b583ac65c6', '2017-01-18 14:49:38', '2017-01-18 14:49:38.0472', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('423792979b4d4830a1de3123b63f9733', '2017-01-18 11:21:04', '2017-01-18 11:21:03.0753', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4239950eb689481b9c2fad4652b6a23c', '2017-01-18 15:00:31', '2017-01-18 15:00:30.0762', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4254c744a4ed4bdbaf0c1e24a7379ee6', '2017-01-18 11:37:17', '2017-01-18 11:37:16.0814', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('428668ce00d848fc88dbfd95b48849d6', '2017-01-18 14:50:15', '2017-01-18 14:50:15.0059', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('42b4cf2a7ceb45a08ee5d464fb2c587e', '2017-01-12 12:02:11', '2017-01-12 12:02:11.0476', null, 'admin', 'admin', '4c211f44-2df8-4eab-b4a5-05e1d33324cf', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('42d6bc4f7dbd4157924c4747505664c6', '2017-01-10 15:36:30', '2017-01-10 15:36:30.0230', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('436ac4e756944f83a7c0264e3e799d9a', '2017-01-11 17:30:31', '2017-01-11 17:30:31.0340', null, 'admin', 'admin', 'e1c888d2-cd21-41f1-b672-7656f69da531', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('43da87af3a7f4af7bb414aace6c31e2d', '2017-01-12 19:04:09', '2017-01-12 19:04:08.0599', null, 'admin', 'admin', '0d5af653-2bfd-49a7-91a0-15d8c1b668bc', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('43efe31a089242718e6bf5468cd1f348', '2017-01-12 19:05:26', '2017-01-12 19:05:26.0460', null, 'admin', 'admin', '0d5af653-2bfd-49a7-91a0-15d8c1b668bc', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4407c8ad85954d73b02086cd74debb2d', '2017-01-18 15:22:44', '2017-01-18 15:22:44.0130', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('44702ed49e684e7a813fefefd547cac2', '2017-01-11 10:26:52', '2017-01-11 10:26:52.0497', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('44857115768740aaa977a78eb2bd567a', '2017-01-12 19:22:12', '2017-01-12 19:22:11.0724', null, 'admin', 'admin', '1d1e55f9-081c-47cb-aff2-50375a9d2d63', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('44b04af9802246c0b8df66f525a07276', '2017-01-12 14:39:51', '2017-01-12 14:39:51.0325', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('44b13b1f1b674307a2bcb0a20f56c3a9', '2017-01-12 14:04:38', '2017-01-12 14:04:37.0669', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('453f0886cfb348e49cf714afb2e871eb', '2017-01-18 11:55:09', '2017-01-18 11:55:08.0715', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('45b0ed4aafaf4718a6a7d2062b4fb26d', '2017-01-11 15:38:04', '2017-01-11 15:38:04.0328', null, 'admin', 'admin', '06e91c2c-cc56-4c15-b5c3-d903949e5626', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4645fd1c563841aeb05d250b5b82093b', '2017-01-12 18:45:35', '2017-01-12 18:45:35.0413', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthemes/iframe.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('464b9cda30c54cc49063e00c5267e93e', '2017-01-12 18:46:05', '2017-01-12 18:46:04.0658', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthemes/default/css/ueditor.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('466243567813478fa443bf4cab94e5a3', '2017-01-11 10:36:34', '2017-01-11 10:36:33.0903', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('466b7f6ee77b498d92703f3dbabc899f', '2017-01-11 11:04:49', '2017-01-11 11:04:49.0173', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4674e93a1ba74ea78bff7092a72bbca3', '2017-01-12 10:14:38', '2017-01-12 10:14:38.0368', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('46891bb697be4f7f94d522bcc6c4eb55', '2017-01-18 12:12:00', '2017-01-18 12:12:00.0409', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('46a19d5b6f0742f8a6cf944e2fc4e2e7', '2017-01-11 16:42:26', '2017-01-11 16:42:25.0713', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/look/json', '[查看角色]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('46c5808a2abf476fa26eb7d8e7a1c441', '2017-01-18 14:27:11', '2017-01-18 14:27:10.0704', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('46eaffd811894807abccabd0603d8376', '2017-01-12 18:53:53', '2017-01-12 18:53:53.0327', null, 'admin', 'admin', '1534998f-ddbc-4cf0-8dc5-d640539678ad', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('470cf13f1e3f4cc681039eddb53c0722', '2017-01-12 15:10:02', '2017-01-12 15:10:01.0792', null, 'admin', 'admin', '97494dcd-46fb-4fb4-bbb1-048237ed5810', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('471e682963f1480a831950e679480e58', '2017-01-11 16:42:27', '2017-01-11 16:42:27.0254', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('47603d0ce84e438c86fc4d442c55c7f6', '2017-01-11 11:03:18', '2017-01-11 11:03:17.0670', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('47747f5f8bcc47f38e63efbe7cc1a175', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0337', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('47f0d9ee0375497ea29622ec496ebd9e', '2017-01-11 09:59:51', '2017-01-11 09:59:51.0459', null, 'admin', 'admin', '87a05838-310a-4bfd-b127-cc0b388faf3f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4807e6c328a046aa88f53bd418c02dda', '2017-01-11 11:44:37', '2017-01-11 11:44:36.0665', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('484a58fbe7154a33bd35f3941b714f41', '2017-01-18 14:27:20', '2017-01-18 14:27:20.0350', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('48544ddd11a34173bf2d9bd2caab6a95', '2017-01-10 14:59:41', '2017-01-10 14:59:41.0261', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('486b431c23b84abca8635ab47cd1aea2', '2017-01-12 13:52:57', '2017-01-12 13:52:57.0490', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('487d23eeebb046b1a4e5104e14058a96', '2017-01-10 14:25:28', '2017-01-10 14:25:28.0357', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('48da557699744d94a0aa2a586ee4ca5c', '2017-01-11 16:56:05', '2017-01-11 16:56:04.0988', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('497e93f33fd54ae7b36e595284a770a7', '2017-01-10 15:26:28', '2017-01-10 15:26:28.0312', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('49b196bbc12845dea898224053b650af', '2017-01-10 14:19:00', '2017-01-10 14:19:00.0036', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('49b6b53e294546f7acad8737c74ebfc3', '2017-01-12 14:53:40', '2017-01-12 14:53:40.0382', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('49cf4d770d6b4ee08a94587038e62d3a', '2017-01-12 14:39:04', '2017-01-12 14:39:03.0950', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4a101183065f4b86b601faa64d4ab057', '2017-01-12 13:55:18', '2017-01-12 13:55:18.0138', null, 'admin', 'admin', '88d414ed-58dd-48b2-9ba3-177907b0da1c', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4a1e2a0ae96447ecbb6bf6e8a7e844ce', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0724', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4a1e34c548374a09b96f601098a6c790', '2017-01-18 15:20:58', '2017-01-18 15:20:57.0620', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4a2ac3716f8c4adf906bc9d7e805296d', '2017-01-12 10:40:07', '2017-01-12 10:40:06.0639', null, 'admin', 'admin', '2ec1cc3e-4092-4a3f-baf8-34234e8b3d65', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4a4c3ca3786f48b5b86b76a3ccdb6a83', '2017-01-18 12:09:22', '2017-01-18 12:09:22.0153', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4a9cfac395b2436fb9ed77649cbc028d', '2017-01-11 10:35:52', '2017-01-11 10:35:51.0624', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4ab871ab22584a32a489af5ea7eb13aa', '2017-01-12 10:35:14', '2017-01-12 10:35:14.0312', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4b1f47c8d799463e8f4bc31be42e193f', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0825', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4b2e0033700743e5933d53c60e24900e', '2017-01-18 11:37:13', '2017-01-18 11:37:13.0303', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4b50e82a6a6543febb9ae58e82349167', '2017-01-18 14:16:05', '2017-01-18 14:16:04.0829', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4bc91b2a552d4de4be0eb91c5763a4b2', '2017-01-12 15:19:24', '2017-01-12 15:19:24.0121', null, 'admin', 'admin', '1043ccac-d9e8-493f-8d39-6504147fe8e8', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4bdc9b1c23c34a4291ae55619045f14e', '2017-01-10 14:20:52', '2017-01-10 14:20:51.0928', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4c090875b3f74f888bb3cc859a3460fd', '2017-01-11 13:38:38', '2017-01-11 13:38:37.0592', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4c2776d8c3044858a32c86b5a03e3cd7', '2017-01-18 14:29:45', '2017-01-18 14:29:45.0084', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4c35244722d64c748a1b6e29515c7a60', '2017-01-18 14:58:30', '2017-01-18 14:58:30.0088', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4c4e831dfa754e16ae45eb4441090739', '2017-01-10 15:38:11', '2017-01-10 15:38:10.0659', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4c5900046ddc4473b324f90bd301e27d', '2017-01-18 14:10:29', '2017-01-18 14:10:29.0060', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4c7ef537601d4a93b98ca7ec54d54a18', '2017-01-12 12:17:59', '2017-01-12 12:17:59.0307', null, 'admin', 'admin', 'ee4ffd11-e144-43ff-8c0f-f4cf892e3778', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4cbb7a70a44d42e09fa7eb56e8eb7840', '2017-01-18 14:37:52', '2017-01-18 14:37:52.0096', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4cd38c86539348c89246e510c5eae4b7', '2017-01-11 10:57:46', '2017-01-11 10:57:46.0237', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4d769799b636410ba8041790cd8a9822', '2017-01-18 14:48:57', '2017-01-18 14:48:56.0635', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4df83a37d708417f93f62f4c9d394ba8', '2017-01-12 19:33:44', '2017-01-12 19:33:43.0753', null, 'admin', 'admin', 'faf1f11c-da15-4cd6-b13a-582ac829aeb5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4e03d1edce824e45b2f37c8ddedd100b', '2017-01-12 12:26:28', '2017-01-12 12:26:27.0657', null, 'admin', 'admin', '4dde7d85-af78-4ef5-8d44-95049547d30a', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4e635b15c0b644489f7c3cb8ce3c9843', '2017-01-18 14:09:42', '2017-01-18 14:09:41.0951', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4f0b7efe72474c94ad88df2294348726', '2017-01-18 14:49:46', '2017-01-18 14:49:46.0313', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4f23d54d397643c3968e045a20075c34', '2017-01-10 14:51:25', '2017-01-10 14:51:25.0370', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4f2515a0f87a4b338d15a6c4a9527a48', '2017-01-10 14:18:55', '2017-01-10 14:18:54.0748', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4f7503e58d3242158081a4f3bbcb807d', '2017-01-18 14:27:35', '2017-01-18 14:27:34.0772', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('4f8435e7e72542e29302c7e6e5dec6fc', '2017-01-18 14:50:23', '2017-01-18 14:50:23.0264', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4fe1bc85112749a3853934bcee4c22e4', '2017-01-18 15:00:32', '2017-01-18 15:00:32.0138', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('4ff65c01f4c8401abfe2db2ce001ac58', '2017-01-18 14:58:30', '2017-01-18 14:58:30.0445', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('505716b8ab88425fa0b91521d85b4362', '2017-01-18 14:58:36', '2017-01-18 14:58:36.0305', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('507da05b9f1044cc84a0794866fd4380', '2017-01-12 13:55:16', '2017-01-12 13:55:15.0849', null, 'admin', 'admin', '88d414ed-58dd-48b2-9ba3-177907b0da1c', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('515c53abbb904959a627642f07d8be00', '2017-01-12 14:45:32', '2017-01-12 14:45:31.0690', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('516423481d714129a4b80eb0dee7a7e6', '2017-01-12 14:00:49', '2017-01-12 14:00:49.0421', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('51719fa9e31e41baa4d056bc8470424a', '2017-01-12 19:34:26', '2017-01-12 19:34:26.0068', null, 'admin', 'admin', '39523b9f-57d7-4ebb-932c-9e15a7ab07cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('51e8c5871f544bb2b782972c7b3da79e', '2017-01-12 10:14:52', '2017-01-12 10:14:51.0848', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5206e1dcd5bf4da299b014ee7b21f5bf', '2017-01-18 15:22:50', '2017-01-18 15:22:50.0127', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('520beae94132460bb381354162b02b27', '2017-01-11 17:30:38', '2017-01-11 17:30:38.0038', null, 'admin', 'admin', 'e1c888d2-cd21-41f1-b672-7656f69da531', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('52adfce5e97f4fff9987e527b3c64c79', '2017-01-10 10:44:36', '2017-01-10 10:44:36.0103', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/grade/list', '[级别管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('52e2e7c2d17049739db566b46ebf8bc6', '2017-01-18 11:38:06', '2017-01-18 11:38:05.0900', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('52f3441598304180a3bcf1bcd06085d5', '2017-01-12 13:52:38', '2017-01-12 13:52:38.0294', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5300c7ca8c484b27ac5f398e12760462', '2017-01-18 14:49:23', '2017-01-18 14:49:23.0108', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('532e1701a70a45afa94cef323f2f35f5', '2017-01-12 12:04:19', '2017-01-12 12:04:18.0754', null, 'admin', 'admin', '4c211f44-2df8-4eab-b4a5-05e1d33324cf', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('533e3278e42942c88cd9eedefa66d634', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0486', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5396f3651d854781972c898420b1f312', '2017-01-18 15:17:02', '2017-01-18 15:17:02.0267', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('53bb0bf4a9fa49c7ab526c30b57e4c96', '2017-01-11 10:30:34', '2017-01-11 10:30:34.0055', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('53e9601db2c24bf6aec35815099a5364', '2017-01-18 14:58:31', '2017-01-18 14:58:30.0799', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('53eeedc6e479445ebbc9369ccdd60259', '2017-01-18 14:57:45', '2017-01-18 14:57:44.0648', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('541e96e916ff4e88ab94988f9ee111a2', '2017-01-12 15:08:32', '2017-01-12 15:08:31.0991', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('544051f4aab64cb188f28b7baef48807', '2017-01-10 14:23:01', '2017-01-10 14:23:00.0732', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('54ae7843bf1549c59b1ed618f8646bf4', '2017-01-18 14:50:41', '2017-01-18 14:50:41.0494', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('551a6f01d4cd4583a344042b9017bda7', '2017-01-11 11:44:49', '2017-01-11 11:44:49.0489', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('556dbe10722f4934a71164f3429a1195', '2017-01-18 11:17:57', '2017-01-18 11:17:56.0567', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('55aefe2c7ec24f368c057e7c6699738a', '2017-01-10 14:26:59', '2017-01-10 14:26:58.0943', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('56a6ce6f937a42869417ad65cfbace4a', '2017-01-11 13:39:36', '2017-01-11 13:39:35.0837', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('56cd5623ac8e4937aac824dcac82003f', '2017-01-12 14:41:31', '2017-01-12 14:41:30.0681', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('570e9614777d40c090bcee33de912dc5', '2017-01-11 16:23:33', '2017-01-11 16:23:32.0987', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5744353e829849b2aa3d89871d27d933', '2017-01-18 14:50:14', '2017-01-18 14:50:13.0893', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('57a0db36978f4e60bf6918cc850b37fb', '2017-01-18 14:38:51', '2017-01-18 14:38:51.0150', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('57fd144060b7486e887c16f17c6120fe', '2017-01-18 14:58:25', '2017-01-18 14:58:25.0332', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5873452d3458487cbbd646f3eb9319f1', '2017-01-12 12:25:46', '2017-01-12 12:25:45.0935', null, 'admin', 'admin', '4dde7d85-af78-4ef5-8d44-95049547d30a', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('58c745fcd407446d9381e8e9acf856c4', '2017-01-12 10:33:29', '2017-01-12 10:33:29.0012', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('590d48d55a304ff4be6b643bfcfe6d1d', '2017-01-18 14:50:08', '2017-01-18 14:50:07.0790', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('59a485e71af64bba8f62683d72dea728', '2017-01-10 14:21:11', '2017-01-10 14:21:10.0756', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('59d5c614045e46c48dbf1e089cf511d5', '2017-01-18 14:49:39', '2017-01-18 14:49:38.0688', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5a06a584320a421ea24e79645ce3f5a0', '2017-01-12 10:14:42', '2017-01-12 10:14:42.0157', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5a85b8ed61a94ceb878f140e432af8e4', '2017-01-10 14:18:59', '2017-01-10 14:18:58.0767', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5ae8a4bb949c42f2a16d27ad30faabd6', '2017-01-11 15:53:56', '2017-01-11 15:53:56.0364', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5af174e4f12144d4875cf81c55420cdd', '2017-01-18 14:10:27', '2017-01-18 14:10:26.0886', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5c1f98ff2e2945a481098790c85adbde', '2017-01-12 14:41:30', '2017-01-12 14:41:30.0432', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5c30fb1939854d4eb4716c883ff3c00b', '2017-01-12 18:43:50', '2017-01-12 18:43:49.0792', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5c5bd2ead30b4b7ab3b0a76a59fd7374', '2017-01-12 10:14:52', '2017-01-12 10:14:52.0344', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5c5e778a98e9493995a729212d6f2831', '2017-01-12 13:53:39', '2017-01-12 13:53:39.0148', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5cac8901dcdc4b0bae5ce4cf77e2008d', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0396', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5cc79a811a9040c4bf9e5ca60b0b4ddf', '2017-01-11 13:39:42', '2017-01-11 13:39:41.0688', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5cca0dbc339d4be9bb3bc652285e1e9e', '2017-01-11 09:44:26', '2017-01-11 09:44:25.0906', null, 'admin', 'admin', '2c888c7f-55ba-4b57-855a-d14224d9fa1b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5cf375b74050464c81f8bb980e516e44', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0517', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5d3e75f4325d4a7b95ed62cd2b7b1ec7', '2017-01-11 10:44:18', '2017-01-11 10:44:17.0738', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5e9a992539874017971867c9d40398a4', '2017-01-12 15:28:36', '2017-01-12 15:28:35.0913', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5ebdf1e6b75647f4be4cd8720319a78c', '2017-01-18 11:38:16', '2017-01-18 11:38:16.0197', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5efc2f19e96547f7b4d1f46234f81704', '2017-01-12 18:46:05', '2017-01-12 18:46:05.0290', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthemes/iframe.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5f3af82aaa2744e180d9057981142f46', '2017-01-12 12:26:39', '2017-01-12 12:26:39.0024', null, 'admin', 'admin', '4dde7d85-af78-4ef5-8d44-95049547d30a', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5f50334b6f4f4e3aa5439ef4de1b6ac3', '2017-01-18 14:58:06', '2017-01-18 14:58:05.0792', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5f610ca916ab47a0a6d68fd6dfa3019b', '2017-01-11 16:41:42', '2017-01-11 16:41:42.0194', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/update', '[修改菜单]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('5f668f842bde4710b24ab3ece64e55e7', '2017-01-18 14:49:57', '2017-01-18 14:49:57.0287', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5f885ab8926744c78e3b51719256239e', '2017-01-17 10:43:30', '2017-01-17 10:43:29.0761', null, 'admin', 'admin', '6e171999-ca41-4f8c-a498-a318b6174528', '127.0.0.1', 'http://localhost:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5fe45f4894364b4dbbc727b9f523ea29', '2017-01-12 15:19:25', '2017-01-12 15:19:24.0898', null, 'admin', 'admin', '1043ccac-d9e8-493f-8d39-6504147fe8e8', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('5fec9069f2ee43739fc77fa01dbf3251', '2017-01-18 14:58:31', '2017-01-18 14:58:30.0660', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6011635f7943430d97420d9316f7515d', '2017-01-10 10:44:38', '2017-01-10 10:44:38.0331', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6039f901442c4984b37cdae52fb6865b', '2017-01-18 11:53:52', '2017-01-18 11:53:52.0473', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('607928157be14d6f839b1f8a26c35d9a', '2017-01-10 14:25:22', '2017-01-10 14:25:22.0204', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('60c838397f9a43f690d8505904f5a299', '2017-01-12 10:14:58', '2017-01-12 10:14:57.0773', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('60d650961b3a4a2eb7e3aef919eedcd2', '2017-01-12 10:33:31', '2017-01-12 10:33:31.0298', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/lang/zh-cn/zh-cn.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6111002c2dcf4e6aae8decdd23d88b7d', '2017-01-12 15:25:33', '2017-01-12 15:25:32.0573', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('611a1d075ca940f8a9ad3319f17542f3', '2017-01-12 13:52:41', '2017-01-12 13:52:41.0499', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('61291988cf9e44ad97a0aba75eae0bd3', '2017-01-12 14:38:05', '2017-01-12 14:38:04.0546', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('61663b44249544bcab7ac6d39ca7a7a4', '2017-01-13 10:21:47', '2017-01-13 10:21:46.0786', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('61b9de1a32564b6881dd8f088c3d96ed', '2017-01-12 14:41:17', '2017-01-12 14:41:16.0718', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6202e817e2f7401ca6be24d116889ad3', '2017-01-18 14:50:15', '2017-01-18 14:50:15.0259', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6236eb033d904d7bac25909b70b62fe2', '2017-01-12 18:34:59', '2017-01-12 18:34:58.0964', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('625406ed2c814a2c867f35a40f134830', '2017-01-10 15:37:30', '2017-01-10 15:37:30.0430', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('626d19b38d9d479787419f9bae984426', '2017-01-12 15:06:32', '2017-01-12 15:06:31.0565', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('62ef042c001b47b18d869b3998050385', '2017-01-18 14:09:45', '2017-01-18 14:09:44.0581', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('63390c747a6c4a11a2806e0fe6ef48b8', '2017-01-10 14:22:00', '2017-01-10 14:22:00.0317', null, 'admin', 'admin', 'c59f18f4-412c-48e4-bcff-3880fca966c4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('633c8724bb6847b1b2543505f8c9b673', '2017-01-12 14:43:47', '2017-01-12 14:43:47.0032', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('634c321168994d358e6cd98516c62588', '2017-01-18 14:49:39', '2017-01-18 14:49:39.0451', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6351914a84a64b5f8dae2274f708f86d', '2017-01-12 13:45:17', '2017-01-12 13:45:17.0139', null, 'admin', 'admin', '1f47dfeb-28d9-4eb1-8e46-d06b1c1668ed', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6388ff46abd14ad49a5ea7b7f61d3edf', '2017-01-12 14:12:19', '2017-01-12 14:12:18.0807', null, 'admin', 'admin', 'e071116e-b364-4a51-a488-64f340ff3599', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('640839a6d4fb4ed58c060e9be89fce9a', '2017-01-11 10:57:04', '2017-01-11 10:57:04.0496', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('646c458d7e6543b2a94f1e26fc5ab0bc', '2017-01-12 19:30:23', '2017-01-12 19:30:22.0642', null, 'admin', 'admin', '27778b74-5bc9-449b-9e29-fca6a177cccd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('64b2f292d7e74c4c8f090cdfa67dc2d3', '2017-01-12 10:15:27', '2017-01-12 10:15:26.0520', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('650db018824c473680b6b35cb0e73caa', '2017-01-11 17:24:58', '2017-01-11 17:24:57.0740', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('65200da8a82649a6b6fb9d9dd0722098', '2017-01-18 14:27:10', '2017-01-18 14:27:10.0480', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6531cb65f26c4528a22241c64d7ef5e4', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0054', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('65481d7b721942d78716344c5c244c28', '2017-01-18 14:09:21', '2017-01-18 14:09:21.0449', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('657ef70054b2409787af31b519a7c4af', '2017-01-18 14:50:35', '2017-01-18 14:50:35.0095', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('65f9a5d916914e39af9cfe90ceb4310b', '2017-01-12 19:34:28', '2017-01-12 19:34:28.0339', null, 'admin', 'admin', '39523b9f-57d7-4ebb-932c-9e15a7ab07cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('666af8d6cdd3477f8325ef833e002eec', '2017-01-18 15:22:44', '2017-01-18 15:22:43.0808', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('66744042e21648b38b200d519e1e4d6f', '2017-01-18 14:13:25', '2017-01-18 14:13:24.0766', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('669d711f5e4341a9962aa853ac86616e', '2017-01-12 11:36:51', '2017-01-12 11:36:51.0189', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('66beaf2af9e44a7884fa47a6342e8631', '2017-01-11 11:44:39', '2017-01-11 11:44:38.0945', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('66ccd6e98f424c8d9083b90accd4ffbc', '2017-01-18 11:38:54', '2017-01-18 11:38:54.0102', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('671915d27bfb42bdbfefe1fd1b88beb1', '2017-01-12 10:14:50', '2017-01-12 10:14:49.0601', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('672d4aac47c4448a9613f5dfdcab5b74', '2017-01-18 14:57:54', '2017-01-18 14:57:54.0282', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('673c1bae53d94b25a81aeea7b340bcfb', '2017-01-18 14:17:22', '2017-01-18 14:17:21.0796', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('674b4c6807604cf1bcbd1688f855343f', '2017-01-18 11:56:33', '2017-01-18 11:56:33.0324', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6789a4f1a6bb4e6294b120948747ac40', '2017-01-18 11:59:50', '2017-01-18 11:59:50.0465', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('67993b5b143346c6ad0b378624a51694', '2017-01-18 14:48:54', '2017-01-18 14:48:54.0416', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('67d520a99d094679a10dac73d615abea', '2017-01-18 12:20:12', '2017-01-18 12:20:12.0275', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('680d7f2870414aaeaddec1f17f739ab8', '2017-01-12 18:45:12', '2017-01-12 18:45:11.0510', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/themes/default/css/ueditor.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('68b211a1a2f9465197948c33aa48b8b9', '2017-01-12 19:01:18', '2017-01-12 19:01:17.0998', null, 'admin', 'admin', '0d5af653-2bfd-49a7-91a0-15d8c1b668bc', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('68ebf1deade440449373dc82c0ad26e3', '2017-01-18 11:55:02', '2017-01-18 11:55:01.0795', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('68f81e32681b4ba89b1d27153b34f16e', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0203', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('69ca701e3a2c4a01b0c1f828b549570a', '2017-01-11 09:59:38', '2017-01-11 09:59:38.0304', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('69d418cf0167446d8e3e688d864c2799', '2017-01-10 15:30:09', '2017-01-10 15:30:08.0847', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('69f6a0e5ac8441e8adbf05d3281c1fa6', '2017-01-12 14:09:01', '2017-01-12 14:09:01.0063', null, 'admin', 'admin', '83e929e2-918d-479f-821b-bf7e3d995c50', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6a0451bd00b54a9c990a16641a1416d2', '2017-01-18 15:22:42', '2017-01-18 15:22:42.0210', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6a0509ac2fa84076a790581dc6be623e', '2017-01-12 18:51:01', '2017-01-12 18:51:00.0659', null, 'admin', 'admin', 'bf865796-14f7-427b-91c9-2c97e9ad0fb2', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6b2987eb93254f459f8440d5d07d780a', '2017-01-10 14:24:39', '2017-01-10 14:24:38.0799', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6b63d6f5a32e405b9e413af676134a1a', '2017-01-18 14:50:07', '2017-01-18 14:50:06.0806', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6b90e98922a949bab398a8a2e49475ea', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0352', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6b91bfdd20814206b29b95629cfa6c90', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0439', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6bcacfdc62c8433f9d80f097d022cf7b', '2017-01-12 12:34:56', '2017-01-12 12:34:55.0895', null, 'admin', 'admin', 'b75ce246-bc93-4c34-b53a-b69c5906e5d7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6cab028e6ab540d0a61ed72db1a849a1', '2017-01-12 14:03:42', '2017-01-12 14:03:42.0175', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6cda6d61502e4d4ca44199eac7ec4a81', '2017-01-10 10:44:44', '2017-01-10 10:44:43.0701', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/grade/list', '[级别管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6cdf53b17c5044f5a40a105b9742a716', '2017-01-18 11:06:13', '2017-01-18 11:06:12.0775', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6d47d2bfbab64eb8bce2fd5bc55cdc58', '2017-01-18 14:49:26', '2017-01-18 14:49:26.0173', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6d7f1bec7a914f7b857bef6e6d3270ba', '2017-01-18 11:53:49', '2017-01-18 11:53:49.0186', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6dc45c2f40cf495987588d96bcf5d09f', '2017-01-12 19:27:25', '2017-01-12 19:27:24.0576', null, 'admin', 'admin', '4acfe4e8-3242-414f-be7b-ca1c6176668b', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6dd21ead12fb4ad6a99cff98ccc143e3', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0321', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6e1beb2316514c9f94dbdca2a37d7a45', '2017-01-11 11:44:38', '2017-01-11 11:44:38.0035', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/look/json', '[查看角色]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6e3adcadc8b24dd0ba6b36fb828b47f4', '2017-01-12 10:15:39', '2017-01-12 10:15:38.0591', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6e71c05da9544b3e9f2b3c2f00a77c2d', '2017-01-12 14:45:32', '2017-01-12 14:45:31.0828', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6e71cc44cfc348bf9ce10e7fe7d357b9', '2017-01-12 18:45:31', '2017-01-12 18:45:30.0647', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6e88e197ae404384af04d91c2dc24491', '2017-01-18 14:10:14', '2017-01-18 14:10:14.0245', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6e99ae404ee742b6ad7ee4aa74ea8cfb', '2017-01-18 15:22:45', '2017-01-18 15:22:44.0951', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6eaa14403327449bab2142708c428421', '2017-01-11 11:49:07', '2017-01-11 11:49:06.0865', null, 'admin', 'admin', '1e3a60e6-cea9-4d83-b16c-a54bd9b97bf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('6eeae405419147229fb83afe22d1a2e5', '2017-01-12 10:33:31', '2017-01-12 10:33:31.0298', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/themes/default/css/ueditor.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('6f86219c797745ffbc2db0418869fa5f', '2017-01-18 12:09:40', '2017-01-18 12:09:40.0123', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7038048d1a4c4cae8ea84c01105c0dae', '2017-01-11 10:35:17', '2017-01-11 10:35:16.0615', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7074e0f0b58b43f19f5d2ebfdcce9944', '2017-01-18 14:39:48', '2017-01-18 14:39:47.0732', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('708df031401840b0b4e5525753947045', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0858', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('70bcbdc8e4b445e6909c9bb34e41c771', '2017-01-18 14:50:23', '2017-01-18 14:50:23.0452', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('719b15f57653423da1a069b548aad3d6', '2017-01-12 18:43:58', '2017-01-12 18:43:57.0543', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('71bb3ee646d14b9e9c9c1927a60fd4b1', '2017-01-18 14:50:31', '2017-01-18 14:50:31.0006', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('71dd69b273af409ab13e200ccbf10cbb', '2017-01-12 18:47:15', '2017-01-12 18:47:14.0579', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7204048852a6453ca59aa3d8500aad35', '2017-01-10 14:23:10', '2017-01-10 14:23:09.0506', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('720b9d21d94141a3848089989c1e6bb8', '2017-01-12 18:45:35', '2017-01-12 18:45:34.0562', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthemes/default/css/ueditor.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('721490413653471782b074a2910bca67', '2017-01-11 14:36:47', '2017-01-11 14:36:47.0064', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('722bc756d375413e9a4b0cb1bacd23bd', '2017-01-18 12:20:07', '2017-01-18 12:20:07.0097', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('729e48298fec436091fb3260962ed198', '2017-01-18 14:09:42', '2017-01-18 14:09:42.0282', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('72b32dab01ec41d18a5415399b195502', '2017-01-11 17:27:32', '2017-01-11 17:27:32.0372', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7321f5dbff9b4833ab5aa78aae74b16d', '2017-01-11 16:24:32', '2017-01-11 16:24:31.0919', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7326b41711af425b93305ad9c1b3d61d', '2017-01-18 14:30:07', '2017-01-18 14:30:07.0231', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('73691114e0b14204977464d9014a04ce', '2017-01-12 14:37:24', '2017-01-12 14:37:23.0583', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('73886c3535064b8e8b14888d5108d1c7', '2017-01-11 11:04:43', '2017-01-11 11:04:42.0842', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('73b4039a0ab246f7b6845ae0272edd91', '2017-01-18 11:56:34', '2017-01-18 11:56:33.0660', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('73c3a6f8c3ba4f61bd181b102b9b998f', '2017-01-18 14:48:59', '2017-01-18 14:48:58.0793', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('73cd91978eba4762be74c5183a06d824', '2017-01-18 14:57:52', '2017-01-18 14:57:51.0999', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7423d7b534c346949c6abc0e52a37364', '2017-01-12 15:09:58', '2017-01-12 15:09:58.0272', null, 'admin', 'admin', '97494dcd-46fb-4fb4-bbb1-048237ed5810', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('742b1d399ab34ba095c92a1290f17091', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0327', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('74b099232f184041b5fb54be3bc22477', '2017-01-11 14:37:16', '2017-01-11 14:37:16.0485', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('74bfac4ca5d9447281ba1b9818d0d398', '2017-01-13 10:22:02', '2017-01-13 10:22:01.0672', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('751d1266a9ec49658a81c5909fb3781a', '2017-01-18 14:50:14', '2017-01-18 14:50:14.0399', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('754aed4387714ebd94e50d1a0ab65c4c', '2017-01-12 19:30:26', '2017-01-12 19:30:26.0240', null, 'admin', 'admin', '27778b74-5bc9-449b-9e29-fca6a177cccd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('75d3f1576aa3464ea91a7c83633241eb', '2017-01-12 14:08:12', '2017-01-12 14:08:12.0094', null, 'admin', 'admin', '83e929e2-918d-479f-821b-bf7e3d995c50', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7625ae3a273a43c787a832bbfa66bff1', '2017-01-18 14:30:20', '2017-01-18 14:30:19.0983', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('769128c785194c2ca76ccfe8247b6061', '2017-01-12 14:37:28', '2017-01-12 14:37:28.0260', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('76af11ab16de4386816bc6a2ea8238c6', '2017-01-18 14:49:23', '2017-01-18 14:49:23.0390', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('76b97120b43540918f453f0100ad3566', '2017-01-12 18:41:26', '2017-01-12 18:41:26.0213', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('76dfe9810913497490540e77b05f74c6', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0789', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7702fc81ba744dd0b37baf1a597f86f0', '2017-01-18 14:57:54', '2017-01-18 14:57:53.0978', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('77157897253240b1a7ea17ce059aa4f5', '2017-01-10 14:20:39', '2017-01-10 14:20:39.0195', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7732930472614af185af4f1523b9724d', '2017-01-12 19:01:25', '2017-01-12 19:01:25.0241', null, 'admin', 'admin', '0d5af653-2bfd-49a7-91a0-15d8c1b668bc', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('773776042bce42dea6f0b3b5a3ccc087', '2017-01-10 14:20:40', '2017-01-10 14:20:40.0255', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('775c168a3fab44f3809b2d72632981b3', '2017-01-10 14:24:41', '2017-01-10 14:24:41.0191', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('77615b0b87fe426b826d4ecdd756e6a9', '2017-01-18 14:27:10', '2017-01-18 14:27:10.0330', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/', null, '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('77a39d372af94615aebf4826cfa2a12d', '2017-01-12 14:01:11', '2017-01-12 14:01:11.0390', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('77c0c8666928412d86ff3bdf44f1d847', '2017-01-10 15:29:40', '2017-01-10 15:29:40.0465', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('782273edf5104a2aa42dc28a499a99da', '2017-01-18 15:22:42', '2017-01-18 15:22:41.0974', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('78b1c922eeb74909b4f085248c171a2c', '2017-01-11 17:24:21', '2017-01-11 17:24:20.0651', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('78d932834a0b46d282582aa5d4ef5e71', '2017-01-18 14:50:15', '2017-01-18 14:50:14.0873', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('78ec837d08e44f53a51bcf57d624a8cb', '2017-01-18 11:19:40', '2017-01-18 11:19:39.0941', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('79209e0165f84b7d939176b53fe6119a', '2017-01-18 14:57:45', '2017-01-18 14:57:44.0783', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('79361d7e1e064c959a6a7f9961588dcb', '2017-01-10 14:21:02', '2017-01-10 14:21:01.0878', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('79512bd4608041b3a64f0f84dd41c4a1', '2017-01-18 11:20:05', '2017-01-18 11:20:04.0647', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('79538fbf06d04559a644196e3be1cb10', '2017-01-10 10:44:49', '2017-01-10 10:44:49.0390', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('799663c48975421bba8efed91730cfb0', '2017-01-12 15:29:20', '2017-01-12 15:29:19.0817', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('79beb456275e476ca34244932d242a1d', '2017-01-12 14:12:24', '2017-01-12 14:12:24.0305', null, 'admin', 'admin', 'e071116e-b364-4a51-a488-64f340ff3599', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('79e38f250897477e8fa22bb9d2b46473', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0293', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7a2b5718c08c4e8d98e73cc5bbc9ae1e', '2017-01-18 14:58:09', '2017-01-18 14:58:09.0031', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7a5743e6273a40f2bef97f8291837df3', '2017-01-18 14:49:20', '2017-01-18 14:49:20.0269', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7a6d44afe3814cea85ed6a9b0cb5f099', '2017-01-18 14:49:21', '2017-01-18 14:49:20.0712', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7a81840bf22443848d623b08f9d8539e', '2017-01-12 14:03:20', '2017-01-12 14:03:19.0964', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7a8af7b66cc344588aa6f68cd51b913e', '2017-01-12 15:06:34', '2017-01-12 15:06:33.0840', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7a9cf79ee4024cd3b6a5596187449b39', '2017-01-18 14:58:29', '2017-01-18 14:58:28.0635', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7ab19d69fc794fdf8fa29027c51997cd', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0421', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7acac631c5fa4c67ae7e56e0eaee5490', '2017-01-18 14:50:31', '2017-01-18 14:50:31.0258', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7af6f2a1d2c64405a106086bbfb9b912', '2017-01-18 15:17:50', '2017-01-18 15:17:50.0219', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7b1d57d17a404758a55adb59b8e8f214', '2017-01-11 11:05:42', '2017-01-11 11:05:41.0598', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7b319440db5141b9a2fe71b8eb547ea2', '2017-01-18 12:08:53', '2017-01-18 12:08:52.0697', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7b4cf5c612ff4dd283c5b71c16245344', '2017-01-10 14:20:56', '2017-01-10 14:20:55.0819', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7b62c931638f425c8ad4961a92dad38d', '2017-01-18 14:57:57', '2017-01-18 14:57:57.0173', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7b724871068e412d92afeedb3da87dad', '2017-01-18 14:27:34', '2017-01-18 14:27:34.0331', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7bbc1483ccc643c78582e80eb84eeff3', '2017-01-11 10:28:42', '2017-01-11 10:28:41.0776', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7c0f314b3fca4ec6b0598aa238da1067', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0365', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7c11f39a8c0a473696adbaf691167ad7', '2017-01-12 19:27:23', '2017-01-12 19:27:22.0724', null, 'admin', 'admin', '4acfe4e8-3242-414f-be7b-ca1c6176668b', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7d2de6e30d2b49cb9a702b2fd5bec3da', '2017-01-18 14:50:15', '2017-01-18 14:50:15.0320', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7d8f134dba044050975d98a6d8a592a3', '2017-01-11 15:00:36', '2017-01-11 15:00:36.0236', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7dbc36eb36214d7bb6a1f8382f41fa79', '2017-01-18 14:58:30', '2017-01-18 14:58:29.0612', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7dc9af45774d4251a12ff85401f68533', '2017-01-12 10:15:01', '2017-01-12 10:15:00.0860', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/role/list', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7dd2aaf6de5c44a8a6fae89a65da033a', '2017-01-18 14:39:52', '2017-01-18 14:39:51.0978', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7e22de1671994b189c1ee4b67d7d81c0', '2017-01-18 15:24:21', '2017-01-18 15:24:20.0827', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7e431ea52b544745b5f9ac88f8c4a37d', '2017-01-18 11:54:59', '2017-01-18 11:54:58.0545', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7e9b8bce8a1241f284c619b5c2860045', '2017-01-10 14:23:39', '2017-01-10 14:23:39.0307', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/update', '[修改菜单]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7eb8262a91b14cf689b934ba686355f1', '2017-01-10 14:26:15', '2017-01-10 14:26:14.0895', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7eba293affab40a8b3a531fb2d120ccb', '2017-01-12 15:28:39', '2017-01-12 15:28:38.0510', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7f16d82511a945a4b837731c448f11e1', '2017-01-11 13:39:25', '2017-01-11 13:39:25.0293', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7f34875d6e2d49a088c6f97dff1ca6ef', '2017-01-18 14:57:50', '2017-01-18 14:57:50.0025', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7f6307595b134631a8368159f1dad79e', '2017-01-18 12:09:33', '2017-01-18 12:09:32.0863', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7f6ec456019c4e1cbc9f1e5dfc63c431', '2017-01-10 10:44:30', '2017-01-10 10:44:29.0679', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('7f865e395fec4e76b2908a32664b74cf', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0255', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('7fc69e8e9e6d4308a0676919bb40fc4a', '2017-01-11 09:59:37', '2017-01-11 09:59:36.0614', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('80378bbf685a478392d7fe38e7d48bfd', '2017-01-10 14:19:52', '2017-01-10 14:19:51.0567', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('809b7d790a9f434d9a9ece45aaac4979', '2017-01-11 11:49:31', '2017-01-11 11:49:31.0281', null, 'admin', 'admin', '1e3a60e6-cea9-4d83-b16c-a54bd9b97bf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('80aab2310f0f452ba8482f62351ee6fe', '2017-01-18 15:20:56', '2017-01-18 15:20:56.0107', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('818b21b666b84462a5617b8bb7994aff', '2017-01-18 11:57:58', '2017-01-18 11:57:57.0667', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('819199d6c15c4548b4907d86b2bf6738', '2017-01-18 11:50:02', '2017-01-18 11:50:01.0522', null, 'admin', 'admin', 'd291ac99-d294-437e-9c59-b6bcb49afe7b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('821bcf498d1441e583135e20aa05c07e', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0088', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8241dda66b3e42cc87584970d9cf4957', '2017-01-18 14:50:14', '2017-01-18 14:50:13.0735', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('82a2f098b176448ca149b4d08c4787e8', '2017-01-18 11:50:04', '2017-01-18 11:50:04.0409', null, 'admin', 'admin', 'd291ac99-d294-437e-9c59-b6bcb49afe7b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('82e1e786530948fda53317892d8e7957', '2017-01-11 11:43:15', '2017-01-11 11:43:15.0067', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('835b960110ae43ffa29a2c5e145e64a8', '2017-01-18 14:39:48', '2017-01-18 14:39:48.0460', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('835d17b684d343598994706cea928b61', '2017-01-11 16:42:34', '2017-01-11 16:42:34.0199', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/update', '[修改角色]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('83b149f129bc49fc9874094b5dfaac42', '2017-01-10 14:20:54', '2017-01-10 14:20:54.0267', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('83b687f98d224161af87efef0b20f95a', '2017-01-18 14:39:52', '2017-01-18 14:39:51.0643', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('83be0c026ec0445a8e2c2a7609084915', '2017-01-12 14:17:37', '2017-01-12 14:17:37.0261', null, 'admin', 'admin', '7b862a1e-d5b9-4509-8b02-dc28c5bd9f0b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('83cf66b8ee034cd8a3f6e1f2d6174023', '2017-01-10 15:29:20', '2017-01-10 15:29:20.0128', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('83df67912c4245af9182dfa3abadf44a', '2017-01-10 14:24:13', '2017-01-10 14:24:13.0245', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('843f8f9ed80a4674ae8647834ea08e9b', '2017-01-10 14:26:17', '2017-01-10 14:26:16.0862', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8454404d63ae41d9a0b142af285eb61a', '2017-01-12 19:08:10', '2017-01-12 19:08:10.0150', null, 'admin', 'admin', 'f2afac55-d9b7-49cd-b0f4-fa37b1fd792f', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('84668734a23e4fafbdb236acaa2fe42d', '2017-01-11 10:42:21', '2017-01-11 10:42:21.0330', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('84f5e0cdb5ea4afaa66589ae2a59a3ac', '2017-01-10 14:20:39', '2017-01-10 14:20:38.0789', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('85010dd4526b400ba914dce1ec0375ed', '2017-01-18 14:49:34', '2017-01-18 14:49:34.0458', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('852d2d86c8344c8dabb48d046c95b342', '2017-01-18 15:01:55', '2017-01-18 15:01:54.0533', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8573533869de4b38a1e34ad0c861cff9', '2017-01-18 12:20:19', '2017-01-18 12:20:19.0149', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8574f2f07104461980a0dc02c8eab834', '2017-01-12 19:22:03', '2017-01-12 19:22:02.0739', null, 'admin', 'admin', '1d1e55f9-081c-47cb-aff2-50375a9d2d63', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('858aeb103e31427a8edd8829858febbc', '2017-01-12 12:34:02', '2017-01-12 12:34:02.0435', null, 'admin', 'admin', '792b32ba-af19-44c0-8b07-e217416c9b08', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('85abfed98cb4458a8d217b553f0f194f', '2017-01-18 14:27:34', '2017-01-18 14:27:34.0191', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('86a87ec8b21a47daa936b6cd86bac048', '2017-01-12 14:40:55', '2017-01-12 14:40:55.0325', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('86f1cd2dcf204cbb946a04400f5e9a01', '2017-01-10 14:27:01', '2017-01-10 14:27:01.0044', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('878391f72d3b4b0ab438ec6ecb5cbfdf', '2017-01-18 11:13:18', '2017-01-18 11:13:17.0816', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('882c302e894246ba86795e14c78b0265', '2017-01-11 11:05:01', '2017-01-11 11:05:00.0641', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('88369bf957c84a13a061bd5e1c6f3dda', '2017-01-12 18:35:23', '2017-01-12 18:35:22.0906', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('883d7e3aacbb4a2ba83c91e25a47873d', '2017-01-18 11:38:10', '2017-01-18 11:38:09.0634', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('884e347191274f97a1567b032efbdd08', '2017-01-12 13:45:13', '2017-01-12 13:45:12.0821', null, 'admin', 'admin', '1f47dfeb-28d9-4eb1-8e46-d06b1c1668ed', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('88564414dcda4b988b58c1560f81ea4d', '2017-01-10 14:20:58', '2017-01-10 14:20:58.0144', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8859230dfd6d49a599a627c077867f29', '2017-01-12 16:03:43', '2017-01-12 16:03:43.0368', null, 'admin', 'admin', '71da9334-ba68-46fc-966b-6aa214f52f10', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/f/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8881b2f6c1c847d0ad3dcdbe63078dc7', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0389', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('88a48d4bc4cc4784a589f3ded4ad56dc', '2017-01-18 14:16:08', '2017-01-18 14:16:08.0057', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('88d56b9a66aa48508ea7362b9aa3d8f4', '2017-01-11 11:18:36', '2017-01-11 11:18:35.0992', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('89339de4482e468083e59e3d19f364f5', '2017-01-18 14:50:42', '2017-01-18 14:50:42.0323', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('893ab82bd3294bc4b0e7983d6df5f0ba', '2017-01-12 18:48:00', '2017-01-12 18:48:00.0444', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('89abdc6e6051443fafa882a8999942d1', '2017-01-12 19:04:08', '2017-01-12 19:04:07.0824', null, 'admin', 'admin', '0d5af653-2bfd-49a7-91a0-15d8c1b668bc', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('89c87229caca4944824e7ea81bc027b4', '2017-01-10 14:26:17', '2017-01-10 14:26:17.0284', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('89ecb79e203a49ca88e688f238657b68', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0592', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('89fe365090c248bb9ae5e9868530b95d', '2017-01-12 10:34:22', '2017-01-12 10:34:22.0391', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8a00ad0373e0477d850a9d4f9fb6829f', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0680', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8af15a8a2c274ad581e6301d50d69ae3', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0286', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8b07afbb2cab434ca3777e37f4a99d7c', '2017-01-18 14:58:29', '2017-01-18 14:58:28.0825', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8b0f2d1d04594b7d89c38d13581f4960', '2017-01-11 15:38:07', '2017-01-11 15:38:07.0113', null, 'admin', 'admin', '06e91c2c-cc56-4c15-b5c3-d903949e5626', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8b136b2ee65040bcabef496da80d4576', '2017-01-12 18:46:05', '2017-01-12 18:46:05.0403', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthird-party/codemirror/codemirror.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8b308a5f59314ea4b24e30204968d181', '2017-01-12 13:45:05', '2017-01-12 13:45:04.0522', null, 'admin', 'admin', '1f47dfeb-28d9-4eb1-8e46-d06b1c1668ed', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8b701419fe0b4dada5ad9a8ec55dc6e2', '2017-01-18 14:16:05', '2017-01-18 14:16:05.0390', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8b7cc5f3f6bd4922a468f9f81e23e42b', '2017-01-18 12:20:20', '2017-01-18 12:20:19.0672', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8b8bb1b92b00423394c2cbd3480cd508', '2017-01-12 14:02:51', '2017-01-12 14:02:50.0787', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8b9fa2a1eec64403a72287d453f7b969', '2017-01-10 14:23:04', '2017-01-10 14:23:03.0869', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8bb653fa7d8344f6aabb3e3a153dc089', '2017-01-11 11:05:29', '2017-01-11 11:05:29.0208', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8bc0b4f0dc8546f9a36a46cb5e27f7a2', '2017-01-10 14:25:47', '2017-01-10 14:25:46.0921', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8c0c1a5b363840ae91fd16adde1db4ee', '2017-01-10 15:01:48', '2017-01-10 15:01:47.0709', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8c0e631138494b6fb3c910ec72ec246f', '2017-01-10 10:44:17', '2017-01-10 10:44:17.0199', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8c4228ec1df24a8da85a270129234adc', '2017-01-11 10:44:12', '2017-01-11 10:44:11.0688', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cmssite/list', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8c995f5fd7d04289976151703fc8dd28', '2017-01-11 11:43:13', '2017-01-11 11:43:13.0144', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/update', '[修改菜单]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8cc775d6cb5548a499f274bb66fa9846', '2017-01-10 14:22:05', '2017-01-10 14:22:04.0861', null, 'admin', 'admin', 'c59f18f4-412c-48e4-bcff-3880fca966c4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8cf01b87a7054505bf98aa7124eeb642', '2017-01-12 15:25:41', '2017-01-12 15:25:40.0685', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8d038fc5443942e0b0660b9acbdeea59', '2017-01-18 14:30:21', '2017-01-18 14:30:21.0030', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8d084e3393334aba96e5848cb8e5946c', '2017-01-11 16:24:22', '2017-01-11 16:24:21.0527', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8da4365181ab46d29ae0595e87dc325c', '2017-01-10 14:24:37', '2017-01-10 14:24:36.0734', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/grade/list', '[级别管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8dd78d307f974e48a8ffdeac9c7156ec', '2017-01-12 11:58:57', '2017-01-12 11:58:56.0995', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8e27262aaac042b396850f0ebe4a6633', '2017-01-18 14:50:16', '2017-01-18 14:50:15.0581', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8f7351e7bdea46d6b1f042f0443222d6', '2017-01-12 12:33:56', '2017-01-12 12:33:55.0933', null, 'admin', 'admin', '792b32ba-af19-44c0-8b07-e217416c9b08', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8f85b13ec795487b8e41bdd557bfd1c3', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0951', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8f87b8c9ae714e41a41db8dacaa260d0', '2017-01-12 15:26:47', '2017-01-12 15:26:46.0761', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8f977b22f3a9449b96f1a3ddeebed083', '2017-01-12 18:50:51', '2017-01-12 18:50:50.0693', null, 'admin', 'admin', 'bf865796-14f7-427b-91c9-2c97e9ad0fb2', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8f9c0269c2d94ea7bf542d105850ee9b', '2017-01-10 14:24:31', '2017-01-10 14:24:30.0677', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/update', '[修改菜单]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('8f9f70cc0b054a4896b0d9ff23f6069d', '2017-01-18 12:09:40', '2017-01-18 12:09:39.0896', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8fcdfa19a8184e928c63a93edb6008af', '2017-01-18 11:20:38', '2017-01-18 11:20:37.0846', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('8ff3c9461e99455ebf961c780cfd09d6', '2017-01-13 10:25:10', '2017-01-13 10:25:10.0064', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('901c05250f9d4835bbf502dd302bce39', '2017-01-12 14:40:57', '2017-01-12 14:40:56.0725', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('90bd828a12b04fcd9616509292be1161', '2017-01-18 14:58:30', '2017-01-18 14:58:30.0002', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('90d8892d9b1946b2bfa7c046707f7896', '2017-01-10 14:20:35', '2017-01-10 14:20:35.0167', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('915dba7822a045b6a1df1891ce2fb072', '2017-01-11 17:27:25', '2017-01-11 17:27:24.0809', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('91ca7f460381454498affa8c64dfe66d', '2017-01-18 14:58:29', '2017-01-18 14:58:29.0454', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('92084b3f45024b63ab90b832eb1e7b4e', '2017-01-12 10:15:03', '2017-01-12 10:15:02.0672', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/role/look/json', '[查看角色]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9216ab4973eb48b08f0d518451bb92e4', '2017-01-10 14:24:12', '2017-01-10 14:24:11.0967', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/update', '[修改菜单]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9220fa5d08904295b1616a1eceb17601', '2017-01-12 14:41:17', '2017-01-12 14:41:16.0530', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('923f53088bb04bd2b16d5efa5087d2ab', '2017-01-11 10:56:37', '2017-01-11 10:56:36.0573', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('92daacd40feb43b795eebc542b43bab2', '2017-01-10 14:25:44', '2017-01-10 14:25:43.0890', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/update', '[修改菜单]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('930846c337f84c7281ebe1f2338b3f46', '2017-01-10 14:51:35', '2017-01-10 14:51:34.0950', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9310d60bdee24979b7006db663260768', '2017-01-18 14:37:52', '2017-01-18 14:37:51.0709', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('933fe21fec9c4a9f89ce2cc312e2a24e', '2017-01-18 14:58:20', '2017-01-18 14:58:19.0622', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('93ff8b372fb3422d81a6730f54df309e', '2017-01-18 14:58:34', '2017-01-18 14:58:34.0049', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('93fffc4d7ddd45e08620ac52890e0b35', '2017-01-12 18:45:11', '2017-01-12 18:45:11.0499', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('94d227ddb5884fbc9ea767c0c968d7b2', '2017-01-18 15:00:50', '2017-01-18 15:00:50.0351', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('94d8e8177cb841729fc9d2bed3b4f2cb', '2017-01-11 15:53:56', '2017-01-11 15:53:56.0221', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('950207eabb6d4ed69ff82096b26e44ff', '2017-01-18 14:37:54', '2017-01-18 14:37:54.0133', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('95225c03094b4d4c8a17f3f89a70b6d0', '2017-01-18 11:20:03', '2017-01-18 11:20:02.0681', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('95454fdae31545cbb84f1fa48739e1d1', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0153', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9587c6df2b99446da1c168cfe0e876db', '2017-01-11 13:53:24', '2017-01-11 13:53:23.0784', null, 'admin', 'admin', 'f081c6f4-5160-47e5-ab0c-a98a3ad10c4d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9593e40a4c544f9d90b926ae93625819', '2017-01-12 19:22:15', '2017-01-12 19:22:15.0419', null, 'admin', 'admin', '1d1e55f9-081c-47cb-aff2-50375a9d2d63', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('95fa9036ffa74ae7a7b030d4f3fd7fe4', '2017-01-18 11:50:02', '2017-01-18 11:50:01.0825', null, 'admin', 'admin', 'd291ac99-d294-437e-9c59-b6bcb49afe7b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('963ee5c2861140f0842dc80f70221edf', '2017-01-11 10:27:05', '2017-01-11 10:27:05.0035', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('969c2bebc1204fce94dc9d07f561af1a', '2017-01-13 10:24:48', '2017-01-13 10:24:48.0231', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('96a1343f2cd049dfb1010243f77cf768', '2017-01-12 18:47:18', '2017-01-12 18:47:18.0068', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('96d1cb691c254e93b0bf0b15450feade', '2017-01-18 14:57:52', '2017-01-18 14:57:52.0249', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('96e499fe5c83490d9cbb3d73a1191c6b', '2017-01-10 15:27:02', '2017-01-10 15:27:01.0909', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9741bced339b484fa53f1aabcee08007', '2017-01-10 14:37:23', '2017-01-10 14:37:22.0586', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9749153e27cb4752882709d7c88277f8', '2017-01-18 11:58:15', '2017-01-18 11:58:15.0376', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('97a6eb6f46e5424c9d488bf81e6330bf', '2017-01-18 15:20:58', '2017-01-18 15:20:57.0964', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('97d4b0ec48d64504a866f40185c97e40', '2017-01-10 15:16:13', '2017-01-10 15:16:13.0173', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9915886893f54fa19ff920b2f4689486', '2017-01-11 10:26:16', '2017-01-11 10:26:16.0421', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('991636b61afb40e595d1f409170c2726', '2017-01-11 17:27:35', '2017-01-11 17:27:34.0614', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('992bf021854841c297a9b3bd3f399015', '2017-01-18 14:10:15', '2017-01-18 14:10:14.0676', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('996e2561dec2485287ef39c5037984ae', '2017-01-11 10:37:23', '2017-01-11 10:37:23.0155', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('996e5de2a3a7419fbfb444abe0d1688d', '2017-01-11 11:18:31', '2017-01-11 11:18:30.0591', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9994b8cbd44f498e8fa4c37c49b14b9a', '2017-01-18 14:58:19', '2017-01-18 14:58:18.0709', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9a3e1b1715e8482e8700341c08737572', '2017-01-12 14:02:48', '2017-01-12 14:02:48.0187', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9a438bd0a00245f39ced8717b49108f8', '2017-01-18 14:50:41', '2017-01-18 14:50:41.0230', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9aa408e673ee4b8ebc1fa09e3363f2f9', '2017-01-12 14:17:40', '2017-01-12 14:17:39.0689', null, 'admin', 'admin', '7b862a1e-d5b9-4509-8b02-dc28c5bd9f0b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9ae882f0aab84a99a5c5b483f751aa88', '2017-01-10 14:23:41', '2017-01-10 14:23:41.0194', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9b044ae980fe470eb965eeeeb7e8defe', '2017-01-18 14:58:31', '2017-01-18 14:58:30.0936', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9b5c82ce51da492a825def24c2852ec3', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0626', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9c1d8f9b16614c7387bb9ae950a1aac6', '2017-01-18 11:30:16', '2017-01-18 11:30:16.0168', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9c60a751c0f045c898d18043497f7e9c', '2017-01-18 14:49:51', '2017-01-18 14:49:50.0764', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9c787e1e3f8f4605a10b0609386a9a4e', '2017-01-12 14:01:07', '2017-01-12 14:01:07.0391', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9c8002679ca04b06b37d1b7ee2cc6e30', '2017-01-13 10:24:05', '2017-01-13 10:24:05.0379', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9cfcc63488ed44b4807248a2b37d3399', '2017-01-13 10:25:10', '2017-01-13 10:25:09.0776', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9d2ef6032c3a4c32a0b9c6c3b81d7f56', '2017-01-18 14:57:50', '2017-01-18 14:57:49.0828', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9d79e5867c7f41ea93c25d22a7365f39', '2017-01-18 11:58:15', '2017-01-18 11:58:14.0821', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9dde70da8b804987a058d14c2a837094', '2017-01-12 12:17:57', '2017-01-12 12:17:57.0193', null, 'admin', 'admin', 'ee4ffd11-e144-43ff-8c0f-f4cf892e3778', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9de6b72e22264fdbb6d12781bb390c6a', '2017-01-10 14:22:23', '2017-01-10 14:22:23.0348', null, 'admin', 'admin', 'e37c1a0e-2a38-4d24-90ec-5a93734d128d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9deb4a79ec154f1cb5e5e43e5622d269', '2017-01-12 10:38:34', '2017-01-12 10:38:33.0593', null, 'admin', 'admin', '2ec1cc3e-4092-4a3f-baf8-34234e8b3d65', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9e1eada5e8794efaa172c3525571a525', '2017-01-10 10:44:34', '2017-01-10 10:44:34.0180', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list/json', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9e257bd133ee4d9eadee0d0507882b66', '2017-01-12 19:33:45', '2017-01-12 19:33:44.0928', null, 'admin', 'admin', 'faf1f11c-da15-4cd6-b13a-582ac829aeb5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9e2a66e588834985b8202f3d17b56530', '2017-01-10 14:37:01', '2017-01-10 14:37:00.0635', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9e32bcb7f3d84302a27561084565e4eb', '2017-01-10 15:00:58', '2017-01-10 15:00:57.0796', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9ec95f119f224b1f9c31aaf71ab671d3', '2017-01-18 11:20:39', '2017-01-18 11:20:39.0058', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9ecdf87cc411469084c60671740eda03', '2017-01-12 19:33:45', '2017-01-12 19:33:45.0314', null, 'admin', 'admin', 'faf1f11c-da15-4cd6-b13a-582ac829aeb5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9ee3fcb348cf42e9aed372a339fade5a', '2017-01-10 14:21:57', '2017-01-10 14:21:56.0552', null, 'admin', 'admin', 'c59f18f4-412c-48e4-bcff-3880fca966c4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9f34546a2fe9442cb4c30a29f2bd8d74', '2017-01-12 19:07:57', '2017-01-12 19:07:56.0730', null, 'admin', 'admin', 'f2afac55-d9b7-49cd-b0f4-fa37b1fd792f', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9fa2d4ad18514135bf7d272bc55f383a', '2017-01-12 14:37:26', '2017-01-12 14:37:26.0256', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9fa85759487843f2a5db3930779dabc9', '2017-01-11 16:41:44', '2017-01-11 16:41:44.0137', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9fd8774ba0ab494fb675b21b03754505', '2017-01-11 11:45:00', '2017-01-11 11:44:59.0720', null, 'admin', 'admin', '30d2378e-ce1f-48a2-91a5-a7fe18ddd94f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9fec065a8d3b4db0b2493028ced3f0f2', '2017-01-10 14:26:13', '2017-01-10 14:26:13.0341', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('9ff20564e5bf4557ba104257774fd112', '2017-01-11 10:58:08', '2017-01-11 10:58:07.0761', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('9ff3b824e8bd43ee8b64e6959b4bf2a5', '2017-01-10 14:20:55', '2017-01-10 14:20:55.0351', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a0211b3594624cce805237165665803a', '2017-01-12 12:33:52', '2017-01-12 12:33:52.0008', null, 'admin', 'admin', '792b32ba-af19-44c0-8b07-e217416c9b08', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a036239e80a9404f9e1ad1ffc60e3d92', '2017-01-10 15:00:57', '2017-01-10 15:00:56.0750', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a0786f71b143495d90bc6b6d23976c19', '2017-01-18 14:58:35', '2017-01-18 14:58:35.0045', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a0aa79a74b8e4cd7928a7e1cd24f73f0', '2017-01-12 18:36:11', '2017-01-12 18:36:10.0815', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a106284f16a64ea2b15298d67df869dd', '2017-01-11 09:59:35', '2017-01-11 09:59:35.0422', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/update', '[修改角色]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a15a71aa1a644a45b52ea248bfdf66d3', '2017-01-18 15:21:09', '2017-01-18 15:21:09.0482', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a19cbbf7e4b14125bc9b26b30ba1f314', '2017-01-18 11:37:46', '2017-01-18 11:37:46.0047', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a19fe440aada4a2789fa7ba10442ae5d', '2017-01-12 18:46:05', '2017-01-12 18:46:05.0410', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthird-party/zeroclipboard/ZeroClipboard.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a1c674a0a1ca4e188c716cfa7707ca27', '2017-01-18 14:27:17', '2017-01-18 14:27:17.0068', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a1cfccd0da4541f2ac307b0bea3f52dc', '2017-01-10 14:20:57', '2017-01-10 14:20:56.0607', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/grade/list', '[级别管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a1d039a9667547e489108ef4c111eb62', '2017-01-10 15:16:09', '2017-01-10 15:16:08.0797', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a26971417bbe4d7692387355f8b71919', '2017-01-12 19:01:21', '2017-01-12 19:01:20.0908', null, 'admin', 'admin', '0d5af653-2bfd-49a7-91a0-15d8c1b668bc', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a272127e39e2447f8d3942ea1c32cf9d', '2017-01-18 14:09:45', '2017-01-18 14:09:44.0989', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a2c90d6e228446178dd7028b73e42e34', '2017-01-18 11:38:10', '2017-01-18 11:38:09.0746', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a2fd73799cec442baa60c1659dab15f3', '2017-01-18 11:53:38', '2017-01-18 11:53:37.0523', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/', null, '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a3114411941941dfb37041b0eba4ddf6', '2017-01-18 14:50:11', '2017-01-18 14:50:11.0294', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a345a820901246a3bdacd4af6717cfbc', '2017-01-18 14:10:21', '2017-01-18 14:10:21.0489', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a349be771ae941ba85842ba4cc9ad0e6', '2017-01-11 11:18:34', '2017-01-11 11:18:33.0808', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a36504d9672942a6a37ae2457f47657d', '2017-01-12 19:28:48', '2017-01-12 19:28:48.0199', null, 'admin', 'admin', '7bdb306b-a7cd-4c37-b38b-f602a8760273', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a372840ebdeb4717a3916bf0aa72132b', '2017-01-18 14:58:36', '2017-01-18 14:58:36.0428', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a38a61222b5e4af9961bcc18cad989ec', '2017-01-18 11:54:54', '2017-01-18 11:54:53.0989', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a3ba5337647a4fd99a109058504c6b4b', '2017-01-12 13:58:42', '2017-01-12 13:58:42.0386', null, 'admin', 'admin', '62a2b596-f843-45ac-9d60-4e297251c6eb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a3caccae54c740168a7c1db2fcbd273d', '2017-01-11 10:02:28', '2017-01-11 10:02:28.0492', null, 'admin', 'admin', '4b34858b-3c9d-4853-ad00-501a1d5057e4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a3d9b53af22249daa6a6f14af985c040', '2017-01-10 14:25:20', '2017-01-10 14:25:20.0139', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a3da326b205545c0aa8ad10416bdb76d', '2017-01-18 11:20:11', '2017-01-18 11:20:10.0799', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a3f6c120c197410c83d6db0fe5a445f2', '2017-01-18 14:50:10', '2017-01-18 14:50:10.0443', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a41832d090bf470da39c58ca20cce1a9', '2017-01-11 13:39:36', '2017-01-11 13:39:36.0265', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a4c25ab115da46f0b9fbf5da577b2bd4', '2017-01-11 16:49:08', '2017-01-11 16:49:07.0676', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a4c63bf0297845fb9a7f9257aa0f5087', '2017-01-18 14:50:10', '2017-01-18 14:50:10.0161', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a4d245803ef24eab96585caa1e3134d9', '2017-01-12 14:02:45', '2017-01-12 14:02:44.0788', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a518c2a2d36e4607a35b48b06102592a', '2017-01-10 15:37:41', '2017-01-10 15:37:40.0893', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a552bc9f4b414e6f88d520986107520e', '2017-01-11 15:43:53', '2017-01-11 15:43:53.0010', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a555ed2f62d346808e655ec7dee7c66c', '2017-01-12 10:14:56', '2017-01-12 10:14:56.0113', null, 'admin', 'admin', '0d77cc55-c3de-473f-b15e-4d7ab16cb864', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a58a9ad1a4aa4e0b8a9ccf8f4b245774', '2017-01-18 12:21:13', '2017-01-18 12:21:12.0972', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a5a6a79836204183bfb733616ff46a66', '2017-01-11 09:55:35', '2017-01-11 09:55:35.0170', null, 'admin', 'admin', '2c888c7f-55ba-4b57-855a-d14224d9fa1b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a5d41c5e5ae847b3ab11f812af9fb3f9', '2017-01-18 14:27:28', '2017-01-18 14:27:27.0913', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a5f03f3e16b045578a13da4c461ac841', '2017-01-11 13:35:11', '2017-01-11 13:35:10.0802', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a644e12f35e5478ba496cd608ecc0fc1', '2017-01-12 14:45:08', '2017-01-12 14:45:08.0135', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a66d829c116b4e178eb6c3e0a7b9c024', '2017-01-11 14:37:14', '2017-01-11 14:37:14.0230', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a68a2127b10043eb9bee71c817e8b3d5', '2017-01-12 19:28:51', '2017-01-12 19:28:50.0633', null, 'admin', 'admin', '7bdb306b-a7cd-4c37-b38b-f602a8760273', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a70b47070df545cf8e5f9a9ae7b870af', '2017-01-12 10:35:36', '2017-01-12 10:35:36.0322', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/ueditorConfig/init', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a7b6837b9940415898f78fafba2984f8', '2017-01-12 14:46:31', '2017-01-12 14:46:30.0899', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a805731f88a743c7991e1e08723e21fe', '2017-01-10 14:23:06', '2017-01-10 14:23:06.0471', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a8ee513f3ede41d5b03a0ae1f28869b1', '2017-01-18 11:59:11', '2017-01-18 11:59:11.0067', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a93b01e0b5104500b859d0e0f58f7bfc', '2017-01-12 18:35:01', '2017-01-12 18:35:01.0449', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('a94613c1d7314914a30fae43bffc0937', '2017-01-18 14:27:16', '2017-01-18 14:27:15.0891', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a99fec01df0e4afb99b26acc2a290c2e', '2017-01-11 14:36:38', '2017-01-11 14:36:37.0833', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a9d0058c3b20413abcbb58242507bed0', '2017-01-18 14:58:33', '2017-01-18 14:58:33.0452', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a9e8d3ba91684d0b9b51981b8741c091', '2017-01-11 11:05:41', '2017-01-11 11:05:41.0488', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('a9f26328080c4688a23eb4a19ad50b3f', '2017-01-11 09:59:25', '2017-01-11 09:59:24.0756', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('aa056ffd01b245aca770fcde62be02bb', '2017-01-12 13:58:40', '2017-01-12 13:58:39.0849', null, 'admin', 'admin', '62a2b596-f843-45ac-9d60-4e297251c6eb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('aa3a7ad527b442f7a726e1720fd7109c', '2017-01-11 17:25:10', '2017-01-11 17:25:09.0514', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('aa5aabc0beb84d608095e5565937fbf3', '2017-01-11 10:29:26', '2017-01-11 10:29:26.0417', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('aaa794d9708c4af3a381e555092437ef', '2017-01-18 11:55:03', '2017-01-18 11:55:03.0041', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('aac7a424bdf947b0abda309cf8e85fee', '2017-01-11 11:18:28', '2017-01-11 11:18:27.0945', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('aacfcddffe4a495ab12dd72568c781ea', '2017-01-10 14:20:35', '2017-01-10 14:20:34.0622', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ab5051efca6f4460b8b92adc30471280', '2017-01-12 14:38:57', '2017-01-12 14:38:56.0736', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('abccf1ce5ed142be8778e62a13134e54', '2017-01-12 14:08:12', '2017-01-12 14:08:12.0398', null, 'admin', 'admin', '83e929e2-918d-479f-821b-bf7e3d995c50', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('abd1265bc79149ae99162526ec8a48fc', '2017-01-18 11:20:02', '2017-01-18 11:20:02.0129', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ac2534b059bf4a2eaef4169c90bba887', '2017-01-12 12:27:29', '2017-01-12 12:27:29.0056', null, 'admin', 'admin', '4dde7d85-af78-4ef5-8d44-95049547d30a', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ac75a0ff7cf3466dba98cf8a267ed071', '2017-01-11 11:01:44', '2017-01-11 11:01:44.0193', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ac834a1464164adc95a37c629ec2b857', '2017-01-12 18:51:04', '2017-01-12 18:51:03.0505', null, 'admin', 'admin', 'bf865796-14f7-427b-91c9-2c97e9ad0fb2', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('acbcb99479a44e80924cba10325887a9', '2017-01-18 14:30:20', '2017-01-18 14:30:20.0270', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ace77169e49f44cd9f20b9b1d6712279', '2017-01-10 10:44:34', '2017-01-10 10:44:33.0854', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/user/list', '[用户管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ad0031e95e6e42c38b01e8f1bc7542c2', '2017-01-12 19:01:23', '2017-01-12 19:01:22.0638', null, 'admin', 'admin', '0d5af653-2bfd-49a7-91a0-15d8c1b668bc', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ada65825dc0349ec869340449b4cdaf9', '2017-01-12 14:01:14', '2017-01-12 14:01:13.0599', null, 'admin', 'admin', '0815dafc-cf24-4b21-b976-15c7ffbbea75', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ae789b144bcb48b68915e53f458ce4c8', '2017-01-11 13:39:53', '2017-01-11 13:39:53.0274', null, 'admin', 'admin', 'f081c6f4-5160-47e5-ab0c-a98a3ad10c4d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ae7f5882a31549b9bc18ad6ce2155c53', '2017-01-18 14:57:57', '2017-01-18 14:57:56.0979', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('affa8a46374d4d02832436cd1a790ae2', '2017-01-18 14:50:14', '2017-01-18 14:50:13.0934', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b07a4fb6652e40fd8483015250dd2a87', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0846', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b0f908de263e4edeb5989d0d896ddb27', '2017-01-12 13:47:03', '2017-01-12 13:47:02.0522', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b11bac99cdd64898924c7a79fe8d4063', '2017-01-11 10:44:16', '2017-01-11 10:44:15.0538', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b14cdddc9c8a4b9e8f54eaf84299996b', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0529', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b1f46dfa55b84c8a890e41f1e7901e78', '2017-01-18 11:50:13', '2017-01-18 11:50:13.0171', null, 'admin', 'admin', 'd291ac99-d294-437e-9c59-b6bcb49afe7b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b25840127d0f4eaaa4586d6ecfd26584', '2017-01-18 14:49:51', '2017-01-18 14:49:50.0629', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b281780ceb2d4f4a80ec2bc4a972067b', '2017-01-12 15:29:18', '2017-01-12 15:29:17.0651', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b2e6c1583b8e4135978eb56d73cf7725', '2017-01-12 11:36:48', '2017-01-12 11:36:47.0875', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b2f45116169a46cf8f00bea71e811f70', '2017-01-11 13:39:23', '2017-01-11 13:39:23.0132', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b38f8c29137e41ce90708df31daa46c2', '2017-01-11 13:37:42', '2017-01-11 13:37:42.0070', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b3a6fcca5941491298ea043f759f17f3', '2017-01-11 15:43:14', '2017-01-11 15:43:14.0225', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b3bed4509ade4279871f3dc7917a70ed', '2017-01-18 15:22:49', '2017-01-18 15:22:49.0325', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b3c83a14c0bc48ac88e6e9485ea575b5', '2017-01-18 15:00:51', '2017-01-18 15:00:50.0711', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b3d3e25525904a7c8b55a7afe4a65b39', '2017-01-18 14:09:37', '2017-01-18 14:09:36.0774', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b3f827f7cc334e879f4e000987376de0', '2017-01-12 19:34:29', '2017-01-12 19:34:28.0697', null, 'admin', 'admin', '39523b9f-57d7-4ebb-932c-9e15a7ab07cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b40cd7b8528041a8971d6243268d7056', '2017-01-18 11:37:16', '2017-01-18 11:37:15.0847', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b4101f03193f4e2a93415e7f786fb68c', '2017-01-11 10:31:54', '2017-01-11 10:31:54.0327', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b436bb87cada46ea8bd0c241a35be8ce', '2017-01-12 10:35:34', '2017-01-12 10:35:33.0544', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b4503b51ce3c4e7b9fb38fc07e8fb66a', '2017-01-11 16:42:35', '2017-01-11 16:42:35.0383', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b484694eb43947d6aaa43d6d91519bfc', '2017-01-18 15:22:49', '2017-01-18 15:22:49.0205', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b484c4807daa482ba84293630c94506d', '2017-01-11 17:25:07', '2017-01-11 17:25:07.0338', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b49eb80c6e5d4b5fbbcda0fbc4d26c1d', '2017-01-11 16:40:51', '2017-01-11 16:40:51.0144', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b4a46c2766694a2b8f9f75577ed5daa4', '2017-01-18 11:37:20', '2017-01-18 11:37:20.0395', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b5246fb014eb4c2aa7479bdccf07bd72', '2017-01-18 11:18:20', '2017-01-18 11:18:19.0800', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b55bbddfc11f4490a9a5117c893efcfd', '2017-01-18 14:27:28', '2017-01-18 14:27:27.0786', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b5a328d539374445875008ce717ff6fb', '2017-01-10 14:35:42', '2017-01-10 14:35:41.0818', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b5e338049078411b8ec6c5dda0078f0b', '2017-01-18 15:00:32', '2017-01-18 15:00:32.0360', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b5ef5195a7b6403680b790bf69375faa', '2017-01-11 13:39:44', '2017-01-11 13:39:44.0392', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b68e3a28712442888752664f93206fe7', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0987', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b69e0707a0c14136b4215e16bb17af83', '2017-01-12 12:02:16', '2017-01-12 12:02:15.0738', null, 'admin', 'admin', '4c211f44-2df8-4eab-b4a5-05e1d33324cf', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b6bdd66c33a0494ca6d58c950989ba3a', '2017-01-18 11:50:12', '2017-01-18 11:50:12.0233', null, 'admin', 'admin', 'd291ac99-d294-437e-9c59-b6bcb49afe7b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b70dd082bb5041f6a8728702d0ece30a', '2017-01-12 19:30:27', '2017-01-12 19:30:26.0571', null, 'admin', 'admin', '27778b74-5bc9-449b-9e29-fca6a177cccd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b7bf715d7b684c3791dba64bbe3e63a4', '2017-01-18 12:09:45', '2017-01-18 12:09:45.0366', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b84f9692e3cd428897e7254317145e07', '2017-01-12 10:15:42', '2017-01-12 10:15:41.0773', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b87a294a09eb4331bf86c93eb652a82a', '2017-01-17 11:53:25', '2017-01-17 11:53:25.0490', null, 'admin', 'admin', 'c52afa87-4eb2-467a-9e55-3caf46f8e018', '127.0.0.1', 'http://localhost:8080/springrain/f/2/site_11/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b8c2eecd85da4d6fbd83a546ec4c61a5', '2017-01-17 11:53:06', '2017-01-17 11:53:06.0226', null, 'admin', 'admin', 'c52afa87-4eb2-467a-9e55-3caf46f8e018', '127.0.0.1', 'http://localhost:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b8f1799e8fde41ffa3e104bb13246de1', '2017-01-11 11:04:47', '2017-01-11 11:04:47.0140', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b91edcf536d240e9ae8bf629484c0586', '2017-01-10 14:24:32', '2017-01-10 14:24:32.0339', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b9252815babf48c18e355b43b1df73bb', '2017-01-18 11:07:38', '2017-01-18 11:07:37.0819', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('b926a5200c064074b26da98fdab5a361', '2017-01-12 14:02:56', '2017-01-12 14:02:55.0612', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b95ab012a35849f0a0cb54d058045ab5', '2017-01-18 14:10:21', '2017-01-18 14:10:21.0062', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('b9db672868a9458d9d25c8df9e6000e4', '2017-01-18 14:14:24', '2017-01-18 14:14:24.0175', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ba47ebe781ae475facef7a2d18d7ea5c', '2017-01-18 11:53:40', '2017-01-18 11:53:39.0746', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ba66d332069c460b858d5f3e0e92265a', '2017-01-18 14:50:14', '2017-01-18 14:50:14.0101', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('baaa98c903764471bffe8368c1272ae0', '2017-01-12 15:12:37', '2017-01-12 15:12:36.0658', null, 'admin', 'admin', '97494dcd-46fb-4fb4-bbb1-048237ed5810', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('bb0c9601ff4c4b8fac664fac02f2c885', '2017-01-18 11:54:52', '2017-01-18 11:54:52.0075', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('bb2f5ad752874fd9a60ad3a1cc025e77', '2017-01-18 15:00:31', '2017-01-18 15:00:30.0516', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('bb7599aa1efc470b98b745d775e7f25d', '2017-01-10 14:17:20', '2017-01-10 14:17:19.0720', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('bb96013dedd443d9b51cfbdd13d3680b', '2017-01-10 14:23:41', '2017-01-10 14:23:40.0651', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('bc5f411783604cab85ab5fd35867eaf9', '2017-01-11 13:39:55', '2017-01-11 13:39:55.0045', null, 'admin', 'admin', 'f081c6f4-5160-47e5-ab0c-a98a3ad10c4d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('bc9f96a1c21c4f2a98fda8f7ebbd0b70', '2017-01-13 10:22:51', '2017-01-13 10:22:50.0931', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('bcc05d6dfe224f3a8a94d63a8f6acb85', '2017-01-10 15:38:14', '2017-01-10 15:38:14.0011', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('bd31b6af155044acb291d3d59c0b8b79', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0879', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('bdaff57309d54094bc65d5bf2bf12ce1', '2017-01-18 14:58:26', '2017-01-18 14:58:26.0202', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('bdc84d4cf37645819bd78e2f3a571e79', '2017-01-11 10:32:53', '2017-01-11 10:32:53.0462', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('be38b998e50041ce8a441a47281c543f', '2017-01-11 14:45:11', '2017-01-11 14:45:11.0353', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('bf980a8063944d858ea972643eb6ede6', '2017-01-12 10:15:44', '2017-01-12 10:15:43.0762', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/themes/default/css/ueditor.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('bfd853783138401a8f79ae1fde79647c', '2017-01-12 19:05:27', '2017-01-12 19:05:27.0189', null, 'admin', 'admin', '0d5af653-2bfd-49a7-91a0-15d8c1b668bc', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c0251bceeb2e4b8f8883f64f9dc3c942', '2017-01-18 11:37:46', '2017-01-18 11:37:45.0741', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c04f250b8a7e4b3c941411594a99ba87', '2017-01-12 14:43:47', '2017-01-12 14:43:46.0864', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c059680ab2634d13a0322c70af6451f8', '2017-01-12 18:45:13', '2017-01-12 18:45:12.0636', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/themes/iframe.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c090531b562044f281650ad6821986ca', '2017-01-12 11:58:42', '2017-01-12 11:58:41.0782', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c0f95ae996ba4d03821beb7609ba569e', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0988', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c0fb94397088499b84976007f75bf30c', '2017-01-11 11:45:29', '2017-01-11 11:45:29.0273', null, 'admin', 'admin', '30d2378e-ce1f-48a2-91a5-a7fe18ddd94f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c1a56fd309274fa3af655db1fd8a61b5', '2017-01-11 13:53:22', '2017-01-11 13:53:21.0512', null, 'admin', 'admin', 'f081c6f4-5160-47e5-ab0c-a98a3ad10c4d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/cmschannel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c1e8301e25934a1fb4af209f3a6d2b68', '2017-01-12 14:12:33', '2017-01-12 14:12:32.0500', null, 'admin', 'admin', 'e071116e-b364-4a51-a488-64f340ff3599', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c2001a8525d84056bb5e014b7cdd20ca', '2017-01-18 11:18:21', '2017-01-18 11:18:20.0525', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c26b84e6cfb3400292fd8d9ae2e36b08', '2017-01-12 18:41:29', '2017-01-12 18:41:29.0416', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c31c8dab3d8a4799977e55c1cccc7188', '2017-01-10 15:37:39', '2017-01-10 15:37:38.0879', null, 'admin', 'admin', 'f4de232f-d716-4759-acdb-e2ffb2ec5afe', '127.0.0.1', 'http://127.0.0.1:8080/springrain/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c3cd411d53a24232b0deb834917abfac', '2017-01-11 16:55:35', '2017-01-11 16:55:35.0321', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c3f48f7dbd124c2cabce248a942cf98e', '2017-01-18 11:50:12', '2017-01-18 11:50:11.0953', null, 'admin', 'admin', 'd291ac99-d294-437e-9c59-b6bcb49afe7b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c445ebcbc1564da2868cca29a82d2bbb', '2017-01-11 17:30:21', '2017-01-11 17:30:20.0744', null, 'admin', 'admin', 'e1c888d2-cd21-41f1-b672-7656f69da531', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c4ef4bbaa44c4b529341cfdf6a2fba99', '2017-01-18 14:58:29', '2017-01-18 14:58:28.0964', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c5e0e8f11eaf4904af39796190771015', '2017-01-17 11:53:29', '2017-01-17 11:53:29.0393', null, 'admin', 'admin', 'c52afa87-4eb2-467a-9e55-3caf46f8e018', '127.0.0.1', 'http://localhost:8080/springrain/f/0/site_11/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c5e5c4c64c3e4195b7c89487ee7e5dc3', '2017-01-18 11:38:54', '2017-01-18 11:38:53.0850', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c5eca98b06534096b738e79b4e3e7312', '2017-01-10 14:24:42', '2017-01-10 14:24:41.0593', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c6181a0019be49589fa1b3f78cb0a531', '2017-01-12 15:19:21', '2017-01-12 15:19:20.0768', null, 'admin', 'admin', '1043ccac-d9e8-493f-8d39-6504147fe8e8', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c72269b2c49045b7a7cbe4b904a90d5c', '2017-01-18 14:17:22', '2017-01-18 14:17:21.0904', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c7669035cc6740859e66d528ac5a5fe2', '2017-01-18 11:19:42', '2017-01-18 11:19:42.0388', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c778cee0568c4e5b9dc47ade9322a4da', '2017-01-13 10:26:00', '2017-01-13 10:25:59.0777', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c870b963537645eb9e8d519050aeed0d', '2017-01-10 14:17:20', '2017-01-10 14:17:20.0169', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c88049cb029f40bfbb43b1c142b7ef26', '2017-01-12 12:34:53', '2017-01-12 12:34:52.0563', null, 'admin', 'admin', 'b75ce246-bc93-4c34-b53a-b69c5906e5d7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c8869aeb8b7447ed8299bc045cf04ece', '2017-01-12 18:45:13', '2017-01-12 18:45:13.0052', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/third-party/codemirror/codemirror.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c9192bf8a14e409084b7f44582de0371', '2017-01-12 18:53:47', '2017-01-12 18:53:46.0649', null, 'admin', 'admin', '1534998f-ddbc-4cf0-8dc5-d640539678ad', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c94bdc8b11ea4dde9fbccd81955cb1cc', '2017-01-12 18:47:00', '2017-01-12 18:46:59.0626', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c963030b2c9d492c969a237bfff16308', '2017-01-12 18:45:13', '2017-01-12 18:45:13.0053', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/third-party/codemirror/codemirror.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('c9d532617a0b4cb4a1b1e0c63d171157', '2017-01-12 19:35:36', '2017-01-12 19:35:36.0212', null, 'admin', 'admin', '35616fb5-6b83-4707-a604-52e68f109437', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('c9e62efcb6744e1d9ac1ea88ed96632e', '2017-01-12 14:02:54', '2017-01-12 14:02:54.0235', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ca365159948b4d82a9a0768e51a8a619', '2017-01-11 14:45:24', '2017-01-11 14:45:24.0288', null, 'admin', 'admin', 'bcfe7660-834c-4a84-8640-6cf32fe9123b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ca631c15e8eb425d9b7a75748f0b3a38', '2017-01-18 14:09:25', '2017-01-18 14:09:25.0377', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('caa314b7564b46c4872b91e6ad0be269', '2017-01-18 11:38:54', '2017-01-18 11:38:53.0598', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cbb7cb12c4c74e2fa1cd5e1090eb15d7', '2017-01-11 11:08:16', '2017-01-11 11:08:15.0921', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('cbd6653403f042169c47efce67b988d8', '2017-01-12 10:15:44', '2017-01-12 10:15:43.0764', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/ueditorConfig/init', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cc1040f7912d4408a6de9fd581d54298', '2017-01-18 14:49:26', '2017-01-18 14:49:26.0005', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cc183884447147c7b47d8d5ee2b75261', '2017-01-18 14:13:25', '2017-01-18 14:13:24.0891', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('cc22994e64f34dcd9c5c8bd753029331', '2017-01-11 16:42:23', '2017-01-11 16:42:23.0248', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/list', '[角色管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('cc533b90e40d42338f5c4c6e69bf5def', '2017-01-11 09:59:22', '2017-01-11 09:59:21.0563', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('cc5a198cec264ced9abd4361d86dd385', '2017-01-18 11:50:04', '2017-01-18 11:50:04.0073', null, 'admin', 'admin', 'd291ac99-d294-437e-9c59-b6bcb49afe7b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cc68ea80d9de44e48e24652fceb72c72', '2017-01-18 11:37:21', '2017-01-18 11:37:20.0650', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('cd867884a717474ba606434f97ef5fbc', '2017-01-18 15:01:00', '2017-01-18 15:00:59.0984', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('cdb576ad36d647b4a54b2e64269c3995', '2017-01-18 11:53:45', '2017-01-18 11:53:44.0677', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ce119b9d943549049051f129732fe8db', '2017-01-18 14:49:34', '2017-01-18 14:49:34.0054', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cebbd67f5580496b8ef0d47396c7bb6e', '2017-01-11 10:26:58', '2017-01-11 10:26:58.0001', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cef22a1b8b634a078b17c7e7b53b3aca', '2017-01-12 15:42:01', '2017-01-12 15:42:01.0262', null, 'admin', 'admin', 'a2894356-0276-407a-bbb6-1f9ad5da026a', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cf5e18eb5386406884cd9ba6d55a1725', '2017-01-18 15:01:54', '2017-01-18 15:01:54.0290', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('cfa9b37dc5c342c1b5fa38b46ef897bc', '2017-01-18 15:21:09', '2017-01-18 15:21:09.0210', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cfb5bfaf3c8d49e1a4f5c284223f9ace', '2017-01-18 14:50:15', '2017-01-18 14:50:14.0635', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('cfe16f73ca4042b28b4fa7600007d818', '2017-01-12 18:39:57', '2017-01-12 18:39:57.0465', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d07f81debf0545b7affff3b81e2f8ef9', '2017-01-11 11:05:15', '2017-01-11 11:05:15.0033', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d0a30a0bf3714659ae96548c6c3ddc99', '2017-01-10 14:25:46', '2017-01-10 14:25:45.0548', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d0abb581218b4f389e6627775218e075', '2017-01-13 10:25:05', '2017-01-13 10:25:04.0566', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d0cd8e45d3b54c1e93073fc6d5a4db66', '2017-01-12 18:46:05', '2017-01-12 18:46:04.0660', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d20bd8a053a746f8b5d753a9cf45ac96', '2017-01-18 14:58:34', '2017-01-18 14:58:34.0238', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d2437e672da14a999a6e54b02ecd755c', '2017-01-18 11:55:09', '2017-01-18 11:55:08.0569', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d25750061e754616b667c73160afed92', '2017-01-11 10:09:00', '2017-01-11 10:08:59.0876', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d2686d3b4e284c44ab01eef11e61c77e', '2017-01-11 11:44:45', '2017-01-11 11:44:45.0449', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/role/update', '[修改角色]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d312b6a571f44bfdb2537b0f180b1c37', '2017-01-11 10:44:02', '2017-01-11 10:44:01.0778', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/site/list', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d32e09da697a4f2b995ac8e5c2a62a7c', '2017-01-18 11:55:03', '2017-01-18 11:55:03.0439', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d33bae12871d40a79b2b10380f5c923f', '2017-01-13 10:26:00', '2017-01-13 10:25:59.0561', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d34b202816f843598352950c6f25331c', '2017-01-18 11:37:16', '2017-01-18 11:37:15.0975', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d355b41254e240928b424d25c419902b', '2017-01-12 14:43:24', '2017-01-12 14:43:24.0485', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d3b8bc079fc04620a8ae0cf0486a33b4', '2017-01-11 11:18:33', '2017-01-11 11:18:33.0223', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d3c98c725d5949729fa3b6b935c6dd71', '2017-01-12 18:41:49', '2017-01-12 18:41:48.0543', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d3ea39beec6c4155a33f3c27d4fba942', '2017-01-18 14:10:30', '2017-01-18 14:10:30.0403', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d44e596a4cf64ea9ac9a33e8cc8cbb8c', '2017-01-12 14:02:50', '2017-01-12 14:02:50.0497', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d51b8880646e4b84abf585b182479075', '2017-01-18 11:07:41', '2017-01-18 11:07:40.0527', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d52ede09f71d4cb0825e2dc81b03fe45', '2017-01-11 17:24:29', '2017-01-11 17:24:28.0704', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/cmscontent/list', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d57b6b6f3f054c42ac086397eeaccd9e', '2017-01-18 14:50:32', '2017-01-18 14:50:31.0995', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d63107b92b5d4d5cbabe228f468924e3', '2017-01-10 14:19:51', '2017-01-10 14:19:51.0126', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d64b06a9367741e7b1e0686a1356b64f', '2017-01-18 14:10:25', '2017-01-18 14:10:25.0182', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d67eec6c06eb4c29ba9de506c478c8d2', '2017-01-10 14:18:55', '2017-01-10 14:18:55.0266', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d6863f667b8a41bc9743a50d737c1cb7', '2017-01-10 14:35:36', '2017-01-10 14:35:36.0453', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d724adb509bc41e8bc92ed7831a767ad', '2017-01-11 11:18:34', '2017-01-11 11:18:34.0357', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d7e8ab384fd7427ead3d43d5ba846dcb', '2017-01-12 19:07:56', '2017-01-12 19:07:56.0387', null, 'admin', 'admin', 'f2afac55-d9b7-49cd-b0f4-fa37b1fd792f', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d88652d0531a4013aaec56d04417a02b', '2017-01-11 10:59:24', '2017-01-11 10:59:24.0240', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d8b71107433041b49640985444fe0889', '2017-01-10 14:20:54', '2017-01-10 14:20:53.0836', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('d8fbbbef3a8341008c5db605edb27da6', '2017-01-18 14:39:53', '2017-01-18 14:39:52.0752', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d9694f50ac3d4777bb472a65bd591e9d', '2017-01-18 14:58:34', '2017-01-18 14:58:34.0011', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('d9bcb67669d445df9c4df1a1bb6add85', '2017-01-12 14:43:24', '2017-01-12 14:43:24.0132', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('da15ebde6eb74212b2a710c2d14fccef', '2017-01-12 18:47:09', '2017-01-12 18:47:09.0135', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('da32eaadca2443558bb26da9951ff6b0', '2017-01-11 10:57:15', '2017-01-11 10:57:15.0289', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('daf158aa5f9044f8a10cf510087874c4', '2017-01-13 10:21:51', '2017-01-13 10:21:50.0811', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('db05ab5ef9454f42838ac3119debde61', '2017-01-18 14:49:46', '2017-01-18 14:49:46.0163', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('db557efd96e9431d8ad3b58badb346d6', '2017-01-10 14:20:37', '2017-01-10 14:20:37.0193', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('dbae957b37604f66811971b85ae99c83', '2017-01-18 14:58:32', '2017-01-18 14:58:32.0107', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dbbe918da4414fa8917810599c04ef63', '2017-01-12 18:45:36', '2017-01-12 18:45:35.0532', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthird-party/codemirror/codemirror.css', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dbfa60728c824d34a154f0fa44ad303e', '2017-01-12 19:40:50', '2017-01-12 19:40:49.0982', null, 'admin', 'admin', '81e57aea-c9f8-42e1-8b94-71cf8c2964df', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dc0f75b085e244c79d7b444e26b1a0ad', '2017-01-12 19:22:08', '2017-01-12 19:22:08.0135', null, 'admin', 'admin', '1d1e55f9-081c-47cb-aff2-50375a9d2d63', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('dc2e5500d28f40df993dd0508a533e41', '2017-01-12 19:40:48', '2017-01-12 19:40:48.0154', null, 'admin', 'admin', '81e57aea-c9f8-42e1-8b94-71cf8c2964df', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('dc3f8cea433241679b0c04779d0b2429', '2017-01-17 10:47:54', '2017-01-17 10:47:53.0711', null, 'admin', 'admin', '6e171999-ca41-4f8c-a498-a318b6174528', '127.0.0.1', 'http://localhost:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dc480a72bc294fc0ac68473c8ce9312e', '2017-01-12 15:12:45', '2017-01-12 15:12:44.0691', null, 'admin', 'admin', '97494dcd-46fb-4fb4-bbb1-048237ed5810', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dc4fc0db70f2405fad34a63b6eaffc46', '2017-01-12 14:40:57', '2017-01-12 14:40:56.0984', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dc86d5f9837448cbab43208422bd9a9e', '2017-01-11 16:41:45', '2017-01-11 16:41:44.0712', null, 'admin', 'admin', '9fedc4d1-ce31-49cc-94c0-ae5185300371', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('dc8d474670fd40b1a893c4a896c13719', '2017-01-18 12:12:00', '2017-01-18 12:12:00.0301', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dca11dae4acd4046801dd330a8c6ec18', '2017-01-18 14:27:19', '2017-01-18 14:27:18.0755', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dcb8c0d06c434627ae98df57beb3f266', '2017-01-11 16:24:30', '2017-01-11 16:24:29.0577', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/delete', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dcd5ca1d3ab2409b81a4646e7f7b4fa0', '2017-01-19 10:09:58', '2017-01-19 10:09:58.0183', null, 'admin', 'admin', '1d2d8e75-faa6-4aa4-8976-34652e766fa3', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/', null, '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('dcdb50709fda4d228093c9677b53c4e1', '2017-01-12 15:19:50', '2017-01-12 15:19:50.0213', null, 'admin', 'admin', '1043ccac-d9e8-493f-8d39-6504147fe8e8', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dd1a036e893b41648ee50b058dc3d353', '2017-01-18 15:20:56', '2017-01-18 15:20:56.0314', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('dd47f359e6d3490cadc08307fccf3a21', '2017-01-12 18:35:06', '2017-01-12 18:35:06.0449', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dd54cf79a790499b85ba4de077de77c2', '2017-01-18 11:30:16', '2017-01-18 11:30:16.0114', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/', null, '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('dda463313d174348b45f704bee0bc766', '2017-01-12 15:08:33', '2017-01-12 15:08:32.0626', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ddb059987aa9475cad2492d2ab74ce84', '2017-01-12 15:19:23', '2017-01-12 15:19:22.0531', null, 'admin', 'admin', '1043ccac-d9e8-493f-8d39-6504147fe8e8', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('de049d2c2d2f4bbfa721cb5d8b862f8a', '2017-01-18 14:31:41', '2017-01-18 14:31:41.0234', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('de2317d70264467ab6414dff5ebf856e', '2017-01-18 14:58:31', '2017-01-18 14:58:31.0127', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('de299b97d96642fa80abfaa59ea08272', '2017-01-18 14:49:47', '2017-01-18 14:49:47.0011', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('de2dae1e09a345a6b006bb65d9a2036f', '2017-01-11 10:01:06', '2017-01-11 10:01:05.0830', null, 'admin', 'admin', '4b34858b-3c9d-4853-ad00-501a1d5057e4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/site/delete', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('de7b111c8b9342679c6b75c0e6a9e3f3', '2017-01-12 15:25:38', '2017-01-12 15:25:37.0907', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('df3206f9cbac4271a592ca20d017914b', '2017-01-18 14:09:40', '2017-01-18 14:09:39.0810', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('df39876a129b4370b991d72b1429c36e', '2017-01-12 18:45:36', '2017-01-12 18:45:35.0527', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/undefinedthird-party/codemirror/codemirror.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('df6391c44f75424cbb1b09585ce39c24', '2017-01-12 13:54:11', '2017-01-12 13:54:11.0333', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('df9b11a871c249a59483bc3503db62a2', '2017-01-18 14:58:09', '2017-01-18 14:58:09.0283', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('df9fa62bb2e94659a080832a8aafa43c', '2017-01-10 14:25:46', '2017-01-10 14:25:46.0456', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('dfac0b47a09141ba9ee155f05173e0af', '2017-01-13 14:10:38', '2017-01-13 14:10:38.0346', null, 'admin', 'admin', '479e8dba-8177-45fe-9f61-6dc36210a0e1', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('dff228a2797d4533be665a259b53a917', '2017-01-11 11:01:46', '2017-01-11 11:01:46.0413', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e02fe28a6beb470891d4bda083548af5', '2017-01-11 09:56:34', '2017-01-11 09:56:33.0762', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e04ab9f083da475b86d3fa95dd0d1ac7', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0559', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e08574d711ec4d52894813f7f5e9acb2', '2017-01-18 14:08:54', '2017-01-18 14:08:53.0972', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/', null, '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e132458eab0e4b52a2f1bd02f905db52', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0647', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e168addc3d3042a28f4411ee722a97e6', '2017-01-10 14:24:44', '2017-01-10 14:24:44.0269', null, 'admin', 'admin', 'b4ebe5a0-2b88-49c2-8814-cda9cd040a37', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/grade/list', '[级别管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e17af240a2604a3b8d099ed3e349cac0', '2017-01-18 15:01:00', '2017-01-18 15:01:00.0149', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e1841a2e53594d829120c08a9751dc2f', '2017-01-11 10:59:05', '2017-01-11 10:59:05.0076', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e1fcb79b1eae4017bc887d5fb9790ce4', '2017-01-18 11:13:14', '2017-01-18 11:13:14.0391', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e26134e63d7a4127992ee2619e55a8d1', '2017-01-18 11:37:53', '2017-01-18 11:37:52.0925', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e2777ed34be8465a8475bef0f0d158a1', '2017-01-18 11:54:50', '2017-01-18 11:54:50.0154', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e2e2755605d34ca4b5f2dfdffb90a42a', '2017-01-12 14:08:08', '2017-01-12 14:08:07.0762', null, 'admin', 'admin', '83e929e2-918d-479f-821b-bf7e3d995c50', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e3d34a6a36d443768bb4af22a5981f20', '2017-01-13 10:22:51', '2017-01-13 10:22:50.0708', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e433af70c86c41208c3c876535f8a668', '2017-01-18 14:57:46', '2017-01-18 14:57:45.0561', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e4d695de0b7945339dac2b520df3c09f', '2017-01-18 14:31:41', '2017-01-18 14:31:40.0894', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e4d8b13e5e3f4bbcb4855f542e569648', '2017-01-12 11:58:00', '2017-01-12 11:58:00.0419', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e58201ea3b57414c8689e9997c78f431', '2017-01-12 13:53:35', '2017-01-12 13:53:35.0250', null, 'admin', 'admin', '6ffe4eb7-77a9-4b6f-9263-00d2e2a9b7ac', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e5ed052c659746baa78347642a87dcbb', '2017-01-18 14:50:34', '2017-01-18 14:50:34.0152', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e6267d62d3cd44a385eeff973ba80f7f', '2017-01-12 18:48:07', '2017-01-12 18:48:06.0581', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e6423aeaff534054827771630dc9a650', '2017-01-12 15:10:13', '2017-01-12 15:10:12.0608', null, 'admin', 'admin', '97494dcd-46fb-4fb4-bbb1-048237ed5810', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e653cdd5f57342f9b36e8448fbc101e9', '2017-01-11 11:03:32', '2017-01-11 11:03:32.0277', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e680f5649a3245af91e722822525ecba', '2017-01-12 14:38:57', '2017-01-12 14:38:57.0048', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e6e117f52b1644f4b7355f1e4dd77fd6', '2017-01-10 14:33:27', '2017-01-10 14:33:26.0857', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/minzu/list', '[民族管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e7129f9ea18949c6b384039dfeb1e7ab', '2017-01-11 17:26:33', '2017-01-11 17:26:33.0379', null, 'admin', 'admin', '6c2086af-e070-47cf-9ddc-3eb09dcde8f3', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e72ab5200b834094bfd33f4bfb9521b9', '2017-01-11 09:55:35', '2017-01-11 09:55:35.0437', null, 'admin', 'admin', '2c888c7f-55ba-4b57-855a-d14224d9fa1b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e77cf8cbefc14224a327164037e487d9', '2017-01-10 14:26:14', '2017-01-10 14:26:13.0789', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e7daea79059d4a34b060b69b6dea34a8', '2017-01-12 18:43:54', '2017-01-12 18:43:54.0471', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e7f5cfeac3a1419f94687717ea1c16f8', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0583', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e8d99b65f28942efa9422771852cb361', '2017-01-12 14:08:10', '2017-01-12 14:08:10.0441', null, 'admin', 'admin', '83e929e2-918d-479f-821b-bf7e3d995c50', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e8e42acb541f4dfeb7d670c1d8ddd382', '2017-01-12 18:45:12', '2017-01-12 18:45:11.0515', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/ueditorConfig/init', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e8f210d2f6ed4e33948278b520af0c51', '2017-01-10 14:21:10', '2017-01-10 14:21:10.0284', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('e94b046bc7e842e78219524834eafefe', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0714', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e9c76aecf3d649f2a982f9e5fb989cb1', '2017-01-12 16:05:12', '2017-01-12 16:05:11.0712', null, 'admin', 'admin', '0777777b-0826-47c7-9ff6-93ccdda0513d', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('e9cf28ef7e044171b3fa7d2293ee814b', '2017-01-12 14:12:33', '2017-01-12 14:12:32.0597', null, 'admin', 'admin', 'e071116e-b364-4a51-a488-64f340ff3599', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ea2bedacfed643f68d163e8c6d1de841', '2017-01-18 14:09:37', '2017-01-18 14:09:36.0888', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ea57e37d74b54b0cb127662245f667b7', '2017-01-12 09:48:12', '2017-01-12 09:48:11.0658', null, 'admin', 'admin', 'a43ddec1-d5a4-4b20-acca-ccc568a8fa78', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('eacadf89604749d4b38a31438b474d18', '2017-01-18 14:48:56', '2017-01-18 14:48:56.0477', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('eacfc39a7a6645cab002f9f8332f63fd', '2017-01-12 12:17:54', '2017-01-12 12:17:53.0865', null, 'admin', 'admin', 'ee4ffd11-e144-43ff-8c0f-f4cf892e3778', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('eb621442eab54d419b195fa1678d0c89', '2017-01-18 15:24:20', '2017-01-18 15:24:20.0425', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('eb7eed9ed6264c6a9dd743fe972072cf', '2017-01-11 13:39:35', '2017-01-11 13:39:34.0625', null, 'admin', 'admin', '9edd6325-8440-4c51-92c9-beae0370a8a7', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/update', '[修改菜单]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('eb9de0aa3c0f4a1fbe7bf99bf88b2770', '2017-01-11 11:18:38', '2017-01-11 11:18:37.0879', null, 'admin', 'admin', 'cc29ccfd-adc0-4070-b46c-823fbe81baf1', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ebdf224303654b289da48dbd9628cd29', '2017-01-18 11:13:47', '2017-01-18 11:13:46.0810', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ec15cb8c14694515abeab403d7f86a9a', '2017-01-13 10:22:00', '2017-01-13 10:21:59.0685', null, 'admin', 'admin', 'ceeb7d1d-080d-4f86-907d-e33539fda744', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ec1a29c1bd6846f49a1492932390e02b', '2017-01-12 19:35:35', '2017-01-12 19:35:34.0796', null, 'admin', 'admin', '35616fb5-6b83-4707-a604-52e68f109437', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ec3af1bd79524932b997b1f4155213d2', '2017-01-10 14:36:13', '2017-01-10 14:36:13.0105', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ec3ce7d3fe3049fb954be2d713967ac5', '2017-01-18 14:14:22', '2017-01-18 14:14:22.0231', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ec8c3df2fec0484b8cc37642202c0bff', '2017-01-12 14:46:26', '2017-01-12 14:46:26.0189', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ec8eaab00ada470a85600f165aa44418', '2017-01-11 10:57:07', '2017-01-11 10:57:07.0031', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ed2c9eb2e9814ee9addba229c029cd34', '2017-01-12 14:00:05', '2017-01-12 14:00:05.0163', null, 'admin', 'admin', '62a2b596-f843-45ac-9d60-4e297251c6eb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ed8a9fe08a1e4030a69d89d381154699', '2017-01-12 18:36:08', '2017-01-12 18:36:07.0941', null, 'admin', 'admin', '9fdd050f-a8ee-4afe-9253-bbc4319c23f4', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('edbef7da8e5541d0bc6bf74a813f6adb', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0618', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('edc2bb1ed3c6497eaf7673b63b5c37dd', '2017-01-11 09:59:28', '2017-01-11 09:59:27.0944', null, 'admin', 'admin', '552bfbd7-247c-41ff-8bb9-12adc5f08e1c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list/json', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('edec2949a7d140bfb68566c1cd9e640a', '2017-01-18 11:53:45', '2017-01-18 11:53:45.0119', null, 'admin', 'admin', '6027aa6b-532e-4f67-b589-abacd04b1b09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ee086789dc3d46829943828462c64d85', '2017-01-18 14:58:34', '2017-01-18 14:58:33.0550', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ee0974e54b394653916b93be6c28d473', '2017-01-11 11:05:01', '2017-01-11 11:05:00.0535', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ee0a0aa5004246c29f56e777c2719a31', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0693', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ee0f4c7ba3f34b898d87e5dee287b8ae', '2017-01-12 19:40:47', '2017-01-12 19:40:46.0524', null, 'admin', 'admin', '81e57aea-c9f8-42e1-8b94-71cf8c2964df', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ee513607a07a4006a14add2212f4b224', '2017-01-12 10:34:27', '2017-01-12 10:34:26.0886', null, 'admin', 'admin', '9e32099e-b6f5-4eda-9132-59157483910e', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/js/ueditor/lang/zh-cn/zh-cn.js', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ee57624076cc4952bad7d8062b1eae68', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0921', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('eea409e15028493983b12425168baabe', '2017-01-18 14:10:30', '2017-01-18 14:10:30.0060', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('eec4a0363f9641a59760b5bc993b4571', '2017-01-12 14:53:42', '2017-01-12 14:53:41.0876', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('eeda1ba4a1214c52b227d16cd0cdb8ad', '2017-01-10 14:25:27', '2017-01-10 14:25:26.0523', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ef21c0ba23d24c1aa0fee67ef59bda64', '2017-01-11 11:01:42', '2017-01-11 11:01:41.0803', null, 'admin', 'admin', '1f836ded-3dcd-4337-84ea-9e0900b6a8d6', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ef3d0e5399f24d52baafe9c60fd692f6', '2017-01-12 14:03:20', '2017-01-12 14:03:20.0369', null, 'admin', 'admin', '8e339f9d-a371-4987-ba0b-6e666e2af40a', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ef6abc8544614406b0205e5ed26bfbd8', '2017-01-19 10:09:59', '2017-01-19 10:09:58.0970', null, 'admin', 'admin', '1d2d8e75-faa6-4aa4-8976-34652e766fa3', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('efad031607154ff4b8a9401a007e500d', '2017-01-11 11:08:17', '2017-01-11 11:08:17.0255', null, 'admin', 'admin', 'f14f3cbb-9104-48d7-8c67-9c6ffe91e647', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f005c3353bdd4af0a8d7f16b00da90c7', '2017-01-18 12:20:12', '2017-01-18 12:20:11.0927', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f0442094c63b429696d7f99d0921224f', '2017-01-12 18:45:35', '2017-01-12 18:45:34.0591', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f0490a8c0fca4ccabd47c8744716570e', '2017-01-18 14:58:31', '2017-01-18 14:58:31.0232', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f09e3e4208c84a7ca1d078b2672b134f', '2017-01-10 14:50:29', '2017-01-10 14:50:29.0066', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f0a84fb8fb5b4ad8bd8e8beb6beb9a8f', '2017-01-12 15:06:35', '2017-01-12 15:06:35.0312', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f0ba25bf9cba4cee9edaea621447dd8c', '2017-01-18 11:20:05', '2017-01-18 11:20:04.0854', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f10aa921dfeb4991b9d08141ab8e5635', '2017-01-18 11:37:46', '2017-01-18 11:37:45.0594', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f113d16285584a04ae565749c17d7008', '2017-01-12 18:45:09', '2017-01-12 18:45:08.0727', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f156b2e7349147c09889c184e694f62f', '2017-01-10 14:25:28', '2017-01-10 14:25:27.0908', null, 'admin', 'admin', 'be1eaaf5-0e3e-40b6-88dd-c508456f2542', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f1aeab88a21e471fac7feccffbea109e', '2017-01-18 14:50:13', '2017-01-18 14:50:13.0132', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f1c0bc549ff946e58684b0f2836ccd8a', '2017-01-18 14:27:29', '2017-01-18 14:27:28.0640', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f1f6dfc738fd4dc5985dd6b35815826a', '2017-01-18 14:08:56', '2017-01-18 14:08:55.0537', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f2770a9879ea465590602a65c2664d43', '2017-01-12 13:55:17', '2017-01-12 13:55:17.0288', null, 'admin', 'admin', '88d414ed-58dd-48b2-9ba3-177907b0da1c', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f281d4c8c5284656bc6c0049c3634191', '2017-01-11 10:00:59', '2017-01-11 10:00:59.0111', null, 'admin', 'admin', '4b34858b-3c9d-4853-ad00-501a1d5057e4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f28302d82ade4214bec7ca95b6dd9c87', '2017-01-18 14:37:54', '2017-01-18 14:37:54.0373', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f286019753534dab8b621704dfe18b19', '2017-01-18 14:09:24', '2017-01-18 14:09:23.0545', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f319fdad135c4cba88040de8eeabc5a9', '2017-01-12 13:55:14', '2017-01-12 13:55:14.0146', null, 'admin', 'admin', '88d414ed-58dd-48b2-9ba3-177907b0da1c', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f3268aedb3b54493b007e796927d5cef', '2017-01-11 15:43:16', '2017-01-11 15:43:15.0750', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f334bc3cf8044a5d8feb1d6d38515384', '2017-01-11 15:53:39', '2017-01-11 15:53:39.0325', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f3698acc25a644d299ef2b48e01a3a9d', '2017-01-18 14:27:16', '2017-01-18 14:27:16.0133', null, 'admin', 'admin', '81f69d9a-3108-41ac-8c13-5ee2d3d45c30', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f39dabf7668c46ca939d302187caa994', '2017-01-11 10:56:03', '2017-01-11 10:56:03.0100', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f3ad8fa622714f17924650c79f00adf3', '2017-01-12 14:46:34', '2017-01-12 14:46:33.0934', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f43e3158a4c84156aead06005afe49f9', '2017-01-10 14:20:50', '2017-01-10 14:20:49.0690', null, 'admin', 'admin', '026cb068-264d-4a76-b8bc-def8b9065e2c', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f521a92976a442b49becb085e9480813', '2017-01-18 11:20:07', '2017-01-18 11:20:06.0981', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f60619f737a64d529f7a26b2545472b1', '2017-01-11 13:53:26', '2017-01-11 13:53:26.0474', null, 'admin', 'admin', 'f081c6f4-5160-47e5-ab0c-a98a3ad10c4d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[分类管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f60664bcc4e4419782a98551b9421b84', '2017-01-18 11:57:58', '2017-01-18 11:57:57.0825', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f6388c344c884c28a399edf07dd35213', '2017-01-18 11:37:23', '2017-01-18 11:37:22.0691', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f6c67158c74842709f464c1f58187e92', '2017-01-12 14:12:25', '2017-01-12 14:12:24.0708', null, 'admin', 'admin', 'e071116e-b364-4a51-a488-64f340ff3599', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f7521dc787464cd089e43868b4f0bcab', '2017-01-18 11:31:00', '2017-01-18 11:30:59.0765', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f808c9e25b2c4ee5b11458aa754ce9d4', '2017-01-18 11:37:55', '2017-01-18 11:37:55.0405', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f83de1ab55374026ba53a3efab6d9396', '2017-01-18 11:37:55', '2017-01-18 11:37:55.0292', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f8d5015e7bbc40c98e4d4a2e562954e8', '2017-01-12 11:37:08', '2017-01-12 11:37:07.0961', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f9176a61e2774a8185d99adc9e0fb4ea', '2017-01-10 10:44:30', '2017-01-10 10:44:30.0449', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f9657ebf16cc481ab0502a021bdf864e', '2017-01-18 14:31:41', '2017-01-18 14:31:40.0998', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('f976822227f34722a37e1ba54c019fbb', '2017-01-10 14:37:20', '2017-01-10 14:37:20.0234', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/list', '[站点管理]', '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('f9a62cad589a415fb310e82963f7fb2a', '2017-01-18 15:21:10', '2017-01-18 15:21:09.0719', null, 'admin', 'admin', '489404b0-59ad-41bb-8a99-da210c83ec09', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fa31c16f77774876bff38ee91f8f073d', '2017-01-18 11:37:53', '2017-01-18 11:37:52.0707', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('faaa956ae3384a17a12ed3c7162e4bbf', '2017-01-12 11:51:20', '2017-01-12 11:51:19.0823', null, 'admin', 'admin', '02a9d907-7e7c-474c-bd5e-2ea4a88e27f5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('faafcf053e3848d1a3d73893f4e3f7f0', '2017-01-18 14:49:37', '2017-01-18 14:49:36.0592', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fab77d2e42014c198248dd775af4569d', '2017-01-18 11:55:02', '2017-01-18 11:55:02.0134', null, 'admin', 'admin', 'dd5fffb6-973b-4b99-925d-1514ab14f6d5', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fb29dd9680bd4759ab3e5e796d92516c', '2017-01-12 14:45:08', '2017-01-12 14:45:08.0458', null, 'admin', 'admin', '998d29fd-3fee-4da8-a586-39205d310d34', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fb4356aeac6d4e3a9bcf7a22973658d9', '2017-01-10 14:27:01', '2017-01-10 14:27:01.0464', null, 'admin', 'admin', '3b68d476-3d9d-46b8-a72e-eef0bc5a933b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/org/list/json', '[部门管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fb533093123f4cfdb998eef1b6b36468', '2017-01-11 10:25:45', '2017-01-11 10:25:44.0800', null, 'admin', 'admin', '720f6333-1805-468d-ad53-bc8031fa4fde', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/site/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fb630d744d4742a59892f9d73de828ec', '2017-01-11 09:55:17', '2017-01-11 09:55:16.0581', null, 'admin', 'admin', '2c888c7f-55ba-4b57-855a-d14224d9fa1b', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/auditlog/list', '[修改日志]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fb9e2f5bffd240e1a6eeea66e396c9e3', '2017-01-12 10:38:00', '2017-01-12 10:38:00.0323', null, 'admin', 'admin', '2ec1cc3e-4092-4a3f-baf8-34234e8b3d65', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fbe9dc4027724fc68686ceb20a73c739', '2017-01-18 11:38:06', '2017-01-18 11:38:05.0673', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/update', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fbf166d6c8e349d9b2bdfb85243d699c', '2017-01-12 19:33:42', '2017-01-12 19:33:42.0073', null, 'admin', 'admin', 'faf1f11c-da15-4cd6-b13a-582ac829aeb5', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fc2b0ff4688f41ccb11b7b2160057386', '2017-01-12 14:38:05', '2017-01-12 14:38:04.0711', null, 'admin', 'admin', '86654863-c258-4430-bdcc-6709a3a6cd0f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fc8abffe5fef4c449394b65d7fa39b19', '2017-01-18 14:58:32', '2017-01-18 14:58:31.0561', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fccd7f52df8b468c93f9a96377f49c86', '2017-01-12 18:46:20', '2017-01-12 18:46:19.0508', null, 'admin', 'admin', '353d5521-670d-40a4-96c9-dbc7e13004cd', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fd01726e523c44de84b8b171d8160a82', '2017-01-18 14:30:07', '2017-01-18 14:30:06.0977', null, 'admin', 'admin', '29d8e9b4-b9e6-40cf-9cb7-e9f7d480f73c', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fdbc84cd08894c0d8368938c734ae612', '2017-01-18 14:08:54', '2017-01-18 14:08:54.0133', null, 'admin', 'admin', '1dea728e-b96f-4857-a886-f24cb3e4ad07', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fe113478277a480bb13bf4ebe40e699c', '2017-01-18 11:19:40', '2017-01-18 11:19:40.0173', null, 'admin', 'admin', 'db2ee2c7-0c01-4601-8a13-3f5c0e401c57', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/channel/list', '[栏目管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fe6322e0323740f392ffa234d6ba92a6', '2017-01-18 14:50:28', '2017-01-18 14:50:28.0100', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/list', '[内容管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fe6461a91111496482a10ce83dadb160', '2017-01-10 14:22:04', '2017-01-10 14:22:04.0408', null, 'admin', 'admin', 'c59f18f4-412c-48e4-bcff-3880fca966c4', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/list', '[菜单管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('fe84859b1e41448ba686e5f142de3b9d', '2017-01-18 14:58:25', '2017-01-18 14:58:25.0174', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('feceec4031bd435db61cb19aaa55e375', '2017-01-18 14:58:33', '2017-01-18 14:58:32.0668', null, 'admin', 'admin', '2839fdcd-de52-4e68-93a3-05a18dcfced7', '127.0.0.1', 'http://127.0.0.1:8088/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ff44dad37ed947b9b027d2092a0afd0c', '2017-01-10 10:44:48', '2017-01-10 10:44:48.0191', null, 'admin', 'admin', 'b3b12f54-4aff-4a86-a2ff-278bfdcc278f', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/dicdata/xueli/list', '[学历管理]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ff4a60b6fbf44c789c87f939718f218b', '2017-01-10 14:19:50', '2017-01-10 14:19:49.0795', null, 'admin', 'admin', '2791f839-88a8-4d7f-a9cc-e16d1f5c40bf', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/menu/update', '[修改菜单]', '是');
INSERT INTO `t_fwlog_history_2017` VALUES ('ff80ddc54d6244ba9f7640fe7637d282', '2017-01-12 18:54:02', '2017-01-12 18:54:01.0593', null, 'admin', 'admin', '1534998f-ddbc-4cf0-8dc5-d640539678ad', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ffa45f3316114c008368eee7524924e8', '2017-01-18 11:38:15', '2017-01-18 11:38:15.0206', null, 'admin', 'admin', '81ce132c-0e92-439e-91d3-1e8f16347403', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/cms/content/update/pre', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ffdf1e6178a74bcc9bafe5dce86388cc', '2017-01-11 15:43:12', '2017-01-11 15:43:12.0269', null, 'admin', 'admin', '5d2986f3-d1f8-4796-b755-3c4ccee2bb8d', '127.0.0.1', 'http://127.0.0.1:8080/springrain/system/index', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('fff076384b8143f69647e81a4b5daf5c', '2017-01-12 15:27:25', '2017-01-12 15:27:25.0294', null, 'admin', 'admin', 'edb93319-f774-43c3-aa0f-66fcb40dd3cb', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/channelList', null, '否');
INSERT INTO `t_fwlog_history_2017` VALUES ('ffff7bea8e6b4773a501db84e84e856a', '2017-01-12 12:33:59', '2017-01-12 12:33:58.0577', null, 'admin', 'admin', '792b32ba-af19-44c0-8b07-e217416c9b08', '117.158.210.232', 'http://cxy.wxyapp.com/springrain/system/cms/content/update/pre', null, '否');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('169815aca9cf41d390e7feb6629d361d', '栏目管理', 'business_manager', '', '/system/cms/channel/list', '1', '1', '4', '&#xe60a;');
INSERT INTO `t_menu` VALUES ('b9c4e8ecffe949c0b346e1fd0d6b9977', '内容管理', 'business_manager', '内容管理', '/system/cms/content/list', '1', '1', '5', '&#xe63c;');
INSERT INTO `t_menu` VALUES ('business_manager', '业务管理', null, null, null, '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('dic_manager', '字典管理', null, null, null, '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('f41b9f3b4a0d45f5a3b5842ee40e0e96', '站点管理', 'business_manager', '', '/system/cms/site/list', '1', '1', '3', '&#xe641;');
INSERT INTO `t_menu` VALUES ('system_manager', '系统管理', null, null, null, '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_auditlog_list', '修改日志', 'system_manager', null, '/system/auditlog/list', '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_auditlog_look', '查看修改日志', 't_auditlog_list', null, '/system/auditlog/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_delete', '删除级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/delete', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_deletemore', '批量删除级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/delete/more', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_list', '级别管理', 'dic_manager', '', '/system/dicdata/grade/list', '1', '1', '1', '&#xe630;');
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_look', '查看级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_tree', '级别树形结构', 't_dic_data_grade_list', null, '/system/dicdata/grade/tree', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_update', '修改级别', 't_dic_data_grade_list', null, '/system/dicdata/grade/update', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_delete', '删除民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/delete', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_deletemore', '批量删除民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/delete/more', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_list', '民族管理', 'dic_manager', null, '/system/dicdata/minzu/list', '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_look', '查看民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_tree', '民族树形结构', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/tree', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_update', '修改民族', 't_dic_data_minzu_list', null, '/system/dicdata/minzu/update', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_delete', '删除学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/delete', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_deletemore', '批量删除学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/delete/more', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_list', '学历管理', 'dic_manager', '', '/system/dicdata/xueli/list', '1', '1', '3', '&#xe621;');
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_look', '查看学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_tree', '学历树形结构', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/tree', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_update', '修改学历', 't_dic_data_xueli_list', null, '/system/dicdata/xueli/update', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_fwlog_list', '访问日志', 'system_manager', null, '/system/fwlog/list', '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_fwlog_look', '查看访问日志', 't_fwlog_list', null, '/system/fwlog/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_menu_delete', '删除菜单', 't_menu_list', null, '/system/menu/delete', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_menu_deletemore', '批量删除菜单', 't_menu_list', null, '/system/menu/delete/more', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_menu_list', '菜单管理', 'system_manager', null, '/system/menu/list', '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_menu_look', '查看菜单', 't_menu_list', null, '/system/menu/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_menu_tree', '菜单树形结构', 't_menu_list', null, '/system/menu/tree', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_menu_update', '修改菜单', 't_menu_list', null, '/system/menu/update', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_org_delete', '删除部门', 't_org_list', null, '/system/org/delete', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_org_deletemore', '批量删除部门', 't_org_list', null, '/system/org/delete/more', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_org_list', '部门管理', 'business_manager', null, '/system/org/list', '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_org_look', '查看部门', 't_org_list', null, '/system/org/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_org_tree', '部门树形结构', 't_org_list', null, '/system/org/tree', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_org_update', '修改部门', 't_org_list', null, '/system/org/update', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_role_delete', '删除角色', 't_role_list', null, '/system/role/delete', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_role_deletemore', '批量删除角色', 't_role_list', null, '/system/role/delete/more', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_role_list', '角色管理', 'system_manager', '', '/system/role/list', '1', '1', null, '');
INSERT INTO `t_menu` VALUES ('t_role_look', '查看角色', 't_role_list', null, '/system/role/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_role_update', '修改角色', 't_role_list', null, '/system/role/update', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_user_delete', '删除用户', 't_user_list', null, '/system/user/delete', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_user_deletemore', '批量删除用户', 't_user_list', null, '/system/user/delete/more', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_user_list', '用户管理', 'business_manager', null, '/system/user/list', '1', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_user_list_export', '导出用户', 't_user_list', null, '/system/user/list/export', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_user_look', '查看用户', 't_user_list', null, '/system/user/look', '0', '1', null, null);
INSERT INTO `t_menu` VALUES ('t_user_update', '修改用户', 't_user_list', null, '/system/user/update', '0', '1', null, null);

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `name` varchar(60) DEFAULT NULL COMMENT '名称',
  `comcode` varchar(1000) DEFAULT NULL COMMENT '代码',
  `pid` varchar(50) DEFAULT NULL COMMENT '上级部门ID',
  `managerRoleId` varchar(100) DEFAULT NULL COMMENT '对应的角色Id',
  `sysid` varchar(50) DEFAULT NULL COMMENT '子系统ID',
  `orgType` int(11) DEFAULT NULL COMMENT '0组织机构 ,1.部门,2.虚拟权限组',
  `leaf` int(11) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `sortno` int(11) DEFAULT NULL COMMENT '排序号',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `active` int(11) DEFAULT '1' COMMENT '是否有效(0否,1是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES ('8ab05a1acfd54590942b88fc6d4d77ee', '测试部门', ',8ab05a1acfd54590942b88fc6d4d77ee,', null, null, null, null, null, '1', '', '1');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('admin', '管理员', null, null, '0', '', '1');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `roleId` varchar(50) NOT NULL COMMENT '角色编号',
  `menuId` varchar(50) NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`id`),
  KEY `fk_t_role_menu_roleId_t_role_id` (`roleId`),
  KEY `fk_t_role_menu_menuId_t_menu_id` (`menuId`),
  CONSTRAINT `fk_t_role_menu_menuId_t_menu_id` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `fk_t_role_menu_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单中间表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('001a1c624ce2429bbca105c58792fd8d', 'admin', 't_user_deletemore');
INSERT INTO `t_role_menu` VALUES ('0324dd16a332483a915483771d491842', 'admin', 't_dic_data_grade_update');
INSERT INTO `t_role_menu` VALUES ('087c9f7b0a6244a2920c76bb603216b6', 'admin', 't_role_update');
INSERT INTO `t_role_menu` VALUES ('0d2ea133cb27486baf771d4a5672f4d5', 'admin', 'dic_manager');
INSERT INTO `t_role_menu` VALUES ('19d04ce149d5471687ddf09dafd464ee', 'admin', 't_menu_list');
INSERT INTO `t_role_menu` VALUES ('1be39c19215d4eae941a001442c49da8', 'admin', 't_role_list');
INSERT INTO `t_role_menu` VALUES ('210b35dfa70c4d26907c2cb97bbeec90', 'admin', 'f41b9f3b4a0d45f5a3b5842ee40e0e96');
INSERT INTO `t_role_menu` VALUES ('2c12cc9b747541b6950fdb980554e7b6', 'admin', 't_dic_data_xueli_tree');
INSERT INTO `t_role_menu` VALUES ('2eab95dd4dda4241a8303fbf7047b2ca', 'admin', 't_menu_delete');
INSERT INTO `t_role_menu` VALUES ('2ef151ce8a8e45d09635f21337205d09', 'admin', 't_user_look');
INSERT INTO `t_role_menu` VALUES ('2fdf23167fd64d899183224aabc935c6', 'admin', 't_menu_tree');
INSERT INTO `t_role_menu` VALUES ('31422f219b874cf5985f9b4e3ccc0a00', 'admin', 't_auditlog_look');
INSERT INTO `t_role_menu` VALUES ('3294654b0aa247018264f7bbb95e447b', 'admin', 't_org_update');
INSERT INTO `t_role_menu` VALUES ('37789dbfad7140988b849b6f3e246ae5', 'admin', 't_dic_data_minzu_tree');
INSERT INTO `t_role_menu` VALUES ('3a0a3e3853cd466e8cf0bf1306cfc985', 'admin', 't_org_deletemore');
INSERT INTO `t_role_menu` VALUES ('3bfcf1b6d71847148c3717b4f58da91a', 'admin', 't_user_list_export');
INSERT INTO `t_role_menu` VALUES ('4a38cefab0874d5b83c6534c4b47aeda', 'admin', 't_dic_data_minzu_list');
INSERT INTO `t_role_menu` VALUES ('51a0a2d1e004422c83163813dee8a8ee', 'admin', 't_dic_data_xueli_look');
INSERT INTO `t_role_menu` VALUES ('560956a9f29943d0be1106db643bb009', 'admin', 'b9c4e8ecffe949c0b346e1fd0d6b9977');
INSERT INTO `t_role_menu` VALUES ('560e55ca7bc14e659bb2ba56706c45d7', 'admin', 't_user_delete');
INSERT INTO `t_role_menu` VALUES ('5776b6b6e3484213afbec97f79d3957c', 'admin', 't_fwlog_look');
INSERT INTO `t_role_menu` VALUES ('59616f2b6b364d1e89a7be64e053032c', 'admin', 't_dic_data_grade_delete');
INSERT INTO `t_role_menu` VALUES ('59d4b9ed98074717b4e5f9a995dd857d', 'admin', 't_role_deletemore');
INSERT INTO `t_role_menu` VALUES ('5e59a6a7ba11493e85f9ed86777157a7', 'admin', 't_menu_deletemore');
INSERT INTO `t_role_menu` VALUES ('60ea86d6422b4060ba9c257c4cc1eec4', 'admin', '169815aca9cf41d390e7feb6629d361d');
INSERT INTO `t_role_menu` VALUES ('65cb03bb4a4d4f33a6c213d0f1a5a0a6', 'admin', 't_org_look');
INSERT INTO `t_role_menu` VALUES ('665571fdc13e4a1985707c246d5686b0', 'admin', 't_user_list');
INSERT INTO `t_role_menu` VALUES ('691fe8c0ad844d2fa9feb1958193c69e', 'admin', 't_role_look');
INSERT INTO `t_role_menu` VALUES ('6b4179fa942d4ad9922ab8864f6393a7', 'admin', 't_dic_data_minzu_update');
INSERT INTO `t_role_menu` VALUES ('700476536d8643759a1849f1ffc9b9fd', 'admin', 't_auditlog_list');
INSERT INTO `t_role_menu` VALUES ('7203ca2bc497423b8247eeabc6e630bf', 'admin', 't_dic_data_grade_deletemore');
INSERT INTO `t_role_menu` VALUES ('7db22a6554a54385ad79614859cac705', 'admin', 't_dic_data_grade_look');
INSERT INTO `t_role_menu` VALUES ('8c8309acb74f4dcbbc614db07ac05def', 'admin', 't_fwlog_list');
INSERT INTO `t_role_menu` VALUES ('9536c440ea8142ab881c6335c903a7ad', 'admin', 'business_manager');
INSERT INTO `t_role_menu` VALUES ('a26b9aaaac874f26ba6170a6ec2e0237', 'admin', 't_dic_data_xueli_list');
INSERT INTO `t_role_menu` VALUES ('a755f59664694a01ba2d3f6060b4ce2a', 'admin', 't_dic_data_minzu_deletemore');
INSERT INTO `t_role_menu` VALUES ('b44a7f18a7d149b0a002b98cf04dda77', 'admin', 't_user_update');
INSERT INTO `t_role_menu` VALUES ('b7949985b3e545ddbb93a057a3e5c6c4', 'admin', 't_dic_data_minzu_look');
INSERT INTO `t_role_menu` VALUES ('b8aecccfd89d4355a3bb57164480977b', 'admin', 't_menu_update');
INSERT INTO `t_role_menu` VALUES ('ba8c13b6db6645fab3e573d4fe35521c', 'admin', 't_org_list');
INSERT INTO `t_role_menu` VALUES ('ba93b2e17dab4adcb78104b1fa7c13de', 'admin', 't_menu_look');
INSERT INTO `t_role_menu` VALUES ('bae8949f420d45e5a0af630524cb2aa6', 'admin', 't_dic_data_minzu_delete');
INSERT INTO `t_role_menu` VALUES ('bc72823cd678418d9318257ef6df35d3', 'admin', 't_dic_data_xueli_deletemore');
INSERT INTO `t_role_menu` VALUES ('bf04febd9a7949249b93491e4feaf0e5', 'admin', 't_role_delete');
INSERT INTO `t_role_menu` VALUES ('d1ead29d5b9e4bd8ad2abd1d245436b7', 'admin', 't_dic_data_grade_tree');
INSERT INTO `t_role_menu` VALUES ('df60df3f97ad4c4d8bf171d8b2259329', 'admin', 'system_manager');
INSERT INTO `t_role_menu` VALUES ('ece2acfdc14647a79b8ae46dfcd27b06', 'admin', 't_dic_data_xueli_delete');
INSERT INTO `t_role_menu` VALUES ('f2082e78793140038122e06cfc655e63', 'admin', 't_dic_data_grade_list');
INSERT INTO `t_role_menu` VALUES ('f6605e3916d44a9baffa173e85a2c427', 'admin', 't_org_delete');
INSERT INTO `t_role_menu` VALUES ('f69713ca657e4e20986a372b597e9363', 'admin', 't_org_tree');
INSERT INTO `t_role_menu` VALUES ('fb3b50a9a5ae4f4399bae84355b0b66d', 'admin', 't_dic_data_xueli_update');

-- ----------------------------
-- Table structure for t_role_org
-- ----------------------------
DROP TABLE IF EXISTS `t_role_org`;
CREATE TABLE `t_role_org` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `roleId` varchar(50) NOT NULL COMMENT '角色编号',
  `orgId` varchar(50) NOT NULL COMMENT '部门编号',
  `orgType` int(11) DEFAULT NULL COMMENT '0组织机构 ,1.部门,2.虚拟权限组',
  `hasLeaf` int(11) NOT NULL DEFAULT '0' COMMENT '是否包含子部门,0不包含,1包含',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用,0不可用,1不可用',
  PRIMARY KEY (`id`),
  KEY `fk_t_role_org_roleId_t_role_id` (`roleId`),
  KEY `fk_t_role_org_orgId_t_org_id` (`orgId`),
  CONSTRAINT `fk_t_role_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`),
  CONSTRAINT `fk_t_role_org_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色部门中间表';

-- ----------------------------
-- Records of t_role_org
-- ----------------------------

-- ----------------------------
-- Table structure for t_tableindex
-- ----------------------------
DROP TABLE IF EXISTS `t_tableindex`;
CREATE TABLE `t_tableindex` (
  `id` varchar(50) NOT NULL COMMENT '表名',
  `maxIndex` int(11) NOT NULL DEFAULT '1' COMMENT '表记录最大的行,一直累加',
  `prefix` varchar(50) NOT NULL COMMENT '前缀 单个字母加 _',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='记录表最大的行记录';

-- ----------------------------
-- Records of t_tableindex
-- ----------------------------
INSERT INTO `t_tableindex` VALUES ('cms_channel', '1001', 'h_');
INSERT INTO `t_tableindex` VALUES ('cms_content', '10001', 'c_');
INSERT INTO `t_tableindex` VALUES ('cms_site', '101', 's_');

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('admin', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', '男', null, null, null, '0', '1');

-- ----------------------------
-- Table structure for t_user_org
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `userId` varchar(50) NOT NULL COMMENT '用户编号',
  `orgId` varchar(50) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`id`),
  KEY `fk_t_user_org_userId_t_user_id` (`userId`),
  KEY `fk_t_user_org_orgId_t_org_id` (`orgId`),
  CONSTRAINT `fk_t_user_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`),
  CONSTRAINT `fk_t_user_org_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户部门中间表';

-- ----------------------------
-- Records of t_user_org
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(50) NOT NULL COMMENT '编号',
  `userId` varchar(50) NOT NULL COMMENT '用户编号',
  `roleId` varchar(50) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`),
  KEY `fk_t_user_role_userId_t_user_id` (`userId`),
  KEY `fk_t_user_role_roleId_t_role_id` (`roleId`),
  CONSTRAINT `fk_t_user_role_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`),
  CONSTRAINT `fk_t_user_role_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色中间表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('admin_admin', 'admin', 'admin');



-- 20170120修改表t_user_org
alter table t_user_org add ismanager int(11) NOT NULL DEFAULT '1' COMMENT '是否主管(0不是1是)';
alter table t_user_org add hasleaf int(11) NOT NULL DEFAULT '1' COMMENT '是否包含子部门(0不包含1包含)';

