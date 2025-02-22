package com.example.my_calibre_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.my_calibre_server.File;
//  import com.example.my_calibre_server.repository.FileRepository;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@Service
public class FileStorageService {

    // 定义文件存储目录
    @Value("${file.storage.location}")
    private String storageLocation;
    @Autowired
    JdbcTemplate jdbcTemplate;

    static long count = 0;
//    @Autowired
//    private FileRepository fileRepository;
//
    public String initFiles() {
        String res = "hello";
        jdbcTemplate.execute("DROP TABLE files IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE files(id SERIAL, name VARCHAR(255), path VARCHAR(255))");
        jdbcTemplate.execute("INSERT INTO files (name, id) VALUES ('file1', 1);");
        jdbcTemplate.execute("INSERT INTO files (name, id) VALUES ('file2', 2);");
        jdbcTemplate.query("SELECT id, name FROM files", (rs, rowNum)->new File(rs.getString("name"))).forEach(file -> {System.out.println(file);});
        System.out.println(res+count);
        count++;
        return res;
    }   
//
//    public File getFileByName(String name) {
//        return fileRepository.findByName(name);
//    }
//
//    public File saveFile(File file) {
//        return fileRepository.save(file);
//    }
//
    // 存储文件
    //  public String storeFile(MultipartFile file) throws IOException {
    //      Path targetLocation = Paths.get(storageLocation).resolve(file.getOriginalFilename());
    //      Files.copy(file.getInputStream(), targetLocation);
    //      return file.getOriginalFilename();
    //  }

    // public ResponseEntity<Resource> downloadFile(int fileIndex) {
    //     
    // }
    

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
