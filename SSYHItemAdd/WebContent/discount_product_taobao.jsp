<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品比价</title>
</head>
<body>
<iframe src="http://a.m.taobao.com/i${param.itemid}.htm" width="100%" height="620"></iframe>
<iframe src="http://item.taobao.com/item.htm?id=${param.itemid}"  width="100%" height="620"></iframe>
<form action="<%=request.getContextPath() %>/DiscountKeywordServlet" method="post">
商品价格<input type="text" name = "newprice" value="${param.price}" />
 <input type="hidden" name="atp" value="19">
 <input type="hidden" name="tagid" value="${param.tagid}">
 <input type="hidden" name="catId" value="${param.catId}">
 <input type="hidden" name="keyword" value="${param.keyword}">
 <input type="hidden" name="page" value="${param.page}">
 <input type="hidden" name="itemId" value="${param.itemid}">
<input type="submit" value="修改">
</form>
</body>
</html>