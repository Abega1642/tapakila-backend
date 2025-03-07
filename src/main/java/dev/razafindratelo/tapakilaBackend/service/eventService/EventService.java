package dev.razafindratelo.tapakilaBackend.service.eventService;

import dev.razafindratelo.tapakilaBackend.entity.Event;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EventService {
    Event findById(String id);
    List<Event> findAll(long page, long size);

}
