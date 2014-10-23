package cn.youhui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	public static String getDateTimeStr(long mills)
	{
		String dateStr = "";
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr = sdf.format(new Date(mills));
		} catch (Exception e) {
			return dateStr;
		}
		return dateStr;
	}
	
	public static long getTimeMillis(String dateStr) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long longDate = 0;
		//此处会抛异常
		java.util.Date date;
		try {
			date = sdf.parse(dateStr );
			longDate = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取毫秒数
		
		return longDate;
	}
	
	public static String getDateTime(long mills){
		String dateStr = "";
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
			dateStr = sdf.format(new Date(mills));
		} catch (Exception e) {
			return dateStr;
		}
		return dateStr;
	}
	
	public static void main(String[] args) {
		System.out.println(getTimeMillis("2014-4-12 00:00:00"));
	}
}
