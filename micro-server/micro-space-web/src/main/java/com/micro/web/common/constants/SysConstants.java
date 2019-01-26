package com.micro.web.common.constants;

/**
 * 系统参数
 */
public interface SysConstants {


    /**Excel 文件生成路径*/
   public static final String  PROJECT_TEMP_DIR = "/tmp/fw/excel/";

    /**扩展数据库名*/
   public static final String  EX_DBNAME_1 = "";

    /**sqlite  driver */
    public static final String  SQLITE_KEY_DRIVER = "spring.datasource.hikari.sqlite.driver-class-name";

    /**sqlite 数据库初始化 url*/
    public static final String  SQLITE_KEY_INITURL = "spring.datasource.init.sqlite.url";

}
