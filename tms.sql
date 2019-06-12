# Host: localhost  (Version 5.5.5-10.2.20-MariaDB)
# Date: 2019-06-12 11:39:58
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "tb_load"
#

DROP TABLE IF EXISTS `tb_load`;
CREATE TABLE `tb_load` (
  `loadID` int(11) NOT NULL AUTO_INCREMENT,
  `carrierID` int(11) NOT NULL,
  `systemLoadID` int(11) NOT NULL,
  `alertTypeCode` varchar(20) NOT NULL,
  `loadDescription` varchar(255) DEFAULT NULL,
  `loadStartDateTime` datetime DEFAULT NULL,
  `loadEndDateTime` datetime DEFAULT NULL,
  `completedFlag` varchar(15) NOT NULL,
  `errorMessage` text DEFAULT NULL,
  `lastUpdateDate` datetime DEFAULT NULL,
  `lastUpdateUser` varchar(20) DEFAULT NULL,
  `gatein` varchar(50) DEFAULT NULL,
  `gateout` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`loadID`)
) ENGINE=InnoDB AUTO_INCREMENT=20814 DEFAULT CHARSET=utf8mb4;

#
# Data for table "tb_load"
#

INSERT INTO `tb_load` VALUES (2838,10,157044,'LOAD_TENDER_NEW','AGA071-FTL-F-1WAY','2018-11-02 08:00:00','2018-11-02 11:45:00','In transit',NULL,'2019-05-30 14:40:22','bambamka','test',NULL),(3627,9,157044,'LOAD_TENDER_NEW',NULL,NULL,NULL,'N/A',NULL,'2018-11-16 14:00:56','bambamka',NULL,NULL),(8163,12,157044,'LOAD_TENDER_NEW',NULL,NULL,NULL,'N/A',NULL,'2018-11-18 16:38:56','bambamka',NULL,NULL);
