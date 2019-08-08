package com.lc.vo;

public class ResultMsg {
    private String code;//1--成功，0--失败
    private String msg;
    private Object data;

    public ResultMsg success(Object list) {
        this.data = list;
        this.code = "1";
        this.msg = "成功";
        return this;
    }

    public ResultMsg success(String code, String msg, Object list) {
        this.data = list;
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResultMsg error(Object list) {
        this.data = list;
        this.code = "0";
        this.msg = "失败";
        return this;
    }
    public ResultMsg error(String msg) {
        this.code = "0";
        this.msg = msg;
        return this;
    }
    public ResultMsg error(String code, String msg, Object list) {
        this.data = list;
        this.code = code;
        this.msg = msg;
        return this;
    }

    public static ResultMsg successWithData(Object obj) {
        ResultMsg msg = new ResultMsg();
        msg.data = obj;
        msg.code = "1";
        msg.msg = "success";
        return msg;
    }

    public static ResultMsg successWithData(String code, String msg, Object obj) {
        ResultMsg re = new ResultMsg();
        re.data = obj;
        re.code = "1";
        re.msg = "success";
        return re;
    }

    public static ResultMsg errorWithData(Object obj) {
        ResultMsg msg = new ResultMsg();
        msg.data = obj;
        msg.code = "0";
        msg.msg = "error";
        return msg;
    }

    public static ResultMsg errorWithData(String code, String msg, Object obj) {
        ResultMsg re = new ResultMsg();
        re.data = obj;
        re.code = "0";
        re.msg = "error";
        return re;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
