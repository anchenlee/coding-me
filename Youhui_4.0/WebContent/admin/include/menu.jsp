<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="sidebar" class="aside">
	
	<c:if test="${menu_list_1 != null || fn:length(menu_list_1) > 0}">
			<div class="widget widget_tj_portfolio_types">  
				<div class="clear">
					<h2 class="title-left widget-title">商品管理</h2>
					<div class="title-right"></div>
				</div>
				<ul>
					<c:forEach var="menu" items="${menu_list_1}">
						<li class="cat-item cat-item-1">
							<a href="<%=request.getContextPath()%>${menu.url}"><img width="20px" height="20px" src="<%=request.getContextPath() %>${menu.icon}" style="float: left"/>&nbsp;${menu.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
	</c:if>
	
	<c:if test="${menu_list_2 != null || fn:length(menu_list_2) > 0}">
			<div class="widget widget_tj_portfolio_types">  
				<div class="clear">
					<h2 class="title-left widget-title">发送管理</h2>
					<div class="title-right"></div>
				</div>
				<ul>
					<c:forEach var="menu" items="${menu_list_2}">
						<li class="cat-item cat-item-1">
							<a href="<%=request.getContextPath()%>${menu.url}"><img width="20px" height="20px" src="<%=request.getContextPath() %>${menu.icon}"  style="float: left"/>&nbsp;${menu.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
	</c:if>
	
	<c:if test="${menu_list_3 != null || fn:length(menu_list_3) > 0}">
		<c:forEach var="menu" items="${menu_list_3}">
			<div class="widget widget_tj_portfolio_types">  
				<div class="clear">
					<a href="<%=request.getContextPath()%>${menu.url}">
						<h2 class="title-left widget-title">${menu.name}</h2>
					</a>
					<div class="title-right"></div>
				</div>
			</div>
		</c:forEach>
	</c:if>
	
</div>