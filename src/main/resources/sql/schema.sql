-- 这里是定义数据结构的SQL，每次运行都会执行，此文件不能为空，必须至少写一句Sql语句。
show tables;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
CREATE TABLE If Not Exists `activity` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT,
                                        `name` varchar(100) DEFAULT NULL,
                                        `descr` varchar(255) DEFAULT NULL,
                                        `company_name` varchar(50) DEFAULT NULL,
                                        `created_at` datetime DEFAULT NULL,
                                        `updated_at` datetime DEFAULT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ad_machine
-- ----------------------------
CREATE TABLE If Not Exists `ad_machine` (
                                          `imei` varchar(50) NOT NULL,
                                          `name` varchar(50) DEFAULT NULL,
                                          `activity_id` int(11) NOT NULL,
                                          `ad_machine_type` varchar(20) DEFAULT NULL,
                                          `created_at` datetime DEFAULT NULL,
                                          `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ad_position
-- ----------------------------
CREATE TABLE If Not Exists `ad_position` (
                                           `id` int(11) NOT NULL AUTO_INCREMENT,
                                           `name` varchar(100) DEFAULT NULL,
                                           `descr` varchar(255) DEFAULT NULL,
                                           `created_at` datetime DEFAULT NULL,
                                           `updated_at` datetime DEFAULT NULL,
                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for material
-- ----------------------------
CREATE TABLE If Not Exists `material` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT,
                                        `name` varchar(50) DEFAULT NULL,
                                        `descr` varchar(255) DEFAULT NULL,
                                        `type` varchar(20) DEFAULT NULL,
                                        `url` varchar(255) DEFAULT NULL,
                                        `activity_id` int(11) DEFAULT NULL,
                                        `ad_position_id` int(11) DEFAULT NULL,
                                        `created_at` datetime DEFAULT NULL,
                                        `updated_at` datetime DEFAULT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;