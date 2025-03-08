package dev.razafindratelo.tapakilaBackend.service.mailService;

import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendAccountActivationEmail(AccountActivation accountActivation) {
        throw new NotImplementedException("Send account activation email not implemented yet");
    }

    @Override
    public void sendResetPasswordValidationEmail(AccountActivation accountActivation) {
        throw new NotImplementedException("Send reset password validation email not implemented yet");
    }

    @Override
    public void sendReminderEmail(User user) {
        throw new NotImplementedException("Send reminder email not implemented yet");
    }
}
