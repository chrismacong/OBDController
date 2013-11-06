<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/agps.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#send_agps_data").click(function(){
			window.location.href = "${pageContext.request.contextPath}/agps/sendagps.html?terminalId=${terminalId}";
		})
		$("#check_agps_time").click(function(){
			window.location.href = "${pageContext.request.contextPath}/agps/checkagps.html?terminalId=${terminalId}";
		})
	})
</script>
<title>AGPS</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId} AGPS设置</div>
	<button id="send_agps_data">发送AGPS数据包</button>
	<button id="check_agps_time">判断AGPS数据时间是否最新</button>
</body>
</html>