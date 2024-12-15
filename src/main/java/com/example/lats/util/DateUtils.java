package com.example.lats.util;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class DateUtils {
    public Date format(String date) {
        try {
            if (date == null || date.isEmpty()) {
                throw new IllegalArgumentException("Date string is null or empty.");
            }

            SimpleDateFormat dateFormat;
            if (date.contains("-")) {
                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            } else if (date.contains("/")) {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            } else {
                throw new IllegalArgumentException("Invalid date format. Expected 'dd-MM-yyyy' or 'dd/MM/yyyy'.");
            }

            // Check if the input date can be parsed correctly
            return dateFormat.parse(date);

        } catch (ParseException e) {
            System.out.println("Error parsing date: " + date);
            throw new RuntimeException("Error parsing date: " + date, e);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + date);
            throw e;  // Re-throw the exception after logging
        }
    }

    public boolean checkFormat(String date) {
        try {
            if (date == null || date.isEmpty()) {
                return false;
            }

            SimpleDateFormat dateFormat;
            if (date.contains("-")) {
                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            } else if (date.contains("/")) {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            } else {
                return false;
            }
            dateFormat.parse(date);
            return true;

        } catch (ParseException e) {
            System.out.println("Error parsing date: " + date);
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + date);
            return false;
        }
    }
}
