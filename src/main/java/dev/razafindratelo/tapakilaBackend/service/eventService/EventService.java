package dev.razafindratelo.tapakilaBackend.service.eventService;

import dev.razafindratelo.tapakilaBackend.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    Event findById(String id);
    List<Event> findAll(Long page, Long size);
    List<Event> findAllByAdminId(String email, Long page, Long size);
    Event save(Event event);
}
