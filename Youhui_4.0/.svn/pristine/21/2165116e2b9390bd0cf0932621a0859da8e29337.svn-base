package com.netting.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

import jxl.Cell;
import jxl.CellType;
import jxl.NumberCell;

/**
 * 工具类
 * @author YAOJIANBO
 *
 */
public class CodeUtil 
{
	/**
	 * 获取某日某时刻毫秒数
	 * @param dateStr 年月日格式：2013-07-25
	 * @param timeStr 时分秒格式：15:17:16
	 * @return 某日某时刻毫秒数
	 * @throws ParseException
	 */
	public static long getTimeMillis(String dateStr, String timeStr) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//此处会抛异常
		java.util.Date date = sdf.parse(dateStr + " " + timeStr);
		
		//获取毫秒数
		long longDate = date.getTime();
		
		return longDate;
	}
	
	/**
	 * 获取某日某时刻毫秒数
	 * @param dateTiemStr 年月日时分秒格式：2013-07-25 15:17:16
	 * @return 某日某时刻毫秒数
	 * @throws ParseException
	 */
	public static long getTimeMillis_2(String dateTiemStr) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//此处会抛异常
		java.util.Date date = sdf.parse(dateTiemStr);
		
		//获取毫秒数
		long longDate = date.getTime();
		
		return longDate;
	}
	
	/**
	 * 获取日期
	 * @param mills
	 * @return
	 */
	public static String getDateStr(long mills)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(new Date(mills));
		return dateStr;
	}
	
	/**
	 * 获取日期时间
	 * @param mills
	 * @return
	 */
	public static String getDateTimeStr(long mills)
	{
		if (mills > 0l)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(new Date(mills));
			return dateStr;
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * 获取日期字符串,格式：2013-09-11 11:23:15
	 * @return
	 */
	public static String getDateTimeStr()
	{
		Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DAY_OF_MONTH, -1);
		String datetime = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", cal);
		return datetime;
	}
	
	/**
	 * 获取日期字符串,格式：2013-09-11
	 * @return
	 */
	public static String getDateStr()
	{
		Calendar cal = Calendar.getInstance();
		String datetime = String.format("%1$tY-%1$tm-%1$td", cal);
		return datetime;
	}
	
	/**
	 * 计算两个时间相隔天数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException 
	 */
	public static long differ(Date date1, Date date2) throws ParseException
	{
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		date1 = ft.parse(ft.format(date1));
		date2 = ft.parse(ft.format(date2));
		
		long def = 0;
		def = date1.getTime() - date2.getTime();
		def = Math.abs(def);
		def = def/1000/60/60/24;
		
		return Math.abs(def);
	}
	
	/**
	 * 获取EXCEL文件中每个单元格的内容(数值类型)
	 * @param cellA1
	 * @return
	 */
	public static String getCellValue(Cell cell)
	{
		String value="";
		if(cell != null)
		{
			if (cell.getType().equals(CellType.LABEL))
			{
				value = cell.getContents();
			}
			else if (cell.getType().equals(CellType.NUMBER)) 
			{
				NumberCell numberCell = (NumberCell) cell;
				long douval = (long)numberCell.getValue();
				value = ""+douval;
			}
		}
		return value;
	}
	
	/**
	 * 取第二天的日期字符串表示</p>
	 * 
	 * @return String 例：2012-12-28
	 */
	public static String getNextDateString(String date) 
	{
		if (null != date && date.length() >= 10)
		{
			Calendar ca = Calendar.getInstance();
			
			ca.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
			ca.set(Calendar.MONTH, Integer.parseInt(date.substring(5, 7)) - 1);
			ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(8, 10)));
			
			ca.add( Calendar.DATE, 1);
			
			String dateStr = String.format("%1$tY%1$tm%1$td", ca);
			dateStr = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6);
			return dateStr;
		}
		else
		{
			return "";
		}
		
	}
	
	/**
	 * 获取下一个月
	 * @param month
	 * @return
	 */
	public static String getNextMonth(String month) {
		String des = "";
		String year = month.substring(0, 4);
		if(month.length()==7) {
			if(month.substring(5,7).equals("12")) des = (Integer.parseInt(year) + 1) + "-" + "01" ;
			else if(Integer.parseInt(month.substring(5, 7)) >= 9) 
			{
				// 开始时间月份数大于9时，结束时间月份+1
				des = month.substring(0,4) + "-" + (Integer.parseInt(month.substring(5, 7)) + 1)  ;
			}
			else 
			{
				// 开始时间月份数<=9时，结束时间月份+1后前置0，如：2013-02-01
				des = month.substring(0,4) + "-0" + (Integer.parseInt(month.substring(5, 7)) + 1)  ;
			}
		}
		return des;
	}
	
	/**
	 * 获取下？个小时
	 * @param time
	 * @param statue
	 * @return
	 */
	public static String getNextHour(String time, int statue)
	{
		if(time != null && time.length() >= 13) 
		{
			Calendar cal = Calendar.getInstance();
	        
	        SimpleDateFormat ftime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
	        Date trialTime;
			try 
			{
				trialTime = ftime.parse(time+":00:00");
				cal.setTime(trialTime);
			} catch (ParseException e) 
			{
				e.printStackTrace();
			}        
	        
	        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + statue);
	        return ftime.format(cal.getTime()).substring(0, 13)+"";
		}
		else {
			return time;
		}
	}
	
	
	public static long getUnixTimestemp(String dateTiemStr) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long longDate = 0;
		//此处会抛异常
		java.util.Date date;
		try 
		{
			date = sdf.parse(dateTiemStr);
			longDate = date.getTime();
		} 
		catch (ParseException e) 
		{
			longDate = 0;
		}
		
		//获取毫秒数
		
		return longDate;
	}
	
	/**
	 * 获取图片的宽高比
	 * @return
	 * @throws IOException
	 */
	public static String getImgSizeProportion(String imgUrl)
	{
		/*
		File picture = new File("D:/IMG_20121031_215503.jpg");
		System.out.println(String.format("%.1f",picture.length()/1024.0));  
		*/
		String proportion = "";
		
		try
		{
			//new一个URL对象
	        URL url = new URL(imgUrl);
	        //打开链接
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        //设置请求方式为"GET"
	        conn.setRequestMethod("GET");
	        //超时响应时间为5秒
	        conn.setConnectTimeout(5 * 1000);
	        
	        //通过输入流获取图片数据
	        InputStream inStream = conn.getInputStream();
	        BufferedImage sourceImg =ImageIO.read(inStream); 
	        
	        double img_width = sourceImg.getWidth();
	        double img_height = sourceImg.getHeight();
	        if (img_width != 0 || img_height != 0)
	        {
	        	proportion = String.format("%.1f", (img_width / img_height));
	        }
		}
		catch (IOException e)
		{
			proportion = "";
		}
		return proportion;
	}
	
	public static String getMonthLastDay(){
		Calendar c = Calendar.getInstance();
		int MaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		  //按你的要求设置时间
		  c.set( c.get(Calendar.YEAR), c.get(Calendar.MONTH), MaxDay, 23, 59, 59);
		  //按格式输出
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		  String gtime = sdf.format(c.getTime()); //上月最后一天
//		  System.out.println(gtime);
		  return gtime;
	}
	
	public static String formEventToJson(String event){
		JSONObject jso = new JSONObject();
		Date now = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str1 = sdf.format(now);
		try {
			jso.put("date", str1);
			jso.put("event", event);
		} catch (JSONException e) {
			
		}
		return jso.toString();
	}
	
    public static String getRespJSONString(String status,String result) 
    {
    	JSONObject respOBJ = new JSONObject();
		try {
			respOBJ.put("status",  status);
			respOBJ.put("result",  result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respOBJ.toString();
    }
	
	
	public static void main(String[] args) throws ParseException
	{
//		System.out.println(getTimeMillis("2013-11-20", "00:00:00"));
//		System.out.println(getTimeMillis("2013-11-20", "23:59:59"));
//		System.out.println(getDateTimeStr());
//		System.out.println(getDateStr());
//		
//		System.out.println(7 / 2 + 1);
//		
//		double f = 111231.5585;
//		System.out.println(String.format("%.1f", f));
//		System.out.println(getDateTimeStr(1385515980000l));
		
		System.out.println(getImgSizeProportion("http://img4.duitang.com/uploads/item/201202/15/20120215171627_hkicr.jpg"));
	}
}
