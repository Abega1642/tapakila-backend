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

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;
    private final long DEFAULT_PAGE = 1;
    private final long DEFAULT_SIZE = 10;
    private final LocalDate DEFAULT_DATE = LocalDate.now();
    private final int DEFAULT_YEAR = DEFAULT_DATE.getYear();
    private final Month DEFAULT_MONTH = DEFAULT_DATE.getMonth();

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
        final long finalPage = (page == null) ? DEFAULT_PAGE : page;
        final long finalSize = (size == null) ? DEFAULT_SIZE : size;

        if (finalPage < 0 || finalSize < 0) {
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
