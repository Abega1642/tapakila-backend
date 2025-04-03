package dev.razafindratelo.tapakilaBackend.service.qrCodeService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dev.razafindratelo.tapakilaBackend.dao.TicketsDao;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.entity.Event;
import dev.razafindratelo.tapakilaBackend.entity.QRCode;
import dev.razafindratelo.tapakilaBackend.entity.Tickets;
import dev.razafindratelo.tapakilaBackend.exception.ResourceNotFoundException;
import dev.razafindratelo.tapakilaBackend.service.eventService.EventService;
import dev.razafindratelo.tapakilaBackend.service.imgServices.FileTool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QRCodeServiceImpl implements QRCodeService {
    private final TicketsDao ticketsDao;
    private final EventService eventService;
    private final static String ROOT_PATH = "src/main/resources/static/assets/image/user";


    @Override
    public List<QRCode> generateQRCode(String eventId, List<TicketPurchase> ticketPurchase) {
        List<QRCode> qrCodes = new ArrayList<>();

        ticketPurchase.forEach(t -> {
            try {
                qrCodes.add(generateQRCode(eventId, t));
            } catch (WriterException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        return qrCodes;
    }


    @Override
    public QRCode generateQRCode(String eventId, TicketPurchase ticketPurchase) throws WriterException, IOException {
        if (ticketPurchase == null)
            throw new IllegalArgumentException("ticketPurchase cannot be null");

        if (ticketPurchase.getOwner() == null || ticketPurchase.getOwner().trim().isEmpty())
            throw new IllegalArgumentException("ticketPurchase owner cannot be null or empty");

        if (ticketPurchase.getUserEmail() == null || ticketPurchase.getUserEmail().trim().isEmpty())
            throw new IllegalArgumentException("ticketPurchase userEmail cannot be null or empty");

        if (ticketPurchase.getEventId() == null || ticketPurchase.getEventId().trim().isEmpty())
            throw new IllegalArgumentException("Ticket event id must be not null");

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(ticketPurchase.toString(), BarcodeFormat.QR_CODE, 300, 300);

        Path path = createPath(eventId, ticketPurchase);

        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);

        return new QRCode(path, MatrixToImageWriter.toBufferedImage(bitMatrix));
    }

    public byte[] findQRCodeByPath(String imagePath) {
        if (imagePath.trim().isEmpty())
            throw new IllegalArgumentException("Image path cannot be empty");

        return FileTool.getFileBytes(imagePath);
    }

    @Override
    public String findQRCodePath(String ticketsId) {
        if (ticketsId.trim().isEmpty())
            throw new IllegalArgumentException("Id must not be null");

        Tickets ticket = ticketsDao.findByTicketsId(ticketsId);

        if (ticket == null)
            throw new ResourceNotFoundException("Ticket with id " + ticketsId + " not found");

        return ticket.getQrCodePath();
    }

    @Override
    public byte[] findQRCode(String ticketsId) {
        return findQRCodeByPath(
                findQRCodePath(ticketsId)
        );
    }


    private Path createPath(String eventId, TicketPurchase ticketPurchase) throws IOException {
        final long now = System.currentTimeMillis();
        Event ev = eventService.findById(eventId);

        String fileDir = String.format(
                "%s/%s/qr_code/%s/%d%s",
                ROOT_PATH , ticketPurchase.getUserEmail().trim(), ev.getId(), now, ".png");

        File directory = new File(fileDir);

        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs();
            if (!isCreated)
                throw new IOException(
                        String.format("Failed to create directory: %s", directory.getAbsolutePath())
                );
        }

        return Paths.get(fileDir);
    }
}
