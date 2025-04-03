package dev.razafindratelo.tapakilaBackend.service.qrCodeService;

import com.google.zxing.WriterException;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.entity.QRCode;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public interface QRCodeService {
    List<QRCode> generateQRCode(String eventId, List<TicketPurchase> ticketPurchase);
    QRCode generateQRCode(String eventId, TicketPurchase ticketPurchase) throws WriterException, IOException;
    String findQRCodePath(String ticketsId);
    byte[] findQRCode(String ticketsId);
}
