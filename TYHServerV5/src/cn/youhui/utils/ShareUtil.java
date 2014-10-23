package cn.youhui.utils;

import java.net.URLEncoder;

import cn.youhui.common.ParamConfig;

/**
 * 分享工具类
 * @author lijun
 * @since 2014-5-16
 */
public class ShareUtil {

	/**
	 * 只用于活动分享，（还有一种 share_type）
	 * @param title
	 * @param content
	 * @param version
	 * @return
	 */
	public static String getShareUrl(String platform,String title, String shareStr, String version){
		String cUrl = "";
		try {
			if ("ipad".equals(platform) || "apad".equals(platform)) {
				if(VersionUtil.isHigher("4.0.0", version)){
					cUrl = "suishouhd://app.youhui.cn/YouHuiSharePageHD?title="+ URLEncoder.encode(title, "UTF-8")+ "&share_type=activity_type&share_encryption_code=" +URLEncoder.encode(Base64.encode(shareStr.getBytes("UTF-8")), "UTF-8");
				}else{
					cUrl = "suishouhd://youhui.cn?action_title="+ URLEncoder.encode(title, "UTF-8")+"&action_value=" + URLEncoder.encode(Base64.encode(shareStr.getBytes("UTF-8")), "UTF-8")+"&action_type=youhuishare&jump_from=suishou";
				}
			}else {
				if(VersionUtil.isHigher("4.0.0", version)){
					cUrl = "suishou://app.youhui.cn/YouHuiSharePage?title="+ URLEncoder.encode(title, "UTF-8")+ "&share_type=activity_type&share_encryption_code=" +URLEncoder.encode(Base64.encode(shareStr.getBytes("UTF-8")), "UTF-8");
				}else{
					cUrl = "suishou://youhui.cn?action_title="+ URLEncoder.encode(title, "UTF-8")+"&action_value=" + URLEncoder.encode(Base64.encode(shareStr.getBytes("UTF-8")), "UTF-8")+"&action_type=youhuishare&jump_from=suishou";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cUrl;
	}
	
	/**
	 * 用于商品的分享
	 * @param title
	 * @param content
	 * @param imgUrl
	 * @param clickUrl
	 * @param itemId
	 * @return
	 */
//	public static String getItemShareUrl(String title, String content, String imgUrl, String clickUrl){
//		String cUrl = "";
//		try {
//			cUrl = "suishou://app.youhui.cn/YouHuiSharePage?title="+ URLEncoder.encode(title, "UTF-8")+ "&share_type=product_type&content="+ URLEncoder.encode(content, "UTF-8")+ "&img_url="+ URLEncoder.encode(imgUrl, "UTF-8") + "&click_url="+ URLEncoder.encode(clickUrl, "UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cUrl;
//	}
	
	public static String shareItem(String title,String desc,String clickUrl,String img,String platform){
		String string = "{\"isshare\":true,\"title\":\""+title+"\",\"content\":\""+desc+"\",\"clickurl\":\""+clickUrl+"\",\"imgurl\":\""+img+"\",\"activity_key\":\"9ouea44m\",\"jifen_num\":\"0\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm,weibo\"}";
		String ret = getShareUrl(platform,"超级惠,惠到让你停不下来", string, "4.0.1");
		return ret;
	}
	
	public static void main(String[] args) {
//		String string = "{\"isshare\":true,\"title\":\"超级惠,惠到让你停不下来!\",\"content\":\"跌破眼镜的优惠好货正一波波的袭来，还不赶紧来抢~~每周一至周五10点准时开抢！\",\"clickurl\":\""+ParamConfig.copy_of_index+"\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/2gkz0mkncb8.jpg\",\"activity_key\":\"6r9n8svz\",\"jifen_num\":\"0\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm,weibo\"}";
//		String ret = getShareUrl("超级惠,惠到让你停不下来", string, "4.0.1");
		
		String string = "{\"isshare\":true,\"title\":\"免邮试吃疯狂来袭\",\"content\":\"疯抢“试吃优惠券”获得试吃资格，限量100份，先抢先得，赶紧来抢吧\",\"clickurl\":\"http://youhui.cn\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/2vt4dib51r8.jpg\",\"activity_key\":\"q1u4pz2j\",\"jifen_num\":\"0\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
		String ret = getShareUrl("超级惠免单第二季", string, "4.0.1","iphone");
		
		System.out.println(ret);
	}
}
