/*
Navicat MySQL Data Transfer

Source Server         : 兵哥腾讯云
Source Server Version : 50718
Source Host           : 5828484357b83.gz.cdb.myqcloud.com:14259
Source Database       : xiehuan

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-09-20 09:07:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for courseinfo
-- ----------------------------
DROP TABLE IF EXISTS `courseinfo`;
CREATE TABLE `courseinfo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varbinary(50) DEFAULT NULL COMMENT '课程名',
  `course_code` varbinary(20) DEFAULT NULL COMMENT '课程代码',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 0:删除 1:正常',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程 courseinfo';

-- ----------------------------
-- Table structure for courseinfo_courseresoure
-- ----------------------------
DROP TABLE IF EXISTS `courseinfo_courseresoure`;
CREATE TABLE `courseinfo_courseresoure` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `courseinfo_id` bigint(20) DEFAULT NULL COMMENT '课程',
  `courseresoure_id` bigint(20) DEFAULT NULL COMMENT '课件',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='courseinfo_courseresoure';

-- ----------------------------
-- Table structure for courseresoure
-- ----------------------------
DROP TABLE IF EXISTS `courseresoure`;
CREATE TABLE `courseresoure` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varbinary(50) DEFAULT NULL COMMENT '课件名称',
  `file_name` varbinary(100) DEFAULT NULL COMMENT '课件文件名',
  `conent` longtext COMMENT '简介',
  `file_type` smallint(6) DEFAULT NULL COMMENT '文件类型',
  `cover_path` varbinary(100) DEFAULT NULL COMMENT '封面文件名',
  `credit` int(11) DEFAULT NULL COMMENT '学分',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 0:删除 1:正常',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课件 courseresoure';

-- ----------------------------
-- Table structure for examscore
-- ----------------------------
DROP TABLE IF EXISTS `examscore`;
CREATE TABLE `examscore` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `score` double DEFAULT NULL COMMENT '成绩',
  `studybatch_id` bigint(20) DEFAULT NULL COMMENT '学习批次',
  `user_id` bigint(20) DEFAULT NULL COMMENT '学员',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成绩 examscore';

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) DEFAULT NULL COMMENT '上级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `type` smallint(6) DEFAULT NULL COMMENT '类型 0、菜单 1、功能',
  `state` smallint(6) DEFAULT NULL COMMENT '状态 0、禁用 1、正常',
  `sort` smallint(6) DEFAULT NULL COMMENT '排序',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  `perm_code` varchar(30) DEFAULT NULL COMMENT '权限编码',
  `icon` varchar(30) DEFAULT NULL COMMENT '图标',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='permission 权限表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '角色名',
  `sort` smallint(6) DEFAULT NULL COMMENT '排序',
  `description` varchar(60) DEFAULT NULL COMMENT '描述',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='role 角色表';

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `rid` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) unsigned DEFAULT NULL COMMENT '权限ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`rid`),
  KEY `FK_Reference_4` (`pid`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`rid`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`pid`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='role_permission 角色权限表';

-- ----------------------------
-- Table structure for studybatch
-- ----------------------------
DROP TABLE IF EXISTS `studybatch`;
CREATE TABLE `studybatch` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varbinary(50) DEFAULT NULL COMMENT '学习批次名称',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '截止时间',
  `can_exam_credit` int(11) DEFAULT NULL COMMENT '考试学分',
  `status` smallint(6) DEFAULT NULL COMMENT '状态 0:删除 1:正常',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学习批次 studybatch';

-- ----------------------------
-- Table structure for studybatch_courseinfo
-- ----------------------------
DROP TABLE IF EXISTS `studybatch_courseinfo`;
CREATE TABLE `studybatch_courseinfo` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `studybatch_id` bigint(20) DEFAULT NULL COMMENT '学习批次',
  `courseinfo_id` bigint(20) DEFAULT NULL COMMENT '课程',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='studybatch_courseinfo';

-- ----------------------------
-- Table structure for studyrecord
-- ----------------------------
DROP TABLE IF EXISTS `studyrecord`;
CREATE TABLE `studyrecord` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `time_count` int(11) DEFAULT NULL COMMENT '学习时间',
  `play_pos` int(11) DEFAULT NULL COMMENT '学习中断位置',
  `user_id` bigint(20) DEFAULT NULL COMMENT '学员',
  `studybatch_id` bigint(20) DEFAULT NULL COMMENT '学习批次',
  `courseinfo_id` bigint(20) DEFAULT NULL COMMENT '课程',
  `courseresoure_id` bigint(20) DEFAULT NULL COMMENT '课件',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学习记录 studyrecord';

-- ----------------------------
-- Table structure for studyrecord_relation
-- ----------------------------
DROP TABLE IF EXISTS `studyrecord_relation`;
CREATE TABLE `studyrecord_relation` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `studybatch_id` bigint(20) DEFAULT NULL COMMENT '学习批次_主键',
  `courseinfo_id` bigint(20) DEFAULT NULL COMMENT '课程_主键',
  `courseresoure_id` bigint(20) DEFAULT NULL COMMENT '课件_主键',
  `studyrecord_id` bigint(20) DEFAULT NULL COMMENT '学习记录_主键',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`id`) REFERENCES `courseresoure` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='studyrecord_relation';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(20) NOT NULL COMMENT '主键',
  `uid` int(20) DEFAULT NULL COMMENT '用户ID',
  `content` varchar(255) DEFAULT NULL COMMENT '日志内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sys_log 操作日志表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` smallint(6) DEFAULT NULL COMMENT '0、禁用 1、正常',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='user 用户表';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `rid` bigint(20) unsigned DEFAULT NULL COMMENT '角色ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`uid`),
  KEY `FK_Reference_2` (`rid`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user_role 用户角色关联表';
