package cn.youhui.itemDAO;

import java.net.URLEncoder;

import cn.youhui.platform.util.NetManager;


public class UpdateAliyunItemJob extends Thread{

public static boolean runtimedebug = true;
	
	public static String getHttpUrl() {
		return runtimedebug ? "http://b17.cn/" : "http://127.0.0.1:8080/AliyunSkipServer/";
	}
	
	public String itemid;
	
	public String picUrl;
	
	public String price;
	
	public String priceHigh;
	
	public String title;
	
	public UpdateAliyunItemJob(String itemid,String picUrl,String price,String priceHigh,String title){
		this.itemid = itemid;
		this.picUrl = picUrl;
		this.price = price;
		this.priceHigh = priceHigh;
		this.title = title;
		
	}
	
	public void run(){
//		System.out.println(getHttpUrl()+"/refreshitem?itemid="+itemid+"&title="+title+"&price="+price+"&price_high="+priceHigh+"&pic_url="+picUrl);
		updateItem(itemid, picUrl, price, priceHigh, title);

	}
	
	public static void updateItem(String itemid,String picUrl,String price,String priceHigh,String title){
		try {
//			System.out.println(getHttpUrl()+"/refreshitem?itemid="+itemid+"&title="+title+"&price="+price+"&price_high="+priceHigh+"&pic_url=");
			String content = NetManager.send(getHttpUrl()+"/refreshitem", "itemid="+itemid+"&title="+URLEncoder.encode(title, "utf-8")+"&price="+price+"&price_high="+priceHigh+"&pic_url="+picUrl);
//			System.out.println(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
