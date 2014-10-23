<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>消息列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript">
    
	    function platformSelect(platform)
		{
	    	location.href="<%=request.getContextPath()%>/ad/message_manager?actionmethod=showMessageList&platform=" + platform;
		}
    
    	function delMessage(pid, title)
    	{
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/message_manager?actionmethod=delMessage',
    		    type: 'POST',
    		    data: {
    		    	pid:pid,
    		    	title:title
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var delElement = document.getElementById(pid);
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
    	
    	function goto_add_message()
		{
	    	location.href="<%=request.getContextPath()%>/ad/message_manager?actionmethod=showAddMessageJSP&page=${page}&platform=${platform}";
		}
    	
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">消息列表</h1>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/message_manager?actionmethod=showMessageList&platform=${platform}" method="post" >
		         关键词:<input type="text" name="keyword" value="">
		         <input type="submit" class="button" value="搜索" />
		         <input type="button" class="button" value="新增" onclick="goto_add_message()" />
		         <div  style="float: right">
					<div style="float: left">
					平台类型:<select id="platform" onChange="platformSelect(this.value)">
						<option value="all">all</option>
						<c:forEach var="Platform" items="${MessagePlatformMap}">
							<c:choose>
								<c:when test="${platform == Platform.key}">
									<option value="${Platform.key}" selected>${Platform.value}</option>
								 </c:when>
								 <c:otherwise>
								 	<option value="${Platform.key}">${Platform.value}</option>
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
	    				<th>标题</th>
	    				<th>图片</th>
	    				<th>平台</th>
	    				<th>发送范围</th>
	    				<th>发送条件</th>
	    				<th>有效期</th>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="message" items="${MessageList}">
	                 <tr id="${message.pId}">
	                 	<td>
	                 		<c:choose>
		                 		<c:when test="${message.action.actionType == 'tagStyleWeb' || message.action.actionType == 'systemWeb'}">
									<a rel='slide' target='_blank' href='${message.action.actionValue}' style="color: #1F92FF">${message.title}</a>
								</c:when>
								<c:otherwise>
									${message.title}
								</c:otherwise>
							</c:choose>
                       	</td>
						<td>
	                       	<a rel="slide" target="_blank" href="${message.icon}" title="${message.title}">
	                       		<img width="100px" src="${message.icon}" />
	                       	</a>
                       	</td>
                       	<td>${message.platform}</td>
                       	<td>
	                       	<c:forEach var="MessageRange" items="${MessageRangeMap}">
								<c:choose>
									<c:when test="${message.range == MessageRange.key}">
										${MessageRange.value}
									 </c:when>
								</c:choose>
							</c:forEach>
						</td>
                       	<td>
                       		<c:choose>
                       			<c:when test="${fn:length(message.formula) > 30}">
                       				<c:out value="${fn:substring(message.formula, 0, 30)}......" /> 
                       			</c:when>
                       			<c:otherwise>
                       				${message.formula}
                       			</c:otherwise>
                       		</c:choose>
                       	</td>
                       	<td>${message.starttime} --- ${message.endtime}</td>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/message_manager?actionmethod=showUpdateMessageJSP&pid=${message.pId}&page=${page}&platform=${platform}">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="#" onclick="delMessage('${message.pId}', '${message.title}')">删除</a>&nbsp;&nbsp;
                        	<c:choose>
		                 		<c:when test="${message.urlKey != ''}">
		                 		|&nbsp;&nbsp;
                        	<a href="<%=request.getContextPath()%>/ad/statistic?actionmethod=checkClickNum&title=${message.title}&id=${message.urlKey}">查看点击量</a>
	                   	</c:when>
	                   	</c:choose>
	                   	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/message_manager?actionmethod=showMessageList&platform=${platform}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>