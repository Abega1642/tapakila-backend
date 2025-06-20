package dev.razafindratelo.tapakilaBackend.service.ticketsService;

import com.google.zxing.WriterException;
import dev.razafindratelo.tapakilaBackend.dao.TicketsDao;
import dev.razafindratelo.tapakilaBackend.dto.EventTicketDto;
import dev.razafindratelo.tapakilaBackend.dto.PrimitiveEventTicketDto;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.entity.*;
import dev.razafindratelo.tapakilaBackend.entity.enums.PaymentProvider;
import dev.razafindratelo.tapakilaBackend.entity.enums.PaymentType;
import dev.razafindratelo.tapakilaBackend.service.eventService.EventService;
import dev.razafindratelo.tapakilaBackend.service.qrCodeService.QRCodeService;
import dev.razafindratelo.tapakilaBackend.service.ticketPriceInfoService.TicketPriceInfoService;
import dev.razafindratelo.tapakilaBackend.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public class TicketsServiceImpl implements TicketsService {

    private final String CACH_ID = "$PmD-1034e7b8-405f-4177-ba81-36a4c9543d76";
    private final PaymentMode CACH = new PaymentMode(
            CACH_ID,
            "Payment via cash for in-person transactions.",
            PaymentType.CASH,
            "N/A",
            PaymentProvider.CASH,
            LocalDateTime.now(),
            LocalDateTime.now(),
            true
    );
    private final TicketsDao ticketsDao;
	private final String BASE_URL = "http://localhost:8080/tapakila-api/qr-codes/";
    private final UserService userService;
    private final EventService eventService;
    private final TicketPriceInfoService ticketPriceInfoService;
    private final QRCodeService qrCodeService;


    @Override
    public Tickets findById(String id) {
        if (id.trim().isEmpty())
            throw new IllegalArgumentException("Id must not be null");

        return ticketsDao.findByTicketsId(id);
    }

    @Override
    public Tickets save(TicketPurchase ticket) throws IOException, WriterException {
        final User user = userService.findByEmail(ticket.getUserEmail().trim());

        long lastId = ticketsDao.findLastTicketNumber(ticket.getEventId());

        final TicketSignature ticketSignature = new TicketSignature(
                ticket.getEventId(),
                ticket.getUserEmail(),
                ticket.getOwner(),
                ticket.getTicketPriceInfoId(),
                Long.toString(lastId)
        );

        final QRCode qrCode = qrCodeService.generateQRCode(ticketSignature.getEventId(), ticketSignature);

        TicketPriceInfo tp = ticketPriceInfoService.getTicketPriceInfoById(ticket.getTicketPriceInfoId());

        Tickets tkt = new Tickets (
                lastId,
                qrCode.path().toString(),
                ticket.getOwner(),
                tp,
                user,
                ticket.getEventId(),
                CACH
        );

        return ticketsDao.save(tkt);
    }

    @Override
    public List<Tickets> saveAll(List<TicketPurchase> ticketPurchases) throws IOException {
        List<Tickets> savedTickets = new ArrayList<>();

        ticketPurchases.forEach(tpu -> {
            try {
                savedTickets.add(save(tpu));
            } catch (IOException | WriterException e) {
                throw new RuntimeException("TicketsServiceImpl.saveAll :: " + e);
            }
        });

        return savedTickets;
    }

    @Override
    public List<EventTicketDto> findAllByUserEmail(String email, Long page, Long size) {
        final long fp = (page == null) ? 1L : page;
        final long fs = (size == null) ? 10L : size;


        if (email.trim().isEmpty())
            throw new IllegalArgumentException("TicketsServiceImpl.findAllByUserEmail :: email cannot be empty");

        if (fp < 0 || fs < 0)
            throw new IllegalArgumentException("Page or size can't be null");

        Set<PrimitiveEventTicketDto> allEventRelatedToUserTicket = ticketsDao.getAllEventRelatedToUserTicket(email, fp, fs);

        var results = allEventRelatedToUserTicket.stream().map(
                t -> new EventTicketDto(eventService.findById(t.getEventId()), t.getAssociatedTickets())
        ).toList();
		
		results.forEach(etd -> {
			etd.getAssociatedTickets().forEach(tkt -> tkt.setQrCodePath(BASE_URL + tkt.getId()));
		});

		return results;
    }

	@Override
	public EventTicketDto findAllByUserEmailByEventId(String userEmail, String eventId) {
		final User user = userService.findByEmail(userEmail);
		final Event event = eventService.findById(eventId);

		if (event.getId() == null) throw new IllegalArgumentException("Event id not found");
		
		var res = ticketsDao.findAllByUserEmailAndEventId(userEmail, eventId);

		var trans = new EventTicketDto(event, res);

		trans.getAssociatedTickets().forEach(tkt -> tkt.setQrCodePath(BASE_URL + tkt.getId()));

		return trans;
	}
}
