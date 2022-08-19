package com.study.boke.exception;

public enum ReggieExceptionEnum {


    EXITED_NAME(10001,"已存在"),
    ERROR_PASSWORD(10002,"用户名或密码错误"),
    NEED_ADMIN(10004,"需要管理员权限"),
    DELETE_ERROR(10006,"无法删除!"),
    FREEZE_USER(10003,"用户名已被冻结");
    int code;

    String msg;

    ReggieExceptionEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
