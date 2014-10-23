package cn.suishou.some.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchData {

	/**
	 * 0：正常 1:表示即将开始 2：不可购买 
	 * @param itemId
	 * @return
	 */
	public static int isSoldOut(String itemId){
		int status = 0;
		try {
			String content = NetManager.getInstance().getContent("http://a.m.taobao.com/i" + itemId + ".htm?v=1");
//			System.out.println(content);
			Matcher m = Pattern.compile("<span style=\"color:red;\">.*?</span>").matcher(content);
			if(m.find()){
				String statusContent = m.group();
				if(statusContent.contains("即将开始")){
					return 1;
				}else if(statusContent.contains("不可购买")){
					return 2;
				}
			}
			else if(content.contains(itemId) && content.contains("价格")){
				return 0;
			}
			if(content == null || "".equals(content)){
				return 2;
			}
			status = 2;
		} catch (Exception e) {
			return 2;
		}
	
		return status;
	}
	
	public static void main(String[] args) {
		System.out.println( isSoldOut("23646240072"));
	}
}
