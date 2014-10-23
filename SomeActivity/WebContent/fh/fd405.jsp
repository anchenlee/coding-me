<%@page import="cn.suishou.some.util.ShareUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.net.URLEncoder"%>
<%@ page import="cn.suishou.some.util.Base64"%>
<%@ page import="cn.suishou.some.dao.UserDAO"%>
<%@ page import="cn.suishou.some.fenhong.Fenhong" %>
<%@ page import="cn.suishou.some.fenhong.FenhongDAO" %>

<%
    String uid = request.getParameter("tyh_web_uid");
    String taobaoNick = request.getParameter("tyh_web_taobaonick");
    String version = request.getParameter("tyh_web_version");
    String uid64 = "";
    if(taobaoNick != null && !"".equals(taobaoNick)){
    	taobaoNick = new String(taobaoNick.getBytes("iso-8859-1"), "utf-8");
    }
    if(uid != null && !"".equals(uid)){
    	uid64 = Base64.encode(uid.getBytes()).replaceAll("=", "");
    }else{
    	String us = request.getParameter("u");
    	if(us != null && !"".equals(us)){
    		uid = new String(Base64.decode(us + "=="));
    		taobaoNick = UserDAO.getInstance().getTBNick(uid);
    	}
    }
    
   // String taobaoNick = UserDAO.getInstance().getTBNick(uid);
    
    String url = "http://d.b17.cn/sactivity/fh/fd405.jsp?u=" + uid64;
    
    String shareStr = "{\"isshare\":true,\"title\":\"5月集分宝红包发放啦！\",\"content\":\"随手君发放5月购物额外奖励红包啦！用的越久，赚的越多！点我省钱——淘宝购物就用【随手优惠】\",\"clickurl\":\""+ url +"\",\"imgurl\":\"http://static.etouch.cn/suishou/ad_img/kj3acla8xx.jpg\",\"activity_key\":\"08btqisk\",\"channel\":\"weixin,weibo,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
    
   // String newShareStr = Base64.encode(shareStr.getBytes("UTF-8"));
    
  //  String cUrl = "suishou://youhui.cn?action_title="+ URLEncoder.encode("4月集分宝红包发放啦", "UTF-8")+"&action_value=" + URLEncoder.encode(newShareStr, "UTF-8")+"&action_type=youhuishare&jump_from=suishou";
    
  String cUrl = ShareUtil.getShareUrl("5月集分宝红包发放啦", shareStr, version);
  Fenhong fh = FenhongDAO.getInstance().getFenhong(uid, "2014", "05");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1//DTD//xhtml1-transitional.dtd">
<html>
	<title>送集分宝红包啦</title>
	<head>
	<link rel="stylesheet" type="text/css" href="redbag0.css" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
</head>

<body>
<div id="content">
<img src="images/bg1_copy.png" id="bg_pic" alt="" />
		<div id="bg">
			<%if(fh != null && fh.getFhJfbNum() > 0){%>
			<%if(taobaoNick == null || "".equals(taobaoNick)){%>
			<div class="title"><span class="yellow">亲</span></div>
			<%}else{%>
			<div class="title"><span class="yellow">亲爱的</span></div>
			<div class="title"><span class="yellow"><%=taobaoNick%></span></div>
			<%}%>
			<div class="title"><span class="yellow">恭喜你</span></div>
			<div class="title"><span class="yellow">本月获得<span class="white"><%=fh.getFhJfbNum()%></span>个</span></div>
			<div class="title"><span class="yellow">集分宝红包</span></div>
			<% }else{%>
			<div class="title"><span class="yellow">亲</span></div>
			<div class="title"><span class="yellow">您本月暂无分红</span></div>
			<div class="title"><span class="yellow">请下月再接再厉</span></div>
			<%}%>
		</div>
		<div id="share">
		<%if(fh.getFhJfbNum() > 0){%>
		<a href="<%=cUrl%>"><div class="share">分享</div></a>
		<% }else{%>
		<a href="http://v2.api.njnetting.cn/fenhong/fenhong_desc.html"><div class="share">分红说明</div></a>
		<%}%>
		</div>
		<div id="bill">
			<div class="bill">
			    <table class="tb">
					<tr>
					<td style="width:32%">当月购物奖励<br/><span class="orange"><%=fh.getJfbNum()%></span><span class="jfb">(集分宝)</span></td>
					<td style="width:32%">
						本月分红比例<br/><span class="orange"><%=fh.getFhRate()%>%</span>
					</td>
					<td style="width:32%">
					<%if(fh.getIcon() != null && !"".equals(fh.getIcon())){%>
					<span class="logo">
						<img src="images/<%=fh.getIcon()%>"/>
					</span>
					<%}%>
					加送<br/>
					<span class="orange"><%=fh.getLevelRate()%>%</span>
</td>
</tr>
</table>

				<div class="div3">本月分红总额：</div>
				<div id="div4"><div class="div4"><%=fh.getJfbNum()%>x(<%=fh.getFhRate()%>%+<%=fh.getLevelRate()%>%)=<span class="red"><%=fh.getFhJfbNum()%></span><span class="jfb">(集分宝)</span></div></div>
			</div>
		</div>
</div>
<script type="text/javascript">
	function control_position(){
		var view_width = document.documentElement.clientWidth || document.body.clientWidth; 
		var oImg = document.getElementById("bg");
		var oImg_2 = document.getElementById("share");

		oImg.style.left=35/640*view_width+"px";
		oImg_top=105/640*view_width;
		if (oImg_top > 146) {//控制top的大小
			oImg.style.top=146+"px";
		}else{
			oImg.style.top=oImg_top+"px";
		};

		var fontChangeSize = view_width/18;
		if (fontChangeSize>59) {//控制字体的尺寸
			oImg.style.fontSize=59 +"px";
			oImg_2.style.fontSize=59 +"px";
		}else{
			oImg.style.fontSize=fontChangeSize+"px";
			oImg_2.style.fontSize=fontChangeSize+"px";
		};

		oImg_2.style.left=35/640*view_width+ "px";
	
		oImg_2_top=430/640*view_width;
		if (oImg_2_top > 570) {//控制top的大小
			oImg_2.style.top=570+"px";
		}else{
			oImg_2.style.top=oImg_2_top+"px";
		};
	}
	control_position();
	window.onresize = function (){control_position();}
</script>
</body>

</html>