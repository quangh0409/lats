package com.example.lats.service.Impl;

import com.example.lats.common.exception.BaseException;
import com.example.lats.common.BaseResponse;
import com.example.lats.common.ErrorCode;
import com.example.lats.model.entity.*;
import com.example.lats.repository.*;
import com.example.lats.service.HandleFile;
import com.example.lats.util.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class HandleFileServiceImpl implements HandleFile {

    private final CitizenRepository citizenRepository;
    private final EducationRepository educationRepository;
    private final OccupationRepository occupationRepository;
    private final MaritalStatusRepository maritalStatusRepository;
    private final JobExperienceRepository jobExperienceRepository;
    private final MarriageCertificateRepository marriageCertificateRepository;
    private final HouseholdRepository householdRepository;
    private final BirthCertificateRepository birthCertificateRepository;
    private final DeathCertificateRepository deathCertificateRepository;

    @Override
    @Transactional
    public BaseResponse<String> handleFileExcel(MultipartFile file) {
        if (file.isEmpty()) {
            return BaseResponse.notFound();
        }

        try (InputStream is = file.getInputStream()) {
            // Check if the file is an Excel file
            Workbook workbook = new XSSFWorkbook(is); // For .xlsx files
            Sheet sheet = workbook.getSheetAt(0); // Read first sheet
            switch (sheet.getSheetName()) {
                case "Citizen": {
                    handleFileCitizen(sheet);
                    break;
                }
                case "Household": {
                    handleFileHousehold(sheet);
                    break;
                }
                case "JobExperience": {
                    handleFileJobExperience(sheet);
                    break;
                }
                case "MarriageCertificate": {
                    handleFileMarriageCertificate(sheet);
                    break;
                }
                case "BirthCertificate": {
                    handleFileBirthCertificate(sheet);
                    break;
                }
                case "DeathCertificate": {
                    handleFileDeathCertificate(sheet);
                    break;
                }
                default:
                    throw  new BaseException(ErrorCode.SHEET_NOT_SUPPORT);
            }

            return BaseResponse.ok("File uploaded and processed successfully.");
        } catch (IOException e) {
            return BaseResponse.badRequest("Failed to process the file.");
        }
    }

    private void handleFileCitizen(Sheet sheet) {
        var citizens = new ArrayList<Citizen>();
        var educations = new ArrayList<Education>();
        var occupations = new ArrayList<Occupation>();
        var maritalStatuses = new ArrayList<MaritalStatus>();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1) // Bỏ qua hàng đầu tiên
                .forEach(row -> {
                    var citizen = CitizenUtils.processCitizen(row);
                    var education = CitizenUtils.processEducation(row, citizen.getCitizenId());
                    var occupation = CitizenUtils.processOccupation(row, citizen.getCitizenId());
                    var maritalStatus = CitizenUtils.processMaritalStatus(row, citizen.getCitizenId());

                    citizens.add(citizen);
                    educations.add(education);
                    occupations.add(occupation);
                    maritalStatuses.add(maritalStatus);
                });
        citizenRepository.persistAllAndFlush(citizens);
        educationRepository.persistAllAndFlush(educations);
        occupationRepository.persistAllAndFlush(occupations);
        maritalStatusRepository.persistAllAndFlush(maritalStatuses);
    }


    private void handleFileHousehold(Sheet sheet) {
        var households = new ArrayList<Household>();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .forEach(row -> {
                    households.add(HouseholdUtils.processHousehold(row));
                });
        householdRepository.persistAllAndFlush(households);
    }

    private void handleFileJobExperience(Sheet sheet) {
        var jobExperiences = new ArrayList<JobExperience>();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1) // Bỏ qua hàng đầu tiên
                .forEach(row -> {
                    jobExperiences.add(JobExperienceUtils.processJobExperience(row));
                });
        jobExperienceRepository.persistAllAndFlush(jobExperiences);
    }

    private void handleFileMarriageCertificate(Sheet sheet) {
        var marriageCertificates = new ArrayList<MarriageCertificate>();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .forEach(row -> {
                    marriageCertificates.add(MarriageCertificateUtils.processMarriageCertificate(row));
                });
        marriageCertificateRepository.persistAllAndFlush(marriageCertificates);
    }

    private void handleFileBirthCertificate(Sheet sheet) {
        var birthCertificates =  new ArrayList<BirthCertificate>();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .forEach(row -> {
                    birthCertificates.add(BirthCertificateUtils.processBirthCertificate(row));
                });
        birthCertificateRepository.persistAllAndFlush(birthCertificates);
    }

    private void handleFileDeathCertificate(Sheet sheet) {
        var deathCertificates = new ArrayList<DeathCertificate>();

        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .forEach(row -> {
                    deathCertificates.add(DeathCertificateUtils.processDeathCertificate(row));
                });
        deathCertificateRepository.persistAllAndFlush(deathCertificates);
    }
}


