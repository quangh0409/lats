package com.example.lats.util;

import com.example.lats.model.entity.Citizen;
import com.example.lats.model.entity.Education;
import com.example.lats.model.entity.MaritalStatus;
import com.example.lats.model.entity.Occupation;
import lombok.experimental.UtilityClass;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.IntStream;

@UtilityClass
public class CitizenUtils {

    // Hàm xử lý 13 ô đầu tiên
    public Citizen processCitizen(Row row) {
        var citizen = new Citizen().setCreateAt(OffsetDateTime.now());
        var first13Cells = IntStream.range(0, 14)
                .mapToObj(row::getCell)
                .toList();

        first13Cells.forEach(cell ->
        {
            if (cell != null) {
                switch (cell.getColumnIndex()) {
                    case 0: {
                        citizen.setCitizenId(cell.toString());
                        break;
                    }
                    case 1: {
                        citizen.setFullName(cell.toString());
                        break;
                    }
                    case 2: {
                        citizen.setDateOfBirth(DateUtils.format(cell.toString()));
                        break;
                    }
                    case 3: {
                        citizen.setGender(cell.toString());
                        break;
                    }
                    case 4: {
                        citizen.setPlaceOfBirth(cell.toString());
                        break;
                    }
                    case 5: {
                        citizen.setHometown(cell.toString());
                        break;
                    }
                    case 6: {
                        citizen.setEthnicity(cell.toString());
                        break;
                    }
                    case 7: {
                        citizen.setReligion(cell.toString());
                        break;
                    }
                    case 8: {
                        citizen.setNationality(cell.toString());
                        break;
                    }
                    case 9: {
                        citizen.setPersonalTaxId(cell.toString());
                        break;
                    }
                    case 10: {
                        citizen.setPermanentAddress(cell.toString());
                        break;
                    }
                    case 11: {
                        citizen.setCurrentAddress(cell.toString());
                        break;
                    }
                    case 12: {
                        citizen.setRelationToHead(cell.toString());
                        break;
                    }
                    case 13: {
                        citizen.setHouseholdId(cell.toString());
                        break;
                    }
                    /**'
                     * @TODO handle exceptions
                     */
                    default:
                        break;
                }
            }

        });
        return citizen;
    }

    // Hàm xử lý 2 ô tiếp theo
    public Education processEducation(Row row, String citizenID) {
        var education = new Education().setCitizenId(citizenID).setCreateAt(OffsetDateTime.now());
        List<Cell> next2Cells = IntStream.range(14, 16)
                .mapToObj(row::getCell)
                .toList();
        next2Cells.forEach(cell ->
        {
            if (cell != null) {
                switch (cell.getColumnIndex()) {
                    case 14: {
                        education.setEducationalLevel(cell.toString());
                        break;
                    }
                    case 15: {
                        education.setTechnicalQualification(cell.toString());
                        break;
                    }
                    /**'
                     * @TODO handle exceptions
                     */
                    default:
                        break;
                }
            }
        });
        return education;
    }

    // Hàm xử lý 2 ô tiếp theo
    public Occupation processOccupation(Row row, String citizenId) {
        var occupation = new Occupation().setCitizenId(citizenId).setCreateAt(OffsetDateTime.now());
        List<Cell> next2Cells = IntStream.range(16, 18)
                .mapToObj(row::getCell)
                .toList();

        next2Cells.forEach(cell ->
        {
            System.out.print((cell != null ? cell.toString() : "null") + "\t");
            if (cell != null) {
                switch (cell.getColumnIndex()) {
                    case 16: {
                        occupation.setOccupation(cell.toString());
                        break;
                    }
                    case 17: {
                        occupation.setWorkplace(cell.toString());
                        break;
                    }
                    /**'
                     * @TODO handle exceptions
                     */
                    default:
                        break;
                }
            }
        });
        return occupation;
    }

    // Hàm xử lý 2 ô tiếp theo
    public MaritalStatus processMaritalStatus(Row row, String citizenId) {
        var maritalStatus = new MaritalStatus().setCitizenId(citizenId).setCreateAt(OffsetDateTime.now());
        List<Cell> next2Cells = IntStream.range(18, 20)
                .mapToObj(row::getCell)
                .toList();

        next2Cells.forEach(cell ->
        {
            if (cell != null) {
                switch (cell.getColumnIndex()) {
                    case 18: {
                        maritalStatus.setMaritalStatus(cell.toString());
                        break;
                    }
                    case 19: {
                        maritalStatus.setMarriageAge((long) Double.parseDouble(cell.toString()));
                        break;
                    }
                    /**'
                     * @TODO handle exceptions
                     */
                    default:
                        break;
                }
            }
        });
        return maritalStatus;
    }
}
