<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.Sale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增IPAD广告-随手4.0</title>
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
	    	
	    	var _row = "${_row}";
	    	var _col = "${_col}";
	    	var _width = "${_width}";
	    	var _height = "${_height}";
	    	var _classnames = "${_classnames}";
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/ad_manager?actionmethod=add_IPAD_AD',
    		    type: 'POST',
    		    data: {
    		    	ad_ids:ad_ids,
    		    	bgcolor:bgcolor,
    		    	ipad_ad_style:ipad_ad_style,
    		    	_row:_row,
    		    	_col:_col,
    		    	_width:_width,
    		    	_height:_height,
    		    	_classnames:_classnames
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
	    
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">新增IPAD广告</h1>
	         <hr/>
	         背景颜色:
	         <input type="text" name="bgcolor" id="bgcolor" value="" />
	         样式:
	         <select id="ipad_ad_style" name="ipad_ad_style">
				<c:forEach var="ad_style" items="${IPAD_AD_STYLE_MAP}">
					<option value="${ad_style.key}">${ad_style.value}</option>
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