package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.youhui.admin.dao.AdminSearchKeyWordDAO;
import cn.youhui.admin.dao.GetItemUtil;
import cn.youhui.bean.Searchkeyword;
import cn.youhui.itemDAO.ItemDAO;
import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.platform.config.param;
import cn.youhui.platform.util.NetManager;

import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class UpdateItem
 */
@WebServlet("/update")
public class UpdateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateItem() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("yes")!=null){
			if(request.getParameter("yes").equals("updateInfo")){
				String id=request.getParameter("id");
				System.out.println(request.getParameter("item_title"));
				String title=zm(request.getParameter("item_title"));
				String recom=zm(request.getParameter("item_recom"));
				String remark=zm(request.getParameter("item_remark"));
				int rate2=(int)Double.parseDouble(request.getParameter("rate"));
				Double zkPrice=Double.parseDouble(request.getParameter("zk_price"));
				String imgSize=request.getParameter("img_size");
				
				
				
				if(ItemDAO.getInstance().uodateItem(Integer.parseInt(id), title, recom, remark,rate2,zkPrice,imgSize)){
					response.getWriter().write("success");
				}else{
					response.getWriter().write("fail");
				}
				
			}else if(request.getParameter("yes").equals("setitem")){
				String rate=request.getParameter("rate");
				int rate2=0;
				if(!rate.equals("0")){
					rate2=2;
				}
//				String zkPrice=request.getParameter("zkPrice");
				String zkPrice="";
				String itemId=request.getParameter("itemId");
				
				//------------2014.5.8
				DecimalFormat sdf=new DecimalFormat("#.00");
				String url1="http://ok.etao.com/api/batch_purchase_decision.do?src=etao_srp&items="+itemId;
				String url2="http://fetch.b17.cn/price?itemids="+itemId;
				String back="";
				try {
					back=NetManager.getInstance().getContent(url1);
					JSONObject json=JSONObject.fromObject(back);
					if(json.has("result")){
						if(json.getJSONObject("result").has(itemId)){
							if(json.getJSONObject("result").getJSONObject(itemId).has("currentPrice")){
								zkPrice=String.valueOf(sdf.format((json.getJSONObject("result").getJSONObject(itemId).getDouble("currentPrice"))));
							}
						}
					}
					
					if("".equals(zkPrice)){
						back=NetManager.getInstance().getContent(url2);
						json=JSONObject.fromObject(back);
						if(json.has(itemId)){
							zkPrice=String.valueOf(sdf.format(json.getDouble(itemId)));
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ItemDAO.getInstance().setStatus(itemId, ItemDAO.STATUS_IS_UPDATE);
					response.getWriter().print("success");
					return;
				}
				
				//---------------------------------
				
				if(ItemDAO.getInstance().uodateItem(Double.parseDouble(zkPrice), rate2, itemId)&&ItemDAO.getInstance().getNumByStatus1()){
					response.getWriter().write("success");
				}else{
					response.getWriter().write("fail");
				}
				
			}else if(request.getParameter("yes").equals("update")){
				System.out.println("1111111111111111----------------->>>>"+System.currentTimeMillis());
				if(request.getParameter("id")==null||"".equals(request.getParameter("id"))){
					response.getWriter().write("paramException");
					return;
				}
				String id=request.getParameter("id");
				ItemBean ib=ItemDAO.getInstance().getPreItem(id);
				request.setAttribute("item",ib);
				
				
				JSONObject jo=null;
				if(param.json.equals("")){
					String str=GetItemUtil.ListToString();
//				String str=NetManager.getInstance().send(param.tagAndKeyword_Address, "");
					jo=JSONObject.fromObject(str);
					param.json=jo.toString();
				}else{
					jo=JSONObject.fromObject(param.json);
				}
				
				
				Map<String,String> map2=new HashMap<String,String>();
				List<String[]> l1=new ArrayList<String[]>();
				List<String[]> l2=new ArrayList<String[]>();
				
				JSONObject d=(JSONObject) jo.get("data");
				
				
				JSONArray ja=(JSONArray) d.get("tags");
//				for(int i=0;i<ja.size();i++){
//					JSONObject tag=(JSONObject) ja.get(i);
//					String idd=tag.getString("id");
//					String name=tag.getString("name");
//					String[] s=new String[2];
//					s[0]=idd;
//					s[1]=name;
//					l1.add(s);
//				}
				for(int i=ja.size()-1;i>0;i--){
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
					
						map2.put(idd,name);
						String[] s=new String[2];
						s[0]=idd;
						s[1]=name;
						l2.add(s);
				}
				
				
				List<Searchkeyword> l3=AdminSearchKeyWordDAO.getShoudongKeywordList();
				
				request.setAttribute("list1", l1);
				request.setAttribute("list2", l2);
				request.setAttribute("list3", l3);
				request.setAttribute("pagNow", request.getParameter("pagNow"));
				request.setAttribute("id", id);
				if(ib.getRecom()!=null&&!ib.getRecom().equals("")){
					ib.setRecom(ib.getRecom().replaceAll("'",""));
					ib.setRecom(ib.getRecom().replaceAll("\"",""));
				}
				if(ib.title!=null&&!ib.title.equals("")){
					ib.setTitle(ib.getTitle().replaceAll("'", ""));
					ib.setTitle(ib.getTitle().replaceAll("\"", ""));
				}
				String itemval="{'recom':'"+ib.recom+"','jfb_rate':'"+ib.rate+"','title':'"+ib.title+"','pic_url':'"+ib.imgurl+"','price_high':'"+ib.price+"','click_url':'"+ib.shopurl+"','price_low':'"+ib.zkPrice+"','item_id':'"+ib.itemid+"'}";
				request.setAttribute("itemval", itemval);
				
				System.out.println("22222222222222222----------------->>>>"+System.currentTimeMillis());
				request.getRequestDispatcher("/update.jsp").forward(request,response);
			}else if(request.getParameter("yes").equals("uploadProfRecomImg")){
				String id=request.getParameter("id");
				String x=request.getParameter("x");
				String y=request.getParameter("y");
				String w=request.getParameter("w");
				String h=request.getParameter("h");
				String imgurl=request.getParameter("img");
				String imgname=System.nanoTime() + "";
				String type=imgurl.substring(imgurl.lastIndexOf("."),imgurl.length());
//				String url="http://a.suishouyouhui.cn/img/cutImg?imgUrl="+imgurl+"&imgsn="+imgname+"&x="+x+"&y="+y+"&w="+w+"&h="+h;
//				String url="http://localhost:8080/ImgManage/cutImg?imgUrl="+imgurl+"&imgsn="+imgname+"&x="+x+"&y="+y+"&w="+w+"&h="+h;
				String url="http://172.16.71.32:8081/img/cutImg?imgUrl="+imgurl+"&imgsn="+imgname+"&x="+x+"&y="+y+"&w="+w+"&h="+h;
				
				if(NetManager.getInstance().send(url,"").equals("success")){
					if(ItemDAO.getInstance().uodateItemImg("http://static.etouch.cn/suishou/item_img/"+imgname+type, id)){
						response.getWriter().write("success");
						return;
					}
				}
				
				response.getWriter().write("fail");
			}else{
				if(request.getParameter("val")==null||request.getParameter("id")==null||"".equals(request.getParameter("val"))||"".equals(request.getParameter("id"))){
					response.getWriter().write("paramException");
					return ;
				}
				String val=zm(request.getParameter("val"));
				String id=request.getParameter("id");
				if(request.getParameter("yes").equals("recom")){
					if(ItemDAO.getInstance().updateRecom(id, val)){
						response.getWriter().write("success");
					}else{
						response.getWriter().write("fail");
					}
				}else if(request.getParameter("yes").equals("remark")){
					if(ItemDAO.getInstance().updateRemark(id, val)){
						response.getWriter().write("success");
					}else{
						response.getWriter().write("fail");
					}
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public String zm(String str){
		byte[] b;
		String tmp="";
		try {
			b = str.getBytes("iso8859-1");
			tmp=new String(b,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
	public static void main(String[] args) {
		String zkPrice="";
		String itemId="16137413388";
		
		
		DecimalFormat sdf=new DecimalFormat("#.00");
//		String url1="http://ok.etao.com/api/batch_purchase_decision.do?src=etao_srp&items="+itemId;
		String url2="http://fetch.b17.cn/price?itemids="+itemId;
		String back="";
		try {
//			back=NetManager.getInstance().getContent(url1);
//			JSONObject json=JSONObject.fromObject(back);
//			if(json.has("result")){
//				if(json.getJSONObject("result").has(itemId)){
//					if(json.getJSONObject("result").getJSONObject(itemId).has("currentPrice")){
//						zkPrice=String.valueOf(sdf.format((json.getJSONObject("result").getJSONObject(itemId).getDouble("currentPrice"))));
//					}
//				}
//			}
//			
//			if("".equals(zkPrice)){
				back=NetManager.getInstance().getContent(url2);
				JSONObject json=JSONObject.fromObject(back);
				if(json.has(itemId)){
					zkPrice=String.valueOf(sdf.format(json.getDouble(itemId)));
				}
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
