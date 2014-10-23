package cn.youhui.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import cn.youhui.bean.Action;
import cn.youhui.bean.GiftBean;
import cn.youhui.common.RedisMySqlExecutor;
import cn.youhui.common.RedisMySqlRunner;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.utils.ActionChangeUtil;
import cn.youhui.utils.OuterCode;

public class GiftManager 
{
	private static final Logger log = Logger.getLogger(GiftManager.class);
	
	private static GiftManager instance = null;
	
	private static final String GIFT_ID_BEAN = "gift.giftid2bean";
	
	private static final String GIFT_RANK = "gift.giftid2rank";
	
	public static Long lastupdatetime = 0L;
	
	public static GiftManager getInstance(){
		if(instance == null) 
		{
			instance = new GiftManager();
		}
		return instance;
	}
	
	public List<GiftBean> getGiftList(String keyWord, List<GiftBean> resultList, 
			String platform, String uid, String version_code) throws JSONException
	{
		if (System.currentTimeMillis() - lastupdatetime > 60 * 60 * 1000) 
		{
			lastupdatetime = System.currentTimeMillis();
			new Thread() 
			{
				public void run() 
				{
					reload();
				}
			}.start();
		}
		
		List<GiftBean> list = new ArrayList<GiftBean>();
		
		Set<String> idset = new JedisSortedSetManager(GIFT_RANK).zrange(0, -1);
		
		if(idset != null && idset.size() > 0)
		{
			Jedis j = JedisDBIns.getInstance().getJedis() ;
			
			try 
			{
				A:for(String id : idset)
				{
					// 检查校验 该礼物是否已经被调取查询
					for (GiftBean compareBean : resultList)
					{
						if (compareBean.getGift_id().equals(id))
						{
							continue A;
						}
					}
					
					String json = j.hget(GIFT_ID_BEAN, id);
					if (null != json && !"".equals(json))
					{
						JSONObject jsonObject = new JSONObject(json);
						
						String gift_ID = jsonObject.getString("id");
						String gift_title = jsonObject.getString("title");
						String type = jsonObject.getString("type");
						String desc = jsonObject.getString("desc");
						String img = jsonObject.getString("img");
						String imgIpad = "";
						if(jsonObject.has("img_ipad"))
						{
							imgIpad = jsonObject.getString("img_ipad");
						}
						String clickname = jsonObject.getString("clickname");
						String actiontype = jsonObject.getString("actiontype");
						String actionvalue = jsonObject.getString("actionvalue");
						
						Action action = ActionChangeUtil.lowVersionItemAction(gift_ID, uid, platform, "12", actiontype, actionvalue, version_code);
						
						GiftBean gift = new GiftBean();
						
						gift.setGift_id(gift_ID);
						gift.setTitle(gift_title);
						gift.setType(type);
						gift.setDescription(desc);
						gift.setImg(img);
						gift.setImgIpad(imgIpad);
						gift.setClickname(clickname);
						gift.setActiontype(action.getActionType());
						gift.setActionvalue(action.getActionValue());
						
						if (keyWord != null && !keyWord.equals("") && !keyWord.equals("null"))
						{
							if (clickname != null && !clickname.equals("") && clickname.contains(keyWord))
							{
								list.add(gift);
								return list;
							}
							else
							{
								continue;
							}
						}
						else
						{
							list.add(gift);
						}
					}
				}
			} 
			catch (JedisConnectionException e) 
			{
				JedisDBIns.getInstance().releaseBrokenJedis(j);
			}
			finally
			{
				JedisDBIns.getInstance().release(j);
			}
			
		}
		return list;
	}
	
	public int getSize()
	{
		return (int)new JedisHashManager(GIFT_ID_BEAN).size();
	}
	
	public static String convertoRedisJson(String id, 
			String type, String title, String desc, 
			String img, String imgIpad, String clickname, String actiontype, String actionvalue)
	{
		String json = "{\"id\":\"" + id + "\"," 
					+ "\"type\":\"" + type + "\","
					+ "\"title\":\"" + title + "\","
					+ "\"desc\":\"" + desc + "\","
					+ "\"img\":\"" + img + "\","
					+ "\"img_ipad\":\"" + imgIpad + "\","
					+ "\"clickname\":\"" + clickname + "\","
					+ "\"actiontype\":\"" + actiontype + "\","
					+ "\"actionvalue\":\"" + actionvalue + "\""
					+ "}";
		
		return json;
	}
	
	public void setGiftid2Bean2(String id, String json)
	{
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try 
		{
		    jedis.hset(GIFT_ID_BEAN, id, json);
		} 
		catch (JedisConnectionException e) 
		{
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}
		finally
		{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public boolean init() 
	{
		RedisMySqlRunner<Boolean> r = new RedisMySqlRunner<Boolean>() 
		{
			public Boolean run(Connection conn, Jedis jedis) throws JedisConnectionException, SQLException 
			{
				log.info("giftid2rank init............");
				try
				{
					String sql = "SELECT * FROM `youhui_datamining`.`tyh_gifts` where `status`=1 and `start_time`<? and `end_time`>? order by `rank` asc";
					java.sql.PreparedStatement ps = conn.prepareStatement(sql);
					long nowTime = System.currentTimeMillis();
					ps.setLong(1, nowTime);
					ps.setLong(2, nowTime);
					
					ResultSet rs = ps.executeQuery();
					
					jedis.del(GIFT_RANK);
					jedis.del(GIFT_ID_BEAN);
					while (rs.next())
					{
						jedis.zadd(GIFT_RANK, rs.getInt("rank"), rs.getString("id"));
						
						GiftBean gift = new GiftBean();
						gift.setGift_id(rs.getString("id"));
						gift.setTitle(rs.getString("gift_title"));
						gift.setType(rs.getString("type"));
						gift.setDescription(rs.getString("gift_desc"));
						gift.setImg(rs.getString("img"));
						gift.setImgIpad(rs.getString("img_ipad"));
						gift.setClickname(rs.getString("clickname"));
						gift.setActiontype(rs.getString("action_type"));
						gift.setActionvalue(rs.getString("action_value"));
						
						String json = convertoRedisJson(
											gift.getGift_id(), 
											gift.getType(), 
											gift.getTitle(), 
											gift.getDescription(),
											gift.getImg(),
											gift.getImgIpad(),
											gift.getClickname(),
											gift.getActiontype(), 
											gift.getActionvalue());
						
						setGiftid2Bean2(gift.getGift_id(), json);
					}
				}
				catch(Exception e)
				{
					log.error("giftid2rank init exception  " + e.getMessage());
				}
				return true;
			}
		};
		RedisMySqlExecutor<Boolean> e = new RedisMySqlExecutor<Boolean>();
		return e.exe(r);
	}
	
	public void reload()
	{
		init();
	}
}
