<%@page import="com.netting.util.CodeUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>广告列表-随手4.0</title>
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
	    	location.href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&platform=${platform}&status=" + status;
		}
	    
	    function platformSelect(platform)
		{
	    	location.href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&status=${status}&platform=" + platform;
		}
    
    	function delAD(ad_id, ad_title)
    	{
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/ad_manager?actionmethod=delAD',
    		    type: 'POST',
    		    data: {
    		    	ad_id:ad_id,
    		    	ad_title:ad_title
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var delElement = document.getElementById(ad_id);
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
    	
    	function goto_add_ad()
		{
	    	location.href="<%=request.getContextPath()%>/ad/Admin_Ads_Manager_Action_New?actionmethod=updateAdsPage";
		}
    	
    	function goto_ipad_ad()
		{
	    	location.href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=show_IPAD_AD_List";
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
    	
    	function getLocalTime(nS) {     
    		   return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");      
    		}
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">广告列表</h1>
	         <hr/>
	         <form action="" method="post" >
	         <!-- 
		         关键词:<input type="text" name="keyword" value="">
		         <input type="submit" class="button" value="搜索" />
	          -->
		         <input type="button" class="button" value="新增" onclick="goto_add_ad()" />		     
		         <div  style="float: right">
			         <div style="float: left">

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
	    				<th>更新时间</th>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="ad_bean" items="${list}">
	                 <tr id="${ad_bean.id}">
	                 	<td>${ad_bean.id}</td>
	                 	<td>${ad_bean.title} </td>
						<td>
							<label class="media_photos">
		                       	<a rel="slide" target="_blank" href="${ad_bean.img}" title="${ad_bean.title}">
		                       		<img width="100px" src="${ad_bean.img}" />
		                       	</a>
		                    </label>
                       	</td>
                       	<td>
							<c:forEach var="ActionType" items="${ActionTypeMap}">
								<c:choose>
									<c:when test="${ad_bean.actionType == ActionType.key}">
										${ActionType.value}
									 </c:when>
								</c:choose>
							</c:forEach>
						</td>
                       	<td>
                       	<%
                       		//out.print(CodeUtil.getDateTimeStr(${ad_bean.updateTime}));
                       	%>
                       	 ${ad_bean.updateDate}</td>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/Admin_Ads_Manager_Action_New?actionmethod=updateAdsPage&id=${ad_bean.id}">编辑</a>
                        	<a href="<%=request.getContextPath()%>/ad/Admin_Ads_Manager_Action_New?actionmethod=delAds&id=${ad_bean.id}" onclick="return confirm('确定删除？');">删除</a>
	                   	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/Admin_Ads_Manager_Action_New?actionmethod=showAdsList" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>