<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商品混排列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <script type="text/javascript">
	    	    
	    function add_new_item()
	    {
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addMixStyleItemList&tagid=${tagid}&keyword=${keyword}";			
	    }
	    
	    function del_item(id,tag_id)
	    {
	    	var item_ids = $("#"+id).val();

	    	if(item_ids == ""||id==""||tag_id==""){
	    		alert("删除失败！");
	    		return false;
	        }else{
	            if(confirm("你确定要删除这个商品吗？")){
	            	$.ajax({
	        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=delMixStyleItem',
	        		    type: 'POST',
	        		    data: {
	        		    			pids:item_ids,
	        		    			tagid:tag_id,
	        		    			id:id,
	        		    			},
	        		    dataType: 'json',
	        		    timeout: 1000,
	        		    success: function(json)
	        		    {
	        		    	var result = json.result;
	        		    	if (result == "0")
	       		    		{
	        		    		var tr = document.getElementById("tr"+id);
    		    				tr.style.display="none";
	        		    		return true;
	        		    	//	var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=getMixStyleItemList&tag_id=${tagid}&page=${page}&keyword=${keyword}";
	        				//	location.href=url;
	       		    		}
	        		    	else if (result == "1")
	        		    	{
	        		    		alert("删除操作失败!");
	        		    		return false;
	        		    	}
	        		    }
	        		});
	            	} else {
	    				return false;
	    			}
	    		}
	    	
		}
	    
	    function move_position(type,id,position)
	    {
	    	var tag_id = ${tagid}; 
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=MixStyleItemMovePosition',
    		    type: 'POST',
    		    data: {
    		    			tag_id:tag_id,
    		    			movetype:type,
    		    			id:id,
    		    			position:position,
    		    			},
    		    dataType: 'json',
    		    timeout: 1000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=getMixStyleItemList&tagid=${tagid}&page=${page}&keyword=${keyword}";
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
	    
	    function refresh()
	    {
	    	var tag_id = ${tagid}; 
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=ReloadMixStyleItem',
    		    type: 'POST',
    		    data: {
    		    			tagid:tag_id,
    		    			},
    		    dataType: 'json',
    		    timeout: 1000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		alert("刷新缓存成功!");
    		    		return true;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("刷新缓存失败!");
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
	         <h1 class="entry-title">《${keyword}》商品列表</h1>
	         <hr/>
	         <form action="#" method="post" >
		         标签:<input type="text" id="item_ids" name="item_ids" value="">
		         <input type="button" class="button" value="搜索" onclick="" />
		         <div  style="float: right">
			         <input type="button" class="button" value="新增商品页" onclick="add_new_item()" />
			         <input type="button" class="button" value="刷新缓存" onclick="refresh()" />
			     </div>
	        </form>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th style="width: 5%"><input type="checkbox" id="check_all" name="check_all" /></th>
	    			    <th style="width: 5%">标签ID</th>
	    				<th style="width: 5%">模板ID</th>
	    				<th style="width: 20%;">商品集合</th>
	    				<th style="width: 20%;">商品图片</th>
	    				<th style="width: 110px;">调整顺序</th>
	    				<th style="width: 10%">操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach var="item" items="${ItemList}">
	                 <tr id="tr${item.id}">
	                 	<td><input type="checkbox" class="idcheck" value="" /></td>
	                 	<td>${item.tag_id}</td>
                       	<td>${item.typeid}</td>
                       	<td>${item.item_ids}</td>
                       	<td>
                       	<c:forEach var="tjbean" items="${item.tjGoodsBeanList}">
                       		<img alt="${tjbean.title}" title="${tjbean.title}" src="${tjbean.pic_url}_60x60.jpg" style="float: left;border: 1px solid #EE1196;">				
                       	</c:forEach>
                       	</td>
                       	<td width="110px;">
							<img style="width: 25px;" alt="上移一位" title="上移一位" src="<%=request.getContextPath() %>/img/moveup.png" onclick="move_position(1,${item.id},${item.rank})">
							<img style="width: 25px;" alt="下移一位" title="下移一位" src="<%=request.getContextPath() %>/img/movedown.png" onclick="move_position(2,${item.id},${item.rank})">
							<img style="width: 25px;" alt="移至首位" title="移至首位" src="<%=request.getContextPath() %>/img/movetop.png" onclick="move_position(3,${item.id},${item.rank})">
							<img style="width: 25px;" alt="移至末位" title="移至末位" src="<%=request.getContextPath() %>/img/movelast.png" onclick="move_position(4,${item.id},${item.rank})'">
						</td>
                       	<td>
                       	<input type="hidden" id="${item.id}" name="pids" value="${item.item_ids}" >
                       		<a href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=updateMixStyleItemList&id=${item.id}&pids=${item.item_ids}&tagid=${item.tag_id}&keyword=${keyword}">修改</a>
                       		<a href="javascript:del_item(${item.id},${item.tag_id});" >删除</a>
                       	</td>
	                </tr>
                   </c:forEach>
	    		</tbody>
	    	</table>
	    	
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=getMixStyleItemList&tagid=${tagid}&keyword=${keyword}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>