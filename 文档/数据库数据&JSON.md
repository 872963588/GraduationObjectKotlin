**1.user表**

id, user_number,  user_name, user_password,user_school, user_sex,user_img

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_number` VARCHAR(45) NULL,
  `user_name` VARCHAR(45) NULL,
  `user_password` VARCHAR(45) NULL,
  `user_school` VARCHAR(45) NULL,
  `user_sex` VARCHAR(45) NULL,
  `user_img` VARCHAR(200) NULL DEFAULT 'http://localhost/Study/images/user_default.png',
  PRIMARY KEY (`id`))
ENGINE = InnoDB

​	insert into user (user_number,user_name,user_password,user_school,user_sex) values ('10001','张一','123465','河南中医药大学','男'),
​	('10002','Jack','123465','河南中医药大学','男'),
​    ('10003','张二','123465','河南中医药大学','女'),
​    ('10004','张三','123465','河南中医药大学','男'),
​    ('10005','李四','123465','河南中医药大学','女'),
​    ('10006','李四','123465','河南中医药大学','男'),
​    ('10007','Lucy','123465','河南中医药大学','女'),
​    ('10008','Mike','123465','河南中医药大学','男'),
​    ('20000','李四','123465','河南中医药大学','男'),
​    ('20010','李四','abcdefg','河南中医药大学','男');



**2.course表**

id,course_name, course_detail,course_owner, course_img

DROP TABLE IF EXISTS course;
CREATE TABLE IF NOT EXISTS `mydb`.`course` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `course_name` VARCHAR(45) NULL,
  `course_detail` VARCHAR(200) NULL,
  `course_owner` INT UNSIGNED NULL,
  `course_img` VARCHAR(200) NULL DEFAULT 'http://localhost/Study/images/course_default.png',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

​	insert into course (course_name,course_detail,course_owner) values 
('软件工程','软件工程是一门研究用工程化方法构建和维护有效的、实用的和高质量的软件的学科。',1),
('数据库设计','数据库设计(Database Design)是指对于一个给定的应用环境，构造最优的数据库模式，建立数据库及其应用系统，使之能够有效地存储数据，满足各种用户的应用需求（信息要求和处理要求）。',1),
('操作系统','操作系统(Operating System，简称OS)是管理计算机硬件与软件资源的计算机程序。',1),
('数据结构','数据结构是计算机存储、组织数据的方式。',2),
('JAVA开发','Java 是由Sun Microsystems公司于1995年5月推出的高级程序设计语言。',4);





**3.class表**

id,class_name,course_id

DROP TABLE IF EXISTS class;
CREATE TABLE IF NOT EXISTS `mydb`.`class` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `class_name` VARCHAR(45) NULL,
  `course_id` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

insert into class (class_name,course_id) values('16级','1'),('17级','1'),('18级','1'),('16级','2'),('17级','2'),('18级','2'),('16级','3'),('17级','3'),('18级','3');



**4.user_course_class表**

user_id,course_id,class_id

DROP TABLE IF EXISTS user_course_class;
CREATE TABLE IF NOT EXISTS `mydb`.`user_course_class` (
  `user_id` INT NOT NULL,
  `course_id` INT NOT NULL,
  `class_id` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`, `course_id`, `class_id`))
ENGINE = InnoDB;

insert into user_course_class (user_id,course_id,class_id) values(1,1,0),(1,2,1),(1,3,0),(1,5,0),(2,1,0),(2,4,0),(3,1,0),(4,1,0),(4,5,0),(5,1,0),(6,1,0);





**5.task表**

### **//还没设计好呢**

id, task_name, task_time, task_detail,task_url,course_id

DROP TABLE IF EXISTS task;
CREATE TABLE IF NOT EXISTS `mydb`.`task` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `task_name` VARCHAR(45) NULL,
  `task_time` VARCHAR(45) NULL,
  `task_detail` VARCHAR(45) NULL,
  `task_url` VARCHAR(45) NULL,
  `course_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

​	insert into task (task_name, task_time, task_detail,task_url,course_id) 
values ('观看视频','2020年4月15日 16点15分','观看视频后发表感悟','',1),
​	('预习','2020年4月14日 16点15分','查看PPT，预习下一章的知识','',1),
​	('复习','2020年4月13日 16点15分','查看此文档，复习这节课讲的内容','',1),
​	('预习','2020年4月12日 16点15分','观看视频后发表感悟','',1),
​	('观看视频','2020年4月11日 16点15分','观看视频后发表感悟','',1),
​	('预习','2020年4月10日 16点15分','观看视频，预习下节课的知识','',1),
​	('观看视频','2020年4月10日 16点15分','观看视频后发表感悟','',1),
​	('复习','2020年4月10日 16点15分','观看视频后发表感悟','',1),
​	('观看视频','2020年4月10日 16点15分','观看视频后发表感悟','',1),
​	('预习','2020年4月10日 16点15分','预习下一章的知识','',1);

insert into task (task_name, task_time, task_detail,task_url,course_id) 
values ('观看视频','2020年4月15日 16点15分','观看视频后发表感悟','',1),('观看视频','2020年4月15日 16点15分','观看视频后发表感悟','',2),
	('预习','2020年4月14日 16点15分','查看PPT，预习下一章的知识','',2),
	('复习','2020年4月13日 16点15分','查看此文档，复习这节课讲的内容','',2),
	('预习','2020年4月12日 16点15分','观看视频后预习','',2),
	('观看视频','2020年4月11日 16点15分','观看视频后发表感悟','',2),



​	('观看视频','2020年4月15日 16点15分','观看视频后发表感悟','',3),
​	('预习','2020年4月14日 16点15分','查看PPT，预习下一章的知识','',3),
​	('复习','2020年4月13日 16点15分','查看此文档，复习这节课讲的内容','',3),
​	('预习','2020年4月12日 16点15分','观看视频后发表感悟','',3),
​	('观看视频','2020年4月11日 16点15分','观看视频后发表感悟','',3),





**6.comment表**

### **如果加上赞数的话，怎么判断有没有点过？**

id, user_id,comment_time, comment_detail,course_id,task_id

DROP TABLE IF EXISTS comment;
CREATE TABLE IF NOT EXISTS `mydb`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(45) NULL,
  `comment_time` VARCHAR(45) NULL,
  `comment_detail` VARCHAR(45) NULL,
  `comment_support` INT NULL,
  `course_id` VARCHAR(45) NULL,
  `task_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

insert into comment (user_id,comment_time, comment_detail,course_id,task_id) values(1,'2020年4月15日 16点15分','老师讲的真好',1,0),
(1,'2020年4月15日 16点15分','这个视频讲的很全面',1,1),
(2,'2020年4月15日 16点15分','老师讲的真好',1,1),
(3,'2020年4月15日 16点15分','老师讲的真好',1,1),
(4,'2020年4月15日 16点15分','老师讲的真好',1,1),
(5,'2020年4月15日 16点15分','老师讲的真好',1,1),
(6,'2020年4月15日 16点15分','老师讲的真好',1,1),
(1,'2020年4月15日 16点15分','这个视频讲的很全面',1,2),
(2,'2020年4月15日 16点15分','老师讲的真好',1,2),
(3,'2020年4月15日 16点15分','老师讲的真好',1,2),
(4,'2020年4月15日 16点15分','老师讲的真好',1,2),
(5,'2020年4月15日 16点15分','老师讲的真好',1,2),
(6,'2020年4月15日 16点15分','老师讲的真好',1,2);





一对多 多对多 关系表

**android:ems**



{"status":"ok",
"query":"Lucy",
"courses":[{"course_name":"软件工程","course_task":"1","course_img":"图片"},{"course_name":"数据库设计","course_task":"1","course_img":"图片"},{"course_name":"操作系统","course_task":"1","course_img":"图片"}]}

{"status":"ok","query":"Lucy","courses":[{"course_name":"软件工程","course_task":"1","course_img":"http://localhost/AppService/ruanjian.jpg","course_detail":"123456","course_name":"me"},{"course_name":"数据库设计","course_task":"1","course_img":"http://localhost/AppService/sql.jpg","course_detail":"123456","course_name":"me"},{"course_name":"操作系统1","course_task":"1","course_img":"http://localhost/AppService/caozuo.jpg","course_detail":"123456","course_name":"me"},{"course_name":"操作系统2","course_task":"1","course_img":"http://localhost/AppService/caozuo.jpg","course_detail":"123456","course_name":"me"},{"course_name":"操作系统3","course_task":"1","course_img":"http://localhost/AppService/caozuo.jpg","course_detail":"123456","course_name":"me"},{"course_name":"操作系统4","course_task":"1","course_img":"http://localhost/AppService/caozuo.jpg","course_detail":"123456","course_name":"me"},{"course_name":"操作系统5","course_task":"1","course_img":"http://localhost/AppService/caozuo.jpg","course_detail":"123456","course_name":"me"}]}



{"status":"ok",
"query":"软件工程",
"tasks":[
{"task_name":"观看视频","task_time":"2020年4月15日 16点15分","task_detail":"观看视频后发表感悟","task_url":"mp4"},
{"task_name":"预习","task_time":"2020年4月15日 16点15分","task_detail":"预习下一章的知识","task_url":"text"},
{"task_name":"观看视频","task_time":"2020年4月15日 16点15分","task_detail":"观看视频后发表感悟","task_url":"mp4"},
{"task_name":"预习","task_time":"2020年4月15日 16点15分","task_detail":"预习下一章的知识","task_url":"text"},
{"task_name":"观看视频","task_time":"2020年4月15日 16点15分","task_detail":"观看视频后发表感悟","task_url":"mp4"},
{"task_name":"预习","task_time":"2020年4月15日 16点15分","task_detail":"预习下一章的知识","task_url":"text"},
{"task_name":"观看视频","task_time":"2020年4月15日 16点15分","task_detail":"观看视频后发表感悟","task_url":"mp4"}
]}



{"status":"ok",
"query":"软件工程任务1",
"comments":[
{"user_name":"小明","comment_time":"2020年4月15日 16点15分","comment_detail":"哈啊啊啊啊啊"},
{"user_name":"小黑","comment_time":"2020年4月15日 16点15分","comment_detail":"激发昆仑山搭街坊立"},{"user_name":"小明","comment_time":"2020年4月15日 16点15分","comment_detail":"大商股份"},{"user_name":"小明","comment_time":"2020年4月15日 16点15分","comment_detail":"户籍改革和"},{"user_name":"小明","comment_time":"2020年4月15日 16点15分","comment_detail":"从v不能把v从v不能"},{"user_name":"小明","comment_time":"2020年4月15日 16点15分","comment_detail":"野兔液体"},{"user_name":"小明","comment_time":"2020年4月15日 16点15分","comment_detail":"告诉对方嘎达"},{"user_name":"小明","comment_time":"2020年4月15日 16点15分","comment_detail":"啊梵蒂冈梵蒂冈士大夫"}
]}

