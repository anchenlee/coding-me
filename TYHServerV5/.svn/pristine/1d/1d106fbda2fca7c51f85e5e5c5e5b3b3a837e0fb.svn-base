
package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;
import cn.youhui.bean.JuhuasuanBean;
import cn.youhui.common.X;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JedisSortedSetManager;
import cn.youhui.manager.JuhuasuanNewManager;

public class JuHuaSuanCacher {
	static String YOUHUI_JuHuaSuan = "youhui.juhuasuan.new";
	static JuHuaSuanCacher cacher = new JuHuaSuanCacher();
	private static String tagCacheKey = X.CachePrefix.TAG_TO_TAG;
	
	private static final String PinPaiTuanTagId = "609";
	
	private static String tagItemCacheKey = X.CachePrefix.TAG_TO_ITEM;
	
	private static String juBrandKeyId = "010";
	
	public static JuHuaSuanCacher getInstance(){
		return cacher;
	}
	
	//获取对应类别下的数据
	public ArrayList<JuhuasuanBean> getList(String type){
		ArrayList<JuhuasuanBean> list = new ArrayList<JuhuasuanBean>();
		
		Gson g = new Gson();
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		String context = "";
		
		context = jedis.hget(getKey(), type);
		
		try{
			JSONArray array = new JSONArray(context);
			
			for(int i = 0;i<array.length();i++){
				JuhuasuanBean b = new JuhuasuanBean();
				b = g.fromJson(array.getString(i).toString(), JuhuasuanBean.class);		
				list.add(b);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JedisDBIns.getInstance().release(jedis);
		return list;
	}
	
		public List<JuhuasuanBean> getList(String type, int page, int pageSize){
			List<JuhuasuanBean> list = new ArrayList<JuhuasuanBean>();
			if(page < 1){
				page = 1;
			}
			Gson g = new Gson();
			Map<String, String> jhslist = new JedisHashManager(getKey() + type).getAll();
			System.out.println(jhslist.size());
			if(jhslist != null && jhslist.size() > 0){
				int index = 0;
				for(Map.Entry<String, String> m : jhslist.entrySet()){
					if(index < (page-1)*pageSize){
						index ++;
						continue;
					}
					index ++;
					JuhuasuanBean bean = g.fromJson(m.getValue(), JuhuasuanBean.class);
					list.add(bean);
					if(list.size() >= pageSize){
						break;
					}
				}
			}
			
			return list;
		}
		
		
		public List<JuhuasuanBean> getAllList(String type){
			List<JuhuasuanBean> list = new ArrayList<JuhuasuanBean>();
			Gson g = new Gson();
			Map<String, String> jhslist = new JedisHashManager(getKey() + type).getAll();
			if(jhslist != null && jhslist.size() > 0){
				for(Map.Entry<String, String> m : jhslist.entrySet()){
					JuhuasuanBean bean = g.fromJson(m.getValue(), JuhuasuanBean.class);
					list.add(bean);
				}
			}
			
			return list;
		}
		
		public long getTotal(String type){
			return new JedisHashManager(getKey() + type).size();
		}
	
	
	//设置对应类别下的数据
	public boolean setListold(ArrayList<JuhuasuanBean> list,String type){
		
		boolean flag = false;
		
		if(list!=null&&list.size()>0){
			Gson g = new Gson();
			String context = g.toJson(list);
			Jedis jedis = JedisDBIns.getInstance().getJedis();
			long i = jedis.hset(getKey(), type, context);
			JedisDBIns.getInstance().release(jedis);
			flag = i>0?true:false;
		}
		return flag;
	}
	
	//设置对应类别下的数据
	public boolean setList(ArrayList<JuhuasuanBean> list,String type){
		
		boolean flag = false;
		
		if(list!=null&&list.size()>0){
			Gson g = new Gson();
			Map<String, String> map = new HashMap<String, String>();
			for(JuhuasuanBean bean : list){
				String jhs = g.toJson(bean);
				map.put(bean.getItemid(), jhs);
			}
			if(map != null && map.size()>0){
				new JedisHashManager(getKey() + type).clean();
			}
			new JedisHashManager(getKey() + type).setAll(map);
		}
		return flag;
	}
	
	public boolean exists(String type){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		boolean flag = jedis.hexists(getKey(), type);
		JedisDBIns.getInstance().release(jedis);
		return flag;
	}
	
	public boolean deleteAll(){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		long i = jedis.del(getKey());
		JedisDBIns.getInstance().release(jedis);
		return i>0?true:false;
	}
	
	public static boolean isExistItem(String itemid){
		if(isExistJuBrand(itemid)){
			return true;
		}
		return new JedisHashManager(YOUHUI_JuHuaSuan + "-1").isExist(itemid);
	}
	
	public static boolean isExistJuBrand(String itemid){
			
		Set<String> set = new JedisSortedSetManager(tagItemCacheKey+juBrandKeyId).getAll();
		if(set.contains(itemid)) return true;
//				System.out.println(set.contains(itemid));
		
		return false;
	}
	
	public String getKey(){
		return YOUHUI_JuHuaSuan;
	}
	
	public static void main(String[] args) {
//		System.out.println(isExistItem("37126505665"));
//		long s = System.currentTimeMillis();
//		List<JuhuasuanBean> list = JuHuaSuanCacher.getInstance().getList("0", 60, 12);
//		long s1 = System.currentTimeMillis();
//		System.out.println("take : "+ (s1 - s));
//		System.out.println(list.size());
//		System.out.println(JuHuaSuanCacher.getInstance().getTotal("0"));
//		System.out.println(isExistJuBrand("37126505665"));
	}
}
