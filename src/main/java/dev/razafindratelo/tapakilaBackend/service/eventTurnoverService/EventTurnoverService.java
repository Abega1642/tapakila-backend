package dev.razafindratelo.tapakilaBackend.service.eventTurnoverService;

import dev.razafindratelo.tapakilaBackend.entity.EventTurnover;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public interface EventTurnoverService {
    List<EventTurnover> finAll(Long page, Long size);
    List<EventTurnover> finAllAtAGivenDate(LocalDate date, Long page, Long size);
    EventTurnover findById(String id);
    EventTurnover findByIdAtGivenDate(String id, LocalDate date);
}
