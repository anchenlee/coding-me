<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>搜索关键字列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
        <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
    <script type="text/javascript">
    
	    function statusSelect(status)
		{
	    	var status = $("#status").val();
	    	location.href="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showSearchKeywordList&parent_id=${parent_id}&version=1&status=" + status;
		}
    
    	function delSearchKeyword(id, name)
    	{
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=delSearchKeyword',
    		    type: 'POST',
    		    data: {
    		    	id:id,
    		    	name:name
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var delElement = document.getElementById(id);
    		    		delElement.parentNode.removeChild(delElement);
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("删除操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
    	}
    	
    	function updateSearchKeywordStatus(id, name, status)
    	{
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=updateSearchKeywordStatus',
    		    type: 'POST',
    		    data: {
    		    	id:id,
    		    	name:name,
    		    	status:status
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var delElement = document.getElementById(id);
    		    		delElement.parentNode.removeChild(delElement);
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
    	}
    	
    	function goto_add_searchkeyword()
		{
	    	location.href="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showAddSearchKeywordJSP&parent_id=${parent_id}";
		}
    	
    	function goto_update_searchkeyword(id)
    	{
    		location.href="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showUpdateSearchKeywordJSP&parent_id=${parent_id}&id=" + id;
    	}
    	
    	function goto_last_level_list()
    	{
    		location.href="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showLastLevelList&parent_id=${parent_id}";
    	}
    	
    	function movePosition(id,parent_id,movetype,position)
    	{
    		var status = "${status}";
    		$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=movePosition',
    		    type: 'POST',
    		    data: {
    		    	id:id,
    		    	parent_id:parent_id,
    		    	movetype:movetype,
    		    	position:position,
    		    	status:status
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		location.href="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showSearchKeywordList&parent_id=${parent_id}";
    		        	return;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("删除操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
    	}
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">搜索热门关键字列表</h1>
	         <hr/>
	           <form action="../ad/search_keyword_manager?actionmethod=showHotSearchKeywordsList" method="post">
开始时间：<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="" />
结束时间：<input type="text" class="ui_timepicker" name="end_time" id="end_time" value="" />
<input type="submit" value="查看"><font color="red">  (默认时间月初到今天)</font> 
	         </form>
	         <div  style="float: right">
		         <div style="float: left">
		         
				</div>
			</div>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>

	    				<th>搜索关键词</th>
	    				<th>搜索次数</th>

	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="searchkeyword" items="${list}">
	                 <tr >
	                 <td>${searchkeyword.name}</td>
	                 	<td>${searchkeyword.rank}</td>
	                 	
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showHotSearchKeywordsList" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
		<script type="text/javascript">
	$(function () {
        $(".ui_timepicker").datetimepicker({
            //showOn: "button",
            //buttonImage: "./css/images/icon_calendar.gif",
            //buttonImageOnly: true,
            showSecond: true,
            timeFormat: 'hh:mm:ss',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        });
    });
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>