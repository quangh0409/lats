package com.example.lats.util;

import com.example.lats.model.entity.MarriageCertificate;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Row;

import java.time.OffsetDateTime;

@UtilityClass
public class MarriageCertificateUtils {
    public MarriageCertificate processMarriageCertificate(Row row) {
        var marriageCertificate = new MarriageCertificate().setCreateAt(OffsetDateTime.now());
        row.forEach(cell -> {
            if(cell != null){
                switch (cell.getColumnIndex()){
                    case 0:
                        marriageCertificate.setMarriageCertificateId(Long.valueOf(cell.toString()));
                        break;
                    case 1:
                        marriageCertificate.setSignDate(DateUtils.format(cell.toString()));
                        break;
                    case 2:
                        marriageCertificate.setRegistrationPlace(cell.toString());
                        break;
                    case 3:
                        marriageCertificate.setWifeId(Long.valueOf(cell.toString()));
                        break;
                    case 4:
                        marriageCertificate.setHusbandId(Long.valueOf(cell.toString()));
                        break;
                    /**
                     * @TODO handle exceptions
                     */
                    default:
                        break;
                }
            }
        });
        return marriageCertificate;
    }

}
