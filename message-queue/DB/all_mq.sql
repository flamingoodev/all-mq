DROP DATABASE IF EXISTS `all_mq`;
CREATE DATABASE sso_master DEFAULT CHARACTER SET utf8;

-- ----------------------------
-- Table structure for sys_connection_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_connection_config`;
CREATE TABLE `sys_connection_config` (
  `connection_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '连接配置ID',
  `connection_code` VARCHAR(50) NOT NULL COMMENT '*连接配置编码',
  `connection_name` VARCHAR(50) NOT NULL COMMENT '*连接配置名称',
  `protocol` varchar(20) NOT NULL DEFAULT '' COMMENT '*协议/方式',
  `domain` varchar(50) NOT NULL DEFAULT '' COMMENT '*域名/IP/地址',
  `port` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '*端口',
  `arguments1` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '参数1',
  `arguments2` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '参数2',
  `arguments3` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '参数3',
  `arguments4` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '参数4',
  `arguments5` VARCHAR(200) NOT NULL DEFAULT '' COMMENT '参数5',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码/授权',
  `remark` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '备注',
  -- 用户自定义字段Start 预留5个字符型字段 只在定制开发时使用
  `user_item1` VARCHAR(100) DEFAULT '' COMMENT '自定义字段1',
  `user_item2` VARCHAR(100) DEFAULT '' COMMENT '自定义字段2',
  `user_item3` VARCHAR(100) DEFAULT '' COMMENT '自定义字段3',
  `user_item4` VARCHAR(100) DEFAULT '' COMMENT '自定义字段4',
  `user_item5` VARCHAR(100) DEFAULT '' COMMENT '自定义字段5',
  -- 用户自定义字段End
  `update_user` VARCHAR(50) DEFAULT 'system' COMMENT '更新人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`connection_id`),
  UNIQUE KEY `unique_1` (`connection_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8 COMMENT='连接配置表';

-- ----------------------------
-- Records of sys_connection_config
-- ----------------------------
INSERT INTO `sys_connection_config` VALUES ('1', 'ONE-MQ-BROKER', 'MQ服务', 'tcp', '127.0.0.1', '61616', 'ActiveMQ', '', '', '', '', 'admin', 'admin', '连接消息队列客户端，参数1只能填写ActiveMQ或者Kafka，参数2可填写多个IP（半角逗号分隔），修改后需重启当前服务才能生效。', '', '', '', '', '', 'system', '2019-04-20 00:00:00');
-- INSERT INTO `sys_connection_config` VALUES ('2', 'ONE-MQ-BROKER', 'MQ服务', '', '127.0.0.1', '9092', 'Kafka', '', '', '', '', '', '', '连接消息队列客户端，参数1只能填写ActiveMQ或者Kafka，参数2可填写多个IP（半角逗号分隔），修改后需重启当前服务才能生效。', '', '', '', '', '', 'system', '2019-04-20 00:00:00');