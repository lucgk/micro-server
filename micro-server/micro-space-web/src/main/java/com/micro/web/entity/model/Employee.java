package com.micro.web.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.web.common.config.ConfigLoadUtil;
import com.micro.web.common.constants.SysContants;
import com.micro.web.entity.common.PageEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class Employee extends PageEntity {

    private String name;

    private int age;

    private int sex;

    private String address;

    private String dbName;//数据库名字[将要从哪个库获取数据质量表信息]

    public String getDbName() {
        if(StringUtils.isBlank(dbName)){
            this.dbName = ConfigLoadUtil.getConfValueBykey(SysContants.EX_DBNAME_1);
        }
        return dbName;
    }

    public void setDbName(String dbName) {
        if(StringUtils.isBlank(dbName)){
            this.dbName = ConfigLoadUtil.getConfValueBykey(SysContants.EX_DBNAME_1);
            return;
        }
        this.dbName = dbName;
    }

    /**更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date updateTime;
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date createTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee() {

    }

    public Employee(String name, int age, int sex, String address) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }
}
