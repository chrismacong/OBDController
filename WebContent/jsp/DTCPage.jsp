<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/global.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/dtc.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript">
	var terminal_id;
	var isACCOn;
	$(function() {
		terminal_id = "${terminalId}";
		isACCOn = "${isACCOn}";
	});
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dtc.js"></script>
<title>OBD日志</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId}
		的OBD日志</div>
	<button id="refresh_button">刷新</button>
	<button class="button_to_disable" id="get_dtc_status_button">读取故障码</button>
	<div id="dtc_status_date_div">${DTC_date}</div>
	<div id="dtc_status_div">${DTCDefect}</div>
	<button class="button_to_disable" id="get_obd_defect_button">读取OBD故障状态</button>
	<div id="obd_defect_date_div">${DTC_status_date}</div>
	<div id="obd_defect_div">${OBDDefect}</div>
	<button class="button_to_disable" id="clear_dtc_status_button">清除故障码</button>
	<button class="button_to_disable" id="analysis_dtc_status">解析故障码</button>
	"
	<div id="waitBlock" style="display: none; cursor: default;"
		align="center">
		<div id="operating">正在操作..</div>
		<p id="waiting"></p>
	</div>
	<div id="detailBlock" style="display: none; cursor: default;"
		align="center">
		<div>故障码解析</div>
		<a class="block_close_btn"></a> <br />
		<table class="bordered">
			<thead>
				<tr>
					<th>故障码</th>
					<th>解析内容</th>
				</tr>
			</thead>
			<c:forEach items="${fault_code_list}" var="xx" varStatus="loop">
				<tr>
					<td>${xx.getIndex()}</td>
					<td>${xx.getFaultDetail()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>