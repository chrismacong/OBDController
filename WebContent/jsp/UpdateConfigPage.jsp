<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/configupdate.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript">
	var terminal_id = "${terminalId}";
	$(function(){
		$("#submit_button").click(function(){
			var selected_index = $("#type_select").get(0).selectedIndex + 1;
			var server_port = $("#server_port")[0].value;
			var server_ip = $("#server_ip")[0].value;
			var server_apn = $("#server_apn")[0].value;
			$.blockUI({
				message: $('#waitBlock'),
				css:{
					top:'20%',
					width:'24%',
					height:'100px',
					left:'38%'
				}
			});
			var $params="terminalId=" + terminal_id + "&selected_index=" + selected_index + "&server_port=" + server_port + "&server_ip=" + server_ip + "&server_apn=" + server_apn;
			$.ajax({
				type:'GET',
				contentType: 'application/json',  
				url:"updateconfig/configserver.html",
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
		})
	})
</script>
<title>配置服务器升级信息</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId}
	</div>
	<table class="bordered" id="info_table">
		<thead>
			<tr>
				<th colspan="2">服务器信息</th>
			</tr>
		</thead>
		<tr>
			<td>升级服务器类型</td>
			<td><select id="type_select">
				<option>tcp,ip地址</option>
				<option>udf,ip地址</option>
				<option>tcp,域名地址</option>
				<option>udf,域名地址</option>
				<option>使用本服务器升级</option>
			</select></td>
		</tr>
		<tr>
			<td>服务器端口</td>
			<td><input class="update_config_input" id="server_port" type="text"></td>
		</tr>
		<tr>
			<td>服务器ip/域名</td>
			<td><input class="update_config_input" id="server_ip" type="text"></td>
		</tr>
		<tr>
			<td>服务器APN</td>
			<td><input class="update_config_input" id="server_apn" type="text"></td>
		</tr>
	</table>
	<button id="submit_button">提交</button>
	<div id="waitBlock" style="display:none; cursor:default;" align="center">
		<div id="operating">正在操作..</div>
		<p id="waiting"></p>
	</div>
</body>
</html>