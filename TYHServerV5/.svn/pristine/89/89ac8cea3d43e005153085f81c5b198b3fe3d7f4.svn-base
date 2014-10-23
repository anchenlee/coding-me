package cn.youhui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

public class Util {

	// 获取两个日期间的所有日期
	public static GregorianCalendar[] getBetweenDate(String d1, String d2)
			throws ParseException {
		Vector<GregorianCalendar> v = new Vector<GregorianCalendar>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		GregorianCalendar gc1 = new GregorianCalendar(), gc2 = new GregorianCalendar();
		gc1.setTime(sdf.parse(d1));
		gc2.setTime(sdf.parse(d2));
		do {
			GregorianCalendar gc3 = (GregorianCalendar) gc1.clone();
			v.add(gc3);
			gc1.add(Calendar.DAY_OF_MONTH, 1);
		} while (!gc1.after(gc2));
		return v.toArray(new GregorianCalendar[v.size()]);
	}

	
	public static String getSimpleImg(String pic_url, String size) {   
		if(pic_url.indexOf(".jpg") < 0){
			return pic_url;
		}
		pic_url = pic_url == null ? "" : pic_url;
		pic_url = pic_url.replaceAll(".jpg_.*?.jpg.*", "");
		if (!pic_url.equals("")) {
			pic_url = pic_url.replace(".jpg", "") + ".jpg_" + size + ".jpg";
		}
		pic_url = pic_url.replaceAll(" ", "");
		return pic_url;
	}
	
//	public static String getCustomImg(String pic_url, String size) {
//		if(pic_url.indexOf("taobaocdn.com") < 0 && pic_url.indexOf("alicdn.com")<0){
//			return pic_url;
//		}
//		pic_url = pic_url == null ? "" : pic_url;
//		pic_url = pic_url.replaceAll(".jpg_.*?.jpg", "");
//		if (!pic_url.equals("")) {
//			pic_url = pic_url.replace(".jpg", "");
//			pic_url += ".jpg_" + size + ".jpg";
//		}
//		return pic_url;
//	}
	public static String getCustomImg(String pic_url, String size) {
		if(pic_url.indexOf("taobaocdn.com") < 0 && pic_url.indexOf("alicdn.com")<0){
			return pic_url;
		}
		pic_url=pic_url.replaceAll("_.webp", "");
		String type="";
		pic_url = pic_url == null ? "" : pic_url;
		if(pic_url.length()!=pic_url.replaceAll(".jpg_.*?.jpg", "").length()){
			type="jpg";
		}else if(pic_url.length()!=pic_url.replaceAll(".png_.*?.jpg", "").length()){
			type="png";
		}else if(pic_url.indexOf(".jpg")>-1){
			type="jpg";
		}else if(pic_url.indexOf(".png")>-1){
			type="png";
		}
		pic_url = pic_url.replaceAll(".jpg_.*?.jpg", "");
		pic_url = pic_url.replaceAll(".png_.*?.jpg", "");
		pic_url = pic_url.replaceAll(".jpg", "");
		pic_url = pic_url.replaceAll(".png", "");
		if (!pic_url.equals("")) {
			pic_url = pic_url.replace(".jpg", "");
			pic_url += "."+type+"_" + size + ".jpg";
		}
		return pic_url;
	}
	public static void main(String[] args) {
		String pic_url="http://gi2.md.alicdn.com/bao/uploaded/i2/T1OSMNFUBaXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg";
//		String pic_url="http://image.taobao.com/bao/uploaded/i4/17275037431468359/T1_UlMFa4eXXXXXXXX_!!2-item_pic.png";
		String size="b";
		System.out.println(getCustomImg(pic_url, size));
	}

	/**
	 * 获取小图片
	 * */
	public static String getSmallImg(String pic_url) {
		if(pic_url.indexOf("taobaocdn.com") < 0){
			return pic_url;
		}
		pic_url = pic_url == null ? "" : pic_url;
		if (!pic_url.equals("")) {
			if (pic_url.indexOf("_b.jpg") != -1) {
				pic_url = pic_url.replace("_b.jpg", "") + "_m.jpg";
			} else if (pic_url.indexOf("_m.jpg") == -1) {
				pic_url = pic_url + "_m.jpg";
			}
		}
		return pic_url;
	}

	/**
	 * 获取大图片
	 * */
	public static String getBigImg(String pic_url) {
		if(pic_url.indexOf("taobaocdn.com") < 0){
			return pic_url;
		}
		pic_url = pic_url == null ? "" : pic_url;
		if (!pic_url.equals("")) {
			if (pic_url.indexOf("_m.jpg") != -1) {
				pic_url = pic_url.replace("_m.jpg", "") + "_b.jpg";
			} else if (pic_url.indexOf("_b.jpg") == -1) {
				pic_url = pic_url + "_b.jpg";
			}
		}
		return pic_url;
	}

	/**
	 * 获取大图片
	 * */
	public static String get310X310Img(String pic_url) {
		if(pic_url.indexOf("taobaocdn.com") < 0){
			return pic_url;
		}
		pic_url = pic_url == null ? "" : pic_url;
		if (!pic_url.equals("")) {
			if (pic_url.indexOf("_m.jpg") != -1) {
				pic_url = pic_url.replace("_m.jpg", "") + "_310x310.jpg";
			} else if (pic_url.indexOf("_b.jpg") == -1) {
				pic_url = pic_url + "_310x310.jpg";
			} else {
				pic_url = pic_url.replace("_b.jpg", "") + "_310x310.jpg";
			}
		}
		return pic_url;
	}

	/**
	 * 正则过滤html
	 * */
	public static String filterHtml(String str) {
		// 正在表达式过滤html标签
		str = str.replaceAll("<(?)[^>]+>", "");
		return str;
	}

	/**
	 * 格式化数据
	 */
	public static String priceFormat(String price) {

		if (price != null && !price.equals("")) {
			double priceDouble = Double.parseDouble(price);
			priceDouble = priceDouble * 100;
			String priceStr = priceDouble + "";
			int pos = priceStr.indexOf(".");
			return priceStr.substring(0, pos);
		} else {
			return "0";
		}

	}

	// 将实际戳变为指定类型的时间
	public static String stamp2Data(long time, String format) {
		Date date = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	/**
	 * 将指定String类型转换为long
	 * 
	 * @param date
	 *            时间数据 如1998-08-97
	 * @param format
	 *            时间的格式如 yyyy-MM-dd 时间格式要与date 一致
	 * @return long
	 */
	public static long getTimestamp(String dateStr, String format) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(dateStr);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

}
