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
            if(cell != null){
                switch (cell.getColumnIndex()){
                    case 0:
                        jobExperience.setCitizenId(Long.valueOf(cell.toString()));
                        break;
                    case 1:
                        jobExperience.setStartDate(DateUtils.format(cell.toString()));
                        break;
                    case 2:
                        jobExperience.setEndDate(DateUtils.format(cell.toString()));
                        break;
                    case 3:
                        jobExperience.setJobPosition(cell.toString());
                        break;
                    case 4:
                        jobExperience.setWorkLocation(cell.toString());
                        break;
                    case 5:
                        jobExperience.setJobDescription(cell.toString());
                        break;
                    case 6:
                        jobExperience.setEmployer(cell.toString());
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
