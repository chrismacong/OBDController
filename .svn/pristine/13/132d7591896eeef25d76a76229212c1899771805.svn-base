<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy_md5.js"></script>
 <script type="text/javascript">
<%--  window.onload=function(){
	 
	 if(<%=request.getAttribute("checked")%>==null){
		    var message = document.getElementById("password-reget-message"); 
	   		message.innerText="对不起，您无权访问此页面！";
	   		message.style.display="";
	   		document.getElementById("password").style.display="none";
	   		document.getElementById("password-again").style.display="none";
	   		document.getElementById("password-reset").style.display="none";
	 }
 } --%>

 function checkPassword(){
	 var userPass = document.getElementById("password").value; 
	   	var userRePass = document.getElementById("password-again").value; 
	   	var passwordMessage = document.getElementById("password-message");
	   	passwordMessage.style.display="none";
	   	if(userPass==""||userRePass==""){
	   		passwordMessage.innerText="密码不能为空";
	   		passwordMessage.style.display="";
	   		document.getElementById("password").value="";
	   		document.getElementById("password-again").value="";
	   		return false;
	   	}
	   	if(!(userPass==userRePass)){
	   		passwordMessage.innerText="两次密码输入不同，请重新输入";
	   		passwordMessage.style.display="";
	   		document.getElementById("password").value="";
	   		document.getElementById("password-again").value="";
	   		return false;
	   	}else{
	   		passwordMessage.innerText="";
	       	passwordMessage.style.display="none";
	      
	   	}
 }
 var xmlHttp;
 function createXMLHttpRequest(){
	  if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlHttp=new XMLHttpRequest();
		  /* alert("in createXMLHttpRequest:XMLHttpRequest"); */
	  }
	  else
	  {// code for IE6, IE5
		  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		  /* alert("in createXMLHttpRequest:ActiveXObject"); */
	  }
 }
 function resetPassword(){
 	var userPass = document.getElementById("password").value; 
   	var userRePass = document.getElementById("password-again").value; 
   	var passwordMessage = document.getElementById("password-message");
   	passwordMessage.style.display="none";
   	if(userPass==""||userRePass==""){
   		passwordMessage.innerText="密码不能为空";
   		passwordMessage.style.display="";
   		document.getElementById("password").value="";
   		document.getElementById("password-again").value="";
   		return false;
   	}
   	if(!(userPass==userRePass)){
   		passwordMessage.innerText="两次密码输入不同，请重新输入";
   		passwordMessage.style.display="";
   		document.getElementById("password").value="";
   		document.getElementById("password-again").value="";
   		return false;
   	}else{
   		passwordMessage.innerText="";
       	passwordMessage.style.display="none";
       	//传到后台
		createXMLHttpRequest();
 		xmlHttp.onreadystatechange = function(){
			var responseContext;
			if(xmlHttp.readyState == 4){
				if(xmlHttp.status == 200){
					responseContext = xmlHttp.responseText;
					if(responseContext.indexOf("true")!=-1){
					    var message = document.getElementById("password-reget-message-success"); 
				   		message.innerText="密码修改成功！";
				   		message.style.display="";
				   		document.getElementById("return-to-login").style.display="";
				   		document.getElementById("password").style.display="none";
				   		document.getElementById("password-again").style.display="none";
				   		document.getElementById("password-reset").style.display="none";                     //验证成功
					}else{
				   		document.getElementById("password").value="";
				   		document.getElementById("password-again").value="";
				   		document.getElementById("password-reget-message-error").innerText="对不起，您无权进行此操作!";
						email_message.style.display="";
					}
				}
			}
		} 
		xmlHttp.open("GET", "${pageContext.request.contextPath}/mail/password_reset.html?password="+hex_md5(document.getElementById("password").value),true);
		xmlHttp.send(null);
		if(document.getElementById("password-reget-message-success").innerText==null){
			return false;
		}else{
			return true;
		}
   	}
   	
}
 </script>
<title>OBD在线支持系统--找回密码</title>
</head>
<body>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 70px; background: #28a9ff repeat-x center 50%; line-height: 55px; color: #fff; font-family: Verdana, Î¢ï¿½ï¿½ï¿½Åºï¿½, ï¿½ï¿½ï¿½ï¿½">
		<span style="padding-left: 10px; padding-top: 8px; font-size: 40px; float: left;">
			<img src="${pageContext.request.contextPath}/images/blocks.gif" width="20" height="20" align="absmiddle" /> 
		OBD在线支持系统</span>
	</div>
	<div id="user-auth-dialog-container"
		style="position: absolute; display: block; z-index: 999999; margin: 100px 0px; zoom: 1; top: 25px; visibility: visible;"
		lang="en">
		<div class="dialog-body">
			<div class="dialog-tab dialog-box-login">
				<div class="clearfix"></div>
				<div class="dialog-box">
					<div class="dialog-title">重设密码</div>
					<label name="message" class="input-box-desc hidden-element error-message" style="display: ; font-size:24px" id="password-reget-message-success"></label>
					<a id="return-to-login" href="${pageContext.request.contextPath}/login.html" class="switch-context switch-context-link " style="display: none;">返回登陆界面</a> 
					<label name="message" class="input-box-desc hidden-element error-message" style="display: ;color:red; font-size:24px" id="password-reget-message-error"></label>                   
				</div>
				<div class="input-box text-input-box">
					<input tabindex="1" id="password" name="password"
						type="password" maxlength="15" class="inp enter-key"
						validation="notempty;length:{4,15};password" placeholder="密码">
				</div>

				<div class="input-box text-input-box">
					<input tabindex="2" id="password-again"
						name="password-again" type="password" maxlength="15"
						class="inp enter-key" validation="notempty;length:{4,15};password"
						placeholder="再次输入密码" onblur="checkPassword()"> <label
						name="password-message"
						class="input-box-desc hidden-element error-message"
						style="display: none;" id="password-message"></label>
				</div>
				<div class="action-box">
					<input type="submit" id="password-reset"
						class="dialog-button action-login" value="重设密码"
						onkeydown="if(event.keyCode==13){event.keyCode=9}"
						onclick="resetPassword()" >
				</div>
			</div>
		</div>
	</div>
</body>
</html>