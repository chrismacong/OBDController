<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/travelinfo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript">
	var terminal_id;
	$(function(){
		terminal_id = "" + ${terminalId};
	});
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/travelinfo.js"></script>
<title>行程记录信息</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId} 的行程记录</div>
	<button id="refresh_button">手动刷新</button>
	<button id="get_travel_info_button">获取最近行程记录</button>
	<button id="get_which_travel_info_button">获取特定行程记录</button>
	<select id="travel_info_select">
		<option>0</option>
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
		<option>6</option>
		<option>7</option>
		<option>8</option>
		<option>9</option>
		<option>10</option>
		<option>11</option>
		<option>12</option>
		<option>13</option>
		<option>14</option>
		<option>15</option>
		<option>16</option>
		<option>17</option>
		<option>18</option>
		<option>19</option>
	</select>
	<table class="bordered" id="travel_info_table">
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