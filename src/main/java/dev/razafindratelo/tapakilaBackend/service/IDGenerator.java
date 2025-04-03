package dev.razafindratelo.tapakilaBackend.service;

import java.util.UUID;

public class IDGenerator {
    public static String generateId(String prefix) {
        return prefix + UUID.randomUUID();
    }
}
