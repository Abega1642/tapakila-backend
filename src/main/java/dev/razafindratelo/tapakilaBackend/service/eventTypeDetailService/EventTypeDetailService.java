package dev.razafindratelo.tapakilaBackend.service.eventTypeDetailService;

import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EventTypeDetailService {
    EventTypeDetail findById(String id);
    List<EventTypeDetail> findAll(Long page, Long size);
    EventTypeDetail save(EventTypeDetail eventTypeDetail);
}
