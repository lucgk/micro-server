package com.micro.web.common.config.excel;

import com.micro.web.common.exception.CommonException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @desc Excel导入导出 接口
 * @author  liuhui
 * @date  2018-08-05
 */
public interface IExcel {

    /**
     * excel导入  入口（excel 数据转 java实体）
     * @param row
     * @param rowIndex
     * @param paras
     * @return
     */
    IExcel buildEntity(Row row, int rowIndex, HashMap<String, Object> paras) throws CommonException;

    /**
     * excel 导出 入口 （生成excel 文件）
     * @param workbook
     * @param datas
     * @return
     * @throws CommonException
     */
    File buildExcelSheet(Workbook workbook, List<IExcel> datas, String fileName) throws CommonException, CommonException;
}
