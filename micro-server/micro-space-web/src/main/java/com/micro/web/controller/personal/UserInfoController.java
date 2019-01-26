package com.micro.web.controller.personal;

import com.micro.web.common.constants.NoticeMsgConstants;
import com.micro.web.common.exception.CommonException;
import com.micro.web.common.utils.QueryByPage;
import com.micro.web.common.utils.RetJsonMsg;
import com.micro.web.controller.BaseController;
import com.micro.web.entity.model.Employee;
import com.micro.web.entity.personal.UserInfo;
import com.micro.web.entity.personal.UserInfoQuery;
import com.micro.web.service.personal.UserInfoService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @desc 个人信息
 * @date 2019-01-26
 * @author lh
 */
@Api(tags = "UserInfoController",description = "用户信息管理 userInfo Restful API")
@RequestMapping("/userInfo")
@RestController
public class UserInfoController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "保存用户信息",notes = "保存用户信息 - /userInfo/saveUserInfo" ,response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/saveUserInfo",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg saveUserInfo(@ApiParam(value = "userInfo",required = true) UserInfo userInfo){
        logger.info("UserInfoController saveUserInfo start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            userInfoService.saveUserInfo(userInfo);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("UserInfoController saveUserInfo end............................");
        return retJson;
    }

    @ApiOperation(value = "分页查询用户信息",notes = "分页查询用户信息 - /userInfo/pageQuery" ,response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/pageQuery",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg pageQuery(@ModelAttribute(value = "queryByPage") QueryByPage queryByPage){
        logger.info("UserInfoController pageQuery start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            UserInfoQuery para = queryByPage.build(UserInfoQuery.class);
            List<UserInfo> datas =  userInfoService.pageQuery(para);
            long count = userInfoService.queryTotalCount(para);
            retJson.setData(datas);
            retJson.setTotalCount(count);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION);
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("UserInfoController pageQuery end............................");
        return retJson;
    }

    @ApiOperation(value = "根据id获取用户信息",notes = "根据id获取用户信息 - /userInfo/queryUserInfoById")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="用户ID", required=true, paramType="query", dataType="int")
    })
    @RequestMapping(value = "/queryUserInfoById",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "请求成功",response = RetJsonMsg.class)
    })
    public String queryUserInfoById(@RequestParam(value = "id" ,required = true) int id){
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            UserInfo info =  userInfoService.queryUserInfoById(id);
            retJson.setData(info);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        return  mapper.toJson(retJson);
    }



}
