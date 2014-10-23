<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>热门关键词列表</title>
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
 
	    function goto_del_item_ab()
		{
	    	eval("opt_success.style.display=\"none\"");
	    	var item_ids = $("#item_ids").val();
	    	item_ids = trimString(item_ids);
	    	
	    	$(".idcheck").each(function() 
   			{
   				if ($(this).attr("checked")) 
   				{
   					var checkedID = $(this).val();
   					if (checkedID !=null && checkedID != "")
   					{
   						if (item_ids != null && item_ids != "")
   						{
   							item_ids = item_ids + "," + checkedID;
   						}
   						else
   						{
   							item_ids = checkedID;
   						}
   					}
   				}
	        });
	    	if (item_ids == null || item_ids == "")
	    	{
	    		alert("您尚未选择要删除的商品!");
				return false;
	    	}
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=delTagItemAB',
    		    type: 'POST',
    		    data: {
    		    			item_ids:item_ids
    		    },
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		eval("opt_success.style.display=\"\"");
    		    		item_idsArr = item_ids.split(",");
    		    		for(var i=0;i<item_idsArr.length;i++)
   		    			{
   		    				var tr = document.getElementById("tr"+item_idsArr[i]);
   		    				tr.parentNode.removeChild(tr);
   		    				//tr.style.display="none";
   		    			}
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("删除操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
		}
	    
	function delKeyword(id){
		if(confirm("确定删除该关键词？")){
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/hot_keyword_manager?actionmethod=delHotKeyword',
    		    type: 'POST',
    		    data: {
    		    			id:id
    		    },
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		location.href="<%=request.getContextPath()%>/ad/hot_keyword_manager?actionmethod=showHotKeywordList";
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("删除操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
		}
	}
	function goto_add_hot_keyword()
	{
    	location.href="<%=request.getContextPath()%>/ad/hot_keyword_manager?actionmethod=showAddHotKeyword";
	}
	

    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h3 class="entry-title">关键词列表列表</h3>
	         <hr/>
	         <form action="<%=request.getContextPath()%>/ad/hot_keyword_manager?actionmethod=" method="post" >
		   <input type="button" value="新增" onclick="goto_add_hot_keyword()">
		   <!-- 
		         关键词:<input type="text" id="item_ids" name="item_ids" value="">
				<input type="button" value="搜索">
		    -->      
				
	        </form>

		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th>ID</th>
	    				<th>关键词</th>
	    			    <th>更新时间</th>
	    			    <!-- 
	    				<th>排序</th>
	    			     -->
	    			    <th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody id="tablebd">
	    			<c:forEach var="item" items="${list}"  varStatus="status">
	                 <tr>
	                 	<td>${item.id}</td>
	                 	<td>${item.keyword}</td>
	                 	<td>${item.timestampStr}</td>
	                 	<td>
	                 		<a href="javascript:delKeyword('${item.id}');" >删除</a>
	                 	</td>
	                </tr>
                   </c:forEach>
	    		</tbody>
	    	</table>
	    	<hr/>
	    	<hr/>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&parent=${parent}&tag_id=${tag_id}&tag_name=${tag_name}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	<script type="text/javascript">
	
	function dialog(id) {
		var val = window.showModalDialog("<%=request.getContextPath()%>/admin/tag_manager/tag_item_update.jsp?id="+id, 
		window, "dialogWidth:800px;status:no;dialogHeight:400px");
		
		if(!val){ 
			val= window.ReturnValue;
		} 
	}

	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>