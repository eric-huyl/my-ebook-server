package com.example.toy_file_server.service;

import com.example.toy_file_server.model.FileEntity;
import com.example.toy_file_server.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
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
        FileEntity testf = new FileEntity("f3.txt");
        testf.setPath("1.txt");
        fileRepository.save(testf);
        // fileRepository.save(new File("f2"));
        fileRepository.findAll().forEach(file -> {
            log.info(file.toString());
            out.concat(file.toString());
        });
        return out;
    }

    public FileEntity getFileByName(String name) {
        return fileRepository.findByName(name);
    }

    public List<FileEntity> getAllFiles() {
        List<FileEntity> out = new ArrayList<FileEntity>();
        fileRepository.findAll().forEach(file -> {
            out.add(file);
        });
        return out;
    }

    // 加载文件
    public ResponseEntity<Resource> loadFileByName(String name) throws IOException {
        FileEntity file = fileRepository.findByName(name);
        if (file == null) {
            throw new IOException("File not found with name: " + name);
        }
        Path filePath = Paths.get(storageLocation).resolve(file.getPath());
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentDisposition = "attachment; filename=\"" + filePath.getFileName().toString() + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    public void saveFile(FileEntity file) {
        fileRepository.save(file);
    }

    public void removeFile(FileEntity file) {
        fileRepository.delete(file);
    }

    public void removeAll() {
        fileRepository.deleteAll();
    }
}
