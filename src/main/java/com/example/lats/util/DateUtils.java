package com.example.lats.util;

import com.example.lats.common.exception.BaseErrorCode;
import com.example.lats.common.exception.BaseException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

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
                System.out.println(date);
                throw new IllegalArgumentException("Invalid date format. Expected 'dd-MM-yyyy' or 'dd/MM/yyyy'.");
            }
            return dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println(date);
            throw new RuntimeException(e);
        }
    }
}
