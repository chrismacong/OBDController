<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/obdlog.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/terminallog.js"></script>
<script type="text/javascript">
	$(function(){
		var str = "" + ${terminalId};
		$("#remove_all_button").click(function(){
			window.location.href = "/OBDController/terminallog/removeall.html?terminalId=" + ${terminalId};
		});
		$("#refresh_button").click(function(){
			window.location.reload();
		});
	});
</script>
<title>OBD日志</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId} 的OBD日志</div>
	<button id="remove_all_button">清空日志</button>
	<button id="refresh_button">刷新</button>
	<table class="bordered" id="log_table">
		<thead>
			<tr>
				<th>时间</th>
				<th>内容</th>
			</tr>
		</thead>
		<c:forEach items="${logs_list}" var="xx" varStatus="loop">
		<tr id="tablerow_${xx.getLid()}">
			<td>${xx.getDate()}</td>
			<td>${xx.getInfo()}</td>
		</tr>
		</c:forEach>
	</table>
	<div id="logBlock" style="display: none; cursor: default;"
		align="center">
		<div>日志解析</div>
		<a class="block_close_btn"></a> <br />
		<div id="log_decode_content"></div>
	</div>
</body>
</html>