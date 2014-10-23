package cn.youhui.utils;

import cn.youhui.common.Enums;
import cn.youhui.common.Enums.ActionStatus;
import cn.youhui.ramdata.TagItemCacher;


public class RespStatusBuilder {
	
	public static StringBuffer message(int code, String msg) {
		return new StringBuffer()
				.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
				.append("<resp>")
				.append("<head>")
				.append("<status>").append(code).append("</status>")
				.append("<desc><![CDATA[").append(msg).append("]]></desc>")
				.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
				.append("</head>")
				.append("</resp>");
	}
	
	public static StringBuffer message(String code, String msg) {
		return new StringBuffer()
				.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
				.append("<resp>")
				.append("<head>")
				.append("<status>").append(code).append("</status>")
				.append("<desc><![CDATA[").append(msg).append("]]></desc>")
				.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
				.append("</head>")
				.append("</resp>");
	}
	
	public static StringBuffer messageAccess(int code, String msg,String access_token) {
		return new StringBuffer()
				.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
				.append("<resp>")
				.append("<head>")
				.append("<status>").append(code).append("</status>")
				.append("<desc><![CDATA[").append(msg).append("]]></desc>")
				.append("<acctoken>").append(access_token).append("</acctoken>")
				.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
				.append("</head>")
				.append("</resp>");
	}
	
	public static StringBuffer message(ActionStatus status,String accToken, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<acctoken>"+accToken+"</acctoken>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer message(ActionStatus status, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	public static StringBuffer messageNew(ActionStatus status, String data,String jfbSwitch){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("</head>")
		.append("<data>")
		.append("<jfb_switch>")
		.append(jfbSwitch)
		.append("</jfb_switch>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	
	
	public static StringBuffer messageGift(ActionStatus status,String accToken, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<acctoken>"+accToken+"</acctoken>")
		.append("<isconverted>1</isconverted>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer messageGiftError(ActionStatus status, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<isconverted>1</isconverted>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer messageJson(ActionStatus status,String accToken, String data){
		return new StringBuffer()
		.append("{\"resp\":{")
		.append("\"head\":{")
		.append("\"status\":\"").append(status.inValue()).append("\",")
		.append("\"desc\":\"").append(status.getDescription()).append("\",")
		.append("\"isconverted\":\"").append(TagItemCacher.getInstance().getIsconverted()).append("\",")
		.append("\"acctoken\":\"").append(accToken).append("\"")
		.append("},")
		.append("\"data\":")
		.append(data)
		.append("}}");
	}
	
	public static StringBuffer messageJson(ActionStatus status,String accToken){
		return new StringBuffer()
		.append("{\"resp\":{")
		.append("\"head\":{")
		.append("\"status\":\"").append(status.inValue()).append("\",")
		.append("\"desc\":\"").append(status.getDescription()).append("\",")
		.append("\"isconverted\":\"").append(TagItemCacher.getInstance().getIsconverted()).append("\",")
		.append("\"acctoken\":\"").append(accToken).append("\"")
		.append("}}}");
	}
	
	public static StringBuffer messageJson(ActionStatus status){
		return new StringBuffer()
		.append("{\"resp\":{")
		.append("\"head\":{")
		.append("\"status\":\"").append(status.inValue()).append("\",")
		.append("\"desc\":\"").append(status.getDescription()).append("\",")
		.append("\"isconverted\":\"").append(TagItemCacher.getInstance().getIsconverted()).append("\"")
		.append("}}}");
	}
	
	public static StringBuffer messageAccess(int code, String msg,String access_token,String data) {
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(code).append("</status>")
		.append("<desc><![CDATA[").append(msg).append("]]></desc>")
		.append("<acctoken>").append(access_token).append("</acctoken>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer message(ActionStatus status,String accToken,int pageCount, int pageIndex, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<acctoken>"+accToken+"</acctoken>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("<page>")
		.append(pageIndex)
		.append("</page>")
		.append("<total>")
		.append(pageCount)
		.append("</total>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer message(ActionStatus status,String accToken,int pageCount, int pageIndex, String data,String jfbSwitch){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<acctoken>"+accToken+"</acctoken>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("<page>")
		.append(pageIndex)
		.append("</page>")
		.append("<total>")
		.append(pageCount)
		.append("</total>")
		.append("</head>")
		.append("<data>")
		.append("<jfb_switch>")
		.append(jfbSwitch)
		.append("</jfb_switch>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer messageJson(ActionStatus status,String accToken,int pageCount, int pageIndex, String data){
		
		 return new StringBuffer()
		.append("{\"resp\":{")
		.append("\"head\":{")
		.append("\"status\":\"").append(status.inValue()).append("\",")
		.append("\"desc\":\"").append(status.getDescription()).append("\",")
		.append("\"isconverted\":\"").append(TagItemCacher.getInstance().getIsconverted()).append("\",")
		.append("\"page\":\"").append(pageIndex).append("\",")
		.append("\"total\":\"").append(pageCount).append("\",")
		.append("\"acctoken\":\"").append(accToken).append("\"")
		.append("},")
		.append("\"data\":")
		.append(data)
		.append("}}");
	}
	
	public static StringBuffer message(ActionStatus status,int pageCount, int pageIndex, String data, float fanli){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<page>")
		.append(pageIndex)
		.append("</page>")
		.append("<total>")
		.append(pageCount)
		.append("</total>")
		.append("</head>")
		.append("<data>")
		.append("<je>").append(fanli).append("</je>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer message(ActionStatus status,int pageCount, int pageIndex, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<page>")
		.append(pageIndex)
		.append("</page>")
		.append("<total>")
		.append(pageCount)
		.append("</total>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	public static StringBuffer message4GuessYouLike(ActionStatus status,int pageCount, int pageIndex, String data,String layout_type,String jfbSwitch){
		String zk="";//是否显示折扣 是网格布局就显示折扣否则显示jfb比例
		if(layout_type.equals("grid")){
			zk="zk";
		}
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(status.inValue()).append("</status>")
		.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
		.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
		.append("<page>")
		.append(pageIndex)
		.append("</page>")
		.append("<total>")
		.append(pageCount)
		.append("</total>")
		.append("<layout_type>")
		.append(layout_type)
		.append("</layout_type>")
		
		.append("</head>")
		.append("<data>")
		.append("<jfb_switch>")
		.append(jfbSwitch)
		.append("</jfb_switch>")
		.append("<show>")
		.append(zk)
		.append("</show>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer message(int code , String msg , int pageCount , int pageIndex, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(code).append("</status>")
		.append("<desc><![CDATA[").append(msg).append("]]></desc>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("<page>")
		.append(pageIndex)
		.append("</page>")
		.append("<total>")
		.append(pageCount)
		.append("</total>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer message(int code , String name , int pageCount , String pageIndex,int totalitem, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<status>").append(code).append("</status>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("<page current=\"" + pageIndex + "\" total=\"" + pageCount+ "\" totalitem=\"" + totalitem + "\"></page>")
		.append("<"+name+">")
		.append(data)
		.append("</"+name+">")
		.append("</resp>");
	}
	
	
	public static StringBuffer messageAccess(int code , String msg ,String access_token, int pageCount , int pageIndex, String data){
		return new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(code).append("</status>")
		.append("<desc><![CDATA[").append(msg).append("]]></desc>")
		.append("<acctoken>").append(access_token).append("</acctoken>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("<page>")
		.append(pageIndex)
		.append("</page>")
		.append("<total>")
		.append(pageCount)
		.append("</total>")
		.append("</head>")
		.append("<data>")
		.append(data)
		.append("</data>")
		.append("</resp>");
	}
	
	public static StringBuffer message(Enums.ActionStatus status) {
		return new StringBuffer()
				.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
				.append("<resp>")
				.append("<head>")
				.append("<status>").append(status.inValue()).append("</status>")
				.append("<desc><![CDATA[").append(status.getDescription()).append("]]></desc>")
				.append("<show_desc><![CDATA[").append(status.getShowDesc()).append("]]></show_desc>")
				.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
				.append("</head>")
				.append("</resp>");
	}
	
	public static StringBuffer message11(int code , String msg , String data , boolean doEncode){
		StringBuffer respBuffer = new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(code).append("</status>")
		.append("<desc><![CDATA[").append(msg).append("]]></desc>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("</head>")
		.append("<data>") ;

		respBuffer.append(data) ;
		
		respBuffer.append("</data>")
		.append("</resp>");
		
		return respBuffer ;
	}
	
	public static StringBuffer message(int code , String msg , String data , String version, boolean doEncode){
		StringBuffer respBuffer = new StringBuffer()
		.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>")
		.append("<resp>")
		.append("<head>")
		.append("<status>").append(code).append("</status>")
		.append("<desc><![CDATA[").append(msg).append("]]></desc>")
		.append("<isconverted>").append(TagItemCacher.getInstance().getIsconverted()).append("</isconverted>")
		.append("</head>")
		.append("<data version=\""+version+"\">") ;

		respBuffer.append(data) ;
		
		respBuffer.append("</data>")
		.append("</resp>");
		
		return respBuffer ;
	}
}
