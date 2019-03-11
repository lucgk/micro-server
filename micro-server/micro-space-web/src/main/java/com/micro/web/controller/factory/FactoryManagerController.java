package com.micro.web.controller.factory;

import com.micro.web.common.constants.NoticeMsgConstants;
import com.micro.web.common.utils.RetJsonMsg;
import com.micro.web.controller.BaseController;
import com.micro.web.entity.factory.FactoryInfo;
import com.micro.web.entity.personal.UserInfo;
import com.micro.web.service.factory.FactoryManagerService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "FactoryManagerController",description = "厂房信息管理 Factory  Restful API")
@RequestMapping("/factorymanager")
@RestController
public class FactoryManagerController extends BaseController {

    private final Logger logger  = LoggerFactory.getLogger(FactoryManagerController.class);

    @Autowired
    FactoryManagerService factoryManagerService;


    @ApiOperation(value = "保存厂房信息",notes = "保存厂房信息 - /factorymanager/saveFactoryInfo" ,response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/saveFactoryInfo",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg saveFactoryInfo(@ApiParam(value = "factoryInfo",required = true) FactoryInfo factoryInfo){
        logger.info("FactoryManagerController saveFactoryInfo start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            factoryManagerService.saveFactoryInfo(factoryInfo);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("FactoryManagerController saveFactoryInfo end............................");
        return retJson;
    }

    @ApiOperation(value = "删除厂房信息",notes = "删除厂房信息 - /factorymanager/deleteFactoryInfo" ,response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="厂房ID", required=true, paramType="query", dataType="int")
    })
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/deleteFactoryInfo",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg deleteFactoryInfo(@RequestParam(value = "id" ,required = true) int id){
        logger.info("FactoryManagerController deleteFactoryInfo start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            factoryManagerService.deleteFactoryInfo(id);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("FactoryManagerController deleteFactoryInfo end............................");
        return retJson;
    }

    @ApiOperation(value = "查询厂房信息",notes = "查询厂房信息 - /factorymanager/queryAllFactoryInfo" ,response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/queryAllFactoryInfo",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg queryAllFactoryInfo(){
        logger.info("FactoryManagerController deleteFactoryInfo start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            List<FactoryInfo> facts = factoryManagerService.queryAllFactoryInfo();
            retJson.setData(facts);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("FactoryManagerController deleteFactoryInfo end............................");
        return retJson;
    }
}
