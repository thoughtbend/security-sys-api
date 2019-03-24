CREATE TABLE `secstore`.`sec_user` (
  `id` INT NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `date_added` DATETIME NOT NULL,
  `password_update_date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));

    
insert into secstore.sec_user
values (1, 'mnolan', 'passw0rd', current_timestamp(), current_timestamp());