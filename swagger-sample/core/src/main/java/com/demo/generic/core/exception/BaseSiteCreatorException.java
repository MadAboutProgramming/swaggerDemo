package com.demo.generic.core.exception;

public class BaseSiteCreatorException extends Exception {

	private static final long serialVersionUID = 1L;
	private final ErrorCodes.Exception_Codes errCode;
	private final transient Object[] params;

	public BaseSiteCreatorException(ErrorCodes.Exception_Codes errCode1) {
		super(errCode1.getMessageId());
		this.errCode = errCode1;
		this.params = new Object[] {};
	}

	public BaseSiteCreatorException(ErrorCodes.Exception_Codes errCode1, Object... params1) {
		super(errCode1.getMessageId());
		this.errCode = errCode1;
		this.params = params1;
	}

	public ErrorCodes.Exception_Codes getErrCode() {
		return errCode;
	}

	public Object[] getParams() {
		return params;
	}
}
