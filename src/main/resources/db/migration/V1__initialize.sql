CREATE TABLE USERS (
    ID           BIGINT        NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    LOGIN_ID     VARCHAR(128)  NOT NULL,
    NAME         VARCHAR(128)  NOT NULL,
    PASSWORD     VARCHAR(1024) NOT NULL,
    MAIL_ADDRESS VARCHAR(256),
    CONSTRAINT USERS_PK PRIMARY KEY (ID),
    CONSTRAINT USERS_UK1 UNIQUE (LOGIN_ID),
    CONSTRAINT USERS_UK2 UNIQUE (NAME)
);

INSERT INTO USERS (LOGIN_ID, NAME, PASSWORD, MAIL_ADDRESS)
VALUES ('admin', '管理者', 'test', 'admin@test.com');

INSERT INTO USERS (LOGIN_ID, NAME, PASSWORD, MAIL_ADDRESS)
VALUES ('general', '一般ユーザ', 'test', 'user@test.com');
