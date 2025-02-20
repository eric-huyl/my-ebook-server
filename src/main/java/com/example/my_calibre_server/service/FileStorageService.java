package com.example.my_calibre_server.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    // 定义文件存储目录
    @Value("${file.storage.location}")
    private String storageLocation;

    // 存储文件
    public String storeFile(MultipartFile file) throws IOException {
        Path targetLocation = Paths.get(storageLocation).resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), targetLocation);
        return file.getOriginalFilename();
    }

    // 加载文件
    public byte[] loadFile(String fileName) throws IOException {
        Path filePath = Paths.get(storageLocation).resolve(fileName);
        return Files.readAllBytes(filePath);
    }
}
