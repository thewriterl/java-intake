package com.example.javaintake.unit;

import com.example.javaintake.utils.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Month;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DateUtilsUnitTest {

    @Test
    @DisplayName("should parse system defined date")
    void parseValidDate() throws Exception {
        ZonedDateTime parsedDate = DateUtils.parseToZonedDateTime("July 2020");
        assertEquals(Month.JULY, parsedDate.getMonth());
    }

    @Test
    @DisplayName("should fail parsing invalid date")
    void shouldFailParsingInvalidDate() {
        assertThrows(Exception.class, () ->  DateUtils.parseToZonedDateTime("01/01/2020"));
    }

    @Test
    @DisplayName("should verify invalid date format")
    void shouldVerifyInvalidDateFormat() {
        assertFalse(DateUtils.isDateValid("01/01/2020"));
    }

    @Test
    @DisplayName("should verify valid date format")
    void shouldVerifyValidDateFormat() {
        assertTrue(DateUtils.isDateValid("July 2020"));
    }

}
