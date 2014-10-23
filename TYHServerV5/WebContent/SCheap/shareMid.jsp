<%@page import="cn.youhui.common.ParamConfig"%>
<%@page import="cn.youhui.utils.RequestUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>随手优惠-超级惠分享</title>
	<script type="text/javascript" src="js/jumpss.js"></script><!--导入js-->
</head>
<body>

<script type="text/javascript">

	var w_url = "http://youhui.cn";
	var ss_url = "suishou://app.youhui.cn/WebStylePage?url=<%=ParamConfig.index%>";
	
	<%
		if(RequestUtil.isFromWeixin(request)){
			if(RequestUtil.getPlatform(request).equals("android")){
				out.print("w_url=\"android.jsp?c_url="+ParamConfig.index+"\";");
			}else if(RequestUtil.getPlatform(request).equals("iphone")){
				out.print("w_url=\"ios.jsp?c_url="+ParamConfig.index+"\";");
			}
		}
	%>

	jumpToSS(ss_url, w_url);


</script>
</body>
</html>