<%@page import="com.netting.dao.admin.Admin_Tag_Item_DAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netting.bean.TeJiaGoodsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js"></script><!--谷歌jquery包-->
    
    <%
    	String p = request.getParameter("page");
    	String tagid = request.getParameter("tagid"); 
    	String total = request.getParameter("totalPage");
    	List<TeJiaGoodsBean> list = new ArrayList<TeJiaGoodsBean>();
    	int count = 0;
    	if(total == null || "".equals(total)){
    		count = Admin_Tag_Item_DAO.getTagItemByTagIDPage(tagid);
    	}
    	total = count+"";
    	int pInt = 1;
    	try{
    		pInt = Integer.parseInt(p);
    	}catch (Exception e) 
		{
    		pInt = 1;
		}
    	list = Admin_Tag_Item_DAO.getTagItemByTagID(tagid,pInt);
    	request.setAttribute("page", pInt);
    	request.setAttribute("ItemList", list);
		request.setAttribute("tagid", tagid);
		request.setAttribute("totalPage", total);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">  
        $(function(){  
            $("#div_test form").click(function(){  
            	 $('#div_test').empty(); 
                var link = $(this).attr('action');  
                $('#div_view').attr('src', link);  
                var href = window.location.href;  
                window.location.href = href.substr(0, href.indexOf('#')) + '#' + link;  
                return false;  
            });  
        });  
    </script>  
<div class="" style="" id="div_test">
					  <ul style="list-style-type: none;float: right; " id="item_list"> 
					  
					  	<c:forEach var="item" items="${ItemList}">
					  
	
					            <li style="border: 1px solid #DD4B39 ;float: left;width: 120px;margin-left: 10px;" class="draggable">
					            
					            <img src="${item.pic_url}"  height="120px" width="120px" class="draggable txt1" id="${item.item_id}" onclick="addDisplay('${item.pic_url}','${item.item_id}','${item.clickURL}')"/>
					            
					            </li>
					  </c:forEach>
					  </ul>
<br>
	      	<div class="" align="center" id="pagedisplay">

          当前页${page }/${totalPage} &nbsp;
                                   <a href="#" onclick="getPageItemList('1')">首页</a> &nbsp;    <a href="#" onclick="getPageItemList('${page-1}')">上一页</a>
    &nbsp;<a href="#" onclick="getPageItemList('${page+1 }')">下一页</a>&nbsp;<a href="#" onclick="getPageItemList('<%=total %>')">末页</a>&nbsp;&nbsp;转到第
    <select onchange="getPageItem()" id="pagejump">
    <%
    	for(int i = 1; i <= count ; i ++){
    		%>  		
    <option value="<%=i%>"><%=i%></option>
    		<% 
    	}
    %>
    </select>页
    
        
        
             <!-- 
              <form name="PageForm"  action="<%=request.getContextPath()%>/admin/tag_manager/tag_item_list_page.jsp?tagid=${tagid}&totalPage=${totalPage }" method="post"  >
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
              -->
            </div>
				 
					
				</div>