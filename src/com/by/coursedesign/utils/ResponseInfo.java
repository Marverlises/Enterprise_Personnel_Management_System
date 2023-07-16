package com.by.coursedesign.utils;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/16 9:41
 * @StudentNumber 2018217662
 * @Description
 * 描述返回信息
 */
public class ResponseInfo<T> {
    int code;           //状态码
    String msg;         //返回信息
    int count;          //返回数量
    List<T> data;  //返回列表
    
    public ResponseInfo() {
    }
    
    public ResponseInfo(int code, String msg, int count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "ResponseInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
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
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    public List<T> getData() {
        return data;
    }
    
    public void setData(List<T> data) {
        this.data = data;
    }
}
