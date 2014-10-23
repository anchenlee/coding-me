<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>推荐列表-随手4.0</title>
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
	    	location.href="<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showRecomList&status=" + status;
		}
    	function delRecom(recom_id, recom_title)
    	{
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/recom_manager?actionmethod=delRecom',
    		    type: 'POST',
    		    data: {
    		    			recom_id:recom_id,
    		    			recom_title:recom_title
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var delElement = document.getElementById(recom_id);
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
    	
    	function goto_add_recom(ispadtag)
		{
	    	location.href="<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showAddRecomJSP&page=${page}";
		}
    	
    	 function movePosition(id,position,movetype) {
 	    	$.ajax({
     		    url: '<%=request.getContextPath()%>/ad/recom_manager?actionmethod=MovePosition',
     		    type: 'POST',
     		    data: {    		    			
     		    			id:id,
     		    			position:position,
     		    			movetype:movetype,
     		    			parent_id:2,
     		    			state:0
     		    			},
     		    dataType: 'json',
     		    timeout: 5000,
     		    success: function(json)
     		    {
     		    	var result = json.result;
     		    	if (result == "0")
    		    		{
     		    		var url = "<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showRecomList&page=${page}&status=${status}";
     					location.href=url;
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
	         <h1 class="entry-title">推荐列表</h1>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showRecomList" method="post" >
		         关键词:<input type="text" name="keyword" value="">
		         <input type="submit" class="button" value="搜索" />
		         <input type="button" class="button" value="新增" onclick="goto_add_recom()" />
		         <div  style="float: right">
			         <div style="float: left">
				          状态:<select id="status" onChange="statusSelect(this.value)">
							<c:forEach var="Status" items="${RecomStatusMap}">
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
	    				<c:when test="${status==2}">
	    				
	    				<th>排序</th>
	    				</c:when>
	    				</c:choose>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="recom_bean" items="${RecomList}">
	                 <tr id="${recom_bean.id}">
	                 	<td>${recom_bean.id}</td>
	                 	<td>
	                 		<c:choose>
		                 		<c:when test="${recom_bean.action.actionType == 'tagStyleWeb'}">
									<a rel='slide' target='_blank' href='${recom_bean.action.actionValue}' style="color: #1F92FF">${recom_bean.title}</a>
								</c:when>
								<c:otherwise>
									${recom_bean.title}
								</c:otherwise>
							</c:choose>
                       	</td>
						<td>
						<label class="media_photos">
	                       	<a rel="slide" target="_blank" href="${recom_bean.icon}" title="${recom_bean.title}">
	                       		<img width="100px"  src="${recom_bean.icon}" />
	                       	</a>
	                       	</label>
                       	</td>
                       	<td>
							<c:forEach var="ActionType" items="${ActionTypeMap}">
								<c:choose>
									<c:when test="${recom_bean.action.actionType == ActionType.key}">
										${ActionType.value}
									 </c:when>
								</c:choose>
							</c:forEach>
						</td>
                       	<td>${recom_bean.startTime}</td>
                       	<td>${recom_bean.endTime}</td> 
                       	<c:choose>
	    				<c:when test="${status==2}">
                       	<td width="110px;">
                       	<a onclick="movePosition(${recom_bean.id},${recom_bean.rank},1)">
							<img style="width: 25px;" alt="上移一位" title="上移一位" src="<%=request.getContextPath() %>/img/moveup.png" onclick="location.href='#'">
                       	</a>
						<a onclick="movePosition(${recom_bean.id},${recom_bean.rank},2)">
							<img style="width: 25px;" alt="下移一位" title="下移一位" src="<%=request.getContextPath() %>/img/movedown.png" onclick="location.href='#'">
						</a>
						<a onclick="movePosition(${recom_bean.id},${recom_bean.rank},3)">
							<img style="width: 25px;" alt="移至首位" title="移至首位" src="<%=request.getContextPath() %>/img/movetop.png" onclick="location.href='#'">
						</a>
						<a onclick="movePosition(${recom_bean.id},${recom_bean.rank},4)">
							<img style="width: 25px;" alt="移至末位" title="移至末位" src="<%=request.getContextPath() %>/img/movelast.png" onclick="location.href='#'">
						</a>
						</td>
						</c:when>
						</c:choose>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showUpdateRecomJSP&recom_id=${recom_bean.id}&page=${page}">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="#" onclick="delRecom('${recom_bean.id}', '${recom_bean.title}')">删除</a>&nbsp;&nbsp;
                        	<c:choose>
		                 		<c:when test="${recom_bean.urlKey != ''}">
		                 		|&nbsp;&nbsp;
                        	<a href="<%=request.getContextPath()%>/ad/statistic?actionmethod=checkClickNum&title=${recom_bean.title}&id=${recom_bean.urlKey}">查看点击量</a>
	                   	</c:when>
	                   	</c:choose>
	                   	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/recom_manager?actionmethod=showRecomList" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>