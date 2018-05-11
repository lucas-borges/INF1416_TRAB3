create table USERS (
  EMAIL varchar(50) NOT NULL, #log in name, coletado do cert digi ao cadastrar
  PASSWORD varchar(40) NOT NULL, #3 fonemas, sem repetir, guardar hash dela+sal
  SALT char(10) NOT NULL,
  GROUP integer NOT NULL,
  NAME varchar(50) NOT NULL, #vem do cert digi
  DIGITAL_CERT blob NOT NULL, #no formato PEM !! conferir se blob e suficiente
  PASSWORD_ERRORS integer DEFAULT 0,
  PRIVATEKEY_VALIDATION_ERRORS integer DEFAULT 0,
  ACCESS_NUMBER integer DEFAULT 0,
  BLOCKED_UNTIL datetime,
  PRIMARY KEY (EMAIL),
  FOREIGN KEY (GROUP) REFERENCES GROUPS (ID)
);
  
create table GROUPS (
  ID int NOT NULL,
  NAME varchar(32) NOT NULL,
  PRIMARY KEY (ID)
);
  
create table REGISTERS (
  ID integer NOT NULL AUTO_INCREMENT,
  MESSAGE integer NOT NULL,
  DATETIME datetime NOT NULL,
  USER varchar(32) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (MESSAGE) REFERENCES MESSAGES (ID)
);

create table MESSAGES (
  ID integer NOT NULL,
  STRING varchar(100) NOT NULL,
  PRIMARY KEY (ID)
);
  

  
  