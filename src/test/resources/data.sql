CREATE TABLE USERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

INSERT INTO USERS (id,firstname, lastname,username,password, email )
VALUES (2,'biniam','okbe','gentette','trtrtrt','yyyy@gmail.com');

