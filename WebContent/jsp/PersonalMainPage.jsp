<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US">

<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>OBD个人主页</title>
	
	
	<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/personal.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
	<script src="${pageContext.request.contextPath}/js/highcharts-more.js"></script>
	<script src="${pageContext.request.contextPath}/js/exporting.js"></script>
	<script type="text/javascript">
		var useremail = "${useremail}";
	</script>
	<script src="${pageContext.request.contextPath}/js/personal.js"></script>

	<!-- LINK PAGE SCROLLER PRO JS FILE -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pagescroller.lite.js"></script>
	<!-- / -->

	<script type="text/javascript">
	
	
	</script>
	
</head>

<body>
	
	<div id="nav" class="pageScrollerNav standardNav right dark">
		<ul>
			<li><a href="#">欢迎使用</a></li>
			<li><a href="#">行车参数</a></li>
			<li><a href="#">车辆体检</a></li>
			<li><a href="#">驾驶习惯</a></li>
			<li><a href="#">行车轨迹</a></li>
		</ul>
	</div>
	<div id="wrapper">
		<div id="main">
			<div class="section" id="welcome_section"></div>
			<div class="section" id="parameter_section">
				<div id="container1"></div>
				<div id="container2"></div>
				<div id="container3"></div>
				<div id="container4"></div>
				<div id="container5"></div>
				<div id="container6"></div>
			</div>
			<div class="section" id="vehicle_exm_section">
				<iframe id="vehicle_exm_iframe" class="section_iframe" src="${pageContext.request.contextPath}/vehicleexm.html?terminalId=${terminalId}" width="960px" height="540px" scrolling="yes" frameborder="0"></iframe>
			</div>
			<div class="section" id="travel_exm_section">
				<iframe id="travel_exm_iframe" class="section_iframe" src="${pageContext.request.contextPath}/travelexmpersonal.html?terminalId=${terminalId}" width="960px" height="540px" scrolling="yes" frameborder="0"></iframe>
			</div>
			<div class="section" id="map_section">
				<iframe id="position_iframe" class="section_iframe" src="${pageContext.request.contextPath}/positioninfo/getlgraph.html?terminalId=${terminalId}" width="960px" height="540px" scrolling="yes" frameborder="0"></iframe>
			</div>
		</div><!-- [END] #main -->
	</div><!-- [END] #wrapper -->
	<div id="top_list">
		<img id="logo_img" src="${pageContext.request.contextPath}/images/personal/logo.png"></img>
		<a id="title1_on_top_selected"></a>
		<a id="title2_on_top_selected" href="#"></a>
		<a id="title3_on_top_selected" href="#"></a>
		<a id="title4_on_top_selected" href="#"></a>
		<div id="logout_div">${useremail}[ <a href="${pageContext.request.contextPath}/login.html">注销</a> ]</div>
	</div>
</body><!-- [END] body -->
</html>