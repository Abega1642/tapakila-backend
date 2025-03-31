package dev.razafindratelo.tapakilaBackend.service.qrCodeService;

import com.google.zxing.WriterException;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.entity.QRCode;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public interface QRCodeService {
    List<QRCode> generateQRCode(List<TicketPurchase> ticketPurchase);
    QRCode generateQRCode(TicketPurchase ticketPurchase) throws WriterException, IOException;
}
