CREATE TABLE AUTHENTICATE
(
    ID                INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    LOGIN             VARCHAR(50)                        NOT NULL,
    PASSWORD          VARCHAR(50)                        NOT NULL,
    PROFILE_ENABLE    VARCHAR(50)                        NOT NULL,
);


CREATE TABLE USERS
(
    ID       INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    NAME     VARCHAR(50)                        NOT NULL,
    SURNAME  VARCHAR(50)                        NOT NULL,
    EMAIL    VARCHAR(50)                        NOT NULL,
    AGE      INTEGER                            NOT NULL,
);

CREATE TABLE ROLES
(
    ID      INTEGER AUTO_INCREMENT NOT NULL,
    ROLE    VARCHAR(50)            NOT NULL
);

CREATE TABLE USERS_ROLES
(
    ID      INTEGER AUTO_INCREMENT NOT NULL,
    USER_ID INTEGER                NOT NULL,
    ROLE_ID VARCHAR(50)            NOT NULL
);