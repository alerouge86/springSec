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
        
        
/*crypt password "123"*/
INSERT INTO users (email,password,nome,cognome,dataNascita,dataIscrizione,dataModifica,sesso,enabled) VALUES ('a@a.it','$2a$11$SBx69SKkGo9.SklF/Yhv3e1o84rm4zXBMBh8iQgifYIxfBVgG03h6','alessandro','rossi',{d '2020-02-26'},{ts '2020-07-15 14:09:05'},{ts '2020-07-20 14:35:39'},'M',true);
        

INSERT INTO user_authorities (user_id,authority) VALUES (1,'ROLE_USER');
/*INSERT INTO user_authorities (user_id,authority) VALUES (2,'ROLE_ADMIN');*/