<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>数据统计-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jqplot/jquery.jqplot.min.css">
    
    <jsp:include page="../include/css_js.jsp"></jsp:include>
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.img.preload.js"></script> 
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/jquery.jqplot.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/shCore.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/shBrushXml.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/shBrushXml.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/plugins/jqplot.highlighter.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/plugins/jqplot.cursor.min.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/plugins/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/plugins/jqplot.canvasAxisTickRenderer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/plugins/jqplot.barRenderer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqplot/plugins/jqplot.pointLabels.min.js"></script>
    <script type="text/javascript">
    function change()
    {
    	var type = $("#type").val();
    	if(type=='itemTrade')
    	{
    		 eval("itemid.style.display=\"\"");
    		 eval("target.style.display=\"none\"");
    		 eval("pingtai.style.display=\"none\"");
    		 eval("jineleixing.style.display=\"none\"");
    		 eval("jiaoyileixing.style.display=\"none\"");
    		 eval("yonghuId.style.display=\"none\"");
    	}
    	else if(type=='targetItemTrade'||type=='targetItemClick')
    	{
    		eval("jineleixing.style.display=\"none\"");
    		eval("itemid.style.display=\"none\"");
   		 	eval("target.style.display=\"\"");
   		 	eval("pingtai.style.display=\"none\"");
   		 	eval("jiaoyileixing.style.display=\"none\"");
   		 	eval("yonghuId.style.display=\"none\"");
    	}
    	else if(type=='ItemOrderTotalTongji'||type=='jifenbao')
    	{
    		eval("jineleixing.style.display=\"none\"");
    		eval("itemid.style.display=\"none\"");
   		 	eval("target.style.display=\"none\"");
   		 	eval("pingtai.style.display=\"none\"");
   		 	eval("jiaoyileixing.style.display=\"none\"");
   		 	eval("yonghuId.style.display=\"none\"");
    	}
    	else if(type=='itemJine')
    	{
    		eval("jineleixing.style.display=\"\"");
    		eval("itemid.style.display=\"none\"");
   		 	eval("target.style.display=\"\"");
   		 	eval("pingtai.style.display=\"none\"");
   		 	eval("jiaoyileixing.style.display=\"none\"");
   		 	eval("yonghuId.style.display=\"none\"");
    	}
    	else if(type=='userRegister')
    	{
    		eval("jineleixing.style.display=\"none\"");
    		eval("itemid.style.display=\"none\"");
   		 	eval("target.style.display=\"none\"");
   		 	eval("pingtai.style.display=\"\"");
   		 	eval("jiaoyileixing.style.display=\"none\"");
   		 	eval("yonghuId.style.display=\"none\"");
    	}
    	else if(type=='newUserTrade')
    	{
    		eval("jineleixing.style.display=\"none\"");
    		eval("itemid.style.display=\"none\"");
   		 	eval("target.style.display=\"none\"");
   		 	eval("pingtai.style.display=\"none\"");
   		 	eval("jiaoyileixing.style.display=\"\"");
   		 	eval("yonghuId.style.display=\"none\"");
    	}
    	else if(type=='userFanxian')
    	{
    		eval("jineleixing.style.display=\"none\"");
    		eval("itemid.style.display=\"none\"");
   		 	eval("target.style.display=\"none\"");
   		 	eval("pingtai.style.display=\"none\"");
   		 	eval("jiaoyileixing.style.display=\"none\"");
   		 	eval("yonghuId.style.display=\"\"");
    	}
    }
    
    function selectType()
    {
    	var type = $("#itemtype").val();
    	if(type=='target')
    	{
    		eval("itemid.style.display=\"none\"");
    		eval("target.style.display=\"\"");
    	}
    	else if(type=='item')
    	{
    		eval("itemid.style.display=\"\"");
    		eval("target.style.display=\"none\"");
    	}
    	else if(type=='all')
    	{
    		eval("itemid.style.display=\"none\"");
    		eval("target.style.display=\"none\"");
    	}
    }
    
    function daochu()
    {
    	var type = $("#type").val();
    	var flag = isEmpty(type);
    	if(flag == false)
    	{
    		alert("请填写正确完整");
    		return;
    	}
    	var keyword = "";
    	var sdate = $("#startTime").val();
    	var edate = $("#endTime").val();
    	var frequency = $("#frequency").val();
    	var channel = $("#plant").val();
    	if(type=="itemTrade")
    	{
    		keyword = $("#item").val();
    	}
    	else if(type=="targetItemTrade") 
    	{
    		keyword = $("#biaoqian").val();
    	}
    	else if (type == 'itemJine') 
    	{
    		var leixi = $("#itemtype").val();
    		if(leixi=='target') keyword = $("#biaoqian").val();
    		else if(leixi=='item') keyword = $("#item").val();
    		else keyword = "all";
    	}
    	eval("daochuimg.style.display=\"\"");
		var url1 = '<%=request.getContextPath()%>/ad/statistic?actionmethod=dataStatistic&type='+type+'&keyword='+keyword+'&startdate='+sdate+'&enddate='+edate+'&frequency='+frequency+'&channel='+channel+'&daochu=1';	
    	
    	var xmlHttp;  
    	if (window.ActiveXObject) {  
    	    xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');  
    	} else if (window.XMLHttpRequest) {  
    	    xmlHttp = new XMLHttpRequest();  
    	} 
    	var realUrl = url1 ;  
    	realUrl = encodeURI(encodeURI(realUrl));  
    	xmlHttp.onreadystatechange = function() {  
    	    if (xmlHttp.readyState==4 && xmlHttp.status==200) {  
    	        //var labelSpan = document.getElementById(label);  
    	       // labelSpan.innerHTML = decodeURI(xmlHttp.responseText);  
    	      // $("#chart1").empty();
    			data = xmlHttp.responseText; 
    			eval("daochuimg.style.display=\"none\"");
    	        delete xmlHttp;  // 手动删除  
    	        xmlHttp = null  ;
    	        window.location.href=data;
    	    }  
    	} 
    	
   		xmlHttp.open('GET', realUrl);  
   		xmlHttp.send(null);
    }
    
    function tongji()
    {
    	var type = $("#type").val();
        $("#chart1").empty();   
    	$("#d1").empty();
    	
    	var flag = isEmpty(type);
		if (flag == false)
		{
			alert("请填写正确完整");
			return;
		}
		creatediv();
    	var keyword = "";
    	var sdate = $("#startTime").val();
    	var edate = $("#endTime").val();
    	var frequency = $("#frequency").val();
    	var channel = $("#qudao").val();
    	if(type=="itemTrade")
    	{
    		keyword = $("#item").val();
    	}
    	else if(type=="targetItemTrade"||type=="targetItemClick") 
    	{
    		keyword = $("#biaoqian").val();
    	}
    	else if(type=="userRegister")
    	{
    		keyword = $("#plant").val();
    	}
    	else if (type == 'itemJine') 
    	{
    		var leixi = $("#itemtype").val();
    		if(leixi=='target') keyword = $("#biaoqian").val();
    		else if(leixi=='item') keyword = $("#item").val();
    		else keyword = "all";
    	}
    	var tishi = "商品交易、订单详细";
		var tishi1 = "订单成功";
		var tishi2 = "订单创建";
		var tishi3 = "订单结算";
		
		if (type == 'itemJine') 
		{
			tishi = "商品金额详情";
			tishi1 = "----";
			tishi2 = "----";
			tishi3 = "商品交易金额";
		}
		else if(type=="userRegister")
		{
			tishi = "注册用户详情";
			tishi1 = "----";
			tishi2 = "----";
			tishi3 = "注册用户量";
		}
		else if(type == "newUserTrade")
		{
			tishi = "新增用户交易详情";
			tishi1 = "交易数据";
			tishi2 = "----";
			tishi3 = "----";
		}
		else if (type == "userFanxian") 
		{
			tishi = "用户返利统计";
			tishi1 = "----";
			tishi2 = "返利统计";
			tishi3 = "佣金统计";
		} 
		else if (type == "jifenbao") 
		{
			tishi = "集分宝统计";
			tishi1 = "领取总人数";
			tishi2 = "人均领取量";
			tishi3 = "总集分宝量";
		} 
		var url1 = '<%=request.getContextPath()%>/ad/statistic?actionmethod=dataStatistic&type='+type+'&keyword='+keyword+'&startdate='+sdate+'&enddate='+edate+'&frequency='+frequency+'&channel='+channel;	
    	
    	var xmlHttp;  
    	if (window.ActiveXObject) {  
    	    xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');  
    	} else if (window.XMLHttpRequest) {  
    	    xmlHttp = new XMLHttpRequest();  
    	} 
    	var realUrl = url1 ;  
    	realUrl = encodeURI(encodeURI(realUrl));  
    	xmlHttp.onreadystatechange = function() {  
    	    if (xmlHttp.readyState==4 && xmlHttp.status==200) {  
    	        //var labelSpan = document.getElementById(label);  
    	       // labelSpan.innerHTML = decodeURI(xmlHttp.responseText);  
    	      // $("#chart1").empty();
    			json1 = xmlHttp.responseText; 
    	        delete xmlHttp;  // 手动删除  
    	        xmlHttp = null  ;
    	        var arrayObj = new Array();
    		     var line1 = '';
    		     var line2 = '';
    		     var line4 = '';
    		     var json3 = '';
    		     if(json1!='') {
    		     	arrayObj = json1.split("?");
    			    if(arrayObj.length>0) line1 = JSON.parse(arrayObj[0]);
    			    if(arrayObj.length>1&&arrayObj[1]!='') line2 = JSON.parse(arrayObj[1]);
    			    if(arrayObj.length>2&&arrayObj[2]!='') line4 = JSON.parse(arrayObj[2]);
    			    if(arrayObj.length==2) arrayObj[2] = "";
    		      	json3 = arrayObj[3];
    		     }
				if(type=="targetItemClick") 
				{
					drawBarChart(json1);
					return;
				}
    			autocreate(json3,line1,line2,line4,tishi1,tishi2,tishi3);  
    			var arrayObjshuju = json3+'?'+arrayObj[2]+'?'+arrayObj[1]+'?'+arrayObj[0];
    			var arrayObjtishi = tishi+'?'+tishi1+'?'+tishi2+'?'+tishi3;	 
    		 	drawTu(arrayObjshuju,arrayObjtishi);
    	    }  
    	}  
    		xmlHttp.open('GET', realUrl);  
    		xmlHttp.send(null);
    }
    
    function isEmpty(type)
    {
    	var startTime = $("#startTime").val();
    	var endTime = $("#endTime").val();
    	if(endTime==''||startTime=='') 
    	{
    		return false;
    	}
    	if(type=='itemTrade') 
    	{
    		
    	}
    }
    
    function drawTu(object1,object2) {
		 $("#chart1").empty();
		  var shuju =  new Array();
		  var tishiarr = new Array();
		  shuju	= object1.split("?");
		  tishiarr =  object2.split("?");
		  var line1 = shuju[0];
		  var line2 = ''; var line3 = ''; var line4 = '';
		  if(shuju.length>1 &&shuju[1]!='') line2 = JSON.parse(shuju[1]);
		  if(shuju.length>2 &&shuju[3]!='') line3 = JSON.parse(shuju[3]);
		  if(shuju.length>3 &&shuju[2]!='') line4 = JSON.parse(shuju[2]);
		  var tishi = tishiarr[0];
		  var tishi1 = tishiarr[1];
		  var tishi2 = tishiarr[3];
		  var tishi3 = tishiarr[2];
		  var xian1 = line4; var xian2 = line2; var xian3 = line3;
		  var col1 = '#00BFFF'; var col2 = '#FFA500';var col3 = '#CC9966';
		  //#00BFFF', '#FFA500', '#CC9966']
		 // alert(line4);
		//  alert(line2=='');
	//	  alert(line3=='');
		  if(line4=='') {
			  if(line2!='') {
				  
			  xian1 = JSON.parse(shuju[1]);  tishi3=tishi1;  col1 = '#00BFFF';//绿色
			  xian2 = JSON.parse(shuju[3]);  tishi1 = tishi2; col2 = '#FFA500';
			  xian3 = '';   tishi2="--";  col3 = 'CC9966';
			  }
			  else if(line2=='') {
			  xian1 = JSON.parse(shuju[3]); tishi3 = tishi2; col1 = '#CC9966';
			  xian2='';  tishi1="--";    col2 = '#00BFFF';
			  xian3='';	 tishi2="--";	col3 = '#FFA500';
		  }
		  
		  }
	     var arrayObj = new Array();
	     arrayObj = line1.split(",");
	     var lengt = arrayObj.length;
	    	 var arrayObj1 = new Array();
	    	 var num = parseInt(lengt/10) +1;
	    	 if(lengt<10) arrayObj1 = arrayObj ;
	    	 else if(10<=length<=20) {
	    	 for(var i=0;i<lengt;i++) {
	    		 if(i%num==0){
	    			 arrayObj1[i]=arrayObj[i];
	    		 }
	    		 else {
	    			 arrayObj1[i]=".";
	    		 }
	    	 }
	     }

	     //var date1 = ['2012-01-01', '2012-01-02', '2012-01-03', '2012-01-04', '2012-01-05','2012-01-06','2012-01-07'];
	     line5 = [[1,3], [2,7], [3,2], [4,9], [5,16], [6,8], [7,12]];
	     line6 = [[1,4], [2,5], [3,4], [4,8], [5,13], [6,7], [7,17]];
	     //详细配置
	     plot = $.jqplot('chart1', [xian1,xian2,xian3], {
	     
	    		 title: '随手优惠数据统计',                          //图表表头标题
	    axes: { 

	     xaxis: {    
	    	 ticks:arrayObj1,
	                 renderer: $.jqplot.CategoryAxisRenderer,
	                 tickInterval:7,
	                 label: tishi, // 用于显示在分类名称框中的分类名称 
	                 color: '', // 分类在图标中表示（折现，柱状图等）的颜色 
	                 labelPosition: 'middle',
	                 tickOptions:{
	                	 mark: 'outside',    // 设置横（纵）坐标刻度在坐标轴上显示方式，分为坐标轴内，外，穿过坐标轴显示   
                        // 值也分为：'outside', 'inside' 和 'cross',   
    showMark: true,     //设置是否显示刻度   
    showGridLine: true, // 是否在图表区域显示刻度值方向的网格线   
    markSize: 4,        // 每个刻度线顶点距刻度线在坐标轴上点距离（像素为单位）    
    show: true,         // 是否显示刻度线，与刻度线同方向的网格线，以及坐标轴上的刻度值   
    showLabel: true,    // 是否显示刻度线以及坐标轴上的刻度值   
	                	 fontSize:'1px',    //刻度值的字体大小   
	                     fontFamily:'Tahoma', //刻度值上字体   
	                     angle:-40,           //刻度值与坐标轴夹角，角度为坐标轴正向顺时针方向   
	 		          } 
	             },
	            
			        yaxis:{
			          tickOptions:{
			            formatString:'%.2f'
			            }
			        }
	    },             
	    seriesColors: [ col1, col2, col3],                    //定义趋势线颜色   
series: [{label: tishi3}, {label: tishi1}, {label: tishi2}], 

legend: {
	show: true,//设置是否出现分类名称框(即所有分类的名称出现在图的某个位置)
	location: 'ne', //分类名称框出现位置, nw, n, ne, e, se, s, sw, w.
	xoffset: 12, //分类名称框距图表区域上边框的距离(单位px)
	yoffset: 12, //分类名称框距图表区域左边框的距离(单位px)
	background:'', //分类名称框距图表区域背景色
	textColor:'', //分类名称框距图表区域内字体颜色
	},
	grid: {
	drawGridLines: true, // wether to draw lines across the grid or not.
	gridLineColor: '#cccccc', //设置整个图标区域网格背景线的颜色
	background: '#fffdf6', //设置整个图标区域的背景色
	borderColor: '#999999', //设置图表的(最外侧)边框的颜色
	borderWidth: 2.0, //设置图表的(最外侧)边框宽度
	shadow: true, //为整个图标（最外侧）边框设置阴影，以突出其立体效果
	shadowAngle: 45, //设置阴影区域的角度，从x轴顺时针方向旋转
	shadowOffset: 1.5, //设置阴影区域偏移出图片边框的距离
	shadowWidth: 3, //设置阴影区域的宽度
	shadowDepth: 3, //设置影音区域重叠阴影的数量
	shadowAlpha: 0.07 ,//设置阴影区域的透明度
	renderer: jQuery.jqplot.CanvasGridRenderer, // renderer to use to draw the grid.
	rendererOptions: {} // options to pass to the renderer. Note, the default CanvasGridRenderer takes no additional options.
	},

	    highlighter: { 
	    	show: true,
	     lineWidthAdjust: 50, //当鼠标移动到放大的数据点上时，设置增大的数据点的宽度,目前仅适用于非实心数据点 
	     sizeAdjust: 10, // 当鼠标移动到数据点上时，数据点扩大的增量增量 
	     showTooltip: true, // 是否显示提示信息栏 
	     tooltipLocation: 'nw', // 提示信息显示位置（英文方向的首写字母）: n, ne, e, se, s, sw, w, nw. 
	     tooltipAxes: 'y', // 提示信息框显示数据点那个坐标轴上的值，目前有横/纵/横纵三种方式,值分别为 x, y or xy. 
	     tooltipSeparator: '' // 提示信息栏不同值之间的间隔符号 
	    },
	      cursor: {
	    	  show: true,
		        zoom:true, 
		        showTooltip:false
	      }
	     });
	}
    
    function drawBarChart(data) {
		var line1 = data;
		             var plot3 = $.jqplot('chart1', [line1], {
		               title: '随手优惠数据统计',
		               series:[{renderer:$.jqplot.BarRenderer}],
		               axesDefaults: {
		                   tickRenderer: $.jqplot.CanvasAxisTickRenderer,
		                   tickOptions: {
		                     angle: -30
		                   }
		               },
		               axes: {
		                 xaxis: {
		                   renderer: $.jqplot.CategoryAxisRenderer,
		                   tickOptions: {
		                     labelPosition: 'middle'
		                   }
		                 },
		                 yaxis: {
		                   autoscale:true,
		                   tickRenderer: $.jqplot.CanvasAxisTickRenderer,
		                   tickOptions: {
		                     labelPosition: 'start'
		                   }
		                 }
		               }
		             });
	}
    function autocreate(line1,line2,line3,line4,tishi1,tishi2,tishi3){
    	$("#d1").empty();
    	line1 = "'" + line1 + "'";
    	line2 = "'" + line2 + "'";
    	line3 = "'" + line3 + "'";
    	line4 = "'" + line4 + "'";
    	 var arrayObj1 = new Array(); 
    	 var arrayObj2 = new Array(); 
    	 var arrayObj3 = new Array();
    	 var arrayObj4 = new Array();
    	 arrayObj1 = line1.split(",");

    	 arrayObj2 = line2.split(",");
    	 arrayObj3 = line3.split(",");
    	 arrayObj4 = line4.split(",");
    	var length = arrayObj2.length;
    	//创建table表格
        var table=document.createElement("table");
        table.setAttribute("border","1");
        //table.style.width = "1000px";
        table.setAttribute("style","width:900px; height:100px;  cellspacing:1; ");
        table.setAttribute("class","tablewidget");
        table.setAttribute("background-color","red");
        //创建tr
            var thead = document.createElement("thead");
            thead.setAttribute("class","title");
        var tr=document.createElement("tr");
            //alert(list);
            //创建td

            var td=document.createElement("td");
            var date = document.createTextNode("日期");
            td.appendChild(date);
            tr.appendChild(td);
            thead.appendChild(tr);
            
            if(tishi3!='----')
            {            	
            var td=document.createElement("td");   
            var jiaoyi= document.createTextNode(tishi3);
            td.appendChild(jiaoyi);
            tr.appendChild(td);
            thead.appendChild(tr);
            }
            
            if(tishi1!='----')
            {            	
            var td=document.createElement("td");            
            var trade = document.createTextNode(tishi1);
            td.appendChild(trade);  
            tr.appendChild(td);
            thead.appendChild(tr);
            }
            
            if(tishi2!='----')
            {           	
            var td=document.createElement("td");            
            var tardeSucc = document.createTextNode(tishi2);
            td.appendChild(tardeSucc);       
            tr.appendChild(td);
            thead.appendChild(tr);
            }
          
            table.appendChild(thead);
            var tbody=document.createElement("tbody");
            var j = 0;
        	for(var i=1;i<length;i=i+2){
            //创建tr
            var tr=document.createElement("tr");
                //创建td
                
                var td=document.createElement("td");
                var bshuju2 = "";
                if(arrayObj1!=''&&arrayObj1.length>1) bshuju2 = arrayObj1[j];
                var date = document.createTextNode(bshuju2);
                td.appendChild(date);
                tr.appendChild(td);
                tbody.appendChild(tr);
                
                if(tishi3!='----')
                {               	
                var td=document.createElement("td");   
                var ashuju2 = "";
                if(arrayObj2!=''&&arrayObj2.length>1) ashuju2 = arrayObj2[i];
                var jiaoyi = document.createTextNode(ashuju2);
                td.appendChild(jiaoyi);
                tr.appendChild(td);
                tbody.appendChild(tr);
                }
                
                if(tishi1!='----')
                {               	
                var td=document.createElement("td");  
                var cshuju2 = "";
                if(arrayObj4!='/'/''&&arrayObj4.length>1) cshuju2 = arrayObj4[i];
                var trade = document.createTextNode(cshuju2);
                td.appendChild(trade);  
                tr.appendChild(td);
                tbody.appendChild(tr);
                }
                
                if(tishi2!='----')
                {               	
                var td=document.createElement("td");  
                var dshuju2 = "";
                if(arrayObj3!=''&&arrayObj3.length>1) dshuju2 = arrayObj3[i];
                var tardeSucc = document.createTextNode(dshuju2);
                td.appendChild(tardeSucc);            
                tr.appendChild(td);
                tbody.appendChild(tr);
                }
              
                table.appendChild(tbody);
                j++;
            }
            document.getElementById("d1").appendChild(table);
        }
    
	function creatediv(){	  
        var e = document.createElement("img");
        e.src="../img/loading.gif";
        e.style.margin = "0 auto";
        e.style.width="30px";
        e.style.height="30px";
        e.style.position="relative";
        e.style.left="430px";
        e.style.top="150px";
		e.id="imgdisplayer";
  		document.getElementById("chart1").appendChild(e);
	}
    </script>
    <style type="text/css">
	div#itemid { display: inline }
	div#target { display: inline }
	div#pingtai { display: inline }
	div#startTime { display: inline }
	div#endTime { display: inline }
	div#jiaoyileixing { display: inline }
	div#jineleixing { display: inline }
	div#yonghuId { display: inline }
	</style>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
	         <h1 class="entry-title">数据统计</h1>
	         <hr/>
		<div>
		<select style="padding: 5px;" id="type" name="type" onchange="change()">
		<option value ="itemTrade">商品交易数据量统计</option>
  		<option value="targetItemTrade">标签下商品数据统计</option>
  		<option value="ItemOrderTotalTongji">总商品交易数据统计 </option>
  		<option value="itemJine">商品交易金额统计</option>
  		<option value="userRegister">注册用户统计</option>
  		<!-- 
	  	<option value="targetItemClick">标签下商品点击量量前十</option>
 		<option value="newUserTrade">新增用户交易统计</option>
  		 -->
  		<option value="userFanxian">用户返现统计</option>
  		<option value="jifenbao">集分宝领取统计 </option>
		</select>
		<div id="jineleixing" style="display: none;">
		<select id="itemtype" onchange="selectType()" style="padding: 5px;"> 
		<option value="target">标签</option>
		<option value="item">商品id</option>
		<option value="all">所有商品</option>
		</select>
		</div>
		<div id="itemid">
		 商品ID<input type="text" id="item" name="item" value="" style="display: ;">
		</div>
		
		<div style="display: none;" id="target">
		 标签<select id="biaoqian" name="biaoqian" style="padding: 5px;">
		 <c:forEach var="target" items="${targetList}">
		 <option value="${target.id}">${target.keyword}</option>
		 </c:forEach>
		  </select >
		  </div>

		<div style="display: none;" id="pingtai">
		平台<select id="plant" name="plant" style="padding: 5px;">
			<option value ="ipad">ipad</option>
   			<option value ="iphone">iphone</option>
    		<option value ="android">android</option>
    		<option value ="">全部</option>
			</select>
			<select id="qudao" name="qudao" style="padding: 5px;">
			<option value="">所有渠道</option>
			<c:forEach var="channel" items="${channelList}">
			<option value="${channel}">${channel}</option>
			</c:forEach>
			</select>
		</div>
		
		<div id="jiaoyileixing" style="display: none;" >
		交易类型<select style="padding: 5px;">
			<option value="5">期间注册用户月交易单数</option>
	  		<option value="6">期间注册用户月交易总额</option>
	  		<option value="7">期间注册用户月交易人数</option>
			</select>
		</div>
		
		<div id="yonghuId" style="display: none;">
		用户ID<input type="text" value="">
		</div>
		
		开始时间 <input type="text" id="startTime" class="datepicker" name="startTime" onClick="WdatePicker()" value="">
		结束时间<input type="text" id="endTime" class="datepicker" name="endTime" onClick="WdatePicker()" value="">
		<select style="padding: 5px;" id="frequency">
		<option value="1">按月查询</option>
		<option value="2" selected="selected">按天查询</option>
		<option value="3">按时查询</option>
		</select>
		<input type="button" value="查看" onclick="tongji()">
		<input type="button" value="导出Excel" onclick="daochu()">
		<img id="daochuimg" alt="" src="../img/loading.gif" width="25px" height="25px" style="display:none">
		</div>
		<hr/>
		  <div id="chart1" style="width: 900px; height: 500px;" class="jqplot-target">
 
  </div>
  <div id="d1" style="width: 900px;"></div>
	

	      	<div class="pagein" align="center">
              <form name="PageForm"  action="" method="post">
              </form>
            </div>
		</div>
	  </div>
	 </div>
	
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>