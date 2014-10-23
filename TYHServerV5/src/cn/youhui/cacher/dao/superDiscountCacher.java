package cn.youhui.cacher.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.taobao.api.domain.Brand;

import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.common.Comp;
import cn.youhui.common.ParamConfig;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JedisKeyManager;

public class superDiscountCacher {

	private static final Logger log = Logger.getLogger(superDiscountCacher.class);
	private static final String cacheKey = ParamConfig.superDiscount_TAG;
	private static final String relationcacheKey = ParamConfig.relationcache_TAG;
	private static superDiscountCacher instance = null;
	private static final int pageSize = 18;
	public superDiscountCacher(){}
	public static superDiscountCacher getInstance() {
		if (instance == null){
			instance = new superDiscountCacher();
		}
		return instance;
	}
	
	
	public List<SuperDiscountBean> getSuperDisCountListByDate(String disCount_timestamp)
	{
		List<SuperDiscountBean> list = new ArrayList<SuperDiscountBean>();;
		String value = new JedisHashManager(relationcacheKey).get(disCount_timestamp);
		if(value!=null){
			String ids [] = value.split(";");
			for (int i = ids.length-1; i >=0; i--) {
				SuperDiscountBean bean = this.getSuperDiscountById(ids[i]);
				if(bean != null){
					list.add(bean);
				}
			}
		}
		return list;	
	}
	
	public int getTotalPags(String tomorrow){
		Map<String,String> map = new JedisHashManager(relationcacheKey).getAll();
		Iterator<String> it= map.keySet().iterator();
		List<Long> l=new ArrayList<Long>();
		while(it.hasNext()){
			String key =it.next();
			Long day=Long.parseLong(key);
			if(!day.equals(tomorrow)){
				l.add(day);
			}
		}
		System.out.println("========================>>>"+l.size());
		if(l.size()%ParamConfig.superNumPerPage==0){
			return l.size()/ParamConfig.superNumPerPage;
		}else{
			return l.size()/ParamConfig.superNumPerPage+1;
		}
	}
	public List<Long> getPags(int pagNow,String tomorrow){
		Map<String,String> map = new JedisHashManager(relationcacheKey).getAll();
		Iterator<String> it= map.keySet().iterator();
		List<Long> l=new ArrayList<Long>();
		System.out.println("----------------------------->");
		while(it.hasNext()){
			String key =it.next();
			
			System.out.println(key);
			Long day=Long.parseLong(key);
			
			if(Long.parseLong(key)<Long.parseLong(tomorrow)){
				l.add(day);
			}
		}
		Comp comparator=new Comp();
		Collections.sort(l, comparator);
		
		int a=(pagNow-1)*ParamConfig.superNumPerPage;
		int b=(pagNow-1)*ParamConfig.superNumPerPage+ParamConfig.superNumPerPage;
		List<Long> l2=new ArrayList<Long>();
		for(int i=0;i<l.size();i++){
			if(i>=a&&i<b){
				l2.add(l.get(i));
			}
		}
		return l2;
	}
	
	public void delSuperDiscountById(String id)
	{
		new JedisHashManager(cacheKey).delete(id);
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

}
