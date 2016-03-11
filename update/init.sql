/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : belog

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2016-03-05 00:43:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_auth_menu_permissions
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_menu_permissions`;
CREATE TABLE `t_auth_menu_permissions` (
  `m_id` bigint(20) NOT NULL,
  `p_id` bigint(20) NOT NULL,
  PRIMARY KEY (`m_id`,`p_id`),
  KEY `FK_qfwo729v2fch0korb4e4x4fti` (`p_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_menu_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for t_commentmeta
-- ----------------------------
DROP TABLE IF EXISTS `t_commentmeta`;
CREATE TABLE `t_commentmeta` (
  `meta_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meta_key` varchar(225) DEFAULT NULL,
  `meta_value` varchar(255) DEFAULT NULL,
  `comment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`meta_id`),
  KEY `FK_jr2v448qnfyayjac90vmm44j9` (`comment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_commentmeta
-- ----------------------------

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `comment_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_agent` varchar(255) NOT NULL,
  `comment_approved` varchar(20) NOT NULL,
  `comment_author` varchar(255) NOT NULL,
  `comment_author_email` varchar(100) NOT NULL,
  `comment_author_IP` varchar(100) NOT NULL,
  `comment_author_url` varchar(200) NOT NULL,
  `comment_content` varchar(255) NOT NULL,
  `comment_date` datetime NOT NULL,
  `comment_date_gmt` datetime NOT NULL,
  `comment_karma` int(11) NOT NULL,
  `comment_parent` bigint(20) NOT NULL,
  `comment_type` varchar(20) NOT NULL,
  `comment_post_ID` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comment_ID`),
  KEY `FK_rx1h1wroyxpi53uo7ao9yi94q` (`comment_post_ID`),
  KEY `FK_bh19ds51esxs6xiu77y6jnvgc` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comments
-- ----------------------------

-- ----------------------------
-- Table structure for t_links
-- ----------------------------
DROP TABLE IF EXISTS `t_links`;
CREATE TABLE `t_links` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `link_description` varchar(225) DEFAULT NULL,
  `link_image` varchar(225) DEFAULT NULL,
  `link_name` varchar(225) DEFAULT NULL,
  `link_notes` varchar(255) DEFAULT NULL,
  `link_rel` varchar(225) DEFAULT NULL,
  `link_rss` varchar(225) DEFAULT NULL,
  `link_sort` int(11) DEFAULT NULL,
  `link_target` varchar(25) DEFAULT NULL,
  `link_updated` datetime DEFAULT NULL,
  `link_url` varchar(225) DEFAULT NULL,
  `link_visible` varchar(20) DEFAULT NULL,
  `link_owner` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ohvdm3x464mnv7m2o61w3a1lh` (`link_owner`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_links
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_options
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
  `option_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `autoload` varchar(20) NOT NULL,
  `option_name` varchar(64) NOT NULL,
  `type` varchar(200) NOT NULL,
  `option_value` longtext NOT NULL,
  PRIMARY KEY (`option_id`),
  UNIQUE KEY `UK_dgear72sshi9g8oxkt93trwew` (`option_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_options
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(225) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `value` varchar(225) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_postmeta
-- ----------------------------
DROP TABLE IF EXISTS `t_postmeta`;
CREATE TABLE `t_postmeta` (
  `meta_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meta_value` varchar(255) DEFAULT NULL,
  `meta_key` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`meta_id`),
  KEY `FK_3komgwix21wx5u5dys0spxmnm` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_postmeta
-- ----------------------------

-- ----------------------------
-- Table structure for t_posts
-- ----------------------------
DROP TABLE IF EXISTS `t_posts`;
CREATE TABLE `t_posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_count` bigint(20) DEFAULT NULL,
  `comment_status` varchar(20) NOT NULL,
  `post_content` longtext NOT NULL,
  `post_content_filtered` varchar(255) DEFAULT NULL,
  `post_date` datetime NOT NULL,
  `post_excerpt` varchar(255) DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  `post_mime_type` varchar(100) DEFAULT NULL,
  `post_modified` datetime DEFAULT NULL,
  `post_name` varchar(200) DEFAULT NULL,
  `post_parent` bigint(20) DEFAULT NULL,
  `post_password` varchar(20) DEFAULT NULL,
  `ping_status` varchar(20) NOT NULL,
  `pinged` varchar(255) DEFAULT NULL,
  `post_status` varchar(20) NOT NULL,
  `post_title` varchar(255) NOT NULL,
  `to_ping` varchar(255) DEFAULT NULL,
  `post_type` varchar(20) NOT NULL,
  `post_author` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o0dab243es9hdi2ag93de8trs` (`post_author`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_posts
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `role_name` varchar(225) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `t_roles_permissions`;
CREATE TABLE `t_roles_permissions` (
  `r_id` bigint(20) NOT NULL,
  `p_id` bigint(20) NOT NULL,
  PRIMARY KEY (`r_id`,`p_id`),
  KEY `FK_nrli8k56cr8t7i6sid0ey75xx` (`p_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_roles_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for t_term_relationships
-- ----------------------------
DROP TABLE IF EXISTS `t_term_relationships`;
CREATE TABLE `t_term_relationships` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `term_order` int(11) DEFAULT NULL,
  `links_id` bigint(20) DEFAULT NULL,
  `posts_id` bigint(20) DEFAULT NULL,
  `term_taxonomy_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f5rgv270y1stkr93hva88pi1e` (`links_id`),
  KEY `FK_l03snqvrwj2ifr7bahlw36jgv` (`posts_id`),
  KEY `FK_may5kdfj3lhit8oa5sdx2r1g` (`term_taxonomy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_term_relationships
-- ----------------------------

-- ----------------------------
-- Table structure for t_term_taxonomy
-- ----------------------------
DROP TABLE IF EXISTS `t_term_taxonomy`;
CREATE TABLE `t_term_taxonomy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `taxonomy` varchar(255) DEFAULT NULL,
  `term_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7meaiq4u9epjmdl4ll5en64cb` (`term_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_term_taxonomy
-- ----------------------------

-- ----------------------------
-- Table structure for t_terms
-- ----------------------------
DROP TABLE IF EXISTS `t_terms`;
CREATE TABLE `t_terms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `termGroup` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_terms
-- ----------------------------

-- ----------------------------
-- Table structure for t_usermeta
-- ----------------------------
DROP TABLE IF EXISTS `t_usermeta`;
CREATE TABLE `t_usermeta` (
  `umeta_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `umeta_key` varchar(225) DEFAULT NULL,
  `umeta_value` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`umeta_id`),
  KEY `FK_gh0pnvbem7joow9pu8o7vb2if` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_usermeta
-- ----------------------------

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activation_key` varchar(60) DEFAULT NULL,
  `display_name` varchar(250) NOT NULL,
  `email` varchar(100) NOT NULL,
  `login` varchar(60) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `pass` varchar(64) NOT NULL,
  `registered` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', null, 'beldon', 'email', 'beldon', 'beldon', 'edac1a03b0a9a8e024342c172454897a92e4a3c35eaaf36b1d7d0702f5b95fdc', '2016-02-02 11:50:06', '0', null);

-- ----------------------------
-- Table structure for t_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_users_roles`;
CREATE TABLE `t_users_roles` (
  `u_id` bigint(20) NOT NULL,
  `r_id` bigint(20) NOT NULL,
  PRIMARY KEY (`u_id`,`r_id`),
  KEY `FK_mmffyiigjp208wtp2agal681n` (`r_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users_roles
-- ----------------------------
