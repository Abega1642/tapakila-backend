package dev.razafindratelo.tapakilaBackend.service.eventDescription;

import dev.razafindratelo.tapakilaBackend.dto.EventDescription;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EventDescriptionService {
    List<EventDescription> findAllEvDescription(Long page, Long size);
}
