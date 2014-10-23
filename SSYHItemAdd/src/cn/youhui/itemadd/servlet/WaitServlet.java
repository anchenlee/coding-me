package cn.youhui.itemadd.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taobao.api.domain.Item;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.youhui.admin.dao.AddItemUtil;
import cn.youhui.bean.WaitBean;
import cn.youhui.itemDAO.ItemDAO;
import cn.youhui.itemDAO.WaitDAO;
import cn.youhui.itemadd.dataadapter.ItemBean;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.SQL;
import cn.youhui.platform.util.NetManager;

/**
 * Servlet implementation class WaitServlet
 */
@WebServlet("/wait")
public class WaitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getParameter("yes")!=null){
			if(request.getParameter("yes").equals("insert")){
				String tagids=zm(request.getParameter("tagsval"));
				String keywordids=zm(request.getParameter("keywordsval"));
				String home=request.getParameter("homeval");
				JSONArray ja=JSONObject.fromObject(zm(request.getParameter("content"))).getJSONArray("item_list");
				String itemId="";
				String original="";
				for(int i=0;i<ja.size();i++){
					 itemId=JSONObject.fromObject(ja.get(i)).getString("item_id");
					 original= JSONObject.fromObject(ja.get(i)).getString("pic_url");
				}
				if(WaitDAO.getInstance().insert(itemId,original,tagids,keywordids,home)&&ItemDAO.getInstance().setStatus(itemId,ItemDAO.STATUS_WAIT)){
					response.getWriter().write("success");
					return;
				}
				response.getWriter().write("fail");
			}else if(request.getParameter("yes").equals("submit")){
				long l1=System.currentTimeMillis();
				if(request.getParameter("id")==null||"".equals(request.getParameter("id"))){
					response.getWriter().write("paramException");
					return;
				}
				String id=request.getParameter("id");
				int counttag=0;int countkey=0;int counthome=0;
				Connection conn = SQL.getInstance().getConnection();;
				PreparedStatement ps  = null;
				
				long l2=System.currentTimeMillis();
				
				for(int i=0;i<id.split(",").length;i++){
					try{
						long la=System.currentTimeMillis();
						WaitBean wb=WaitDAO.getInstance().getById(Integer.parseInt(id.split(",")[i]),conn);
						long lb=System.currentTimeMillis();
						
						String tagids=wb.getTagids();
						String itemId=wb.getItemId();
						String ori=wb.getOriginal();
						String keywordids=wb.getKeywordids();
						String home=wb.getHome();
						
						long lc=System.currentTimeMillis();
						ItemBean item=ItemDAO.getInstance().getItem(itemId,conn);
						long ld=System.currentTimeMillis();
						
						long le=System.currentTimeMillis();
						String str=NetManager.getInstance().send(param.baoyou, "baoyou=baoyou&itemid="+itemId);
						long lf=System.currentTimeMillis();
						
						JSONObject jo=JSONObject.fromObject(str);
						if(jo.has("baoyou")){
							item.setBaoyou(jo.getInt("baoyou"));
						}
						
						if(jo.has("catid")){
							item.setCatId(jo.getString("catid"));
						}
						item.setOriginalImgUrl(ori);
						
						long lg=System.currentTimeMillis();
						
						if(tagids!=null&&!"".endsWith(tagids)&&AddItemUtil.addItem(item, tagids,"tagItem",conn)){
							counttag=counttag+tagids.split(",").length;
						}
						
						long xx=System.currentTimeMillis();
						System.out.println("11--->>"+(xx-lg));
						
						if(keywordids!=null&&!"".endsWith(keywordids)&&AddItemUtil.addItem(item, keywordids,"keywordItem",conn)){
							countkey=countkey+keywordids.split(",").length;
						}
						
						long xxx=System.currentTimeMillis();
						System.out.println("22--->>"+(xxx-xx));
						
						if(home.equals("1")){
							if(AddItemUtil.addItem(item, home,"recoItem",conn)){
								counthome++;
							}
						}
						long lh=System.currentTimeMillis();
						System.out.println("33--->>"+(lh-xxx));
						
						WaitDAO.getInstance().dellInfo(Integer.parseInt(id.split(",")[i]),conn);
						ItemDAO.getInstance().setStatus(itemId,ItemDAO.STATUS_FINISH,conn);
						
						long li=System.currentTimeMillis();
						System.out.println("lb-la::"+(lb-la));
						System.out.println("lc-lb::"+(lc-lb));
						System.out.println("ld-lc::"+(ld-lc));
//			暂时			System.out.println("le-ld::"+(le-ld));
//						System.out.println("lf-le::"+(lf-le));
//						System.out.println("lg-lf::"+(lg-lf));
						System.out.println("lh-lg::"+(lh-lg));
						System.out.println("li-lh::"+(li-lh));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				long l3=System.currentTimeMillis();
				
				System.out.println("l2-l1::"+(l2-l1));
				System.out.println("l3-l2::"+(l3-l2));
		
				SQL.getInstance().release(ps, conn);
				response.getWriter().write(String.valueOf(counttag+","+countkey+","+counthome));
			}else if(request.getParameter("yes").equals("deltag")){
				String tagId=request.getParameter("tagId");
				int id=Integer.parseInt(request.getParameter("id"));
				if(WaitDAO.getInstance().delTag(tagId, id)){
					response.getWriter().write("success");
					return;
				}
				response.getWriter().write("fail");
			}else if(request.getParameter("yes").equals("delkey")){
				String keywordId=request.getParameter("keywordId");
				int id=Integer.parseInt(request.getParameter("id"));
				if(WaitDAO.getInstance().delKey(keywordId, id)){
					response.getWriter().write("success");
					return;
				}
				response.getWriter().write("fail");
			}else if(request.getParameter("yes").equals("delhome")){
				int id=Integer.parseInt(request.getParameter("id"));
				if(WaitDAO.getInstance().delHome(id)){
					response.getWriter().write("success");
					return;
				}
				response.getWriter().write("fail");
			}else if(request.getParameter("yes").equals("addtkh")){
				if(request.getParameter("id")==null||"".equals(request.getParameter("id"))){
					response.getWriter().write("paramException");
					return;
				}
				String tagids=request.getParameter("tagsval");
				String keywordids=request.getParameter("keywordsval");
				String home=request.getParameter("homeval");
				String ids=request.getParameter("id");
				try{
					for(int i=0;i<ids.split(",").length;i++){
						int id=Integer.parseInt(ids.split(",")[i]);
						if(tagids!=null&&!"".equals(tagids)){
							WaitDAO.getInstance().addTag(tagids,id);
						}
						if(keywordids!=null&&!"".equals(keywordids)){
							WaitDAO.getInstance().addKey(keywordids, id);
						}
						if(home.equals("1")){
							WaitDAO.getInstance().addHome(id);
						}
					}
				}catch( Exception e){
					response.getWriter().write("fail");
				}
				response.getWriter().write("success");
			}else if(request.getParameter("yes").equals("del")){
				if(request.getParameter("id")==null||"".equals(request.getParameter("id"))||request.getParameter("itemId")==null||"".equals(request.getParameter("itemId"))){
					response.getWriter().write("paramException");
					return;
				}
				System.out.println("1--------------------------->>>"+System.currentTimeMillis());
				int id=Integer.parseInt(request.getParameter("id"));
				String itemId=request.getParameter("itemId");
				if(WaitDAO.getInstance().getCount(itemId)>1){
					if(WaitDAO.getInstance().dellInfo(id)){
						response.getWriter().write("success");
					}else{
						response.getWriter().write("fail");
					}
				}else{
					if(WaitDAO.getInstance().dellInfo(id)&&ItemDAO.getInstance().delInfo(itemId)){
						response.getWriter().write("success");
					}else{
						response.getWriter().write("fail");
					}
				}
				System.out.println("2--------------------------->>>"+System.currentTimeMillis());
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
		
//		String str="{"+
//    "'item_list': ["+
//        "{"+
//            "'recom': '【淘金币抵扣】韩国新生代潮流品牌，漫画风格平檐帽，让你成为人群中的焦点',"+
//            "jfb_rate': '0.0',"+
//           " 'title': '韩国正品代购HATSON新款帅气个性嘻哈帽平檐圆顶帽URBFIE3318',"+
//            "'pic_url': 'http: //static.etouch.cn/suishou/item_img/415388031265340.jpg',"+
//           " 'price_high': '345.0',"+
//           " 'click_url': 'http: //store.taobao.com/shop/view_shop.htm?user_number_id=11812219',"+
//            "'price_low': '345.0',"+
//           " 'item_id': '38955344940'"+
//       " }"+
//    "]"+
//"}";
//		JSONObject jso=JSONObject.fromObject(str);
		long ll=System.currentTimeMillis();
		System.out.println(NetManager.getInstance().send(param.baoyou, "baoyou=baoyou&itemid=37359005565"));
		System.out.println(System.currentTimeMillis()-ll);
	}
	
}
