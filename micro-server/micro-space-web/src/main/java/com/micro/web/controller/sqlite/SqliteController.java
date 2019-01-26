package com.micro.web.controller.sqlite;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.micro.web.common.exception.CommonException;
import com.micro.web.common.json.JsonMapper;
import com.micro.web.common.utils.RetJsonMsg;
import com.micro.web.controller.BaseController;
import com.micro.web.service.sqlite.SqliteService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Api(tags = "sqlite接口",description = "sqlite Restful API")
@RequestMapping("/sqlite")
@Controller
public class SqliteController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SqliteController.class);

    @Autowired
    SqliteService sqliteService ;


    @ApiOperation(value = "初始化sqlite数据库",notes = "initDB 接口描述")
    @RequestMapping(value = "/initDB",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "请求成功",response = RetJsonMsg.class)
    })
    @ResponseBody
    public RetJsonMsg initSqlite(){
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            sqliteService.initSqlite();
        } catch (CommonException e) {
            retJson.setFail();
            retJson.setMsg(e.getMessage());
            logger.error(e.getMessage(),e);
        }
        return  retJson;
    }

    @ApiOperation(value = "执行sql",notes = "executeSqliteScripte 接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name="script", value="sql脚本", required=true, paramType="query", dataType="String")
    })
    @RequestMapping(value = "/executeSqliteScripte",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "请求成功",response = RetJsonMsg.class)
    })
    @ResponseBody
    public String executeSqliteScripte(@RequestParam(value = "script" ,required = true) String script){
        RetJsonMsg retJson = new RetJsonMsg();
        try {
             sqliteService.executeSqliteScripte(script);
        } catch (CommonException e) {
            retJson.setFail();
            retJson.setMsg(e.getMessage());
            logger.error(e.getMessage(),e);
        }
        return  mapper.toJson(retJson);
    }

    @ApiOperation(value = "查询sql",notes = "executeSqliteScripte 接口描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name="script", value="sql脚本", required=true, paramType="query", dataType="String")
    })
    @RequestMapping(value = "/queryForObject",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200 ,message = "请求成功",response = RetJsonMsg.class)
    })
    @ResponseBody
    public String queryForObject(@RequestParam(value = "script" ,required = true) String script){
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            List<Map<String, Object>> rs =  sqliteService.queryForObject(script);
            retJson.setMsg("查询成功！");
            retJson.setData(rs);
        } catch (CommonException e) {
            retJson.setFail();
            retJson.setMsg(e.getMessage());
            logger.error(e.getMessage(),e);
        }
        return  mapper.toJson(retJson);
    }

}
