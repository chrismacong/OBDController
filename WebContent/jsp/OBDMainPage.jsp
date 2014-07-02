<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OBD Support System</title>
<link href="${pageContext.request.contextPath}/css/default.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/themes/icon.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery.easyui.min-1.2.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jQuery/jquery.cookie.js"></script>
<script type="text/javascript"
	src='${pageContext.request.contextPath}/js/yy_md5.js'></script>
<script type="text/javascript"
	src='${pageContext.request.contextPath}/js/outlook.js'></script>
<script type="text/javascript">
	onlineId_list = "${onlineId_list}";
	refresh_Time = "${online_terminal_refresh_minute}";
	var count_interval;
	function ff(onlineId_list) {
		var terminals = onlineId_list.split(";");
		var jsonStr = "[{";
		for ( var i = 0; i < terminals.length; i++) {
			jsonStr += "\"menuid\" : \"1"
					+ i
					+ "\","
					+ "\"icon\" : \"icon-sys\","
					+ "\"menuname\" : \""
					+ terminals[i]
					+ "\","
					+ "\"menus\" : [{"
					+ "\"menuid\" : \"1"
					+ i
					+ "0\","
					+ "\"menuname\" : \"设备信息\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/terminalinfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "1\","
					+ "\"menuname\" : \"OBD日志\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/terminallog.html?terminalId="
					+ terminals[i]
					+ "&pageSize=50\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "2\","
					+ "\"menuname\" : \"地理位置和驾驶习惯\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/positioninfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "3\","
					+ "\"menuname\" : \"车辆健康体检\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/vehicleexm.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "4\","
					+ "\"menuname\" : \"参数设置与查询\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/character.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "5\","
					+ "\"menuname\" : \"行程记录查询\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/travelinfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "6\","
					+ "\"menuname\" : \"OBD状态查询\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/obdinfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "7\","
					+ "\"menuname\" : \"车险风险分析\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/insurance.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "8\","
					+ "\"menuname\" : \"故障码操作\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/dtc.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "9\","
					+ "\"menuname\" : \"配置升级服务器信息\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/updateconfig.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "10\","
					+ "\"menuname\" : \"其他功能\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/other.html?terminalId="
					+ terminals[i] + "\"" + "}]" + "},{";
		}
		jsonStr = jsonStr.substring(0, jsonStr.lastIndexOf(','));
		jsonStr += "]";
		return eval(jsonStr);
	}
	function fg(onlineId_list,online_b) {
		var terminals = onlineId_list.split(";");
		var jsonStr = "[{";
		for ( var i = 0; i < terminals.length; i++) {
			var icon_str = "icon-sys";
			if(online_b[i]=="0")
				icon_str = "icon-away";
			jsonStr += "\"menuid\" : \"1"
					+ i
					+ "\","
					+ "\"icon\" : \"" + icon_str + "\","
					+ "\"menuname\" : \""
					+ terminals[i]
					+ "\","
					+ "\"menus\" : [{"
					+ "\"menuid\" : \"1"
					+ i
					+ "0\","
					+ "\"menuname\" : \"设备信息\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/terminalinfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "1\","
					+ "\"menuname\" : \"OBD日志\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/terminallog.html?terminalId="
					+ terminals[i]
					+ "&pageSize=50\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "2\","
					+ "\"menuname\" : \"地理位置和驾驶习惯\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/positioninfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "3\","
					+ "\"menuname\" : \"车辆健康体检\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/vehicleexm.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "4\","
					+ "\"menuname\" : \"参数设置与查询\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/character.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "5\","
					+ "\"menuname\" : \"行程记录查询\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/travelinfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "6\","
					+ "\"menuname\" : \"OBD状态查询\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/obdinfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "7\","
					+ "\"menuname\" : \"车险风险分析\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/insurance.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "8\","
					+ "\"menuname\" : \"故障码操作\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/dtc.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "9\","
					+ "\"menuname\" : \"配置升级服务器信息\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/updateconfig.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "10\","
					+ "\"menuname\" : \"其他功能\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/other.html?terminalId="
					+ terminals[i] + "\"" + "}]" + "},{";
		}
		jsonStr = jsonStr.substring(0, jsonStr.lastIndexOf(','));
		jsonStr += "]";
		return eval(jsonStr);
	}
	var _menus = {
		obd : ff(onlineId_list),
		config : [ {
			"menuid" : "20",
			"icon" : "icon-sys",
			"menuname" : "全局设置",
			"menus" : [ {
				"menuid" : "201",
				"menuname" : "个人用户管理",
				"icon" : "icon-nav",
				"url" : "${pageContext.request.contextPath}/personaluserconfig.html"
			}, {
				"menuid" : "202",
				"menuname" : "行业用户管理",
				"icon" : "icon-nav",
				"url" : "${pageContext.request.contextPath}/businessuserconfig.html"
			} ]

		} ]
	};
	$(function() {
		openPwd();
		$('#editpass').click(function() {
			$('#w').window('open');
		});
		$('#btnEp').click(function() {
			serverLogin();
		})
		$('#btnCancel').click(function() {
			closePwd();
		})
		$('#loginOut')
				.click(
						function() {
							$.messager
									.confirm(
											'系统提示',
											'您确定要退出本次登录吗?',
											function(r) {
												if (r) {
													$.cookie('email', null, {
														path : "/"
													});
													$.cookie('rolename', null,
															{
																path : "/"
															});
													window.location.href = "${pageContext.request.contextPath}/login.html";
												}
											});
						})
		var refresh_T = "${online_terminal_refresh_minute}";
		count_interval = window.setInterval("initEvery5Second()",
				parseInt(refresh_T));
	});
</script>
<Style>
#css3menu li {
	float: left;
	list-style-type: none;
}

#css3menu li a {
	color: #fff;
	padding-right: 20px;
}

#css3menu li a.active {
	color: yellow;
}
</style>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="float: right; padding-right: 20px;" class="head">欢迎
			-${useremail}- <a href="#" id="editpass">修改密码</a> <a href="#"
			id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px; float: left;"><img
			src="images/blocks.gif" width="20" height="20" align="absmiddle" />
			OBD在线支持系统</span>
		<ul id="css3menu"
			style="padding: 0px; margin: 0px; list-type: none; float: left; margin-left: 40px;">
			<li><a name="obd" href="javascript:;" title="终端数据">终端数据</a></li>
			<li><a name="config" href="javascript:;" title="全局设置">全局设置</a></li>
		</ul>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer">By chrismacong. DeployTimeStamp: 2014-07-02
			22:22 V1.1.4.0</div>
	</div>
	<div region="west" hide="true" split="true" title="导航菜单"
		style="width: 240px;" id="west">
		<div id="scan_button"></div>
		<div id="search_button"></div>
		<div id='wnav' class="easyui-accordion" fit="true" border="false">
			<!--导航内容-->
		</div>

	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">

				<h1>Welcome to using The OBD Support System</h1>
				<div>欢迎使用OBD在线支持系统 版本号1.1.3.11</div>
				<h1>近期版本更新说明</h1>
				<br />
				<h2>1.1.4.0</h2>
				<div>更新了极光推送的Appkey和MarketId，开始新的Android端开发</div>
				<div>加入了今日驾驶行程统计功能，在手机用户登录时进行计算并反馈给用户，用于手机端主页显示。</div>
				<br />
				<h2>1.1.3.11</h2>
				<div>修复了重复体检table不清空的bug</div>
				<br />
				<h2>1.1.3.10</h2>
				<div>修复了清除日志后跳integerformatexception的bug</div>
				<br />
				<h2>1.1.3.9</h2>
				<div>将行程开始地点和结束地点作为字段保存在了travelinfo表中，不再在访问近期行程时进行批量转换，进而缩短访问时间，访问手机端调用service</div>
				<div>修复了单一行程信息中开始时间和结束时间对调的问题</div>
				<br />
				<h2>1.1.3.8</h2>
				<div>添加了BusinessInfo的信息表和Personal_Business的关系映射表</div>
				<div>修改了无法修改企业用户信息的bug</div>
				<div>本版本将作为一个相对稳定的版本，短期内不再进行更新</div>
				<br />
			</div>
		</div>
	</div>


	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a id="btnCancel"
					class="easyui-linkbutton" icon="icon-cancel"
					href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</html>