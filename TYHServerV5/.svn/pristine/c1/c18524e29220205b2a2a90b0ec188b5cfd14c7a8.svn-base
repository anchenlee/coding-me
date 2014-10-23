package cn.youhui.ramdata;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;


import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import cn.youhui.bean.KeyCategoryBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.dao.JedisDBIns;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.manager.KeywordManager;
import cn.youhui.utils.Util;

import com.google.gson.Gson;

public class DiscountProductCacher {
	private static final Logger log = Logger.getLogger(DiscountProductCacher.class);
	private static final String PREFIX = "youhui.cn.tag.products.";
	private static final String TAG_ITEMS = "tag.items4tag";
	private static final String TAG_ALL_TAGS = "tag.all_tags";         //标签数据
	private static final String TAG_TO_TAG = "tag.tag2tag";
	private static final String TAG_TO_ITEM = "tag.tag2item";
	private static final String TAG_ID_NAME = "tag.tagid2name";
	private static final String TAG_ALL_TAG = "tag.alltags";           //首页标签对应关系
	private static final String TAG_TO_TAG_INIPAD = "tag.tag2taginipad";     //ipad标签对应关系
	private static final String TAG_ALLINIPAD_TAGS = "tag.allinipad_tags";    //ipad下标签数据，和手机不同在于图片和bacolor
	
	private static final String tagStatus = "tag.status";           //标签热度
	private static final String isconverted = "tag.itemclick.isconverted";
	
    public static final String TAG_SEPA = ";";
	private static DiscountProductCacher instance;

	// private static boolean SET_INDEX_RUNNING = false;

	private DiscountProductCacher() {

	}

	public static DiscountProductCacher getInstance() {
		if (instance == null) {
			instance = new DiscountProductCacher();
		}
		return instance;
	}

	public void setIndexProduct(String keyword, String itemId) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			jedis.hset(PREFIX + "show_index", keyword, itemId);
			jedis.expire(PREFIX + "show_index", 8 * 60 * 60);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void delTagid2Tagname(String name){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hdel(TAG_ID_NAME, name);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void setTagid2Tagname(String name, String id){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hset(TAG_ID_NAME, name, id);
		    jedis.hset(TAG_ID_NAME, id, name);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void addTag(KeyCategoryBean.KeywordBean keyword){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hset(TAG_ALL_TAGS, keyword.getId(), new Gson().toJson(keyword));
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}

	public void delTag(String tagid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hdel(TAG_ALL_TAGS, tagid);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally {
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void addIpadTag(KeyCategoryBean.KeywordBean keyword){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hset(TAG_ALLINIPAD_TAGS, keyword.getId(), new Gson().toJson(keyword));
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}

	public void delIpadTag(String tagid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hdel(TAG_ALLINIPAD_TAGS, tagid);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally {
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void addTag2Tag(String ptagid, String ctagid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    String ctagids = jedis.hget(TAG_TO_TAG, ptagid);
		    jedis.hset(TAG_TO_TAG, ptagid, ctagids + TAG_SEPA + ctagid);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void setTag2Tag(String ptagid, String ctagids){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hset(TAG_TO_TAG, ptagid, ctagids.replaceAll(",", TAG_SEPA));
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void addTag2AllTag(String ptagid, String ctagid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    String ctagids = jedis.hget(TAG_ALL_TAG, ptagid);
		    jedis.hset(TAG_ALL_TAG, ptagid, ctagids + TAG_SEPA + ctagid);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void setTag2AllTag(String ptagid, String ctagids){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hset(TAG_ALL_TAG, ptagid, ctagids.replaceAll(",", TAG_SEPA));
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void delTag2AllTag(String ptagid, String ctagid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    String ctagids = jedis.hget(TAG_ALL_TAG, ptagid);
		    jedis.hset(TAG_ALL_TAG, ptagid, ctagids.replaceAll(ctagid, ""));
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void addTag2AllIpadTag(String ptagid, String ctagid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    String ctagids = jedis.hget(TAG_TO_TAG_INIPAD, ptagid);
		    jedis.hset(TAG_TO_TAG_INIPAD, ptagid, ctagids + TAG_SEPA + ctagid);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void setTag2AllIpadTag(String ptagid, String ctagids){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.hset(TAG_TO_TAG_INIPAD, ptagid, ctagids.replaceAll(",", TAG_SEPA));
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void delTag2AllIpadTag(String ptagid, String ctagid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			String ctagids = jedis.hget(TAG_TO_TAG_INIPAD, ptagid);
		    jedis.hset(TAG_TO_TAG_INIPAD, ptagid, ctagids.replaceAll(ctagid, ""));
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void delItem2Tag(String tagid, String itemid){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.zrem(TAG_TO_ITEM + tagid, itemid);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	
	public void addItem2Tag(String tagid, String itemid, int rank){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
		    jedis.zadd(TAG_TO_ITEM + tagid, rank, itemid);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public TeJiaGoodsBean getIndexProduct(final String keyword) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		TeJiaGoodsBean bean = null;
		try {
			Gson gson = new Gson();
			String id = jedis.hget(PREFIX + "show_index", keyword);
			if (id != null) {
				String json = jedis.hget(PREFIX, id);
				if (json != null) {
					bean = gson.fromJson(json, TeJiaGoodsBean.class);
				}
			} else {
				/*   去掉自动添加封面
				synchronized (keyword) {
					Runnable r = new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							// SET_INDEX_RUNNING = true;
							ArrayList<TeJiaGoodsBean> list = TaobaokeManager
									.searchTaoBao(keyword, keyword);
							for (TeJiaGoodsBean item : list) {
								boolean result = DiscountKeywordManager.getInstance().addDiscountProducts(item);
								if (result) {
									addProduct(item);
									setIndexProduct(keyword, item.getItem_id());
									KeywordManager manager = new KeywordManager();
									try {
										manager.setDiscountShowIndex(
												item.getItem_id(), keyword);
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									updateImageSize(item);
									break;
								}
							}
							// SET_INDEX_RUNNING = false;
						}
					};
					TPool.getInstance().doit(r);
				}
				*/
			}
			if (bean == null)
				bean = new KeywordManager().getDiscountShowIndex(keyword);
			JedisDBIns.getInstance().release(jedis);
		} catch (Exception e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
		return bean;
	}

	private void updateImageSize(TeJiaGoodsBean bean) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			try {
				int[] size_m = readWidthHeigh(bean.getPic_url() + "_m.jpg");
				int[] size_b = readWidthHeigh(bean.getPic_url() + "_b.jpg");
				int[] size_310 = readWidthHeigh(bean.getPic_url()
						+ "_310x310.jpg");
				int[] size_170 = readWidthHeigh(Util.getCustomImg(
						bean.getPic_url(), "170x170"));
				String sql = "UPDATE `youhui_datamining`.`m_discount_products` SET `width_b` = '"
						+ size_b[0]
						+ "',`height_b` = '"
						+ size_b[1]
						+ "',`width_m` = '"
						+ size_m[0]
						+ "',`height_m` = '"
						+ size_m[1]
						+ "',`width_310` = '"
						+ size_310[0]
						+ "',`height_310` = '"
						+ size_310[1]
						+ "',`width_170` = '"
						+ size_170[0]
						+ "',`height_170` = '"
						+ size_170[1]
						+ "' WHERE `item_id` = '" + bean.getItem_id() + "'";
				s.executeUpdate(sql);
				bean.setWidth_m(size_m[0]);
				bean.setWidth_b(size_b[0]);
				bean.setWidth_310(size_310[0]);
				bean.setWidth_170(size_170[0]);

				bean.setHeight_310(size_310[1]);
				bean.setHeight_b(size_b[1]);
				bean.setHeight_m(size_m[1]);
				bean.setHeight_170(size_170[1]);
				DiscountProductCacher.getInstance().updateProduct(bean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
	}

	private int[] readWidthHeigh(String url) throws Exception {
		BufferedImage bi = ImageIO.read(new URL(url));
		int[] size = { bi.getWidth(), bi.getHeight() };
		return size;
	}

	public TeJiaGoodsBean getProduct(String itemId) {
		TeJiaGoodsBean bean = null;
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			String json = jedis.hget(PREFIX, itemId);
			if (json != null) {
				bean = gson.fromJson(json, TeJiaGoodsBean.class);
			}
			if (bean == null) {
				bean = getProductFromDB(itemId);
				if (bean != null)
					addProduct(bean);
			}
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			// TODO: handle exception
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		} catch (Exception e) {
			// TODO: handle exception
			JedisDBIns.getInstance().release(jedis);
		}
		return bean;
	}

	public boolean moveProductold(String itemId0, long time0, String itemId1, long time1, String type){
		boolean flag = false;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql0 = "update youhui_datamining.m_discount_products set update_time= ? where item_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql0);
			if(type.equals("1")) {//上移一位
				ps.setLong(1, time0);
				ps.setString(2, itemId1);
				ps.addBatch();
				ps.setLong(1, time1);
				ps.setString(2, itemId0);
				ps.addBatch();
			}
			else
			if(type.equals("2")) {//移至本页首位
				ps.setLong(1, time1+1);
				ps.setString(2, itemId0);
				ps.addBatch();
			}
			else
			if(type.equals("3")) {//移至首位
				ps.setLong(1, time1+1);
				ps.setString(2, itemId0);
				ps.addBatch();
			}
			else
			if(type.equals("4")) {//下移一位
				ps.setLong(1, time0);
				ps.setString(2, itemId1);
				ps.addBatch();
				ps.setLong(1, time1);
				ps.setString(2, itemId0);
				ps.addBatch();
			}
			else
			if(type.equals("5")) {//移至本页末位
				ps.setLong(1, time1-1);
				ps.setString(2, itemId0);
				ps.addBatch();
			}
			else
			if(type.equals("6")) {//移至末位
				ps.setLong(1, time1-1);
				ps.setString(2, itemId0);
				ps.addBatch();
			}
			
			int[] i = ps.executeBatch();
			if(i[0]>0) flag = true;
		}catch(SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public boolean moveProduct(String tagid, String itemId0, int rank0, String itemId1, int rank1, String type){
		boolean flag = false;
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try{
			String sql = "update youhui_datamining.m_tagtoitem set rank = ? where item_id = ? and tag_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			if(type.equals("2")){
				ps.setInt(1, rank1-1);
				ps.setString(2, itemId0);
				ps.setString(3, tagid);
				ps.addBatch();
			}else if(type.equals("4")){
				ps.setInt(1, rank1+1);
				ps.setString(2, itemId0);
				ps.setString(3, tagid);
				ps.addBatch();
			}else{
				ps.setInt(1, rank0);
				ps.setString(2, itemId1);
				ps.setString(3, tagid);
				ps.addBatch();
				ps.setInt(1, rank1);
				ps.setString(2, itemId0);
				ps.setString(3, tagid);
				ps.addBatch();
			}
			int[] i = ps.executeBatch();
			if(i[0]>0) flag = true;
		}catch(SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	
	public TeJiaGoodsBean getProductFromDB(String itemId) {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		TeJiaGoodsBean bean = new TeJiaGoodsBean();
		try {
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` WHERE `item_id` = '"
					+ itemId + "' ;";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setClickURL(rs.getString("click_url"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setCommission(rs.getString("commission"));
				bean.setCommission_rate(rs.getString("commission_rate"));
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return bean;
	}

	public void addProduct(TeJiaGoodsBean bean) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			String json = gson.toJson(bean);
			jedis.hset(PREFIX, bean.getItem_id(), json);
			// add to tag2item relation cache. key: TAG_ITEMS + keyword
//			jedis.zadd(TAG_ITEMS + bean.getKeyword(), Double.parseDouble(System.currentTimeMillis()+""), bean.getItem_id());
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}

	public void deleteDiscountProducts(String keyword, List<String> ids){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			for(String itemId : ids){
//				jedis.hdel(PREFIX, itemId);           //以免混合模式中还未来的及修改，导致客户端显示异常
				jedis.zrem(TAG_ITEMS + keyword, itemId);
			}
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);  
		}
	}
	
	public void updateKeywordProduct(String oldKeyword, String newKeyword){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Set<String> ids = jedis.zrange(TAG_ITEMS + oldKeyword, Integer.MIN_VALUE, Integer.MAX_VALUE);
			for (String itemId : ids) {
			    jedis.zadd(TAG_ITEMS + newKeyword, System.currentTimeMillis(), itemId); 
			}
			jedis.del(TAG_ITEMS + oldKeyword);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);
		}
	}
	
	public void updateProduct(TeJiaGoodsBean bean) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			Gson gson = new Gson();
			String json = gson.toJson(bean);
			jedis.hset(PREFIX, bean.getItem_id(), json);
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			// TODO: handle exception
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			// TODO: handle exception
			JedisDBIns.getInstance().release(jedis);
		}
	}

	public void removeProduct(String keyword, String itemId) {
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		try {
			TeJiaGoodsBean bean = getIndexProduct(keyword);
			jedis.hdel(PREFIX, itemId);
			// remove relation 4 tag item
			jedis.zrem(TAG_ITEMS + keyword, itemId);
			if (bean != null && bean.getItem_id().equals(itemId)) {
				jedis.hdel(PREFIX + "show_index", keyword);
			//	jedis.set("tag.all_tags" + keyword + ".icon","");
			}
			JedisDBIns.getInstance().release(jedis);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			JedisDBIns.getInstance().releaseBrokenJedis(jedis);
		}finally{
			JedisDBIns.getInstance().release(jedis);  
		}
	}
	
	
	public void changeTagStatus(String status){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		jedis.set(tagStatus, status);
		JedisDBIns.getInstance().release(jedis);
	}
	
	public String getTagStatus(){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		String ret = jedis.get(tagStatus);
		JedisDBIns.getInstance().release(jedis);
		return ret;
	}
	
	public void setIsconverted(String isconvert){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		jedis.set(isconverted, isconvert);
		JedisDBIns.getInstance().release(jedis);
	}
	
	public String getIsconverted(){
		Jedis jedis = JedisDBIns.getInstance().getJedis();
		String ret = jedis.get(isconverted);
		JedisDBIns.getInstance().release(jedis);
		return ret;
	}
	
	public static void main(String[] a){
		DiscountProductCacher.getInstance().addTag2AllTag(null, null);
	}

}
