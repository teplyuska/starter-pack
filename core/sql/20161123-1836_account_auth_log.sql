CREATE TABLE `starter_pack`.`account_auth_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `account_id` BIGINT NOT NULL,
  `ip_address` VARCHAR(50) NOT NULL,
  `status` VARCHAR(255) NOT NULL,
  `created_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_account_auth_log_account_idx` (`account_id` ASC),
  CONSTRAINT `fk_account_auth_log_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `starter_pack`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `starter_pack`.`account`
ADD COLUMN `password_hash` VARCHAR(255) NOT NULL AFTER `email`,
ADD COLUMN `display_name` VARCHAR(255) NULL AFTER `password_hash`;