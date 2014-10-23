package cn.suishou.some.util;

public class ImgUtil {

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
}
