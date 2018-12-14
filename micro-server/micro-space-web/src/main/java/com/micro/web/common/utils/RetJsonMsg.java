package com.micro.web.common.utils;

/**
 * @Description:
 * @Date 2017/5/25 17:49
 */
public class RetJsonMsg {

	private boolean isOk = true;
	private String code = "";
	private String msg = "";
	private long totalCount = 0;
	private long errorTotal = 0;
	private Object data;
	
	public boolean getIsOk() {
		return isOk;
	}

	public void setIsOk(boolean isOk) {
		this.isOk = isOk;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public RetJsonMsg setFail() {
		this.isOk = false;
		return this;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getErrorTotal() {
		return errorTotal;
	}

	public void setErrorTotal(long errorTotal) {
		this.errorTotal = errorTotal;
	}
	
}
