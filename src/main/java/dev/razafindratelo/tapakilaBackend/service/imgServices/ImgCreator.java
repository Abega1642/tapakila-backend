package dev.razafindratelo.tapakilaBackend.service.imgServices;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ImgCreator{

    public static String create(MultipartFile file, String name, String fileDir) throws IOException {
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
    }
}
