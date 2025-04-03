package dev.razafindratelo.tapakilaBackend.service.eventTurnoverService;

import dev.razafindratelo.tapakilaBackend.dao.EventTurnoverDao;
import dev.razafindratelo.tapakilaBackend.entity.EventTurnover;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventTurnoverServiceImpl implements EventTurnoverService {
    private final EventTurnoverDao eventTurnoverDao;

    @Override
    public List<EventTurnover> finAll(Long page, Long size) {
        long finalPage = (page == null) ? 1 : page;
        long finalSize = (size == null) ? 10 : size;

        return eventTurnoverDao.findAll(finalPage, finalSize);
    }

    @Override
    public List<EventTurnover> finAllAtAGivenDate(LocalDate date, Long page, Long size) {
        long finalPage = (page == null) ? 1 : page;
        long finalSize = (size == null) ? 10 : size;

        return eventTurnoverDao.findAllAtAGivenDate(date, finalPage, finalSize);
    }

    @Override
    public EventTurnover findById(String id) {
        if (id.trim().isEmpty()) {
            throw new IllegalArgumentException("Event ID must not be empty");
        }

        return eventTurnoverDao.findTurnoverByEventId(id);
    }

    @Override
    public EventTurnover findByIdAtGivenDate(String id, LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null");
        }

        if (id.trim().isEmpty()) {
            throw new IllegalArgumentException("Event ID must not be empty");
        }

        return eventTurnoverDao.findTurnoverByEventId(id);
    }
}
