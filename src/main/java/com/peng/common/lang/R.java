package com.peng.common.lang;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;

/**
 * 返回前端 数据封闭类
 */
public class R implements Serializable {
    private Integer code;//状态码
    private String msg;//信息
    private Object data;//数据
    private Long total; // 分页信息：总条数

    public R() {
    }

    public R(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        if (data instanceof Page<?>) {
            Page<?> page = (Page<?>) data;
            this.total = page.getTotal();
            this.data = page.getRecords();
        } else {
            this.data = data;
        }
    }

    public static R ok() {
        return new R(Constants.OK_CODE, Constants.OK_MSG, null);
    }

    public static R ok(Object data) {
        return new R(Constants.OK_CODE, Constants.OK_MSG, data);
    }

    public static R ok(String msg, Object data) {
        return new R(Constants.OK_CODE, msg, data);
    }

    public static R fail(String msg) {
        return new R(Constants.FAIL_CODE, msg, null);
    }

    public static R fail(int errorCode, String msg) {
        return new R(errorCode, msg, null);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public Long getTotal() {
        return total;
    }

    public R setTotal(Long total) {
        this.total = total;
        return this;
    }

}
