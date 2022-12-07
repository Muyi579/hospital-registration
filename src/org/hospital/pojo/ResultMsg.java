package org.hospital.pojo;

import java.io.Serializable;

/**
 * @author 吕牧
 * @version 1.0
 * @date 2022/12/4 17:18
 * @Description 返回结果实体类
 */
public class ResultMsg implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;
    private Object data;
    private String msg;

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public ResultMsg() {
    }

    public ResultMsg(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static ResultMsg create(int code, Object data, String msg) {
        return new ResultMsg(code, data, msg);
    }

    public static ResultMsg create(int code, Object data) {
        return new ResultMsg(code, data, null);
    }

    public static ResultMsg create(int code) {
        return new ResultMsg(code, null, null);
    }

    public ResultMsg setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultMsg setData(Object data) {
        this.data = data;
        return this;
    }

    public ResultMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static int SUCCESS = 200;
    public static int ERROR = 500;
    public static int NO_API_ERROR = 1002;

}
