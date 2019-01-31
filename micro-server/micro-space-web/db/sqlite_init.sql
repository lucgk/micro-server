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