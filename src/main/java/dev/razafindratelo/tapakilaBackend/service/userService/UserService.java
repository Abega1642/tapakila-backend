package dev.razafindratelo.tapakilaBackend.service.userService;

import dev.razafindratelo.tapakilaBackend.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {
    List<User> findAll(Long page, Long size);
    User findByEmail(String email);
    List<User> findAllUserByUsername(String username, Long page, Long size);
    User save(User user);
    User update(User user);
}
