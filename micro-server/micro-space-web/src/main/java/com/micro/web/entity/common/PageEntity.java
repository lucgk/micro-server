package com.micro.web.entity.common;

/**
 * 统一定义分页查询.
 * 
 * @author well
 */
public abstract class PageEntity extends IdEntity {
	
	private static final long serialVersionUID = 1505723477750819671L;

	protected Long start = 0L;

	protected Long size = 10L;

	protected String orderBy = "create_time";

	protected String order = "DESC";
	
	// 是否使用模糊查询
	protected Integer useFuzzy;
	
	protected Integer page;
	
	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getUseFuzzy() {
		return useFuzzy;
	}

	public void setUseFuzzy(Integer useFuzzy) {
		this.useFuzzy = useFuzzy;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
}
