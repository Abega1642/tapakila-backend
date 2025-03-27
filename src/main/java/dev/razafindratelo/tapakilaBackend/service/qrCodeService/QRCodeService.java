package dev.razafindratelo.tapakilaBackend.service.qrCodeService;

import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.util.List;

@Service
public interface QRCodeService {
    BufferedImage generateQRCode(List<TicketPurchase> ticketPurchase);
}
