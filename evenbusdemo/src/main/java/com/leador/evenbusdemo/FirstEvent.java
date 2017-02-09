package com.leador.evenbusdemo;

/**
 * Created by xuwei on 2017/2/4.
 */

public class FirstEvent {
    private String msg;

    public FirstEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
