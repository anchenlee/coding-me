<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<title>随手优惠</title>
<link rel="stylesheet" type="css" href="css/style.css">
<style>
a {
	TEXT-DECORATION: none
}
</style>
</head>
<body screen_capture_injected="true">
	<%
String version = request.getParameter("version");
if(version==null||"".equals(version))
 version = "V1.0.0";
%>
	<div id="m_title">关于</div>
	<div id="m_products_details">
		<img border="0" alt="logo" src="css/images/m_logo.png" width="169"
			height="37" style="border: 0;">


		<p>
		           随手优惠(<font color="red">淘优惠</font>正式更名为<font color="red">随手优惠</font>)—淘宝省钱伴侣! 淘宝无线官方推荐应用，安全购物优惠神器，汇集万千淘宝优惠信息，智能精准搜索，购物奖励集分宝！
		</p>
	</div>
	<div id="m_title">官方网站</div>
	<div id="m_products_details">
		<p>
			<a href="http://www.suishou.cn/" style="color: #0170b7">http://www.suishou.cn</a>
		</p>
	</div>


	<div id="m_title">联系我们</div>
	<div id="m_products_details">
		<p>
			问题反馈(企业QQ)：<a style="color: #0170b7">800060256</a>
		</p>
		
		<p>
			商户报名(QQ群)：<a style="color: #0170b7">215459060</a>
		</p>
		<p>
			淘帮派：<a style="color: #0170b7">随手优惠</a>
		</p>
		<p>
			新浪微博：<a style="color: #0170b7" href="http://e.weibo.com/339393691">http://e.weibo.com/339393691</a>
		</p>
		<p>
			腾讯微博：<a style="color: #0170b7"
				href="http://t.qq.com/taobaoyouhuifanli">http://t.qq.com/taobaoyouhuifanli</a>
		</p>
		<p>
		            联系邮箱：<a  style="color:#0170b7" href="mailto:support@taoyouhui.mobi">support@suishou.cn</a>
		</p>
		<p>
			公司地址：<a style="color: #0170b7">江苏省南京市雨花台区花神大道17号华博智慧园6楼</a>
		</p>
	</div>


	<div id="m_title">版本</div>
	<div id="m_products_details">
		<img border="0" alt="logo" src="css/images/logo.png"
			width="64" height="64" style="border: 0;">
		<div id="version"><%=version %></div>
	</div>

	<div id="footer">
		Copyright © 2010 NJNETTING. All Rights Reserved. <br>
		<a href="#">宁网科技</a>版权所有
	</div>
</body>
</html>
