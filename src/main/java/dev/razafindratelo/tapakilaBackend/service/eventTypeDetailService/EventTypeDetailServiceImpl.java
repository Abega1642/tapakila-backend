package dev.razafindratelo.tapakilaBackend.service.eventTypeDetailService;

import dev.razafindratelo.tapakilaBackend.dao.EventTypeDetailDao;
import dev.razafindratelo.tapakilaBackend.entity.EventTypeDetail;
import dev.razafindratelo.tapakilaBackend.exception.BadRequestException;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.service.PaginationFormatUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class EventTypeDetailServiceImpl implements EventTypeDetailService {
    private final EventTypeDetailDao eventTypeDetailDao;

    @Override
    public EventTypeDetail findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new BadRequestException("Event type id cannot be null or empty");
        }

        if (eventTypeDetailDao.findById(id).isPresent()) {
            return eventTypeDetailDao.findById(id).get();
        } else {
            throw new ResourceNotFoundException("EventType with id " + id + " not found");
        }
    }

    @Override
    public List<EventTypeDetail> findAll(Long page, Long size) {
        long finalPage = PaginationFormatUtil.normalizePage(page);
        long finalSize = PaginationFormatUtil.normalizeSize(size);

        if (page < 0 || size < 0) {
            throw new BadRequestException("Page and size cannot be negative");
        }
        return eventTypeDetailDao.findAll(finalPage, finalSize);
    }

    @Override
    public EventTypeDetail save(EventTypeDetail eventTypeDetail) {
        throw new NotImplementedException("Save event type service is not implemented yet");
    }
}
