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
	$(function() {
		var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
		var len = $("#focus ul li").length; //获取焦点图个数
		var index = 0;
		var picTimer;
		
		//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
		var btn = "<div class='btnBg'></div><div class='btn'>";
		for(var i=0; i < len; i++) {
			btn += "<span></span>";
		}
		btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
		$("#focus").append(btn);
		$("#focus .btnBg").css("opacity",0.5);
	
		//为小按钮添加鼠标滑入事件，以显示相应的内容
		$("#focus .btn span").css("opacity",0.4).mouseover(function() {
			index = $("#focus .btn span").index(this);
			showPics(index);
		}).eq(0).trigger("mouseover");
	
		//上一页、下一页按钮透明度处理
		$("#focus .preNext").css("opacity",0.2).hover(function() {
			$(this).stop(true,false).animate({"opacity":"0.5"},300);
		},function() {
			$(this).stop(true,false).animate({"opacity":"0.2"},300);
		});
	
		//上一页按钮
		$("#focus .pre").click(function() {
			index -= 1;
			if(index == -1) {index = len - 1;}
			showPics(index);
		});
	
		//下一页按钮
		$("#focus .next").click(function() {
			index += 1;
			if(index == len) {index = 0;}
			showPics(index);
		});
	
		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
		$("#focus ul").css("width",sWidth * (len));
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$("#focus").hover(function() {
			clearInterval(picTimer);
		},function() {
			picTimer = setInterval(function() {
				showPics(index);
				index++;
				if(index == len) {index = 0;}
			},2000); //此4000代表自动播放的间隔，单位：毫秒
		}).trigger("mouseleave");
		
		//显示图片函数，根据接收的index值显示相应的内容
		function showPics(index) { //普通切换
			var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
			$("#focus ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
			//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
			$("#focus .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
		}
	});

</script>
	
	<script type="text/javascript">
		var b17_url = "http://b17.cn/item?itemid=";
		var nextPage;
		var thisCityCode = "239";
		var thisCityName =  "北京";
		var thisCatID = "48000";
		
		function loadBody()
		{
			$.ajax({
				async: false,
			    url: '<%=request.getContextPath()%>/tyh2/citys',
			    type: 'GET',
			    dataType: 'xml',
			    timeout: 1000,
			    success: function(xml)
			    {
			    	$(xml).find("data").find("citylist").find("a").each(function(i)
			    	{
			    		var li_element = "";
			    		var a = $(this).attr("char");
			    		li_element = li_element + "<li><span class=\"index\">" + a +"</span>";
			    		
			    		$(this).find("city").each(function(i){
			    			var codeStr = $(this).children("code").text();
			    			var nameStr = $(this).children("name").text();
			    			li_element = li_element + "<a onclick=\"changeCity('" + codeStr + "','" + nameStr + "')\"><span>" + nameStr + "</span></a>";
			    		});
			    		
			    		li_element = li_element + "</li>";
			    		$("#city-category").append(li_element);
			    	});
			    	
			    	if (thisCityCode == "" || thisCityName == "")
			    	{
			    		var localElement = $(xml).find("data").find("local");
			    		var localCity = $(localElement).children("city");
			    		var localCityCodeStr = $(localCity).children("code").text();
		    			var localCityNameStr = $(localCity).children("name").text();
		    			if (localCity != null || localCityCodeStr != null || localCityNameStr != null || localCityCodeStr != "" || localCityNameStr != "")
		    			{
		    				thisCityCode = localCityCodeStr;
			    			thisCityName = localCityNameStr;
		    			}
			    	}
	    			
	    			$("#localCityDIV").text(thisCityName);
			    }
			});
			
			loadItems(1);
		}
		
		function loadItems(pageNo)
		{
			var requestURL = "<%=request.getContextPath()%>/tyh2/citygroup?pageNo=" + pageNo + "&cityCode=" + thisCityCode + "&catId=" + thisCatID;
			
			$.ajax({
				async: false,
			    url: requestURL,
			    type: 'GET',
			    dataType: 'xml',
			    timeout: 1000,
			    success: function(xml)
			    {
			    	$(xml).find("data").find("citygroup").find("item").each(function(i){
			    		var itemidStr = $(this).children("itemid").text();
			    		var subNameStr = $(this).children("subName").text();
			    		var picWideUrl = $(this).children("picWideUrl").text();
			    		var soldCount = $(this).children("soldCount").text();
			    		var originalPrice = $(this).children("originalPrice").text();
			    		var activityPrice = $(this).children("activityPrice").text();
			    		
			    		$("#J_JuList").append("<li><a href=\"" + b17_url + itemidStr + "\"><div class=\"item-pic\"><img src=\""
			    				+ picWideUrl + "\" width=\"135\" height=\"90\" class=\"\"/></div><div class=\"item-info\"><h3 class=\"good-name\">"
			    				+ subNameStr + "</h3><div class=\"pirce\"><b class=\"promote-price\">￥" + activityPrice + "</b><span class=\"cost-price\">￥<del>" 
			    				+ originalPrice + "</del></span></div><div class=\"buy-now\"><span class=\"buy-count\">" + soldCount + "人已购买</span><p class=\"status purchase\">抢购中</p></div></div></a></li>");
			    	});
			    	
			    	$("#localCityDIV").text(thisCityName);
			    	
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
			    }
			});
		}
		
		function loadNextPage()
		{
			loadItems(nextPage);
		}
		
		function closeCityDiv()
		{
			eval("leftCate.style.display=\"none\"");
		}
		
		function showCityDiv()
		{
			eval("leftCate.style.display=\"block\"");
		}
		
		function changeCat(catID)
		{
			thisCatID = catID;
			
			$("#J_JuList").empty();
			eval("footToolBar.style.display=\"none\"");
			loadItems(1);
		}
		
		function changeCity(cityCode, cityName)
		{
			thisCityCode = cityCode;
			thisCityName = cityName;
			
			$("#J_JuList").empty();
			eval("footToolBar.style.display=\"none\"");
			loadItems(1);
		}

	</script>
	<title>七夕随手优惠专场</title>
</head>
<body screen_capture_injected="true" onload="loadBody()">
	<div class="toolbar">
		<div class="shop-container">
			<div class="title-container">
				<span class="title">生活汇</span>
			</div>
			<div class="nav_right_button c-btn " id="localCityDIV" onclick="showCityDiv()"></div>
		</div>
	</div> 
	<div class="wrapper">
		<div id="focus">
			<ul>
				<li onclick="changeCat(48000)"><img width="320px" height="134px" src="css/imgs/step1.jpg" alt="" /></li>
				<li onclick="changeCat(30000)"><img width="320px" height="134px" src="css/imgs/step2.jpg" alt="" /></li>
				<li onclick="changeCat(59000)"><img width="320px" height="134px" src="css/imgs/step3.png" alt="" /></li>
			</ul>
		</div><!--focus end-->
	</div>
	<div id="content">
		<div class="in-commt"> 
			<ul>                  
				<li onclick="changeCat(48000)">
				<a href="#">晚餐</a>
				</li>                 
				<li onclick="changeCat(30000)">
				<a href="#">电影</a>
				</li>
				<li onclick="changeCat(59000)">
				<a href="#">酒店</a>
				</li>
			</ul>
		</div>
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
					选择城市信息
					</li>
					<li>
						<div id="closeCityDiv" onclick="closeCityDiv()">关闭</div>
					</li>
				</ul>
			</div>
			<div id="swipeTopCont" class="c-tcate-cont">
			<div id="swipeCont-1" class="c-tcate-cont-div">
			<div>
			<ul class="c-list-ext-group" id="city-category">
			
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
