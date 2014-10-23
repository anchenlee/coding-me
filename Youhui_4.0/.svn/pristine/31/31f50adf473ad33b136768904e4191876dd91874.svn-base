<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增标签-随手4.0</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="../include/css_js.jsp"></jsp:include>
        <jsp:include page="../include/datetime_css_js.jsp"></jsp:include>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
    
    <script type="text/javascript">
    	// 上传手机图标
	    function add_phone_icon_ImgName(ImgName)
		{
		    var filename = $("#phone_icon_imgFile").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix+ImgName+suffix;
		    $("#phone_icon").val(url);
		    $("#upload_phone_icon_ImgForm").submit();
		}
	    
	 	// 上传PAD图标
	    function add_pad_icon_ImgName(padImgName)
		{
		    var filename = $("#pad_icon_imgFile").val();  
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix + padImgName + suffix;
		    $("#pad_icon").val(url);
		    $("#upload_pad_icon_ImgForm").submit();
		}
	    
		// 上传插页图标
	    function add_chaye_icon_ImgName(chayeImgName)
		{
		    var filename = $("#chaye_icon_ImgFile").val();
		    var suffix = filename.substring(filename.lastIndexOf("."));
			var prefix = "http://static.etouch.cn/suishou/ad_img/";
			var url = prefix + chayeImgName + suffix;
		    $("#chaye_icon").val(url);
		    $("#upload_chaye_icon_ImgForm").submit();
		}
	    
		// 提交时检查参数
	    function checkParam()
		{
			var parent_tag_id = $("#parent_tag_id").val();
			var tongbu = $("#tongbu").val();
			var position = "";
			if (tongbu == 1)
			{
				position = $("#position").val();
			}
			
			if (parent_tag_id == null || parent_tag_id == "")
			{
				alert("父标签为空，不合法!");
				
				var url = "<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&parent=538";
				location.href=url;
				
				return false;
			}
			
			var tag_name = $("#tag_name").val();
			if (tag_name == null || tag_name == "")
			{
				alert("请填写<标签名称>!");
				return false;
			}
			
			var description = $("#description").val();
			if (description == null)
			{
				description == "";
			}
			
			var bgcolor = $("#bgcolor").val();
			if (bgcolor == null)
			{
				bgcolor == "";
			}
			
			var jfb_rate = $("#jfb_rate").val();
			if (jfb_rate == null || jfb_rate == "")
			{
				jfb_rate = "0.0";
			}
			
			var sex = $("#sex").val();
			var small_show = $("#small_show").val();
			var status = $("#status").val();
			var isPadTag = $("#isPadTag").val();
			var heat = $("#heat").val();
			if (heat == null)
			{
				heat == "";
			}
			
			var phone_icon = $("#phone_icon").val();
			if (phone_icon == null)
			{
				phone_icon == "";
			}
			
			var pad_icon = $("#pad_icon").val();
			if (pad_icon == null)
			{
				pad_icon == "";
			}
			
			var action_type = $("#action_type").val();
			var action_value = getActionValue(action_type);
			
			
			var chaye_action_type = $("#chaye_action_type").val();
			var chaye_action_value = getChayeActionValue(chaye_action_type);
			if(chaye_action_type == ''){
				if(chaye_action_value !=''){
					alert("插页样式为空情况下，插页内容必须为空！");
					return;
				}
			}
			var chaye_icon = $("#chaye_icon").val();
			if (chaye_icon == null)
			{
				chaye_icon = "";
			}
			var chaye_icon_size = $("#chaye_icon_size").val();
			if (chaye_icon_size == null)
			{
				chaye_icon_size = "";
			}
			var start_time = $("#start_time").val();
			if(start_time == null || start_time == ''){
				alert("开始时间不能为空");
				return;
			}
			
			$.ajax({
    		    url: '<%=request.getContextPath()%>/ad/tag_manager?actionmethod=addTag',
    		    type: 'POST',
    		    data: {parent_id:parent_tag_id,
    		    			tag_name:tag_name,
    		    			description:description,
    		    			bgcolor:bgcolor,
    		    			jfb_rate:jfb_rate,
    		    			sex:sex,
    		    			small_show:small_show,
    		    			status:status,
    		    			isPadTag:isPadTag,
    		    			heat:heat,
    		    			action_type:action_type,
    		    			action_value:action_value,
    		    			phone_icon:phone_icon,
    		    			pad_icon:pad_icon,
    		    			chaye_action_type:chaye_action_type,
    		    			chaye_action_value:chaye_action_value,
    		    			chaye_icon:chaye_icon,
    		    			chaye_icon_size:chaye_icon_size,
    		    			tongbu:tongbu,
    		    			position:position,
    		    			start_time:start_time
    		    			},
    		    dataType: 'json',
    		    timeout: 10000,
    		    success: function(json)
    		    {
    		    	var result = json.result;
    		    	if (result == "0")
   		    		{
    		    		var url = "<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&status=${status}&ispad=${ispad}&page=${page}&parent=" + parent_tag_id;
    					location.href=url;
   		    		}
    		    	else if (result == "1")
    		    	{
    		    		alert("操作失败!");
    		    		return false;
    		    	}
    		    	else if (result == "2")
    		    	{
    		    		alert("标签名重复，请重填!");
    		    		return false;
    		    	}
    		    }
    		});
		}
		
		function cancel()
		{
			var parent_tag_id = $("#parent_tag_id").val();
			var url = "<%=request.getContextPath()%>/ad/tag_manager?actionmethod=showTagListToAdmin&status=${status}&ispad=${ispad}&page=${page}&parent=" + parent_tag_id;
			location.href=url;
		}
		
		function changebgcolor(ispadtag)
		{
			if (ispadtag == "1")
			{
				eval("p_bgcolor.style.display=\"\"");
				$("#bgcolor").val("99ffffff");
			}
			else if (ispadtag == "0")
			{
				$("#bgcolor").val("");
				eval("p_bgcolor.style.display=\"none\"");
			}
		}
		
		function isTongbu()
		{
			var type = $("#tongbu").val();
			if(type==0)
			{
				eval("yonghuweizi.style.display=\"none\"");
			}
			else
			{
				eval("yonghuweizi.style.display=\"\"");
			}
		}
    </script>
	</head>
	<body class="home page page-id-14 page-child parent-pageid-10 page-template page-template-template-portfolio-php chrome" style="min-width:1300px;">
	<%@include file="../include/header.jsp" %>
	<div id="container" class = "clear">	
     <div id="content">
		<jsp:include page="../include/menu.jsp"></jsp:include>
         <div id="primary" class="hfeed">
         
		   <div id="post-8" class="post-8 page type-page status-publish hentry">
	         <h2 class="entry-title">新增标签</h2>
				<div id="submit-content">
                <div id="formbox">
                		<input type="hidden" id="parent_tag_id" name="parent_tag_id" value="${parent}" />
                		<p>
                             <label><font color="#777">标签名称：</font></label>
                             <input type="text" name="tag_name" id="tag_name" value="" />
                        </p>
                        <p>
                             <label><font color="#777">标签描述：</font></label>
                             <input type="text" name="description" id="description" value="" />
                       	</p>
                       	<p id="p_bgcolor">
							<label><font color="#777">背景色：</font></label>
							<input type="text" name="bgcolor" id="bgcolor" value="99ffffff" />
						</p>
						<p>
							<label><font color="#777">集分宝比例：</font></label>
							<input type="text" name="jfb_rate" id="jfb_rate" value="" />
						</p>
						<p>
							<label><font color="#777">开始时间：</font></label>
							<input type="text" class="ui_timepicker" name="start_time" id="start_time" value="" />
						</p>
						<p>
							<label><font color="#777">同步到所有用户：</font></label>
							<select id="tongbu" name="tongbu" onchange="isTongbu()">
								<option value="0" selected>否</option>
								<option value="1">是</option>
							</select>
						</p>
						<p id="yonghuweizi" style="display: none">
							<label><font color="#777">添加到用户位置：</font></label>
							<select id="position" name="position">
								<option value="0">首位</option>
								<option value="1" selected>末尾</option>
							</select>
						</p>
                        <p>
							<label><font color="#777">标签性别：</font></label>
							<select id="sex" name="sex">
								<c:forEach var="SEX" items="${TagSexMap}">
									<option value="${SEX.key}">${SEX.value}</option>
								</c:forEach>
							</select>
						</p>
                        <p>
							<label><font color="#777">显示小条：</font></label>
							<select id="small_show" name="small_show">
								<c:forEach var="SmallShow" items="${TagSmallShowMap}">
									<option value="${SmallShow.key}">${SmallShow.value}</option>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">显示状态：</font></label>
							<select id="status" name="status">
								<c:forEach var="TagStatus" items="${TagStatusMap}">
									<c:choose>
										<c:when test="${TagStatus.key == 2}">
											<option value="${TagStatus.key}" selected>${TagStatus.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${TagStatus.key}">${TagStatus.value}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">PAD标签：</font></label>
							<select id="isPadTag" name="isPadTag" onchange="changebgcolor(this.value)">
								<c:forEach var="IsPadTag" items="${IsPadTagMap}">
									<c:choose>
										<c:when test="${IsPadTag.key == 0}">
											<option value="${IsPadTag.key}" selected>${IsPadTag.value}</option>
										</c:when>
										<c:otherwise>
											<option value="${IsPadTag.key}" selected>${IsPadTag.value}</option>
										</c:otherwise>
									</c:choose>
									
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">标签热度：</font></label>
							<select id="heat" name="heat">
								<option value=""></option>
								<c:forEach var="TagHeat" items="${TagHeatMap}">
									<option value="${TagHeat.key}">${TagHeat.value}</option>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">展示样式：</font></label>
							<select id="action_type" name="action_type" onchange="actionTypeChange_1()">
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<option value="${ActionType.key}">${ActionType.value}</option>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">展示内容：</font></label>
							<input type="text" name="action_value" id="action_value" value="" style="display: none;" />
							<select id="xitongyemian" name="xitongyemian"  style="display: none;">
								<c:forEach var="XTYM" items="${Tag_XTYM_Map}">
									<option value="${XTYM.key}">${XTYM.value}</option>
								</c:forEach>
							</select>
							<select id="tagactionvalue" name="tagactionvalue"  >
								<option value="default">默认</option>
								<c:forEach var="tag_map" items="${Tag_Type_Value_Map}">
									<c:forEach var="tag" items="${tag_map}">
										<option value="${tag.key}">${tag.value}</option>
									</c:forEach>
								</c:forEach>
							</select>
						</p>
						<p>
							<label><font color="#777">手机图标：</font></label>
							<input type="text" name="phone_icon" id="phone_icon"  value="" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：192*168</font>
							<!-- <img id="img_show" src="${activitybean.img}"  height="100"/> -->
						</p>
						<%
						    String phone_icon_ImgSaveName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_phone_icon_ImgForm" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=phone_icon_ImgSaveName%>" target="chaye_icon_ImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="phone_icon_imgFile" name="file" type="file" />
						    <input type="button" onclick="add_phone_icon_ImgName('<%=phone_icon_ImgSaveName%>')" value="上传"  class="button"/>
						</form>
						<br/>
						
						<p>
							<label><font color="#777">PAD图标：</font></label>
							<input type="text" name="pad_icon" id="pad_icon"  value="" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：221*190</font>
							<!-- <img id="img_show" src="${activitybean.img}"  height="100"/> -->
						</p>
						<%
						    String pad_icon_ImgSaveName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_pad_icon_ImgForm" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=pad_icon_ImgSaveName%>" target="chaye_icon_ImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="pad_icon_imgFile" name="file" type="file" />
						    <input type="button" onclick="add_pad_icon_ImgName('<%=pad_icon_ImgSaveName%>')" value="上传"  class="button"/>
						</form>
						<iframe name="padImgUploadIfr" style="display:none" ></iframe>
						<br/>
						<p>
							<label><font color="#777">插页样式：</font></label>
							<select id="chaye_action_type" name="chaye_action_type" onchange="actionTypeChange_2()">
								<option value="">空</option>
								<c:forEach var="ActionType" items="${ActionTypeMap}">
									<option value="${ActionType.key}">${ActionType.value}</option>
								</c:forEach>
							</select>
						</p>
                        <p>
                           <label><font color="#777">插页内容：</font></label>
                           <input type="text" name="chaye_action_value" id="chaye_action_value" value=""  />
                           <select id="xitongyemian2" name="xitongyemian2"  style="display: none;">
								<c:forEach var="XTYM" items="${Tag_XTYM_Map}">
									<option value="${XTYM.key}">${XTYM.value}</option>
								</c:forEach>
							</select>
							<select id="tagactionvalue2" name="tagactionvalue2"  style="display: none;">
								<option value="default">默认</option>
								<c:forEach var="tag_map" items="${Tag_Type_Value_Map}">
									<c:forEach var="tag" items="${tag_map}">
										<option value="${tag.key}">${tag.value}</option>
									</c:forEach>
								</c:forEach>
							</select>
                        </p>
						<p>
								<label><font color="#777">插页图标：</font></label>
								<input type="text" size="50" name="chaye_icon" id="chaye_icon"  value="" />
						</p>
						<%
						    String chaye_icon_ImgSaveName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_chaye_icon_ImgForm" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=chaye_icon_ImgSaveName%>" target="chaye_icon_ImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="chaye_icon_ImgFile" name="file"  type="file" />
						    <input type="button" onclick="add_chaye_icon_ImgName('<%=chaye_icon_ImgSaveName%>')" value="上传"  class="button"/>
						</form>
						<iframe name="chaye_icon_ImgUploadIfr" style="display:none" ></iframe>
						<br/>
						<p>
                              <label><font color="#777">图标尺寸比：</font></label>
                              <input type="text" name="chaye_icon_size" id="chaye_icon_size" value="" />
                       	</p>
						
						<p>
							<input id="submit" type="button" class="button" onclick="checkParam()" value="提交" />
							<input id="cancel" type="button" class="button" onclick="cancel()" value="取消" />
						</p>
                  </div>
                </div>
		</div>
		
	  </div>
	 </div>
	</div>
	<script type="text/javascript">
	
	$(function () {
        $(".ui_timepicker").datetimepicker({
            //showOn: "button",
            //buttonImage: "./css/images/icon_calendar.gif",
            //buttonImageOnly: true,
            showSecond: true,
            timeFormat: 'hh:mm:ss',
            stepHour: 1,
            stepMinute: 1,
            stepSecond: 1
        });
    });
	</script>
	<jsp:include page="../include/foot.jsp"></jsp:include>
	</body>
</html>