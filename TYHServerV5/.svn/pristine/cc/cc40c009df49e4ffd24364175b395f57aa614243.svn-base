package cn.youhui.manager;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.youhui.bean.MMixBean;
import cn.youhui.bean.MMixEighth;
import cn.youhui.bean.MMixWhole;
import cn.youhui.bean.MMixWhole;
import cn.youhui.bean.MMixWhole;
import cn.youhui.bean.SuiShouAction;
import cn.youhui.dao.MySQLDBIns;

public class MMixPageManager {

	private static int pageSize = 20;
	
	public static List<MMixBean> getMMixPageList(String pid,Connection conn){
		List<MMixBean> list = new ArrayList<MMixBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_mix_products where p_id=? ;";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, pid);
			rs = ps.executeQuery();
			while(rs.next()){
				MMixBean bean = new MMixBean();
				bean.setId(rs.getString("id"));
				bean.setIs_Levels(rs.getString("is_levels"));
				bean.setItem_id(rs.getString("item_id"));
				bean.setP_id(pid);
				bean.setPic(rs.getString("pic"));
				bean.setRank(rs.getInt("rank"));
				bean.setSsac(new SuiShouAction(rs.getString("action")));
				bean.setStatus(rs.getString("status"));
				bean.setTag_id(rs.getString("tag_id"));
				bean.setType(rs.getString("type"));
				bean.setProportion(rs.getString("proportion"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<MMixWhole> getMMixWholeList(int page,String tagid){
		List<MMixWhole> list = new ArrayList<MMixWhole>();
		if(page <1) page = 1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM youhui_datamining.m_mix_products where p_id='0'and tag_id=? order by id desc limit ?,? ;";
		
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagid);
			ps.setInt(2, (page-1)*pageSize);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			while(rs.next()){
				String wholeLevels = rs.getString("is_levels");
				String p_id = rs.getString("id");
				MMixWhole whole = new MMixWhole();
				whole.setSign(rs.getString("type"));
				whole.setProportion(rs.getString("proportion"));
				whole.setHeight(rs.getInt("height"));
				whole.setWidth(rs.getInt("width"));
				whole.setTitle(rs.getString("title"));
				if(rs.getString("bg_color") == null || "".equals(rs.getString("bg_color"))){
					whole.setBgColor("ffffff");
				}else{
					whole.setBgColor(rs.getString("bg_color"));
				}
				if(rs.getString("title_ico_url") == null || "".equals(rs.getString("title_ico_url"))){
					whole.setTitleIconUrl("http://static.etouch.cn/suishou/ad_img/9ctczm2ba.jpg");
				}else{
					whole.setTitleIconUrl(rs.getString("title_ico_url"));
				}
				if(wholeLevels!= null && wholeLevels.equals("1")){	//整个没有分页
					MMixBean bean = new MMixBean();
					bean.setId(rs.getString("id"));
					bean.setIs_Levels(rs.getString("is_levels"));
					bean.setItem_id(rs.getString("item_id"));
					bean.setP_id("0");
					bean.setPic(rs.getString("pic"));
					bean.setRank(rs.getInt("rank"));
					bean.setSsac(new SuiShouAction(rs.getString("action")));
					bean.setStatus(rs.getString("status"));
					bean.setTag_id(rs.getString("tag_id"));
					bean.setType(rs.getString("type"));
					bean.setProportion(rs.getString("proportion"));
					whole.setItem(bean);
					whole.setProportion(rs.getString("proportion"));
					list.add(whole);
				}else if(wholeLevels!= null && wholeLevels.equals("0")) {	//获取half
					List<MMixBean> halfList = getMMixPageList(p_id, conn);
					if(halfList != null && halfList.size() > 1){
						String halfOneLevels = halfList.get(0).getIs_Levels();
						String halfAnotherLevels = halfList.get(1).getIs_Levels();
						String halfType = halfList.get(0).getType();
						whole.setSign(halfType);
						MMixWhole oneHalf = new MMixWhole();
						oneHalf.setProportion(halfList.get(0).getProportion());
						if(halfOneLevels != null && halfOneLevels.equals("1")){	
							oneHalf.setItem(halfList.get(0));
						}else if(halfOneLevels != null && halfOneLevels.equals("0")){//获取half的quarter
							
							List<MMixBean> quarterList = getMMixPageList(halfList.get(0).getId(), conn);
							if(quarterList != null && quarterList.size() > 1){
								String quarterOneLevels = quarterList.get(0).getIs_Levels();
								String quarterAnotherLevels = quarterList.get(1).getIs_Levels();
								String quarterType = quarterList.get(0).getType();
								oneHalf.setSign(quarterType);
								oneHalf.setProportion(halfList.get(0).getProportion());
								MMixWhole oneQuarter = new MMixWhole();
								MMixWhole anotherQuarter = new MMixWhole();
								if(quarterOneLevels != null && quarterOneLevels.equals("1")){
									oneQuarter.setItem(quarterList.get(0));
								}else if(quarterOneLevels != null && quarterOneLevels.equals("0")){//获取quarter的eighth
									List<MMixBean> eighthList = getMMixPageList(quarterList.get(0).getId(),conn);
									if(eighthList != null && eighthList.size() > 1){
										MMixWhole oneEighth = new MMixWhole();
										oneEighth.setItem(eighthList.get(0));
										oneEighth.setProportion(eighthList.get(0).getProportion());
										MMixWhole anotherEighth = new MMixWhole();
										anotherEighth.setItem(eighthList.get(1));
										anotherEighth.setProportion(eighthList.get(1).getProportion());
//										MMixWhole itemMMixWhole0 = new MMixWhole();
										
										oneQuarter.setOnehalf(oneEighth);// (oneEighth);
										oneQuarter.setAnotnerHalf(anotherEighth);
										String eighthType = eighthList.get(0).getType();
										oneQuarter.setSign(eighthType);
//										oneQuarter.setProportion(eighthList.get(0).getProportion());
									}
								}
								oneQuarter.setProportion(quarterList.get(0).getProportion());
								oneHalf.setOnehalf(oneQuarter);

								if(quarterAnotherLevels != null && quarterAnotherLevels.equals("1")){
									anotherQuarter.setItem(quarterList.get(1));
								}else if(quarterAnotherLevels != null && quarterAnotherLevels.equals("0")){//获取quarter的eighth
									List<MMixBean> eighthList = getMMixPageList(quarterList.get(1).getId(),conn);
									if(eighthList != null && eighthList.size() > 1){
										MMixWhole oneEighth = new MMixWhole();
										oneEighth.setItem(eighthList.get(0));
										oneEighth.setProportion(eighthList.get(0).getProportion());
										MMixWhole anotherEighth = new MMixWhole();
										anotherEighth.setItem(eighthList.get(1));
										anotherEighth.setProportion(eighthList.get(1).getProportion());
										anotherQuarter.setOnehalf(oneEighth);
										anotherQuarter.setAnotnerHalf(anotherEighth);
										String eighthType = eighthList.get(0).getType();
										anotherQuarter.setSign(eighthType);
//										anotherQuarter.setProportion(eighthList.get(0).getProportion());
									}									
								}
								anotherQuarter.setProportion(quarterList.get(1).getProportion());
								oneHalf.setAnotnerHalf(anotherQuarter);
							}
						}
						whole.setOnehalf(oneHalf);
						
						
						MMixWhole anotherHalf = new MMixWhole();
						anotherHalf.setProportion(halfList.get(1).getProportion());
						if(halfAnotherLevels != null && halfAnotherLevels.equals("1")){
							anotherHalf .setItem(halfList.get(1));
						}else if(halfAnotherLevels != null && halfAnotherLevels.equals("0")){//获取half的quarter
							List<MMixBean> quarterList = getMMixPageList(halfList.get(1).getId(), conn);
							if(quarterList != null && quarterList.size() > 1){
								String quarterOneLevels = quarterList.get(0).getIs_Levels();
								String quarterAnotherLevels = quarterList.get(1).getIs_Levels();
								MMixWhole oneQuarter = new MMixWhole();
								MMixWhole anotherQuarter = new MMixWhole();
								String quarterType = quarterList.get(0).getType();
								anotherHalf.setSign(quarterType);
//								anotherHalf.setProportion(quarterList.get(0).getProportion());
								if(quarterOneLevels != null && quarterOneLevels.equals("1")){
									oneQuarter.setItem(quarterList.get(0));
								}else if(quarterOneLevels != null && quarterOneLevels.equals("0")){//获取quarter的eighth
									List<MMixBean> eighthList = getMMixPageList(quarterList.get(0).getId(),conn);
									if(eighthList != null && eighthList.size() > 1){
										MMixWhole oneEighth = new MMixWhole();
										oneEighth.setItem(eighthList.get(0));
										oneEighth.setProportion(eighthList.get(0).getProportion());
										MMixWhole anotherEighth = new MMixWhole();
										anotherEighth.setItem(eighthList.get(1));
										anotherEighth.setProportion(eighthList.get(1).getProportion());
										oneQuarter.setOnehalf(oneEighth);
										oneQuarter.setAnotnerHalf(anotherEighth);
										String eighthType = eighthList.get(0).getType();
										oneQuarter.setSign(eighthType);
//										oneQuarter.setProportion(eighthList.get(0).getProportion());
									}
								}
								oneQuarter.setProportion(quarterList.get(0).getProportion());
								anotherHalf.setOnehalf(oneQuarter);
								
								if(quarterAnotherLevels != null && quarterAnotherLevels.equals("1")){
									anotherQuarter.setItem(quarterList.get(1));
								}else if(quarterAnotherLevels != null && quarterAnotherLevels.equals("0")){//获取quarter的eighth
									List<MMixBean> eighthList = getMMixPageList(quarterList.get(1).getId(),conn);
									if(eighthList != null && eighthList.size() > 1){
										MMixWhole oneEighth = new MMixWhole();
										oneEighth.setItem(eighthList.get(0));
										oneEighth.setProportion(eighthList.get(0).getProportion());
										MMixWhole anotherEighth = new MMixWhole();
										anotherEighth.setItem(eighthList.get(1));
										anotherEighth.setProportion(eighthList.get(1).getProportion());
										anotherQuarter.setOnehalf(oneEighth);
										anotherQuarter.setAnotnerHalf(anotherEighth);
										String eighthType = eighthList.get(0).getType();
										anotherQuarter.setSign(eighthType);
//										anotherQuarter.setProportion(eighthList.get(0).getProportion());
									}
								}
								anotherQuarter.setProportion(quarterList.get(1).getProportion());
								anotherHalf.setAnotnerHalf(anotherQuarter);
							}
						}
						
						whole.setAnotnerHalf(anotherHalf);
						list.add(whole);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		
		return list;
	}
	
	public static int getListPage(String tagid){
		int count = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) as c FROM youhui_datamining.m_mix_products where p_id='0'and tag_id=?  ;";
		
		try {
			conn = MySQLDBIns.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tagid);
			
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			MySQLDBIns.getInstance().release(conn);
		}
		if(count % pageSize == 0){
			count = count / pageSize;
		}else{
			count = count / pageSize +1;
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		List<MMixWhole> list = getMMixWholeList(1,"569");
		for(MMixWhole bean : list){
			System.out.println(bean.getSign());
		}
	}
}
