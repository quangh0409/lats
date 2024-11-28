package com.example.lats.util;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class DateUtils {
    public Date format(String date) {
        try {
            SimpleDateFormat dateFormat;
            if (date.contains("-")) {
                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            } else if (date.contains("/")) {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            } else {
                throw new IllegalArgumentException("Invalid date format. Expected 'dd-MM-yyyy' or 'dd/MM/yyyy'.");
            }
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
