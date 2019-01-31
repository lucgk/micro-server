package com.micro.web.controller.wxapplet;

import com.micro.web.common.constants.NoticeMsgConstants;
import com.micro.web.common.json.AppletJson;
import com.micro.web.common.utils.RetJsonMsg;
import com.micro.web.controller.BaseController;
import com.micro.web.entity.personal.UserInfo;
import com.micro.web.service.wxapplet.WxCertificationService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc 小程序登录认证
 * @author liuhui
 * @date 2019-01-28
 */
@Api(tags = "WxCertificationController",description = "微信认证 wxCert Restful API")
@RequestMapping("/wxCert")
@RestController
public class WxCertificationController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(WxCertificationController.class);


    @Autowired
    private WxCertificationService wxCertificationService;


    @ApiOperation(value = "通过凭证code登录",notes = "通过凭证code登录 - /wxCert/loginByCode")
    @ApiImplicitParams({
            @ApiImplicitParam(name="code", value="用户code", required=true, paramType="query", dataType="String")
    })
    @RequestMapping(value = "/loginByCode",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "请求成功",response = RetJsonMsg.class)
    })
    public String loginByCode(String code, HttpServletRequest request){

        RetJsonMsg retJson = new RetJsonMsg();
        try {
            String sessionId  = wxCertificationService.loginByCode(code);
            retJson.setData(sessionId);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        return  mapper.toJson(retJson);
    }

    @ApiOperation(value = "根据sessionId获取用户信息",notes = "根据微信code获取用户信息 - /userInfo/queryUserInfoBySessionId")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sessionId", value="用户code", required=true, paramType="query", dataType="String")
    })
    @RequestMapping(value = "/queryUserInfoBySessionId",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "请求成功",response = RetJsonMsg.class)
    })
    public String queryUserInfoBySessionId(@RequestParam(value = "sessionId" ,required = true) String sessionId, HttpServletRequest request){
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            UserInfo info =  wxCertificationService.queryUserInfoBySessionId(sessionId);
            retJson.setData(info);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        return  mapper.toJson(retJson);
    }

    @ApiOperation(value = "保存用户信息",notes = "保存用户信息 - /userInfo/saveUserInfo" ,response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/saveUserInfo",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg saveUserInfo(@ApiParam(value = "userInfo",required = true) AppletJson appletJson){
        logger.info("WxCertificationController saveUserInfo start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            wxCertificationService.saveUserInfo(appletJson);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("WxCertificationController saveUserInfo end............................");
        return retJson;
    }
}
