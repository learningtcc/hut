CREATE TABLE persistent_files(
  id int AUTO_INCREMENT,
  createdAt datetime,
  trash TINYINT DEFAULT 0,
  userId varchar(20),
  appId varchar(30),
  path varchar(255),
  filename varchar(100),
  size INTEGER ,
  contentType varchar(100),
  md5 varchar(100),
  fileIndex varchar(300),
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET =UTF8