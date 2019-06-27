package com.mr.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author LiangYongjie
 * @date 2019-01-05
 */
public class GsonUtils {

    private GsonUtils(){}

    public static Gson get() {
        return GsonHolder.INSTANCE;
    }

    private static class GsonHolder {
        private static final Gson INSTANCE =
            new GsonBuilder()
                .serializeNulls()
                .create();
    }
}
