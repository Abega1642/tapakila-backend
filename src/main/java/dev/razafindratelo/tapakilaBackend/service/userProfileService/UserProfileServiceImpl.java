package dev.razafindratelo.tapakilaBackend.service.userProfileService;

import dev.razafindratelo.tapakilaBackend.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final static String ROOT_PATH = "assets/images";

    @Override
    public byte[] findUserProfileByImagePath(String imagePath) {
        if (imagePath.trim().isEmpty())
            throw new IllegalArgumentException("Image path cannot be empty");

        return FileTool.getFileBytes(imagePath);
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


            String fileDir = String.format("%s/%s", ROOT_PATH, user.getEmail());
            File directory = new File(fileDir);


            if (!directory.exists()) {
                boolean isCreated = directory.mkdirs();
                if (!isCreated)
                    throw new IOException(
                            String.format("Failed to create directory: %s", directory.getAbsolutePath())
                    );

            }

            Path DIR_PATH = Paths.get(fileDir);
            InputStream inputStream = file.getInputStream();
            Path filePath = DIR_PATH.resolve(name);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return filePath.toString();

        } catch (IOException e) {
            throw new RuntimeException("User profile image cannot be saved", e);
        }
    }
}
