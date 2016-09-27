/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : wichat

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2016-09-27 18:01:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `u_account` varchar(50) DEFAULT NULL COMMENT '账号',
  `u_password` varchar(100) DEFAULT NULL COMMENT '密码',
  `u_nickname` varchar(30) DEFAULT NULL COMMENT '昵称',
  `u_avatar_url` varchar(200) DEFAULT NULL COMMENT '头像地址',
  `u_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `u_effective` int(1) DEFAULT '0' COMMENT '是否生效 1:是 0:否',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1', 'Silence', null, '2016-09-27 15:44:01', '1');

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `c_user_id` int(10) DEFAULT NULL,
  `c_user_agent` varchar(40) DEFAULT NULL,
  `c_token` varchar(40) DEFAULT NULL,
  `c_type` varchar(100) DEFAULT NULL,
  `c_create_time` datetime DEFAULT NULL,
  `c_expires` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_token
-- ----------------------------
INSERT INTO `user_token` VALUES ('1', '1', '4d2d9d763523c895553f44341f68e2b9', '252ad859505dfb619f6eb827d9ad9dac', null, '2016-09-27 09:48:52', '7200');
