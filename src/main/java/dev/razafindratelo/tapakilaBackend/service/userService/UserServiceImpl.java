package dev.razafindratelo.tapakilaBackend.service.userService;

import dev.razafindratelo.tapakilaBackend.dao.UserDao;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.BooleanOperator;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.exception.BadRequestException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.service.PaginationFormatUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll(Long page, Long size) {
        long finalPage = PaginationFormatUtil.normalizePage(page);
        long finalSize = PaginationFormatUtil.normalizeSize(size);

        return userDao.findAll(finalPage, finalSize);
    }

    @Override
    public User findByEmail(String email) {
        String finalEmail = email.trim();

        if (!EmailChecker.isValidEmail(finalEmail)) {
            throw new BadRequestException(
                    String.format("Email %s is not valid", finalEmail)
            );
        }
        return userDao.findById(email).orElseThrow(
                () -> new ResourceNotFoundException(
                        String.format("User with email %s not found", finalEmail)
                )
        );
    }

    @Override
    public List<User> findAllUserByUsername(String username, Long page, Long size) {
        long finalPage = PaginationFormatUtil.normalizePage(page);
        long finalSize = PaginationFormatUtil.normalizeSize(size);

        if (username == null || username.trim().isEmpty()) {
            return findAll(finalPage, finalSize);
        }

        List<Criteria> filters = List.of(
                new Filter(AvailableColumn.USER_FIRST_NAME, OperatorType.CONTAINS, username),
                new Filter(BooleanOperator.OR, AvailableColumn.USER_LAST_NAME, OperatorType.CONTAINS, username)
        );

        return userDao.findAllByCriteria(filters, finalPage, finalSize);

    }

    @Override
    public User save(User user) {
        if (user == null) throw new BadRequestException("User cannot be null");

        if (user.getEmail().isEmpty()) throw new BadRequestException("User email cannot be empty");

        if (EmailChecker.isValidEmail(user.getEmail())) throw new BadRequestException("Email is not valid");

        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty())
            throw new BadRequestException("User first name or user last name cannot be empty or null");

        if (user.getPassword().trim().isEmpty()) throw new BadRequestException("Password cannot be empty");

        String finalPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(finalPassword);

        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }
}
