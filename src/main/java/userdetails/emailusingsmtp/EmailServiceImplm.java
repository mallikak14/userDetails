package userdetails.emailusingsmtp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailServiceImplm implements EmailService {
    @Autowired
    JavaMailSender emailSender;

    @Override
    public String sendSimpleMail(EmailDetails email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("saimallika149@gmail.com");
        message.setTo(email.getToAddress());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        emailSender.send(message);
       return "Message sent successfully";
    }

    @Override
    public String sendMailWithAttachment(EmailDetails email) {
        MimeMessage mimeMessage
            = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("saimallika149@gmail.com");
            mimeMessageHelper.setTo(email.getToAddress());
            mimeMessageHelper.setText(email.getBody());
            mimeMessageHelper.setSubject(email.getSubject());
 
            FileSystemResource file = new FileSystemResource( new String(email.getAttachment()));
            mimeMessageHelper.addAttachment( file.getFilename(), file);
            emailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
        catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }

   

    
}