package dev.razafindratelo.tapakilaBackend.service.eventTitleService;

import dev.razafindratelo.tapakilaBackend.dto.EventTitle;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface EventTitleService {
    List<EventTitle> findAllEventTitles(Long page, Long size);
}
