package dev.razafindratelo.tapakilaBackend.service.eventTitleService;

import dev.razafindratelo.tapakilaBackend.dao.EventTitleDao;
import dev.razafindratelo.tapakilaBackend.dto.EventTitle;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class EventTitleServiceImpl implements EventTitleService {
    private final EventTitleDao eventTitleDao;

    @Override
    public List<EventTitle> findAllEventTitles(Long page, Long size) {
        long fp = (page == null) ? 1L : page;
        long fs = (size == null) ? 10L : size;

        if (fp < 0L || fs < 0L) throw new IllegalArgumentException("page or size is can't be negative");

        return eventTitleDao.findAllEvTitle(fp, fs);
    }
}

