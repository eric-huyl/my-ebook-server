package com.example.toy_file_server.service;


import com.example.toy_file_server.model.File;
import com.example.toy_file_server.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

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
        File testf = new File("f3");
        testf.setPath("1.txt");
        fileRepository.save(testf);
        // fileRepository.save(new File("f2"));
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

    public List<File> getAllFiles() {
        ArrayList<File> out = new ArrayList<File>();
        fileRepository.findAll().forEach(file->{out.add(file);});
        return out;
    }
    

    // 加载文件
    public byte[] loadFileByName(String name) throws IOException {
        File file = fileRepository.findByName(name);
        if (file == null) {
            throw new IOException("File not found with name: " + name);
        }
        Path filePath = Paths.get(storageLocation).resolve(file.getPath());
        return Files.readAllBytes(filePath);
    }
}
