package com.micro.web.entity.common;

import io.swagger.annotations.ApiModelProperty;

public abstract class PageEntity extends IdEntity
{
  private static final long serialVersionUID = 1505723477750819671L;
  @ApiModelProperty(value="开始记录数", notes="开始记录数")
  protected Long start = Long.valueOf(0L);
  @ApiModelProperty(value="每页大小", notes="每页大小")
  protected Long size = Long.valueOf(10L);
  @ApiModelProperty(value="排序字段", notes="排序字段")
  protected String orderBy = "create_time";
  @ApiModelProperty(value="排序方式", notes="排序方式")
  protected String order = "DESC";
  @ApiModelProperty(value="是否使用模糊查询", notes="是否使用模糊查询")
  protected Integer useFuzzy;
  @ApiModelProperty(value="总共页数", notes="总共页数")
  protected Integer page;
  
  public Long getStart()
  {
    return this.start;
  }
  
  public void setStart(Long start)
  {
    this.start = start;
  }
  
  public Long getSize()
  {
    return this.size;
  }
  
  public void setSize(Long size)
  {
    this.size = size;
  }
  
  public String getOrderBy()
  {
    return this.orderBy;
  }
  
  public void setOrderBy(String orderBy)
  {
    this.orderBy = orderBy;
  }
  
  public String getOrder()
  {
    return this.order;
  }
  
  public void setOrder(String order)
  {
    this.order = order;
  }
  
  public Integer getUseFuzzy()
  {
    return this.useFuzzy;
  }
  
  public void setUseFuzzy(Integer useFuzzy)
  {
    this.useFuzzy = useFuzzy;
  }
  
  public Integer getPage()
  {
    return this.page;
  }
  
  public void setPage(Integer page)
  {
    this.page = page;
  }
}