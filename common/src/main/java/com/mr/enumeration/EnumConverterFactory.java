package com.mr.enumeration;

import com.mr.exception.RRException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author LiangYongjie
 * @date 2019-01-15
 */
public class EnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    private static final Map<Class, Converter> converterMap = new WeakHashMap<>();

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter converter = converterMap.get(targetType);
        if (converter == null) {
            converter = new StringToEnum<>(targetType);
            converterMap.put(targetType, converter);
        }

        return converter;
    }

    class StringToEnum<T extends BaseEnum> implements Converter<String, T> {

        private Map<String, T> enumMap = new HashMap<>();

        StringToEnum(Class<T> enumType) {
            for (T t : enumType.getEnumConstants()) {
                enumMap.put(t.value() + "", t);
            }
        }

        @Override
        public T convert(String source) {
            T result = enumMap.get(source);
            if (result == null) {
                throw new RRException("参数错误", 500);
            }

            return result;
        }
    }
}
