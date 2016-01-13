package com.suypower.cloudx.support.util;

/**
 * Created by Bingdor on 2015/11/25.
 */
public class StringUtil {
    public static Boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判定一个对象是否为null or empty
     *
     * @param o 对象
     * @return true or false
     */
    public static boolean isNullOrEmpty(Object o) {
        return o == null || String.valueOf(o).trim().length() == 0
                || String.valueOf(o).trim().equals("null");
    }
}
