package userdetails.emailusingsmtp;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
 
    String sendMailWithAttachment(EmailDetails details);   
    
}
