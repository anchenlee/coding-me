<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@page import="com.netting.bean.IPAD_Ad_Bean"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>IPAD广告列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
  
<link href="<%=request.getContextPath()%>/js/fancybox/jquery.fancybox-1.3.0.css" rel="stylesheet"
	type="text/css" media="all">

<style>
	#sortable {
		list-style-type: none;
		margin: 0;
		padding: 0;
		width: 100%;
	}

	#sortable li {
		margin: 0 3px 3px 3px;
		padding: 2em;
		font-size: 1.2em;
		width: 100px;
		float: left;
		display: inline;
	}

	#sortable li.s00,#sortable li.s01,#sortable li.s02,#sortable li.s03,#sortable li.s10,#sortable li.s11,
	#sortable li.s12,#sortable li.s13,#sortable li.s20,#sortable li.s21,#sortable li.s22,#sortable li.s23 {
		margin: 4px;
		padding: 4px;
		width: 23%;
		height: 80px;
		border: 2px solid #888;
	}

	#sortable li span {
		position: absolute;
		margin-left: -1.3em;
	}
</style>

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
$(document).ready(function()
{
	<%
	ArrayList<IPAD_Ad_Bean> ipad_ad_list = (ArrayList<IPAD_Ad_Bean>) request.getAttribute("IPAD_AD_LIST");
	for(int i = 0;i < ipad_ad_list.size(); i++) 
	{
		String arr[] = ipad_ad_list.get(i).getClassnames().split(",");
		for(int j = 0; j < arr.length; j++)
		{
		%>
			$(".<%=arr[j].trim()%>").html("<h1><%=ipad_ad_list.get(i).getId()%></h1>");
			$(".<%=arr[j].trim()%>").css("border","2px solid #0033FF");
		<%
		}
	}
	%>
 });
 
function startAdd()
{
	var _row = '';
	var _col = '';
	var _width = '';
	var _height = '';
	var _classnames = '';
    var ulDiv = document.getElementById("sortable");
    var tt = ulDiv.getElementsByTagName("li");
    for(var i = 0; i < tt.length; i++)
    {
    	if(tt[i].getAttribute("check") == '1')
    	{
        	_row = tt[i].className.substring(1,2);
        	_col = tt[i].className.substring(2,3);
        	//alert(_row);
        	//alert(_col);
        	break;
    	}
    }
    var tempheight = '';
    var tempwidth = '';
    for(var i = 0; i < tt.length; i++)
    {
    	if(tt[i].getAttribute("check")=='1') 
    	{
        	tempheight = tt[i].className.substring(1,2);
        	tempwidth = tt[i].className.substring(2,3);
        	_classnames += tt[i].className + ",";
    	}
    }
    _classnames = _classnames.substring(0, _classnames.length-1);
    _width = tempwidth - _col + 1;
    _height = tempheight - _row + 1;
    //alert(_width);
    //alert(_height);
    if(_row == '' || _col == '')
    {
    	alert('请选择填充区域');
    	return;
    }
    
    location.href = "<%=request.getContextPath()%>/ad/ad_manager?actionmethod=show_IPAD_AD_ADD_JSP&_row="+_row+"&_col="+_col+"&_width="+_width+"&_height="+_height + "&_classnames="+_classnames;
}

function setEdit(check)
{
	if($("."+check).html()==null||$("."+check).html().trim()=='')
	{
    	if($("."+check).attr("check")=="1") 
    	{
    		$("."+check).css("border","2px solid #888");
    		$("."+check).attr("check","0");//此域选择
    	} 
    	else 
    	{
    		$("."+check).css("border","2px solid #FF0000");//颜色标明
    		$("."+check).attr("check","1");//此域选择
    	}
	}
	else 
	{
		alert("该区域已编辑过，如需修改，请对下面列表操作");
		return;
	}
}

function del_IPAD_AD(ipad_ad_id)
{
	$.ajax({
	    url: '<%=request.getContextPath()%>/ad/ad_manager?actionmethod=del_IPAD_AD',
	    type: 'POST',
	    data: {
	    	ipad_ad_id:ipad_ad_id
	    },
	    dataType: 'json',
	    timeout: 5000,
	    success: function(json)
	    {
	    	var result = json.result;
	    	if (result == "0")
    		{
	    		location.href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=show_IPAD_AD_List";
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
	         <h1 class="entry-title">广告列表</h1>
	         <hr/>
	         <input type="button" class="button" value="编辑广告区域" onclick="startAdd()"/>
		    <hr/>
		    <span style="color: red;">
		    （选择好区域后点击按钮, 区域为蓝色且内部有数字，表示该区域已经填充，相同数字表示一个块，不允许操作，需要修改请对下方列表进行操作）
			</span>
			<hr/>
			<ul id="sortable">
		 		   <li class="s00" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s01" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s02" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s03" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s10" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s11" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s12" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s13" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s20" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s21" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s22" onclick="setEdit($(this).attr('class'));"> </li>
		 		   <li class="s23" onclick="setEdit($(this).attr('class'));"> </li>
			</ul>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    			    <th>区间</th>
	    				<th>广告ID集合</th>
	    				<th>样式</th>
	    				<th>背景色</th>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="ipad_ad_bean" items="${IPAD_AD_LIST}">
	                 <tr id="ipad_ad_${ipad_ad_bean.id}">
	                 	<td>${ipad_ad_bean.id}</td>
	                 	<td>${ipad_ad_bean.adids}</td>
						<td>
							<c:forEach var="ipad_ad_style" items="${IPAD_AD_STYLE_MAP}">
								<c:choose>
			                 		<c:when test="${ipad_ad_bean.style == ipad_ad_style.key}">
			                 			${ipad_ad_style.value}
		                   			</c:when>
		                   		</c:choose>
							</c:forEach>
						</td>
                       	<td>${ipad_ad_bean.bgcolor}</td>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=show_IPAD_AD_Update_JSP&ipad_ad_id=${ipad_ad_bean.id}">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="#" onclick="del_IPAD_AD('${ipad_ad_bean.id}')">删除</a>&nbsp;&nbsp;
	                   	</td>
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