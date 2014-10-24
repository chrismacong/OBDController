<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Speed Graph</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(function () {
	var date_list = "${date_list}";
	var speed_list = "${speed_list}";
	window.setInterval("window.location.reload()", parseInt("${graph_refresh_minute}"));
	function makeDateList(date_list){
		var jsonStr = "[";
		var dates = date_list.split(",");
		for(var i=0;i<dates.length;i++){
			jsonStr += "\'" + dates[i] + "\',";
		}
		jsonStr = jsonStr.substring(0,jsonStr.lastIndexOf(','));
		jsonStr += "]";
	    return eval(jsonStr);
	}
	function makeSpeedList(speed_list){
		var jsonStr = "[";
		var speeds = speed_list.split(",");
		for(var i=0;i<speeds.length;i++){
			jsonStr += speeds[i] + ",";
		}
		jsonStr = jsonStr.substring(0,jsonStr.lastIndexOf(','));
		jsonStr += "]";
	    return eval(jsonStr);
	}
        $('#container').highcharts({
            title: {
                text: 'OBD速度折线图',
                x: -20 //center
            },
            subtitle: {
                text: '终端ID: ${terminalId}',
                x: -20
            },
            xAxis: {
                categories: makeDateList(date_list)
            },
            yAxis: {
                title: {
                    text: '速度 (km/h)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: 'km/h'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'OBD速度',
                data: makeSpeedList(speed_list)
            }]
        });
    });
    

</script>
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
	<script src="${pageContext.request.contextPath}/js/exporting.js"></script>

	<div id="container"
		style="min-width: 310px; height: 400px;"></div>

</body>
</html>
