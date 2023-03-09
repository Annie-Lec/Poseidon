CREATE SCHEMA IF NOT EXISTS `demo2` ;
use demo2;

DROP TABLE IF EXISTS `bid_list`;
DROP TABLE IF EXISTS `app_user`;
DROP TABLE IF EXISTS `curve_point`;
DROP TABLE IF EXISTS `trade`;
DROP TABLE IF EXISTS `rule_name`;
DROP TABLE IF EXISTS `rating`;

 CREATE TABLE `bid_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(32) DEFAULT NULL,
  `ask` decimal(8,1) DEFAULT NULL,
  `ask_quantity` decimal(8,1) DEFAULT NULL,
  `benchmark` varchar(125) DEFAULT NULL,
  `bid` decimal(8,1) DEFAULT NULL,
  `bid_list_date` datetime DEFAULT NULL,
  `bid_quantity` decimal(8,1) DEFAULT NULL,
  `book` varchar(32) DEFAULT NULL,
  `commentary` varchar(250) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `creation_name` varchar(32) DEFAULT NULL,
  `deal_name` varchar(32) DEFAULT NULL,
  `deal_type` varchar(32) DEFAULT NULL,
  `revision_date` datetime DEFAULT NULL,
  `revision_name` varchar(32) DEFAULT NULL,
  `security` varchar(32) DEFAULT NULL,
  `side` varchar(32) DEFAULT NULL,
  `source_list_id` varchar(32) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `trader` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `trade` (
  `id` tinyint NOT NULL AUTO_INCREMENT,
  `account` varchar(32) NOT NULL,
  `type` varchar(32) NOT NULL,
  `buy_quantity` decimal(8,1) NOT NULL,
  `sell_quantity` decimal(8,1) DEFAULT NULL,
  `buy_price` decimal(8,1) DEFAULT NULL,
  `sell_price` decimal(8,1) DEFAULT NULL,
  `trade_date` timestamp NULL DEFAULT NULL,
  `security` varchar(32) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `trader` varchar(32) DEFAULT NULL,
  `benchmark` varchar(32) DEFAULT NULL,
  `book` varchar(32) DEFAULT NULL,
  `creation_name` varchar(32) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT NULL,
  `revision_name` varchar(32) DEFAULT NULL,
  `revision_date` timestamp NULL DEFAULT NULL,
  `deal_name` varchar(32) DEFAULT NULL,
  `deal_type` varchar(32) DEFAULT NULL,
  `source_list_id` varchar(32) DEFAULT NULL,
  `side` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `curve_point` (
  `id` int NOT NULL AUTO_INCREMENT,
  `as_of_date` datetime DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `curve_id` int NOT NULL,
  `term` decimal(8,1) DEFAULT NULL,
  `value` decimal(8,1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `rating` (
  `Id` tinyint NOT NULL AUTO_INCREMENT,
  `fitch_rating` varchar(32) DEFAULT NULL,
  `moodys_rating` varchar(32) DEFAULT NULL,
  `order_number` int NOT NULL,
  `sandprating` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

CREATE TABLE `rule_name` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(32) DEFAULT NULL,
  `json` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sql_part` varchar(32) DEFAULT NULL,
  `sql_str` varchar(32) DEFAULT NULL,
  `template` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `app_user` (
  `Id` tinyint NOT NULL AUTO_INCREMENT,
  `username` varchar(125) DEFAULT NULL,
  `password` varchar(125) DEFAULT NULL,
  `fullname` varchar(125) DEFAULT NULL,
  `role` varchar(125) DEFAULT NULL,
  PRIMARY KEY (`Id`)
);

INSERT INTO `app_user`(fullname, username, `password`, `role`) VALUES ('Administrator', 'admin', "$2a$10$TBQB.eqftzLWSjOm74U9Ceh3IffPKKS22Kstpfhz7smHSfNrQFtPO", "ROLE_ADMIN");
INSERT INTO `app_user`(fullname, username, `password`, `role`) VALUES ('User', 'user', "$2a$10$mirkejV74ZXYX1ol8A8W8.Wp3zKqa4SIt4MoDEKqDTv41NWtaRd4G", "ROLE_USER");

INSERT INTO `bid_list`(`account`, `type`, bid_quantity) VALUES ("account1", "type1", '11.1' );
INSERT INTO `curve_point` (`curve_id`, `term`, `value`) VALUES ('3', '3.3', '33.3');
INSERT INTO `rating` (`fitch_rating`, `moodys_rating`, `order_number`, `sandprating`) VALUES ('fitch1', 'mood1', '1', 'sandp1');
INSERT INTO `rule_name` (`description`, `json`, `name`, `sql_part`, `sql_str`, `template`) VALUES ('description1', 'json1', 'nom1', 'le sql part', 'le sql STR', 'un template');
INSERT INTO `trade` (`account`, `type`, `buy_quantity`) VALUES ('account1', 'type1', '11.1');


