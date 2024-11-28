package com.example.lats.service;

import com.example.lats.common.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface HandleFile {
    BaseResponse<String> handleFileExcel(MultipartFile file);
}
