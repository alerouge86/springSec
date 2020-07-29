create table nazioni
(
    `id`       bigint(11) unsigned NOT NULL AUTO_INCREMENT,
    `descrizione` varchar(250) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


create table citta
(
    `id`       bigint(11) unsigned NOT NULL AUTO_INCREMENT,
    `nazioneId`   bigint(11) unsigned NOT NULL,
    `descrizione` varchar(250) not null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

  
ALTER TABLE `citta`
    ADD CONSTRAINT `fk_citta`
        FOREIGN KEY (`nazioneId`) REFERENCES `nazioni` (`id`);
  

        
INSERT INTO nazioni (descrizione) VALUES ('Italy');
INSERT INTO nazioni (descrizione) VALUES ('Ukraine');


INSERT INTO citta (nazioneId,descrizione) VALUES (1,'Roma');
INSERT INTO citta (nazioneId,descrizione) VALUES (2,'Kyiv');
INSERT INTO citta (nazioneId,descrizione) VALUES (2,'Lviv');
INSERT INTO citta (nazioneId,descrizione) VALUES (2,'Odessa');

