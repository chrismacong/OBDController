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
	            type: 'scatter',                                                                 
	            zoomType: 'xy'                                                                   
	        },                                                                                   
	        title: {                                                                             
	            text: '行车速度规划'                        
	        },                                                                                   
	        subtitle: {                                                                          
	            text: '急刹车/急加速与行程平均速度关系'                                                      
	        },                                                                                   
	        xAxis: {                                                                             
	            title: {                                                                         
	                enabled: true,                                                               
	                text: '平均速度 (km/h)'                                                          
	            },                                                                               
	            startOnTick: true,                                                               
	            endOnTick: true,                                                                 
	            showLastLabel: true                                                              
	        },                                                                                   
	        yAxis: {                                                                             
	            title: {                                                                         
	                text: '次数'                                                         
	            }                                                                                
	        },                                                                                   
	        legend: {                                                                            
	            layout: 'vertical',                                                              
	            align: 'left',                                                                   
	            verticalAlign: 'top',                                                            
	            x: 100,                                                                          
	            y: 70,                                                                           
	            floating: true,                                                                  
	            backgroundColor: '#FFFFFF',                                                      
	            borderWidth: 1                                                                   
	        },                                                                                   
	        plotOptions: {                                                                       
	            scatter: {                                                                       
	                marker: {                                                                    
	                    radius: 5,                                                               
	                    states: {                                                                
	                        hover: {                                                             
	                            enabled: true,                                                   
	                            lineColor: 'rgb(100,100,100)'                                    
	                        }                                                                    
	                    }                                                                        
	                },                                                                           
	                states: {                                                                    
	                    hover: {                                                                 
	                        marker: {                                                            
	                            enabled: false                                                   
	                        }                                                                    
	                    }                                                                        
	                },                                                                           
	                tooltip: {                                                                   
	                    headerFormat: '<b>{series.name}</b><br>',                                
	                    pointFormat: '{point.x} km/h, {point.y} 次'                                
	                }                                                                            
	            }                                                                                
	        },                                                                                   
	        series: [{                                                                           
	            name: '急刹车',                                                                  
	            color: 'rgba(223, 83, 83, .5)',                                                  
	            data: ${brake_speed}   
	        }, {                                                                                 
	            name: '急加速',                                                                    
	            color: 'rgba(119, 152, 191, .5)',                                                
	            data: ${speedup_speed}                                              
	        }]                                                                                   
	    });                                                                                      
	});                                                                                          				
  </script>
</head>
<body>
  <div id="container" style="min-width:700px;height:400px"></div>
</body>
</html>