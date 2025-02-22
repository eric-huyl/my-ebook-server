package com.example.my_calibre_server.controller;

import com.example.my_calibre_server.File;
import com.example.my_calibre_server.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    // 上传文件
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") String file) {
            // String fileName = fileStorageService.storeFile(file);
            System.out.println("file.getOriginalFilename() = " + file);
            return "File uploaded successfully: ";
    }

    @GetMapping("/list")
    public List<File> listFiles() {
        System.out.println("fileStorageService.listFiles() = " + fileStorageService.listFiles());
        return fileStorageService.listFiles();
    }

    // 下载文件
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] fileContent = fileStorageService.loadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(fileContent);
    }

    @GetMapping("/init")
    public String initFiles() {
        return fileStorageService.initFiles();
    }
//
//    @PostMapping("/new")
//    public ResponseEntity<File> createFile(@RequestBody File file) {
//        File savedFile = fileStorageService.saveFile(file);
//        return ResponseEntity.ok(savedFile);
//    }

}