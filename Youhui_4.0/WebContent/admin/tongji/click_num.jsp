<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>点击量查看-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/googlejsapi.js"></script>
    <script type="text/javascript">
    function back() {
    	history.back();
    }
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">《${title}》点击量</h1>
	         <hr/>
	         <form action="#" method="post" >
<span> <input type="button" value="返回" onclick="back()" /></span>
	        </form>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th>时间</th>
	    				<th>点击量</th>
	
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach var="item" items="${list}"  varStatus="status">
	                 <tr>
                       	<td>${item.title}</td>
                       	<td>${item.value}</td>
	                </tr>
                   </c:forEach>
	    		</tbody>
	    	</table>
	    	<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  //var content = ${content};
    	  //data = JSON.parse(data); 
    	 var s=  [
           ['Date', 'count'],${content}
           ];
        var data = google.visualization.arrayToDataTable(s);
        var options = {
          title: '广告点击量 '
        };

        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
      
      
    </script>
      <div id="chart_div" style="width: 100%; height: 500px;"></div>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="" method="post">
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>