package dev.razafindratelo.tapakilaBackend.service.imgServices.userProfileService;

import dev.razafindratelo.tapakilaBackend.dao.FileDao;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.service.imgServices.FileTool;
import dev.razafindratelo.tapakilaBackend.service.imgServices.ImgCreator;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final static String ROOT_PATH = "src/main/resources/static/assets/image/user";
    final String BASE_URL = Dotenv.load().get("BASE_URL");
    private final FileDao fileDao;


    public byte[] findUserProfileByImagePath(String imagePath) {
        if (imagePath.trim().isEmpty())
            throw new IllegalArgumentException("Image path cannot be empty");

        return FileTool.getFileBytes(imagePath);
    }

    @Override
    public byte[] findUserProfileByUserEmail(String userEmail) {
        if (userEmail.trim().isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");

        String imagePath = fileDao.findProfileByUserEmail(userEmail);
		
        return findUserProfileByImagePath(imagePath);
    }

    @Override
    public String updateProfileImageByUserEmail(String email, MultipartFile file) {
        email = email.trim();

        if (email.isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");

        String fileName = file.getOriginalFilename();

        assert fileName != null;
        fileName = (fileName.contains(".png") ? fileName : fileName.concat(".png"));

        String path = String.format("%s/%s/%s", ROOT_PATH , email, fileName);

        String finalPath = fileDao.updateProfileImageByUserEmail(email, path);
        User fakeUser = new User();
        fakeUser.setEmail(email);

        String savedPath = saveUserProfileByImagePath(file, fakeUser);

        return BASE_URL + "/user/profile/" + email;
    }

    @Override
    public String saveUserProfileByImagePath(MultipartFile file, User user) {
        String name = file.getOriginalFilename();

        try {

            if (!Objects.equals(file.getContentType(), "image/png"))
                throw new IllegalArgumentException(
                        "Photo must be an image/png type only"
                );

            if (name != null) name = name.replaceAll("[^a-zA-Z0-9.\\-]", "_");
            else throw new IllegalArgumentException("File name cannot null");


            String fileDir = String.format("%s/%s", ROOT_PATH , user.getEmail());
            return ImgCreator.create(file, name, fileDir);

        } catch (IOException e) {
            throw new RuntimeException("User profile image cannot be saved", e);
        }
    }







}
