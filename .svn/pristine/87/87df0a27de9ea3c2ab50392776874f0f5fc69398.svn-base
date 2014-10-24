<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/positioninfopersonal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/positioninfo.js"></script>
<script type="text/javascript">
	$(function(){
		$("#refresh_button").click(function(){
			window.location.reload();
		});
		window.setInterval("window.location.reload()", parseInt("${position_info_refresh_millisecond}"));
	});
</script>
<title>地理位置信息</title>
</head>
<body>
	<div id="OBD_date_div">OBD时间：${positiondate}</div>
	<button id="refresh_button">手动刷新</button>
	<table class="bordered" id="position_info_table">
		<thead>
			<tr>
				<th>项目</th>
				<th>内容</th>
			</tr>
		</thead>
		<c:forEach items="${character_list}" var="xx" varStatus="loop">
		<tr>
			<td>${xx.getName()}</td>
			<td>${xx.getContent()}</td>
		</tr>
		</c:forEach>
	</table>
	<table id="alert_table">
		<tr class="alert_table_tr">
		<c:forEach items="${alert_list}" var="xx" varStatus="loop">
				<td>
					${xx.getName()}
				</td>
				<td>
					<img class="alert_img" src="${pageContext.request.contextPath}/images/alert${xx.getContent()}_user.png">
				</td>
				<c:if test="${loop.count%2==0}">
					<%
						out.println("<tr/><tr class='alert_table_tr'>");
					%>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	<a id="speed_graph" target="_blank" href="${pageContext.request.contextPath}/positioninfo/getsgraph.html?terminalId=${terminalId}"></a>
	<a id="temperature_graph" target="_blank" href="${pageContext.request.contextPath}/positioninfo/gettgraph.html?terminalId=${terminalId}"></a>
	<a id="location_graph" target="_blank" href="${pageContext.request.contextPath}/positioninfo/getlgraph.html?terminalId=${terminalId}"></a>
	<a id="pressure_graph" target="_blank" href="${pageContext.request.contextPath}/obdinfo/getpgraph.html?terminalId=${terminalId}"></a>
	<a id="dash_graph" target="_blank" href="${pageContext.request.contextPath}/obdinfo/getdgraph.html?terminalId=${terminalId}"></a>
	<a id="travel_exm_graph" target="_blank" href= "${pageContext.request.contextPath}/travelexm.html?terminalId=${terminalId}"></a>
</body>
</html>