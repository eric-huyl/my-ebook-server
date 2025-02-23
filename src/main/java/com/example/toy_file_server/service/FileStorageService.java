package com.example.toy_file_server.service;


import com.example.toy_file_server.repository.FileRepository;
import com.example.toy_file_server.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FileStorageService {

    // 定义文件存储目录
    @Value("${file.storage.location}")
    private String storageLocation;

    @Autowired
    private FileRepository fileRepository;


    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);

    public String initFiles() {
        String out = new String();
        fileRepository.save(new File("f1"));
        fileRepository.save(new File("f2"));
        fileRepository.findAll().forEach(file -> {
            log.info(file.toString());
            out.concat(file.toString());
        });
        return out;
    }   

    public File getFileByName(String name) {
        return fileRepository.findByName(name);
    }

    public File saveFile(File file) {
        return fileRepository.save(file);
    }
    

    public List<File> listFiles() {

        File file1 = new File("3");
        File file2 = new File("4");
        
        return List.of(file1, file2);
    }

    // 加载文件
    public byte[] loadFile(String fileName) throws IOException {
        Path filePath = Paths.get(storageLocation).resolve(fileName);
        return Files.readAllBytes(filePath);
    }
}
