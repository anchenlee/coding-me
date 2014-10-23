package com.netting.ItemUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bouncycastle.ocsp.Req;

import com.netting.bean.Tag_Bean;
import com.netting.dao.admin.Admin_Tag_DAO;
import com.netting.dao.admin.Admin_Tag_Item_DAO;
import com.netting.dao.admin.TaobaoAPI_DAO;
import com.netting.job.AddTagItemJob;
import com.netting.util.CodeUtil;
import com.taobao.api.domain.TaobaokeItem;

/**
 * 商品添加操作
 * @author belonghu
 *
 */
public class AddItemOperation {

	/**
	 * id添加商品
	 * @return
	 */
	public static String addTagItemById(HashMap<String,String> map){
		String tagId = map.get("tagId");
		String itemId = map.get("itemId");
		return addItem(tagId, itemId);
	}
	
	/**
	 * URL来添加商品
	 * @return
	 */
	public static String addTagItemByUrl(HashMap<String,String> map){
		String itemUrl = map.get("itemUrl");
		String tagId = map.get("tagId");
		String itemId = null;
    	Pattern pat = Pattern.compile("id=\\d+");
    	Matcher ma = pat.matcher(itemUrl);
    	if (ma.find())
    	{
    		itemId = ma.group();
    		itemId = itemId.replaceAll("id=", "").replaceAll("&", "");
    	}
		return CodeUtil.getRespJSONString("1","");
	}
	
	public static String addItem(String tagId,String itemId){
		Tag_Bean tag_bean = Admin_Tag_DAO.getTagBean(tagId);  		
		
		
		ArrayList<TaobaokeItem> items = TaobaoAPI_DAO.getItemList(itemId);
		
		
		if (items != null && items.size() > 0)
		{
			for (int i = 0; i < items.size(); i++)
			{
				TaobaokeItem item = items.get(i);
				AddTagItemJob.processTbkItem(item, tag_bean);
			}
		}else if(items == null){
			// 淘宝接口超时
			return(CodeUtil.getRespJSONString("4",""));
		}
		else
		{
			// 无法获取到淘宝客商品或者该商品没有返利
			return(CodeUtil.getRespJSONString("2",""));
		}
		AddTagItemJob.reLockHeaderItem(tag_bean);
		return(CodeUtil.getRespJSONString("0",""));
	}
	
}
