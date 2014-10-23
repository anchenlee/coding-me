package cn.youhui.utils;

import org.json.JSONObject;

public class CityUtil {

	private static final String BaiduIPApi = "http://api.map.baidu.com/location/ip?ak=EC7ecd85f4986bffdd69e9f99c254e9f&coor=bd09ll&ip=";
	
	/**
	 * 根据ip 获取百度对应的城市ID
	 */
	public static String getBaiduCityCode(String ip){
		String cityId = null;
		try {
			String content = NetManager.getInstance().getContent(BaiduIPApi + ip);
			if(content != null && !"".equals(content)){
				JSONObject cjo = new JSONObject(content);
				if(cjo.has("content")){
					JSONObject ajo = cjo.getJSONObject("content");
					if(ajo.has("address_detail")){
						JSONObject ccjo = ajo.getJSONObject("address_detail");
						if(ccjo.has("city_code")){
							cityId = ccjo.getString("city_code");
						}
					}
				}
				
			}
		}catch(Exception e){
			//不写日志
		}
		return cityId;
	}
}