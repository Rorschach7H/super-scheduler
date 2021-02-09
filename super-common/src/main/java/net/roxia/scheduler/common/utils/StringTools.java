package net.roxia.scheduler.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: StringTools
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-14 11:09:01
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-14    meixiaoxi       v1.0.0           创建
 */
public class StringTools {
    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String underline2Camel(String str) {
        return toCamel(str, "_").toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String camel2UnderLine(String str) {
        return camelTo(str, "_").toString();
    }

    /**
     * 中划线转驼峰
     *
     * @param str
     * @return
     */
    public static String line2Camel(String str) {
        return toCamel(str, "-").toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String camel2Line(String str) {
        return camelTo(str, "-").toString();
    }

    /**
     * 转驼峰
     *
     * @param str
     * @return
     */
    public static StringBuffer toCamel(String str, String split) {
        //利用正则删除下划线，把下划线后一位改成大写
        Matcher matcher = Pattern.compile(split + "(\\w)").matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return toCamel(sb.toString(), split);
    }

    /**
     * 转驼峰
     *
     * @param str
     * @return
     */
    public static StringBuffer camelTo(String str, String split) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, split + matcher.group(0).toLowerCase());
            //把之后的也添加到StringBuffer对象里
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return camelTo(sb.toString(), split);
    }

    public static void main(String[] args) {
        String str = "helloWorldFuck";
        System.out.println(camel2UnderLine(str));
        System.out.println(camel2Line(str));
        String str1 = "hello-world-fuck";
        String str2 = "hello_world_fuck";
        System.out.println(line2Camel(str1));
        System.out.println(underline2Camel(str2));
    }
}
