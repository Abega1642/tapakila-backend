package dev.razafindratelo.tapakilaBackend.service.eventDescriptionService;

import dev.razafindratelo.tapakilaBackend.dto.EventDescription;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EventDescriptionService {
    List<EventDescription> findAllEvDescription(Long page, Long size);
}
