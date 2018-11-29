package com.hello.pojo;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.hello.config.MailContentTypeEnum;

public class MailSender {
	private EmailEntity mail;
	
	public MailSender(EmailEntity mail) {
		this.mail = mail;
	}
	
	// 邮件实体
	
	// 设置邮件格式
	public MailSender contentType(MailContentTypeEnum typeEnum)
    {
        mail.setContentType(typeEnum.getValue());
        return this;
    }
	
	// 设置标题
	public MailSender title(String title) {
		mail.setTitle(title);
		return this;
	}
    
    // 设置请求目标邮件地址
    public MailSender targets(List<String> targets)
    {
        mail.setList(targets);
        return this;
    }
    
    // 发送邮件
    public void send() throws UnsupportedEncodingException, Exception {
    	System.out.println(mail);
    	mail.setContentType(MailContentTypeEnum.HTML.getValue());
    	 // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = mail.getAddress();
                String password = mail.getPwd();
                return new PasswordAuthentication(userName, password);
            }
        };
        final Properties props = new Properties();
        // 表示SMTP发送邮件，必须进行身份验证
        props.put("mail.smtp.auth", "true");
        //此处填写SMTP服务器
        props.put("mail.smtp.host", mail.getService());
        //设置端口号，QQ邮箱给出了两个端口465/587
        props.put("mail.smtp.port", mail.getPort());
        // 设置发送邮箱
        props.put("mail.user", mail.getAddress());
        // 设置发送邮箱的16位STMP口令
        props.put("mail.password", mail.getPwd());
        
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        String nickName = MimeUtility.encodeText(mail.getNickname());
        InternetAddress form = new InternetAddress(nickName + " <" + mail.getAddress() + ">");
        message.setFrom(form);
        
        // 设置邮件标题
        message.setSubject(mail.getTitle());
        //html发送邮件
        if(mail.getContentType().equals(MailContentTypeEnum.TEXT.getValue())) {
            // 设置邮件的内容体
            message.setContent(mail.getContent(), mail.getContentType());
        }
        //文本发送邮件
        else if(mail.getContentType().equals(MailContentTypeEnum.TEXT.getValue())){
            message.setText(mail.getContent());
        }
        //发送邮箱地址
        List<String> targets = mail.getList();
        for(int i = 0;i < targets.size();i++){
            try {
                // 设置收件人的邮箱
                InternetAddress to = new InternetAddress(targets.get(i));
                message.setRecipient(Message.RecipientType.TO, to);
                // 最后当然就是发送邮件啦
                Transport.send(message);
            }catch (Exception e)
            {
                continue;
            }

        }
    }
	
	
}
