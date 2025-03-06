package dev.razafindratelo.tapakilaBackend.service.eventService;

import dev.razafindratelo.tapakilaBackend.entity.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EventService {
    List<Event> findAll(long page, long size);
    List<Event> findAllByDate(LocalDate from, LocalDate to);
}
