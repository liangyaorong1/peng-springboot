package com.peng.common.lang;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author 如果加该注解的字段为null, 那么就不序列化这个字段了。
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result ok(){
        return new Result(Constants.OK_CODE, Constants.OK_MSG,null);
    }
    public static Result ok(Object data){
        return new Result(Constants.OK_CODE, Constants.OK_MSG,data);
    }
    public static Result ok(String msg, Object data){
        return new Result(Constants.OK_CODE, msg,data);
    }

    public static Result fail(String msg){
        return new Result(Constants.FAIL_CODE, msg,null);
    }

    public static Result fail(int code, String msg){
        return new Result(code, msg,null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
