package cn.suishou.some.util;

import java.net.URLEncoder;

/**
 * 分享工具类
 * @author lijun
 * @since 2014-5-16
 */
public class ShareUtil {

	/**
	 * 只用于活动分享，（还有一种 share_type:product_type）
	 * @param title
	 * @param content
	 * @param version
	 * @return
	 */
	public static String getShareUrl(String title, String shareStr, String version){
		String cUrl = "";
		try {
			if(VersionUtil.isHigher("4.0.0", version)){
				cUrl = "suishou://app.youhui.cn/YouHuiSharePage?title="+ URLEncoder.encode(title, "UTF-8")+ "&share_type=activity_type&share_encryption_code=" +URLEncoder.encode(Base64.encode(shareStr.getBytes("UTF-8")), "UTF-8");
			}else{
				cUrl = "suishou://youhui.cn?action_title="+ URLEncoder.encode(title, "UTF-8")+"&action_value=" + URLEncoder.encode(Base64.encode(shareStr.getBytes("UTF-8")), "UTF-8")+"&action_type=youhuishare&jump_from=suishou";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cUrl;
	}
	
	public static void main(String[] args) {
		String string = "{\"isshare\":true,\"title\":\"超级惠,惠到让你停不下来!\",\"content\":\"跌破眼镜的优惠好货正一波波的袭来，还不赶紧来抢~~每周一至周五10点准时开抢！\",\"clickurl\":\"http://youhui.cn\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/16qlwftw1q5.jpg\",\"activity_key\":\"6r9n8svz\",\"jifen_num\":\"0\",\"channel\":\"weixin,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
		String ret = getShareUrl("超级惠,惠到让你停不下来", string, "4.0.1");
		System.out.println(ret);
	}
}
