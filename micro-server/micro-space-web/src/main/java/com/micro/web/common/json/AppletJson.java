package com.micro.web.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * @desc 小程序通用数据结构
 * @author  liuhui
 * @date
 */
@ApiModel(description = "小程序通用数据结构",value = "AppletJson")
public class AppletJson {
    protected static JsonMapper mapper = new JsonMapper(JsonInclude.Include.ALWAYS);

    /**
     * 会话ID
     */
    @ApiModelProperty(value = "会话ID",notes = "会话ID",required = true)
    private String sessionId = "";
    /**
     * 请求描述
     */
    @ApiModelProperty(value = "请求描述",notes = "请求描述" )
    private String reqDesc = "";
    /**
     * applet 请求参数
     */
    @ApiModelProperty(value = "请求参数",notes = "请求参数" )
    private String paras;

    @ApiModelProperty(value = "请求参数",notes = "请求参数" )
    private   AppletEntity  paraObj;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getReqDesc() {
        return reqDesc;
    }

    public void setReqDesc(String reqDesc) {
        this.reqDesc = reqDesc;
    }

    public String getParas() {
        return paras;
    }

    public void setParas(String paras) {
        this.paras = paras;
    }

    public AppletEntity getParaObj() {
        return paraObj;
    }

    public void setParaObj(AppletEntity paraObj) {
        this.paraObj = paraObj;
    }

    public <T extends  AppletEntity> T getParaObj(Class<T> cls) {
        if(StringUtils.isNotBlank(paras)){
            T obj = mapper.fromJson(paras, cls);
            this.paraObj = obj;
            return obj;
        }
        return null;
    }
}
