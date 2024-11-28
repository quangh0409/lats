package com.example.lats.util;

import com.example.lats.model.entity.Household;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Row;

import java.time.OffsetDateTime;

@UtilityClass
public class HouseholdUtils {
    public Household processHousehold(Row row) {
        var household = new Household().setCreateAt(OffsetDateTime.now());
        row.forEach(cell -> {
            if (cell != null) {
                switch (cell.getColumnIndex()) {
                    case 0: {
                        household.setHouseholdId(Long.valueOf(cell.toString()));
                        break;
                    }
                    case 1: {
                        household.setHouseholdHeadId(Long.valueOf(cell.toString()));
                        break;
                    }
                    case 2: {
                        household.setNumberOfMembers(Integer.valueOf(cell.toString()));
                    }
                }
            }
        });
        return household;
    }
}