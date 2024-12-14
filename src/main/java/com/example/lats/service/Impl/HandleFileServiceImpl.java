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
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
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
    private static final int BATCH_SIZE = 1000;

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
                    throw new BaseException(ErrorCode.SHEET_NOT_SUPPORT);
            }

            return BaseResponse.ok("File uploaded and processed successfully.");
        } catch (IOException e) {
            return BaseResponse.badRequest("Failed to process the file.");
        }
    }

    private void handleFileCitizen(Sheet sheet) {
        AtomicInteger counter = new AtomicInteger();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1) // Skip the header row
                .collect(Collectors.groupingBy(row -> counter.incrementAndGet() / BATCH_SIZE))
                .values()
                .forEach(batch -> {
                    List<Citizen> citizens = new ArrayList<>();
                    List<Education> educations = new ArrayList<>();
                    List<Occupation> occupations = new ArrayList<>();
                    List<MaritalStatus> maritalStatuses = new ArrayList<>();

                    batch.forEach(row -> {
                        var citizen = CitizenUtils.processCitizen(row);
                        var education = CitizenUtils.processEducation(row, citizen.getCitizenId());
                        var occupation = CitizenUtils.processOccupation(row, citizen.getCitizenId());
                        var maritalStatus = CitizenUtils.processMaritalStatus(row, citizen.getCitizenId());

                        citizens.add(citizen);
                        educations.add(education);
                        occupations.add(occupation);
                        maritalStatuses.add(maritalStatus);
                    });

                    saveDataInBatches(citizens, educations, occupations, maritalStatuses, BATCH_SIZE / 10);
                });


    }

    private void handleFileHousehold(Sheet sheet) {
        var households = new ArrayList<Household>();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .forEach(row -> {
                    households.add(HouseholdUtils.processHousehold(row));
                });
        householdRepository.mergeAllAndFlush(households);
    }

    private void handleFileJobExperience(Sheet sheet) {

        AtomicInteger counter = new AtomicInteger();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1) // Skip the header row
                .collect(Collectors.groupingBy(row -> counter.incrementAndGet() / BATCH_SIZE))
                .values()
                .forEach(batch -> {
                    var jobExperiences = new ArrayList<JobExperience>();
                    batch.forEach(row -> {
                        jobExperiences.add(JobExperienceUtils.processJobExperience(row));
                    });
                    jobExperienceRepository.mergeAllAndFlush(jobExperiences);
                });
    }

    private void handleFileMarriageCertificate(Sheet sheet) {
        AtomicInteger counter = new AtomicInteger();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .collect(Collectors.groupingBy(row -> counter.incrementAndGet() / BATCH_SIZE))
                .values()
                .forEach(batch -> {
                    var marriageCertificates = new ArrayList<MarriageCertificate>();
                    batch.forEach(row -> {
                        marriageCertificates.add(MarriageCertificateUtils.processMarriageCertificate(row));
                    });
                    marriageCertificateRepository.mergeAllAndFlush(marriageCertificates);
                });
    }

    private void handleFileBirthCertificate(Sheet sheet) {
        AtomicInteger counter = new AtomicInteger();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .collect(Collectors.groupingBy(row -> counter.incrementAndGet() / BATCH_SIZE))
                .values()
                .forEach(batch -> {
                    var birthCertificates = new ArrayList<BirthCertificate>();
                    batch.forEach(row -> {
                        birthCertificates.add(BirthCertificateUtils.processBirthCertificate(row));
                    });
                    birthCertificateRepository.mergeAllAndFlush(birthCertificates);
                });
    }

    private void handleFileDeathCertificate(Sheet sheet) {
        AtomicInteger counter = new AtomicInteger();
        StreamSupport.stream(sheet.spliterator(), false)
                .skip(1)
                .collect(Collectors.groupingBy(row -> counter.incrementAndGet() / BATCH_SIZE))
                .values()
                .forEach(batch -> {
                    var deathCertificates = new ArrayList<DeathCertificate>();
                    batch.forEach(row -> {
                        deathCertificates.add(DeathCertificateUtils.processDeathCertificate(row));
                    });
                    deathCertificateRepository.mergeAllAndFlush(deathCertificates);
                });
    }

    public void saveCitizensInBatchesUsingStream(List<Citizen> citizens, int batchSize) {
        if (citizens == null || citizens.isEmpty()) {
            return;
        }

        AtomicInteger counter = new AtomicInteger(0);

        // Sử dụng Stream để chia danh sách thành các batch
        citizens.stream()
                .collect(Collectors.groupingBy(citizen -> counter.getAndIncrement() / batchSize))
                .values()
                .forEach(batch -> {
                    try {
                        // Đẩy batch xuống DB
                        citizenRepository.mergeAllAndFlush(batch);
                    } catch (Exception e) {
                        // Xử lý lỗi nếu cần
                        System.err.println("Error while saving batch: " + e.getMessage());
                    }
                });
    }

    public void saveEducationsInBatchesUsingStream(List<Education> educations, int batchSize) {
        if (educations == null || educations.isEmpty()) {
            return;
        }

        AtomicInteger counter = new AtomicInteger(0);

        // Sử dụng Stream để chia danh sách thành các batch
        educations.stream()
                .collect(Collectors.groupingBy(education -> counter.getAndIncrement() / batchSize))
                .values()
                .forEach(batch -> {
                    try {
                        // Đẩy batch xuống DB
                        educationRepository.mergeAllAndFlush(batch);
                    } catch (Exception e) {
                        // Xử lý lỗi nếu cần
                        System.err.println("Error while saving batch of educations: " + e.getMessage());
                    }
                });
    }

    public void saveOccupationsInBatchesUsingStream(List<Occupation> occupations, int batchSize) {
        if (occupations == null || occupations.isEmpty()) {
            return;
        }

        AtomicInteger counter = new AtomicInteger(0);

        // Sử dụng Stream để chia danh sách thành các batch
        occupations.stream()
                .collect(Collectors.groupingBy(occupation -> counter.getAndIncrement() / batchSize))
                .values()
                .forEach(batch -> {
                    try {
                        // Đẩy batch xuống DB
                        occupationRepository.mergeAllAndFlush(batch);
                    } catch (Exception e) {
                        // Xử lý lỗi nếu cần
                        System.err.println("Error while saving batch of occupations: " + e.getMessage());
                    }
                });
    }

    public void saveMaritalStatusesInBatchesUsingStream(List<MaritalStatus> maritalStatuses, int batchSize) {
        if (maritalStatuses == null || maritalStatuses.isEmpty()) {
            return;
        }

        AtomicInteger counter = new AtomicInteger(0);

        // Sử dụng Stream để chia danh sách thành các batch
        maritalStatuses.stream()
                .collect(Collectors.groupingBy(status -> counter.getAndIncrement() / batchSize))
                .values()
                .forEach(batch -> {
                    try {
                        // Đẩy batch xuống DB
                        maritalStatusRepository.mergeAllAndFlush(batch);
                    } catch (Exception e) {
                        // Xử lý lỗi nếu cần
                        System.err.println("Error while saving batch of marital statuses: " + e.getMessage());
                    }
                });
    }

    public void saveDataInBatches(List<Citizen> citizens, List<Education> educations, List<Occupation> occupations, List<MaritalStatus> maritalStatuses, int batchSize) {
        // Chạy song song các tác vụ lưu dữ liệu
        CompletableFuture<Void> citizensFuture = CompletableFuture.runAsync(() -> saveCitizensInBatchesUsingStream(citizens, batchSize));
        CompletableFuture<Void> educationsFuture = CompletableFuture.runAsync(() -> saveEducationsInBatchesUsingStream(educations, batchSize));
        CompletableFuture<Void> occupationsFuture = CompletableFuture.runAsync(() -> saveOccupationsInBatchesUsingStream(occupations, batchSize));
        CompletableFuture<Void> maritalStatusesFuture = CompletableFuture.runAsync(() -> saveMaritalStatusesInBatchesUsingStream(maritalStatuses, batchSize));

        // Chờ cho tất cả các tác vụ hoàn thành
        CompletableFuture.allOf(citizensFuture, educationsFuture, occupationsFuture, maritalStatusesFuture).join();
    }
}


