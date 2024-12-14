package com.example.lats.util;

import com.example.lats.model.entity.JobExperience;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Row;

import java.time.OffsetDateTime;

@UtilityClass
public class JobExperienceUtils {
    public JobExperience processJobExperience(Row row) {
        var jobExperience = new JobExperience().setCreateAt(OffsetDateTime.now());
        row.forEach(cell -> {
            if (cell != null) {
                switch (cell.getColumnIndex()) {
                    case 0:
                        jobExperience.setCitizenId(cell.toString());
                        break;
                    case 2:
                        jobExperience.setGender(cell.toString());
                        break;
                    case 3:
                        jobExperience.setDateOfBirth(DateUtils.checkFormat(cell.toString()) ? DateUtils.format(cell.toString()) : null);
                        break;
                    case 4:
                        jobExperience.setHometown(cell.toString());
                        break;
                    case 5:
                        jobExperience.setStartDate(DateUtils.checkFormat(cell.toString()) ? DateUtils.format(cell.toString()) : null);
                        break;
                    case 6:
                        jobExperience.setEndDate(DateUtils.checkFormat(cell.toString()) ? DateUtils.format(cell.toString()) : null);
                        break;
                    case 7:
                        jobExperience.setJobPosition(cell.toString());
                        break;
                    case 8:
                        jobExperience.setWorkLocation(cell.toString());
                        break;
                    /**
                     * @TODO handle exceptions
                     */
                    default:
                        break;
                }
            }
        });
        return jobExperience;
    }
}
