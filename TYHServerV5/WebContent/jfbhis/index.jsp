<%@page import="cn.youhui.common.SysConf"%>
<%@page import="cn.youhui.utils.SuiShouActionUtil"%>
<%@page import="cn.youhui.common.Config"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="cn.youhui.utils.TimeUtil"%>
<%@page import="cn.youhui.bean.UserGainJFBHis.YearHis.OnceHis"%>
<%@page import="cn.youhui.bean.UserGainJFBHis.YearHis"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="cn.youhui.manager.UserGainJFBHisDAO"%>
<%@page import="cn.youhui.bean.UserGainJFBHis"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String uid = request.getParameter("tyh_web_uid");
    String platform = request.getParameter("tyh_web_platform");
	UserGainJFBHis his = null; 
	if(uid != null && !"".equals(uid)){
		his = UserGainJFBHisDAO.getInstance().getHis(uid);
	}else{
		response.getWriter().print("你还未登陆");
		return;
	}
%>
<html>
<head>
	<title>我的优惠之旅</title>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/controller.js"></script><!--导入js-->
	<link rel="stylesheet" type="text/css" href="css/style.css"/><!--导入css-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
    <script type="text/javascript">
    function changePie1(shuju1,shuju2,shuju3){
    	data1[0] = shuju1;
    	data1[1] = shuju2;
    	data1[2] = shuju3;
    	
    }
    
    function changePie2(shuju1,shuju2,shuju3){
    	data2[0] = shuju1;
    	data2[1] = shuju2;
    	data2[2] = shuju3;
    	
    }
    </script>
</head>
<body onselectstart="return false">
<%
	if(his!= null){
%>
<div id="ad1">
<div id="head_portrait">
		<img src="<%=his.getMyImg() %>" id="head_portrait_pic" class="ad1_pic" style="z-index:500;" alt="">
		<img src="images/yuan_bg.png" class="ad1_pic" style="z-index:1000;"alt="">
	</div>
		<!-- <img src="images/bg1_12.png" id="ad1_pic2" alt=""> -->
	<span id="ad1_word" class="red">累计省钱<%=his.getAllGain() %>元</span>
</div>



<div id="content">
<%
	int y,m;    
	Calendar cal=Calendar.getInstance();    
	y=cal.get(Calendar.YEAR);    
	m=cal.get(Calendar.MONTH);    
	Map<String, YearHis> yearMap = his.getHisList();
	Iterator<String> iter = yearMap.keySet().iterator();
	int i = 2;
	String position = "left";
	String position1 = "right";
	String lastYear = "";
	while (iter.hasNext()) {
		String  year = iter.next();
		lastYear = year;
		YearHis  yearData = yearMap.get(year);
		Map<String, OnceHis>  monthMap = yearData.getList();	
		Iterator<String> iter1 = monthMap.keySet().iterator();		
			%>
	<div class="year">
	<%
	if(year.equals("2014")){
		
	%>
	<img onclick="javascript:location.href='../fenhong/level_desc.html'" id="ad2" src="images/2014_<%=his.getLevel()+1 %>.png" alt="">
	<span class="year2014 orange">2014年</span>
	<%
	} else {
		%>
	<img id="<%=year %>" src="images/<%=year %>.png" alt="">
	<span class="year<%=year %> orange"><%=year %>年</span>
		<% 
	}
	%>
	</div>
	<%
		if(year.equals("2013")){
	%>
	<div id="ad6">
	<img src="images/totalleft.png" alt="">
	<img id="ad6_pic1" src="images/input4_78.png" alt="" />
	<img id="ad6_pic2" src="images/pie_pic1_42.png" alt="">
	<p><span id="ad6_word" class="red">今年累计获得<%=yearData.getFanliJFB()+yearData.getOtherJFB()+yearData.getSignJFB() %>个集分宝</span></p>
	<script type="text/javascript">
	<%int total =yearData.getFanliJFB()+yearData.getOtherJFB()+yearData.getSignJFB(); %>
	changePie1('<%=(yearData.getFanliJFB()*100/total) %>','<%=100-(yearData.getFanliJFB()*100/total+yearData.getSignJFB()*100/total)%>','<%=(yearData.getSignJFB()*100/total)%>');
	</script>
	<canvas id="circleone">您的浏览器不支持canvas</canvas>
</div>
			<% 
		}else if(year.equals("2012")){
			%>
			<div id="ad11">
	<img src="images/totalright.png" alt="">
	<img id="ad11_pic1" src="images/input9_108.png" alt="" />
	<img id="ad11_pic2" src="images/pie_pic2_60.png" alt="">
	<p><span class="red">今年累计获得<%=yearData.getFanliJFB()+yearData.getOtherJFB()+yearData.getSignJFB() %>集分宝</span></p>
	<script type="text/javascript">
	<%int total =yearData.getFanliJFB()+yearData.getOtherJFB()+yearData.getSignJFB(); %>
	changePie2('<%=(yearData.getFanliJFB()*100/total)%>','<%=100-(yearData.getFanliJFB()*100/total+yearData.getSignJFB()*100/total)%>','<%=(yearData.getSignJFB()*100/total)%>');
	</script>
	<canvas id="circletwo">您的浏览器不支持canvas</canvas>
</div>
			<% 
		}
			while (iter1.hasNext()) {
				String month = iter1.next();		
				OnceHis monthData = monthMap.get(month);
				boolean isFirst = monthData.isFirst();
				String type = monthData.getType();
				String pic1 = "";
				String pic2 = "";
				String word = "";
				String detail = "";
				String clas = "";
				String dataPos = "";
				String picPos = "";
				if(isFirst){
					if(type.equals("fenhong")){	//分红
						if(year.equals("2014") || year.equals("2012")){
							dataPos = "left";
							picPos = "right";
						 	pic1 = "input2_59";
						 	clas = "ad4";
						}else{
							dataPos = "right";
							picPos = "left";
							pic1 = "input6_91";
							clas = "ad7";
						}
							 
						 	word = "orange";
						 	detail = "上月实时分红"+monthData.getJfbNum()+"个集分宝";					 	
							if(year.equals("2014")){
								if(monthData.getTime() != null && monthData.getTime().length()>2 && monthData.getTime().substring(0,1).equals((m+1)+"")){	//只有本月分红记录才显示红色
									word = "red";
									clas = "ad3";
									pic1 = "input1_48";
									pic2 = "fangdajing_51";
								}
							}						 	
					}else if(type.equals("sign")){	//签到
						if(year.equals("2014") || year.equals("2012")){
							dataPos = "left";
							picPos = "right";
							pic1 = "input10_115";
						clas = "ad4";
						}else{
							dataPos = "right";
							picPos = "left";
							pic1 = "green_jiantou_right";
							clas = "ad8";
						}
						 pic2 = "";
						 word = "green";
						 detail = "第一个月签到获得"+monthData.getJfbNum()+"个集分宝";
					}else if(type.equals("fanli")){	//购物
						if(year.equals("2014") || year.equals("2012")){
							dataPos = "left";
							picPos = "right";
							pic1 = "yellow_jiantou_left";
							clas = "ad5";
						}else{
							dataPos = "right";
							picPos = "left";
							pic1 = "input5_86";
							clas = "ad7";
						}
						 pic2 = "duihao_65";
						 word = "yellow";
						 detail = "第一个月购物奖励"+monthData.getJfbNum()+"个集分宝";
					}else{
						continue;
						 //pic1 = "6_91";
						 //pic2 = "";
						 //word = "green";
						// detail = "其他活动获得"+monthData.getJfbNum()+"个集分宝";
					}
				}
				else{

					if(type.equals("fenhong")){	//分红
						if(year.equals("2014") || year.equals("2012")){
							dataPos = "left";
							picPos = "right";
						 	pic1 = "input2_59";
						 	clas = "ad4";
						}else{
							dataPos = "right";
							picPos = "left";
							pic1 = "input6_91";
							clas = "ad7";
						}
						word = "orange";
						
						 	detail = "上月实时分红"+monthData.getJfbNum()+"个集分宝";
						 	if(year.equals("2014")){
								if(monthData.getTime() != null && monthData.getTime().length()>2 && monthData.getTime().substring(0,1).equals((m+1)+"")){	//只有本月分红记录才显示红色
									word = "red";
									clas = "ad3";
									pic1 = "input1_48";
									pic2 = "fangdajing_51";
								}
							}	
					}else if(type.equals("sign")){	//签到
						if(year.equals("2014") || year.equals("2012")){
							dataPos = "left";
							picPos = "right";
							pic1 = "input2_59";
						clas = "ad4";
						}else{
							dataPos = "right";
							picPos = "left";
							pic1 = "input6_91";
							clas = "ad8";
						}
						 pic2 = "";
						 word = "orange";
						 detail = "签到获得"+monthData.getJfbNum()+"个集分宝";
					}else if(type.equals("fanli")){	//购物
						if(year.equals("2014") || year.equals("2012")){
							dataPos = "left";
							picPos = "right";
							pic1 = "input2_59";
							clas = "ad5";
						}else{
							dataPos = "right";
							picPos = "left";
							pic1 = "input6_91";
						clas = "ad7";
						}
						 pic2 = "duihao_65";
						 word = "yellow";
						 detail = "购物奖励"+monthData.getJfbNum()+"个集分宝";
					}else{
						continue;
						 //pic1 = "6_91";
						 //pic2 = "";
						 //word = "green";
						// detail = "其他活动获得"+monthData.getJfbNum()+"个集分宝";
					}
				
				}
				%>
				<div class="<%=clas%>">
				
	<span class="data_<%=dataPos%> gray"><%=monthData.getTime() %></span>
	<img src="images/normal<%=picPos%>.png" alt="">
	<img class="<%=clas%>_pic1" src="images/<%=pic1%>.png" alt="" />
	<%
	if(type.equals("fenhong")){
		String preYear = year;
		String preMonth = month;
		if(month!= null && month.length() >2){		
			if(month.substring(1,2).equals("1") || month.substring(1,2).equals("2") || month.substring(1,2).equals("0")){
				preMonth = Integer.parseInt(month.substring(0,2))-1+"";
			}else{
				if(month.subSequence(0, 1).equals("1")){
					preMonth = "12";
					preYear = (Integer.parseInt(year) - 1)  +"";
				}else{
					preMonth = Integer.parseInt(month.substring(0,1))-1+"";
				}
			}
		}
		%>
		<%
			String clickUrl = "";
			if("iphone".equalsIgnoreCase(platform)){				
				String ecoUrl = SuiShouActionUtil.ecodeUrl( Config.JFBAD_HOST+"fenhong/index.jsp?tyh_web_uid="+uid+"&year="+preYear+"&month="+preMonth);
				clickUrl = SuiShouActionUtil.urlHttp +SysConf.actionTypeToUrlMap.get("tagStyleWeb")+ "?url="+ ecoUrl;
			}else{
				clickUrl = Config.JFBAD_HOST+"fenhong/index.jsp?tyh_web_uid="+uid+"&year="+preYear+"&month="+preMonth;
			}
		%>
		<a href="<%=clickUrl %> " style="text-decoration:none;">
	<span class="<%=clas%>_word   <%=word%>">	
		<%
		detail = preMonth+"月实时分红"+monthData.getJfbNum()+"个集分宝";
		out.print(detail+">>");
		%>
	</span>
		</a>		
		<%
	}else{
		%>
		<span class="<%=clas%>_word   <%=word%>">	
		<% 
		out.print(detail);
		%>
		</span>
		<% 
	}
	%>
	<%
		if(!"".equals(pic2)){
			
	%>
	<img class="<%=clas%>_pic2" src="images/<%=pic2%>.png" alt="" />
	<%
			}
	%>
</div>
				<% 
			}
			
	}
	%>

<%if(lastYear.equals("2013")){ %>

<div class="ad8">
	<span class="data_right_begin gray"><%
			DateFormat df = new SimpleDateFormat("M月d日");
	if(his.getStartTime()  != 0){
		//String startDate = TimeUtil.getDateTimeStr(his.getStartTime());
		//if(!"".equals(startDate)){
		out.print(df.format(new Date(his.getStartTime())));
		//}
	}
	%></span>
	<img src="images/normalleft.png" alt="">
	<img class="ad13right_pic" src="images/orange_jiantou_right.png" alt="" />
	<p style="margin:0;"><span class="ad8_word orange" >开始加入随手优惠，开启了您新的购物省钱旅程</span></p>
</div>
<img src="images/startright.png" alt="">
<%}else{ %>
<div id="ad13">
	<span class="data_left_begin gray"><%
	DateFormat df = new SimpleDateFormat("M月d日");
if(his.getStartTime()  != 0){
//String startDate = TimeUtil.getDateTimeStr(his.getStartTime());
//if(!"".equals(startDate)){
out.print(df.format(new Date(his.getStartTime())));
//}
}
	%></span>
	<img src="images/normalright.png" alt="">
	<img id="ad13_pic1" src="images/input11_121.png" alt="" />
	<p><span id="ad13_word" class="orange">开始加入随手优惠，开启了您新的购物省钱旅程</span></p>
</div>
<img src="images/start.png" alt="" >
<%} %>
</div>


<%} %>

	
</body>
</html> 	