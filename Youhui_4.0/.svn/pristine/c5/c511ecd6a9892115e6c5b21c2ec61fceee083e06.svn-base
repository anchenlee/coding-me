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
    	function goto_hot_searchkeyword()
		{
	    	location.href="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showHotSearchKeywordsList";
		}
    	function goto_hot_keyword()
		{
	    	location.href="<%=request.getContextPath()%>/ad/hot_keyword_manager?actionmethod=showHotKeywordList";
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
	         <h1 class="entry-title">搜索关键字列表</h1>
	         <hr/>
	         <c:choose>
				<c:when test="${parent_id != '0'}">
					<input type="submit" class="button" value="返回上一级"  onclick="goto_last_level_list()" />
				 </c:when>
			</c:choose>
	         <input type="button" class="button" value="新增关键字" onclick="goto_add_searchkeyword()" />
	         <input type="button" class="button" value="热门搜索关键词"  onclick="goto_hot_searchkeyword()">
	         <input type="button" class="button" value="添加热门关键词"  onclick="goto_hot_keyword()">
	         <div  style="float: right">
		         <div style="float: left">
		          状态:<select id="status" name="status" onChange="statusSelect(this.value)">
						<c:forEach var="SearchKeywordStatus" items="${SearchKeywordStatusMap}">
							<c:choose>
								<c:when test="${status == SearchKeywordStatus.key}">
									<option value="${SearchKeywordStatus.key}" selected>${SearchKeywordStatus.value}</option>
								 </c:when>
								 <c:otherwise>
								 	<option value="${SearchKeywordStatus.key}">${SearchKeywordStatus.value}</option>
								 </c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th>ID</th>
	    			    <th>图片</th>
	    				<th>标题</th>
	    				<th>排序</th>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="search_keyword_bean" items="${SearchKeywordList}">
	                 <tr id="${search_keyword_bean.id}">
	                 	<td>${search_keyword_bean.id}</td>
	                 	<td>
	                       	<a rel="slide" target="_blank" href="${search_keyword_bean.icon}" title="${search_keyword_bean.name}">
	                       		<img width="100px"  src="${search_keyword_bean.icon}" />
	                       	</a>
                       	</td>
	                 	<td>
	                 		<c:choose>
		                 		<c:when test="${search_keyword_bean.action_type == 'tagStyleWeb'}">
									<a rel='slide' target='_blank' href='${search_keyword_bean.action_value}' style="color: #1F92FF">${search_keyword_bean.name}</a>
								</c:when>
								<c:otherwise>
									${search_keyword_bean.name}
								</c:otherwise>
							</c:choose>
                       	</td>
                       	<td width="110px;">
							<img style="width: 25px;" alt="上移一位" title="上移一位" src="<%=request.getContextPath() %>/img/moveup.png" onclick="movePosition(${search_keyword_bean.id},${parent_id},1,${search_keyword_bean.rank})">
							<img style="width: 25px;" alt="下移一位" title="下移一位" src="<%=request.getContextPath() %>/img/movedown.png" onclick="movePosition(${search_keyword_bean.id},${parent_id},2,${search_keyword_bean.rank})">
							<img style="width: 25px;" alt="移至首位" title="移至首位" src="<%=request.getContextPath() %>/img/movetop.png" onclick="movePosition(${search_keyword_bean.id},${parent_id},3,${search_keyword_bean.rank})">
							<img style="width: 25px;" alt="移至末位" title="移至末位" src="<%=request.getContextPath() %>/img/movelast.png" onclick="movePosition(${search_keyword_bean.id},${parent_id},4,${search_keyword_bean.rank})">
						</td>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showSearchKeywordList&parent_id=${search_keyword_bean.id}&version=1">查看子标签</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="#" onclick="goto_update_searchkeyword(${search_keyword_bean.id})">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="#" onclick="delSearchKeyword('${search_keyword_bean.id}', '${search_keyword_bean.name}')">删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<c:choose>
                        		<c:when test="${status == '0'}">
                        			<a href="#" onclick="updateSearchKeywordStatus('${search_keyword_bean.id}', '${search_keyword_bean.name}', 1)">隐藏</a>&nbsp;&nbsp;
                        		</c:when>
                        		<c:when test="${status == '1'}">
                        			<a href="#" onclick="updateSearchKeywordStatus('${search_keyword_bean.id}', '${search_keyword_bean.name}', 0)">启用</a>&nbsp;&nbsp;
                        		</c:when>
                        	</c:choose>
	                   	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showSearchKeywordList&parent_id=${parent_id}&status=${status}&version=1" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>