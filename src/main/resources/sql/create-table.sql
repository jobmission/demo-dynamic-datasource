### 建表
CREATE TABLE day_task_result
(
  id         BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  day        VARCHAR(30),
  begin_time DATETIME,
  end_time   DATETIME,
  result     INT(11) COMMENT '-1失败，０执行中，1成功',
  remark     VARCHAR(2000)
);

## 每日统计 总有功功率
CREATE TABLE day_stat
(
  id         BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  value_time DATETIME,
  value      DECIMAL(10, 2),
  version    INT(11)                         DEFAULT '0'
);
