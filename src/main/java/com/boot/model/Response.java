package com.boot.model;

public class Response<T> {
	private ResponseStatus status = ResponseStatus.OK;
	private T baseResponse;
	private String errorCode = "";
	private String errorDesc = "";

	public static enum ResponseStatus {
		OK, WITH_ERROR;
	}

	public T getBaseResponse() {
		return baseResponse;
	}

	public void setBaseResponse(T baseResponse) {
		this.baseResponse = baseResponse;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
}
