package com.micro.web.service.sqlite;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.micro.web.common.config.ConfigLoadUtil;
import com.micro.web.common.constants.SysContants;
import com.micro.web.common.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SqliteService {

    @Autowired
    @Qualifier("sqliteJdbcTemplate")
    JdbcTemplate sqliteDataSource;


    /**
     * @desc 初始化sqlite 数据库
     *
     */
    public void initSqlite() throws CommonException {
        try{
            String sqliteUrl = ConfigLoadUtil.getConfValueBykey(SysContants.SQLITE_KEY_INITURL);
            String sqliteDriver = ConfigLoadUtil.getConfValueBykey(SysContants.SQLITE_KEY_DRIVER);
            sqliteDataSource.execute("create table Test ( id  long(11) PRIMARY key ,coldesc varchar(32))");
        }catch (Exception e){
            throw new CommonException("初始化数据库异常！"+e.getMessage(),e);
        }
    }

    public void executeSqliteScripte(String script) throws CommonException {
        try{
            sqliteDataSource.execute(script);
        }catch (Exception e){
            throw new CommonException("执行脚本异常！"+e.getMessage(),e);
        }

    }

    public List<Map<String, Object>> queryForObject(String script) throws CommonException {
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> map = Maps.newHashMap();
        try{
            list  = sqliteDataSource.queryForList(script);
             map = sqliteDataSource.queryForMap(script);
        }catch (Exception e){
            throw new CommonException("执行脚本异常！"+e.getMessage());
        }
        return list;
    }
}
