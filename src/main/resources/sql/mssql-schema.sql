### 建表
CREATE TABLE IF NOT EXISTS tag_index
(
  id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  tag_name        VARCHAR(30),
  tagType     INT
);
