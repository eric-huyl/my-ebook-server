package com.example.toy_file_server.controller;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.toy_file_server.model.FileEntity;
import com.example.toy_file_server.service.FileStorageService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    // 上传文件
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") String file) {
            return "File uploaded successfully: ";
    }

    @GetMapping("/list")
    public List<FileEntity> getAllFiles() {
        return fileStorageService.getAllFiles();
    }


    // 下载文件
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        return fileStorageService.loadFileByName(fileName);
    }

    @GetMapping("/init")
    public String initFiles() {
        return fileStorageService.initFiles();
    }

}