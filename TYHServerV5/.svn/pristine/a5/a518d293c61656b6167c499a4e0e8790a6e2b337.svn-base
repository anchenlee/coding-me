package cn.youhui.api.superdiscount;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.common.Config;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.utils.Util;

/**
 * 获取标签下商品列表
 * jiangjun
 */
@WebServlet("/GetItemTagsForWeb")
public class GetItemTagsForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetItemTagsForWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<String> list=new ArrayList<String>();
//		list.add("1073");//优惠街
		if(Config.relase){
			list.add("909");
			list.add("1011");//女装惠
			list.add("1017");//女鞋惠
			list.add("1015");//化妆品
			list.add("1013");//男人惠
			
		}else{
			list.add("569");
			list.add("676");
			list.add("632");
			list.add("627");
		}
		int page = 1;
		int pageSize = 10;
		StringBuffer  jsa=new StringBuffer();
		jsa.append("[");
		for(String l:list){
			String tagId = l;
			KeywordBean tag = TagCacher.getInstance().getTag(tagId);
			
			if(l.equals("909")){
				tag.setKeyword("9.9包邮");
			}
			
			List<TeJiaGoodsBean> itemlist = null;
			itemlist = Tag2ItemCacher.getInstance().getItemsByTagid(tagId, page, pageSize);
			if (itemlist == null || itemlist.size() <=0){
				if (tag.getDefaultSonTagId() != null && !tag.getDefaultSonTagId().equals(""))
				{
					itemlist = Tag2ItemCacher.getInstance().getItemsByTagid(tag.getDefaultSonTagId(), page, pageSize);
				}
			}
			StringBuffer ja=new StringBuffer();
			ja.append("[");
			for(TeJiaGoodsBean tb:itemlist){
				double h=Double.parseDouble(tb.getPrice_high());
				double low=Double.parseDouble(tb.getPrice_low());
				if(h==0||low==h){
					tb.setDiscount(0+"");
				}else{
					DecimalFormat df = new DecimalFormat("#0.0");
					double d=low/h*10;
					tb.setDiscount(df.format(d));
				}
				if(tb.getPicCutUrl()!=null&&!"".equals(tb.getPicCutUrl())){
					tb.setPic_url(Util.getCustomImg(tb.getPicCutUrl(), "b"));
				}else{
					tb.setPic_url(Util.getCustomImg(tb.getPic_url(), "b"));
				}
				ja.append(tb.toJson().toString()+",");
			}
			ja.append("]");
			StringBuffer sb=new StringBuffer();
			sb.append("{\"tag_id\":\""+tagId+"\",\"tag_name\":\""+tag.getKeyword()+"\",\"tag_num\":\""+Tag2ItemCacher.getInstance().getItemsSizeByTagid(tagId)+"\",\"item\":"+ja.toString()+"}");
			jsa.append(sb.toString()+",");
		}
		jsa.append("]");
		System.out.println(jsa.toString());
		response.getWriter().print(jsa.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
