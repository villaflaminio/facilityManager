package it.bruffa.facilitymanager.utilities;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import it.bruffa.facilitymanager.model.dto.MailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;


@Service
public class EmailService {
    @Autowired
    private Configuration freeMarkerConfiguration;

    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;


    public Session getGmailMailServer(){
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        return session;
    }
    // Methods that sends an email using Freemarker specified template.
    public MailResponse sendEmail(String to, String subject, Map<String, Object> model, String ftlFileName) {
        MailResponse response = new MailResponse();
        Message message = new MimeMessage(getGmailMailServer());
        try {
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");


            // Get the correct template into resources/mail-templates
            Template t = freeMarkerConfiguration.getTemplate(ftlFileName + ".ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            message.setFrom(new InternetAddress(username, "NoReply"));

            message.setReplyTo(InternetAddress.parse(to, false));
            message.setSubject(subject);

            // Set up the email
            message.setContent(html, "text/html; charset=utf-8");
            message.setSentDate(new Date());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));


            Transport.send(message);

            response.setMessage(ftlFileName + " | Mail sent to : " + to);
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage(ftlFileName + "Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }
}












