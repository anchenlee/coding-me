<%@page import="com.netting.bean.Menu"%>  
<%@page import="java.util.ArrayList"%> 
<%@page import="com.netting.dao.admin.Menu_DAO"%> 
<%@page import="com.netting.bean.ProfUser"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.netting.action.admin.AdminLoginAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			<%
				if (!(AdminLoginAction.check(request))) 
					{
						response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
						return;
					}
			%>
    		<div id="header">
				<div id="header-content">
		          <img width="120px" style="margin: 20px 0 0 40px" src="<%=request.getContextPath()%>/img/header_dr.png" alt="" id="logo" />
		          <div id="header-name">达人管理系统</div>
		          <div id="header-nav">
					    <span style="color:white">
					   
			       		</span>
						<div class="nav">
							<ul id="" class="">
							<!-- 	 <li class="first"><a href="#">Home</a></li>							
								 <li class="page_item page-item-2"><a href="#">Blog</a></li>
			                        <li class="page_item page-item-8"><a href="#">Elements</a></li>
			                    -->
						    </ul>
						</div>
				    </div>
				    <div id="header-social" style="color: #fff;">
			            <ul>
			            	<li  style="float:left;font-size: 12px;font-weight: bold;">
			            <%
			            	ProfUser admin =  AdminLoginAction.getAdminUserFromCookie(request);
           		       		if(admin!=null)
           	       			{
           	       				out.print(admin.getUsername());
           	       			}
           		       		
           		       		ArrayList<Menu> menu_list_1 = Menu_DAO.getMenuListByUID(admin.getId(), 1);
           		       		if (menu_list_1 != null && menu_list_1.size() > 0)
           		       		{
           		       			request.setAttribute("menu_list_1", menu_list_1);
           		       		}
           		       		
           		       		ArrayList<Menu> menu_list_2 = Menu_DAO.getMenuListByUID(admin.getId(), 2);
           		       		if (menu_list_2 != null && menu_list_2.size() > 0)
           		       		{
           		       			request.setAttribute("menu_list_2", menu_list_2);
           		       		}
           		       		
           		       		ArrayList<Menu> menu_list_3 = Menu_DAO.getMenuListByUID(admin.getId(), 3);
           		       		if (menu_list_3 != null && menu_list_3.size() > 0)
           		       		{
           		       			request.setAttribute("menu_list_3", menu_list_3);
           		       		}
			            %> &nbsp;</li>
			       		<li  style="float:left;font-size: 12px;font-weight: bold;"><a href="<%=request.getContextPath()%>/AdminLoginAction?actionmethod=logout">退出</a></li>
			            </ul>
				    </div>
	        </div>
		</div>