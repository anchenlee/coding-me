<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.youhui.manager.FenhongManager" %>
<%@ page import="cn.youhui.manager.UserManager" %>
<%@ page import="cn.youhui.bean.Fenhong" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<% 
String taobaoNick = request.getParameter("tyh_web_taobaonick");
String uid = request.getParameter("tyh_web_uid");
if(taobaoNick == null){
	taobaoNick = "";
}
taobaoNick = new String(taobaoNick.getBytes("iso-8859-1"), "utf-8");
Map<String ,List<Fenhong>> fenhongHis = null;

if(UserManager.getInstance().isUserExist(uid, taobaoNick)){
	fenhongHis = FenhongManager.getInstance().getFenhong(uid);
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史分红</title>
<meta name="viewport" content="width=device-width" initial-scale="1.0" maximum-scale="1.0" user-scalable="no" />
	<style type="text/css">
		*{margin:0;padding:0;}
		body,html{width:100%;}
		.header{width:100%;color:#6b6b6b;height:50px;line-height:50px;border-bottom:1px solid #c7c6c6;background:#e8e8e8;text-indent:18px;font-size:14px;font-weight:normal;}
		.list{width:100%;background:#e8e8e8;border-top:1px solid #fff;}
		.icon{display:block;width:28px;height:28px;background:url(./images/icon1.png);float:right;margin:11px 20px;}
		.mark{background:url(./images/icon.png);}
		.list h2{font-size:14px;}
		.tab{background:#fff;text-align:center;color:#6b6b6b;font-size:14px;border-top:1px solid #fff;display:none;}
		.tab td{border-bottom:1px solid #d5d5d5;position:relative;}
		/*.tab tr td:nth-child(1){background:#e8e8e8;}*/
		.orange{color:#ff7b00;font-weight:bold;}
		.small{font-size:10px;color:#6b6b6b;vertical-align: baseline;}
		.icon_img{position:relative;top:3px;}
		/*.border:after{content:"";display:block;width:1px;height:70px;border-right:1px solid #d7d7d7;position:absolute;right:0px;top:5px;}*/
		.border{border-right:1px solid #d7d7d7;}
		.tab tr td:nth-child(5){font-size:12px;}
		.red{color:#f2300a;font-size:18px;font-weight:bold;}
	</style>
</head>
<body>
<h2 class="header">亲爱的<%=taobaoNick%>，你的历史分红如下：</h2>
<% if(fenhongHis == null || fenhongHis.size() == 0) {%>
<br/><br/><br/><br/>
<img width="100%" src="./images/wufenhong.png">
<% }else{
   for(Map.Entry<String, List<Fenhong>> m : fenhongHis.entrySet()){
	   String year = m.getKey();
	   List<Fenhong> fhlist = m.getValue();
	   %>
	   <div class="list">
	   <h2 class="header"><span class="icon"></span><%=year%>年分红</h2>
	   <table class="tab" border="0" cellpadding="0" cellspacing="0" width="100%">
	   <tr height="80">
				<td width="20%" class="border">月份</td>
				<td width="20%" class="border">购物奖励</td>
				<td width="20%" class="border">分红比例</td>
				<td width="20%" class="border">加送</td>
				<td width="20%">当月分红</td>
	  </tr>
	  <%
	   for(Fenhong fh : fhlist){
	%>
	<tr height="80">
				<td class="border"><%=fh.getMonth()%>月</td>
				<td class="border"><span class="orange"><%=fh.getJfbNum()%></span></td>
				<td class="border"><span class="orange"><%=fh.getFhRate()%>%</span></td>
				<td class="border"><img class="icon_img" src="./images/<%=fh.getIcon()%>" width="15" alt="" /><span class="orange"><%=fh.getLevelRate()%>%</span></td>
				<td><span class="red"><%=fh.getFhJfbNum()%></span><br />(
				 <%
	     int status = fh.getStatus();
	     if(status == 1){
	    	 out.print("审核中");
	     }else if(status == 2){
	    	 out.print("成功");
	     }else {
	    	 out.print("失败");
	     }
	   %>
				)</td>
			</tr>
	<%
	   }
	  %>
	  </table>
	   </div>
	  <%
   }
 }%>
 
 	<script type="text/javascript" src="./js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
		$(".list:first").find("table").css("display","table");
		$(".list:first").find(".icon").addClass("mark");
		$(".icon").click(function () {
			if ($(this).hasClass("mark")) {
				$(this).removeClass("mark");
				$(this).parent("h2").next(".tab").css("display","none");
			}else{
				$(this).addClass("mark");
				$(this).parent("h2").next(".tab").css("display","table");
			}
		});
	</script>
 
</body>
</html>