package dev.razafindratelo.tapakilaBackend.service.jwtService;

import dev.razafindratelo.tapakilaBackend.dto.JwtDTO;
import dev.razafindratelo.tapakilaBackend.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    JwtDTO generate(User user);
}
