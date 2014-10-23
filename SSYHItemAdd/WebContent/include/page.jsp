<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <body>
          当前页${page}/${totalpage}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <c:choose>
        <c:when test="${page > 1}">
             <a href="javascript:gotoPage(1)">首页</a>&nbsp;
             <a href="javascript:gotoPage(${page-1})">上一页</a>
        </c:when>
        <c:otherwise>
                                   首页     上一页
        </c:otherwise>
    </c:choose>
    &nbsp;
    <c:choose>
        <c:when test="${page >= totalpage}">
                                   下一页     末页
        </c:when>
        <c:otherwise>
             <a href="javascript:gotoPage(${page+1})">下一页</a>&nbsp;
             <a href="javascript:gotoPage(${totalpage})">末页</a>
        </c:otherwise>
    </c:choose>
    &nbsp;
    转到第<select name="page" onchange="jump()">
         <c:forEach var="i" begin="1" end="${totalpage}" step="1">                
                <c:choose>
                       <c:when test="${i == page}">
                               <option value="${i}" selected>${i}</option>
                        </c:when>
                        <c:otherwise>
                               <option value="${i}">${i}</option>
                        </c:otherwise>
                </c:choose>
          </c:forEach>
       </select> 页
    <script language="javascript">
      function jump()
      {
        document.PageForm.submit();
      }
      
      function gotoPage(pagenum)
      {
         document.PageForm.page.value = pagenum;
         document.PageForm.submit();
      }
	</script>
  </body>
</html>
