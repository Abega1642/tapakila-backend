package dev.razafindratelo.tapakilaBackend.service.imgServices.eventImgService;

import dev.razafindratelo.tapakilaBackend.dao.FileDao;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.service.imgServices.ImgCreator;
import dev.razafindratelo.tapakilaBackend.service.imgServices.FileTool;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
@Service
@Slf4j
public class EventImgServiceImpl implements EventImgService {
    private final static String ROOT_PATH = "src/main/resources/static/assets/image/event";
    private final String BASE_URL = Dotenv.load().get("BASE_URL");
    private final FileDao fileDao;



    public byte[] findEventByImgPath(String imagePath) {
        if (imagePath.trim().isEmpty())
            throw new IllegalArgumentException("Image path cannot be empty");

        return FileTool.getFileBytes(imagePath);
    }


    @Override
    public byte[] findEventByEventId(String eventId) {
        if (eventId.trim().isEmpty())
            throw new IllegalArgumentException("Event id cannot be empty");

        String imagePath = fileDao.findEventImgById(eventId);

        return findEventByImgPath(imagePath);
    }

    @Override
    public String updateEventImgByIdAndPath(String eventId, MultipartFile file) {

        eventId = eventId.trim();

        if (eventId.isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");

        String fileName = file.getOriginalFilename();

        assert fileName != null;
        fileName = (fileName.contains(".png") ? fileName : fileName.concat(".png"));

        String path = String.format("%s/%s/%s", ROOT_PATH, eventId, fileName);

        fileDao.updateProfileImageByUserEmail(eventId, path);

        Event fakeEvent = Event.builder().id(eventId).build();

        saveEventImg(file, fakeEvent);

        return BASE_URL + "/event/image/" + eventId;
    }

    @Override
    public String saveEventImg(MultipartFile file, Event event) {
        String name = file.getOriginalFilename();

        try {

            if (!Objects.equals(file.getContentType(), "image/png"))
                throw new IllegalArgumentException(
                        "Photo must be an image/png type only"
                );

            if (name != null) name = name.replaceAll("[^a-zA-Z0-9.\\-]", "_");
            else throw new IllegalArgumentException("File name cannot null");


            String fileDir = String.format("%s/%s", ROOT_PATH, event.getId());
            return ImgCreator.create(file, name, fileDir);

        } catch (IOException e) {
            throw new RuntimeException("User profile image cannot be saved", e);
        }
    }
}
