<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="cn.suishou.some.util.Base64" %>
<%@ page import="cn.suishou.some.dao.ShareAppDAO" %>
<%@ page import="java.util.Map" %>

<%
    String uid = request.getParameter("uid");
    int jfb = ShareAppDAO.getInstance().getJFBNum(uid);
    
    Map<String, Integer> drban = ShareAppDAO.getInstance().getFirst(3);
    String uid64 = "";
    if(uid != null && !"".equals(uid)){
    	uid64 = "h" + Base64.encode(uid.getBytes());
    }
    
    String url = "http://youhui.cn/" + uid64;
    String shareStr = "{\"isshare\":true,\"title\":\"分享软件，送集分宝\",\"content\":\"我正在用#随手优惠#淘宝贝，购物还能赚集分宝返利，首次登陆免费领50个集分宝，每日签到能领更多，帮你淘宝省钱无下限，下载链接："+ url +" ——来自史上最高返利的手机应用 \",\"clickurl\":\""+ url +"\",\"imgurl\":\"http://bcs.duapp.com/taoyouhui-ad/ad/1.jpg\",\"activity_key\":\"4302tljb\",\"channel\":\"weixin,weibo,pengyou,txwb,qqkj,renren,duoban,email,sm\"}";
    
    String newShareStr = Base64.encode(shareStr.getBytes("UTF-8"));
    
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>

<style type="text/css">
a{
text-decoration: none;
}
#title{
background: url("./images/yihuode.png") no-repeat;
background-size: 100%;
}

#jifenbao{
padding: 17% 0px 0px 50%;
color:#fff;
font-size:50px;
}

#darenb_w{
margin: 20px 10px 0 10px;
padding: 0px 0px 15px 0px;
background: #fff;
text-align: center;
}

#darenb{
margin: 10px 0px 10px 0px;
padding: 15px 0px 15px 15px;
background: #fff;
text-align: left;
color:#6f0300;
font-size:15px;
font-weight:bold;
border: 1px solid #ccc;
-moz-box-shadow: 1px 1px 5px #cccccc;
-webkit-box-shadow: 1px 1px 5px #cccccc;
box-shadow:1px 1px 5px #cccccc;
}

#detail_s{
margin: 10px 10px 0 10px;
padding: 15px;
background: #fff;
border: 1px solid #ccc;
text-align: center;
}
#detail{
margin: 0px 10px 0 10px;
padding: 0px 15px 15px 15px;
background: #fff;
border: 1px solid #ccc;
border-top:0px;
text-align: left;
display:none;
line-height: 1.5;
}
#fixed{
border:0px;
position:fixed;
left:20px;
bottom:10px;
}
</style>
<script type="text/javascript">

function sorhdetail(){
	var de_display = document.getElementById("detail").style.display;
	if(de_display == '' || de_display == 'none'){
		document.getElementById("detail").style.display="block";
		document.getElementById("detail_img").src = "./images/sanjiao_x.png";
	}else{
		document.getElementById("detail").style.display="none";
		document.getElementById("detail_img").src = "./images/sanjiao_z.png";
	}
}

function changeh(){
	var  bw = document.body.clientWidth;
	$("#title").css("height",0.9*bw+"px");
    //document.getElementById("title").style.height = 20px;
}

window.addEventListener("resize", changeh, false);
</script>
<title>
   随手优惠-活动
</title>
</head>
<body onload="changeh()">
<div id="title">
   <div id="jifenbao">
	<div id="qipao_left" style="float:left;"><img height=80px src="./images/bai_1.png" ></div>
	<div id="jfb_num" style="float:left; background:url('./images/bai_2.png') repeat-x; height:66px;"><%=jfb%></div>
	<div id="qipao_right" style="float:left;"><img height=80px src="./images/bai_3.png" ></div>
   </div>
</div>

<div id="youhuishare" style="display:none">
<%=newShareStr%>
</div>

<div id="darenb_w">
<img width="60%" src="./images/darenb.png" />
  <div id="darenb">
     <%
        if(drban != null && drban.size() >0){
        	int mci = 1;
        	for(Map.Entry<String, Integer> m: drban.entrySet()){
        		String mc = "一";
        		if(mci == 2){
        			mc = "二";
        		}else if(mci == 3){
        			mc = "三";
        		}else if(mci == 4){
        			mc = "四";
        		}else if(mci == 5){
        			mc = "五";
        		}
        		mci ++;
        		out.print("第"+mc+"名："+m.getKey()+"获得"+m.getValue()+"个集分宝");
        		if(mci <= drban.size()){
     %>
	     <hr size=1 style="border-style:dashed ;width:97%;color:#ccc;">
     <%
        		}
        	}
        }
     %>
  </div>
</div>

<div id="detail_s" onclick="sorhdetail()">
 <div style="text-align:left;float:left">
    了解详情
 </div>
 <div style="text-align:right">
  
  <img id="detail_img" src="./images/sanjiao_z.png" />
  
 </div>
</div >

<div id="detail">
<br/>
   <strong>活动详情:</strong><br/>
11月2日—11月17日，每成功推荐一个有效用户（淘宝买家等级1颗<img src="http://pics.taobaocdn.com/newrank/b_red_1.gif" />以上的独立用户）即可获得50个集分宝，上不封顶！<br/>
所有参与活动的用户更可参与抽奖，赢取苹果5S土豪金一部！<br/>
被推荐用户成功注册，首次登陆可领50集分宝，每日签到能领更多！<br/>
如此丰厚奖品，真真儿的让奴家心动啊！<br/>
心动不如行动，骚年们，行动吧！<br/>
<br/>
<strong>活动须知:</strong><br/>
1. 本活动仅限安卓手机用户参与（发起分享和被分享用户均为安卓手机用户）<br/>
2. “有效用户”释义：淘宝买家等级1颗<img src="http://pics.taobaocdn.com/newrank/b_red_1.gif" />以上的独立用户<br/>
3. 被推荐用户需在11月17日前完成注册，推荐者方可获得奖励<br/>
4. 被推荐用户为有效用户，首次登陆可免费领取50集分宝（若淘宝等级不足1颗<img src="http://pics.taobaocdn.com/newrank/b_red_1.gif" />，不可领取）<br/>
5. 成功推荐有效用户所获集分宝将在被推荐用户完成注册后1小时内发放完毕<br/>
6. 11月18日将对所有参与活动用户进行抽奖，奖品为苹果5S土豪金一部，中奖名单将在11月22日前在“随手优惠”官方微博进行公布(在新浪微博搜索“随手优惠”即可随时关注活动动向)<br/>
<span style="color:red;">重要：</span><br/>
   &nbsp;&nbsp;&nbsp;如系统合理判别您在参与过程中有违规行为（如使用同一设备激活多个账户，恶意套取活动奖励），【随手优惠】将取消您的领奖资格。
</div>
<br/>
<br/>
<br/>
<div id="fixed">
<img src="./images/button.png" width="70%" />
</div>

<div id="etouchshare" style="display:none">
<%=shareStr%>
</div>

<div id="youhuishare" style="display:none">
<%=newShareStr%>
</div>
</body>
</html>