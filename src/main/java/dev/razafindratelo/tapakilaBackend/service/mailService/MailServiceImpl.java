package dev.razafindratelo.tapakilaBackend.service.mailService;

import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendAccountActivationEmail(AccountActivation accountActivation) throws MessagingException {
        Dotenv dotenv = Dotenv.load();
        String sender = dotenv.get("MAIL_USERNAME");

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mailFacto = new MimeMessageHelper(message, true, "UTF-8");
        String htmlContent = MailMessageHandler.getEmailAccountActivation(accountActivation);

        mailFacto.setSubject("Tapakila - Account activation code");
        mailFacto.setFrom(sender);
        mailFacto.setTo(accountActivation.getUser().getEmail());
        mailFacto.setText(htmlContent, true);

        mailSender.send(message);
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
