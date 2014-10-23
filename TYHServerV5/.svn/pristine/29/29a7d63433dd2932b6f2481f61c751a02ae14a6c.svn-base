package cn.youhui.bean;

import java.util.Date;
import java.util.Map;


import cn.youhui.cacher.dao.SuperCouponCacher;
import cn.youhui.cacher.dao.secondKillCacher;
import cn.youhui.cacher.dao.superItemTagsCacher;
import cn.youhui.common.Config;
import cn.youhui.common.ParamConfig;
import cn.youhui.dao.ShareItemDAO;
import cn.youhui.utils.DateUtil;
import cn.youhui.utils.OuterCode;
import cn.youhui.utils.ShareUtil;
import cn.youhui.utils.SuiShouActionUtil;


public class SuperDiscountBean implements Comparable<SuperDiscountBean>{
	/*
	 * 主键Id
	 */
	public int id;
	
	/*
	 * 商品Id
	 */
	public String itemId;

	/*
	 * 优惠标题
	 */
	public String title;

	/*
	 * 优惠图片
	 */
	public String img;

	/*
	 * 原价
	 */
	public double priceBefore;

	/*
	 * 现价
	 */
	public double priceLow;

	/*
	 * 限量数量
	 */
	public int num;

	/*
	 * 是否包邮，0不包 1包
	 */
	public int postage;

	/*
	 * 超级惠具体时间
	 */
	public long discountTimestamp;

	/*
	 * 插入时间
	 */
	public long addTimestamp;

	/*
	 * 超级优惠日期
	 */
	public String discountDate;

	/*
	 * 超级优惠开始时间
	 */
	public long startTimestamp;

	/*
	 * 超级优惠结束时间
	 */
	public long endTimestamp;
	
	/**
	 * 是否售罄0未售罄 1售罄
	 */
	public int soldOut;
	
	/**
	 * 排序
	 * @return
	 */
	public Integer rank;
	
	/**
	 * 是否秒杀商品 0不是 1是
	 * @return
	 */
	public int isSecondKill;
	
	/**
	 * 商品点击地址
	 */
	
	public String clickUrl;
		
	/**
	 * 剩余数量
	 */
	public int remainNum;
	
	/**
	 *标签 
	 */
	public String itemTags;
	
	/**
	 * 淘金币
	 */
	public int  taoJb;
	
	/**
	 * 是否隐藏
	 */
	public int isHide;
	
	
	
	
	public int getIsHide() {
		return isHide;
	}

	public void setIsHide(int isHide) {
		this.isHide = isHide;
	}

	public int getTaoJb() {
		return taoJb;
	}

	public void setTaoJb(int taoJb) {
		this.taoJb = taoJb;
	}

	public String getItemTags() {
		return itemTags;
	}

	public void setItemTags(String itemTags) {
		this.itemTags = itemTags;
	}

	public int getRemainNum() {
		return remainNum;
	}

	public void setRemainNum(int remainNum) {
		this.remainNum = remainNum;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public int getIsSecondKill() {
		return isSecondKill;
	}

	public void setIsSecondKill(int isSecondKill) {
		this.isSecondKill = isSecondKill;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public double getPriceBefore() {
		return priceBefore;
	}

	public void setPriceBefore(double priceBefore) {
		this.priceBefore = priceBefore;
	}

	public double getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(double priceLow) {
		this.priceLow = priceLow;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}



	public long getDiscountTimestamp() {
		return discountTimestamp;
	}

	public void setDiscountTimestamp(long discountTimestamp) {
		this.discountTimestamp = discountTimestamp;
	}

	public long getAddTimestamp() {
		return addTimestamp;
	}

	public void setAddTimestamp(long addTimestamp) {
		this.addTimestamp = addTimestamp;
	}

	
	
	public String getDiscountDate() {
		return discountDate;
	}

	public void setDiscountDate(String discountDate) {
		this.discountDate = discountDate;
	}

	public long getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(long startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public long getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public int getPostage() {
		return postage;
	}

	public void setPostage(int postage) {
		this.postage = postage;
	}

	public int getSoldOut() {
		return soldOut;
	}

	public void setSoldOut(int soldOut) {
		this.soldOut = soldOut;
	}

//	public String toXML(){
//		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.#");  
//		java.text.DecimalFormat   df2=new   java.text.DecimalFormat("#.##"); 
//		String priceBefore_str=df2.format(priceBefore);
//		String priceLow_str=df2.format(priceLow);
//		
//		
//		
//		SuiShouAction action = new SuiShouAction();
//		action.setUrl(SuiShouActionUtil.getSuiShouActionUrl("tagStyleItem", itemId));
//		
//		return new StringBuffer().append("<item>")
//				.append("<id>").append(id).append("</id>")
//				.append("<title><![CDATA[").append(title).append("]]></title>")
//				.append("<img>").append(img).append("</img>")
//				.append("<original_price>").append(priceBefore_str).append("</original_price>")
//				.append("<item_id>").append(itemId).append("</item_id>")
//				.append("<discount_price>").append(priceLow_str).append("</discount_price>")
//				.append("<limit_num>").append(num).append("</limit_num>")
//				.append("<is_sold_out>").append(soldOut).append("</is_sold_out>")
//				.append("<is_baoyou>").append(postage).append("</is_baoyou>")
//				.append("<is_second_kill>").append(isSecondKill).append("</is_second_kill>")
//				.append(action.toXML())
//				.append("</item>").toString();
//	}
	
	public String toJson(String platform){
		String outerCode = OuterCode.getOuterCode("", "", "0", "17", "");
		String click_web=(Config.SKIP_URL + "tyh_outer_code=" + outerCode + "&itemid=" +itemId);
		
//		Map<String,String> map=ShareItemDAO.getInstance().getByDiscountId(id);
		Map<String,String> map=null;
		String shareItem="";//单品分享
		if(map!=null){
				shareItem=ShareUtil.shareItem(map.get("title"), map.get("desc"),ParamConfig.shareMid+"?id="+id,img,platform);
		}else{
			shareItem=ShareUtil.shareItem(ParamConfig.SHARE_TITLE,title,ParamConfig.shareMid+"?id="+id,img,platform);
		}
		
		long aaa=System.currentTimeMillis();
		
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.#");  
		java.text.DecimalFormat   df2=new   java.text.DecimalFormat("#.##"); 
		String zk=df.format(priceLow/priceBefore*10.00);
		String yh=df2.format(priceBefore-priceLow);
		String priceBefore_str=df2.format(priceBefore);
		
//		priceLow=priceLow-(double)taoJb/100;//减去淘金币的现价
		String priceLow_str=df2.format(priceLow);
		
		String tags="";
		if(itemTags!=null&&!"".equals(itemTags)){
			for(int i=0;i<itemTags.split(",").length;i++){
				tags=tags+superItemTagsCacher.getInstance().getSuperItemTagsById(itemTags.split(",")[i]).toJson()+",";
			}
			if(tags.length()>0){
				tags=tags.substring(0, tags.length()-1);
			}
		}
		SuiShouAction action = new SuiShouAction();
		action.setUrl(SuiShouActionUtil.getSuiShouActionUrl(platform,"tagStyleItem", itemId));
		action.setUrl(SuiShouActionUtil.getSuiShouActionUrl(platform, "tagStyleItem", itemId));
		
		StringBuffer sb=new StringBuffer();
		
		
		if(isSecondKill==1){
			
			String str=ParamConfig.secondKillType+String.valueOf(id);
			SecondKill sk=secondKillCacher.getInstance().getSecondKillById(str);
			int status=0;// 0正常 1还未开抢 2结束了 或者卖光了
			if(sk.getKillStartTimestamp()>new Date().getTime() ){
				status=1;
			}
			if(sk.getKillEndTimestamp()<new Date().getTime()||sk.getRemainNum()==0){
				status=2;
			}
			
			//判断是否卖光
			if(sk.getRemainNum()==0){
				soldOut=1;
			}else if(new Date().getTime()>sk.getKillEndTimestamp()){
				soldOut=2;
			}
			
			String superCoupon="\"\"";
			SuperCouponBean scb=SuperCouponCacher.getInstance().getInfo(itemId);
			if(scb!=null){
				superCoupon=scb.toJson(platform);
			}
			sb.append("{\"super_coupon\":"+superCoupon+",");
			
			sb.append("\"id\":\""+id+"\",");
			sb.append("\"title\":\""+title+"\",");
			sb.append("\"img\":\""+img+"\",");
			sb.append("\"original_price\":\""+priceBefore_str+"\",");
			sb.append("\"item_id\":\""+itemId+"\",");
			sb.append("\"discount_price\":\""+priceLow_str+"\",");
			sb.append("\"limit_num\":\""+sk.getKillNum()+"\",");
			sb.append("\"is_sold_out\":\""+soldOut+"\",");
			sb.append("\"is_baoyou\":\""+postage+"\",");
			sb.append("\"second_kill_num\":\""+sk.getKillNum()+"\",");
			sb.append("\"second_kill_price\":\""+sk.getKillPrice()+"\",");
			sb.append("\"is_second_kill\":\""+isSecondKill+"\",");
			sb.append("\"coupon_id\":\""+ParamConfig.secondKillType+id+"\",");
			sb.append("\"kill_start_timestamp\":\""+sk.getKillStartTimestamp()+"\",");
			sb.append("\"time_now\":\""+new Date().getTime()+"\",");
			sb.append("\"click_url\":\""+clickUrl+"\",");
			sb.append("\"click_web\":\""+click_web+"\",");
			sb.append("\"status\":\""+status+"\",");
			sb.append("\"remain_num\":\""+sk.getRemainNum()+"\",");
			sb.append("\"tao_jb\":\""+taoJb+"\",");
			sb.append("\"di_tao_jb\":\""+(double)taoJb/100+"\",");
			sb.append("\"share_item\":\""+shareItem+"\",");
			if(tags.equals("")){
				sb.append("\"item_tags\":\"\",");
			}else{
				sb.append("\"item_tags\":["+tags+"],");
			}
			if(status==1){
				sb.append("\"time_distances\":\""+DateUtil.getRestTimeCH2(sk.getKillStartTimestamp())+"\",");
			}
			sb.append(action.toJson());
			sb.append("}");
		}else{
			int status=0;// 0正常 1已经还未开抢 2结束了 或者卖光了
			if(startTimestamp>new Date().getTime() ){
				status=1;
			}
			if(endTimestamp<new Date().getTime()||remainNum==0){
				status=2;
			}
			
			//判断是否卖光
			if(remainNum==0){
				soldOut=1;
			}else if(new Date().getTime()>endTimestamp){
				soldOut=2;
			}
			
			
			String superCoupon="\"\"";
			SuperCouponBean scb=SuperCouponCacher.getInstance().getInfo(itemId);
			if(scb!=null){
				superCoupon=scb.toJson(platform);
			}
			sb.append("{\"super_coupon\":"+superCoupon+",");
			
			sb.append("\"id\":\""+id+"\",");
			sb.append("\"start_timestamp\":\""+startTimestamp+"\",");
			sb.append("\"end_timestamp\":\""+endTimestamp+"\",");
			sb.append("\"time_now\":\""+new Date().getTime()+"\",");
			sb.append("\"title\":\""+title+"\",");
			sb.append("\"img\":\""+img+"\",");
			sb.append("\"original_price\":\""+priceBefore_str+"\",");
			sb.append("\"item_id\":\""+itemId+"\",");
			sb.append("\"discount_price\":\""+priceLow_str+"\",");
			sb.append("\"limit_num\":\""+num+"\",");
			sb.append("\"is_sold_out\":\""+soldOut+"\",");
			sb.append("\"is_baoyou\":\""+postage+"\",");
			sb.append("\"discount\":\""+zk+"\",");
			sb.append("\"save_money\":\""+yh+"\",");
			sb.append("\"is_second_kill\":\""+isSecondKill+"\",");
			sb.append("\"click_url\":\""+clickUrl+"\",");
			sb.append("\"click_web\":\""+click_web+"\",");
			sb.append("\"status\":\""+status+"\",");
			sb.append("\"remain_num\":\""+remainNum+"\",");
			sb.append("\"tao_jb\":\""+taoJb+"\",");
			sb.append("\"di_tao_jb\":\""+(double)taoJb/100+"\",");
			sb.append("\"share_item\":\""+shareItem+"\",");
			if(tags.equals("")){
				sb.append("\"item_tags\":\"\",");
			}else{
				sb.append("\"item_tags\":["+tags+"],");
			}
			if(status==1){
				sb.append("\"time_distances\":\""+DateUtil.getRestTimeCH2(startTimestamp)+"\",");
			}
			sb.append(action.toJson());
			sb.append("}");
		}
		
		return sb.toString();
	}
	
	public String toXML(String platform){
		Map<String,String> map=ShareItemDAO.getInstance().getByDiscountId(id);
		String shareItem="";//单品分享
		if(map!=null){
			shareItem=ShareUtil.shareItem(map.get("title"), map.get("desc"),ParamConfig.shareMid,img,platform);
		}
		
		java.text.DecimalFormat   df2=new   java.text.DecimalFormat("#.##"); 
		String priceBefore_str=df2.format(priceBefore);
		
//		priceLow=priceLow-(double)taoJb/100;//减去淘金币的现价
		String priceLow_str=df2.format(priceLow);
		
		String tags="";
		if(itemTags!=null&&!"".equals(itemTags)){
			for(int i=0;i<itemTags.split(",").length;i++){
				tags=tags+"<tag>"+superItemTagsCacher.getInstance().getSuperItemTagsById(itemTags.split(",")[i]).toXML()+"</tag>";
			}
		}
		SuiShouAction action = new SuiShouAction();
		action.setUrl(SuiShouActionUtil.getSuiShouActionUrl(platform,"tagStyleItem", itemId));
		
		StringBuffer sb=new StringBuffer();
		
		if(isSecondKill==1){
			
			String str=ParamConfig.secondKillType+String.valueOf(id);
			SecondKill sk=secondKillCacher.getInstance().getSecondKillById(str);
			int status=0;// 0正常 1还未开抢 2结束了 或者卖光了
			if(sk.getKillStartTimestamp()>new Date().getTime() ){
				status=1;
			}
			if(sk.getKillEndTimestamp()<new Date().getTime()||sk.getRemainNum()==0){
				status=2;
			}
			
			//判断是否卖光
			if(sk.getRemainNum()==0){
				soldOut=1;
			}else if(new Date().getTime()>sk.getKillEndTimestamp()){
				soldOut=2;
			}
			
			sb.append("<item>");
			sb.append("<id>"+id+"</id>");
			sb.append("<title><![CDATA["+title+"]]></title>");
			sb.append("<img>"+img+"</img>");
			sb.append("<original_price>"+priceBefore_str+"</original_price>");
			sb.append("<item_id>"+itemId+"</item_id>");
			sb.append("<is_sold_out>"+soldOut+"</is_sold_out>");
			sb.append("<is_baoyou>"+postage+"</is_baoyou>");
			sb.append("<second_kill_num>"+sk.getKillNum()+"</second_kill_num>");
			sb.append("<second_kill_price>"+sk.getKillPrice()+"</second_kill_price>");
			sb.append("<tao_jb>"+taoJb+"</tao_jb>");
			sb.append("<di_tao_jb>"+(double)taoJb/100+"</di_tao_jb>");
			sb.append("<is_second_kill>"+isSecondKill+"</is_second_kill>");
//			sb.append("<coupon_id>"+ParamConfig.secondKillType+id+"</coupon_id>");
			sb.append("<kill_start_timestamp>"+sk.getKillStartTimestamp()+"</kill_start_timestamp>");
			sb.append("<time_now>"+new Date().getTime()+"</time_now>");
			sb.append("<status>"+status+"</status>");
			sb.append("<remain_num>"+sk.getRemainNum()+"</remain_num>");
			sb.append("<share_item><![CDATA["+shareItem+"]]></share_item>");
			if(tags.equals("")){
				sb.append("<item_tags></item_tags>");
			}else{
				sb.append("<item_tags>"+tags+"</item_tags>");
			}
			if(status==1){
				sb.append("<time_distances>"+DateUtil.getRestTimeCH2(sk.getKillStartTimestamp())+"</time_distances>");
			}
			sb.append(action.toXML());
			sb.append("</item>");
		}else{
			int status=0;// 0正常 1还未开抢 2结束了 或者卖光了
			if(startTimestamp>new Date().getTime() ){
				status=1;
			}
			if(endTimestamp<new Date().getTime()||remainNum==0){
				status=2;
			}
			
			//判断是否卖光
			if(remainNum==0){
				soldOut=1;
			}else if(new Date().getTime()>endTimestamp){
				soldOut=2;
			}
			
			sb.append("<item>");
			sb.append("<id>"+id+"</id>");
			sb.append("<title><![CDATA["+title+"]]></title>");
			sb.append("<img>"+img+"</img>");
			sb.append("<original_price>"+priceBefore_str+"</original_price>");
			sb.append("<item_id>"+itemId+"</item_id>");
			sb.append("<discount_price>"+priceLow_str+"</discount_price>");
			sb.append("<limit_num>"+num+"</limit_num>");
			sb.append("<is_sold_out>"+soldOut+"</is_sold_out>");
			sb.append("<is_baoyou>"+postage+"</is_baoyou>");
			sb.append("<is_second_kill>"+isSecondKill+"</is_second_kill>");
			sb.append("<status>"+status+"</status>");
			sb.append("<remain_num>"+remainNum+"</remain_num>");
			sb.append("<start_timestamp>"+startTimestamp+"</start_timestamp>");
			sb.append("<time_now>"+new Date().getTime()+"</time_now>");
			sb.append("<tao_jb>"+taoJb+"</tao_jb>");
			sb.append("<di_tao_jb>"+(double)taoJb/100+"</di_tao_jb>");
			sb.append("<share_item><![CDATA["+shareItem+"]]></share_item>");
			if(tags.equals("")){
				sb.append("<item_tags></item_tags>");
			}else{
				sb.append("<item_tags>"+tags+"</item_tags>");
			}
			if(status==1){
				sb.append("<time_distances>"+DateUtil.getRestTimeCH2(startTimestamp)+"</time_distances>");
			}
			sb.append(action.toXML());
			sb.append("</item>");
		}
		return sb.toString();
	}

	@Override
	public int compareTo(SuperDiscountBean o) {
		return o.getRank().compareTo(this.getRank());
	}
}
