package com.assignment.user.exception;

public enum StatusCode {
	
	SUCCESS(200),
	BAD_REQUEST(400),
	INVALID_INPUT_EXCEPTION(600),
	GENERIC_EXCEPTION(500);
	
	private int code;
	
	StatusCode(int code)
	{
		this.code = code;
	}
	
	public int getCode()
	{
	  return code;
	}

}
