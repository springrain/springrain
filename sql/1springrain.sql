

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_auditlog_history_2018
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2018`;
CREATE TABLE `t_auditlog_history_2018`  (
  `id` varchar(50)  NOT NULL COMMENT 'ID',
  `operationType` varchar(50)  NULL DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50)  NULL DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text  NULL COMMENT '旧值',
  `curValue` text  NULL COMMENT '新值',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500)  NULL DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50)  NULL DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '操作记录' ;

-- ----------------------------
-- Table structure for t_auditlog_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `t_auditlog_history_2019`;
CREATE TABLE `t_auditlog_history_2019`  (
  `id` varchar(50)  NOT NULL COMMENT 'ID',
  `operationType` varchar(50)  NULL DEFAULT NULL COMMENT '操作类型',
  `operatorName` varchar(50)  NULL DEFAULT NULL COMMENT '操作人姓名',
  `preValue` text  NULL COMMENT '旧值',
  `curValue` text  NULL COMMENT '新值',
  `operationTime` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `operationClass` varchar(500)  NULL DEFAULT NULL COMMENT '操作类',
  `operationClassID` varchar(50)  NULL DEFAULT NULL COMMENT '记录ID',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '操作记录' ;

-- ----------------------------
-- Table structure for t_dic_data
-- ----------------------------
DROP TABLE IF EXISTS `t_dic_data`;
CREATE TABLE `t_dic_data`  (
  `id` varchar(50)  NOT NULL,
  `name` varchar(60)  NOT NULL COMMENT '名称',
  `code` varchar(60)  NULL DEFAULT NULL COMMENT '编码',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '父ID',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(2000)  NULL DEFAULT NULL COMMENT '描述',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `typekey` varchar(20)  NULL DEFAULT NULL COMMENT '类型',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '公共字典' ;

-- ----------------------------
-- Records of t_dic_data
-- ----------------------------
INSERT INTO `t_dic_data` VALUES ('16b80bfb-f0ee-47a0-ba94-cc256abaed17', '专科', '', NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('7ed23330-5538-4943-8678-0c5a2121cf57', '高中', '', NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae1-45a7-a657-b60580e2a77a', '汉族', '101', NULL, NULL, '', 1, 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae2-45a7-a657-b60580e2a77a', '回族', '', NULL, NULL, '', 1, 'minzu', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae3-45a7-a657-b60580e2a77a', '一级', '', NULL, NULL, '', 1, 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('936db407-ae4-45a7-a657-b60580e2a77a', '二级', '', NULL, NULL, '', 1, 'grade', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_dic_data` VALUES ('d7d1744b-e69f-48d0-9760-b2eae6af039b', '本科', '', NULL, NULL, '', 1, 'xueli', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_fwlog_history_2018
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2018`;
CREATE TABLE `t_fwlog_history_2018`  (
  `id` varchar(100)  NOT NULL COMMENT 'ID',
  `startDate` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100)  NULL DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50)  NULL DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300)  NULL DEFAULT NULL COMMENT '登录账号',
  `userName` varchar(200)  NULL DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300)  NULL DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200)  NULL DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000)  NULL DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100)  NULL DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2)  NULL DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '访问日志' ;

-- ----------------------------
-- Table structure for t_fwlog_history_2019
-- ----------------------------
DROP TABLE IF EXISTS `t_fwlog_history_2019`;
CREATE TABLE `t_fwlog_history_2019`  (
  `id` varchar(100)  NOT NULL COMMENT 'ID',
  `startDate` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `strDate` varchar(100)  NULL DEFAULT NULL COMMENT '访问时间(毫秒)',
  `tomcat` varchar(50)  NULL DEFAULT NULL COMMENT 'Tomcat',
  `userCode` varchar(300)  NULL DEFAULT NULL COMMENT '登录账号',
  `userName` varchar(200)  NULL DEFAULT NULL COMMENT '姓名',
  `sessionId` varchar(300)  NULL DEFAULT NULL COMMENT 'sessionId',
  `ip` varchar(200)  NULL DEFAULT NULL COMMENT 'IP',
  `fwUrl` varchar(3000)  NULL DEFAULT NULL COMMENT '访问菜单',
  `menuName` varchar(100)  NULL DEFAULT NULL COMMENT '菜单名称',
  `isqx` varchar(2)  NULL DEFAULT NULL COMMENT '是否有权限访问',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '访问日志' ;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` varchar(50)  NOT NULL,
  `name` varchar(500)  NULL DEFAULT NULL,
  `pid` varchar(50)  NULL DEFAULT NULL,
  `description` varchar(2000)  NULL DEFAULT NULL COMMENT '描述',
  `pageurl` varchar(3000)  NULL DEFAULT NULL,
  `menuType` int(11) NULL DEFAULT NULL COMMENT '0.功能按钮,1.导航菜单',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `sortno` int(11) NULL DEFAULT NULL,
  `menuIcon` varchar(100)  NULL DEFAULT NULL,
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '菜单' ;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('081b3344872545448cf5d1804890ab03', '选择专题页', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/xcx/topic/list', 0, 1, 4, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('169815aca9cf41d390e7feb6629d361d', '栏目管理', 'business_manager', '', '/system/cms/channel/list', 1, 1, 4, '/img/iconImg/icon15.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3330456139a241b1a27a7dcd171d7bf1', '拖拽演示网站', 'f4d7a1bf7ddf43dc9016e1465cd3d9d8', '', NULL, 1, 1, NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('3501ed1e23da40219b4f0fa5b7b2749a', '菜单列表', 't_menu_list', '', '/system/menu/list', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('36ab9175f7b7423eadda974ba046be05', '修改密码', 't_user_list', '', '/system/user/modifiypwd/pre', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('4adc1e3e3e244c0991d9dab66c63badf', '目录创建', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/uploadDic', 0, 1, 2, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('50374413883c45ae9b9f8e8d7c7609bf', '微信首页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/dragPage', 0, 1, 1, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('5cce870b5880479794c2c00535c55ad8', '后台管理', '3330456139a241b1a27a7dcd171d7bf1', '', NULL, 1, 1, NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('78287e4ac70546168b2fa68818710470', '保存首页数据', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/saveDragJosn', 0, 1, 2, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('7cd0678633d5407dba2bd6a1553cadce', '文件下载', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/downfile', 0, 1, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('8c72a4b5e56643ac9a9ca3aeec753c4e', '启用/禁用', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/updateActive', 0, 1, 2, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('91779a0d304f4b91932b63dec87a8536', '角色管理-系统', 'system_manager', '', '/system/role/list/all', 1, 1, NULL, '/img/iconImg/icon23.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('9bccbc28b32e41438c5ac73a5e61ed58', '专题页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/specialPage/list', 1, 1, 2, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('9efc46fc51304cae8a35d12c942059c9', '首页设置', 's_PT_854e84ec22284834b9055aaea98e910c', '', '/s/s_PT/dragpage/1/list', 1, 1, 1, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('af298b90f073443bbde4b9e67113d697', '添加/编辑', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/update', 0, 1, 1, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('aff3dc802af540c298af95cb5608fefe', '拖拽页面', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/drop', 0, 1, 4, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('b94392f7b8714f64819c5c0222eb134a', '角色修改-系统', 't_role_list', '', '/system/role/update/admin', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('b9c4e8ecffe949c0b346e1fd0d6b9977', '内容管理', 'business_manager', '', '/system/cms/content/list', 1, 1, 5, '/img/iconImg/icon9.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('business_manager', '业务管理', NULL, '', NULL, 1, 1, 1, '/img/iconImg/icon10.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('ca152df1a7b44d4f81162f34b808934a', '验证老密码', '36ab9175f7b7423eadda974ba046be05', '', '/system/user/modifiypwd/ispwd', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('ca28235dbd234b7585e133e70cc7999a', '文件上传', 'f5203235547342f094a2c126ad4603bb', '', '/system/file/uploadFile', 0, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('cafda855318c4560874f7fb14419be4f', '楼层商品选择', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/addGoods', 0, 1, 3, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('d6abe682007849869c3a168215ae40d4', 'WEB-INF文件管理', 'system_manager', '', '/system/file/web/list', 1, 1, 7, '/img/iconImg/icon20.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('d7e44d49421e41ef9c3329be68dff6f7', '获取微信首页', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/adver/weChat/dragPageJosn', 0, 1, 1, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('dic_manager', '字典管理', 'system_manager', '', NULL, 1, 1, NULL, '/img/iconImg/icon30.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('e51808e351c24a7e9fb4d47392930a2d', '保存新密码', '36ab9175f7b7423eadda974ba046be05', '', '/system/user/modifiypwd/save', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('e614beb39da04bd79797d7fc6ab91d66', '获取专题页json数据', '50374413883c45ae9b9f8e8d7c7609bf', '', '/s/s_PT/dragpage/specialPageJson', 0, 1, 3, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f41b9f3b4a0d45f5a3b5842ee40e0e96', '站点管理', 'business_manager', '', '/system/cms/site/list', 1, 1, 3, '/img/iconImg/icon4.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f4d7a1bf7ddf43dc9016e1465cd3d9d8', '网站', NULL, '', NULL, 1, 1, 3, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f5203235547342f094a2c126ad4603bb', '文件管理', 'system_manager', '', '/system/file/list', 1, 1, 6, '/img/iconImg/icon20.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('f86962e16c214382bd6a7f57a765693f', '删除', '9efc46fc51304cae8a35d12c942059c9', '', '/s/s_PT/dragpage/delete', 0, 1, 3, '/img/iconImg/default.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('system_manager', '系统管理', NULL, '', NULL, 1, 1, 2, '/img/iconImg/icon13.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('s_PT_854e84ec22284834b9055aaea98e910c', '拖拽网页', '5cce870b5880479794c2c00535c55ad8', '', NULL, 1, 1, 6, '/img/iconImg/icon5.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_auditlog_list', '修改日志', 'system_manager', '', '/system/auditlog/list', 1, 1, 1, '/img/iconImg/icon42.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_auditlog_look', '查看修改日志', 't_auditlog_list', NULL, '/system/auditlog/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_delete', '删除级别', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_deletemore', '批量删除级别', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_list', '级别管理', 'dic_manager', '', '/system/dicdata/grade/list', 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_look', '查看级别', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_tree', '级别树形结构', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_grade_update', '修改级别', 't_dic_data_grade_list', NULL, '/system/dicdata/grade/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_delete', '删除民族', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_deletemore', '批量删除民族', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_list', '民族管理', 'dic_manager', '', '/system/dicdata/minzu/list', 1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_look', '查看民族', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_tree', '民族树形结构', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_minzu_update', '修改民族', 't_dic_data_minzu_list', NULL, '/system/dicdata/minzu/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_delete', '删除学历', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_deletemore', '批量删除学历', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_list', '学历管理', 'dic_manager', '', '/system/dicdata/xueli/list', 1, 1, 3, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_look', '查看学历', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_tree', '学历树形结构', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_dic_data_xueli_update', '修改学历', 't_dic_data_xueli_list', NULL, '/system/dicdata/xueli/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_fwlog_list', '访问日志', 'system_manager', '', '/system/fwlog/list', 1, 1, 2, '/img/iconImg/icon42.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_fwlog_look', '查看访问日志', 't_fwlog_list', NULL, '/system/fwlog/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_delete', '删除菜单', 't_menu_list', NULL, '/system/menu/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_deletemore', '批量删除菜单', 't_menu_list', NULL, '/system/menu/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_list', '菜单管理', 'system_manager', '', '/system/menu/list/all', 1, 1, 3, '/img/iconImg/icon11.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_look', '查看菜单', 't_menu_list', NULL, '/system/menu/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_tree', '菜单树形结构', 't_menu_list', NULL, '/system/menu/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_menu_update', '修改菜单', 't_menu_list', NULL, '/system/menu/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_delete', '删除部门', 't_org_list', NULL, '/system/org/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_deletemore', '批量删除部门', 't_org_list', NULL, '/system/org/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_list', '部门管理', 'business_manager', '', '/system/org/list', 1, 1, 1, '/img/iconImg/icon29.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_look', '查看部门', 't_org_list', NULL, '/system/org/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_tree', '部门树形结构', 't_org_list', NULL, '/system/org/tree', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_org_update', '修改部门', 't_org_list', NULL, '/system/org/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_delete', '删除角色', 't_role_list', NULL, '/system/role/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_deletemore', '批量删除角色', 't_role_list', NULL, '/system/role/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_list', '角色管理', 'system_manager', '', '/system/role/list', 1, 1, 4, '/img/iconImg/icon23.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_look', '查看角色', 't_role_list', NULL, '/system/role/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_role_update', '修改角色', 't_role_list', NULL, '/system/role/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_delete', '删除用户', 't_user_list', NULL, '/system/user/delete', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_deletemore', '批量删除用户', 't_user_list', NULL, '/system/user/delete/more', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_list', '用户管理', 'business_manager', '', '/system/user/list', 1, 1, 2, '/img/iconImg/icon24.png', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_list_export', '导出用户', 't_user_list', NULL, '/system/user/list/export', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_look', '查看用户', 't_user_list', NULL, '/system/user/look', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` VALUES ('t_user_update', '修改用户', 't_user_list', NULL, '/system/user/update', 0, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `name` varchar(60)  NULL DEFAULT NULL COMMENT '名称',
  `comcode` varchar(1000)  NULL DEFAULT NULL COMMENT '代码',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '上级部门ID',
  `orgType` int(11) NULL DEFAULT NULL COMMENT '1.部门,2.虚拟权限组,10站长部门,11微信,12企业号,13pc,14wap,15投票',
  `leaf` int(11) NULL DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `sortno` int(11) NULL DEFAULT NULL COMMENT '排序号',
  `description` varchar(2000)  NULL DEFAULT NULL COMMENT '描述',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '部门' ;

-- ----------------------------
-- Records of t_org
-- ----------------------------
INSERT INTO `t_org` VALUES ('o_10001', '平台', ',o_10001,', NULL, NULL, NULL, 1, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10002', '网站', ',o_10001,o_10002,', 'o_10001', NULL, NULL, 1, '', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_org` VALUES ('o_10003', '拖拽演示', ',o_10001,o_10002,o_10003,', 'o_10002', 10, NULL, 1, '', 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_permissions_log
-- ----------------------------
DROP TABLE IF EXISTS `t_permissions_log`;
CREATE TABLE `t_permissions_log`  (
  `id` varchar(50)  NOT NULL COMMENT '主键',
  `siteId` varchar(50)  NULL DEFAULT NULL COMMENT '站点ID',
  `actionType` int(2) NULL DEFAULT NULL COMMENT '操作类型 创建、编辑、删除、启用、禁用',
  `operatorUserId` varchar(50)  NULL DEFAULT NULL COMMENT '操作人ID',
  `operatorUserName` varchar(200)  NULL DEFAULT NULL COMMENT '操作人当时名称',
  `operatorUserRoles` text  NULL COMMENT '操作人当时所属角色名称，逗号分割',
  `operatorObjectType` int(2) NULL DEFAULT NULL COMMENT '操作对象类型',
  `operatorObjectId` varchar(50)  NULL DEFAULT NULL COMMENT '操作对象ID',
  `operatorObjectName` varchar(200)  NULL DEFAULT NULL COMMENT '操作对象当时的名称',
  `actionContent` longtext  NULL COMMENT '操作内容详情',
  `createUserId` varchar(50)  NULL DEFAULT NULL COMMENT '记录创建人',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `bak1` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak2` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak3` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak4` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  `bak5` varchar(200)  NULL DEFAULT NULL COMMENT '备用字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '权限变更日志' ;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(50)  NOT NULL COMMENT '角色ID',
  `name` varchar(60)  NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(255)  NULL DEFAULT NULL COMMENT '权限编码',
  `pid` varchar(50)  NULL DEFAULT NULL COMMENT '上级角色ID,暂时不实现',
  `roleType` int(11) NOT NULL DEFAULT 0 COMMENT '0系统角色,1部门主管,2业务岗位',
  `remark` varchar(255)  NULL DEFAULT NULL COMMENT '备注',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `orgId` varchar(50)  NULL DEFAULT NULL COMMENT '归属的部门Id',
  `createTime` datetime(0) NULL DEFAULT NULL,
  `createUserId` varchar(50)  NULL DEFAULT NULL,
  `updateTime` datetime(0) NULL DEFAULT NULL,
  `updateUserId` varchar(50)  NULL DEFAULT NULL,
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色' ;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('e8a4ad9944894908b43ded631094dcbb', '演示站长', NULL, NULL, 1, '', 1, 'o_10003', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role` VALUES ('r_10001', '超级管理员', NULL, NULL, 0, '', 1, 'o_10001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `menuId` varchar(50)  NOT NULL COMMENT '菜单编号',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_t_role_menu_roleId_t_role_id`(`roleId`) USING BTREE,
  INDEX `fk_t_role_menu_menuId_t_menu_id`(`menuId`) USING BTREE,
  CONSTRAINT `fk_t_role_menu_menuId_t_menu_id` FOREIGN KEY (`menuId`) REFERENCES `t_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_role_menu_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色菜单中间表' ;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('0060df3515694b268110cefdd937e51d', 'e8a4ad9944894908b43ded631094dcbb', '9bccbc28b32e41438c5ac73a5e61ed58', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('044d51aa28284877b13398b4e41a19ef', 'r_10001', 'ca28235dbd234b7585e133e70cc7999a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0626b594e7f8428b87ef5c11c494fb52', 'r_10001', 'dic_manager', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0717e629cecf4f29816208e96c6cfdb5', 'r_10001', '081b3344872545448cf5d1804890ab03', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('07bb61936d3148e3a555081bcaa039fa', 'r_10001', 't_auditlog_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0ad8b9ceb54d4e3ea2b932bbf05f098a', 'r_10001', 'aff3dc802af540c298af95cb5608fefe', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0b3d0790459346c1bed59c092e21b45e', 'r_10001', '7cd0678633d5407dba2bd6a1553cadce', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('0e0f716292b248b89d1bf8ee3e04e611', 'r_10001', 't_user_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('18aeb685ab654c058784f16cd8dfa64f', 'r_10001', 't_dic_data_minzu_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('1953283e845f4181b1ce1a59e8af3da6', 'r_10001', 't_user_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('1d69f4febb074398b9d92165f5ba0541', 'r_10001', 't_dic_data_grade_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('1e8f03316566461295d59bea54933f80', 'r_10001', 'f5203235547342f094a2c126ad4603bb', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('209b1c9894d84605b007f682fb309fd3', 'r_10001', 'b94392f7b8714f64819c5c0222eb134a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('24879970948149e3b8f6a04cb87803ff', 'e8a4ad9944894908b43ded631094dcbb', '5cce870b5880479794c2c00535c55ad8', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('28ad70e3fe104fa7bfb785f828d87d3e', 'r_10001', 'e614beb39da04bd79797d7fc6ab91d66', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('3749117fadbf45e09b843d0c321aa0b0', 'r_10001', '9efc46fc51304cae8a35d12c942059c9', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('39a2a7544ad3418aabbceecc07b63ff7', 'r_10001', 't_dic_data_grade_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('3c5bfeb5b1a64f4684148c31471ccec2', 'r_10001', '91779a0d304f4b91932b63dec87a8536', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('43419f02c95c4408b2090c108c0fb8ed', 'r_10001', 'd6abe682007849869c3a168215ae40d4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('47cafe9a1a6e4cc284fd8cce8bcac751', 'e8a4ad9944894908b43ded631094dcbb', '3330456139a241b1a27a7dcd171d7bf1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('48b508983f424b4588a3d47ccb804acd', 'r_10001', '78287e4ac70546168b2fa68818710470', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('4b09ef03ac634d4a8d40d4921531daee', 'r_10001', 't_user_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('54cc4a1baa494c89b14a9fc1a6131f4c', 'r_10001', 'af298b90f073443bbde4b9e67113d697', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('576cf7e03d83498da1e44f45b981f6aa', 'r_10001', 'business_manager', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('5d921b6428b34368883749d0713ec7c7', 'r_10001', 't_org_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('5f77b7ac7fbd4142801c25926d91ba48', 'e8a4ad9944894908b43ded631094dcbb', '9efc46fc51304cae8a35d12c942059c9', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('622afbe526b742da85656a5cd3275a42', 'r_10001', '36ab9175f7b7423eadda974ba046be05', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('64351523b1c64603b9db4698af9d5905', 'r_10001', '8c72a4b5e56643ac9a9ca3aeec753c4e', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('6617bb7e1cc74bc29b0c0f9fb6dac762', 'r_10001', 's_PT_854e84ec22284834b9055aaea98e910c', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('66b570a6c9594d619217cb2689193c2f', 'r_10001', 't_dic_data_minzu_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('67b9bd7c0d324868a93d582111fd3c7a', 'r_10001', 't_dic_data_minzu_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('6ac40862cd9e49b2a70eef86574decba', 'r_10001', 't_role_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('7316b9482e02416f8403c9cec7076de9', 'r_10001', 't_menu_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('73cd2ff78f214e1583c7f480bb80c4bb', 'e8a4ad9944894908b43ded631094dcbb', '50374413883c45ae9b9f8e8d7c7609bf', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('74fd467552b1438ea4012dd5d1e395a8', 'r_10001', 't_dic_data_grade_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('75458bea2acd403e8f1950c4b64465e0', 'r_10001', '3501ed1e23da40219b4f0fa5b7b2749a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('7c6ae81475884235b13bbbfffb805467', 'r_10001', 't_dic_data_xueli_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('81b9ffcfdc5a4c959c268c306c1dd84e', 'r_10001', 't_menu_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('8392fd4f460d44f294cb8d2cc5777d6a', 'r_10001', 't_auditlog_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('83ddff0ff5184d06a51f6947abc13b64', 'r_10001', 't_org_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('852efc9c9953441a99671bb3a2de9b80', 'r_10001', 't_dic_data_xueli_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('858eda8dee6441dd81cb21787b00b624', 'r_10001', 't_org_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('8961288dad9c4652adbaba4a4ef05ebc', 'e8a4ad9944894908b43ded631094dcbb', '78287e4ac70546168b2fa68818710470', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('898d4937aeb84760ab0e527453035de1', 'r_10001', 't_dic_data_minzu_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('9425a831124c4137adb75fe0d3324639', 'r_10001', 't_role_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a1f5143cc765499d9ba47bb4dce3c0a9', 'r_10001', 't_role_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a3b2fe2144b84623868cedd4caefab24', 'r_10001', 't_dic_data_grade_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a52b264f7ba4417f8f0f1e933a0dec53', 'r_10001', 't_user_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a8a8ba0e89294b9ebd5ae890120466ad', 'r_10001', 't_dic_data_xueli_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a8bd194150fe4fd4912291f8cae72eef', 'r_10001', 't_fwlog_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('a966d66d174a41d0ab309841d457b4f6', 'r_10001', '50374413883c45ae9b9f8e8d7c7609bf', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('aa5e76f506f54cb48e95d2d7b065cc74', 'r_10001', '4adc1e3e3e244c0991d9dab66c63badf', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('aaf5d6a2b7ed4651a5e1e02252a8e016', 'r_10001', 't_dic_data_xueli_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('ab5db59a708d4689afa2cb320a9592d2', 'e8a4ad9944894908b43ded631094dcbb', 'd7e44d49421e41ef9c3329be68dff6f7', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('ae021afcea874620a8337c21914b0244', 'r_10001', 't_role_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('af92b74385c94cc78548d2014ffadd17', 'r_10001', '9bccbc28b32e41438c5ac73a5e61ed58', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b0a82cf6773341d89267994a59e3f004', 'r_10001', 'system_manager', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b2bffc54c8df4d2eacabc747bb9f2f7f', 'r_10001', 't_menu_tree', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b62086c3c4794451aef36a70273f1f82', 'r_10001', 't_dic_data_minzu_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('b66a63a56cb64a6daa49eaf456850fac', 'r_10001', 't_role_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('bad1e832a2ca41839ae15acc53070d4d', 'e8a4ad9944894908b43ded631094dcbb', 's_PT_854e84ec22284834b9055aaea98e910c', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('bb2d588a15264d5ba02dbdce7c876105', 'r_10001', 't_menu_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('bb6d2ce9371e4654a03fb53e516f226d', 'r_10001', 't_menu_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('be29394d55ca43cc899235ef06c0f978', 'r_10001', 'ca152df1a7b44d4f81162f34b808934a', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('c39e25b122e341f5a713c3ac79d405c4', 'r_10001', 'cafda855318c4560874f7fb14419be4f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('cb1196d49acb44bcaf65871de1472e0c', 'r_10001', 'f86962e16c214382bd6a7f57a765693f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('d102518883544b52816b70382702590d', 'r_10001', 't_org_delete', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('d2e97f48d4044506aca7011e3616dce9', 'e8a4ad9944894908b43ded631094dcbb', '081b3344872545448cf5d1804890ab03', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('d8576f6d04e249858029ed4e20249be7', 'e8a4ad9944894908b43ded631094dcbb', 'af298b90f073443bbde4b9e67113d697', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('d98fe095578d424c83570d5d961931e1', 'r_10001', 'e51808e351c24a7e9fb4d47392930a2d', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('dd75e67089a640079527ead4c90d8d43', 'r_10001', 't_dic_data_minzu_update', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('de67b0aa1b094a43871951714eaae9a7', 'r_10001', 't_dic_data_xueli_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('e16e1dbf5c74448da774493602016990', 'r_10001', 't_dic_data_grade_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('e2315a28b7aa4e9eb1643f40176e940a', 'r_10001', 't_dic_data_xueli_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('e51470df0018461c9e231287d6b9f88c', 'e8a4ad9944894908b43ded631094dcbb', 'f4d7a1bf7ddf43dc9016e1465cd3d9d8', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('e9c511b8bc064eafb31c4b19bc80893f', 'r_10001', 't_user_list_export', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('eb1f7cde31e6458c8bd88e0a224942be', 'e8a4ad9944894908b43ded631094dcbb', 'aff3dc802af540c298af95cb5608fefe', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('ed95c85b76504602a8201c420f3cdca9', 'r_10001', 't_menu_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('eebdbdd8278d4df6abe8dee39a5c4972', 'r_10001', 't_org_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('f00faad6a19b4da8a3caeb8340f7666c', 'r_10001', 't_user_deletemore', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('f0faaa9c51064b779d14edaea2487d8a', 'e8a4ad9944894908b43ded631094dcbb', 'f86962e16c214382bd6a7f57a765693f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('f17badbb4d38484893c3bc15d3911e1d', 'r_10001', 't_fwlog_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('f366eb74755c43b991b4b292d245426e', 'r_10001', 't_org_look', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('f6544c19ddea45ae862be6792343c2a1', 'e8a4ad9944894908b43ded631094dcbb', 'e614beb39da04bd79797d7fc6ab91d66', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('f8554632a6d942d39ab95344a4f9bfc2', 'e8a4ad9944894908b43ded631094dcbb', '8c72a4b5e56643ac9a9ca3aeec753c4e', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('fa21e7d6caad4c2fbd97aa026a396cdf', 'e8a4ad9944894908b43ded631094dcbb', 'cafda855318c4560874f7fb14419be4f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('fb6ab8791e2449938ad5b77e6592dc39', 'r_10001', 't_dic_data_grade_list', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` VALUES ('ffabbb9da63842609ff3d367874c32ca', 'r_10001', 'd7e44d49421e41ef9c3329be68dff6f7', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_role_org
-- ----------------------------
DROP TABLE IF EXISTS `t_role_org`;
CREATE TABLE `t_role_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `orgId` varchar(50)  NOT NULL COMMENT '部门编号',
  `orgType` int(11) NULL DEFAULT NULL COMMENT '0组织机构 ,1.部门,2.虚拟权限组',
  `hasLeaf` int(11) NOT NULL DEFAULT 0 COMMENT '是否包含子部门,0不包含,1包含',
  `active` int(11) NOT NULL DEFAULT 1 COMMENT '是否可用,0不可用,1可用',
  `manager` int(11) NULL DEFAULT 0 COMMENT '0:非主管，1:主管',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_t_role_org_roleId_t_role_id`(`roleId`) USING BTREE,
  INDEX `fk_t_role_org_orgId_t_org_id`(`orgId`) USING BTREE,
  CONSTRAINT `fk_t_role_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_role_org_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '角色部门中间表' ;

-- ----------------------------
-- Table structure for t_tableindex
-- ----------------------------
DROP TABLE IF EXISTS `t_tableindex`;
CREATE TABLE `t_tableindex`  (
  `id` varchar(50)  NOT NULL COMMENT '表名',
  `maxIndex` int(11) NOT NULL DEFAULT 1 COMMENT '表记录最大的行,一直累加',
  `prefix` varchar(50)  NOT NULL COMMENT '前缀 单个字母加 _',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '记录表最大的行记录' ;

-- ----------------------------
-- Records of t_tableindex
-- ----------------------------
INSERT INTO `t_tableindex` VALUES ('cms_channel', 10000, 'h_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('cms_content', 100000, 'c_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('cms_site', 10001, 's_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('t_org', 10003, 'o_', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_tableindex` VALUES ('t_user', 10001, 'u_', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `name` varchar(30)  NULL DEFAULT NULL COMMENT '姓名',
  `account` varchar(50)  NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(50)  NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(2)  NULL DEFAULT '男' COMMENT '性别',
  `mobile` varchar(16)  NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60)  NULL DEFAULT NULL COMMENT '邮箱',
  `weixinId` varchar(200)  NULL DEFAULT NULL COMMENT '微信Id',
  `userType` int(11) NULL DEFAULT NULL COMMENT '0后台管理员|/system/,1会员用户|/front/,2cms管理员|/cms/houtai/|cms_siteManager,3活动管理员|/huodong/houtai',
  `active` int(11) NULL DEFAULT 1 COMMENT '是否有效(0否,1是)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户' ;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('23a2c0c52ed142938c159c9b9004fa35', 'ptAdmin', 'ptAdmin', '21232f297a57a5a743894a0e4a801fc3', '男', '', '', '', 2, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('u_10001', '超级管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '男', NULL, NULL, NULL, 0, 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_org
-- ----------------------------
DROP TABLE IF EXISTS `t_user_org`;
CREATE TABLE `t_user_org`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `userId` varchar(50)  NOT NULL COMMENT '用户编号',
  `orgId` varchar(50)  NOT NULL COMMENT '机构编号',
  `managerType` int(11) NOT NULL DEFAULT 1 COMMENT '是否主管(0会员 10 员工 11主管 12代理主管 13虚拟主管)',
  `hasleaf` int(11) NOT NULL DEFAULT 1 COMMENT '是否包含子部门(0不包含1包含)',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_t_user_org_userId_t_user_id`(`userId`) USING BTREE,
  INDEX `fk_t_user_org_orgId_t_org_id`(`orgId`) USING BTREE,
  CONSTRAINT `fk_t_user_org_orgId_t_org_id` FOREIGN KEY (`orgId`) REFERENCES `t_org` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_user_org_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户部门中间表' ;

-- ----------------------------
-- Records of t_user_org
-- ----------------------------
INSERT INTO `t_user_org` VALUES ('1', 'u_10001', 'o_10001', 11, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_org` VALUES ('e6e6ed8fce534c6d9b66feb77c817413', '23a2c0c52ed142938c159c9b9004fa35', 'o_10003', 11, 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_platform_infos
-- ----------------------------
DROP TABLE IF EXISTS `t_user_platform_infos`;
CREATE TABLE `t_user_platform_infos`  (
  `id` varchar(50)  NOT NULL COMMENT '主键id',
  `openId` varchar(100)  NULL DEFAULT NULL COMMENT '公众号openId,企业号userId,小程序openId,APP推送deviceToken',
  `deviceType` int(11) NULL DEFAULT NULL COMMENT '设备/应用类型：1公众号2小程序3企业号4APP IOS消息推送5APP安卓消息推送6web',
  `siteId` varchar(50)  NULL DEFAULT NULL COMMENT '所属站点ID',
  `userId` varchar(50)  NULL DEFAULT NULL COMMENT 't_user表中ID',
  `bak1` varchar(255)  NULL DEFAULT NULL,
  `bak2` varchar(255)  NULL DEFAULT NULL,
  `bak3` varchar(255)  NULL DEFAULT NULL,
  `bak4` varchar(255)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户平台信息表' ;

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(50)  NOT NULL COMMENT '编号',
  `userId` varchar(50)  NOT NULL COMMENT '用户编号',
  `roleId` varchar(50)  NOT NULL COMMENT '角色编号',
  `bak1` varchar(100)  NULL DEFAULT NULL,
  `bak2` varchar(100)  NULL DEFAULT NULL,
  `bak3` varchar(100)  NULL DEFAULT NULL,
  `bak4` varchar(100)  NULL DEFAULT NULL,
  `bak5` varchar(100)  NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_t_user_role_userId_t_user_id`(`userId`) USING BTREE,
  INDEX `fk_t_user_role_roleId_t_role_id`(`roleId`) USING BTREE,
  CONSTRAINT `fk_t_user_role_roleId_t_role_id` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_t_user_role_userId_t_user_id` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4  COMMENT = '用户角色中间表' ;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', 'u_10001', 'r_10001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user_role` VALUES ('8a7f31289845414583f230839b98e98d', '23a2c0c52ed142938c159c9b9004fa35', 'e8a4ad9944894908b43ded631094dcbb', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
