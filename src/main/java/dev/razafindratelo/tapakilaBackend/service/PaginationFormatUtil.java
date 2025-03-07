package dev.razafindratelo.tapakilaBackend.service;

public class PaginationFormat {
    public static void normalize(Long page, Long size) {
        page = (page == null) ? 1L : page;
        size = (size == null) ? 10L : size;
    }
}
