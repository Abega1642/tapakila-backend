package dev.razafindratelo.tapakilaBackend.service.qrCodeService;

import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Override
    public BufferedImage generateQRCode(List<TicketPurchase> ticketPurchase) {
        throw new NotImplementedException("Not implemented yet");
    }
}
