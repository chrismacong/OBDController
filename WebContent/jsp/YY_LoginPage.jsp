<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%
	try {
	    	System.out.println("I'm checking!");
	        Cookie[] cookies = request.getCookies() ;
	        if (cookies != null) {
	        	 System.out.println("cookie!null");
	        	 System.out.println("cookie length"+cookies.length);
	            for (int i = 0; i < cookies.length; i++) {
	                Cookie newCookie = cookies[i];
	                if ("user".equals(newCookie.getName() )) {
	                	 response.sendRedirect(request.getContextPath()+"/jsp/YY_RegisterPage.jsp");
	                	//直接定位到主界面！
	                	/*
	                	System.out.println("user!null");
	                    String email = newCookie.getValue().split("-")[0];
	                    String password = newCookie.getValue().split("-")[1];
	                    request.setAttribute("email", email);
	                    request.setAttribute("password", password);
	                    System.out.println("email"+email);
	                   */
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Exception!");
	    }
        
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery/jquery-1.8.2.min.js"></script>

 <script type="text/javascript">
      function checkEmail()
    {
	   
	    var email = document.getElementById("login-input-email");
		var email_message = document.getElementById("email-message");
		//email_message.style.display="none";
		if(email.value!=""){
		//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		var reg=/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
		if(!reg.test(email.value)){
        //x.className = 'error-message'; 
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
       
      
 </script>
<!--   <script type="text/javascript">
	
</script>
-->
<link rel="stylesheet" type="text/css" href="../css/yy.css">


<title>OBD在线支持系统</title>
</head>

<body >

    
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 70px; background: url(images/layout-browser-hd-bg.gif) #28a9ff repeat-x center 50%; line-height: 55px; color: #fff; font-family: Verdana, Î¢ï¿½ï¿½ï¿½Åºï¿½, ï¿½ï¿½ï¿½ï¿½">
		 <span style="padding-left: 10px; padding-top:8px; font-size: 40px; float: left;"><img
			src="images/blocks.gif" width="20" height="20" align="absmiddle" />
			OBD在线支持系统</span>
		
	</div>
<div id="user-auth-dialog-container" style="position: absolute; display: block; z-index: 999999; margin: 100px 0px; zoom: 1; top: 25px; visibility: visible;" lang="en">
     <div class="dialog-body">                                       
		 <form class="dialog-tab dialog-box-login" action="${pageContext.request.contextPath}/login/checkuser.html" method="post" >                
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
			   <input type="submit" tabindex="6" id="login-action-go" class="dialog-button action-login" value="登录" >                    
		    </div>
             
            <div class="dialog-box">                          
			   <span class="dialog-checkbox">                                                             
				  <label for="new-user">还不是我们的用户？</label><a id="login-new-user" tabindex="9" href="YY_RegisterPage.jsp" class="switch-context switch-context-link " rev="new-user">立即注册</a>
			   </span>                        
			</div>                    
		</form>                          
      </div>
  </div>
</body>
</html>
