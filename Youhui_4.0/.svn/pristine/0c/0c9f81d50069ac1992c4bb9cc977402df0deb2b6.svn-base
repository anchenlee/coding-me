<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>编辑搜索关键字-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    <script type="text/javascript">
    	// 上传图标
	    function add_Img_Name(ImgName)
		{
		    var filename = $("#img_File").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#img").val(url);
		    $("#upload_Img_Form").submit();
		}
    	
		// 提交时检查参数
	    function checkParam()
		{
			var name = $("#name").val();
			if (name == null || name == "")
			{
				alert("请填写<关键字>!");
				return false;
			}
			
			var action_type = $("#action_type").val();
			var action_value = getActionValue(action_type);
			
			var img = $("#img").val();
			if (img == null)
			{
				img = "";
			}
			
			var parent_id = "${parent_id}";
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=addSearchKeyword',
    		    type: 'POST',
    		    data: {
    		    	parent_id:parent_id,
    		    	name:name,
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
    					var url = "<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showSearchKeywordList&parent_id=${parent_id}&version=1";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("数据库操作失败!");
    		    		return false;
    		    	}
    		    	else if (result == "2")
    		    	{
    		    		alert("该级别下已存在与该名称相同的其他关键字,请重填!");
    		    		return false;
    		    	}
    		    	else if (result == "3")
    		    	{
    		    		alert("地址转换失败!");
    		    		return false;
    		    	}
    		    }
    		});
		}
		
		function cancel()
		{
			var url = "<%=request.getContextPath()%>/ad/search_keyword_manager?actionmethod=showSearchKeywordList&parent_id=${parent_id}&version=1";
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
	         <h2 class="entry-title">新增搜索关键字</h2>
				<div id="submit-content">
                <div id="formbox">
                		<p>
                             <label><font color="#777">关键字：</font></label>
                             <input type="text" name="name" id="name" value="" />
                        </p>
						<p>
							<label><font color="#777">展示样式：</font></label>
							<select id="action_type" name="action_type" onchange="actionTypeChange_1()">
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<c:choose>
										<c:when test="${ActionType.key == 'searchKeyword'}">
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
							<input type="text" name="action_value" id="action_value" value="" />
							<select id="xitongyemian" name="xitongyemian"  style="display: none;">
								<c:forEach var="XTYM" items="${XTYM_Map}">
									<option value="${XTYM.key}">${XTYM.value}</option>
								</c:forEach>
							</select>
							<select id="tagactionvalue" name="tagactionvalue"  style="display: none;">
								<c:forEach var="tag_map" items="${Tag_Type_Value_Map}">
									<c:forEach var="tag" items="${tag_map}">
										<option value="${tag.key}">${tag.value}</option>
									</c:forEach>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">图标：</font></label>
							<input type="text" name="img" id="img"  value="" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：X*X</font>
						</p>
						<%
						    String imgName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_Img_Form" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=imgName%>" target="ImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="img_File" name="file" type="file" />
						    <input type="button" onclick="add_Img_Name('<%=imgName%>')" value="上传"  class="button"/>
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
	
	
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>