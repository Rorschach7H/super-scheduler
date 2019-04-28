package com.meixiaoxi.scheduler.common;


import com.meixiaoxi.scheduler.exception.TaskTimeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /**
     * 将日期转换成字符串，不带时分秒
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date, String format) {
        if (null != date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } else {
            return null;
        }
    }

    /**
     * 将字符串转成成日期，带时分秒
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String date, String pattern) {
        if (date == null || date.trim().length() == 0) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据传递的日期增加天数
     *
     * @param date     日期
     * @param timeUnit 单位
     * @param count    增加的数量
     * @return
     */
    public static Date changeDate(Date date, TimeUnit timeUnit, int count) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            switch (timeUnit) {
                case year:
                    calendar.add(Calendar.YEAR, count);
                    break;
                case month:
                    calendar.add(Calendar.MONTH, count);
                    break;
                case day:
                    calendar.add(Calendar.DAY_OF_MONTH, count);
                    break;
                case hour:
                    calendar.add(Calendar.HOUR_OF_DAY, count);
                    break;
                case minute:
                    calendar.add(Calendar.MINUTE, count);
                    break;
                case second:
                    calendar.add(Calendar.SECOND, count);
                    break;
                default:
                    throw new TaskTimeException("error TimeUnit!");
            }
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断当前时间是否是周一
     * <p>
     * 说明：周日是第一天，周一是第二天
     * </p>
     *
     * @return
     */
    public static boolean nowIsMonday() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day == 2;
    }

    /**
     * 判断当前时间是否是没有的第一天
     *
     * @return
     */
    public static boolean nowIsFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day == 1;
    }

    /**
     * 毫秒级别
     *
     * @param date
     * @return
     */
    public static long dateToTimestramp(Date date) {
        return date.getTime();
    }

    /**
     * 毫秒级别
     *
     * @param date
     * @return
     */
    public static long dateToTimestramp(String date, String pattern) {
        return stringToDate(date, pattern).getTime();
    }


    /**
     * 将long格式的日期（秒）转化为时间
     *
     * @param second
     * @return
     */
    public static Date longSecondToDate(String second) {
        Date dat = new Date(Long.parseLong(second) * 1000);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        return gc.getTime();
    }

    public enum TimeUnit {
        year,
        month,
        day,
        hour,
        minute,
        second
    }

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
//        Date newDate = getMatureDate(date, ConstUtil.TOKEN_VALID_TIME);
//        System.out.println(newDate.getTime() + "," + date.getTime());
//        System.out.println(dateToString(newDate) + "," + dateToString(date));

        System.out.println(longSecondToDate("1458788699"));
    }
}
