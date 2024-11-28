package com.example.lats.util;

import com.example.lats.model.entity.BirthCertificate;
import com.example.lats.model.entity.DeathCertificate;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Row;

import java.time.OffsetDateTime;

@UtilityClass
public class DeathCertificateUtils {
    public DeathCertificate processDeathCertificate(Row row) {
        var deathCertificate = new DeathCertificate().setCreateAt(OffsetDateTime.now());
        row.forEach(cell -> {
            if(cell != null){
                switch (cell.getColumnIndex()){
                    case 6:
                        deathCertificate.setDeceasedId(Long.valueOf(cell.toString()));
                        break;
                    case 7:
                        deathCertificate.setTimeOfDeath(DateUtils.format(cell.toString()));
                        break;
                    case 8:
                        deathCertificate.setPlaceOfDeath(cell.toString());
                        break;
                    case 9:
                        deathCertificate.setCauseOfDeathCode(cell.toString());
                        break;
                    case 10:
                        deathCertificate.setRegistrationPlace(cell.toString());
                        break;
                    case 11:
                        deathCertificate.setConfirmedId(Long.valueOf(cell.toString()));
                        break;
                    case 12:
                        deathCertificate.setDateOfRegistration(DateUtils.format(cell.toString()));
                        break;
                    /**
                     * @TODO handle exceptions
                     */
                    default:
                        break;
                }
            }
        });
        return deathCertificate;
    }
}
