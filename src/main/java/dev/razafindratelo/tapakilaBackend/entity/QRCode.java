package dev.razafindratelo.tapakilaBackend.entity;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public record QRCode(Path path, BufferedImage qrCodeImage) {
}
