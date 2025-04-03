package dev.razafindratelo.tapakilaBackend.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PaginationFormatUtilTest {
  
  @Test
  void page_equals_not_null() {
    Long expected = 12L;
    Long actual = PaginationFormatUtil.normalizePage(expected);
    assertEquals(expected, actual);
  }

  @Test
  void page_equals_null() {
    Long expected = 1L;
    Long actual = PaginationFormatUtil.normalizePage(null);
    assertEquals(expected, actual);
  }

  @Test
  void size_equals_not_null() {
    Long expected = 14L;
    Long actual = PaginationFormatUtil.normalizeSize(expected);
    assertEquals(expected, actual);
  }

  @Test
  void size_equals_null() {
    Long expected = 10L;
    Long actual = PaginationFormatUtil. normalizeSize(null);
    assertEquals(expected, actual);
  }
}