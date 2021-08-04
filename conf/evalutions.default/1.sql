-- User schema

-- !Ups

CREATE TABLE `task` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(30) COLLATE utf8mb4_bin NOT NULL,
    `completed` tinyint(1) default NULL,
    `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `created` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- !Downs
drop table `task`
