package cn.youhui.cache.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.itemDAO.SecondKillDAO;
import cn.youhui.itemDAO.SuperDiscountDAO;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.jedis.JedisHashManager;
import cn.youhui.platform.jedis.JedisKeyManager;

public class superDiscountCacher {

//	private static final Logger log = Logger.getLogger(superDiscountCacher.class);
	private static final String cacheKey = param.superDiscount_TAG;
	private static final String relationcacheKey = param.relationcache_TAG;
	private static superDiscountCacher instance = null;
	private static final int pageSize = 18;
	public superDiscountCacher(){}
	public static superDiscountCacher getInstance() {
		if (instance == null){
			instance = new superDiscountCacher();
		}
		return instance;
	}
	
	public void addRelation(String id ,String disCount_timestamp)
	{
		if (new JedisHashManager(relationcacheKey).isExist(disCount_timestamp)) {
			String ids = new JedisHashManager(relationcacheKey).get(disCount_timestamp);
			if (!ids.contains(id + ";")) {
				ids += id + ";";
			}
			new JedisHashManager(relationcacheKey).add(disCount_timestamp, ids);
		}else
		{
			new JedisHashManager(relationcacheKey).add(disCount_timestamp, id + ";");
		}
	}
	
	public void delRelation(String newIds ,String disCount_date)
	{
		new JedisHashManager(relationcacheKey).add(disCount_date, newIds);
	}
	
	
	public List<SuperDiscountBean> getSuperDisCountListByDate(String disCount_date)
	{
		List<SuperDiscountBean> list = new ArrayList<SuperDiscountBean>();;
		String value = new JedisHashManager(relationcacheKey).get(disCount_date);
		String ids [] = value.split(";");
		for (int i = ids.length-1; i > 0; i--) {
			list.add(this.getSuperDiscountById(ids[i]));
		}
		return list;	
	}
	
	public void addSuperDiscount(String id , SuperDiscountBean sd)
	{
		Gson gson = new Gson();
		new JedisHashManager(cacheKey).add(id, gson.toJson(sd ,SuperDiscountBean.class));
		this.addRelation(id, sd.getDiscountTimestamp()+"");
	}
	
	public void delSuperDiscountById(String id,String disCount_date)
	{
		
		System.out.println("--------------------------------->"+id+":::"+disCount_date);
		new JedisHashManager(cacheKey).delete(id);
		String ids = new JedisHashManager(relationcacheKey).get(disCount_date);
		String newIds = "";
		newIds = ids.replace(id + ";", "");
		System.out.println("FFFFFFFFFFFFFKKKKK:::"+newIds);
		if(newIds.equals("")){
			System.out.println("===========================>>>");
			new JedisHashManager(relationcacheKey).delete(disCount_date);
		}
		this.delRelation(newIds, disCount_date);
	}
	
	public boolean updateSuperDiscountById(String id,SuperDiscountBean sd)
	{
		boolean flag = false;
		SuperDiscountBean  _sd = this.getSuperDiscountById(id);
		if (_sd != null) {
			this.addSuperDiscount(id, sd);
			flag = true;
		}
		return flag;
	}
	
	public SuperDiscountBean getSuperDiscountById(final String id) {
		SuperDiscountBean sd = null;
		String superDiscountStr = new JedisHashManager(cacheKey).get(id);
		if (superDiscountStr==null || superDiscountStr.equals("")) {
			return null;
		}
		sd = new Gson().fromJson(superDiscountStr, SuperDiscountBean.class);
		System.out.println(sd);
		return sd;
	}

	public void reload(){
		List<SuperDiscountBean> list=SuperDiscountDAO.getInstance().getAll();
		for(SuperDiscountBean sb:list){
			getInstance().addSuperDiscount(sb.getId()+"", sb);
		}
	}
	
//	public List<SuperDiscountBean> getBrands(int page){
//		List<SuperDiscountBean> list = new ArrayList<SuperDiscountBean>();
//		JedisHashManager redis = new JedisHashManager(cacheKey);
//		List<String> AllfiledsStr = new JedisHashManager(cacheKey).getAllField();
//		ArrayList<String> filed_ids = new ArrayList<String>();
//		for (int i = (page-1)*pageSize; i < AllfiledsStr.size() && i < page*pageSize; i++) {
//			filed_ids.add(AllfiledsStr.get(i));
//		}
//		List<String> sd_ids = redis.getListByFields(filed_ids);
//		Gson gson = new Gson();
//		for (int i = 0; i < sd_ids.size(); i++) {
//			SuperDiscountBean sd = gson.fromJson(sd_ids.get(i), SuperDiscountBean.class);
//			if (sd != null) {
//				list.add(sd);
//			}
//		}
//		return list;
//	}

	
	public static void main(String[] args) {
		getInstance().reload();
	}
}
