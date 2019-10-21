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
  `open_time` datetime DEFAULT NULL COMMENT '开放报名时间',
  `close_time` datetime DEFAULT NULL COMMENT '关闭报名时间',
  `post_time` datetime DEFAULT NULL COMMENT '活动发布时间',
  `max_num` int(11) DEFAULT NULL COMMENT '活动可容纳最大人数',
  `area_width` int(11) DEFAULT NULL COMMENT '场地矩形行座位数',
  `area_length` int(11) DEFAULT NULL COMMENT '场地矩形列座位数',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `activity` */

insert  into `activity`(`activity_id`,`activity_fee`,`activity_name`,`activity_introduction`,`activity_date`,`activity_length_min`,`contacts`,`activity_address`,`open_time`,`close_time`,`post_time`,`max_num`,`area_width`,`area_length`) values (1,'188.88','垃圾分类讲解','  今年夏天，垃圾分类无疑是最火的话题。作为全国46个垃圾分类试点城市之一，长沙市围绕垃圾分类开展了形式多样、内容丰富的宣传活动，趣味游戏、知识宣讲、在线答题、儿童手抄报……一场全民参与、声势浩大的“全民生活垃圾分类之旅”让环保理念深植人心。','2019-10-01 20:00:00',60,'王先生 13332122312','广东省广州市华南理工大学大学城校区','2019-09-25 20:00:00','2019-10-09 02:32:42','2019-10-05 02:41:10',50,NULL,NULL),(2,'299.99','英语角','  我们的英语俱乐部旨在为大家创造一个英语的交流环境，让所有想学英语，想说好英语的人们，在这样一个极具特色的氛围中学好英语，在外语老师的引导下，大家会积极交流，大胆开口说，培养纯正的外语语感，让你更加自信说英语，并结交更多的外国友人，避免找个好工作， 而因为英语困惑自己~~~~','2019-09-30 15:00:00',120,'李先生 13332122312','广东省广州市华南理工大学大学城校区','2019-09-25 13:00:00','2019-10-19 00:32:46','2019-10-04 02:41:14',59,NULL,NULL),(3,'199.99','“拥抱春天，播种绿色”植树节活动','  植树节是按照法律规定宣传保护树木，并组织动员群众积极参加以植树造林为活动内容的节日。按时间长短可分为植树日、植树周和植树月，共称为国际植树节。提倡通过这种活动，激发人们爱林造林的热情、意识到环保的重要性。',NULL,NULL,'李先生 13332122312','广东省广州市华南理工大学大学城校区','2019-09-24 02:28:46','2019-09-30 02:32:52','2019-10-18 02:41:16',46,NULL,NULL),(4,'213.22','主题餐厅','  主题餐厅旨在为大家提供一起品尝美食的机会','2019-11-09 14:28:55',NULL,NULL,NULL,'2019-10-09 14:29:09','2019-11-16 14:29:12','2019-09-05 02:41:20',40,NULL,NULL);

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
  `payment_state` tinyint(1) DEFAULT '0' COMMENT '0为未付款，1为已付款',
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

insert  into `activity_order`(`activity_order_id`,`activity_id`,`order_type`,`member_id`,`student_id`,`order_price`,`order_begin_time`,`payment_state`,`close`) values ('20191001145853382363',1,1,NULL,1,'188.88','2019-10-01 14:58:54',1,0),('20191009144301721429',4,1,NULL,1,'213.22','2019-10-09 14:43:01',0,0),('20191009163126575245',4,0,'mb_ca33eae307ae4',NULL,'426.44','2019-10-09 16:31:27',1,0),('20191009163703148065',4,0,'mb_ca33eae307ae4',NULL,'213.22','2019-10-09 16:37:04',0,0),('20191010164121915078',4,0,'mb_ca33eae307ae4',NULL,'426.44','2019-10-10 16:41:22',0,0);

/*Table structure for table `activity_order_student` */

DROP TABLE IF EXISTS `activity_order_student`;

CREATE TABLE `activity_order_student` (
  `activity_order_id` varchar(20) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `is_paid` tinyint(1) DEFAULT '0' COMMENT '0:未支付',
  KEY `activity_order_id` (`activity_order_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `activity_order_student_ibfk_1` FOREIGN KEY (`activity_order_id`) REFERENCES `activity_order` (`activity_order_id`),
  CONSTRAINT `activity_order_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `activity_order_student` */

insert  into `activity_order_student`(`activity_order_id`,`student_id`,`is_paid`) values ('20191009163126575245',1,1),('20191009163126575245',8,1),('20191009163703148065',2,0),('20191010164121915078',38,0),('20191010164121915078',39,0);

/*Table structure for table `activity_seat` */

DROP TABLE IF EXISTS `activity_seat`;

CREATE TABLE `activity_seat` (
  `activity_id` int(11) DEFAULT NULL COMMENT '活动id',
  `x_index` int(11) DEFAULT NULL COMMENT '横坐标',
  `y_index` int(11) DEFAULT NULL COMMENT '纵坐标',
  `is_lock` tinyint(1) DEFAULT '0' COMMENT '1:锁定座位',
  `is_occupied` tinyint(1) DEFAULT '1' COMMENT '0:空座 1:已有人',
  `seat_number` varchar(20) DEFAULT NULL COMMENT '座位号(Y行X座)',
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
  `admin_id` varchar(20) NOT NULL,
  `admin_password` varchar(20) DEFAULT NULL,
  `permission_value` tinyint(1) DEFAULT '0' COMMENT '0位普通管理员，1为审核管理员',
  `is_locked` tinyint(1) DEFAULT '0' COMMENT '状态：0正常，1锁定',
  `admin_name` varchar(10) DEFAULT NULL COMMENT '管理员名',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

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

/*Table structure for table `association` */

DROP TABLE IF EXISTS `association`;

CREATE TABLE `association` (
  `association_name` varchar(30) DEFAULT NULL COMMENT '协会名称',
  `association_introduce` varchar(1000) DEFAULT NULL COMMENT '协会介绍'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `association` */

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

/*Table structure for table `certificate_change_queue` */

DROP TABLE IF EXISTS `certificate_change_queue`;

CREATE TABLE `certificate_change_queue` (
  `re_id` int(11) DEFAULT NULL,
  `change_description` varchar(50) DEFAULT NULL COMMENT '变更描述',
  `op_user_type` tinyint(1) DEFAULT NULL COMMENT '0:学员申请 1:会员申请',
  `student_id` int(11) DEFAULT NULL,
  `member_id` varchar(20) DEFAULT NULL COMMENT '若为学员申请则置null',
  KEY `re_id` (`re_id`),
  KEY `student_id` (`student_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `certificate_change_queue_ibfk_1` FOREIGN KEY (`re_id`) REFERENCES `certificate_re_student` (`re_id`),
  CONSTRAINT `certificate_change_queue_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `certificate_change_queue_ibfk_3` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
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

/*Table structure for table `certificate_recheck_queue` */

DROP TABLE IF EXISTS `certificate_recheck_queue`;

CREATE TABLE `certificate_recheck_queue` (
  `re_id` int(11) DEFAULT NULL COMMENT '证书——学生 唯一id',
  KEY `re_id` (`re_id`),
  CONSTRAINT `certificate_recheck_queue_ibfk_1` FOREIGN KEY (`re_id`) REFERENCES `certificate_re_student` (`re_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `certificate_recheck_queue` */

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
  `exam_start_time` datetime DEFAULT NULL,
  `exam_end_time` datetime DEFAULT NULL,
  `is_posted` tinyint(1) DEFAULT '0' COMMENT '0:未发布 1:已发布',
  `have_questions` tinyint(1) DEFAULT '0' COMMENT '0:无题目 1:已加题目',
  `create_time` datetime DEFAULT NULL COMMENT '考试创建(添加)时间',
  PRIMARY KEY (`exam_id`),
  KEY `training_id` (`training_id`),
  CONSTRAINT `exam_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `exam` */

insert  into `exam`(`exam_id`,`exam_name`,`training_id`,`exam_num`,`exam_pass`,`exam_length_min`,`exam_start_time`,`exam_end_time`,`is_posted`,`have_questions`,`create_time`) values (1,'质量测试',1,20,55,60,'2019-10-17 12:00:00','2019-10-17 14:00:00',1,1,NULL),(2,'食品测试',2,20,55,80,'2019-10-17 13:00:00','2019-10-17 15:00:00',0,0,NULL),(3,'监督测试',3,20,65,70,'2019-10-17 14:00:00','2019-10-17 16:00:00',0,0,NULL),(4,'网络建设',4,30,60,60,'2019-10-17 18:00:00','2019-10-17 20:00:00',0,0,NULL),(6,'产品教育',5,20,50,120,'2019-10-01 00:00:00','2019-12-01 00:00:00',0,0,NULL);

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

/*Table structure for table `exam_re_student` */

DROP TABLE IF EXISTS `exam_re_student`;

CREATE TABLE `exam_re_student` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩单编号',
  `exam_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `score` int(6) DEFAULT NULL COMMENT '考试分数',
  `remaining_times` tinyint(4) DEFAULT '3' COMMENT '可重考剩余次数',
  `is_invalid` tinyint(1) DEFAULT '0' COMMENT '1为无效(考试不通过)',
  `is_verify` tinyint(1) DEFAULT '0' COMMENT '0:未审核 1:已审核',
  `in_line` tinyblob COMMENT '0:不在审核队列里 1:在审核队列里',
  `not_pass_reason` varchar(100) DEFAULT NULL COMMENT '审核不通过理由',
  `not_pass_times` int(11) DEFAULT NULL COMMENT '审核不通过次数，被驳回一次+1',
  PRIMARY KEY (`report_id`),
  KEY `exam_id` (`exam_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `exam_re_student_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`),
  CONSTRAINT `exam_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `exam_re_student` */

insert  into `exam_re_student`(`report_id`,`exam_id`,`student_id`,`score`,`remaining_times`,`is_invalid`,`is_verify`,`in_line`,`not_pass_reason`,`not_pass_times`) values (1,1,1,55,2,0,0,NULL,NULL,NULL),(2,1,2,34,1,0,0,NULL,NULL,NULL),(3,2,1,41,0,0,0,NULL,NULL,NULL),(4,4,1,56,1,0,0,NULL,NULL,NULL);

/*Table structure for table `exam_report_op_log` */

DROP TABLE IF EXISTS `exam_report_op_log`;

CREATE TABLE `exam_report_op_log` (
  `log_id` int(11) DEFAULT NULL COMMENT '日志编号',
  `report_id` int(11) DEFAULT NULL COMMENT '成绩单编号',
  `admin_id` varchar(20) DEFAULT NULL COMMENT '操作管理员id',
  `op_description` varchar(50) DEFAULT NULL COMMENT '操作描述',
  `op_time` datetime DEFAULT NULL COMMENT '操作时间',
  `is_success` tinyint(1) DEFAULT '1' COMMENT '1:操作成功 0操作失败'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exam_report_op_log` */

/*Table structure for table `exam_report_verify_queue` */

DROP TABLE IF EXISTS `exam_report_verify_queue`;

CREATE TABLE `exam_report_verify_queue` (
  `report_id` int(11) DEFAULT NULL,
  `admin_id` varchar(20) DEFAULT NULL,
  KEY `report_id` (`report_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `exam_report_verify_queue_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `exam_re_student` (`report_id`),
  CONSTRAINT `exam_report_verify_queue_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exam_report_verify_queue` */

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `member_id` varchar(20) NOT NULL,
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
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `Phone` (`member_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member` */

insert  into `member`(`member_id`,`member_phone`,`member_email`,`member_password`,`member_tel`,`member_name`,`is_vip`,`enter_date`,`vip_end_date`,`vip_begin_date`,`member_country`,`member_province`,`member_city`,`member_area`,`member_address`) values ('mb_ca33eae307ae4','15521065463','12345@163.com','123456','222102312','BB股份有限公司',1,'2019-09-19','2019-12-22','2019-09-20','中国','江苏省','南京市','鼓楼区','南京大学');

/*Table structure for table `member_re_student` */

DROP TABLE IF EXISTS `member_re_student`;

CREATE TABLE `member_re_student` (
  `member_id` varchar(20) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  UNIQUE KEY `member_student` (`member_id`,`student_id`),
  KEY `student_id` (`student_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `member_re_student_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `member_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member_re_student` */

insert  into `member_re_student`(`member_id`,`student_id`) values ('mb_ca33eae307ae4',1),('mb_ca33eae307ae4',8),('mb_ca33eae307ae4',38),('mb_ca33eae307ae4',39),('mb_ca33eae307ae4',40);

/*Table structure for table `member_subscription_order` */

DROP TABLE IF EXISTS `member_subscription_order`;

CREATE TABLE `member_subscription_order` (
  `subscription_order_id` varchar(20) NOT NULL COMMENT '协会会员会费订单',
  `member_id` varchar(20) DEFAULT NULL COMMENT '会员Id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `payment_state` tinyint(1) DEFAULT '0' COMMENT '0:未付款 1:已付款',
  `close` tinyint(1) DEFAULT '0' COMMENT '0:订单有效/待支付 1:订单关闭/完成',
  PRIMARY KEY (`subscription_order_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `member_subscription_order_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `member_subscription_order` */

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT,
  `as_admin_id` varchar(20) DEFAULT NULL COMMENT '发布的管理员',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '简介',
  `content` text COMMENT '内容',
  `post_time` datetime DEFAULT NULL COMMENT '发布时间',
  `type` varchar(10) DEFAULT NULL COMMENT '新闻类别',
  PRIMARY KEY (`news_id`),
  KEY `as_admin_id` (`as_admin_id`),
  CONSTRAINT `news_ibfk_1` FOREIGN KEY (`as_admin_id`) REFERENCES `admin` (`admin_id`)
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

insert  into `question`(`question_id`,`training_id`,`question_state`,`type`,`choice_a`,`choice_b`,`choice_c`,`choice_d`,`right_choice_one`,`right_choice_two`,`right_choice_three`,`right_choice_four`) values (1,1,'以下属于物理层的设备是',0,'中继器','以太网交换机','桥','网关',1,NULL,NULL,NULL),(2,1,' 在以太网中，是根据___地址来区分不同的设备的.',0,'LLC地址','MAC地址','IP地址','IPX地址',2,NULL,NULL,NULL),(3,1,'下面哪种LAN 是应用CSMA/CD协议的',1,'令牌环','FDDI','ETHERNET','NOVELL',3,4,NULL,NULL),(4,1,'在路由器上可以出现的端口是',1,'Console端口','AUX端口','PCI端口','RJ45端口',1,2,4,NULL);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_password` varchar(20) NOT NULL DEFAULT '123456',
  `student_idcard` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `student_phone` varchar(20) DEFAULT NULL,
  `student_name` varchar(12) DEFAULT NULL,
  `student_email` varchar(50) DEFAULT NULL,
  `student_position` varchar(20) DEFAULT NULL COMMENT '职位',
  `student_country` varchar(20) DEFAULT NULL COMMENT '地址',
  `student_province` varchar(20) DEFAULT NULL,
  `student_city` varchar(20) DEFAULT NULL,
  `student_area` varchar(20) DEFAULT NULL COMMENT '所在区镇',
  `student_address` varchar(200) DEFAULT NULL,
  `company` varchar(30) DEFAULT NULL COMMENT '所属机构',
  `enter_time` datetime DEFAULT NULL COMMENT '入会时间',
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `UNIQUE1` (`student_phone`),
  UNIQUE KEY `UNIQUE2` (`student_idcard`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`student_id`,`student_password`,`student_idcard`,`student_phone`,`student_name`,`student_email`,`student_position`,`student_country`,`student_province`,`student_city`,`student_area`,`student_address`,`company`,`enter_time`) values (1,'123456','445281199308310056','15521054785','张三','11@qq.com','经理','中国','广东省','广州市','番禺区','大学城华南理工大学',NULL,NULL),(2,'123456','445281199308310037','15521064789','李四','22@qq.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'123456','445281199707774569','13112114587','王建国',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL),(38,'123456','4342323432','15521065436','张英语',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'BB股份有限公司',NULL),(39,'123456','134455','15521065326','李数学',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'BB股份有限公司',NULL),(40,'123456','445281199302210226','15521065416','陈政治',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'BB股份有限公司',NULL);

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
  `contacts` varchar(50) DEFAULT NULL COMMENT '联系人 联系方式',
  PRIMARY KEY (`training_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `training` */

insert  into `training`(`training_id`,`training_name`,`training_introduce`,`training_fee_normal`,`training_fee_vip`,`training_end_time`,`training_start_time`,`post_time`,`level`,`training_pic`,`training_address`,`contacts`) values (1,'质量检测','示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n示例范文示例范文示例范文示例范文','199.99','199.00','2019-09-28 16:13:03','2019-09-13 16:12:30','2019-09-21 00:41:14',1,NULL,NULL,NULL),(2,'食品安全','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','888.01','699.01','2019-10-27 12:00:00','2019-10-01 12:00:00','2019-07-15 00:41:19',2,NULL,NULL,NULL),(3,'食品监督','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','2000.00','1899.11','2019-09-11 16:14:32','2019-09-05 16:14:25','2020-02-06 00:41:22',1,NULL,NULL,NULL),(4,'网络工程','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','800.90','700.00','2019-10-26 16:14:40','2019-09-11 16:14:36','2019-11-01 00:41:27',3,NULL,NULL,NULL),(5,'产品规范','    示例范文示例范文示例范文示例范文示例范文示例范文示例范文示例范文\r\n    示例范文示例范文示例范文示例范文','99.99','98.99','2019-11-08 16:14:56','2019-09-29 16:14:44','2019-10-17 00:41:35',1,NULL,NULL,NULL);

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

insert  into `training_order`(`training_order_id`,`training_id`,`order_type`,`member_id`,`student_id`,`order_price`,`order_begin_time`,`payment_state`,`close`) values ('20191011164356741646',2,0,'mb_ca33eae307ae4',NULL,'1398.02','2019-10-11 16:43:57',1,0),('20191011164411967235',2,0,'mb_ca33eae307ae4',NULL,'699.01','2019-10-11 16:44:12',0,0),('20191011164630774051',2,0,'mb_ca33eae307ae4',NULL,'699.01','2019-10-11 16:46:31',0,0),('20191016222310229181',1,1,NULL,1,'199.99','2019-10-16 22:23:10',0,0);

/*Table structure for table `training_order_student` */

DROP TABLE IF EXISTS `training_order_student`;

CREATE TABLE `training_order_student` (
  `training_order_id` varchar(20) DEFAULT NULL,
  `student_id` int(20) DEFAULT NULL,
  `is_paid` tinyint(1) DEFAULT '0' COMMENT '1:已支付',
  KEY `training_order_id` (`training_order_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `training_order_student_ibfk_1` FOREIGN KEY (`training_order_id`) REFERENCES `training_order` (`training_order_id`),
  CONSTRAINT `training_order_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `training_order_student` */

insert  into `training_order_student`(`training_order_id`,`student_id`,`is_paid`) values ('20191011164356741646',1,1),('20191011164356741646',8,1),('20191011164411967235',38,0),('20191011164630774051',39,0);

/*Table structure for table `training_re_student` */

DROP TABLE IF EXISTS `training_re_student`;

CREATE TABLE `training_re_student` (
  `apply_id` varchar(20) NOT NULL COMMENT '报名编号',
  `training_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL COMMENT '报名时间',
  `is_done` tinyint(1) DEFAULT '0' COMMENT '1:完成培训 0:未完成',
  `is_invalid` tinyint(1) DEFAULT '0' COMMENT '1为无效(考试不通过)',
  PRIMARY KEY (`apply_id`),
  KEY `training_id` (`training_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `training_re_student_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`),
  CONSTRAINT `training_re_student_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `training_re_student` */

insert  into `training_re_student`(`apply_id`,`training_id`,`student_id`,`begin_time`,`is_done`,`is_invalid`) values ('23123123',2,1,'2019-10-11 16:45:27',0,0),('324wsds',2,8,'2019-10-11 16:45:42',0,0);

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
  `message_content` varchar(1000) DEFAULT NULL COMMENT '信息内容',
  `send_time` datetime DEFAULT NULL COMMENT '站内信发送时间',
  PRIMARY KEY (`mail_id`),
  KEY `member_id` (`member_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `web_mail_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `web_mail_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `web_mail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
