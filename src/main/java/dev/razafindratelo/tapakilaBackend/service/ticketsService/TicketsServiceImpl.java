package dev.razafindratelo.tapakilaBackend.service.ticketsService;

import com.google.zxing.WriterException;
import dev.razafindratelo.tapakilaBackend.dao.TicketsDao;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.entity.*;
import dev.razafindratelo.tapakilaBackend.entity.enums.PaymentProvider;
import dev.razafindratelo.tapakilaBackend.entity.enums.PaymentType;
import dev.razafindratelo.tapakilaBackend.service.qrCodeService.QRCodeService;
import dev.razafindratelo.tapakilaBackend.service.ticketPriceInfoService.TicketPriceInfoService;
import dev.razafindratelo.tapakilaBackend.service.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private final UserService userService;
    private final TicketPriceInfoService ticketPriceInfoService;
    private final QRCodeService qrCodeService;


    @Override
    public Tickets save(TicketPurchase ticket) throws IOException, WriterException {
        User user = userService.findByEmail(ticket.getUserEmail().trim());

        QRCode qrCode = qrCodeService.generateQRCode(ticket);

        TicketPriceInfo tp = ticketPriceInfoService.getTicketPriceInfoById(ticket.getTicketPriceInfoId());

        Tickets tkt = new Tickets (
                1,
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
                throw new RuntimeException(e);
            }
        });

        return savedTickets;
    }

    @Override
    public List<Tickets> findAllByUserEmail(String email, Long page, Long size) {
        final long fp = (page == null) ? 1L : page;
        final long fs = (size == null) ? 10L : size;

        userService.findByEmail(email.trim());

        if (email.trim().isEmpty())
            throw new IllegalArgumentException("TicketsServiceImpl.findAllByUserEmail :: email cannot be empty");

        if (fp < 0 || fs < 0)
            throw new IllegalArgumentException("Page or size can't be null");

        return ticketsDao.findAllByUserEmail(email, fp, fs);
    }
}
