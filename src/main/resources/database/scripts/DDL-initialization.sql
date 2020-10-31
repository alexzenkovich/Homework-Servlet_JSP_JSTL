CREATE TABLE AUTHENTICATE
(
    ID                  INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    LOGIN               VARCHAR(50)                        NOT NULL,
    PASSWORD            VARCHAR(50)                        NOT NULL,
    PROFILE_ENABLE      VARCHAR(50)
);

CREATE TABLE BASKETCELLS
(
    ID                  INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    BOOK_ID             INTEGER                            NOT NULL,
    DAYS_FOR_READING    INTEGER                            NOT NULL,
    DATE_OF_TAKING_BOOK DATE                               NOT NULL,
    BASKET_ID           INTEGER                            NOT NULL
);

CREATE TABLE BASKETS
(
    ID                  INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    USER_ID             INTEGER                            NOT NULL
);

CREATE TABLE BOOKS
(
    ID                  INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    AUTHOR              VARCHAR (50)                       NOT NULL,
    TITLE               VARCHAR (50)                       NOT NULL,
    PAGES               INTEGER                            NOT NULL
);

CREATE TABLE ROLES
(
    ID                  INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL ,
    ROLE                VARCHAR(50)                        NOT NULL
);

CREATE TABLE USERS
(
    ID                  INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME                VARCHAR(50)                        NOT NULL,
    SURNAME             VARCHAR(50)                        NOT NULL,
    EMAIL               VARCHAR(50)                        NOT NULL,
    AGE                 INTEGER                            NOT NULL
);

CREATE TABLE USERS_ROLES
(
    ID      INTEGER AUTO_INCREMENT PRIMARY KEY ,
    USER_ID INTEGER                NOT NULL,
    ROLE_ID VARCHAR(50)            NOT NULL
);

ALTER TABLE AUTHENTICATE ADD CONSTRAINT FK_AUTHENTICATE_USERS FOREIGN KEY (USER_ID) references USERS (ID);

ALTER TABLE BASKETCELLS ADD CONSTRAINT FK_BASKETCELLS_BOOKS FOREIGN KEY (BOOK_ID) references BOOKS (ID);

ALTER TABLE BASKETCELLS ADD CONSTRAINT FK_BASKETCELLS_BASKETS FOREIGN KEY (BASKET_ID) references BASKETS (ID);

ALTER TABLE BASKETS ADD CONSTRAINT FK_BASKETS_USERS FOREIGN KEY (USER_ID) references USERS (ID);

ALTER TABLE USERS_ROLES ADD CONSTRAINT USER_ID FOREIGN KEY (USER_ID) references USERS (ID);

ALTER TABLE USERS_ROLES ADD CONSTRAINT ROLE_ID FOREIGN KEY (ROLE_ID) references ROLES (ID);



