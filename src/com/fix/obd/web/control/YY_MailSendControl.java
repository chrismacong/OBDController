package com.fix.obd.web.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fix.obd.web.service.YY_EditPasswordService;
import com.fix.obd.web.service.YY_UserPasswordService;
import com.fix.obd.web.util.Random24BitArray;

import sun.misc.BASE64Encoder;
@Controller
@RequestMapping("/mail")
public class YY_MailSendControl {
	private MimeMessage mimeMsg; //MIME邮件对象

    private Session session; //邮件会话对象

    private Properties props; //系统属性

    private boolean needAuth = true; //smtp是否需要认证

    private String username = "obd_admin@163.com"; //smtp认证用户名和密码

    private String password = "Obd15298386781";

    private Multipart mp = new MimeMultipart(); //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

    public YY_MailSendControl() {
        try {
              System.out.println("设置系统属性：mail.smtp.host = "+"mail.tplife.com");
            if (props == null) {
                props = System.getProperties(); //获得系统属性对象
            }
            props.put("mail.smtp.host", "smtp.163.com"); //设置SMTP主机
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
              System.out.println("准备获取邮件会话对象！");
            session = Session.getDefaultInstance(props, null); //获得邮件会话对象
            mimeMsg = new MimeMessage(session); //创建MIME邮件对象
        } catch (Exception e) {
            System.err.println("邮件初始化失败！" + e);
        }
    }

    public void setSubject(String sub) {
        try {
            mimeMsg.setSubject(sub, "GB2312");
               System.out.println("设置邮件标题为：" + sub + ".");
        } catch (Exception e) {
            System.err.println("邮件标题设置失败！" + e);
        }
    }

    public void setContent(String text, String type) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent(text, type);
            mp.addBodyPart(bp);
               System.out.println("正在设置邮件内容");
        } catch (Exception e) {
            System.err.println("邮件内容设置失败！" + e);
        }
    }

    public void setText(String text) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setText(text);
            mp.addBodyPart(bp);
              System.out.println("正在设置邮件内容");
        } catch (Exception e) {
            System.err.println("邮件内容设置失败！" + e);
        }
    }

    public void addAttach(String AttachURL) {
        try {
            //添加附件
            BodyPart bp = new MimeBodyPart();
            BASE64Encoder enc = new BASE64Encoder();//解决附件名乱码问题
            FileDataSource fileds = new FileDataSource(AttachURL);
            bp.setDataHandler(new DataHandler(fileds));
            bp.setFileName("=?GBK?B?" + enc.encode((fileds.getName()).getBytes()) + "?=");
              System.out.println("增加邮件附件：" + fileds.getName());
            mp.addBodyPart(bp);
        } catch (Exception e) {
            System.err.println("邮件附件粘贴失败！" + e);
        }
    }

    public void clearAttach() {
        try {
            int n = mp.getCount();
            System.out.println();
            for (int i = 0; i <= n - 1; i++) {
                mp.removeBodyPart(0);
            }
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
        } catch (Exception e) {
            System.err.println("清除附件失败！" + e);
        }
    }

    public void setFrom(String address) {
        try {
            mimeMsg.setFrom(new InternetAddress(address)); //设置发信人
               System.out.println("正在设置发件人地址");
        } catch (Exception e) {
            System.err.println("邮件发件人地址设置失败！" + e);
        }
    }

    public void setRecipients(String address) {
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address)); //收信人
              System.out.println("正在设置收件人地址");
        } catch (Exception e) {
            System.err.println("邮件收件人地址设置失败！" + e);
        }
    }

    public void setRecipients(String[] address, String type) {
        try {
            Address[] add = new Address[address.length];
            if (address.length - 1 >= 0) {
                for (int i = 0; i <= address.length - 1; i++) {
                    add[i] = new InternetAddress(address[i]);
                }
                if (type == null)
                    type = "TO";
                if (type == "TO")
                    mimeMsg.setRecipients(Message.RecipientType.TO, add);
                else if (type == "CC")
                    mimeMsg.setRecipients(Message.RecipientType.CC, add);
                else if (type == "BCC")
                    mimeMsg.setRecipients(Message.RecipientType.BCC, add);//收信人
                else
                    System.out.println("类型不正确!");
                /// System.out.println("正在设置收件人地址");
            }
        } catch (Exception e) {
            System.err.println("邮件收件人地址设置失败！" + e);
        }
    }

    public void setSentDate() {
        try {
            mimeMsg.setSentDate(new Date());
        } catch (Exception e) {
            System.err.println("时间设置失败！" + e);
        }
    }

    public boolean sendMail() {
        boolean flag = false;
        try {

            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            System.out.println("正在发送邮件....");
            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect((String) props.get("mail.smtp.host"), username, password);
            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
            System.out.println("发送邮件成功！");
            transport.close();
            flag = true;
        } catch (SendFailedException e1) {
            System.err.println("邮件发送失败SendFailed！" + e1);
            return false;

        } catch (MessagingException e2) {
            System.err.println("邮件发送失败Messaging！" + e2);
            return false;

        } catch (Exception e3) {
            System.err.println("邮件发送失败！" + e3);
            return false;
        }

        return flag;
    }

//    public static void main(String argv[]) {
//    	YY_MailSendControl ms = new YY_MailSendControl();
//        ms.setSubject("MailSend Test");
//        ms.setText("Test Ok");
//        ms.setFrom("kyuhyundashizi@163.com");
//        String[] result = new String[2];
//        result[0] = "yxy10@software.nju.edu.cn";
//        result[1] = "1016465323@qq.com";
//        ms.setRecipients(result, "TO");
//        ms.setSentDate();
//        ms.sendMail();
//    }
    @Resource
    private YY_UserPasswordService userPasswordService;
    
	public YY_UserPasswordService getUserPasswordService() {
		return userPasswordService;
	}

	public void setUserPasswordService(YY_UserPasswordService userPasswordService) {
		this.userPasswordService = userPasswordService;
	}
/*
	@RequestMapping(value = "/forget_password_sendmail", method=RequestMethod.GET)
	public ModelAndView listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		System.out.println("in listResult: "+request.getRequestURL());
		if(request.getParameter("email")==null){
			return new ModelAndView("YY_PasswordRegetPage");
		}
		//发送邮件
    	YY_MailSendControl ms = new YY_MailSendControl();
        ms.setSubject("OBD在线支持系统密码找回");
        String array = Random24BitArray.getArray();
        String text = "本邮件由OBD在线支持系统发送，您请求了找回密码服务，如果不是您本人操作,您可以放心地忽略此邮件。\n如果您要重设您的账户密码，请点击链接："+request.getRequestURI()+"/mail/forget_password_check.html?email="+request.getParameter("email")+"&array="+array+"\n如果点击以上链接没有反应，请将该网址复制并粘贴到新的浏览器窗口中打开\n\n此致\nOBD在线支持系统账户小组敬上";
        ms.setText(text);
        ms.setFrom("kyuhyundashizi@163.com");
        String[] result = new String[1];
        result[0] = request.getParameter("email");
        ms.setRecipients(result, "TO");
        ms.setSentDate();
        if(ms.sendMail()){
            //将array写入数据库
            userPasswordService.addRecord(request.getParameter("email"), array);
    		return new ModelAndView("/YY_PasswordRegetPage_Success");
        }else{
        	request.getSession().setAttribute("message", "邮件发送失败！请重新申请找回密码操作。"); 
        	return new ModelAndView("/YY_ErrorPage");
        }

	}
	*/
	@RequestMapping(value = "/forget_password_sendmail", method = RequestMethod.POST)
	public void listResult(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		
		try {
			String email = request.getParameter("email");
			System.out.println("email:"+email);
		   // System.out.println("in listResult: "+request.getRequestURL());
		    PrintWriter pw = null;
		    pw=response.getWriter();
		    JSONObject jsonObject = new JSONObject(); 
		    
		   if(request.getParameter("email")==null){
			     jsonObject.put("success", "false");
				 pw.print(jsonObject.toString());
				 pw.flush();
				 pw.close();
				 return;
			  //return new ModelAndView("YY_PasswordRegetPage");
		   }
		   
			//发送邮件
	    	YY_MailSendControl ms = new YY_MailSendControl();
	        ms.setSubject("OBD在线支持系统密码找回");
	        String array = Random24BitArray.getArray();
	        String text = "本邮件由OBD在线支持系统发送，您请求了找回密码服务，如果不是您本人操作,您可以放心地忽略此邮件。\n如果您要重设您的账户密码，请点击链接：http://112.124.47.134/OBDController/mail/forget_password_check.html?email="+email+"&array="+array+"\n如果点击以上链接没有反应，请将该网址复制并粘贴到新的浏览器窗口中打开\n\n此致\nOBD在线支持系统账户小组敬上";
	        ms.setText(text);
	        ms.setFrom("obd_admin@163.com");
	        String[] result = new String[1];
	        result[0] = email;
	        ms.setRecipients(result, "TO");
	        ms.setSentDate();
	        if(ms.sendMail()){
	            //将array写入数据库
	            userPasswordService.addRecord(email, array);
	            jsonObject.put("success", "true");
				 pw.print(jsonObject.toString());
				 pw.flush();
				 pw.close();
				 return;
	        }else{
	        	 jsonObject.put("success", "false");
				 pw.print(jsonObject.toString());
				 pw.flush();
				 pw.close();
				 return;	
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("I'm here!!!!");
			e.printStackTrace();
		}
	}
	//用户登录注册用邮箱点击链接，链接到这里，判断链接里的参数跟数据库里记载的是否相同
	@RequestMapping(value = "/forget_password_check", method=RequestMethod.GET)
	public ModelAndView forgetPasswordCheck(HttpServletRequest request,HttpSession session,HttpServletResponse response){
		String email = request.getParameter("email");
		String array = request.getParameter("array");
		boolean result = userPasswordService.checkArray(email, array);
		if(result==true){
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("checked", "true");
			userPasswordService.delete(email);
			return new ModelAndView("/YY_PasswordResetPage");        //验证正确to be added
			
		}else{
			request.getSession().setAttribute("message", "未通过验证！请重新申请找回密码操作。");          //验证错误
			System.out.println("这里");
			return new ModelAndView("/ErrorPage");
		}
	}
	
	@Resource
	private YY_EditPasswordService editPasswordService;

	public YY_EditPasswordService getEditPasswordService() {
		return editPasswordService;
	}

	public void setEditPasswordService(YY_EditPasswordService editPasswordService) {
		this.editPasswordService = editPasswordService;
	}
	
	@RequestMapping(value = "/password_reset", method=RequestMethod.GET)
	public void passwordReset(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		System.out.println("in passwordReset");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String responseContext = "true";
		String email=null;
		String password=null;
		if(request.getSession().getAttribute("email")==null||request.getSession().getAttribute("checked")==null||request.getParameter("password")==null){
			responseContext = "false";
			out.println(responseContext);
			out.flush();
			out.close();
		}else{
			email = (String)request.getSession().getAttribute("email");
			password = request.getParameter("password");
			if(!editPasswordService.askEditPassword(email, password)){
				responseContext = "false";
			}
			System.out.println("responseContext:"+responseContext);
			out.println(responseContext);
			out.flush();
			out.close();
		}

	}
}
