<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Dash Board</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
    <style>
      body {
        text-align: center;
      }
      
      #g1, #g2, #g3 {
        width:200px; height:160px;
        display: inline-block;
        margin: 1em;
      }
    </style>
    
    <script src="${pageContext.request.contextPath}/js/raphael.2.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/justgage.1.0.1.min.js"></script>
    <script>
      var g1, g2, g3;
      
      window.onload = function(){
      var g1 = new JustGage({
    	  id: "g1", 
          value: "${oillevel}", 
          min: 0,
          max: 100,
          title: "油量%",
          label: "",    
          shadowOpacity: 1,
          shadowSize: 0,
          shadowVerticalOffset: 10,        
          levelColors: [
         	"#E71515",
         	"#05CC30"
          ]     
      });
      
      var g2 = new JustGage({
        id: "g2", 
        value: "${absolute}", 
        min: 0,
        max: 100,
        title: "绝对节气门位置",
        label: "",    
        shadowOpacity: 1,
        shadowSize: 0,
        shadowVerticalOffset: 10,        
        levelColors: [
       		"#00fff6",
        	"#ff00fc"
        ]     
      });
      
      var g3 = new JustGage({
        id: "g3", 
        value: "${relative}", 
        min: 0,
        max: 100,
        title: "相对节气门位置",
        label: "",    
        shadowOpacity: 1,
        shadowSize: 0,
        shadowVerticalOffset: 10,        
        levelColors: [
       	"#00fff6",
        "#ff00fc"
        ]     
      });
      setInterval(function(){
    	  g1.refresh(parseInt(refreshByAjax1()));
    	  g2.refresh(parseInt(refreshByAjax2()));
    	  g3.refresh(parseInt(refreshByAjax3()));
      }, 5000);
      };
      function refreshByAjax1(){
    	  var str;
    	  var $params="terminalId=" + "${terminalId}";
    	  $.ajax({
    			type:'GET',
    			contentType: 'application/json',
    			url:"${pageContext.request.contextPath}/obdinfo/dashUpdate.html",  
    			data: $params,
    			dataType: "json",
    			async:false,
        		success:function(data){
        			if (data && data.success == "true") {
        				str = data.oillevel;
        			}
        		},
        		error: function(data){
        			alert("网络延时，请刷新界面");
        		}
    		});
    	  	return str;
      	}
      function refreshByAjax2(){
    	  var str;
    	  var $params="terminalId=" + "${terminalId}";
    	  $.ajax({
    			type:'GET',
    			contentType: 'application/json',
    			url:"${pageContext.request.contextPath}/obdinfo/dashUpdate.html",  
    			data: $params,
    			dataType: "json",
    			async:false,
        		success:function(data){
        			if (data && data.success == "true") {
        				str = data.absolute;
        			}
        		},
        		error: function(data){
        			alert("网络延时，请刷新界面");
        		}
    		});
    	  	return str;
      	}
      function refreshByAjax3(){
    	  var str;
    	  var $params="terminalId=" + "${terminalId}";
    	  $.ajax({
    			type:'GET',
    			contentType: 'application/json',
    			url:"${pageContext.request.contextPath}/obdinfo/dashUpdate.html",  
    			data: $params,
    			dataType: "json",
    			async:false,
        		success:function(data){
        			if (data && data.success == "true") {
        				str = data.relative;
        			}
        		},
        		error: function(data){
        			alert("网络延时，请刷新界面");
        		}
    		});
    	  	return str;
      	}
    </script>

	</head>
  <body>    
  	<p class="title_font">OBD仪表盘</p>
    <div id="g1"></div>
    <div id="g2"></div>
    <div id="g3"></div>
  </body>
</html>
