package dev.razafindratelo.tapakilaBackend.service.eventService;

import dev.razafindratelo.tapakilaBackend.dao.EventDao;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.exception.BadRequestException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private EventDao eventDao;

    @Override
    public Event findById(String id) {
        if (id.trim().isEmpty()) {
            throw new BadRequestException("Event id cannot be empty");
        }

        if (eventDao.findById(id).isPresent()) {
            return eventDao.findById(id).get();
        } else {
            throw new ResourceNotFoundException("Event with id " + id + " not found");
        }
    }

    @Override
    public List<Event> findAll(long page, long size) {
        if(page < 0 || size < 0) {
            throw new BadRequestException("Page and size cannot be negative");
        }
        return eventDao.findAll(page, size);
    }
}
