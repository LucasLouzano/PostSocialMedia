package postSocialMedia.postSocialMedia.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import postSocialMedia.postSocialMedia.enums.StatusEmail;
import postSocialMedia.postSocialMedia.model.Email;
import postSocialMedia.postSocialMedia.repository.EmailRepository;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;
    @Autowired
    private JavaMailSender emailSender;

    @Transactional
    public Email sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);

            email.setStatusEmail(StatusEmail.SENT);

        } catch (MailException e) {
            email.setStatusEmail(StatusEmail.ERROR);
        }finally {

            return repository.save(email);
        }
    }
}
