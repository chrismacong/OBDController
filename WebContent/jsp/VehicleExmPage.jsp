<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/vehicleExm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vehicleexm.js"></script>
<script type="text/javascript">
	var terminal_id;
	$(function(){
		terminal_id = "${terminalId}";
	});
</script>
<title>车辆健康体检</title>
</head>
<body>
	<div id="subtitle">车辆健康体检</div>
	<div id="terminalTitle">终端Id：${terminalId}</div>
	<div id="scoreTxtDiv">您的车辆健康指数为</div>
	<div id="scoreDiv">100</div>
	<button id="exm_button" class="healthy_button">开始体检</button>
	<div id="totalsaying"></div>
	<div id="totalreport">对车辆进行故障检查，以下0项存在问题</div>
	<table class="bordered" id="error_table">
		<thead>
			<tr>
				<th style="width:80px;">故障码</th>
				<th style="width:75px;">重要</th>
				<th style="width:80px;">类属</th>
				<th style="width:300px;">错误信息</th>
				<th>解决方法</th>
			</tr>
		</thead>
	</table>
	<div id="waitBlock" style="display:none; cursor:default;" align="center">
		<div id="operating">正在操作..</div>
		<p id="waiting"></p>
	</div>
</body>
</html>