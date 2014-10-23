<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>编辑IPAD广告-随手4.0</title>
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
	    function ChkAllClick(sonName, cbAllId)
	    {
	     	var cbAll = document.getElementById("chkAll");
	     	var tempState = cbAll.checked;
	     	
	     	var arrSon = document.getElementsByName("chkItem");
	    	for(var i = 0; i < arrSon.length; i++) 
	    	{
	      		if(arrSon[i].checked != tempState)
	      		{
	      			arrSon[i].click();
	      		}
	    	}
	    }
	    
	    function goto_save_ipad_ad()
	    {
	    	var ad_ids = "";
	    	$(".idcheck").each(function() 
    		{
    				if ($(this).attr("checked")) 
    				{
    					var checkedID = $(this).val();
    					if (checkedID !=null && checkedID != "")
    					{
    						if (ad_ids != null && ad_ids != "")
    						{
    							ad_ids = ad_ids + "," + checkedID;
    						}
    						else
    						{
    							ad_ids = checkedID;
    						}
    					}
    				}
   	        });
	    	
	    	var bgcolor = $("#bgcolor").val();
	    	var ipad_ad_style = $("#ipad_ad_style").val();
	    	var ipad_ad_id = "${IPAD_AD_BEAN.id}";
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/ad_manager?actionmethod=update_IPAD_AD',
    		    type: 'POST',
    		    data: {
    		    	ad_ids:ad_ids,
    		    	bgcolor:bgcolor,
    		    	ipad_ad_style:ipad_ad_style,
    		    	ipad_ad_id:ipad_ad_id
    		    },
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		location.href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=show_IPAD_AD_List";
    		    		return;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
	    }
	    
	    function movePosition(ad_id, position)
	    {
	    	var ipad_ad_id = "${IPAD_AD_BEAN.id}";
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/ad_manager?actionmethod=update_IPAD_AD_Rank',
    		    type: 'POST',
    		    data: {
    		    	ipad_ad_id:ipad_ad_id,
    		    	ad_id:ad_id,
    		    	position:position
    		    },
    		    dataType: 'json',
    		    cache: false,
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		location.href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=show_IPAD_AD_Update_JSP&ipad_ad_id=" + ipad_ad_id;
    		    		return;
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
	         <h1 class="entry-title">编辑IPAD广告</h1>
	         <hr/>
	         背景颜色:
	         <input type="text" name="bgcolor" id="bgcolor" value="${IPAD_AD_BEAN.bgcolor}" />
	         样式:
	         <select id="ipad_ad_style" name="ipad_ad_style">
				<c:forEach var="ad_style" items="${IPAD_AD_STYLE_MAP}">
					<c:choose>
						<c:when test="${IPAD_AD_BEAN.style == ad_style.key}">
							<option value="${ad_style.key}" selected>${ad_style.value}</option>
						 </c:when>
						 <c:otherwise>
						 	<option value="${ad_style.key}">${ad_style.value}</option>
						 </c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			<input type="button" class="button" value="保存" onclick="goto_save_ipad_ad()" />
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th style="width: 3%"><input name="chkAll" id="chkAll" type="checkbox" onclick="ChkAllClick()" /></th>
	    			    <th>ID</th>
	    				<th>标题</th>
	    				<th>图片</th>
	    				<th>有效期</th>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="ad_bean" items="${USED_IPAD_AD_LIST}">
		                 <tr id="${ad_bean.id}">
		                 	<td><input type="checkbox" class="idcheck" name="chkItem" value="${ad_bean.id}"  checked="checked"/></td>
		                 	<td>${ad_bean.id}</td>
		                 	<td>
		                 		<c:choose>
			                 		<c:when test="${ad_bean.type == 'url'}">
										<a rel='slide' target='_blank' href='${ad_bean.actionValue}' style="color: #1F92FF">${ad_bean.title}</a>
									</c:when>
									<c:otherwise>
										${ad_bean.title}
									</c:otherwise>
								</c:choose>
	                       	</td>
							<td>
							<label class="media_photos">
		                       	<a rel="slide" target="_blank" href="${ad_bean.img}" title="${ad_bean.title}">
		                       		<img width="100px" height="80px" src="${ad_bean.img}" />
		                       	</a>
		                    </label>
	                       	</td>
	                       	<td>${ad_bean.start_time} --- ${ad_bean.end_time}</td>
	                       	<td width="110px;">
								<img style="width: 25px;" alt="上移一位" title="上移一位" src="<%=request.getContextPath() %>/img/moveup.png" onclick="movePosition(${ad_bean.id},1)" />
								<img style="width: 25px;" alt="下移一位" title="下移一位" src="<%=request.getContextPath() %>/img/movedown.png" onclick="movePosition(${ad_bean.id},2)" />
								<img style="width: 25px;" alt="移至首位" title="移至首位" src="<%=request.getContextPath() %>/img/movetop.png" onclick="movePosition(${ad_bean.id},3)" />
								<img style="width: 25px;" alt="移至末位" title="移至末位" src="<%=request.getContextPath() %>/img/movelast.png" onclick="movePosition(${ad_bean.id},4)" />
							</td>
		                </tr>
	                </c:forEach>
	                <c:forEach var="ad_bean" items="${IPAD_AD_LIST}">
		                 <tr id="${ad_bean.id}">
		                 	<td><input type="checkbox" class="idcheck" name="chkItem" value="${ad_bean.id}" /></td>
		                 	<td>${ad_bean.id}</td>
		                 	<td>
		                 		<c:choose>
			                 		<c:when test="${ad_bean.type == 'url'}">
										<a rel='slide' target='_blank' href='${ad_bean.actionValue}' style="color: #1F92FF">${ad_bean.title}</a>
									</c:when>
									<c:otherwise>
										${ad_bean.title}
									</c:otherwise>
								</c:choose>
	                       	</td>
							<td>
							<label class="media_photos">
		                       	<a rel="slide" target="_blank" href="${ad_bean.img}" title="${ad_bean.title}">
		                       		<img width="100px" height="80px" src="${ad_bean.img}" />
		                       	</a>
		                    </label>
	                       	</td>
	                       	<td>${ad_bean.start_time} --- ${ad_bean.end_time}</td>
	                       	<td width="110px;">&nbsp;</td>
		                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>