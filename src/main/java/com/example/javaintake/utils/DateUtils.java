package com.example.javaintake.utils;

import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class DateUtils {

    private static final DateTimeFormatter inputFormat = new DateTimeFormatterBuilder().parseCaseInsensitive()
            .appendPattern("MMMM yyyy")
            .toFormatter(Locale.ENGLISH);

    private DateUtils() {
        super();
    }

    public static ZonedDateTime parseToZonedDateTime(String inputDate) {
        YearMonth date = YearMonth.parse(inputDate, inputFormat);
        return date.atDay(1).atStartOfDay(ZoneId.systemDefault());
    }
    public static Boolean isDateValid(String date) {
        try {
            YearMonth.parse(date, inputFormat);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
