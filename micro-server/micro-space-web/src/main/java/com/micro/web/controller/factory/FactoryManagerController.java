package com.micro.web.controller.factory;

import com.micro.web.common.constants.NoticeMsgConstants;
import com.micro.web.common.utils.RetJsonMsg;
import com.micro.web.controller.BaseController;
import com.micro.web.entity.factory.FactoryImage;
import com.micro.web.entity.factory.FactoryInfo;
import com.micro.web.entity.personal.UserInfo;
import com.micro.web.service.factory.FactoryManagerService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        logger.info("FactoryManagerController queryAllFactoryInfo start............................");
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
        logger.info("FactoryManagerController queryAllFactoryInfo end............................");
        return retJson;
    }

    @ApiOperation(value = "图片信息上传",notes = "图片信息上传 - /factorymanager/uploadImage" ,response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg uploadImage(@RequestParam(value = "image" ,required = true)MultipartFile image,String factoryId,String isCore){
        logger.info("FactoryManagerController uploadImage start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            factoryManagerService.uploadImage(image,factoryId,isCore);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("FactoryManagerController uploadImage end............................");
        return retJson;
    }

    @ApiOperation(value = "图片信息批量上传",notes = "图片信息批量上传 - /factorymanager/batchUploadImages" ,response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/batchUploadImages",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg batchUploadImages(@RequestParam(value = "images" ,required = true)MultipartFile[] images,String factoryId){
        logger.info("FactoryManagerController batchUploadImages start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            factoryManagerService.batchUploadImages(images,factoryId);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("FactoryManagerController batchUploadImages end............................");
        return retJson;
    }

    @ApiOperation(value = "删除图片信息",notes = "删除图片信息 - /factorymanager/deleteImageInfoById" ,response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="图片关联ID", required=true, paramType="query", dataType="int")
    })
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/deleteImageInfoById",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg deleteImageInfoById(@RequestParam(value = "id" ,required = true) int id){
        logger.info("FactoryManagerController deleteImageInfoById start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            factoryManagerService.deleteImageInfoById(id);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("FactoryManagerController deleteImageInfoById end............................");
        return retJson;
    }

    @ApiOperation(value = "查询厂房图片信息",notes = "查询厂房图片信息 - /factorymanager/queryFactoryImages" ,response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name="factoryId", value="厂房ID", required=true, paramType="query", dataType="String"),
            @ApiImplicitParam(name="isCore", value="isCore",  paramType="query", dataType="String")
    })
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "成功返回公共接口类",response = RetJsonMsg.class)
    })
    @RequestMapping(value = "/queryFactoryImages",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public RetJsonMsg queryFactoryImages(@RequestParam(value = "factoryId" ,required = true) String factoryId,@RequestParam(value = "isCore" ,required = false) String isCore){
        logger.info("FactoryManagerController queryAllFactoryInfo start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            List<FactoryImage> facts = factoryManagerService.queryFactoryImages(factoryId,isCore);
            retJson.setData(facts);
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_SUCCESS);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION+e.getMessage());
            logger.error(NoticeMsgConstants.COMMON_RESULTMSG_EXCEPTION, e);
        }
        logger.info("FactoryManagerController queryFactoryImages end............................");
        return retJson;
    }

}
