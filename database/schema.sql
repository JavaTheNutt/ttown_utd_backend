CREATE SCHEMA IF NOT EXISTS thomastown_utd_test;
USE thomastown_utd_test;

CREATE TABLE IF NOT EXISTS Doctor(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(30),
	`surname` VARCHAR(30),
	`street_address` VARCHAR(30),
	`town_address` VARCHAR(30) DEFAULT 'Thomastown' NOT NULL,
	`contact_number` VARCHAR(15) NOT NULL,
	CONSTRAINT `doc_pk` PRIMARY KEY `Doctor`(`id`),
	CONSTRAINT `doc_unique_name` UNIQUE (`first_name`, `surname`),
	CONSTRAINT `doc_unique_num` UNIQUE (`contact_number`)
);
ALTER TABLE `Doctor` ADD INDEX `surname` (`surname`);
INSERT INTO Doctor (first_name, surname, street_address, contact_number) VALUES (
		'Achim',
	'Schlunke',
	'Low Street',
	'05188945'
);
/*************************************************************************************************/
CREATE TABLE IF NOT EXISTS `Family`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`family_name` VARCHAR(30),
	`street_address` VARCHAR(30),
	`town_address` VARCHAR(30) DEFAULT 'Thomastown' NOT NULL,
	`paid_so_far` FLOAT DEFAULT 0 NOT NULL,
	`willing_to_volunteer` BOOLEAN DEFAULT FALSE NOT NULL,
	`doctor` BIGINT,
	CONSTRAINT `family_pk` PRIMARY KEY `Family`(`id`),
	CONSTRAINT `family_doctor_fk` FOREIGN KEY `Family`(`doctor`) REFERENCES `Doctor`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);
ALTER TABLE `Family` ADD UNIQUE INDEX `family_name`(`family_name`);
ALTER TABLE `Family` ADD INDEX `willing_volunteer`(`willing_to_volunteer`);
INSERT INTO `Family` (`family_name`, `street_address`, `town_address`, `paid_so_far`, `willing_to_volunteer`, `doctor`) VALUES(
	'Wemyss',
	'Jerpoint Abbey',
	'Thomastown',
	0.0,
	TRUE,
	1
);

/*************************************************************************************************/
CREATE TABLE IF NOT EXISTS `Parent`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(30),
	`surname` VARCHAR(30),
	`contact_number` VARCHAR(15),
	`email` VARCHAR(50),
	`family` BIGINT,
	CONSTRAINT `parent_pk` PRIMARY KEY `Parent`(`id`),
	CONSTRAINT `parent_family_fk` FOREIGN KEY `Parent`(`family`) REFERENCES `Family`(`id`)
);
ALTER TABLE `Parent` ADD INDEX `parent_name` (`first_name`, `surname`);
INSERT INTO `Parent` (`first_name`, `surname`, `contact_number`, `email`, `family`) VALUES(
	'Joe',
	'Wemyss',
	'0899999901',
	'joewemyss3@gmail.com',
	1
);
INSERT INTO `Parent` (`first_name`, `surname`, `contact_number`, `email`, `family`) VALUES(
	'Michelle',
	'Power',
	'0897999901',
	'michellepower3@gmail.com',
	1
);
/*************************************************************************************************/

CREATE TABLE IF NOT EXISTS `Player`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(30) NOT NULL,
	`surname` VARCHAR(30) NOT NULL,
	`is_male` BOOLEAN DEFAULT TRUE NOT NULL,
	`year_of_birth` INT NOT NULL,
	`month_of_birth` TINYINT NOT NULL,
	`day_of_birth` TINYINT NOT NULL,
	`family` BIGINT,
	CONSTRAINT `player_pk` PRIMARY KEY `Player`(`id`),
	CONSTRAINT `player_family_fk` FOREIGN KEY `Player`(`family`) REFERENCES `Family`(`id`)ON DELETE RESTRICT ON UPDATE CASCADE
);
ALTER TABLE `Player` ADD INDEX `player_year` (`year_of_birth`);
ALTER TABLE `Player` ADD INDEX `player_name` (`first_name`, `surname`);
INSERT INTO `Player`(`first_name`, `surname`, `is_male`, `year_of_birth`, `month_of_birth`, `day_of_birth`, `family`) VALUES ('Joe', 'Wemyss', TRUE, 2005, 11, 7, 1);
INSERT INTO `Player`(`first_name`, `surname`, `is_male`, `year_of_birth`, `month_of_birth`, `day_of_birth`, `family`) VALUES ('Josephine', 'Wemyss', FALSE, 2000, 11, 7, 1);
INSERT INTO `Player`(`first_name`, `surname`, `is_male`, `year_of_birth`, `month_of_birth`, `day_of_birth`, `family`) VALUES ('James', 'Wemyss', True, 2000, 11, 7, 1);
/*************************************************************************************************/

CREATE TABLE IF NOT EXISTS `Medical_Note`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`condition_name` VARCHAR(30),
	`description` VARCHAR(255),
	`player` BIGINT,
	CONSTRAINT `medical_pk` PRIMARY KEY `MedicalNote`(`id`),
	CONSTRAINT `medical_player_fk` FOREIGN KEY `MedicalNote`(`player`) REFERENCES `Player`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `Medical_Note`(`condition_name`, `description`, `player`) VALUES ('Asthma', 'Severe asthma', 1);
INSERT INTO `Medical_Note`(`condition_name`, `description`, `player`) VALUES ('Asthma', 'Severe asthma', 2);
INSERT INTO `Medical_Note`(`condition_name`, `description`, `player`) VALUES ('Peanut allergy', 'allergic to peanuts', 3);
/*************************************************************************************************/
CREATE TABLE IF NOT EXISTS `Low_Fee`(
	`id` BIGINT NOT NULL,
	`fee` FLOAT NOT NULL,
	CONSTRAINT `low_fee_pk` PRIMARY KEY `Low_Fee`(`id`)
);

INSERT INTO `Low_Fee` VALUES (1, 30.0);
INSERT INTO `Low_Fee` VALUES (2, 50.0);
INSERT INTO `Low_Fee` VALUES (3, 70.0);
INSERT INTO `Low_Fee` VALUES (4, 100.0);
INSERT INTO `Low_Fee` VALUES (5, 120.0);

CREATE TABLE IF NOT EXISTS `High_Fee`(
	`id` BIGINT NOT NULL,
	`fee` FLOAT NOT NULL,
	CONSTRAINT `high_fee_pk` PRIMARY KEY `High_Fee`(`id`)
);

INSERT INTO `High_Fee` VALUES (1, 50.0);
INSERT INTO `High_Fee` VALUES (2, 90.0);
INSERT INTO `High_Fee` VALUES (3, 130.0);
INSERT INTO `High_Fee` VALUES (4, 180.0);
INSERT INTO `High_Fee` VALUES (5, 220.0);

CREATE TABLE IF NOT EXISTS `User` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`email_address` VARCHAR(30) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`first_name` VARCHAR(30),
	`surname` VARCHAR(30),
	`role` INT NOT NULL,
	CONSTRAINT `user_pk` PRIMARY KEY `User`(`id`)
);
ALTER TABLE `User` ADD UNIQUE INDEX `unique_email` (`email_address`);

INSERT INTO `User`(`email_address`, `password`, `first_name`, `surname`, `role`) VALUES ('joewemyss3@gmail.com', '$2a$04$ixT1MkSyNq.NVLyQMc.VWuAIivEjrW.8Tks/rycrVroN1nQVU9ijq', 'Joe', 'Wemyss', 1 );
INSERT INTO `User`(`email_address`, `password`, `first_name`, `surname`, `role`) VALUES ('michellepower3@gmail.com', '$2a$04$ixT1MkSyNq.NVLyQMc.VWuAIivEjrW.8Tks/rycrVroN1nQVU9ijq', 'Michelle', 'Power', 2);
INSERT INTO `User`(`email_address`, `password`, `first_name`, `surname`, `role`) VALUES ('agwemyss3@gmail.com', '$2a$04$ixT1MkSyNq.NVLyQMc.VWuAIivEjrW.8Tks/rycrVroN1nQVU9ijq', 'Agnes', 'Wemyss', 3);

