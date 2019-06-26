package com.mr.enumeration;

import com.google.gson.*;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Type;

/**
 * @author LiangYongjie
 * @date 2019-01-20
 */
public class EnumTypeAdapter implements JsonSerializer<BaseEnum>, JsonDeserializer<BaseEnum> {

    private static final EnumConverterFactory factory = new EnumConverterFactory();

    @Override
    public BaseEnum deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Converter<String, ? extends BaseEnum> converter;
        try {
            Class clazz = Class.forName(typeOfT.getTypeName());
            converter = factory.getConverter(clazz);
            return converter.convert(json.getAsString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JsonElement serialize(BaseEnum src, Type typeOfSrc, JsonSerializationContext context) {
        if (src != null) {
            return new JsonPrimitive(src.value());
        }

        return null;
    }
}
