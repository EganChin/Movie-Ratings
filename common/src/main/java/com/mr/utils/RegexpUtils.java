package com.mr.utils;

/**
 * @author LiangYongjie
 * @date 2019-05-23
 */
public class RegexpUtils {

    public static final String PHONE = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";

    public static final String NAME = "^([^\\x00-\\xff]{2,10})?$";
}
