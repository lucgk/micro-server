package com.micro.web.service.wxapplet;

import com.google.common.collect.Maps;
import com.micro.web.common.constants.WxAppletConstants;
import com.micro.web.common.exception.CommonException;
import com.micro.web.common.json.AppletJson;
import com.micro.web.common.json.FastJsonUtil;
import com.micro.web.common.utils.EHCacheUtil;
import com.micro.web.common.utils.HttpsUtils;
import com.micro.web.common.utils.MD5Utils;
import com.micro.web.dao.personal.UserInfoDao;
import com.micro.web.entity.personal.UserInfo;
import com.micro.web.service.personal.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;

@Service
@PropertySource(value = "classpath:/wx/wx.properties")
@Configuration
public class WxCertificationService {

    private static final Logger logger = LoggerFactory.getLogger(WxCertificationService.class);

    @Value("${micro.wx.wxCodeHost}")
    private String wxCodeHost;

    @Value("${micro.wx.appId}")
    private String appId;

    @Value("${micro.wx.secret}")
    private String secret;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoDao userInfoDao;




    /**
     * 通过凭证code登录
     * @param code
     */
    public String loginByCode(String code) throws Exception{
        logger.info(" wxCodeHost : " + wxCodeHost +" appId : " + appId +" secret : " + secret );
        HashMap<String,String> para = Maps.newHashMap();
        para.put(WxAppletConstants.WX_LOGIN_APPID,appId);
        para.put(WxAppletConstants.WX_LOGIN_SECRET,secret);
        para.put(WxAppletConstants.WX_LOGIN_JSCODE, code);
        para.put(WxAppletConstants.WX_LOGIN_GRANTTYPE,WxAppletConstants.WX_LOGIN_GRANTTYPE_CODE);
        String rs = HttpsUtils.sendHttpsPost(this.wxCodeHost, para);
        HashMap<String, String> rsMap = FastJsonUtil.json2Map(rs);
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId(rsMap.get(WxAppletConstants.WX_LOGIN_RSP_OPENID));
        userInfoService.saveOrUpdateByOpenId(userInfo);
        String sessionId = MD5Utils.encryption(rsMap.get(WxAppletConstants.WX_LOGIN_RSP_OPENID));
//        saveToCache(sessionId,rsMap);
        EHCacheUtil.put(WxAppletConstants.WX_CACHE_SESSION,sessionId,rsMap);
        return sessionId;
    }


    /**
     * @desc  注 : 同一bean 内调用不起作用
     * @param openId  md5(openId)
     * @param sessionMap
     * @return
     */
    @Cacheable(value = {"weChatSession"},key = "#openId")
    public  HashMap saveToCache(String openId,HashMap sessionMap){
        return sessionMap;
    }

    /**
     *  @desc  注 : 同一bean 内调用不起作用
     * @param openId
     * @return
     */
    @Cacheable(value = {"weChatSession"},key = "#openId")
    public HashMap getByKey(String openId){
        HashMap sessionMap = Maps.newHashMap();
        return  sessionMap;
    }

    /**
     * 微信请求code
     * @param sessionId
     * @return
     */
    public UserInfo queryUserInfoBySessionId(String sessionId) throws Exception {
        HashMap sessionMap = (HashMap) EHCacheUtil.get(WxAppletConstants.WX_CACHE_SESSION,sessionId);
        if(CollectionUtils.isEmpty(sessionMap)){
            throw new CommonException("当前用户未登录");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId(sessionMap.get(WxAppletConstants.WX_LOGIN_RSP_OPENID).toString());
        return  userInfoDao.queryUserInfoByOpenId(userInfo);
    }

    public void saveUserInfo(AppletJson appletJson) throws CommonException {
        HashMap sessionMap = (HashMap) EHCacheUtil.get(WxAppletConstants.WX_CACHE_SESSION,appletJson.getSessionId());
        if(CollectionUtils.isEmpty(sessionMap)){
            throw new CommonException("当前用户未登录");
        }
        UserInfo userInfo = null;
        if(!StringUtils.isEmpty(appletJson.getParas())){
            userInfo = appletJson.getParaObj(UserInfo.class);
        }
        userInfo.setOpenId(sessionMap.get(WxAppletConstants.WX_LOGIN_RSP_OPENID).toString());
        UserInfo rs = userInfoDao.queryUserInfoByOpenId(userInfo);
        if(null==rs){//新增
            userInfoDao.insertUserInfo(userInfo);
        }else {//修改
            userInfoDao.updateUserInfoByOpenId(userInfo);
        }
    }
}
