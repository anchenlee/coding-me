<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>商品列表-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
    
    <script type="text/javascript">
	    function goto_del_item()
		{
	    	var item_ids = $("#item_ids").val();
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	
	    	$(".idcheck").each(function() 
   			{
   				if ($(this).attr("checked")) 
   				{
   					var checkedID = $(this).val();
   					if (checkedID !=null && checkedID != "")
   					{
   						if (item_ids != null && item_ids != "")
   						{
   							item_ids = item_ids + "," + checkedID;
   						}
   						else
   						{
   							item_ids = checkedID;
   						}
   					}
   				}
	        });
	    	if (item_ids == null || item_ids == "")
	    	{
	    		alert("您尚未选择要删除的商品!");
				return false;
	    	}
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=delTagItem',
    		    type: 'POST',
    		    data: {
    		    			item_ids:item_ids,
    		    			tag_id:tag_id,
    		    			tag_name:tag_name
    		    			},
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=${tag_id}&page=${page}&tag_name=${tag_name}";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("删除操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
		}
	    
	    // 更新商品的折扣价格
	    function refresh_item_price()
	    {
	    	var tag_id = "${tag_id}";
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=updateTagItemPrice',
    		    type: 'POST',
    		    data: {
    		    			tag_id:tag_id
    		    			},
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=${tag_id}&page=${page}&tag_name=${tag_name}";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		// alert("更新价格操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
	    }
	    
	    // 刷新商品列表(删除已经不能存在或下架的商品)
	    function refresh_item()
	    {
	    	var tag_id = "${tag_id}";
	    	
	    	$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=refreshTagItem',
    		    type: 'POST',
    		    data: {
    		    			tag_id:tag_id
    		    			},
    		    dataType: 'json',
    		    timeout: 5000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=${tag_id}&page=${page}&tag_name=${tag_name}";
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		// alert("刷新操作失败!");
    		    		return false;
    		    	}
    		    }
    		});
	    }
	    
	    function goto_add_item()
		{
	    	var item_ids = $("#item_ids").val();
	    	var tag_id = "${tag_id}";
	    	var tag_name = "${tag_name}";
	    	if(item_ids == ""){
	    		alert("你还未填写要添加的商品ID！");
	    		return false;
	        }else{
	            if(confirm("你确定要添加这个商品吗？")){
	            	$.ajax({
	        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addTagItem',
	        		    type: 'POST',
	        		    data: {
	        		    			item_ids:item_ids,
	        		    			tag_id:tag_id,
	        		    			tag_name:tag_name
	        		    			},
	        		    dataType: 'json',
	        		    timeout: 5000,
	        		    success: function(json)
	        		    {
	        		    	var result = json.result;
	        		    	if (result == "0")
	       		    		{
	        		    		var url = "<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemList&tag_id=${tag_id}&page=${page}&tag_name=${tag_name}";
	        					location.href=url;
	       		    		}
	        		    	else if (result == "1")
	        		    	{
	        		    		alert("添加操作失败!");
	        		    		return false;
	        		    	}
	        		    }
	        		});
	            	} else {
	    				return false;
	    			}
	    		}
	    	location.href="<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&parent=${parent}&status=${status}&ispad=" + ispadtag;
		}
	    
	    function addGoods(){
	    	var content = "";   
	    	$(".idcheck").each(function() {

	            if ($(this).attr("checked")) {
	                content +=$(this).val()+",";
	            }

	        });

	    	if(content == ""){
	    		alert("你还未选择要添加的列！");
	    		return false;
	        }else{
	            if(confirm("你确定要添加这些数据到 ${keyword} 类别下吗？")){
	    		$("#selGoods").val(content); 
	    		$("#selGoods2").val(content); 
	    		
	    		$.ajax({
        		    url: '<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addTagItemNet&keyword=${keyword}&tagid=${tagid}&startPrice=${startPrice}&endPrice=${endPrice}&startCredit=${startCredit}&endCredit=${endCredit}&startCommissionRate=${startCommissionRate}&endCommissionRate=${endCommissionRate}&page=${page}&pageSize=${totalpage}&sort=${sort}&selGoods='+content,
        		    type: 'POST',
        		    data: {
        		    			
        		    			},
        		    dataType: 'json',
        		    timeout: 5000,
        		    success: function(json)
        		    {
        		    	var result = json.result;
        		    	if (result == "0")
       		    		{
        		    		location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemListFromTaobao&keyword=${keyword}&tagid=${tagid}&status=${status}&page=${page}&coup=${coup}&parent=${parent}&catId=${catId}&startCouponRate=${startCouponRate}&endCouponRate=${endCouponRate}&sort=${sort}&startPrice=${startPrice}&endPrice=${endPrice}&startCredit=${startCredit}&endCredit=${endCredit}&startCommissionRate=${startCommissionRate}&endCommissionRate=${endCommissionRate}";			
       		    	    
       		    		}
        		    	else if (result == "1")
        		    	{
        		    		alert("添加操作失败!");
        		    		return false;
        		    	}
        		    }
        		});
	    		
	    		return true;
	            }else{
	    			return false;
	            }
	        }
	    }
	    
	    function search(){
	    	var key = $("#key").val();
	    	if(key !=""){
	    		location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemListFromTaobao&keyword="+key+"&status=${status}&tagid=${tagid}&page=${page}&coup=${coup}&parent=${parent}&catId=${catId}&startCouponRate=${startCouponRate}&endCouponRate=${endCouponRate}&sort=${sort}&startPrice=${startPrice}&endPrice=${endPrice}&startCredit=${startCredit}&endCredit=${endCredit}&startCommissionRate=${startCommissionRate}&endCommissionRate=${endCommissionRate}";			
		    	    
		    		   
	    	}else{
	    		alert('搜索不能为空');
	    	}
	    }
	    $(function(){     
	    	$(".grayTips") //所有样式名中含有grayTips的input     
	    	.each(function(){     
	    	   var oldVal=$(this).val();   //默认的提示性文本     
	    	   $(this)     
	    	   .css({"color":"#888"})  //灰色     
	    	   .focus(function(){     
	    	    if($(this).val()!=oldVal){$(this).css({"color":"#000"})}else{$(this).val("").css({"color":"#888"})}     
	    	   })     
	    	   .blur(function(){     
	    	    if($(this).val()==""){$(this).val(oldVal).css({"color":"#888"})}     
	    	   })     
	    	   .keydown(function(){$(this).css({"color":"#000"})})     
	    	})     
	    	});
	    function showCoupon(){ document.getElementById("couponButton").style.display = "";}
	    function showPbt(){ document.getElementById("priceButton").style.display = "";}
	    function showCrbt(){ document.getElementById("creditButton").style.display = "";}
	    function showCbt(){ document.getElementById("commissionRButton").style.display = "";}
	    
	    function changeCoupon() {
	    	var type = $("#type").val();
	    	if(type==1){	
	    		document.getElementById("startCouponRate").style.display = "";
	    		document.getElementById("endCouponRate").style.display = "";
	    		document.getElementById("zhekoubili").style.display = "";
	    		document.getElementById("startPrice").style.display = "none";
	    		document.getElementById("endPrice").style.display = "none";
	    		document.getElementById("price").style.display = "none";
	    	}
	    	else if(type==0){	
	    		document.getElementById("startCouponRate").style.display = "none";
	    		document.getElementById("endCouponRate").style.display = "none";
	    		document.getElementById("zhekoubili").style.display = "none";
	    		document.getElementById("startPrice").style.display = "";
	    		document.getElementById("endPrice").style.display = "";
	    		document.getElementById("price").style.display = "";
	    		}
	    }
	    
	    function tiaojianchaxun(){
	    	var keyword = $("#key").val(); 
	    	var startCouponRate = $("#startCouponRate").val();
	    	var endCouponRate = $("#endCouponRate").val();
	    	var startPrice = $("#startPrice").val();
	    	var endPrice = $("#endPrice").val();
	    	var startCredit = $("#startCredit").val();
	    	var endCredit = $("#endCredit").val();
	    	var startCommissionRate = $("#startCommissionRate").val();
	    	var endCommissionRate = $("#endCommissionRate").val();
	    	var sort = $("#sort").val();
	    	if(keyword=='') keyword = ""; if(startCouponRate=='') startCouponRate = "";if(endCouponRate=='') endCouponRate = "";if(startPrice=='') startPrice = "";if(sort=='') sort = "";
	    	if(endPrice=='') endPrice = "";if(startCredit=='') startCredit = "";if(endCredit=='') endCredit = "";if(startCommissionRate=='') startCommissionRate = "";if(endCommissionRate=='') endCommissionRate = "";
	    	location.href="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=showTagItemListFromTaobao&keyword="+keyword+"&status=${status}&tagid=${tagid}&page=${page}&coup=${coup}&parent=${parent}&catId=${catId}&startCouponRate="+startCouponRate+"&endCouponRate="+endCouponRate+"&sort="+sort+"&startPrice="+startPrice+"&endPrice="+endPrice+"&startCredit="+startCredit+"&endCredit="+endCredit+"&startCommissionRate="+startCommissionRate+"&endCommissionRate="+endCommissionRate;		
	    	   
	    }
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
         <form action="<%=request.getContextPath()%>/ad/tag_item_manager?actionmethod=addTagItem&keyword=${keyword}&tagid=${tagid}&startPrice=${startPrice}&endPrice=${endPrice}
				&startCredit=${startCredit}&endCredit=${endCredit}&startCommissionRate=${startCommissionRate}&endCommissionRate=${endCommissionRate}
				&page=${page}&pageSize=${totalpage}&sort=${sort}" method="post">
				<input type="hidden" id="selGoods" name="selGoods" />
				<input type="hidden" name="catId" value="${catId }" />
				<input type="hidden" name="parent" value="${parent}" />
				<input type="button" onclick="addGoods();" value="添加所选商品到  ${keyword} 标签下" />
		</form>
	         <hr/>
	         <form action="#" method="post" >
	         <span>搜索</span><input type="text" class="grayTips" value="${keyword}" name="keyword" id="key"> <input type="button" value="搜索" onclick="search()">
	        </form>
	        <form id="changeForm" action="" method="get">

	   <table width="100%" bgcolor="#FFFFFF"><tr>
		<td style="vertical-align: top;vertical-align: left;">
		<label></label>
	    <c:choose>
			<c:when test="${coup == 1}">
				<select id="type" onChange="changeCoupon()" style="padding: 5px;">
					<option value="1" selected>使用折扣</option>
					<option value="0" >不使用折扣</option>
				</select>
				<label id="zhekoubili" style="display: ">折扣比例</label>
				<input type="text" id="startCouponRate"  name="startCouponRate" value="" style="display:  ; width: 40px">
				<input type="text" id="endCouponRate" name="endCouponRate" value="" onfocus="showCoupon()" style="display:  ; width: 40px">
			</c:when>
			<c:otherwise>
				<select id="type" onChange="changeCoupon()" style="padding: 5px;">
					<option value="1" >使用折扣</option>
					<option value="0" selected>不使用折扣</option>
				</select>
				<label id="zhekoubili" style="display: none">折扣比例</label>
				<input type="text" id="startCouponRate"  name="startCouponRate" value="" style="display: none ; width: 40px">
				<input type="text" id="endCouponRate" name="endCouponRate" value="" onfocus="showCoupon()" style="display: none ; width: 40px">
			</c:otherwise>
	</c:choose>
	<input type="button" id="couponButton" value="确定" onclick="tiaojianchaxun()" style="display: none"/>
	</td>
	<td style="vertical-align: top;vertical-align: left;">
	    <label>排序</label>
	    <select id="sort" name="paixu" onChange="tiaojianchaxun()" style="padding: 5px;">
		    <c:forEach var="paixu" items="${sortMap}">
			    <c:choose>
					<c:when test="${sort == paixu.key}">
						<option value="${paixu.key}" selected>${paixu.value}</option>
					</c:when>
					<c:otherwise>
						<option value="${paixu.key}">${paixu.value}</option>
					</c:otherwise>
				</c:choose>
		    </c:forEach>
		</select>
    </td>
    <td>
	    <c:choose>
			<c:when test="${coup ==1}">
				<label id="price" style="display: none">价格</label>
				<input type="text" id="startPrice" name="startPrice" value="${startPrice}" style="display: none ; width: 40px">-
			    <input type="text" id="endPrice" name="endPrice" value="${endPrice}" onfocus="showPbt()" style="display: none ; width: 40px"><br>
			   	<input type="button" id="priceButton" value="确定" onclick="tiaojianchaxun()" style="display: none"/>
			</c:when>
			<c:otherwise>
				<label id="price" style="display: ">价格</label>
				<input type="text" id="startPrice" name="startPrice" value="${startPrice}" style="display:  ; width: 40px">-
			    <input type="text" id="endPrice" name="endPrice" value="${endPrice}" onfocus="showPbt()" style="display:  ; width: 40px"><br>
			   	<input type="button" id="priceButton" value="确定" onclick="tiaojianchaxun()" style="display: none"/>
			</c:otherwise>
		</c:choose>
	</td>
    <td >
	    <label>信用等级</label>
	    <select id="startCredit" name="startCredit" style="padding: 5px;">
	        <option value=""></option>
	        <c:forEach var="xinyong" items="${xinyongMap}">
			    <c:choose>
					<c:when test="${startCredit == xinyong.key}">
						<option value="${xinyong.key}" selected>${xinyong.value}</option>
					</c:when>
					<c:otherwise>
						<option value="${xinyong.key}">${xinyong.value}</option>
					</c:otherwise>
				</c:choose>
		    </c:forEach>
		</select>-
	     <select id="endCredit" name="endCredit" onfocus="showCrbt()" style="padding: 5px;">
	        <option value=""></option>
	        <c:forEach var="xinyong" items="${xinyongMap}">
	    <c:choose>
		<c:when test="${endCredit == xinyong.key}">
		<option value="${xinyong.key}" selected>${xinyong.value}</option>
		</c:when>
		<c:otherwise>
		<option value="${xinyong.key}">${xinyong.value}</option>
		</c:otherwise>
		</c:choose>
	    </c:forEach>
			</select>
	    <br>
	    <input type="button" id="creditButton" value="确定" onclick="tiaojianchaxun()" style="display: none"/>
    </td>
    <td >
    <label>佣金比率</label>
    <input type="text" id="startCommissionRate" value="${startCommissionRate}" name="startCommissionRate" style="width: 40px"/>-
    <input type="text" id="endCommissionRate" value="${endCommissionRate}" name="endCommissionRate" onfocus="showCbt()" style="width: 40px"/><br>
    <input type="button" id="commissionRButton" value="确定" onclick="tiaojianchaxun()" style="display: none"/>
    </td>
   </tr></table>
  </form>
		    <hr/>
			<table class="tablewidget" width="100%"> 
	    		<thead class="title">
	    			<tr>
	    				<th style="width: 5px"><input type="checkbox" id="check_all" name="check_all" /></th>
	    			    <th style="width: 100px">ID</th>
	    			    
	    				<th>名称</th>
	    				<th>图片</th>
	    				<th>价格</th>
			<th style="width: 10%">月销量</th>
			<th style="width: 10%">佣金</th>
			<th style="width: 10%">佣金比率</th>
			<th style="width: 10%">月累计推广量</th>
			<th style="width: 10%">信用等级</th>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach var="item" items="${ItemList}">
	                 <tr>
	                 	<td><input type="checkbox" class="idcheck" value="${item.numIid}" /></td>
	                 	<td>${item.numIid}</td>
	                 	
                       	<td width="150px;"><a target="_blank" href="<%=request.getContextPath() %>/admin/tag_manager/discount_product_taobao.jsp?itemid=${item.numIid}">${item.title}</a></td>
						<td>
	                       	<a rel="slide" target="_blank" href="${item.picUrl}" title="${item.title}">
	                       		<img width="75px" height="60px" src="${item.picUrl}">
	                       	</a>
                       	</td>
                       	<td>${item.price}</td>
                       	<td>${item.volume}</td>
                       	<td>${item.commission}</td>
                       	<td>${item.commissionRate}</td>
                       	<td>${item.commissionNum}</td>
                       	<td>
              <c:forEach var="xinyongIcon" items="${xinyongIconMap}">
    				<c:choose>
						<c:when test="${item.sellerCreditScore == xinyongIcon.key}">
						<img src="${xinyongIcon.value}"/> 
						</c:when>
					</c:choose>
    		</c:forEach>
                    </td>                
	                </tr>
                   </c:forEach>
	    		</tbody>
	    	</table>
	    	
	      	<div class="pagein" align="center">
              <form name="PageForm"  action="<%=request.getContextPath()%>/ad/tag_item_manager">
              	<input type="hidden" name="tagid" value="${tagid}">
                <input type="hidden" name="actionmethod" value="showTagItemListFromTaobao">
                <input type="hidden" name="keyword" value="${keyword}">
                <input type="hidden" name="status" value="${status}">
                <input type="hidden" name="coup" value="${coup}">
                <input type="hidden" name="parent" value="${parent}">
                <input type="hidden" name="catId" value="${catId}">
                <input type="hidden" name="startCouponRate" value="${startCouponRate}">
                <input type="hidden" name="endCouponRate" value="${endCouponRate}">
                <input type="hidden" name="sort" value="${sort}">
                <input type="hidden" name="startPrice" value="${startPrice}">
                <input type="hidden" name="endPrice" value="${endPrice}">
                <input type="hidden" name="startCredit" value="${startCredit}">
                <input type="hidden" name="endCredit" value="${endCredit}">
                <input type="hidden" name="startCommissionRate" value="${startCommissionRate}">
                <input type="hidden" name="endCommissionRate" value="${endCommissionRate}">
                <jsp:include page="../include/page.jsp"></jsp:include>
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>