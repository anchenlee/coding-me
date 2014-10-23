
package cn.youhui.ramdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.SepaConfig;
import cn.youhui.common.X;
import cn.youhui.manager.JedisHashManager;
import cn.youhui.manager.JedisKeyManager;
import cn.youhui.manager.TagManager;


public class Tag2TagCacher {
	private static final Logger log = Logger.getLogger(Tag2TagCacher.class);
	private static String cacheKey = X.CachePrefix.TAG_TO_TAG;
	private static String cacheLock = X.CachePrefix.CACHE_STATUS + X.DOT + cacheKey;
	private static Tag2TagCacher instance = null;
	
	public static Tag2TagCacher getInstance(){
		if(instance == null) instance = new Tag2TagCacher();
		return instance;
	}
	
	
	public List<KeywordBean> getTagsByParentIdNoAll(String ptagid){
		return getTagsByParentId(ptagid, false);
	}
	
	public List<KeywordBean> getTagsByParentIdWithAll(String ptagid){
		if("833".equals(ptagid) || "795".equals(ptagid)){
			return getTagsByParentId(ptagid, false);
		}else{
			return getTagsByParentId(ptagid, true);
		}
	}
	
	public List<KeywordBean> getTagsByParentId(String ptagid, boolean iswithall){
		List<KeywordBean> ret = new ArrayList<KeywordBean>();
		String tagids = new JedisHashManager(cacheKey).get(ptagid);
		if(tagids != null && !"".equals(tagids)){
			if(iswithall){
				KeywordBean allBean = TagCacher.getInstance().getTag(ptagid);
				allBean.setKeyword("全部");
				ret.add(allBean);
			}
			
			String[] tagidss = tagids.split(SepaConfig.TAG_SEPA);
			for(String tagid : tagidss){
				KeywordBean bean = TagCacher.getInstance().getTag(tagid);
				if(bean != null){
					ret.add(bean);
				}
			}
			
			return ret;
		}else{
			return null;
		}
	}
	
	/**
	 * 获取父标签的第一个子标签
	 * @param ptagid
	 * @return
	 */
	public String getFirstTagIdByParentId(String ptagid){
		String tagids = new JedisHashManager(cacheKey).get(ptagid);
		if(tagids != null && !"".equals(tagids)){
			String[] tagidss = tagids.split(SepaConfig.TAG_SEPA);
			return tagidss[0];
		}else{
			return null;
		}
	}
	
	/*
	public List<KeywordBean> getPPTTagsByParentId(String ptagid)
	{
		List<KeywordBean> childBeans = new ArrayList<KeywordBean>();
		String tagids = new JedisHashManager(cacheKey).get(ptagid);
		if(tagids != null && !"".equals(tagids))
		{
			String[] tagidss = tagids.split(SepaConfig.TAG_SEPA);
			for(String tagid : tagidss)
			{
				if (null != tagid && !"".equals(tagid))
				{
					KeywordBean bean = TagCacher.getInstance().getTag(tagid);
					if(bean != null)
					{
						childBeans.add(bean);
					}
				}
			}
			
			return childBeans;
		}
		else 
		{
			return null;
		}
	}
	*/
	
	/**
	 * 更新tag下子tag
	 */
	public void refreshTag2Tag(String pTagId, String cTagIds){
		new JedisHashManager(cacheKey).add(pTagId, cTagIds);
	}
	
	public void init(){
		String lock = new JedisKeyManager(cacheLock).get();
		if(!X.CachePrefix.LOCK_MARK.equals(lock)){
			try{
				log.info("tag2tag init.....");
				new JedisKeyManager(cacheLock).set(X.CachePrefix.LOCK_MARK);
				Map<String, String> map = TagManager.getInstance().getTag2TagMap();
				new JedisKeyManager(cacheKey).del();
				new JedisHashManager(cacheKey).setAll(map);
			}catch(Exception e){
				log.error(" tag2tag init exception  " + e.getMessage());
			}finally{
				new JedisKeyManager(cacheLock).del();				
			}
		}
	}
	
	public void reload(){
		init();
	}
}