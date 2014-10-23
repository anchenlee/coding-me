<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@ page import="cn.suishou.some.util.Base64" %>
<%@ page import="cn.suishou.some.fenhong.Fenhong" %>
<%@ page import="cn.suishou.some.dao.UserDAO"%>
<%@ page import="cn.suishou.some.fenhong.FenhongDAO" %>

<%
    String uid = request.getParameter("tyh_web_uid");
    String uid64 = "";
    if(uid != null && !"".equals(uid)){
    	uid64 = Base64.encode(uid.getBytes()).replaceAll("=", "");
    }else{
    	String us = request.getParameter("u");
    	if(us != null && !"".equals(us)){
    		uid = new String(Base64.decode(us + "=="));
    	}
    }
    
    String taobaoNick = UserDAO.getInstance().getTBNick(uid);
    
    String url = "http://d.b17.cn/sactivity/fh/f402.jsp?u=" + uid64;
    String shareStr = "{\"isshare\":true,\"title\":\"2月分红帐单生成喽！！\",\"content\":\"随手君为您送上热腾腾的2月分红帐单,将于16日到帐!等级越高分红越多！点我省钱——淘宝购物就用【随手优惠】\",\"clickurl\":\""+ url +"\",\"imgurl\":\"http://bcs.duapp.com/taoyouhui-ad/adImg/4zpxmh4uuaa.jpg\",\"activity_key\":\"1da7gktm\",\"channel\":\"weixin,weibo,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
    
    String newShareStr = Base64.encode(shareStr.getBytes("UTF-8"));
    
    String cUrl = "suishou://youhui.cn?action_title="+ URLEncoder.encode("2月分红帐单生成喽", "UTF-8")+"&action_value=" + URLEncoder.encode(newShareStr, "UTF-8")+"&action_type=youhuishare&jump_from=suishou";
    
    Fenhong fh = FenhongDAO.getInstance().getFenhong(uid, "2014", "02");
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<title>分红帐单生成喽</title>
	<head>
	<link rel="stylesheet" type="text/css" href="redbag.css" />
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
</head>

<body>
<div id="content">
		<img src="images/bg2_copy.png" id="bg_pic" alt="" />
		<div id="bg">
		<%if(fh != null && fh.getFhJfbNum() > 0){%>
			<div class="title">亲爱的:</div>
			<div class="title"><span class="brown"><%=taobaoNick%></span></div>
			<div class="title">2月分红已生成</div>
			<div class="title"><%=fh.getFhJfbNum()%>个集分宝红包</div>
			<div class="title"><span class="brown">将于</span>本月16日<span class="brown">到账</span></div>
			<% }else{%>
			<div class="title">亲，您本月暂无分红，请下月再接再厉</div>
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
					<td colspan="1">当月购物奖励<br /><span class="yellow"><%=fh.getJfbNum()%></span><span class="jfb">(集分宝)</span></td>
					<td>
					本月分红比例<br /><span class="yellow"><%=fh.getFhRate()%>%</span>
					</td>
					<td>
					<%if(fh.getIcon() != null && !"".equals(fh.getIcon())){%>
					<span class="logo">
						<img src="images/<%=fh.getIcon()%>"/>
					</span>
					<%}%>
					加送<br />
					<span class="yellow"><%=fh.getLevelRate()%>%</span>
					</td>
					</tr>
					<tr>
					<td colspan="3"><div class="div3">本月分红总额：</div>
						<div id="div4"><div class="div4"><%=fh.getJfbNum()%>x(<%=fh.getFhRate()%>%+<%=fh.getLevelRate()%>%)=<span class="orange"><%=fh.getFhJfbNum()%></span><span class="jfb">(集分宝)</span></div></div></td>	
					</tr>
					</table>
		
			</div>
		</div>
</div>
</body>
<script type="text/javascript">
	function control_position(){
		var view_width = document.documentElement.clientWidth || document.body.clientWidth; 
		var oImg = document.getElementById("bg");
		oImg.style.left=35/640*view_width+"px";

		oImg_top=75/640*view_width;
		if (oImg_top > 116) {//控制top的大小
			oImg.style.top=116+"px";
		}else{
			oImg.style.top=oImg_top+"px";
		};

		var fontChangeSize = view_width/18;
		if (fontChangeSize>59) {//控制字体的尺寸
			oImg.style.fontSize=59 +"px";
		}else{
			oImg.style.fontSize=fontChangeSize+"px";
		};
	}
	control_position();
	window.onresize = function (){control_position();}
</script>
</html>