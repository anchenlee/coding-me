<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="cn.suishou.some.dao.ShareAppDAO" %>
<%@ page import="cn.suishou.some.util.Base64" %>

<%
    String uid = request.getParameter("tyh_web_uid");
    String uid64 = "";
    if(uid != null && !"".equals(uid)){
    	if(ShareAppDAO.getInstance().isJoin(uid)){
    		response.sendRedirect("./share_result.jsp?uid=" + uid);
    		return;
    	}
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
<style type="text/css">
a{
text-decoration: none;
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
<script language="JavaScript">
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

function getshare(){
	alert(document.getElementById("youhuishare").innerHTML);
}

</script>
<title>
   随手优惠-活动
</title>
</head>
<body>


<div id="youhuishare" style="display:none">
<%=newShareStr%>
</div>

<img src="./images/tuijian.png" width="100%" />

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

</body>
</html>