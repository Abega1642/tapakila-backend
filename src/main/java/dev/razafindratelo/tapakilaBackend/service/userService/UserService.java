package dev.razafindratelo.tapakilaBackend.service.userService;

import dev.razafindratelo.tapakilaBackend.dto.JwtDTO;
import dev.razafindratelo.tapakilaBackend.dto.Login;
import dev.razafindratelo.tapakilaBackend.dto.UserUpdatePassword;
import dev.razafindratelo.tapakilaBackend.dto.ValidationCode;
import dev.razafindratelo.tapakilaBackend.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    List<User> findAll(Long page, Long size);
    User findByEmail(String email);
    List<User> findAllUserByUsername(String username, Long page, Long size);
    User signUp(User user) throws MessagingException;
    JwtDTO signIn(Login login);
    User update(User user);
    User updateUserPassword(UserUpdatePassword userUpdatePassword);
    User activateAccount(ValidationCode validationCode);

}
