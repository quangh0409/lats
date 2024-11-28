package com.example.lats.controller;

import com.example.lats.common.BaseResponse;
import com.example.lats.service.HandleFile;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class HandleFileController {

    private final HandleFile handleFile;

    @PostMapping("/upload")
    public BaseResponse<String> handleFileExcel(@RequestParam("file") MultipartFile file){
            return handleFile.handleFileExcel(file);
    }
}
