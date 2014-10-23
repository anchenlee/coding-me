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
	    	location.href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showAddADJSP&page=${page}&status=${status}&platform=${platform}";
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
    	
    	function checkClickNum(){
    		var url = $("#checkAddress").val();
    		if(url == ''){
    			alert("链接地址不能为空！");
    			return;
    		}
    		var key = url.replace("http://b17.cn/","").replace(/\s+/g,"");    
    		location.href="<%=request.getContextPath()%>/ad/statistic?actionmethod=checkClickNum&title=转换地址&id="+key;   		
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
	         <form action="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&status=${status}&platform=${platform}" method="post" >
		         关键词:<input type="text" name="keyword" value="">
		         <input type="submit" class="button" value="搜索" />
		         <input type="button" class="button" value="新增" onclick="goto_add_ad()" />
		         <input type="button" class="button" value="IPAD广告" onclick="goto_ipad_ad()" />
		         <a href="<%=request.getContextPath()%>/admin/ad_manager/url_convert_dialog.jsp" class="model" title="链接转换">
					<input type=button value="链接转换">			  	
				 </a>
				 <a href="<%=request.getContextPath()%>/ad/Admin_AdUp_Manager_Action?actionmethod=showAdUpList">
					<input type=button value="首页">			  	
				 </a>
				 <input id="checkAddress" type="text"><input type="button" value="点击次数" onclick="checkClickNum()">
		         <div  style="float: right">
			         <div style="float: left">
				          状态:<select id="status" onChange="statusSelect(this.value)">
							<c:forEach var="Status" items="${ADStatusMap}">
								<c:choose>
									<c:when test="${status == Status.key}">
										<option value="${Status.key}" selected>${Status.value}</option>
									 </c:when>
									 <c:otherwise>
									 	<option value="${Status.key}">${Status.value}</option>
									 </c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div style="float: right">
					平台类型:<select id="platform" onChange="platformSelect(this.value)">
						<c:forEach var="Platform" items="${ADPlatformMap}">
							<c:choose>
								<c:when test="${platform == Platform.key}">
									<option value="${Platform.key}" selected>${Platform.value}</option>
								 </c:when>
								 <c:otherwise>
								 	<option value="${Platform.key}">${Platform.value}</option>
								 </c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
						<c:when test="${platform == 'quanbu'}">
							<option value="" selected>全部</option>
						</c:when>
						<c:otherwise>
							<option value="quanbu" >全部</option>
						</c:otherwise>
						</c:choose>
					</select>
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
	    				<th>平台</th>
	    				<th>有效期</th>
	    				<th>更新时间</th>
	    				<c:choose>
	    				<c:when test="${status==2 && platform!=''}">
	    				<th>排序</th>	    				
	    				</c:when>
	    				</c:choose>
	    				<th>操作</th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<c:forEach var="ad_bean" items="${ADList}">
	                 <tr id="${ad_bean.id}">
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
                       	<td>${ad_bean.platform}</td>
                       	<td>${ad_bean.start_time} --- ${ad_bean.end_time}</td>
                       	<td>${ad_bean.updatetime}</td>
                       	<c:choose>
                       	<c:when test="${status==2 && platform!=''}">
                       	
                       	<td width="110px;">
							<img style="width: 25px;" alt="上移一位" title="上移一位" src="<%=request.getContextPath() %>/img/moveup.png" onclick="movePosition(${ad_bean.id},1,${ad_bean.rank})">
							<img style="width: 25px;" alt="下移一位" title="下移一位" src="<%=request.getContextPath() %>/img/movedown.png" onclick="movePosition(${ad_bean.id},2,${ad_bean.rank})">
							<img style="width: 25px;" alt="移至首位" title="移至首位" src="<%=request.getContextPath() %>/img/movetop.png" onclick="movePosition(${ad_bean.id},3,${ad_bean.rank})">
							<img style="width: 25px;" alt="移至末位" title="移至末位" src="<%=request.getContextPath() %>/img/movelast.png" onclick="movePosition(${ad_bean.id},4,${ad_bean.rank})">
						</td>
						</c:when>
                       	</c:choose>
	                   	<td>
							<a href="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showUpdateADJSP&ad_id=${ad_bean.id}&page=${page}&status=${status}&platform=${platform}">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        	<a href="#" onclick="delAD('${ad_bean.id}', '${ad_bean.title}')">删除</a>&nbsp;&nbsp;
                        	<c:choose>
		                 		<c:when test="${ad_bean.urlkey != ''}">
		                 		|&nbsp;&nbsp;
                        	<a href="<%=request.getContextPath()%>/ad/statistic?actionmethod=checkClickNum&title=${ad_bean.title}&id=${ad_bean.urlkey}">查看点击量</a>
	                   	</c:when>
	                   	</c:choose>
                        
	                   	</td>
	                </tr>
	                </c:forEach>
	    		</tbody>
	    	</table>
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&status=${status}&platform=${platform}" method="post">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>