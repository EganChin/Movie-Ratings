package com.mr;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import springfox.documentation.spring.web.json.Json;

import java.lang.reflect.Type;

/**
 * @author LiangYongjie
 * @date 2019-05-29
 */
public class SpringfoxJsonToGsonSeializer implements JsonSerializer<Json> {

    @Override
    public JsonElement serialize(Json json, Type typeOfSrc, JsonSerializationContext context) {
        final JsonParser parser = new JsonParser();
        return parser.parse(json.value());
    }
}
