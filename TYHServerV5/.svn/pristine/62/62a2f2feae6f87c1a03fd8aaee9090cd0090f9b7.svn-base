package cn.youhui.common;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {

	private static ScheduledExecutorService scheduledExeSvc = null;

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];

	}

	public static String getMD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	public static String createToken() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			return getMD5Encode("12242872"
					+ getMD5Encode("c48c2177febefe4e195d9301408ba03f")
					+ "yaoyuewudi" + date);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	protected static class DemonThreadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable r) {
			// TODO Auto-generated method stub
			Thread t = new Thread(r);
			t.setDaemon(true);
			return t;
		}

	}



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

	public static String formatTime(int day) {
		return day / 10 > 0 ? day + "" : "0" + day;
	}

	public static String formcat(String start, String end, String content) {

		Pattern p1 = Pattern.compile(start + ".*?" + end);
		Matcher m = p1.matcher(content);
		if (m.find()) {
			return m.group().replace(start, "").replace(end, "");
		}
		return "";
	}

	public static String formcat(String start, String center, String end,
			String content) {
		Pattern p1 = Pattern.compile(start + center + end);
		Matcher m = p1.matcher(content);
		if (m.find()) {
			return m.group().replace(start, "").replace(end, "");
		}
		return "";
	}

	public static List<String> formcatArray(String start, String end,
			String content) {

		List<String> list = new ArrayList<String>();
		Pattern p1 = Pattern.compile(start + ".*?" + end);
		Matcher m = p1.matcher(content);
		while (m.find()) {
			list.add(m.group().replace(start, "").replace(end, ""));
		}
		return list;
	}

	public static String transCode(String str) {
		if (str == null) {
			str = "";
		}
		try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 将淘宝商户的webURL 转化为wap的url
	 * */
	public static String trans4Wap(String url) {
		if (!url.equals("")) {
			int index = url.indexOf("id=");
			url = url.substring(index + 3);
			url = "http://a.m.taobao.com/i" + url + ".htm";
		}
		return url;
	}

	public static String getPid(String url) {
		String pid = "0";
		if (!url.equals("")) {
			int index = url.indexOf("id=");
			pid = url.substring(index + 3);
		}
		return pid;
	}

	/**
	 * 获取小图片
	 * */
	public static String getSmallImg(String pic_url) {
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

//	public static String getCustomImg(String pic_url, String size) {
//		pic_url = pic_url == null ? "" : pic_url;
//		pic_url = pic_url.replaceAll(".jpg_.*?.jpg", "");
//		if (!pic_url.equals("")) {
//			pic_url = pic_url.replace(".jpg", "");
//			pic_url +=  ".jpg_" + size + ".jpg";
//		}
//		return pic_url;
//	}

	public static String getDefaultImg(String picUrl) {
		picUrl = picUrl == null ? "" : picUrl;
		if (!picUrl.equals("")) {
			picUrl = picUrl.replaceAll(".jpg_.*?jpg", "");
			picUrl = picUrl.replace(".jpg", "") + ".jpg";
		}
		return picUrl;
	}

	/**
	 * 获取大图片
	 * */
	public static String getBigImg(String pic_url) {
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
	/***
	 * 将hashtable 转换为string
	 * @param table
	 * @return
	 */
	
	public static String getParametersToString(Hashtable<String, String> table) {
		Enumeration<String> enu = table.keys();
		StringBuffer sb = new StringBuffer();
		String item = "";
		while (enu.hasMoreElements()) {
			item = enu.nextElement();
			sb.append(item + "=" + table.get(item) + "&");
		}
		String str = sb.toString();
		str = str.substring(0,str.length()-1);
		return str;
	}

}
