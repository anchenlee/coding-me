<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
	<meta property="qc:admins" content="2371204445051636" />
	<link rel="stylesheet" type="css" href="css/style.css">
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript">
		var nextPage;
		var thisActionType = "";
		var thisCatID = "";
		var thisCatName = "";
		
		function loadBody()
		{
			$.ajax({
				async: false,
			    url: '<%=request.getContextPath()%>/tyh2/alltag',
			    type: 'GET',
			    dataType: 'xml',
			    timeout: 1000,
			    success: function(xml)
			    {
			    	$(xml).find("data").find("categorys").find("category").find("tags").find("tag").each(function(i)
			    	{
			    		var idStr = $(this).children("id").text();
			    		var nameStr = $(this).children("name").text();
		    			var actionElement = $(this).children("action");
		    			var actiontypeStr = $(actionElement).children("actiontype").text();
		    			var actionvalueStr = $(actionElement).children("actionvalue").text();
		    			// alert(idStr + ":" + actionvalueStr);
		    			if (idStr == actionvalueStr)
		    			{
		    				thisActionType = actiontypeStr;
			    			thisCatID = actionvalueStr;
			    			thisCatName = nameStr;
			    			
			    			var li_element = "<a onclick=\"changeCat('" + actionvalueStr + "', '" + actiontypeStr + "', '" + nameStr + "')\"><span>" + nameStr + "</span></a>";
			    			$("#cat-category-li").append(li_element);
		    			}
			    	});
			    }
			});
			
			loadItems(1, 1);
		}
		
		function loadItems(pageNo, flag)
		{
			var requestURL = "<%=request.getContextPath()%>/tyh2/tagitems?page=" + pageNo + "&tagid=" + thisCatID + "&type=" + thisActionType;
			
			$.ajax({
				async: false,
			    url: requestURL,
			    type: 'GET',
			    dataType: 'xml',
			    timeout: 1000,
			    success: function(xml)
			    {
			    	$(xml).find("data").find("items").find("item").each(function(i)
			    	{
			    		var item_idStr = $(this).children("item_id").text();
			    		var titleStr = $(this).children("title").text();
			    		var img_big_url = $(this).children("img_big").text();
			    		var img_small_url = $(this).children("img_small").text();
			    		var originalPrice = $(this).children("price").attr("phi") / 100;
			    		var activityPrice = $(this).children("price").attr("plow") / 100;
			    		var click_url = $(this).children("click").text();
			    		
			    		$("#J_JuList").append("<li><a href=\"" + click_url + "\" target=\"_blank\"><div class=\"item-pic\"><img src=\""
			    				+ img_big_url + "\" width=\"135\" height=\"90\" class=\"\"/></div><div class=\"item-info\"><h3 class=\"good-name\">"
			    				+ titleStr + "</h3><div class=\"pirce\"><b class=\"promote-price\">￥" + activityPrice + "</b><span class=\"cost-price\">￥<del>" 
			    				+ originalPrice + "</del></span></div><div class=\"buy-now\"><p class=\"status purchase\">抢购中</p></div></div></a></li>");
			    	});
			    	
			    	$("#tag_name").text(thisCatName);
			    	
			    	if (flag == 1)
			    	{
			    		var tagsElements = $(xml).find("data").find("tags");
				    	if (tagsElements.find("tag").length > 0)
				    	{
				    		tagsElements.find("tag").each(function(i)
					    	{
					    		var idStr = $(this).children("id").text();
					    		var nameStr = $(this).children("name").text();
				    			var actionElement = $(this).children("action");
				    			var actiontypeStr = $(actionElement).children("actiontype").text();
				    			var actionvalueStr = $(actionElement).children("actionvalue").text();
				    			
				    			if (idStr == actionvalueStr)
				    			{
					    			var li_element = "<a onclick=\"changeTag('" + actionvalueStr + "', '" + actiontypeStr + "', '" + nameStr + "')\"><span>" + nameStr + "</span></a>";
					    			$("#tag-category-li").append(li_element);
				    			}
					    	});
				    		eval("tagListDIV.style.display=\"block\"");
				    	}
			    	}
			    	
			    	var pageNow = $(xml).find("head").find("page").text();
			    	var pageTotal = $(xml).find("head").find("total").text();
			    	
			    	if (pageNow < pageTotal)
			    	{
			    		eval("footToolBar.style.display=\"\"");
			    		nextPage = parseInt(pageNow) + 1;
			    	}
			    	else
			    	{
			    		eval("footToolBar.style.display=\"none\"");
			    	}
			    	
			    	eval("leftCate.style.display=\"none\"");
			    	eval("rightCate.style.display=\"none\"");
			    }
			});
		}
		
		function loadNextPage()
		{
			// $("#tag-category-li").empty();
			loadItems(nextPage, 0);
		}
		
		function closeTagDiv()
		{
			eval("rightCate.style.display=\"none\"");
		}
		
		function showTagDiv()
		{
			eval("rightCate.style.display=\"block\"");
		}
		
		function closeCatDiv()
		{
			eval("leftCate.style.display=\"none\"");
		}
		
		function showCatDiv()
		{
			eval("leftCate.style.display=\"block\"");
		}
		
		function changeCat(catID, actiontype, catName)
		{
			thisCatID = catID;
			thisActionType = actiontype;
			thisCatName = catName;
			
			$("#J_JuList").empty();
			$("#tag-category-li").empty();
			eval("tagListDIV.style.display=\"none\"");
			
			eval("footToolBar.style.display=\"none\"");
			loadItems(1, 1);
		}
		
		function changeTag(tagID, actiontype, catName)
		{
			thisCatID = tagID;
			thisActionType = actiontype;
			thisCatName = catName;
			
			$("#J_JuList").empty();
			// $("#tag-category-li").empty();
			// eval("tagListDIV.style.display=\"none\"");
			
			eval("footToolBar.style.display=\"none\"");
			loadItems(1, 0);
		}

	</script>
	<title>随手优惠</title>
</head>
<body screen_capture_injected="true" onload="loadBody()">
	<div class="toolbar">
		<div class="shop-container">
			<div class="title-container">
				<span class="title" id="tag_name">随手优惠</span>
			</div>
			<div class="nav_left_button c-btn " id="tagListDIV" onclick="showTagDiv()" style="display: none;">全部</div>
			<div class="nav_right_button c-btn " id="localCityDIV" onclick="showCatDiv()">类目</div>
		</div>
	</div> 
	<div id="content">
		<div class="list-wrap">
			<div class="list-block">
				<div class="bd">
					<ul class="list" id="J_JuList">
					
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="toolbar" id="footToolBar" style="display:none" onclick="loadNextPage()">
		<div class="shop-container">
			<div  class="title" id="nextPageDIV" >
				下一页
			</div>
		</div>
	</div>
	
	<div id="leftCate" class="none" style="display: none; height: 7054px;">
		<div id="swipeTop" class="c-tcate" style="-webkit-backface-visibility: hidden; -webkit-transform-style: preserve-3d; -webkit-transition: -webkit-transform 0.4s; transition: -webkit-transform 0.4s; -webkit-transform-origin: 0px 0px; -webkit-transform: translate(0, 0);">                        
			<div class="c-tab">
				<ul class="tab-2">
					<li class="cur">
					选择类目信息
					</li>
					<li>
						<div id="closeCatDiv" onclick="closeCatDiv()">关闭</div>
					</li>
				</ul>
			</div>
			<div id="swipeTopCont" class="c-tcate-cont">
			<div id="swipeCont-1" class="c-tcate-cont-div">
			<div>
			<ul class="c-list-ext-group" id="city-category">
				<li id="cat-category-li">
					
				</li>
			</ul>
			</div>
			</div>
			</div>
	</div>
	</div>
	
	<div id="rightCate" class="none" style="display: none; height: 7054px;">
		<div id="right-swipeTop" class="c-tcate" style="-webkit-backface-visibility: hidden; -webkit-transform-style: preserve-3d; -webkit-transition: -webkit-transform 0.4s; transition: -webkit-transform 0.4s; -webkit-transform-origin: 0px 0px; -webkit-transform: translate(0, 0);">                        
			<div class="c-tab">
				<ul class="tab-2">
					<li class="cur">
					选择标签
					</li>
					<li>
						<div id="closeTagDiv" onclick="closeTagDiv()">关闭</div>
					</li>
				</ul>
			</div>
			<div id="right-swipeTopCont" class="c-tcate-cont">
			<div id="right-swipeCont-1" class="c-tcate-cont-div">
			<div>
			<ul class="c-list-ext-group" id="cat-category">
				<li id="tag-category-li">
					
				</li>
			</ul>
			</div>
			</div>
			</div>
	</div>
	</div>
	<script>
		var _hmt = _hmt || [];
		(function() {
		  	var hm = document.createElement("script");
		  	hm.src = "//hm.baidu.com/hm.js?b57349b420682be6e5a9a11ed1c356a3";
		  	var s = document.getElementsByTagName("script")[0]; 
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
</body>
</html>
