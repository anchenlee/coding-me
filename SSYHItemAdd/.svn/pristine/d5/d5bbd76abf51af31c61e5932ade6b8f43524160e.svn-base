<%@page import="cn.youhui.itemadd.dataadapter.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="cn.youhui.tools.*" %>
<%@page import="cn.youhui.bean.Searchkeyword" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0058)http://localhost:8080/SSYHItemAdd/update?yes=update&id=167 -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改推荐商品-随手达人</title>
    
    
    <style type="text/css">
    .border{ border:1px solid #CCC; padding:2px;}
    #formbox p {
    	width: 400px;
    	margin-left: 15px;
    }
    </style> 
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery.Jcrop.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery.Jcrop.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/recommend.css">
    
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">

	

	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>
		
	<link href="<%=request.getContextPath() %>/css/custom.css" rel="stylesheet" type="text/css" media="all">
	<script src="<%=request.getContextPath()%>/js/jquery.Jcrop.js"></script>
    <%
    	
    	int pagNow=1;
    	if(request.getAttribute("pagNow")!=null&&!"".equals(request.getAttribute("pagNow"))){
    		pagNow=(Integer.valueOf(String.valueOf(request.getAttribute("pagNow"))));
    	}
    	
    %>
    <script type="text/javascript">
    	showItemImg();
    	var default_img="";
	    jQuery(function initJr($)
	    {
	    	var previewImageWidth = 500;
	    	var consoleImageWidth = 500;
	        // Create variables (in this scope) to hold the API and image size
	        $pre_view = $('#item_preview'),
	        $('#item_img').Jcrop({
	          onChange: updatePreview,
	          onSelect: updatePreview,
	         // aspectRatio: xsize / ysize
	        },function(){
	          // Use the API to get the real image size
	          
	          //var bounds = this.getBounds();
	          // Store the API in the jcrop_api variable
	          //jcrop_api = this;
	
	          // Move the preview into the jcrop container for css positioning
	          //$preview.appendTo(jcrop_api.ui.holder);
	        });
	        
	        function updatePreview(c){
	         		//获取图片实际大小
	              	var newImg = new Image();
	              	newImg.src = $('#item_img')[0].src;
	              	var ratesize = newImg.width/consoleImageWidth;//实际放大倍数
	              	var rateviewsize = Math.max(c.w,c.h)/previewImageWidth;//预览需要放大倍数
	              
	             	var sizex = newImg.width/ratesize/rateviewsize;
	              	var sizey = newImg.height/ratesize/rateviewsize;
	              	
	              	var positionx = c.x/rateviewsize;
	              	var positiony = c.y/rateviewsize;
	        	  
	            	var w =c.w/rateviewsize;
	            	var h =c.h/rateviewsize;
	              
	            	$('#x1').val(parseInt(c.x*ratesize));
	     	   	 	$('#y1').val(parseInt(c.y*ratesize));
	     	   	 	$('#w').val(parseInt(c.w*ratesize));
	     	   	 	$('#h').val(parseInt(c.h*ratesize));
	             
	             	$pre_view.css("width",w+"px");
	             	$pre_view.css("height",h+"px");
	             	$pre_view.css("background-size",sizex+"px "+sizey+"px");
	             	$pre_view.css("background-position-x","-"+positionx+"px");
	             	$pre_view.css("background-position-y","-"+positiony+"px");
	        };
	      });
	    
	    function showItemImg()
	    {
	    	var itemOK = "";
	    	if (itemOK == "0")
	    	{
	    		/*
	    		var  item_img_url = "";
		    	var newImage = new Image;
	    		newImage.src = item_img_url;
	    		changeimg(newImage);
	    		*/
	    	}
	    	else if (itemOK == "1")
	    	{
	    		alert("商品已失效(下架或已不支持返利)!");
				var url = "/SSYHItemAdd/ad/profrecom_manager?actionmethod=showProfRecomList&page=";
				location.href=url;
	    	}
	    }
	    
	    function changeimg(tmpimg)
	    {
	    	$("#item_img").attr("src", tmpimg.src);
	    	$(".jcrop-holder img").attr("src", tmpimg.src);
	    	$("#img_tmp").val(tmpimg.src);
	    	$("#item_preview").css("background-image","url("+tmpimg.src+")");
	    }
    
	    function uploadImg(id)
	    {
	    	var x = $("#x1").val();
	    	var y = $("#y1").val();
	    	var w = $("#w").val();
	    	var h = $("#h").val();
	    	var img = document.getElementById("item_img").src;
	    	img=img.replace(/\+/g,"%2B");
	    	img=img.replace(/\&/g,"%26");
	    	
	    	if (img == null || img == "")
	    	{
	    		alert("图片为空，请重试！");
	    		return false;
	    	}
	    	
	    	if (x == null || x == "" || y == null || y == "" || w == null || w == "" || h == null || h == "")
	    	{
	    		alert("尚未划定图片，请重试！");
	    		return false;
	    	}
	    	
	    	$.ajax({
	    		async: true,
	    	    url: '<%=request.getContextPath()%>/update?yes=uploadProfRecomImg',
	    	    type: 'POST',
	    	    data: {"id":id,"x":x,"y":y,"w":w,"h":h,"img":img,ajaxflag:1},
	    	    success: function(json)
	    	    {
// 	    	    	alert(json);
	    	    }			
	    	});
	    }
	    
		// 提交时检查参数
	    function checkParam(id)
		{
	    	var x = $("#x1").val();
	    	var y = $("#y1").val();
	    	var w = $("#w").val();
	    	var h = $("#h").val();
	    	if (x == null || x == "" || y == null || y == "" || w == null || w == "" || h == null || h == ""){
	    		
	    	}else{
	    		uploadImg(id);
	    	}
	    	
	    	var item_title=document.getElementById("item_title").value;
	    	var item_recom=document.getElementById("item_recom").value;
	    	var item_remark=document.getElementById("item_remark").value;
	    	
	    	item_title=item_title.replace(/\%/g,"%25");
	    	item_recom=item_recom.replace(/\%/g,"%25");
	    	item_remark=item_remark.replace(/\%/g,"%25");
	    	
	    	var item_rate=document.getElementById("item_rate").value;
	    	var item_zkPrice=document.getElementById("item_price_zk").value;
	    	
	    	if(item_title==""){
	    		alert("请填写标题！");
	    		return;
	    	}
	    	if(item_recom==""){
	    		alert("请填写推荐语！");
	    		return;
	    	}
	    	if(item_zkPrice==""){
	    		alert("折扣价格不能为空！");
	    		return;
	    	}
	    	if(item_rate==""){
	    		alert("返集分宝比例不能为空！");
	    		return;
	    	}
	    	
	    	
	    	
	    	var img = new Image();
	    	img.src =document.getElementById("item_img").src;
			var item_img=document.getElementById("item_img");
	    	var w=img.width;
	    	var h=img.height;
			if(document.getElementById("w").value!=""&&document.getElementById("w").value!="0"){
				w=document.getElementById("w").value;
			}
	    	if(document.getElementById("h").value!=""&&document.getElementById("h").value!="0"){
	    		h=document.getElementById("h").value;
	    	}
			var size=w+"x"+h;
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
	                     alert("参数异常！");
	                 }else if(xmlhttp.responseText=="fail"){
	                     alert("失败！");
	                 }else if(xmlhttp.responseText=="success"){
// 	                	 window.history.go(-1);
// 	                	 window.close();
						$('#myModal').modal('show');
	                 }
	             }
	           }
	           var param="yes=updateInfo&id="+id+"&item_title="+item_title+"&item_recom="+item_recom+"&item_remark="+item_remark+"&rate="+item_rate+"&zk_price="+item_zkPrice+"&img_size="+size;
	             xmlhttp.open("POST","<%=request.getContextPath()%>/update",true);
	             xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	             xmlhttp.send(param); 
		}
		
		
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width: 1300px; zoom: 1;">

				<div class="top-panel">
			<div class="top-logo">
				<img  class="logo-img" alt="" src="img/logo.jpg">
				<img class="title-img" alt="" src="img/title.jpg">
				<span class="title">商品管理系统</span>
				<div class="top-tool">
				</div>
				
			</div>
			<img class="right-img" alt="" src="img/top_right.jpg">
			<ul class="nav nav-tabs menu">
				<li><span class="span_50px"></span></li>
				<li class="active"><a href="http://10.0.0.21:8080/ssy/index.jsp?tabId=1">商品</a></li><li><a href="http://10.0.0.21:8080/ssy/index.jsp?tabId=3">主题</a></li>
			</ul>
		</div>

		<!-- /.modal start-->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content" style="width: 750px">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title" id="myModalLabel">选项</h4>
		      </div>
		      <div class="modal-body">
		        

						<div class="mainbody" >
							<div  style="margin: 10;right:100px;top:150px;width: 600px; "  class="panel panel-default " >
							<div class="panel-heading" >
									<h3  style="display: inline;" class="panel-title">选项</h3>
									<a onclick="mol(this)" style="display: inline;"  >more</a>
<!-- 									<input style="display: inline;" align="right" onclick="addtags()"  type="button" value="添加到标签"/> -->
<!-- 									<input style="display: inline;" type="button" onclick="addkeywords()" value="添加到关键字"/> -->
								</div>
								<table id="les" style="width: 100% ">
									<tr>
										
										<td><input name='checkbox_tags' type="checkbox" value="569_亲，包邮！" onchange="tag_change(569,this)" /></td>
										<td>亲，包邮！</td>
										<td><input name='checkbox_tags' type="checkbox" value="909_天天9.9" onchange="tag_change(909,this)" /></td>
										<td>天天9.9</td>
										<td><input name='checkbox_tags' type="checkbox" value="863_淘金币当钱花" onchange="tag_change(863,this)" /></td>
										<td>淘金币当钱花</td>
										<td><input name='checkbox_tags' type="checkbox" value="606_退货无忧" onchange="tag_change(606,this)" /></td>
										<td>退货无忧</td>
										<td><input name='checkbox_tags' type="checkbox" value="613_天猫特卖" onchange="tag_change(613,this)" /></td>
										<td>天猫特卖</td>
										
									</tr>
									<tr>
										
										
<!-- 										<td><input name='checkbox_tags' type="checkbox" value="601_优质精选" onchange="tag_change(610,this)" /></td> -->
<!-- 										<td>优质精选</td> -->
										<td><input name='checkbox_tags' type="checkbox" value="614_随手专享" onchange="tag_change(614,this)" /></td>
										<td>随手专享</td>
<!-- 										<td><input name='checkbox_tags' type="checkbox" value="622_超值女装" onchange="tag_change(622,this)" /></td> -->
<!-- 										<td>超值女装</td> -->
<!-- 										<td><input name='checkbox_tags' type="checkbox" value="615_尾品汇" onchange="tag_change(615,this)" /></td> -->
<!-- 										<td>尾品汇</td> -->
										<td><input name='checkbox_tags' type="checkbox" value="713_天猫超市" onchange="tag_change(713,this)" /></td>
										<td>天猫超市</td>
										<td><input name='checkbox_tags' type="checkbox" value="1017_女鞋" onchange="tag_change(1017,this)" /></td>
										<td>女鞋</td>
										<td><input name='checkbox_tags' type="checkbox" value="1015_化妆品" onchange="tag_change(1015,this)" /></td>
										<td>化妆品</td>
										<td><input name='checkbox_tags' type="checkbox" value="1013_男士" onchange="tag_change(1013,this)" /></td>
										<td>男士</td>
										<td><input name='checkbox_tags' type="checkbox" value="1011_女装" onchange="tag_change(1011,this)" /></td>
										<td>女装</td>
										
									</tr>
								</table>
								</div>
								
								
									<%
							if(request.getAttribute("list1")!=null){
								List<String[]> l1=new ArrayList<String[]>();
								l1=(List<String[]>)request.getAttribute("list1");
							%>
							<div id="more"  style="margin: 10;right:100px;top:250px;width: 600px;display: none "  class="panel panel-default " >
							<table  style="width: 100%">
							<%for(int i=0;i<l1.size();i++){ String key=l1.get(i)[0];String name=l1.get(i)[1];%>
							<tr>
								<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" onchange="tag_change(<%=key %>,this)"/></td>
								<td id="<%=key %>"><%=name %></td>
								<%if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
								<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" onchange="tag_change(<%=key %>,this)"/></td>
								<td id="<%=key %>"><%=name %></td>
								<%}if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
								<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" onchange="tag_change(<%=key %>,this)"/></td>
								<td id="<%=key %>"><%=name %></td>
								<%}if(i<l1.size()-1){i++;key= l1.get(i)[0];name=l1.get(i)[1];%>
								<td><input name='checkbox_tags' type="checkbox" value="<%=key %>_<%=name %>" onchange="tag_change(<%=key %>,this)"/></td>
								<td id="<%=key %>"><%=name %></td>
								<%} %>
							</tr>
							<%} %>
							</table>
							</div>
							<%} %>
								
								<%	if(request.getAttribute("list3")!=null){ 
										List<Searchkeyword> l3=(List<Searchkeyword>)request.getAttribute("list3");
								%>
								<div id="zk_keyword" style="margin: 10;right:100px;top:250px;width: 600px "  class="panel panel-default " >
									<div class="panel-heading" id="h3s">
										<h3 id="h3_1"  style="display: inline;" class="panel-title" >自定义关键词      </h3>
										<input type="text" id="zk_val" /><input type="button" onclick="zk_insert()" value="插入"/>
										<input type="button" onclick="zk_search	(this)" value="搜索"/>
										<input type="button" onclick="zk_hide(this)" value="↑"/>
									</div>
									<table id="zk_table">
												<tr style="width:598px;display: block;display: none;">
										<%for(int j=0;j<l3.size();j++){String id=l3.get(j).getId(); String name=l3.get(j).getName(); %>
											<td style="display: inline-block;"><input name='checkbox_keywords' type='checkbox' value='<%=id%>_<%=name%>'/><%=name %></td>
										<%}%>
									</table>
								</div>
								<%} %>
							<%
								if(request.getAttribute("list2")!=null){
									List<String[]> l2=new ArrayList<String[]>();
									l2=(List<String[]>)request.getAttribute("list2");
								
								%>
								
								<div id="keyword" style="margin: 10;right:100px;top:250px;width: 600px "  class="panel panel-default " >
							<div class="panel-heading" id="h3s">
									<h3 id="h3_1"  style="display: inline;" class="panel-title" onclick="h3_1()">关键词</h3>
								</div>
								
								<table id="table_keywords1" style="width: 100% ">
								<tr style="width: 598px;">
									<%for(int j=0;j<l2.size();j++){ %>
<!-- 										<tr > -->
<%-- 										<%for(int z=0;z<5;z++){if(j<l2.size()){ String id=l2.get(j)[0];String name=l2.get(j)[1];  %> --%>
											<td style="width: 119px;float: left;"  id="td_<%=l2.get(j)[0]%>"><a onclick="key1('<%=l2.get(j)[0]%>','<%=l2.get(j)[1]%>')"><%=l2.get(j)[1] %></a></td>
<%-- 										<%j++;}} %> --%>
										
<!-- 										</tr> -->
									<%} %>
									</tr>
									
<!-- 									<tr> -->
<!-- 										<td id="td_k1" style="width: 33%"> -->
<!-- 											<select id="se_k1" onchange="k1(this)"> -->
<!-- 											<option></option> -->
<%-- 											<%int i=0;for(int j=0;j<l2.size();j++){ String id=l2.get(j)[0];String name=l2.get(j)[1]; %> --%>
<%-- 											<option id="option_<%=id%>"><%=i+"_"+name %></option> --%>
<%-- 											<%i++;} %> --%>
<!-- 											</select> -->
<!-- 										</td> -->
<!-- 										<td id="td_k2" style="width: 33%"></td> -->
<!-- 										<td id="td_k3" style="width: 33%"></td> -->
<!-- 									</tr> -->
								</table>
								
								<table id="table_keywords2" style="width: 100%;display: none;" >
								</table>
								
								<table id="table_keywords3">
								
								</table>
								</div>
								<%} %>
							
						<div  style="margin: 10;right:100px;top:250px;width: 600px "  class="panel panel-default " >
							<div class="panel-heading" id="h3s">
									<h3 id="h3_1"  style="display: inline;" class="panel-title" onclick="h3_1()">首页</h3>
							</div>
							<table>
							<tr>
								<td><input id="home" type="checkbox" checked="checked"  />首页</td>
							</tr>
							</table>
						</div>
							
						</div>

		      </div>
		      <div class="modal-footer" style="text-align: left;">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary" onclick="addAll()">添加</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->




	<div id="container" class="clear">
	<div style="margin:63px 0 0 50px;;" class="panel panel-default left-panel">
		<div class="panel-heading">
			<h3 class="panel-title">菜单</h3>
		</div>
		<div class="panel-body">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="<%=request.getContextPath() %>/index.jsp?tabId=1&menuId=1" title="">新添加商品</a></li>
				<li class="avtive"><a href="<%=request.getContextPath() %>/index.jsp?tabId=1&menuId=3" title="">已更新商品</a></li>
				<li><a href="<%=request.getContextPath() %>/oready.jsp?tabId=1&menuId=5" title="">已处理商品</a></li>		
			</ul>
		</div>
	</div>
    <div style="width:70%;margin-left:50px;float:left;" id="content">
         <div id="primary" class="hfeed">
         
		   <div id="post-8" class="post-8 page type-page status-publish hentry">
	         <h2 class="entry-title">&nbsp&nbsp修改商品</h2>
				<div id="submit-content">
							
                <div id="formbox" >
                		<input type="hidden" id="ProfRecomID" value="167">
                		<input type="hidden" id="img_result" name="img_result" value="">
                        <p class="input-group">
                        	<span class="input-group-addon"><font color="#777">店铺</font></span>
							<input type="text" name="item_id" class="form-control" id="item_id" disabled="disabled" value="${item.nick}" placeholder="  " />
                        </p>
                        <p class="input-group">
                             <span class="input-group-addon"><font color="#777">商品价格：</font></span>
                             <input type="text" name="item_price" class="form-control" id="item_price" value="${item.price }" disabled="disabled">
                        </p>
                        <p class="input-group">
                             <span class="input-group-addon"><font color="#777">商品折扣价格：</font></span>
                             <input type="text" name="item_price_zk" class="form-control" id="item_price_zk" value="${item.zkPrice }" >
                        </p>
                          <p class="input-group">
                             <span class="input-group-addon"><font color="#777">商品返集分宝比例：</font></span>
                             <input type="text" readonly="readonly" name="item_price_zk" class="form-control" id="item_rate" value="${item.rate }" >
                        </p>
                        <p class="input-group" style="width:505px;">
                             <span class="input-group-addon"><font color="#777">标题：</font></span>
                             <input type="text" name="item_title" class="form-control" id="item_title" value="${item.title }">
                             <a style="display: table-cell;vertical-align: middle;" href="javascript:window.open('ItemDetail.jsp?itemId=${item.itemid }');window.parent.location.reload()">查看详情</a>
                        </p>
                        <p class="input-group">
                             <span class="input-group-addon"><font color="#777">推荐：</font></span>
                             <input type="text" name="item_recom" class="form-control" id="item_recom" value="${item.recom }">
                        </p>
                       	<p class="input-group">
							<span class="input-group-addon"><font color="#777">备注：</font></span>
							<!-- <input type="text" name="reason" id="reason" value="" /> -->
							<textarea style="resize:none" name="item_remark" class="form-control" id="item_remark" rows="3">${item.remark}</textarea>
						</p>
						<p class="input-group">
								<span class="input-group-addon">图片尺寸：</span>
								<span class="input-group-addon">x</span><input class="form-control" type="text" size="4" id="x1" name="x1" style="width: 80px" disabled="disabled">
								<span class="input-group-addon">y</span><input class="form-control" type="text" size="4" id="y1" name="y1" style="width: 80px" disabled="disabled">
								<span class="input-group-addon">w</span><input class="form-control" type="text" size="4" id="w" name="w" style="width: 80px" disabled="disabled">
								<span class="input-group-addon">h</span><input class="form-control" type="text" size="4" id="h" name="h" style="width: 80px" disabled="disabled">
						</p>
						<p class="input-group">
						 	<input type="button" value="保存图片" class="button btn btn-primary" onclick="uploadImg(${item.id})">
							<input id="submit" name="submit" type="button" class="button btn btn-primary" style="margin-left:10px;"  onclick="checkParam(${item.id})" value="提交">
						 </p>
						<div id="img_container" >
							<div id="item_pic_left" style="float:left;width:100px;margin-top:10px;">
							<% long l=System.currentTimeMillis(); System.out.print("aaaaaa---------------->>>"+l); List<String> list=Tools.getItemImgFromTaobao(((ItemBean)request.getAttribute("item")).itemid); for(int i=0;i<list.size();i++){String src=list.get(i); %>
								<img name="imgList" onclick="changeImg('<%=src %>',this)" style="width:100%;;margin-top:5px;"  src="<%=src%>">
							<%} System.out.print("bbbbb---------------->>>"+(System.currentTimeMillis()-l)); %>
							</div>
							<div id="item_bigimg"  style="float:left" class ="imageview imageviewborder">
								<img id="item_img"  width="500px" src="${item.imgurl}" />
                            </div>
                           	<div id ="preview"  style="float:left" class="imageview">
                           		<div id="item_preview" class="imageviewborder"  style="margin:0 auto; top:-50%;width:500px;height:500px;background-repeat:no-repeat;background-image:url('${item.imgurl}')" ></div>
                           	</div>
                  		</div>
                </div>	 
				</div>
		
	  </div>
	 </div>
	</div>
	</div>
	
</body>
<script type="text/javascript">

function zk_hide(obj){
	$(obj).parents('#zk_keyword').find('tr').hide();
}

function zk_search(obj){
	$(obj).parents('#zk_keyword').find('tr').show();
	var text = $(obj).prevAll('#zk_val').val();
	$(obj).parents('#zk_keyword').find('td').each(function(){
		if($(this).text().indexOf(text) >= 0){
			$(this).css('color','red');
		}
	})
}

function zk_insert(){
	var val=document.getElementById("zk_val").value;
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
		  
		  var id=xmlhttp.responseText;
		  if(id=="paramException"){
			alert("参数异常");
		  }else if(id=="fail"){
			alert("失败");
		  }else{
			  var newNode=document.createElement("tr");
			  newNode.innerHTML="<td><input name='checkbox_keywords' type='checkbox' value='"+id+"_"+val+"'/>"+val+"</td>";
			  document.getElementById("zk_table").appendChild(newNode);
		  }
	    }
	  }
	  var param="yes=insert&val="+val;
	  var url="<%=request.getContextPath()%>/zkKeyword";
		xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}

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

	function changeImg(src,a){
		document.getElementById("item_img").src=src;
		
		document.getElementById("item_bigimg").children[1].children[3].src=src;
		document.getElementById("item_bigimg").children[1].children[0].children[0].children[0].src=src;
		document.getElementById("item_preview").style.backgroundImage="url("+src+")";
		for(var i=0;i<document.getElementsByName("imgList").length;i++){
			document.getElementsByName("imgList")[i].style.opacity=1;
		}
		a.style.opacity=0.5;
	}

	var tmp1_num=0;
	var tmp2_num=0;
	var tmp3_num=0;
// function crawl(){
// 	var itemIds="";
// 	var itemIdsElement=document.getElementsByName("hid_itemId");
// 	for(var i=0;i<itemIdsElement.length;i++){
// 		var itemid=itemIdsElement[i].value;
		
		
// 		$.getJSON("http://pub.alimama.com/pubauc/searchAuctionList.json?q=http://item.taobao.com/item.htm?id="+itemid+"&callback=?", function(json) {
// 	        alert(json);
// 		});
		
		
// 	    $.ajax({ 
//             async:false, 
//             url: 'http://pub.alimama.com/pubauc/searchAuctionList.json?q=http://item.taobao.com/item.htm?id='+itemid,  // 跨域URL
//             type: 'GET', 
//             dataType: 'jsonp', 
//             jsonp: 'jsoncallback', //默认callback
//            // data: mydata, 
//             timeout: 5000, 
//             beforeSend: function(){  //jsonp 方式此方法不被触发。原因可能是dataType如果指定为jsonp的话，就已经不是ajax事件了
//             },
//             success: function (json) { //客户端jquery预先定义好的callback函数，成功获取跨域服务器上的json数据后，会动态执行这个callback函数 
//                 if(json.actionErrors.length!=0){ 
//                     alert(json.actionErrors); 
//                 } 
//                 alert(json);
//             },
//             complete: function(XMLHttpRequest, textStatus){ 
//                 $.unblockUI({ fadeOut: 10 }); 
//             }, 
//             error: function(xhr){ 
//                 //jsonp 方式此方法不被触发
//                 //请求出错处理 
//                 alert("请求出错(请检查相关度网络状况.)"); 
//             }
//         });
		
// 		if(i==itemIdsElement.length-1){
// 			itemIds=itemIds+itemid;
// 		}else{
// 			itemIds=itemIds+itemid+",";
// 		}
// 	}
	
// 	var xmlhttp;
// 	if (window.XMLHttpRequest)
// 	  {// code for IE7+, Firefox, Chrome, Opera, Safari
// 	  xmlhttp=new XMLHttpRequest();
// 	  }
// 	else
// 	  {// code for IE6, IE5
// 	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
// 	  }
// 	  xmlhttp.onreadystatechange=function()
// 	  {
// 	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
// 	    {
// 		  var val=xmlhttp.responseText;
// 		  alert("成功更新了 "+val+" 条");
// 	    }
// 	  }
<%-- 	  var url="<%=request.getContextPath()%>/crawl?itemIds="+itemIds; --%>
// 		xmlhttp.open("GET",url,true);
// 		xmlhttp.send();
// }

function add(){
	addtags();
	addkeywords();
	addHome();
}

function addAll(){
	 
	var itemval="{'item_list':[<%=request.getAttribute("itemval")%>]}";
	itemval=encodeURIComponent(itemval);
	var tags= document.getElementsByName("checkbox_tags");
	var keywords= document.getElementsByName("checkbox_keywords");
	var keywordsval="";
	for(var i=0;i<keywords.length;i++){
		if(keywords[i].checked){
			keywordsval=keywordsval+keywords[i].value.split("_")[0]+",";
		}
	}
	if(keywordsval.length>0){
		keywordsval=keywordsval.substring(0,keywordsval.length-1);
	}
	
	var tagsval="";
	for(var i=0;i<tags.length;i++){
		if(tags[i].checked){
				tagsval=tagsval+tags[i].value.split("_")[0]+",";
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
			  window.location.href="<%=request.getContextPath()%>/index.jsp?tabId=1&menuId=3&pagNow=<%=pagNow%>"; 
		  }
	    }
	  }
	  var homeval=0;
	  var home=document.getElementById("home");
		if(home.checked){
			homeval=1;
		}
		
	  var url2="<%=request.getContextPath()%>/wait";
	  var param="yes=insert&keywordsval="+keywordsval+"&tagsval="+tagsval+"&homeval="+homeval+"&content="+itemval;
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
}

function addtags(){
	
	var tags= document.getElementsByName("checkbox_tags");
	var itemval="{'item_list':[<%=request.getAttribute("itemval")%>]}";
	itemval.replace(/\&/g,"%26");
	itemval.replace(/\+/g,"%2B");
	var tagsval="";
	for(var i=0;i<tags.length;i++){
		if(tags[i].checked){
				tmp1_num++;
				tagsval=tagsval+tags[i].value+",";
		}
	}
	if(tagsval.length>0){
		tagsval=tagsval.substring(0,tagsval.length-1);
	}
	if(tagsval==""){
		tmp1=1;
		return;
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
			  tmp1_num=xmlhttp.responseText;
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
	var itemval="{\"item_list\":[<%=request.getAttribute("itemval")%>]}";
	itemval.replace(/\&/g,"%26");
	itemval.replace(/\+/g,"%2B");
	var keywordsval="";
	for(var i=0;i<keywords.length;i++){
		if(keywords[i].checked){
			tmp2_num++;
			keywordsval=keywordsval+keywords[i].value+",";
		}
	}
	if(keywordsval.length>0){
		keywordsval=keywordsval.substring(0,keywordsval.length-1);
	}
	if(keywordsval==""){
		tmp2=1;
		return;
	}
	
	
	var url="http://10.0.0.21:8080/Youhui/addcategoryitems?type=keywordItem&id="+keywordsval+"&content="+itemval;
	
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
			  tmp2_num=xmlhttp.responseText;
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
	var home=document.getElementById("home");
	if(!home.checked){
		alert("成功添加了"+tmp1_num+"条到主题，"+tmp2_num+"条到关键字"+tmp3_num+"条到主页！");
		return;
	}
	var itemval="{\"item_list\":[<%=request.getAttribute("itemval")%>]}";
	itemval.replace(/\&/g,"%26");
	itemval.replace(/\+/g,"%2B");
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
			  tmp3_num=xmlhttp.responseText;
			  alert("成功添加了"+tmp1_num+"条到主题，"+tmp2_num+"条到关键字"+tmp3_num+"条到主页！");
// 			  window.opener.location.reload();
// 			  window.close();
				window.history.back()
		  }
	    }
	  }
	  var url2="<%=request.getContextPath()%>/addtok";
	  var param="type=recoItem"+"&content="+itemval+"&id=0_1";
		xmlhttp.open("POST",url2,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
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
		  for(var i=0;i<val.split("*").length;i++){
			  var id=val.split("*")[i].split("_")[0];
			  var name=val.split("*")[i].split("_")[1];
			  var tmp=i+"_"+name;
			  if(i<val.split("*").length-1){
			 	 htm=htm+"<option>"+tmp+"</option>";
			  }
		  }
		  htm=htm+"</select>";
		  document.getElementById("td_k2").innerHTML=htm;
	    }
	  }
	  var param="k_1="+k_1;
	  var url="<%=request.getContextPath()%>/keywords"; 
		xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
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
		  var arrayObj = new Array();
		  for(var i=0;i<val.split("*").length;i++){
			  var id=val.split("*")[i].split("_")[0];
			  var name=val.split("*")[i].split("_")[1];
			  var tmp=i+"_"+name;
			  htm=htm+"<tr>"+"<td><input name='checkbox_keywords' type='checkbox' value='"+id+"_"+name+"'/>"+tmp+"</td>"+"</tr>";
		  }
		  document.getElementById("table_keywords").innerHTML=htm;
	    }
	  }
	  var param="k_1="+k_1+"&k_2="+k_2;
	  var url="<%=request.getContextPath()%>/keywords";
		xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
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
	  var param="k_1="+k_1+"&k_2="+k_2+"&k_3="+k_3;
	  var url="<%=request.getContextPath()%>/keywords";
		xmlhttp.open("POST",url,true);
		xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xmlhttp.send(param);
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
		  var param="yes=recom&val="+val+"&id="+id;
		  var url="<%=request.getContextPath()%>/update";
		  xmlhttp.open("POST",url,true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send(param);
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
		  var param="yes=remark&val="+val+"&id="+id;
		  var url="<%=request.getContextPath()%>/update";
		  xmlhttp.open("POST",url,true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send(param);
	}
	
	function update(id){
		
		  var url="<%=request.getContextPath()%>/update?yes=update&id="+id;
		  window.open(url);
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
			  for(var i=0;i<val.split("*").length;i++){
				  var id=val.split("*")[i].split("_")[0];
				  var name=val.split("*")[i].split("_")[1];
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
		  var param="k_1="+k_1+"&k_2="+k_2;
		  var url="<%=request.getContextPath()%>/keywords";
		  xmlhttp.open("POST",url,true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlhttp.send(param);
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
			  for(var i=0;i<val.split("*").length;i++){
				  
				  for(var z=0;z<5;z++){
					  if(i<val.split("*").length-1){
						  var id=val.split("*")[i].split("_")[0];
						  var name=val.split("*")[i].split("_")[1];
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
		  var param="k_1="+id1;
		  var url="<%=request.getContextPath()%>/keywords";
		  xmlhttp.open("POST",url,true);
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

</script>
</html>