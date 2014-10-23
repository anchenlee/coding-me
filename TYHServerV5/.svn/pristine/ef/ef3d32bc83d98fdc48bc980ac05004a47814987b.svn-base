package cn.youhui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
	
	private static SimpleDateFormat ft1 = new SimpleDateFormat("M月dd日");
	
	private static SimpleDateFormat ftt = new SimpleDateFormat("yyyyMM");
	/**
	 * 计算两个时间相隔天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long differ(Date date1, Date date2){
		long quot = 0;
		try {
			date1 = ft.parse(ft.format(date1));
			date2 = ft.parse(ft.format(date2));
			quot = date1.getTime() - date2.getTime();
			quot = Math.abs(quot);
		}catch (ParseException e){
			e.printStackTrace();
		}
		quot = quot/1000/60/60/24;
		return Math.abs(quot);
	}
	
	/**
	 * 获取date当天凌晨的时间，如2013-05-16 21:20:00得到2013-05-16 00:00:00
	 * @param date
	 * @return
	 */
	public static long getMidnight(Date date){
		long ret = 0;
		try {
			ret = ft.parse(ft.format(date)).getTime();
		}catch (ParseException e){
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * 将时间 转化为中文时间
	 * @param time
	 * @return
	 */
	public static String changeCH(long time){
		String timeStr = "";
		long midnight = getMidnight(new Date());
		if(time > midnight){
			timeStr = "今天";
		}else if(time > midnight - 24*60*60*1000){
			timeStr = "昨天";
		}else{
			timeStr = ft1.format(new Date(time));
		}
		return timeStr;
	}
	
	/**
	 * 获取剩余时间
	 * @param time
	 * @return
	 */
	public static String getRestTimeCH(long time){
		long now =  System.currentTimeMillis();
		if(time <= now){
			return "";
		}
		long mn = time - now;
		long onedaymn = 24*60*60*1000;   //一天毫秒数
		long df = mn / onedaymn;
		if(df > 0){
			df ++;
			return df + "天";
		}else{
			long onehourmn = 60*60*1000;   //一个小时毫秒数
			long hour = mn / onehourmn;
			if(hour > 0){
				hour ++;
				return hour + "小时";
			}else{
				long onemiumn = 60*1000;
				long miu = mn / onemiumn;
				if(miu >0){
					miu ++;
					return miu + "分钟";
				}else{
					long m = mn / 1000;
					m ++;
					return m + "秒";
				}
			}
		}
	}

	
	public static String getRestTimeCH2(long time){
		long now =  System.currentTimeMillis();
		if(time <= now){
			return "";
		}
		long mn = (time - now)/1000;
		
		if(mn<=60*10){
			return mn/60+"分"+mn%60+"秒";
		}else{
			return getRestTimeCH(time);
		}
		
	}
	
	
	public static String getRestTimeCH3(long time){
		long now =  System.currentTimeMillis();
		if(time <= now){
			return "";
		}
		long mn = time - now;
		long onedaymn = 24*60*60*1000;   //一天毫秒数
		long df = mn / onedaymn;
		if(df > 0){
			df ++;
			return df + "天";
		}else{
			String str="";
			long onehourmn = 60*60*1000;   //一个小时毫秒数
			long hour = mn / onehourmn;
			if(hour > 0){
				str= hour + "小时";
			}
			long onemiumn = 60*1000;
			long miu = mn % onehourmn/onemiumn;
			if(miu >0){
				miu ++;
				str=str+ miu + "分钟";
			}
			return str;
		}
	}
	public static void main(String[] args) {
		System.out.println(getRestTimeCH3(Long.parseLong("1404792900000")));
	}
	
	/**
	 * 获取date当月第一天的时间，如2013-05-16 21:20:00得到2013-05-01 00:00:00
	 * @param date
	 * @return
	 */
	public static long getMonthFirst(Date date){
		long ret = 0;
		try {
			ret = ftt.parse(ftt.format(date)).getTime();
		}catch (ParseException e){
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 将毫秒的时间格式转换成 几月几日
	 * @param time
	 * @return
	 */
	public static String getMonthday(Long time){
		SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
		Date d=new Date(time);
		return sdf.format(d);
	}
	
	/**
	 * 将毫秒的时间格式转换成对应的月份
	 * @param time
	 * @return
	 */
	public static String getMonth(Long time){
		SimpleDateFormat sdf=new SimpleDateFormat("MM");
		Date d=new Date(time);
		return sdf.format(d)+"月";
		
	}
}
