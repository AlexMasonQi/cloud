package com.alex.cloud.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author Alex
 * @description 日期时间工具类
 * @date 2018.08.23 21:14
 */
public class DateTimeUtil
{
    /**
     * @return 当前日期
     * @description 获取当前日期, 格式：yyyy-MM-dd
     * @author Alex
     * @date 2018.08.23 21:19
     */
    public static String getCurrentDate()
    {
        return LocalDate.now().toString();
    }

    /**
     * @return 当前日期和时间
     * @description 获取当前日期和时间, 格式：yyyy-MM-dd HH:mm:ss
     * @author Alex
     * @date 2018.08.23 21:22
     */
    public static String getCurrentDateTime()
    {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String dateTimeText = currentDateTime.getYear() + "-" + addZero(currentDateTime.getMonthValue()) + "-" + addZero(currentDateTime.getDayOfMonth()) + " " + addZero(currentDateTime.getHour()) + ":" + addZero(currentDateTime.getMinute()) + ":" + addZero(currentDateTime.getSecond());

        return dateTimeText;
    }

    /**
     * @param seconds 秒数
     * @return 小时和分钟
     * @description 根据秒数获取小时和分钟, 格式：HH:mm
     * @author Alex
     * @date 2018.08.23 21:25
     */
    public static String getHourMinutes(Long seconds)
    {
        return LocalTime.ofSecondOfDay(seconds).format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * @return 当前日期前一天
     * @description 获取当前日期的前一天, 格式：yyyy-MM-dd
     * @author Alex
     * @date 2018.08.23 22:15
     */
    public static String getDateBeforeCurrentDate()
    {
        return LocalDate.now().plusDays(-1L).toString();
    }

    /**
     * @param days 天数
     * @return 当前日期的前days天
     * @description 获取当前日期的前days天, 格式：yyyy-MM-dd
     * @author Alex
     * @date 2018.08.23 22:17
     */
    public static String getDateBeforeDaysCurrentDate(Integer days)
    {
        return LocalDate.now().plusDays(-days).toString();
    }

    /**
     * @return 当前日期的后一天
     * @description 获取当前日期的后一天, 格式：yyyy-MM-dd
     * @author Alex
     * @date 2018.08.23 22:20
     */
    public static String getDateAfterCurrentDate()
    {
        return LocalDate.now().plusDays(1).toString();
    }

    /**
     * @param diff 毫秒数
     * @return 分钟数
     * @description 根据时间差获取分钟数
     * @author Alex
     * @date 2018.08.23 22:21
     */
    public static String getMinutesFromTimeDiff(Long diff)
    {
        return String.valueOf(TimeUnit.MILLISECONDS.toMinutes(diff));
    }

    /**
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 天数
     * @description 根据两个日期获取天数差
     * @author Alex
     * @date 2018.08.23 22:33
     */
    public static Integer getDatePoor(LocalDate startDate, LocalDate endDate)
    {
        return endDate.compareTo(startDate);
    }

    /**
     * @param startDate 开始日期，格式:yyyy-MM-dd
     * @param endDate   结束日期，格式:yyyy-MM-dd
     * @return 天数
     * @description 根据两个日期字符串获取天数差
     * @author Alex
     * @date 2018.08.23 22:37
     */
    public static Integer getDatePoor(String startDate, String endDate)
    {
        return LocalDate.parse(endDate).compareTo(LocalDate.parse(startDate));
    }

    /**
     * @param startDateTime 开始日期，格式:yyyy-MM-dd HH:mm:ss
     * @param endDateTime   结束日期，格式:yyyy-MM-dd HH:mm:ss
     * @return UNIX时间差
     * @description 根据两个日期字符串获取UNIX时间差(东8区即北京时间)
     * @author Alex
     * @date 2018.08.23 22:37
     */
    public static Integer getUnixDiff(String startDateTime, String endDateTime)
    {
        LocalDateTime startTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime = LocalDateTime.parse(endDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return Long.valueOf((endTime.toEpochSecond(ZoneOffset.ofHours(8)) - startTime.toEpochSecond(ZoneOffset.ofHours(8)))).intValue();
    }

    /**
     * @param datetime 日期，格式:yyyy-MM-dd HH:mm:ss
     * @return UNIX时间戳
     * @description 根据日期获取UNIX时间戳(东8区即北京时间)
     * @author Alex
     * @date 2018.08.23 23:14
     */
    public static String datetimeToUnix(String datetime)
    {
        return String.valueOf(LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toEpochSecond(ZoneOffset.ofHours(8)));
    }

    /**
     * @param datetime 日期，格式:yyyy-MM-dd HH:mm:ss
     * @return 当天的秒数
     * @description 根据日期获取当天的秒数
     * @author Alex
     * @date 2018.08.23 23:17
     */
    public static Integer datetimeToSecondOfDays(String datetime)
    {
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return localDateTime.toLocalTime().toSecondOfDay();
    }

    /**
     * @param unix UNIX时间戳
     * @return 日期时间
     * @description 根据UNIX时间戳获取日期时间，格式：yyyy-MM-dd HH:mm:ss
     * @author Alex
     * @date 2018.08.23 23:36
     */
    public static String unixToDateTime(Long unix)
    {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(unix, 0, ZoneOffset.ofHours(8));
        String dateTimeText = localDateTime.getYear() + "-" + addZero(localDateTime.getMonthValue()) + "-" + addZero(localDateTime.getDayOfMonth()) + " " + addZero(localDateTime.getHour()) + ":" + addZero(localDateTime.getMinute()) + ":" + addZero(localDateTime.getSecond());

        return dateTimeText;
    }

    private static String addZero(Integer time)
    {
        String timeText = time.toString();
        if (time >= 0 && time < 10)
        {
            timeText = "0" + time;
        }
        return timeText;
    }
}
