package cn.youhui.api.servlet2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.youhui.bean.KeyCategoryBean.KeywordBean;
import cn.youhui.bean.M_Mix_PagetypeBean;
import cn.youhui.bean.SuperDiscountBean;
import cn.youhui.bean.TeJiaGoodsBean;
import cn.youhui.cacher.dao.superDiscountCacher;
import cn.youhui.common.Config;
import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.manager.FrameTitleManager;
import cn.youhui.ramdata.M_Mix_PagetypeCacher;
import cn.youhui.ramdata.Tag2ItemCacher;
import cn.youhui.ramdata.Tag2TagCacher;
import cn.youhui.ramdata.TagCacher;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.RespStatusBuilder;
import cn.youhui.utils.SuperTools;

/**
 * @category 获取标签下商品列表
 * @author leejun
 * @since 2013-03-24
 */

@WebServlet("/tyh2/tagitems")
public class YHGetTagItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String tagStyleList = "tagStyleList";
	private static final String tagStyleWaterflow = "tagStyleWaterflow";
	private static final String tagStyleGrid = "tagStyleGrid";
	private static final String tagStyleMix = "tagStyleMix";
	private static final String tagStyleWeb = "tagStyleWeb";
	private static final String tagStyleSingle = "tagStyleSingle";
	private static final String tagStyleCategory = "tagStyleCategory";
	
    public YHGetTagItems() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String access = request.getParameter("access_token");
		String tagId = request.getParameter("tagid");
		String page = request.getParameter("page");
		String uid = request.getParameter("uid");
		if(uid == null)
			uid =""; 
		page = page == null ? "1" : page;
		int pageInt = Integer.parseInt(page);
		String platform = request.getParameter("platform");
		String type = request.getParameter("type");
		if (access == null) {
			access = "";
		}
		if (tagId == null || "".equals(tagId)) {
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
		KeywordBean tag = TagCacher.getInstance().getTag(tagId);
		if (tag == null)
		{
			response.getWriter().print(RespStatusBuilder.message(Enums.ActionStatus.PARAMAS_ERROR));
			return;
		}
		if(tag != null){
			if(tag.getShow() == null || "".equals(tag.getShow())){
				tag.setShow("fl");
			}
			if(tag.getKeyword() != null && tag.getKeyword().indexOf("超级惠") == 0){
//				tagId = FrameTitleManager.getActionValue("18");
//				tag = TagCacher.getInstance().getTag(tagId);
				String today=SuperTools.getToday();
				List<SuperDiscountBean> list=superDiscountCacher.getInstance().getSuperDisCountListByDate(today);
				if(list.size()==0){
					list=superDiscountCacher.getInstance().getSuperDisCountListByDate(SuperTools.getYesterday());
				}
				if(list.size()==0){
					list=superDiscountCacher.getInstance().getSuperDisCountListByDate(SuperTools.getYesterday2());
				}
				List<TeJiaGoodsBean> l=new ArrayList<TeJiaGoodsBean>();
				for(SuperDiscountBean sb:list){
					TeJiaGoodsBean tb=new TeJiaGoodsBean();
					tb.setBaoyou(sb.getPostage());
					tb.setPrice_high(sb.getPriceBefore()+"");
					tb.setPrice_low(sb.getPriceLow()+"");
					tb.setItem_id(sb.getItemId());
					tb.setRate(2);
					tb.setTitle(sb.getTitle());
					tb.setPic_url(sb.getImg());
					tb.setCommission_rate("2");
					tb.setClickURL(sb.getClickUrl());
					l.add(tb);
				}
				response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, 1, pageInt, changetoXml(l, null, uid,tag, platform)));
				return;
			}
		}
		int pageSize = 15;
		if("ipad".equalsIgnoreCase(platform)){	
			pageSize = 12;
		}else{
			if(tagStyleMix.equals(type)){
				pageSize = 10;
				int total = M_Mix_PagetypeCacher.getInstance().getTotalNum(tagId, pageSize);
				if(total > 0){
					ArrayList<M_Mix_PagetypeBean> list = M_Mix_PagetypeCacher.getInstance().getProductByKeyword(tagId, pageSize, page);
					List<KeywordBean> taglist = null;
					if (tag != null && tag.getHasSonTagAll() != null && tag.getHasSonTagAll().equals("1")){
						taglist = Tag2TagCacher.getInstance().getTagsByParentIdWithAll(tagId);
					}else{
						taglist = Tag2TagCacher.getInstance().getTagsByParentId(tagId, false);
					}
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, total, Integer.parseInt(page), changetoXml1(list, taglist, uid, tag, platform)));
					return;
				}
			}
			else if(tagStyleCategory.equals(type))
			{
				List<KeywordBean> taglist = Tag2TagCacher.getInstance().getTagsByParentIdNoAll(tagId);
				if(taglist != null && taglist.size() > 0){
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, 1, 1, changetoXml(null, taglist, uid, tag, platform)));
				}else{
					response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
				}
				return;
			}
			else
			{
				if(tagStyleList.equals(type)){
					pageSize = 15;
				} else if(tagStyleWaterflow.equals(type)) {
					pageSize = 30;
				} else if(tagStyleGrid.equals(type) || tagStyleSingle.equals(type)) {
					pageSize = 20;
				} else if(tagStyleWeb.equals(type)) {
					pageSize = 10;
				}
			}
		}
		
		// List<KeywordBean> taglist = Tag2TagCacher.getInstance().getTagsByParentIdWithAll(tagId);
		
		List<KeywordBean> taglist = null;
		if (tag != null && tag.getHasSonTagAll() != null && tag.getHasSonTagAll().equals("1")){
			taglist = Tag2TagCacher.getInstance().getTagsByParentIdWithAll(tagId);
		}else{
			taglist = Tag2TagCacher.getInstance().getTagsByParentId(tagId, false);
		}
		
		if("833".equals(tagId)){
			tagId = "843";
		}
		if("795".equals(tagId)){
			tagId = "806";
		}
		
		int itemPageCount = 0;
		List<TeJiaGoodsBean> itemlist = null;
		if (uid != null && !uid.equals("") && !uid.equalsIgnoreCase("null") && checkParentSonTag(tagId))
		{
			itemPageCount = Tag2ItemCacher.getInstance().getTag569TotalPageByUid(tagId, uid, pageSize);
			itemlist = Tag2ItemCacher.getInstance().getTag569ItemsByUid(tagId, uid, itemPageCount, pageInt, pageSize);
		}
		else
		{
			itemPageCount = Tag2ItemCacher.getInstance().getTotalPage(tagId, pageSize);
			itemlist = Tag2ItemCacher.getInstance().getItemsByTagid(tagId, pageInt, pageSize);
		}
		
//		if(itemPageCount == 0) 
//		{
//			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
//			return;
//		}
		
		if(pageInt < 1){
			pageInt = 1;
		}else if(pageInt > itemPageCount){
			pageInt = itemPageCount;
		}
		
		if (itemlist == null || !(itemlist.size() > 0))
		{
			if (tag.getDefaultSonTagId() != null && !tag.getDefaultSonTagId().equals(""))
			{
				itemlist = Tag2ItemCacher.getInstance().getItemsByTagid(tag.getDefaultSonTagId(), pageInt, pageSize);
			}
		}
		
		tag = TagCacher.getInstance().getTag(tagId);
		if(itemlist != null && itemlist.size() > 0){
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NORMAL_RETURNED, access, itemPageCount, pageInt, changetoXml(itemlist, taglist, uid,tag, platform)));
		}else{
			response.getWriter().print(RespStatusBuilder.message(ActionStatus.NO_RESULT).toString());
		}
	}

	private String changetoXml(List<TeJiaGoodsBean> itemlist,List<KeywordBean> taglist, String uid, KeywordBean tag, String platform){
		StringBuffer sb = new StringBuffer();
		if(tag != null){
			sb.append("<show>").append(tag.getShow()).append("</show>");
			if (null != tag.getChaye_icon() && !"".equals(tag.getChaye_icon()) 
					&& null != tag.getChayeAction())
			{
				sb.append("<chaye>");
				sb.append("<icon><![CDATA[").append(tag.getChaye_icon()).append("]]></icon>");
				sb.append("<wh_ratio><![CDATA[").append(tag.getChayeIconSize()).append("]]></wh_ratio>");
				sb.append(tag.getChayeAction().toXML());
				sb.append("</chaye>");
			}
		}
		if(taglist != null && taglist.size() > 0){
			sb.append("<tags>");
			for(KeywordBean k : taglist){
				sb.append(k.toXML());
			}
			sb.append("</tags>");
		}
		if(itemlist != null && itemlist.size() > 0){
			sb.append("<items>");
			for(TeJiaGoodsBean bean : itemlist){
				String tagId = "0";
				if(tag != null ){
					tagId = tag.getId();
				}
				String tagI = tagId;
				if(tagI.length() > 6){
					tagI = tagI.substring(tagI.length() - 6, tagI.length());
				}
				String outerCode = OuterCode.getOuterCode(uid, platform, bean.getRate() + "", "7", tagI);
				bean.setClickURL(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" + bean.getItem_id());
				sb.append(bean.toXML());
			}
			sb.append("</items>");
		}
		return sb.toString();
	}
	
	private String changetoXml1(ArrayList<M_Mix_PagetypeBean> itemlist, List<KeywordBean> taglist, String uid,KeywordBean tag, String platform){
		StringBuffer sb = new StringBuffer();
		if(tag != null){
			sb.append("<show>").append(tag.getShow()).append("</show>");
			if (null != tag.getChaye_icon() && !"".equals(tag.getChaye_icon()) && null != tag.getChayeAction())
			{
				sb.append("<chaye>");
				sb.append("<icon><![CDATA[").append(tag.getChaye_icon()).append("]]></icon>");
				sb.append("<wh_ratio><![CDATA[").append(tag.getChayeIconSize()).append("]]></wh_ratio>");
				sb.append(tag.getChayeAction().toXML());
				sb.append("</chaye>");
			}
		}
		if(taglist != null && taglist.size() > 0){
			sb.append("<tags>");
			for(KeywordBean k : taglist){
				sb.append(k.toXML());
			}
			sb.append("</tags>");
		}
		if(itemlist != null && itemlist.size() > 0){
			sb.append("<itemspages>");
			for (M_Mix_PagetypeBean bean : itemlist) {
				if (bean != null) {
					sb.append(bean.toXML(uid, platform));
				} 
			}
			sb.append("</itemspages>");
		}
			return sb.toString();
	}
	
	private boolean checkParentSonTag(String sonTagID)
	{
		boolean flag = false;
		
		List<KeywordBean> taglist = Tag2TagCacher.getInstance().getTagsByParentId("569", true);
		for (KeywordBean tag : taglist)
		{
			if (sonTagID.equals(tag.getId()))
			{
				flag = true;
			}
		}
		return flag;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}