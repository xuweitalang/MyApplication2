package com.leador.picassodemo;

/**
 * Created by xuwei on 2016/12/4.
 */
public class Result<T> {
    private int code;
    private String msg;
    private T newslist;

    public Result(int code, String msg, T newslist) {
        this.code = code;
        this.msg = msg;
        this.newslist = newslist;
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

    public T getNewslist() {
        return newslist;
    }

    public void setNewslist(T newslist) {
        this.newslist = newslist;
    }
}
