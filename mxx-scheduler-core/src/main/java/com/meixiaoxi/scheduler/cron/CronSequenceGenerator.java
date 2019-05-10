package com.meixiaoxi.scheduler.cron;

import com.meixiaoxi.scheduler.common.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.*;

/**
 * <a href="http://www.manpagez.com/man/5/crontab/">Crontab pattern</a>模式
 * 的日期队列生成器,允许客户端指定一个队列匹配的模式。
 * <p>
 * 该模式是由六个单独的空格分隔字段组成的列表：分别表示秒、分钟、小时、天、月、工作日。
 * 月份和工作日名称可以作为英文名称的前三个字母。
 * <p>模式案例:
 * <ul>
 * <li>"0 0 * * * *" = 每天每小时的整点执行一次.</li>
 * <li>"10 * * * * *" = 每分钟10秒执行一次.</li>
 * <li>"0 0 8-10 * * *" = 每天的8,9,10点分别执行一次.</li>
 * <li>"0 0 6,19 * * *" = 每天的早上6:00和下午7:00分别执行一次. </li>
 * <li>"0 0/30 8-10 * * *" = 每天的8,9,10点的0分开始每30秒执行一次.</li>
 * <li>"0 0 9-17 * * MON-FRI" = 周一到周五的每天9到17点的整点执行一次.</li>
 * <li>"0 0 0 01 10 ?" = 每年的国庆节的整点执行一次.</li>
 * </ul>
 */
public class CronSequenceGenerator {

    private final String expression;

    private final TimeZone timeZone;

    private final BitSet months = new BitSet(12);

    private final BitSet daysOfMonth = new BitSet(31);

    private final BitSet daysOfWeek = new BitSet(7);

    private final BitSet hours = new BitSet(24);

    private final BitSet minutes = new BitSet(60);

    private final BitSet seconds = new BitSet(60);


    public CronSequenceGenerator(String expression) {
        this(expression, TimeZone.getDefault());
    }

    public CronSequenceGenerator(String expression, TimeZone timeZone) {
        this.expression = expression;
        this.timeZone = timeZone;
        parse(expression);
    }

    private CronSequenceGenerator(String expression, String[] fields) {
        this.expression = expression;
        this.timeZone = null;
        doParse(fields);
    }

    String getExpression() {
        return this.expression;
    }


    /**
     * 在与cron模式匹配的序列中以及在提供的值之后获取下一个执行时间。返回值将具有整数秒，并且在输入值之后。
     *
     * @param date 输入的时间值
     * @return next
     */
    public Date next(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(this.timeZone);
        calendar.setTime(date);

        calendar.set(Calendar.MILLISECOND, 0);
        long originalTimestamp = calendar.getTimeInMillis();
        doNext(calendar, calendar.get(Calendar.YEAR));

        if (calendar.getTimeInMillis() == originalTimestamp) {
            calendar.add(Calendar.SECOND, 1);
            doNext(calendar, calendar.get(Calendar.YEAR));
        }

        return calendar.getTime();
    }

    private void doNext(Calendar calendar, int dot) {
        List<Integer> resets = new ArrayList<>();

        int second = calendar.get(Calendar.SECOND);
        List<Integer> emptyList = Collections.emptyList();
        int updateSecond = findNext(this.seconds, second, calendar, Calendar.SECOND, Calendar.MINUTE, emptyList);
        if (second == updateSecond) {
            resets.add(Calendar.SECOND);
        }

        int minute = calendar.get(Calendar.MINUTE);
        int updateMinute = findNext(this.minutes, minute, calendar, Calendar.MINUTE, Calendar.HOUR_OF_DAY, resets);
        if (minute == updateMinute) {
            resets.add(Calendar.MINUTE);
        } else {
            doNext(calendar, dot);
        }

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int updateHour = findNext(this.hours, hour, calendar, Calendar.HOUR_OF_DAY, Calendar.DAY_OF_WEEK, resets);
        if (hour == updateHour) {
            resets.add(Calendar.HOUR_OF_DAY);
        } else {
            doNext(calendar, dot);
        }

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int updateDayOfMonth = findNextDay(calendar, this.daysOfMonth, dayOfMonth, daysOfWeek, dayOfWeek, resets);
        if (dayOfMonth == updateDayOfMonth) {
            resets.add(Calendar.DAY_OF_MONTH);
        } else {
            doNext(calendar, dot);
        }

        int month = calendar.get(Calendar.MONTH);
        int updateMonth = findNext(this.months, month, calendar, Calendar.MONTH, Calendar.YEAR, resets);
        if (month != updateMonth) {
            if (calendar.get(Calendar.YEAR) - dot > 4) {
                throw new IllegalArgumentException("Invalid cron expression \"" + this.expression +
                        "\" led to runaway search for next trigger");
            }
            doNext(calendar, dot);
        }

    }

    private int findNextDay(Calendar calendar, BitSet daysOfMonth, int dayOfMonth, BitSet daysOfWeek, int dayOfWeek,
                            List<Integer> resets) {

        int count = 0;
        int max = 366;
        // the DAY_OF_WEEK values in java.util.Calendar start with 1 (Sunday),
        // but in the cron pattern, they start with 0, so we subtract 1 here
        while ((!daysOfMonth.get(dayOfMonth) || !daysOfWeek.get(dayOfWeek - 1)) && count++ < max) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            reset(calendar, resets);
        }
        if (count >= max) {
            throw new IllegalArgumentException("Overflow in day for expression \"" + this.expression + "\"");
        }
        return dayOfMonth;
    }

    /**
     * 在提供的值之后搜索下一个设置位提供的位，然后重置日历。
     *
     * @param bits        {@link BitSet} 表示字段的允许的值
     * @param value       字段的当前值
     * @param calendar    通过位移动增加的日历
     * @param field       日历中要递增的字段 (@see {@link Calendar})
     * @param lowerOrders 应重置的日历字段ID（即重要性低于相关字段的日历字段ID）
     * @return 序列中下一个日历字段的值
     */
    private int findNext(BitSet bits, int value, Calendar calendar, int field, int nextField, List<Integer> lowerOrders) {
        int nextValue = bits.nextSetBit(value);
        // roll over if needed
        if (nextValue == -1) {
            calendar.add(nextField, 1);
            reset(calendar, Collections.singletonList(field));
            nextValue = bits.nextSetBit(0);
        }
        if (nextValue != value) {
            calendar.set(field, nextValue);
            reset(calendar, lowerOrders);
        }
        return nextValue;
    }

    /**
     * Reset the calendar setting all the fields provided to zero.
     */
    private void reset(Calendar calendar, List<Integer> fields) {
        for (int field : fields) {
            calendar.set(field, field == Calendar.DAY_OF_MONTH ? 1 : 0);
        }
    }


    // Parsing logic invoked by the constructor

    /**
     * Parse the given pattern expression.
     */
    private void parse(String expression) throws IllegalArgumentException {
        String[] fields = tokenizeToStringArray(expression);
        if (areValidCronFields(fields)) {
            throw new IllegalArgumentException(String.format(
                    "Cron expression must consist of 6 fields (found %d in \"%s\")", fields.length, expression));
        }
        doParse(fields);
    }

    private void doParse(String[] fields) {
        setNumberHits(this.seconds, fields[0], 0, 60);
        setNumberHits(this.minutes, fields[1], 0, 60);
        setNumberHits(this.hours, fields[2], 0, 24);
        setDaysOfMonth(this.daysOfMonth, fields[3]);
        setMonths(this.months, fields[4]);
        setDays(this.daysOfWeek, replaceOrdinals(fields[5], "SUN,MON,TUE,WED,THU,FRI,SAT"), 8);

        if (this.daysOfWeek.get(7)) {
            // Sunday can be represented as 0 or 7
            this.daysOfWeek.set(0);
            this.daysOfWeek.clear(7);
        }
    }

    /**
     * Replace the values in the comma-separated list (case insensitive)
     * with their index in the list.
     *
     * @return a new String with the values from the list replaced
     */
    private String replaceOrdinals(String value, String commaSeparatedList) {
        String[] list = delimitedListToStringArray(commaSeparatedList, ",");
        for (int i = 0; i < list.length; i++) {
            String item = list[i].toUpperCase();
            value = StringUtils.replace(value.toUpperCase(), item, "" + i);
        }
        return value;
    }

    private void setDaysOfMonth(BitSet bits, String field) {
        int max = 31;
        // Days of month start with 1 (in Cron and Calendar) so add one
        setDays(bits, field, max + 1);
        // ... and remove it from the front
        bits.clear(0);
    }

    private void setDays(BitSet bits, String field, int max) {
        if (field.contains("?")) {
            field = "*";
        }
        setNumberHits(bits, field, 0, max);
    }

    private void setMonths(BitSet bits, String value) {
        int max = 12;
        value = replaceOrdinals(value, "FOO,JAN,FEB,MAR,APR,MAY,JUN,JUL,AUG,SEP,OCT,NOV,DEC");
        BitSet months = new BitSet(13);
        // Months start with 1 in Cron and 0 in Calendar, so push the values first into a longer bit set
        setNumberHits(months, value, 1, max + 1);
        // ... and then rotate it to the front of the months
        for (int i = 1; i <= max; i++) {
            if (months.get(i)) {
                bits.set(i - 1);
            }
        }
    }

    /**
     * 设置位set的值
     *
     * @param bits  要设置的位set
     * @param value 当前cron表达式截取的值
     * @param min   最小值
     * @param max   最大值
     */
    private void setNumberHits(BitSet bits, String value, int min, int max) {
        String[] fields = delimitedListToStringArray(value, ",");
        for (String field : fields) {
            if (!field.contains("/")) {
                // Not an incrementer so it must be a range (possibly empty)
                int[] range = getRange(field, min, max);
                bits.set(range[0], range[1] + 1);
            } else {
                String[] split = delimitedListToStringArray(field, "/");
                if (split.length > 2) {
                    throw new IllegalArgumentException("Incrementer has more than two fields: '" +
                            field + "' in expression \"" + this.expression + "\"");
                }
                int[] range = getRange(split[0], min, max);
                if (!split[0].contains("-")) {
                    range[1] = max - 1;
                }
                int delta = Integer.parseInt(split[1]);
                if (delta <= 0) {
                    throw new IllegalArgumentException("Incrementer delta must be 1 or higher: '" +
                            field + "' in expression \"" + this.expression + "\"");
                }
                for (int i = range[0]; i <= range[1]; i += delta) {
                    bits.set(i);
                }
            }
        }
    }

    private int[] getRange(String field, int min, int max) {
        int[] result = new int[2];
        if (field.contains("*")) {
            result[0] = min;
            result[1] = max - 1;
            return result;
        }
        if (!field.contains("-")) {
            result[0] = result[1] = Integer.valueOf(field);
        } else {
            String[] split = delimitedListToStringArray(field, "-");
            if (split.length > 2) {
                throw new IllegalArgumentException("Range has more than two fields: '" +
                        field + "' in expression \"" + this.expression + "\"");
            }
            result[0] = Integer.valueOf(split[0]);
            result[1] = Integer.valueOf(split[1]);
        }
        if (result[0] >= max || result[1] >= max) {
            throw new IllegalArgumentException("Range exceeds maximum (" + max + "): '" +
                    field + "' in expression \"" + this.expression + "\"");
        }
        if (result[0] < min || result[1] < min) {
            throw new IllegalArgumentException("Range less than minimum (" + min + "): '" +
                    field + "' in expression \"" + this.expression + "\"");
        }
        if (result[0] > result[1]) {
            throw new IllegalArgumentException("Invalid inverted range: '" + field +
                    "' in expression \"" + this.expression + "\"");
        }
        return result;
    }


    /**
     * Determine whether the specified expression represents a valid cron pattern.
     *
     * @param expression the expression to evaluate
     * @return {@code true} if the given expression is a valid cron expression
     * @since 4.3
     */
    public static boolean isValidExpression(String expression) {
        if (expression == null) {
            return false;
        }
        String[] fields = tokenizeToStringArray(expression);
        if (areValidCronFields(fields)) {
            return false;
        }
        try {
            new CronSequenceGenerator(expression, fields);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    private static boolean areValidCronFields(String[] fields) {
        return (fields == null || fields.length != 6);
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CronSequenceGenerator)) {
            return false;
        }
        CronSequenceGenerator otherCron = (CronSequenceGenerator) other;
        return (this.months.equals(otherCron.months) && this.daysOfMonth.equals(otherCron.daysOfMonth) &&
                this.daysOfWeek.equals(otherCron.daysOfWeek) && this.hours.equals(otherCron.hours) &&
                this.minutes.equals(otherCron.minutes) && this.seconds.equals(otherCron.seconds));
    }

    @Override
    public int hashCode() {
        return (17 * this.months.hashCode() + 29 * this.daysOfMonth.hashCode() + 37 * this.daysOfWeek.hashCode() +
                41 * this.hours.hashCode() + 53 * this.minutes.hashCode() + 61 * this.seconds.hashCode());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + this.expression;
    }

    /**
     * 按空格对字符串进行切割
     *
     * @param str
     * @return
     */
    private static String[] tokenizeToStringArray(String str) {
        if (str == null) {
            return new String[0];
        }

        StringTokenizer st = new StringTokenizer(str, " ");
        List<String> tokens = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.length() > 0) {
                tokens.add(token);
            }
        }
        return tokens.toArray(new String[0]);
    }

    /**
     * 根据分隔符对字符串进行分隔
     *
     * @param str
     * @param delimiter
     * @return
     */
    private String[] delimitedListToStringArray(String str, String delimiter) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[]{str};
        }

        List<String> result = new ArrayList<>();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(str.substring(i, i + 1));
            }
        } else {
            int pos = 0;
            int delPos;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(str.substring(pos, delPos));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                result.add(str.substring(pos));
            }
        }
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) throws ParseException {
        String expression = "1/9 * 12 * * 1990";
        CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(expression);
        Date date = DateUtil.stringToDate("2019-05-06 12:00:04", "yyyy-MM-dd hh:mm:ss");
        Date next = cronSequenceGenerator.next(date);
        System.out.println(DateUtil.dateToString(next, "yyyy-MM-dd hh:mm:ss"));
    }
}
