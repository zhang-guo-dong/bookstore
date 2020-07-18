package com.mtw.common;

public class Result<T> 
{
	private T obj;
	private ResultCode code;
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	public ResultCode getCode() {
		return code;
	}
	public void setCode(ResultCode code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Result [obj=" + obj + ", code=" + code + "]";
	}
	public Result(T obj, ResultCode code) {
		super();
		this.obj = obj;
		this.code = code;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
