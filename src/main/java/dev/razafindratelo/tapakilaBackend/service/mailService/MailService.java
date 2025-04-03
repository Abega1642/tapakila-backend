package dev.razafindratelo.tapakilaBackend.service.mailService;

import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendAccountActivationEmail(AccountActivation accountActivation) throws MessagingException;
    void sendResetPasswordValidationEmail(AccountActivation accountActivation);
    void sendReminderEmail(User user);
}
