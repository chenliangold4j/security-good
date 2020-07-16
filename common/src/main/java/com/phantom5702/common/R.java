package com.phantom5702.common;

import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 20000);
        put("message","success");
    }

    public static R error() {
        return error(20001, "error");
    }

    public static R error(String msg) {
        return error(20001, msg);
    }

    private static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("message", msg);
        return r;
    }


    public static R ok(Object data) {
        R r = new R();
        r.put("data",data);
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static void main(String[] args) {
        Result dsdf = Result.createDefaultErrorMessage("dsdf");
        System.out.println(JSONUtil.toJsonStr(R.ok(dsdf)));
    }
}