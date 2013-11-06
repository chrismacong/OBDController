<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/obdinfo.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript">
	$(function(){
		var str = "" + ${terminalId};
		$("#refresh_button").click(function(){
			window.location.reload();
		});
		window.setInterval("window.location.reload()", parseInt("${obd_info_refresh_millisecond}"));
		$("#get_one_more_obd_button").click(function(){
			$.blockUI({
				message: $('#waitBlock'),
				css:{
					top:'20%',
					width:'24%',
					height:'100px',
					left:'38%'
				}
			});
			var $params="terminalId=${terminalId}";
			$.ajax({
				type:'GET',
				contentType: 'application/json',  
				url:"obdinfo/askOBDInfo.html",
				data: $params,
				dataType: "json",
				success:function(data){
					if (data && data.success == "true") {
						window.location.reload();
					}
					else{
						$.unblockUI();
						alert("操作失败，请重新操作")
					}
				},
				error:function(){
					$.unblockUI();
					alert("操作失败，请重新操作")
				}
			});
		});
	});
</script>
<title>OBD状态数据</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId} 的OBD状态数据</div>
	<div id="OBD_date_div">OBD时间：${obddate}</div>
	<button id="refresh_button">手动刷新</button>
	<button id="get_one_more_obd_button">请求OBD数据</button>
	<table class="bordered" id="obd_info_table">
		<thead>
			<tr>
				<th>项目</th>
				<th>内容</th>
				<th>说明</th>
			</tr>
		</thead>
		<c:forEach items="${character_list}" var="xx" varStatus="loop">
			<tr>
				<td>${xx.getName()}</td>
				<td>${xx.getContent()}</td>
				<td>${xx.getExtra()}</td>
			</tr>
		</c:forEach>
	</table>
	<div id="waitBlock" style="display:none; cursor:default;" align="center">
		<div id="operating">正在操作..</div>
		<p id="waiting"></p>
	</div>
</body>
</html>