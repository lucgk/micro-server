package com.micro.web.entity.factory;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.micro.web.common.json.AppletEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "厂房图片信息",value = "FactoryInfo")
public class FactoryImage implements   AppletEntity {

    @ApiModelProperty(value = "ID")
    private  long id;

    @ApiModelProperty(value = "厂房标识",notes = "厂房标识",hidden = true)
    private String factoryId;

    @ApiModelProperty(value = "图片路径",notes = "图片路径",required = true)
    private String imagePath;

    @ApiModelProperty(value = "是否为主图，1 是，0 否",notes = "是否为主图，1 是，0 否",required = true)
    private String isCore;

    @ApiModelProperty(value = "更新时间",notes = "更新时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
    private String updateTime;

    @ApiModelProperty(value = "创建时间",notes = "创建时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
    private String createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public String getIsCore() {
        return isCore;
    }

    public void setIsCore(String isCore) {
        this.isCore = isCore;
    }
}
