package cn.youhui.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class SuperTools {

	public static String getTomorrow(){
		Long timestamp=new Date().getTime()+Long.parseLong("86400000");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(timestamp);
		try{
			return sdf.parse(date).getTime()+"";
		}catch(Exception e){
			e.printStackTrace();
		}
		return timestamp+"";
	}
	public static String getTomorrow2(){
		Long timestamp=new Date().getTime()+Long.parseLong("86400000")*2;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(timestamp);
		try{
			return sdf.parse(date).getTime()+"";
		}catch(Exception e){
			e.printStackTrace();
		}
		return timestamp+"";
	}
	public static String getTomorrow3(){
		Long timestamp=new Date().getTime()+Long.parseLong("86400000")*3;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(timestamp);
		try{
			return sdf.parse(date).getTime()+"";
		}catch(Exception e){
			e.printStackTrace();
		}
		return timestamp+"";
	}
	
	
	public static String getToday(){
		Long timestamp=new Date().getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(timestamp);
		try{
			return sdf.parse(date).getTime()+"";
		}catch(Exception e){
			e.printStackTrace();
		}
		return timestamp+"";
	}
	public static String getYesterday(){
		Long timestamp=new Date().getTime()-Long.parseLong("86400000");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(timestamp);
		try{
			return sdf.parse(date).getTime()+"";
		}catch(Exception e){
			e.printStackTrace();
		}
		return timestamp+"";
	}
	public static String getYesterday2(){
		Long timestamp=new Date().getTime()-Long.parseLong("86400000")*2;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(timestamp);
		try{
			return sdf.parse(date).getTime()+"";
		}catch(Exception e){
			e.printStackTrace();
		}
		return timestamp+"";
	}
	
	public static String dat(long timestamp) {
		SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
		String date=sdf.format(timestamp);
		if(date.substring(0,1).equals("0")){
			date=date.substring(1,date.length());
		}
		return date;
	}
	
	public static String getImgType(String img){
		return img.substring(img.lastIndexOf("."),img.length());
	}
	
	//是否满足跳转明日预告条件 （是否5点过后了）
	public static boolean ifOnTime(){
		long timeNow=new Date().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.HOUR_OF_DAY,17);
		long l1=calendar.getTime().getTime();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		calendar2.set(Calendar.HOUR_OF_DAY, 24);
		long l2=calendar2.getTime().getTime();
		System.out.println(l1+"//"+l2);
		
		if(timeNow>=l1&&timeNow<=l2){
			return true;
		}
		
		//判断是否为周末 周末全天都能查看下期预告
		try{
			int dayOfWeek=dayForWeek();
			if(dayOfWeek==6||dayOfWeek==7){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	public static int dayForWeek() throws Exception {
		 Calendar c = Calendar.getInstance();
		 c.setTime(new Date());
		 int dayForWeek = 0;
		 if(c.get(Calendar.DAY_OF_WEEK) == 1){
		  dayForWeek = 7;
		 }else{
		  dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		 }
		 return dayForWeek;
	}
	public static void main(String[] args) throws Exception{
		System.out.println(dayForWeek());
	}
}
