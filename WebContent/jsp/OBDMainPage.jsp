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
					+ "\"menuname\" : \"参数设置与查询\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/character.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "3\","
					+ "\"menuname\" : \"OBD状态查询\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/obdinfo.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "4\","
					+ "\"menuname\" : \"地理位置和驾驶习惯\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/positioninfo.html?terminalId="
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
					+ "\"menuname\" : \"配置升级服务器信息\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/updateconfig.html?terminalId="
					+ terminals[i]
					+ "\""
					+ "}, {"
					+ "\"menuid\" : \"1"
					+ i
					+ "7\","
					+ "\"menuname\" : \"AGPS\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/agps.html?terminalId="
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
					+ "\"menuname\" : \"车辆健康体检\","
					+ "\"icon\" : \"icon-nav\","
					+ "\"url\" : \"${pageContext.request.contextPath}/vehicleexm.html?terminalId="
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
				"menuname" : "更新服务器设置",
				"icon" : "icon-nav",
				"url" : "#"
			}, {
				"menuid" : "202",
				"menuname" : "其他设置",
				"icon" : "icon-nav",
				"url" : "#"
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
		$('#loginOut').click(function() {
			$.messager.confirm('系统提示','您确定要退出本次登录吗?',function(r) {
				if (r) {
					$.cookie('email', null, {
						path : "/"
					});
					$.cookie('password', null,
					{
						path : "/"
					});
					window.location.href = "${pageContext.request.contextPath}/login.html";
				}
			});
		})
		var refresh_T = "${online_terminal_refresh_minute}";
		window.setInterval("initEvery5Second()", parseInt(refresh_T));
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
			-${useremail}- <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a>
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
		<div class="footer">By chrismacong. DeployTimeStamp: 2014-04-14
			11:39 12:52:24</div>
	</div>
	<div region="west" hide="true" split="true" title="导航菜单"
		style="width: 240px;" id="west">
		<div id='wnav' class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->

		</div>

	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">

				<h1>Welcome to using The OBD Support System</h1>
				<div>欢迎使用OBD在线支持系统 版本号1.0.1</div>

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