<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>礼物列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    
<link href="<%=request.getContextPath()%>/css/datepicker.css" rel="stylesheet" type="text/css" media="all">
<link href="<%=request.getContextPath()%>/css/tipsy.css" rel="stylesheet" type="text/css" media="all">
<link href="<%=request.getContextPath()%>/css/visualize.css" rel="stylesheet" type="text/css" media="all">
<link href="<%=request.getContextPath()%>/css/jquery.wysiwyg.css" rel="stylesheet" type="text/css" media="all">
<link href="<%=request.getContextPath()%>/js/fancybox/jquery.fancybox-1.3.0.css" rel="stylesheet" type="text/css" media="all">

    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.img.preload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/hint.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.visualize.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jwysiwyg/jquery.wysiwyg.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/fancybox/jquery.fancybox-1.3.0.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.tipsy.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/custom_blue.js"></script>
	
	<script type="text/javascript">
    
	    function statusSelect(status)
		{
	    	var timeStatus = $("#timeStatus").val();
	    	status = $("#status").val();
	    	location.href="<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showGiftList&status=" + status+"&timeStatus="+timeStatus;
		}
    
    	function delGift(gift_id, gift_title)
    	{
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/gift_manager?actionmethod=delGift',
    		    type: 'POST',
    		    data: {
    		    	gift_id:gift_id,
    		    	gift_title:gift_title
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var delElement = document.getElementById(gift_id);
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
    	
    	function useGift(gift_id, gift_title)
    	{
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/gift_manager?actionmethod=useGift',
    		    type: 'POST',
    		    data: {
    		    	gift_id:gift_id,
    		    	gift_title:gift_title
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		location.href="<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showGiftList&status=1&timeStatus=${timeStatus}";
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("启用操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
    	}
    	
    	function goto_add_gift()
		{
	    	location.href="<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showAddGiftJSP&page=${page}&status=${status}";
		}
    	
    	function movePosition(id,position,status,movetype)
    	{
    		var timeStatus = "${timeStatus}";
    		$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/gift_manager?actionmethod=movePosition',
    		    type: 'POST',
    		    data: {
    		    	id:id,
    		    	position:position,
    		    	status:status,
    		    	movetype:movetype,
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		location.href="<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showGiftList&status="+status+"&timeStatus=${timeStatus}";
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("移动操作失败!");
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
	         <h1 class="entry-title">礼物列表</h1>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showGiftList&status=${status}" method="post" >
		         关键词:<input type="text" name="keyword" value="">
		         <input type="submit" class="button" value="搜索" />
		         <input type="button" class="button" value="新增" onclick="goto_add_gift()" />
		         <div  style="float: right">
			         <div style="float: left">
			       	活动时间:<select id="timeStatus" onchange="statusSelect('')">
			         	<c:forEach var= "time" items="${timeStatusMap}" >
			         	<c:choose>
									<c:when test="${timeStatus == time.key}">
										<option value="${time.key}" selected>${time.value}</option>
									 </c:when>
									 <c:otherwise>
									 	<option value="${time.key}">${time.value}</option>
									 </c:otherwise>
								</c:choose>
			         	</c:forEach>
			         	</select>
			         
				          状态:<select id="status" onChange="statusSelect(this.value)">
							<c:forEach var="Status" items="${GiftStatusMap}">
								<c:choose>
									<c:when test="${status == Status.key}">
										<option value="${Status.key}" selected>${Status.value}</option>
									 </c:when>
									 <c:otherwise>
									 	<option value="${Status.key}">${Status.value}</option>
									 </c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
	        </form>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th>ID</th>
	    				<th>标题</th>
	    				<th>图片</th>
	    				<th>展示样式</th>
	    				<th>开始时间</th>
	    				<th>结束时间</th>
	    		<c:choose>
	    			<c:when test="${status==1 && timeStatus==2}">
	    				<th>排序</th>
	    			</c:when>
	    		</c:choose>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="gift_bean" items="${GiftList}">
	                 <tr id="${gift_bean.id}">
	                 	<td>${gift_bean.id}</td>
	                 	<td>
	                 		<c:choose>
		                 		<c:when test="${gift_bean.actionType == 'tagStyleWeb'}">
		                 		
									<a rel='slide' target='_blank' href='${gift_bean.actionValue}' style="color: #1F92FF">${gift_bean.title}</a>
								
								</c:when>
								<c:otherwise>
									${gift_bean.title}
								</c:otherwise>
							</c:choose>
                       	</td>
						<td>
							<label class="media_photos">
		                       	<a rel="slide" target="_blank" href="${gift_bean.img}" title="${gift_bean.title}">
		                       		<img width="100px" src="${gift_bean.img}" />
		                       	</a>
	                       	</label>
                       	</td>
                       	<td>
							<c:forEach var="ActionType" items="${ActionTypeMap}">
								<c:choose>
									<c:when test="${gift_bean.actionType == ActionType.key}">
										${ActionType.value}
									 </c:when>
								</c:choose>
							</c:forEach>
						</td>
                       	<td>${gift_bean.start_time}</td>
                       	<td>${gift_bean.end_time}</td>
                       	<c:choose>
                       		<c:when test="${status==1 && timeStatus==2}">
                       	<td width="110px;">
							<img style="width: 25px;" alt="上移一位" title="上移一位" src="<%=request.getContextPath() %>/img/moveup.png" onclick="movePosition(${gift_bean.id},${gift_bean.rank},${status},1)">
							<img style="width: 25px;" alt="下移一位" title="下移一位" src="<%=request.getContextPath() %>/img/movedown.png" onclick="movePosition(${gift_bean.id},${gift_bean.rank},${status},2)">
							<img style="width: 25px;" alt="移至首位" title="移至首位" src="<%=request.getContextPath() %>/img/movetop.png" onclick="movePosition(${gift_bean.id},${gift_bean.rank},${status},3)">
							<img style="width: 25px;" alt="移至末位" title="移至末位" src="<%=request.getContextPath() %>/img/movelast.png" onclick="movePosition(${gift_bean.id},${gift_bean.rank},${status},4)">
						</td>
                       		</c:when>
                       	</c:choose>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showUpdateGiftJSP&gift_id=${gift_bean.id}&page=${page}">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<c:choose>
		                 		<c:when test="${status == '1'}">
									<a href="#" onclick="delGift('${gift_bean.id}', '${gift_bean.title}')">注销</a>&nbsp;&nbsp;
								</c:when>
								<c:otherwise>
									<a href="#" onclick="useGift('${gift_bean.id}', '${gift_bean.title}')">启用</a>&nbsp;&nbsp;
								</c:otherwise>
							</c:choose>
							<c:choose>
		                 		<c:when test="${gift_bean.urlKey != ''}">
				                 		|&nbsp;&nbsp;
		                        	<a href="<%=request.getContextPath()%>/ad/statistic?actionmethod=checkClickNum&title=${gift_bean.title}&id=${gift_bean.urlKey}">查看点击量</a>
			                   	</c:when>
	                   	</c:choose>
	                   	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/gift_manager?actionmethod=showGiftList" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>