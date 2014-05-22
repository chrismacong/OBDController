<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/travelExm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/travelexm.js"></script>
<script type="text/javascript">
$(function () {
	$('#container').highcharts({
	            
	    chart: {
	        polar: true,
	        type: 'line'
	    },
	    
	    title: {
	        text: '在好友圈中的健康能力考评',
	        x: -80
	    },
	    
	    pane: {
	    	size: '80%'
	    },

    	credits: {
            enabled: false
        },
        
	    xAxis: {
	        categories: ['节油', '里程', '稳定性', '速度', 
	                '疲劳控制'],
	        tickmarkPlacement: 'on',
	        lineWidth: 0
	    },
	        
	    yAxis: {
	        gridLineInterpolation: 'polygon',
	        lineWidth: 0,
	        min: 0,
	        max: 100
	    },
	    
	    tooltip: {
	    	shared: true,
	        pointFormat: '<span style="color:{series.color}">{series.name}: <b>{point.y}</b><br/>'
	    },
	    
	    legend: {
	        align: 'right',
	        verticalAlign: 'top',
	        y: 70,
	        layout: 'vertical'
	    },
	    
	    series: [{
	        name: '我的评分',
	        data: [parseInt("${oil_score}"), parseInt("${mile_score}"), parseInt("${stability_score}"), parseInt("${speed_score}"), parseInt("${tired_control_score}")],
	        pointPlacement: 'on'
	    },{
	    	name: '满分',
	        data: [100, 100, 100, 100, 100],
	        pointPlacement: 'on'
	    }]
	
	});
});
</script>

<title>驾驶习惯分析</title>
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
	<script src="${pageContext.request.contextPath}/js/highcharts-more.js"></script>
	<script src="${pageContext.request.contextPath}/js/modules/exporting.js"></script>
	<div id="subtitle">驾驶习惯分析</div>
	<div id="terminalTitle">终端Id：${terminalId}</div>
	<div id="container" style="min-width: 400px; max-width: 600px; height: 400px;"></div>
	<div class="healthy_title" id="healthy">健康贴士</div>
	<div id="healthy_container">
		<div>${oil_text}</div>
		<div>${mile_text}</div>
		<div>${stability_text}</div>
		<div>${speed_text}</div>
		<div>${tired_control_text}</div>
	</div>
	<table class="bordered" id="travel_exm_table">
		<thead>
			<tr>
				<th>项目</th>
				<th>统计数据</th>
			</tr>
		</thead>
		<c:forEach items="${character_list}" var="xx" varStatus="loop">
			<tr>
				<td>${xx.getName()}</td>
				<td>${xx.getContent()}${xx.getExtra()}</td>
			</tr>
		</c:forEach>
	</table>
	<a id="hour_btn" title="时钟统计图"></a>
	<a id="speed_hour_btn" title="速度/时钟统计图"></a>
	<a id="brake_hour_btn" title="刹车/时钟统计图"></a>
	<a id="speedup_hour_btn" title="加速/时钟统计图"></a>
	<a id="speedplan_btn" title="车速规划统计图"></a>
	<a id="hotspot_btn" target="_blank" title="热区图" href="${pageContext.request.contextPath}/positioninfo/gethgraph.html?terminalId=${terminalId}"></a>
	<div id="hour_graphBlock" style="display: none; cursor: default;"
		align="center">
		<a class="block_close_btn"></a> <br />
		<iframe id="iframe" src="${pageContext.request.contextPath}/travelexm/hour.html?terminalId=${terminalId}" width="100%" height="500px" scrolling="no" frameborder="0"></iframe>
	</div>
	<div id="speed_hour_graphBlock" style="display: none; cursor: default;"
		align="center">
		<a class="block_close_btn"></a> <br />
		<iframe id="iframe" src="${pageContext.request.contextPath}/travelexm/speedhour.html?terminalId=${terminalId}" width="100%" height="500px" scrolling="no" frameborder="0"></iframe>
	</div>
	<div id="brake_hour_graphBlock" style="display: none; cursor: default;"
		align="center">
		<a class="block_close_btn"></a> <br />
		<iframe id="iframe" src="${pageContext.request.contextPath}/travelexm/brakehour.html?terminalId=${terminalId}" width="100%" height="500px" scrolling="no" frameborder="0"></iframe>
	</div>
	<div id="speedup_hour_graphBlock" style="display: none; cursor: default;"
		align="center">
		<a class="block_close_btn"></a> <br />
		<iframe id="iframe" src="${pageContext.request.contextPath}/travelexm/speeduphour.html?terminalId=${terminalId}" width="100%" height="500px" scrolling="no" frameborder="0"></iframe>
	</div>
	<div id="speedplan_graphBlock" style="display: none; cursor: default;"
		align="center">
		<a class="block_close_btn"></a> <br />
		<iframe id="iframe" src="${pageContext.request.contextPath}/travelexm/speedplan.html?terminalId=${terminalId}" width="100%" height="500px" scrolling="no" frameborder="0"></iframe>
	</div>
	</body>
</html>
