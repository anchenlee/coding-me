<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>标签列表-随手4.0</title>
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
	    
	    function goto_pad_tag()
		{
	    	location.href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&parent=${parent}&status=${status}&ispad=1";
		}
	    
	    function goto_hot_item()
		{
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTopTradeItemList";
		}
	    
	    function goto_add_tag(ispadtag)
		{
	    	location.href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showAddTagJSP&page=${page}&parent=${parent}&status=${status}&ispad=${ispad}";
		}
	    
	    function movePosition(id,position,movetype)
	    {
	    	var parent = "${parent}";
	    	var ispad = "${ispad}";
	    	var status = "${status}";
	    	$.ajax({
     		    url: '<%=request.getContextPath()%>/ad/tag_manager?actionmethod=MovePosition',
     		    type: 'POST',
     		    data: {			
     		    			id:id,
     		    			position:position,
     		    			movetype:movetype,
     		    			parent:parent,
     		    			status:status,
     		    			ispad:ispad
     		    			},
     		    dataType: 'json',
     		    timeout: 5000,
     		    success: function(json)
     		    {
     		    	var result = json.result;
     		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&page=${page}&parent=${parent}&status=${status}&ispad=${ispad}";
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
	         <h1 class="entry-title">特惠</h1>

		    <hr/>
<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th>标签ID</th>
	    				<th>标签名</th>
	    				<th>图片</th>
	    				<th>小条显示</th>
	    				<th>展示样式</th>
	    				<th>状态</th>
	    				<th>是否PAD标签</th>
	    				<c:choose>
		    				<c:when test="${status==1}">
		    					<th>排序</th>
		    				</c:when>
	    				</c:choose>				
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach var="tag" items="${tagList}">
	                 <tr>
	                 	<td>${tag.tag_id}</td>
                       	<td>${tag.tag_name}</td>
                       	<td>
							<c:choose>
								<c:when test="${ispad == 0}">
									<label class="media_photos">
				                       	<a rel="slide" target="_blank" href="${tag.phone_icon}" title="${tag.tag_name}">
				                       		<img width="100px" height="80px" src="${tag.phone_icon}">
				                       	</a>
				                    </label>
								 </c:when>
								 <c:otherwise>
								 	<label class="media_photos">
				                       	<a rel="slide" target="_blank" href="${tag.pad_icon}" title="${tag.tag_name}">
				                       		<img width="100px" height="80px" src="${tag.pad_icon}">
				                       	</a>
				                    </label>
								 </c:otherwise>
							</c:choose>
						</td>
                       	<td>
	                       	<c:forEach var="smallshow" items="${TagSmallShowMap}">
								<c:choose>
									<c:when test="${tag.small_show == smallshow.key}">
										${smallshow.value}
									 </c:when>
								</c:choose>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="ActionType" items="${ActionTypeMap}">
								<c:choose>
									<c:when test="${tag.tag_action.actionType == ActionType.key}">
										${ActionType.value}
									 </c:when>
								</c:choose>
							</c:forEach>
						</td>
						<td>
	                       	<c:forEach var="Status" items="${TagStatusMap}">
								<c:choose>
									<c:when test="${tag.status == Status.key}">
										${Status.value}
									 </c:when>
								</c:choose>
							</c:forEach>
						</td>
						<td>
							<c:choose>
								<c:when test="${tag.isPadTag == '1'}">
									是
								 </c:when>
								 <c:otherwise>
								 	否
								 </c:otherwise>
							</c:choose>
						</td>
	    				<c:choose>
	    				<c:when test="${status==1}">
						<td width="110px;">
                       	<a onclick="movePosition(${tag.tag_id},${tag.rank},1)">
							<img style="width: 25px;" alt="上移一位" title="上移一位" src="<%=request.getContextPath() %>/img/moveup.png" onclick="location.href='#'">
                       	</a>
						<a onclick="movePosition(${tag.tag_id},${tag.rank},2)">
							<img style="width: 25px;" alt="下移一位" title="下移一位" src="<%=request.getContextPath() %>/img/movedown.png" onclick="location.href='#'">
						</a>
						<a onclick="movePosition(${tag.tag_id},${tag.rank},3)">
							<img style="width: 25px;" alt="移至首位" title="移至首位" src="<%=request.getContextPath() %>/img/movetop.png" onclick="location.href='#'">
						</a>
						<a onclick="movePosition(${tag.tag_id},${tag.rank},4)">
							<img style="width: 25px;" alt="移至末位" title="移至末位" src="<%=request.getContextPath() %>/img/movelast.png" onclick="location.href='#'">
						</a>
						</td>
						</c:when>
						</c:choose>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/tehui?actionmethod=showUpdateTagJSP&page=${page}&parent=${parent}&status=${status}&ispad=${ispad}&tag_id=${tag.tag_id}">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="<%=request.getContextPath()%>/ad/tehui?actionmethod=showTagListToAdmin&parent=${tag.tag_id}&ispad=0">子标签管理</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag.tag_id}&tag_name=${tag.tag_name}">商品管理</a>
                        	<a href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagIcoList&tag_id=${tag.tag_id}&tag_name=${tag.tag_name}">&nbsp;&nbsp;|&nbsp;&nbsp;图片管理</a>
                        	
	                   	</td>
	                </tr>
                   </c:forEach>
	    		</tbody>
	    	</table>
	    	
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&parent=${parent}&status=${status}&ispad=${ispad}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>