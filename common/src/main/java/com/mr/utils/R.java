package com.mr.utils;

import java.util.HashMap;

/**
 * @author LiangYongjie
 * @date 2019-01-05
 */
public class R {

    private String message;
    private int code;
    private HashMap<String, Object> data;

    private R(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = new HashMap<>();
    }

    public static R ok() {
        return new R(200, "success");
    }

    public static R error(int code, String message) {
        return new R(code, message);
    }

    public static R error(String message) {
        return R.error(500, message);
    }

    public R put(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return GsonUtils.get().toJson(this);
    }
}
