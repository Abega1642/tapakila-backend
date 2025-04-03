package dev.razafindratelo.tapakilaBackend.service.imgServices.userProfileService;

import dev.razafindratelo.tapakilaBackend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserProfileService {
    byte[] findUserProfileByUserEmail(String path);
    String updateProfileImageByUserEmail(String email, MultipartFile file);
    String saveUserProfileByImagePath(MultipartFile file, User user);
}
