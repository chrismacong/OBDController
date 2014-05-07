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
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/changeMore.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<title>添加普通标注点</title>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
var result = "";
// 百度地图API功能
var gps_latitude_list = "${latitude_list}".split(",");
var gps_longitute_list = "${longitute_list}".split(",");
//1
var terminal_id = "${terminalId}";
var date_list = "${date_list}".split(",");
var points = new Array(date_list.length);
var geocoders = new Array(date_list.length);
var infoWindow_list = new Array(date_list.length);
var latitude_list = new Array(date_list.length);
var longitute_list = new Array(date_list.length);
var map = new BMap.Map("allmap");
map.enableScrollWheelZoom();
map.addControl(new BMap.NavigationControl());
//
var myGeo = new BMap.Geocoder();
for(var i=0;i<points.length;i++){
	points[i] = new BMap.Point(gps_longitute_list[i], gps_latitude_list[i])
}
function getGeocoder(){
	
}

function callback(xyResults){
	var xyResult = null;
	for(var index in xyResults){
		xyResult = xyResults[index];
	  	if(xyResult.error != 0){continue;}//出错就直接返回;
	  	longitute_list[index] = xyResult.x;
	  	latitude_list[index] = xyResult.y;
	}
	/* for(var i=0;i<geocoders.length;i++){
		myGeo.getLocation(new BMap.Point(base64decode(longitute_list[0]), base64decode(latitude_list[0])),function(result){
			if(result){
				geocoders[i] = result.address;
			}
			else
				geocoders[i] = "Nil";
		});
	} */
	if(latitude_list.length>0){
  		map.centerAndZoom(new BMap.Point(longitute_list[longitute_list.length-1], latitude_list[latitude_list.length-1]), 16);
  		var marker = new BMap.Marker(new BMap.Point(longitute_list[0], latitude_list[0]));
  		myGeo.getLocation(new BMap.Point(base64decode(longitute_list[0]), base64decode(latitude_list[0])),function(result){
  			var geocoder0 = result.address;
  			var infoWindow = new BMap.InfoWindow(date_list[0] + "<br/>" + base64decode(longitute_list[0]) + "<br/>" + base64decode(latitude_list[0]) + "<br/>" + geocoder0);
  	  		map.addOverlay(marker);
  	  		marker.addEventListener("click", function(){this.openInfoWindow(infoWindow);});
  	  		for(var i=0;i<latitude_list.length-1;i++){
  	  			var polyline = new BMap.Polyline([
  	  				new BMap.Point(longitute_list[i], latitude_list[i]),
  	  				new BMap.Point(longitute_list[i+1], latitude_list[i+1])
  	  			], {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});
  	  			map.addOverlay(polyline)
  	  			addArrow(polyline,5,Math.PI/7); 
  	  			var marker1 = new BMap.Marker(new BMap.Point(longitute_list[i+1], latitude_list[i+1]));
  	  			marker1.zIndex = i+1;
  	  			map.addOverlay(marker1);
  	  			(function(x,thismarker){
  	  				myGeo.getLocation(new BMap.Point(base64decode(longitute_list[x+1]), base64decode(latitude_list[x+1])),function(singleresult){
  	  					var geocoder = singleresult.address;
  	  	  				infoWindow_list[x+1] = new BMap.InfoWindow(date_list[x+1] + "<br/>" + base64decode(longitute_list[x+1]) + "<br/>" + base64decode(latitude_list[x+1]) + "<br/>" + geocoder);
  	  	  				thismarker.addEventListener("click", function(){this.openInfoWindow(infoWindow_list[this.zIndex]);});
  	  				});
  	  			})(i,marker1);
  	  		}
  		});
  	}
}
setTimeout(function(){
    BMap.Convertor.transMore(points,0,callback);        //一秒之后开始进行坐标转换。参数2，表示是从GCJ-02坐标到百度坐标。参数0，表示是从GPS到百度坐标
}, 1000)
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
function refreshPointsFromServer(){
	var $params="terminalId=" + terminal_id;
	$.ajax({
		type:'GET',
		contentType: 'application/json',  
		url:"refreshlgraph.html",
		data: $params,
		dataType: "json",
		success:function(data){
			if (data && data.success == "true") {
				gps_latitude_list = data.refreshed_latitude_list.split(",");
				gps_longitute_list = data.refreshed_longitute_list.split(",");
				date_list = data.refreshed_date_list.split(",");
				var new_points = new Array(date_list.length);
				latitude_list = new Array(date_list.length);
				longitute_list = new Array(date_list.length);
				for(var i=0;i<new_points.length;i++){
					new_points[i] = new BMap.Point(gps_longitute_list[i], gps_latitude_list[i])
				}
				map.clearOverlays();
				setTimeout(function(){
				    BMap.Convertor.transMore(new_points,0,callback);        //一秒之后开始进行坐标转换。参数2，表示是从GCJ-02坐标到百度坐标。参数0，表示是从GPS到百度坐标
				}, 0)
			}
			else{
				alert("操作失败，请重新操作")
			}
		},
		error:function(){
			alert("操作失败，请重新操作")
		}
	});
}
window.setInterval("refreshPointsFromServer()", parseInt("${graph_refresh_minute}"));

	var base64encodechars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	var base64decodechars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62,
			-1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1,
			-1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1,
			26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42,
			43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
	function base64decode(str) {
		var c1, c2, c3, c4;
		var i, len, out;

		len = str.length;

		i = 0;
		out = "";
		while (i < len) {

			do {
				c1 = base64decodechars[str.charCodeAt(i++) & 0xff];
			} while (i < len && c1 == -1);
			if (c1 == -1)
				break;

			do {
				c2 = base64decodechars[str.charCodeAt(i++) & 0xff];
			} while (i < len && c2 == -1);
			if (c2 == -1)
				break;

			out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

			do {
				c3 = str.charCodeAt(i++) & 0xff;
				if (c3 == 61)
					return out;
				c3 = base64decodechars[c3];
			} while (i < len && c3 == -1);
			if (c3 == -1)
				break;

			out += String.fromCharCode(((c2 & 0xf) << 4) | ((c3 & 0x3c) >> 2));

			do {
				c4 = str.charCodeAt(i++) & 0xff;
				if (c4 == 61)
					return out;
				c4 = base64decodechars[c4];
			} while (i < len && c4 == -1);
			if (c4 == -1)
				break;
			out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
		}
		return out;
	}

	function getGeocoder(lat,lon){
		
	}
</script>