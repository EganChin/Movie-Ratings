package com.mr.validator;

import com.mr.exception.RRException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author LiangYongjie
 * @date 2018-11-14
 */
public class Assert {

    public static void isBlank(String str, String message) {
        Assert.check(StringUtils.isBlank(str), message);
    }

    public static void isNotBlank(String str, String message) {
        Assert.check(StringUtils.isNotBlank(str), message);
    }

    public static void isNull(Object obj, String message) {
        Assert.check(obj == null, message);
    }

    public static void isNotNull(Object obj, String message) {
        Assert.check(obj != null, message);
    }

    /**
     * 如果 bool 为真，则抛出异常
     *
     * @param bool    条件
     * @param message 抛出异常时附带的消息
     */
    public static void check(boolean bool, String message) {
        if (bool) {
            throw new RRException(message);
        }
    }

}
