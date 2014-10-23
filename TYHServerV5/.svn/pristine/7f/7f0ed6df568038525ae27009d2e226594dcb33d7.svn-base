package cn.youhui.ramdata;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import cn.youhui.bean.CouponsShop;
import cn.youhui.common.ParamConfig;
import cn.youhui.common.X;
import cn.youhui.manager.JedisHashManager;

public class CouponsShopCacher {
	public NumberFormat doubleFormat = new DecimalFormat(".000");
	private static String cacheKey = X.CachePrefix.Shop;
	private static CouponsShopCacher instance=null;
	public static  CouponsShopCacher getInstance(){
		if(instance==null){
			instance=new CouponsShopCacher();
		}
		return instance;
	}
	private CouponsShopCacher(){}
	
	public CouponsShop getCouponsShop(String shopId){
		String shopStr = new JedisHashManager(cacheKey).get(shopId);
		CouponsShop cp = null;
		cp = new Gson().fromJson(shopStr, CouponsShop.class);
		return cp;
	}
	
	//为分页做的根据多个shopIds取多条记录
	public List<CouponsShop> getCouponsShop(List<String> shopIds){
		List<CouponsShop> l=new ArrayList<CouponsShop>();
		for(int i=0;i<shopIds.size();i++){
			String shopId=shopIds.get(i);
			String shopStr = new JedisHashManager(cacheKey).get(shopId);
			CouponsShop cp = null;
			cp = new Gson().fromJson(shopStr, CouponsShop.class);
			l.add(cp);
		}
		return l;
	}
	
	//把所有shop存进redis
	public void instAllShopToRedis(List<CouponsShop> list){
		Map<String,String> map=new HashMap<String,String>();
		
		for(int i=0;i<list.size();i++){
			String shopId=list.get(i).getShopId();
			String json = new Gson().toJson(list.get(i));
			map.put(shopId, json);
		}
		new JedisHashManager(cacheKey).setAll(map);
	}
	
	//初次获取数据，存进redis里
	public void instToRedis(double lat,double lon,String ids){
		String key=lat+"_"+lon;
		new JedisHashManager(cacheKey).add(key, ids);
	}
	
	//方案1根据经纬度拿到shopIds
	public List<String> getCouponsShopIds(double lat,double lon,int pagNow){
		String k=lat+"_"+lon;
		String ids=new JedisHashManager(cacheKey).get(k);
		
		List<String> list=new ArrayList<String>();
		if(ids!=null&&!"".equals(ids)){
			for(int i=(pagNow-1)*ParamConfig.numPerPage;i<ParamConfig.numPerPage;i++){
				String shopId=ids.split(",")[i];
				list.add(shopId);
			}
		}
		return list;
		
	}
	
	//拿到表格区间内的shopIds
	public List<String> getCouponsShopFromLattice(double lat,double lon,int pagNow){
		String k=doubleFormat.format(lat)+"_"+doubleFormat.format(lon);
		String ids=new JedisHashManager(cacheKey).get(k);
		
		List<String> list=new ArrayList<String>();
		if(ids!=null&&!"".equals(ids)){
			for(int i=(pagNow-1)*ParamConfig.numPerPage;i<ParamConfig.numPerPage;i++){
				String shopId=ids.split(",")[i];
				list.add(shopId);
			}
		}
		return list;
	}
	
	//插入所有表格
	public void insertLatticeToRedis(Map<String,String> map){
		new JedisHashManager(cacheKey).setAll(map);
	}
	
	
	
}
