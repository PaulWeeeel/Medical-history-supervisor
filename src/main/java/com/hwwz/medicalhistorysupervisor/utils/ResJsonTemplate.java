package com.hwwz.medicalhistorysupervisor.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: HuShili
 * @date: 2017/12/30
 * @description: none
 */
public class ResJsonTemplate<T> {

    private String status;

    private T result;

    private String time;

    public ResJsonTemplate(String status, Date time, T result) {
        this.status = status;
        this.result = result;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分s秒");
        this.time = sdf.format(time);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getTime() { return time; }

    public void setTime(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分s秒");
        this.time = sdf.format(time);
    }
}
