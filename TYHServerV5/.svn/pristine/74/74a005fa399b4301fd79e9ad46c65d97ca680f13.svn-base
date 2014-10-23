package cn.youhui.util.oufeicz;

import java.io.StringReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import cn.youhui.utils.MD5;
import cn.youhui.utils.NetManager;
import cn.youhui.utils.StringUtils;

public class TelephoneCZValidationUtil {
		
	private static final String OF_USER_ID = "A967547";
	private static final String OF_USER_PWS = "Liu.7658032";
	
	public static boolean telephoneCZValidation(String phoneNum,String price){
		
		boolean flag = false;
		if (!TelephoneCZValidationUtil.check(phoneNum)) {
			return flag;
		}
		String apiUrl = "http://api2.ofpay.com/telcheck.do";
		String paramStr = "phoneno=" + phoneNum + "&price=" + price + "&userid=" + OF_USER_ID;
		String out = NetManager.getInstance().sendGB2312(apiUrl,paramStr);
		System.out.println(out);
		if("1".equals(out.substring(0, 1))){
			flag = true;
		}else {
			flag = false;
		}
		return flag;
	}
	
	public static boolean check(String phoneNum)
	 {
	  String phone = "^\\d{11}$";
	  Pattern p = Pattern.compile(phone);
	  Matcher m = p.matcher(phoneNum);
	  return m.matches();
	 }
	
	/**
	 * 是否有库存
	 * @param phoneNum
	 * @param price
	 * @return
	 */
	public static boolean hasGoods(String phoneNum, int price){
		String ret = CZGoodsQuery(phoneNum, "" + price);
		boolean flag = false;
		if ("1".equals(ret)) {
			flag = true;
		}
		return flag;
	}
	
	public static String CZGoodsQuery(String phoneNum, String price){
		
		String retCode = "";
		String apiUrl = "http://api2.ofpay.com/telquery.do";
		String paramStr = "userid=" + OF_USER_ID + "&userpws="+MD5.digest(OF_USER_PWS).toLowerCase()+"&phoneno="
					+phoneNum+"&pervalue="+price+"&version=6.0";
		try {
			String out = NetManager.getInstance().sendGB2312(apiUrl,paramStr);
			retCode = getXMLtoBean(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retCode;
	}
	
	private static String getXMLtoBean111(String out){
		String retCode = "";
		if(out != null){
			
		}
		return retCode;
	}
	
	@SuppressWarnings("unchecked")
	private static   String getXMLtoBean(String out) throws Exception{
		String retCode = "";
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
			if ("retcode".equals(name)) {
				retCode = value;
			}
		}
		return retCode;
	}
	
	/**
	 * 获取帐户余额
	 * @return
	 */
	public static double countMoneyQuery(){
		double yue = 0;
		String apiUrl = "http://api2.ofpay.com/queryuserinfo.do";
		String paramStr = "userid=" + OF_USER_ID + "&userpws="+MD5.digest(OF_USER_PWS).toLowerCase() +"&version=6.0";
		try {
			String out = NetManager.getInstance().sendGB2312(apiUrl,paramStr);
			String retCode = getXMLtoBean1(out);
			if(!StringUtils.isEmpty(retCode)){
				yue = Double.parseDouble(retCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return yue;
	}
	
	
	@SuppressWarnings("unchecked")
	private static   String getXMLtoBean1(String out) throws Exception{
		String money = "";
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
		boolean flag = false;
		for (int i = 0; i < children.size(); i++) {
			et = children.get(i);
			String name =et.getName();
			String value =et.getText();
			if ("retcode".equals(name)) {
				if ("1".equals(value)) {
					flag = true;
					break;
				}
			}
		}
		if(flag){
			for (int i = 0; i < children.size(); i++) {
				et = children.get(i);
				String name =et.getName();
				String value =et.getText();
				if ("ret_leftcredit".equals(name)) {
					money = value;
				}
			}
		}
		return money;
	}
	
	
	public static void main(String [] args){
//		System.out.print(TelephoneCZValidationUtil.telephoneCZValidation("15850673417", "10"));
		System.out.print(TelephoneCZValidationUtil.CZGoodsQuery("13288425472", "10"));
	}
}
