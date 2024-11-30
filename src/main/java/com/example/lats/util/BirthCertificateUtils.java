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
                    case 0:
                        birthCertificate.setChildId(cell.toString());
                        break;
                    case 1:
                        birthCertificate.setMotherId(cell.toString());
                        break;
                    case 2:
                        birthCertificate.setFatherId(cell.toString());
                        break;
                    case 3:
                        birthCertificate.setInformantId(cell.toString());
                        break;
                    case 4:
                        birthCertificate.setInformantRelationship(cell.toString());
                        break;
                    case 5:
                        birthCertificate.setRegistrationPlace(cell.toString());
                        break;
                    case 6:
                        birthCertificate.setRegistrationDate(DateUtils.format(cell.toString()));
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
