<%@page import="java.math.BigDecimal"%>
<%@page import="cn.youhui.bean.Fenhong"%>
<%@page import="cn.youhui.manager.FenhongManager"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.youhui.manager.TradeManager"%>
<%@page import="cn.youhui.bean.FanliBean"%>
<%@page import="java.util.List"%>
<%@page import="cn.youhui.dao.MySQLDBIns"%>
<%@page import="java.sql.Connection"%>
<%@page import="cn.youhui.utils.FenHongUtil"%>
<%@page import="cn.youhui.manager.FanliManager"%>
<%@page import="cn.youhui.manager.JiFenBaoMXManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String uid = request.getParameter("tyh_web_uid");
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String ac_join_id = request.getParameter("ac_join_id");
	if(ac_join_id != null && ac_join_id.length() >=6){
		year = ac_join_id.substring(0,4);
		month = ac_join_id.substring(4,6);
		String preYear = year;
		String preMonth = month;

		if(month.equals("01")){
			preMonth = "12";
			preYear = (Integer.parseInt(year) - 1)  +"";
		}else{
			preMonth = Integer.parseInt(month.substring(0,2))-1+"";
		}
		year = preYear;
		month = preMonth;
	}

	if(year == null){
		year = "";
	}
	if(month == null){
		month = "";
	}
	int mothJfbNum = 0;//总集分宝
	double leveRate = 0;//等级分红比例
	double fanliRate = 0;	//返利分红比例
	int fenhongzonge = 0;
	int level = 0;
	int y,m;    
	Calendar cal=Calendar.getInstance();    
	y=cal.get(Calendar.YEAR);    
	m=cal.get(Calendar.MONTH)+1;
	if(uid != null && !"".equals(uid)){
		if(year == null || "".equals(year) || month == null || "".equals(month) || (year.equals(y+"") && month.equals(m+""))){
			Connection conn = MySQLDBIns.getInstance().getConnection();			
			mothJfbNum = JiFenBaoMXManager.getInstance().getMonthGainJfbNum(uid, conn);
			double fanli = FanliManager.getFanliWithReview(uid, conn);
			int fanlijfb = 0;
			fanlijfb = JiFenBaoMXManager.getInstance().getFanliJfbNum(uid, conn);
			fanli += fanlijfb/100;
			level = FenHongUtil.getLevel(fanli);
			leveRate = FenHongUtil.getRateByLevel(level);
			fanliRate = FenHongUtil.getRateByFanli(mothJfbNum);

			int fenhong = JiFenBaoMXManager.getJFB(mothJfbNum , leveRate , fanliRate);
			double fenhongzong = mothJfbNum*(leveRate+fanliRate)/100;
			fenhongzonge =  (int)Math.rint(fenhongzong);
			int nextLevel = FenHongUtil.getNextLevel(level);
			int nextFanli = FenHongUtil.getNextFanli(mothJfbNum);	
			MySQLDBIns.getInstance().release(conn);
		}
		else{
			Fenhong fh = FenhongManager.getFenhong(uid, year, month);
			if(ac_join_id != null && ac_join_id.length() >6){
				try{				
					if((Integer.parseInt(ac_join_id.substring(0,4)) > 2014) || (Integer.parseInt(ac_join_id.substring(0,4)) == 2014  && Integer.parseInt(ac_join_id.substring(4,6))>3)){
						String outerAcId = FenhongManager.getUniqueId(ac_join_id);
						fh = FenhongManager.getFenhongFromOuterAcId(outerAcId);
					}
				}catch(Exception e){
					
				}
			}
			if(fh != null){				
				mothJfbNum = fh.getJfbNum();
				leveRate = fh.getLevelRate();
				fanliRate = fh.getFhRate();
				fenhongzonge = fh.getFhJfbNum();
				//fanlijfb = fh.getFhJfbNum();
				
				level = FenHongUtil.getLevelByLevelRate(leveRate);
			}
		}
}
	
	
	List<FanliBean> list = new ArrayList<FanliBean>();
	if(year == null || "".equals(year) || month == null || "".equals(month)){		
		list = TradeManager.getInstance().getMonthTradeHistory(uid);
	}else{
		list = TradeManager.getInstance().getMonthTradeHistoryByMonth(uid, year, month);
	}
%>
<html>
<head>
	<title><%if(month != null && !"".equals(month)) out.print(month);else{out.print("每");} %>月分红</title>
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script><!--谷歌jquery包-->
	<script type="text/javascript" src="js/controller.js"></script><!--导入js-->
	<script type="text/javascript" src="jquery.infinitescroll.js"></script><!--导入js-->
	<script type="text/javascript">
	uid = '<%=uid%>';
	dizhi = '<%=request.getContextPath()%>';
	year = '<%=year%>';
	month = '<%=month%>';
	</script>
	<link rel="stylesheet" href="css/style.css"/><!--导入css-->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/><!--屏幕自适应-->
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/><!--字体-->
</head>
<body>
<div id="head">
	<table>
		<tr>
		<%
			String levelPic = "yi@2x.png";
		if(level ==1){
			levelPic = "er@2x.png";
		}else if(level ==2){
			levelPic = "san@2x.png";
		}else if(level ==3){
			levelPic = "si@2x.png";
		}else if(level ==4){
			levelPic = "wu@2x.png";
		}else if(level ==5){
			levelPic = "liu@2x.png";
		}
		%>
			<td colspan="1" class="td_1" rowspan="" headers="">本月购物奖励<br /><span class="orange"><%=mothJfbNum %></span><span class="jifenbao">(集分宝)</span></td>
			<td colspan="1" class="td_1" rowspan="" headers="">本月分红比例<br/><span class="orange"><%=fanliRate %>%</span></td>
			<td colspan="1" class="td_1" rowspan="" headers=""><span><img style="width:12%;" src="images/<%= levelPic %>" alt=""></span>加送<br/><span class="orange"><%=leveRate%>%</span></td>  
		</tr>
		<tr>
			<td colspan="1" class="td_2" rowspan="" headers="">本月分红总额:</td>
			<td colspan="1" class="td_2" rowspan="" headers=""></td>
			<td colspan="1" class="td_2" rowspan="" headers=""></td>
		</tr>
		<tr>
			<td colspan="3" class="td_3" rowspan="1" headers=""><div id="td_3_bg"><%=mothJfbNum %>x(<%=fanliRate %>%+<%=leveRate%>%)=<span class="red"><%=fenhongzonge %></span><span class="jifenbao">(集分宝)</span></div>
			</td>
		</tr>
	</table>
</div>
<div id="content">
	<div id="content_title"><span class="orange">本月有效订单</span></div>
<%
		if(list != null && list.size() > 0){
			for(FanliBean bean : list){
				%>
				<div class="records"><img src="<%=bean.getPrice() %>" class="records_body_1" alt=""><div class="records_body_2"><p><%
				if(bean.getName() != null && bean.getName().length() >12) {				
					out.print(bean.getName().substring(0,12)+"...")  ;
				}
				else out.print(bean.getName());
				
				%></p><p><span class="bold">送<span class="red"><%=(int) Double.parseDouble(bean.getNum()) %></span>个</span></p></div><div class="records_body_3">
				<%
			     String checked = bean.getState();
			     String ch = "OK";
			     if("4".equals(checked)){
			    	 ch = "失败";
			     }else if("5".equals(checked)){
			    	 ch = "请稍等";
			     }
			     out.print(ch);
			%>
				<br/>&nbsp</div><div class="date_left">订单编号:<%=bean.getOrderid() %></div><div class="date_right"><%=bean.getTime() %></div></div>
				<% 
			}
		}
	%>
</div>
</body>
</html> 	