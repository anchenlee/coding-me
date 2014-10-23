package cn.youhui.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import cn.youhui.bean.Action;
import cn.youhui.bean.KeyCategoryBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.ramdata.DiscountProductCacher;

public class KeywordManager {
	private static final Logger log = Logger.getLogger(KeywordManager.class);

	private static KeywordManager ins = new KeywordManager();

	public static KeywordManager getInstance() {
		return ins == null ? (ins = new KeywordManager()) : ins;
	}

	public ArrayList<TeJiaGoodsBean> getTagItems(String keyword)
			throws SQLException {
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `m_discount_products` WHERE `keyword` = '"
					+ keyword + "' ORDER BY `show_index`,`update_time` DESC;";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setClickURL(rs.getString("click_url"));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public ArrayList<TeJiaGoodsBean> getTagItemsFromCacher(String keyword,
			int posStart, int pageSize) throws SQLException {
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT `item_id` FROM `m_discount_products` WHERE `keyword` = '"
					+ keyword
					+ "' ORDER BY `show_index` DESC ,`update_time` DESC LIMIT "
					+ posStart + "," + pageSize + ";";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				String itemId = rs.getString("item_id");
				TeJiaGoodsBean bean = DiscountProductCacher.getInstance()
						.getProduct(itemId);
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public ArrayList<TeJiaGoodsBean> getTagItems(String keyword, int posStart)
			throws SQLException {
		ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `m_discount_products` WHERE `keyword` = '"
					+ keyword
					+ "' ORDER BY `show_index` DESC,`update_time` DESC LIMIT "
					+ posStart + ",10;";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				TeJiaGoodsBean bean = new TeJiaGoodsBean();
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setShow_index(rs.getInt("show_index"));
				bean.setClickURL(rs.getString("click_url"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public int getTagItemCount(String keyword) throws SQLException {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		int count = 0;
		try {
			conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT COUNT(*) AS total FROM `m_discount_products` WHERE `keyword` = '"
					+ keyword
					+ "' ORDER BY `show_index` DESC,`update_time` DESC;";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt("total");
			}
		} catch (SQLException e) {
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return count;
	}

	public void setDiscountShowIndex(String itemId, String keyword)
			throws SQLException {
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "UPDATE `youhui_datamining`.`m_discount_products` SET `show_index` = '1' WHERE `item_id` = '"
					+ itemId + "' AND `keyword` = '" + keyword + "'";
			s.executeUpdate(sql);
			sql = "UPDATE `youhui_datamining`.`m_discount_products` SET `show_index` = '0' WHERE `item_id` != '"
					+ itemId + "' AND `keyword` = '" + keyword + "'";
			s.executeUpdate(sql);
		} catch (SQLException e) {
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
	}

	public TeJiaGoodsBean getDiscountShowIndex(String keyword)
			throws SQLException {
		TeJiaGoodsBean bean = new TeJiaGoodsBean();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_products` WHERE `keyword` = '"
					+ keyword + "' AND `show_index` = '1';";
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				bean.setItem_id(rs.getString("item_id"));
				bean.setTitle(rs.getString("title"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setPrice_high(rs.getString("price_high"));
				bean.setPrice_low(rs.getString("price_low"));
				bean.setDiscount(rs.getString("discount"));
				bean.setPic_url(rs.getString("pic_url"));
				bean.setStatus(rs.getInt("status"));
				bean.setClickURL(rs.getString("click_url"));
			}
		} catch (SQLException e) {
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return bean;
	}

	public ArrayList<KeywordBean> getDiscountKeywords(String cateId , boolean isShowHide) {
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String ishide = "";
			if(!isShowHide)
       			ishide = " and `client_show` = 1 ";
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '" + cateId + "' "+ ishide +"ORDER BY `rank` ASC";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setIcon(rs.getString("icon"));
				bean.setBgcolor(rs.getString("bgcolor"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				bean.setSex(rs.getInt("sex"));
				bean.setRank(rs.getString("rank"));
				bean.setDescription(rs.getString("description"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setClientShow(rs.getInt("client_show"));
				bean.setItemCount(rs.getInt("goods_count"));
				bean.setHeat(rs.getString("heat"));
				bean.setShow(rs.getString("show"));
				bean.setChildKeywords(getDiscountKeywords(rs.getString("id"), isShowHide));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public ArrayList<KeywordBean> getDiscountKeywords(String cateId ) {
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '" + cateId + "' ORDER BY `rank` ASC";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setIcon(rs.getString("icon"));
				bean.setBgcolor(rs.getString("bgcolor"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				bean.setSex(rs.getInt("sex"));
				bean.setRank(rs.getString("rank"));
				bean.setDescription(rs.getString("description"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setClientShow(rs.getInt("client_show"));
				bean.setItemCount(rs.getInt("goods_count"));
				bean.setHeat(rs.getString("heat"));
				bean.setShow(rs.getString("show"));
				bean.setChildKeywords(getDiscountKeywords(rs.getString("id")));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public ArrayList<KeywordBean> getDiscountKeywordsInIpad(String cateId) {
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '"+ cateId + "' and istag = 1 ORDER BY `tag_rank` ASC";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setIcon(rs.getString("tag_icon"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				bean.setSex(rs.getInt("sex"));
				bean.setBgcolor(rs.getString("bgcolor"));
				bean.setRank(rs.getString("rank"));
				bean.setDescription(rs.getString("description"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setClientShow(rs.getInt("client_show"));
				bean.setItemCount(rs.getInt("goods_count"));
				bean.setHeat(rs.getString("heat"));
				bean.setShow(rs.getString("show"));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public String getDiscountKeywordStrInIpad(String cateId) {
		StringBuffer ret = new StringBuffer("");
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '"+ cateId + "' and istag = 1 ORDER BY `rank` ASC";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				ret.append(rs.getString("id")+",");
			}
		} catch (SQLException e) {
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return ret.toString();
	}

	public ArrayList<KeywordBean> getDiscountKeywords(String cateId, String sex) {
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '"
					+ cateId
					+ "' AND (`sex` = '"
					+ sex
					+ "' OR `sex` = '0') ORDER BY `rank` DESC";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setIcon(rs.getString("icon"));
				bean.setSex(rs.getInt("sex"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setItemCount(rs.getInt("goods_count"));
				bean.setHeat(rs.getString("heat"));
				bean.setShow(rs.getString("show"));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public ArrayList<KeyCategoryBean> getEnableKeywordCate()
			throws SQLException {
		ArrayList<KeyCategoryBean> list = new ArrayList<KeyCategoryBean>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '0' AND `fx_showindex` = '1' ORDER BY `rank`,`sex` ASC;";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeyCategoryBean bean = new KeyCategoryBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("keyword"));
				bean.setSex(rs.getInt("sex"));
				bean.setRank(rs.getInt("rank"));
				bean.setIcon(rs.getString("icon"));
				bean.setList(getDiscountKeywords(bean.getId()));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public ArrayList<KeywordBean> getEnableKeywords(String cateId) {
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '"
					+ cateId
					+ "' AND `fx_showindex` = '1' ORDER BY `rank` DESC";
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setIcon(rs.getString("icon"));
				bean.setSex(rs.getInt("sex"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setItemCount(rs.getInt("goods_count"));
				bean.setHeat(rs.getString("heat"));
				bean.setShow(rs.getString("show"));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public ArrayList<KeyCategoryBean> getDiscountKeywordCate()
			throws SQLException {
		ArrayList<KeyCategoryBean> list = new ArrayList<KeyCategoryBean>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '0' ORDER BY `rank`,`sex` ASC;";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeyCategoryBean bean = new KeyCategoryBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("keyword"));
				bean.setSex(rs.getInt("sex"));
				bean.setRank(rs.getInt("rank"));
				bean.setIcon(rs.getString("icon"));
				bean.setList(getDiscountKeywords(bean.getId(), true));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public ArrayList<KeyCategoryBean> getDiscountKeywordCate(String sex)
			throws SQLException {
		ArrayList<KeyCategoryBean> list = new ArrayList<KeyCategoryBean>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '0' AND (`sex` = '"
				+ sex
				+ "' OR `sex` = '0') AND `client_show` = '0'  ORDER BY `rank`,`sex` ASC;";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeyCategoryBean bean = new KeyCategoryBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("keyword"));
				bean.setSex(rs.getInt("sex"));
				bean.setRank(rs.getInt("rank"));
				bean.setIcon(rs.getString("icon"));
				bean.setList(getDiscountKeywords(bean.getId(), sex));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public ArrayList<KeyCategoryBean> getSearchKeywordCategorys(String version) throws SQLException {
		if(!"1".equals(version) && !"0".equals(version)){
			version = "0";
		}
		ArrayList<KeyCategoryBean> list = new ArrayList<KeyCategoryBean>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `status` = 0 AND `parent_id` = '0' and `version`="+ version +" order by rank desc;";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				KeyCategoryBean bean = new KeyCategoryBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setIcon(rs.getString("icon"));
				bean.setRank(rs.getInt("rank"));
				list.add(bean);
			}
		} catch (SQLException e) {
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	

	public ArrayList<KeywordBean> getSearchKeywords(String catId )
			throws SQLException {
		if(catId == null || "".equals(catId))
			return null;
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `status` = 0 AND `parent_id` = '"
				+ catId + "'  ORDER BY `rank` DESC;";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()){
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setKeyword(rs.getString("name"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setIcon(rs.getString("icon"));
				bean.setRank(rs.getString("rank"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public KeywordBean getSearchKeyword(String catId) throws SQLException {
		if(catId == null || "".equals(catId))
			return null;
		KeywordBean bean = new KeyCategoryBean().new KeywordBean();
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `status` = 0 AND `id` = '" + catId +"';";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()){
				bean.setId(rs.getString("id"));
				bean.setKeyword(rs.getString("name"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setIcon(rs.getString("icon"));
				bean.setRank(rs.getString("rank"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
			}
		} catch (SQLException e) {
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return bean;
	}

	public ArrayList<KeywordBean> getSearchKeywordsNew(String catId,String version)
			throws SQLException {
		if(catId == null || "".equals(catId))
			return null;
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_search_keywords` WHERE `status` = 0 AND `parent_id` = '"
				+ catId + "' and version = "+version+" ORDER BY `rank` DESC;";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			// conn.createStatement().execute("use youhui_datamining");
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()){
				KeywordBean bean = new KeyCategoryBean().new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setKeyword(rs.getString("name"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setSearchTimes(rs.getString("search_times"));
				bean.setIcon(rs.getString("icon"));
				bean.setRank(rs.getString("rank"));
				bean.setAction(new Action(rs.getString("action_type"), rs.getString("action_value")));
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e, e);
			throw e;
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}
	
	public ArrayList<KeywordBean> getTYHShowKeywords(String parentId) {
		ArrayList<KeywordBean> list = new ArrayList<KeywordBean>();
		String sql = "SELECT * FROM `youhui_datamining`.`m_discount_keywords` WHERE `parent_id` = '"+parentId+"';";
		Connection conn = MySQLDBIns.getInstance().getConnection();
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			KeyCategoryBean cat = new KeyCategoryBean();
			while (rs.next()) {
				KeywordBean bean = cat.new KeywordBean();
				bean.setId(rs.getString("id"));
				bean.setCategoryId(rs.getString("parent_id"));
				bean.setKeyword(rs.getString("keyword"));
				bean.setSex(rs.getInt("sex"));
				bean.setIcon(rs.getString("icon"));
				bean.setRank(rs.getString("rank"));
				bean.setHeat(rs.getString("heat"));
				bean.setShow(rs.getString("show"));
				if(rs.getInt("client_show") == 1)
				    bean.setClientShow(1);
				else bean.setClientShow(0);
				list.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			MySQLDBIns.getInstance().release(conn);
		}
		return list;
	}

	public List<KeywordBean> getAllKeywords() {
		String sql = "SELECT id,keyword,client_show,parent_id,pcat,fx_showindex,heat FROM `youhui_datamining`.`m_discount_keywords`  inner join (select id as pid,keyword as pcat from  `youhui_datamining`.`m_discount_keywords` where parent_id = 0) as T2 on T2.pid = parent_id order by parent_id";

		Connection conn = MySQLDBIns.getInstance().getConnection();
		ArrayList<KeywordBean> list = null;
		if (conn != null) {
			try {
				conn.createStatement().execute("use youhui_datamining");
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.first()) {
					KeyCategoryBean cat = new KeyCategoryBean();
					list = new ArrayList<KeywordBean>();
					while (rs.next()) {
						KeywordBean b = cat.new KeywordBean();
						b.setId(rs.getString("id"));
						b.setKeyword(rs.getString("keyword"));
						b.setParentCategoryId(rs.getInt("parent_id"));
						b.setParentCategoryName(rs.getString("pcat"));
						b.setFxShowIndex(rs.getInt("fx_showindex"));
						b.setClientShow(rs.getInt("client_show"));
						b.setHeat(rs.getString("heat"));
						list.add(b);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (conn != null) {
					MySQLDBIns.getInstance().release(conn);
				}
			}

		}

		return list;
	}
	
	
}
