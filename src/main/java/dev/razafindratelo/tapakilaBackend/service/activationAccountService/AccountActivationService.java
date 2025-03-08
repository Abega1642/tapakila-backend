package dev.razafindratelo.tapakilaBackend.service.activationAccountService;

import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AccountActivationService {
    AccountActivation create(String userEmail);
    AccountActivation findByEmail(String email);
    AccountActivation findById(String id);
    List<AccountActivation> findAll(Long page, Long size);
    List<AccountActivation> findAllBetweenDates(LocalDateTime dateTime1, LocalDateTime dateTime2, Long page, Long size);
    AccountActivation activateAccount(String email);
}
