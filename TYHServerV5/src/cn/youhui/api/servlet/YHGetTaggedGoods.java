package cn.youhui.api.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.youhui.bean.ProductPageStyleBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.dao.MySQLDBIns;
import cn.youhui.ramdata.Tag4ItemCacher;
import cn.youhui.ramdata.TagStatsCacher;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.TPool;
import cn.youhui.utils.Util;

/**
 *  3.1版本开始暂停使用，用/tyh/taggoods
 */
@WebServlet("/tyh/taggoodsold")
public class YHGetTaggedGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(YHGetTaggedGoods.class);
	private static final String tagStylelList = "tagStylelList";
	private static final String tagStyleWaterflow = "tagStyleWaterflow";
	private static final String tagStyleGrid = "tagStyleGrid";
	private static final String tagStyleMix = "tagStyleMix";
	private static final String tagStyleWeb = "tagStyleWeb";
	private static final String tagStyleSingle = "tagStyleSingle";

	private int PAGE_SIZE = 15;


	public YHGetTaggedGoods() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		String access = request.getParameter("access_token");
		String tagId = request.getParameter("tagId");
		String page = request.getParameter("page");
		page = page == null ? "1" : page;
		String keyword = request.getParameter("keyword");
		String platform = request.getParameter("platform");
		String type = request.getParameter("type");
		keyword = keyword == null ? "" : keyword;
		if (request.getMethod().equals("GET")) {
			keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
		}
		if (access == null) {
			access = "";
		}
		if (uid == null) {
			uid = "";
		}
		if (tagId == null) {
			tagId = "";
		}
		if (keyword.equals("")) {
			response.getWriter()
					.print(RespStatusBuilder
							.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
		//update by gongwenjun 20121121
		//StringBuffer result = tagStyleMix.equals(type)?getXMLData1(keyword, page) : getXMLData(keyword, page, uid);
		StringBuffer result = new StringBuffer();
		if("ipad".equalsIgnoreCase(platform)){	
			PAGE_SIZE = 12;
			result = getXMLData(keyword, page, uid);
		}else{
			if(tagStyleMix.equals(type)) {
//				PAGE_SIZE = 10;
//				result = getXMLData1(keyword, page);
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			}
			else if ("tagStyleCategory".equals(type))
			{
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
			}
			else 
			{
				if(null==type || "".equals(type)) {
					PAGE_SIZE = 15;
				} else if(tagStylelList.equals(type)) {
					PAGE_SIZE = 15;
				} else if(tagStyleWaterflow.equals(type)) {
					PAGE_SIZE = 30;
				} else if(tagStyleGrid.equals(type)) {
					PAGE_SIZE = 20;
				} else if(tagStyleWeb.equals(type)) {
					PAGE_SIZE = 10;
				} else if(tagStyleSingle.equals(type)) {
					PAGE_SIZE = 20;
				}
				result = getXMLData(keyword, page, uid);
			}
		}
		if("1".equals(page) && !"".equals(uid) && !"".equals(keyword)){
			addUidTag(uid, tagId, keyword);
		}
		if (!tagId.equals("")) {
			addStats(tagId);
		}
		response.getWriter().println(result.toString());
		// response.sendRedirect(request.getContextPath()
		// + "/docs/xml/get_tagged_goods.xml");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private StringBuffer getXMLData(String keyword, String pageStr, String outerCode) {
		StringBuffer result = new StringBuffer();
		StringBuffer sb;
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<resp>");
		Tag4ItemCacher cacher = Tag4ItemCacher.getInstance();
//		int PAGE_SIZE = 15;
		try {
			sb = new StringBuffer();
			int total = cacher.getTagItemCount(keyword);
			int totalPage = (int) (total % PAGE_SIZE == 0 ? total / PAGE_SIZE
					: total / PAGE_SIZE + 1);
			int page = Integer.parseInt(pageStr);
			int tbPage = 1;
			totalPage = totalPage > 0 ? totalPage : 1;

			sb.append("<head><status>1000</status><desc><![CDATA[OK]]></desc>");
			sb.append("<page>" + page + "</page>");
			if (page <= totalPage) {
				sb.append("<total>" + totalPage + "</total>");
			} else {
//				long tbCount = TaobaokeManager.getTBKItemCount(keyword,
//						outerCode);
//				tbPage = (int) (tbCount % PAGE_SIZE == 0 ? tbCount / PAGE_SIZE
//						: tbCount / PAGE_SIZE + 1);
//				sb.append("<total>" + totalPage + tbPage + "</total>");
			}
			sb.append("</head>");
			sb.append("<data>");
			sb.append("<tag name=\"" + keyword + "\">");
			if (keyword != null && !keyword.equals("")) {

				ArrayList<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
				if (page < totalPage) {
					int start = (page - 1) * PAGE_SIZE;
					list = cacher.getTagItems(keyword, start, PAGE_SIZE);
				} else if (page == totalPage) {
					int start = (page - 1) * PAGE_SIZE;
					list = cacher.getTagItems(keyword, start, PAGE_SIZE);
					
					
//					ArrayList<TeJiaGoodsBean> tbList = TaobaokeManager
//							.getTBKItems(keyword, outerCode, page, PAGE_SIZE);
//					for (TeJiaGoodsBean bean : tbList) {
//						if (list.size() < PAGE_SIZE) {
//							list.add(bean);
//						} else {
//							break;
//						}
//					}
				} else {
//					page = page > tbPage ? tbPage : page;
//					list = TaobaokeManager.getTBKItems(keyword, outerCode,
//							page, PAGE_SIZE);
				}
				
//				list = TaobaokeManager.getBatchTBKConvert(list, outerCode);
				if (list != null)
					for (TeJiaGoodsBean bean : list) {
						if (bean != null) {
							sb.append("<item>");
							sb.append("<item_id>" + bean.getItem_id()
									+ "</item_id>");
							sb.append("<title><![CDATA["
									+ bean.getTitle().replaceAll("<[^>]+>", "")
									+ "]]></title>");
							sb.append("<img_big width=\""
									+ bean.getWidth_170()
									+ "\" heigh=\""
									+ bean.getHeight_170()
									+ "\"><![CDATA["
									+ Util.getCustomImg(bean.getPic_url(),
											"170x170") + "]]></img_big>");
							sb.append("<img_small width=\"" + bean.getWidth_m()
									+ "\" heigh=\"" + bean.getHeight_m()
									+ "\"><![CDATA["
									+ Util.getSmallImg(bean.getPic_url())
									+ "]]></img_small>");
							sb.append("<price phi=\""
									+ computer(bean.getPrice_high())
									+ "\" plow=\""
									+ computer(bean.getPrice_low())
									+ "\" off=\""
									+ computer(bean.getDiscount()) + "\" />");
							if (bean.getCommission() != null
									&& !bean.getCommission().equals("")) {
								try {
									sb.append("<commission value=\""
											+ new DecimalFormat("0.00").format(Double
													.parseDouble(bean
															.getCommission()) * 0.8)
											+ "\" rate=\""
											+ new DecimalFormat("0.00").format(Double
													.parseDouble(bean.getCommission_rate()) * 0.8)
											+ "\" />");
								} catch (Exception e) {
								}
							} else {
								sb.append("<commission value=\"0\" rate=\""
										+ bean.getCommission_rate() + "\" />");
							}
							if (bean.getClickURL().equals("")) {
								sb.append("<click><![CDATA[http://a.m.taobao.com/i"
										+ bean.getItem_id() + ".htm]]></click>");
							} else {
								sb.append("<click><![CDATA["
										+ bean.getClickURL() + "]]></click>");
							}
							sb.append("</item>");
						}
					}
			}
			sb.append("</tag>");
			sb.append("</data>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e, e);
			sb = new StringBuffer();
			sb.append("<head><status>1005</status><desc><![CDATA["
					+ e.toString() + "]]></desc></head>");
		}
		result.append(sb);
		result.append("</resp>");
		return result;
	}
	
//	private StringBuffer getXMLData1(String keyword, String pageStr) {
//		StringBuffer result = new StringBuffer();
//		StringBuffer sb;
//		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		result.append("<resp>");
//		Tag4StyleItemCacher cacher = Tag4StyleItemCacher.getInstance();
////		int PAGE_SIZE = 15;
//		try {
//			sb = new StringBuffer();
//			int total = cacher.getTagItemCount(keyword);
//			int totalPage = (int) (total % PAGE_SIZE == 0 ? total / PAGE_SIZE
//					: total / PAGE_SIZE + 1);
//			int page = Integer.parseInt(pageStr);
//			totalPage = totalPage > 0 ? totalPage : 1;
//
//			sb.append("<head><status>1000</status><desc><![CDATA[OK]]></desc>");
//			sb.append("<page>" + page + "</page>");
//			if (page <= totalPage) {
//				sb.append("<total>" + totalPage + "</total>");
//			} else {
//
//			}
//			sb.append("</head>");
//			sb.append("<data>");
//			sb.append("<tag name=\"" + keyword + "\">");
//			if (keyword != null && !keyword.equals("")) {
//				Tag4ItemCacher cacher1 = Tag4ItemCacher.getInstance();
//				ArrayList<TeJiaGoodsBean> list1 = cacher1.getTagItems(keyword, 0, 10);
//				ArrayList<ProductPageStyleBean> list = new ArrayList<ProductPageStyleBean>();
//				if (page < totalPage) {
//					int start = (page - 1) * PAGE_SIZE;
//					list = cacher.getTagItems(keyword, start, PAGE_SIZE);
//				} else if (page == totalPage) {
//					int start = (page - 1) * PAGE_SIZE;
//					list = cacher.getTagItems(keyword, start, PAGE_SIZE);
//				} else {
//				}
//				if (list != null)
//					for (ProductPageStyleBean bean : list) {
//						if (bean != null) {
//							//判断商品个数是否匹配，即是否下架
//							String arr[] = bean.getPids().split(",");
//							if(arr.length!=bean.getTjGoodsBeanList().size()){
//								int index = 0;
//								String temp = "";
//								for(TeJiaGoodsBean tempbean:bean.getTjGoodsBeanList()) {
//									temp=temp+tempbean.getItem_id()+",";
//								}
//								for(int i=0;i<arr.length;i++) {
//									if(!temp.contains(arr[i])) {//如果商品不存在
//										bean.getTjGoodsBeanList().add(i,list1.get(index>=list1.size()-1?list1.size()-1:index));
//										index++;
//									}
//								}
//								if(arr.length!=bean.getTjGoodsBeanList().size()) {//完了再验证一次，如果还有问题，跳出
//									continue;
//								}
////								bean.getTjGoodsBeanList().add(list.get(list.size()-1).getTjGoodsBeanList().get(0));
////								bean.getTjGoodsBeanList().add(list1.get(index>=list1.size()-1?list1.size()-1:index));
//
//							}
//							//add itemspage
//							sb.append("<itemspage style=\"style"+bean.getStyle()+"\">");
//							for(TeJiaGoodsBean tjbean : bean.getTjGoodsBeanList()) {
//							
//							sb.append("<item>");
//							sb.append("<item_id>" + tjbean.getItem_id()
//									+ "</item_id>");
//							sb.append("<title><![CDATA["
//									+ tjbean.getTitle().replaceAll("<[^>]+>", "")
//									+ "]]></title>");
//							sb.append("<img_big width=\""
//									+ tjbean.getWidth_170()
//									+ "\" heigh=\""
//									+ tjbean.getHeight_170()
//									+ "\"><![CDATA["
//									+ Util.getCustomImg(tjbean.getPic_url(),
//											"170x170") + "]]></img_big>");
//							sb.append("<img_small width=\"" + tjbean.getWidth_m()
//									+ "\" heigh=\"" + tjbean.getHeight_m()
//									+ "\"><![CDATA["
//									+ Util.getSmallImg(tjbean.getPic_url())
//									+ "]]></img_small>");
//							sb.append("<price phi=\""
//									+ computer(tjbean.getPrice_high())
//									+ "\" plow=\""
//									+ computer(tjbean.getPrice_low())
//									+ "\" off=\""
//									+ computer(tjbean.getDiscount()) + "\" />");
//							if (tjbean.getCommission() != null
//									&& !tjbean.getCommission().equals("")) {
//								try {
//									sb.append("<commission value=\""
//											+ new DecimalFormat("0.00").format(Double
//													.parseDouble(tjbean
//															.getCommission()) * 0.8)
//											+ "\" rate=\""
//											+ new DecimalFormat("0.00").format(Double
//													.parseDouble(tjbean.getCommission_rate()) * 0.8)
//											+ "\" />");
//								} catch (Exception e) {
//								}
//							} else {
//								sb.append("<commission value=\"0\" rate=\""
//										+ tjbean.getCommission_rate() + "\" />");
//							}
//							if (tjbean.getClickURL().equals("")) {
//								sb.append("<click><![CDATA[http://a.m.taobao.com/i"
//										+ tjbean.getItem_id() + ".htm]]></click>");
//							} else {
//								sb.append("<click><![CDATA["
//										+ tjbean.getClickURL() + "]]></click>");
//							}
//							sb.append("</item>");
//							}//item end
//							sb.append("</itemspage>");
//						}
//					}
//			}
//			sb.append("</tag>");
//			sb.append("</data>");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			log.error(e, e);
//			sb = new StringBuffer();
//			sb.append("<head><status>1005</status><desc><![CDATA["
//					+ e.toString() + "]]></desc></head>");
//		}
//		result.append(sb);
//		result.append("</resp>");
//		return result;
//	}

	private double computer(String priceStr) {
		double price = 0;
		try {
			if (priceStr != null && priceStr.length() != 0)
				price = Double.parseDouble(priceStr);
		} catch (Exception e) {
			// TODO: handle exception
			// log.error(e, e);
		}
		BigDecimal b1 = new BigDecimal(Double.toString(price));
		BigDecimal b2 = new BigDecimal(Double.toString(100));
		return b1.multiply(b2).doubleValue();
	}

	private void addStats(final String tagId) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Connection conn = MySQLDBIns.getInstance().getConnection();
				TagStatsCacher cacher = TagStatsCacher.getInstance();
				try {
					Statement s = conn.createStatement();
					String sql = "INSERT INTO `youhui_datamining`.`tyh_tag_stats`(`tag_id`,`click_count`,`timestamp`)VALUE('"
							+ tagId
							+ "','1','"
							+ System.currentTimeMillis()
							+ "') ON DUPLICATE KEY UPDATE `click_count` = `click_count` + 1,`timestamp` = '"
							+ System.currentTimeMillis() + "'";
					s.executeUpdate(sql);
					cacher.incrClickCount(tagId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error(e, e);
				} finally {
					MySQLDBIns.getInstance().release(conn);
				}
			}
		};
		TPool.getInstance().doit(r);
	}
	
	private  void addUidTag(final String uid, final String tagId,  final String keyword) {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Connection conn = MySQLDBIns.getInstance().getConnection();
				TagStatsCacher cacher = TagStatsCacher.getInstance();
				try {
					Statement s = conn.createStatement();
					String sql = "INSERT INTO `youhui_datamining`.`tyh_liulan_user_tag`(`uid`,`tagid`,`keyword`,`timestamp`)VALUE('"
							+ uid
							+ "','"+tagId+ "','"+keyword+"','"
							+ System.currentTimeMillis()+"');";
					s.executeUpdate(sql);
					cacher.incrClickCount(tagId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.error(e, e);
				} finally {
					MySQLDBIns.getInstance().release(conn);
				}
			}
		};
		TPool.getInstance().doit(r);
	}
	
	public static void main(String[] args) {
		new YHGetTaggedGoods().addUidTag("123","13","我去");
	}
}
