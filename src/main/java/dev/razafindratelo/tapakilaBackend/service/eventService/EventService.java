package dev.razafindratelo.tapakilaBackend.service.eventService;

import dev.razafindratelo.tapakilaBackend.dto.FilterDto;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    Event findById(String id);
    List<Event> findAll(Long page, Long size);
    List<Event> findAllByAdminId(String email, Long page, Long size);
    Event save(Event event);
    List<Event> findEventsByFilters(List<FilterDto> filters, Long page, Long size);
}
