package com.data.migrate.datamigratesis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author : ChinhLV
 * @date : 2025/02/11
 **/
@Slf4j
@RestController
@RequestMapping("/upload-file")
@CrossOrigin(origins = "*")
public class DataUploadController {
    private static final String UPLOAD_DIR = "C:/FileSource/";
    private static final String BATCH_FILE_PATH = "C:/FileSource/RunPackage.bat";
    @PostMapping("/csv")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty!");
        }

        try {
            // Tạo thư mục nếu chưa có
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Đường dẫn file mới
            Path filePath = Paths.get(UPLOAD_DIR + "DataCsv.csv");

            // Ghi đè file cũ
            Files.write(filePath, file.getBytes());

            // Chạy file BAT sau khi upload thành công
            runBatchFile();

            return ResponseEntity.ok("Upload và chạy batch thành công!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    private void runBatchFile() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(BATCH_FILE_PATH);
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
