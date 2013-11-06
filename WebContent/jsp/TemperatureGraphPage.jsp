<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Temperature Graph</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(function () {
	window.setInterval("window.location.reload()", parseInt("${graph_refresh_minute}"));
	var date_list = "${date_list}";
	var speed_list = "${temperature_list}";
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
	function makeTemperatureList(temperature_list){
		var jsonStr = "[";
		var temperatures = temperature_list.split(",");
		for(var i=0;i<temperatures.length;i++){
			jsonStr += temperatures[i] + ",";
		}
		jsonStr = jsonStr.substring(0,jsonStr.lastIndexOf(','));
		jsonStr += "]";
	    return eval(jsonStr);
	}
        $('#container').highcharts({
            title: {
                text: '发动机水温折线图',
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
                    text: '温度 (°C)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: '发动机水温',
                data: makeTemperatureList(speed_list)
            }]
        });
    });
    

</script>
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/highcharts.js"></script>
	<script src="${pageContext.request.contextPath}/js/exporting.js"></script>

	<div id="container"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>

</body>
</html>
