## 部门表
CREATE TABLE IF NOT EXISTS  `department` (
  `departmentid` VARCHAR(255) NOT NULL COMMENT '部门id',
  `name` VARCHAR(255) NOT NULL COMMENT '部门名称',
  `aliasname` VARCHAR(255) DEFAULT NULL COMMENT '别名',
  `note` VARCHAR(255) DEFAULT NULL,
  `parentid` VARCHAR(255) DEFAULT NULL COMMENT '上级部门id',
  PRIMARY KEY (`departmentid`),
  KEY `parentid` (`parentid`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`parentid`) REFERENCES `department` (`departmentid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



create table if not exists `user` (
  `guid` varchar(32) not null,
  `account` varchar(32) not null,
  `password` varchar(100) default null,
  `name` varchar(32) not null,
  `telephone` varchar(32) default null,
  `email` varchar(32) default null,
  `fax` varchar(60) default null,
  `disabled` bit(1) default null comment '是否启用',
  `lastlogintime` datetime default null,
  `logincount` int default null,
  `isthreepower` int default null,
  `mobile` varchar(30) default null,
  `createtime` varchar(30) default null,
  `createuserid` varchar(50) default null,
  `note` varchar(255) default null,
  `identities` varchar(255) default null,
  `code` varchar(255) default null,
  `departmentid` varchar(255) default null,
  `department` varchar(255) default null,
  primary key (`guid`),
  key `fk6a68e081eb9b3d8` (`departmentid`),
  key `fk6a68e08893d333d` (`department`),
  constraint `fk6a68e08893d333d` foreign key (`department`) references `department` (`departmentid`),
  constraint `fk6a68e081eb9b3d8` foreign key (`departmentid`) references `department` (`departmentid`)
) engine=innodb default charset=utf8;

create table if not exists `role` (
  `guid` varchar(32) not null,
  `rolename` varchar(32) not null,
  `note` varchar(255) default null,
  `createtime` varchar(19) default null,
  `createuserid` varchar(100) default null,
  `issystemrole` bit(1) default null comment '是否是admin用户',
  `enable` bit(1) not null comment '是否启用',
  primary key (`guid`)
) engine=innodb default charset=utf8;



create table if not exists `userrole` (
  `roleid` varchar(32) not null,
  `userid` varchar(32) not null,
  primary key (`userid`,`roleid`),
) engine=innodb default charset=utf8;



create table if not exists `resource` (
  `guid` varchar(255) not null,
  `funcname` varchar(255) not null,
  `funcname_en` varchar(255) not null,
  `parentid` varchar(255) not null,
  `typeid` int(11) not null comment '菜单类别（顶级菜单、菜单、连接、按钮）',
  `target` varchar(300) default null,
  `url` varchar(300) default null,
  `title` varchar(300) default null,
  `icon` varchar(300) default null,
  `enable` bit(1) default null,
  `sort` int(11) default null,
  primary key (`guid`)
) engine=innodb default charset=utf8;


 create table if not exists `roleresource` (
  `roleid` varchar(32) not null,
  `resourceid` varchar(255) not null,
) engine=innodb default charset=utf8;


