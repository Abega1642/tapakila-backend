package dev.razafindratelo.tapakilaBackend.controller;

import com.google.zxing.WriterException;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.service.qrCodeService.QRCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.awt.image.BufferedImage;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/qr-codes")
public class QRCodeController {
    private final QRCodeService qrCodeService;

    @PostMapping
    public ResponseEntity<BufferedImage> generateQRCode(@RequestBody TicketPurchase ticketPurchase) throws WriterException, IOException {
        return new ResponseEntity<>(qrCodeService.generateQRCode(ticketPurchase).qrCodeImage(), HttpStatus.CREATED);
    }
}
