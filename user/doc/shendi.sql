/*
Navicat MySQL Data Transfer

Source Server         : 192.168.88.170开发
Source Server Version : 50611
Source Host           : 192.168.88.170:3306
Source Database       : people2000

Target Server Type    : MYSQL
Target Server Version : 50611
File Encoding         : 65001

Date: 2017-04-05 14:13:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级编号',
  `function_type` int(11) DEFAULT NULL COMMENT '权限类型：1.菜单；2.功能；3.子功能；0.操作',
  `function_code` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限代码',
  `function_name` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限名称',
  `sort` int(9) DEFAULT NULL COMMENT '排序',
  `href` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '链接地址',
  `icon` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '图标名称',
  `status` int(11) DEFAULT NULL COMMENT '状态：1.显示；0.隐藏',
  `permission` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '权限标识',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新者',
  `remarks` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注信息',
  `is_delete` int(11) DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of function
-- ----------------------------
INSERT INTO `function` VALUES ('1', '0', null, '', '功能菜单', null, '', '', '1', '', '2016-10-23 16:50:34', '系统管理员', '2016-12-08 16:14:48', '申迪', '主页', '0');
INSERT INTO `function` VALUES ('2', '1', '1', 'administrator', '管理员管理', '100', '/administrator', 'user', '1', null, '2016-10-23 17:08:23', '系统管理员', '2016-10-23 17:08:27', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('3', '2', '2', 'list', '管理员列表', '20', '/administrator/list', 'users', '1', '', '2016-10-23 17:24:24', '系统管理员', '2016-12-10 06:22:36', '申迪', '', '0');
INSERT INTO `function` VALUES ('4', '3', '0', 'list_view', '查看', '1', '/administrator/list/view', '', '1', 'administrator:list:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('5', '3', '0', 'list_edit', '编辑', '2', '/administrator/list/edit', '', '1', 'administrator:list:edit', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('6', '3', '0', 'list_delete', '删除', '3', '/administrator/list/delete', null, '1', 'administrator:list:delete', '2016-10-23 17:48:40', '系统管理员', '2016-10-23 17:48:47', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('7', '3', '0', 'list_create', '添加', '4', '/administrator/list/create', null, '1', 'administrator:list:create', '2016-10-23 17:50:45', '系统管理员', '2016-10-23 17:50:52', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('8', '2', '2', 'info', '个人信息', '10', '/administrator/info', 'user-times', '1', '', '2016-10-23 17:24:24', '系统管理员', '2016-12-10 06:22:23', '申迪', '', '0');
INSERT INTO `function` VALUES ('9', '8', '0', 'info_view', '查看', '1', '/administrator/info/view', '', '1', 'administrator:info:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('10', '8', '0', 'info_edit', '编辑', '2', '/administrator/info/edit', '', '1', 'administrator:info:edit', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('11', '2', '2', 'role', '角色管理', '30', '/administrator/role', 'user-secret', '1', '', '2016-10-23 17:24:24', '系统管理员', '2016-12-08 17:21:33', '申迪', '', '0');
INSERT INTO `function` VALUES ('12', '11', '0', 'role_view', '查看', '1', '/administrator/role/view', '', '1', 'administrator:role:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('13', '11', '0', 'role_edit', '编辑', '2', '/administrator/role/edit', '', '1', 'administrator:role:edit', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('14', '11', '0', 'role_delete', '删除', '3', '/administrator/role/delete', '', '1', 'administrator:role:delete', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('15', '11', '0', 'role_create', '添加', '4', '/administrator/role/create', '', '1', 'administrator:role:create', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('16', '1', '1', 'user', '会员管理', '300', '/system/user', 'users', '0', null, '2016-10-23 17:08:23', '系统管理员', '2016-10-23 17:08:27', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('17', '16', '2', 'list', '会员列表', '10', '/system/user/list', 'user-secret', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-12-10 06:15:37', '申迪', '', '0');
INSERT INTO `function` VALUES ('18', '17', '0', 'list_view', '查看', '1', '/system/user/list/view', null, '0', 'user:list:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('19', '17', '0', 'list_edit', '编辑', '2', '/system/user/list/edit', null, '0', 'user:list:edit', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('20', '17', '0', 'list_delete', '删除', '3', '/system/user/list/delete', null, '0', 'user:list:delete', '2016-10-23 17:48:40', '系统管理员', '2016-10-23 17:48:47', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('21', '17', '0', 'list_add', '添加', '4', '/system/user/list/add', null, '0', 'user:list:add', '2016-10-23 17:50:45', '系统管理员', '2016-10-23 17:50:52', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('22', '16', '2', 'grade', '等级管理', '20', '/system/user/grade', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-10-23 17:24:29', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('23', '22', '0', 'grade_view', '查看', '1', '/system/user/grade/view', null, '0', 'user:grade:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('24', '22', '0', 'grade_edit', '编辑', '2', '/system/user/grade/edit', null, '0', 'user:grade:edit', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('25', '16', '2', 'record', '会员记录管理', '30', '/system/user/record', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-10-23 17:24:29', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('26', '25', '0', 'record_view', '查看', '1', '/system/user/record/view', '', '0', 'user:record:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('27', '1', '1', 'goods', '产品管理', '400', '/system/goods', 'product-hunt', '0', null, '2016-10-23 17:08:23', '系统管理员', '2016-10-23 17:08:27', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('28', '27', '2', 'list', '产品列表', '10', '/system/goods/list', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-10-23 17:24:29', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('29', '28', '0', 'list_view', '查看', '1', '/system/goods/list/view', null, '0', 'goods:list:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('30', '28', '0', 'list_edit', '编辑', '2', '/system/goods/list/edit', null, '0', 'goods:list:edit', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('31', '28', '0', 'list_delete', '删除', '3', '/system/goods/list/delete', null, '0', 'goods:list:delete', '2016-10-23 17:48:40', '系统管理员', '2016-10-23 17:48:47', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('32', '28', '0', 'list_add', '添加', '4', '/system/goods/list/add', null, '0', 'goods:list:add', '2016-10-23 17:50:45', '系统管理员', '2016-10-23 17:50:52', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('33', '27', '2', 'classify', '分类管理', '20', '/system/goods/classify', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-10-23 17:24:29', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('34', '33', '0', 'classify_view', '查看', '1', '/system/goods/classify/view', null, '0', 'goods:classify:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('35', '33', '0', 'classify_edit', '编辑', '2', '/system/goods/classify/edit', null, '0', 'goods:classify:edit', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('36', '33', '0', 'classify_delete', '删除', '3', '/system/goods/classify/delete', null, '0', 'goods:classify:delete', '2016-10-23 17:48:40', '系统管理员', '2016-10-23 17:48:47', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('37', '33', '0', 'classify_add', '添加', '4', '/system/goods/classify/add', null, '0', 'goods:classify:add', '2016-10-23 17:50:45', '系统管理员', '2016-10-23 17:50:52', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('38', '27', '2', 'query', '问答管理', '30', '/system/goods/query', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-10-23 17:24:29', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('39', '38', '0', 'query_view', '查看', '1', '/system/goods/query/view', null, '0', 'goods:query:view', '2016-10-23 17:46:12', '系统管理员', '2016-10-23 17:46:18', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('40', '38', '0', 'query_edit', '编辑', '2', '/system/goods/query/edit', null, '0', 'goods:query:edit', '2016-10-23 17:47:14', '系统管理员', '2016-10-23 17:47:23', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('41', '38', '0', 'query_delete', '删除', '3', '/system/goods/query/delete', null, '0', 'goods:query:delete', '2016-10-23 17:48:40', '系统管理员', '2016-10-23 17:48:47', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('42', '38', '0', 'query_add', '添加', '4', '/system/goods/query/add', null, '0', 'goods:query:add', '2016-10-23 17:50:45', '系统管理员', '2016-10-23 17:50:52', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('56', '1', '1', 'email', '邮件管理', '600', '/system/email', 'envelope-o', '0', null, '2016-10-23 20:22:55', '系统管理员', '2016-10-23 20:23:02', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('57', '56', '2', 'send', '发送邮件', '10', '/system/email/info', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-10-23 17:24:29', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('58', '56', '2', 'list', '邮件管理', '20', '/system/email/list', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-10-23 17:24:29', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('59', '1', '1', 'message', '系统消息', '600', '', 'desktop', '0', null, '2016-10-23 20:22:55', '系统管理员', '2016-12-06 17:46:26', '申迪', null, '0');
INSERT INTO `function` VALUES ('60', '59', '2', '', '意见反馈', '10', '', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-12-06 15:46:14', '申迪', null, '0');
INSERT INTO `function` VALUES ('61', '59', '2', '', '系统消息列表', '20', '', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-12-06 15:46:12', '申迪', null, '0');
INSERT INTO `function` VALUES ('62', '59', '2', '', '发送消息', '30', '', '', '0', '', '2016-10-23 17:24:24', '系统管理员', '2016-12-06 15:46:10', '申迪', null, '0');
INSERT INTO `function` VALUES ('65', '1', '1', 'system', '系统管理', '700', '/system', 'universal-access', '1', null, '2016-10-23 20:22:55', '系统管理员', '2016-10-23 20:23:02', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('66', '65', '2', 'menu', '菜单管理', '10', '/system/menu', 'file-text', '1', '', '2016-10-23 17:24:24', '系统管理员', '2016-12-08 17:22:33', '申迪', '', '0');
INSERT INTO `function` VALUES ('69', '3', '0', 'list_audit', '审查', '5', '/administrator/list/audit', '', '1', 'administrator:list:audit', '2016-10-25 17:25:29', '系统管理员', '2016-10-25 17:25:35', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('70', '17', '0', 'list_audit', '审查', '5', '/system/user/list/audit', null, '0', 'user:list:audit', '2016-10-25 17:25:29', '系统管理员', '2016-10-25 17:25:35', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('71', '65', '2', '', '连接池监视', '40', '/druid', 'recycle', '1', null, '2016-11-07 01:16:13', '系统管理员', '2016-12-08 17:24:15', '申迪', '', '0');
INSERT INTO `function` VALUES ('72', '28', '0', 'list_audit', '审查', '5', '/system/goods/list/audit', null, '0', 'goods:list:audit', '2016-10-25 17:25:29', '系统管理员', '2016-10-25 17:25:35', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('74', '66', '0', 'function_view', '查看', '1', '/system/menu/view', null, '1', 'system:menu:view', '2016-12-05 16:08:59', '系统管理员', '2016-12-05 16:09:05', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('75', '66', '0', 'function_edit', '编辑', '2', '/system/menu/edit', '', '1', 'system:menu:edit', '2016-12-05 16:08:59', '系统管理员', '2016-12-10 06:45:32', '申迪', '', '0');
INSERT INTO `function` VALUES ('76', '66', '0', 'function_delete', '删除', '3', '/system/menu/delete', null, '1', 'system:menu:delete', '2016-12-05 16:08:59', '系统管理员', '2016-12-05 16:09:05', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('77', '66', '0', 'function_create', '添加', '4', '/system/menu/create', '', '1', 'system:menu:create', '2016-12-05 16:08:59', '系统管理员', '2016-12-05 16:09:05', '系统管理员', '', '0');
INSERT INTO `function` VALUES ('78', '66', '0', 'function_audit', '审查', '5', '/system/menu/audit', null, '1', 'system:menu:audit', '2016-12-05 16:08:59', '系统管理员', '2016-12-05 16:09:05', '系统管理员', null, '0');
INSERT INTO `function` VALUES ('83', '11', '0', 'role_audit', '审查', '5', '/administrator/role/audit', '', '1', 'administrator:role:audit', '2016-12-10 07:25:58', '申迪', '2016-12-10 07:26:56', '申迪', '', '0');
INSERT INTO `function` VALUES ('84', '65', '2', 'version', '版本日志', '20', '/system/version', 'file-text-o', '1', null, null, '', null, null, '系统版本日志', '0');
INSERT INTO `function` VALUES ('85', '84', '0', 'version_view', '查看', '1', '/system/version/view', '', '1', 'system:version:view', null, '', null, null, '', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(64) DEFAULT NULL COMMENT '角色标志',
  `is_system` int(11) DEFAULT '1' COMMENT '系统数据：1.是，只有超级管理员能修改；0.否，拥有角色修改人员的权限能都修改',
  `status` int(11) DEFAULT '1' COMMENT '状态：1.正常；0.冻结',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新者',
  `remarks` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注信息',
  `is_delete` int(11) DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', 'admin', '1', '1', '2016-10-23 14:26:47', '申迪管理员', '2017-02-28 15:54:18', '申迪', '超级管理员，拥有系统所有的权力！', '0');
INSERT INTO `role` VALUES ('2', '角色管理员', 'role', '1', '1', null, '申迪', '2017-02-28 15:54:18', '申迪', '角色管理员，拥有系统所有的权力！', '0');
INSERT INTO `role` VALUES ('3', '研发测试员', 'test', '0', '1', null, '申迪测试', '2017-02-28 15:54:18', '申迪', '只能看', '0');

-- ----------------------------
-- Table structure for role_function
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色权限编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色编号',
  `function_id` bigint(20) DEFAULT NULL COMMENT '权限编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新者',
  `is_delete` int(11) DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2424 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_function
-- ----------------------------
INSERT INTO `role_function` VALUES ('2289', '3', '1', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2290', '3', '2', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2291', '3', '8', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2292', '3', '9', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2293', '3', '10', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2294', '3', '3', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2295', '3', '4', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2296', '3', '5', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2297', '3', '6', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2298', '3', '7', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2299', '3', '69', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2300', '3', '11', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2301', '3', '12', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2302', '3', '13', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2303', '3', '14', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2304', '3', '15', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2305', '3', '83', '2017-02-17 15:34:29', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2306', '4', '1', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2307', '4', '2', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2308', '4', '8', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2309', '4', '9', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2310', '4', '10', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2311', '4', '3', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2312', '4', '4', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2313', '4', '5', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2314', '4', '6', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2315', '4', '7', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2316', '4', '69', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2317', '4', '11', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2318', '4', '12', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2319', '4', '13', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2320', '4', '14', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2321', '4', '15', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2322', '4', '83', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2323', '4', '86', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2324', '4', '87', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2325', '4', '88', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2326', '4', '89', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2327', '4', '91', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2328', '4', '90', '2017-02-17 15:43:46', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2375', '1', '1', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2376', '1', '2', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2377', '1', '8', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2378', '1', '9', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2379', '1', '10', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2380', '1', '3', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2381', '1', '4', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2382', '1', '5', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2383', '1', '6', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2384', '1', '7', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2385', '1', '69', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2386', '1', '11', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2387', '1', '12', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2388', '1', '13', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2389', '1', '14', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2390', '1', '15', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2391', '1', '83', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2392', '1', '86', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2393', '1', '87', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2394', '1', '88', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2395', '1', '89', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2396', '1', '91', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2397', '1', '90', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2398', '1', '16', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2399', '1', '17', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2400', '1', '22', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2401', '1', '25', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2402', '1', '27', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2403', '1', '28', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2404', '1', '33', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2405', '1', '38', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2410', '1', '56', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2411', '1', '57', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2412', '1', '58', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2413', '1', '65', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2414', '1', '66', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2415', '1', '74', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2416', '1', '75', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2417', '1', '76', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2418', '1', '77', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2419', '1', '78', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2420', '1', '84', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2421', '1', '85', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');
INSERT INTO `role_function` VALUES ('2422', '1', '71', '2017-02-28 15:54:18', '申迪', '2017-02-17 15:43:46', null, '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `login_name` varchar(20) DEFAULT '' COMMENT '管理员账号',
  `login_password` varchar(32) DEFAULT '' COMMENT '管理员密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '加密密码的盐',
  `user_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `sex` int(1) DEFAULT '0' COMMENT '性别：0.保密；1.男； 2.女',
  `age` int(11) DEFAULT '0' COMMENT '年龄',
  `pic_img` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `status` int(1) DEFAULT '1' COMMENT '状态：0.冻结；1.正常；2.删除',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `telephone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新者',
  `is_delete` int(11) DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'd81c31b9348c3da513177a781703767e', 'Dts7jk', '申迪', '杜赛', '0', '22', 'default/avatar/avatar_1.jpg', '1', '810170512@qq.com', '18857105127', '2017-03-27 18:02:52', '192.168.88.195', '2016-10-27 23:11:43', '众人信息', '2017-03-16 15:06:00', '申迪', '0');
INSERT INTO `user` VALUES ('2', 'system', 'a640aa3da9f5d9975d7cee1141fa51a7', 'JGKbZs', '爬梯子的过路人', '杜赛', '1', null, 'default/avatar/avatar_3.jpg', '1', '810170512@qq.com', '18857105127', '2017-02-20 23:27:10', '172.18.52.1', '2017-02-07 16:21:10', '申迪', '2017-02-17 16:25:23', '申迪', '0');
INSERT INTO `user` VALUES ('9', 'du', '48fbc55cb5626809b4ad309f9562bab7', '86GRd6', '杜赛', '杜赛', '1', null, 'default/avatar/avatar_8.jpg', '1', '15632831549@qq.com', '18857105127', '2017-03-31 12:33:26', '127.0.0.1', '2017-02-28 16:08:15', '申迪', '2017-02-28 16:22:01', '申迪', '0');

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `user_login_log`;
CREATE TABLE `user_login_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '登录日志ID',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `user_ip` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `user_id` bigint(20) DEFAULT NULL COMMENT '管理员ID',
  `operating_system` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新者',
  `is_delete` int(11) DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login_log
-- ----------------------------
INSERT INTO `user_login_log` VALUES ('1', '2017-02-21 15:18:06', '172.27.167.184', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('2', '2017-02-21 15:19:01', '172.27.167.184', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('3', '2017-02-21 15:50:43', '172.27.167.184', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('4', '2017-02-22 16:35:53', '172.27.201.108', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('5', '2017-02-22 16:47:19', '172.27.201.108', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('6', '2017-02-22 22:16:23', '172.27.201.108', '8', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('7', '2017-02-22 22:18:08', '172.27.201.108', '9', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('8', '2017-02-22 22:19:24', '172.27.201.108', '10', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('9', '2017-02-22 23:14:21', '172.27.201.108', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('10', '2017-02-22 23:14:35', '172.27.201.108', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('11', '2017-02-22 23:47:42', '172.18.50.54', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('12', '2017-02-22 23:48:19', '172.18.50.54', '1', 'WINDOWS_10', 'CHROME45', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('13', '2017-03-02 14:49:02', '192.168.88.195', '13', 'WINDOWS_7', 'CHROME', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('14', '2017-03-02 14:49:11', '192.168.88.195', '13', 'WINDOWS_7', 'CHROME', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');
INSERT INTO `user_login_log` VALUES ('15', '2017-03-02 14:49:24', '192.168.88.195', '13', 'WINDOWS_7', 'CHROME', '2017-02-21 15:18:06', 'du', '2017-02-21 15:18:06', 'du', '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '管理员ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '更新者',
  `is_delete` int(11) DEFAULT '0' COMMENT '0:未删除 1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2017-02-17 16:25:17', 'du', '2017-02-17 16:25:17', null, '1');
INSERT INTO `user_role` VALUES ('2', '3', '2', '2017-02-17 16:25:17', 'du', '2017-02-17 16:25:17', null, '1');
INSERT INTO `user_role` VALUES ('3', '1', '9', '2017-02-17 16:25:17', 'du', '2017-02-17 16:25:17', null, '1');
