package dev.razafindratelo.tapakilaBackend.service.activationAccountService;

import dev.razafindratelo.tapakilaBackend.dao.AccountActivationDao;
import dev.razafindratelo.tapakilaBackend.dao.UserDao;
import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.exception.BadRequestException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.service.IDGenerator;
import dev.razafindratelo.tapakilaBackend.service.PaginationFormatUtil;
import dev.razafindratelo.tapakilaBackend.service.mailService.MailService;
import dev.razafindratelo.tapakilaBackend.service.userService.EmailChecker;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountActivationServiceImpl implements AccountActivationService {
    private final AccountActivationDao accountActivationDao;
    private final UserDao userDao;
    private final MailService mailService;
    private static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.now();

    @Override
    public AccountActivation create(String userEmail) throws MessagingException {
        String finalEmail = userEmail.trim();

        if (finalEmail.isEmpty()) throw new BadRequestException("Email cannot be empty");

        if (!EmailChecker.isValidEmail(userEmail)) throw new BadRequestException("Email is not a valid email");

        User correspondingUser = userDao.findById(userEmail).orElseThrow(
                () -> new ResourceNotFoundException("User with email " + finalEmail + "not found.")
        );

        AccountActivation accountActivation = new AccountActivation(
                IDGenerator.generateId("$AcA-"),
                correspondingUser
        );

        mailService.sendAccountActivationEmail(accountActivation);
        return accountActivationDao.save(accountActivation);
    }

    @Override
    public AccountActivation findByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new BadRequestException("User email cannot be null or empty");
        }

        return accountActivationDao.findByUserEmail(email.trim())
                .orElseThrow(
                () -> new ResourceNotFoundException(
                        "Activation account not found"
                )
        );
    }

    @Override
    public AccountActivation findById(String id) {
        if (id == null || id.isEmpty()) {
            throw new BadRequestException("User id cannot be null or empty");
        }
        return accountActivationDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Activation account not found"
                )
        );
    }

    @Override
    public List<AccountActivation> findAll(Long page, Long size) {
        if (page < 0 || size < 0) {
            throw new BadRequestException(
                    "Page or size cannot be negative. Do not put any number instead if you don't want to specify them."
            );
        }

        long finalPage = PaginationFormatUtil.normalizePage(page);
        long finalSize = PaginationFormatUtil.normalizePage(size);

        return accountActivationDao.findAll(finalPage, finalSize);
    }

    @Override
    public List<AccountActivation> findAllBetweenDates(LocalDateTime dateTime1, LocalDateTime dateTime2, Long page,Long size) {
        List<LocalDateTime> dateInterval = new ArrayList<>();
        long finalPage = PaginationFormatUtil.normalizePage(page);
        long finalSize = PaginationFormatUtil.normalizePage(size);

        if (dateTime1 == null && dateTime2 != null) {
            dateTime1 = dateTime2.minusDays(2);
        } else if (dateTime1 != null && dateTime2 == null) {
            dateTime2 = dateTime1.plusDays(2);
        }

        assert dateTime1 != null;
        if (dateTime1.isAfter(dateTime2)) {
            dateInterval.add(dateTime2);
            dateInterval.add(dateTime1);
        }

        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.ACCOUNT_ACTIVATION_CREATED_AT, OperatorType.BETWEEN, dateInterval)
        );

        return accountActivationDao.findAllByCriteria(criteria, finalPage, finalSize);
    }

    @Override
    public AccountActivation activateAccount(String email) {
        LocalDateTime NOW = LocalDateTime.now();
        if (email == null || email.isEmpty()) {
            throw new BadRequestException("User email cannot be null or empty");
        }
        return accountActivationDao.update(email, NOW).getFirst();
    }
}
