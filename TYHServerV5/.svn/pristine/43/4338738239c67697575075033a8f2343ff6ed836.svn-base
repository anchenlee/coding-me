package cn.youhui.model.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RunAs;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import cn.youhui.bean.ItemBean;
import cn.youhui.bean.ShopBean;
import cn.youhui.common.MySqlExecutor;
import cn.youhui.common.MySqlRunner;
import cn.youhui.common.RedisExecutor;
import cn.youhui.common.RedisRunner;
import cn.youhui.common.X;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.ramdata.FavAllItemsCacher;
import cn.youhui.ramdata.FavAllShopsCacher;
import cn.youhui.ramdata.FavUser2ItemsCacher;
import cn.youhui.ramdata.FavUser2ShopsCacher;
import cn.youhui.utils.TPool;

public class TBFavListSyncModel {
	static TBFavListSyncModel model = null;

	public static TBFavListSyncModel getInstance() {
		if(model == null) model = new TBFavListSyncModel();
		return model;
	}

	//
	// // 刷新数据到数据库
	// public boolean syncItems() {
	//
	// boolean flag = false;
	// FavAllItemsCacher cacher = FavAllItemsCacher.getInstance();
	// ArrayList<ItemBean> list = cacher.getItems();
	// if (!list.isEmpty() && list.size() > 0) {
	// for (ItemBean bean : list) {
	// insertBaseData(bean);
	// }
	// }
	// flag = true;
	// return flag;
	// }

	// 刷新单个用户item数据到数据库
	public boolean syncItem(long uid) {

		boolean flag = false;
		FavAllItemsCacher cacher = FavAllItemsCacher.getInstance();
		FavUser2ItemsCacher icacher = new FavUser2ItemsCacher(uid);
		ArrayList<String> list = icacher.getItems();
		if (!list.isEmpty() && list.size() > 0) {
			for (String i : list) {
				ItemBean bean = cacher.getItem(i);
				insertItemBaseData(bean);
				uid2Item(uid, bean.getNum_iid());
			}
		}
		flag = true;
		return flag;
	}

	// 刷新单个用户Shop数据到数据库
	public boolean syncShop(long uid) {

		boolean flag = false;
		FavAllShopsCacher cacher = FavAllShopsCacher.getInstance();
		FavUser2ShopsCacher icacher = new FavUser2ShopsCacher(uid);
		ArrayList<String> list = icacher.getShops();
		if (!list.isEmpty() && list.size() > 0) {
			for (String i : list) {
				ShopBean bean = cacher.getItem(i);
				insertShopBaseData(bean);
				uid2Shop(uid, bean.getShop_id());
			}
		}
		flag = true;
		return flag;
	}

	// 刷新单个用户item数据到数据库
	public boolean syncItem(long uid, ItemBean bean)throws Exception {

		boolean flag = false;
		insertItemBaseData(bean);
		uid2Item(uid, bean.getNum_iid());

		flag = true;
		return flag;
	}

	// 刷新单个用户Shop数据到数据库
	public boolean syncShop(long uid, ShopBean bean)throws Exception {

		boolean flag = false;

		insertShopBaseData(bean);
		uid2Shop(uid, bean.getShop_id());

		flag = true;
		return flag;
	}

	/**
	 * 更新基础数据
	 * 
	 * @param item
	 */
	public void insertItemBaseData(ItemBean item) {
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			conn.createStatement().execute("use youhui_datamining");
			String sql = "insert into tyh_fav_items(num_iid, title, pic_url, price, commission_rate, update_time) values(?,?,?,?,?,?) "
					+ "ON DUPLICATE KEY UPDATE `price`= VALUES(`price`),`commission_rate`=VALUES(`commission_rate`),`update_time`=VALUES(`update_time`)";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, item.getNum_iid());
			s.setString(2, item.getTitle());
			s.setString(3, item.getPic_url());
			s.setString(4, item.getPrice());
			s.setString(5, item.getCommission_rate());
			s.setString(6, System.currentTimeMillis() + "");
			s.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				MySQLDBIns.getInstance().release(conn);
			}
		}
	}

	/**
	 * 更新基础数据
	 * 
	 * @param item
	 */
	public void insertShopBaseData(ShopBean shop) {
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			conn.createStatement().execute("use youhui_datamining");
			String sql = "insert into tyh_fav_shops(num_iid, name, pic_url, commission_rate, update_time) values(?,?,?,?,?) "
					+ "ON DUPLICATE KEY UPDATE `commission_rate`=VALUES(`commission_rate`),`update_time`=VALUES(`update_time`)";
			PreparedStatement s = conn.prepareStatement(sql);
			s.setString(1, shop.getShop_id());
			s.setString(2, shop.getTitle());
			s.setString(3, shop.getPic_url());
			s.setString(4, shop.getCommission_rate());
			s.setString(5, System.currentTimeMillis() + "");
			s.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				MySQLDBIns.getInstance().release(conn);
			}
		}
	}

	/**
	 * 更新用户关联关系
	 ***/
	public boolean uid2Item(final long uid, final String itemdId) {
		MySqlRunner<Boolean> rr = new MySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				conn.createStatement().execute("use youhui_datamining");
				String sql = "insert into tyh_fav_uid2itemid(uid, item_id, timestamp) values(?,?,?) "
						+ "ON DUPLICATE KEY UPDATE `uid`= VALUES(`uid`),`item_id`=VALUES(`item_id`),`timestamp`=VALUES(`timestamp`)";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, uid + "");
				s.setString(2, itemdId);
				s.setString(3, System.currentTimeMillis() + "");
				s.executeUpdate();
				return true;
			}
		};

		MySqlExecutor<Boolean> re = new MySqlExecutor<Boolean>();
		return re.exe(rr);

	}

	/**
	 * 更新用户关联关系
	 ***/
	public boolean uid2Shop(final long uid, final String shopId) {
		MySqlRunner<Boolean> rr = new MySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				conn.createStatement().execute("use youhui_datamining");
				String sql = "insert into tyh_fav_uid2shopid(uid, shop_id, timestamp) values(?,?,?) "
						+ "ON DUPLICATE KEY UPDATE `uid`= VALUES(`uid`),`shop_id`=VALUES(`shop_id`),`timestamp`=VALUES(`timestamp`)";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, uid + "");
				s.setString(2, shopId);
				s.setString(3, System.currentTimeMillis() + "");
				s.executeUpdate();
				return true;
			}

		};

		MySqlExecutor<Boolean> re = new MySqlExecutor<Boolean>();
		return re.exe(rr);

	}

	// /**
	// * 同步所有数据
	// * **/
	// public void syncItemsThread() {
	// Runnable run = new Runnable() {
	//
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// syncItems();
	// }
	// };
	// TPool.getInstance().doit(run);
	//
	// }

	/**
	 * 判断uid是否存在
	 **/
	public boolean isUidExistItem(final String uid) {
		MySqlRunner<Boolean> rr = new MySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				boolean flag = false;
				conn.createStatement().execute("use youhui_datamining");
				String sql = "select * from tyh_fav_uid2itemid where uid = ? ";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, uid);
				ResultSet rs = s.executeQuery();
				if (rs.next()) {
					flag = true;
				}

				return flag;
			}

		};

		MySqlExecutor<Boolean> re = new MySqlExecutor<Boolean>();
		return re.exe(rr);
	}
	
	/**
	 * 判断uid是否存在
	 **/
	public boolean isUidExistShop(final String uid) {
		MySqlRunner<Boolean> rr = new MySqlRunner<Boolean>() {

			@Override
			public Boolean run(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				boolean flag = false;
				conn.createStatement().execute("use youhui_datamining");
				String sql = "select * from tyh_fav_uid2shopid where uid = ? ";
				PreparedStatement s = conn.prepareStatement(sql);
				s.setString(1, uid);
				ResultSet rs = s.executeQuery();
				if (rs.next()) {
					flag = true;
				}

				return flag;
			}

		};

		MySqlExecutor<Boolean> re = new MySqlExecutor<Boolean>();
		return re.exe(rr);
	}

	/**
	 * 同步单个用户数据
	 */
	public void syncItems4UidThread(final long uid) {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				syncItem(uid);
			}
		};
		TPool.getInstance().doit(run);
	}
	
	/**
	 * 添加用户收藏数据
	 * @since 2012-03-30
	 */
	public  boolean adduid2Items(final String uid, final List<String> itemdIds) {
		boolean flag = false;
		Connection conn = null;
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			String sql = "insert into `youhui_visit`.`tyh_fav_uid2itemid`(uid, item_id, timestamp) values(?,?,?)"
					+ "ON DUPLICATE KEY UPDATE `timestamp`=VALUES(`timestamp`)";
			PreparedStatement s = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			for(String itemdId : itemdIds){
				s.setString(1, uid);
				s.setString(2, itemdId);
				s.setLong(3, System.currentTimeMillis());
				s.addBatch();
			}
			conn.setAutoCommit(true);
			int[] i = s.executeBatch();
			if(i.length  > 0 && i[0] > 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		return flag;
	}
	

	/**
	 * 添加用户收藏店铺数据
	 * @since 2012-03-30
	 */
	public boolean adduid2Shop(final String uid, final List<String> shopIds){
		if(shopIds == null || shopIds.size()<1){
			return false;
		}
		MySqlRunner<Boolean> rr = new MySqlRunner<Boolean>() {
			public Boolean run(Connection conn) throws SQLException {
				boolean flag = false;
				String sql = "insert into `youhui_datamining`.`tyh_fav_uid2shopid`(uid, shop_id, timestamp) values(?,?,?) "
						+ "ON DUPLICATE KEY UPDATE `timestamp`=VALUES(`timestamp`)";
				PreparedStatement s = conn.prepareStatement(sql);
				conn.setAutoCommit(true);
				for(String shopId : shopIds){
					s.setString(1, uid + "");
					s.setString(2, shopId);
					s.setLong(3, System.currentTimeMillis());
					s.addBatch();
				}
				conn.setAutoCommit(true);
				int[] i = s.executeBatch();
				if(i[0] > 0)
				   flag = true;
				return flag;
			}

		};

		MySqlExecutor<Boolean> re = new MySqlExecutor<Boolean>();
		return re.exe(rr);

	}
	
	public static void main(String[] args) {
	}

}
