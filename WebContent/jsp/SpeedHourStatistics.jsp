<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en">
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/exporting.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts-more.js"></script>
  <script>
  $(function () {
	    $('#container').highcharts({
	        
		    chart: {
		        polar: true
		    },
		    
		    title: {
		        text: '速度/时间统计图'
		    },

	    	credits: {
	            enabled: false
	        },
	        
		    pane: {
		        startAngle: 0,
		        endAngle: 360
		    },
		
		    xAxis: {
		        tickInterval: 15,
		        min: 0,
		        max: 360,
		        labels: {
		        	formatter: function () {
		        		return this.value/15;
		        	}
		        }
		    },
		        
		    yAxis: {
		        min: 0
		    },
		    
	        tooltip: {
	            valueSuffix: 'km/h'
	        },
	        
		    plotOptions: {
		        series: {
		            pointStart: 0,
		            pointInterval: 15
		        },
		        column: {
		            pointPadding: 0,
		            groupPadding: 0
		        }
		    },
		
		    series: [{
		        type: 'column',
		        name: '平均速度',
		        data: ${speed_of_hour},
		        pointPlacement: 'between'
		    }, {
		        type: 'line',
		        name: '最大速度',
		        data: ${max_speed_of_hour},
		        pointPlacement: 'between'
		    }]
		});
	});
					
  </script>
</head>
<body>
  <div id="container" style="height:400px"></div>
</body>
</html>