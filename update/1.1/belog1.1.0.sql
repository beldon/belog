

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_auth_menu_permissions
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_menu_permissions`;
CREATE TABLE `t_auth_menu_permissions` (
  `m_id` bigint(20) NOT NULL,
  `p_id` bigint(20) NOT NULL,
  PRIMARY KEY (`m_id`,`p_id`),
  KEY `FK_qfwo729v2fch0korb4e4x4fti` (`p_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_menu_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment_meta
-- ----------------------------
DROP TABLE IF EXISTS `t_comment_meta`;
CREATE TABLE `t_comment_meta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(225) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `comment_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jr2v448qnfyayjac90vmm44j9` (`comment_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment_meta
-- ----------------------------

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `agent` varchar(255) DEFAULT NULL,
  `approved` varchar(20) DEFAULT NULL,
  `author` varchar(255) NOT NULL,
  `author_email` varchar(100) DEFAULT NULL,
  `author_ip` varchar(100) DEFAULT NULL,
  `author_url` varchar(200) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `karma` int(11) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rx1h1wroyxpi53uo7ao9yi94q` (`post_id`) USING BTREE,
  KEY `FK_bh19ds51esxs6xiu77y6jnvgc` (`user_id`) USING BTREE
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
  `description` varchar(225) DEFAULT NULL,
  `image` varchar(225) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `rel` varchar(225) DEFAULT NULL,
  `rss` varchar(225) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `target` varchar(25) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `url` varchar(225) DEFAULT NULL,
  `visible` varchar(20) DEFAULT NULL,
  `owner` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ohvdm3x464mnv7m2o61w3a1lh` (`owner`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
-- Table structure for t_options
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auto_load` varchar(20) DEFAULT 'yes',
  `name` varchar(64) NOT NULL,
  `type` varchar(200) NOT NULL,
  `value` longtext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_dgear72sshi9g8oxkt93trwew` (`name`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
-- Table structure for t_post_meta
-- ----------------------------
DROP TABLE IF EXISTS `t_post_meta`;
CREATE TABLE `t_post_meta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `meta_value` varchar(255) DEFAULT NULL,
  `meta_key` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3komgwix21wx5u5dys0spxmnm` (`post_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_posts
-- ----------------------------
DROP TABLE IF EXISTS `t_posts`;
CREATE TABLE `t_posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_count` bigint(20) DEFAULT '0',
  `comment_status` varchar(20) NOT NULL,
  `content` longtext NOT NULL,
  `content_filtered` varchar(255) DEFAULT NULL,
  `cre_date` datetime NOT NULL,
  `excerpt` varchar(255) DEFAULT NULL,
  `menu_order` int(11) DEFAULT '0',
  `mime_type` varchar(100) DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `parent` bigint(20) DEFAULT '0',
  `password` varchar(20) DEFAULT NULL,
  `ping_status` varchar(20) NOT NULL,
  `pinged` varchar(255) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `to_ping` varchar(255) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o0dab243es9hdi2ag93de8trs` (`user_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
  KEY `FK_nrli8k56cr8t7i6sid0ey75xx` (`p_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_roles_permissions
-- ----------------------------

-- ----------------------------
-- Table structure for t_taxonomy
-- ----------------------------
DROP TABLE IF EXISTS `t_taxonomy`;
CREATE TABLE `t_taxonomy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` bigint(20) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `parent` bigint(20) DEFAULT '0',
  `taxonomy` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `term_group` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for t_taxonomy_relationships
-- ----------------------------
DROP TABLE IF EXISTS `t_taxonomy_relationships`;
CREATE TABLE `t_taxonomy_relationships` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `term_order` int(11) DEFAULT NULL,
  `type` varchar(200) DEFAULT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `taxonomy_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f5rgv270y1stkr93hva88pi1e` (`type`) USING BTREE,
  KEY `FK_l03snqvrwj2ifr7bahlw36jgv` (`object_id`) USING BTREE,
  KEY `FK_may5kdfj3lhit8oa5sdx2r1g` (`taxonomy_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_meta
-- ----------------------------
DROP TABLE IF EXISTS `t_user_meta`;
CREATE TABLE `t_user_meta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(225) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_gh0pnvbem7joow9pu8o7vb2if` (`user_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_meta
-- ----------------------------

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activation_key` varchar(60) DEFAULT NULL,
  `display_name` varchar(250) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `login` varchar(60) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `pass` varchar(64) NOT NULL,
  `registered` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', null, 'admin', 'email', 'admin', 'admin', 'edac1a03b0a9a8e024342c172454897a92e4a3c35eaaf36b1d7d0702f5b95fdc', '2016-02-02 11:50:06', '0', null);

-- ----------------------------
-- Table structure for t_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `t_users_roles`;
CREATE TABLE `t_users_roles` (
  `u_id` bigint(20) NOT NULL,
  `r_id` bigint(20) NOT NULL,
  PRIMARY KEY (`u_id`,`r_id`),
  KEY `FK_mmffyiigjp208wtp2agal681n` (`r_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users_roles
-- ----------------------------
