<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>固定标签-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
        <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    
    <script type="text/javascript">
    	function checkParam(){
    		var id = document.getElementById("id").value;
    		var title = document.getElementById("title").value;
    		var img = document.getElementById("img").value;
    		img = img.replace(/&/g, "%26");
    		if(id == "" || id == null || title == "" || title == null || img == "" || img == null){
    			alert("参数不能为空");
    			return;
    		}
    		
    		$.ajax({url: "<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showFixedTag",
    		    type: "POST",
    			data: '&id=' + id + '&img=' + img + '&title=' + title,
    			success: function(ret){
    				alert("更新成功");
          		}});
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
	         <h2 class="entry-title">固定标签</h2>
				<div id="submit-content">
                <div id="formbox">
                		<p>
                             <label><font color="#777">标签id：</font></label>
                             <input type="text" name="id" id="id" value="${value.id}" />
                        </p>
                        <p>
                             <label><font color="#777">标签标题：</font></label>
                             <input type="text" name="title" id="title" value="${value.title}" />
                       	</p>
                       	<p id="p_bgcolor">
							<label><font color="#777">标签图片：</font></label>
							<input type="text" name="img" id="img" value="${value.img}"  style="width: 50x;"/>
						</p>
						
						<p>
							<input id="submit" type="button" class="button" onclick="checkParam()" value="更新" />
						</p>
                  </div>
                </div>
		</div>
		
	  </div>
	 </div>
	</div>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>