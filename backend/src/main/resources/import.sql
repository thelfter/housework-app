INSERT INTO `USER` (`USERNAME`, `PASSWORD`, `FULL_NAME`, `LAST_LOGIN`, `ROLE`) VALUES ('admin', '$2a$12$KcEqIspafLltyaXYMZ9CpurCQUnoE2idjWkeaBegGr/DWYD04tSkq', 'Admin User', null, 'ROLE_ADMIN');

INSERT INTO `USER` (`USERNAME`, `PASSWORD`, `FULL_NAME`, `LAST_LOGIN`, `ROLE`) VALUES  ('user1', '$2a$12$aCS4dkuMPV6KVrUUnM5vgu8vzR9W9vEAG1decpcJnWoM0paFqFTcu', 'Teszt Elek', null, 'ROLE_USER');
INSERT INTO `USER` (`USERNAME`, `PASSWORD`, `FULL_NAME`, `LAST_LOGIN`, `ROLE`) VALUES  ('user2', '$2a$12$aCS4dkuMPV6KVrUUnM5vgu8vzR9W9vEAG1decpcJnWoM0paFqFTcu', 'John Doe', null, 'ROLE_USER');

insert into `TASK` (`TASK_NAME`, `TASK_DESCRIPTION`, `SCORE`, `IS_COMPLETED`, `USER_ID`) values ('WC takarítás', 'Valakinek kikéne takarírani a wc helységet.',10, FALSE, NULL);
insert into `TASK` (`TASK_NAME`, `TASK_DESCRIPTION`, `SCORE`, `IS_COMPLETED`, `USER_ID`) values ('Porszívózás', 'Fel kéne porszívózni a konyhát.',5,FALSE, NULL);
insert into `TASK` (`TASK_NAME`, `TASK_DESCRIPTION`, `SCORE`, `IS_COMPLETED`, `USER_ID`) values ('Mosogatás', 'Elkéne mosogatni.',5, FALSE, NULL);
insert into `TASK` (`TASK_NAME`, `TASK_DESCRIPTION`, `SCORE`, `IS_COMPLETED`, `USER_ID`) values ('Portörlés', 'Le kéne törölni a port',3, FALSE, NULL);

insert into `ROOM` (`ROOM_ID`, `NAME`, `USER_ID`, `RESERVED`) values (1, 'Hálószoba 1' , 2, true);
insert into `ROOM` (`ROOM_ID`, `NAME`, `USER_ID`, `RESERVED`) values (2, 'Hálószoba 2' , 3, true);
insert into `ROOM` (`ROOM_ID`, `NAME`, `USER_ID`, `RESERVED`) values (3, 'Hálószoba 3' , NULL, false);
insert into `ROOM` (`ROOM_ID`, `NAME`, `USER_ID`, `RESERVED`) values (4, 'Hálószoba 4' , NULL, false);