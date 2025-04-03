package dev.razafindratelo.tapakilaBackend.service;

public class PaginationFormatUtil {
    public static long normalizePage(Long page) {
        return (page == null) ? 1L : page;
    }

    public static long normalizeSize(Long size) {
        return (size == null) ? 10L : size;
    }
}
