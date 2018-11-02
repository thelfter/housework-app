INSERT INTO `USER` (`USERNAME`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `LAST_LOGIN`, `ROLE`) VALUES ('admin', '$2a$12$KcEqIspafLltyaXYMZ9CpurCQUnoE2idjWkeaBegGr/DWYD04tSkq', 'Admin', 'User', null, 'ROLE_ADMIN');

INSERT INTO `USER` (`USERNAME`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `LAST_LOGIN`, `ROLE`) VALUES  ('user1', '$2a$12$aCS4dkuMPV6KVrUUnM5vgu8vzR9W9vEAG1decpcJnWoM0paFqFTcu', 'Teszt', 'Elek', null, 'ROLE_USER');
INSERT INTO `USER` (`USERNAME`, `PASSWORD`, `FIRST_NAME`, `LAST_NAME`, `LAST_LOGIN`, `ROLE`) VALUES  ('user2', '$2a$12$aCS4dkuMPV6KVrUUnM5vgu8vzR9W9vEAG1decpcJnWoM0paFqFTcu', 'John', 'Doe', null, 'ROLE_USER');

insert into `TASK` (`TASK_NAME`, `TASK_DESCRIPTION`, `SCORE`, `DUE_DATE`, `IS_COMPLETED`, `USER_ID`) values ('proin at turpis', 'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 1, '2018-03-14', FALSE, 1);

insert into `ROOM` (`ROOM_ID`, `NAME`, `USER_ID`) values (1, 'Patrik szoba' , 1);
insert into `ROOM` (`ROOM_ID`, `NAME`, `USER_ID`) values (2, 'Geri szoba' , 2);
insert into `ROOM` (`ROOM_ID`, `NAME`, `USER_ID`) values (3, 'Petko' , 3);



insert into `TASK_CATEGORY` (`ID`, `NAME`) values (1, 'Karbantartás');
insert into `TASK_CATEGORY` (`ID`, `NAME`) values (2, 'Takarítás');
insert into `TASK_CATEGORY` (`ID`, `NAME`) values (3, 'Festés');
insert into `TASK_CATEGORY` (`ID`, `NAME`) values (4, 'Pakolás');