/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.24 : Database - association
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`association` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `association`;

/*Table structure for table `activity` */

DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_fee` decimal(10,2) DEFAULT NULL COMMENT '活动价格',
  `activity_name` varchar(20) DEFAULT NULL COMMENT '活动名称',
  `activity_introduction` varchar(1000) DEFAULT NULL COMMENT '活动介绍',
  `activity_date` datetime DEFAULT NULL COMMENT '活动开始时间',
  `activity_length_min` int(11) DEFAULT NULL COMMENT '活动时长(分钟)',
  `contacts` varchar(50) DEFAULT NULL COMMENT '联系人 联系方式',
  `activity_address` varchar(200) DEFAULT NULL COMMENT '活动地址',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity` */

/*Table structure for table `activity_order` */

DROP TABLE IF EXISTS `activity_order`;

CREATE TABLE `activity_order` (
  `activity_order_id` varchar(20) NOT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `order_type` tinyint(1) DEFAULT NULL COMMENT '0为会员下的订单，1为学员下的订单',
  `member_id` varchar(20) DEFAULT NULL COMMENT '会员订单则不为空',
  `student_id` int(11) DEFAULT NULL COMMENT '学员订单则不为空',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_begin_time` datetime DEFAULT NULL,
  `payment_state` tinyint(1) DEFAULT NULL COMMENT '0为未付款，1为已付款',
  `close` tinyint(1) DEFAULT '0' COMMENT '1为订单关闭',
  PRIMARY KEY (`activity_order_id`),
  KEY `activity_id` (`activity_id`),
  KEY `member_id` (`member_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `activity_order_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`),
  CONSTRAINT `activity_order_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `activity_order_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity_order` */

/*Table structure for table `activity_order_student` */

DROP TABLE IF EXISTS `activity_order_student`;

CREATE TABLE `activity_order_student` (
  `activity_order_id` varchar(20) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  KEY `activity_order_id` (`activity_order_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `activity_order_student_ibfk_1` FOREIGN KEY (`activity_order_id`) REFERENCES `activity_order` (`activity_order_id`),
  CONSTRAINT `activity_order_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity_order_student` */

/*Table structure for table `activity_student` */

DROP TABLE IF EXISTS `activity_student`;

CREATE TABLE `activity_student` (
  `activity_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL COMMENT '报名时间',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '1为报名成功',
  `seat_number` varchar(15) DEFAULT NULL COMMENT '座位号',
  KEY `student_id` (`student_id`),
  KEY `activity_id` (`activity_id`),
  CONSTRAINT `activity_student_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `activity_student_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity_student` */

/*Table structure for table `alipay_log` */

DROP TABLE IF EXISTS `alipay_log`;

CREATE TABLE `alipay_log` (
  `pay_order_id` varchar(50) NOT NULL COMMENT '支付订单编号',
  `create_time` datetime DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',
  PRIMARY KEY (`pay_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `alipay_log` */

/*Table structure for table `as_admin` */

DROP TABLE IF EXISTS `as_admin`;

CREATE TABLE `as_admin` (
  `as_admin_id` varchar(20) NOT NULL,
  `as_admin_password` varchar(20) DEFAULT NULL,
  `access` tinyint(1) DEFAULT '0' COMMENT '是否是审核管理员',
  PRIMARY KEY (`as_admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `as_admin` */

/*Table structure for table `association` */

DROP TABLE IF EXISTS `association`;

CREATE TABLE `association` (
  `association_name` varchar(30) DEFAULT NULL COMMENT '协会名称',
  `association_introduce` text COMMENT '协会介绍'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `association` */

/*Table structure for table `certificate` */

DROP TABLE IF EXISTS `certificate`;

CREATE TABLE `certificate` (
  `certificate_id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) DEFAULT NULL COMMENT '证书所属培训',
  `certificate_length_month` tinyint(4) DEFAULT NULL COMMENT '证书有效时长(月)',
  `certificate_length_day` tinyint(4) DEFAULT NULL COMMENT '证书有效时长(天)',
  `certificate_module` varchar(200) DEFAULT NULL COMMENT '证书模板url',
  `certificate_name` varchar(100) DEFAULT NULL COMMENT '证书名',
  `certificate_content` varchar(300) DEFAULT NULL COMMENT '证书内容',
  PRIMARY KEY (`certificate_id`),
  KEY `training_id` (`training_id`),
  CONSTRAINT `certificate_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate` */

/*Table structure for table `certificate_change_order` */

DROP TABLE IF EXISTS `certificate_change_order`;

CREATE TABLE `certificate_change_order` (
  `certificate_change_order_id` varchar(20) NOT NULL,
  `certificate_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `order_type` tinyint(1) DEFAULT NULL COMMENT '0为会员下的订单，1为学员下的订单',
  `member_id` varchar(20) DEFAULT NULL COMMENT '如果为会员的订单此处应不为空',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_begin_time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `payment_state` tinyint(1) DEFAULT NULL COMMENT '0为未付款，1为已付款',
  `close` tinyint(1) DEFAULT '0' COMMENT '1为订单关闭',
  PRIMARY KEY (`certificate_change_order_id`),
  KEY `certificate_id` (`certificate_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `certificate_change_order_ibfk_1` FOREIGN KEY (`certificate_id`) REFERENCES `certificate` (`certificate_id`),
  CONSTRAINT `certificate_change_order_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_change_order` */

/*Table structure for table `certificate_re_student` */

DROP TABLE IF EXISTS `certificate_re_student`;

CREATE TABLE `certificate_re_student` (
  `certificate_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `certificate_end_date` date DEFAULT NULL COMMENT '证书有效期',
  `certificate_begin_date` date DEFAULT NULL COMMENT '证书生效日期',
  `member_name` varchar(30) DEFAULT NULL COMMENT '所属单位',
  KEY `certificate_id` (`certificate_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `certificate_re_student_ibfk_1` FOREIGN KEY (`certificate_id`) REFERENCES `certificate` (`certificate_id`),
  CONSTRAINT `certificate_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_re_student` */

/*Table structure for table `certificate_recheck_order` */

DROP TABLE IF EXISTS `certificate_recheck_order`;

CREATE TABLE `certificate_recheck_order` (
  `certificate_recheck_order_id` varchar(20) NOT NULL,
  `certificate_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_begin_time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `payment_state` tinyint(1) DEFAULT NULL COMMENT '0为未付款，1为已付款',
  `close` tinyint(1) DEFAULT NULL COMMENT '1为订单关闭',
  PRIMARY KEY (`certificate_recheck_order_id`),
  KEY `certificate_id` (`certificate_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `certificate_recheck_order_ibfk_1` FOREIGN KEY (`certificate_id`) REFERENCES `certificate` (`certificate_id`),
  CONSTRAINT `certificate_recheck_order_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_recheck_order` */

/*Table structure for table `coupon_member` */

DROP TABLE IF EXISTS `coupon_member`;

CREATE TABLE `coupon_member` (
  `coupon_id` varchar(20) NOT NULL COMMENT '优惠券码',
  `member_id` varchar(20) DEFAULT NULL COMMENT '所属会员',
  `value` smallint(6) DEFAULT NULL COMMENT '券值',
  `state` tinyint(1) DEFAULT '0' COMMENT '0为未使用，1为已使用',
  PRIMARY KEY (`coupon_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `coupon_member_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `coupon_member` */

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `exam_id` int(20) NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(30) DEFAULT NULL,
  `training_id` int(20) DEFAULT NULL COMMENT '所属培训id',
  `exam_num` smallint(4) DEFAULT NULL COMMENT '题目数量',
  `exam_pass` smallint(4) DEFAULT NULL COMMENT '考试及格分数',
  `exam_length_min` tinyint(4) DEFAULT NULL COMMENT '考试时长(分钟)',
  `exam_start_time` time DEFAULT NULL,
  `exam_end_time` time DEFAULT NULL,
  `exam_date` date DEFAULT NULL,
  PRIMARY KEY (`exam_id`),
  KEY `training_id` (`training_id`),
  CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `exam` */

insert  into `exam`(`exam_id`,`exam_name`,`training_id`,`exam_num`,`exam_pass`,`exam_length_min`,`exam_start_time`,`exam_end_time`,`exam_date`) values (1,'质量测试',1,20,55,60,'12:00:00','14:00:00','2019-09-26'),(2,'食品测试',2,20,55,80,'13:00:00','15:00:00','2019-10-01'),(3,'监督测试',3,20,65,70,'14:00:00','16:00:00','2019-09-22'),(4,'网络建设',4,30,60,60,'18:00:00','20:00:00','2019-11-22');

/*Table structure for table `exam_question_student_answer` */

DROP TABLE IF EXISTS `exam_question_student_answer`;

CREATE TABLE `exam_question_student_answer` (
  `exam_id` int(11) NOT NULL COMMENT '学生试题答案表',
  `question_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `answer_one` tinyint(4) DEFAULT NULL,
  `answer_two` tinyint(4) DEFAULT NULL,
  `answer_three` tinyint(4) DEFAULT NULL,
  `answer_four` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`exam_id`,`question_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `exam_question_student_answer_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `exam_question_student_answer_ibfk_3` FOREIGN KEY (`exam_id`, `question_id`) REFERENCES `exam_re_question` (`exam_id`, `question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exam_question_student_answer` */

insert  into `exam_question_student_answer`(`exam_id`,`question_id`,`student_id`,`answer_one`,`answer_two`,`answer_three`,`answer_four`) values (1,1,1,1,NULL,NULL,NULL),(1,2,1,3,NULL,NULL,NULL),(1,3,1,2,NULL,NULL,NULL),(1,4,1,1,4,3,NULL);

/*Table structure for table `exam_re_question` */

DROP TABLE IF EXISTS `exam_re_question`;

CREATE TABLE `exam_re_question` (
  `exam_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `score` int(11) DEFAULT NULL COMMENT '题目在该试卷中的分数',
  `question_index` int(11) DEFAULT NULL COMMENT '题目在该试卷中的序号',
  PRIMARY KEY (`exam_id`,`question_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `exam_re_question_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`),
  CONSTRAINT `exam_re_question_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exam_re_question` */

insert  into `exam_re_question`(`exam_id`,`question_id`,`score`,`question_index`) values (1,1,5,1),(1,2,6,2),(1,3,7,3),(1,4,7,4);

/*Table structure for table `exam_re_student` */

DROP TABLE IF EXISTS `exam_re_student`;

CREATE TABLE `exam_re_student` (
  `exam_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `score` int(6) DEFAULT NULL COMMENT '考试分数',
  `remaining_times` tinyint(4) DEFAULT '3' COMMENT '可重考剩余次数',
  `is_invalid` tinyint(1) DEFAULT '0' COMMENT '1为无效(考试不通过)',
  KEY `exam_id` (`exam_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `exam_re_student_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`),
  CONSTRAINT `exam_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exam_re_student` */

insert  into `exam_re_student`(`exam_id`,`student_id`,`score`,`remaining_times`,`is_invalid`) values (1,1,55,2,0),(1,2,34,1,0),(2,1,41,0,0),(4,1,56,1,0);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `member_id` varchar(20) NOT NULL,
  `member_phone` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `member_password` varchar(20) NOT NULL DEFAULT '123456',
  `member_tel` varchar(20) DEFAULT NULL COMMENT '座机',
  `member_address` varchar(100) DEFAULT NULL COMMENT '地址',
  `member_name` varchar(40) DEFAULT NULL COMMENT '单位或机构名称',
  `member_province` varchar(20) DEFAULT NULL COMMENT '会员省份(直辖市)',
  `member_city` varchar(20) DEFAULT NULL COMMENT '会员所在市',
  `vip` tinyint(1) DEFAULT '0' COMMENT '是否为协会会员',
  `vip_end_date` date DEFAULT NULL COMMENT '会员到期时间',
  `enter_date` date DEFAULT NULL COMMENT '入会时间(非vip)',
  `vip_begin_date` date DEFAULT NULL COMMENT '成为协会会员时间',
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `Phone` (`member_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member` */

/*Table structure for table `member_re_student` */

DROP TABLE IF EXISTS `member_re_student`;

CREATE TABLE `member_re_student` (
  `member_id` varchar(20) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  KEY `member_id` (`member_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `member_re_student_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `member_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member_re_student` */

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `as_admin_id` varchar(20) DEFAULT NULL COMMENT '发布的管理员',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '简介',
  `content` text COMMENT '内容',
  `post_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`news_id`),
  KEY `as_admin_id` (`as_admin_id`),
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`as_admin_id`) REFERENCES `as_admin` (`as_admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `news` */

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单类型',
  `order_type` tinyint(4) NOT NULL COMMENT '3种类型，1为培训缴费，2为证书改动缴费，3为证书复核缴费',
  `third_party_id` varchar(50) DEFAULT NULL COMMENT '第三方支付订单号',
  `third_party_type` tinyint(4) DEFAULT NULL COMMENT '1为微信支付，2为支付宝支付，3为银行卡转账，4为线下支付',
  `create_time` datetime DEFAULT NULL COMMENT '支付完成时间',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `pay_or_not` tinyint(1) DEFAULT '0' COMMENT '1为已付款',
  `receive_or_not` tinyint(1) DEFAULT '0' COMMENT '1为已收款',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `payment` */

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) DEFAULT NULL COMMENT '题目所属的培训',
  `question_state` varchar(255) DEFAULT NULL COMMENT '题干',
  `type` tinyint(1) DEFAULT NULL COMMENT '0为单选，1为多选',
  `choice_a` varchar(30) DEFAULT NULL COMMENT '选项A',
  `choice_b` varchar(30) DEFAULT NULL COMMENT '选项B',
  `choice_c` varchar(30) DEFAULT NULL COMMENT '...',
  `choice_d` varchar(30) DEFAULT NULL,
  `right_choice_one` tinyint(4) DEFAULT NULL COMMENT '正确选项',
  `right_choice_two` tinyint(4) DEFAULT NULL COMMENT '1~8 1代表A',
  `right_choice_three` tinyint(4) DEFAULT NULL COMMENT '1~8 2代表B',
  `right_choice_four` tinyint(4) DEFAULT NULL COMMENT '1~8 3代表C',
  PRIMARY KEY (`question_id`),
  KEY `training_id` (`training_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`question_id`,`training_id`,`question_state`,`type`,`choice_a`,`choice_b`,`choice_c`,`choice_d`,`right_choice_one`,`right_choice_two`,`right_choice_three`,`right_choice_four`) values (1,1,'以下属于物理层的设备是',0,'中继器','以太网交换机','桥','网关',1,NULL,NULL,NULL),(2,1,' 在以太网中，是根据___地址来区分不同的设备的.',0,'LLC地址','MAC地址','IP地址','IPX地址',2,NULL,NULL,NULL),(3,1,'下面哪种LAN 是应用CSMA/CD协议的',0,'令牌环','FDDI','ETHERNET','NOVELL',3,NULL,NULL,NULL),(4,1,'在路由器上可以出现的端口是',1,'Console端口','AUX端口','PCI端口','RJ45端口',1,2,4,NULL);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_password` varchar(20) NOT NULL DEFAULT '123456',
  `student_idcard` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `student_phone` varchar(20) DEFAULT NULL,
  `student_name` varchar(12) DEFAULT NULL,
  `student_email` varchar(30) DEFAULT NULL,
  `student_positon` varchar(20) DEFAULT NULL COMMENT '职位',
  `student_country` varchar(20) DEFAULT NULL COMMENT '地址',
  `student_province` varchar(20) DEFAULT NULL,
  `student_city` varchar(20) DEFAULT NULL,
  `student_area` varchar(20) DEFAULT NULL COMMENT '所在区镇',
  `student_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `UNIQUE1` (`student_phone`),
  UNIQUE KEY `UNIQUE2` (`student_idcard`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`student_id`,`student_password`,`student_idcard`,`student_phone`,`student_name`,`student_email`,`student_positon`,`student_country`,`student_province`,`student_city`,`student_area`,`student_address`) values (1,'123456','445281199308310056','15521054785','张三','12@qq.com','经理','中国','广东省','广州市','番禺区','大学城华南理工大学'),(2,'123456','445281199308310037','15521064789','李四','22@qq.com',NULL,NULL,NULL,NULL,NULL,NULL),(3,'123456','445281199308310033','15521064781',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `super_admin` */

DROP TABLE IF EXISTS `super_admin`;

CREATE TABLE `super_admin` (
  `su_id` varchar(20) NOT NULL DEFAULT 'root',
  `su_password` varchar(20) NOT NULL DEFAULT '000000',
  PRIMARY KEY (`su_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `super_admin` */

/*Table structure for table `training` */

DROP TABLE IF EXISTS `training`;

CREATE TABLE `training` (
  `training_id` int(20) NOT NULL AUTO_INCREMENT,
  `training_name` varchar(20) NOT NULL,
  `training_introduce` varchar(500) DEFAULT NULL COMMENT '培训介绍',
  `training_fee_normal` decimal(10,2) DEFAULT NULL COMMENT '培训费用',
  `training_fee_vip` decimal(10,2) DEFAULT NULL COMMENT '协会会员折扣价',
  `training_end_time` datetime DEFAULT NULL COMMENT '培训结束时间',
  `training_start_time` datetime DEFAULT NULL COMMENT '培训开始时间',
  `post_time` datetime DEFAULT NULL COMMENT '发布时间',
  `level` tinyint(4) DEFAULT '1',
  `training_pic` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `training` */

insert  into `training`(`training_id`,`training_name`,`training_introduce`,`training_fee_normal`,`training_fee_vip`,`training_end_time`,`training_start_time`,`post_time`,`level`,`training_pic`) values (1,'质量检测','示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n示例范文示例范文示例范文示例范文','199.99','199.00','2019-09-28 16:13:03','2019-09-13 16:12:30','2019-09-21 00:41:14',1,NULL),(2,'食品安全','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','888.01','699.01','2019-10-03 12:00:00','2019-10-01 12:00:00','2019-07-15 00:41:19',2,NULL),(3,'食品监督','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','2000.00','1899.11','2019-09-11 16:14:32','2019-09-05 16:14:25','2020-02-06 00:41:22',1,NULL),(4,'网络工程','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','800.90','700.00','2019-10-26 16:14:40','2019-09-11 16:14:36','2019-11-01 00:41:27',3,NULL),(5,'产品规范','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','99.99','98.99','2019-11-08 16:14:56','2019-09-29 16:14:44','2019-10-17 00:41:35',1,NULL);

/*Table structure for table `training_order` */

DROP TABLE IF EXISTS `training_order`;

CREATE TABLE `training_order` (
  `training_order_id` varchar(20) NOT NULL COMMENT '订单号',
  `training_id` int(11) DEFAULT NULL,
  `order_type` tinyint(1) DEFAULT '0' COMMENT '0为会员下的订单，1为学员下的订单',
  `member_id` varchar(20) DEFAULT NULL COMMENT '会员订单则不为空',
  `student_id` int(11) DEFAULT NULL COMMENT '学员订单则不为空',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_begin_time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `payment_state` tinyint(1) DEFAULT '0' COMMENT '0为未付款，1为已付款',
  `close` tinyint(1) DEFAULT '0' COMMENT '1为订单关闭',
  PRIMARY KEY (`training_order_id`),
  KEY `training_id` (`training_id`),
  KEY `member_id` (`member_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `training_order_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`),
  CONSTRAINT `training_order_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `training_order_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `training_order` */

/*Table structure for table `training_order_student` */

DROP TABLE IF EXISTS `training_order_student`;

CREATE TABLE `training_order_student` (
  `training_order_id` varchar(20) DEFAULT NULL,
  `student_id` int(20) DEFAULT NULL,
  KEY `training_order_id` (`training_order_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `training_order_student_ibfk_1` FOREIGN KEY (`training_order_id`) REFERENCES `training_order` (`training_order_id`),
  CONSTRAINT `training_order_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `training_order_student` */

/*Table structure for table `training_re_student` */

DROP TABLE IF EXISTS `training_re_student`;

CREATE TABLE `training_re_student` (
  `training_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL COMMENT '报名时间',
  `is_invalid` tinyint(1) DEFAULT '0' COMMENT '1为无效(考试不通过)',
  KEY `training_id` (`training_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `training_re_student_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`),
  CONSTRAINT `training_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `training_re_student` */

insert  into `training_re_student`(`training_id`,`student_id`,`begin_time`,`is_invalid`) values (1,1,'2019-09-14 23:20:23',0),(1,2,'2019-09-19 23:20:29',0);

/*Table structure for table `vote` */

DROP TABLE IF EXISTS `vote`;

CREATE TABLE `vote` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_type` tinyint(4) DEFAULT NULL COMMENT '0为会员投票，1为学院投票，2为皆可',
  `optional_sum` tinyint(4) DEFAULT NULL COMMENT '选项总数量',
  `optional_num` tinyint(4) DEFAULT NULL COMMENT '可多选数目',
  `vote_one` varchar(50) DEFAULT NULL,
  `vote_two` varchar(50) DEFAULT NULL,
  `vote_three` varchar(50) DEFAULT NULL,
  `vote_four` varchar(50) DEFAULT NULL,
  `vote_five` varchar(50) DEFAULT NULL,
  `vote_six` varchar(50) DEFAULT NULL,
  `vote_seven` varchar(50) DEFAULT NULL,
  `vote_eight` varchar(50) DEFAULT NULL,
  `vote_nine` varchar(50) DEFAULT NULL,
  `vote_ten` varchar(50) DEFAULT NULL,
  `vote_eleven` varchar(50) DEFAULT NULL,
  `vote_twelve` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vote` */

/*Table structure for table `vote_student_member` */

DROP TABLE IF EXISTS `vote_student_member`;

CREATE TABLE `vote_student_member` (
  `vote_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `member_id` varchar(20) DEFAULT NULL,
  `choice` tinyint(4) DEFAULT NULL COMMENT '选项1~12',
  KEY `vote_id` (`vote_id`),
  KEY `member_id` (`member_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `vote_student_member_ibfk_1` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`),
  CONSTRAINT `vote_student_member_ibfk_3` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `vote_student_member_ibfk_4` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vote_student_member` */

/*Table structure for table `web_mail` */

DROP TABLE IF EXISTS `web_mail`;

CREATE TABLE `web_mail` (
  `mail_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) DEFAULT NULL COMMENT '0发给会员，1发给学员',
  `student_id` int(11) DEFAULT NULL,
  `member_id` varchar(20) DEFAULT NULL,
  `message_content` text COMMENT '信息内容',
  `send_time` datetime DEFAULT NULL COMMENT '站内信发送时间',
  PRIMARY KEY (`mail_id`),
  KEY `member_id` (`member_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `web_mail_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `web_mail_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `web_mail` */

/*Table structure for table `wxpay_log` */

DROP TABLE IF EXISTS `wxpay_log`;

CREATE TABLE `wxpay_log` (
  `pay_order_id` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`pay_order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wxpay_log` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
