package com.micro.web.entity.personal;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.web.entity.common.PageEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "用户信息",value = "UserInfo")
public class UserInfoQuery extends PageEntity {

    @ApiModelProperty(value = "姓名",notes = "姓名全拼",required = true)
    private String name;

    @ApiModelProperty(value = "生日",notes = "出生日期",required = true)
    private String birthday;

    @ApiModelProperty(value = "性别",notes = "0 ：男 ，1 ：女",required = true)
    private int gender;


    @ApiModelProperty(value = "手机号",notes = "手机号")
    private long phone;

    @ApiModelProperty(value = "微信号",notes = "微信号")
    private String weChat;

    @ApiModelProperty(value = "QQ/TIM",notes = "QQ/TIM")
    private String tim;

    @ApiModelProperty(value = "备注",notes = "备注")
    private String remarks;


    @ApiModelProperty(value = "更新时间",notes = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间",notes = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
    private Date createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

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
}
