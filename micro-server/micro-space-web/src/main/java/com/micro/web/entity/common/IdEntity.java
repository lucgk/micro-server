package com.micro.web.entity.common;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public abstract class IdEntity implements Serializable {


    @ApiModelProperty(value = "ID")
    protected  long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
