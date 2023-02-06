-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema conference_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `conference_db` ;

-- -----------------------------------------------------
-- Schema conference_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `conference_db` DEFAULT CHARACTER SET utf8 ;
USE `conference_db` ;

-- -----------------------------------------------------
-- Table `conference_db`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference_db`.`role` ;

CREATE TABLE IF NOT EXISTS `conference_db`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `conference_db`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference_db`.`users` ;

CREATE TABLE IF NOT EXISTS `conference_db`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` CHAR(88) NOT NULL,
  `prefix` VARCHAR(7) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `cell_phone` VARCHAR(12) NULL,
  `job_title` VARCHAR(100) NULL,
  `organisation` VARCHAR(100) NULL,
  `registration_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `profile_picture` VARCHAR(2048) NULL,
  `role_role_id` INT NOT NULL DEFAULT 4,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_role_idx` (`role_role_id` ASC) VISIBLE,
  UNIQUE INDEX `cell_phone_UNIQUE` (`cell_phone` ASC) VISIBLE,
  CONSTRAINT `fk_user_role`
    FOREIGN KEY (`role_role_id`)
    REFERENCES `conference_db`.`role` (`role_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference_db`.`event_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference_db`.`event_address` ;

CREATE TABLE IF NOT EXISTS `conference_db`.`event_address` (
  `place_id` INT NOT NULL AUTO_INCREMENT,
  `building` VARCHAR(100) NOT NULL,
  `floor` INT UNSIGNED NOT NULL,
  `street_number` VARCHAR(5) NOT NULL,
  `street_name` VARCHAR(45) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  `postal_code` INT NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`place_id`),
  UNIQUE INDEX `address` (`building` ASC, `floor` ASC, `street_number` ASC, `street_name` ASC, `city` ASC, `postal_code` ASC, `country` ASC) INVISIBLE);


-- -----------------------------------------------------
-- Table `conference_db`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference_db`.`event` ;

CREATE TABLE IF NOT EXISTS `conference_db`.`event` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT(1000) NOT NULL,
  `event_date` DATETIME NOT NULL,
  `visitors` INT UNSIGNED NULL DEFAULT 0,
  `place_place_id` INT NOT NULL,
  PRIMARY KEY (`event_id`),
  INDEX `fk_event_event_address1_idx` (`place_place_id` ASC) VISIBLE,
  INDEX `event_date_index` (`event_date` ASC) INVISIBLE,
  FULLTEXT INDEX `event_name_index` (`name`) VISIBLE,
  UNIQUE INDEX `unique_event` (`name` ASC, `place_place_id` ASC, `event_date` ASC) VISIBLE,
  CONSTRAINT `fk_event_event_address1`
    FOREIGN KEY (`place_place_id`)
    REFERENCES `conference_db`.`event_address` (`place_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference_db`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference_db`.`report` ;

CREATE TABLE IF NOT EXISTS `conference_db`.`report` (
  `report_id` INT NOT NULL AUTO_INCREMENT,
  `topic` VARCHAR(255) NOT NULL,
  `outline` TEXT(1000) NULL,
  `event_event_id` INT NOT NULL,
  `users_user_id` INT NULL,
  `accepted_by_moderator` TINYINT(1) NOT NULL DEFAULT 0,
  `accepted_by_speaker` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`report_id`),
  INDEX `fk_report_event1_idx` (`event_event_id` ASC) VISIBLE,
  INDEX `fk_report_user1_idx` (`users_user_id` ASC) VISIBLE,
  FULLTEXT INDEX `index_reportname` (`topic`) VISIBLE,
  UNIQUE INDEX `unique_report` (`topic` ASC, `event_event_id` ASC) VISIBLE,
  CONSTRAINT `fk_report_event1`
    FOREIGN KEY (`event_event_id`)
    REFERENCES `conference_db`.`event` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_report_user1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `conference_db`.`users` (`user_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `conference_db`.`participant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `conference_db`.`participant` ;

CREATE TABLE IF NOT EXISTS `conference_db`.`participant` (
  `users_user_id` INT NOT NULL,
  `event_event_id` INT NOT NULL,
  PRIMARY KEY (`users_user_id`, `event_event_id`),
  INDEX `fk_user_has_event_event1_idx` (`event_event_id` ASC) VISIBLE,
  INDEX `fk_user_has_event_user1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_event_user1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `conference_db`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_has_event_event1`
    FOREIGN KEY (`event_event_id`)
    REFERENCES `conference_db`.`event` (`event_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE USER 'admin_db'@'localhost' IDENTIFIED BY 'r%g20_Fl8_1';
GRANT ALL ON conference_db.* TO 'admin_db'@'localhost';