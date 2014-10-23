<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>编辑广告-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
     <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    <script type="text/javascript">
    	// 上传默认图标
	    function add_Img_Name(ImgName)
		{
		    var filename = $("#ad_img_File").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#img").val(url);
		    $("#upload_ad_Img_Form").submit();
		}
    	
	 	
	
	    
		// 提交时检查参数
	    function checkParam()
		{
			
			var ad_id = $("#ad_id").val();
			
			var title = $("#title").val();
			if (title == null || title == "")
			{
				alert("请填写<标题>!");
				return false;
			}
				
			var action_type = $("#action_type").val();
			var action_value = getActionValue(action_type);
			
			if (action_value == null || action_value == "")
			{
				alert("请填写<展示内容>!");
				return false;
			}
			
			var img = $("#img").val();
			if (img == null || img == "")
			{
				alert("请填写<图标>!");
				return false;
			}
			

			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/Admin_AdUp_Manager_Action?actionmethod=updateAdUp',
    		    type: 'POST',
    		    data: {
    		    	id:ad_id,
    		    	title:title,
					action_type:action_type,
					action_value:action_value,
					img:img
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/Admin_AdUp_Manager_Action?actionmethod=showAdUpList";
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
		
		function cancel()
		{
			var url = "<%=request.getContextPath()%>/ad/ad_manager?actionmethod=showADList&page=${page}&status=${status}&platform=${platform}";
			location.href=url;
		}
		
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">	
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
         
		   <div id="post-8" class="post-8 page type-page status-publish hentry">
	         <h2 class="entry-title">编辑广告</h2>
				<div id="submit-content">
                <div id="formbox">
                		<input type="hidden" id="ad_id" name="ad_id" value="${ADBean.id}" />
                		<input type="hidden" id="rank" name="rank" value="${ADBean.rank}" />
                		<p>
                             <label><font color="#777">标题：</font></label>
                             <input type="text" name="title" id="title" value="${ADBean.title}" />
                        </p>                     	
						<p>
							<label><font color="#777">展示样式：</font></label>
							<select id="action_type" name="action_type" onchange="actionTypeChange_1()">
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<c:choose>
										<c:when test="${ADBean.actionType == ActionType.key}">
											<option value="${ActionType.key}" selected>${ActionType.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${ActionType.key}">${ActionType.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">展示内容：</font></label>
							<input type="text" name="action_value" id="action_value" value="${ADBean.actionValue}" />
							<select id="xitongyemian" name="xitongyemian"  style="display: none;">
								<c:forEach var="XTYM" items="${XTYM_Map}">
									<c:choose>
										<c:when test="${ADBean.actionValue == XTYM.key}">
											<option value="${XTYM.key}" selected>${XTYM.value}</option>
										 </c:when>
										 <c:otherwise>
										 	<option value="${XTYM.key}">${XTYM.value}</option>
										 </c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
							<select id="tagactionvalue" name="tagactionvalue"  style="display: none;">
								<c:forEach var="tag_map" items="${Tag_Type_Value_Map}">
									<c:forEach var="tag" items="${tag_map}">
										<c:choose>
											<c:when test="${ADBean.actionValue == tag.key}">
												<option value="${tag.key}" selected>${tag.value}</option>
											 </c:when>
											 <c:otherwise>
											 	<option value="${tag.key}">${tag.value}</option>
											 </c:otherwise>
										</c:choose>
									</c:forEach>
								</c:forEach>
							</select>
						</p>
						<p>
							
							<label><font color="#777">图140*320：</font></label>
	
							<input type="text" name="img" id="img"  value="${ADBean.img}" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：140*320</font>
						</p>
						<%
						    String imgName_140_320 = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_ad_Img_Form" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=imgName_140_320%>" target="ImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input type="hidden" name="version" value="adImg" >
						    <input type="hidden" name="fileName" value="<%=imgName_140_320%>">
						    <input id="ad_img_File" name="file" type="file" />
						    <input type="button" onclick="add_Img_Name('<%=imgName_140_320%>')" value="上传"  class="button"/>
						</form>
						<iframe name="ImgUploadIfr" style="display:none" ></iframe>
						
						<br/>
						
						<p>
							<input id="submit" type="button" class="button" onclick="checkParam()" value="提交" />
							<input id="cancel" type="button" class="button" onclick="cancel()" value="取消" />
						</p>
                  </div>
                </div>
		</div>
		
	  </div>
	 </div>
	</div>
	<script type="text/javascript">
	
	showActionValue('${ADBean.actionType}');
	
	

	
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>