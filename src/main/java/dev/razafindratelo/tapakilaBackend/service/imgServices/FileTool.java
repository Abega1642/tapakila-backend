package dev.razafindratelo.tapakilaBackend.service.imgServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTool {

    public static String fileName(String path) {
        String[] split = path.split("/");
        return split[split.length - 1];
    }

    public static byte[] getFileBytes(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Error while reading localfile at %s", path)
            );
        }
    }

    public static String getOwnerId(String filePath) {
        return filePath.split("/")[2];
    }
}
