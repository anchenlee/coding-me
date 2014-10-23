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
			var keyword = $("#keyword").val();
			if (keyword == null || keyword == "")
			{
				alert("请填写<关键字>!");
				return false;
			}
			
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/hot_keyword_manager?actionmethod=addHotKeyword',
    		    type: 'POST',
    		    data: {
    		    	keyword:keyword
    		    },
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    					var url = "<%=request.getContextPath()%>/ad/hot_keyword_manager?actionmethod=showHotKeywordList";
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
	         <h2 class="entry-title">新增热门搜索关键字</h2>
				<div id="submit-content">
                <div id="formbox">
                		<p>
                             <label><font color="#777">关键字：</font></label>
                             <input type="text" name="keyword" id="keyword" value="" />
                        </p>
										
						<br/>						
						<p>
							<input id="submit" type="button" class="button" onclick="checkParam()" value="提交" />
							
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