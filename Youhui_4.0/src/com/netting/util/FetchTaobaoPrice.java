package com.netting.util;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;



public class FetchTaobaoPrice {

	
	public static Double getPriceItem(String itemid) {
		if (itemid == null)
			itemid = "";
		String content = "";
		try {
			content = NetManager
					.getContent("http://a.m.taobao.com/ajax/sku.do?item_id="
							+ itemid + "&v=0");
			System.out.println("gfgfgfg"+content);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		double price = -1;
		if (!content.equals("") && content.startsWith("{")) {
			try {
				int start = content.indexOf("promoPrice");
				int end = content.indexOf("quantity");
				String pricestr = content.substring(
						start + "promoPrice".length() + 3, end - 3);
				if (pricestr.indexOf("-") > 0) {
					pricestr = pricestr.substring(0, pricestr.indexOf("-"));
				}
				pricestr = pricestr.replaceAll("\"", "").replaceAll(",", "");
				price = Double.parseDouble(pricestr);
			} catch (Exception e) {
				e.printStackTrace();
				return -1.0;
			}
		}
		return price;
	}

	public static double getPromoPriceFromWeb(String itemId) {
		String url = "http://a.m.taobao.com/i" + itemId + ".htm?v=1";
		String content = "";
		try {
			content = NetManager.getContent(url);
			System.out.println("here"+content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double price = -1;
		Matcher m_new_price = Pattern.compile(
				"<strong class=\"oran\">.*?</strong>").matcher(content);
		if (m_new_price.find()) {
			String content1 = m_new_price.group();
			Pattern pattern1 = Pattern.compile(">.*-");
			Matcher m_item = pattern1.matcher(content1);
			if (m_item.find()) {
				String precontent = m_item.group().replaceAll(">", "");
				price = Double.parseDouble(precontent.replaceAll("-", ""));
			} else
				price = Double.parseDouble(m_new_price.group().replaceAll(
						"[^0-9.]", ""));
		}
		else{
			m_new_price = Pattern.compile(
					"<span\\s*style=\"color:#ffffff;\">.*?</span>").matcher(content);
			if (m_new_price.find()) {
				String content1 = m_new_price.group();
				Pattern pattern1 = Pattern.compile(">.*<");
				Matcher m_item = pattern1.matcher(content1);
				if (m_item.find()) {
					String precontent = m_item.group().replaceAll(">", "");
					price = Double.parseDouble(precontent.replaceAll("<", "").replace("￥", ""));
				} else
					price = Double.parseDouble(m_new_price.group().replaceAll(
							"[^0-9.]", ""));
			}
		}
				
		return price;
	}
	
	public static void main(String[] args) {
		System.out.println(getPriceItem("14602923076"));
	}
}
