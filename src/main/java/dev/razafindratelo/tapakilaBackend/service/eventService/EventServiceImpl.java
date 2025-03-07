package dev.razafindratelo.tapakilaBackend.service.eventService;

import dev.razafindratelo.tapakilaBackend.dao.EventDao;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.exception.BadRequestException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.service.PaginationFormatUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;

    @Override
    public Event findById(String id) {
        if (id.trim().isEmpty()) {
            throw new BadRequestException("Event id cannot be empty");
        }
        return eventDao.findById(id.trim())
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found"));
    }

    @Override
    public List<Event> findAll(Long page, Long size) {
        long finalPage = PaginationFormatUtil.normalizePage(page);
        long finalSize = PaginationFormatUtil.normalizeSize(size);

        if (page < 0 || size < 0) {
            throw new BadRequestException("Page and size cannot be negative");
        }
        return eventDao.findAll(finalPage, finalSize);
    }

    @Override
    public List<Event> findAllByAdminId(String email, Long page, Long size) {
        long finalPage = PaginationFormatUtil.normalizePage(page);
        long finalSize = PaginationFormatUtil.normalizeSize(size);

        if (email.trim().isEmpty()) {
            throw new BadRequestException("Event id cannot be empty");
        }

        List<Criteria> criteria = List.of(
                new Filter(AvailableColumn.USER_EMAIL, OperatorType.EQUAL, email.trim())
        );

        return eventDao.findAllByCriteria(criteria, finalPage, finalSize);
    }
}
