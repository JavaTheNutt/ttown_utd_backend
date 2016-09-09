CREATE SCHEMA IF NOT EXISTS thomastown_utd_test;
USE thomastown_utd_test;

CREATE TABLE IF NOT EXISTS Doctor (
	`id`             BIGINT                           NOT NULL AUTO_INCREMENT,
	`first_name`     VARCHAR(30),
	`surname`        VARCHAR(30),
	`street_address` VARCHAR(30),
	`town_address`   VARCHAR(30) DEFAULT 'Thomastown' NOT NULL,
	`contact_number` VARCHAR(15)                      NOT NULL,
	CONSTRAINT `doc_pk` PRIMARY KEY `Doctor`(`id`),
	CONSTRAINT `doc_unique_name` UNIQUE (`first_name`, `surname`),
	CONSTRAINT `doc_unique_num` UNIQUE (`contact_number`)
);
ALTER TABLE `Doctor`
	ADD INDEX `surname` (`surname`);
INSERT INTO Doctor (first_name, surname, street_address, contact_number) VALUES (
	'Achim',
	'Schlunke',
	'Low Street',
	'05188945'
);
/*************************************************************************************************/
CREATE TABLE IF NOT EXISTS `Family` (
	`id`                   BIGINT                           NOT NULL AUTO_INCREMENT,
	`family_name`          VARCHAR(30),
	`street_address`       VARCHAR(30),
	`town_address`         VARCHAR(30) DEFAULT 'Thomastown' NOT NULL,
	`paid_so_far`          FLOAT DEFAULT 0                  NOT NULL,
	`willing_to_volunteer` BOOLEAN DEFAULT FALSE            NOT NULL,
	`doctor`               BIGINT,
	CONSTRAINT `family_pk` PRIMARY KEY `Family`(`id`),
	CONSTRAINT `family_doctor_fk` FOREIGN KEY `Family`(`doctor`) REFERENCES `Doctor` (`id`)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
);
ALTER TABLE `Family`
	ADD UNIQUE INDEX `family_name`(`family_name`);
ALTER TABLE `Family`
	ADD INDEX `willing_volunteer`(`willing_to_volunteer`);
INSERT INTO `Family` (`family_name`, `street_address`, `town_address`, `paid_so_far`, `willing_to_volunteer`, `doctor`)
VALUES (
	'Wemyss',
	'Jerpoint Abbey',
	'Thomastown',
	0.0,
	TRUE,
	1
);

/*************************************************************************************************/
CREATE TABLE IF NOT EXISTS `Parent` (
	`id`             BIGINT NOT NULL AUTO_INCREMENT,
	`first_name`     VARCHAR(30),
	`surname`        VARCHAR(30),
	`contact_number` VARCHAR(15),
	`email`          VARCHAR(50),
	`family`         BIGINT,
	CONSTRAINT `parent_pk` PRIMARY KEY `Parent`(`id`),
	CONSTRAINT `parent_family_fk` FOREIGN KEY `Parent`(`family`) REFERENCES `Family` (`id`)
);
ALTER TABLE `Parent`
	ADD INDEX `parent_name` (`first_name`, `surname`);
INSERT INTO `Parent` (`first_name`, `surname`, `contact_number`, `email`, `family`) VALUES (
	'Joe',
	'Wemyss',
	'0899999901',
	'joewemyss3@gmail.com',
	1
);
INSERT INTO `Parent` (`first_name`, `surname`, `contact_number`, `email`, `family`) VALUES (
	'Michelle',
	'Power',
	'0897999901',
	'michellepower3@gmail.com',
	1
);
/*************************************************************************************************/

CREATE TABLE IF NOT EXISTS `Player` (
	`id`             BIGINT               NOT NULL AUTO_INCREMENT,
	`first_name`     VARCHAR(30)          NOT NULL,
	`surname`        VARCHAR(30)          NOT NULL,
	`is_male`        BOOLEAN DEFAULT TRUE NOT NULL,
	`year_of_birth`  INT                  NOT NULL,
	`month_of_birth` TINYINT              NOT NULL,
	`day_of_birth`   TINYINT              NOT NULL,
	`family`         BIGINT,
	CONSTRAINT `player_pk` PRIMARY KEY `Player`(`id`),
	CONSTRAINT `player_family_fk` FOREIGN KEY `Player`(`family`) REFERENCES `Family` (`id`)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
);
ALTER TABLE `Player`
	ADD INDEX `player_year` (`year_of_birth`);
ALTER TABLE `Player`
	ADD INDEX `player_name` (`first_name`, `surname`);
INSERT INTO `Player` (`first_name`, `surname`, `is_male`, `year_of_birth`, `month_of_birth`, `day_of_birth`, `family`)
VALUES ('Joe', 'Wemyss', TRUE, 2005, 11, 7, 1);
INSERT INTO `Player` (`first_name`, `surname`, `is_male`, `year_of_birth`, `month_of_birth`, `day_of_birth`, `family`)
VALUES ('Josephine', 'Wemyss', FALSE, 2000, 11, 7, 1);
INSERT INTO `Player` (`first_name`, `surname`, `is_male`, `year_of_birth`, `month_of_birth`, `day_of_birth`, `family`)
VALUES ('James', 'Wemyss', TRUE, 2000, 11, 7, 1);
/*************************************************************************************************/

CREATE TABLE IF NOT EXISTS `Medical_Note` (
	`id`             BIGINT NOT NULL AUTO_INCREMENT,
	`condition_name` VARCHAR(30),
	`description`    VARCHAR(255),
	`player`         BIGINT,
	CONSTRAINT `medical_pk` PRIMARY KEY `MedicalNote`(`id`),
	CONSTRAINT `medical_player_fk` FOREIGN KEY `MedicalNote`(`player`) REFERENCES `Player` (`id`)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);
INSERT INTO `Medical_Note` (`condition_name`, `description`, `player`) VALUES ('Asthma', 'Severe asthma', 1);
INSERT INTO `Medical_Note` (`condition_name`, `description`, `player`) VALUES ('Asthma', 'Severe asthma', 2);
INSERT INTO `Medical_Note` (`condition_name`, `description`, `player`)
VALUES ('Peanut allergy', 'allergic to peanuts', 3);
/*************************************************************************************************/
CREATE TABLE IF NOT EXISTS `Low_Fee` (
	`id`  BIGINT NOT NULL,
	`fee` FLOAT  NOT NULL,
	CONSTRAINT `low_fee_pk` PRIMARY KEY `Low_Fee`(`id`)
);

INSERT INTO `Low_Fee` VALUES (1, 30.0);
INSERT INTO `Low_Fee` VALUES (2, 50.0);
INSERT INTO `Low_Fee` VALUES (3, 70.0);
INSERT INTO `Low_Fee` VALUES (4, 100.0);
INSERT INTO `Low_Fee` VALUES (5, 120.0);

CREATE TABLE IF NOT EXISTS `High_Fee` (
	`id`  BIGINT NOT NULL,
	`fee` FLOAT  NOT NULL,
	CONSTRAINT `high_fee_pk` PRIMARY KEY `High_Fee`(`id`)
);

INSERT INTO `High_Fee` VALUES (1, 50.0);
INSERT INTO `High_Fee` VALUES (2, 90.0);
INSERT INTO `High_Fee` VALUES (3, 130.0);
INSERT INTO `High_Fee` VALUES (4, 180.0);
INSERT INTO `High_Fee` VALUES (5, 220.0);

/********************************************************************************************/

CREATE TABLE IF NOT EXISTS `User` (
	`id`            BIGINT       NOT NULL AUTO_INCREMENT,
	`email_address` VARCHAR(30)  NOT NULL,
	`password`      VARCHAR(255) NOT NULL,
	`first_name`    VARCHAR(30),
	`surname`       VARCHAR(30),
	`role`          INT          NOT NULL,
	CONSTRAINT `user_pk` PRIMARY KEY `User`(`id`)
);
ALTER TABLE `User`
	ADD UNIQUE INDEX `unique_email` (`email_address`);

INSERT INTO `User` (`email_address`, `password`, `first_name`, `surname`, `role`)
VALUES ('joewemyss3@gmail.com', '$2a$04$ixT1MkSyNq.NVLyQMc.VWuAIivEjrW.8Tks/rycrVroN1nQVU9ijq', 'Joe', 'Wemyss', 1);
INSERT INTO `User` (`email_address`, `password`, `first_name`, `surname`, `role`) VALUES
	('michellepower3@gmail.com', '$2a$04$ixT1MkSyNq.NVLyQMc.VWuAIivEjrW.8Tks/rycrVroN1nQVU9ijq', 'Michelle', 'Power', 2);
INSERT INTO `User` (`email_address`, `password`, `first_name`, `surname`, `role`)
VALUES ('agwemyss3@gmail.com', '$2a$04$ixT1MkSyNq.NVLyQMc.VWuAIivEjrW.8Tks/rycrVroN1nQVU9ijq', 'Agnes', 'Wemyss', 3);

/****************************************************************************************************************************/
/*this table will represent a team. The team rank will act as an imaginary foreign key which will represent a one-to-many relationship with players based on their age, which is calculated at the application level*/
CREATE TABLE IF NOT EXISTS `Team` (
	`id`       BIGINT      NOT NULL AUTO_INCREMENT,
	`age_rank` INT         NOT NULL,
	`name`     VARCHAR(15) NOT NULL,
	CONSTRAINT `team_pk` PRIMARY KEY `Team`(`id`)
);
INSERT INTO `Team` (`age_rank`, `name`) VALUES (7, 'U-8M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (7, 'U-8F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (8, 'U-9M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (8, 'U-9F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (9, 'U-10M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (9, 'U-10F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (10, 'U-11M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (10, 'U-11F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (11, 'U-12M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (11, 'U-12F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (12, 'U-13M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (12, 'U-13F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (13, 'U-14M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (13, 'U-14F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (14, 'U-15M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (14, 'U-15F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (15, 'U-16M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (14, 'U-16F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (16, 'U-17M');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (16, 'U-17F');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (17, 'JuniorM');
INSERT INTO `Team` (`age_rank`, `name`) VALUES (17, 'JuniorF');
/********************************************************************/
CREATE TABLE IF NOT EXISTS `Manager` (
	`id`             BIGINT      NOT NULL AUTO_INCREMENT,
	`first_name`     VARCHAR(30) NOT NULL,
	`surname`        VARCHAR(30) NOT NULL,
	`street_address` VARCHAR(30) NOT NULL,
	`town_address`   VARCHAR(30) NOT NULL DEFAULT 'Thomastown',
	`email_address`  VARCHAR(30),
	`contact_number` VARCHAR(30) NOT NULL,
	`team`           BIGINT,
	CONSTRAINT `manager_pk` PRIMARY KEY `Manager`(`id`),
	CONSTRAINT `manager_team_fk` FOREIGN KEY `Manager`(`team`) REFERENCES `Team` (`id`)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);
ALTER TABLE `Manager`
	ADD UNIQUE INDEX `unique_manager_email` (`email_address`);
INSERT INTO `Manager` (`first_name`, `surname`, `street_address`, `town_address`, `email_address`, `contact_number`, `team`)
VALUES (
	'Joe',
	'Bloggs',
	'Mill Street',
	'Thomastown',
	'joe@bloggs.com',
	'087654321',
	1
);
INSERT INTO `Manager` (`first_name`, `surname`, `street_address`, `town_address`, `email_address`, `contact_number`, `team`)
VALUES (
	'John',
	'Bloggs',
	'Mill Street',
	'Thomastown',
	'john@bloggs.com',
	'087654321',
	1
);
INSERT INTO `Manager` (`first_name`, `surname`, `street_address`, `town_address`, `email_address`, `contact_number`, `team`)
VALUES (
	'Jason',
	'Bloggs',
	'Mill Street',
	'Thomastown',
	'john@bloggs.com',
	'087654321',
	2
);
/***********************************************************************/
CREATE TABLE IF NOT EXISTS `Opponent`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(20) NOT NULL,
	`pitch` VARCHAR(20),
	CONSTRAINT `opponent_pk` PRIMARY KEY `Opponent`(`id`)
);
ALTER TABLE `Opponent` ADD UNIQUE INDEX `unique_opponent_name` (`name`);
ALTER TABLE `Opponent` ADD UNIQUE INDEX `unique_opponent_pitch`(`pitch`);

/************************************************************************/

CREATE TABLE IF NOT EXISTS `Fixture`(
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`at_home` BOOLEAN NOT NULL DEFAULT TRUE,
	`date_of_match` DATE NOT NULL,
	`team` BIGINT NOT NULL,
	`opponent` BIGINT,
	CONSTRAINT `fixture_pk` PRIMARY KEY `Fixture`(`id`),
	CONSTRAINT `fixture_team_fk` FOREIGN KEY `Fixture`(`team`) REFERENCES `Team`(`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `fixture_opponent_fk` FOREIGN KEY `Fixture`(`opponent`) REFERENCES `Opponent`(`id`) ON UPDATE CASCADE ON DELETE SET NULL
)
