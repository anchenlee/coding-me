<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商品混排样式列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    <!-- 
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js"></script><!--谷歌jquery包-->

    
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/controller.js"></script><!--导入js-->
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mixstyle.css"/><!--导入css-->
    <script type="text/javascript">
	    	    
	    function add_new_item()
	    {
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addMixStyleItemList&tagid=${tagid}&keyword=${keyword}";			
	    }
	    
	    function goto_mix_list()
	    {
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addMixStyleItemListNew&tagid=${tagid}&keyword=${keyword}";
		}
	    
	    function updateMix(id){
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=updateMixStyleItemPage&id="+id;

	    }
	    
	    function delMix(id){
	    	if(confirm("确定删除?")){
	    	$.ajax(
	            	{
	        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=delMixItem',
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
	        		    		location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showMixItemPage&tagid=${tagid}&keyword=${keyword}";
	        		    		return ;
	       		    		}
	        		    	else if (result == "1")
	        		    	{
	        		    		alert("操作失败!");
	        		    		return false;
	        		    	}
	        		    },error: function(XMLHttpRequest, textStatus, errorThrown)
	        		    {
	        		    	alert("操作失败!");
	        		    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showMixItemPage&tagid=${tagid}&keyword=${keyword}";
        		    		return ;
	        		    }
	        		});
	    }
	    } 
	    function tijiao(){
	    	var width = $("#widthth").val();
	    	var height = $("#heightth").val();
	    	if(width == '' || height == ''){
	    		alert("长宽比例不能为空");
	    		return;
	    	}
	    	$("#width").val(width);
	    	$("#height").val(height);
	    	$("#addMixForm").submit();
	    }

	    function getPageItemList(){
	    	
	    }
	    
	    function baocun(){
	    	alert(document.getElementById("addMixForm").innerHTML);
	    }
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
	
	 

	 
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">混排样式列表
	         </h1>
	         <input type="button" class="button" value="新增混合样式" onclick="goto_mix_list()" />
	         <hr/>
<div id="content">
<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th style="width: 10%">id</th>
	    				<th style="width: 20%">标题</th>
	    				<th style="width: 50%">样式</th>
	    				<th style="width: 20%">操作</th>
	    			    </tr>
	    			    </thead>
	    			    <tbody id="tablebd">
	    			    <c:forEach var="item" items="${list}"  varStatus="status">
	    			    <tr>
	    			    <td>${item.id}</td>
	    			    <td>${item.title}</td>
	    			    <td>
	    			    	${item.content}
						</td>
						<td>
						<c:if test="${item.jsonData != '' &&  item.jsonData != null}">
						<a href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=updateMixStyleItemPage&id=${item.id}&tagid=${tagid}&keyword=${keyword}">编辑</a>
						</c:if>
						<a href="javascript:delMix('${item.id}');" >删除</a>
						</td>
	    			    </tr>
	    			    </c:forEach>
	    			    </tbody>
	    			    </table>
	      	<div class="" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showMixItemPage&tagid=${tagid}&keyword=${keyword}" method="post"  >
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
</div>
		
		</div>
		 
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>