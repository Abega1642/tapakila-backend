package dev.razafindratelo.tapakilaBackend.service.eventService;

import dev.razafindratelo.tapakilaBackend.dao.EventDao;
import dev.razafindratelo.tapakilaBackend.dto.EventDto;
import dev.razafindratelo.tapakilaBackend.dto.FilterDto;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.TicketPriceInfo;
import dev.razafindratelo.tapakilaBackend.entity.User;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Criteria;
import dev.razafindratelo.tapakilaBackend.entity.criteria.Filter;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.AvailableColumn;
import dev.razafindratelo.tapakilaBackend.entity.criteria.enums.OperatorType;
import dev.razafindratelo.tapakilaBackend.exception.BadRequestException;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.mapper.TicketPriceInfoMapper;
import dev.razafindratelo.tapakilaBackend.service.PaginationFormatUtil;
import dev.razafindratelo.tapakilaBackend.service.imgServices.eventImgService.EventImgService;
import dev.razafindratelo.tapakilaBackend.service.userService.UserService;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;
	private final UserService userService;
    private final EventImgService eventImgService;
    private final static String BASE_URL = Dotenv.load().get("BASE_URL") + "/event/image/";

    @Override
    public Event findById(String id) {
        if (id.trim().isEmpty())
            throw new BadRequestException("Event id cannot be empty");

        Event event = eventDao.findById(id.trim())
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " not found"));

        event.setImagePath(BASE_URL + event.getId());

        return event;
    }

    @Override
    public List<Event> findAll(Long page, Long size) {
        long DEFAULT_PAGE = 1;
        long DEFAULT_SIZE = 10;

        final long finalPage = (page == null) ? DEFAULT_PAGE : page;
        final long finalSize = (size == null) ? DEFAULT_SIZE : size;

        if (finalPage < 0 || finalSize < 0)
            throw new BadRequestException("Page and size cannot be negative");

        List<Event> events = eventDao.findAll(finalPage, finalSize);
        events.forEach(e -> e.setImagePath(BASE_URL + e.getId()));

        return events;
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

        List<Event> events = eventDao.findAllByCriteria(criteria, finalPage, finalSize);
        events.forEach(e -> e.setImagePath(BASE_URL + e.getId()));

        return events;
    }

    @Override
    public Event save(EventDto event) {
        String evId = Event.generateId();
        User createdBy = userService.findByEmail(event.getCreatedBy());

        List<TicketPriceInfo> tpInfos = new ArrayList<>();
        event.getTicketsInfo().forEach(t -> tpInfos.add(TicketPriceInfoMapper.mapFrom(t, evId)));

        Event createdEvent = Event.builder()
                .id(evId)
                .createdBy(createdBy)
                .eventTypeDetail(event.getEventTypes())
                .organizer(event.getOrganizer())
                .title(event.getTitle())
                .description(event.getDescription())
                .dateTime(event.getDateTime())
                .timeZone(event.getTimeZone())
                .location(event.getLocation())
                .locationUrl(event.getLocationUrl())
                .imagePath("src/main/resources/static/assets/image/event/no_image.png")
                .category(event.getCategory())
                .status(event.getStatus())
                .numberOfTickets(event.getNumberOfTickets())
                .maxTicketPerUser(event.getMaxTicketPerUser())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getCreatedAt())
                .leftTickets(tpInfos)
                .build();

        Event savedEvent = eventDao.save(createdEvent);
        savedEvent.setImagePath(BASE_URL + savedEvent.getId());

        return savedEvent;
    }

    @Override
    public List<Event> findEventsByFilters(List<FilterDto> filters, Long page, Long size) {
        long DEFAULT_PAGE = 1;
        long DEFAULT_SIZE = 10;

        final long fp = (page == null) ? DEFAULT_PAGE : page;
        final long fs = (size == null) ? DEFAULT_SIZE : size;

        if (fp < 0 || fs < 0)
            throw new IllegalArgumentException("Page and size cannot be negative");

        List<Criteria> criteria = new ArrayList<>(filters.stream().map(Filter::of).toList());

        List<Event> events = eventDao.findAllByCriteria(criteria, fp, fs);

        events.forEach(e -> e.setImagePath(BASE_URL + e.getId()));

        return events;
    }
}
