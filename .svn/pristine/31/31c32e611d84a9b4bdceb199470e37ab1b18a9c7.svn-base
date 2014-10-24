<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/other.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript">
	var terminal_id;
	$(function(){
		terminal_id = "${terminalId}";
		$("#call_button").click(function(){
			var interval = $("#interval_input").get(0).value;
			var times = $("#times_input").get(0).value;
			window.location.href="${pageContext.request.contextPath}/other/call.html?terminalId=" + terminal_id + "&interval=" + interval + "&times=" + times;
		})
		$("#send_agps").click(function(){
			window.location.href = "${pageContext.request.contextPath}/agps/sendagps.html?terminalId=${terminalId}";
		})
		$("#check_agps").click(function(){
			window.location.href = "${pageContext.request.contextPath}/agps/checkagps.html?terminalId=${terminalId}";
		})
	})
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/other.js"></script>
<title>其他功能</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId} 其他功能</div>
	<table class="bordered" id="call_table">
		<thead>
			<tr>
				<th colspan="2">点名和连续查车</th>
			</tr>
		</thead>
		<tr>
			<td>间隔（16进制）</td>
			<td><input class="other_input" id="interval_input" type="text"></td>
		</tr>
		<tr>
			<td>次数（16进制）</td>
			<td><input class="other_input" id="times_input" type="text"></td>
		</tr>
	</table>
	<button id="call_button">点名</button>
	<table class="bordered" id="listen_table">
		<thead>
			<tr>
				<th colspan="2">监听</th>
			</tr>
		</thead>
		<tr>
			<td>电话号码</td>
			<td><input class="other_input" id="phone_input" type="text"></td>
		</tr>
	</table>
	<button id="listen_button">监听</button>
	<button id="reboot_button">重启终端</button>
	<button id="installposition_button">安装位置学习</button>
	<button id="clearblind_button">清除盲点数据</button>
	<button id="arm_button">设防</button>
	<button id="disarm_button">撤防</button>
	<button id="restore_button">恢复出厂设置</button>
	<button id="send_agps">发送AGPS数据包</button>
	<button id="check_agps">判断AGPS数据包是否最新</button>
	<div id="waitBlock" style="display:none; cursor:default;" align="center">
		<div id="operating">正在操作..</div>
		<p id="waiting"></p>
	</div>
</body>
</html>