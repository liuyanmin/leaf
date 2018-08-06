package com.lym.util;

import java.util.Map;

/**
 * 封装接口返回数据
 */
public class ResponseData {

	private String resultCode;
	private String resultMsg;
	private Map<String, Object> data;
	
	public ResponseData() {
	}
	
	public ResponseData(String resultCode) {
		this.resultCode = resultCode;
		this.resultMsg = Const.getMsg(resultCode);
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
		this.resultMsg = Const.getMsg(resultCode);
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
