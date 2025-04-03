package dev.razafindratelo.tapakilaBackend.service.imgServices.eventImgService;

import dev.razafindratelo.tapakilaBackend.entity.Event;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface EventImgService {
    byte[] findEventByEventId(String eventId);
    String updateEventImgByIdAndPath(String eventId, MultipartFile file);
    String saveEventImg(MultipartFile file, Event event);
}
