package com.jersey.json.pojo;

/**
 * @author landyl
 * @date 2/23/2018 9:19 AM
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
