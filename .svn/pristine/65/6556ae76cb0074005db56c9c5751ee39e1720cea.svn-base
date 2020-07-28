package com.jfast.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p> Title: 日期工具类 </p>
 * <p> Description: </p>
 * @Author:Devin Bai
 * @Time:Jun 17, 20154:54:34 PM
 * @Version:1.00
 * @Record
 * <pre>
 * Version 	Author	Time		describe
 * ----------------------------------------
 * 1.00		Devin	Jun 17, 2015	release
 * ----------------------------------------
 * </pre>
 */
public class DateUtil {
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);   
	public static final String DATE_FORMAT_STR = "yyyy-MM-dd";
	public static final String DATE_FORMAT_STR1 = "yyyyMMdd";
	public static final String DATE_FORMAT_STR2 = "yyyy年MM月dd日";
	public static final String DATE_FORMST_STR_TX = "yyMMdd";
	public static final String DATE_FORMST_STR_YEAR2 = "yy";
	public static final String DATE_FORMST_STR_YEAR3 = "yyyy-MM";
	public static final String DATE_FORMST_STR_TIME = "yyyyMMddHH";
	public static final String DATE_FORMST_STR_ALLTIME = "yyyyMMddHHmmss";
	public static final String DATE_FORMST_STR_TEXT_DATE_TIME_MILLIS = "yyyyMMddHHmmssSSS";
	public static final String DATE_FORMST_STR_TEXT_DATE_TIME_MILLIS2 = "yyyyMMddhhmmssSSS";
	public static final String DATE_FORMST_STR_DATETIME = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMST_STR_TIME1 = "HH-mm-ss";
	public static final String DATE_FORMST_STR_TIME2 = "HH:mm:ss";
	public static final String DATE_FORMST_STR_TIME3 = "HHmmss";
	public static final String DAtE_FORMST_STR_TIMESTAMP="yyyy/MM/dd HH:mm:ss";
	
	/**
	 * 
	 *@description:获取当前营业日，返回格式：yyyyMMdd
	 *@return 当前营业日
	 *@since
	 */
	public static String getBussDate() {
		return getBussDate(DATE_FORMAT_STR1);
	}
	
	/**
	 * 
	 *@description:获取当前营业日，返回格式：yyyy-MM-dd
	 *@return 当前营业日
	 *@since
	 */
	public static String getBussDate_() {
		return getBussDate(DATE_FORMAT_STR);
	}
	
	/**
	 * 
	 *@description:获取当前营业日，返回格式：yyMMdd
	 *@return 当前营业日
	 *@since
	 */
	public static String getTxDate() {
		return getBussDate(DATE_FORMST_STR_TX);
	}
	
	/**
	 * 
	 *@description:根据格式返回当前营业日
	 *@param formatStr 格式
	 *@return 当前营业日
	 *@since
	 */
	public static String getBussDate(String formatStr) {
		// TODO
		return formatDate(new Date(), formatStr);
	}
	
	/**
	 * 
	 *@description:获取时间
	 *@return 时间 格式：HHmmss
	 *@since
	 */
	public static String parseTime2() {
		return getBussDate(DATE_FORMST_STR_TIME3);
	}
	
	/**
	 * 
	 *@description:获取当前年份 格式：yy
	 *@return 当前年份
	 *@since
	 */
	public static String getYear2() {
		return getBussDate(DATE_FORMST_STR_YEAR2);
	}
	
	/**
	 * 
	 *@description:获取当前年份 格式：yy
	 *@return 当前年份
	 *@since
	 */
	public static String getYear2MD() {
		return getBussDate(DATE_FORMST_STR_TX);
	}
	
	/**
	 * 
	 *@description: 格式化日期
	 *@param date 要格式化的日期
	 *@return yyyyMMdd
	 *@since
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATE_FORMAT_STR1);
	}
	
	/**
	 * 
	 *@description: 格式化日期
	 *@param date 要格式化的日期
	 *@return yyyy-MM-dd
	 *@since
	 */
	public static String formatDate_(Date date) {
		return formatDate(date, DATE_FORMAT_STR);
	}
	
	/**
	 * 
	 *@description: 格式化日期
	 *@param date 要格式化的日期
	 *@param formatStr 格式化格式
	 *@return 格式化后日期字符串
	 *@since
	 */
	public static String formatDate(Date date, String formatStr) {
		if(date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}
	
	/**
	 * 
	 *@description: 获取当前系统时间
	 *@return 
	 *@since
	 */
	public static String getCurrentTimeStr(){
		return formatDate(new Date(),DATE_FORMST_STR_TIME);
	}
	
	public static String getCurrentDateTimeStr(){
		return formatDate(new Date(),DATE_FORMST_STR_DATETIME);
	}

	public static String getCurrentDateTimeMillisStr(){
		return formatDate(new Date(),DATE_FORMST_STR_TEXT_DATE_TIME_MILLIS);
	}
	
	public static String getCurrentDateTimeMillisStr2(){
		return formatDate(new Date(),DATE_FORMST_STR_TEXT_DATE_TIME_MILLIS2);
	}
	
	public static Date getCurrentDateTime() {
		return new Date();
	}
	
	/**
	 * 
	 *@description:获取日期 
	 *@param dateStr 日期字符串 格式 yyyyMMdd
	 *@return 日期
	 *@since
	 */
	public static Date parseDate_(String dateStr) {
		return parseDate(dateStr, DATE_FORMAT_STR1);
	}

	/**
	 * 
	 *@description:获取日期 
	 *@param dateStr 日期字符串 格式 yyyyMMddHHmmss
	 *@return 日期
	 *@since
	 */
	public static Date parseDateTime(String dateStr) {
		return parseDate(dateStr, DATE_FORMST_STR_ALLTIME);
	}
	
	/**
	 * 
	 *@description:获取日期 
	 *@param dateStr 日期字符串 格式 yyyy/MM/dd HH:mm:ss
	 *@return 日期
	 *@since
	 */
	public static Date parseTimeStamp(String dateStr) {
		return parseDate(dateStr, DAtE_FORMST_STR_TIMESTAMP);
	}
	
	/**
	 * 
	 *@description:获取日期 
	 *@param dateStr 日期字符串 格式 yyyy-MM-dd
	 *@return 日期
	 *@since
	 */
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, DATE_FORMAT_STR);
	}
	/**
	 * 
	 *@description:获取时间
	 *@param dateStr 时间字符串 格式HH-mm-ss
	 *@return 日期
	 *@since
	 */
	public static Date parseTime(String dateStr) {
		return parseDate(dateStr, DATE_FORMST_STR_TIME1);
	}
	
	/**
	 * 
	 *@description:获取时间
	 *@param dateStr 时间字符串 格式HH:mm:ss
	 *@return 日期
	 *@since
	 */
	public static Date parseTime1(String dateStr) {
		return parseDate(dateStr, DATE_FORMST_STR_TIME2);
	}
	
	/**
	 * 
	 *@description:获取日期
	 *@param dateStr 日期字符串
	 *@param formatStr 日期格式
	 *@return 日期
	 *@since
	 */
	public static Date parseDate(String dateStr, String formatStr) {
		if(StringUtils.isBlank(dateStr)){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}
	
	/**
	 * 
	 *@description:获取一个月的第一天
	 *@param date 要获取的时间
	 *@param formatStr 返回的格式
	 *@return 一个月第一天
	 *@since
	 */
	public static String getFirstDayOfMonth(Date date, String formatStr) {
		Calendar firstCal = Calendar.getInstance();
		firstCal.setTime(date);
		firstCal.set(Calendar.DATE, 1);
		return formatDate(firstCal.getTime(), formatStr);
	}
	
	public static Date getTimeByMinute(Date date,int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}
	
	/**
	 * 
	 *@description:获取一个月的第一天
	 *@param dateStr 要获取的时间字符串
	 *@param formatStr 返回的格式
	 *@return 一个月第一天
	 *@since
	 */
	public static String getFirstDayOfMonth(String dateStr, String formatStr){
		return getFirstDayOfMonth(parseDate(dateStr, formatStr), formatStr);
	}
	
	/**
	 * 
	 *@description:获取一个月的最后一天
	 *@param date 要获取的时间
	 *@param formatStr 返回格式
	 *@return 一个月最后一天
	 *@since
	 */
	public static String getLastDayOfMonth(Date date, String formatStr) {
		Calendar lastCal = Calendar.getInstance();
		lastCal.setTime(date); // 设置日期为当前
		lastCal.add(Calendar.MONTH, 1); // 加一个月
		lastCal.set(Calendar.DATE, 1); // 设为下个月的一号
		lastCal.add(Calendar.DAY_OF_YEAR, -1); // 减一天
		return formatDate(lastCal.getTime(), formatStr);
	}
	
	/**
	 * 
	 *@description:获取一个月的最后一天
	 *@param dateStr 要获取的时间字符串
	 *@param formatStr 返回格式
	 *@return 一个月最后一天
	 *@since
	 */
	public static String getLastDayOfMonth(String dateStr, String formatStr) {
		return getLastDayOfMonth(parseDate(dateStr, formatStr), formatStr);
	}
	
	/**
	 * 
	 *@description: 获取指定日期前N天的日期
	 *@param date 指定日期
	 *@param days 天数
	 *@return 指定日期前N天日期
	 *@since
	 */
	public static Date minusDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, -days);
		return cal.getTime();
	}
	
	/**
	 * 
	 *@description: 获取指定日期后N天的日期
	 *@param date 指定日期
	 *@param days 天数
	 *@return 指定日期后N天日期
	 *@since
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		return cal.getTime();
	}
	
	/**
	 * 
	 *@description:对日期进行增加月份操作
	 *@param date 指定日期
	 *@param months 增加的月份，如果要减少输入负值
	 *@return 增加后的日期
	 *@since
	 */
	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}
	
	/**
	 * 
	 *@description:Calendar转化为Date
	 *@return Date型日期
	 *@since
	 */
    public static Date calendar2Date(Calendar cal) {
		Date date = cal.getTime();
		return date;
    }
    
    /**
     * 
     *@description: Date转化为Calendar
     *@return Calendar型日期
     *@since
     */
    public static Calendar date2Calendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
    }
	
	/**
	 * 
	 *@description:计算两个任意时间中间的间隔天数 改进精确计算相隔天数的方法
	 *@param d1 Calendar型日期
	 *@param d2 Calendar型日期
	 *@return
	 *@since
	 */
    public static int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
		    Calendar swap = d1;
		    d1 = d2;
		    d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
		    d1 = (Calendar) d1.clone();
		    do {
			days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
			d1.add(Calendar.YEAR, 1);
		    } while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
    }
    
    /**
	 * 
	 *@description:计算两个任意时间中间的间隔天数
	 *@param d1 Date型日期
	 *@param d2 Date型日期
	 *@return
	 *@since
	 */
    public static int getDaysBetween(Date d1, Date d2) {
    	return getDaysBetween(date2Calendar(d1), date2Calendar(d2));
    }
    
    /**
	 * 
	 *@description:计算两个任意时间中间的间隔天数
	 *@param d1Str yyyy-mm-dd 型日期字符参数
	 *@param d2Str yyyy-mm-dd 型日期字符参数
	 *@return
	 *@since
	 */
    public static int getDaysBetween(String d1Str, String d2Str) {
    	return getDaysBetween(parseDate(d1Str), parseDate(d2Str));
    }
    
    /**
	 * 
	 *@description:计算两个任意时间中间的间隔月数
	 *@param d1 Calendar型日期
	 *@param d2 Calendar型日期
	 *@return
	 *@since
	 */
    public static int getMonthsBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
		    Calendar swap = d1;
		    d1 = d2;
		    d2 = swap;
		}
		int year = d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR);
		int month = d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);
		int months = year*12+month;
		return months;
    }
    
    /**
	 * 
	 *@description:计算两个任意时间中间的间隔月数
	 *@param d1 Date型日期
	 *@param d2 Date型日期
	 *@return
	 *@since
	 */
    public static int getMonthsBetween(Date d1, Date d2) {
    	return getMonthsBetween(date2Calendar(d1), date2Calendar(d2));
    }
    
    /**
	 * 
	 *@description:计算两个任意时间中间的间隔月数
	 *@param d1Str yyyy-mm-dd 型日期字符参数
	 *@param d2Str yyyy-mm-dd 型日期字符参数
	 *@return
	 *@since
	 */
    public static int getMonthsBetween(String d1Str, String d2Str) {
    	return getMonthsBetween(parseDate(d1Str), parseDate(d2Str));
    }
    
    /**
	 * 
	 *@description:计算两个任意时间中间的间隔年数
	 *@param d1 Calendar型日期
	 *@param d2 Calendar型日期
	 *@return
	 *@since
	 */
    public static int getYearsBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
		    Calendar swap = d1;
		    d1 = d2;
		    d2 = swap;
		}
		int years = d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR);
		
		return years;
    }
    
    /**
	 * 
	 *@description:计算两个任意时间中间的间隔年数
	 *@param d1 Date型日期
	 *@param d2 Date型日期
	 *@return
	 *@since
	 */
    public static int getYearsBetween(Date d1, Date d2) {
    	return getYearsBetween(date2Calendar(d1), date2Calendar(d2));
    }
    
    /**
	 * 
	 *@description:计算两个任意时间中间的间隔年数
	 *@param d1Str yyyy-mm-dd 型日期字符参数
	 *@param d2Str yyyy-mm-dd 型日期字符参数
	 *@return
	 *@since
	 */
    public static int getYearsBetween(String d1Str, String d2Str) {
    	return getYearsBetween(parseDate(d1Str), parseDate(d2Str));
    }
    
    /**
     * 
     *@description: 计算两个任意时间的间隔毫秒数
     *@param d1 Calendar型日期
     *@param d2 Calendar型日期
     *@return
     *@since
     */
    public static long getMillisBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) {
		    Calendar swap = d1;
		    d1 = d2;
		    d2 = swap;
		}
		long millis = d2.getTimeInMillis() - d1.getTimeInMillis();
		
		return millis;
    }
	
    /**
	 * 
	 *@description: 计算两个任意时间的间隔毫秒数
	 *@param d1 Date型日期
	 *@param d2 Date型日期
	 *@return
	 *@since
	 */
    public static long getMillisBetween(Date d1, Date d2) {
    	return getMillisBetween(date2Calendar(d1), date2Calendar(d2));
    }
    
    /**
     * 根据Date转换Timestamp
     * @param date 要转换Date
     * @return 转换后Timestamp
     */
    public static Timestamp dateToTimestamp(Date date){
    	Timestamp tm = Timestamp.valueOf(formatDate(date, DATE_FORMST_STR_DATETIME));
    	return tm;
    }
    /**
     * 
     * @Description 13位的时间戳转date  
     * @Author      lixudong  
     * @Date        2018年12月12日 下午3:18:48  
     * @param @param time
     * @param @return 参数  
     * @return Date 返回类型   
     * @throws
     */
    public static Date timestamp13ToDate(String time){
		long temp = Long.valueOf(time);
		return new Timestamp(temp);      
	}
    /**
     * 
     * @Description 10位的时间戳转date  
     * @Author      lixudong  
     * @Date        2018年12月12日 下午3:19:55  
     * @param @param time
     * @param @return 参数  
     * @return Date 返回类型   
     * @throws
     */
    public static Date timestamp10ToDate(String time){
		long temp = Long.valueOf(time+"000");
		return new Timestamp(temp);      
	}
    /**
	 * 
	 *@description:获取日期的上n个月 格式：yyyy-MM
	 *@return 当前日期的上个月
	 *@since
	 */
	public static String getPrevMonthDate(Date date,int n) {
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - n);  
		return new SimpleDateFormat(DATE_FORMST_STR_YEAR3).format(calendar.getTime()); 
	}
}
