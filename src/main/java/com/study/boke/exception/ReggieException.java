package com.study.boke.exception;

public class ReggieException extends RuntimeException{
        private Integer code;
        private String msg;

        public ReggieException(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public ReggieException(ReggieExceptionEnum e){
            this(e.getCode(),e.getMsg());
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
}
