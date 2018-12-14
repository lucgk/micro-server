package com.micro.web.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.micro.web.common.config.excel.ExcelUtils;
import com.micro.web.common.config.excel.IExcel;
import com.micro.web.common.constants.ExcelFileNameConstants;
import com.micro.web.common.enums.SexEnum;
import com.micro.web.common.exception.CommonException;
import com.micro.web.dao.model.EmployeeDao;
import com.micro.web.entity.excel.EmployeeExcel;
import com.micro.web.entity.model.Employee;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    public void saveEmployee() {
        Employee ee = new Employee("zs",12, SexEnum.MALE.getCode(),"深圳");
        employeeDao.insertEmployee(ee);
    }

    public List<IExcel> excelImport(MultipartFile excelFile, String tableName) throws CommonException {
        List<IExcel> clns = Lists.newArrayList();
        try {
            HashMap<String,Object> para = Maps.newHashMap();
            para.put(ExcelFileNameConstants.FILTER_PARA_TABLENAME,tableName);
            clns =  ExcelUtils.getEntityList(excelFile.getOriginalFilename(),excelFile.getInputStream(),new EmployeeExcel(true),para);
        } catch (IOException e) {
            throw new CommonException("根据excel构建表字段信息异常",e);
        }
        return clns;
    }

    public File exportExcel() throws Exception {
        HashMap<String,Object>  map = Maps.newHashMap();
        EmployeeExcel sdtcExcel = new EmployeeExcel(true);
        List<IExcel> sdts = employeeDao.getExcelDatas(sdtcExcel);
        Workbook workbook =  new XSSFWorkbook();
        File file = ExcelUtils.buildExcelSheet(workbook, new EmployeeExcel(true), sdts, ExcelFileNameConstants.EXCEL_FILENAME_EMPLOYEE);
        return  file;
    }
}
