package com.example.lats.util;

import com.example.lats.model.entity.BirthCertificate;
import com.example.lats.model.entity.MarriageCertificate;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Row;

import java.time.OffsetDateTime;

@UtilityClass
public class BirthCertificateUtils {
    public BirthCertificate processBirthCertificate(Row row) {
        var birthCertificate = new BirthCertificate().setCreateAt(OffsetDateTime.now());
        row.forEach(cell -> {
            if(cell != null){
                switch (cell.getColumnIndex()){

                    case 1:
                        birthCertificate.setDateOfBirth(DateUtils.checkFormat(cell.toString()) ? DateUtils.format(cell.toString()) : null);
                        break;
                    case 2:
                        birthCertificate.setGender(cell.toString());
                        break;
                    case 4:
                        birthCertificate.setHometown(cell.toString());
                        break;
                    case 5:
                        birthCertificate.setChildId(cell.toString());
                        break;
                    case 6:
                        birthCertificate.setMotherId(cell.toString());
                        break;
                    case 7:
                        birthCertificate.setFatherId(cell.toString());
                        break;
                    case 8:
                        birthCertificate.setInformantId(cell.toString());
                        break;
                    case 9:
                        birthCertificate.setRegistrationPlace(cell.toString());
                        break;
                    case 10:
                        birthCertificate.setRegistrationDate(DateUtils.checkFormat(cell.toString()) ? DateUtils.format(cell.toString()) : null);
                        break;
                    /**
                     * @TODO handle exceptions
                     */
                    default:
                        break;
                }
            }
        });
        return birthCertificate;
    }
}
