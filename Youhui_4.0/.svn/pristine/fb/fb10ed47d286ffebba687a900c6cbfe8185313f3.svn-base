<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>公告列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
  
<link href="<%=request.getContextPath()%>/js/fancybox/jquery.fancybox-1.3.0.css" rel="stylesheet"
	type="text/css" media="all">

    
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
	    	location.href="<%=request.getContextPath()%>/ad/announcement_action?actionmethod=showAnnouncementList&status=" + status+"&page=1";
		}
	    
	    function platformSelect(type)
		{
	    	location.href="<%=request.getContextPath()%>/ad/announcement_action?actionmethod=showAnnouncementList&status=${status}&type=" + type+"&page=1";
		}
    
	    function updateAnnouncement(id)
		{
	    	location.href="<%=request.getContextPath()%>/ad/announcement_action?actionmethod=showUpdateAnnouncementJSP&id=" + id;
		}
	    
	    
    	function delAN(id)
    	{
    		if(!confirm("确定删除？")){
    			return;
    		}
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/announcement_action?actionmethod=delAnnouncement',
    		    type: 'POST',
    		    data: {
    		    	id:id
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/announcement_action?actionmethod=showAnnouncementList";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("删除操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
    	}
    	
    	function goto_add_an()
		{
	    	location.href="<%=request.getContextPath()%>/ad/announcement_action?actionmethod=showAddAnnouncementJSP";
		}
    	
    	
    	function movePosition(id,movetype,position)
    	{
    		var status = "${status}";
    		var platform = "${platform}";
    		$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/ad_manager?actionmethod=movePosition',
    		    type: 'POST',
    		    data: {
    		    	id:id,
    		    	movetype:movetype,
    		    	position:position,
    		    	status:status,
    		    	platform:platform,
    		    	
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		location.href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&status=${status}&platform=${platform}";
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("操作失败!");
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
	         <h1 class="entry-title">公告列表</h1>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/announcement_action?actionmethod=showAnnouncementList" method="post" >
		         关键词:<input type="text" name="keyword" value="">
		         <input type="submit" class="button" value="搜索" />
		         <input type="button" class="button" onclick="goto_add_an()" value="新增" />
		         <div  style="float: right">
			         <div style="float: left">
				          状态:<select id="status" onChange="statusSelect(this.value)">
								<c:choose>
									<c:when test="${status =='0'}">
										<option value="0" selected>正常</option>
										<option value="1">删除</option>
									 </c:when>
									 <c:otherwise>
									 	<option value="0" >正常</option>
										<option value="1" selected>删除</option>
									 </c:otherwise>
								</c:choose>
						</select>
					</div>
					<div style="float: right">
					时间类型:<select id="platform" onChange="platformSelect(this.value)">
							<c:choose>
								<c:when test="${type =='0'}">
									<option value="0" selected>尚未开始</option>
									<option value="1" >正在进行</option>
									<option value="2">已经结束</option>
								 </c:when>
								 <c:when test="${type =='1'}">
								 	<option value="0" >尚未开始</option>
									<option value="1" selected>正在进行</option>
									<option value="2">已经结束</option>
								 </c:when>
								 <c:otherwise>
								 	<option value="0" >尚未开始</option>
									<option value="1" >正在进行</option>
									<option value="2" selected>已经结束</option>
								 </c:otherwise>
							</c:choose>						
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
	    			    <th>页面地址</th>
	    			    <th>开始时间</th>
	    			    <th>结束时间</th>
	    			    <th>按钮类型</th>
	    			    <th>左按钮</th>
	    			    <th>右按钮</th>
	    			    <th>编辑</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="bean" items="${list}">
	                 <tr id="${bean.id}">
	                 	<td>${bean.id}</td>
	                 	<td>${bean.title}</td>
	                 	<td>
	                 		<a target="_blank" href="${bean.contentUrl}">
	                 		${bean.contentUrl}
	                 		</a>
	                 	</td>
	                 	<td>${bean.startDate}</td>
	                 	<td>${bean.endDate}</td>
	                 	<td></td>
	                 	<td>${bean.leftButton.name}</td>
	                 	<td>${bean.rightButton.name}</td>
	                 	<td>
	                 		<a href='javascript:updateAnnouncement(${bean.id})'>编辑</a>
	                 		<a href='javascript:delAN(${bean.id})'>删除</a>
	                 		
	                 	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/announcement_action?actionmethod=showAnnouncementList&status=${status}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>