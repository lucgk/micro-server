package com.micro.web.common.config.excel;

import com.micro.web.common.constants.SysContants;
import com.micro.web.common.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @desc excel 导入、导出 适配类
 * @author  liuhui
 * @date 2018-09-06
 */
public abstract class AbstractExcel implements IExcel{

    /**
     * excel导出处理接口
     */
    protected  ExcelExport expor;

    /**
     * excel导入处理接口
     */
    protected  ExcelImport impor;

    public AbstractExcel() {
    }


    public IExcel buildEntity(Row row, int rowIndex, HashMap<String,Object> paras) throws CommonException {
        if(null==impor){
            throw new CommonException("导入接口尚未定义或初始化,解析Excel文件失败！");
        }
        if(impor.doFilter(row, rowIndex, paras)){
            return impor.rowToIExcel(row, rowIndex, paras);
        }else {
            return null;
        }
    }

    public File buildExcelSheet(Workbook workbook , List<IExcel> datas,String fileName) throws CommonException {
        if(null==impor){
            throw new CommonException("导出接口尚未定义或初始化,解析Excel文件失败！");
        }
        Sheet sheet = workbook.createSheet("集成模板数据");
        expor.buildExcelHeader(sheet);
        expor.buildExcelContext(sheet,datas);
        return expor.buildExcelFile(workbook,sheet,fileName);
    }


    /**
     * @desc 导入接口抽象
     * @date 2018-09-06
     * @author  liuhui
     */
    public abstract  class ExcelImport{
        public ExcelImport() {
        }

        /**
         * 类型转换：StringCellValue  ---->  String
         * @param cell
         * @return
         */
        public  String tranStringValue(Cell cell){
            String celVal = "";
            if(null==cell){
                return celVal;
            }
            celVal = cell.getStringCellValue();
            if(StringUtils.isBlank(celVal)){
                return  celVal;
            }
            return celVal;
        }

        /**
         * 类型转换： NumericCellValue  ---->  String
         * @param cell
         * @param defaultVal
         * @return
         */
        public  int tranIntegerValue(Cell cell,int defaultVal){
            if(null==cell){
                return defaultVal;
            }
            double numVal = cell.getNumericCellValue();
            return (int)numVal;
        }

        /**
         * 类型转换： getNumericCellValue  ---->  String
         * @param cell
         * @param deafultVal
         * @return
         */
        public  String tranIntegerValue(Cell cell,String deafultVal){
            if(null==cell){
                return deafultVal;
            }
            double numVal = cell.getNumericCellValue();
            return (int)numVal+"";
        }

        /**
         * 数据过滤，不满足条件数据丢弃
         * @return false:数据丢弃
         */
        protected abstract boolean doFilter(Row row, int rowIndex, HashMap<String, Object> paras);

        /**
         * excel 行数据转java excel 实体
         * @param row
         * @param rowIndex
         * @param paras
         * @return
         */
        protected abstract IExcel rowToIExcel(Row row, int rowIndex, HashMap<String, Object> paras);

    }

    /**
     * @desc 导出接口抽象类
     * @date 2018-09-06
     * @author  liuhui
     */
    public abstract  class ExcelExport{

        public ExcelExport() {
        }

        /**
         * 构建Excel 表头
         * @param sheet
         * @return
         */
        public abstract void buildExcelHeader(Sheet sheet);


        /**
         * 构建表数据
         * @param sheet
         * @param datas
         */
        public abstract void buildExcelContext(Sheet sheet, List<IExcel> datas);

        /**
         * @param field
         * @return
         */
        public String tranStringValue(String field){
            return  StringUtils.isBlank(field)?"":field;
        }

        public int tranIntegerValue(Integer field){
            return   tranIntegerValue(field,0);
        }
        public int tranIntegerValue(Integer field,int defaultVal){
            return   null==field?defaultVal:field;
        }

        public int tranIntegerValue(String field){
            return   tranIntegerValue(field, 0);
        }

        public int tranIntegerValue(String field,int defaulVal){
            return   StringUtils.isBlank(field)?defaulVal:Integer.valueOf(field);
        }

        /**
         * 生成Excel 文件
         * @param workbook
         * @param sheet
         * @param fileName
         * @return
         * @throws CommonException
         */
        public File buildExcelFile(Workbook workbook,Sheet sheet,String fileName) throws CommonException {
            File dir = new File(SysContants.PROJECT_TEMP_DIR);
            File file = null;
            FileOutputStream fos = null;
            try {
                if(!dir.exists()||!dir.isDirectory()){
                    FileUtils.forceMkdir(dir);
                }
                file = new File(SysContants.PROJECT_TEMP_DIR+File.separator+fileName);
                fos = new FileOutputStream(file);
                workbook.write(fos);
                fos.flush();
            }catch (Exception e){
                throw new CommonException("生成excel 文件异常",e);
            }finally {
                if(null!=fos) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return file;
        }
    }
}
