create database  youhui_talk charset UTF8;

create table activity(
	id int(11) not null auto_increment,
	name varchar(30) not null,
	topic varchar(100),
	startTime TIMESTAMP,
	endTime TIMESTAMP,	
	primary key(id)
);

create table message(
	id int(11) not null auto_increment,
	generateTime TIMESTAMP not null,
	message varchar(100),
	ip varchar(15),
	platform varchar(20),
	app_version varchar(30),
	u_nick varchar(30), 
	uid varchar(20),
	aid int(11),
	primary key(id),
	constraint foreign key(aid) references activity(id),
	unique key date_uid (generateTime,uid)
);

insert into activity(name,topic,startTime,endTime) values('美食','十一黄金周','2014-09-15 15:00:00','2014-10-15 15:00:00');
insert into activity(name,topic,startTime,endTime) values('衣服','秋天秋风秋雨时','2014-10-20 15:00:00','2014-11-15 15:00:00');

insert into message(generateTime,message,nick,uid,aid) values(now(),'这是测试用的第一条消息',' 哈1',100,1);

-- 暂时去除外键
alter table youhui_talk.message 
	drop  FOREIGN KEY message_ibfk_1;

-- 存用户抽奖信息的表
create table user_win_info(
	uid varchar(11),
	win_type varchar(30),
	answer varchar(100)
);

insert into user_win_info(uid, win_type, answer) values('11','正确','这是一个谜');





