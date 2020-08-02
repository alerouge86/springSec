create table users
(
    `id`       bigint(11) unsigned NOT NULL AUTO_INCREMENT,
    `email` varchar(250) not null,
    `password` varchar(100) not null,
    `nome` varchar(250) not null,
    `cognome` varchar(250) not null,
    `dataNascita` date  not null,
    `dataIscrizione` datetime DEFAULT CURRENT_TIMESTAMP,
    `dataModifica` datetime ON UPDATE CURRENT_TIMESTAMP,
    `sesso` varchar(1) not null,
    `enabled`  boolean     not null,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_unique` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table user_authorities
(
    `id`        bigint(11) unsigned NOT NULL AUTO_INCREMENT,
    `user_id`   bigint(11) unsigned NOT NULL,
    `authority` varchar(50) not null,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_authorities_unique` (`user_id`, `authority`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


ALTER TABLE `user_authorities`
    ADD CONSTRAINT `fk_authorities`
        FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);