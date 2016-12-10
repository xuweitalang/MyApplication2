package com.leador.picassodemo;

/**
 * Created by xuwei on 2016/12/3.
 */
public class DemoBean {
    int code;
    String state;
    String [] json;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getJson() {
        return json;
    }

    public void setJson(String[] json) {
        this.json = json;
    }

    public DemoBean(int code, String state, String[] json) {
        this.code = code;
        this.state = state;
        this.json = json;
    }
}
