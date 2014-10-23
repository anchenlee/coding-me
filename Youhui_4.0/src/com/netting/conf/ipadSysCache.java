package com.netting.conf;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统的默认缓存参数
 * @author YAOJIANBO
 *
 */
public class ipadSysCache 
{
	/**
	 * 标签的性别
	 */
	public static Map<String, String> TagSexMap = new HashMap<String, String>();
	static
	{
		TagSexMap.put("0", "通用");
		// TagSexMap.put("1", "女性");
		// TagSexMap.put("2", "男性");
	}
	
	/**
	 * 标签的状态
	 */
	public static Map<String, String> TagStatusMap = new HashMap<String, String>();
	static
	{
		TagStatusMap.put("1", "显示");
		TagStatusMap.put("2", "隐藏");
		TagStatusMap.put("3", "删除");
	}
	
	/**
	 * 是否是PAD标签
	 */
	public static Map<String, String> IsPadTagMap = new HashMap<String, String>();
	static
	{
		IsPadTagMap.put("1", "是");
		IsPadTagMap.put("0", "不是");
	}
	
	/**
	 * 是否在手机端展示"全部"的子标签
	 */
	public static Map<String, String> Has_Son_Tag_All_Map = new HashMap<String, String>();
	static
	{
		Has_Son_Tag_All_Map.put("0", "不展示");
		Has_Son_Tag_All_Map.put("1", "展示");
	}
	
	/**
	 * 标签的小条显示
	 */
	public static Map<String, String> TagSmallShowMap = new LinkedHashMap<String, String>();
	static
	{
		TagSmallShowMap.put("zk", "折扣");
		TagSmallShowMap.put("fl", "返利");
	}
	
	/**
	 * 标签的状态
	 */
	public static Map<String, String> GiftStatusMap = new HashMap<String, String>();
	static
	{
		GiftStatusMap.put("1", "正常");
		GiftStatusMap.put("0", "无效");
	}
	
	/**
	 * ActionType标签
	 */
	public static Map<String, String> ActionTypeMap = new LinkedHashMap<String, String>();
	static
	{
		ActionTypeMap.put("tagStyleGrid", "网格");
		ActionTypeMap.put("tagStyleSale", "特惠");		
		ActionTypeMap.put("tagStyleWaterflow", "瀑布流");
		ActionTypeMap.put("searchShop", "搜索店铺");
		ActionTypeMap.put("searchItem", "搜索商品");
		ActionTypeMap.put("shopDetail", "搜索店铺详情");
		ActionTypeMap.put("couponDetail", "搜索优惠券详情");
		ActionTypeMap.put("goodDetail", "搜索商品详情");
		ActionTypeMap.put("goodShare", "商品分享");
		ActionTypeMap.put("activityShare", "活动分享");
		ActionTypeMap.put("couponBehindBrand", "品牌下优惠券");
		ActionTypeMap.put("myCouponDetailView", "我的优惠详情月视图");
		ActionTypeMap.put("wSCDYouHui", "我收藏的优惠信息");
		ActionTypeMap.put("tagStyleWeb", "网页");
		ActionTypeMap.put("tagStyleAppPage", "系统页面");
		ActionTypeMap.put("systemWeb", "系统浏览器");
	}
	
	/**
	 * 标签的热度
	 */
	public static Map<String, String> TagHeatMap = new HashMap<String, String>();
	static
	{
		TagHeatMap.put("普通", "普通");
		TagHeatMap.put("最新", "最新");
		TagHeatMap.put("火热", "火热");
		TagHeatMap.put("推荐", "推荐");
		TagHeatMap.put("官方", "官方");
	}
	
	/**
	 * TAG展示内容，系统页面的选项
	 */
	public static Map<String, String> XTYM_Map = new HashMap<String, String>();
	static
	{
		XTYM_Map.put("loginPage", "登录页面");
		XTYM_Map.put("woDeTaoBaoPage", "我的页面");
		XTYM_Map.put("juYouHuiPage", "聚优惠");
		XTYM_Map.put("pinPaiHuiPage", "品牌惠");
		XTYM_Map.put("gengDuoPage", "更多");
		XTYM_Map.put("ssFenLeiPage", "搜索分类");
		XTYM_Map.put("mainPage", "首页");
		XTYM_Map.put("mianZePage", "免责声明");
		XTYM_Map.put("yJFKPage", "意见反馈");
		XTYM_Map.put("gYWMPage", "关于我们");
		XTYM_Map.put("xXZXPage", "消息中心页面");
		XTYM_Map.put("bangZhuPage", "帮助");
		XTYM_Map.put("faXianPinPaiPage", "发现更多品牌");
		XTYM_Map.put("wDYHXQPage", "我的优惠详情/我的省钱之旅");
		XTYM_Map.put("liWuPage", "礼物页面");
		XTYM_Map.put("zHSZPage", "账户设置");
		XTYM_Map.put("zJPage", "足迹");
		XTYM_Map.put("jfbHistoryPage", "集分宝历史");
		XTYM_Map.put("miBaoSetPage", "设置密保界面");
		XTYM_Map.put("miBaoReSetPage", "重置密保界面");
		XTYM_Map.put("mobileForgetPage", "忘记密码");
		XTYM_Map.put("mobileRegisterPage", "注册手机");
	}

	
	/**
	 * 淘宝获取商品排序条件
	 */
	public static LinkedHashMap<String, String> ItemSort = new LinkedHashMap<String, String>();
	static
	{
		ItemSort.put("price_desc", "价格从高到低");
		ItemSort.put("price_asc", "价格从低到高");
		ItemSort.put("credit_desc", "信用等级从高到低");
		ItemSort.put("commissionRate_desc ", "佣金比率从高到低");
		ItemSort.put("commissionRate_asc ", "佣金比率从低到高");
		ItemSort.put("commissionNum_desc ", "成交量从高到低");
		ItemSort.put("commissionNum_asc ", "成交量从低到高");
		ItemSort.put("commissionVolume_desc ", "总支出佣金从高到低");
		ItemSort.put("commissionVolume_asc", "总支出佣金从低到高");
		ItemSort.put("delistTime_desc ", "商品下架时间从高到低");
		ItemSort.put("delistTime_asc ", "商品下架时间从低到高");
	}

	public static LinkedHashMap<String, String> xinyongdengji = new LinkedHashMap<String, String>();
	static
	{
		xinyongdengji.put("1heart", "一心");
		xinyongdengji.put("2heart", "二心");
		xinyongdengji.put("3heart", "三心");
		xinyongdengji.put("4heart", "四心");
		xinyongdengji.put("5heart", "五心");
		xinyongdengji.put("1diamond", "一钻");
		xinyongdengji.put("2diamond", "二钻");
		xinyongdengji.put("3diamond", "三钻");
		xinyongdengji.put("4diamond", "四钻");
		xinyongdengji.put("5diamond", "五钻");
		xinyongdengji.put("1crown", "一冠");
		xinyongdengji.put("2crown", "二冠");
		xinyongdengji.put("3crown", "三冠");
		xinyongdengji.put("4crown", "四冠");
		xinyongdengji.put("5crown", "五冠");
		xinyongdengji.put("1goldencrown", "一黄冠");
		xinyongdengji.put("2goldencrown", "二黄冠");
		xinyongdengji.put("3goldencrown", "三黄冠");
		xinyongdengji.put("4goldencrown", "四黄冠");
		xinyongdengji.put("5goldencrown", "五黄冠");
	}

	public static LinkedHashMap<String, String> xinyongIcon = new LinkedHashMap<String, String>() ;
	static 
	{
		xinyongIcon.put("1", "http://pics.taobaocdn.com/newrank/s_red_1.gif");
		xinyongIcon.put("2", "http://pics.taobaocdn.com/newrank/s_red_2.gif");
		xinyongIcon.put("3", "http://pics.taobaocdn.com/newrank/s_red_3.gif");
		xinyongIcon.put("4", "http://pics.taobaocdn.com/newrank/s_red_4.gif");
		xinyongIcon.put("5", "http://pics.taobaocdn.com/newrank/s_red_5.gif");
		xinyongIcon.put("6", "http://pics.taobaocdn.com/newrank/s_blue_1.gif");
		xinyongIcon.put("7", "http://pics.taobaocdn.com/newrank/s_blue_2.gif");
		xinyongIcon.put("8", "http://pics.taobaocdn.com/newrank/s_blue_3.gif");
		xinyongIcon.put("9", "http://pics.taobaocdn.com/newrank/s_blue_4.gif");
		xinyongIcon.put("10", "http://pics.taobaocdn.com/newrank/s_blue_5.gif");
		xinyongIcon.put("11", "http://pics.taobaocdn.com/newrank/s_cap_1.gif");
		xinyongIcon.put("12", "http://pics.taobaocdn.com/newrank/s_cap_1.gif");
		xinyongIcon.put("13", "http://pics.taobaocdn.com/newrank/s_cap_1.gif");
		xinyongIcon.put("14", "http://pics.taobaocdn.com/newrank/s_cap_1.gif");
		xinyongIcon.put("15", "http://pics.taobaocdn.com/newrank/s_cap_1.gif");
		xinyongIcon.put("16", "http://pics.taobaocdn.com/newrank/s_crown_1.gif");
		xinyongIcon.put("17", "http://pics.taobaocdn.com/newrank/s_crown_2.gif");
		xinyongIcon.put("18", "http://pics.taobaocdn.com/newrank/s_crown_3.gif");
		xinyongIcon.put("19", "http://pics.taobaocdn.com/newrank/s_crown_4.gif");
		xinyongIcon.put("20", "http://pics.taobaocdn.com/newrank/s_crown_5.gif");
	}
	
	/**
	 * 集分宝活动的规则
	 */
	public static Map<String, String> ActivityRuleMap = new HashMap<String, String>();
	static
	{
		ActivityRuleMap.put("all", "都可以参加");
	}
	
	/**
	 * 集分宝活动的参加频次
	 */
	public static Map<String, String> ActivityFrequencyMap = new HashMap<String, String>();
	static
	{
		ActivityFrequencyMap.put("1", "只能参加一次");
		ActivityFrequencyMap.put("2", "每天一次");
		ActivityFrequencyMap.put("3", "每周一次");
	}

	
	/**
	 * 广告的平台类型
	 */
	public static Map<String, String> ADPlatformMap = new HashMap<String, String>();
	static
	{
		ADPlatformMap.put("all", "all");
		ADPlatformMap.put("quanbu", "全部");
		ADPlatformMap.put("android", "android");
		ADPlatformMap.put("ipad2.0", "ipad2.0");
		ADPlatformMap.put("iphone", "iphone");
	}
	
	/**
	 * 广告的状态类型
	 */
	public static Map<String, String> ADStatusMap = new HashMap<String, String>();
	static
	{
		ADStatusMap.put("1", "尚未开始");
		ADStatusMap.put("2", "正在进行");
		ADStatusMap.put("3", "已经结束");
		ADStatusMap.put("4", "全部");
	}
	
	/**
	 * IPAD广告的展示效果
	 */
	public static Map<String, String> IPAD_AD_STYLE_MAP = new HashMap<String, String>();
	static
	{
		IPAD_AD_STYLE_MAP.put("scrollad", "滚动样式");
		IPAD_AD_STYLE_MAP.put("singlead", "单个样式");
		IPAD_AD_STYLE_MAP.put("shutterad", "百叶窗样式");
	}
	
	/**
	 * 推荐的状态类型
	 */
	public static Map<String, String> RecomStatusMap = new HashMap<String, String>();
	static
	{
		RecomStatusMap.put("1", "尚未开始");
		RecomStatusMap.put("2", "正在进行");
		RecomStatusMap.put("3", "已经结束");
		RecomStatusMap.put("4", "全部");
	}
	
	/**
	 * 搜索关键字的状态类型
	 */
	public static Map<String, String> SearchKeywordStatusMap = new HashMap<String, String>();
	static
	{
		SearchKeywordStatusMap.put("0", "正常");
		SearchKeywordStatusMap.put("1", "隐藏");
	}
	
	/**
	 * 消息推送的平台类型
	 */
	public static Map<String, String> MessagePlatformMap = new HashMap<String, String>();
	static
	{
		MessagePlatformMap.put("android", "android");
		MessagePlatformMap.put("iphone", "iphone");
		MessagePlatformMap.put("ipad", "ipad");
	}
	
	/**
	 * 消息推送的版本类型
	 */
	public static Map<String, String> MessageVersionMap = new HashMap<String, String>();
	static
	{
		MessageVersionMap.put("all", "all");
	}
	
	/**
	 * 消息推送的版本类型
	 */
	public static Map<String, String> MessageModeMap = new HashMap<String, String>();
	static
	{
		MessageModeMap.put("0", "只发送一次");
		MessageModeMap.put("1", "发送多次");
	}
	
	/**
	 * 消息推送的通知方式类型
	 */
	public static Map<String, String> MessageCodeMap = new HashMap<String, String>();
	static
	{
		MessageCodeMap.put("0", "直接通知");
		MessageCodeMap.put("1", "弹出对话框");
		MessageCodeMap.put("2", "强制推送");
	}
	
	/**
	 * 消息推送的发送范围
	 */
	public static Map<String, String> MessageRangeMap = new HashMap<String, String>();
	static
	{
		MessageRangeMap.put("0", "发送给所有");
		MessageRangeMap.put("1", "按条件发送");
	}
	
	/**
	 * 后台用户的类型
	 */
	public static Map<String, String> AdminUserGroupMap = new HashMap<String, String>();
	static
	{
		AdminUserGroupMap.put("1", "管理员");
		AdminUserGroupMap.put("2", "普通用户");
	}
	
	/**
	 * 操作日志的操作类型
	 */
	public static Map<String, String> OptTypeMap = new HashMap<String, String>();
	static
	{
		OptTypeMap.put("all", "全部");
		OptTypeMap.put("1", "新增");
		OptTypeMap.put("2", "删除");
		OptTypeMap.put("3", "修改");
		OptTypeMap.put("4", "其他");
	}
	
	public static Map<String, String> ButtonTypeMap = new HashMap<String, String>();
	
	static{
		ButtonTypeMap.put("0", "左不再显示，右不再显示");
		ButtonTypeMap.put("1", "左显示，右不再显示");
		ButtonTypeMap.put("2", "左显示，右显示");
		ButtonTypeMap.put("3", "左不再显示，右显示");
	}
	
	public static Map<String, String> ButtonCloseTypeMap = new HashMap<String, String>();
	
	static{
		ButtonCloseTypeMap.put("0", "可以关闭通知");
		ButtonCloseTypeMap.put("1", "不能关闭通知");
	}
	
}
