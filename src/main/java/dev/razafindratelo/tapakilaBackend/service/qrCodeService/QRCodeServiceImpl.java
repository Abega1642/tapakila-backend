package dev.razafindratelo.tapakilaBackend.service.qrCodeService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.entity.QRCode;
import dev.razafindratelo.tapakilaBackend.exception.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {
    private final static String ROOT_PATH = "src/main/resources/static/assets/image/user";


    @Override
    public List<QRCode> generateQRCode(List<TicketPurchase> ticketPurchase) {
        List<QRCode> qrCodes = new ArrayList<>();

        ticketPurchase.forEach(t -> {
            try {
                qrCodes.add(generateQRCode(t));
            } catch (WriterException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        return qrCodes;
    }


    @Override
    public QRCode generateQRCode(TicketPurchase ticketPurchase) throws WriterException, IOException {
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

        Path path = createPath(ticketPurchase);

        MatrixToImageWriter.writeToPath(bitMatrix, "png", path);

        return new QRCode(path, MatrixToImageWriter.toBufferedImage(bitMatrix));
    }




    private Path createPath(TicketPurchase ticketPurchase) throws IOException {
        final long now = System.currentTimeMillis();

        String fileDir = String.format(
                "%s/%s/qr_code/%d%s",
                ROOT_PATH , ticketPurchase.getUserEmail().trim(), now, ".png");

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
