<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/Checkable.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/character.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/global.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/Checkable.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/character.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript">
	terminalId = "${terminalId}";
</script>
<title>参数设置与查询</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId}
		参数设置与查询</div>
	<div id="type_div" style="margin-left: 30px;"><br />
	<div id="type_title">模式选择</div>
	<input id="Radio1" type="radio" class="myClass" value="yes" name="type" data-label="查询"
			data-color="blue" onchange="clickforask()" checked/> 
	<input id="Radio2" type="radio" class="myClass" value="yes" name="type" data-label="设置" onchange="clickforset()"/> <br />
	<div id="character_title">参数操作</div>
	<table style="width:100%;">
		<tr>
		<c:forEach items="${character_list}" var="xx" varStatus="loop">
				<td>
				<input type="checkbox" class="myClass" value="yes" id="Checkbox_${xx.getCid()}" data-label="${xx.getCname()}" name="answer" />
				<input type="text" disabled="disabled" name="input_text" class="input_text" id="input_${xx.getCid()}" value="${xx.getCanswer()}">
				</td>
				<c:if test="${loop.count%2==0}">
					<%
						out.println("<tr/><tr>");
					%>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	<button id="submit_button">发送参数请求</button>
	<button id="refresh_button">手动刷新</button>
	</div>
	<div id="waitBlock" style="display:none; cursor:default;" align="center">
		<div id="operating">正在操作..</div>
		<p id="waiting"></p>
	</div>
</body>
</html>