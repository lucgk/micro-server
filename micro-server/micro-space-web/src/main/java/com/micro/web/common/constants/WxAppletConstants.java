package com.micro.web.common.constants;

public interface WxAppletConstants {

    /**
     * 登陆认证请求参数
     */
    /**小程序 appId*/
    public static final String WX_LOGIN_APPID = "appId";
    /**小程序 appSecret*/
    public static final String WX_LOGIN_SECRET = "secret";
    /**登录时获取的 code*/
    public static final String WX_LOGIN_JSCODE = "js_code";
    /**授权类型*/
    public static final String WX_LOGIN_GRANTTYPE = "grant_type";
    public static final String WX_LOGIN_GRANTTYPE_CODE = "authorization_code";


    /**会话密钥*/
    public static final String WX_LOGIN_RSP_SESSIONKEY = "session_key";
    /**用户唯一标识*/
    public static final String WX_LOGIN_RSP_OPENID = "openid";

    public static final String WX_CACHE_SESSION = "weChatSession";

}
