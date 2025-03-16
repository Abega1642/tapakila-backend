package dev.razafindratelo.tapakilaBackend.service.mailService;

import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;

public class MailMessageHandler {
    public static String getEmailAccountActivation(AccountActivation accountActivation) {
        throw new NotImplementedException("getEmailFromAccountActivation not implemented yet");
    }

    public static String getEmailResetPassword(AccountActivation accountActivation) {
        throw new NotImplementedException("getEmailFromUserResetPassword not implemented yet");
    }

    public static String getEMailReminderMessage(User user) {
        throw new NotImplementedException("getEMailFromUserReminderMessage not implemented yet");
    }
}
