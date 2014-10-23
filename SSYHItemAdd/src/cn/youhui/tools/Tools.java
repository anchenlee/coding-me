package cn.youhui.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.youhui.platform.util.NetManager;

public class Tools {

	public static void main(String[] args) {
		try{
			long l=System.currentTimeMillis();
			NetManager.getInstance().getContent("http://item.taobao.com/item.htm?ft=t&id=16834217973");
			System.out.println(System.currentTimeMillis()-l);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static List<String> getItemImgFromTaobao(String itemid){
		List<String> list = new ArrayList<String>();
		if(itemid == null || "".equals(itemid)){
			return null;
		}
		try {
			long l=System.currentTimeMillis();
			String content = NetManager.getInstance().getContent("http://detail.tmall.com/item.htm?id="+itemid);
			long l2=System.currentTimeMillis();
			System.out.println("ffffffffffffff:::::"+(l2-l));
//			String content = NetManager.getContent("www.baidu.com");
			Pattern pattern = Pattern.compile("<ul id=\"J_UlThumb\".*?</ul>");
			Matcher matcher = pattern.matcher(content);
			if(matcher.find()){
				String imgs = matcher.group();
				Pattern pattern1 = Pattern.compile("src=.*?/>");
				Matcher matcher1 = pattern1.matcher(imgs);
				while(matcher1.find()){
					String img = matcher1.group();
					img = img.replaceAll("src=", "").replaceAll("/>", "").replaceAll("\"", "");
					img =getSmallImg(img);
					img = img.replaceAll(" ", "");
					list.add(img);
				}
			}
			if(content == null || "".equals(content)){
				content = NetManager.getInstance().getContent("http://a.m.tmall.com/i"+itemid+".htm");
				pattern = Pattern.compile("class=\"mt\".*?</table>");
				matcher = pattern.matcher(content);
				if(matcher.find()){
					String imgs = matcher.group();
					Pattern pattern1 = Pattern.compile("src=\".*?\"");
					Matcher matcher1 = pattern1.matcher(imgs);
					while(matcher1.find()){
						String img = matcher1.group();
						img = img.replaceAll("src=", "").replaceAll("\"", "");
						img = getSmallImg(img);
						img = img.replaceAll(" ", "");
						list.add(img);
					}
				}
			}
			
			System.out.println(System.currentTimeMillis()-l2);
			
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}
	
	public static String getSmallImg(String pic_url) 
	{
		if(pic_url.indexOf("taobaocdn.com") < 0 && pic_url.indexOf("taobao.com") < 0  && pic_url.indexOf("alicdn.com") < 0)
		{
			return pic_url;
		}
		pic_url = pic_url == null ? "" : pic_url;
		
		String reg_1 = "png_.*?.jpg";
		String reg_2 = "jpg_.*?.jpg";
		
		Pattern p_1 = Pattern.compile(reg_1);
		Pattern p_2 = Pattern.compile(reg_2);
		
		Matcher m_1 = p_1.matcher(pic_url);
		if (m_1.find())
		{
			pic_url = pic_url.replaceAll(reg_1, "");
			if (!pic_url.equals("")) 
			{
				pic_url += "jpg_600x600.jpg";
			}
			return pic_url;
		}
		Matcher m_2 = p_2.matcher(pic_url);
		if (m_2.find())
		{
			pic_url = pic_url.replaceAll(reg_2, "");
			if (!pic_url.equals("")) 
			{
				pic_url += "jpg_600x600.jpg";
			}
			return pic_url;
		}
		pic_url = pic_url.replaceAll(" ", "");
		return pic_url;
	}
}
