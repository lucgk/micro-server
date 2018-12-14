package com.micro.web.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.micro.web.common.config.excel.IExcel;
import com.micro.web.common.json.JsonMapper;
import com.micro.web.common.utils.RetJsonMsg;
import com.micro.web.controller.BaseController;
import com.micro.web.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/employ")
public class EmployeeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    private static JsonMapper mapper = new JsonMapper(JsonInclude.Include.ALWAYS);

    @ApiOperation(value = "保存")
    @RequestMapping("/saveEmployee")
    public RetJsonMsg saveEmployee(String params){
        logger.info("EmployeeController saveEmployee start............................");
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            employeeService.saveEmployee();
            if(StringUtils.isNotBlank(params)){
//              Employee sdt = mapper.fromJson(params, Employee.class);
                employeeService.saveEmployee();
                retJson.setMsg("保存集成模式表成功");
            }else{
                retJson.setMsg("获取表信息失败，保存失败！");
            }
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg("保存集成模式表失败!");
            logger.error("保存集成模式表失败", e);
        }
        logger.info("EmployeeController saveEmployee end............................");
        return retJson;
    }

    @ApiOperation(value = "excel导入")
    @RequestMapping(value = "excelImport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String excelImport(@RequestParam(value = "excelFile", required = true) MultipartFile excelFile, @RequestParam(value = "tableName", required = true) String tableName  ) {
        RetJsonMsg retJson = new RetJsonMsg();
        try {
            List<IExcel> sdtcExcels = employeeService.excelImport(excelFile,tableName);
            retJson.setData(sdtcExcels);
        } catch (Exception e) {
            retJson.setFail();
            retJson.setMsg("excel导入异常!"+e.getMessage());
            logger.error(" excel导入异常", e);
        }
        return mapper.toJson(retJson);
    }

    @ApiOperation(value = "Excel导出")
    @RequestMapping(value = "excelExport", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> exportExcel() {
        ResponseEntity<InputStreamResource> re = null;
        try {
            File file = employeeService.exportExcel();
             re  = buildResponseEntity(file);
        } catch ( Exception e) {
            logger.error("Excel导出失败！", e);
        }
        return re;
    }


}
