<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/terminalinfo.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<title>设备信息</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId} 的详细信息</div>
	<img id="product_img" src="${pageContext.request.contextPath}/images/terminal/${info.getProduct()}.jpg">
	<table class="bordered" id="info_table">
		<thead>
			<tr>
				<th colspan="2">基本参数</th>
			</tr>
		</thead>
			<tr>
				<td>Id</td>
				<td>${terminalId}</td>
			</tr>
			<tr>
				<td>IP地址</td>
				<td>${info.getTerminalIp()}</td>
			</tr>
			<tr>
				<td>终端型号</td>
				<td>${info.getProduct()}</td>
			</tr>
			<tr>
				<td>最近连接时间</td>
				<td>${lastUpdateDate}</td>
			</tr>
	</table>
	<button id="modify_button">修改</button>
	<table class="bordered" id="user_table">
		<thead>
			<tr>
				<th colspan="2">用户信息</th>
			</tr>
		</thead>
			<tr>
				<td>邮箱</td>
				<td>${user.getEmail()}</td>
			</tr>
			<tr>
				<td>姓名</td>
				<td>${user.getRealname()}</td>
			</tr>
			<tr>
				<td>身份证号</td>
				<td>${user.getIdnumber()}</td>
			</tr>
			<tr>
				<td>昵称</td>
				<td>${user.getNickname()}</td>
			</tr>
			<tr>
				<td>车辆牌号</td>
				<td>${user.getCarnumber()}</td>
			</tr>
			<tr>
				<td>车型</td>
				<td>${user.getCartype()}</td>
			</tr>
			<tr>
				<td>联系电话</td>
				<td>${user.getTel()}</td>
			</tr>
	</table>
</body>
</html>