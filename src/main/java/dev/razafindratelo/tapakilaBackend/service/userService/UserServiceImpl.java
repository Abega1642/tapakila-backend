package dev.razafindratelo.tapakilaBackend.service.userService;

import dev.razafindratelo.tapakilaBackend.dao.UserDao;
import dev.razafindratelo.tapakilaBackend.dto.JwtDTO;
import dev.razafindratelo.tapakilaBackend.dto.Login;
import dev.razafindratelo.tapakilaBackend.dto.UserUpdatePassword;
import dev.razafindratelo.tapakilaBackend.dto.ValidationCode;
import dev.razafindratelo.tapakilaBackend.dto.logout.LogOutDto;
import dev.razafindratelo.tapakilaBackend.entity.AccountActivation;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Column;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.BooleanOperator;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.entity.token.RefreshToken;
import dev.razafindratelo.tapakilaBackend.exception.ActionNotAllowedException;
import dev.razafindratelo.tapakilaBackend.exception.BadRequestException;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.service.PaginationFormatUtil;
import dev.razafindratelo.tapakilaBackend.service.activationAccountService.AccountActivationService;
import dev.razafindratelo.tapakilaBackend.service.jwtService.JwtService;
import dev.razafindratelo.tapakilaBackend.service.jwtService.TokenService;
import dev.razafindratelo.tapakilaBackend.service.imgServices.userProfileService.UserProfileService;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserDao userDao;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserProfileService userProfileService;
    private final AccountActivationService accountActivationService;
    private final static String BASE_URL = Dotenv.load().get("BASE_URL") + "/user/profile/";

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
        User user = userDao.findById(email).orElseThrow(
                () -> new ResourceNotFoundException(
                        String.format("User with email %s not found", finalEmail)
                )
        );
        String imageUrl = BASE_URL + user.getEmail();
        user.setImgProfilePath(imageUrl);

        return user;
    }

    @Override
    public List<User> findAllUserByUsername(String username, Long page, Long size) {
        long finalPage = PaginationFormatUtil.normalizePage(page);
        long finalSize = PaginationFormatUtil.normalizeSize(size);

        if (username == null || username.trim().isEmpty()) {
			
        	List<User> users = findAll(finalPage, finalSize);
			users.forEach(u -> u.setImgProfilePath(BASE_URL + u.getEmail()));

			return users;
        }

        List<Criteria> filters = List.of(
                new Filter(AvailableColumn.USER_FIRST_NAME, OperatorType.CONTAINS, username),
                new Filter(BooleanOperator.OR, AvailableColumn.USER_LAST_NAME, OperatorType.CONTAINS, username)
        );

        List<User> users = userDao.findAllByCriteria(filters, finalPage, finalSize);

		
		users.forEach(u -> u.setImgProfilePath(BASE_URL + u.getEmail()));

        return users;
    }

    @Override
    public User signUp(User user) throws MessagingException {
        if (user == null) throw new BadRequestException("User cannot be null");

        if (user.getEmail().isEmpty()) throw new BadRequestException("User email cannot be empty");

        if (!EmailChecker.isValidEmail(user.getEmail())) throw new BadRequestException("Email is not valid");

        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty())
            throw new BadRequestException("User first name or user last name cannot be empty or null");

        if (user.getPassword().trim().isEmpty()) throw new BadRequestException("Password cannot be empty");

        String finalPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(finalPassword);
		
		User savedUser = userDao.save(user);
        accountActivationService.create(user.getEmail());
        return savedUser;
    }

    @Override
    public JwtDTO signIn(Login login) {
        User user = findByEmail(login.email());
        return jwtService.generate(user);
    }

    @Override
    public LogOutDto logOut(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        String accessToken = auth.replace("Bearer ", "");
        return tokenService.disableTokens(accessToken);
    }

    @Override
    public User update(User user) {
        throw new NotImplementedException("UserService.update :: Not implemented yet");
    }

    @Override
    public User updateUserPassword(UserUpdatePassword userUpdatePassword) {
        AccountActivation acc = accountActivationService.findByEmail(userUpdatePassword.userEmail());

        if (!acc.isActive()) {
            throw new BadRequestException("Activation code expired");
        }

        if (!acc.getActivationCode().equals(userUpdatePassword.validationCode())) {
            throw new BadRequestException("Activation code doesn't match");
        }

        User user = findByEmail(userUpdatePassword.userEmail());

        String encodedPassword = passwordEncoder.encode(userUpdatePassword.newPassword());

        List<Column> password = List.of(
                new Column(AvailableColumn.USER_PASSWORD, encodedPassword)
        );
        List<Filter> emailCriteria = List.of(
                new Filter(AvailableColumn.USER_EMAIL, OperatorType.EQUAL, user.getEmail())
        );

        User updatedUser = userDao.update(password, emailCriteria).getFirst();

        if (updatedUser == null) {  throw new RuntimeException("Could not update user password");   }

         updatedUser.setImgProfilePath(BASE_URL + updatedUser.getEmail());
         return updatedUser;
    }

    @Override
    public User activateAccount(ValidationCode validationCode) {
        AccountActivation accountActivation = accountActivationService.findByEmail(validationCode.email());

        if (!Objects.equals(accountActivation.getActivationCode(), validationCode.activationCode()))
            throw new BadRequestException("Invalid activation code");

        accountActivation = accountActivationService.activateAccount(validationCode.email());

        findByEmail(validationCode.email());

        User activatedUser = userDao.update(
                List.of(new Column(AvailableColumn.USER_STATUS, "true")),
                List.of(new Filter(AvailableColumn.USER_EMAIL, OperatorType.EQUAL, validationCode.email()))
        ).getFirst();

        if (activatedUser == null) throw new RuntimeException("Could not activate user");

        activatedUser.setImgProfilePath(BASE_URL + activatedUser.getEmail());
        return activatedUser;
    }

    @Override
    public JwtDTO refreshToken(String userEmail, String refreshToken) {
        LocalDateTime NOW = LocalDateTime.now();
        User user = findByEmail(userEmail);
        RefreshToken correspondingRefreshToken = tokenService.findByRefreshToken(refreshToken);

        if (!user.getEmail().equals(correspondingRefreshToken.getUserEmail()))
            throw new ActionNotAllowedException(
                    "UserService.refreshToken :: refresh token doesn't match to the user " + user.getEmail()
            );

        if (!correspondingRefreshToken.isValid() || correspondingRefreshToken.getExpiresAt().isBefore(NOW))
            throw new ActionNotAllowedException(
                    "UserService.refreshToken :: refresh token has expired"
            );

        return jwtService.generate(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }
}
