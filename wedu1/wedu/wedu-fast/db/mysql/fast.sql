/*
 Navicat Premium Data Transfer

 Source Server         : 开发库 mysq8 root
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 58.220.240.50:19002
 Source Schema         : baize_fast

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 07/03/2023 14:24:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('01ca40c2-f915-4533-8953-7e2f36adbc3c', 'n7by7', '2022-03-22 11:57:09');
INSERT INTO `sys_captcha` VALUES ('066a2eae-4053-464c-81ea-acf320ba33cf', '5nxwg', '2022-03-22 11:48:23');
INSERT INTO `sys_captcha` VALUES ('066e37ca-8c79-4336-8b98-974cc4871aa0', 'dff5n', '2022-03-22 12:09:38');
INSERT INTO `sys_captcha` VALUES ('10dab7de-afd2-4533-844c-a76b9dc5944b', 'm552m', '2022-03-22 09:27:05');
INSERT INTO `sys_captcha` VALUES ('13c7b87e-69bc-4dec-8ed5-73151d1117a3', '745ae', '2022-03-22 11:48:28');
INSERT INTO `sys_captcha` VALUES ('196aea81-04f6-4189-89e8-64ba1ce679c3', 'y337f', '2022-03-22 11:48:26');
INSERT INTO `sys_captcha` VALUES ('1a041d3b-8487-4593-87c4-5c79f7807147', 'py262', '2022-03-22 17:13:04');
INSERT INTO `sys_captcha` VALUES ('1d6c25ad-df9e-4f0a-8927-51f911ee1d83', '5dmnb', '2022-03-22 17:34:50');
INSERT INTO `sys_captcha` VALUES ('1e217596-499c-4da2-88b2-ea4864af5875', '6egnb', '2022-03-22 12:09:42');
INSERT INTO `sys_captcha` VALUES ('310d950f-fd90-4a41-8773-30a907bd33a5', 'gnddn', '2022-03-25 11:01:54');
INSERT INTO `sys_captcha` VALUES ('419058f2-4f91-4f91-89e8-ae6a903fd196', '8ffgn', '2022-03-22 11:44:16');
INSERT INTO `sys_captcha` VALUES ('458a8e01-1c8f-4c40-89b8-41f567e959ff', 'n5yn8', '2022-03-22 12:09:17');
INSERT INTO `sys_captcha` VALUES ('468d6113-7997-4f1f-83a8-ede207395d34', 'ec3fy', '2022-03-22 17:32:38');
INSERT INTO `sys_captcha` VALUES ('51f123d3-d46e-47f2-86d7-37e542146f48', 'y2e28', '2022-03-21 14:13:37');
INSERT INTO `sys_captcha` VALUES ('524cae32-964e-4d63-854b-55e21d1122cd', 'neya3', '2022-03-21 13:46:38');
INSERT INTO `sys_captcha` VALUES ('59d7987f-5b73-4b5e-853d-b812aaec38a2', 'ayfd7', '2022-03-22 17:31:26');
INSERT INTO `sys_captcha` VALUES ('5def5f94-731f-4d92-8514-a31a094b144f', 'fc3yp', '2022-03-21 13:46:57');
INSERT INTO `sys_captcha` VALUES ('6022680f-1ac7-40b9-8bd4-6e59ae7fd507', 'dyfyn', '2022-04-11 10:55:06');
INSERT INTO `sys_captcha` VALUES ('6a173138-8df4-4130-81d3-b45bcd14abc6', '2bfbg', '2022-03-22 12:09:13');
INSERT INTO `sys_captcha` VALUES ('6b0d95a9-8e76-46bc-8518-afe9c6538572', 'enyaw', '2023-03-07 10:26:57');
INSERT INTO `sys_captcha` VALUES ('7577daa0-0de3-4afc-8a7a-f38f54779fe6', 'mnpcn', '2022-03-22 11:49:54');
INSERT INTO `sys_captcha` VALUES ('792e2808-4b6f-4dcc-8458-b4bd0ba22dc2', 'nc4c7', '2022-03-21 14:13:08');
INSERT INTO `sys_captcha` VALUES ('7935c441-4fc2-4272-84f4-72730783be7f', 'a8dnn', '2022-03-22 10:51:18');
INSERT INTO `sys_captcha` VALUES ('79c6fe21-a8b7-4b75-8941-f4c0643629a9', 'mdawg', '2022-03-22 17:34:44');
INSERT INTO `sys_captcha` VALUES ('7aecc678-c790-4fa1-84e9-f70d68fdcf9b', 'd433m', '2022-03-22 12:07:56');
INSERT INTO `sys_captcha` VALUES ('94ba1143-7a0f-4a23-86ee-57c01ecff2d3', 'xwdn6', '2022-03-22 12:11:24');
INSERT INTO `sys_captcha` VALUES ('961d221d-6274-47d0-801e-4a48f04c6e0b', 'md5na', '2023-03-06 20:32:52');
INSERT INTO `sys_captcha` VALUES ('9a546da6-cc34-49f3-8501-c605ae959f96', 'y5fnf', '2022-03-22 17:35:43');
INSERT INTO `sys_captcha` VALUES ('a78c6677-8655-4a08-8fa5-2d2aa4fb2d38', 'ybb2w', '2022-03-22 17:12:30');
INSERT INTO `sys_captcha` VALUES ('b78a823d-275a-4660-8d3c-73c0b1121bb7', 'xff7x', '2022-03-22 11:47:38');
INSERT INTO `sys_captcha` VALUES ('c282b492-99b7-4989-8170-595b283c736b', '2aw72', '2022-03-22 11:58:24');
INSERT INTO `sys_captcha` VALUES ('cede2964-95b1-41e6-812f-e2d81e9ffcad', 'gnewm', '2022-03-22 12:07:15');
INSERT INTO `sys_captcha` VALUES ('e3e6bb83-4063-4612-8992-7aa6e72308f9', 'xbcx7', '2022-03-22 17:32:43');
INSERT INTO `sys_captcha` VALUES ('e41f2f01-7d4b-473c-8d6a-617f08830277', 'y7ef8', '2022-03-22 17:31:52');
INSERT INTO `sys_captcha` VALUES ('ed832fbb-ac21-4909-8f03-b6ad79b84598', 'nbw82', '2022-03-22 12:00:43');
INSERT INTO `sys_captcha` VALUES ('f98287be-1a34-413c-8dc1-705331b0f3e6', '7d228', '2022-03-22 12:07:53');
INSERT INTO `sys_captcha` VALUES ('fe08d7fe-41c3-4808-85a2-09698fe3a7a0', 'd4nf5', '2023-02-20 16:43:46');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息');
INSERT INTO `sys_config` VALUES (2, '标志', 'BAIZE', 1, '');
INSERT INTO `sys_config` VALUES (3, 'wx_init_password', 'abc123-=', 1, '微信用户初始密码');
INSERT INTO `sys_config` VALUES (4, 'wx_role', '2', 1, '微信用户角色ID');
INSERT INTO `sys_config` VALUES (5, 'admin_id', '1', 1, '超级管理员用户ID');
INSERT INTO `sys_config` VALUES (6, '测试', '1', 1, '');
INSERT INTO `sys_config` VALUES (7, '测试2', '1', 1, '');
INSERT INTO `sys_config` VALUES (8, '测试3', '3', 1, '');
INSERT INTO `sys_config` VALUES (9, '测试4', '4', 1, '');
INSERT INTO `sys_config` VALUES (10, '测试5', '5', 1, '');
INSERT INTO `sys_config` VALUES (11, '测试7', '7', 1, '');
INSERT INTO `sys_config` VALUES (12, '测试8', '8', 1, '');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级部门',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '开发部', '2019-08-19 14:54:34');
INSERT INTO `sys_dept` VALUES ('2', '0', '运维部', '2019-08-19 14:54:47');
INSERT INTO `sys_dept` VALUES ('3', '1', '开发一部', '2019-08-19 15:27:47');
INSERT INTO `sys_dept` VALUES ('5', '2', '运维一部', NULL);
INSERT INTO `sys_dept` VALUES ('7', '1', '开发二部', '2019-08-19 15:54:09');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '1 字典 2字典项',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典值',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('5281c91db04fcdf26ab2d8e965bbe942', 'nation', '1', NULL, '国籍', '', '2020-05-28 15:17:49');
INSERT INTO `sys_dict` VALUES ('595f09af8349a473a4a4a31011276310', 'area', '2', 1, '邗江', '1', '2020-05-28 15:29:37');
INSERT INTO `sys_dict` VALUES ('b5c4cbe78ff92bbe8738a5e7f766a700', 'area', '1', NULL, '地区', '', '2020-05-28 15:20:24');
INSERT INTO `sys_dict` VALUES ('e27b23daff6dcad84de0c1586a2a5795', 'nation', '2', 2, '中国', '1', '2020-05-28 15:19:48');
INSERT INTO `sys_dict` VALUES ('f549d74ccb2f58b6d2196ea46e2fea95', 'nation', '2', 4, '美国', '2', '2020-05-28 15:20:03');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bucket_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `original` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `file_size` bigint(0) NULL DEFAULT NULL COMMENT '文件大小',
  `create_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `update_user` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (23, 'fe73c06586014bd8bb8df64cb389eea0.jpg', 'fast', '奥迪.jpg', 'jpg', 44917, 'admin', '2022-03-22 17:45:20', NULL, '2022-03-22 17:45:20', '0');
INSERT INTO `sys_file` VALUES (24, '0a522ca1c06b46aa8c9dc4cae19a86b6.jpg', 'fast', '奥迪.jpg', 'jpg', 44917, 'admin', '2022-03-25 10:57:28', NULL, '2022-03-25 10:57:28', '0');
INSERT INTO `sys_file` VALUES (25, '5f7cd5345e6b47e9be8491a9a2830a5f.jpg', 'fast', '奥迪.jpg', 'jpg', 44917, 'admin', '2022-03-25 10:58:47', NULL, '2022-03-25 10:58:47', '0');
INSERT INTO `sys_file` VALUES (26, '38db94e9376043f2baaed48ab8a4e0a3.jpg', 'fast', 'lz.jpg', 'jpg', 1046531, 'admin', '2022-03-25 11:01:35', NULL, '2022-03-25 11:01:35', '0');
INSERT INTO `sys_file` VALUES (27, 'c9f7543246574e2885f26f41eec30cbf.jpg', 'fast', '奥迪.jpg', 'jpg', 44917, 'admin', '2022-03-25 11:18:32', NULL, '2022-03-25 11:18:31', '0');
INSERT INTO `sys_file` VALUES (28, '56303691c0da4b82b8845b32715501e6.jpg', 'fast', '奥迪.jpg', 'jpg', 44917, 'admin', '2022-03-25 11:37:20', NULL, '2022-03-25 11:37:20', '0');
INSERT INTO `sys_file` VALUES (29, '0b7fd4f6af7246939bf27605e5e501b2.jpg', 'fast', '奥迪.jpg', 'jpg', 44917, 'admin', '2022-03-25 14:22:23', NULL, '2022-03-25 14:22:22', '0');
INSERT INTO `sys_file` VALUES (30, '2abfd928cbc747cebd606255950b4e69.jpg', 'fast', '奥迪.jpg', 'jpg', 44917, 'admin', '2022-03-25 14:23:41', NULL, '2022-03-25 14:23:41', '0');
INSERT INTO `sys_file` VALUES (31, 'b7fb0bffa072464e9954e8a5fe7e9038.jpg', 'fast', '奥迪.jpg', 'jpg', 44917, 'admin', '2022-03-25 14:29:03', NULL, '2022-03-25 14:29:02', '0');
INSERT INTO `sys_file` VALUES (32, 'ae7e8c50c95e4a78a84025d346b4ea6b.jpg', 'fast', 'lz.jpg', 'jpg', 1046531, 'admin', '2022-03-25 14:39:11', NULL, '2022-03-25 14:39:11', '0');
INSERT INTO `sys_file` VALUES (33, 'fee9985b0004440a86fdf2bfc27b6bca.jpg', 'fast', '美女.jpg', 'jpg', 76580, 'manager', '2022-03-25 15:29:01', NULL, '2022-03-25 15:29:01', '0');
INSERT INTO `sys_file` VALUES (34, '743544b3b1114d6f8ba354f5905a1ddb.jpg', 'fast', '美女.jpg', 'jpg', 76580, 'manager', '2022-03-25 15:31:35', NULL, '2022-03-25 15:31:34', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(0) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '保存配置', 'io.renren.modules.sys.controller.SysConfigController.save()', '[{\"id\":2,\"paramKey\":\"标志\",\"paramValue\":\"BAIZE\",\"remark\":\"\"}]', 154, '0:0:0:0:0:0:0:1', '2022-03-18 14:03:22');
INSERT INTO `sys_log` VALUES (2, 'admin', '保存菜单', 'com.wedu.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":43,\"parentId\":0,\"name\":\"测试\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"duanxin\",\"orderNum\":99,\"list\":[]}]', 18, '0:0:0:0:0:0:0:1', '2022-03-21 18:06:10');
INSERT INTO `sys_log` VALUES (3, 'admin', '保存菜单', 'com.wedu.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":44,\"parentId\":43,\"name\":\"测试1\",\"url\":\"1111\",\"perms\":\"\",\"type\":1,\"icon\":\"tixing\",\"orderNum\":99,\"list\":[]}]', 21, '0:0:0:0:0:0:0:1', '2022-03-21 18:06:33');
INSERT INTO `sys_log` VALUES (4, 'admin', '修改菜单', 'com.wedu.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":5,\"parentId\":1,\"name\":\"SQL监控\",\"url\":\"http://localhost:8001/wedu-fast/druid/sql.html\",\"type\":1,\"icon\":\"sql\",\"orderNum\":4,\"list\":[]}]', 31, '0:0:0:0:0:0:0:1', '2022-03-22 09:28:54');
INSERT INTO `sys_log` VALUES (5, 'admin', '修改菜单', 'com.wedu.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":5,\"parentId\":1,\"name\":\"SQL监控\",\"url\":\"http://localhost:8080/wedu-fast/druid/sql.html\",\"type\":1,\"icon\":\"sql\",\"orderNum\":4,\"list\":[]}]', 20, '0:0:0:0:0:0:0:1', '2022-03-22 09:30:58');
INSERT INTO `sys_log` VALUES (6, 'admin', '删除菜单', 'com.wedu.modules.sys.controller.SysMenuController.delete()', '[43]', 8, '0:0:0:0:0:0:0:1', '2022-03-22 09:37:36');
INSERT INTO `sys_log` VALUES (7, 'admin', '删除菜单', 'com.wedu.modules.sys.controller.SysMenuController.delete()', '[44]', 75, '0:0:0:0:0:0:0:1', '2022-03-22 09:37:40');
INSERT INTO `sys_log` VALUES (8, 'admin', '删除菜单', 'com.wedu.modules.sys.controller.SysMenuController.delete()', '[43]', 42, '0:0:0:0:0:0:0:1', '2022-03-22 09:37:44');
INSERT INTO `sys_log` VALUES (9, 'admin', '修改用户', 'com.wedu.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"443125267@qq.com\",\"mobile\":\"15949081611\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 214, '0:0:0:0:0:0:0:1', '2022-03-22 09:38:07');
INSERT INTO `sys_log` VALUES (10, 'admin', '保存角色', 'com.wedu.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"超级管理员\",\"remark\":\"超级管理员\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,5,6,7,8,9,10,11,12,13,14,27,29,30,34,35,36,37,38,39,40,41,42,-666666],\"createTime\":\"Mar 22, 2022 10:42:38 AM\"}]', 588, '0:0:0:0:0:0:0:1', '2022-03-22 10:42:39');
INSERT INTO `sys_log` VALUES (11, 'admin', '保存用户', 'com.wedu.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"manager\",\"password\":\"3d7f5360ea7557fdc79bd4bc0003469156ab3ae9af44bf1b2f87a084502ee891\",\"salt\":\"UWorVzuSI2QMdFBpJcai\",\"deptId\":\"0\",\"email\":\"443125267@qq.com\",\"mobile\":\"19951806130\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1,\"createTime\":\"Mar 22, 2022 10:43:17 AM\"}]', 79, '0:0:0:0:0:0:0:1', '2022-03-22 10:43:17');
INSERT INTO `sys_log` VALUES (12, 'admin', '保存菜单', 'com.wedu.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":49,\"parentId\":0,\"name\":\"日志管理\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"bianji\",\"orderNum\":99,\"list\":[]}]', 17, '0:0:0:0:0:0:0:1', '2022-03-22 11:33:45');
INSERT INTO `sys_log` VALUES (13, 'admin', '修改菜单', 'com.wedu.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":43,\"parentId\":49,\"name\":\"登录日志\",\"url\":\"sys/loginlog\",\"perms\":\"\",\"type\":1,\"icon\":\"loginrz\",\"orderNum\":0,\"list\":[]}]', 24, '0:0:0:0:0:0:0:1', '2022-03-22 11:34:03');
INSERT INTO `sys_log` VALUES (14, 'admin', '修改菜单', 'com.wedu.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":5,\"parentId\":49,\"name\":\"SQL监控\",\"url\":\"http://localhost:8080/wedu-fast/druid/sql.html\",\"type\":1,\"icon\":\"sql\",\"orderNum\":4,\"list\":[]}]', 18, '0:0:0:0:0:0:0:1', '2022-03-22 11:34:19');
INSERT INTO `sys_log` VALUES (15, 'admin', '修改菜单', 'com.wedu.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":29,\"parentId\":49,\"name\":\"系统日志\",\"url\":\"sys/log\",\"perms\":\"sys:log:list\",\"type\":1,\"icon\":\"log\",\"orderNum\":7,\"list\":[]}]', 18, '0:0:0:0:0:0:0:1', '2022-03-22 11:34:31');
INSERT INTO `sys_log` VALUES (16, 'admin', '修改角色', 'com.wedu.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"超级管理员\",\"remark\":\"超级管理员\",\"createUserId\":1,\"menuIdList\":[1,2,15,16,17,18,3,19,20,21,22,4,23,24,25,26,6,7,8,9,10,11,12,13,14,27,30,34,35,36,37,38,39,40,41,42,49,5,29,43,44,45,46,47,-666666]}]', 767, '0:0:0:0:0:0:0:1', '2022-03-22 14:13:01');
INSERT INTO `sys_log` VALUES (17, 'admin', '修改用户', 'com.wedu.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"manager\",\"salt\":\"UWorVzuSI2QMdFBpJcai\",\"deptId\":\"0\",\"email\":\"443125267@qq.com\",\"mobile\":\"19951806130\",\"avatar\":\"/admin/sys-file/fast/56303691c0da4b82b8845b32715501e6.jpg\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 248, '0:0:0:0:0:0:0:1', '2022-03-25 11:37:23');
INSERT INTO `sys_log` VALUES (18, 'admin', '修改用户', 'com.wedu.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"manager\",\"salt\":\"UWorVzuSI2QMdFBpJcai\",\"deptId\":\"0\",\"email\":\"443125267@qq.com\",\"mobile\":\"19951806130\",\"avatar\":\"/admin/sys-file/fast/0b7fd4f6af7246939bf27605e5e501b2.jpg\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 74, '0:0:0:0:0:0:0:1', '2022-03-25 14:22:29');
INSERT INTO `sys_log` VALUES (19, 'admin', '修改用户', 'com.wedu.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"manager\",\"salt\":\"UWorVzuSI2QMdFBpJcai\",\"deptId\":\"0\",\"email\":\"443125267@qq.com\",\"mobile\":\"19951806130\",\"avatar\":\"/admin/sys-file/fast/2abfd928cbc747cebd606255950b4e69.jpg\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 66, '0:0:0:0:0:0:0:1', '2022-03-25 14:23:43');
INSERT INTO `sys_log` VALUES (20, 'admin', '修改用户', 'com.wedu.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"manager\",\"salt\":\"UWorVzuSI2QMdFBpJcai\",\"deptId\":\"0\",\"email\":\"443125267@qq.com\",\"mobile\":\"19951806130\",\"avatar\":\"/sys/file/getFile/fast/ae7e8c50c95e4a78a84025d346b4ea6b.jpg\",\"status\":1,\"roleIdList\":[1],\"createUserId\":1}]', 224, '0:0:0:0:0:0:0:1', '2022-03-25 14:40:18');
INSERT INTO `sys_log` VALUES (21, 'manager', '修改用户', 'com.wedu.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"manager\",\"salt\":\"UWorVzuSI2QMdFBpJcai\",\"deptId\":\"0\",\"email\":\"443125267@qq.com\",\"mobile\":\"19951806130\",\"avatar\":\"/sys/file/getFile/fast/743544b3b1114d6f8ba354f5905a1ddb.jpg\",\"status\":1,\"roleIdList\":[1],\"createUserId\":2}]', 321, '0:0:0:0:0:0:0:1', '2022-03-25 15:31:36');
INSERT INTO `sys_log` VALUES (22, 'admin', '保存配置', 'com.wedu.modules.sys.controller.SysConfigController.save()', '[{\"id\":6,\"paramKey\":\"测试\",\"paramValue\":\"1\",\"remark\":\"\"}]', 130, '0:0:0:0:0:0:0:1', '2023-02-20 16:40:13');
INSERT INTO `sys_log` VALUES (23, 'admin', '保存配置', 'com.wedu.modules.sys.controller.SysConfigController.save()', '[{\"id\":7,\"paramKey\":\"测试2\",\"paramValue\":\"1\",\"remark\":\"\"}]', 25, '0:0:0:0:0:0:0:1', '2023-02-20 16:40:27');
INSERT INTO `sys_log` VALUES (24, 'admin', '保存配置', 'com.wedu.modules.sys.controller.SysConfigController.save()', '[{\"id\":8,\"paramKey\":\"测试3\",\"paramValue\":\"3\",\"remark\":\"\"}]', 52, '0:0:0:0:0:0:0:1', '2023-02-20 16:40:36');
INSERT INTO `sys_log` VALUES (25, 'admin', '保存配置', 'com.wedu.modules.sys.controller.SysConfigController.save()', '[{\"id\":9,\"paramKey\":\"测试4\",\"paramValue\":\"4\",\"remark\":\"\"}]', 35, '0:0:0:0:0:0:0:1', '2023-02-20 16:40:41');
INSERT INTO `sys_log` VALUES (26, 'admin', '保存配置', 'com.wedu.modules.sys.controller.SysConfigController.save()', '[{\"id\":10,\"paramKey\":\"测试5\",\"paramValue\":\"5\",\"remark\":\"\"}]', 30, '0:0:0:0:0:0:0:1', '2023-02-20 16:40:49');
INSERT INTO `sys_log` VALUES (27, 'admin', '保存配置', 'com.wedu.modules.sys.controller.SysConfigController.save()', '[{\"id\":11,\"paramKey\":\"测试7\",\"paramValue\":\"7\",\"remark\":\"\"}]', 12, '0:0:0:0:0:0:0:1', '2023-02-20 16:40:57');
INSERT INTO `sys_log` VALUES (28, 'admin', '保存配置', 'com.wedu.modules.sys.controller.SysConfigController.save()', '[{\"id\":12,\"paramKey\":\"测试8\",\"paramValue\":\"8\",\"remark\":\"\"}]', 20, '0:0:0:0:0:0:0:1', '2023-02-20 16:41:11');
INSERT INTO `sys_log` VALUES (29, 'admin', '保存定时任务', 'com.wedu.modules.job.controller.ScheduleJobController.save()', '[{\"jobId\":3,\"beanName\":\"systemInfoTask\",\"params\":\"\",\"cronExpression\":\"0 0/1 * * * ?\",\"status\":0,\"remark\":\"\",\"createTime\":\"Mar 7, 2023 9:31:07 AM\"}]', 302, '0:0:0:0:0:0:0:1', '2023-03-07 09:31:07');
INSERT INTO `sys_log` VALUES (30, 'admin', '立即执行任务', 'com.wedu.modules.job.controller.ScheduleJobController.run()', '[[3]]', 70, '0:0:0:0:0:0:0:1', '2023-03-07 09:31:13');

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `login_time` datetime(0) NOT NULL COMMENT '登录时间',
  `location` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录地点',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `op_system` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `op_browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES ('0bce4a27d91a5b8cd875728dbeebb1bf', 'admin', '2022-03-25 15:12:10', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('108e26851d1e129d11dde4f5346d3863', 'admin', '2023-03-07 09:23:26', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('29085300ded2c85c6c757e41a6f467aa', 'admin', '2023-02-20 16:38:59', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('398dee9fd25593471eeeaabc7460d15f', 'manager', '2022-03-22 11:17:32', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');
INSERT INTO `sys_login_log` VALUES ('3ad9b9adcfcc533455cee8346347aadb', 'admin', '2022-03-22 11:22:57', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');
INSERT INTO `sys_login_log` VALUES ('3addc2b0f0192018b7cd7688d965e369', 'manager', '2022-03-25 15:31:51', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('3c54f20d393b58f6db0c517d89336680', 'admin', '2022-03-22 13:33:29', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');
INSERT INTO `sys_login_log` VALUES ('3e32c0bfd0772aef1283e70eb9d7c8f2', 'admin', '2022-03-25 14:49:38', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('5d6b6dcc99f42f4c87dd35ba82ccf20c', 'admin', '2022-03-25 15:32:01', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('6b59a8e71cc66d972bf31c269734d647', 'admin', '2022-03-22 16:27:46', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('6c690a25117c3d5d8a938d7f14b44c8b', 'admin', '2022-03-25 15:27:24', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('73d300334c007a383b98478594f35234', 'admin', '2022-04-11 10:50:17', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 10');
INSERT INTO `sys_login_log` VALUES ('7b2cb0d80c82269f561a504fc45c9655', 'manager', '2022-03-25 15:00:36', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('7e91c6f1b756d611620883669fca71f7', 'admin', '2022-03-22 12:06:33', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');
INSERT INTO `sys_login_log` VALUES ('8c282de5e4be61645dce733a785977c2', 'admin', '2023-03-06 21:46:27', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 11');
INSERT INTO `sys_login_log` VALUES ('962a42b5c8447e4524f39f42481a1280', 'admin', '2022-04-26 15:06:00', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');
INSERT INTO `sys_login_log` VALUES ('a543d907e3a06964c58e405b32437bd2', 'admin', '2023-03-06 20:28:23', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 11');
INSERT INTO `sys_login_log` VALUES ('a842b32f60721f4aaa82122b76b8eedd', 'manager', '2022-03-25 15:28:50', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('a91144d9d037194edcd1dfaa5b0c1ed8', 'admin', '2022-03-22 11:55:31', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');
INSERT INTO `sys_login_log` VALUES ('ad44a3526b3af0ebd5a6eddd71c09ad2', 'admin', '2022-03-25 15:05:29', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('b2056ab1f397b7494d826e5420171934', 'admin', '2023-03-07 12:19:12', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 94');
INSERT INTO `sys_login_log` VALUES ('b24cfd37cd7bc7312f3f3c776e043c51', 'admin', '2023-03-07 13:25:22', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('bbb5f8506e1f10ca4698140cf87f083d', 'manager', '2022-03-25 15:12:48', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('bd691f241b384318dec57f5fcc290524', 'admin', '2022-03-22 13:50:07', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');
INSERT INTO `sys_login_log` VALUES ('cd30b77097a5d9cf2fe6a43bb14ccb82', 'admin', '2023-03-07 08:47:00', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 94');
INSERT INTO `sys_login_log` VALUES ('d4bbc02af5975d00569f466cd3e97d98', 'admin', '2022-03-25 10:57:14', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('df46a44934661e49cbf81d8a7995f5d0', 'admin', '2022-03-22 11:28:20', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');
INSERT INTO `sys_login_log` VALUES ('eab6d18a21a8f72988a3618879e457d0', 'manager', '2022-03-25 15:08:08', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 96');
INSERT INTO `sys_login_log` VALUES ('ebc876065a020ef4de79e587a27d21ce', 'admin', '2022-03-22 10:53:26', '', '0:0:0:0:0:0:0:1', 'Windows 10', 'Chrome 70');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(0) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu` VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu` VALUES (5, 49, 'SQL监控', 'http://localhost:8080/wedu-fast/druid/sql.html', NULL, 1, 'sql', 4);
INSERT INTO `sys_menu` VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5);
INSERT INTO `sys_menu` VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (27, 1, '参数管理', 'sys/config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6);
INSERT INTO `sys_menu` VALUES (29, 49, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
INSERT INTO `sys_menu` VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6);
INSERT INTO `sys_menu` VALUES (34, 1, '部门管理', 'sys/dept', '', 1, 'dept', 2);
INSERT INTO `sys_menu` VALUES (35, 34, '查看', NULL, 'sys:dept:list,sys:dept:info', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (36, 34, '新增', '', 'sys:dept:save', 2, '', 0);
INSERT INTO `sys_menu` VALUES (37, 34, '修改', '', 'sys:dept:update', 2, '', 0);
INSERT INTO `sys_menu` VALUES (38, 34, '删除', '', 'sys:dept:delete', 2, '', 0);
INSERT INTO `sys_menu` VALUES (39, 1, '字典管理', 'sys/dict', '', 1, 'dict', 3);
INSERT INTO `sys_menu` VALUES (40, 39, '新增', '', 'sys:dict:save', 2, '', 0);
INSERT INTO `sys_menu` VALUES (41, 39, '删除', '', 'sys:dict:delete', 2, '', 0);
INSERT INTO `sys_menu` VALUES (42, 39, '查看', '', 'sys:dict:list', 2, '', 0);
INSERT INTO `sys_menu` VALUES (43, 49, '登录日志', 'sys/loginlog', '', 1, 'loginrz', 0);
INSERT INTO `sys_menu` VALUES (44, 43, '编辑', '', 'sys:loginlog:update', 2, '', 0);
INSERT INTO `sys_menu` VALUES (45, 43, '查看', '', 'sys:loginlog:info,sys:loginlog:list', 2, '', 0);
INSERT INTO `sys_menu` VALUES (46, 43, '删除', '', 'sys:loginlog:delete', 2, '', 0);
INSERT INTO `sys_menu` VALUES (47, 43, '新增', NULL, 'sys:loginlog:save', 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (49, 0, '日志管理', '', '', 0, 'bianji', 99);

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件上传' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '超级管理员', 1, '2022-03-22 10:42:38');
INSERT INTO `sys_role` VALUES (2, '微信用户', '', 1, '2022-04-19 10:24:17');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (40, 1, 1);
INSERT INTO `sys_role_menu` VALUES (41, 1, 2);
INSERT INTO `sys_role_menu` VALUES (42, 1, 15);
INSERT INTO `sys_role_menu` VALUES (43, 1, 16);
INSERT INTO `sys_role_menu` VALUES (44, 1, 17);
INSERT INTO `sys_role_menu` VALUES (45, 1, 18);
INSERT INTO `sys_role_menu` VALUES (46, 1, 3);
INSERT INTO `sys_role_menu` VALUES (47, 1, 19);
INSERT INTO `sys_role_menu` VALUES (48, 1, 20);
INSERT INTO `sys_role_menu` VALUES (49, 1, 21);
INSERT INTO `sys_role_menu` VALUES (50, 1, 22);
INSERT INTO `sys_role_menu` VALUES (51, 1, 4);
INSERT INTO `sys_role_menu` VALUES (52, 1, 23);
INSERT INTO `sys_role_menu` VALUES (53, 1, 24);
INSERT INTO `sys_role_menu` VALUES (54, 1, 25);
INSERT INTO `sys_role_menu` VALUES (55, 1, 26);
INSERT INTO `sys_role_menu` VALUES (56, 1, 6);
INSERT INTO `sys_role_menu` VALUES (57, 1, 7);
INSERT INTO `sys_role_menu` VALUES (58, 1, 8);
INSERT INTO `sys_role_menu` VALUES (59, 1, 9);
INSERT INTO `sys_role_menu` VALUES (60, 1, 10);
INSERT INTO `sys_role_menu` VALUES (61, 1, 11);
INSERT INTO `sys_role_menu` VALUES (62, 1, 12);
INSERT INTO `sys_role_menu` VALUES (63, 1, 13);
INSERT INTO `sys_role_menu` VALUES (64, 1, 14);
INSERT INTO `sys_role_menu` VALUES (65, 1, 27);
INSERT INTO `sys_role_menu` VALUES (66, 1, 30);
INSERT INTO `sys_role_menu` VALUES (67, 1, 34);
INSERT INTO `sys_role_menu` VALUES (68, 1, 35);
INSERT INTO `sys_role_menu` VALUES (69, 1, 36);
INSERT INTO `sys_role_menu` VALUES (70, 1, 37);
INSERT INTO `sys_role_menu` VALUES (71, 1, 38);
INSERT INTO `sys_role_menu` VALUES (72, 1, 39);
INSERT INTO `sys_role_menu` VALUES (73, 1, 40);
INSERT INTO `sys_role_menu` VALUES (74, 1, 41);
INSERT INTO `sys_role_menu` VALUES (75, 1, 42);
INSERT INTO `sys_role_menu` VALUES (76, 1, 49);
INSERT INTO `sys_role_menu` VALUES (77, 1, 5);
INSERT INTO `sys_role_menu` VALUES (78, 1, 29);
INSERT INTO `sys_role_menu` VALUES (79, 1, 43);
INSERT INTO `sys_role_menu` VALUES (80, 1, 44);
INSERT INTO `sys_role_menu` VALUES (81, 1, 45);
INSERT INTO `sys_role_menu` VALUES (82, 1, 46);
INSERT INTO `sys_role_menu` VALUES (83, 1, 47);
INSERT INTO `sys_role_menu` VALUES (84, 1, -666666);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `dept_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门ID',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `mini_openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信小程序openid',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `create_user_id` bigint(0) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', NULL, '443125267@qq.com', '15949081611', NULL, 1, NULL, NULL, NULL, 1, '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES (2, 'manager', '3d7f5360ea7557fdc79bd4bc0003469156ab3ae9af44bf1b2f87a084502ee891', 'UWorVzuSI2QMdFBpJcai', '0', '443125267@qq.com', '19951806130', '/sys/file/getFile/fast/743544b3b1114d6f8ba354f5905a1ddb.jpg', 1, NULL, NULL, NULL, 2, '2022-03-22 10:43:17');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(0) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (6, 2, 1);

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(0) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, '8b155a9d371a748a292f5645af53d050', '2023-03-08 01:25:22', '2023-03-07 13:25:22');
INSERT INTO `sys_user_token` VALUES (2, '5c6069f971ab04dcbd566ba7853f9bb4', '2022-03-26 03:31:51', '2022-03-25 15:31:51');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2017-03-23 22:37:41');

SET FOREIGN_KEY_CHECKS = 1;
