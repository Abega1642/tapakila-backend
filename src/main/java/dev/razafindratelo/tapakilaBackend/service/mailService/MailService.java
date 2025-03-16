package dev.razafindratelo.tapakilaBackend.service.mailService;

import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendAccountActivationEmail(AccountActivation accountActivation);
    void sendResetPasswordValidationEmail(AccountActivation accountActivation);
    void sendReminderEmail(User user);
}
