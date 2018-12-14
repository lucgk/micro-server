package com.micro.web.common.config.excel;

import com.micro.web.common.exception.CommonException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author liuhui
 * @date 2018-08-30
 * @desc Excel处理工具
 */
public class ExcelUtils {

    private  static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);


    public static boolean isExcel2003(String filePath){
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath)
    {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 解析excel  获取导入数据
     * @param fileName  文件名
     * @param is    文件流
     * @param cls   excel文件映射类型
     * @param paras  过滤参数
     * @return
     * @throws CommonException
     */
    public static   List<IExcel> getEntityList(String fileName, InputStream is, IExcel cls, HashMap<String,Object> paras) throws CommonException {
        Workbook wb = null;
        List<IExcel> list = new ArrayList<IExcel>();
        try
        {
            if ( isExcel2007(fileName)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = new HSSFWorkbook(is);
            }
        }
        catch (IOException e)
        {
            throw new CommonException("解析excel异常 ",e);
        }

        Sheet sheet = wb.getSheetAt(0);//获取第一张表
        for (int i = 0; i <= sheet.getLastRowNum(); i++)
        {

            Row row = sheet.getRow(i);//获取索引为i的行，以0开始
            try {
                IExcel iExcel = cls.buildEntity(row,i,paras);
                if(null!=iExcel){
                    list.add(iExcel);
                }
            } catch (Exception e) {
                logger.error("构建字段列表异常 rowNum :"+i,e);
            }

        }
        return list;
    }


    /**
     * 根据数据构建excel 文件
     * @param workbook  excel 工作簿
     * @param cls  数据类型实体
     * @param datas  要转换的数据
     * @param fileName 生成的Excel文件名
     * @return  excel文件
     * @throws CommonException
     */
    public static File buildExcelSheet(Workbook workbook , IExcel cls, List<IExcel> datas,String fileName) throws CommonException {
        return cls.buildExcelSheet(workbook, datas,fileName);
    }


}
