package dev.razafindratelo.tapakilaBackend.service.userService;

import dev.razafindratelo.tapakilaBackend.dto.JwtDTO;
import dev.razafindratelo.tapakilaBackend.dto.Login;
import dev.razafindratelo.tapakilaBackend.dto.UserUpdatePassword;
import dev.razafindratelo.tapakilaBackend.dto.ValidationCode;
import dev.razafindratelo.tapakilaBackend.dto.logout.LogOutDto;
import dev.razafindratelo.tapakilaBackend.entity.User;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll(Long page, Long size);
    User findByEmail(String email);
    List<User> findAllUserByUsername(String username, Long page, Long size);
    User signUp(User user) throws MessagingException;
    JwtDTO signIn(Login login);
    LogOutDto logOut(HttpServletRequest request);
    User update(User user);
	User updateUserToAdmin(String userEmail);
    User updateUserPassword(UserUpdatePassword userUpdatePassword);
    User activateAccount(ValidationCode validationCode);
    JwtDTO refreshToken(String userEmail, String refreshToken);
	Long getUserCounts();
}
