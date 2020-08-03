/*crypt password "123"*/
INSERT INTO users (email,password,nome,cognome,dataNascita,dataIscrizione,dataModifica,sesso,enabled) VALUES ('a@a.it','$2a$11$SBx69SKkGo9.SklF/Yhv3e1o84rm4zXBMBh8iQgifYIxfBVgG03h6','alessandro','rossi',{d '2020-02-26'},{ts '2020-07-15 14:09:05'},{ts '2020-07-20 14:35:39'},'M',true);
        

INSERT INTO user_authorities (user_id,authority) VALUES (1,'ROLE_USER');
/*INSERT INTO user_authorities (user_id,authority) VALUES (2,'ROLE_ADMIN');*/