
## 创建数据库</br>
````SQL
CREATE DATABASE IF NOT EXISTS demo_07;
create user 'user_dev'@'localhost' identified by 'pass_dev';
grant all privileges on demo_07.* to 'user_dev'@'localhost';

````