package dev.razafindratelo.tapakilaBackend.service.qrCodeService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Override
    public BufferedImage generateQRCode(List<TicketPurchase> ticketPurchase) {
        throw new NotImplementedException("not implemented yet");
    }

    private BufferedImage generateQRCode(TicketPurchase ticketPurchase) throws WriterException {
        if (ticketPurchase == null)
            throw new IllegalArgumentException("ticketPurchase cannot be null");

        if (ticketPurchase.getOwner() == null || ticketPurchase.getOwner().trim().isEmpty())
            throw new IllegalArgumentException("ticketPurchase owner cannot be null or empty");

        if (ticketPurchase.getUserEmail() == null || ticketPurchase.getUserEmail().trim().isEmpty())
            throw new IllegalArgumentException("ticketPurchase userEmail cannot be null or empty");

        if (ticketPurchase.getEventId() == null || ticketPurchase.getEventId().trim().isEmpty())
            throw new IllegalArgumentException("Ticket event id must be not null");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(ticketPurchase.toString(), BarcodeFormat.QR_CODE, 250, 250);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
