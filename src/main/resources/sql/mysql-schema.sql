### 建表
CREATE TABLE IF NOT EXISTS day_task_result
(
  id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  day        VARCHAR(30),
  begin_time DATETIME,
  end_time   DATETIME,
  result     INT COMMENT '-1失败，０执行中，1成功',
  remark     VARCHAR(2000)
);

## 每日统计 总有功功率
CREATE TABLE IF NOT EXISTS day_stat
(
  id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  value_time varchar(30),
  value      DECIMAL(10, 2),
  version    INT                         DEFAULT '0'
);
