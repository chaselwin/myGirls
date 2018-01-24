/*
Navicat MySQL Data Transfer

Source Server         : www.saidbye.com
Source Server Version : 50719
Source Host           : www.saidbye.com:3306
Source Database       : girl

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-01-08 00:01:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_type` varchar(255) DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_object_name` varchar(255) DEFAULT NULL,
  `image_memo` varchar(255) DEFAULT NULL,
  `image_create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `image_creator` varchar(255) DEFAULT NULL,
  `image_update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `image_updator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `image_id` (`id`),
  KEY `image_type` (`image_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
