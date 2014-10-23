package cn.youhui.bean;

import java.sql.Connection;
import java.text.SimpleDateFormat;

import cn.youhui.cache.dao.SuperCouponCacher;
import cn.youhui.cache.dao.SuperForWebCacher;
import cn.youhui.itemDAO.SecondKillDAO;
import cn.youhui.itemDAO.ShareItemDAO;
import cn.youhui.itemDAO.SuperItemTagsDAO;
import cn.youhui.platform.config.param;
import cn.youhui.platform.db.SQL;


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
	 * 剩余数量
	 */
	public int remainNum;
	
	public String itemTags;
	
	public int  taoJb;
	public int isHide;
	
	
	
	
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

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

	
	
	public int getIsSecondKill() {
		return isSecondKill;
	}

	public void setIsSecondKill(int isSecondKill) {
		this.isSecondKill = isSecondKill;
	}

	public String toXML(){
		
		Connection conn=SQL.getInstance().getConnection();
		
		String shareItem=ShareItemDAO.getInstance().getByDiscountId(id,"xml",conn);
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.#");  
		java.text.DecimalFormat   df2=new   java.text.DecimalFormat("#.##"); 
		String zk=df.format(priceLow/priceBefore*10.00);
		String yh=df2.format(priceBefore-priceLow);
		String priceBefore_str=df2.format(priceBefore);
		String priceLow_str=df2.format(priceLow);
		
		SQL.getInstance().release(null, conn);
		
		return new StringBuffer().append("<item>")
//				.append("<id>").append(id).append("</id>")
				.append("<title><![CDATA[").append(title).append("]]></title>")
				.append("<is_hide>").append(isHide).append("</is_hide>")
				.append("<img>").append(img).append("</img>")
				.append("<original_price>").append(priceBefore_str).append("</original_price>")
				.append("<item_id>").append(itemId).append("</item_id>")
				.append("<discount_price>").append(priceLow_str).append("</discount_price>")
				.append("<limit_num>").append(num).append("</limit_num>")
				.append("<is_sold_out>").append(soldOut).append("</is_sold_out>")
				.append("<is_baoyou>").append(postage).append("</is_baoyou>")
				.append("<discount>").append(zk).append("</discount>")
				.append("<save_money>").append(yh).append("</save_money>")
				.append("<share_item>").append(shareItem).append("</share_item>")
				.append("</item>").toString();
		
		
	}
	
	public String toJson(Connection conn){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String tags="";
		if(itemTags!=null&&!"".equals(itemTags)){
			for(int i=0;i<itemTags.split(",").length;i++){
				tags=tags+SuperItemTagsDAO.getInstance().getById(Integer.parseInt(itemTags.split(",")[i]),conn).toJson()+",";
			}
			if(tags.length()>0){
				tags=tags.substring(0, tags.length()-1);
			}
		}
		String shareItem=ShareItemDAO.getInstance().getByDiscountId(id,"json",conn);//单品分享数据
		if(shareItem.equals("")){
			shareItem="\""+shareItem+"\"";
		}
		String tmp=SuperForWebCacher.getSuperForWebById2(id+"");
		if(tmp!=null&&!"".equals(tmp)){
			tmp=""+SuperForWebCacher.getSuperForWebById2(id+"")+"";
		}else{
			tmp="\"\"";
		}
		
		if(isSecondKill==1){
			SecondKill sk=SecondKillDAO.getInstance().getInfo(id, param.secondkilltype);
			num=sk.getKillNum();
			remainNum=sk.getRemainNum();
		}
		String json = "{";
		json += "\"super_web\":"+tmp+",\"is_hide\":\""+isHide+"\",\"share_item\":"+shareItem+",\"tao_jb\":\""+taoJb+"\""+",\"remain_num\":\""+remainNum+"\""+",\"item_tags\":["+tags+ "],\"is_second_kill\":\""+isSecondKill+"\",\"id\":\"" + this.getId() + "\",\"item_id\":\"" + this.getItemId() + "\",\"title\":\"" + this.getTitle() + "\",\"img\":\"" + this.getImg() + "\",\"price_before\":\"" + this.getPriceBefore();
		json += "\",\"price_low\":\"" + this.getPriceLow() + "\",\"num\":\"" + this.getNum() +"\",\"postage\":\"" + this.getPostage() + "\",\"discount_timestamp\":\"" + sdf.format(this.getDiscountTimestamp());
		json += "\",\"add_timestamp\":\"" + sdf.format(this.getAddTimestamp()) + "\",\"discount_date\":\"" + this.getDiscountDate() + "\",\"start_timestamp\":\"" + sdf.format(this.getStartTimestamp());
		String superCoupon="\"\"";
		SuperCouponBean scb=SuperCouponCacher.getInstance().getInfo(itemId);
		if(scb!=null){
			superCoupon=scb.toJson();
		}
		json += "\",\"end_timestamp\":\"" + sdf.format(this.getEndTimestamp()) + "\",\"sold_out\":\"" + this.getSoldOut() + "\",\"rank\":\"" + this.getRank() + "\",\"super_coupon\":"+superCoupon+"}";
		
		
		return json;
	}
	public static String dat(long timestamp) {
		SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
		String date=sdf.format(timestamp);
		if(date.substring(0,1).equals("0")){
			date=date.substring(1,date.length());
		}
		return date;
	}

	@Override
	public int compareTo(SuperDiscountBean o) {
		return this.getRank().compareTo(o.getRank());
	}
}
