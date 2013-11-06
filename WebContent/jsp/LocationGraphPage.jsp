<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
body,html,#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden; hidden;
	margin: 0;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=15cf002106718ce6a60a7841ea39f127"></script>
<title>添加普通标注点</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">

// 百度地图API功能
var latitude_list = "${latitude_list}".split(",");
var longitute_list = "${longitute_list}".split(",");
var date_list = "${date_list}".split(",");
var map = new BMap.Map("allmap");
map.enableScrollWheelZoom();
map.addControl(new BMap.NavigationControl());
if(latitude_list.length>0){
	map.centerAndZoom(new BMap.Point(longitute_list[0], latitude_list[0]), 16);
	var marker = new BMap.Marker(new BMap.Point(longitute_list[0], latitude_list[0]));
	var infoWindow = new BMap.InfoWindow(date_list[0]);
	map.addOverlay(marker);
	marker.addEventListener("click", function(){this.openInfoWindow(infoWindow);});
	for(var i=0;i<latitude_list.length-1;i++){
		var polyline = new BMap.Polyline([
			new BMap.Point(longitute_list[i], latitude_list[i]),
			new BMap.Point(longitute_list[i+1], latitude_list[i+1])
		], {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});
		map.addOverlay(polyline)
		addArrow(polyline,10,Math.PI/7); 
		var marker1 = new BMap.Marker(new BMap.Point(longitute_list[i+1], latitude_list[i+1]));
		map.addOverlay(marker1);
		var infoWindow1 = new BMap.InfoWindow(date_list[i]);
		marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});
	}
}
function addArrow(polyline,length,angleValue){ //绘制箭头的函数  
	var linePoint=polyline.getPath();//线的坐标串  
	var arrowCount=linePoint.length;  
	for(var i =1;i<arrowCount;i++){ //在拐点处绘制箭头  
		var pixelStart=map.pointToPixel(linePoint[i-1]);  
		var pixelEnd=map.pointToPixel(linePoint[i]);  
		var angle=angleValue;//箭头和主线的夹角  
		var r=length; // r/Math.sin(angle)代表箭头长度  
		var delta=0; //主线斜率，垂直时无斜率  
		var param=0; //代码简洁考虑  
		var pixelTemX,pixelTemY;//临时点坐标  
		var pixelX,pixelY,pixelX1,pixelY1;//箭头两个点  
		if(pixelEnd.x-pixelStart.x==0){ //斜率不存在是时  
	    	pixelTemX=pixelEnd.x;  
	    	if(pixelEnd.y>pixelStart.y)  
	    	{  
	    		pixelTemY=pixelEnd.y-r;  
	    	}  
	    	else  
	   		{  
	    		pixelTemY=pixelEnd.y+r;  
	    	}     
	    	//已知直角三角形两个点坐标及其中一个角，求另外一个点坐标算法  
	   		pixelX=pixelTemX-r*Math.tan(angle);   
	    	pixelX1=pixelTemX+r*Math.tan(angle);  
	    	pixelY=pixelY1=pixelTemY;  
		}  
		else  //斜率存在时  
		{  
	    	delta=(pixelEnd.y-pixelStart.y)/(pixelEnd.x-pixelStart.x);  
	    	param=Math.sqrt(delta*delta+1);  
	  
	    	if((pixelEnd.x-pixelStart.x)<0) //第二、三象限  
	    	{  
	    		pixelTemX=pixelEnd.x+ r/param;  
	    		pixelTemY=pixelEnd.y+delta*r/param;  
	    	}  
	    	else//第一、四象限  
	    	{  
	   			pixelTemX=pixelEnd.x- r/param;  
	    		pixelTemY=pixelEnd.y-delta*r/param;  
	    	}  
	    	//已知直角三角形两个点坐标及其中一个角，求另外一个点坐标算法  
	    	pixelX=pixelTemX+ Math.tan(angle)*r*delta/param;  
	    	pixelY=pixelTemY-Math.tan(angle)*r/param;  
	  
	    	pixelX1=pixelTemX- Math.tan(angle)*r*delta/param;  
	    	pixelY1=pixelTemY+Math.tan(angle)*r/param;  
		}
		var pointArrow=map.pixelToPoint(new BMap.Pixel(pixelX,pixelY));  
		var pointArrow1=map.pixelToPoint(new BMap.Pixel(pixelX1,pixelY1));  
		var Arrow = new BMap.Polyline([  
	    	pointArrow,  
	    	linePoint[i],  
	    	pointArrow1  
		], {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});  
		map.addOverlay(Arrow);  
		
	}  
}
window.setInterval("window.location.reload()", parseInt("${graph_refresh_minute}"));
</script>