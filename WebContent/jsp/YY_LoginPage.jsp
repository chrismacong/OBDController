<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" content='0;url=login.html'/>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery.cookie.js"></script>

 <script type="text/javascript">

 window.onload=function()//用window的onload事件，窗体加载完毕的时候
 {
	 var email = $.cookie('email');
	 var password = $.cookie('password');
	 email = email.substring(1,(email.length-1));
     if(email!=null){
		window.location.href = "${pageContext.request.contextPath}/login/login_check.html?email="+email+"&password="+password+"&rememberMe=on";
	 }
 }
 
      function checkEmail()
    {
	   
	    var email = document.getElementById("login-input-email");
		var email_message = document.getElementById("email-message");
	    if(email.value!=""){
		var reg=/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
		if(!reg.test(email.value)){
        email_message.innerText="请输入正确邮箱地址!";
		email_message.style.display="";
		
		}
		}
    }
      function checkEmailInputing()
      {
  	   
  		var email_message = document.getElementById("email-message");
  		email_message.innerText="";
  		email_message.style.display="none";
  		var password_message = document.getElementById("password-message");
  		password_message.innerText="";
  		password_message.style.display="none";
  		
      }
      function checkPasswordInputing()
      {
  	   
  		var password_message = document.getElementById("password-message");
  		password_message.innerText="";
  		password_message.style.display="none";
  		
      }

      
     function logincheck(){
    	 
    	  // document.getElementById("login-input-password").value = hex_md5(document.getElementById("login-input-password").value);
    	   var email = document.getElementById("login-input-email");
    	   
   		   var email_message = document.getElementById("email-message");
   		   var password = document.getElementById("login-input-password");
   		   var password_message = document.getElementById("password-message");
   		   var rememberMe =  document.getElementById("login-input-remember");
   		   var reg=/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
   		   
   		 
     	   if(email.value==""){
         	  
     		  email_message.innerText="请输入邮箱地址!";
     		  email_message.style.display="";
       	     return;
     		}else if(!reg.test(email.value)){
     			email_message.innerText="请输入正确邮箱地址!";
       		    email_message.style.display="";
       		 return;
     		}else if(password.value==""){
     		    password_message.innerText="请输入密码!";
     	  		password_message.style.display="";
     	  		return;
     	  	}else{
         	  if(rememberMe.checked==true){
         		 window.location.href = "${pageContext.request.contextPath}/login/login_check.html?email="+email.value+"&password="+hex_md5(password.value)+"&rememberMe="+rememberMe.value;
             	  }
			  window.location.href = "${pageContext.request.contextPath}/login/login_check.html?email="+email.value+"&password="+hex_md5(password.value)+"&rememberMe=null";
     	  	}
		}
 </script>
<!--   <script type="text/javascript">
	
</script>
-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/yy.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/yy_md5.js"></script>


<title>OBD在线支持系统</title>
</head>

<body >
<jsp:useBean  id="user"
	class="com.fix.obd.web.model.YY_User" scope="session"></jsp:useBean>
    
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 70px; background: #28a9ff repeat-x center 50%; line-height: 55px; color: #fff; font-family: Verdana, Î¢ï¿½ï¿½ï¿½Åºï¿½, ï¿½ï¿½ï¿½ï¿½">
		 <span style="padding-left: 10px; padding-top:8px; font-size: 40px; float: left;"><img
			src="${pageContext.request.contextPath}/images/blocks.gif" width="20" height="20" align="absmiddle" />
			OBD在线支持系统</span>
		
	</div>
<div id="user-auth-dialog-container" style="position: absolute; display: block; z-index: 999999; margin: 100px 0px; zoom: 1; top: 25px; visibility: visible;" lang="en">
     <div class="dialog-body">                                       
		 <div class="dialog-tab dialog-box-login" >                
		    <div class="clearfix"></div>  
                               
			<div class="dialog-box">                            
			   <div class="dialog-title">系统登录</div>                       
		    </div>      
                             
			<div class="input-box text-input-box">                            
			  <input  autofocus="" tabindex="1" id="login-input-email" name="email" type="text" maxlength="60" class="inp enter-key" validation="notempty;email" placeholder="邮箱" onkeydown="checkEmailInputing()" onchange="checkEmail()" value="${email}" />
			  
			  <label name="email" class="input-box-desc hidden-element error-message" style="display: ;" id="email-message">${email_message}</label>           
			  <label class="input-box-desc desc-message">e.g. yourname@gmail.com</label>                        
			</div>                        
			                       
		   <div class="input-box text-input-box">                           
		      <input  tabindex="2" id="login-input-password" name="password" type="password" maxlength="15" class="inp enter-key" validation="notempty;length:{4,15};password" placeholder="密码" value="${password}" onkeydown="checkPasswordInputing()"  />                            
			  <label name="password" class="input-box-desc hidden-element error-message" style="display: ;" id="password-message" >${password_message}</label>              
              <span class="input-box-desc desc-message"></span>                        
			</div>              
                      
			<div class="dialog-box">                            
			   <span class="dialog-checkbox">                              
			     <input tabindex="5" id="login-input-remember" name="rememberMe" type="checkbox" checked="checked">                
				 <label for="login-input-remember">记住我！</label>                               
				 <a id="login-switch-context-forgot-password" tabindex="9" href="#" class="switch-context switch-context-link right" rev="forgot-password">忘记密码？</a>
			   </span>                        
			</div>                        
			<div class="clearfix"></div>                        
			<div class="action-box">                            
			   <input type="submit" tabindex="6" id="login-action-go" class="dialog-button action-login" value="登录"  onclick="logincheck()">                    
		    </div>
             
            <div class="dialog-box">                          
			   <span class="dialog-checkbox">                                                             
				  <label for="new-user">还不是我们的用户？</label><a id="login-new-user" tabindex="9" href="${pageContext.request.contextPath}/register.html" class="switch-context switch-context-link " rev="new-user">立即注册</a>
			   </span>                        
			</div>                    
		</div>  <!-- form -->                        
      </div>
  </div>
</body>
</html>
