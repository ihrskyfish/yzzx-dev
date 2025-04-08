package com.wocai.platform.common.vo;

import com.wocai.platform.common.constant.CommonConstant;
import com.wocai.platform.common.exception.BizException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 *   接口返回数据格式
 * @date  2019年1月19日
 */
@Slf4j
@Data
@ApiModel(value="接口返回对象", description="接口返回对象")
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	@ApiModelProperty(value = "成功标志")
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	@ApiModelProperty(value = "返回处理消息")
	private String message = "操作成功！";

	/**
	 * 返回代码
	 */
	@ApiModelProperty(value = "返回代码")
	private Integer code = 0;
	
	/**
	 * 返回数据对象 data
	 */
	@ApiModelProperty(value = "返回数据对象")
	private T result;
	
	/**
	 * 时间戳
	 */
	@ApiModelProperty(value = "时间戳")
	private long timestamp = System.currentTimeMillis();

	public Result() {
		
	}
	
	public Result<T> error500(String message) {
		this.message = message;
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
		return this;
	}

	public Result<T> error500(String message, Exception e) {
		if(e instanceof BizException) {
			this.message = e.getMessage();
			// 打印异常出错位置
			log.error("msg:{}\n path:{}", e.getMessage(),e.getStackTrace()[0].toString());
		} else {
			this.message = message;
			log.error(e.getMessage(), e);
		}
		this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
		this.success = false;
		return this;
	}
	
	public Result<T> success(String message) {
		this.message = message;
		this.code = CommonConstant.SC_OK_200;
		this.success = true;
		return this;
	}
	public Result<T> success(T result,String message) {
		this.message = message;
		this.code = CommonConstant.SC_OK_200;
		this.success = true;
		this.result = result;
		return this;
	}
	
	public static Result<Object> ok() {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage("成功");
		return r;
	}
	
	public static Result<Object> ok(String msg) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setMessage(msg);
		return r;
	}
	
	public static Result<Object> ok(Object data) {
		Result<Object> r = new Result<Object>();
		r.setSuccess(true);
		r.setCode(CommonConstant.SC_OK_200);
		r.setResult(data);
		return r;
	}
	
	public static Result<Object> error(String msg) {
		return error(CommonConstant.SC_INTERNAL_SERVER_ERROR_500, msg);
	}
	
	public static Result<Object> error(int code, String msg) {
		Result<Object> r = new Result<Object>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}
	
	/**
	 * 无权限访问返回结果
	 */
	public static Result<Object> noauth(String msg) {
		return error(CommonConstant.SC_JEECG_NO_AUTHZ, msg);
	}



	public static Result toSuccess() {
		return toSuccess(null, "成功");
	}

	public static Result toSuccess(Object o) {
		return toSuccess(o, "成功");
	}

	public static Result toSuccess(Object o, String message) {
		Result<Object> result = new Result<>();
		result.setMessage(message);
		result.setCode(CommonConstant.SC_OK_200);
		result.setSuccess(true);
		result.setResult(o);
		return result;
	}

	public static Result toError() {
		return toSuccess(null, "失败");
	}

	public static Result toError(Object o) {
		return toError(o, "失败");
	}

	public static Result toError(Object o, String message) {
		Result<Object> result = new Result<>();
		result.setMessage(message);
		result.setCode(400);
		result.setSuccess(false);
		result.setResult(o);
		return result;
	}
}