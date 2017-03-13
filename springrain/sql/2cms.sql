

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_banner
-- ----------------------------
DROP TABLE IF EXISTS `cms_banner`;
CREATE TABLE `cms_banner` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `siteId` varchar(50) NOT NULL COMMENT '站点ID',
  `businessId` varchar(50) NOT NULL COMMENT '业务id',
  `imgSrc` varchar(255) NOT NULL COMMENT '图片路径',
  `imgAlt` varchar(255) DEFAULT NULL COMMENT '图片提示语',
  `redirectUrl` varchar(255) DEFAULT NULL COMMENT '跳转路径',
  `sortno` int(11) DEFAULT NULL COMMENT '排序',
  `active` int(11) DEFAULT NULL COMMENT '0 禁用  1启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_banner
-- ----------------------------
INSERT INTO `cms_banner` VALUES ('1e25657eadde42038fca02a175efddc8', 's_10006', 'h_10013', '[{\"prefix\":\"jpg\",\"name\":\"java\",\"path\":\"/upload/s_10006/h_10013/c2d151a0812e41f99e1340e29ef22b70java.jpg\",\"size\":\"304943722496\"}]', '11', '11', '11', '1');
INSERT INTO `cms_banner` VALUES ('4881297f157b45a4aad66364021d5e2b', 's_10006', 'h_10013', '[{\"prefix\":\"jpg\",\"name\":\"jquery\",\"path\":\"/upload/s_10006/h_10013/404454938561477aae7b6967224c6900jquery.jpg\",\"size\":\"304943722496\"}]', 'jquery', 'http://www.baidu.com', '2', '1');
INSERT INTO `cms_banner` VALUES ('be9bf58ec0764e10871753dd98df2d21', 's_10006', 'h_10013', '[{\"prefix\":\"jpg\",\"name\":\"java\",\"path\":\"/upload/s_10006/h_10013/5e714605d7294a4d9e944ca34d0245a8java.jpg\",\"size\":\"304943722496\"}]', 'javas', 'http://www.baidu.com', '1', '1');

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
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='栏目表';

-- ----------------------------
-- Records of cms_channel
-- ----------------------------
INSERT INTO `cms_channel` VALUES ('h_10009', '新闻发布', null, ',h_10009,', 's_10006', '1', '1', '1', null, '1', null, '1');
INSERT INTO `cms_channel` VALUES ('h_10010', '首页', null, ',h_10010,', 's_10006', '1', '1', '1', null, '1', null, '1');
INSERT INTO `cms_channel` VALUES ('h_10011', '服务号信息配置', 's_10006', ',h_10011,', 's_10006', '1', '1', '服务号信息配置', null, '3', null, '1');
INSERT INTO `cms_channel` VALUES ('h_10012', '微信菜单管理', null, ',h_10012,', 's_10006', '1', '微信菜单管理', '微信菜单管理', null, '4', null, '1');
INSERT INTO `cms_channel` VALUES ('h_10013', 'banner管理', null, ',h_10013,', 's_10006', '1', '1', '1', null, '4', null, '1');
INSERT INTO `cms_channel` VALUES ('h_10014', '11', 's_10006', ',h_10014,', 's_10006', '1', '11', '1', null, '1', null, '1');

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
INSERT INTO `cms_channel_content` VALUES ('02da641032de45c792399b211dc92bd2', 's_10002', 'h_10001', 'c_100001', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('134335b6a9e64c95af8b5a4055bead64', 's_10006', 'h_10009', 'c_100009', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('17c9c9a4d16644c3b7e95e6ffbaf75db', 's_10003', 'h_10002', 'c_100002', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('43a8e5d8c9f94510a2feae50ff9260a3', 's_10006', 'h_10009', 'c_100013', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('60799735dfe24d8a9e9df95d5786ac87', 's_10006', 'h_10009', 'c_100007', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('7e28b813577b4bb4a39ae974f2687c0e', 's_10006', 'h_10009', 'c_100008', '2', '1');
INSERT INTO `cms_channel_content` VALUES ('800d38e7bec546aeb1ae900cc5d4d662', 's_10005', 'h_10007', 'c_100006', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('82d49a148afb4bf3bfbf63fabded2781', 's_10006', 'h_10009', 'c_100018', '100018', '0');
INSERT INTO `cms_channel_content` VALUES ('8835965d75ae47c4a33059368b33e940', 's_10006', 'h_10009', 'c_100015', '100015', '1');
INSERT INTO `cms_channel_content` VALUES ('9248697cbc484f2296594b731be055d1', 's_10003', 'h_10002', 'c_100003', '2', '1');
INSERT INTO `cms_channel_content` VALUES ('a3b0ca60e48d4f8d8146259df3e29b6d', 's_10006', 'h_10009', 'c_100014', '2', '1');
INSERT INTO `cms_channel_content` VALUES ('a52e8432509841cb8ea1ceb54345bf3f', 's_10006', 'h_10009', 'c_100012', '2', '1');
INSERT INTO `cms_channel_content` VALUES ('b9a91eddf4b7428ab0bf6741230025c0', 's_10006', 'h_10009', 'c_100011', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('c7d9d1f437334473acdba5720bc15a57', 's_10006', 'h_10009', 'c_100010', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('d48a5eedd5614737bb984b300c17b857', 's_10006', 'h_10009', 'c_100017', '22', '1');
INSERT INTO `cms_channel_content` VALUES ('ddcf1438fa5947d2a35df14103ccf074', 's_10006', 'h_10009', 'c_100016', '11', '1');
INSERT INTO `cms_channel_content` VALUES ('e386f580edaa4298a381f09b2b20ace7', 's_10003', 'h_10003', 'c_100004', '1', '1');
INSERT INTO `cms_channel_content` VALUES ('f78c015d5048495eb50c8275af5f46c1', 's_10004', 'h_10005', 'c_100005', '1', '1');

-- ----------------------------
-- Table structure for cms_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL COMMENT '评论用户ID',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `pid` varchar(50) NOT NULL COMMENT '父级评论Id',
  `siteId` int(11) NOT NULL COMMENT '站点ID',
  `createDate` datetime NOT NULL COMMENT '评论时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `content` longtext COMMENT '评论内容',
  `ups` smallint(6) NOT NULL DEFAULT '0' COMMENT '支持数',
  `downs` smallint(6) NOT NULL DEFAULT '0' COMMENT '反对数',
  `checked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否审核',
  `score` int(11) DEFAULT NULL COMMENT '评分',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `userName` varchar(50) DEFAULT NULL COMMENT '姓名',
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  `bak5` varchar(255) DEFAULT NULL,
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
  `content` text COMMENT '内容',
  `keywords` varchar(1000) DEFAULT NULL COMMENT '关键字',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `lookcount` int(11) DEFAULT NULL COMMENT '打开次数',
  `createPerson` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `source` varchar(1000) DEFAULT NULL COMMENT '来源',
  `sourceurl` varchar(1000) DEFAULT NULL COMMENT '来源地址',
  `status` int(11) DEFAULT NULL COMMENT '状态  0未审核  1审核通过',
  `active` int(11) DEFAULT NULL COMMENT '是否可用',
  `commentPerm` int(11) DEFAULT NULL COMMENT '评论开关  0不允许评论  1 允许评论',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `sortno` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容表';

-- ----------------------------
-- Records of cms_content
-- ----------------------------
INSERT INTO `cms_content` VALUES ('c_100007', 'test2', 'test1', null, '<p>dsf&nbsp;</p>', 'test1', 'test1', null, null, '2017-02-17 15:04:16', 'test1', 'test1', null, '1', '0', null, '1');
INSERT INTO `cms_content` VALUES ('c_100015', 't1', 't1', null, '<p>t1t1t1t1t1t1t1t1t1t1t1t1t1t1t1t1t1t1</p>', 't1', 't1', null, null, '2017-02-21 17:09:00', 't1', 't1', null, '1', '1', null, '100015');
INSERT INTO `cms_content` VALUES ('c_100016', '11', '11', null, '<p>1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111</p>', '11', '11', null, null, '2017-02-23 12:18:56', '11', '11', null, '1', '1', null, '11');
INSERT INTO `cms_content` VALUES ('c_100017', '22', '22', null, '<p>22222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222</p>', '22', '22', null, null, '2017-02-23 16:32:58', '22', '22', null, '1', '1', null, '22');

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
  `modelType` int(11) NOT NULL DEFAULT '0' COMMENT '0site,1channel,2content,3投票 4,站长后台模板(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `ftlfile` varchar(1000) NOT NULL COMMENT '当前渲染使用的模板路径',
  `nodeftlfile` varchar(1000) DEFAULT NULL COMMENT '子内容使用的ftl模板文件',
  `statichtml` int(11) NOT NULL DEFAULT '0' COMMENT '是否静态化 0否,1是',
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  `jumpType` int(11) DEFAULT NULL COMMENT '跳转方式',
  `loginuser` int(11) DEFAULT NULL COMMENT '是否需要登录访问  0否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务链接表';

-- ----------------------------
-- Records of cms_link
-- ----------------------------
INSERT INTO `cms_link` VALUES ('0dd704d2f9744f77890d29bc271f8ac9', '2test', '/f/mp/s_10006/c_100012', '/f/mp/s_10006/c_100012', 's_10006', 'c_100012', '1', '0', '/u/s_10006/f/content', null, '0', '2', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('0fb1a65a3aef478381f04f5afe7c7e61', '微信菜单管理', '/f/mp/s_10006/h_10012', '/f/mp/s_10006/h_10012', 's_10006', 'h_10012', '1', '0', '/u/s_10006/channel', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('141e3133bbb64fdab2b7a4d120d1ccc0', '11', '/s/s_10006/h_10014/content/list', '/s/s_10006/h_10014/content/list', 's_10006', 'h_10014', '1', '4', '1', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('164e0a7b453d4d84a552cf2e370d8410', 'banner管理', '/s/s_10006/h_10013/banner/list', '/s/s_10006/h_10013/banner/list', 's_10006', 'h_10013', '1', '4', '/u/s_10006/s/banner/bannerList', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('19836e0016a149ea95a799297cd43791', '首页', '/s/s_10006/h_10010/site/list', '/s/s_10006/h_10010/site/list', 's_10006', 'h_10010', '1', '4', '/u/s_10006/s/site/siteList', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('1d6fc5e3e1904704a5bea82418401ab5', '站长后台测试站', '/f/mp/s_10006/index', '/f/mp/s_10006/index', 's_10006', 's_10006', '1', '0', '/u/s_10006/index', '/u/s_10006/channel', '0', '1', '0', '0', null);
INSERT INTO `cms_link` VALUES ('295dcd778a3b463798261531140ece76', '11', '/f/mp/s_10006/c_100016', '/f/mp/s_10006/c_100016', 's_10006', 'c_100016', '1', '0', '/u/s_10006/f/content', null, '0', '11', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('2b7de34e40794a1fa621596bf801cb0a', '站长后台测试站', '/s/s_10006/index', '/s/s_10006/index', 's_10006', 's_10006', '1', '4', '/u/s_10006/s/index', null, '0', '1', '0', '0', null);
INSERT INTO `cms_link` VALUES ('2b9c60eea773463ebe95f182b23de48a', '首页', '/f/mp/s_10006/h_10010', '/f/mp/s_10006/h_10010', 's_10006', 'h_10010', '1', '0', '/u/s_10006/channel', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('3d2481159c0c4b6fa9a1714720a69012', '1test', '/f/mp/s_10006/c_100013', '/f/mp/s_10006/c_100013', 's_10006', 'c_100013', '1', '0', '/u/s_10006/f/content', null, '0', '1', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('4391b4e677f04f25a654aae9d3ec5b24', '微信菜单管理', '/s/s_10006/h_10012/mp/menu/update/pre', '/s/s_10006/h_10012/mp/menu/update/pre', 's_10006', 'h_10012', '1', '4', '/u/s_10006/s/mp/menu/menuCru', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('4df111cb-f718-11e6-9a02-002421b6d6a2', '站点管理', '/s/s_10006/s_10006/site/look', '/s/s_10006/s_10006/site/look', 's_10006', 's_10006', '1', '4', '/u/s_10006/s/site/siteLook', null, '0', '0', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('4dfb50ec-f718-11e6-9a02-002421b6d6a2', '站点管理', '/s/s_10006/s_10006/site/update/pre', '/s/s_10006/s_10006/site/update/pre', 's_10006', 's_10006', '1', '4', '/u/s_10006/s/site/siteCru', null, '0', '0', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('4e02916d-f718-11e6-9a02-002421b6d6a2', '站点管理', '/s/s_10006/s_10006/site/list', '/s/s_10006/s_10006/site/list', 's_10006', 's_10006', '1', '4', '/u/s_10006/s/site/siteList', null, '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('4f707406eda54114a8ba0ac75424e360', '11', '/s/s_10006/h_10014/content/update/pre', '/s/s_10006/h_10014/content/update/pre', 's_10006', 'h_10014', '1', '4', '1', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('51e40edbb58742f1a82533ab95218e75', 'banner管理', '/s/s_10006/h_10013/banner/look', '/s/s_10006/h_10013/banner/look', 's_10006', 'h_10013', '1', '4', '/u/s_10006/s/banner/bannerLook', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('5698f72465fd40bdbc17e81c2c628ee2', '微信菜单管理', '/s/s_10006/h_10012/mp/menu/look', '/s/s_10006/h_10012/mp/menu/look', 's_10006', 'h_10012', '1', '4', '/u/s_10006/s/mp/menu/menuLook', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('5b8d44e32dbd4e37b9854e73b64ecd20', '首页', '/s/s_10006/h_10010/site/update/pre', '/s/s_10006/h_10010/site/update/pre', 's_10006', 'h_10010', '1', '4', '/u/s_10006/s/site/siteCru', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('6892930b053744d3b80238753ae22691', '服务号信息配置', '/s/s_10006/h_10011/mp/update/pre', '/s/s_10006/h_10011/mp/update/pre', 's_10006', 'h_10011', '1', '4', '/u/s_10006/s/mp/conf/confCru', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('6f1f3b329e474be399a1516541f43bcf', '微信菜单管理', '/s/s_10006/h_10012/mp/menu/list', '/s/s_10006/h_10012/mp/menu/list', 's_10006', 'h_10012', '1', '4', '/u/s_10006/s/mp/menu/mpList', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('6f405d27a108430088f54387bec9b969', '1test', '/f/mp/s_10006/c_100011', '/f/mp/s_10006/c_100011', 's_10006', 'c_100011', '1', '0', '/u/s_10006/f/content', null, '0', '1', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('7f418f690a5e40d5afeb69abcbe35d18', '服务号信息配置', '/s/s_10006/h_10011/mp/list', '/s/s_10006/h_10011/mp/list', 's_10006', 'h_10011', '1', '4', '/u/s_10006/s/mp/conf/confList', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('83f5cd732a4641ed9f4fdd01e99bd215', '11', '/f/mp/s_10006/h_10014', '/f/mp/s_10006/h_10014', 's_10006', 'h_10014', '1', '0', '/u/s_10006/channel', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('8cad6425930641e5b877b8f9a3cddec1', '11', '/s/s_10006/h_10014/content/look', '/s/s_10006/h_10014/content/look', 's_10006', 'h_10014', '1', '4', '1', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('a357d5b6d94b497f88658940e885e1f4', '20170221内容', '/f/mp/s_10006/c_100008', '/f/mp/s_10006/c_100008', 's_10006', 'c_100008', '1', '0', '/u/s_10006/f/content', null, '0', '2', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('a40a305ca3bc437f9624bb04e48c83c5', '22', '/f/mp/s_10006/c_100017', '/f/mp/s_10006/c_100017', 's_10006', 'c_100017', '1', '0', '/u/s_10006/f/content', null, '0', '22', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('ba9969f9941944539e910cc632e540c9', '服务号信息配置', '/f/mp/s_10006/h_10011', '/f/mp/s_10006/h_10011', 's_10006', 'h_10011', '1', '0', '/u/s_10006/channel', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('bb439c13748642afba8a46c42b1814c0', '首页', '/s/s_10006/h_10010/site/look', '/s/s_10006/h_10010/site/look', 's_10006', 'h_10010', '1', '4', '/u/s_10006/s/site/siteLook', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('c28a43d450d04e778b9981278d71377e', 'test1', '/f/mp/s_10006/c_100007', '/f/mp/s_10006/c_100007', 's_10006', 'c_100007', '1', '0', '/u/s_10006/content', null, '0', '1', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('c923cdcbe12743cba9212aac3ecabd35', 't1', '/f/mp/s_10006/c_100015', '/f/mp/s_10006/c_100015', 's_10006', 'c_100015', '1', '0', '/u/s_10006/f/content', null, '0', '100015', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('d34fc79972aa41d18dc12ce8931959ec', '服务号信息配置', '/s/s_10006/h_10011/mp/look', '/s/s_10006/h_10011/mp/look', 's_10006', 'h_10011', '1', '4', '/u/s_10006/s/mp/conf/confLook', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('d55cf3be1bad4b2fbb98c966fa0353e1', 'banner管理', '/f/mp/s_10006/h_10013', '/f/mp/s_10006/h_10013', 's_10006', 'h_10013', '1', '0', '/u/s_10006/channel', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('dd0c8d4412a0432480ec92a528d10d71', '2test', '/f/mp/s_10006/c_100014', '/f/mp/s_10006/c_100014', 's_10006', 'c_100014', '1', '0', '/u/s_10006/f/content', null, '0', '2', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('e3a8d1e1-f8de-11e6-9a02-002421b6d6a2', '微信菜单管理', '/s/s_10006/h_10011/mp/menu/update/pre', '/s/s_10006/h_10011/mp/menu/update/pre', 's_10006', 'h_10011', '1', '4', '/u/s_10006/s/mp/menu/menuCru', null, '0', '0', '1', '0', '0');
INSERT INTO `cms_link` VALUES ('e6934d4c4e9f4c758ff44efc73751033', 'banner管理', '/s/s_10006/h_10013/banner/update/pre', '/s/s_10006/h_10013/banner/update/pre', 's_10006', 'h_10013', '1', '4', '/u/s_10006/s/banner/bannerCru', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('eec9c15307b4449594d2bd40752cba56', '新闻发布', '/f/mp/s_10006/h_10009', '/f/mp/s_10006/h_10009', 's_10006', 'h_10009', '1', '0', '/u/s_10006/channel', '/u/s_10006/content.html', '0', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('f56a86beaab44e4fbbeb922b018b1b19', '20170221测试', '/f/mp/s_10006/c_100009', '/f/mp/s_10006/c_100009', 's_10006', 'c_100009', '1', '0', '/u/s_10006/f/content', null, '0', '1', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('f5c569e55ae64608ab0f8d19d44613', '新闻发布', '/s/s_10006/h_10009/content/look', '/s/s_10006/h_10009/content/look', 's_10006', 'h_10009', '1', '4', '/u/s_10006/s/content/contentLook', null, '0', '0', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('f5c569e55ae64608ab0f8d19d446139', '新闻发布', '/s/s_10006/h_10009/content/update/pre', '/s/s_10006/h_10009/content/update/pre', 's_10006', 'h_10009', '1', '4', '/u/s_10006/s/content/contentCru', null, '0', '0', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('f5c569e55ae64608ab0f8d19d4461396', '新闻发布', '/s/s_10006/h_10009/content/list', '/s/s_10006/h_10009/content/list', 's_10006', 'h_10009', '1', '4', '/u/s_10006/s/content/contentList', null, '1', '1', '0', '0', '0');
INSERT INTO `cms_link` VALUES ('f7a8385caae447449e808c7e51f29951', '20170221测试', '/f/mp/s_10006/c_100010', '/f/mp/s_10006/c_100010', 's_10006', 'c_100010', '1', '0', '/u/s_10006/f/content', null, '0', '1', '1', '0', '1');
INSERT INTO `cms_link` VALUES ('f9d92cb502144386a433b7f92ccbdab1', 'dfasa', '/f/mp/s_10006/c_100018', '/f/mp/s_10006/c_100018', 's_10006', 'c_100018', '1', '0', '/u/s_10006/f/content', null, '0', '100018', '0', '0', '1');

-- ----------------------------
-- Table structure for cms_praise
-- ----------------------------
DROP TABLE IF EXISTS `cms_praise`;
CREATE TABLE `cms_praise` (
  `id` varchar(50) NOT NULL,
  `businessId` varchar(50) NOT NULL COMMENT '业务id',
  `userId` varchar(50) NOT NULL COMMENT '点赞人',
  `siteId` varchar(50) NOT NULL COMMENT '站点id',
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  `bak5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='点赞表';

-- ----------------------------
-- Records of cms_praise
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
  `inputType` int(11) NOT NULL DEFAULT '0' COMMENT '0text,1date,2datatime,3int,4float,5select,6file,7img,8imgs',
  `businessId` varchar(50) NOT NULL COMMENT '业务Id',
  `modelType` int(11) NOT NULL DEFAULT '0' COMMENT '0site,1channel,2content,3投票(以后可能扩展更多系统功能,例如 注册 登陆 订单 购物车)',
  `createPerson` varchar(50) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `defaultValue` varchar(100) DEFAULT NULL COMMENT '默认值',
  `pvalue` varchar(500) DEFAULT NULL,
  `style` varchar(500) DEFAULT NULL COMMENT '样式',
  `leafUse` int(11) NOT NULL DEFAULT '0' COMMENT '自是否可用,0不可用,1可以用',
  `sortno` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='自定义属性表';

-- ----------------------------
-- Records of cms_property
-- ----------------------------
INSERT INTO `cms_property` VALUES ('0ce9f5f3452345839b9712ff49f8e663', 's_10006', '扩展浮点数输入框属性', 'extendProperty5', '4', 'c_100018', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '5', '1');
INSERT INTO `cms_property` VALUES ('13655333067c4da88933a3c2ca249668', 's_10006', '扩展整型输入框属性', 'extendProperty4', '3', 'c_100018', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '4', '1');
INSERT INTO `cms_property` VALUES ('1a9b87fb25fa439e9e802d13a9abf506', 's_10006', '扩展图片上传属性', 'extendProperty8', '7', 'c_100017', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '8', '1');
INSERT INTO `cms_property` VALUES ('2f664cc75e2341c3b937d600239e1bef', 's_10006', '扩展浮点数输入框属性', 'extendProperty5', '4', 'c_100016', '1', 'u_10001', '2017-02-23 10:46:01', null, '12', null, '1', '5', '1');
INSERT INTO `cms_property` VALUES ('530b5cd44df64889852b7fe7d48fa8ef', 's_10006', '扩展日期属性', 'extendProperty2', '1', 'c_100018', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '2', '1');
INSERT INTO `cms_property` VALUES ('53d230401fbd45778f5d3370a240b55c', 's_10006', '扩展图片上传属性', 'extendProperty8', '7', 'c_100018', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '8', '1');
INSERT INTO `cms_property` VALUES ('6138c3233bb640d5b0405b7001500aa0', 's_10006', '扩展文件上传属性', 'extendProperty7', '6', 'c_100018', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '7', '1');
INSERT INTO `cms_property` VALUES ('744960ce7d344d4c9c1e4bd9cf9b1465', 's_10006', '扩展整型输入框属性', 'extendProperty4', '3', 'c_100016', '1', 'u_10001', '2017-02-23 10:46:01', null, '12', null, '1', '4', '1');
INSERT INTO `cms_property` VALUES ('7c83be94cd9a4a79883c77f1aad92835', 's_10006', '扩展文件上传属性', 'extendProperty7', '6', 'c_100016', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '7', '1');
INSERT INTO `cms_property` VALUES ('826365ba81bf4e0e8b1ea2935e87785e', 's_10006', '扩展时间属性', 'extendProperty3', '2', 'c_100017', '1', 'u_10001', '2017-02-23 10:46:01', null, '33', null, '1', '3', '1');
INSERT INTO `cms_property` VALUES ('843082b5e5f5478ea717f9c93f91c958', 's_10006', '扩展浮点数输入框属性', 'extendProperty5', '4', 'c_100017', '1', 'u_10001', '2017-02-23 10:46:01', null, '33', null, '1', '5', '1');
INSERT INTO `cms_property` VALUES ('87652f1d-f972-11e6-9a02-002421b6d6a2', 's_10006', '扩展文本域属性', 'extendProperty1', '0', 'h_10009', '1', 'u_10001', '2017-02-23 10:45:30', null, null, null, '1', '1', '1');
INSERT INTO `cms_property` VALUES ('8ec26e0a99bf4d1bbd854ad5f55af8e3', 's_10006', '扩展时间属性', 'extendProperty3', '2', 'c_100016', '1', 'u_10001', '2017-02-23 10:46:01', null, '12', null, '1', '3', '1');
INSERT INTO `cms_property` VALUES ('98c7efc8c81e4c12ae32b16d75991b6f', 's_10006', '扩展文本域属性', 'extendProperty1', '0', 'c_100017', '1', 'u_10001', '2017-02-23 10:45:30', null, '33', null, '1', '1', '1');
INSERT INTO `cms_property` VALUES ('99f31083-f972-11e6-9a02-002421b6d6a2', 's_10006', '扩展日期属性', 'extendProperty2', '1', 'h_10009', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '2', '1');
INSERT INTO `cms_property` VALUES ('99f8ca1c-f972-11e6-9a02-002421b6d6a2', 's_10006', '扩展时间属性', 'extendProperty3', '2', 'h_10009', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '3', '1');
INSERT INTO `cms_property` VALUES ('9a049663-f972-11e6-9a02-002421b6d6a2', 's_10006', '扩展整型输入框属性', 'extendProperty4', '3', 'h_10009', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '4', '1');
INSERT INTO `cms_property` VALUES ('9a0cec1c-f972-11e6-9a02-002421b6d6a2', 's_10006', '扩展浮点数输入框属性', 'extendProperty5', '4', 'h_10009', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '5', '1');
INSERT INTO `cms_property` VALUES ('9a1401d6-f972-11e6-9a02-002421b6d6a2', 's_10006', '扩展下拉框属性', 'extendProperty6', '5', 'h_10009', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '6', '1');
INSERT INTO `cms_property` VALUES ('9a21e2fe-f972-11e6-9a02-002421b6d6a2', 's_10006', '扩展文件上传属性', 'extendProperty7', '6', 'h_10009', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '7', '1');
INSERT INTO `cms_property` VALUES ('9a295f8c-f972-11e6-9a02-002421b6d6a2', 's_10006', '扩展图片上传属性', 'extendProperty8', '7', 'h_10009', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '8', '1');
INSERT INTO `cms_property` VALUES ('9ca6d53cd45c44b3a29a9a644c4a7931', 's_10006', '扩展下拉框属性', 'extendProperty6', '5', 'c_100017', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '6', '1');
INSERT INTO `cms_property` VALUES ('a36a590646f84ab2b5fd5747b8f0ec04', 's_10006', '扩展文件上传属性', 'extendProperty7', '6', 'c_100017', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '7', '1');
INSERT INTO `cms_property` VALUES ('ad18d81a715842ea8bb71ea649abe0f7', 's_10006', '扩展图片上传属性', 'extendProperty8', '7', 'c_100016', '1', 'u_10001', '2017-02-23 10:46:01', null, null, null, '1', '8', '1');
INSERT INTO `cms_property` VALUES ('aec9ddd2d8c14ba7805f4638438c56eb', 's_10006', '扩展时间属性', 'extendProperty3', '2', 'c_100018', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '3', '1');
INSERT INTO `cms_property` VALUES ('bb8a97baf9fc4f91910ab043437abd8e', 's_10006', '扩展下拉框属性', 'extendProperty6', '5', 'c_100018', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '6', '1');
INSERT INTO `cms_property` VALUES ('bdefeeca44f14bfc9a054544e41f6fb7', 's_10006', '扩展整型输入框属性', 'extendProperty4', '3', 'c_100017', '1', 'u_10001', '2017-02-23 10:46:01', null, '33', null, '1', '4', '1');
INSERT INTO `cms_property` VALUES ('ce7fa548840a426ab088b7c768017ad8', 's_10006', '扩展日期属性', 'extendProperty2', '1', 'c_100017', '1', 'u_10001', '2017-02-23 10:46:01', null, '33', null, '1', '2', '1');
INSERT INTO `cms_property` VALUES ('d242c8ffb7a044638a4a0b5a181ca511', 's_10006', '扩展下拉框属性', 'extendProperty6', '5', 'c_100016', '1', 'u_10001', '2017-02-23 10:46:01', null, '', null, '1', '6', '1');
INSERT INTO `cms_property` VALUES ('ebe840566f30463c86192f3d446a706a', 's_10006', '扩展文本域属性', 'extendProperty1', '0', 'c_100018', '1', 'u_10001', '2017-02-23 10:45:30', null, '', null, '1', '1', '1');
INSERT INTO `cms_property` VALUES ('f592ca620e674f1195891089ae166ddb', 's_10006', '扩展文本域属性', 'extendProperty1', '0', 'c_100016', '1', 'u_10001', '2017-02-23 10:45:30', null, '12', null, '1', '1', '1');
INSERT INTO `cms_property` VALUES ('fbec9304441646dda6c5bc07f21b7edb', 's_10006', '扩展日期属性', 'extendProperty2', '1', 'c_100016', '1', 'u_10001', '2017-02-23 10:46:01', null, '12', null, '1', '2', '1');

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
  `siteType` int(11) NOT NULL DEFAULT '0' COMMENT '11微信,12企业号,13pc,14wap,15投票',
  `active` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0不可用,1可用',
  `springbeanid` varchar(100) DEFAULT NULL COMMENT 'springbeanid',
  `orgId` varchar(50) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  KEY `orgId` (`orgId`),
  CONSTRAINT `orgId` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站点表';

-- ----------------------------
-- Records of cms_site
-- ----------------------------
INSERT INTO `cms_site` VALUES ('s_10006', null, null, 'u_10001', '站长后台测试站', '站长后台测试站', 'cxy.abc.com', '[{\"prefix\":\"jpg\",\"name\":\"java\",\"path\":\"/upload/s_10006/s_10006/1986dfde070e4b39b7173ecbb4653959java.jpg\",\"size\":\"304943722496\"}]', '123213213', '12345', '1', '1', '1', '1', '', null, '11', '1', null, 'o_10005');

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
