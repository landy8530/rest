package com.jersey.json.pojo;

/**
 * @author landyl
 * @create 2018-02-1:47 PM
 */
public class Response {
    private Integer code;
    private String msg;

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
}
