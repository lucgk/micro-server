CREATE TABLE userInfo (
 id   INTEGER  PRIMARY KEY autoincrement  default '唯一标识',
 openId   varchar(32) NOT NULL default '小程序用户唯一标识',
 name  TEXT(32)  default '姓名',
 birthday   varchar(32)   default '出生日期',
 gender   INTEGER  default '性别 0 男  1 女',
 phone   INTEGER default '电话号码',
 weChat   varchar(32) default '微信号',
 tim   varchar(32) default 'tim 或 QQ',
 remarks   TEXT default '备注',
 create_time   DATETIME default '创建时间',
 update_time   DATETIME default '更新时间'
);

CREATE TABLE factoryInfo (
 id   INTEGER  PRIMARY KEY autoincrement  default '唯一标识',
 openId   varchar(32) NOT NULL default '小程序用户唯一标识',
 factoryName  varchar(32)  default '厂房名字',
 factorySize   INTEGER   default '厂房大小',
 factoryDesc   TEXT  default '厂房描述',
 phone   INTEGER default '联系方式',
 remarks   TEXT default '备注',
 createTime   DATETIME default '创建时间',
 updateTime   DATETIME default '更新时间'
);

CREATE TABLE factoryImage (
 id   INTEGER  PRIMARY KEY autoincrement  default '唯一标识',
 factoryId  varchar(32)  default '厂房标识',
 imagePath varchar(255) default '图片路径',
 isCore    char(1)    default '是否为主图，1 是，0 否',
 createTime   DATETIME default '创建时间',
 updateTime   DATETIME default '更新时间'
);