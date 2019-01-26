package com.micro.web.entity.excel;

import com.micro.web.common.config.ConfigLoadUtil;
import com.micro.web.common.config.excel.AbstractExcel;
import com.micro.web.common.config.excel.IExcel;
import com.micro.web.common.constants.SysConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.HashMap;
import java.util.List;

/**
 * @author liuhui
 * @date  2018-08-30
 * @desc   用于excel 导入 导出
 */
public class EmployeeExcel extends AbstractExcel {


    /**
     * 指定重第几行开始解析
     */
    private static int startRow = 1;

    private String name;

    private int age;

    private String sex;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String dbName;//数据库名字[将要从哪个库获取数据质量表信息]

    public String getDbName() {
        if(StringUtils.isBlank(dbName)){
            this.dbName = ConfigLoadUtil.getConfValueBykey(SysConstants.EX_DBNAME_1);
        }
        return dbName;
    }

    public void setDbName(String dbName) {
        if(StringUtils.isBlank(dbName)){
            this.dbName = ConfigLoadUtil.getConfValueBykey(SysConstants.EX_DBNAME_1);
            return;
        }
        this.dbName = dbName;
    }


    public EmployeeExcel() {
    }

    public EmployeeExcel(String name, int age, String sex, String address) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
    }

    /**
     *  初始化excel 解析接口
     * @param init  true:初始化  false:不初始化
     */
    public EmployeeExcel(boolean init){
        if(init){
            this.impor = new SDTCExcelImport();
            this.expor = new SDTCExcelExport();
        }
    }

    class SDTCExcelImport extends AbstractExcel.ExcelImport{
        @Override
        protected boolean doFilter(Row row, int rowIndex, HashMap<String, Object> paras) {
            if(rowIndex<EmployeeExcel.startRow) return false;
          /*  if(null==row.getCell(0)|| StringUtils.isBlank(row.getCell(0).getStringCellValue())||!paras.get(ExcelFileNameConstants.FILTER_PARA_TABLENAME).toString().equals(row.getCell(0).getStringCellValue())){
                return false;//过滤
            }*/
            return true;
        }

        @Override
        protected IExcel rowToIExcel(Row row, int rowIndex, HashMap<String, Object> paras) {
            String name =  this.tranStringValue(row.getCell(0));
            int age = this.tranIntegerValue(row.getCell(1),0);
            String sex = this.tranStringValue(row.getCell(2));
            String address = this.tranStringValue(row.getCell(3));
            return new EmployeeExcel(name,age,sex,address);
        }
    }

    class SDTCExcelExport extends AbstractExcel.ExcelExport{

        @Override
        public void buildExcelHeader(Sheet sheet) {
            String[] headers = {"姓名","年龄","性别","地址" };
            XSSFRow row = (XSSFRow) sheet.createRow(0);
            for(int i=0;i<headers.length;i++){
                XSSFCell cell = row.createCell(i);
                XSSFRichTextString text = new XSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
        }

        @Override
        public void buildExcelContext(Sheet sheet, List<IExcel> datas) {
            int rowNum = 1;
            for(IExcel data:datas){
                EmployeeExcel ee = (EmployeeExcel) data;
                XSSFRow  rowT = (XSSFRow) sheet.createRow(rowNum);
                rowT.createCell(0).setCellValue(this.tranStringValue(ee.getName()));
                rowT.createCell(1).setCellValue(this.tranIntegerValue(ee.getAge()));
                rowT.createCell(2).setCellValue(this.tranStringValue(ee.getSex()));
                rowT.createCell(3).setCellValue(this.tranStringValue(ee.getAddress()));
                rowNum++;
            }
        }
    }
}
