
## 创建导航仓数据库</br>
````SQL
CREATE DATABASE IF NOT EXISTS data_warehouse DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
grant all privileges on data_warehouse.* to data_warehouse@localhost identified by 'xxx';
````

