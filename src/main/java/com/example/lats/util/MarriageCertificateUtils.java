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
                        marriageCertificate.setSignDate(DateUtils.format(cell.toString()));
                        break;
                    case 1:
                        marriageCertificate.setRegistrationPlace(cell.toString());
                        break;
                    case 2:
                        marriageCertificate.setWifeId(cell.toString());
                        break;
                    case 6:
                        marriageCertificate.setWifeDateOfBirth(DateUtils.format(cell.toString()));
                        break;
                    case 7:
                        marriageCertificate.setWifeHometown(cell.toString());
                        break;
                    case 8:
                        marriageCertificate.setHusbandId(cell.toString());
                        break;
                    case 12:
                        marriageCertificate.setHusbandDateOfBirth(DateUtils.format(cell.toString()));
                        break;
                    case 13:
                        marriageCertificate.setHusbandHometown(cell.toString());
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
