package dev.razafindratelo.tapakilaBackend.service.eventDescriptionService;

import dev.razafindratelo.tapakilaBackend.dao.EventDescriptionDao;
import dev.razafindratelo.tapakilaBackend.dto.EventDescription;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class EventDescriptionServiceImpl implements EventDescriptionService {
    private final EventDescriptionDao eventDescriptionDao;

    @Override
    public List<EventDescription> findAllEvDescription(Long page, Long size) {
        long fp = (page == null ? 0 : page);
        long sp = (size == null ? 0 : size);

        if (fp < 0 || sp < 0) throw new IllegalArgumentException("page and sp must be greater than zero");

        return eventDescriptionDao.findAllEvDescription(fp, sp);
    }
}
