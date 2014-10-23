package cn.youhui.utils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.youhui.bean.Coupons;
import cn.youhui.dao.MySQLDBIns;

public class FetchCoupons {

	
	
	public static List<Coupons> fetchCoupons(String shop){
		List<Coupons> list = new ArrayList<Coupons>();
		try {
			String content = NetManager.getInstance().getContent("http://youhui.kfc.com.cn/");
			list = getKFCCoupons(content);
		} catch (Exception e) {
			return null;
		}
		return list;
	}
	
	public static List<Coupons> getKFCCoupons(String content){
		if(content == null || "".equals(content)){
			return null;
		}
		List<Coupons> list = new ArrayList<Coupons>();
		System.out.println(content);
		Pattern pattern = Pattern.compile("<div class=\"list\" id=\"conyouhui3\".*?</div>");
		Matcher matcher = pattern.matcher(content);
		if(matcher.find()){
			String items = matcher.group();
			pattern = Pattern.compile("<dl class=\"conyouhui\".*?</dl>");
			matcher = pattern.matcher(items);
			while(matcher.find()){
				String item = matcher.group();
				Coupons bean = new Coupons();
				Pattern pattern1 = Pattern.compile("title=\".*?\"");
				Matcher matcher1 = pattern1.matcher(item);
				if(matcher1.find()){
					String title = matcher1.group().replaceAll("title=\"", "").replaceAll("\"", "");
					bean.setTitle(title);
					bean.setDesc(title);
				}
				
				pattern1 = Pattern.compile("bigimg=\".*?\"");
				matcher1 = pattern1.matcher(item);
				if(matcher1.find()){
					String detailImg = matcher1.group().replaceAll("bigimg=\"", "").replaceAll("\"", "");
					bean.setDetail_pic(detailImg);
				}
				
				pattern1 = Pattern.compile("src=\".*?\"");
				matcher1 = pattern1.matcher(item);
				if(matcher1.find()){
					String img = matcher1.group().replaceAll("src=\"", "").replaceAll("\"", "");
					bean.setPicUrl(img);
				}
				
				bean.setClickUrl("http://youhui.kfc.com.cn/");
				bean.setShopID("451615615");
				bean.setShopName("KFC");
				bean.setCategoryId("1");
				bean.setOrginPrice(15.0);
				bean.setNowPrice(12.5);
				bean.setLat(36.5);
				bean.setLon(63.4);
				bean.setGouMaiRenShu(56423);
				bean.setAttention("过期日期：2014-12-13");
				list.add(bean);
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		List<Coupons> list = fetchCoupons("");
		Connection conn = MySQLDBIns.getInstance().getConnection();
		if(list != null && list.size() >0){
			for(Coupons bean : list){
				System.out.println(bean.getTitle());
//				CouponsDAO.insertItem(bean, conn);
			}
		}
		MySQLDBIns.getInstance().release(conn);
	}
}
