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
            if (cell != null) {
                switch (cell.getColumnIndex()) {
                    case 1:
                        deathCertificate.setGender(cell.toString());
                        break;
                    case 2:
                        deathCertificate.setDateOfBirth(DateUtils.checkFormat(cell.toString()) ? DateUtils.format(cell.toString()) : null);
                        break;
                    case 5:
                        deathCertificate.setDeceasedId(cell.toString());
                        break;
                    case 6:
                        deathCertificate.setTimeOfDeath(DateUtils.checkFormat(cell.toString()) ? DateUtils.format(cell.toString()) : null);
                        break;
                    case 7:
                        deathCertificate.setPlaceOfDeath(cell.toString());
                        break;
                    case 8:
                        deathCertificate.setCauseOfDeathCode(cell.toString());
                        break;
                    case 9:
                        deathCertificate.setRegistrationPlace(cell.toString());
                        break;
                    case 10:
                        deathCertificate.setConfirmedId(cell.toString());
                        break;
                    case 11:
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
