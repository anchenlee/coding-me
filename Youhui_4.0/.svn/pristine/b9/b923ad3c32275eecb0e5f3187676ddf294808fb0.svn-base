<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>标签图片列表列表-随手4.0</title>
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
	    	location.href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&parent=${parent}&ispad=${ispad}&status=" + status;
		}
	    
	    function goto_tag()
		{
	    	location.href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin";
		}
	    
	    function goto_hot_item()
		{
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTopTradeItemList";
		}
	    
	    function goto_add_tag()
		{
	    	var tag_id = $("#tag_id").val();
	    	var tag_name = $("#tag_name").val();
	    	location.href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showAddTagIconJSP&tag_id="+tag_id+"&tag_name="+tag_name;
		}
	    
	    function delTag(id)
	    {
	    	if(!confirm("确定删除？")){
	    		return ;
	    	}
	    	$.ajax({
     		    url: '<%=request.getContextPath()%>/ad/tag_manager?actionmethod=delTagIcon',
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
    		    		var url = "<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagIcoList&tag_id=${tag_id}&tag_name=${ tag_name}";
    					location.href=url;
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
	         <h1 class="entry-title">${tag_name}图片列表</h1>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&parent=${parent}&status=${status}&ispad=${ispad}" method="post" >
		         <input type="button" class="button" value="标签列表" onclick="goto_tag()" />
		         <input type="hidden" id="tag_id" name="tag_id" value="${tag_id}">
                <input type="hidden" id="tag_name" name="tag_name" value="${ tag_name}">
		         <input type="button" class="button" value="新增图片" onclick="goto_add_tag()" />
	        </form>
		    <hr/>
		    <c:if test="${headTagList_1 != null || fn:length(headTagList_1) > 0}">
			    <c:forEach var="tag_map" items="${headTagList_1}">
				    <c:forEach var="tag" items="${tag_map}">
						<a href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagIcoList&tag_id=${tag.key}&tag_name=${tag.value}" style="color: #1F92FF">${tag.value}</a>&nbsp;|&nbsp;
					</c:forEach>
				</c:forEach>
		    </c:if>
		    <hr/>
		    <c:if test="${headTagList_2 != null || fn:length(headTagList_2) > 0}">
			    <c:forEach var="tag_map_2" items="${headTagList_2}">
				    <c:forEach var="tag_2" items="${tag_map_2}">
						<a href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagIcoList&tag_id=${tag_2.key}&tag_name=${tag_2.value}" style="color: #1F92FF">${tag_2.value}</a>&nbsp;|&nbsp;
					</c:forEach>
				</c:forEach>
			</c:if>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th>标签ID</th>
	    				<th>图片</th>
	    				<th>开始时间</th>
	    				<th>结束时间</th>		
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach var="tag" items="${tagList}">
	                 <tr>
	                 	<td>${tag.tagId}</td>
                       	<td>
                       		<img alt="" width="100px" height="80px" src="${tag.icon}">
                       	</td>
                       	<td>${tag.beginTime}</td>
                       	<td>${tag.endTime}</td>
                       	<td> 
                       	<a href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showAddTagIconJSP&id=${ tag.id}&tag_id=${tag_id}&tag_name=${tag_name}">编辑</a>
                       	<a href="" onclick="delTag('${tag.id}')">删除</a>
                       </td>
	                </tr>
                   </c:forEach>
	    		</tbody>
	    	</table>
	    	<!-- 
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&parent=${parent}&status=${status}&ispad=${ispad}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
	    	 -->
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>