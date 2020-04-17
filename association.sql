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
  `activity_address` varchar(200) DEFAULT NULL COMMENT '活动地址',
  `open_time` datetime DEFAULT NULL COMMENT '开放报名时间',
  `close_time` datetime DEFAULT NULL COMMENT '关闭报名时间',
  `post_time` datetime DEFAULT NULL COMMENT '活动发布时间',
  `contact_name` varchar(10) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  `max_num` int(11) DEFAULT NULL COMMENT '活动可容纳最大人数',
  `area_width` int(11) DEFAULT NULL COMMENT '场地矩形行座位数',
  `area_length` int(11) DEFAULT NULL COMMENT '场地矩形列座位数',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `activity` */

insert  into `activity`(`activity_id`,`activity_fee`,`activity_name`,`activity_introduction`,`activity_date`,`activity_length_min`,`activity_address`,`open_time`,`close_time`,`post_time`,`contact_name`,`contact_phone`,`max_num`,`area_width`,`area_length`) values (1,'188.88','垃圾分类讲解','  今年夏天，垃圾分类无疑是最火的话题。|作为全国46个垃圾分类试点城市之一，|长沙市围绕垃圾分类开展了形式多样、内容丰富的宣传活动，趣味游戏、知识宣讲、在线答题、儿童手抄报……一场全民参与、声势浩大的“全民生活垃圾分类之旅”让环保理念深植人心。','2019-10-01 20:00:00',60,'广东省广州市华南理工大学大学城校区','2019-09-25 20:00:00','2019-10-09 02:32:42','2019-10-05 02:41:10','王先生','13333333333',50,NULL,NULL),(2,'299.99','英语角','  我们的英语俱乐部旨在为大家创造一个英语的交流环境，让所有想学英语，想说好英语的人们，在这样一个极具特色的氛围中学好英语，|在外语老师的引导下，大家会积极交流，大胆开口说，培养纯正的外语语感，让你更加自信说英语，并结交更多的外国友人，|避免找个好工作， 而因为英语困惑自己~~~~','2019-09-30 15:00:00',120,'广东省广州市华南理工大学大学城校区','2019-09-25 13:00:00','2019-10-19 00:32:46','2019-10-04 02:41:14','李先生','13555555555',59,NULL,NULL),(3,'199.99','“拥抱春天，播种绿色”植树节活动','  植树节是按照法律规定宣传保护树木，并组织动员群众积极参加以植树造林为活动内容的节日。按时间长短可分为植树日、植树周和植树月，共称为国际植树节。提倡通过这种活动，激发人们爱林造林的热情、意识到环保的重要性。',NULL,NULL,'广东省广州市华南理工大学大学城校区','2019-09-24 02:28:46','2019-09-30 02:32:52','2019-10-18 02:41:16','李先生','13666666666',46,NULL,NULL),(4,'213.22','主题餐厅','  主题餐厅旨在为大家提供一起品尝美食的机会','2019-11-09 14:28:55',NULL,NULL,'2019-10-09 14:29:09','2019-11-16 14:29:12','2019-09-05 02:41:20',NULL,NULL,40,NULL,NULL);

/*Table structure for table `activity_order` */

DROP TABLE IF EXISTS `activity_order`;

CREATE TABLE `activity_order` (
  `order_key_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单主键',
  `activity_order_id` varchar(20) NOT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `order_type` tinyint(1) DEFAULT NULL COMMENT '0为会员下的订单，1为学员下的订单',
  `member_key_id` int(11) DEFAULT NULL COMMENT '会员订单则不为空',
  `student_id` int(11) DEFAULT NULL COMMENT '学员订单则不为空',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_begin_time` datetime DEFAULT NULL,
  `payment_state` tinyint(1) DEFAULT '0' COMMENT '0为未付款，1为已付款',
  `close` tinyint(1) DEFAULT '0' COMMENT '1为订单关闭',
  PRIMARY KEY (`order_key_id`),
  UNIQUE KEY `activity_order_id` (`activity_order_id`),
  KEY `activity_id` (`activity_id`),
  KEY `student_id` (`student_id`),
  KEY `member_key_id` (`member_key_id`),
  KEY `key_id` (`order_key_id`),
  CONSTRAINT `activity_order_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`),
  CONSTRAINT `activity_order_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `activity_order_ibfk_4` FOREIGN KEY (`member_key_id`) REFERENCES `member` (`member_key_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `activity_order` */

insert  into `activity_order`(`order_key_id`,`activity_order_id`,`activity_id`,`order_type`,`member_key_id`,`student_id`,`order_price`,`order_begin_time`,`payment_state`,`close`) values (1,'20191001145853382363',1,1,NULL,1,'188.88','2019-10-01 14:58:54',1,0),(2,'20191009163126575245',4,0,1,NULL,'426.44','2019-10-09 16:31:27',1,0),(3,'20191009163703148065',4,0,1,NULL,'213.22','2019-10-09 16:37:04',0,0),(4,'20191010164121915078',4,0,1,NULL,'426.44','2019-10-10 16:41:22',0,0),(5,'20200313145643717849',3,0,1,NULL,'399.98','2020-03-13 14:56:44',0,0),(8,'20200313150507599301',3,0,1,NULL,'199.99','2020-03-13 15:05:08',0,0);

/*Table structure for table `activity_order_student` */

DROP TABLE IF EXISTS `activity_order_student`;

CREATE TABLE `activity_order_student` (
  `order_key_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `is_paid` tinyint(1) DEFAULT '0' COMMENT '0:未支付',
  KEY `student_id` (`student_id`),
  KEY `order_key_id` (`order_key_id`,`student_id`),
  CONSTRAINT `activity_order_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `activity_order_student_ibfk_3` FOREIGN KEY (`order_key_id`) REFERENCES `activity_order` (`order_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity_order_student` */

insert  into `activity_order_student`(`order_key_id`,`student_id`,`is_paid`) values (2,1,1),(2,8,1),(3,2,0),(4,38,0),(4,39,0),(5,1,0),(5,2,0),(8,8,0);

/*Table structure for table `activity_seat` */

DROP TABLE IF EXISTS `activity_seat`;

CREATE TABLE `activity_seat` (
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `real_x` int(11) DEFAULT NULL COMMENT '横坐标',
  `real_y` int(11) DEFAULT NULL COMMENT '纵坐标',
  `is_lock` tinyint(1) DEFAULT '0' COMMENT '1:锁定座位',
  `is_occupied` tinyint(1) DEFAULT '1' COMMENT '0:空座 1:已有人',
  `fake_x` int(11) DEFAULT NULL COMMENT '展示给用户的X座',
  `fake_y` int(11) DEFAULT NULL COMMENT '展示给用户的Y行',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '座位主键',
  PRIMARY KEY (`id`),
  KEY `activity_id` (`activity_id`),
  CONSTRAINT `activity_seat_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity_seat` */

/*Table structure for table `activity_student` */

DROP TABLE IF EXISTS `activity_student`;

CREATE TABLE `activity_student` (
  `apply_number` varchar(20) NOT NULL COMMENT '报名编号',
  `activity_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL COMMENT '报名时间',
  `seat_id` int(11) DEFAULT NULL COMMENT '活动座位表外键',
  `seat_number` varchar(15) DEFAULT NULL COMMENT '座位号',
  PRIMARY KEY (`apply_number`),
  KEY `student_id` (`student_id`),
  KEY `activity_id` (`activity_id`),
  KEY `seat_id` (`seat_id`),
  CONSTRAINT `activity_student_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `activity_student_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`),
  CONSTRAINT `activity_student_ibfk_3` FOREIGN KEY (`seat_id`) REFERENCES `activity_seat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity_student` */

insert  into `activity_student`(`apply_number`,`activity_id`,`student_id`,`apply_time`,`seat_id`,`seat_number`) values ('34cvsf',4,1,'2019-10-09 16:33:40',NULL,'1-5'),('asdasd',1,1,'2019-10-09 14:38:36',NULL,'4-6'),('ssdas1',4,8,'2019-10-09 16:34:09',NULL,'1-12');

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_account` varchar(20) DEFAULT NULL COMMENT '管理员账号',
  `admin_password` varchar(20) DEFAULT '123456',
  `permission_value` tinyint(1) DEFAULT '0' COMMENT '0位普通管理员，1为审核管理员',
  `is_locked` tinyint(1) DEFAULT '0' COMMENT '状态：0正常，1锁定',
  `admin_name` varchar(10) DEFAULT NULL COMMENT '管理员名',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

insert  into `admin`(`admin_id`,`admin_account`,`admin_password`,`permission_value`,`is_locked`,`admin_name`) values (1,'admin','admin',1,0,NULL),(2,'admin22','123456',0,0,'李逵');

/*Table structure for table `admin_log` */

DROP TABLE IF EXISTS `admin_log`;

CREATE TABLE `admin_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志编号',
  `description` varchar(50) DEFAULT NULL COMMENT '操作描述',
  `time` datetime DEFAULT NULL COMMENT '操作时间',
  `request_url` varchar(40) DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(10) DEFAULT NULL COMMENT '请求方式',
  `respond` varchar(500) DEFAULT NULL COMMENT '返回结果',
  `admin_name` varchar(10) DEFAULT NULL COMMENT '操作人',
  `admin_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_log` */

/*Table structure for table `admin_re_authority` */

DROP TABLE IF EXISTS `admin_re_authority`;

CREATE TABLE `admin_re_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL,
  `authority_id` int(11) DEFAULT NULL,
  `authority_name` varchar(50) DEFAULT NULL,
  `has_or_not` tinyint(1) DEFAULT '0' COMMENT '是否有权限 1:有',
  PRIMARY KEY (`id`),
  UNIQUE KEY `admin_id` (`admin_id`,`authority_id`),
  KEY `authority_id` (`authority_id`),
  CONSTRAINT `admin_re_authority_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `admin_re_authority_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `admin_re_authority` */

insert  into `admin_re_authority`(`id`,`admin_id`,`authority_id`,`authority_name`,`has_or_not`) values (1,1,1,NULL,1),(2,1,2,NULL,1),(3,1,3,NULL,1),(4,1,4,NULL,1),(5,1,5,NULL,0),(6,1,6,NULL,0),(7,1,7,NULL,0),(8,1,8,NULL,0),(9,1,9,NULL,0),(10,1,10,NULL,0),(11,1,11,NULL,0),(12,1,12,NULL,0),(13,2,1,'member',0),(14,2,2,'student',0),(15,2,3,'training',0),(16,2,4,'exam',1),(17,2,5,'grade',1),(18,2,6,'certificate',1),(19,2,7,'activity',0),(20,2,8,'vote',0),(21,2,9,'index',0),(22,2,10,'system',0),(23,2,11,'order',0),(24,2,12,'coupon',0);

/*Table structure for table `association` */

DROP TABLE IF EXISTS `association`;

CREATE TABLE `association` (
  `association_name` varchar(30) DEFAULT NULL COMMENT '协会名称',
  `association_introduce` varchar(1000) DEFAULT NULL COMMENT '协会介绍'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `association` */

/*Table structure for table `authority` */

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `authority_id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `authority` */

insert  into `authority`(`authority_id`,`authority`) values (1,'member'),(2,'student'),(3,'training'),(4,'exam'),(5,'grade'),(6,'certificate'),(7,'activity'),(8,'vote'),(9,'index'),(10,'system'),(11,'order'),(12,'coupon');

/*Table structure for table `certificate` */

DROP TABLE IF EXISTS `certificate`;

CREATE TABLE `certificate` (
  `certificate_id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) DEFAULT NULL COMMENT '证书所属培训',
  `certificate_length_day` tinyint(4) DEFAULT NULL COMMENT '证书有效时长(天)',
  `certificate_module` varchar(200) DEFAULT NULL COMMENT '证书模板url',
  `certificate_name` varchar(100) DEFAULT NULL COMMENT '证书名',
  `certificate_content` varchar(300) DEFAULT NULL COMMENT '证书内容',
  `recheck_fee` decimal(10,2) DEFAULT NULL COMMENT '证书复审费用',
  PRIMARY KEY (`certificate_id`),
  KEY `training_id` (`training_id`),
  CONSTRAINT `certificate_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate` */

/*Table structure for table `certificate_change_log` */

DROP TABLE IF EXISTS `certificate_change_log`;

CREATE TABLE `certificate_change_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `re_id` int(11) DEFAULT NULL COMMENT '证书——学生 唯一id',
  `op_description` varchar(50) DEFAULT NULL COMMENT '操作描述',
  `op_time` datetime DEFAULT NULL COMMENT '操作时间',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '1:操作成功 0操作失败',
  `op_user_type` varchar(20) DEFAULT NULL COMMENT '操作人类型(student/member/admin)',
  `op_user_id` varchar(20) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_change_log` */

/*Table structure for table `certificate_change_order` */

DROP TABLE IF EXISTS `certificate_change_order`;

CREATE TABLE `certificate_change_order` (
  `order_key_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单主键',
  `certificate_change_order_id` varchar(20) NOT NULL,
  `certificate_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `order_type` tinyint(1) DEFAULT NULL COMMENT '0为会员下的订单，1为学员下的订单',
  `member_key_id` int(11) DEFAULT NULL COMMENT '如果为会员的订单此处应不为空',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_begin_time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `payment_state` tinyint(1) DEFAULT NULL COMMENT '0为未付款，1为已付款',
  `close` tinyint(1) DEFAULT '0' COMMENT '1为订单关闭',
  PRIMARY KEY (`order_key_id`),
  KEY `certificate_id` (`certificate_id`),
  KEY `student_id` (`student_id`),
  KEY `member_key_id` (`member_key_id`),
  KEY `key_id` (`order_key_id`),
  CONSTRAINT `certificate_change_order_ibfk_1` FOREIGN KEY (`certificate_id`) REFERENCES `certificate` (`certificate_id`),
  CONSTRAINT `certificate_change_order_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `certificate_change_order_ibfk_3` FOREIGN KEY (`member_key_id`) REFERENCES `member` (`member_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_change_order` */

/*Table structure for table `certificate_change_queue` */

DROP TABLE IF EXISTS `certificate_change_queue`;

CREATE TABLE `certificate_change_queue` (
  `re_id` int(11) DEFAULT NULL,
  `change_description` varchar(50) DEFAULT NULL COMMENT '变更描述',
  `op_user_type` tinyint(1) DEFAULT NULL COMMENT '0:学员申请 1:会员申请',
  `student_id` int(11) DEFAULT NULL,
  `member_key_id` int(11) DEFAULT NULL COMMENT '若为学员申请则置null',
  KEY `re_id` (`re_id`),
  KEY `student_id` (`student_id`),
  KEY `member_key_id` (`member_key_id`),
  CONSTRAINT `certificate_change_queue_ibfk_1` FOREIGN KEY (`re_id`) REFERENCES `certificate_re_student` (`re_id`),
  CONSTRAINT `certificate_change_queue_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `certificate_change_queue_ibfk_3` FOREIGN KEY (`member_key_id`) REFERENCES `member` (`member_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_change_queue` */

/*Table structure for table `certificate_re_student` */

DROP TABLE IF EXISTS `certificate_re_student`;

CREATE TABLE `certificate_re_student` (
  `re_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `certificate_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `certificate_end_date` date DEFAULT NULL COMMENT '证书失效日期',
  `certificate_begin_date` date DEFAULT NULL COMMENT '证书生效日期',
  `member_name` varchar(30) DEFAULT NULL COMMENT '所属单位',
  `recheckable` tinyint(1) DEFAULT '1' COMMENT '0:不可再申请复审',
  PRIMARY KEY (`re_id`),
  KEY `certificate_id` (`certificate_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `certificate_re_student_ibfk_1` FOREIGN KEY (`certificate_id`) REFERENCES `certificate` (`certificate_id`),
  CONSTRAINT `certificate_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_re_student` */

/*Table structure for table `certificate_recheck_log` */

DROP TABLE IF EXISTS `certificate_recheck_log`;

CREATE TABLE `certificate_recheck_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `re_id` int(11) DEFAULT NULL COMMENT '证书——学生 唯一id',
  `op_description` varchar(50) DEFAULT NULL COMMENT '操作描述',
  `op_time` datetime DEFAULT NULL COMMENT '操作时间',
  `is_success` tinyint(1) DEFAULT NULL COMMENT '1:操作成功 0操作失败',
  `op_user_type` varchar(20) DEFAULT NULL COMMENT '操作人类型(student/admin)',
  `op_user_id` varchar(20) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`log_id`),
  KEY `re_id` (`re_id`),
  CONSTRAINT `certificate_recheck_log_ibfk_1` FOREIGN KEY (`re_id`) REFERENCES `certificate_re_student` (`re_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_recheck_log` */

/*Table structure for table `certificate_recheck_order` */

DROP TABLE IF EXISTS `certificate_recheck_order`;

CREATE TABLE `certificate_recheck_order` (
  `order_key_id` int(11) NOT NULL AUTO_INCREMENT,
  `certificate_recheck_order_id` varchar(20) NOT NULL,
  `certificate_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_begin_time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `payment_state` tinyint(1) DEFAULT NULL COMMENT '0为未付款，1为已付款',
  `close` tinyint(1) DEFAULT NULL COMMENT '1为订单关闭',
  PRIMARY KEY (`order_key_id`),
  KEY `certificate_id` (`certificate_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `certificate_recheck_order_ibfk_1` FOREIGN KEY (`certificate_id`) REFERENCES `certificate` (`certificate_id`),
  CONSTRAINT `certificate_recheck_order_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_recheck_order` */

/*Table structure for table `certificate_recheck_queue` */

DROP TABLE IF EXISTS `certificate_recheck_queue`;

CREATE TABLE `certificate_recheck_queue` (
  `re_id` int(11) DEFAULT NULL COMMENT '证书——学生 唯一id',
  KEY `re_id` (`re_id`),
  CONSTRAINT `certificate_recheck_queue_ibfk_1` FOREIGN KEY (`re_id`) REFERENCES `certificate_re_student` (`re_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_recheck_queue` */

/*Table structure for table `coupon` */

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
  `coupon_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL COMMENT '金额折扣:0  百分比折扣:1',
  `amount_discount` decimal(10,2) DEFAULT NULL COMMENT '金额折扣形式(两位小数)',
  `percent_discount` int(11) DEFAULT NULL COMMENT '百分比折扣形式(-1~100%)',
  PRIMARY KEY (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `coupon` */

/*Table structure for table `coupon_member` */

DROP TABLE IF EXISTS `coupon_member`;

CREATE TABLE `coupon_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) DEFAULT NULL,
  `coupon_code` varchar(20) NOT NULL COMMENT '优惠券码（唯一）',
  `member_key_id` int(11) DEFAULT NULL COMMENT '所属会员',
  `state` tinyint(1) DEFAULT '0' COMMENT '0为未使用，1为已使用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `coupon_code` (`coupon_code`),
  KEY `member_key_id` (`member_key_id`),
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `coupon_member_ibfk_1` FOREIGN KEY (`member_key_id`) REFERENCES `member` (`member_key_id`),
  CONSTRAINT `coupon_member_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`coupon_id`)
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
  `exam_start_time` datetime DEFAULT NULL,
  `exam_end_time` datetime DEFAULT NULL,
  `is_posted` tinyint(1) DEFAULT '0' COMMENT '0:未发布 1:已发布',
  `have_questions` tinyint(1) DEFAULT '0' COMMENT '0:无题目 1:已加题目',
  `create_time` datetime DEFAULT NULL COMMENT '考试创建(添加)时间',
  PRIMARY KEY (`exam_id`),
  KEY `training_id` (`training_id`),
  CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `exam` */

insert  into `exam`(`exam_id`,`exam_name`,`training_id`,`exam_num`,`exam_pass`,`exam_length_min`,`exam_start_time`,`exam_end_time`,`is_posted`,`have_questions`,`create_time`) values (8,'网络工程考试1',4,20,60,120,'2019-10-01 00:00:00','2019-10-31 00:00:00',1,1,NULL),(9,'网络工程考试2',4,10,40,40,'2019-10-01 00:00:00','2019-11-05 00:00:00',1,1,NULL),(10,'我',1,10,55,34,'1970-01-01 08:00:00','1970-01-01 08:00:00',0,0,NULL),(11,'444',2,34,34,55,NULL,NULL,0,1,NULL),(12,'测试考试',3,10,60,5,'2020-04-18 00:00:00','2020-05-13 00:00:00',0,1,NULL);

/*Table structure for table `exam_question_student_answer` */

DROP TABLE IF EXISTS `exam_question_student_answer`;

CREATE TABLE `exam_question_student_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) NOT NULL COMMENT '学生试题答案表',
  `question_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `answer_one` tinyint(4) DEFAULT NULL,
  `answer_two` tinyint(4) DEFAULT NULL,
  `answer_three` tinyint(4) DEFAULT NULL,
  `answer_four` tinyint(4) DEFAULT NULL,
  `correct_or_not` tinyint(1) DEFAULT NULL COMMENT '0:错误 1:正确',
  PRIMARY KEY (`id`),
  UNIQUE KEY `exam_id` (`exam_id`,`question_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `exam_question_student_answer_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `exam_question_student_answer_ibfk_3` FOREIGN KEY (`exam_id`, `question_id`) REFERENCES `exam_re_question` (`exam_id`, `question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `exam_question_student_answer` */

insert  into `exam_question_student_answer`(`id`,`exam_id`,`question_id`,`student_id`,`answer_one`,`answer_two`,`answer_three`,`answer_four`,`correct_or_not`) values (1,9,3,1,4,3,NULL,NULL,NULL),(2,9,8,1,1,NULL,NULL,NULL,NULL),(3,9,9,1,1,NULL,NULL,NULL,NULL),(4,9,10,1,1,NULL,NULL,NULL,NULL),(5,9,15,1,4,NULL,NULL,NULL,NULL),(6,9,20,1,1,NULL,NULL,NULL,NULL),(7,9,29,1,1,NULL,NULL,NULL,NULL),(8,9,33,1,1,NULL,NULL,NULL,NULL),(9,9,35,1,1,NULL,NULL,NULL,NULL),(10,9,40,1,1,NULL,NULL,NULL,NULL);

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

insert  into `exam_re_question`(`exam_id`,`question_id`,`score`,`question_index`) values (8,1,5,16),(8,4,5,12),(8,7,5,17),(8,10,5,1),(8,11,5,19),(8,12,5,9),(8,14,5,11),(8,15,5,3),(8,16,5,10),(8,17,5,5),(8,18,5,2),(8,25,5,4),(8,26,5,14),(8,27,5,20),(8,29,5,7),(8,30,5,8),(8,32,5,6),(8,33,5,13),(8,35,5,15),(8,40,5,18),(9,3,10,6),(9,8,10,1),(9,9,10,7),(9,10,10,8),(9,15,10,3),(9,20,10,9),(9,29,10,5),(9,33,10,4),(9,35,10,2),(9,40,10,10),(10,1,10,2),(10,2,10,3),(10,3,10,4),(10,4,10,5),(10,7,10,6),(10,8,10,9),(10,9,10,10),(10,12,10,7),(10,31,10,8),(10,55,10,1),(11,1,2,2),(11,2,2,29),(11,4,2,17),(11,6,2,19),(11,8,2,28),(11,10,2,12),(11,11,2,16),(11,12,2,23),(11,14,2,20),(11,15,34,34),(11,16,2,7),(11,17,2,5),(11,18,2,11),(11,19,2,9),(11,20,2,8),(11,21,2,14),(11,23,2,13),(11,24,2,10),(11,25,2,31),(11,26,2,3),(11,27,2,21),(11,28,2,25),(11,29,2,18),(11,30,2,1),(11,31,2,22),(11,32,2,27),(11,33,2,15),(11,34,2,4),(11,35,2,6),(11,37,2,33),(11,38,2,24),(11,39,2,26),(11,41,2,32),(11,56,2,30),(12,1,10,10),(12,16,10,9),(12,17,10,6),(12,20,10,5),(12,24,10,3),(12,25,10,2),(12,35,10,1),(12,37,10,8),(12,39,10,7),(12,56,10,4);

/*Table structure for table `exam_re_student` */

DROP TABLE IF EXISTS `exam_re_student`;

CREATE TABLE `exam_re_student` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩单编号',
  `exam_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `score` int(6) DEFAULT NULL COMMENT '考试分数',
  `remaining_times` tinyint(4) DEFAULT '3' COMMENT '可重考剩余次数',
  `is_invalid` tinyint(1) DEFAULT '0' COMMENT '1为无效(考试不通过)',
  `is_verify` tinyint(4) DEFAULT '0' COMMENT '0:未审核 1:审核不通过 2:审核通过',
  `in_line` tinyint(1) DEFAULT '0' COMMENT '0:不在审核队列里 1:在审核队列里',
  `not_pass_reason` varchar(100) DEFAULT NULL COMMENT '审核不通过理由',
  `not_pass_times` int(11) DEFAULT NULL COMMENT '审核不通过次数，被驳回一次+1',
  PRIMARY KEY (`report_id`),
  KEY `student_id` (`student_id`),
  KEY `exam_id` (`exam_id`),
  CONSTRAINT `exam_re_student_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`),
  CONSTRAINT `exam_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `exam_re_student` */

insert  into `exam_re_student`(`report_id`,`exam_id`,`student_id`,`score`,`remaining_times`,`is_invalid`,`is_verify`,`in_line`,`not_pass_reason`,`not_pass_times`) values (22,8,1,60,2,0,2,0,NULL,NULL),(23,9,1,30,1,0,2,0,NULL,NULL);

/*Table structure for table `exam_report_op_log` */

DROP TABLE IF EXISTS `exam_report_op_log`;

CREATE TABLE `exam_report_op_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志编号',
  `report_id` int(11) DEFAULT NULL COMMENT '成绩单编号',
  `exam_id` int(11) DEFAULT NULL COMMENT '考试id',
  `admin_id` int(11) DEFAULT NULL COMMENT '操作管理员id',
  `op_description` varchar(50) DEFAULT NULL COMMENT '操作描述',
  `op_time` datetime DEFAULT NULL COMMENT '操作时间',
  `is_success` tinyint(1) DEFAULT '1' COMMENT '1:操作成功 0操作失败',
  `admin_name` varchar(20) DEFAULT NULL,
  `student_name` varchar(20) DEFAULT NULL,
  `is_pass` tinyint(1) DEFAULT '0' COMMENT '0:未通过  1:通过',
  PRIMARY KEY (`log_id`),
  KEY `report_id` (`report_id`),
  KEY `exam_report_op_log_ibfk_2` (`exam_id`),
  CONSTRAINT `exam_report_op_log_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `exam_re_student` (`report_id`),
  CONSTRAINT `exam_report_op_log_ibfk_2` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `exam_report_op_log` */

insert  into `exam_report_op_log`(`log_id`,`report_id`,`exam_id`,`admin_id`,`op_description`,`op_time`,`is_success`,`admin_name`,`student_name`,`is_pass`) values (9,22,8,1,'将成绩单22放入管理员ID:1的审核队列中','2020-04-13 22:18:43',1,NULL,'张三',NULL),(10,23,9,1,'将成绩单23放入管理员ID:1的审核队列中','2020-04-13 22:18:44',1,NULL,'张三',NULL),(11,22,8,1,'通过成绩单22，并移出队列','2020-04-14 15:40:29',1,NULL,'张三',NULL),(12,23,9,1,'通过成绩单23，并移出队列','2020-04-14 15:40:33',1,NULL,'张三',NULL);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `member_key_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `member_id` varchar(20) NOT NULL COMMENT 'uuid',
  `member_phone` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `member_email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `member_password` varchar(20) NOT NULL DEFAULT '123456',
  `member_tel` varchar(20) DEFAULT NULL COMMENT '座机',
  `member_name` varchar(40) DEFAULT NULL COMMENT '单位或机构名称',
  `is_vip` tinyint(1) DEFAULT '0' COMMENT '是否为协会会员 0:否 1:是',
  `enter_date` date DEFAULT NULL COMMENT '入会时间(非vip)',
  `vip_end_date` date DEFAULT NULL COMMENT '会员到期时间',
  `vip_begin_date` date DEFAULT NULL COMMENT '成为协会会员时间',
  `member_country` varchar(20) DEFAULT NULL,
  `member_province` varchar(20) DEFAULT NULL COMMENT '会员省份(直辖市)',
  `member_city` varchar(20) DEFAULT NULL COMMENT '会员所在市',
  `member_area` varchar(20) DEFAULT NULL COMMENT '所在区镇',
  `member_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`member_key_id`),
  UNIQUE KEY `member_id` (`member_id`),
  UNIQUE KEY `Phone` (`member_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `member` */

insert  into `member`(`member_key_id`,`member_id`,`member_phone`,`member_email`,`member_password`,`member_tel`,`member_name`,`is_vip`,`enter_date`,`vip_end_date`,`vip_begin_date`,`member_country`,`member_province`,`member_city`,`member_area`,`member_address`) values (1,'mb_ca33eae307ae4','15521065463','12345@163.com','123456','222102312','AA股份有限公司',1,'2019-09-19','2019-12-22','2019-09-20','中国','江苏省','南京市','鼓楼区','南京大学');

/*Table structure for table `member_re_student` */

DROP TABLE IF EXISTS `member_re_student`;

CREATE TABLE `member_re_student` (
  `student_id` int(11) NOT NULL,
  `member_key_id` int(11) NOT NULL,
  UNIQUE KEY `student_id` (`student_id`,`member_key_id`),
  KEY `member_key_id` (`member_key_id`),
  CONSTRAINT `member_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `member_re_student_ibfk_3` FOREIGN KEY (`member_key_id`) REFERENCES `member` (`member_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member_re_student` */

insert  into `member_re_student`(`student_id`,`member_key_id`) values (1,1),(38,1),(39,1),(40,1);

/*Table structure for table `member_subscription_order` */

DROP TABLE IF EXISTS `member_subscription_order`;

CREATE TABLE `member_subscription_order` (
  `subscription_order_id` varchar(20) NOT NULL COMMENT '协会会员会费订单',
  `member_key_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `payment_state` tinyint(1) DEFAULT '0' COMMENT '0:未付款 1:已付款',
  `close` tinyint(1) DEFAULT '0' COMMENT '0:订单有效/待支付 1:订单关闭/完成',
  PRIMARY KEY (`subscription_order_id`),
  KEY `member_key_id` (`member_key_id`),
  CONSTRAINT `member_subscription_order_ibfk_1` FOREIGN KEY (`member_key_id`) REFERENCES `member` (`member_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member_subscription_order` */

/*Table structure for table `message_text` */

DROP TABLE IF EXISTS `message_text`;

CREATE TABLE `message_text` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message_text` */

/*Table structure for table `message_to_admin` */

DROP TABLE IF EXISTS `message_to_admin`;

CREATE TABLE `message_to_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL COMMENT '三选一',
  `member_id` int(11) DEFAULT NULL COMMENT '三选一',
  `student_id` int(11) DEFAULT NULL COMMENT '三选一',
  `who_send` tinyint(4) DEFAULT NULL COMMENT '管理员法:0 学生发:1 会员发:2',
  `message_id` int(11) DEFAULT NULL COMMENT '对应的站内信id',
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_id` (`admin_id`),
  KEY `member_id` (`member_id`),
  KEY `student_id` (`student_id`),
  KEY `message_id` (`message_id`),
  CONSTRAINT `message_to_admin_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `message_to_admin_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_key_id`),
  CONSTRAINT `message_to_admin_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `message_to_admin_ibfk_4` FOREIGN KEY (`message_id`) REFERENCES `message_text` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message_to_admin` */

/*Table structure for table `message_to_member` */

DROP TABLE IF EXISTS `message_to_member`;

CREATE TABLE `message_to_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL COMMENT '发送信息的管理员id',
  `member_id` int(11) DEFAULT NULL COMMENT '接收信息的会员id',
  `message_id` int(11) DEFAULT NULL COMMENT '站内信id',
  `status` tinyint(1) DEFAULT '0' COMMENT '1为已读',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message_to_member` */

/*Table structure for table `message_to_student` */

DROP TABLE IF EXISTS `message_to_student`;

CREATE TABLE `message_to_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL COMMENT '发出信息的管理员',
  `student_id` int(11) DEFAULT NULL COMMENT '接收的学员编号',
  `message_id` int(11) DEFAULT NULL COMMENT '站内信编号',
  `status` tinyint(1) DEFAULT '0' COMMENT '1为已读',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`),
  KEY `admin_id` (`admin_id`),
  KEY `student_id` (`student_id`),
  KEY `message_to_student_ibfk_1` (`message_id`),
  CONSTRAINT `message_to_student_ibfk_1` FOREIGN KEY (`message_id`) REFERENCES `message_text` (`message_id`),
  CONSTRAINT `message_to_student_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `message_to_student_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message_to_student` */

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL COMMENT '发布的管理员',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '简介',
  `content` text COMMENT '内容',
  `post_time` datetime DEFAULT NULL COMMENT '发布时间',
  `type` varchar(10) DEFAULT NULL COMMENT '新闻类别',
  PRIMARY KEY (`news_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
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
  `type` int(2) DEFAULT NULL COMMENT '0:单选 1:多选 2:判断',
  `choice_a` varchar(100) DEFAULT NULL COMMENT '选项A',
  `choice_b` varchar(100) DEFAULT NULL COMMENT '选项B',
  `choice_c` varchar(100) DEFAULT NULL COMMENT '...',
  `choice_d` varchar(100) DEFAULT NULL,
  `right_choice_one` tinyint(4) DEFAULT NULL COMMENT '正确选项',
  `right_choice_two` tinyint(4) DEFAULT NULL COMMENT '1~4 1代表A',
  `right_choice_three` tinyint(4) DEFAULT NULL COMMENT '1~4 2代表B',
  `right_choice_four` tinyint(4) DEFAULT NULL COMMENT '1~4 3代表C',
  PRIMARY KEY (`question_id`),
  KEY `training_id` (`training_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`question_id`,`training_id`,`question_state`,`type`,`choice_a`,`choice_b`,`choice_c`,`choice_d`,`right_choice_one`,`right_choice_two`,`right_choice_three`,`right_choice_four`) values (1,NULL,'以下属于物理层的设备是',0,'中继器','以太网交换机','桥','网关',1,NULL,NULL,NULL),(2,NULL,'下面哪种LAN 是应用CSMA/CD协议的',1,'令牌环','FDDI','ETHERNET','NOVELL',3,4,NULL,NULL),(3,NULL,'下面哪种LAN 是应用CSMA/CD协议的',1,'令牌环','FDDI','ETHERNET','NOVELL',3,4,NULL,NULL),(4,NULL,'在路由器上可以出现的端口是',1,'Console端口','AUX端口','PCI端口','RJ45端口',1,2,4,NULL),(6,NULL,'系统采用PPP协议（其成帧方法是带字节填充的帧界标记法），收方接收到的字节序列为： 10 20 7D 5E 30 5E 7D 5D。那么，发方发送的原始序列应该是多少？',0,'10 20 5E 30 5E 5D','10 20 7E 30 5E 5D','10 20 30','10 20 7E 30 5E 7D',4,NULL,NULL,NULL),(7,NULL,'系统采用带位填充的帧界标记法，收方从网络接收到一个字符串是 1110111110。那么，发送发送的原始字符串是什么？',0,'11101111100','11111111','1110111110','111011111',4,NULL,NULL,NULL),(8,NULL,'系统采用的码字分别是 0000，0011， 1100， 1111（分别对应待传送的码 00， 01， 10 和 11）。请使用海明距离判断：该系统可以检测出多少位错误？',0,'0','3','2','1',4,NULL,NULL,NULL),(9,NULL,'有一个字符串1001011，采用偶校验进行检错。试计算应追加到字符串后的校验位是什么？',0,'1','4','10010110','0',4,NULL,NULL,NULL),(10,NULL,'考虑客户端与服务器建立 TCP 连接。客户端发送一个 SYN 数据段，序列号 7000，并让服务器回应一个 SYN 数据段，序列号为 9000 ， ACK 设置为7001。以下哪一个选项最好地描述了客户端向服务器发送下一个数据段的情况？',0,'SYN 数据段序列号 7001 ， ACK 号为 9000','标准数据段（即，SYN标志未设置） 序列号为 7001 ，ACK 号为 9001','标准数据段 （即，SYN 标志未设置） 序列号为 7001 ，ACK 号为 9000','SYN 数据段序列号 7000 ， ACK 号为 9001',2,NULL,NULL,NULL),(11,NULL,'考虑一个RTT 为 20 ms的 TCP 连接。假设网络容量是 10 Mbps ，数据段是 1000 Bytes。如果要充分利用网络容量，所需的滑动窗口大小是多少？',0,'200 kilobits','25000 packets','400 packets','400 kilobits',1,NULL,NULL,NULL),(12,NULL,'考虑 TCP 的流量控制机制。假设由 TCP 接收方发送的最近一个确认消息有以下字段： ACK = 12000，WIN = 8000。下列哪一项不是发送方的可以传输的有效的消息？（也就是说，如果发送方发送此消息，接收方没有足够的缓冲空间来充分处理该消息。此外请注意发送方可能传输的字节已经超过 12000 序列号，这些字节可能在传输过程中。因此传输的下一个数据段的序列号能超过 12000)。',0,'发送方可以传输 2000 字节数据段， SEQ = 18100','发送方可以传输 1500 字节数据段， SEQ = 18100','发送方可以传输 1000 字节数据段， SEQ = 18000','发送方可以传输 2000 字节数据段， SEQ = 17000',1,NULL,NULL,NULL),(13,NULL,'下列哪个信息不是TCP端点发送的数据段头部中的一部分？',1,'端点接收到的数据段中的目标端口','目的地 IP 报头','在 TCP 流中接收数据的端点中的可用缓冲空间','数据段大小',2,4,NULL,NULL),(14,NULL,'让一个 TCP 发送端发送数据段，每个数据段具有 1200 个字节的有效载荷，让接收方使用累加的 ACK 模型以确认收到的数据段。考虑下面的场景，发送端按照下面的序列编号发送数据段： 14200，15400，16600，17800。假设所有的数据段都按顺序收到。如果接收方在收到最后一个数据分组后发送一个 ACK 数据段，那么此数据段的确认号是多少？',0,'17799','19000','17801','17800',2,NULL,NULL,NULL),(15,NULL,'考虑在网络路径在使用慢启动，20 millisecs的往返时间，没有拥塞 （即，假设没有数据段丢失）。假设流是能够以很小的时延注入back-to-back数据段，当瓶颈链路传输数据段时。如果流通过在t=0发送一个数据段开始，那么在时间 t = 60 ms 到t = 80ms中间，可以发送多少数据段？',0,'2','3','8','4',3,NULL,NULL,NULL),(16,NULL,'下列哪些关于TCP 报头的陈述不准确？',1,'TCP 段头允许接收端标记到目前为止接收了什么数据和可达的缓冲空间','TCP 段头被用来确定数据段应被传递到的套接字','TCP 段头包含确定TCP 数据段的源和目的地的数据','TCP 段头不包含数据段总体长度有关的信息',1,2,NULL,NULL),(17,NULL,'当 TCP 接收端获得一个有效载荷大小 1000和序列号8000 的数据段时，有效载荷中的最后一个字节相关联的序列号是多少？',0,'7999','9000','8999','8000',3,NULL,NULL,NULL),(18,NULL,'设x 和 y 是接收端发送的两个连续的数据段携带的累积ACKs，x 在 y 之前发送。设与这两个数据段相关联的数据有效载荷的大小为 s。以下陈述中哪些是正确的？',0,'y < = x + s','y > = x + s','x < = y','x < y',3,NULL,NULL,NULL),(19,NULL,'假设一个 TCP 流在慢启动阶段，k 个数据段在 t 与 t + RTT 之间被发送。假设仍然保持在慢启动阶段，预期有多少个数据段在 t + RTT 和t + 2 * RTT 发送？',0,'2的k次方','k + 1','k','2k',4,NULL,NULL,NULL),(20,NULL,'考虑从一个接收端收到的 ACK 数据段序列： ACK 数据段 #1 ack 号 3000，ACK 数据段 #2  ack 号4000，ACK 数据段 #3 ack 号 5000，ACK 数据段 #4  ack 号 5000，ACK 数据段 #5  ack 号 5000，ACK 数据段 #6  ack 号 5000 和 ACK 数据段 #7 ack 号 5000。假设发送端使用快速重传。当它重传时，哪一个是由发送端重新传输的数据段？',0,'收到 ACK 数据段 #6 后序列号 5000 的数据段','收到 ACK 数据段 #6 后序列号6000 的数据段','收到 ACK 数据段 #5 后序列号 5000 的数据段','收到 ACK 数据段 #7 后序列号 6000 的数据段',1,NULL,NULL,NULL),(21,NULL,'主机甲向主机乙发出一个(SYN，seq=11220)的TCP段，期望与主机乙建立TCP连接，若主机乙接受该连接请求，则主机乙向主机甲发送的正确的TCP段可能是',0,'（SYN=0，ACK=0，seq=11221，ack=11221）','（SYN=1，ACK=1，seq=11220，ack=11220）','（SYN=1，ACK=1，seq=11221，ack=11221）','（SYN=0，ACK=0，seq=11220，ack=11220）',3,NULL,NULL,NULL),(22,NULL,'公共电话交换网络（PSTN）主要由下面哪几部分构成？',1,'交换局','本地回路','干线','以上都不是',1,2,3,NULL),(23,NULL,'下面哪种设备的使用可以导致冲突域增加？',1,'中继器','集线器','交换机','网桥',1,2,NULL,NULL),(24,NULL,'路由和转发的主要区别是什么？',1,'它们是同一个意思','路由是基于最短路径，而转发是基于生成树','路由是寻找路径的过程，转发是沿路径发送分组的过程','路由是涉及整个网络的过程，转发是本地操作',3,4,NULL,NULL),(25,NULL,'下列哪些语句正确描述了分层路由的好处？',1,'它减少了路由消息的大小','它减少了路由的计算量','它减少了路由表的大小','它可以找到到达目的机的最短路径',1,2,3,NULL),(26,NULL,'一个ISP想要为一个公司提供中转路由服务，那么它应该将什么路由告诉谁？',1,'它应该将互联网的其他地址前缀宣告给互联网的其他部分','它应该只将这个公司的前缀宣告给这个公司','它应该将这家公司的前缀宣告给互联网的其他部分','它应该将互联网的其他前缀告诉这家公司',3,4,NULL,NULL),(27,NULL,'考虑一个令牌产生速率为 R ，大小为 B 的令牌桶，使用它来整形一个流。下列陈述哪是正确的？',1,'如果网络中没有交叉通信，流的长期速度可以超过 R','发送大小 B 一次突发量后， 流必须保持空闲，直到令牌桶重新完全地装满。','在短时间内流的带宽速率可以超过 R','如果一个流在长时间内处于空闲状态，它可以使用累积的令牌发送一次超过 B 字节的突发量',3,4,NULL,NULL),(28,NULL,'动态路由路由选择协议的两个主要任务是什么？',1,'发现主机','更新和维护路由表','传播主机的缺省路由','网络发现',2,4,NULL,NULL),(29,NULL,'这个问题探讨了最短路径路由的使用。假设你想在一个混合网络中使用高带宽路径，这个网络是1Gbps和100Mbps的混合网络，平时使用1 Gbps高带宽链路; 100 Mbps的链接是只用于发生故障时。你会如何设置链接的成本来实现这一目标？',0,'这个问题不能用最短路径来解决。需要用到局域网交换机','将传播延迟了多少微秒设置为链路的开销','将1Gbps这条链路的开销设置为远低于100Mbps这条链路的开销','将所有链路的开销设置为1.',3,NULL,NULL,NULL),(30,NULL,'Dijkstra算法可以在给定一个源节点的情况下寻找到达所有目的节点的最短路径，从一个给定的源节点到达网络中的所有目标。这个算法是按照什么样的顺序找到这些最短路径的？',0,'首先找到与源节点距离最远的目的节点','首先找到可能产生最短路径的源和目的节点对的那些目的节点','首先找到与源节点距离最短的那些目的节点。','首先找到距离源节点跳数最少的结点',3,NULL,NULL,NULL),(31,NULL,'考虑网络中的一个节点N，网络中有一个消息通过泛洪的方式被发布；这个消息是由网络中某个其他的节点产生的。请问，在最坏的情况下，节点N可能会收到多少份这个消息的副本？选择一个最佳的答案。（建设一个节点不会两次发送统一个消息给它的邻居）',0,'节点N和产生消息的结点之间有多少条与他们最短路径开销相等的路径，就会收到多少份副本','产生消息的结点与其他节点有多少通路，就会收到多少份副本。','只有一份副本，这份副本是通过节点N与产生消息的结点之间最短路径发送给N的','节点N与其他节点有多少条通路，就会收到多少份副本',4,NULL,NULL,NULL),(32,NULL,'这个问题测试你对距离矢量路由的理解，假设路由器A和路由器B之间的链路开销为1，路由器A和路由器C之间的链路开销为3。A收到了从B发送过来的距离矢量，列出了与A,B,C,D之间的开销依次是（1,0,1,5）。这表示，例如，B和D之间的开销是5,。A同时收到了C发送过来的距离矢量，是（3,1,0,1）。下列哪一个距离矢量是A计算出来的会发送给其邻居的？',0,'（0,1,2,4）','（0,1,3,1）','（0,1,2,1）','（2,1,2,4）',1,NULL,NULL,NULL),(33,NULL,'下列哪个答案最能说明由节点A泛洪发送的链路状态数据包（LSA/LSP）所代表的信息',0,'所有从A到其相邻路由器的链路成本','所有与A相连的邻居路由的标识','所有与A直接相连的邻居路由的标识以及到他们之间的链路成本','所有网络中的路由器的标识以及估计的到达这些路由器的最小开销',3,NULL,NULL,NULL),(34,NULL,'假设在ISP A收到BGP路由通告，上面说：“到达网络P，路径ISP C, ISP B,ISP A, 下一跳N”。当路由器收到这个通告的的时候会怎么处理',0,'路径会被缩短，删除环','路径会被传播，A作为这条路径的起点','路径会被丢弃，因为它包含了环','ISP A的策略会决定是否用这条路径',3,NULL,NULL,NULL),(35,NULL,'数据报网络允许路由器丢弃数据包。考虑一个源主机连接到路由器 R1，R1连接到路由器 R2，R2连接目标主机的情况。如果任一路由器丢弃数据包，源主机将超时，并再次尝试发送。如果 R1 具有概率0.3 丢弃数据包， R2 具有概率0.1 丢弃数据包，数据包从源主机发出成功到达目标主机的概率是多少。',0,'0.63','0.6','0.4','0.81',1,NULL,NULL,NULL),(36,NULL,'下面是一些主机的IP地址，哪些属于私人地址',1,'192.0.2.3','172.15.1.9','172.17.9.6','10.10.10.10',3,4,NULL,NULL),(37,NULL,'一个公司有一个网络地址192.168.1.64，对应的子网掩码是255.255.255.192，这个公司想创建2个子网，1个可容纳10台主机，另外一个可容纳18台主机，下面哪两个网络地址可用于这两个子网？',1,'192.168.1.16/28','192.168.1.64/27','192.168.1.128/27','192.168.1.96/28',2,4,NULL,NULL),(38,NULL,'某网络的IP地址空间为192.168.5.0/24，采用长子网划分，子网掩码为255.255.255.248，则该网络的最大子网个数、每个子网内的最大可分配地址个数是多少',0,'32,8','32,6','8,32','8,30',2,NULL,NULL,NULL),(39,NULL,'在下面这些点分十进制表示的IP地址中，哪一个是子网172.25.0.64/26中的最后一个可用地址?',0,'172.25.0.94','172.25.0.95','172.25.0.126','172.25.0.127',3,NULL,NULL,NULL),(40,NULL,'对于一个冒分十六进制表示的IPv6地址：FE80:0:0:0:2AA:FF:FE9A:4CA3，下面哪个地址缩写是正确的？',0,'FE8::2AA:FF:FE9A:4CA3','FE80::2AA:FF:FE9A:4CA3','FE80::0:2AA:FF:FE9A:4CA3','FE80:::0:2AA:FF:FE9A:4CA3',2,NULL,NULL,NULL),(41,NULL,'十六进制数0x3F 对应的十进制数是多少？',0,'61','62','63','65',3,NULL,NULL,NULL),(54,1,'题干22',1,'11','22','33','44',2,2,NULL,NULL),(55,4,'题干33',0,'111','222','333','444',1,NULL,NULL,NULL),(56,NULL,'题干44',0,'xuan1','xuan2','xuan3','xuan4',2,NULL,NULL,NULL),(110,1,'题干实例',1,'选项A','选项B','选项C','选项D',1,2,3,NULL),(111,2,'示例2',0,'1','2','3','4',NULL,2,NULL,NULL),(112,NULL,'示例6',1,'1','2','3','4',NULL,2,NULL,4),(113,NULL,'示例7',2,'对','错',NULL,NULL,NULL,2,NULL,NULL);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_password` varchar(20) NOT NULL DEFAULT '123456',
  `student_idcard` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `student_phone` varchar(20) DEFAULT NULL,
  `student_name` varchar(12) DEFAULT NULL,
  `student_sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `student_email` varchar(50) DEFAULT NULL,
  `student_position` varchar(20) DEFAULT NULL COMMENT '职位',
  `student_country` varchar(20) DEFAULT NULL COMMENT '地址',
  `student_province` varchar(20) DEFAULT NULL,
  `student_city` varchar(20) DEFAULT NULL,
  `student_area` varchar(20) DEFAULT NULL COMMENT '所在区镇',
  `student_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `company` varchar(30) DEFAULT NULL COMMENT '所属机构',
  `enter_time` datetime DEFAULT NULL COMMENT '入会时间',
  `has_org` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `UNIQUE1` (`student_phone`),
  UNIQUE KEY `UNIQUE2` (`student_idcard`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`student_id`,`student_password`,`student_idcard`,`student_phone`,`student_name`,`student_sex`,`student_email`,`student_position`,`student_country`,`student_province`,`student_city`,`student_area`,`student_address`,`company`,`enter_time`,`has_org`) values (1,'123456','445281199308310056','15521054785','张三',NULL,'11@qq.com','经理','中国','广东省','广州市','番禺区','大学城华南理工大学','AA股份有限公司',NULL,1),(2,'123456','445281199308310037','15521064789','李四',NULL,'22@qq.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'123456','445281199707774569','13112114587','王建国',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(38,'123456','4342323432','15521065436','张英语',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'BB股份有限公司',NULL,1),(39,'123456','134455','15521065326','李数学',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'BB股份有限公司',NULL,1),(40,'123456','445281199302210226','15521065416','陈政治',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'BB股份有限公司',NULL,1);

/*Table structure for table `system_parameter` */

DROP TABLE IF EXISTS `system_parameter`;

CREATE TABLE `system_parameter` (
  `para_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '方案编号',
  `para_key` varchar(50) DEFAULT NULL COMMENT '参数名称',
  `para_value` varchar(200) DEFAULT NULL COMMENT '参数内容',
  `value_type` varchar(20) DEFAULT NULL COMMENT '参数数据类型:String,Integer,BigDecimal,Date...',
  `flag` tinyint(1) DEFAULT '1' COMMENT '1:启用/0:停用',
  `description` varchar(200) DEFAULT NULL COMMENT '参数描述',
  `modify_time` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`para_id`),
  UNIQUE KEY `key` (`para_key`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `system_parameter` */

insert  into `system_parameter`(`para_id`,`para_key`,`para_value`,`value_type`,`flag`,`description`,`modify_time`) values (1,'stu_tran_permission','1','Interge',1,'学员培训权限 0:关闭 1:开放',NULL),(2,'activity_order_length','60','Interge',1,'活动报名订单有效时长 单位:min',NULL),(3,NULL,NULL,NULL,1,NULL,NULL);

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
  `training_address` varchar(200) DEFAULT NULL COMMENT '培训地点',
  `contact_name` varchar(10) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `training` */

insert  into `training`(`training_id`,`training_name`,`training_introduce`,`training_fee_normal`,`training_fee_vip`,`training_end_time`,`training_start_time`,`post_time`,`level`,`training_pic`,`training_address`,`contact_name`,`contact_phone`) values (1,'质量检测','示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n示例范文示例范文示例范文示例范文','199.99','199.00','2019-11-02 16:13:03','2019-09-13 16:12:30','2019-10-31 00:41:14',1,NULL,NULL,NULL,NULL),(2,'食品安全','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','888.01','699.01','2019-11-01 12:00:00','2019-10-01 12:00:00','2019-07-15 00:41:19',2,NULL,NULL,NULL,NULL),(3,'食品监督','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','2000.00','1899.11','2020-11-12 16:14:32','2019-09-05 16:14:25','2021-01-08 00:41:22',1,NULL,NULL,NULL,NULL),(4,'网络工程','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','800.90','700.00','2019-11-22 16:14:40','2019-09-11 16:14:36','2019-11-01 00:41:27',3,NULL,NULL,NULL,NULL),(5,'产品规范','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','99.99','98.99','2019-11-08 16:14:56','2019-09-29 16:14:44','2019-10-17 00:41:35',1,NULL,NULL,NULL,NULL);

/*Table structure for table `training_order` */

DROP TABLE IF EXISTS `training_order`;

CREATE TABLE `training_order` (
  `order_key_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单主键',
  `training_order_id` varchar(20) NOT NULL COMMENT '订单号',
  `training_id` int(11) DEFAULT NULL,
  `order_type` tinyint(1) DEFAULT '0' COMMENT '0为会员下的订单，1为学员下的订单',
  `member_key_id` int(11) DEFAULT NULL COMMENT '会员订单则不为空',
  `student_id` int(11) DEFAULT NULL COMMENT '学员订单则不为空',
  `order_price` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_begin_time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `payment_state` tinyint(1) DEFAULT '0' COMMENT '0为未付款，1为已付款',
  `close` tinyint(1) DEFAULT '0' COMMENT '1为订单关闭',
  PRIMARY KEY (`order_key_id`),
  KEY `training_id` (`training_id`),
  KEY `student_id` (`student_id`),
  KEY `member_key_id` (`member_key_id`),
  KEY `key_id` (`order_key_id`),
  CONSTRAINT `training_order_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`),
  CONSTRAINT `training_order_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `training_order_ibfk_4` FOREIGN KEY (`member_key_id`) REFERENCES `member` (`member_key_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `training_order` */

insert  into `training_order`(`order_key_id`,`training_order_id`,`training_id`,`order_type`,`member_key_id`,`student_id`,`order_price`,`order_begin_time`,`payment_state`,`close`) values (1,'20191023111733112724',2,0,1,NULL,'2097.03','2019-10-23 11:17:34',1,0),(2,'20191023112509346401',4,0,1,NULL,'2100.00','2019-10-23 11:25:10',1,0),(3,'20200313152644744451',3,0,1,NULL,'3798.22','2020-03-13 15:26:44',1,0),(5,'20200313152950680848',3,0,1,NULL,'1899.11','2020-03-13 15:29:51',1,0);

/*Table structure for table `training_order_student` */

DROP TABLE IF EXISTS `training_order_student`;

CREATE TABLE `training_order_student` (
  `order_key_id` int(11) NOT NULL,
  `student_id` int(20) NOT NULL,
  `is_paid` tinyint(1) DEFAULT '0' COMMENT '1:已支付',
  KEY `student_id` (`student_id`),
  KEY `order_key_id` (`order_key_id`,`student_id`),
  CONSTRAINT `training_order_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `training_order_student` */

insert  into `training_order_student`(`order_key_id`,`student_id`,`is_paid`) values (1,1,0),(1,8,0),(1,38,0),(2,1,0),(2,8,0),(2,38,0),(3,1,0),(3,2,0),(5,8,0);

/*Table structure for table `training_re_student` */

DROP TABLE IF EXISTS `training_re_student`;

CREATE TABLE `training_re_student` (
  `re_id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_id` varchar(20) NOT NULL COMMENT '报名编号',
  `training_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL COMMENT '报名时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成/结束时间',
  `is_done` tinyint(1) DEFAULT '0' COMMENT '1:完成培训 0:未完成',
  `is_invalid` tinyint(1) DEFAULT '0' COMMENT '1为无效(考试不通过)',
  PRIMARY KEY (`re_id`),
  KEY `student_id` (`student_id`),
  KEY `training_id` (`training_id`,`student_id`),
  CONSTRAINT `training_re_student_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`),
  CONSTRAINT `training_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `training_re_student` */

insert  into `training_re_student`(`re_id`,`apply_id`,`training_id`,`student_id`,`begin_time`,`finish_time`,`is_done`,`is_invalid`) values (1,'123123',1,1,'2019-10-22 20:47:51',NULL,0,0),(2,'t2_26cd94',2,38,'2019-10-23 11:17:34',NULL,0,0),(3,'t2_9c96de',2,1,'2019-10-23 11:17:34',NULL,0,0),(4,'t2_e2c534',2,8,'2019-10-23 11:17:34',NULL,0,0),(5,'t3_55ac64',3,2,'2020-03-13 15:26:45',NULL,0,0),(6,'t3_866926',3,1,'2020-03-13 15:26:45',NULL,0,0),(7,'t3_97817b',3,8,'2020-03-13 15:29:51',NULL,0,0),(8,'t4_9ffa06',4,38,'2019-10-23 11:25:11',NULL,0,0),(9,'t4_cfd30e',4,8,'2019-10-23 11:25:11',NULL,0,0),(10,'t4_fb4b75',4,1,'2019-10-23 11:25:11',NULL,0,0);

/*Table structure for table `vote` */

DROP TABLE IF EXISTS `vote`;

CREATE TABLE `vote` (
  `vote_id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_content` varchar(255) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL COMMENT '投票截止时间',
  `vote_type` tinyint(4) DEFAULT '2' COMMENT '0为会员投票，1为学院投票，2为皆可',
  `optional_sum` tinyint(4) DEFAULT NULL COMMENT '选项总数量',
  `optional_num` tinyint(4) DEFAULT NULL COMMENT '可多选数目',
  `post_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `vote` */

insert  into `vote`(`vote_id`,`vote_content`,`end_time`,`vote_type`,`optional_sum`,`optional_num`,`post_time`) values (1,'投票测试1','2020-11-01 15:00:00',2,3,3,'2020-03-22 02:45:55'),(3,'投票测试2','2020-11-01 15:00:00',1,4,3,'2020-03-23 00:30:04'),(4,'投票测试3','2020-03-20 12:49:41',2,3,3,'2020-03-29 12:50:02');

/*Table structure for table `vote_option` */

DROP TABLE IF EXISTS `vote_option`;

CREATE TABLE `vote_option` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `vote_id` int(11) DEFAULT NULL,
  `option_text` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`option_id`),
  KEY `vote_id` (`vote_id`),
  CONSTRAINT `vote_option_ibfk_1` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `vote_option` */

insert  into `vote_option`(`option_id`,`vote_id`,`option_text`) values (1,1,'选项1'),(3,1,'选项3'),(4,1,'选项4'),(8,3,'选项1'),(9,3,'选项2'),(10,3,'选项3'),(11,3,'选项4'),(12,4,'选项1'),(13,4,'选项2'),(14,4,'选项3');

/*Table structure for table `vote_option_member` */

DROP TABLE IF EXISTS `vote_option_member`;

CREATE TABLE `vote_option_member` (
  `vote_id` int(11) DEFAULT NULL,
  `option_id` int(11) DEFAULT NULL,
  `member_key_id` int(11) DEFAULT NULL,
  KEY `vote_id` (`vote_id`),
  KEY `option_id` (`option_id`),
  KEY `member_id` (`member_key_id`),
  CONSTRAINT `vote_option_member_ibfk_1` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`),
  CONSTRAINT `vote_option_member_ibfk_2` FOREIGN KEY (`option_id`) REFERENCES `vote_option` (`option_id`),
  CONSTRAINT `vote_option_member_ibfk_3` FOREIGN KEY (`member_key_id`) REFERENCES `member` (`member_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vote_option_member` */

insert  into `vote_option_member`(`vote_id`,`option_id`,`member_key_id`) values (1,1,1),(1,3,1),(3,8,1),(3,9,1),(4,12,1);

/*Table structure for table `vote_option_student` */

DROP TABLE IF EXISTS `vote_option_student`;

CREATE TABLE `vote_option_student` (
  `vote_id` int(11) DEFAULT NULL,
  `option_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  KEY `vote_id` (`vote_id`),
  KEY `option_id` (`option_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `vote_option_student_ibfk_1` FOREIGN KEY (`vote_id`) REFERENCES `vote` (`vote_id`),
  CONSTRAINT `vote_option_student_ibfk_2` FOREIGN KEY (`option_id`) REFERENCES `vote_option` (`option_id`),
  CONSTRAINT `vote_option_student_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vote_option_student` */

insert  into `vote_option_student`(`vote_id`,`option_id`,`student_id`) values (1,1,2),(1,3,2),(1,1,2),(1,3,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
