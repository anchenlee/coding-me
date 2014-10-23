package cn.youhui.util.oufeicz;

import java.io.StringReader;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
import cn.youhui.common.TPool;
import cn.youhui.utils.MD5;
import cn.youhui.utils.NetManager;

/**
 * 欧飞   各充值接口
 * @author lijun
 * @since 2014-7-23
 */
public class OfPayUtil {
	private static final String OF_USER_ID = "A967547";
	private static final String OF_USER_PWS = "Liu.7658032";
	private static final String KC_CARDID = "140101";   //快充 商品编码
	private static final String SIGN_KEY = "OFCARD";

	public static boolean huafeiCZ(String phoneNum, int num){
		return huafeiCZ(phoneNum, num, "", "");
	}
	
	/**
	 * 话费充值
	 * @param phoneNum
	 * @param num
	 * @param acId    标识哪个活动
	 * @param acUniqueId  和外部活动关联的id
	 * @return
	 */
	public static boolean huafeiCZ(String phoneNum, int num, String acId, String acUniqueId){
		OufeiCZ order = new OufeiCZ();
		order.setNum(num);
		order.setPhoneNum(phoneNum);
		order.setAcId(acId);
		order.setAcUniqueId(acUniqueId);
		boolean flag = OufeiCZDAO.getInstance().add(order); 
		if (flag) {
			String str = OF_USER_ID+MD5.digest(OF_USER_PWS).toLowerCase()+KC_CARDID+String.valueOf(num)+ order.getOrdreId() +order.getTimeStr()+String.valueOf(phoneNum)+SIGN_KEY;
			str=MD5.digest(str).toUpperCase();
			String apiUrl = "http://api2.ofpay.com/onlineorder.do";
			String paramStr = "userid=" + OF_USER_ID + "&userpws="+MD5.digest(OF_USER_PWS).toLowerCase()+"&cardid="
						+KC_CARDID+"&cardnum="+String.valueOf(num)+"&sporder_id="+ order.getOrdreId()+"&sporder_time="
						+order.getTimeStr()+"&game_userid="+ phoneNum + "&md5_str=" + str
						+"&ret_url=&version=6.0";
			try {
				String out = NetManager.getInstance().sendGB2312(apiUrl,paramStr);
				order = getXMLtoBean(order, out);
				OufeiCZDAO.getInstance().updateStatus(order);
				if (order.getCpStatus() == 0 ) {
					TPool.getInstance().doit(new QueryThread(order,order.getOrdreId()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public static void huafeiCZRetry(OufeiCZ order){
			String str = OF_USER_ID+MD5.digest(OF_USER_PWS).toLowerCase()+KC_CARDID+String.valueOf(order.getNum())
					+ order.getOrdreId() +order.getTimeStr()+String.valueOf(order.getPhoneNum())+SIGN_KEY;
			str=MD5.digest(str).toUpperCase();
			String apiUrl = "http://api2.ofpay.com/onlineorder.do";
			String paramStr = "userid=" + OF_USER_ID + "&userpws="+MD5.digest(OF_USER_PWS).toLowerCase()+"&cardid="
						+KC_CARDID+"&cardnum="+String.valueOf(order.getNum())+"&sporder_id="+ order.getOrdreId()+"&sporder_time="
						+order.getTimeStr()+"&game_userid="+ order.getPhoneNum() + "&md5_str=" + str
						+"&ret_url=&version=6.0";
			try {
				String out = NetManager.getInstance().send(apiUrl,paramStr);
				order = getXMLtoBean(order, out);
				OufeiCZDAO.getInstance().updateStatus(order);
				if (order.getCpStatus() == 0 ) {
					TPool.getInstance().doit(new QueryThread(order,order.getOrdreId()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	private static   OufeiCZ getXMLtoBean(OufeiCZ order, String out) throws Exception
	{
		//创建一个新的字符串
		StringReader sr = new StringReader(out);
		//创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource is = new InputSource(sr);
		//创建一个新的SAXBuilder
		SAXBuilder sax = new SAXBuilder();
		//通过输入源构造一个Document
		Document dc = sax.build(is);
		//取的根元素
		Element root = dc.getRootElement();
		//得到根元素所有子元素的集合
		List<Element> children = root.getChildren();
		Element et = null;
		for (int i = 0; i < children.size(); i++) {
			et = children.get(i);
			String name =et.getName();
			String value =et.getText();
			if ("orderid".equals(name)) {
				order.setCpOrderId(value);
			}	 
			if ("ordercash".endsWith(name)) {
				order.setOrderCash(value);
			}
			if ("retcode".equals(name)) {
				order.setCpStatus(Integer.parseInt(value));   //1 成功    9 撤消    0 充值中
			}
		}
		return order;
	}
	
//	public void newTask( final String oderId,final OufeiCZ order){
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				String status = "";
//				int i = 0;
//				for (i = 1; i <= 5; i++) {
//					status = OufeiQuery(oderId);
//					if (i == 5 || "1".equals(status) || "9".equals(status)){
//						break;
//					}
//				}
//				if (i == 5) {
//					status = "9";
//				}
//				OufeiCZDAO.getInstance().updateStatus(order);
//				this.cancel();
//			}
//	       };
//	       Timer timer = new Timer();
//	       timer.schedule(task, Calendar.getInstance ().getTime(), 1000);
//	}
	
	
	public static String OufeiQuery(String oderId)
	{
		String status = "";
		String apiUrl = "http://api2.ofpay.com/api/query.do";
		String paramStr = "userid=" + OF_USER_ID + "&spbillid=" + oderId;
		status = NetManager.getInstance().send(apiUrl,paramStr);
		return status;
	}
	
	public static void main(String[] args) throws Exception {
//		OfPayUtil.huafeiCZ("15850673417", 10); //羊羊羊
//		new OfPayUtil().huafeiCZ("18656600913", 1);
//		new OfPayUtil().huafeiCZ("13770751893", 1);
//		System.out.println(new OfPayUtil().OufeiQuery("20140729781452798503"));
//		new OfPayUtil().huafeiCZ("111111111111", 1);
//		new OfPayUtil().huafeiCZ("222222222222", 1);
		
//		String str = OF_USER_ID+MD5.digest(OF_USER_PWS).toLowerCase()
//				+KC_CARDID+String.valueOf(1)+ "20140813244533950290" +"20140813124141"+"13951702445"+SIGN_KEY;
//		str=MD5.digest(str).toUpperCase();
//		String apiUrl = "http://api2.ofpay.com/onlineorder.do";
//		String paramStr = "userid=" + OF_USER_ID + "&userpws="+MD5.digest(OF_USER_PWS).toLowerCase()+"&cardid="
//					+KC_CARDID+"&cardnum=1&sporder_id=20140813244533950290&sporder_time=20140813124141&game_userid=13951702445&md5_str=" + str
//					+"&ret_url=&version=6.0";
//		String out = NetManager.getInstance().sendGB2312(apiUrl,paramStr);
//		System.out.println(out);
		
//		String oderId="14120832741663143664188";
		String oderId="20141008887693498225";
		System.out.println(OufeiQuery(oderId));
//		String url="http://api2.ofpay.com/reissue.do";
//		String content="userid=" + OF_USER_ID + "&userpws="+MD5.digest(OF_USER_PWS).toLowerCase()+"&version=6.0&spbillid=" + oderId;
//		System.out.println(content);
//		System.out.println(NetManager.getInstance().send(url, content));
//		huafeiCZ("13584435770", 10);
	}
}
