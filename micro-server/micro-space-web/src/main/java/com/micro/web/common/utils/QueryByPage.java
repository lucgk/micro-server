package com.micro.web.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.micro.web.common.json.JsonMapper;
import com.micro.web.entity.common.PageEntity;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;

public class QueryByPage
{
  private static JsonMapper mapper = new JsonMapper(JsonInclude.Include.ALWAYS);
  @ApiParam(value="查询条件 JSON", required=true)
  private String sSearch;
  @ApiParam(value="第 N 条开始", required=false, defaultValue="0")
  private Long iDisplayStart;
  @ApiParam(value="每页数据条数", required=false, defaultValue="10")
  private Long iDisplayLength;
  @ApiParam(value="排序字段名词", required=false, defaultValue="create_time")
  private String iSortCol_0;
  @ApiParam(value="排序: DESC or ASC", required=false, defaultValue="DESC")
  private String sSortDir_0;
  
  public <T extends PageEntity> T build(Class<T> cls)
  {
    T ret = mapper.fromJson(this.sSearch, cls);
    if (ret == null) {
      return null;
    }
    if ((StringUtils.isNotBlank(this.iSortCol_0)) && (StringUtils.isNotBlank(this.iSortCol_0)))
    {
      ret.setOrder(this.sSortDir_0);
      ret.setOrderBy(this.iSortCol_0);
    }
    if (this.iDisplayStart == null) {
      this.iDisplayStart = Long.valueOf(0L);
    }
    ret.setStart(this.iDisplayStart);
    ret.setSize(this.iDisplayLength);
    
    return ret;
  }
  
  public String getsSearch()
  {
    return this.sSearch;
  }
  
  public void setsSearch(String sSearch)
  {
    this.sSearch = sSearch;
  }
  
  public Long getiDisplayStart()
  {
    return this.iDisplayStart;
  }
  
  public void setiDisplayStart(Long iDisplayStart)
  {
    this.iDisplayStart = iDisplayStart;
  }
  
  public Long getiDisplayLength()
  {
    return this.iDisplayLength;
  }
  
  public void setiDisplayLength(Long iDisplayLength)
  {
    this.iDisplayLength = iDisplayLength;
  }
  
  public String getiSortCol_0()
  {
    return this.iSortCol_0;
  }
  
  public void setiSortCol_0(String iSortCol_0)
  {
    this.iSortCol_0 = iSortCol_0;
  }
  
  public String getsSortDir_0()
  {
    return this.sSortDir_0;
  }
  
  public void setsSortDir_0(String sSortDir_0)
  {
    this.sSortDir_0 = sSortDir_0;
  }
}