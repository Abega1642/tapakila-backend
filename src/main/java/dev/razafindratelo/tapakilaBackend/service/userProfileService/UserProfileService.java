package dev.razafindratelo.tapakilaBackend.service.userProfileService;

import dev.razafindratelo.tapakilaBackend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserProfileService {
    byte[] findUserProfileByImagePath(String imagePath);
    String saveUserProfileByImagePath(MultipartFile file, User user);
}
