package com.netting.cache.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.google.gson.Gson;
import com.netting.bean.KeyCategoryBean;
import com.netting.bean.KeyCategoryBean.KeywordBean;
import com.netting.db.DataSourceFactory;
import com.netting.redis.executor.JedisHashManager;
import com.netting.redis.executor.JedisKeyManager;

/**
 * 标签管理---REDIS缓存更新操作类
 * @author YAOJIANBO
 *
 */
public class Admin_Tag_Cache_DAO 
{
	public static final String TYH_TAG_ID = "538";
	
	private static final String TAG_VERSION = "tag.version";
	
	private static final String TAG_ALL_TAGS = "tag.all_tags";
	
	private static final String TAG_ALLTAGS = "tag.alltags"; 
	
	private static final String TAG_TO_TAG = "tag.tag2tag";
	
	private static final String TAG_ID_NAME = "tag.tagid2name";
	
	private static final String TAG_USERTAGS = "tag.usertags";
	
	private static final String TAG_USERTAGS_IPAD = "tag.usertags.ipad";
	
	private static final String TAG_ALLINIPAD_TAGS = "tag.allinipad_tags";
	
	private static final String TAG_TO_TAG_IPAD = "tag.tag2taginipad";
	
	public static String TAG_SEPA = ";";
	
	private static Log logger = LogFactory.getLog(Admin_Tag_Cache_DAO.class);
	
	/**
	 * 新增/更新标签数据至REDIS,hash类型，key=tag.all_tags
	 * @param keyword
	 */
	public static void addTag2All_Tags(KeyCategoryBean.KeywordBean keyword)
	{
		JedisHashManager.set(TAG_ALL_TAGS, keyword.getId(), new Gson().toJson(keyword));
	}
	
	/**
	 * 删除tag.all_tags中的相应标签数据,hash类型，key=tag.all_tags
	 * @param keyword
	 */
	public static void delTag2All_Tags(String tagID)
	{
		JedisHashManager.delete(TAG_ALL_TAGS, tagID);
	}
	
	/**
	 * 新增/更新PAD标签数据至REDIS,hash类型，key=tag.allinipad_tags
	 * @param keyword
	 */
	public static void addTag2All_Tags_Pad(KeyCategoryBean.KeywordBean keyword)
	{
		JedisHashManager.set(TAG_ALLINIPAD_TAGS, keyword.getId(), new Gson().toJson(keyword));
	}
	
	/**
	 * 删除tag.allinipad_tags中的相应PAD标签数据,hash类型，key=tag.allinipad_tags
	 * @param keyword
	 */
	public static void delTag2All_Tags_Pad(String tagID)
	{
		JedisHashManager.delete(TAG_ALLINIPAD_TAGS, tagID);
	}
	
	/**
	 * 新增/更新标签数据至REDIS,hash类型，key=tag.alltags
	 * @param keyword
	 */
	public static void addTag2AllTags(String parentID, String tagID)
	{
		String oldTagIDs = JedisHashManager.get(TAG_ALLTAGS, parentID);
	    if (null == oldTagIDs || "".equals(oldTagIDs))
	    {
	    	JedisHashManager.set(TAG_ALLTAGS, parentID, tagID);
	    }
	    else if (!oldTagIDs.contains(";" + tagID) && !oldTagIDs.contains(tagID))
	    {
	    	JedisHashManager.set(TAG_ALLTAGS, parentID, oldTagIDs + ";" + tagID);
	    }
	}
	
	/**
	 * 删除tag.alltags中的相应标签数据,hash类型，key=tag.alltags
	 * @param keyword
	 */
	public static void delTag2AllTags(String parentID, String tagID)
	{
		String oldTagIDs = JedisHashManager.get(TAG_ALLTAGS, parentID);
	    if (null != oldTagIDs && !"".equals(oldTagIDs) && oldTagIDs.contains(tagID))
	    {
	    	String newTagIDs = oldTagIDs.replaceAll(tagID, "");
	    	newTagIDs = newTagIDs.replaceAll(";;", ";");
	    	
	    	JedisHashManager.set(TAG_ALLTAGS, parentID, newTagIDs);
	    }
	}
	
	/**
	 * 新增标签数据至REDIS,hash类型，key=tag.tag2tag
	 * @param keyword
	 */
	public static void addTag2Tag(String parentID, String tagID)
	{
		String oldTagIDs = JedisHashManager.get(TAG_TO_TAG, parentID);
	    if (null == oldTagIDs || "".equals(oldTagIDs))
	    {
	    	JedisHashManager.set(TAG_TO_TAG, parentID, tagID);
	    }
	    else if (!oldTagIDs.contains(";" + tagID) && !oldTagIDs.contains(tagID))
	    {
	    	JedisHashManager.set(TAG_TO_TAG, parentID, oldTagIDs + ";" + tagID);
	    }
	}
	
	/**
	 * 删除tag.tag2tag中的相应标签数据,hash类型，key=tag.tag2tag
	 * @param keyword
	 */
	public static void delTag2Tag(String parentID, String tagID)
	{
		String oldTagIDs = JedisHashManager.get(TAG_TO_TAG, parentID);
		
	    if (null != oldTagIDs && !"".equals(oldTagIDs) && oldTagIDs.contains(tagID))
	    {
	    	String newTagIDs = oldTagIDs.replaceAll(tagID, "");
	    	newTagIDs = newTagIDs.replaceAll(";;", ";");
	    	
	    	JedisHashManager.set(TAG_TO_TAG, parentID, newTagIDs);
	    }
	}
	
	/**
	 * 新增标签数据至REDIS,hash类型，key=tag.tag2taginipad
	 * @param keyword
	 */
	public static void addTag2Tag_Pad(String parentID, String tagID)
	{
		String oldTagIDs = JedisHashManager.get(TAG_TO_TAG_IPAD, parentID);
	    if (null == oldTagIDs || "".equals(oldTagIDs))
	    {
	    	JedisHashManager.set(TAG_TO_TAG_IPAD, parentID, tagID);
	    }
	    else if (!oldTagIDs.contains(";" + tagID) && !oldTagIDs.contains(tagID))
	    {
	    	JedisHashManager.set(TAG_TO_TAG_IPAD, parentID, oldTagIDs + ";" + tagID);
	    }
	}
	
	/**
	 * 删除tag.tag2tag中的相应标签数据,hash类型，key=tag.tag2taginipad
	 * @param keyword
	 */
	public static void delTag2Tag_Pad(String parentID, String tagID)
	{
		String oldTagIDs = JedisHashManager.get(TAG_TO_TAG_IPAD, parentID);
		
	    if (null != oldTagIDs && !"".equals(oldTagIDs) && oldTagIDs.contains(tagID))
	    {
	    	String newTagIDs = oldTagIDs.replaceAll(tagID, "");
	    	newTagIDs = newTagIDs.replaceAll(";;", ";");
	    	
	    	JedisHashManager.set(TAG_TO_TAG_IPAD, parentID, newTagIDs);
	    }
	}
	
	/**
	 * 添加标签至Redis默认用户标签列表中,hash类型，key=tag.usertags
	 * @param tagID
	 */
	public static void addDefaultUserTagCache(String tagID)
	{
		String defaultOldTagids = JedisHashManager.get(TAG_USERTAGS, "00000000");
		
		if(defaultOldTagids == null || "".equals(defaultOldTagids))
		{
			JedisHashManager.set(TAG_USERTAGS, "00000000", tagID);
		}
		else if (!defaultOldTagids.contains(";" + tagID) && !defaultOldTagids.contains(tagID))
	    {
			JedisHashManager.set(TAG_USERTAGS, "00000000", defaultOldTagids + ";" + tagID);
	    }
	}
	
	/**
	 * 将标签从Redis默认用户标签列表中删除,hash类型，key=tag.usertags
	 * @param tagID
	 */
	public static void delDefaultUserTagCache(String tagID)
	{
		String defaultOldTagids = JedisHashManager.get(TAG_USERTAGS, "00000000");
		
		if (null != defaultOldTagids && !"".equals(defaultOldTagids) && defaultOldTagids.contains(tagID))
		{
			defaultOldTagids = defaultOldTagids.replaceAll(tagID, "");
			defaultOldTagids = defaultOldTagids.replaceAll(";;", ";");
			
			JedisHashManager.set(TAG_USERTAGS, "00000000", defaultOldTagids);
		}
	}
	
	/**
	 * 添加标签至Redis默认IPAD用户标签列表中,hash类型，key=tag.usertags.ipad
	 * @param tagID
	 */
	public static void addDefaultUserIpadTagCache(String tagID)
	{
		String defaultOldTagids = JedisHashManager.get(TAG_USERTAGS_IPAD, "00000001");
		
		if(defaultOldTagids == null || "".equals(defaultOldTagids))
		{
			JedisHashManager.set(TAG_USERTAGS_IPAD, "00000001", tagID);
		}
		else if (!defaultOldTagids.contains(";" + tagID) && !defaultOldTagids.contains(tagID))
	    {
			JedisHashManager.set(TAG_USERTAGS_IPAD, "00000001", defaultOldTagids + ";" + tagID);
	    }
	}
	
	/**
	 * 将标签从Redis默认IPAD用户标签列表中删除,hash类型，key=tag.usertags.ipad
	 * @param tagID
	 */
	public static void delDefaultUserIpadTagCache(String tagID)
	{
		String defaultOldTagids = JedisHashManager.get(TAG_USERTAGS_IPAD, "00000001");
		
		if (null != defaultOldTagids && !"".equals(defaultOldTagids) && defaultOldTagids.contains(tagID))
		{
			defaultOldTagids = defaultOldTagids.replaceAll(tagID, "");
			defaultOldTagids = defaultOldTagids.replaceAll(";;", ";");
			
			JedisHashManager.set(TAG_USERTAGS_IPAD, "00000001", defaultOldTagids.replaceAll(";" + tagID, ""));
		}
	}
	
	/**
	 * 新增标签数据至REDIS,hash类型，key=tag.tagid2name
	 * @param keyword
	 */
	public static void setTag_ID_NAME(KeyCategoryBean.KeywordBean keyword)
	{
		JedisHashManager.set(TAG_ID_NAME, keyword.getId(), keyword.getKeyword());
		JedisHashManager.set(TAG_ID_NAME, keyword.getKeyword(), keyword.getId());
	}
	
	public static void update_Tag_Version()
	{
		JedisKeyManager.set(TAG_VERSION, System.currentTimeMillis()+"");
	}
	
	
	public static void updateDefalutCacheInIpad(String tagids){

		JedisHashManager.set(TAG_USERTAGS_IPAD, "00000001",  tagids.replaceAll(",", ";"));
	}
	
	public static void setTag2AllIpadTag(String ptagid, String ctagids)
	{
		JedisHashManager.set(TAG_USERTAGS_IPAD, ptagid, ctagids.replaceAll(",", ";"));
	}
	
	public static void  updateDefalutCache(String tagids)
	{
		JedisHashManager.set(TAG_USERTAGS, "00000000",tagids.replaceAll(",", ";"));
	}
	
	public static void setTag2Tag(String ptagid, String ctagids)
	{
		JedisHashManager.set(TAG_TO_TAG, ptagid, ctagids.replaceAll(",", ";"));
	}
	
	public static void setTag2AllTag(String ptagid, String ctagids)
	{
		JedisHashManager.set(TAG_ALLTAGS, ptagid, ctagids.replaceAll(",", ";"));
	}
	
	public static void reloadUserTag()
	{
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;	
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_user_tag` order by uid,rank asc;";
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
			rs = s.executeQuery();
			
			List<String> list = new ArrayList<String>();

			while (rs.next()) 
			{
				String uid = rs.getString("uid");
				String tagid = rs.getString("tagid");
				
				if(!list.contains(uid))
				{
					list.add(uid);
					JedisHashManager.delete(TAG_USERTAGS, uid);
					JedisHashManager.set(TAG_USERTAGS, uid, tagid);
				}
				else
				{
					String tag = JedisHashManager.get(TAG_USERTAGS, uid);
					if(tag != null && !tag.contains(tagid))
					{							
						JedisHashManager.set(TAG_USERTAGS, uid, tag + ";" + tagid);
					}
				}
				// System.out.println("加载：" + uid + "  " + tagid);
			}
		} 
		catch (SQLException e) 
		{
			logger.error("Admin_Tag_Cache_DAO.reloadUserTag error:", e);
		}
		finally
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}
	}
	
	public static void reloadUserTagIpad()
	{
		Connection conn = null;
		PreparedStatement s = null;
		ResultSet rs = null;	
		String sql = "SELECT * FROM `youhui_datamining`.`tyh_user_tag_ipad` order by uid,rank asc;";
		
		try 
		{
			conn = DataSourceFactory.getConnection();
			s = conn.prepareStatement(sql);
							
			rs = s.executeQuery();
			
			List<String> list = new ArrayList<String>();

			while (rs.next()) 
			{
				String uid = rs.getString("uid");
				String tagid = rs.getString("tagid");
				
				if(!list.contains(uid))
				{
					list.add(uid);
					JedisHashManager.delete(TAG_USERTAGS_IPAD, uid);
					JedisHashManager.set(TAG_USERTAGS_IPAD, uid,tagid);
				}
				else
				{
					String tag = JedisHashManager.get(TAG_USERTAGS_IPAD, uid);
					
					if(tag != null && !tag.contains(tagid))
					{						
						JedisHashManager.set(TAG_USERTAGS_IPAD, uid,tag + ";" + tagid);
					}
				}
				// System.out.println("加载：" + uid + "  " + tagid);
			}
		} 
		catch (SQLException e)
		{
			logger.error("Admin_Tag_Cache_DAO.reloadUserTagIpad error:", e);

		}
		finally
		{
			DataSourceFactory.closeAll(rs, s, conn);
		}

	}
	
	
	
	public static List<KeywordBean> getTagsByParentId(String ptagid){
		List<KeywordBean> ret = new ArrayList<KeywordBean>();
//		String tagids = new JedisHashManager(TAG_TO_TAG).get(ptagid);
		String tagids = JedisHashManager.get(TAG_TO_TAG, ptagid);
		if(tagids != null && !"".equals(tagids)){
			
			String[] tagidss = tagids.split(TAG_SEPA);
			for(String tagid : tagidss){
//				KeywordBean bean = TagCacher.getInstance().getTag(tagid);
				String source = JedisHashManager.get(TAG_ALL_TAGS, tagid);
				KeywordBean bean = null;
				if (source != null)
				{
					bean = new Gson().fromJson(source, KeywordBean.class);
					
//					Set<String> idset = new JedisSortedSetManager(cacheKey + tagid).
//					zrange((page - 1)*pageSize, (page * pageSize - 1)); 
//			Set<String> idset = JedisSortedSetManager.zrange(cacheKey + tagid, (page - 1)*pageSize, (page * pageSize - 1));
	//	
				}
				
				
				if(bean != null){
					String itemids = Admin_Tag_Item_Cache_DAO.getItemsByTag(tagid);
					bean.setItemids(itemids);
					ret.add(bean);
				}
			}
			
			return ret;
		}else{
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(getTagsByParentId("569").size());
//		reloadUserTag();
	}
}
