<%@page import="cn.youhui.platform.db.DBManager"%>
<%@page import="cn.youhui.itemadd.dataadapter.BaseAdapter"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="content-script-type" content="text/javascript">
<title>商品管理后台</title>
<!-- 最新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap2.0.1.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom2.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style2.css">
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom2.css">
</head>

<%
String tabId = request.getParameter("tabId");
String menuId = request.getParameter("menuId");
String itemId="";
String titleOrRecom="";
int pagNow=1;
if(request.getParameter("pagNow")!=null&&!"".equals(request.getParameter("pagNow"))){
	if(Integer.parseInt(request.getParameter("pagNow"))>1){
		pagNow=Integer.parseInt(request.getParameter("pagNow"));
	}
}
if(request.getParameter("itemId")!=null&&!"".equals(request.getParameter("itemId"))){
	itemId=request.getParameter("itemId");
}
if(request.getParameter("titleOrRecom")!=null&&!"".equals(request.getParameter("titleOrRecom"))){
	titleOrRecom=request.getParameter("titleOrRecom");
}
int tid = 1;
int mid = 1;
	if(tabId!=null&&!"".equals(tabId)){
		tid = Integer.parseInt(tabId);
	}

	if(menuId!=null&&!"".equals(menuId)){
		mid = Integer.parseInt(menuId);
	}

	BaseAdapter ba = new BaseAdapter(tid,mid);
%>

<body onload="ol()">
		<div class="top-panel">
			<div class="top-logo">
				<img class="logo-img" alt="" src="img/logo.jpg">
				<img class="title-img" alt="" src="img/title.jpg">
				<span class="title">商品管理系统</span>
				<div class="top-tool">
				</div>
				
			</div>
			<img class="right-img" alt="" src="img/top_right.jpg">
			<ul class="nav nav-tabs menu">
				<li><span class="span_50px"></span></li>
				<%=ba.getTab(request) %>
			</ul>
		</div>
		<div class="mainbody">

		<div class="panel panel-default left-panel" >
			<div class="tree well" style="border:none;">
				<ul style="padding:0;">
					<li>
						<span><i class="icon-folder-open"></i> 呵呵</span> <a href="">哈哈</a>
						<ul>
							<li>
								<span><i class="icon-minus-sign"></i> 呵呵</span> <a href="">哈哈</a>
								<ul>
									<li>
										<span><i class="icon-leaf"></i> 呵呵</span> <a href="">哈哈</a>
									</li>
								</ul>
							</li>
							<li>
								<span><i class="icon-minus-sign"></i> 呵呵</span> <a href="">哈哈</a>
								<ul>
									<li>
										<span><i class="icon-leaf"></i> 呵呵</span> <a href="">哈哈</a>
									</li>
									<li>
										<span><i class="icon-minus-sign"></i> 呵呵</span> <a href="">哈哈</a>
										<ul>
											<li>
												<span><i class="icon-minus-sign"></i> 呵呵</span> <a href="">哈哈</a>
												<ul>
													<li>
														<span><i class="icon-leaf"></i> 呵呵</span> <a href="">哈哈</a>
													</li>
													<li>
														<span><i class="icon-leaf"></i> 呵呵</span> <a href="">哈哈</a>
													</li>
												 </ul>
											</li>
											<li>
												<span><i class="icon-leaf"></i> 呵呵</span> <a href="">哈哈</a>
											</li>
											<li>
												<span><i class="icon-leaf"></i> 呵呵</span> <a href="">哈哈</a>
											</li>
										</ul>
									</li>
									<li>
										<span><i class="icon-leaf"></i> 呵呵</span> <a href="">哈哈</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li>
						<span><i class="icon-folder-open"></i> 呵呵</span> <a href="">哈哈</a>
						<ul>
							<li>
								<span><i class="icon-leaf"></i> 呵呵</span> <a href="">哈哈</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>

		<div class="panel panel-default main-panel">
			<div class="panel-heading">
				<h3 style="display: inline;" class="panel-title">菜单</h3>
				<input type="button" value="添加" class="btn btn-default"  onclick="tj()" style="position:relative;" align="right" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="search" type="text" /><input type="button" style="margin: 0 2px 0 4px" class="btn btn-default" onclick="searchByItemId()"  value="按商品id搜索"/><input type="button" style="margin: 0 0 0 2px" class="btn btn-default"  onclick="searchByItemTitleOrRecom()"  value="按商品标题或者推荐语搜索"/>
			</div>
			<div class="panel-body" style="position: relative;width:1400px;">
<%-- 			<%ba.tmp(request); %>  --%>
				 <%if(mid==5){ ba.tmp(request);%>
				<%DBManager.getPreItemListAlready(itemId, titleOrRecom, 1, request); %>
				<%}else if(mid==3){ %>
					<%=ba.getContent2(pagNow) %>
					<%}else{ %>
					<%=ba.getContent3() %>
					<%} %> 
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
				<div style="width:218px;height:344px;padding:10px 15px;margin:5px;border:1px solid #ddd;float:left;border-radius:5px;">
					<img src="xx.jpg" style="display:inline-block;width:100px;"/>
					<a style="display:inline-block;">删除</a>
					<a style="display:inline-block;">编辑</a>
					<input name="checkbox_item" type="checkbox" style="display:inline-block;"/>
					<p>芳慕2014夏装大码女装欧根纱防晒显瘦长裙雪纺新款大码短袖连衣裙</p>
					<p>商品id:123456789</p>
					<p>添加者:admin</p>
					<p>折扣价格:168.01</p>
					<p>折扣:0.38</p>
					<p>返集分宝比例:4.0</p>
				</div>
			</div>
			
			<%int pagNum=(Integer)request.getAttribute("pagNum"); String  url=request.getContextPath()+"/oready.jsp?tabId=1&menuId=5"+"&itemId="+itemId+"&titleOrRecom="+request.getAttribute("titleOrRecom"); %>
			<div>
			<ul class="pagination" style="float: right;">
			
			
			<%if(pagNow==1){ %>
			  	<li><a href="<%=url%>&pagNow=1">&laquo;</a></li>
			  <%}else{ %>
			  	<li><a href="<%=url%>&pagNow=<%=(pagNow-1)%>">&laquo;</a></li>
			  <%} %>
			<%int num=5;if(pagNum<5){num=pagNum;}if(pagNow-5<=0){
				for(int i=1;i<=num;i++){%>
				<%if(pagNow==i){ %>
				<li class="active"><a href="<%=url%>&pagNow=<%=i%>"><%=i %></a></li>
				<%}else{ %>
					<li><a href="<%=url%>&pagNow=<%=i%>"><%=i %></a></li>
				<%} %>
				
			<%}%>
			<li><a >...</a></li>
			<% }else if(pagNow+5>=pagNum){ %>
				<li ><a >...</a></li>
				<%for(int i=pagNum-5;i<=pagNum;i++){%>
				<%if(pagNow==i){ %>
				<li class="active"><a href="<%=url%>&pagNow=<%=i%>"><%=i %></a></li>
				<%}else{ %>
					<li><a href="<%=url%>&pagNow=<%=i%>"><%=i %></a></li>
				<%} %>
			<%}%>
			<%}else{ %>
				<li><a >...</a></li>
				<li ><a href="<%=url%>&pagNow=<%=pagNow-2%>"><%=pagNow-2%></a></li>
				<li ><a href="<%=url%>&pagNow=<%=pagNow-1%>"><%=pagNow-1%></a></li>
				<li class="active"><a href="<%=url%>&pagNow=<%=pagNow%>"><%=pagNow%></a></li>
				<li ><a href="<%=url%>&pagNow=<%=pagNow+1%>"><%=pagNow+1%></a></li>
				<li ><a href="<%=url%>&pagNow=<%=pagNow+2%>"><%=pagNow+2%></a></li>
				<li><a >...</a></li>
			<%} %>
			
			<%if(pagNow==pagNum){ %>
			  	<li><a href="<%=url%>&pagNow=<%=pagNum%>">&raquo;</a></li>
			  <%}else{ %>
			  	<li><a href="<%=url%>&pagNow=<%=(pagNow+1)%>">&raquo;</a></li>
			  <%} %>
			
			</ul>
			<select onchange="sel(this)" style="float: right;position: relative; right: 10px;top: 25px;">
			<%for(int i=1;i<=pagNum;i++){ %>
				<%if(pagNow==i){ %>
					<option selected="selected"><%=i %></option>
				<%}else{ %>
					<option><%=i %></option>
				<%} %>
			<%} %>
			</select>
			</div>
		</div>	
	</div>
		
</body>

<script type="text/javascript">

$(function(){  // 树状图的js
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
});

String.prototype.replaceAll = function(s1,s2) {
	return this.replace(new RegExp(s1,"gm"),s2); 
   }
var tt=0;
var kk=0;
var hh=0;


function tag_change(tagId,a){
	var tt=document.getElementsByName("tr_"+tagId);
	if(a.checked&&tt.length==0){
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  
			  var val=xmlhttp.responseText;
			  if(val!="empty"){
				 $("#les").append(val);
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/GetSonOfTag?tagId="+tagId;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
}

function sel(a){
	var pagNow=a.value;
	window.location.href="<%=url%>&pagNow="+pagNow;
}

function ol(){
// 	$(function(){
// 		$.getJSON("http://10.0.0.21:8080/testapi/test?callback=?", function(json) {
// 	        alert(json);
// 		});
// 	});
}
function back(a){
	alert(a.content);
}

function crawl(){
	var itemIds="";
	var itemIdsElement=document.getElementsByName("hid_itemId");
	for(var i=0;i<itemIdsElement.length;i++){
		var itemid=itemIdsElement[i].value;
		
		
		$.getJSON("http://pub.alimama.com/pubauc/searchAuctionList.json?q=http://item.taobao.com/item.htm?id="+itemid+"&callback=?", function(json) {
	        alert(json);
		});
		
		
	    $.ajax({ 
            async:false, 
            url: 'http://pub.alimama.com/pubauc/searchAuctionList.json?q=http://item.taobao.com/item.htm?id='+itemid,  // 跨域URL
            type: 'GET', 
            dataType: 'jsonp', 
            jsonp: 'jsoncallback', //默认callback
           // data: mydata, 
            timeout: 5000, 
            beforeSend: function(){  //jsonp 方式此方法不被触发。原因可能是dataType如果指定为jsonp的话，就已经不是ajax事件了
            },
            success: function (json) { //客户端jquery预先定义好的callback函数，成功获取跨域服务器上的json数据后，会动态执行这个callback函数 
                if(json.actionErrors.length!=0){ 
                    alert(json.actionErrors); 
                } 
                alert(json);
            },
            complete: function(XMLHttpRequest, textStatus){ 
                $.unblockUI({ fadeOut: 10 }); 
            }, 
            error: function(xhr){ 
                //jsonp 方式此方法不被触发
                //请求出错处理 
                alert("请求出错(请检查相关度网络状况.)"); 
            }
        });
		
		if(i==itemIdsElement.length-1){
			itemIds=itemIds+itemid;
		}else{
			itemIds=itemIds+itemid+",";
		}
	}
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var val=xmlhttp.responseText;
		  alert("成功更新了 "+val+" 条");
	    }
	  }
	  var url="<%=request.getContextPath()%>/crawl?itemIds="+itemIds;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function addAll(){
	addtags();
	addkeywords();
	addHome();
}


function addtags(){
	var items= document.getElementsByName("checkbox_item");
	var tags= document.getElementsByName("checkbox_tags");
	var itemval="{\"item_list\":[";
	var tmp="";
	for(var i=0;i<items.length;i++){
		if(items[i].checked){
				tmp=tmp+items[i].value+",";
		}
	}
	if(tmp.length>0){
		tmp=tmp.substring(0,tmp.length-1);
	}
	itemval=itemval+tmp+"]}";
	
	var tagsval="";
	for(var i=0;i<tags.length;i++){
		if(tags[i].checked){
				tagsval=tagsval+tags[i].value+",";
		}
	}
	if(tagsval.length>0){
		tagsval=tagsval.substring(0,tagsval.length-1);
	}
	
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 if(xmlhttp.responseText=="paramException"){
			 alert("参数异常");
		 }
		 else if(xmlhttp.responseText=="fail"){
		  }
		 else{
			 // alert("成功 "+xmlhttp.responseText+" 条！");
			  tt=xmlhttp.responseText;
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="type=tagItem"+"&id="+tagsval+"&content="+itemval;
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}
function addkeywords(){
	var items= document.getElementsByName("checkbox_item");
	var keywords= document.getElementsByName("checkbox_keywords");
	var itemval="{\"item_list\":[";
	var tmp="";
	for(var i=0;i<items.length;i++){
		if(items[i].checked){
				tmp=tmp+items[i].value+",";
		}
	}
	if(tmp.length>0){
		tmp=tmp.substring(0,tmp.length-1);
	}
	itemval=itemval+tmp+"]}";
	
	var keywordsval="";
	for(var i=0;i<keywords.length;i++){
		if(keywords[i].checked){
			keywordsval=keywordsval+keywords[i].value+",";
		}
	}
	if(keywordsval.length>0){
		keywordsval=keywordsval.substring(0,keywordsval.length-1);
	}
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 if(xmlhttp.responseText=="paramException"){
			 alert("参数异常");
		 }
		 else if(xmlhttp.responseText=="fail"){
		  }
		 else{
			  for(var i=0;i<items.length;i++){
					if(items[i].checked){
							document.getElementById("ul_item").removeChild(items[i].parentNode.parentNode.parentNode);
					}
				}
//  			  alert("成功 "+xmlhttp.responseText+" 条！");
			  kk=xmlhttp.responseText;
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="type=keywordItem"+"&id="+keywordsval+"&content="+itemval;
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}

function addHome(){
	var items= document.getElementsByName("checkbox_item");
	var home=document.getElementById("home");
	if(!home.checked){
// 		alert("成功添加了"+tt+"条到主题，"+kk+"条到关键字"+hh+"条到主页！");
		window.location.reload();
		return;
	}
	var itemval="{\"item_list\":[";
	var tmp="";
	for(var i=0;i<items.length;i++){
		if(items[i].checked){
				tmp=tmp+items[i].value+",";
		}
	}
	if(tmp.length>0){
		tmp=tmp.substring(0,tmp.length-1);
	}
	itemval=itemval+tmp+"]}";
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		 if(xmlhttp.responseText=="paramException"){
			 alert("参数异常");
		 }
		 else if(xmlhttp.responseText=="fail"){
		  }
		 else{
			  hh=xmlhttp.responseText;
// 			  alert("成功添加了"+tt+"条到主题，"+kk+"条到关键字"+hh+"条到主页！");
			  window.location.reload();
// 			  alert("成功!");
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="type=recoItem"+"&content="+itemval+"&id=0";
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}

function h3_1(){
	if(document.getElementById("h3_2")!=null){
		document.getElementById("h3s").removeChild(document.getElementById("h3_2"));
	}
	if(document.getElementById("h3_3")!=null){
		document.getElementById("h3s").removeChild(document.getElementById("h3_3"));
	}
	document.getElementById("table_keywords2").style.display="none";
	document.getElementById("table_keywords3").style.display="none";
	document.getElementById("table_keywords1").style.display="";
}

function h3_2(){
	if(document.getElementById("h3_3")!=null){
		document.getElementById("h3s").removeChild(document.getElementById("h3_3"));
	}
	document.getElementById("table_keywords1").style.display="none";
	document.getElementById("table_keywords3").style.display="none";
	document.getElementById("table_keywords2").style.display="";
}

function key1(id1,name2){
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var val=xmlhttp.responseText;
		  var htm="";
		  for(var i=0;i<val.split("/").length;i++){
			  
			  for(var z=0;z<5;z++){
				  if(i<val.split("/").length-1){
					  var id=val.split("/")[i].split("_")[0];
					  var name=val.split("/")[i].split("_")[1];
					  htm=htm+"<td><a onclick=\"key2('"+id1+"','"+id+"','"+name+"')\">"+name+"</a></td>";
					  i++;
				  }
			  }
			  
		  }
		  document.getElementById("table_keywords2").innerHTML=htm;
		  document.getElementById("table_keywords1").style.display="none";
		  $("#h3_1").after("<h3 id=\"h3_2\"  style=\"display: inline;\" class=\"panel-title\" onclick=\"h3_2()\">>>"+name2+"</h3>");
		  document.getElementById("table_keywords2").style.display="";
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+id1;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function k1(a){
	var k_1=a.value.split("_")[0];
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var val=xmlhttp.responseText;
		  var htm="<select id='se_k2' onchange='k2(this)'><option></option>";
		  for(var i=0;i<val.split("/").length;i++){
			  var id=val.split("/")[i].split("_")[0];
			  var name=val.split("/")[i].split("_")[1];
			  var tmp=i+"_"+name;
			  if(i<val.split("/").length-1){
			 	 htm=htm+"<option>"+tmp+"</option>";
			  }
		  }
		  htm=htm+"</select>";
		  document.getElementById("td_k2").innerHTML=htm;
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+k_1;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}


function key2(k_1,k_2,name2){
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var val=xmlhttp.responseText;
		  var htm="";
		  for(var i=0;i<val.split("/").length;i++){
			  var id=val.split("/")[i].split("_")[0];
			  var name=val.split("/")[i].split("_")[1];
			  var tmp=id+"_"+name;
			  htm=htm+"<tr>"+"<td><input name='checkbox_keywords' type='checkbox' value="+tmp+">"+name+"</td>"+"</tr>";
		  }
		  document.getElementById("table_keywords3").innerHTML=htm;
		  document.getElementById("table_keywords1").style.display="none";
		  document.getElementById("table_keywords2").style.display="none";
		  $("#h3_2").after("<h3 id=\"h3_3\"  style=\"display: inline;\" class=\"panel-title\">>>"+name2+"</h3>");
		  document.getElementById("table_keywords3").style.display="";
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+k_1+"&k_2="+k_2;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function k2(a){
	var k_1=document.getElementById("se_k1").value.split("_")[0];
	var k_2=a.value.split("_")[0];
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  var val=xmlhttp.responseText;
		  var htm="";
		  for(var i=0;i<val.split("/").length;i++){
			  var id=val.split("/")[i].split("_")[0];
			  var name=val.split("/")[i].split("_")[1];
			  var tmp=id+"_"+name;
			  htm=htm+"<tr>"+"<td><input name='checkbox_keywords' type='checkbox' value="+tmp+">"+name+"</td>"+"</tr>";
		  }
		  document.getElementById("table_keywords").innerHTML=htm;
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+k_1+"&k_2="+k_2;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function k3(a){
	var k_1=document.getElementById("td_k1").value.split("_")[0];
	var k_2=document.getElementById("td_k2").value.split("_")[0];
	var k_3=a.value.split("_")[0];
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  alert(xmlhttp.responseText)
	    }
	  }
	  var url="<%=request.getContextPath()%>/keywords?k_1="+k_1+"&k_2="+k_2+"&k_3="+k_3;
		xmlhttp.open("GET",url,true);
		xmlhttp.send();
}

function mol(a){
	var table=document.getElementById("more");
	if(table.style.display=="none"){
		table.style.display="";
		a.innerHTML="less";
		var h=parseInt(document.getElementById("keyword").style.top.split("px")[0])+970+"px";
		document.getElementById("keyword").style.top=h;
	}else{
		table.style.display="none";
		a.innerHTML="more";
		var h=parseInt(document.getElementById("keyword").style.top.split("px")[0])-970+"px";
		document.getElementById("keyword").style.top=h;
	}
	
}


	function changeRecom(a,id){
		var val=a.value;
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  if(xmlhttp.responseText=="paramException"){
				  alert("参数异常");
			  }else if(xmlhttp.responseText=="fail"){
				  alert("失败");
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/update?yes=recom&val="+val+"&id="+id;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	function changeRemark(a,id){
		var val=a.value;
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  if(xmlhttp.responseText=="paramException"){
				  alert("参数异常");
			  }else if(xmlhttp.responseText=="fail"){
				  alert("失败");
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/update?yes=remark&val="+val+"&id="+id;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	
	function update(id){
		
		  var url="<%=request.getContextPath()%>/update?yes=update&id="+id;
		  window.open(url);
	}
	
	function tj(){
		
		var items=document.getElementsByName("checkbox_item");
		var tmp=0;
		for(var i=0;i<items.length;i++){
			if(items[i].checked){
				tmp=1;
			}
		}
		if(tmp==0){
			alert("请选择商品！");
			return;
		}
		for(var i=0;i<document.getElementsByName("checkbox_tags").length;i++){
			document.getElementsByName("checkbox_tags")[i].checked=false;
		}
		for(var i=0;i<document.getElementsByName("fuck").length;i++){
			document.getElementById("les").childNodes[0].removeChild(document.getElementsByName("fuck")[i]);
		}
		
		$('#myModal').modal('show');
	}
	
	
	function delTok(id,a,type,itemId){
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  if(xmlhttp.responseText=="paramException"){
				  alert("参数异常");
			  }else if(xmlhttp.responseText=="fail"){
				  alert(1);
			  }else if(xmlhttp.responseText=="success"){
				  $(a).parent().remove();
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/deltok?id="+id+"&type="+type+"&itemId="+itemId;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	
	function del(itemId,a){
		
		var xmlhttp;
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		  xmlhttp.onreadystatechange=function()
		  {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		    {
			  if(xmlhttp.responseText=="paramException"){
				  alert("参数异常");
			  }else if(xmlhttp.responseText=="fail"){
				  alert("失败");
			  }else if(xmlhttp.responseText=="success"){
				  var li=document.getElementById(a);
					document.getElementById("ul_item").removeChild(li);
			  }
		    }
		  }
		  var url="<%=request.getContextPath()%>/deltkh?itemId="+itemId;
			xmlhttp.open("GET",url,true);
			xmlhttp.send();
	}
	
	function update(itemId){
		var url="<%=request.getContextPath()%>/updateForOready?yes=update&itemId="+itemId;
		  window.location.href=url;
	}
	
	function searchByItemId(){
		var itemId=document.getElementById("search").value;
		if(itemId.replaceAll(" ","")==""){
			alert("请填写商品id！");
		}
		var url="<%=request.getContextPath()%>/oready.jsp?tabId=1&menuId=5&itemId="+itemId;
		window.location.href=url;
	}
	function searchByItemTitleOrRecom(){
		var titleOrRecom=document.getElementById("search").value;
		if(titleOrRecom.replaceAll(" ","")==""){
			alert("请填写商品id！");
		}
		var url="<%=request.getContextPath()%>/oready.jsp?tabId=1&menuId=5&titleOrRecom="+titleOrRecom;
		url=encodeURI(url);
		window.location.href=url;
	}
	
</script>
</html>