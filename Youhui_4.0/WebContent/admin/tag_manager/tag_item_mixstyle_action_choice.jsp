<%@page import="com.netting.dao.admin.Admin_Tag_DAO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.netting.conf.SysCache"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
request.setAttribute("ActionTypeMap", SysCache.ActionTypeMap);
request.setAttribute("Tag_XTYM_Map", SysCache.XTYM_Map);
ArrayList<HashMap<String, String>> Tag_Type_Value_Map = Admin_Tag_DAO.getTagActionType_Value_Map("538");
request.setAttribute("Tag_Type_Value_Map", Tag_Type_Value_Map);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/actionTypeValue.js"></script>
<script type="text/javascript">

function add_icon_ImgName(ImgName)
{
    var filename = $("#recom_icon_imgFile").val();  
    var suffix = filename.substring(filename.lastIndexOf("."));
	var prefix = "http://static.etouch.cn/suishou/ad_img/";
	var url = prefix+ImgName+suffix;
    $("#recom_icon").val(url);
    $("#upload_recom_icon_ImgForm").submit();
}

function checkParam(){
	var recom_icon = $("#recom_icon").val();
	if(recom_icon == ''){
		alert("图片不能为空！");
		return ;
	}
	
	
}
</script>
<title>Insert title here</title>
</head>
<body>
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
							<label><font color="#777">图标：</font></label>
							<input type="text" name="recom_icon" id="recom_icon"  value="" />&nbsp;&nbsp;&nbsp;<font color="red">尺寸：616*268  50KB</font>
							<!-- <img id="img_show" src="${activitybean.img}"  height="100"/> -->
						</p>
						<%
						    String imgSaveName = Long.toString(System.nanoTime(), 36);
						%>
					    <form id="upload_recom_icon_ImgForm" method="post" enctype="multipart/form-data" action="http://a.suishouyouhui.cn/img/upload?imgsn=<%=imgSaveName%>" target="ImgUploadIfr" >
						    <label>&nbsp;</label>
						    <input id="recom_icon_imgFile" name="file" type="file" />
						    <input type="button" onclick="add_icon_ImgName('<%=imgSaveName%>')" value="上传"  class="button"/>
						</form>
						<iframe name="ImgUploadIfr" style="display:none" ></iframe>
						<br/>
						<p>
							<input id="submit" type="button" class="button" onclick="checkParam()" value="确定" />							
						</p>
</body>
</html>