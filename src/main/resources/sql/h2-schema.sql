
 -- DROP TABLE IF EXISTS student;

CREATE TABLE IF NOT EXISTS month_stat(
 id bigint auto_increment primary key not null ,
 value_date varchar(30),
 value varchar(100)
);