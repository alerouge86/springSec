create table categoriaEvento
(
    `id`       bigint(11) unsigned NOT NULL AUTO_INCREMENT,
    `descrizioneIta` varchar(200) not null,
    `descrizioneEng` varchar(200),
    `descrizioneRus` varchar(200),
    `descrizioneUa` varchar(200),
    `iconaFntAws` varchar(30) DEFAULT '',
    `dataCreazione` datetime DEFAULT CURRENT_TIMESTAMP,
    `userIdCreazione` bigint(11) unsigned DEFAULT 0,
    `creazioneAdmin`  boolean  DEFAULT true,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


create table eventi
(
    `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
    `categoriaEventoId` bigint(11) unsigned NOT NULL,
    `descrizione` varchar(250) not null,
    `numeroPartecipanti` int(7) DEFAULT 0,
    `dataOra` datetime  not null,
    `giornoSettimana` varchar(20) not null,
    `durataOre` double DEFAULT 0.0,
    `cittaId` bigint(11) unsigned NOT NULL,
    `indirizzo` varchar(250) not null,
    `prezzoPersona` double DEFAULT 0.0,
    `stato` varchar(100) DEFAULT '',
    `privato`  boolean  DEFAULT false,
    `scaduto`  boolean  DEFAULT false,
    `gestioneListaAttesa`  boolean  DEFAULT true,
    `dataCreazione` datetime DEFAULT CURRENT_TIMESTAMP,
    `dataModifica` datetime ON UPDATE CURRENT_TIMESTAMP,
    `userIdCreazione` bigint(11) unsigned NOT NULL,
    `annullamentoLogico`  boolean  DEFAULT false,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

  
ALTER TABLE eventi
    ADD CONSTRAINT `fk_eventi_categoria`
        FOREIGN KEY (`categoriaEventoId`) REFERENCES `categoriaEvento` (`id`);
  
ALTER TABLE eventi
    ADD CONSTRAINT `fk_eventi_citta`
        FOREIGN KEY (`cittaId`) REFERENCES `citta` (`id`);
  
ALTER TABLE eventi
    ADD CONSTRAINT `fk_eventi_utentiCreazione`
        FOREIGN KEY (`userIdCreazione`) REFERENCES `users` (`id`);


        
INSERT INTO categoriaEvento (descrizioneIta,descrizioneEng,descrizioneRus,descrizioneUa,dataCreazione,userIdCreazione,creazioneAdmin) VALUES ('Sport',null,null,null,{ts '2020-07-20 14:46:30'},0,true);
INSERT INTO categoriaEvento (descrizioneIta,descrizioneEng,descrizioneRus,descrizioneUa,dataCreazione,userIdCreazione,creazioneAdmin) VALUES ('Musica',null,null,null,{ts '2020-07-20 14:46:34'},0,true);
INSERT INTO categoriaEvento (descrizioneIta,descrizioneEng,descrizioneRus,descrizioneUa,dataCreazione,userIdCreazione,creazioneAdmin) VALUES ('Cultura',null,null,null,{ts '2020-07-20 14:46:37'},0,true);
INSERT INTO categoriaEvento (descrizioneIta,descrizioneEng,descrizioneRus,descrizioneUa,dataCreazione,userIdCreazione,creazioneAdmin) VALUES ('Giochi da tavolo',null,null,null,{ts '2020-07-20 14:46:41'},0,true);
INSERT INTO categoriaEvento (descrizioneIta,descrizioneEng,descrizioneRus,descrizioneUa,dataCreazione,userIdCreazione,creazioneAdmin) VALUES ('Speaking club',null,null,null,{ts '2020-07-20 14:47:01'},0,true);
