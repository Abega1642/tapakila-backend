package dev.razafindratelo.tapakilaBackend.controller;

import com.google.zxing.WriterException;
import dev.razafindratelo.tapakilaBackend.dto.TicketPurchase;
import dev.razafindratelo.tapakilaBackend.service.qrCodeService.QRCodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/qr-codes")
public class QRCodeController {
    private final QRCodeService qrCodeService;

    @PostMapping("{eventId}")
    public ResponseEntity<BufferedImage> generateQRCode(
            @RequestBody TicketPurchase ticketPurchase,
            @PathVariable String eventId
    ) throws WriterException, IOException {
        return new ResponseEntity<>(qrCodeService.generateQRCode(eventId, ticketPurchase).qrCodeImage(), HttpStatus.CREATED);
    }

    @GetMapping("/{ticketsId}")
    public ResponseEntity<byte[]> getQRCode(@PathVariable String ticketsId) {
        return	ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
				.body(qrCodeService.findQRCode(ticketsId));
    }
}
