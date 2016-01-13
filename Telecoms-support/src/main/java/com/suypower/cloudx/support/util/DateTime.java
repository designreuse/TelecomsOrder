package com.suypower.cloudx.support.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Zhengtao on 2015/12/1.
 */
public class DateTime {

    /**
     * 将日期类型转化为字符串，默认格式yyyy-MM-dd
     *
     * @param date
     * @return 返回结果
     */
    public static String dateToStr(java.util.Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        return newFormat.format(date);
    }

    /**
     * 将日期类型转化为字符串，默认格式yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return 返回结果
     */
    public static String dateTimeToStr(java.util.Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return newFormat.format(date);
    }

    /**
     * 将日期类型转化为字符串,根据传入的格式输出日期（如YYYY年MM月DD日等）
     *
     * @param date
     *            日期
     * @param sFormat
     *            日期格式
     * @return 返回结果
     */
    public static String dateToStr(Object date, String sFormat) {
        if (date == null) {
            return null;
        }
        if (StringUtil.isEmpty(sFormat) || "".equals(sFormat.trim())) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
        return newFormat.format(date);
    }

    /**
     * 字符串转化为日期类型，默认格式yyyy-MM-dd
     *
     * @param strDate
     *            字符串
     * @return 返回日期
     */
    public static java.util.Date strToDate(String strDate) {
        if (StringUtil.isEmpty(strDate) || "".equals(strDate.trim())) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return newFormat.parse(strDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 将格式化的日期字串转化成日期类型(如yyyy年MM月DD日->date)
     *
     * @param strDate
     *            字符串
     * @return 返回日期
     */
    public static java.util.Date strToDate(String strDate, String sFormat) {
        if (StringUtil.isEmpty(strDate) || "".equals(strDate.trim())) {
            return null;
        }
        if (StringUtil.isEmpty(sFormat) || "".equals(sFormat.trim())) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
        try {
            return newFormat.parse(strDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 字符串转化为日期类型，默认格式yyyy-MM-dd HH:mm:ss
     *
     * @param strTimeStamp
     *            字符串
     * @return 返回日期
     */
    public static Timestamp strTimeStamp(String strTimeStamp) {
        if (StringUtil.isEmpty(strTimeStamp) || "".equals(strTimeStamp.trim())) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new Timestamp(newFormat.parse(strTimeStamp).getTime());
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 字符串转化为日期类型，默认格式
     *
     * @param strTimeStamp
     *            字符串
     * @return 返回日期
     */
    public static Timestamp strTimeStamp(String strTimeStamp, String sFormat) {
        if (StringUtil.isEmpty(strTimeStamp) || "".equals(strTimeStamp.trim())) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
        try {
            return new Timestamp(newFormat.parse(strTimeStamp.trim()).getTime());
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 获取某年某月天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取某年某月最后一天
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthLastDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取某年某月第一天
     * @param year
     * @param month
     * @return
     */
    public static Date getMonthFirstDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 将日期类型转化为字符串，默认格式yyyy-MM-dd
     *
     * @return 返回结果
     */
    public static String getCurDate() {
        return dateToStr(new Date());
    }

    /**
     * 显示时间，如果与当前时间差别小于一天，则自动用**秒(分，小时)前，如果大于一天则用format规定的格式显示
     *
     * @author wxy
     * @param ctime
     *            时间
     * @param format
     *            格式 格式描述:例如:yyyy-MM-dd yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String showTime(long timeGaps, Date ctime, String format) {
        String r = "";

        if (timeGaps < 60000) {// 一分钟内
            long seconds = timeGaps / 1000;
            if (seconds == 0) {
                r = "刚刚";
            } else {
                r = seconds + "秒前";
            }
        } else if (timeGaps >= 60000 && timeGaps < 3600000) {// 一小时内
            long seconds = timeGaps / 60000;
            r = seconds + "分钟前";
        } else if (timeGaps >= 3600000 && timeGaps < 86400000) {// 一天内
            long seconds = timeGaps / 3600000;
            r = seconds + "小时前";
        } else if (timeGaps >= 86400000 && timeGaps < 1702967296) {// 三十天内
            long seconds = timeGaps / 86400000;
            r = seconds + "天前";
        } else {// 日期格式
            if (format == null) {
                format = "MM月dd日 HH:mm";
                Calendar cal = Calendar.getInstance();
                if (!String.valueOf(cal.get(Calendar.YEAR)).equals(dateToStr(ctime, "dd"))) {
                    format = "yyyy年MM月dd日 HH:mm";
                }
            }

            SimpleDateFormat df = new SimpleDateFormat(format);
            r = df.format(ctime).toString();
        }
        return r;
    }

    public static Date addTime(Date d, long timeGaps) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime() + timeGaps);
        return cal.getTime();
    }

}
