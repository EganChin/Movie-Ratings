package com.mr.utils;

import com.mr.SpringfoxJsonToGsonSeializer;
import com.mr.enumeration.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import springfox.documentation.spring.web.json.Json;

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
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonSeializer())
                .create();
    }
}
