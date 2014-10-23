package cn.youhui.ramdata;

import java.util.ArrayList;
import org.json.JSONArray;
import cn.youhui.bean.TianTianDaZheBean;
import cn.youhui.dao.JedisDBIns;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;



/**
 *  favorite items list for each user
 * 
 * 	Redis Data structure : sorted set
 *  Hash_key:X.CachePrefix.YOUHUI_TEJIA {
 * 		score		member
 * 		type 		json
 * }
 * 
 * ***/
public class TTTeJiaCacher {
	static 	String YOUHUI_TEJIA = "youhui.tejia";
	static TTTeJiaCacher cacher = new TTTeJiaCacher();
	
	public static TTTeJiaCacher getInstance(){
		return cacher;
	}
	
	//获取对应类别下的数据
	public ArrayList<TianTianDaZheBean> getList(String type){
		ArrayList<TianTianDaZheBean> list = new ArrayList<TianTianDaZheBean>();
		
		Gson g = new Gson();
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		
		
		String context = "";
		context = jedis.hget(getKey(), type);
		
		try{
			JSONArray array = new JSONArray(context);
			for(int i = 0;i<array.length();i++){
				TianTianDaZheBean b = new TianTianDaZheBean();
				b = g.fromJson(array.getString(i).toString(), TianTianDaZheBean.class);
				list.add(b);
			}
		}catch(Exception e){
				e.printStackTrace();
		}
		
		JedisDBIns.getInstance().release(jedis);
		return list;
	}
	
	//设置对应类别下的数据
	public boolean setList(ArrayList<TianTianDaZheBean> list,String type){
		
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
	
	
	
	
	public String getKey(){
		return YOUHUI_TEJIA;
	}
	
	
}
