package com.micro.web.entity.factory;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.web.common.json.AppletEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "厂房信息",value = "FactoryInfo")
public class FactoryInfo implements   AppletEntity {

    @ApiModelProperty(value = "ID")
    private  long id;

    @ApiModelProperty(value = "小程序用户唯一标识",notes = "小程序用户唯一标识")
    private String openId;

    @ApiModelProperty(value = "厂房名字",notes = "厂房名字",required = true)
    private String factoryName;

    @ApiModelProperty(value = "厂房大小",notes = "厂房大小 ㎡",required = true)
    private int factorySize;

    @ApiModelProperty(value = "厂房描述",notes = "厂房描述",required = true)
    private String factoryDesc;

    @ApiModelProperty(value = "联系方式",notes = "联系方式")
    private long phone;

    @ApiModelProperty(value = "备注",notes = "备注")
    private String remarks;

    @ApiModelProperty(value = "Image",notes = "Image")
    private String imagePath;


    @ApiModelProperty(value = "更新时间",notes = "更新时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
    private String updateTime;

    @ApiModelProperty(value = "创建时间",notes = "创建时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
    private String createTime;


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public int getFactorySize() {
        return factorySize;
    }

    public void setFactorySize(int factorySize) {
        this.factorySize = factorySize;
    }

    public String getFactoryDesc() {
        return factoryDesc;
    }

    public void setFactoryDesc(String factoryDesc) {
        this.factoryDesc = factoryDesc;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
