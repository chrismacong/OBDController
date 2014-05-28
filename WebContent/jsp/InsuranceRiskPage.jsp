<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/css/global.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/insurance.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery。easing.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/raphael.2.1.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/insuranceRisk.js"></script>
<title>车险风险分析</title>
</head>
<body>
	<div class="title_font terminal_Id_class" id="terminal_ID_div">终端ID：${terminalId} 车险风险分析</div>
	<div id="content">
        <div id="diagram"></div> 
         <div class="legend">
            
            <div class="ratios">
            <ul>
                <li class="stable">稳定性</li>
                <li class="crecord">碰撞记录</li>
                <li class="age">驾驶员年龄</li>
                <li class="ttime">疲劳驾驶</li>
                <li class="dtime">驾车时间</li>
            </ul>
            </div>	
        </div>
    </div>
	
      <table class="bordered">
          <tr>
          <th>项目</th>
          <th>统计数据</th>
          </tr>
           <tr>
              <td>证件号</td>
              <td>1234567890123456</td>
          </tr>
          <tr>
              <td>年龄</td>
              <td>20岁</td>
          </tr>
          <tr>
              <td>急刹车次数</td>
              <td>15次</td>
          </tr>
          <tr>
              <td>紧急刹车次数</td>
              <td>15次</td>
          </tr>
          <tr>
              <td>急加速次数</td>
              <td>15次</td>
          </tr>
          <tr>
              <td>紧急加速次数</td>
              <td>15次</td>
          </tr>
          <tr>
              <td>疲劳驾驶时间</td>
              <td>150分钟</td>
          </tr>
          <tr id="collnum">
              <td>碰撞次数</td>
              <td>3次</td>
          </tr>
      </table>          
      			
	</div>
   
  	<div class="get">
	
	<div class="arc">
		<span class="text">稳定性</span>
		<input type="hidden" class="percent" value="95" />
		<input type="hidden" class="color" value="#97BE0D" />
	</div>
	
	<div class="arc">
		<span class="text">碰撞记录</span>
		<input type="hidden" class="percent" value="90" />
		<input type="hidden" class="color" value="#D84F5F" />
	</div>
	
	<div class="arc">
		<span class="text">驾驶员年龄</span>
		<input type="hidden" class="percent" value="80" />
		<input type="hidden" class="color" value="#2797E9" />
	</div>
	
	<div class="arc">
		<span class="text">疲劳驾驶</span>
		<input type="hidden" class="percent" value="53" />
		<input type="hidden" class="color" value="#66c" />
	</div>
	
	<div class="arc">
		<span class="text">驾车时间</span>
		<input type="hidden" class="percent" value="45" />
		<input type="hidden" class="color" value="#424242" />
	</div>
	
</div>
    <a id="hour_btn" title="时钟统计图"></a>
	<a id="speed_hour_btn" title="速度/时钟统计图"></a>
	<a id="brake_hour_btn" title="刹车/时钟统计图"></a>
	<a id="speedup_hour_btn" title="加速/时钟统计图"></a>
	<a id="speedplan_btn" title="车速规划统计图"></a>
    <a id="hotspot_btn" target="_blank" title="热区图"></a>  
</body>
</html>