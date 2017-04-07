/*
Navicat MySQL Data Transfer

Source Server         : myonemysql
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-10-30 23:17:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `admin_blacklist`;
CREATE TABLE `admin_blacklist` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_ip` varchar(15) DEFAULT NULL,
  `b_time` varchar(14) DEFAULT NULL,
  `b_note` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_blacklist
-- ----------------------------
INSERT INTO `admin_blacklist` VALUES ('1', '192.168.168.255', '20161029234259', '恶意注入sql');
INSERT INTO `admin_blacklist` VALUES ('2', '192.168.168.254', '20161029234259', '恶意注册大量用户');
INSERT INTO `admin_blacklist` VALUES ('3', '192.168.168.253', '20161029234259', '疑似CC攻击');

-- ----------------------------
-- Table structure for admin_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_log`;
CREATE TABLE `admin_log` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(20) DEFAULT NULL,
  `l_note` varchar(255) DEFAULT NULL,
  `l_time` varchar(15) DEFAULT NULL,
  `ip` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_log
-- ----------------------------
INSERT INTO `admin_log` VALUES ('1', '12', '注册成功', '20161030105745', '196.168.253.222');
INSERT INTO `admin_log` VALUES ('2', '12', '上线', '20161030105501', '196.168.253.222');
INSERT INTO `admin_log` VALUES ('3', '12', '修改密码', '20161030110154', '196.168.253.222');
INSERT INTO `admin_log` VALUES ('4', '12', '修改个人资料', '20161030105501', '196.168.253.222');
INSERT INTO `admin_log` VALUES ('5', '12', '添加留言', '20161030110350', '196.168.256.222');
INSERT INTO `admin_log` VALUES ('6', '12', '下线', '20161030110511', '196.168.256.222');

-- ----------------------------
-- Table structure for admin_pow
-- ----------------------------
DROP TABLE IF EXISTS `admin_pow`;
CREATE TABLE `admin_pow` (
  `control` varchar(30) NOT NULL,
  `power` tinyint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_pow
-- ----------------------------
INSERT INTO `admin_pow` VALUES ('ChangePassword', '0');
INSERT INTO `admin_pow` VALUES ('DeleteUser', '1');

-- ----------------------------
-- Table structure for users_data
-- ----------------------------
DROP TABLE IF EXISTS `users_data`;
CREATE TABLE `users_data` (
  `t_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL,
  `t_title` varchar(20) NOT NULL,
  `t_data` longtext NOT NULL,
  `t_time` varchar(15) NOT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_data
-- ----------------------------
INSERT INTO `users_data` VALUES ('1', '1', '每日好心情', ' 感谢伤害你的人，因为他磨练了你的心志；感谢欺骗你的人，因为他增进了你的智慧；感谢中伤你的人，因为他砥砺了你的人格；感谢鞭打你的人，因为他激发了你的斗志；感谢遗弃你的人，因为他教导了你该独立；感谢绊倒你的人，因为他强化了你的能力；感谢斥责你的人，因为他提醒了你的缺点。怀着一颗感恩的心，感激一切使你成长的人！\r\n 感谢伤害你的人，因为他磨练了你的心志；感谢欺骗你的人，因为他增进了你的智慧；感谢中伤你的人，因为他砥砺了你的人格；感谢鞭打你的人，因为他激发了你的斗志；感谢遗弃你的人，因为他教导了你该独立；感谢绊倒你的人，因为他强化了你的能力；感谢斥责你的人，因为他提醒了你的缺点。怀着一颗感恩的心，感激一切使你成长的人！\r\n 感谢伤害你的人，因为他磨练了你的心志；感谢欺骗你的人，因为他增进了你的智慧；感谢中伤你的人，因为他砥砺了你的人格；感谢鞭打你的人，因为他激发了你的斗志；感谢遗弃你的人，因为他教导了你该独立；感谢绊倒你的人，因为他强化了你的能力；感谢斥责你的人，因为他提醒了你的缺点。怀着一颗感恩的心，感激一切使你成长的人！\r\n', '20151030192001');
INSERT INTO `users_data` VALUES ('2', '1', '测试123', '感谢老板', '20161030182005');

-- ----------------------------
-- Table structure for users_info
-- ----------------------------
DROP TABLE IF EXISTS `users_info`;
CREATE TABLE `users_info` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_nickname` varchar(20) NOT NULL,
  `u_sex` varchar(10) NOT NULL,
  `u_ico` varchar(50) NOT NULL,
  `u_sign` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_info
-- ----------------------------
INSERT INTO `users_info` VALUES ('1', '春风沐雨', '男', '1', '\r\n╰つ 情不知所起 一往情深゛\r\n');
INSERT INTO `users_info` VALUES ('2', '習慣沉默', '女', '2', '\r\n无论这个世界对你怎样，都请你一如既往的努力、勇敢、充满希望。\r\n');
INSERT INTO `users_info` VALUES ('3', '我是逗比我自豪', '男', '3', '人生只有出走的美丽，而没有等出来的辉煌。');
INSERT INTO `users_info` VALUES ('4', '陌上柳絮', '女', '4', '现在的努力决定你以后是抽烟还是点烟！');

-- ----------------------------
-- Table structure for users_online
-- ----------------------------
DROP TABLE IF EXISTS `users_online`;
CREATE TABLE `users_online` (
  `u_id` int(11) NOT NULL,
  `u_time` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_online
-- ----------------------------
INSERT INTO `users_online` VALUES ('1', '20161030163005');

-- ----------------------------
-- Table structure for users_password
-- ----------------------------
DROP TABLE IF EXISTS `users_password`;
CREATE TABLE `users_password` (
  `u_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `u_username` varchar(20) NOT NULL,
  `u_password` varchar(20) NOT NULL,
  `u_power` tinyint(3) NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_password
-- ----------------------------
INSERT INTO `users_password` VALUES ('1', '123', '123', '1');
INSERT INTO `users_password` VALUES ('2', 'admin', 'admin111', '1');
INSERT INTO `users_password` VALUES ('3', 'qwe', 'qwer', '0');
INSERT INTO `users_password` VALUES ('4', '1203', '10', '1');
