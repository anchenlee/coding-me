package cn.youhui.platform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

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
}
