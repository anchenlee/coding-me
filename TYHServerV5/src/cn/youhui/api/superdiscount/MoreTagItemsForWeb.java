package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.common.Config;
import cn.youhui.common.ParamConfig;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.Tag2TagCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.utils.Util;

/**
 * 更多标签下子标签以及商品
 * jiangjun
 */
@WebServlet("/MoreTagItemsForWeb")
public class MoreTagItemsForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MoreTagItemsForWeb() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=====================================");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StringBuffer sbf=new StringBuffer();
		String ptagid=request.getParameter("tag_id");
		int pages=Tag2ItemCacher.getInstance().getTotalPage(ptagid, ParamConfig.MOR_TAG_ITEMS_PAGESIZE);
		String pagNow=request.getParameter("pag_now");
		if(pagNow==null||"".equals(pagNow)){
			pagNow="1";
		}
		String allTags="[{\"tag_id\":\"909\",\"tag_name\":\"9.9包邮\"},{\"tag_id\":\"1011\",\"tag_name\":\"女装惠\"},{\"tag_id\":\"1017\",\"tag_name\":\"女鞋惠\"},{\"tag_id\":\"1015\",\"tag_name\":\"化妆品\"},{\"tag_id\":\"1013\",\"tag_name\":\"男人惠\"}]";
		if(!Config.relase){
			allTags="[{\"tag_id\":\"569\",\"tag_name\":\"网格\"},{\"tag_id\":\"676\",\"tag_name\":\"外套\"},{\"tag_id\":\"632\",\"tag_name\":\"服饰\"},{\"tag_id\":\"627\",\"tag_name\":\"内衣\"}]";
		}
		sbf.append("{\"parent_tags\":"+allTags+",\"pag_now\":\""+pagNow+"\",\"pages\":\""+pages+"\",\"item_tags\":");
		List<TeJiaGoodsBean> itemlist=Tag2ItemCacher.getInstance().getItemsByTagid(ptagid, Integer.parseInt(pagNow), ParamConfig.MOR_TAG_ITEMS_PAGESIZE);
		sbf.append("[{\"tag_name\":\""+TagCacher.getInstance().getTag(ptagid).getKeyword()+"\",\"items\":[");
		for(TeJiaGoodsBean item:itemlist){
			if(item.getPicCutUrl()!=null&&!"".equals(item.getPicCutUrl())){
				item.setPic_url(Util.getCustomImg(item.getPicCutUrl(), "b"));
			}else{
				item.setPic_url(Util.getCustomImg(item.getPic_url(), "b"));
			}
			
			double h=Double.parseDouble(item.getPrice_high());
			double l=Double.parseDouble(item.getPrice_low());
			if(h==0||l==h){
				item.setDiscount(0+"");
			}else{
				DecimalFormat df = new DecimalFormat("#0.0");
				double d=l/h*10;
				item.setDiscount(df.format(d));
			}
			sbf.append(item.toJson()+",");
		}
		if(itemlist.size()>0){
			sbf=new StringBuffer(sbf.toString().substring(0, sbf.length()-1));
		}
		sbf.append("]}]");
		
		if(request.getParameter("main")!=null&&request.getParameter("main").equals("1")){
			List<KeywordBean> list=Tag2TagCacher.getInstance().getTagsByParentId(ptagid, true);
			sbf.append(",\"tags\":[");
			for(int j=0;j<list.size();j++){
				KeywordBean kb=list.get(j);
				if(j==list.size()-1){
					sbf.append(kb.toJson());
				}else{
					sbf.append(kb.toJson()+",");
				}
			}
			sbf.append("]");
		}
		sbf.append("}");
		System.out.println(sbf.toString());
		response.getWriter().write(sbf.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
