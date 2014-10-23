package cn.youhui.itemadd.dataadapter;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.apache.jasper.tagplugins.jstl.core.Param;

import com.mysql.jdbc.PreparedStatement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import cn.youhui.admin.dao.AdminRecoItemDAO;
import cn.youhui.admin.dao.AdminSearchKeyWordDAO;
import cn.youhui.admin.dao.AdminTagDAO;
import cn.youhui.admin.dao.GetItemUtil;
import cn.youhui.bean.Searchkeyword;
import cn.youhui.bean.TagBean;
import cn.youhui.bean.WaitBean;
import cn.youhui.itemDAO.ItemDAO;
import cn.youhui.itemDAO.WaitDAO;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.DBManager;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.util.NetManager;

public class BaseAdapter {
	private int _tabId ,_menuId;
	public BaseAdapter(int tabId,int menuId){
		_tabId = tabId;
		_menuId = menuId;
		
	}
	public String getMenu(){
		StringBuffer sb = new StringBuffer();
		ArrayList<MenuBean> list  = DBManager.getMenu(_tabId);
		String str="index.jsp";
		if(list!=null){
			for (MenuBean tabBean : list) {
				if(tabBean.id==5){
					str="oready.jsp";
				}else if(tabBean.id==7){
					str="wait.jsp";
				}
				if(_menuId == tabBean.id){
					sb.append("<li class=\"active\"><a href=\""+str+"?tabId="+_tabId+"&menuId="+tabBean.id+"\">"+tabBean.title+"</a></li>");
				}else {
					sb.append("<li><a href=\""+str+"?tabId="+_tabId+"&menuId="+tabBean.id+"\">"+tabBean.title+"</a></li>");
				}
			}
		}
		return sb.toString();
	}
	
	public String getTab(HttpServletRequest request){
		
		
		StringBuffer sb = new StringBuffer();
		ArrayList<TabBean> list  = DBManager.getTabList();
		if(list!=null){
			for (TabBean tabBean : list) {
				if(_tabId == tabBean.id){
					sb.append("<li class=\"active\"><a href=\"?tabId="+tabBean.id+"\">"+tabBean.title+"</a></li>");
				}else {
					sb.append("<li><a href=\"?tabId="+tabBean.id+"\">"+tabBean.title+"</a></li>");
				}
			}
		}
		
		sb.append("<li><a href=\"javascript:window.open('"+request.getContextPath()+"/DetailFromRedis2Phone');window.parent.location.reload()\">查看首页</a></li>");
		
		sb.append("<li>日期：<input style=\"display: inline;\" value='' type='text' id='date' name='senddate0' onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\" onchange='checkPlanDate(\"senddate0\")'/></li>");
		sb.append("<li>账号：<select onchange='selChange(this)'><option></option><option>yangyaping</option><option>wangqianying</option><option>luolu</option><option>qinran</option><option>shanshan</option><option>zhanglu</option><option>wangxz</option><option>fangzy</option><option>fangqy</option></select></li>");
		sb.append("<li id='lii'></li>");
		return sb.toString();
	}
	
	public String getContent(String id){
		StringBuffer sb = new StringBuffer();
		ArrayList<ItemBean> list  =new ArrayList<ItemBean>();
		list.add(ItemDAO.getInstance().getPreItem(id));
		if(list!=null)for (ItemBean itemBean : list) {
			sb.append(getItemContent(itemBean));
		}
		
		return "<ul id=\"ul_item\" class=\"list-group\">" +sb.toString() + "</ul>";
	}
	
	public String getContentAlready(String itemId,String titleOrRecom,int pageNow,HttpServletRequest request){
		
		
		
		StringBuffer sb = new StringBuffer();
		ArrayList<ItemBean> list  = DBManager.getPreItemListAlready( itemId, titleOrRecom, pageNow, request);
		if(list!=null){
			Connection conn=null;
			PreparedStatement ps=null;
			try{
				conn=SQL.getInstance().getConnection();
				for (ItemBean itemBean : list) {
					sb.append(getItemContentAlready(itemBean,conn));
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				SQL.getInstance().release(ps, conn);
			}
		}
		return "<ul id=\"ul_item\" class=\"list-group\">" +sb.toString() + "</ul>";
	}
	
	public HttpServletRequest tmp(HttpServletRequest request){
		long l=System.currentTimeMillis();
		
		List<String[]> l1=new ArrayList<String[]>();
		List<String[]> l2=new ArrayList<String[]>();
		
		if(param.json.equals("")){
			param.json=GetItemUtil.ListToString();
			System.out.println("xxxx");
//			param.json=NetManager.getInstance().send(param.tagAndKeyword_Address, "");
		}
		JSONObject jo=null;
		jo=JSONObject.fromObject(param.json);
		param.json=jo.toString();
		JSONObject d=(JSONObject) jo.get("data");
		
		JSONArray ja=(JSONArray) d.get("tags");
		for(int i=0;i<ja.size();i++){
			JSONObject tag=(JSONObject) ja.get(i);
			String idd=tag.getString("id");
			String name=tag.getString("name");
			String[] s=new String[2];
			s[0]=idd;
			s[1]=name;
			l1.add(s);
		}
		JSONArray ja2=(JSONArray) d.get("keywords");
		for(int i=0;i<ja2.size();i++){
			JSONObject jo2=ja2.getJSONObject(i);
			String idd=(String) jo2.get("id");
			String name=(String) jo2.get("name");
			String[] s=new String[2];
			s[0]=idd;
			s[1]=name;
			l2.add(s);
		}
		
		List<Searchkeyword> l3=AdminSearchKeyWordDAO.getShoudongKeywordList();
		request.setAttribute("list1", l1);
		request.setAttribute("list2", l2);
		request.setAttribute("list3", l3);
		
		System.out.println(System.currentTimeMillis()-l);
		return request;
	}
	
	public String getContent(){
		StringBuffer sb = new StringBuffer();
		ArrayList<ItemBean> list  = DBManager.getPreItemList();
		if(list!=null)for (ItemBean itemBean : list) {
			sb.append(getItemContent(itemBean));
		}
		
		return "<ul id=\"ul_item\" class=\"list-group\">" +sb.toString() + "</ul>";
	}
	public String getContent2(int pagNow){
		StringBuffer sb = new StringBuffer();
		ArrayList<ItemBean> list  = DBManager.getPreItemList2(pagNow);
		if(list!=null)for (ItemBean itemBean : list) {
			sb.append(getItemContent2(itemBean));
		}
		
		return "<ul style=\"width:1430px;margin:0 auto;\" class=\"list-group\">" +sb.toString() + "</ul>";
	}
	
	public String getContent4(HttpServletRequest request,int pagNow){
		long ll=System.currentTimeMillis();
		int num=WaitDAO.getInstance().getTotalNum();
		if(num==-1){
			num=1;
		}
		request.setAttribute("pagNum", num);
		StringBuffer sb = new StringBuffer();
		List<WaitBean> list=WaitDAO.getInstance().getAll(pagNow);
		if(list!=null)for (WaitBean waitBean : list) {
			ItemBean itemBean=ItemDAO.getInstance().getItem(waitBean.getItemId());
			sb.append(getItemContent4(itemBean,waitBean.getTagids(),waitBean.getKeywordids(),waitBean.getHome(),waitBean.getId()));
		}
		System.out.println("sddddddddddddddd::"+(System.currentTimeMillis()-ll));
		return "<ul style=\"width:1430px;\" class=\"list-group\">" +sb.toString() + "</ul>";
	}
	
	public String getContent3(){
		StringBuffer sb = new StringBuffer();
		ArrayList<ItemBean> list  = DBManager.getPreItemList3();
		int i=1;
		if(list!=null)for (ItemBean itemBean : list) {
			
			sb.append(getItemContent3(itemBean,i));
			i++;
		}
		
		return "<ul class=\"list-group\">" +sb.toString() + "</ul>";
	}
	
	public String getItemContent3(ItemBean ib,int i){
		Connection conn=SQL.getInstance().getConnection();
		int uid=DBManager.getRecord(ib.itemid, conn);
		String time=DBManager.getTime(ib.itemid, conn);
		String username=DBManager.getUsername(uid, conn);
		SQL.getInstance().release(null, conn);
		String sb ="<li style=\"float:left;height:200px;width:500px;margin:10px 0 0 10px\" class=\"list-group-item\"><input name=\"hid_itemId\" type=\"hidden\" value=\""+ib.itemid+"\"/>"+
				" <div style='display:inline' class=\"item-line\"> <img class=\"img-rounded item-img\" style=\"width:150px;display:block;\" src=\""+ib.imgurl+"_m.jpg\" alt=\""+ib.title+"\">" +
						"" +
						"<div  style=\"margin-left:200px\"><h6>"+ib.title+"</h6><span class=\"nick\">卖家:</span><span class=\"nickvalue\">"+ib.nick+"</span><span class=\"price\">价格:</span><span class=\"pricevalue\">"+ib.price+"</span>  <h6>推荐:"+ib.recom+"</h6><h6>备注: "+ib.remark+"</h6><h6>由: "+username+"在"+time+"添加</h6><a onclick=\"return confirm('确定将此记录删除?')\" href=\"javascript:del("+ib.itemid+")\">删除</a></div></div><div style=\"clear:both;\"></li>";
		return sb;
	}
	
	public String getItemContentAlready(ItemBean ib,Connection conn){
		
		String str="{\"recom\":\""+ib.recom+"\",\"jfb_rate\":\""+ib.rate+"\",\"title\":\""+ib.title+"\",\"pic_url\":\""+ib.imgurl+"\",\"price_high\":\""+ib.price+"\",\"price_low\":\""+ib.zkPrice+"\",\"item_id\":\""+ib.itemid+"\"}";
		DecimalFormat   df   =   new   DecimalFormat("#####0.00");  
		double zk=0;
		String tags="";
		String h="";
		String keywords="";
		String username="";
		
			int uid=DBManager.getRecord(ib.itemid, conn);
			username=DBManager.getUsername(uid, conn);
			String time=DBManager.getTime(ib.itemid, conn);
			List<TagBean> ltb=GetItemUtil.getTagBeanList(ib.itemid,conn);
			
			for(int i=0;i<ltb.size();i++){
				TagBean tb=ltb.get(i);
				String tagName=tb.getTag_name();
				String tagId=tb.getTag_id();
				if(i==ltb.size()-1){
					tags=tags+"<span style=\"position:relative;margin-left:20px;\">"+tagName+"<button onclick=\"delTok('"+tagId+"',this,'tag','"+ib.itemid+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>";
				}else{
					tags=tags+"<span style=\"position:relative;margin-left:20px;\">"+tagName+"<button onclick=\"delTok('"+tagId+"',this,'tag','"+ib.itemid+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>"+",";
				}
			}
			
			List<Searchkeyword> lkey=GetItemUtil.getKeywordList(ib.itemid,conn);
			
			for(int i=0;i<lkey.size();i++){
				Searchkeyword key=lkey.get(i);
				String keyName=key.getName();
				String keyId=key.getId();
				if(i==lkey.size()-1){
					keywords=keywords+"<span style=\"position:relative;margin-left:20px;\">"+keyName+"<button onclick=\"delTok('"+keyId+"',this,'keyword','"+ib.itemid+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>";
				}else{
					keywords=keywords+"<span style=\"position:relative;margin-left:20px;\">"+keyName+"<button onclick=\"delTok('"+keyId+"',this,'keyword','"+ib.itemid+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>"+",";
				}
			}
			
			if(AdminRecoItemDAO.isExit(ib.itemid,conn)){
				h="<span style=\"position:relative;margin-left:20px;\">首页<button onclick=\"delTok('11',this,'home','"+ib.itemid+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>";;
			}
			
			zk=Double.parseDouble(String.valueOf(ib.zkPrice))/Double.parseDouble(String.valueOf(ib.price));
			
			
			
			String imgType="_m"+ib.imgurl.substring(ib.imgurl.lastIndexOf("."),ib.imgurl.length());
		
		
		
		String sb ="<li id=\"li_"+ib.itemid+"\" class=\"list-group-item\">"+
				
				
				" <div style='display:inline' class=\"item-line\"> <img style=\"max-height: 100px;max-width: 100px\" class=\"img-rounded item-img\" src=\""+ib.imgurl+imgType+"\" alt=\""+ib.title+"\">" +
				"<a onclick=\"return confirm('确定将此记录删除?')\" href=\"javascript:del('"+ib.itemid+"','li_"+ib.itemid+"')\">删除</a>" +
				"<a  href=\"javascript:update('"+ib.itemid+"')\">编辑</a>"+
						"<div class=\"item-detail\" ><h4><a href=\"javascript:window.open('http://a.m.taobao.com/i"+ib.itemid+".htm')\">"+ib.title+"</a></h4><span class=\"nick\">商品id:</span><span class=\"nickvalue\">"+ib.itemid+"</span><span class=\"nick\">由:</span><span class=\"nickvalue\">"+username+"在"+time+"添加</span><span class=\"price\">折扣价格:</span><span class=\"pricevalue\">"+ib.zkPrice+"</span><span class=\"nick\">折扣:</span><span class=\"nickvalue\">"+df.format(zk)+"</span> <span class=\"nick\">返集分宝比例:</span><span class=\"nickvalue\">"+ib.rate+"</span>" ;
							if(ib.recom!=null&&!ib.recom.equals("")){
								sb=sb+"<h4>推荐:"+ib.recom+"</h4>";
							}
							if(ib.remark!=null&&!ib.remark.equals("")){
								sb=sb+"<h4>备注:"+ib.remark+"</h4>";
							}
							if(!tags.equals("")){
								sb=sb+"<h4>添加的主题:"+tags+"</h4>";
							}
							if(!keywords.equals("")){
								sb=sb+"<h4>添加的关键字:"+keywords+"</h4>";
							}
							if(!h.equals("")){
								sb=sb+"<h4>添加的首页:"+h+"</h4>";
							}
							sb=sb+"</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style=\"display:inline;position: absolute;right:2px;top: 50px;\"><input name='checkbox_item'  type=\"checkbox\" value='"+str+"' /></div></div><div style=\"clear:both;\"></li>";
							
							return sb;
	}
	
	public String getItemContent4(ItemBean ib,String tagids,String keywordids,String home,int id){
		int str=id;
		String tags="";
		Connection conn=SQL.getInstance().getConnection();
		int uid=DBManager.getRecord(ib.itemid, conn);
		String time=DBManager.getTime(ib.itemid, conn);
		String username=DBManager.getUsername(uid, conn);
		SQL.getInstance().release(null, conn);
		
		for(int i=0;i<tagids.split(",").length;i++){
			String tagId=tagids.split(",")[i];
			if(tagId==null||"".equals(tagId)){
				break;
			}
			String tagName=AdminTagDAO.getTagBean(tagId).getTag_name();
			if(i==tagids.split(",").length-1){
				tags=tags+"<span style=\"position:relative;margin-left:20px;\">"+tagName+"<button onclick=\"delTag('"+tagId+"',this,'"+id+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>";
			}else{
				tags=tags+"<span style=\"position:relative;margin-left:20px;\">"+tagName+"<button onclick=\"delTag('"+tagId+"',this,'"+id+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>"+",";
			}
		}
		String keywords="";
		for(int i=0;i<keywordids.split(",").length;i++){
			String keyId=keywordids.split(",")[i];
			if("".equals(keyId)||keyId==null){
				break;
			}
			String keyName=AdminSearchKeyWordDAO.getSearchKeyword(keyId).getName();
			
			if(i==keywordids.split(",").length-1){
				keywords=keywords+"<span style=\"position:relative;margin-left:20px;\">"+keyName+"<button onclick=\"delKey('"+keyId+"',this,'"+id+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>";
			}else{
				keywords=keywords+"<span style=\"position:relative;margin-left:20px;\">"+keyName+"<button onclick=\"delKey('"+keyId+"',this,'"+id+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>"+",";
			}
		}
		String h="";
		if(home.equals("1")){
			h="<span style=\"position:relative;margin-left:20px;\">首页<button onclick=\"delHome(this,'"+id+"')\" style=\"position:absolute;left:-15px;\" type=\"button\" class=\"close\" aria-hidden=\"true\">×</button></span>";
		}
		double zk=Double.parseDouble(String.valueOf(ib.zkPrice))/Double.parseDouble(String.valueOf(ib.price));
		DecimalFormat   df   =   new   DecimalFormat("#####0.00");   
		String sb ="<li class=\"list-group-item\">"+
				" <div style='display:inline' class=\"item-line\"> <img style=\"max-height: 100px;max-width: 100px\" class=\"img-rounded item-img\" src=\""+ib.imgurl+"\" alt=\""+ib.title+"\">" +
						"<a onclick=\"return confirm('确定将此记录删除?')\" href=\"javascript:del('"+id+"','"+ib.itemid+"')\">删除</a>" +
						"<a  href=\"javascript:update('"+ib.itemid+"')\">编辑</a>"+
						"<div class=\"item-detail\" ><h4><a href=\"javascript:window.open('http://a.m.taobao.com/i"+ib.itemid+".htm')\">"+ib.title+"</a></h4><span class=\"nick\">商品id:</span><span class=\"nickvalue\">"+ib.itemid+"</span><h4>由:"+username+"在"+time+"添加</h4><span class=\"price\">折扣价格:</span><span class=\"pricevalue\">"+ib.zkPrice+"</span><span class=\"nick\">折扣:</span><span class=\"nickvalue\">"+df.format(zk)+"</span> <span class=\"nick\">返集分宝比例:</span><span class=\"nickvalue\">"+ib.rate+"</span> <h4>推荐:"+ib.recom+"</h4><h4>备注:"+ib.remark+"</h4><h4>添加的主题:"+tags+"</h4><h4>添加的关键字:"+keywords+"</h4><h4>首页:"+h+"</h4></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style=\"display:inline;position: absolute;right:2px;top: 50px;\"><input name='checkbox_item'  type=\"checkbox\" value='"+str+"' onclick=\"check(this)\" /></div></div><div style=\"clear:both;\"></li>";
						return sb;
	}
	
	public String getItemContent(ItemBean ib){
		String str="{\"recom\":\""+ib.recom+"\",\"jfb_rate\":\""+ib.rate+"\",\"title\":\""+ib.title+"\",\"pic_url\":\""+ib.imgurl+"\",\"price_high\":\""+ib.price+"\",\"price_low\":\""+ib.zkPrice+"\",\"item_id\":\""+ib.itemid+"\"}";
		String sb ="<li class=\"list-group-item\">"+
				" <div style='display:inline' class=\"item-line\"> <img class=\"img-rounded item-img\" src=\""+ib.imgurl+"_m.jpg\" alt=\""+ib.title+"\">" +
						"" +
//						"<div class=\"item-detail\"><h4>"+ib.title+"</h4><span class=\"nick\">卖家:</span><span class=\"nickvalue\">"+ib.nick+"</span><span class=\"price\">价格:</span><span class=\"pricevalue\">"+ib.price+"</span> <h4>推荐:<input style=\"width:400px\" onchange=\"changeRecom(this,"+ib.id+")\" type=\"text\" value=\""+ib.recom+"\"></h4><h4>备注:<input style=\"width:400px\" onchange=\"changeRemark(this,"+ib.id+")\" type=\"text\" value=\""+ib.remark+"\"></h4></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style=\"display:inline;position: absolute;right:2px;top: 50px;\"><input name='checkbox_item'  type=\"checkbox\" value='"+str+"' /></div></div><div style=\"clear:both;\"></li>";
						"<div class=\"item-detail\"><h4>"+ib.title+"</h4><span class=\"nick\">卖家:</span><span class=\"nickvalue\">"+ib.nick+"</span><span class=\"price\">价格:</span><span class=\"pricevalue\">"+ib.price+"</span> <h4>推荐:"+ib.recom+"</h4><h4>备注:"+ib.remark+"</h4></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style=\"display:inline;position: absolute;right:2px;top: 50px;\"><input name='checkbox_item'  type=\"checkbox\" value='"+str+"' /></div></div><div style=\"clear:both;\"></li>";
						return sb;		
	}
	public String getItemContent2(ItemBean ib){
		Connection conn=SQL.getInstance().getConnection();
			int uid=DBManager.getRecord(ib.itemid, conn);
			String time=DBManager.getTime(ib.itemid, conn);
			String username=DBManager.getUsername(uid, conn);
			
		SQL.getInstance().release(null, conn);
		
		DecimalFormat   df   =   new   DecimalFormat("#####0.00");  
		double zk=Double.parseDouble(String.valueOf(ib.zkPrice))/Double.parseDouble(String.valueOf(ib.price));
		String sb ="<li class=\"list-group-item\"  >"+
				"<div class=\"item-line\"><img class=\"img-rounded item-img\" style=\"width:135px;\" src=\""+ib.imgurl+"\" alt=\""+ib.title+"\">" +
						"" +
						"<div class=\"item-detail\"><h4><a href=\"javascript:window.open('http://a.m.taobao.com/i"+ib.itemid+".htm')\">"+ib.title+"</a></h4><span class=\"nick\">卖家:</span><span class=\"nickvalue\">"+ib.nick+"</span><span class=\"price\">价格:</span><span class=\"pricevalue\">"+ib.price+"</span>   <span class=\"price\">折扣:</span><span class=\"pricevalue\">"+df.format(zk)+"</span><span>由:"+username+" 在 "+time+" 添加</span> <span class=\"nick\">返集分宝比例:</span><span class=\"nickvalue\">"+ib.rate+"</span> <h4>推荐:"+ib.recom+"</h4><h4>备注:"+ib.remark+"</h4></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<div style=\"display:inline;position: absolute;right:0;top: 20px;\"><input style=\"position:relative;right:200px\" class=\"btn btn-default\" type=\"button\" onclick=\"update("+ib.id+")\" value=\"编辑\"/><input style=\"position:relative;right:200px\" class=\"btn btn-default\" type=\"button\" onclick=\"del2("+ib.itemid+")\" value=\"删除\"/></div></div><div style=\"clear:both;\"></li>";
		return sb;
	}
	
	
}


